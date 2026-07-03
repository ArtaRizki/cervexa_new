package com.gizthon.camera.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import com.gizthon.camera.databinding.HelpActivityBinding;
import com.jaeger.library.StatusBarUtil;
import com.weioa.KmedHealthIndonesia.R;

/* JADX INFO: loaded from: classes.dex */
public class HelpActivity extends CameraBaseActivity {
    private android.widget.ImageView ivR;
    private android.widget.ImageView ivL;
    private android.widget.ImageView ivHelp1;
    private android.widget.ImageView ivHelp2;
    private android.view.View llLayout1;
    private android.view.View llLayout2;
    private android.view.View backBtn;

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) HelpActivity.class));
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getResources().getIdentifier("help_activity", "layout", getPackageName());
        setContentView(layoutId);
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#09B0F3"));

        this.ivR = findViewById(getResources().getIdentifier("ivR", "id", getPackageName()));
        this.ivL = findViewById(getResources().getIdentifier("ivL", "id", getPackageName()));
        this.ivHelp1 = findViewById(getResources().getIdentifier("iv_help1", "id", getPackageName()));
        this.ivHelp2 = findViewById(getResources().getIdentifier("iv_help2", "id", getPackageName()));
        this.llLayout1 = findViewById(getResources().getIdentifier("ll_layout1", "id", getPackageName()));
        this.llLayout2 = findViewById(getResources().getIdentifier("ll_layout2", "id", getPackageName()));
        this.backBtn = findViewById(getResources().getIdentifier("back", "id", getPackageName()));

        if (this.ivR != null) {
            this.ivR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (HelpActivity.this.llLayout1 != null) HelpActivity.this.llLayout1.setVisibility(View.GONE);
                    if (HelpActivity.this.llLayout2 != null) HelpActivity.this.llLayout2.setVisibility(View.VISIBLE);
                }
            });
        }
        
        if (this.ivL != null) {
            this.ivL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (HelpActivity.this.llLayout2 != null) HelpActivity.this.llLayout2.setVisibility(View.GONE);
                    if (HelpActivity.this.llLayout1 != null) HelpActivity.this.llLayout1.setVisibility(View.VISIBLE);
                }
            });
        }
        
        if (this.backBtn != null) {
            this.backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    HelpActivity.this.finish();
                }
            });
        }
        String language = getResources().getConfiguration().locale.getLanguage();
        int help1Res = getResources().getIdentifier("icon_help_bg_en", "mipmap", getPackageName());
        int help2Res = getResources().getIdentifier("icon_help_bg2_en", "mipmap", getPackageName());
        if (language.contains("zh")) {
            help1Res = getResources().getIdentifier("icon_help_bg", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2", "mipmap", getPackageName());
        } else if (language.contains("de")) {
            help1Res = getResources().getIdentifier("icon_help_bg_de", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2_de", "mipmap", getPackageName());
        } else if (language.contains("fr")) {
            help1Res = getResources().getIdentifier("icon_help_bg_fr", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2_fr", "mipmap", getPackageName());
        } else if (language.contains("es")) {
            help1Res = getResources().getIdentifier("icon_help_bg_es", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2_es", "mipmap", getPackageName());
        } else if (language.contains("it")) {
            help1Res = getResources().getIdentifier("icon_help_bg_it", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2_it", "mipmap", getPackageName());
        } else if (language.contains("ja")) {
            help1Res = getResources().getIdentifier("icon_help_bg_ja", "mipmap", getPackageName());
            help2Res = getResources().getIdentifier("icon_help_bg2_ja", "mipmap", getPackageName());
        }
        
        if (this.ivHelp1 != null && help1Res != 0) {
            this.ivHelp1.setImageResource(help1Res);
        }
        if (this.ivHelp2 != null && help2Res != 0) {
            this.ivHelp2.setImageResource(help2Res);
        }
    }
}
