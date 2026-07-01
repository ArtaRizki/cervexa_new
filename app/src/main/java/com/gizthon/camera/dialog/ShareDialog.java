package com.gizthon.camera.dialog;

import android.os.Bundle;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import com.gizthon.camera.databinding.DialogShareBinding;
import com.weioa.KmedHealthIndonesia.R;
import java.io.FileNotFoundException;

/* JADX INFO: loaded from: classes.dex */
public class ShareDialog extends CenterDialog {
    private DialogShareBinding binding;
    private OnClickListener onClickListener;

    public interface OnClickListener {
        void shareWhatsApp() throws FileNotFoundException;

        void shareTelegram() throws FileNotFoundException;

        void shareGmail() throws FileNotFoundException;

        void shareSystem() throws FileNotFoundException;
    }

    @Override // com.gizthon.camera.dialog.CenterDialog
    public int getLayoutId() {
        return R.layout.dialog_share;
    }

    @Override // com.gizthon.camera.dialog.CenterDialog
    public boolean isBottom() {
        return false;
    }

    @Override // com.gizthon.camera.dialog.CenterDialog
    public boolean isCancele() {
        return false;
    }

    public ShareDialog setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }

    public static ShareDialog newInstance() {
        ShareDialog shareDialog = new ShareDialog();
        shareDialog.setArguments(new Bundle());
        return shareDialog;
    }

    @Override // com.gizthon.camera.dialog.CenterDialog
    public void initView(ViewDataBinding viewDataBinding) {
        DialogShareBinding dialogShareBinding = (DialogShareBinding) viewDataBinding;
        this.binding = dialogShareBinding;
        dialogShareBinding.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.gizthon.camera.dialog.ShareDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ShareDialog.this.dismiss();
            }
        });
        this.binding.tvWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ShareDialog.this.onClickListener.shareWhatsApp();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ShareDialog.this.dismiss();
            }
        });
        this.binding.tvTelegram.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ShareDialog.this.onClickListener.shareTelegram();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ShareDialog.this.dismiss();
            }
        });
        this.binding.tvGmail.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ShareDialog.this.onClickListener.shareGmail();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ShareDialog.this.dismiss();
            }
        });
        this.binding.tvSystem.setOnClickListener(new View.OnClickListener() {
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    ShareDialog.this.onClickListener.shareSystem();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ShareDialog.this.dismiss();
            }
        });
    }
}
