package androidx.core.text;

import android.text.TextUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: String.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, m2290d2 = {"htmlEncode", "", "core-ktx_release"}, m2291k = 2, m2292mv = {1, 1, 15})
public final class StringKt {
    public static final String htmlEncode(String htmlEncode) {
        Intrinsics.checkParameterIsNotNull(htmlEncode, "$this$htmlEncode");
        String strHtmlEncode = TextUtils.htmlEncode(htmlEncode);
        Intrinsics.checkExpressionValueIsNotNull(strHtmlEncode, "TextUtils.htmlEncode(this)");
        return strHtmlEncode;
    }
}
