package com.serenegiant.math;

import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseBounds implements Serializable {
    private static final long serialVersionUID = 5504958491886331189L;
    public float radius;
    public final Vector position = new Vector();
    public final Vector angle = new Vector();

    public abstract boolean ptInBounds(float f, float f2, float f3);

    public BaseBounds() {
    }

    public BaseBounds(BaseBounds baseBounds) {
        set(baseBounds);
    }

    public BaseBounds(float f, float f2, float f3) {
        this.position.set(f, f2);
        this.radius = f3;
    }

    public BaseBounds(float f, float f2, float f3, float f4) {
        this.position.set(f, f2, f3);
        this.radius = f4;
    }

    public BaseBounds set(BaseBounds baseBounds) {
        this.position.set(baseBounds.position);
        this.angle.set(baseBounds.angle);
        this.radius = baseBounds.radius;
        return this;
    }

    protected boolean ptInBoundsSphere(float f, float f2, float f3, float f4) {
        return this.position.distSquared(f, f2, f3) < f4 * f4;
    }

    public boolean ptInBounds(float f, float f2) {
        return ptInBounds(f, f2, this.position.f2254z);
    }

    public boolean ptInBounds(Vector vector) {
        return ptInBounds(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public BaseBounds move(float f, float f2) {
        this.position.add(f, f2);
        return this;
    }

    public BaseBounds move(float f, float f2, float f3) {
        this.position.add(f, f2, f3);
        return this;
    }

    public BaseBounds move(Vector vector) {
        this.position.add(vector);
        return this;
    }

    public BaseBounds setPosition(Vector vector) {
        this.position.set(vector);
        return this;
    }

    public BaseBounds setPosition(float f, float f2) {
        this.position.set(f, f2);
        return this;
    }

    public BaseBounds setPosition(float f, float f2, float f3) {
        this.position.set(f, f2, f3);
        return this;
    }

    public void centerX(float f) {
        this.position.f2252x = f;
    }

    public float centerX() {
        return this.position.f2252x;
    }

    public void centerY(float f) {
        this.position.f2253y = f;
    }

    public float centerY() {
        return this.position.f2253y;
    }

    public void centerZ(float f) {
        this.position.f2254z = f;
    }

    public float centerZ() {
        return this.position.f2254z;
    }

    public void rotate(Vector vector) {
        vector.set(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public void rotate(float f, float f2, float f3) {
        this.angle.set(f, f2, f3);
    }

    public void rotateX(float f) {
        this.angle.f2252x = f;
    }

    public void rotateY(float f) {
        this.angle.f2253y = f;
    }

    public void rotateZ(float f) {
        this.angle.f2254z = f;
    }
}
