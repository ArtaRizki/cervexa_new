package kotlinx.coroutines.debug;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.p029io.ByteStreamsKt;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/* JADX INFO: compiled from: AgentPremain.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0002J\u001a\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0011"}, m2290d2 = {"Lkotlinx/coroutines/debug/AgentPremain;", "", "()V", "enableCreationStackTraces", "", "isInstalledStatically", "()Z", "setInstalledStatically", "(Z)V", "installSignalHandler", "", "premain", "args", "", "instrumentation", "Ljava/lang/instrument/Instrumentation;", "DebugProbesTransformer", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
public final class AgentPremain {
    public static final AgentPremain INSTANCE = new AgentPremain();
    private static final boolean enableCreationStackTraces;
    private static boolean isInstalledStatically;

    static {
        String property = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
        enableCreationStackTraces = property != null ? Boolean.parseBoolean(property) : DebugProbesImpl.INSTANCE.getEnableCreationStackTraces();
    }

    private AgentPremain() {
    }

    public final boolean isInstalledStatically() {
        return isInstalledStatically;
    }

    public final void setInstalledStatically(boolean z) {
        isInstalledStatically = z;
    }

    @JvmStatic
    public static final void premain(String args, Instrumentation instrumentation) {
        isInstalledStatically = true;
        instrumentation.addTransformer(DebugProbesTransformer.INSTANCE);
        DebugProbesImpl.INSTANCE.setEnableCreationStackTraces(enableCreationStackTraces);
        DebugProbesImpl.INSTANCE.install();
        INSTANCE.installSignalHandler();
    }

    /* JADX INFO: compiled from: AgentPremain.kt */
    @Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J:\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u000e"}, m2290d2 = {"Lkotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer;", "Ljava/lang/instrument/ClassFileTransformer;", "()V", "transform", "", "loader", "Ljava/lang/ClassLoader;", "className", "", "classBeingRedefined", "Ljava/lang/Class;", "protectionDomain", "Ljava/security/ProtectionDomain;", "classfileBuffer", "kotlinx-coroutines-core"}, m2291k = 1, m2292mv = {1, 4, 0})
    public static final class DebugProbesTransformer implements ClassFileTransformer {
        public static final DebugProbesTransformer INSTANCE = new DebugProbesTransformer();

        private DebugProbesTransformer() {
        }

        public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
            if (!Intrinsics.areEqual(className, "kotlin/coroutines/jvm/internal/DebugProbesKt")) {
                return null;
            }
            AgentPremain.INSTANCE.setInstalledStatically(true);
            return ByteStreamsKt.readBytes(loader.getResourceAsStream("DebugProbesKt.bin"));
        }
    }

    private final void installSignalHandler() {
        try {
            Signal.handle(new Signal("TRAP"), new SignalHandler() { // from class: kotlinx.coroutines.debug.AgentPremain.installSignalHandler.1
                public final void handle(Signal signal) {
                    if (DebugProbesImpl.INSTANCE.isInstalled$kotlinx_coroutines_core()) {
                        DebugProbesImpl.INSTANCE.dumpCoroutines(System.out);
                    } else {
                        System.out.println((Object) "Cannot perform coroutines dump, debug probes are disabled");
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }
}
