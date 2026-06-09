package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.tencent.connect.common.Constants;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int iMin = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
            for (int i2 = 0; i2 < iMin; i2++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i2)).getX();
                this.mBarShadowRectBuffer.top = x - barWidth;
                this.mBarShadowRectBuffer.bottom = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z2 = iBarDataSet.getColors().size() == 1;
        if (z2) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        for (int i3 = 0; i3 < barBuffer.size(); i3 += 4) {
            int i4 = i3 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i4])) {
                return;
            }
            int i5 = i3 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i5])) {
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i3 / 4));
                }
                int i6 = i3 + 2;
                canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i5], barBuffer.buffer[i6], barBuffer.buffer[i4], this.mRenderPaint);
                if (z) {
                    canvas.drawRect(barBuffer.buffer[i3], barBuffer.buffer[i5], barBuffer.buffer[i6], barBuffer.buffer[i4], this.mBarBorderPaint);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        int i;
        IBarDataSet iBarDataSet;
        float[] fArr;
        int i2;
        float[] fArr2;
        float f;
        int i3;
        IValueFormatter iValueFormatter;
        List list2;
        BarBuffer barBuffer;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float fConvertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean zIsDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i4 = 0;
            while (i4 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet2 = (IBarDataSet) dataSets.get(i4);
                if (shouldDrawValues(iBarDataSet2)) {
                    boolean zIsInverted = this.mChart.isInverted(iBarDataSet2.getAxisDependency());
                    applyValueTextStyle(iBarDataSet2);
                    float f2 = 2.0f;
                    float fCalcTextHeight = Utils.calcTextHeight(this.mValuePaint, Constants.VIA_REPORT_TYPE_SHARE_TO_QQ) / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet2.getValueFormatter();
                    BarBuffer barBuffer2 = this.mBarBuffers[i4];
                    float phaseY = this.mAnimator.getPhaseY();
                    if (iBarDataSet2.isStacked()) {
                        list = dataSets;
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet2.getAxisDependency());
                        int i5 = 0;
                        int length = 0;
                        while (i5 < iBarDataSet2.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry = (BarEntry) iBarDataSet2.getEntryForIndex(i5);
                            int valueTextColor = iBarDataSet2.getValueTextColor(i5);
                            float[] yVals = barEntry.getYVals();
                            if (yVals == null) {
                                int i6 = length + 1;
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i6])) {
                                    break;
                                }
                                if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[length]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i6])) {
                                    String formattedValue = valueFormatter.getFormattedValue(barEntry.getY(), barEntry, i4, this.mViewPortHandler);
                                    float fCalcTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    float f3 = zIsDrawValueAboveBarEnabled ? fConvertDpToPixel : -(fCalcTextWidth + fConvertDpToPixel);
                                    float f4 = zIsDrawValueAboveBarEnabled ? -(fCalcTextWidth + fConvertDpToPixel) : fConvertDpToPixel;
                                    if (zIsInverted) {
                                        f3 = (-f3) - fCalcTextWidth;
                                        f4 = (-f4) - fCalcTextWidth;
                                    }
                                    float f5 = barBuffer2.buffer[length + 2];
                                    if (barEntry.getY() < 0.0f) {
                                        f3 = f4;
                                    }
                                    iBarDataSet = iBarDataSet2;
                                    fArr = yVals;
                                    i = i5;
                                    drawValue(canvas, formattedValue, f5 + f3, barBuffer2.buffer[i6] + fCalcTextHeight, valueTextColor);
                                }
                            } else {
                                i = i5;
                                iBarDataSet = iBarDataSet2;
                                fArr = yVals;
                                int length2 = fArr.length * 2;
                                float[] fArr3 = new float[length2];
                                float f6 = -barEntry.getNegativeSum();
                                int i7 = 0;
                                int i8 = 0;
                                float f7 = 0.0f;
                                while (i7 < length2) {
                                    float f8 = fArr[i8];
                                    if (f8 == 0.0f && (f7 == 0.0f || f6 == 0.0f)) {
                                        float f9 = f6;
                                        f6 = f8;
                                        f = f9;
                                    } else if (f8 >= 0.0f) {
                                        f7 += f8;
                                        f = f6;
                                        f6 = f7;
                                    } else {
                                        f = f6 - f8;
                                    }
                                    fArr3[i7] = f6 * phaseY;
                                    i7 += 2;
                                    i8++;
                                    f6 = f;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i9 = 0;
                                while (i9 < length2) {
                                    float f10 = fArr[i9 / 2];
                                    String formattedValue2 = valueFormatter.getFormattedValue(f10, barEntry, i4, this.mViewPortHandler);
                                    BarEntry barEntry2 = barEntry;
                                    float fCalcTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float f11 = zIsDrawValueAboveBarEnabled ? fConvertDpToPixel : -(fCalcTextWidth2 + fConvertDpToPixel);
                                    int i10 = length2;
                                    float f12 = zIsDrawValueAboveBarEnabled ? -(fCalcTextWidth2 + fConvertDpToPixel) : fConvertDpToPixel;
                                    if (zIsInverted) {
                                        f11 = (-f11) - fCalcTextWidth2;
                                        f12 = (-f12) - fCalcTextWidth2;
                                    }
                                    boolean z = (f10 == 0.0f && f6 == 0.0f && f7 > 0.0f) || f10 < 0.0f;
                                    float f13 = fArr3[i9];
                                    if (z) {
                                        f11 = f12;
                                    }
                                    float f14 = f13 + f11;
                                    float f15 = (barBuffer2.buffer[length + 1] + barBuffer2.buffer[length + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f15)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(f14) && this.mViewPortHandler.isInBoundsBottom(f15)) {
                                        i2 = i9;
                                        fArr2 = fArr3;
                                        drawValue(canvas, formattedValue2, f14, f15 + fCalcTextHeight, valueTextColor);
                                    } else {
                                        i2 = i9;
                                        fArr2 = fArr3;
                                    }
                                    i9 = i2 + 2;
                                    barEntry = barEntry2;
                                    fArr3 = fArr2;
                                    length2 = i10;
                                }
                            }
                            length = fArr == null ? length + 4 : length + (fArr.length * 4);
                            i5 = i + 1;
                            iBarDataSet2 = iBarDataSet;
                        }
                    } else {
                        int i11 = 0;
                        while (i11 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            int i12 = i11 + 1;
                            float f16 = (barBuffer2.buffer[i12] + barBuffer2.buffer[i11 + 3]) / f2;
                            if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i12])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i11]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i12])) {
                                BarEntry barEntry3 = (BarEntry) iBarDataSet2.getEntryForIndex(i11 / 4);
                                float y = barEntry3.getY();
                                String formattedValue3 = valueFormatter.getFormattedValue(y, barEntry3, i4, this.mViewPortHandler);
                                float fCalcTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                float f17 = zIsDrawValueAboveBarEnabled ? fConvertDpToPixel : -(fCalcTextWidth3 + fConvertDpToPixel);
                                IValueFormatter iValueFormatter2 = valueFormatter;
                                float f18 = zIsDrawValueAboveBarEnabled ? -(fCalcTextWidth3 + fConvertDpToPixel) : fConvertDpToPixel;
                                if (zIsInverted) {
                                    f17 = (-f17) - fCalcTextWidth3;
                                    f18 = (-f18) - fCalcTextWidth3;
                                }
                                float f19 = barBuffer2.buffer[i11 + 2];
                                if (y >= 0.0f) {
                                    f18 = f17;
                                }
                                i3 = i11;
                                iValueFormatter = iValueFormatter2;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                                drawValue(canvas, formattedValue3, f18 + f19, f16 + fCalcTextHeight, iBarDataSet2.getValueTextColor(i11 / 2));
                            } else {
                                i3 = i11;
                                iValueFormatter = valueFormatter;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i11 = i3 + 4;
                            barBuffer2 = barBuffer;
                            valueFormatter = iValueFormatter;
                            dataSets = list2;
                            f2 = 2.0f;
                        }
                        list = dataSets;
                    }
                } else {
                    list = dataSets;
                }
                i4++;
                dataSets = list;
            }
        }
    }

    protected void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f2, f - f4, f3, f + f4);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    protected boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }
}
