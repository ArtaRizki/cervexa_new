package com.baidu.trace.api.entity;

import com.baidu.trace.model.BaseResponse;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CommonResponse extends BaseResponse {

    /* JADX INFO: renamed from: a */
    protected int f1396a;

    /* JADX INFO: renamed from: b */
    protected int f1397b;

    /* JADX INFO: renamed from: c */
    protected List<EntityInfo> f1398c;

    public CommonResponse() {
    }

    public CommonResponse(int i, int i2, String str) {
        super(i, i2, str);
    }

    public CommonResponse(int i, int i2, String str, int i3, int i4, List<EntityInfo> list) {
        super(i, i2, str);
        this.f1396a = i3;
        this.f1397b = i4;
        this.f1398c = list;
    }

    public List<EntityInfo> getEntities() {
        return this.f1398c;
    }

    public int getSize() {
        return this.f1397b;
    }

    public int getTotal() {
        return this.f1396a;
    }

    public void setEntities(List<EntityInfo> list) {
        this.f1398c = list;
    }

    public void setSize(int i) {
        this.f1397b = i;
    }

    public void setTotal(int i) {
        this.f1396a = i;
    }

    public String toString() {
        return "CommonResponse [tag=" + this.tag + ", status=" + this.status + ", message=" + this.message + ", total=" + this.f1396a + ", size=" + this.f1397b + ", entities=" + this.f1398c + "]";
    }
}
