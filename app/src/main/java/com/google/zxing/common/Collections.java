package com.google.zxing.common;

import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public final class Collections {
    private Collections() {
    }

    public static void insertionSort(Vector vector, Comparator comparator) {
        int size = vector.size();
        for (int i = 1; i < size; i++) {
            Object objElementAt = vector.elementAt(i);
            int i2 = i - 1;
            while (i2 >= 0) {
                Object objElementAt2 = vector.elementAt(i2);
                if (comparator.compare(objElementAt2, objElementAt) > 0) {
                    vector.setElementAt(objElementAt2, i2 + 1);
                    i2--;
                }
            }
            vector.setElementAt(objElementAt, i2 + 1);
        }
    }
}
