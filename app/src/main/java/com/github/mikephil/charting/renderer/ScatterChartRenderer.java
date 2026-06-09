package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* JADX INFO: loaded from: classes.dex */
public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer;

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mPixelBuffer = new float[2];
        this.mChart = scatterDataProvider;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (T t : this.mChart.getScatterData().getDataSets()) {
            if (t.isVisible()) {
                drawDataSet(canvas, t);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        Transformer transformer = this.mChart.getTransformer(iScatterDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
        if (shapeRenderer == null) {
            Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
            return;
        }
        int iMin = (int) Math.min(Math.ceil(iScatterDataSet.getEntryCount() * this.mAnimator.getPhaseX()), iScatterDataSet.getEntryCount());
        for (int i = 0; i < iMin; i++) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex(i);
            this.mPixelBuffer[0] = entryForIndex.getX();
            this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
            transformer.pointValuesToPixel(this.mPixelBuffer);
            if (!viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                return;
            }
            if (viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) && viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                this.mRenderPaint.setColor(iScatterDataSet.getColor(i / 2));
                ViewPortHandler viewPortHandler2 = this.mViewPortHandler;
                float[] fArr = this.mPixelBuffer;
                shapeRenderer.renderShape(canvas, iScatterDataSet, viewPortHandler2, fArr[0], fArr[1], this.mRenderPaint);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00bb  */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drawValues(android.graphics.Canvas r19) {
        /*
            r18 = this;
            r9 = r18
            com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider r0 = r9.mChart
            boolean r0 = r9.isDrawingValuesAllowed(r0)
            if (r0 == 0) goto Lc4
            com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider r0 = r9.mChart
            com.github.mikephil.charting.data.ScatterData r0 = r0.getScatterData()
            java.util.List r10 = r0.getDataSets()
            r11 = 0
            r12 = 0
        L16:
            com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider r0 = r9.mChart
            com.github.mikephil.charting.data.ScatterData r0 = r0.getScatterData()
            int r0 = r0.getDataSetCount()
            if (r12 >= r0) goto Lc4
            java.lang.Object r0 = r10.get(r12)
            r13 = r0
            com.github.mikephil.charting.interfaces.datasets.IScatterDataSet r13 = (com.github.mikephil.charting.interfaces.datasets.IScatterDataSet) r13
            boolean r0 = r9.shouldDrawValues(r13)
            if (r0 != 0) goto L31
            goto Lc0
        L31:
            r9.applyValueTextStyle(r13)
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r0 = r9.mXBounds
            com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider r1 = r9.mChart
            r0.set(r1, r13)
            com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider r0 = r9.mChart
            com.github.mikephil.charting.components.YAxis$AxisDependency r1 = r13.getAxisDependency()
            com.github.mikephil.charting.utils.Transformer r1 = r0.getTransformer(r1)
            com.github.mikephil.charting.animation.ChartAnimator r0 = r9.mAnimator
            float r3 = r0.getPhaseX()
            com.github.mikephil.charting.animation.ChartAnimator r0 = r9.mAnimator
            float r4 = r0.getPhaseY()
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r0 = r9.mXBounds
            int r5 = r0.min
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r0 = r9.mXBounds
            int r6 = r0.max
            r2 = r13
            float[] r14 = r1.generateTransformedValuesScatter(r2, r3, r4, r5, r6)
            float r0 = r13.getScatterShapeSize()
            float r15 = com.github.mikephil.charting.utils.Utils.convertDpToPixel(r0)
            r8 = 0
        L67:
            int r0 = r14.length
            if (r8 >= r0) goto Lc0
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r9.mViewPortHandler
            r1 = r14[r8]
            boolean r0 = r0.isInBoundsRight(r1)
            if (r0 != 0) goto L75
            goto Lc0
        L75:
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r9.mViewPortHandler
            r1 = r14[r8]
            boolean r0 = r0.isInBoundsLeft(r1)
            if (r0 == 0) goto Lbb
            com.github.mikephil.charting.utils.ViewPortHandler r0 = r9.mViewPortHandler
            int r1 = r8 + 1
            r2 = r14[r1]
            boolean r0 = r0.isInBoundsY(r2)
            if (r0 != 0) goto L8c
            goto Lbb
        L8c:
            int r0 = r8 / 2
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r2 = r9.mXBounds
            int r2 = r2.min
            int r2 = r2 + r0
            com.github.mikephil.charting.data.Entry r4 = r13.getEntryForIndex(r2)
            com.github.mikephil.charting.formatter.IValueFormatter r2 = r13.getValueFormatter()
            float r3 = r4.getY()
            r6 = r14[r8]
            r1 = r14[r1]
            float r7 = r1 - r15
            com.github.mikephil.charting.renderer.BarLineScatterCandleBubbleRenderer$XBounds r1 = r9.mXBounds
            int r1 = r1.min
            int r0 = r0 + r1
            int r16 = r13.getValueTextColor(r0)
            r0 = r18
            r1 = r19
            r5 = r12
            r17 = r8
            r8 = r16
            r0.drawValue(r1, r2, r3, r4, r5, r6, r7, r8)
            goto Lbd
        Lbb:
            r17 = r8
        Lbd:
            int r8 = r17 + 2
            goto L67
        Lc0:
            int r12 = r12 + 1
            goto L16
        Lc4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.ScatterChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                ?? entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.f1968x, (float) pixelForValues.f1969y);
                    drawHighlightLines(canvas, (float) pixelForValues.f1968x, (float) pixelForValues.f1969y, iScatterDataSet);
                }
            }
        }
    }
}
