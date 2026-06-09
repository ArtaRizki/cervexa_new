package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public class ImageWatermarkCommand {

    /* JADX INFO: renamed from: a */
    private String f1367a;

    /* JADX INFO: renamed from: b */
    private int f1368b;

    /* JADX INFO: renamed from: c */
    private int f1369c;

    /* JADX INFO: renamed from: d */
    private int f1370d;

    /* JADX INFO: renamed from: e */
    private int f1371e;

    /* JADX INFO: renamed from: f */
    private int f1372f;

    public ImageWatermarkCommand() {
        this.f1368b = 9;
        this.f1369c = 10;
        this.f1370d = 10;
        this.f1371e = 0;
        this.f1372f = 100;
    }

    public ImageWatermarkCommand(String str, int i, int i2, int i3, int i4, int i5) {
        this.f1368b = 9;
        this.f1369c = 10;
        this.f1370d = 10;
        this.f1371e = 0;
        this.f1372f = 100;
        this.f1367a = str;
        this.f1368b = i;
        this.f1369c = i2;
        this.f1370d = i3;
        this.f1371e = i4;
        this.f1372f = i5;
    }

    public int getAngle() {
        return this.f1371e;
    }

    public int getGravity() {
        return this.f1368b;
    }

    public int getGravityX() {
        return this.f1369c;
    }

    public int getGravityY() {
        return this.f1370d;
    }

    public String getObjectKey() {
        return this.f1367a;
    }

    public int getOpacity() {
        return this.f1372f;
    }

    public void setAngle(int i) {
        this.f1371e = i;
    }

    public void setGravity(int i) {
        this.f1368b = i;
    }

    public void setGravityX(int i) {
        this.f1369c = i;
    }

    public void setGravityY(int i) {
        this.f1370d = i;
    }

    public void setObjectKey(String str) {
        this.f1367a = str;
    }

    public void setOpacity(int i) {
        this.f1372f = i;
    }

    public String toString() {
        return "ImageWatermarkCommand [objectKey=" + this.f1367a + ", gravity=" + this.f1368b + ", gravityX=" + this.f1369c + ", gravityY=" + this.f1370d + ", angle=" + this.f1371e + ", opacity=" + this.f1372f + "]";
    }
}
