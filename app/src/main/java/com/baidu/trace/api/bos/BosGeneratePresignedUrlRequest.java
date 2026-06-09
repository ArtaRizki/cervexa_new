package com.baidu.trace.api.bos;

/* JADX INFO: loaded from: classes.dex */
public final class BosGeneratePresignedUrlRequest extends BosObjectRequest {

    /* JADX INFO: renamed from: a */
    private ImageProcessCommand f1331a;

    /* JADX INFO: renamed from: b */
    private ImageWatermarkCommand f1332b;

    /* JADX INFO: renamed from: c */
    private TextWatermarkCommand f1333c;

    public BosGeneratePresignedUrlRequest() {
        this.f1331a = null;
        this.f1332b = null;
        this.f1333c = null;
    }

    public BosGeneratePresignedUrlRequest(int i, long j) {
        super(i, j);
        this.f1331a = null;
        this.f1332b = null;
        this.f1333c = null;
    }

    public BosGeneratePresignedUrlRequest(int i, long j, String str, BosObjectType bosObjectType) {
        super(i, j, str, bosObjectType);
        this.f1331a = null;
        this.f1332b = null;
        this.f1333c = null;
    }

    public BosGeneratePresignedUrlRequest(int i, long j, String str, BosObjectType bosObjectType, ImageProcessCommand imageProcessCommand, ImageWatermarkCommand imageWatermarkCommand, TextWatermarkCommand textWatermarkCommand) {
        this(i, j, str, bosObjectType);
        this.f1331a = imageProcessCommand;
        this.f1332b = imageWatermarkCommand;
        this.f1333c = textWatermarkCommand;
    }

    public final ImageProcessCommand getImageProcessCommand() {
        return this.f1331a;
    }

    public final ImageWatermarkCommand getImageWatermarkCommand() {
        return this.f1332b;
    }

    public final TextWatermarkCommand getTextWatermarkCommand() {
        return this.f1333c;
    }

    public final void setImageProcessCommand(ImageProcessCommand imageProcessCommand) {
        this.f1331a = imageProcessCommand;
    }

    public final void setImageWatermarkCommand(ImageWatermarkCommand imageWatermarkCommand) {
        this.f1332b = imageWatermarkCommand;
    }

    public final void setTextWatermarkCommand(TextWatermarkCommand textWatermarkCommand) {
        this.f1333c = textWatermarkCommand;
    }

    @Override // com.baidu.trace.api.bos.BosObjectRequest
    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer("BosGeneratePresignedUrlRequest{");
        stringBuffer.append("imageProcessCommand=");
        stringBuffer.append(this.f1331a);
        stringBuffer.append(", imageWatermarkCommand=");
        stringBuffer.append(this.f1332b);
        stringBuffer.append(", textWatermarkCommand=");
        stringBuffer.append(this.f1333c);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
