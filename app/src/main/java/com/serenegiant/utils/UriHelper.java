package com.serenegiant.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public final class UriHelper {
    private static final boolean DEBUG = false;
    public static final String[] STANDARD_DIRECTORIES;
    private static final String TAG = UriHelper.class.getSimpleName();

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            STANDARD_DIRECTORIES = new String[]{Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DCIM, Environment.DIRECTORY_DOCUMENTS};
        } else {
            STANDARD_DIRECTORIES = new String[]{Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_DOWNLOADS, Environment.DIRECTORY_DCIM};
        }
    }

    public static String getAbsolutePath(ContentResolver contentResolver, Uri uri) {
        String string = null;
        if (uri != null) {
            try {
                Cursor cursorQuery = contentResolver.query(uri, new String[]{"_data"}, null, null, null);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            string = cursorQuery.getString(0);
                        }
                        cursorQuery.close();
                    } catch (Throwable th) {
                        cursorQuery.close();
                        throw th;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return string;
    }

    public static boolean isStandardDirectory(String str) {
        try {
            for (String str2 : STANDARD_DIRECTORIES) {
                if (str2.equals(str)) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        return false;
    }

    public static String getPath(Context context, Uri uri) {
        Uri uri2;
        boolean z;
        String str = null;
        if (BuildCheck.isKitKat() && DocumentsContract.isDocumentUri(context, uri)) {
            int i = 0;
            if (isExternalStorageDocument(uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                BuildCheck.isLollipop();
                String[] strArrSplit = documentId.split(":");
                String str2 = strArrSplit[0];
                if (str2 != null) {
                    if ("primary".equalsIgnoreCase(str2)) {
                        String str3 = Environment.getExternalStorageDirectory() + "/";
                        if (strArrSplit.length <= 1) {
                            return str3;
                        }
                        return str3 + strArrSplit[1];
                    }
                    if ("home".equalsIgnoreCase(str2)) {
                        if (strArrSplit.length > 1 && isStandardDirectory(strArrSplit[1])) {
                            return Environment.getExternalStoragePublicDirectory(strArrSplit[1]) + "/";
                        }
                        String str4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/";
                        if (strArrSplit.length <= 1) {
                            return str4;
                        }
                        return str4 + strArrSplit[1];
                    }
                    String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    File[] externalFilesDirs = context.getExternalFilesDirs(null);
                    int length = externalFilesDirs != null ? externalFilesDirs.length : 0;
                    StringBuilder sb = new StringBuilder();
                    int i2 = 0;
                    while (i2 < length) {
                        File file = externalFilesDirs[i2];
                        if (file == null || !file.getAbsolutePath().startsWith(absolutePath)) {
                            String absolutePath2 = file != null ? file.getAbsolutePath() : str;
                            if (TextUtils.isEmpty(absolutePath2)) {
                                continue;
                            } else {
                                String[] strArrSplit2 = absolutePath2.split("/");
                                int length2 = strArrSplit2.length;
                                int i3 = 2;
                                if (length2 > 2 && "storage".equalsIgnoreCase(strArrSplit2[1]) && str2.equalsIgnoreCase(strArrSplit2[2])) {
                                    sb.setLength(i);
                                    sb.append('/');
                                    sb.append(strArrSplit2[1]);
                                    while (true) {
                                        if (i3 >= length2) {
                                            z = false;
                                            break;
                                        }
                                        if ("Android".equalsIgnoreCase(strArrSplit2[i3])) {
                                            z = true;
                                            break;
                                        }
                                        sb.append('/');
                                        sb.append(strArrSplit2[i3]);
                                        i3++;
                                    }
                                    if (z) {
                                        return new File(new File(sb.toString()), strArrSplit[1]).getAbsolutePath();
                                    }
                                }
                            }
                        }
                        i2++;
                        str = null;
                        i = 0;
                    }
                } else {
                    Log.w(TAG, "unexpectedly type is null");
                }
            } else {
                if (isDownloadsDocument(uri)) {
                    return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
                }
                if (isMediaDocument(uri)) {
                    String[] strArrSplit3 = DocumentsContract.getDocumentId(uri).split(":");
                    String str5 = strArrSplit3[0];
                    if ("image".equals(str5)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str5)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else {
                        uri2 = "audio".equals(str5) ? MediaStore.Audio.Media.EXTERNAL_CONTENT_URI : null;
                    }
                    if (uri2 != null) {
                        return getDataColumn(context, uri2, "_id=?", new String[]{strArrSplit3[1]});
                    }
                }
            }
        } else if (uri != null) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                if (isGooglePhotosUri(uri)) {
                    return uri.getLastPathSegment();
                }
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }
        Log.w(TAG, "unexpectedly not found,uri=" + uri);
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) throws Throwable {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = cursorQuery;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
