package com.baidu.trace.api.track;

import com.baidu.trace.model.BaseRequest;
import com.baidu.trace.model.CoordType;
import com.baidu.trace.model.ProcessOption;
import com.baidu.trace.model.SortType;

/* JADX INFO: loaded from: classes.dex */
public final class HistoryTrackRequest extends BaseRequest {

    /* JADX INFO: renamed from: a */
    private String f1543a;

    /* JADX INFO: renamed from: b */
    private long f1544b;

    /* JADX INFO: renamed from: c */
    private long f1545c;

    /* JADX INFO: renamed from: d */
    private boolean f1546d;

    /* JADX INFO: renamed from: e */
    private ProcessOption f1547e;

    /* JADX INFO: renamed from: f */
    private SupplementMode f1548f;

    /* JADX INFO: renamed from: g */
    private SortType f1549g;

    /* JADX INFO: renamed from: h */
    private CoordType f1550h;

    /* JADX INFO: renamed from: i */
    private int f1551i;

    /* JADX INFO: renamed from: j */
    private int f1552j;

    public HistoryTrackRequest() {
        this.f1546d = false;
        this.f1548f = SupplementMode.no_supplement;
        this.f1549g = SortType.asc;
        this.f1550h = CoordType.bd09ll;
    }

    public HistoryTrackRequest(int i, long j) {
        super(i, j);
        this.f1546d = false;
        this.f1548f = SupplementMode.no_supplement;
        this.f1549g = SortType.asc;
        this.f1550h = CoordType.bd09ll;
    }

    public HistoryTrackRequest(int i, long j, String str) {
        this(i, j);
        this.f1543a = str;
    }

    public HistoryTrackRequest(int i, long j, String str, long j2, long j3, boolean z, ProcessOption processOption, SupplementMode supplementMode, SortType sortType, CoordType coordType, int i2, int i3) {
        this(i, j, str);
        this.f1544b = j2;
        this.f1545c = j3;
        this.f1546d = z;
        this.f1547e = processOption;
        this.f1548f = supplementMode;
        this.f1549g = sortType;
        this.f1550h = coordType;
        this.f1551i = i2;
        this.f1552j = i3;
    }

    public final CoordType getCoordTypeOutput() {
        return this.f1550h;
    }

    public final long getEndTime() {
        return this.f1545c;
    }

    public final String getEntityName() {
        return this.f1543a;
    }

    public final int getPageIndex() {
        return this.f1551i;
    }

    public final int getPageSize() {
        return this.f1552j;
    }

    public final ProcessOption getProcessOption() {
        return this.f1547e;
    }

    public final SortType getSortType() {
        return this.f1549g;
    }

    public final long getStartTime() {
        return this.f1544b;
    }

    public final SupplementMode getSupplementMode() {
        return this.f1548f;
    }

    public final boolean isProcessed() {
        return this.f1546d;
    }

    public final void setCoordTypeOutput(CoordType coordType) {
        this.f1550h = coordType;
    }

    public final void setEndTime(long j) {
        this.f1545c = j;
    }

    public final void setEntityName(String str) {
        this.f1543a = str;
    }

    public final void setPageIndex(int i) {
        this.f1551i = i;
    }

    public final void setPageSize(int i) {
        this.f1552j = i;
    }

    public final void setProcessOption(ProcessOption processOption) {
        this.f1547e = processOption;
    }

    public final void setProcessed(boolean z) {
        this.f1546d = z;
    }

    public final void setSortType(SortType sortType) {
        this.f1549g = sortType;
    }

    public final void setStartTime(long j) {
        this.f1544b = j;
    }

    public final void setSupplementMode(SupplementMode supplementMode) {
        this.f1548f = supplementMode;
    }

    public final String toString() {
        return "HistoryTrackRequest [tag=" + this.tag + ", serviceId=" + this.serviceId + ", entityName=" + this.f1543a + ", startTime=" + this.f1544b + ", endTime=" + this.f1545c + ", isProcessed=" + this.f1546d + ", processOption=" + this.f1547e + ", supplementMode=" + this.f1548f + ", sortType=" + this.f1549g + ", coordTypeOutput=" + this.f1550h + ", pageIndex=" + this.f1551i + ", pageSize=" + this.f1552j + "]";
    }
}
