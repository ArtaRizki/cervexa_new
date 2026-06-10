package com.serenegiant.mediastore;

import android.content.AsyncQueryHandler;
import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.viewpager.widget.PagerAdapter;
import com.serenegiant.common.C1831R;
import com.serenegiant.mediastore.MediaStoreHelper;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class MediaStoreImageAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = MediaStoreImageAdapter.class.getSimpleName();
    protected ChangeObserver mChangeObserver;
    private final ContentResolver mCr;
    private Cursor mCursor;
    protected DataSetObserver mDataSetObserver;
    protected boolean mDataValid;
    private final LayoutInflater mInflater;
    private final int mLayoutId;
    private final MyAsyncQueryHandler mQueryHandler;
    protected int mRowIDColumn;
    private String mSelection;
    private String[] mSelectionArgs;
    private boolean mShowTitle;

    public void notifyDataSetInvalidated() {
    }

    public MediaStoreImageAdapter(Context context, int i) {
        this(context, i, true);
    }

    public MediaStoreImageAdapter(Context context, int i, boolean z) {
        this.mSelection = MediaStoreHelper.SELECTIONS[1];
        this.mSelectionArgs = null;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = i;
        this.mCr = context.getContentResolver();
        this.mQueryHandler = new MyAsyncQueryHandler(this.mCr, this);
        if (z) {
            startQuery();
        }
    }

    protected void finalize() {
        changeCursor(null);
        super.finalize();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return 0;
        }
        return cursor.getCount();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View viewInflate = this.mInflater.inflate(this.mLayoutId, viewGroup, false);
        if (viewInflate != null) {
            viewGroup.addView(viewInflate);
            ViewHolder viewHolder = (ViewHolder) viewInflate.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
            }
            TextView textView = (TextView) viewInflate.findViewById(C1831R.id.title);
            viewHolder.mTitleView = textView;
            ImageView imageView = (ImageView) viewInflate.findViewById(C1831R.id.thumbnail);
            viewHolder.mImageView = imageView;
            if (viewHolder.info == null) {
                viewHolder.info = new MediaStoreHelper.MediaInfo();
            }
            viewHolder.info.loadFromCursor(getCursor(i));
            Drawable drawable = imageView.getDrawable();
            if (!(drawable instanceof MediaStoreHelper.LoaderDrawable)) {
                drawable = createLoaderDrawable(this.mCr, viewHolder.info);
                imageView.setImageDrawable(drawable);
            }
            ((MediaStoreHelper.LoaderDrawable) drawable).startLoad(viewHolder.info.mediaType, 0, viewHolder.info.f2256id);
            if (textView != null) {
                textView.setVisibility(this.mShowTitle ? 0 : 8);
                if (this.mShowTitle) {
                    textView.setText(viewHolder.info.title);
                }
            }
        }
        return viewInflate;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (obj instanceof View) {
            viewGroup.removeView((View) obj);
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return super.getItemPosition(obj);
    }

    public int getItemPositionFromID(long j) {
        Cursor cursorQuery = this.mCr.query(MediaStoreHelper.QUERY_URI, MediaStoreHelper.PROJ_MEDIA, this.mSelection, this.mSelectionArgs, null);
        int i = -1;
        if (cursorQuery != null) {
            try {
                if (cursorQuery.moveToFirst()) {
                    int i2 = 0;
                    while (true) {
                        if (cursorQuery.getLong(0) == j) {
                            i = i2;
                            break;
                        }
                        if (!cursorQuery.moveToNext()) {
                            break;
                        }
                        i2++;
                    }
                }
            } finally {
                cursorQuery.close();
            }
        }
        return i;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view.equals(obj);
    }

    protected void changeCursor(Cursor cursor) {
        Cursor cursorSwapCursor = swapCursor(cursor);
        if (cursorSwapCursor == null || cursorSwapCursor.isClosed()) {
            return;
        }
        cursorSwapCursor.close();
    }

    protected Cursor getCursor(int i) {
        Cursor cursor;
        if (!this.mDataValid || (cursor = this.mCursor) == null) {
            return null;
        }
        cursor.moveToPosition(i);
        return this.mCursor;
    }

    protected Cursor swapCursor(Cursor cursor) {
        Cursor cursor2 = this.mCursor;
        if (cursor == cursor2) {
            return null;
        }
        if (cursor2 != null) {
            ChangeObserver changeObserver = this.mChangeObserver;
            if (changeObserver != null) {
                cursor2.unregisterContentObserver(changeObserver);
            }
            DataSetObserver dataSetObserver = this.mDataSetObserver;
            if (dataSetObserver != null) {
                cursor2.unregisterDataSetObserver(dataSetObserver);
            }
        }
        this.mCursor = cursor;
        if (cursor != null) {
            ChangeObserver changeObserver2 = this.mChangeObserver;
            if (changeObserver2 != null) {
                cursor.registerContentObserver(changeObserver2);
            }
            DataSetObserver dataSetObserver2 = this.mDataSetObserver;
            if (dataSetObserver2 != null) {
                cursor.registerDataSetObserver(dataSetObserver2);
            }
            this.mRowIDColumn = cursor.getColumnIndexOrThrow("_id");
            this.mDataValid = true;
            notifyDataSetChanged();
        } else {
            this.mRowIDColumn = -1;
            this.mDataValid = false;
            notifyDataSetInvalidated();
        }
        return cursor2;
    }

    public void startQuery() {
        this.mQueryHandler.requery();
    }

    protected MediaStoreHelper.LoaderDrawable createLoaderDrawable(ContentResolver contentResolver, MediaStoreHelper.MediaInfo mediaInfo) {
        return new ImageLoaderDrawable(contentResolver, mediaInfo.width, mediaInfo.height);
    }

    private static final class ViewHolder {
        MediaStoreHelper.MediaInfo info;
        ImageView mImageView;
        TextView mTitleView;

        private ViewHolder() {
        }
    }

    private static final class MyAsyncQueryHandler extends AsyncQueryHandler {
        private final MediaStoreImageAdapter mAdapter;

        public MyAsyncQueryHandler(ContentResolver contentResolver, MediaStoreImageAdapter mediaStoreImageAdapter) {
            super(contentResolver);
            this.mAdapter = mediaStoreImageAdapter;
        }

        public void requery() {
            synchronized (this.mAdapter) {
                startQuery(0, this.mAdapter, MediaStoreHelper.QUERY_URI, MediaStoreHelper.PROJ_MEDIA, this.mAdapter.mSelection, this.mAdapter.mSelectionArgs, null);
            }
        }

        @Override // android.content.AsyncQueryHandler
        protected void onQueryComplete(int i, Object obj, Cursor cursor) {
            Cursor cursorSwapCursor = this.mAdapter.swapCursor(cursor);
            if (cursorSwapCursor == null || cursorSwapCursor.isClosed()) {
                return;
            }
            cursorSwapCursor.close();
        }
    }

    private class ChangeObserver extends ContentObserver {
        @Override // android.database.ContentObserver
        public boolean deliverSelfNotifications() {
            return true;
        }

        public ChangeObserver() {
            super(new Handler());
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            MediaStoreImageAdapter.this.startQuery();
        }
    }

    private class MyDataSetObserver extends DataSetObserver {
        private MyDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            MediaStoreImageAdapter.this.mDataValid = true;
            MediaStoreImageAdapter.this.notifyDataSetChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            MediaStoreImageAdapter.this.mDataValid = false;
            MediaStoreImageAdapter.this.notifyDataSetInvalidated();
        }
    }

    private static class ImageLoaderDrawable extends MediaStoreHelper.LoaderDrawable {
        @Override // com.serenegiant.mediastore.MediaStoreHelper.LoaderDrawable
        protected Bitmap checkBitmapCache(int i, long j) {
            return null;
        }

        public ImageLoaderDrawable(ContentResolver contentResolver, int i, int i2) {
            super(contentResolver, i, i2);
        }

        @Override // com.serenegiant.mediastore.MediaStoreHelper.LoaderDrawable
        protected MediaStoreHelper.ImageLoader createThumbnailLoader() {
            return new MyImageLoader(this);
        }
    }

    private static class MyImageLoader extends MediaStoreHelper.ImageLoader {
        public MyImageLoader(ImageLoaderDrawable imageLoaderDrawable) {
            super(imageLoaderDrawable);
        }

        @Override // com.serenegiant.mediastore.MediaStoreHelper.ImageLoader
        protected Bitmap loadBitmap(ContentResolver contentResolver, int i, int i2, long j, int i3, int i4) {
            Bitmap image;
            try {
                image = MediaStoreHelper.getImage(contentResolver, j, i3, i4);
                if (image != null) {
                    try {
                        int width = image.getWidth();
                        int height = image.getHeight();
                        Rect rect = new Rect();
                        this.mParent.copyBounds(rect);
                        int iCenterX = rect.centerX();
                        int iCenterY = rect.centerY();
                        rect.set(iCenterX - (width / 2), iCenterY - (height / width), iCenterX + (width / 2), iCenterY + (height / 2));
                        this.mParent.onBoundsChange(rect);
                    } catch (IOException e) {
                        e = e;
                        Log.w(MediaStoreImageAdapter.TAG, e);
                    }
                }
            } catch (IOException e2) {
                e = e2;
                image = null;
            }
            return image;
        }
    }
}
