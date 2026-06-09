package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.C0689l.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.baidu.mapapi.map.l */
/* JADX INFO: loaded from: classes.dex */
class C0689l<T extends a> {

    /* JADX INFO: renamed from: a */
    private final C0683f f675a;

    /* JADX INFO: renamed from: b */
    private final int f676b;

    /* JADX INFO: renamed from: c */
    private List<T> f677c;

    /* JADX INFO: renamed from: d */
    private List<C0689l<T>> f678d;

    /* JADX INFO: renamed from: com.baidu.mapapi.map.l$a */
    static abstract class a {
        a() {
        }

        /* JADX INFO: renamed from: a */
        abstract Point mo408a();
    }

    private C0689l(double d, double d2, double d3, double d4, int i) {
        this(new C0683f(d, d2, d3, d4), i);
    }

    public C0689l(C0683f c0683f) {
        this(c0683f, 0);
    }

    private C0689l(C0683f c0683f, int i) {
        this.f678d = null;
        this.f675a = c0683f;
        this.f676b = i;
    }

    /* JADX INFO: renamed from: a */
    private void m440a() {
        ArrayList arrayList = new ArrayList(4);
        this.f678d = arrayList;
        arrayList.add(new C0689l(this.f675a.f662a, this.f675a.f666e, this.f675a.f663b, this.f675a.f667f, this.f676b + 1));
        this.f678d.add(new C0689l<>(this.f675a.f666e, this.f675a.f664c, this.f675a.f663b, this.f675a.f667f, this.f676b + 1));
        this.f678d.add(new C0689l<>(this.f675a.f662a, this.f675a.f666e, this.f675a.f667f, this.f675a.f665d, this.f676b + 1));
        this.f678d.add(new C0689l<>(this.f675a.f666e, this.f675a.f664c, this.f675a.f667f, this.f675a.f665d, this.f676b + 1));
        List<T> list = this.f677c;
        this.f677c = null;
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            m441a(r7.mo408a().x, r7.mo408a().y, it.next());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m441a(double d, double d2, T t) {
        List<C0689l<T>> list;
        int i;
        if (this.f678d == null) {
            if (this.f677c == null) {
                this.f677c = new ArrayList();
            }
            this.f677c.add(t);
            if (this.f677c.size() <= 40 || this.f676b >= 40) {
                return;
            }
            m440a();
            return;
        }
        double d3 = this.f675a.f667f;
        double d4 = this.f675a.f666e;
        if (d2 < d3) {
            list = this.f678d;
            i = d < d4 ? 0 : 1;
        } else {
            list = this.f678d;
            i = d < d4 ? 2 : 3;
        }
        list.get(i).m441a(d, d2, t);
    }

    /* JADX INFO: renamed from: a */
    private void m442a(C0683f c0683f, Collection<T> collection) {
        if (this.f675a.m436a(c0683f)) {
            List<C0689l<T>> list = this.f678d;
            if (list != null) {
                Iterator<C0689l<T>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().m442a(c0683f, collection);
                }
            } else if (this.f677c != null) {
                if (c0683f.m437b(this.f675a)) {
                    collection.addAll(this.f677c);
                    return;
                }
                for (T t : this.f677c) {
                    if (c0683f.m435a(t.mo408a())) {
                        collection.add(t);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public Collection<T> m443a(C0683f c0683f) {
        ArrayList arrayList = new ArrayList();
        m442a(c0683f, arrayList);
        return arrayList;
    }

    /* JADX INFO: renamed from: a */
    public void m444a(T t) {
        Point pointMo408a = t.mo408a();
        if (this.f675a.m433a(pointMo408a.x, pointMo408a.y)) {
            m441a(pointMo408a.x, pointMo408a.y, t);
        }
    }
}
