package androidx.core.graphics;

import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.Drawable;
import com.generalplus.GoPlusDrone.Fragment.BaseFragment;
import com.tencent.tauth.AuthActivity;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImageDecoder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u00020\u0001*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b\u001aR\u0010\r\u001a\u00020\u000e*\u00020\u00022C\b\u0004\u0010\u0003\u001a=\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0004¢\u0006\u0002\b\fH\u0087\b¨\u0006\u000f"}, m2290d2 = {"decodeBitmap", "Landroid/graphics/Bitmap;", "Landroid/graphics/ImageDecoder$Source;", AuthActivity.ACTION_KEY, "Lkotlin/Function3;", "Landroid/graphics/ImageDecoder;", "Landroid/graphics/ImageDecoder$ImageInfo;", "Lkotlin/ParameterName;", BaseFragment.DATA_NAME, "info", "source", "", "Lkotlin/ExtensionFunctionType;", "decodeDrawable", "Landroid/graphics/drawable/Drawable;", "core-ktx_release"}, m2291k = 2, m2292mv = {1, 1, 15})
public final class ImageDecoderKt {
    public static final Bitmap decodeBitmap(ImageDecoder.Source decodeBitmap, final Function3<? super ImageDecoder, ? super ImageDecoder.ImageInfo, ? super ImageDecoder.Source, Unit> action) throws IOException {
        Intrinsics.checkParameterIsNotNull(decodeBitmap, "$this$decodeBitmap");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Bitmap bitmapDecodeBitmap = ImageDecoder.decodeBitmap(decodeBitmap, new ImageDecoder.OnHeaderDecodedListener() { // from class: androidx.core.graphics.ImageDecoderKt.decodeBitmap.1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
                Function3 function3 = action;
                Intrinsics.checkExpressionValueIsNotNull(decoder, "decoder");
                Intrinsics.checkExpressionValueIsNotNull(info, "info");
                Intrinsics.checkExpressionValueIsNotNull(source, "source");
                function3.invoke(decoder, info, source);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(bitmapDecodeBitmap, "ImageDecoder.decodeBitma…ction(info, source)\n    }");
        return bitmapDecodeBitmap;
    }

    public static final Drawable decodeDrawable(ImageDecoder.Source decodeDrawable, final Function3<? super ImageDecoder, ? super ImageDecoder.ImageInfo, ? super ImageDecoder.Source, Unit> action) throws IOException {
        Intrinsics.checkParameterIsNotNull(decodeDrawable, "$this$decodeDrawable");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Drawable drawableDecodeDrawable = ImageDecoder.decodeDrawable(decodeDrawable, new ImageDecoder.OnHeaderDecodedListener() { // from class: androidx.core.graphics.ImageDecoderKt.decodeDrawable.1
            @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
            public final void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info, ImageDecoder.Source source) {
                Function3 function3 = action;
                Intrinsics.checkExpressionValueIsNotNull(decoder, "decoder");
                Intrinsics.checkExpressionValueIsNotNull(info, "info");
                Intrinsics.checkExpressionValueIsNotNull(source, "source");
                function3.invoke(decoder, info, source);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(drawableDecodeDrawable, "ImageDecoder.decodeDrawa…ction(info, source)\n    }");
        return drawableDecodeDrawable;
    }
}
