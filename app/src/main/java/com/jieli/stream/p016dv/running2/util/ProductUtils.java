package com.jieli.stream.p016dv.running2.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProductUtils {
    private static final String[] PHONE_MODEL = {"PLK-AL10"};
    private static final List<String> MODEL_LIST = new ArrayList(Arrays.asList(PHONE_MODEL));

    public static boolean isExistModel(String str) {
        return MODEL_LIST.contains(str);
    }
}
