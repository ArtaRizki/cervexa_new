package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public class ImageProcessCommand {

    /* JADX INFO: renamed from: a */
    private int f1354a;

    /* JADX INFO: renamed from: b */
    private int f1355b;

    /* JADX INFO: renamed from: c */
    private int f1356c;

    /* JADX INFO: renamed from: d */
    private int f1357d;

    /* JADX INFO: renamed from: e */
    private ImageFormat f1358e;

    /* JADX INFO: renamed from: f */
    private int f1359f;

    /* JADX INFO: renamed from: g */
    private ImageDisplay f1360g;

    /* JADX INFO: renamed from: h */
    private boolean f1361h;

    /* JADX INFO: renamed from: i */
    private boolean f1362i;

    /* JADX INFO: renamed from: j */
    private int f1363j;

    /* JADX INFO: renamed from: k */
    private int f1364k;

    /* JADX INFO: renamed from: l */
    private int f1365l;

    /* JADX INFO: renamed from: m */
    private int f1366m;

    public ImageProcessCommand() {
        this.f1354a = 0;
        this.f1358e = ImageFormat.jpg;
        this.f1359f = 0;
        this.f1360g = ImageDisplay.baseline;
        this.f1361h = false;
        this.f1362i = false;
        this.f1363j = 0;
        this.f1364k = 0;
    }

    public ImageProcessCommand(int i, int i2, int i3, int i4, ImageFormat imageFormat, int i5, ImageDisplay imageDisplay, boolean z, boolean z2, int i6, int i7, int i8, int i9) {
        this.f1354a = 0;
        this.f1358e = ImageFormat.jpg;
        this.f1359f = 0;
        this.f1360g = ImageDisplay.baseline;
        this.f1361h = false;
        this.f1362i = false;
        this.f1363j = 0;
        this.f1364k = 0;
        this.f1354a = i;
        this.f1355b = i2;
        this.f1356c = i3;
        this.f1357d = i4;
        this.f1358e = imageFormat;
        this.f1359f = i5;
        this.f1360g = imageDisplay;
        this.f1361h = z;
        this.f1362i = z2;
        this.f1363j = i6;
        this.f1364k = i7;
        this.f1365l = i8;
        this.f1366m = i9;
    }

    public int getAngle() {
        return this.f1359f;
    }

    public int getCropHeight() {
        return this.f1366m;
    }

    public int getCropWidth() {
        return this.f1365l;
    }

    public ImageDisplay getDisplay() {
        return this.f1360g;
    }

    public ImageFormat getFormat() {
        return this.f1358e;
    }

    public int getMaxHeight() {
        return this.f1356c;
    }

    public int getMaxWidth() {
        return this.f1355b;
    }

    public int getOffsetX() {
        return this.f1363j;
    }

    public int getOffsetY() {
        return this.f1364k;
    }

    public int getQuality() {
        return this.f1357d;
    }

    public int getScale() {
        return this.f1354a;
    }

    public boolean isCrop() {
        return this.f1362i;
    }

    public boolean isLimit() {
        return this.f1361h;
    }

    public void setAngle(int i) {
        this.f1359f = i;
    }

    public void setCrop(boolean z) {
        this.f1362i = z;
    }

    public void setCropHeight(int i) {
        this.f1366m = i;
    }

    public void setCropWidth(int i) {
        this.f1365l = i;
    }

    public void setDisplay(ImageDisplay imageDisplay) {
        this.f1360g = imageDisplay;
    }

    public void setFormat(ImageFormat imageFormat) {
        this.f1358e = imageFormat;
    }

    public void setLimit(boolean z) {
        this.f1361h = z;
    }

    public void setMaxHeight(int i) {
        this.f1356c = i;
    }

    public void setMaxWidth(int i) {
        this.f1355b = i;
    }

    public void setOffsetX(int i) {
        this.f1363j = i;
    }

    public void setOffsetY(int i) {
        this.f1364k = i;
    }

    public void setQuality(int i) {
        this.f1357d = i;
    }

    public void setScale(int i) {
        this.f1354a = i;
    }

    public String toString() {
        return "ImageProcessCommand [scale=" + this.f1354a + ", maxWidth=" + this.f1355b + ", maxHeight=" + this.f1356c + ", quality=" + this.f1357d + ", format=" + this.f1358e + ", angle=" + this.f1359f + ", display=" + this.f1360g + ", limit=" + this.f1361h + ", crop=" + this.f1362i + ", offsetX=" + this.f1363j + ", offsetY=" + this.f1364k + ", cropWidth=" + this.f1365l + ", cropHeight=" + this.f1366m + "]";
    }
}
