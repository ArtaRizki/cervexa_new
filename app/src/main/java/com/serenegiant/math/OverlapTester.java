package com.serenegiant.math;

/* JADX INFO: loaded from: classes.dex */
public class OverlapTester {
    private static final Vector r1L = new Vector();
    private static final Vector r2L = new Vector();

    public static boolean check(BaseBounds baseBounds, BaseBounds baseBounds2) {
        float fDistSquared = baseBounds.position.distSquared(baseBounds2.position);
        float f = baseBounds.radius + baseBounds2.radius;
        return fDistSquared <= f * f;
    }

    public static boolean check(CircleBounds circleBounds, CircleBounds circleBounds2) {
        float fDistSquared = circleBounds.position.distSquared(circleBounds2.position);
        float f = circleBounds.radius + circleBounds2.radius;
        return fDistSquared <= f * f;
    }

    public static boolean check(RectangleBounds rectangleBounds, RectangleBounds rectangleBounds2) {
        r1L.set(rectangleBounds.position).sub(rectangleBounds.box);
        float f = rectangleBounds.box.f2252x * 2.0f;
        float f2 = rectangleBounds.box.f2253y * 2.0f;
        float f3 = rectangleBounds.box.f2254z * 2.0f;
        r2L.set(rectangleBounds2.position).sub(rectangleBounds2.box);
        return r1L.f2252x < r2L.f2252x + (rectangleBounds2.box.f2252x * 2.0f) && r1L.f2252x + f > r2L.f2252x && r1L.f2253y < r2L.f2253y + (rectangleBounds2.box.f2253y * 2.0f) && r1L.f2253y + f2 > r2L.f2253y && r1L.f2254z < r2L.f2254z + (rectangleBounds2.box.f2254z * 2.0f) && r1L.f2254z + f3 > r2L.f2254z;
    }

    public static boolean check(CircleBounds circleBounds, RectangleBounds rectangleBounds) {
        float f = circleBounds.position.f2252x;
        float f2 = circleBounds.position.f2253y;
        float f3 = circleBounds.position.f2254z;
        r1L.set(rectangleBounds.position).sub(rectangleBounds.box);
        float f4 = rectangleBounds.box.f2252x * 2.0f;
        float f5 = rectangleBounds.box.f2253y * 2.0f;
        float f6 = rectangleBounds.box.f2254z * 2.0f;
        if (circleBounds.position.f2252x < r1L.f2252x) {
            f = r1L.f2252x;
        } else if (circleBounds.position.f2252x > r1L.f2252x + f4) {
            f = r1L.f2252x + f4;
        }
        if (circleBounds.position.f2253y < r1L.f2253y) {
            f2 = r1L.f2253y;
        } else if (circleBounds.position.f2253y > r1L.f2253y + f5) {
            f2 = r1L.f2253y + f5;
        }
        if (circleBounds.position.f2254z < r1L.f2254z) {
            f3 = r1L.f2254z;
        } else if (circleBounds.position.f2254z > r1L.f2254z + f6) {
            f3 = r1L.f2254z + f6;
        }
        return circleBounds.position.distSquared(f, f2, f3) < circleBounds.radius * circleBounds.radius;
    }

    public static boolean check(CircleBounds circleBounds, Vector vector) {
        return circleBounds.position.distSquared(vector) < circleBounds.radius * circleBounds.radius;
    }

    public static boolean check(CircleBounds circleBounds, float f, float f2, float f3) {
        return circleBounds.position.distSquared(f, f2, f3) < circleBounds.radius * circleBounds.radius;
    }

    public static boolean check(CircleBounds circleBounds, float f, float f2) {
        return circleBounds.position.distSquared(f, f2) < circleBounds.radius * circleBounds.radius;
    }

    public static boolean check(RectangleBounds rectangleBounds, Vector vector) {
        return check(rectangleBounds, vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public static boolean check(RectangleBounds rectangleBounds, float f, float f2) {
        return check(rectangleBounds, f, f2, 0.0f);
    }

    public static boolean check(RectangleBounds rectangleBounds, float f, float f2, float f3) {
        r1L.set(rectangleBounds.position).sub(rectangleBounds.box);
        return r1L.f2252x <= f && r1L.f2252x + (rectangleBounds.box.f2252x * 2.0f) >= f && r1L.f2253y <= f2 && r1L.f2253y + (rectangleBounds.box.f2253y * 2.0f) >= f2 && r1L.f2254z <= f3 && r1L.f2254z + (rectangleBounds.box.f2254z * 2.0f) >= f3;
    }

    public static boolean check(SphereBounds sphereBounds, SphereBounds sphereBounds2) {
        return sphereBounds.position.distance(sphereBounds2.position) <= sphereBounds.radius + sphereBounds2.radius;
    }

    public static boolean check(SphereBounds sphereBounds, Vector vector) {
        return sphereBounds.position.distSquared(vector) < sphereBounds.radius * sphereBounds.radius;
    }

    public static boolean check(SphereBounds sphereBounds, float f, float f2, float f3) {
        return sphereBounds.position.distance(f, f2, f3) < sphereBounds.radius * sphereBounds.radius;
    }
}
