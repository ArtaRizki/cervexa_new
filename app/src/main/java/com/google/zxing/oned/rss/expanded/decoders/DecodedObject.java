package com.google.zxing.oned.rss.expanded.decoders;

/* JADX INFO: loaded from: classes.dex */
abstract class DecodedObject {
    protected final int newPosition;

    DecodedObject(int i) {
        this.newPosition = i;
    }

    int getNewPosition() {
        return this.newPosition;
    }
}
