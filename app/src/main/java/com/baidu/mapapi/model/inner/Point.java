package com.baidu.mapapi.model.inner;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class Point implements Serializable {

    /* JADX INFO: renamed from: x */
    public int f712x;

    /* JADX INFO: renamed from: y */
    public int f713y;

    public Point() {
    }

    public Point(int i, int i2) {
        this.f712x = i;
        this.f713y = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Point point = (Point) obj;
        return this.f712x == point.f712x && this.f713y == point.f713y;
    }

    public int getmPtx() {
        return this.f712x;
    }

    public int getmPty() {
        return this.f713y;
    }

    public int hashCode() {
        return ((this.f712x + 31) * 31) + this.f713y;
    }

    public void setmPtx(int i) {
        this.f712x = i;
    }

    public void setmPty(int i) {
        this.f713y = i;
    }

    public String toString() {
        return "Point [x=" + this.f712x + ", y=" + this.f713y + "]";
    }
}
