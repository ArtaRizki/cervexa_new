package p035rx.internal.util;

import java.util.concurrent.CountDownLatch;
import p035rx.Subscription;

/* JADX INFO: loaded from: classes2.dex */
public final class BlockingUtils {
    private BlockingUtils() {
    }

    public static void awaitForComplete(CountDownLatch countDownLatch, Subscription subscription) {
        if (countDownLatch.getCount() == 0) {
            return;
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            subscription.unsubscribe();
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for subscription to complete.", e);
        }
    }
}
