package com.baidu.trace.api.fence;

/* JADX INFO: loaded from: classes.dex */
public class DistrictFence extends Fence {

    /* JADX INFO: renamed from: f */
    private String f1447f;

    /* JADX INFO: renamed from: g */
    private String f1448g;

    private DistrictFence(long j, String str, String str2, int i, FenceType fenceType, String str3) {
        super(j, str, str2, i, fenceType);
        this.f1447f = str3;
    }

    public static DistrictFence buildServerFence(long j, String str, String str2, int i, String str3) {
        return new DistrictFence(j, str, str2, i, FenceType.server, str3);
    }

    public String getDistrict() {
        return this.f1448g;
    }

    public String getKeyword() {
        return this.f1447f;
    }

    public void setDistrict(String str) {
        this.f1448g = str;
    }

    public void setKeyword(String str) {
        this.f1447f = str;
    }

    @Override // com.baidu.trace.api.fence.Fence
    public String toString() {
        return "DistrictFence [fenceId=" + this.f1449a + ", fenceName=" + this.f1450b + ", fenceType=" + this.f1453e + ", monitoredPerson=" + this.f1451c + ", keyword=" + this.f1447f + ", district=" + this.f1448g + ", denoise=" + this.f1452d + "]";
    }
}
