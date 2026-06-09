package com.jieli.lib.p015dv.control.udp;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.jieli.lib.p015dv.control.json.bean.NotifyInfo;
import com.jieli.lib.p015dv.control.utils.BufChangeHex;
import com.jieli.lib.p015dv.control.utils.Constants;
import com.jieli.lib.p015dv.control.utils.Dlog;
import com.jieli.lib.p015dv.control.utils.ListenerHelper;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UdpSocketReceiver extends Thread {

    /* JADX INFO: renamed from: a */
    private String f2212a = "UdpSocketReceiver";

    /* JADX INFO: renamed from: b */
    private WeakReference<DatagramSocket> f2213b;

    /* JADX INFO: renamed from: c */
    private volatile boolean f2214c;

    public UdpSocketReceiver(DatagramSocket datagramSocket) {
        this.f2213b = new WeakReference<>(datagramSocket);
    }

    public boolean isReceiver() {
        return this.f2214c;
    }

    @Override // java.lang.Thread
    public synchronized void start() {
        this.f2214c = true;
        super.start();
    }

    public synchronized void stopReceiver() {
        Dlog.m1386w(this.f2212a, "stop receive udp message");
        this.f2214c = false;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        Dlog.m1386w(this.f2212a, "receive udp message start, isReceiver : " + this.f2214c);
        while (this.f2214c) {
            try {
                m1380a();
            } catch (IOException e) {
                e.printStackTrace();
                Dlog.m1386w(this.f2212a, "receive udp error: " + e.getMessage());
            }
        }
        Dlog.m1386w(this.f2212a, "receive udp close, isReceiver : " + this.f2214c);
    }

    /* JADX INFO: renamed from: a */
    private void m1380a() throws IOException {
        byte[] data;
        DatagramSocket datagramSocket = this.f2213b.get();
        if (datagramSocket != null) {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[2048], 2048);
            datagramSocket.receive(datagramPacket);
            int length = datagramPacket.getLength();
            Dlog.m1386w(this.f2212a, "receive udp message length : " + length);
            if (length > 0 && (data = datagramPacket.getData()) != null && data.length > 0 && length < data.length) {
                byte[] bArr = new byte[length];
                System.arraycopy(data, 0, bArr, 0, length);
                NotifyInfo notifyInfoM1379a = m1379a(bArr);
                if (notifyInfoM1379a != null) {
                    ListenerHelper.getInstance().dispatchNotify(notifyInfoM1379a);
                    return;
                }
            }
            Dlog.m1386w(this.f2212a, "receive message is null or error.");
            return;
        }
        Dlog.m1386w(this.f2212a, "udpSocket is null");
        ListenerHelper.getInstance().dispatchDeviceConnectState(5);
        stopReceiver();
    }

    /* JADX INFO: renamed from: a */
    private NotifyInfo m1379a(byte[] bArr) {
        int length;
        int i;
        int i2;
        if (bArr == null || 10 >= (length = bArr.length)) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        if (!Constants.CTP_SIGNATURE.equals(new String(bArr2))) {
            return null;
        }
        byte[] bArr3 = new byte[2];
        System.arraycopy(bArr, 4, bArr3, 0, 2);
        int iByteArrayToInt = BufChangeHex.byteArrayToInt(bArr3);
        if (iByteArrayToInt < 0 || (i = iByteArrayToInt + 6) > length) {
            return null;
        }
        byte[] bArr4 = new byte[iByteArrayToInt];
        System.arraycopy(bArr, 6, bArr4, 0, iByteArrayToInt);
        String str = new String(bArr4);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setTopic(str);
        int i3 = i + 4;
        if (i3 <= length) {
            byte[] bArr5 = new byte[4];
            System.arraycopy(bArr, i, bArr5, 0, 4);
            int iByteArrayToInt2 = BufChangeHex.byteArrayToInt(bArr5);
            if (iByteArrayToInt2 >= 0 && (i2 = i3 + iByteArrayToInt2) <= length) {
                byte[] bArr6 = new byte[iByteArrayToInt2];
                System.arraycopy(bArr, i3, bArr6, 0, iByteArrayToInt2);
                String str2 = new String(bArr6);
                if (i2 == length) {
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            JSONObject jSONObject = new JSONObject(str2);
                            notifyInfo.setErrorType(jSONObject.optInt(Constants.JSON_ERROR_NUMBER));
                            notifyInfo.setOperation(jSONObject.optString(Constants.JSON_OP));
                            if (jSONObject.has(Constants.JSON_PARAM)) {
                                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(Constants.JSON_PARAM);
                                Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                                ArrayMap<String, String> arrayMap = new ArrayMap<>();
                                while (itKeys.hasNext()) {
                                    String next = itKeys.next();
                                    arrayMap.put(next, jSONObjectOptJSONObject.optString(next));
                                }
                                notifyInfo.setParams(arrayMap);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        notifyInfo.setParams(null);
                        notifyInfo.setErrorType(0);
                        notifyInfo.setOperation(null);
                    }
                }
            }
        }
        return notifyInfo;
    }
}
