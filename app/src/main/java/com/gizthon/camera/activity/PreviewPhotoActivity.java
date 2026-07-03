package com.gizthon.camera.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.print.PrintHelper;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.generalplus.GoPlusDrone.View.MultiTouchZoomableImageView;
import com.gizthon.camera.dialog.ShareDialog;
import com.jieli.stream.p016dv.running2.util.IConstant;
import com.weioa.KmedHealthIndonesia.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PreviewPhotoActivity extends Activity {
    public static final String PACKAGE_MOBILE_QQ = "com.tencent.mobileqq";
    public static final String PACKAGE_WECHAT = "com.tencent.mm";
    private FullImageAdapter adapter;
    private ArrayList<String> m_ayFilePath = null;
    private ImageView shareView;
    private android.widget.TextView printView;
    private ViewPager viewPager;

    public static void start(Context context, ArrayList<String> arrayList, int i) {
        Intent intent = new Intent(context, (Class<?>) PreviewPhotoActivity.class);
        intent.putStringArrayListExtra("FilePath", arrayList);
        intent.putExtra(IConstant.KEY_POSITION, i);
        context.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getResources().getIdentifier("preview_photo_activity", "layout", getPackageName()));
        this.viewPager = (ViewPager) findViewById(getResources().getIdentifier("pager", "id", getPackageName()));
        this.shareView = (ImageView) findViewById(getResources().getIdentifier("iv_share", "id", getPackageName()));
        Intent intent = getIntent();
        final int i = intent.getExtras().getInt(IConstant.KEY_POSITION);
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("FilePath");
        this.m_ayFilePath = stringArrayListExtra;
        FullImageAdapter fullImageAdapter = new FullImageAdapter(this, stringArrayListExtra);
        this.adapter = fullImageAdapter;
        this.viewPager.setAdapter(fullImageAdapter);
        this.viewPager.setCurrentItem(i);
        
        int printViewId = getResources().getIdentifier("iv_print", "id", getPackageName());
        this.printView = printViewId != 0 ? (android.widget.TextView) findViewById(printViewId) : null;
        if (this.printView != null) {
            this.printView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        int currentItem = viewPager.getCurrentItem();
                        String imagePath = m_ayFilePath.get(currentItem);
                        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                        if (bitmap != null) {
                            PrintHelper photoPrinter = new PrintHelper(PreviewPhotoActivity.this);
                            photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
                            photoPrinter.printBitmap("Cetak Foto Serviks", bitmap);
                        } else {
                            Toast.makeText(PreviewPhotoActivity.this, "Gagal memuat foto", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(PreviewPhotoActivity.this, "Gagal mencetak", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        
        this.shareView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareDialog.newInstance().setOnClickListener(new ShareDialog.OnClickListener() {
                    @Override
                    public void shareWhatsApp() { shareToPackage("com.whatsapp"); }

                    @Override
                    public void shareTelegram() { shareToPackage("org.telegram.messenger"); }

                    @Override
                    public void shareGmail() { shareToPackage("com.google.android.gm"); }

                    @Override
                    public void shareSystem() { shareToPackage(null); }
                }).show(PreviewPhotoActivity.this);
            }
        });
    }

    private void shareToPackage(String packageName) {
        try {
            if (packageName != null && !isInstallApp(packageName)) {
                Toast.makeText(this, "Aplikasi tidak terinstal", Toast.LENGTH_SHORT).show();
                return;
            }
            
            int currentItem = viewPager.getCurrentItem();
            File imageFile = new File(m_ayFilePath.get(currentItem));
            Uri uriFromFile;
            Intent intent = new Intent(Intent.ACTION_SEND);
            
            if (Build.VERSION.SDK_INT >= 24) {
                uriFromFile = androidx.core.content.FileProvider.getUriForFile(this, "com.weioa.GoPlusDrone.fileProvider", imageFile);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uriFromFile = Uri.fromFile(imageFile);
            }
            
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, uriFromFile);
            if (packageName != null) {
                intent.setPackage(packageName);
            }
            startActivity(Intent.createChooser(intent, "Bagikan via"));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal membagikan", Toast.LENGTH_SHORT).show();
        }
    }

    public class FullImageAdapter extends PagerAdapter {
        private Activity _activity;
        private ArrayList<String> _imagePaths;
        private LayoutInflater inflater;

        @Override // androidx.viewpager.widget.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public FullImageAdapter(Activity activity, ArrayList<String> arrayList) {
            this._activity = activity;
            this._imagePaths = arrayList;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this._imagePaths.size();
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            LayoutInflater layoutInflater = this._activity.getLayoutInflater();
            this.inflater = layoutInflater;
            View viewInflate = layoutInflater.inflate(R.layout.layout_fullscreen_image, viewGroup, false);
            ((MultiTouchZoomableImageView) viewInflate.findViewById(R.id.imgDisplay)).setImageBitmap(BitmapFactory.decodeFile(this._imagePaths.get(i)));
            ((ViewPager) viewGroup).addView(viewInflate);
            return viewInflate;
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            ((ViewPager) viewGroup).removeView((RelativeLayout) obj);
        }
    }

    public boolean isInstallApp(String str) {
        List<PackageInfo> installedPackages = getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (str.equals(installedPackages.get(i).packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
