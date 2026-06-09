package androidx.lifecycle;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewTreeViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002¨\u0006\u0003"}, m2290d2 = {"findViewTreeViewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "Landroid/view/View;", "lifecycle-viewmodel-ktx_release"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class ViewTreeViewModelKt {
    public static final ViewModelStoreOwner findViewTreeViewModelStoreOwner(View findViewTreeViewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(findViewTreeViewModelStoreOwner, "$this$findViewTreeViewModelStoreOwner");
        return ViewTreeViewModelStoreOwner.get(findViewTreeViewModelStoreOwner);
    }
}
