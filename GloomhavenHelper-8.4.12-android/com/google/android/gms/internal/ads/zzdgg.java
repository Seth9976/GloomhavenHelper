package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "PoolConfigurationCreator")
@ParametersAreNonnullByDefault
public final class zzdgg extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final zzdgf[] zzgsn;
    private final int[] zzgso;
    private final int[] zzgsp;
    @Field(getter = "getFormatInt", id = 1)
    private final int zzgsq;
    public final zzdgf zzgsr;
    @Field(id = 2)
    public final int zzgss;
    @Field(id = 3)
    public final int zzgst;
    @Field(id = 4)
    public final int zzgsu;
    @Field(id = 5)
    public final String zzgsv;
    @Field(getter = "getPoolDiscardStrategyInt", id = 6)
    private final int zzgsw;
    public final int zzgsx;
    @Field(getter = "getPrecacheStartTriggerInt", id = 7)
    private final int zzgsy;
    private final int zzgsz;
    @Nullable
    public final Context zzur;

    static {
        zzdgg.CREATOR = new zzdgh();
    }

    @Constructor
    public zzdgg(@Param(id = 1) int v, @Param(id = 2) int v1, @Param(id = 3) int v2, @Param(id = 4) int v3, @Param(id = 5) String s, @Param(id = 6) int v4, @Param(id = 7) int v5) {
        this.zzgsn = zzdgf.values();
        this.zzgso = zzdgi.zzary();
        this.zzgsp = zzdgi.zzarz();
        this.zzur = null;
        this.zzgsq = v;
        this.zzgsr = this.zzgsn[v];
        this.zzgss = v1;
        this.zzgst = v2;
        this.zzgsu = v3;
        this.zzgsv = s;
        this.zzgsw = v4;
        this.zzgsx = this.zzgso[v4];
        this.zzgsy = v5;
        this.zzgsz = this.zzgsp[v5];
    }

    private zzdgg(@Nullable Context context0, zzdgf zzdgf0, int v, int v1, int v2, String s, String s1, String s2) {
        int v3;
        this.zzgsn = zzdgf.values();
        this.zzgso = zzdgi.zzary();
        this.zzgsp = zzdgi.zzarz();
        this.zzur = context0;
        this.zzgsq = zzdgf0.ordinal();
        this.zzgsr = zzdgf0;
        this.zzgss = v;
        this.zzgst = v1;
        this.zzgsu = v2;
        this.zzgsv = s;
        if("oldest".equals(s1)) {
            v3 = zzdgi.zzgta;
        }
        else {
            v3 = "lru".equals(s1) || !"lfu".equals(s1) ? zzdgi.zzgtb : zzdgi.zzgtc;
        }
        this.zzgsx = v3;
        this.zzgsw = this.zzgsx - 1;
        "onAdClosed".equals(s2);
        this.zzgsz = zzdgi.zzgte;
        this.zzgsy = this.zzgsz - 1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zzgsq);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzgss);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzgst);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzgsu);
        SafeParcelWriter.writeString(parcel0, 5, this.zzgsv, false);
        SafeParcelWriter.writeInt(parcel0, 6, this.zzgsw);
        SafeParcelWriter.writeInt(parcel0, 7, this.zzgsy);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static zzdgg zza(zzdgf zzdgf0, Context context0) {
        if(zzdgf0 == zzdgf.zzgsj) {
            return new zzdgg(context0, zzdgf0, ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrl)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrr)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrt)))), ((String)zzvh.zzpd().zzd(zzzx.zzcrv)), ((String)zzvh.zzpd().zzd(zzzx.zzcrn)), ((String)zzvh.zzpd().zzd(zzzx.zzcrp)));
        }
        if(zzdgf0 == zzdgf.zzgsk) {
            return new zzdgg(context0, zzdgf0, ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrm)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrs)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcru)))), ((String)zzvh.zzpd().zzd(zzzx.zzcrw)), ((String)zzvh.zzpd().zzd(zzzx.zzcro)), ((String)zzvh.zzpd().zzd(zzzx.zzcrq)));
        }
        return zzdgf0 == zzdgf.zzgsl ? new zzdgg(context0, zzdgf0, ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcrz)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcsb)))), ((int)(((Integer)zzvh.zzpd().zzd(zzzx.zzcsc)))), ((String)zzvh.zzpd().zzd(zzzx.zzcrx)), ((String)zzvh.zzpd().zzd(zzzx.zzcry)), ((String)zzvh.zzpd().zzd(zzzx.zzcsa))) : null;
    }

    public static boolean zzarx() {
        return ((Boolean)zzvh.zzpd().zzd(zzzx.zzcrk)).booleanValue();
    }
}

