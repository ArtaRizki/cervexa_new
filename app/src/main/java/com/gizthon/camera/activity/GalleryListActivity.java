package com.gizthon.camera.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.gizthon.camera.adapter.PhotoFragmentAdapter;
import com.gizthon.camera.fragment.PhotoListFragment;
import com.gizthon.camera.fragment.VideoListFragment;
import com.gizthon.camera.model.GalleryViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jaeger.library.StatusBarUtil;
import com.weioa.KmedHealthIndonesia.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GalleryListActivity extends CameraBaseActivity {
    private androidx.viewpager2.widget.ViewPager2 pager;
    private com.google.android.material.tabs.TabLayout tabLayout;
    private android.widget.CheckBox done;
    private android.widget.TextView tvComplete;
    private android.view.View btnDelete;
    private android.widget.TextView backBtn;
    private ArrayList<Fragment> fragments;
    private GalleryViewModel viewModel;

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) GalleryListActivity.class));
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int layoutId = getResources().getIdentifier("gallery_activity", "layout", getPackageName());
        setContentView(layoutId);
        
        GalleryViewModel galleryViewModel = new GalleryViewModel();
        this.viewModel = galleryViewModel;
        
        int pagerId = getResources().getIdentifier("pager", "id", getPackageName());
        this.pager = findViewById(pagerId);
        
        int tabLayoutId = getResources().getIdentifier("tab_layout", "id", getPackageName());
        this.tabLayout = findViewById(tabLayoutId);
        
        int doneId = getResources().getIdentifier("done", "id", getPackageName());
        this.done = findViewById(doneId);
        
        int tvCompleteId = getResources().getIdentifier("tv_complete", "id", getPackageName());
        this.tvComplete = findViewById(tvCompleteId);
        
        int backId = getResources().getIdentifier("back", "id", getPackageName());
        this.backBtn = findViewById(backId);
        
        android.view.View root = findViewById(android.R.id.content);
        if (root != null) {
            this.btnDelete = root.findViewWithTag("binding_2");
        }

        if (this.backBtn != null) {
            this.backBtn.setOnClickListener(new android.view.View.OnClickListener() {
                @Override public void onClick(android.view.View v) { onClickBack(); }
            });
        }
        if (this.done != null) {
            this.done.setOnClickListener(new android.view.View.OnClickListener() {
                @Override public void onClick(android.view.View v) { onClickDone(); }
            });
        }
        if (this.tvComplete != null) {
            this.tvComplete.setOnClickListener(new android.view.View.OnClickListener() {
                @Override public void onClick(android.view.View v) { onClickComplete(); }
            });
        }
        if (this.btnDelete != null) {
            this.btnDelete.setOnClickListener(new android.view.View.OnClickListener() {
                @Override public void onClick(android.view.View v) { onClickDelete(); }
            });
        }

        ArrayList<Fragment> arrayList = new ArrayList<>();
        this.fragments = arrayList;
        String snapsDir = getIntent().getStringExtra("snapsDir");
        String vidsDir = getIntent().getStringExtra("vidsDir");
        arrayList.add(PhotoListFragment.newInstance(snapsDir));
        this.fragments.add(VideoListFragment.newInstance(vidsDir));
        if (this.pager != null) {
            this.pager.setAdapter(new PhotoFragmentAdapter(this, this.fragments));
        }
        
        if (this.tabLayout != null && this.pager != null) {
            new TabLayoutMediator(this.tabLayout, this.pager, new TabLayoutMediator.TabConfigurationStrategy() { // from class: com.gizthon.camera.activity.GalleryListActivity.1
                @Override // com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
                public void onConfigureTab(TabLayout.Tab tab, int i) {
                    if (i == 0) {
                        tab.setText(GalleryListActivity.this.getResources().getString(R.string.photo));
                    } else if (i == 1) {
                        tab.setText(GalleryListActivity.this.getResources().getString(R.string.video));
                    }
                }
            }).attach();
        }
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#09B0F3"));
    }

    @Override // com.gizthon.camera.activity.CameraBaseActivity
    public void onClickBack() {
        finish();
    }

    public void onClickDelete() {
        if (this.pager == null) return;
        Fragment fragment = this.fragments.get(this.pager.getCurrentItem());
        if (fragment instanceof PhotoListFragment) {
            ((PhotoListFragment) fragment).deleteSelected();
            onShowDone(false);
        }
        if (fragment instanceof VideoListFragment) {
            ((VideoListFragment) fragment).deleteSelected();
            onShowDone(false);
        }
    }

    public void onClickDone() {
        if (this.pager == null || this.done == null) return;
        Fragment fragment = this.fragments.get(this.pager.getCurrentItem());
        if (fragment instanceof PhotoListFragment) {
            ((PhotoListFragment) fragment).resetStatus(this.done.isChecked());
        }
        if (fragment instanceof VideoListFragment) {
            ((VideoListFragment) fragment).resetStatus(this.done.isChecked());
        }
    }

    public void onClickComplete() {
        onShowDone(false);
        if (this.pager == null) return;
        Fragment fragment = this.fragments.get(this.pager.getCurrentItem());
        if (fragment instanceof PhotoListFragment) {
            ((PhotoListFragment) fragment).resetComplete();
        }
        if (fragment instanceof VideoListFragment) {
            ((VideoListFragment) fragment).resetComplete();
        }
    }

    public void onShowDone(boolean z) {
        this.viewModel.setEditVisible(z ? 0 : 8);
        if (z) {
            if (this.btnDelete != null) this.btnDelete.setVisibility(android.view.View.VISIBLE);
            if (this.tvComplete != null) this.tvComplete.setVisibility(android.view.View.VISIBLE);
            return;
        }
        if (this.btnDelete != null) this.btnDelete.setVisibility(android.view.View.GONE);
        if (this.tvComplete != null) this.tvComplete.setVisibility(android.view.View.GONE);
        if (this.done != null) {
            this.done.setChecked(false);
        }
    }
}
