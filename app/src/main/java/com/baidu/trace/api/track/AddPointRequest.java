package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.Point;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AddPointRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1517a;

    /* JADX INFO: renamed from: b */
    private Point f1518b;

    /* JADX INFO: renamed from: c */
    private String f1519c;

    /* JADX INFO: renamed from: d */
    private Map<String, String> f1520d;

    public AddPointRequest() {
    }

    public AddPointRequest(int i, long j) {
        super(i, j);
    }

    public AddPointRequest(int i, long j, String str, Point point, String str2, Map<String, String> map) {
        this(i, j);
        this.f1517a = str;
        this.f1518b = point;
        this.f1519c = str2;
        this.f1520d = map;
    }

    public Map<String, String> getColumns() {
        return this.f1520d;
    }

    public String getEntityName() {
        return this.f1517a;
    }

    public String getObjectName() {
        return this.f1519c;
    }

    public Point getPoint() {
        return this.f1518b;
    }

    public void setColumns(Map<String, String> map) {
        this.f1520d = map;
    }

    public void setEntityName(String str) {
        this.f1517a = str;
    }

    public void setObjectName(String str) {
        this.f1519c = str;
    }

    public void setPoint(Point point) {
        this.f1518b = point;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AddPointRequest{");
        stringBuffer.append("tag=");
        stringBuffer.append(this.tag);
        stringBuffer.append(", serviceId=");
        stringBuffer.append(this.serviceId);
        stringBuffer.append(", entityName='");
        stringBuffer.append(this.f1517a);
        stringBuffer.append('\'');
        stringBuffer.append(", point=");
        stringBuffer.append(this.f1518b);
        stringBuffer.append(", objectName='");
        stringBuffer.append(this.f1519c);
        stringBuffer.append('\'');
        stringBuffer.append(", columns=");
        stringBuffer.append(this.f1520d);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
