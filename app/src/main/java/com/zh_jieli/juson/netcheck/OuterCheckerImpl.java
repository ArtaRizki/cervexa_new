package com.zh_jieli.juson.netcheck;

import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes2.dex */
public class OuterCheckerImpl {
    private boolean result = false;
    List<HashMap<String, Object>> mList = new ArrayList();

    private interface ResultCallback {
        void onResult(boolean z);
    }

    public OuterCheckerImpl() {
        HashMap<String, Object> map = new HashMap<>();
        map.put(BaseFragment.DATA_NAME, "qq.com");
        map.put("host", "125.39.240.113");
        map.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_PORT, 80);
        this.mList.add(map);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put(BaseFragment.DATA_NAME, "qq.com");
        map2.put("host", "125.39.240.113");
        map2.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_PORT, 443);
        this.mList.add(map2);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put(BaseFragment.DATA_NAME, "qq.com");
        map3.put("host", "61.135.157.156");
        map3.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_PORT, 80);
        this.mList.add(map3);
        HashMap<String, Object> map4 = new HashMap<>();
        map4.put(BaseFragment.DATA_NAME, "qq.com");
        map4.put("host", "61.135.157.156");
        map4.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_PORT, 443);
        this.mList.add(map4);
    }

    public static boolean check(List<HashMap> list, long j) {
        try {
            return new OuterCheckerImpl().realCheck(list, j);
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean check(long j) {
        try {
            OuterCheckerImpl outerCheckerImpl = new OuterCheckerImpl();
            return outerCheckerImpl.realCheck(outerCheckerImpl.mList, j);
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean realCheck(List list, long j) throws Exception {
        this.result = false;
        Thread thread = new Thread(new InternetCheckRunner(list, j, new ResultCallback() { // from class: com.zh_jieli.juson.netcheck.OuterCheckerImpl.1
            @Override // com.zh_jieli.juson.netcheck.OuterCheckerImpl.ResultCallback
            public void onResult(boolean z) {
                OuterCheckerImpl.this.result = z;
            }
        }));
        thread.start();
        thread.join();
        return this.result;
    }

    private class InternetCheckRunner implements Runnable {
        private List<HashMap<String, Object>> mList;
        ResultCallback mResultCallback;
        private long mTimeout;

        public InternetCheckRunner(List<HashMap<String, Object>> list, long j, ResultCallback resultCallback) {
            this.mList = list;
            this.mTimeout = j;
            this.mResultCallback = resultCallback;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0089 A[Catch: all -> 0x00c8, IOException -> 0x00cb, PHI: r11
  0x0089: PHI (r11v10 boolean) = (r11v9 boolean), (r11v12 boolean) binds: [B:15:0x0064, B:23:0x0086] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #7 {IOException -> 0x00cb, all -> 0x00c8, blocks: (B:14:0x005e, B:16:0x0066, B:17:0x006e, B:19:0x0074, B:22:0x0082, B:25:0x0089), top: B:95:0x005e }] */
        /* JADX WARN: Removed duplicated region for block: B:77:0x0144 A[Catch: IOException -> 0x0140, TryCatch #5 {IOException -> 0x0140, blocks: (B:73:0x013c, B:77:0x0144, B:79:0x0147, B:81:0x014b, B:82:0x014e, B:83:0x0151), top: B:90:0x013c }] */
        /* JADX WARN: Removed duplicated region for block: B:90:0x013c A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 347
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.zh_jieli.juson.netcheck.OuterCheckerImpl.InternetCheckRunner.run():void");
        }
    }
}
