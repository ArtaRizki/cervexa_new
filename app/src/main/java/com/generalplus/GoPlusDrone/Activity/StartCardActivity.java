package com.generalplus.GoPlusDrone.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import generalplus.com.GPCamLib.CamWrapper;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public class StartCardActivity extends Activity {
    protected static Thread m_connectGPWifiDeviceThread;
    protected ProgressDialog m_Dialog;
    private String strSaveDirectory;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.strSaveDirectory = Environment.getExternalStorageDirectory().getPath() + "/" + CamWrapper.CamDefaulFolderName;
        new File(this.strSaveDirectory).mkdir();
        File file = new File(Environment.getExternalStorageDirectory().getPath() + CamWrapper.SaveFileToDevicePath);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    class ConnectGPWifiDeviceRunnable implements Runnable {
        private int i32Status;
        private boolean bCheckWifiStatus = false;
        private int i32RetryCheckWifiStatusCount = 100;
        private int i32CheckWifiStatusDelayTime = 200;
        private boolean bCheckConnectStatus = false;
        private int i32RetryCount = 20;

        ConnectGPWifiDeviceRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (true == this.bCheckConnectStatus) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int iExecuteCommandLine = -1;
            try {
                try {
                    iExecuteCommandLine = StartCardActivity.this.executeCommandLine("ping -c 1 192.168.25.1", 1500L);
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            } catch (TimeoutException e5) {
                e5.printStackTrace();
            }
            this.bCheckWifiStatus = iExecuteCommandLine == 0;
            if (this.bCheckWifiStatus) {
                CamWrapper.getComWrapperInstance().GPCamConnectToDevice(CamWrapper.COMMAND_URL, CamWrapper.COMMAN_PORT);
                while (this.bCheckWifiStatus) {
                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException e6) {
                        e6.printStackTrace();
                    }
                    int iGPCamGetStatus = CamWrapper.getComWrapperInstance().GPCamGetStatus();
                    this.i32Status = iGPCamGetStatus;
                    if (iGPCamGetStatus == 0) {
                        this.bCheckConnectStatus = false;
                        this.bCheckWifiStatus = true;
                    } else if (iGPCamGetStatus == 1) {
                        this.bCheckConnectStatus = false;
                        this.bCheckWifiStatus = true;
                    } else if (iGPCamGetStatus == 2) {
                        this.bCheckConnectStatus = true;
                        this.bCheckWifiStatus = false;
                        CamWrapper.getComWrapperInstance().SetGPCamSetDownloadPath(StartCardActivity.this.strSaveDirectory);
                        CamWrapper.getComWrapperInstance().SetGPCamSendGetParameterFile(CamWrapper.ParameterFileName);
                        CamWrapper.getComWrapperInstance().GPCamGetStatus();
                    } else if (iGPCamGetStatus == 3) {
                        int i = this.i32RetryCount - 1;
                        this.i32RetryCount = i;
                        if (i == 0) {
                            this.bCheckConnectStatus = false;
                            this.bCheckWifiStatus = false;
                            CamWrapper.getComWrapperInstance().GPCamDisconnect();
                        }
                    } else if (iGPCamGetStatus == 10) {
                        int i2 = this.i32RetryCount - 1;
                        this.i32RetryCount = i2;
                        if (i2 == 0) {
                            this.bCheckConnectStatus = false;
                            this.bCheckWifiStatus = false;
                            CamWrapper.getComWrapperInstance().GPCamDisconnect();
                        }
                    }
                }
            }
            StartCardActivity.this.m_Dialog.dismiss();
            StartCardActivity.this.runOnUiThread(new Runnable() { // from class: com.generalplus.GoPlusDrone.Activity.StartCardActivity.ConnectGPWifiDeviceRunnable.1
                @Override // java.lang.Runnable
                public void run() {
                    if (ConnectGPWifiDeviceRunnable.this.bCheckConnectStatus) {
                        Intent intent = new Intent(StartCardActivity.this, (Class<?>) MainOldActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("IsCard", true);
                        intent.putExtras(bundle);
                        StartCardActivity.this.startActivity(intent);
                        return;
                    }
                    Toast.makeText(StartCardActivity.this, "Please connect to GoPlus Drone or retry to reset GoPlus Drone first.", 0).show();
                }
            });
            StartCardActivity.m_connectGPWifiDeviceThread = null;
        }
    }

    private class Worker extends Thread {
        private Integer exit;
        private final Process process;

        private Worker(Process process) {
            this.process = process;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                this.exit = Integer.valueOf(this.process.waitFor());
            } catch (InterruptedException unused) {
            }
        }
    }

    public int executeCommandLine(String str, long j) throws InterruptedException, TimeoutException, IOException {
        Process processExec = Runtime.getRuntime().exec(str);
        Worker worker = new Worker(processExec);
        worker.start();
        try {
            try {
                worker.join(j);
                if (worker.exit != null) {
                    return worker.exit.intValue();
                }
                throw new TimeoutException();
            } catch (InterruptedException e) {
                worker.interrupt();
                Thread.currentThread().interrupt();
                throw e;
            }
        } finally {
            processExec.destroy();
        }
    }
}
