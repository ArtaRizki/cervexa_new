package com.serenegiant.math;

/* JADX INFO: loaded from: classes.dex */
public class CylinderBounds extends BaseBounds {
    private static final long serialVersionUID = -2875851852923460432L;
    protected float height;
    protected float outer_r;

    /* JADX INFO: renamed from: w1 */
    private final Vector f2249w1;

    /* JADX INFO: renamed from: w2 */
    private final Vector f2250w2;

    public CylinderBounds(float f, float f2, float f3, float f4, float f5) {
        this.f2249w1 = new Vector();
        this.f2250w2 = new Vector();
        this.position.set(f, f2, f3);
        this.radius = (float) Math.sqrt((f5 * f5) + ((f4 * f4) / 4.0f));
        this.outer_r = f5;
        this.height = f4 / 2.0f;
    }

    public CylinderBounds(Vector vector, float f, float f2) {
        this(vector.f2252x, vector.f2253y, vector.f2254z, f, f2);
    }

    protected boolean ptInCylinder(float f, float f2, float f3, float f4) {
        this.f2249w1.set(f, f2, f3).sub(this.position).rotate(this.angle, -1.0f);
        this.f2250w2.set(this.f2249w1);
        this.f2250w2.f2253y = 0.0f;
        if (this.f2250w2.distSquared(this.position.f2252x, 0.0f, this.position.f2254z) < f4 * f4) {
            return this.f2249w1.f2252x >= this.position.f2252x - f4 && this.f2249w1.f2252x <= this.position.f2252x + f4 && this.f2249w1.f2253y >= this.position.f2253y - this.height && this.f2249w1.f2253y <= this.position.f2253y + this.height;
        }
        return false;
    }

    @Override // com.serenegiant.math.BaseBounds
    public boolean ptInBounds(float f, float f2, float f3) {
        boolean zPtInBoundsSphere = ptInBoundsSphere(f, f2, f3, this.radius);
        return zPtInBoundsSphere ? ptInCylinder(f, f2, f3, this.outer_r) : zPtInBoundsSphere;
    }
}
