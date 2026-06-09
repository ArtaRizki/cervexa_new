package com.google.zxing.multi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import java.util.Hashtable;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {
    private static final Result[] EMPTY_RESULT_ARRAY = new Result[0];

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    @Override // com.google.zxing.multi.MultipleBarcodeReader
    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Hashtable hashtable) throws NotFoundException {
        Vector vector = new Vector();
        DetectorResult[] detectorResultArrDetectMulti = new MultiDetector(binaryBitmap.getBlackMatrix()).detectMulti(hashtable);
        for (int i = 0; i < detectorResultArrDetectMulti.length; i++) {
            try {
                DecoderResult decoderResultDecode = getDecoder().decode(detectorResultArrDetectMulti[i].getBits());
                Result result = new Result(decoderResultDecode.getText(), decoderResultDecode.getRawBytes(), detectorResultArrDetectMulti[i].getPoints(), BarcodeFormat.QR_CODE);
                if (decoderResultDecode.getByteSegments() != null) {
                    result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, decoderResultDecode.getByteSegments());
                }
                if (decoderResultDecode.getECLevel() != null) {
                    result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, decoderResultDecode.getECLevel().toString());
                }
                vector.addElement(result);
            } catch (ReaderException unused) {
            }
        }
        if (vector.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        Result[] resultArr = new Result[vector.size()];
        for (int i2 = 0; i2 < vector.size(); i2++) {
            resultArr[i2] = (Result) vector.elementAt(i2);
        }
        return resultArr;
    }
}
