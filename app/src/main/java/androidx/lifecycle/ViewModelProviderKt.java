package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewModelProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002*\u00020\u0003H\u0087\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m2290d2 = {"get", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/ViewModelProvider;", "(Landroidx/lifecycle/ViewModelProvider;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel-ktx_release"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class ViewModelProviderKt {
    public static final /* synthetic */ <VM extends ViewModel> VM get(ViewModelProvider get) {
        Intrinsics.checkNotNullParameter(get, "$this$get");
        Intrinsics.reifiedOperationMarker(4, "VM");
        VM vm = (VM) get.get(ViewModel.class);
        Intrinsics.checkNotNullExpressionValue(vm, "get(VM::class.java)");
        return vm;
    }
}
