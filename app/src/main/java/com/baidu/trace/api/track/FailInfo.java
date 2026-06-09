package com.baidu.trace.api.track;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FailInfo {

    /* JADX INFO: renamed from: a */
    private List<ParamError> f1538a;

    /* JADX INFO: renamed from: b */
    private List<InternalError> f1539b;

    public class InternalError {

        /* JADX INFO: renamed from: a */
        String f1540a;

        /* JADX INFO: renamed from: b */
        TrackPoint f1541b;

        public InternalError(FailInfo failInfo) {
        }

        public InternalError(FailInfo failInfo, String str, TrackPoint trackPoint) {
            this.f1540a = str;
            this.f1541b = trackPoint;
        }

        public String getEntityName() {
            return this.f1540a;
        }

        public TrackPoint getTrackPoint() {
            return this.f1541b;
        }

        public void setEntityName(String str) {
            this.f1540a = str;
        }

        public void setTrackPoint(TrackPoint trackPoint) {
            this.f1541b = trackPoint;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("InternalError{");
            stringBuffer.append("entityName='");
            stringBuffer.append(this.f1540a);
            stringBuffer.append('\'');
            stringBuffer.append(", trackPoint=");
            stringBuffer.append(this.f1541b);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    public class ParamError extends InternalError {

        /* JADX INFO: renamed from: c */
        private String f1542c;

        public ParamError(FailInfo failInfo) {
            super(failInfo);
        }

        public ParamError(FailInfo failInfo, String str, TrackPoint trackPoint, String str2) {
            super(failInfo, str, trackPoint);
            this.f1542c = str2;
        }

        public String getError() {
            return this.f1542c;
        }

        public void setError(String str) {
            this.f1542c = str;
        }

        @Override // com.baidu.trace.api.track.FailInfo.InternalError
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("ParamError{");
            stringBuffer.append("entityName='");
            stringBuffer.append(this.f1540a);
            stringBuffer.append('\'');
            stringBuffer.append(", trackPoint=");
            stringBuffer.append(this.f1541b);
            stringBuffer.append(", error='");
            stringBuffer.append(this.f1542c);
            stringBuffer.append('\'');
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    public FailInfo() {
    }

    public FailInfo(List<ParamError> list, List<InternalError> list2) {
        this.f1538a = list;
        this.f1539b = list2;
    }

    public List<InternalError> getInternalErrors() {
        return this.f1539b;
    }

    public List<ParamError> getParamErrors() {
        return this.f1538a;
    }

    public void setInternalErrors(List<InternalError> list) {
        this.f1539b = list;
    }

    public void setParamErrors(List<ParamError> list) {
        this.f1538a = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("FailInfo{");
        stringBuffer.append("paramErrors=");
        stringBuffer.append(this.f1538a);
        stringBuffer.append(", internalErrors=");
        stringBuffer.append(this.f1539b);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
