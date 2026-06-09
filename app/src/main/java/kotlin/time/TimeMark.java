package kotlin.time;

import kotlin.Metadata;

/* JADX INFO: compiled from: TimeSource.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(m2288bv = {1, 0, 3}, m2289d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u0004H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\u001b\u0010\n\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\r\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0010"}, m2290d2 = {"Lkotlin/time/TimeMark;", "", "()V", "elapsedNow", "Lkotlin/time/Duration;", "elapsedNow-UwyO8pc", "()D", "hasNotPassedNow", "", "hasPassedNow", "minus", "duration", "minus-LRDsOJo", "(D)Lkotlin/time/TimeMark;", "plus", "plus-LRDsOJo", "kotlin-stdlib"}, m2291k = 1, m2292mv = {1, 4, 0})
public abstract class TimeMark {
    /* JADX INFO: renamed from: elapsedNow-UwyO8pc */
    public abstract double mo3604elapsedNowUwyO8pc();

    /* JADX INFO: renamed from: plus-LRDsOJo */
    public TimeMark mo3605plusLRDsOJo(double duration) {
        return new AdjustedTimeMark(this, duration, null);
    }

    /* JADX INFO: renamed from: minus-LRDsOJo, reason: not valid java name */
    public TimeMark m3661minusLRDsOJo(double duration) {
        return mo3605plusLRDsOJo(Duration.m3652unaryMinusUwyO8pc(duration));
    }

    public final boolean hasPassedNow() {
        return !Duration.m3632isNegativeimpl(mo3604elapsedNowUwyO8pc());
    }

    public final boolean hasNotPassedNow() {
        return Duration.m3632isNegativeimpl(mo3604elapsedNowUwyO8pc());
    }
}
