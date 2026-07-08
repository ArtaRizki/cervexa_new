package com.generalplus.GoPlusDrone.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.generalplus.GoPlusDrone.Activity.FileViewController;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.generalplus.ffmpegLib.ffmpegWrapper;
import com.jiangdg.usbcamera.UVCCameraHelper;
import generalplus.com.GPCamLib.CamWrapper;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class VideoListFragment extends BaseFragment {
    private static final String TAG = "VideoListFragment";
    private GridView m_GridView = null;
    private String strSaveDirectory;

    public static VideoListFragment newInstance(String str) {
        VideoListFragment videoListFragment = new VideoListFragment();
        videoListFragment.setTitle(str);
        Bundle bundle = new Bundle();
        bundle.putString(BaseFragment.DATA_NAME, str);
        videoListFragment.setArguments(bundle);
        return videoListFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            getFile();
        }
    }

    public class FileComparator implements Comparator<String> {
        public FileComparator() {
        }

        @Override // java.util.Comparator
        public int compare(String str, String str2) {
            return (str == null || str.compareTo(str2) < 0) ? 1 : -1;
        }
    }

    private void getFile() {
        this.listImageItem.clear();
        this.strSaveDirectory = Environment.getExternalStorageDirectory().getPath() + "/GoPlus_Drone/Video/";
        String[] list = new File(this.strSaveDirectory).list();
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            for (String str : list) {
                arrayList.add(str);
            }
            Collections.sort(arrayList, new FileComparator());
            for (int i = 0; i < arrayList.size(); i++) {
                String str2 = (String) arrayList.get(i);
                if (str2.contains(UVCCameraHelper.SUFFIX_MP4)) {
                    this.m_ayFilePath.add(this.strSaveDirectory + str2);
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("KEY_ThumbnailFilePath", this.strSaveDirectory + "thumbnails/" + str2.replace("mp4", "jpg"));
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.strSaveDirectory);
                    sb.append(str2);
                    map.put("KEY_FilePath", sb.toString());
                    map.put("KEY_FileType", 1);
                    map.put("KEY_FileName", str2);
                    map.put("KEY_FileIndex", String.valueOf(i));
                    this.listImageItem.add(map);
                } else if (str2.contains(".avi")) {
                    this.m_ayFilePath.add(this.strSaveDirectory + str2);
                    HashMap<String, Object> map2 = new HashMap<>();
                    map2.put("KEY_ThumbnailFilePath", this.strSaveDirectory + "thumbnails/" + str2.replace("avi", "jpg"));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.strSaveDirectory);
                    sb2.append(str2);
                    map2.put("KEY_FilePath", sb2.toString());
                    map2.put("KEY_FileType", 1);
                    map2.put("KEY_FileName", str2);
                    map2.put("KEY_FileIndex", String.valueOf(i));
                    this.listImageItem.add(map2);
                }
            }
        }
        if (this.m_Adapter != null) {
            this.m_Adapter.notifyDataSetChanged();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(com.weioa.KmedHealthIndonesia.R.layout.activity_filelist, viewGroup, false);
        this.mContext = getActivity();
        GridView gridView = (GridView) viewInflate.findViewById(com.weioa.KmedHealthIndonesia.R.id.gridView);
        this.m_GridView = gridView;
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.generalplus.GoPlusDrone.Fragment.VideoListFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (VideoListFragment.this.m_bEdit) {
                    CheckBox checkBox = (CheckBox) view.findViewById(com.weioa.KmedHealthIndonesia.R.id.checkBox);
                    checkBox.toggle();
                    if (checkBox.isChecked()) {
                        VideoListFragment.this.m_HashMap.put(Integer.valueOf(i), Integer.valueOf(i));
                        return;
                    } else {
                        VideoListFragment.this.m_HashMap.remove(Integer.valueOf(i));
                        return;
                    }
                }
                Intent intent = new Intent(VideoListFragment.this.getActivity(), (Class<?>) FileViewController.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString(CamWrapper.GPFILECALLBACKTYPE_FILEURL, VideoListFragment.this.m_ayFilePath.get(i));
                bundle2.putInt(CamWrapper.GPFILECALLBACKTYPE_FILEFLAG, 1);
                intent.putExtras(bundle2);
                VideoListFragment.this.startActivity(intent);
            }
        });
        getFile();
        getThumbnail();
        this.m_Adapter = new BaseFragment.ListViewItemAdapter(this.mContext, this.listImageItem, com.weioa.KmedHealthIndonesia.R.layout.downloaded_filelist, new String[]{"KEY_ThumbnailFilePath", "KEY_FileName", "KEY_FileIndex"}, new int[]{com.weioa.KmedHealthIndonesia.R.id.imageView1, com.weioa.KmedHealthIndonesia.R.id.textView1});
        this.m_GridView.setAdapter((ListAdapter) this.m_Adapter);
        this.m_Adapter.notifyDataSetChanged();
        return viewInflate;
    }

    private void getThumbnail() {
        new Thread(new Runnable() { // from class: com.generalplus.GoPlusDrone.Fragment.VideoListFragment.2
            @Override // java.lang.Runnable
            public void run() {
                for (int i = 0; i < VideoListFragment.this.listImageItem.size(); i++) {
                    String str = (String) VideoListFragment.this.listImageItem.get(i).get("KEY_ThumbnailFilePath");
                    if (!new File(str).exists()) {
                        ffmpegWrapper.getInstance();
                        int iNaExtractFrame = ffmpegWrapper.naExtractFrame((String) VideoListFragment.this.listImageItem.get(i).get("KEY_FilePath"), str, 0L);
                        Log.e(VideoListFragment.TAG, "not exists");
                        if (iNaExtractFrame == 0) {
                            VideoListFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: com.generalplus.GoPlusDrone.Fragment.VideoListFragment.2.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    VideoListFragment.this.m_Adapter.notifyDataSetChanged();
                                }
                            });
                        }
                    }
                }
            }
        }).start();
    }

    @Override // com.generalplus.GoPlusDrone.Fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
