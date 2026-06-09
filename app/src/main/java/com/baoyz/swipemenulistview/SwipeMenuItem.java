package com.baoyz.swipemenulistview;

import android.content.Context;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public class SwipeMenuItem {
    private Drawable background;
    private Drawable icon;

    /* JADX INFO: renamed from: id */
    private int f1899id;
    private Context mContext;
    private String title;
    private int titleColor;
    private int titleSize;
    private int width;

    public SwipeMenuItem(Context context) {
        this.mContext = context;
    }

    public int getId() {
        return this.f1899id;
    }

    public void setId(int i) {
        this.f1899id = i;
    }

    public int getTitleColor() {
        return this.titleColor;
    }

    public int getTitleSize() {
        return this.titleSize;
    }

    public void setTitleSize(int i) {
        this.titleSize = i;
    }

    public void setTitleColor(int i) {
        this.titleColor = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitle(int i) {
        setTitle(this.mContext.getString(i));
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public void setIcon(Drawable drawable) {
        this.icon = drawable;
    }

    public void setIcon(int i) {
        this.icon = this.mContext.getResources().getDrawable(i);
    }

    public Drawable getBackground() {
        return this.background;
    }

    public void setBackground(Drawable drawable) {
        this.background = drawable;
    }

    public void setBackground(int i) {
        this.background = this.mContext.getResources().getDrawable(i);
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
