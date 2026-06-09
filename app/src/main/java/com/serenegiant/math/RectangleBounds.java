package com.serenegiant.math;

import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class RectangleBounds extends BaseBounds {
    private static final long serialVersionUID = 260429282595037220L;
    private final Rect boundsRect;
    public final Vector box;

    /* JADX INFO: renamed from: w */
    private final Vector f2251w;

    public RectangleBounds(float f, float f2, float f3, float f4, float f5, float f6) {
        this.box = new Vector();
        this.boundsRect = new Rect();
        this.f2251w = new Vector();
        this.position.set(f, f2, f3);
        this.box.set(f4 / 2.0f, f5 / 2.0f, f6 / 2.0f);
        this.radius = this.box.len();
    }

    public RectangleBounds(float f, float f2, float f3, float f4) {
        this(f, f2, 0.0f, f3, f4, 0.0f);
    }

    public RectangleBounds(Vector vector, float f, float f2) {
        this(vector.f2252x, vector.f2253y, vector.f2254z, f, f2, 0.0f);
    }

    public RectangleBounds(Vector vector, Vector vector2) {
        this.box = new Vector();
        this.boundsRect = new Rect();
        this.f2251w = new Vector();
        if (vector.f2252x > vector2.f2252x) {
            float f = vector.f2252x;
            vector.f2252x = vector2.f2252x;
            vector2.f2252x = f;
        }
        if (vector.f2253y > vector2.f2253y) {
            float f2 = vector.f2253y;
            vector.f2253y = vector2.f2253y;
            vector2.f2253y = f2;
        }
        if (vector.f2254z > vector2.f2254z) {
            float f3 = vector.f2254z;
            vector.f2254z = vector2.f2254z;
            vector2.f2254z = f3;
        }
        setPosition((vector2.f2252x - vector.f2252x) / 2.0f, (vector2.f2253y - vector.f2253y) / 2.0f, (vector2.f2254z - vector.f2254z) / 2.0f);
        this.box.set(this.position).sub(vector);
        this.radius = this.box.len();
    }

    public RectangleBounds(Rect rect) {
        this(rect.centerX(), rect.centerY(), rect.width(), rect.height());
    }

    @Override // com.serenegiant.math.BaseBounds
    public boolean ptInBounds(float f, float f2, float f3) {
        boolean zPtInBoundsSphere = ptInBoundsSphere(f, f2, f3, this.radius);
        if (!zPtInBoundsSphere) {
            return zPtInBoundsSphere;
        }
        this.f2251w.set(f, f2, f3).sub(this.position).rotate(this.angle, -1.0f);
        return this.f2251w.f2252x >= this.position.f2252x - this.box.f2252x && this.f2251w.f2252x <= this.position.f2252x + this.box.f2252x && this.f2251w.f2253y >= this.position.f2253y - this.box.f2253y && this.f2251w.f2253y <= this.position.f2253y + this.box.f2253y && this.f2251w.f2254z >= this.position.f2254z - this.box.f2254z && this.f2251w.f2254z <= this.position.f2254z + this.box.f2254z;
    }

    public Rect boundsRect() {
        this.boundsRect.set((int) (this.position.f2252x - this.box.f2252x), (int) (this.position.f2253y - this.box.f2253y), (int) (this.position.f2252x + this.box.f2252x), (int) (this.position.f2253y + this.box.f2253y));
        return this.boundsRect;
    }

    public Rect boundsRect(float f) {
        this.boundsRect.set((int) (this.position.f2252x - (this.box.f2252x * f)), (int) (this.position.f2253y - (this.box.f2253y * f)), (int) (this.position.f2252x + (this.box.f2252x * f)), (int) (this.position.f2253y + (this.box.f2253y * f)));
        return this.boundsRect;
    }
}
