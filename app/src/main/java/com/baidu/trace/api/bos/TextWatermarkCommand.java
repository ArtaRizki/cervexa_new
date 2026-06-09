package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public class TextWatermarkCommand {

    /* JADX INFO: renamed from: a */
    private String f1373a;

    /* JADX INFO: renamed from: b */
    private int f1374b;

    /* JADX INFO: renamed from: c */
    private int f1375c;

    /* JADX INFO: renamed from: d */
    private int f1376d;

    /* JADX INFO: renamed from: e */
    private int f1377e;

    /* JADX INFO: renamed from: f */
    private int f1378f;

    /* JADX INFO: renamed from: g */
    private String f1379g;

    /* JADX INFO: renamed from: h */
    private FontFamily f1380h;

    /* JADX INFO: renamed from: i */
    private FontStyle f1381i;

    public TextWatermarkCommand() {
        this.f1374b = 9;
        this.f1375c = 10;
        this.f1376d = 10;
        this.f1377e = 0;
        this.f1378f = 30;
        this.f1379g = "000000";
        this.f1380h = FontFamily.SimSun;
        this.f1381i = FontStyle.normal;
    }

    public TextWatermarkCommand(String str, int i, int i2, int i3, int i4, int i5, String str2, FontFamily fontFamily, FontStyle fontStyle) {
        this.f1374b = 9;
        this.f1375c = 10;
        this.f1376d = 10;
        this.f1377e = 0;
        this.f1378f = 30;
        this.f1379g = "000000";
        this.f1380h = FontFamily.SimSun;
        this.f1381i = FontStyle.normal;
        this.f1373a = str;
        this.f1374b = i;
        this.f1375c = i2;
        this.f1376d = i3;
        this.f1377e = i4;
        this.f1378f = i5;
        this.f1379g = str2;
        this.f1380h = fontFamily;
        this.f1381i = fontStyle;
    }

    public int getAngle() {
        return this.f1377e;
    }

    public String getFontColor() {
        return this.f1379g;
    }

    public FontFamily getFontFamily() {
        return this.f1380h;
    }

    public int getFontSize() {
        return this.f1378f;
    }

    public FontStyle getFontStyle() {
        return this.f1381i;
    }

    public int getGravity() {
        return this.f1374b;
    }

    public int getGravityX() {
        return this.f1375c;
    }

    public int getGravityY() {
        return this.f1376d;
    }

    public String getText() {
        return this.f1373a;
    }

    public void setAngle(int i) {
        this.f1377e = i;
    }

    public void setFontColor(String str) {
        this.f1379g = str;
    }

    public void setFontFamily(FontFamily fontFamily) {
        this.f1380h = fontFamily;
    }

    public void setFontSize(int i) {
        this.f1378f = i;
    }

    public void setFontStyle(FontStyle fontStyle) {
        this.f1381i = fontStyle;
    }

    public void setGravity(int i) {
        this.f1374b = i;
    }

    public void setGravityX(int i) {
        this.f1375c = i;
    }

    public void setGravityY(int i) {
        this.f1376d = i;
    }

    public void setText(String str) {
        this.f1373a = str;
    }

    public String toString() {
        return "TextWatermarkCommand [text=" + this.f1373a + ", gravity=" + this.f1374b + ", gravityX=" + this.f1375c + ", gravityY=" + this.f1376d + ", angle=" + this.f1377e + ", fontSize=" + this.f1378f + ", fontColor=" + this.f1379g + ", fontFamily=" + this.f1380h + ", fontStyle=" + this.f1381i + "]";
    }
}
