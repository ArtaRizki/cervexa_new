package com.serenegiant.usb;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.usb.UsbDevice;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import com.jiangdg.libusbcamera.C1401R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CameraDialog extends DialogFragment {
    private static final String TAG = CameraDialog.class.getSimpleName();
    private DeviceListAdapter mDeviceListAdapter;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.serenegiant.usb.CameraDialog.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view.getId() != 16908315) {
                return;
            }
            CameraDialog.this.updateDevices();
        }
    };
    private final DialogInterface.OnClickListener mOnDialogClickListener = new DialogInterface.OnClickListener() { // from class: com.serenegiant.usb.CameraDialog.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                ((CameraDialogParent) CameraDialog.this.getActivity()).onDialogResult(true);
                return;
            }
            if (i != -1) {
                return;
            }
            Object selectedItem = CameraDialog.this.mSpinner.getSelectedItem();
            if (selectedItem instanceof UsbDevice) {
                CameraDialog.this.mUSBMonitor.requestPermission((UsbDevice) selectedItem);
                ((CameraDialogParent) CameraDialog.this.getActivity()).onDialogResult(false);
            }
        }
    };
    private Spinner mSpinner;
    protected USBMonitor mUSBMonitor;

    public interface CameraDialogParent {
        USBMonitor getUSBMonitor();

        void onDialogResult(boolean z);
    }

    public static CameraDialog showDialog(Activity activity) {
        CameraDialog cameraDialogNewInstance = newInstance();
        try {
            cameraDialogNewInstance.show(activity.getFragmentManager(), TAG);
            return cameraDialogNewInstance;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    public static CameraDialog newInstance() {
        CameraDialog cameraDialog = new CameraDialog();
        cameraDialog.setArguments(new Bundle());
        return cameraDialog;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (this.mUSBMonitor == null) {
            try {
                this.mUSBMonitor = ((CameraDialogParent) activity).getUSBMonitor();
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        if (this.mUSBMonitor != null) {
            return;
        }
        throw new ClassCastException(activity.toString() + " must implement CameraDialogParent#getUSBController");
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            getArguments();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            bundle.putAll(arguments);
        }
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(initView());
        builder.setTitle(C1401R.string.select);
        builder.setPositiveButton(R.string.ok, this.mOnDialogClickListener);
        builder.setNegativeButton(R.string.cancel, this.mOnDialogClickListener);
        builder.setNeutralButton(C1401R.string.refresh, (DialogInterface.OnClickListener) null);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setCancelable(true);
        alertDialogCreate.setCanceledOnTouchOutside(true);
        return alertDialogCreate;
    }

    private final View initView() {
        View viewInflate = getActivity().getLayoutInflater().inflate(C1401R.layout.dialog_camera, (ViewGroup) null);
        this.mSpinner = (Spinner) viewInflate.findViewById(C1401R.id.spinner1);
        this.mSpinner.setEmptyView(viewInflate.findViewById(R.id.empty));
        return viewInflate;
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        updateDevices();
        Button button = (Button) getDialog().findViewById(R.id.button3);
        if (button != null) {
            button.setOnClickListener(this.mOnClickListener);
        }
    }

    @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        ((CameraDialogParent) getActivity()).onDialogResult(true);
        super.onCancel(dialogInterface);
    }

    public void updateDevices() {
        DeviceListAdapter deviceListAdapter = new DeviceListAdapter(getActivity(), this.mUSBMonitor.getDeviceList(DeviceFilter.getDeviceFilters(getActivity(), C1401R.xml.device_filter).get(0)));
        this.mDeviceListAdapter = deviceListAdapter;
        this.mSpinner.setAdapter((SpinnerAdapter) deviceListAdapter);
    }

    private static final class DeviceListAdapter extends BaseAdapter {
        private final LayoutInflater mInflater;
        private final List<UsbDevice> mList;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public DeviceListAdapter(Context context, List<UsbDevice> list) {
            this.mInflater = LayoutInflater.from(context);
            this.mList = list == null ? new ArrayList<>() : list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mList.size();
        }

        @Override // android.widget.Adapter
        public UsbDevice getItem(int i) {
            if (i < 0 || i >= this.mList.size()) {
                return null;
            }
            return this.mList.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = this.mInflater.inflate(C1401R.layout.listitem_device, viewGroup, false);
            }
            if (view instanceof CheckedTextView) {
                UsbDevice item = getItem(i);
                ((CheckedTextView) view).setText(String.format("UVC Camera:(%x:%x:%s)", Integer.valueOf(item.getVendorId()), Integer.valueOf(item.getProductId()), item.getDeviceName()));
            }
            return view;
        }
    }
}
