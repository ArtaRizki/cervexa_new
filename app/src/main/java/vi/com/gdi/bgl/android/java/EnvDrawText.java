package vi.com.gdi.bgl.android.java;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.SparseArray;
import com.baidu.mapapi.common.SysOSUtil;

/* JADX INFO: loaded from: classes2.dex */
public class EnvDrawText {

    /* JADX INFO: renamed from: a */
    private static final String f3549a = EnvDrawText.class.getSimpleName();

    /* JADX INFO: renamed from: pt */
    public static Paint f3550pt = null;
    public static int iWordWidthMax = 0;
    public static int iWordHightMax = 0;
    public static boolean bBmpChange = false;
    public static Bitmap bmp = null;
    public static Canvas canvasTemp = null;
    public static int[] buffer = null;
    public static SparseArray<C2946a> fontCache = null;

    public static synchronized int[] drawText(String str, int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
        Typeface typefaceCreate;
        Paint paint;
        int iMeasureText;
        C2946a c2946a;
        if (f3550pt == null) {
            f3550pt = new Paint();
        } else {
            f3550pt.reset();
        }
        String phoneType = android.os.Build.MODEL;
        int i7 = (phoneType == null || !phoneType.equals("vivo X3L")) ? i2 : 0;
        if (i7 == 1) {
            typefaceCreate = Typeface.create(Typeface.DEFAULT, 1);
            paint = f3550pt;
        } else if (i7 != 2) {
            typefaceCreate = Typeface.create(Typeface.DEFAULT, 0);
            paint = f3550pt;
        } else {
            typefaceCreate = Typeface.create(Typeface.DEFAULT, 2);
            paint = f3550pt;
        }
        paint.setTypeface(typefaceCreate);
        f3550pt.setSubpixelText(true);
        f3550pt.setAntiAlias(true);
        if (i7 != 0 && fontCache != null && (c2946a = fontCache.get(i7)) != null) {
            f3550pt.setTypeface(c2946a.f3551a);
        }
        f3550pt.setTextSize(i);
        int i8 = 92;
        int iIndexOf = str.indexOf(92, 0);
        if (iIndexOf == -1) {
            Paint.FontMetrics fontMetrics = f3550pt.getFontMetrics();
            int iMeasureText2 = (int) f3550pt.measureText(str);
            int iCeil = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
            iArr[0] = iMeasureText2;
            iArr[1] = iCeil;
            int iPow = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(iMeasureText2) / Math.log(2.0d)));
            int iPow2 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(iCeil) / Math.log(2.0d)));
            if (iWordWidthMax < iPow || iWordHightMax < iPow2) {
                bBmpChange = true;
                iWordWidthMax = iPow;
                iWordHightMax = iPow2;
            }
            iArr[2] = iWordWidthMax;
            iArr[3] = iWordHightMax;
            if (bBmpChange) {
                if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                }
                bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Bitmap.Config.ARGB_8888);
                if (canvasTemp == null) {
                    canvasTemp = new Canvas();
                }
                canvasTemp.setBitmap(bmp);
            } else {
                bmp.eraseColor(0);
            }
            if (((-16777216) & i5) == 0) {
                canvasTemp.drawColor(33554431);
            } else {
                canvasTemp.drawColor(i5);
            }
            if (i6 != 0) {
                f3550pt.setStrokeWidth(i6);
                f3550pt.setStrokeCap(Paint.Cap.ROUND);
                f3550pt.setStrokeJoin(Paint.Join.ROUND);
                f3550pt.setStyle(Paint.Style.STROKE);
                f3550pt.setColor(i4);
                canvasTemp.drawText(str, 0.0f, 0.0f - fontMetrics.ascent, f3550pt);
            }
            f3550pt.setStyle(Paint.Style.FILL);
            f3550pt.setColor(i3);
            canvasTemp.drawText(str, 0.0f, 0.0f - fontMetrics.ascent, f3550pt);
        } else {
            int i9 = iIndexOf + 1;
            int iMeasureText3 = (int) f3550pt.measureText(str.substring(0, iIndexOf));
            int i10 = 2;
            while (true) {
                int iIndexOf2 = str.indexOf(i8, i9);
                if (iIndexOf2 <= 0) {
                    break;
                }
                int iMeasureText4 = (int) f3550pt.measureText(str.substring(i9, iIndexOf2));
                if (iMeasureText4 > iMeasureText3) {
                    iMeasureText3 = iMeasureText4;
                }
                i9 = iIndexOf2 + 1;
                i10++;
                i8 = 92;
            }
            if (i9 != str.length() && (iMeasureText = (int) f3550pt.measureText(str.substring(i9, str.length()))) > iMeasureText3) {
                iMeasureText3 = iMeasureText;
            }
            Paint.FontMetrics fontMetrics2 = f3550pt.getFontMetrics();
            int lineHeight = (int) Math.ceil(fontMetrics2.descent - fontMetrics2.ascent);
            int iCeil2 = i10 * lineHeight;
            iArr[0] = iMeasureText3;
            iArr[1] = iCeil2;
            int iPow3 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(iMeasureText3) / Math.log(2.0d)));
            int iPow4 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(iCeil2) / Math.log(2.0d)));
            if (iWordWidthMax < iPow3 || iWordHightMax < iPow4) {
                bBmpChange = true;
                iWordWidthMax = iPow3;
                iWordHightMax = iPow4;
            }
            iArr[2] = iWordWidthMax;
            iArr[3] = iWordHightMax;
            if (bBmpChange) {
                if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                }
                bmp = Bitmap.createBitmap(iWordWidthMax, iWordHightMax, Bitmap.Config.ARGB_8888);
                if (canvasTemp == null) {
                    canvasTemp = new Canvas();
                }
                canvasTemp.setBitmap(bmp);
            } else {
                bmp.eraseColor(0);
            }
            if (((-16777216) & i5) == 0) {
                canvasTemp.drawColor(33554431);
            } else {
                canvasTemp.drawColor(i5);
            }
            int i11 = 0;
            int i12 = 92;
            int i13 = 0;
            while (true) {
                int iIndexOf3 = str.indexOf(i12, i11);
                if (iIndexOf3 <= 0) {
                    break;
                }
                String strSubstring = str.substring(i11, iIndexOf3);
                int iMeasureText5 = (int) f3550pt.measureText(strSubstring);
                int i14 = iIndexOf3 + 1;
                if (i6 != 0) {
                    f3550pt.setStrokeWidth(i6);
                    f3550pt.setStrokeCap(Paint.Cap.ROUND);
                    f3550pt.setStrokeJoin(Paint.Join.ROUND);
                    f3550pt.setStyle(Paint.Style.STROKE);
                    f3550pt.setColor(i4);
                    canvasTemp.drawText(strSubstring, (iArr[0] - iMeasureText5) / 2, (i13 * lineHeight) - fontMetrics2.ascent, f3550pt);
                }
                f3550pt.setStyle(Paint.Style.FILL);
                f3550pt.setColor(i3);
                canvasTemp.drawText(strSubstring, (iArr[0] - iMeasureText5) / 2, (i13 * lineHeight) - fontMetrics2.ascent, f3550pt);
                i13++;
                i11 = i14;
                i12 = 92;
            }
            if (i11 != str.length()) {
                String strSubstring2 = str.substring(i11, str.length());
                int iMeasureText6 = (int) f3550pt.measureText(strSubstring2);
                if (i6 != 0) {
                    f3550pt.setStrokeWidth(i6);
                    f3550pt.setStrokeCap(Paint.Cap.ROUND);
                    f3550pt.setStrokeJoin(Paint.Join.ROUND);
                    f3550pt.setStyle(Paint.Style.STROKE);
                    f3550pt.setColor(i4);
                    canvasTemp.drawText(strSubstring2, (iArr[0] - iMeasureText6) / 2, (i13 * lineHeight) - fontMetrics2.ascent, f3550pt);
                }
                f3550pt.setStyle(Paint.Style.FILL);
                f3550pt.setColor(i3);
                canvasTemp.drawText(strSubstring2, (iArr[0] - iMeasureText6) / 2, (i13 * lineHeight) - fontMetrics2.ascent, f3550pt);
            }
        }
        int i15 = iWordWidthMax * iWordHightMax;
        if (bBmpChange) {
            buffer = new int[i15];
        }
        bmp.getPixels(buffer, 0, iWordWidthMax, 0, 0, iWordWidthMax, iWordHightMax);
        bBmpChange = false;
        return buffer;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025 A[PHI: r2
  0x0025: PHI (r2v1 int) = (r2v0 int), (r2v5 int) binds: [B:7:0x0019, B:9:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static short[] getTextSize(java.lang.String r5, int r6, int r7) {
        /*
            int r0 = r5.length()
            if (r0 != 0) goto L8
            r5 = 0
            return r5
        L8:
            android.graphics.Paint r1 = new android.graphics.Paint
            r1.<init>()
            r2 = 1
            r1.setSubpixelText(r2)
            r1.setAntiAlias(r2)
            float r6 = (float) r6
            r1.setTextSize(r6)
            r6 = 0
            if (r7 == r2) goto L25
            r2 = 2
            if (r7 == r2) goto L25
            android.graphics.Typeface r7 = android.graphics.Typeface.DEFAULT
            android.graphics.Typeface r7 = android.graphics.Typeface.create(r7, r6)
            goto L2b
        L25:
            android.graphics.Typeface r7 = android.graphics.Typeface.DEFAULT
            android.graphics.Typeface r7 = android.graphics.Typeface.create(r7, r2)
        L2b:
            r1.setTypeface(r7)
            short[] r7 = new short[r0]
            r2 = 0
        L31:
            if (r2 >= r0) goto L43
            int r3 = r2 + 1
            java.lang.String r4 = r5.substring(r6, r3)
            float r4 = r1.measureText(r4)
            int r4 = (int) r4
            short r4 = (short) r4
            r7[r2] = r4
            r2 = r3
            goto L31
        L43:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: vi.com.gdi.bgl.android.java.EnvDrawText.getTextSize(java.lang.String, int, int):short[]");
    }

    public static synchronized void registFontCache(int i, Typeface typeface) {
        if (i == 0 || typeface == null) {
            return;
        }
        if (fontCache == null) {
            fontCache = new SparseArray<>();
        }
        C2946a c2946a = fontCache.get(i);
        if (c2946a == null) {
            C2946a c2946a2 = new C2946a();
            c2946a2.f3551a = typeface;
            c2946a2.f3552b++;
            fontCache.put(i, c2946a2);
        } else {
            c2946a.f3552b++;
        }
    }

    public static synchronized void removeFontCache(int i) {
        C2946a c2946a = fontCache.get(i);
        if (c2946a == null) {
            return;
        }
        c2946a.f3552b--;
        if (c2946a.f3552b == 0) {
            fontCache.remove(i);
        }
    }
}
