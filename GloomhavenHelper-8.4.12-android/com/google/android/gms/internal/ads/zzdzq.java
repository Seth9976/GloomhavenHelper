package com.google.android.gms.internal.ads;

public class zzdzq {
    private static final zzdym zzhnv;
    private zzdxn zzhun;
    private volatile zzeah zzhuo;
    private volatile zzdxn zzhup;

    static {
        zzdzq.zzhnv = zzdym.zzbcg();
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zzdzq)) {
            return false;
        }
        zzeah zzeah0 = this.zzhuo;
        zzeah zzeah1 = ((zzdzq)object0).zzhuo;
        if(zzeah0 == null && zzeah1 == null) {
            return this.zzbai().equals(((zzdzq)object0).zzbai());
        }
        if(zzeah0 != null && zzeah1 != null) {
            return zzeah0.equals(zzeah1);
        }
        return zzeah0 == null ? this.zzm(zzeah1.zzbcy()).equals(zzeah1) : zzeah0.equals(((zzdzq)object0).zzm(zzeah0.zzbcy()));
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public final zzdxn zzbai() {
        if(this.zzhup != null) {
            return this.zzhup;
        }
        synchronized(this) {
            if(this.zzhup != null) {
                return this.zzhup;
            }
            this.zzhup = this.zzhuo == null ? zzdxn.zzhoe : this.zzhuo.zzbai();
            return this.zzhup;
        }
    }

    public final int zzbda() {
        if(this.zzhup != null) {
            return this.zzhup.size();
        }
        return this.zzhuo == null ? 0 : this.zzhuo.zzbda();
    }

    private final zzeah zzm(zzeah zzeah0) {
        if(this.zzhuo == null) {
            synchronized(this) {
                if(this.zzhuo != null) {
                    return this.zzhuo;
                }
                this.zzhuo = zzeah0;
                this.zzhup = zzdxn.zzhoe;
            }
        }
        return this.zzhuo;
    }

    public final zzeah zzn(zzeah zzeah0) {
        zzeah zzeah1 = this.zzhuo;
        this.zzhun = null;
        this.zzhup = null;
        this.zzhuo = zzeah0;
        return zzeah1;
    }
}

