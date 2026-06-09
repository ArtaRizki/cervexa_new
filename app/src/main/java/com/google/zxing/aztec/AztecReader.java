package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.aztec.decoder.Decoder;
import com.google.zxing.aztec.detector.Detector;
import com.google.zxing.common.DecoderResult;
import java.util.Hashtable;

/* JADX INFO: loaded from: classes.dex */
public final class AztecReader implements Reader {
    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap) throws NotFoundException, FormatException {
        return decode(binaryBitmap, null);
    }

    @Override // com.google.zxing.Reader
    public Result decode(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException, FormatException {
        ResultPointCallback resultPointCallback;
        AztecDetectorResult aztecDetectorResultDetect = new Detector(binaryBitmap.getBlackMatrix()).detect();
        ResultPoint[] points = aztecDetectorResultDetect.getPoints();
        if (hashtable != null && aztecDetectorResultDetect.getPoints() != null && (resultPointCallback = (ResultPointCallback) hashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) != null) {
            for (int i = 0; i < aztecDetectorResultDetect.getPoints().length; i++) {
                resultPointCallback.foundPossibleResultPoint(aztecDetectorResultDetect.getPoints()[i]);
            }
        }
        DecoderResult decoderResultDecode = new Decoder().decode(aztecDetectorResultDetect);
        Result result = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), points, BarcodeFormat.AZTEC);
        if (decoderResultDecode.getByteSegments() != null) {
            result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResultDecode.getByteSegments());
        }
        if (decoderResultDecode.getECLevel() != null) {
            result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResultDecode.getECLevel().toString());
        }
        return result;
    }

    @Override // com.google.zxing.Reader
    public void reset() {
    }
}
