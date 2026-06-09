package com.serenegiant.math;

import android.opengl.Matrix;
import java.io.Serializable;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class Vector implements Serializable, Cloneable {
    public static final float TO_DEGREE = 57.29578f;
    public static final float TO_RADIAN = 0.017453292f;
    private static final long serialVersionUID = 1620440892067002860L;

    /* JADX INFO: renamed from: x */
    public float f2252x;

    /* JADX INFO: renamed from: y */
    public float f2253y;

    /* JADX INFO: renamed from: z */
    public float f2254z;
    public static final Vector zeroVector = new Vector();
    public static final Vector normVector = new Vector(1.0f, 1.0f, 1.0f);
    private static final float[] matrix = new float[16];
    private static final float[] inVec = new float[4];
    private static final float[] outVec = new float[4];

    public Vector() {
    }

    public Vector(float f, float f2) {
        this(f, f2, 0.0f);
    }

    public Vector(Vector vector) {
        this(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public Vector(float f, float f2, float f3) {
        this.f2252x = f;
        this.f2253y = f2;
        this.f2254z = f3;
    }

    public static Vector vector(float f, float f2, float f3) {
        return new Vector(f, f2, f3);
    }

    public static Vector vector(Vector vector) {
        return new Vector(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public Vector m2347clone() throws CloneNotSupportedException {
        return (Vector) super.clone();
    }

    public Vector clear(float f) {
        this.f2254z = f;
        this.f2253y = f;
        this.f2252x = f;
        return this;
    }

    public Vector set(float f, float f2) {
        return set(f, f2, 0.0f);
    }

    public Vector set(Vector vector) {
        return set(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public Vector set(Vector vector, float f) {
        return set(vector.f2252x, vector.f2253y, vector.f2254z, f);
    }

    public Vector set(float f, float f2, float f3) {
        this.f2252x = f;
        this.f2253y = f2;
        this.f2254z = f3;
        return this;
    }

    public Vector set(float f, float f2, float f3, float f4) {
        this.f2252x = f * f4;
        this.f2253y = f2 * f4;
        this.f2254z = f3 * f4;
        return this;
    }

    /* JADX INFO: renamed from: x */
    public float m1395x() {
        return this.f2252x;
    }

    /* JADX INFO: renamed from: x */
    public void m1396x(float f) {
        this.f2252x = f;
    }

    /* JADX INFO: renamed from: y */
    public float m1397y() {
        return this.f2253y;
    }

    /* JADX INFO: renamed from: y */
    public void m1398y(float f) {
        this.f2253y = f;
    }

    /* JADX INFO: renamed from: z */
    public float m1399z() {
        return this.f2254z;
    }

    /* JADX INFO: renamed from: z */
    public void m1400z(float f) {
        this.f2254z = f;
    }

    public Vector add(float f, float f2) {
        return add(f, f2, 0.0f);
    }

    public Vector add(float f, float f2, float f3) {
        this.f2252x += f;
        this.f2253y += f2;
        this.f2254z += f3;
        return this;
    }

    public Vector add(float f, float f2, float f3, float f4) {
        this.f2252x += f * f4;
        this.f2253y += f2 * f4;
        this.f2254z += f3 * f4;
        return this;
    }

    public Vector add(Vector vector) {
        return add(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public Vector add(Vector vector, float f) {
        return add(vector.f2252x, vector.f2253y, vector.f2254z, f);
    }

    public Vector sub(float f, float f2) {
        return add(-f, -f2, 0.0f);
    }

    public Vector sub(Vector vector) {
        return add(-vector.f2252x, -vector.f2253y, -vector.f2254z);
    }

    public Vector sub(Vector vector, float f) {
        return add(-vector.f2252x, -vector.f2253y, -vector.f2254z, f);
    }

    public Vector sub(float f, float f2, float f3) {
        return add(-f, -f2, -f3);
    }

    public Vector sub(float f, float f2, float f3, float f4) {
        return add(-f, -f2, -f3, f4);
    }

    public Vector mult(Vector vector) {
        this.f2252x *= vector.f2252x;
        this.f2253y *= vector.f2253y;
        this.f2254z *= vector.f2254z;
        return this;
    }

    public Vector mult(float f) {
        this.f2252x *= f;
        this.f2253y *= f;
        this.f2254z *= f;
        return this;
    }

    public Vector mult(float f, float f2) {
        this.f2252x *= f;
        this.f2253y *= f2;
        return this;
    }

    public Vector mult(float f, float f2, float f3) {
        this.f2252x *= f;
        this.f2253y *= f2;
        this.f2254z *= f3;
        return this;
    }

    public Vector div(Vector vector) {
        this.f2252x /= vector.f2252x;
        this.f2253y /= vector.f2253y;
        this.f2254z /= vector.f2254z;
        return this;
    }

    public Vector div(float f) {
        this.f2252x /= f;
        this.f2253y /= f;
        this.f2254z /= f;
        return this;
    }

    public Vector div(float f, float f2) {
        this.f2252x /= f;
        this.f2253y /= f2;
        return this;
    }

    public Vector div(float f, float f2, float f3) {
        this.f2252x /= f;
        this.f2253y /= f2;
        this.f2254z /= f3;
        return this;
    }

    public Vector mod(float f) {
        this.f2252x %= f;
        this.f2253y %= f;
        this.f2254z %= f;
        return this;
    }

    public Vector toRadian() {
        return mult(0.017453292f);
    }

    public Vector toDegree() {
        return mult(57.29578f);
    }

    public Vector limit(float f) {
        float f2;
        float f3 = this.f2252x;
        if (f3 >= f) {
            f3 = f;
        } else {
            float f4 = -f;
            if (f3 < f4) {
                f3 = f4;
            }
        }
        this.f2252x = f3;
        float f5 = this.f2253y;
        if (f5 >= f) {
            f5 = f;
        } else {
            float f6 = -f;
            if (f5 < f6) {
                f5 = f6;
            }
        }
        this.f2253y = f5;
        float f7 = this.f2254z;
        if (f7 >= f) {
            f7 = f;
        } else {
            float f8 = -f;
            if (f7 < f8) {
                f7 = f8;
            }
        }
        this.f2254z = f7;
        while (true) {
            float f9 = this.f2252x;
            if (f9 < f) {
                break;
            }
            this.f2252x = f9 - f;
        }
        while (true) {
            float f10 = this.f2252x;
            f2 = -f;
            if (f10 >= f2) {
                break;
            }
            this.f2252x = f10 + f;
        }
        while (true) {
            float f11 = this.f2253y;
            if (f11 < f) {
                break;
            }
            this.f2253y = f11 - f;
        }
        while (true) {
            float f12 = this.f2253y;
            if (f12 >= f2) {
                break;
            }
            this.f2253y = f12 + f;
        }
        while (true) {
            float f13 = this.f2254z;
            if (f13 < f) {
                break;
            }
            this.f2254z = f13 - f;
        }
        while (true) {
            float f14 = this.f2254z;
            if (f14 >= f2) {
                return this;
            }
            this.f2254z = f14 + f;
        }
    }

    public Vector limit(float f, float f2) {
        float f3 = this.f2252x;
        if (f3 >= f2) {
            f3 = f2;
        } else if (f3 < f) {
            f3 = f;
        }
        this.f2252x = f3;
        float f4 = this.f2253y;
        if (f4 >= f2) {
            f4 = f2;
        } else if (f4 < f) {
            f4 = f;
        }
        this.f2253y = f4;
        float f5 = this.f2254z;
        if (f5 >= f2) {
            f = f2;
        } else if (f5 >= f) {
            f = f5;
        }
        this.f2254z = f;
        return this;
    }

    public float len() {
        float f = this.f2252x;
        float f2 = this.f2253y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.f2254z;
        return (float) Math.sqrt(f3 + (f4 * f4));
    }

    public float lenSquared() {
        float f = this.f2252x;
        float f2 = this.f2253y;
        float f3 = (f * f) + (f2 * f2);
        float f4 = this.f2254z;
        return f3 + (f4 * f4);
    }

    public Vector normalize() {
        float fLen = len();
        if (fLen != 0.0f) {
            this.f2252x /= fLen;
            this.f2253y /= fLen;
            this.f2254z /= fLen;
        }
        return this;
    }

    public float dot(Vector vector) {
        return (this.f2252x * vector.f2252x) + (this.f2253y * vector.f2253y) + (this.f2254z * vector.f2254z);
    }

    public float dotProduct(Vector vector) {
        return (this.f2252x * vector.f2252x) + (this.f2253y * vector.f2253y) + (this.f2254z * vector.f2254z);
    }

    public float dot(float f, float f2, float f3) {
        return (this.f2252x * f) + (this.f2253y * f2) + (this.f2254z * f3);
    }

    public float dotProduct(float f, float f2, float f3) {
        return (this.f2252x * f) + (this.f2253y * f2) + (this.f2254z * f3);
    }

    public float cross2(Vector vector) {
        return (this.f2252x * vector.f2253y) - (vector.f2252x * this.f2253y);
    }

    public float crossProduct2(Vector vector) {
        return (this.f2252x * vector.f2253y) - (vector.f2252x * this.f2253y);
    }

    public Vector cross(Vector vector) {
        return crossProduct(this, this, vector);
    }

    public Vector crossProduct(Vector vector) {
        return crossProduct(this, this, vector);
    }

    public static Vector cross(Vector vector, Vector vector2, Vector vector3) {
        float f = vector2.f2253y;
        float f2 = vector3.f2254z;
        float f3 = vector2.f2254z;
        float f4 = vector3.f2253y;
        float f5 = (f * f2) - (f3 * f4);
        float f6 = vector3.f2252x;
        float f7 = vector2.f2252x;
        vector.f2252x = f5;
        vector.f2253y = (f3 * f6) - (f2 * f7);
        vector.f2254z = (f7 * f4) - (f * f6);
        return vector;
    }

    public static Vector crossProduct(Vector vector, Vector vector2, Vector vector3) {
        float f = vector2.f2253y;
        float f2 = vector3.f2254z;
        float f3 = vector2.f2254z;
        float f4 = vector3.f2253y;
        float f5 = (f * f2) - (f3 * f4);
        float f6 = vector3.f2252x;
        float f7 = vector2.f2252x;
        vector.f2252x = f5;
        vector.f2253y = (f3 * f6) - (f2 * f7);
        vector.f2254z = (f7 * f4) - (f * f6);
        return vector;
    }

    public float angleXY() {
        float fAtan2 = ((float) Math.atan2(this.f2253y, this.f2252x)) * 57.29578f;
        return fAtan2 < 0.0f ? fAtan2 + 360.0f : fAtan2;
    }

    public float angleXZ() {
        float fAtan2 = ((float) Math.atan2(this.f2254z, this.f2252x)) * 57.29578f;
        return fAtan2 < 0.0f ? fAtan2 + 360.0f : fAtan2;
    }

    public float angleYZ() {
        float fAtan2 = ((float) Math.atan2(this.f2254z, this.f2253y)) * 57.29578f;
        return fAtan2 < 0.0f ? fAtan2 + 360.0f : fAtan2;
    }

    public float getAngle(Vector vector) {
        return ((float) Math.acos(dotProduct(vector) / ((float) Math.sqrt(lenSquared() * vector.lenSquared())))) * 57.29578f;
    }

    public Vector rotateXY(float f) {
        double d = f * 0.017453292f;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = this.f2252x;
        float f3 = this.f2253y;
        this.f2252x = (f2 * fCos) - (f3 * fSin);
        this.f2253y = (f2 * fSin) + (f3 * fCos);
        return this;
    }

    public Vector rotateXZ(float f) {
        double d = f * 0.017453292f;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = this.f2252x;
        float f3 = this.f2254z;
        this.f2252x = (f2 * fCos) - (f3 * fSin);
        this.f2254z = (f2 * fSin) + (f3 * fCos);
        return this;
    }

    public Vector rotateYZ(float f) {
        double d = f * 0.017453292f;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f2 = this.f2253y;
        float f3 = this.f2254z;
        this.f2253y = (f2 * fCos) - (f3 * fSin);
        this.f2254z = (f2 * fSin) + (f3 * fCos);
        return this;
    }

    public Vector rotate(float f, float f2, float f3, float f4) {
        float[] fArr = inVec;
        fArr[0] = this.f2252x;
        fArr[1] = this.f2253y;
        fArr[2] = this.f2254z;
        fArr[3] = 1.0f;
        Matrix.setIdentityM(matrix, 0);
        Matrix.rotateM(matrix, 0, f, f2, f3, f4);
        Matrix.multiplyMV(outVec, 0, matrix, 0, inVec, 0);
        float[] fArr2 = outVec;
        this.f2252x = fArr2[0];
        this.f2253y = fArr2[1];
        this.f2254z = fArr2[2];
        return this;
    }

    public Vector rotate(float f, float f2, float f3) {
        return rotate(this, f, f2, f3);
    }

    public static Vector rotate(Vector vector, float f, float f2, float f3) {
        float[] fArr = inVec;
        fArr[0] = vector.f2252x;
        fArr[1] = vector.f2253y;
        fArr[2] = vector.f2254z;
        fArr[3] = 1.0f;
        Matrix.setIdentityM(matrix, 0);
        if (f != 0.0f) {
            Matrix.rotateM(matrix, 0, f, 1.0f, 0.0f, 0.0f);
        }
        if (f2 != 0.0f) {
            Matrix.rotateM(matrix, 0, f2, 0.0f, 1.0f, 0.0f);
        }
        if (f3 != 0.0f) {
            Matrix.rotateM(matrix, 0, f3, 0.0f, 0.0f, 1.0f);
        }
        Matrix.multiplyMV(outVec, 0, matrix, 0, inVec, 0);
        float[] fArr2 = outVec;
        vector.f2252x = fArr2[0];
        vector.f2253y = fArr2[1];
        vector.f2254z = fArr2[2];
        return vector;
    }

    public static Vector[] rotate(Vector[] vectorArr, float f, float f2, float f3) {
        Matrix.setIdentityM(matrix, 0);
        if (f != 0.0f) {
            Matrix.rotateM(matrix, 0, f, 1.0f, 0.0f, 0.0f);
        }
        if (f2 != 0.0f) {
            Matrix.rotateM(matrix, 0, f2, 0.0f, 1.0f, 0.0f);
        }
        if (f3 != 0.0f) {
            Matrix.rotateM(matrix, 0, f3, 0.0f, 0.0f, 1.0f);
        }
        int length = vectorArr != null ? vectorArr.length : 0;
        for (int i = 0; i < length; i++) {
            if (vectorArr[i] != null) {
                float[] fArr = inVec;
                fArr[0] = vectorArr[i].f2252x;
                fArr[1] = vectorArr[i].f2253y;
                fArr[2] = vectorArr[i].f2254z;
                fArr[3] = 1.0f;
                Matrix.multiplyMV(outVec, 0, matrix, 0, fArr, 0);
                Vector vector = vectorArr[i];
                float[] fArr2 = outVec;
                vector.f2252x = fArr2[0];
                vectorArr[i].f2253y = fArr2[1];
                vectorArr[i].f2254z = fArr2[2];
            }
        }
        return vectorArr;
    }

    public Vector rotate(Vector vector, float f) {
        rotate(vector.f2252x * f, vector.f2253y * f, vector.f2254z * f);
        return this;
    }

    public Vector rotate(Vector vector) {
        return rotate(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public Vector rotate_inv(float f, float f2, float f3) {
        float[] fArr = inVec;
        fArr[0] = this.f2252x;
        fArr[1] = this.f2253y;
        fArr[2] = this.f2254z;
        fArr[3] = 1.0f;
        Matrix.setIdentityM(matrix, 0);
        if (f3 != 0.0f) {
            Matrix.rotateM(matrix, 0, f3, 0.0f, 0.0f, 1.0f);
        }
        if (f2 != 0.0f) {
            Matrix.rotateM(matrix, 0, f2, 0.0f, 1.0f, 0.0f);
        }
        if (f != 0.0f) {
            Matrix.rotateM(matrix, 0, f, 1.0f, 0.0f, 0.0f);
        }
        Matrix.multiplyMV(outVec, 0, matrix, 0, inVec, 0);
        float[] fArr2 = outVec;
        this.f2252x = fArr2[0];
        this.f2253y = fArr2[1];
        this.f2254z = fArr2[2];
        return this;
    }

    public Vector rotate_inv(Vector vector, float f) {
        rotate_inv(vector.f2252x * f, vector.f2253y * f, vector.f2254z * f);
        return this;
    }

    public Vector rotate_inv(Vector vector) {
        rotate_inv(vector, -1.0f);
        return this;
    }

    public float[] getQuat() {
        return new float[]{this.f2252x, this.f2253y, this.f2254z, 1.0f};
    }

    public Vector setQuat(float[] fArr) {
        this.f2252x = fArr[0];
        this.f2253y = fArr[1];
        this.f2254z = fArr[2];
        return this;
    }

    public float distance(Vector vector) {
        return distance(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public float distance(float f, float f2) {
        return distance(f, f2, this.f2254z);
    }

    public float distance(float f, float f2, float f3) {
        return (float) Math.sqrt(distSquared(f, f2, f3));
    }

    public float distSquared(Vector vector) {
        return distSquared(vector.f2252x, vector.f2253y, vector.f2254z);
    }

    public float distSquared(float f, float f2) {
        return distSquared(f, f2, this.f2254z);
    }

    public float distSquared(float f, float f2, float f3) {
        float f4 = this.f2252x - f;
        float f5 = this.f2253y - f2;
        float f6 = this.f2254z - f3;
        return (f4 * f4) + (f5 * f5) + (f6 * f6);
    }

    public Vector swap(Vector vector) {
        float f = this.f2252x;
        this.f2252x = vector.f2252x;
        vector.f2252x = f;
        float f2 = this.f2253y;
        this.f2253y = vector.f2253y;
        vector.f2253y = f2;
        float f3 = this.f2254z;
        this.f2254z = vector.f2254z;
        vector.f2254z = f3;
        return this;
    }

    public Vector swapXY() {
        float f = this.f2252x;
        this.f2252x = this.f2253y;
        this.f2253y = f;
        return this;
    }

    public float slope(Vector vector) {
        float f = vector.f2252x;
        float f2 = this.f2252x;
        if (f != f2) {
            return (vector.f2253y - this.f2253y) / (f - f2);
        }
        return vector.f2253y - this.f2253y >= 0.0f ? Float.MAX_VALUE : Float.MIN_VALUE;
    }

    public float slope() {
        float f = this.f2252x;
        if (f != 0.0f) {
            return this.f2253y / f;
        }
        return this.f2253y >= 0.0f ? Float.MAX_VALUE : Float.MIN_VALUE;
    }

    public Vector sign() {
        this.f2252x = Math.signum(this.f2252x);
        this.f2253y = Math.signum(this.f2253y);
        this.f2254z = Math.signum(this.f2254z);
        return this;
    }

    public String toString() {
        return String.format(Locale.US, "(%f,%f,%f)", Float.valueOf(this.f2252x), Float.valueOf(this.f2253y), Float.valueOf(this.f2254z));
    }

    public String toString(String str) {
        return String.format(Locale.US, str, Float.valueOf(this.f2252x), Float.valueOf(this.f2253y), Float.valueOf(this.f2254z));
    }
}
