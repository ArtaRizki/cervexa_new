package com.gizthon.camera.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.util.Log;

import org.tensorflow.lite.Interpreter;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ViaModelHelper {
    private static final String TAG = "ViaModelHelper";
    private Interpreter tflite;
    private int inputWidth;
    private int inputHeight;
    private boolean isInitialized = false;

    private String shapeInfo = "";

    public ViaModelHelper(Context context) {
        try {
            MappedByteBuffer tfliteModel = loadModelFile(context, "via_model.tflite");
            Interpreter.Options options = new Interpreter.Options();
            options.setNumThreads(4);
            tflite = new Interpreter(tfliteModel, options);

            // Log input/output shapes to determine architecture
            int[] inputShape = tflite.getInputTensor(0).shape();
            int[] outputShape = tflite.getOutputTensor(0).shape();
            
            shapeInfo = "In: " + Arrays.toString(inputShape) + "\nOut: " + Arrays.toString(outputShape);
            Log.d(TAG, shapeInfo);

            // Assuming typical input format: [1, height, width, 3]
            if (inputShape.length == 4) {
                inputHeight = inputShape[1];
                inputWidth = inputShape[2];
            } else {
                inputHeight = 416; // Fallback
                inputWidth = 416;
            }
            
            isInitialized = true;
            Log.d(TAG, "Model via_model.tflite loaded successfully.");
        } catch (Exception e) {
            shapeInfo = "Error: " + e.getMessage();
            Log.e(TAG, "Error loading model: ", e);
        }
    }

    public String getShapeInfo() {
        return shapeInfo;
    }

    private MappedByteBuffer loadModelFile(Context context, String modelName) throws IOException {
        AssetFileDescriptor fileDescriptor = context.getAssets().openFd(modelName);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public void close() {
        if (tflite != null) {
            tflite.close();
            tflite = null;
        }
    }

    public boolean isInitialized() {
        return isInitialized;
    }
    
    // TODO: Implement inference logic (detect method) once we confirm the output shape.
}
