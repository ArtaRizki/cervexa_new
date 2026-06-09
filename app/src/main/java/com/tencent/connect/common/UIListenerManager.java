package com.tencent.connect.common;

import android.content.Intent;
import com.tencent.open.p026a.C2061f;
import com.tencent.open.utils.C2087g;
import com.tencent.open.utils.C2089i;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
public class UIListenerManager {

    /* JADX INFO: renamed from: a */
    private static UIListenerManager f3030a;

    /* JADX INFO: renamed from: b */
    private Map<String, ApiTask> f3031b;

    public static UIListenerManager getInstance() {
        if (f3030a == null) {
            f3030a = new UIListenerManager();
        }
        return f3030a;
    }

    private UIListenerManager() {
        Map<String, ApiTask> mapSynchronizedMap = Collections.synchronizedMap(new HashMap());
        this.f3031b = mapSynchronizedMap;
        if (mapSynchronizedMap == null) {
            this.f3031b = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        ApiTask apiTaskPut;
        String strM2237a = C2087g.m2237a(i);
        if (strM2237a == null) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "setListener action is null! rquestCode=" + i);
            return null;
        }
        synchronized (this.f3031b) {
            apiTaskPut = this.f3031b.put(strM2237a, new ApiTask(i, iUiListener));
        }
        if (apiTaskPut == null) {
            return null;
        }
        return apiTaskPut.mListener;
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        ApiTask apiTaskPut;
        int iM2234a = C2087g.m2234a(str);
        if (iM2234a == -1) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "setListnerWithAction fail, action = " + str);
            return null;
        }
        synchronized (this.f3031b) {
            apiTaskPut = this.f3031b.put(str, new ApiTask(iM2234a, iUiListener));
        }
        if (apiTaskPut == null) {
            return null;
        }
        return apiTaskPut.mListener;
    }

    public IUiListener getListnerWithRequestCode(int i) {
        String strM2237a = C2087g.m2237a(i);
        if (strM2237a == null) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "getListner action is null! rquestCode=" + i);
            return null;
        }
        return getListnerWithAction(strM2237a);
    }

    public IUiListener getListnerWithAction(String str) {
        ApiTask apiTask;
        if (str == null) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "getListnerWithAction action is null!");
            return null;
        }
        synchronized (this.f3031b) {
            apiTask = this.f3031b.get(str);
            this.f3031b.remove(str);
        }
        if (apiTask == null) {
            return null;
        }
        return apiTask.mListener;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.UIListenerManager", "handleDataToListener");
        if (intent == null) {
            iUiListener.onCancel();
            return;
        }
        String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
        if ("action_login".equals(stringExtra)) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                String stringExtra2 = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra2 != null) {
                    try {
                        iUiListener.onComplete(C2089i.m2271d(stringExtra2));
                        return;
                    } catch (JSONException e) {
                        iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2));
                        C2061f.m2131b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                        return;
                    }
                }
                C2061f.m2130b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                iUiListener.onComplete(new JSONObject());
                return;
            }
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + intExtra + "");
            iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
            return;
        }
        if ("action_share".equals(stringExtra)) {
            String stringExtra3 = intent.getStringExtra("result");
            String stringExtra4 = intent.getStringExtra("response");
            if ("cancel".equals(stringExtra3)) {
                iUiListener.onCancel();
                return;
            }
            if (IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR.equals(stringExtra3)) {
                iUiListener.onError(new UiError(-6, "unknown error", stringExtra4 + ""));
                return;
            }
            if ("complete".equals(stringExtra3)) {
                try {
                    iUiListener.onComplete(new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    iUiListener.onError(new UiError(-4, "json error", stringExtra4 + ""));
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private IUiListener m2015a(int i, IUiListener iUiListener) {
        if (i == 11101) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "登录的接口回调不能重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == 11105) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "Social Api 的接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        } else if (i == 11106) {
            C2061f.m2135e("openSDK_LOG.UIListenerManager", "Social Api 的H5接口回调需要使用param来重新构建，暂时无法提供，先记录下来这种情况是否存在");
        }
        return iUiListener;
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        C2061f.m2133c("openSDK_LOG.UIListenerManager", "onActivityResult req=" + i + " res=" + i2);
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i);
        if (listnerWithRequestCode == null) {
            if (iUiListener != null) {
                listnerWithRequestCode = m2015a(i, iUiListener);
            } else {
                C2061f.m2135e("openSDK_LOG.UIListenerManager", "onActivityResult can't find the listener");
                return false;
            }
        }
        if (i2 != -1) {
            listnerWithRequestCode.onCancel();
        } else {
            if (intent == null) {
                listnerWithRequestCode.onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
                return true;
            }
            String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
            if ("action_login".equals(stringExtra)) {
                int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    String stringExtra2 = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra2 != null) {
                        try {
                            listnerWithRequestCode.onComplete(C2089i.m2271d(stringExtra2));
                        } catch (JSONException e) {
                            listnerWithRequestCode.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2));
                            C2061f.m2131b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        C2061f.m2130b("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onComplete");
                        listnerWithRequestCode.onComplete(new JSONObject());
                    }
                } else {
                    C2061f.m2135e("openSDK_LOG.UIListenerManager", "OpenUi, onActivityResult, onError = " + intExtra + "");
                    listnerWithRequestCode.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else if ("action_share".equals(stringExtra)) {
                String stringExtra3 = intent.getStringExtra("result");
                String stringExtra4 = intent.getStringExtra("response");
                if ("cancel".equals(stringExtra3)) {
                    listnerWithRequestCode.onCancel();
                } else if (IjkMediaPlayer.OnNativeInvokeListener.ARG_ERROR.equals(stringExtra3)) {
                    listnerWithRequestCode.onError(new UiError(-6, "unknown error", stringExtra4 + ""));
                } else if ("complete".equals(stringExtra3)) {
                    try {
                        listnerWithRequestCode.onComplete(new JSONObject(stringExtra4 == null ? "{\"ret\": 0}" : stringExtra4));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        listnerWithRequestCode.onError(new UiError(-4, "json error", stringExtra4 + ""));
                    }
                }
            } else {
                int intExtra2 = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra2 == 0) {
                    String stringExtra5 = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra5 != null) {
                        try {
                            listnerWithRequestCode.onComplete(C2089i.m2271d(stringExtra5));
                        } catch (JSONException unused) {
                            listnerWithRequestCode.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra5));
                        }
                    } else {
                        listnerWithRequestCode.onComplete(new JSONObject());
                    }
                } else {
                    listnerWithRequestCode.onError(new UiError(intExtra2, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            }
        }
        return true;
    }

    /* JADX INFO: compiled from: ProGuard */
    public class ApiTask {
        public IUiListener mListener;
        public int mRequestCode;

        public ApiTask(int i, IUiListener iUiListener) {
            this.mRequestCode = i;
            this.mListener = iUiListener;
        }
    }
}
