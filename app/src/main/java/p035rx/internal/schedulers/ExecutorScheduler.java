package p035rx.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import p035rx.Scheduler;
import p035rx.Subscription;
import p035rx.functions.Action0;
import p035rx.plugins.RxJavaPlugins;
import p035rx.subscriptions.CompositeSubscription;
import p035rx.subscriptions.MultipleAssignmentSubscription;
import p035rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes2.dex */
public final class ExecutorScheduler extends Scheduler {
    final Executor executor;

    public ExecutorScheduler(Executor executor) {
        this.executor = executor;
    }

    @Override // p035rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new ExecutorSchedulerWorker(this.executor);
    }

    static final class ExecutorSchedulerWorker extends Scheduler.Worker implements Runnable {
        final Executor executor;
        final ConcurrentLinkedQueue<ScheduledAction> queue = new ConcurrentLinkedQueue<>();
        final AtomicInteger wip = new AtomicInteger();
        final CompositeSubscription tasks = new CompositeSubscription();
        final ScheduledExecutorService service = GenericScheduledExecutorService.getInstance();

        public ExecutorSchedulerWorker(Executor executor) {
            this.executor = executor;
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduledAction = new ScheduledAction(action0, this.tasks);
            this.tasks.add(scheduledAction);
            this.queue.offer(scheduledAction);
            if (this.wip.getAndIncrement() == 0) {
                try {
                    this.executor.execute(this);
                } catch (RejectedExecutionException e) {
                    this.tasks.remove(scheduledAction);
                    this.wip.decrementAndGet();
                    RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                    throw e;
                }
            }
            return scheduledAction;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.tasks.isUnsubscribed()) {
                ScheduledAction scheduledActionPoll = this.queue.poll();
                if (scheduledActionPoll == null) {
                    return;
                }
                if (!scheduledActionPoll.isUnsubscribed()) {
                    if (!this.tasks.isUnsubscribed()) {
                        scheduledActionPoll.run();
                    } else {
                        this.queue.clear();
                        return;
                    }
                }
                if (this.wip.decrementAndGet() == 0) {
                    return;
                }
            }
            this.queue.clear();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // rx.Scheduler.Worker
        public Subscription schedule(final Action0 action0, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return schedule(action0);
            }
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            final MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription2.set(multipleAssignmentSubscription);
            this.tasks.add(multipleAssignmentSubscription2);
            final Subscription subscriptionCreate = Subscriptions.create(new Action0() { // from class: rx.internal.schedulers.ExecutorScheduler.ExecutorSchedulerWorker.1
                @Override // p035rx.functions.Action0
                public void call() {
                    ExecutorSchedulerWorker.this.tasks.remove(multipleAssignmentSubscription2);
                }
            });
            ScheduledAction scheduledAction = new ScheduledAction(new Action0() { // from class: rx.internal.schedulers.ExecutorScheduler.ExecutorSchedulerWorker.2
                @Override // p035rx.functions.Action0
                public void call() {
                    if (multipleAssignmentSubscription2.isUnsubscribed()) {
                        return;
                    }
                    Subscription subscriptionSchedule = ExecutorSchedulerWorker.this.schedule(action0);
                    multipleAssignmentSubscription2.set(subscriptionSchedule);
                    if (subscriptionSchedule.getClass() == ScheduledAction.class) {
                        ((ScheduledAction) subscriptionSchedule).add(subscriptionCreate);
                    }
                }
            });
            multipleAssignmentSubscription.set(scheduledAction);
            try {
                scheduledAction.add(this.service.schedule(scheduledAction, j, timeUnit));
                return subscriptionCreate;
            } catch (RejectedExecutionException e) {
                RxJavaPlugins.getInstance().getErrorHandler().handleError(e);
                throw e;
            }
        }

        @Override // p035rx.Subscription
        public boolean isUnsubscribed() {
            return this.tasks.isUnsubscribed();
        }

        @Override // p035rx.Subscription
        public void unsubscribe() {
            this.tasks.unsubscribe();
            this.queue.clear();
        }
    }
}
