package p035rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import p035rx.Observable;
import p035rx.Observer;
import p035rx.Subscriber;
import p035rx.functions.Action0;
import p035rx.subjects.Subject;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class BufferUntilSubscriber<T> extends Subject<T, T> {
    static final Observer EMPTY_OBSERVER = new Observer() { // from class: rx.internal.operators.BufferUntilSubscriber.1
        @Override // p035rx.Observer
        public void onCompleted() {
        }

        @Override // p035rx.Observer
        public void onError(Throwable th) {
        }

        @Override // p035rx.Observer
        public void onNext(Object obj) {
        }
    };
    private boolean forward;
    final State<T> state;

    public static <T> BufferUntilSubscriber<T> create() {
        return new BufferUntilSubscriber<>(new State());
    }

    static final class State<T> extends AtomicReference<Observer<? super T>> {
        private static final long serialVersionUID = 8026705089538090368L;
        final Object guard = new Object();
        boolean emitting = false;
        final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue<>();

        /* JADX INFO: renamed from: nl */
        final NotificationLite<T> f3477nl = NotificationLite.instance();

        State() {
        }

        boolean casObserverRef(Observer<? super T> observer, Observer<? super T> observer2) {
            return compareAndSet(observer, observer2);
        }
    }

    static final class OnSubscribeAction<T> implements Observable.OnSubscribe<T> {
        final State<T> state;

        public OnSubscribeAction(State<T> state) {
            this.state = state;
        }

        @Override // p035rx.functions.Action1
        public void call(Subscriber<? super T> subscriber) {
            boolean z;
            if (this.state.casObserverRef(null, subscriber)) {
                subscriber.add(Subscriptions.create(new Action0() { // from class: rx.internal.operators.BufferUntilSubscriber.OnSubscribeAction.1
                    @Override // p035rx.functions.Action0
                    public void call() {
                        OnSubscribeAction.this.state.set(BufferUntilSubscriber.EMPTY_OBSERVER);
                    }
                }));
                synchronized (this.state.guard) {
                    z = true;
                    if (this.state.emitting) {
                        z = false;
                    } else {
                        this.state.emitting = true;
                    }
                }
                if (!z) {
                    return;
                }
                NotificationLite notificationLiteInstance = NotificationLite.instance();
                while (true) {
                    Object objPoll = this.state.buffer.poll();
                    if (objPoll != null) {
                        notificationLiteInstance.accept(this.state.get(), objPoll);
                    } else {
                        synchronized (this.state.guard) {
                            if (this.state.buffer.isEmpty()) {
                                this.state.emitting = false;
                                return;
                            }
                        }
                    }
                }
            } else {
                subscriber.onError(new IllegalStateException("Only one subscriber allowed!"));
            }
        }
    }

    private BufferUntilSubscriber(State<T> state) {
        super(new OnSubscribeAction(state));
        this.forward = false;
        this.state = state;
    }

    private void emit(Object obj) {
        synchronized (this.state.guard) {
            this.state.buffer.add(obj);
            if (this.state.get() != null && !this.state.emitting) {
                this.forward = true;
                this.state.emitting = true;
            }
        }
        if (!this.forward) {
            return;
        }
        while (true) {
            Object objPoll = this.state.buffer.poll();
            if (objPoll == null) {
                return;
            } else {
                this.state.f3477nl.accept(this.state.get(), objPoll);
            }
        }
    }

    @Override // p035rx.Observer
    public void onCompleted() {
        if (this.forward) {
            this.state.get().onCompleted();
        } else {
            emit(this.state.f3477nl.completed());
        }
    }

    @Override // p035rx.Observer
    public void onError(Throwable th) {
        if (this.forward) {
            this.state.get().onError(th);
        } else {
            emit(this.state.f3477nl.error(th));
        }
    }

    @Override // p035rx.Observer
    public void onNext(T t) {
        if (this.forward) {
            this.state.get().onNext(t);
        } else {
            emit(this.state.f3477nl.next(t));
        }
    }

    @Override // p035rx.subjects.Subject
    public boolean hasObservers() {
        boolean z;
        synchronized (this.state.guard) {
            z = this.state.get() != null;
        }
        return z;
    }
}
