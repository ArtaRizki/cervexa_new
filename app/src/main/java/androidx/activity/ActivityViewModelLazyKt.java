package androidx.activity;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: ActivityViewModelLazy.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a4\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\u0010\b\n\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, m2290d2 = {"viewModels", "Lkotlin/Lazy;", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/activity/ComponentActivity;", "factoryProducer", "Lkotlin/Function0;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "activity-ktx_release"}, m2291k = 2, m2292mv = {1, 4, 0})
public final class ActivityViewModelLazyKt {
    public static /* synthetic */ Lazy viewModels$default(ComponentActivity viewModels, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = (Function0) null;
        }
        Intrinsics.checkNotNullParameter(viewModels, "$this$viewModels");
        if (function0 == null) {
            function0 = new ActivityViewModelLazyKt$viewModels$factoryPromise$1(viewModels);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new C00321(viewModels), function0);
    }

    public static final /* synthetic */ <VM extends ViewModel> Lazy<VM> viewModels(ComponentActivity viewModels, Function0<? extends ViewModelProvider.Factory> function0) {
        Intrinsics.checkNotNullParameter(viewModels, "$this$viewModels");
        if (function0 == null) {
            function0 = new ActivityViewModelLazyKt$viewModels$factoryPromise$1(viewModels);
        }
        Intrinsics.reifiedOperationMarker(4, "VM");
        return new ViewModelLazy(Reflection.getOrCreateKotlinClass(ViewModel.class), new C00321(viewModels), function0);
    }

    /* JADX INFO: renamed from: androidx.activity.ActivityViewModelLazyKt$viewModels$1 */
    /* JADX INFO: compiled from: ActivityViewModelLazy.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m2290d2 = {"<anonymous>", "Landroidx/lifecycle/ViewModelStore;", "VM", "Landroidx/lifecycle/ViewModel;", "invoke"}, m2291k = 3, m2292mv = {1, 4, 0})
    public static final class C00321 extends Lambda implements Function0<ViewModelStore> {
        final /* synthetic */ ComponentActivity $this_viewModels;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C00321(ComponentActivity componentActivity) {
            super(0);
            this.$this_viewModels = componentActivity;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.$this_viewModels.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }
}
