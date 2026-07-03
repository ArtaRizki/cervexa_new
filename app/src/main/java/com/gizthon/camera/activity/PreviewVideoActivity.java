package com.gizthon.camera.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;
import androidx.core.content.FileProvider;
import com.gizthon.camera.view.AndroidMediaController;
import com.gizthon.camera.view.IjkVideoView;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.weioa.KmedHealthIndonesia.R;
import java.io.File;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class PreviewVideoActivity extends Activity {
    private MediaController mController;
    private ArrayList<String> m_ayFilePath = null;
    int position;
    private IjkVideoView video_view;

    public static void start(Context context, String str) {
        Uri uriFromFile;
        Intent intent = new Intent("android.intent.action.VIEW");
        File file = new File(str);
        if (Build.VERSION.SDK_INT >= 24) {
            uriFromFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileProvider", file);
        } else {
            uriFromFile = Uri.fromFile(file);
        }
        intent.setFlags(1);
        intent.setDataAndType(uriFromFile, "video/*");
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getResources().getIdentifier("preview_video_activity", "layout", getPackageName()));
        this.video_view = (IjkVideoView) findViewById(getResources().getIdentifier("video_view", "id", getPackageName()));
        Intent intent = getIntent();
        this.position = intent.getExtras().getInt(IConstant.KEY_POSITION);
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("FilePath");
        this.m_ayFilePath = stringArrayListExtra;
        String str = stringArrayListExtra.get(this.position);
        this.mController = new MediaController(this);
        this.video_view.setMediaController(new AndroidMediaController((Context) this, false));
        this.video_view.setVideoURI(Uri.parse(str));
        this.video_view.start();
    }
}
