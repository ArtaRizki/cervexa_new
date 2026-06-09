package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.app.SharedElementCallback;
import androidx.core.os.CancellationSignal;
import androidx.core.util.Preconditions;
import androidx.core.view.OneShotPreDrawListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class DefaultSpecialEffectsController extends SpecialEffectsController {
    DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    @Override // androidx.fragment.app.SpecialEffectsController
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void executeOperations(java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r11, boolean r12) {
        /*
            r10 = this;
            java.util.Iterator r0 = r11.iterator()
            r1 = 0
            r2 = r1
        L6:
            boolean r3 = r0.hasNext()
            r4 = 1
            if (r3 == 0) goto L43
            java.lang.Object r3 = r0.next()
            androidx.fragment.app.SpecialEffectsController$Operation r3 = (androidx.fragment.app.SpecialEffectsController.Operation) r3
            androidx.fragment.app.Fragment r5 = r3.getFragment()
            android.view.View r5 = r5.mView
            androidx.fragment.app.SpecialEffectsController$Operation$State r5 = androidx.fragment.app.SpecialEffectsController.Operation.State.from(r5)
            int[] r6 = androidx.fragment.app.DefaultSpecialEffectsController.C033910.f63xe493b431
            androidx.fragment.app.SpecialEffectsController$Operation$State r7 = r3.getFinalState()
            int r7 = r7.ordinal()
            r6 = r6[r7]
            if (r6 == r4) goto L3b
            r4 = 2
            if (r6 == r4) goto L3b
            r4 = 3
            if (r6 == r4) goto L3b
            r4 = 4
            if (r6 == r4) goto L35
            goto L6
        L35:
            androidx.fragment.app.SpecialEffectsController$Operation$State r4 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r5 == r4) goto L6
            r2 = r3
            goto L6
        L3b:
            androidx.fragment.app.SpecialEffectsController$Operation$State r4 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r5 != r4) goto L6
            if (r1 != 0) goto L6
            r1 = r3
            goto L6
        L43:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>(r11)
            java.util.Iterator r11 = r11.iterator()
        L56:
            boolean r6 = r11.hasNext()
            if (r6 == 0) goto L94
            java.lang.Object r6 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r6 = (androidx.fragment.app.SpecialEffectsController.Operation) r6
            androidx.core.os.CancellationSignal r7 = new androidx.core.os.CancellationSignal
            r7.<init>()
            r6.markStartedSpecialEffect(r7)
            androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo r8 = new androidx.fragment.app.DefaultSpecialEffectsController$AnimationInfo
            r8.<init>(r6, r7)
            r0.add(r8)
            androidx.core.os.CancellationSignal r7 = new androidx.core.os.CancellationSignal
            r7.<init>()
            r6.markStartedSpecialEffect(r7)
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r8 = new androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo
            r9 = 0
            if (r12 == 0) goto L82
            if (r6 != r1) goto L85
            goto L84
        L82:
            if (r6 != r2) goto L85
        L84:
            r9 = 1
        L85:
            r8.<init>(r6, r7, r12, r9)
            r3.add(r8)
            androidx.fragment.app.DefaultSpecialEffectsController$1 r7 = new androidx.fragment.app.DefaultSpecialEffectsController$1
            r7.<init>()
            r6.addCompletionListener(r7)
            goto L56
        L94:
            java.util.Map r11 = r10.startTransitions(r3, r12, r1, r2)
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r4)
            boolean r12 = r11.containsValue(r12)
            r10.startAnimations(r0, r12, r11)
            java.util.Iterator r11 = r5.iterator()
        La7:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto Lb7
            java.lang.Object r12 = r11.next()
            androidx.fragment.app.SpecialEffectsController$Operation r12 = (androidx.fragment.app.SpecialEffectsController.Operation) r12
            r10.applyContainerChanges(r12)
            goto La7
        Lb7:
            r5.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.executeOperations(java.util.List, boolean):void");
    }

    /* JADX INFO: renamed from: androidx.fragment.app.DefaultSpecialEffectsController$10 */
    static /* synthetic */ class C033910 {

        /* JADX INFO: renamed from: $SwitchMap$androidx$fragment$app$SpecialEffectsController$Operation$State */
        static final /* synthetic */ int[] f63xe493b431;

        static {
            int[] iArr = new int[SpecialEffectsController.Operation.State.values().length];
            f63xe493b431 = iArr;
            try {
                iArr[SpecialEffectsController.Operation.State.GONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f63xe493b431[SpecialEffectsController.Operation.State.INVISIBLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f63xe493b431[SpecialEffectsController.Operation.State.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f63xe493b431[SpecialEffectsController.Operation.State.VISIBLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private void startAnimations(List<AnimationInfo> list, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        final ViewGroup container = getContainer();
        Context context = container.getContext();
        ArrayList<AnimationInfo> arrayList = new ArrayList();
        boolean z2 = false;
        for (final AnimationInfo animationInfo : list) {
            if (animationInfo.isVisibilityUnchanged()) {
                animationInfo.completeSpecialEffect();
            } else {
                FragmentAnim.AnimationOrAnimator animation = animationInfo.getAnimation(context);
                if (animation == null) {
                    animationInfo.completeSpecialEffect();
                } else {
                    final Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(animationInfo);
                    } else {
                        SpecialEffectsController.Operation operation = animationInfo.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Boolean.TRUE.equals(map.get(operation))) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            animationInfo.completeSpecialEffect();
                        } else {
                            z2 = true;
                            final View view = fragment.mView;
                            container.startViewTransition(view);
                            animator.addListener(new AnimatorListenerAdapter() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.2
                                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                                public void onAnimationEnd(Animator animator2) {
                                    container.endViewTransition(view);
                                    animationInfo.completeSpecialEffect();
                                }
                            });
                            animator.setTarget(view);
                            animator.start();
                            animationInfo.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.3
                                @Override // androidx.core.os.CancellationSignal.OnCancelListener
                                public void onCancel() {
                                    animator.cancel();
                                }
                            });
                        }
                    }
                }
            }
        }
        for (final AnimationInfo animationInfo2 : arrayList) {
            SpecialEffectsController.Operation operation2 = animationInfo2.getOperation();
            Fragment fragment2 = operation2.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo2.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo2.completeSpecialEffect();
            } else {
                final View view2 = fragment2.mView;
                Animation animation2 = (Animation) Preconditions.checkNotNull(((FragmentAnim.AnimationOrAnimator) Preconditions.checkNotNull(animationInfo2.getAnimation(context))).animation);
                if (operation2.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                    view2.startAnimation(animation2);
                    animationInfo2.completeSpecialEffect();
                } else {
                    container.startViewTransition(view2);
                    FragmentAnim.EndViewTransitionAnimation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation2, container, view2);
                    endViewTransitionAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4
                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationRepeat(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationStart(Animation animation3) {
                        }

                        @Override // android.view.animation.Animation.AnimationListener
                        public void onAnimationEnd(Animation animation3) {
                            container.post(new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    container.endViewTransition(view2);
                                    animationInfo2.completeSpecialEffect();
                                }
                            });
                        }
                    });
                    view2.startAnimation(endViewTransitionAnimation);
                }
                animationInfo2.getSignal().setOnCancelListener(new CancellationSignal.OnCancelListener() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.5
                    @Override // androidx.core.os.CancellationSignal.OnCancelListener
                    public void onCancel() {
                        view2.clearAnimation();
                    }
                });
            }
        }
    }

    private Map<SpecialEffectsController.Operation, Boolean> startTransitions(List<TransitionInfo> list, final boolean z, final SpecialEffectsController.Operation operation, final SpecialEffectsController.Operation operation2) {
        View view;
        SpecialEffectsController.Operation operation3;
        Object objMergeTransitionsTogether;
        ArrayList<View> arrayList;
        Object objMergeTransitionsTogether2;
        ArrayList<View> arrayList2;
        HashMap map;
        View view2;
        ArrayMap arrayMap;
        SpecialEffectsController.Operation operation4;
        View view3;
        FragmentTransitionImpl fragmentTransitionImpl;
        HashMap map2;
        SpecialEffectsController.Operation operation5;
        ArrayList<View> arrayList3;
        Rect rect;
        SharedElementCallback enterTransitionCallback;
        SharedElementCallback exitTransitionCallback;
        ArrayList<String> arrayList4;
        final Rect rect2;
        final View view4;
        String strFindKeyForValue;
        ArrayList<String> arrayList5;
        boolean z2 = z;
        SpecialEffectsController.Operation operation6 = operation;
        SpecialEffectsController.Operation operation7 = operation2;
        HashMap map3 = new HashMap();
        final FragmentTransitionImpl fragmentTransitionImpl2 = null;
        for (TransitionInfo transitionInfo : list) {
            if (!transitionInfo.isVisibilityUnchanged()) {
                FragmentTransitionImpl handlingImpl = transitionInfo.getHandlingImpl();
                if (fragmentTransitionImpl2 == null) {
                    fragmentTransitionImpl2 = handlingImpl;
                } else if (handlingImpl != null && fragmentTransitionImpl2 != handlingImpl) {
                    throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + transitionInfo.getOperation().getFragment() + " returned Transition " + transitionInfo.getTransition() + " which uses a different Transition  type than other Fragments.");
                }
            }
        }
        if (fragmentTransitionImpl2 == null) {
            for (TransitionInfo transitionInfo2 : list) {
                map3.put(transitionInfo2.getOperation(), false);
                transitionInfo2.completeSpecialEffect();
            }
            return map3;
        }
        View view5 = new View(getContainer().getContext());
        Rect rect3 = new Rect();
        ArrayList<View> arrayList6 = new ArrayList<>();
        ArrayList<View> arrayList7 = new ArrayList<>();
        ArrayMap arrayMap2 = new ArrayMap();
        Object obj = null;
        View view6 = null;
        boolean z3 = false;
        for (TransitionInfo transitionInfo3 : list) {
            if (!transitionInfo3.hasSharedElementTransition() || operation6 == null || operation7 == null) {
                arrayMap = arrayMap2;
                operation4 = operation7;
                view3 = view5;
                fragmentTransitionImpl = fragmentTransitionImpl2;
                map2 = map3;
                operation5 = operation6;
                arrayList3 = arrayList7;
                rect = rect3;
                view6 = view6;
            } else {
                Object objWrapTransitionInSet = fragmentTransitionImpl2.wrapTransitionInSet(fragmentTransitionImpl2.cloneTransition(transitionInfo3.getSharedElementTransition()));
                ArrayList<String> sharedElementSourceNames = operation2.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementSourceNames2 = operation.getFragment().getSharedElementSourceNames();
                ArrayList<String> sharedElementTargetNames = operation.getFragment().getSharedElementTargetNames();
                View view7 = view6;
                int i = 0;
                while (i < sharedElementTargetNames.size()) {
                    int iIndexOf = sharedElementSourceNames.indexOf(sharedElementTargetNames.get(i));
                    ArrayList<String> arrayList8 = sharedElementTargetNames;
                    if (iIndexOf != -1) {
                        sharedElementSourceNames.set(iIndexOf, sharedElementSourceNames2.get(i));
                    }
                    i++;
                    sharedElementTargetNames = arrayList8;
                }
                ArrayList<String> sharedElementTargetNames2 = operation2.getFragment().getSharedElementTargetNames();
                if (!z2) {
                    enterTransitionCallback = operation.getFragment().getExitTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getEnterTransitionCallback();
                } else {
                    enterTransitionCallback = operation.getFragment().getEnterTransitionCallback();
                    exitTransitionCallback = operation2.getFragment().getExitTransitionCallback();
                }
                int i2 = 0;
                for (int size = sharedElementSourceNames.size(); i2 < size; size = size) {
                    arrayMap2.put(sharedElementSourceNames.get(i2), sharedElementTargetNames2.get(i2));
                    i2++;
                }
                ArrayMap<String, View> arrayMap3 = new ArrayMap<>();
                findNamedViews(arrayMap3, operation.getFragment().mView);
                arrayMap3.retainAll(sharedElementSourceNames);
                if (enterTransitionCallback != null) {
                    enterTransitionCallback.onMapSharedElements(sharedElementSourceNames, arrayMap3);
                    int size2 = sharedElementSourceNames.size() - 1;
                    while (size2 >= 0) {
                        String str = sharedElementSourceNames.get(size2);
                        View view8 = arrayMap3.get(str);
                        if (view8 == null) {
                            arrayMap2.remove(str);
                            arrayList5 = sharedElementSourceNames;
                        } else {
                            arrayList5 = sharedElementSourceNames;
                            if (!str.equals(ViewCompat.getTransitionName(view8))) {
                                arrayMap2.put(ViewCompat.getTransitionName(view8), (String) arrayMap2.remove(str));
                            }
                        }
                        size2--;
                        sharedElementSourceNames = arrayList5;
                    }
                    arrayList4 = sharedElementSourceNames;
                } else {
                    arrayList4 = sharedElementSourceNames;
                    arrayMap2.retainAll(arrayMap3.keySet());
                }
                final ArrayMap<String, View> arrayMap4 = new ArrayMap<>();
                findNamedViews(arrayMap4, operation2.getFragment().mView);
                arrayMap4.retainAll(sharedElementTargetNames2);
                arrayMap4.retainAll(arrayMap2.values());
                if (exitTransitionCallback != null) {
                    exitTransitionCallback.onMapSharedElements(sharedElementTargetNames2, arrayMap4);
                    for (int size3 = sharedElementTargetNames2.size() - 1; size3 >= 0; size3--) {
                        String str2 = sharedElementTargetNames2.get(size3);
                        View view9 = arrayMap4.get(str2);
                        if (view9 == null) {
                            String strFindKeyForValue2 = FragmentTransition.findKeyForValue(arrayMap2, str2);
                            if (strFindKeyForValue2 != null) {
                                arrayMap2.remove(strFindKeyForValue2);
                            }
                        } else if (!str2.equals(ViewCompat.getTransitionName(view9)) && (strFindKeyForValue = FragmentTransition.findKeyForValue(arrayMap2, str2)) != null) {
                            arrayMap2.put(strFindKeyForValue, ViewCompat.getTransitionName(view9));
                        }
                    }
                } else {
                    FragmentTransition.retainValues(arrayMap2, arrayMap4);
                }
                retainMatchingViews(arrayMap3, arrayMap2.keySet());
                retainMatchingViews(arrayMap4, arrayMap2.values());
                if (arrayMap2.isEmpty()) {
                    arrayList6.clear();
                    arrayList7.clear();
                    arrayMap = arrayMap2;
                    arrayList3 = arrayList7;
                    rect = rect3;
                    view3 = view5;
                    fragmentTransitionImpl = fragmentTransitionImpl2;
                    view6 = view7;
                    obj = null;
                    operation4 = operation2;
                    map2 = map3;
                    operation5 = operation;
                } else {
                    FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z2, arrayMap3, true);
                    HashMap map4 = map3;
                    arrayMap = arrayMap2;
                    View view10 = view5;
                    ArrayList<View> arrayList9 = arrayList7;
                    Rect rect4 = rect3;
                    ArrayList<View> arrayList10 = arrayList6;
                    OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.6
                        @Override // java.lang.Runnable
                        public void run() {
                            FragmentTransition.callSharedElementStartEnd(operation2.getFragment(), operation.getFragment(), z, arrayMap4, false);
                        }
                    });
                    Iterator<View> it = arrayMap3.values().iterator();
                    while (it.hasNext()) {
                        captureTransitioningViews(arrayList10, it.next());
                    }
                    if (arrayList4.isEmpty()) {
                        view6 = view7;
                    } else {
                        View view11 = arrayMap3.get(arrayList4.get(0));
                        fragmentTransitionImpl2.setEpicenter(objWrapTransitionInSet, view11);
                        view6 = view11;
                    }
                    Iterator<View> it2 = arrayMap4.values().iterator();
                    while (it2.hasNext()) {
                        captureTransitioningViews(arrayList9, it2.next());
                    }
                    if (!sharedElementTargetNames2.isEmpty() && (view4 = arrayMap4.get(sharedElementTargetNames2.get(0))) != null) {
                        rect2 = rect4;
                        OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.7
                            @Override // java.lang.Runnable
                            public void run() {
                                fragmentTransitionImpl2.getBoundsOnScreen(view4, rect2);
                            }
                        });
                        view3 = view10;
                        z3 = true;
                        fragmentTransitionImpl2.setSharedElementTargets(objWrapTransitionInSet, view3, arrayList10);
                        rect = rect2;
                        arrayList6 = arrayList10;
                        arrayList3 = arrayList9;
                        fragmentTransitionImpl = fragmentTransitionImpl2;
                        fragmentTransitionImpl2.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList3);
                        operation5 = operation;
                        map2 = map4;
                        map2.put(operation5, true);
                        operation4 = operation2;
                        map2.put(operation4, true);
                        obj = objWrapTransitionInSet;
                    } else {
                        rect2 = rect4;
                        view3 = view10;
                        fragmentTransitionImpl2.setSharedElementTargets(objWrapTransitionInSet, view3, arrayList10);
                        rect = rect2;
                        arrayList6 = arrayList10;
                        arrayList3 = arrayList9;
                        fragmentTransitionImpl = fragmentTransitionImpl2;
                        fragmentTransitionImpl2.scheduleRemoveTargets(objWrapTransitionInSet, null, null, null, null, objWrapTransitionInSet, arrayList3);
                        operation5 = operation;
                        map2 = map4;
                        map2.put(operation5, true);
                        operation4 = operation2;
                        map2.put(operation4, true);
                        obj = objWrapTransitionInSet;
                    }
                }
            }
            z2 = z;
            view5 = view3;
            operation7 = operation4;
            rect3 = rect;
            arrayList7 = arrayList3;
            operation6 = operation5;
            map3 = map2;
            arrayMap2 = arrayMap;
            fragmentTransitionImpl2 = fragmentTransitionImpl;
        }
        View view12 = view6;
        ArrayMap arrayMap5 = arrayMap2;
        SpecialEffectsController.Operation operation8 = operation7;
        View view13 = view5;
        FragmentTransitionImpl fragmentTransitionImpl3 = fragmentTransitionImpl2;
        boolean z4 = false;
        HashMap map5 = map3;
        SpecialEffectsController.Operation operation9 = operation6;
        ArrayList<View> arrayList11 = arrayList7;
        Rect rect5 = rect3;
        ArrayList arrayList12 = new ArrayList();
        Iterator<TransitionInfo> it3 = list.iterator();
        Object obj2 = null;
        Object obj3 = null;
        while (it3.hasNext()) {
            TransitionInfo next = it3.next();
            if (next.isVisibilityUnchanged()) {
                map5.put(next.getOperation(), Boolean.valueOf(z4));
                next.completeSpecialEffect();
                it3 = it3;
            } else {
                Iterator<TransitionInfo> it4 = it3;
                Object objCloneTransition = fragmentTransitionImpl3.cloneTransition(next.getTransition());
                SpecialEffectsController.Operation operation10 = next.getOperation();
                boolean z5 = obj != null && (operation10 == operation9 || operation10 == operation8);
                if (objCloneTransition == null) {
                    if (!z5) {
                        map5.put(operation10, Boolean.valueOf(z4));
                        next.completeSpecialEffect();
                    }
                    view = view13;
                    arrayList = arrayList6;
                    arrayList2 = arrayList11;
                    objMergeTransitionsTogether = obj2;
                    map = map5;
                    view2 = view12;
                } else {
                    final ArrayList<View> arrayList13 = new ArrayList<>();
                    Object obj4 = obj2;
                    captureTransitioningViews(arrayList13, operation10.getFragment().mView);
                    if (z5) {
                        if (operation10 == operation9) {
                            arrayList13.removeAll(arrayList6);
                        } else {
                            arrayList13.removeAll(arrayList11);
                        }
                    }
                    if (arrayList13.isEmpty()) {
                        fragmentTransitionImpl3.addTarget(objCloneTransition, view13);
                        view = view13;
                        arrayList = arrayList6;
                        arrayList2 = arrayList11;
                        operation3 = operation10;
                        objMergeTransitionsTogether2 = obj3;
                        map = map5;
                        objMergeTransitionsTogether = obj4;
                    } else {
                        fragmentTransitionImpl3.addTargets(objCloneTransition, arrayList13);
                        view = view13;
                        operation3 = operation10;
                        objMergeTransitionsTogether = obj4;
                        arrayList = arrayList6;
                        objMergeTransitionsTogether2 = obj3;
                        arrayList2 = arrayList11;
                        map = map5;
                        fragmentTransitionImpl3.scheduleRemoveTargets(objCloneTransition, objCloneTransition, arrayList13, null, null, null, null);
                        if (operation3.getFinalState() == SpecialEffectsController.Operation.State.GONE) {
                            fragmentTransitionImpl3.scheduleHideFragmentView(objCloneTransition, operation3.getFragment().mView, arrayList13);
                            OneShotPreDrawListener.add(getContainer(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.8
                                @Override // java.lang.Runnable
                                public void run() {
                                    FragmentTransition.setViewVisibility(arrayList13, 4);
                                }
                            });
                        }
                    }
                    if (operation3.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                        arrayList12.addAll(arrayList13);
                        if (z3) {
                            fragmentTransitionImpl3.setEpicenter(objCloneTransition, rect5);
                        }
                        view2 = view12;
                    } else {
                        view2 = view12;
                        fragmentTransitionImpl3.setEpicenter(objCloneTransition, view2);
                    }
                    map.put(operation3, true);
                    if (next.isOverlapAllowed()) {
                        objMergeTransitionsTogether2 = fragmentTransitionImpl3.mergeTransitionsTogether(objMergeTransitionsTogether2, objCloneTransition, null);
                    } else {
                        objMergeTransitionsTogether = fragmentTransitionImpl3.mergeTransitionsTogether(objMergeTransitionsTogether, objCloneTransition, null);
                    }
                    obj3 = objMergeTransitionsTogether2;
                }
                it3 = it4;
                obj2 = objMergeTransitionsTogether;
                map5 = map;
                view12 = view2;
                view13 = view;
                arrayList6 = arrayList;
                arrayList11 = arrayList2;
                z4 = false;
            }
        }
        ArrayList<View> arrayList14 = arrayList6;
        ArrayList<View> arrayList15 = arrayList11;
        HashMap map6 = map5;
        Object objMergeTransitionsInSequence = fragmentTransitionImpl3.mergeTransitionsInSequence(obj3, obj2, obj);
        for (final TransitionInfo transitionInfo4 : list) {
            if (!transitionInfo4.isVisibilityUnchanged()) {
                Object transition = transitionInfo4.getTransition();
                SpecialEffectsController.Operation operation11 = transitionInfo4.getOperation();
                boolean z6 = obj != null && (operation11 == operation9 || operation11 == operation8);
                if (transition != null || z6) {
                    fragmentTransitionImpl3.setListenerForTransitionEnd(transitionInfo4.getOperation().getFragment(), objMergeTransitionsInSequence, transitionInfo4.getSignal(), new Runnable() { // from class: androidx.fragment.app.DefaultSpecialEffectsController.9
                        @Override // java.lang.Runnable
                        public void run() {
                            transitionInfo4.completeSpecialEffect();
                        }
                    });
                }
            }
        }
        FragmentTransition.setViewVisibility(arrayList12, 4);
        ArrayList<String> arrayListPrepareSetNameOverridesReordered = fragmentTransitionImpl3.prepareSetNameOverridesReordered(arrayList15);
        fragmentTransitionImpl3.beginDelayedTransition(getContainer(), objMergeTransitionsInSequence);
        fragmentTransitionImpl3.setNameOverridesReordered(getContainer(), arrayList14, arrayList15, arrayListPrepareSetNameOverridesReordered, arrayMap5);
        FragmentTransition.setViewVisibility(arrayList12, 0);
        fragmentTransitionImpl3.swapSharedElementTargets(obj, arrayList14, arrayList15);
        return map6;
    }

    void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Iterator<Map.Entry<String, View>> it = arrayMap.entrySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(ViewCompat.getTransitionName(it.next().getValue()))) {
                it.remove();
            }
        }
    }

    void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (ViewGroupCompat.isTransitionGroup(viewGroup)) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    captureTransitioningViews(arrayList, childAt);
                }
            }
            return;
        }
        arrayList.add(view);
    }

    void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    void applyContainerChanges(SpecialEffectsController.Operation operation) {
        operation.getFinalState().applyState(operation.getFragment().mView);
    }

    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation mOperation;
        private final CancellationSignal mSignal;

        SpecialEffectsInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            this.mOperation = operation;
            this.mSignal = cancellationSignal;
        }

        SpecialEffectsController.Operation getOperation() {
            return this.mOperation;
        }

        CancellationSignal getSignal() {
            return this.mSignal;
        }

        boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State stateFrom = SpecialEffectsController.Operation.State.from(this.mOperation.getFragment().mView);
            SpecialEffectsController.Operation.State finalState = this.mOperation.getFinalState();
            return stateFrom == finalState || !(stateFrom == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        void completeSpecialEffect() {
            this.mOperation.completeSpecialEffect(this.mSignal);
        }
    }

    private static class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator mAnimation;
        private boolean mLoadedAnim;

        AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal) {
            super(operation, cancellationSignal);
            this.mLoadedAnim = false;
        }

        FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            if (this.mLoadedAnim) {
                return this.mAnimation;
            }
            FragmentAnim.AnimationOrAnimator animationOrAnimatorLoadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE);
            this.mAnimation = animationOrAnimatorLoadAnimation;
            this.mLoadedAnim = true;
            return animationOrAnimatorLoadAnimation;
        }
    }

    private static class TransitionInfo extends SpecialEffectsInfo {
        private final boolean mOverlapAllowed;
        private final Object mSharedElementTransition;
        private final Object mTransition;

        TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            Object exitTransition;
            Object enterTransition;
            boolean allowEnterTransitionOverlap;
            super(operation, cancellationSignal);
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                if (z) {
                    enterTransition = operation.getFragment().getReenterTransition();
                } else {
                    enterTransition = operation.getFragment().getEnterTransition();
                }
                this.mTransition = enterTransition;
                if (z) {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowReturnTransitionOverlap();
                } else {
                    allowEnterTransitionOverlap = operation.getFragment().getAllowEnterTransitionOverlap();
                }
                this.mOverlapAllowed = allowEnterTransitionOverlap;
            } else {
                if (z) {
                    exitTransition = operation.getFragment().getReturnTransition();
                } else {
                    exitTransition = operation.getFragment().getExitTransition();
                }
                this.mTransition = exitTransition;
                this.mOverlapAllowed = true;
            }
            if (!z2) {
                this.mSharedElementTransition = null;
            } else if (z) {
                this.mSharedElementTransition = operation.getFragment().getSharedElementReturnTransition();
            } else {
                this.mSharedElementTransition = operation.getFragment().getSharedElementEnterTransition();
            }
        }

        Object getTransition() {
            return this.mTransition;
        }

        boolean isOverlapAllowed() {
            return this.mOverlapAllowed;
        }

        public boolean hasSharedElementTransition() {
            return this.mSharedElementTransition != null;
        }

        public Object getSharedElementTransition() {
            return this.mSharedElementTransition;
        }

        FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.mTransition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.mSharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl != null ? handlingImpl : handlingImpl2;
            }
            throw new IllegalArgumentException("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.mTransition + " which uses a different Transition  type than its shared element transition " + this.mSharedElementTransition);
        }

        private FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
