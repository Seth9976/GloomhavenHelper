package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import java.util.List;

@Class(creator = "WakeLockEventCreator")
public final class WakeLockEvent extends StatsEvent {
    public static final Parcelable.Creator CREATOR;
    private long durationMillis;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getTimeMillis", id = 2)
    private final long zzfo;
    @Field(getter = "getEventType", id = 11)
    private int zzfp;
    @Field(getter = "getWakeLockName", id = 4)
    private final String zzfq;
    @Field(getter = "getSecondaryWakeLockName", id = 10)
    private final String zzfr;
    @Field(getter = "getCodePackage", id = 17)
    private final String zzfs;
    @Field(getter = "getWakeLockType", id = 5)
    private final int zzft;
    @Field(getter = "getCallingPackages", id = 6)
    private final List zzfu;
    @Field(getter = "getEventKey", id = 12)
    private final String zzfv;
    @Field(getter = "getElapsedRealtime", id = 8)
    private final long zzfw;
    @Field(getter = "getDeviceState", id = 14)
    private int zzfx;
    @Field(getter = "getHostPackage", id = 13)
    private final String zzfy;
    @Field(getter = "getBeginPowerPercentage", id = 15)
    private final float zzfz;
    @Field(getter = "getTimeout", id = 16)
    private final long zzga;
    @Field(getter = "getAcquiredWithTimeout", id = 18)
    private final boolean zzgb;

    static {
        WakeLockEvent.CREATOR = new zza();
    }

    @Constructor
    WakeLockEvent(@Param(id = 1) int v, @Param(id = 2) long v1, @Param(id = 11) int v2, @Param(id = 4) String s, @Param(id = 5) int v3, @Param(id = 6) List list0, @Param(id = 12) String s1, @Param(id = 8) long v4, @Param(id = 14) int v5, @Param(id = 10) String s2, @Param(id = 13) String s3, @Param(id = 15) float f, @Param(id = 16) long v6, @Param(id = 17) String s4, @Param(id = 18) boolean z) {
        this.versionCode = v;
        this.zzfo = v1;
        this.zzfp = v2;
        this.zzfq = s;
        this.zzfr = s2;
        this.zzfs = s4;
        this.zzft = v3;
        this.durationMillis = -1L;
        this.zzfu = list0;
        this.zzfv = s1;
        this.zzfw = v4;
        this.zzfx = v5;
        this.zzfy = s3;
        this.zzfz = f;
        this.zzga = v6;
        this.zzgb = z;
    }

    public WakeLockEvent(long v, int v1, String s, int v2, List list0, String s1, long v3, int v4, String s2, String s3, float f, long v5, String s4, boolean z) {
        this(2, v, v1, s, v2, list0, s1, v3, v4, s2, s3, f, v5, s4, z);
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final int getEventType() {
        return this.zzfp;
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final long getTimeMillis() {
        return this.zzfo;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel0, 2, this.getTimeMillis());
        SafeParcelWriter.writeString(parcel0, 4, this.zzfq, false);
        SafeParcelWriter.writeInt(parcel0, 5, this.zzft);
        SafeParcelWriter.writeStringList(parcel0, 6, this.zzfu, false);
        SafeParcelWriter.writeLong(parcel0, 8, this.zzfw);
        SafeParcelWriter.writeString(parcel0, 10, this.zzfr, false);
        SafeParcelWriter.writeInt(parcel0, 11, this.getEventType());
        SafeParcelWriter.writeString(parcel0, 12, this.zzfv, false);
        SafeParcelWriter.writeString(parcel0, 13, this.zzfy, false);
        SafeParcelWriter.writeInt(parcel0, 14, this.zzfx);
        SafeParcelWriter.writeFloat(parcel0, 15, this.zzfz);
        SafeParcelWriter.writeLong(parcel0, 16, this.zzga);
        SafeParcelWriter.writeString(parcel0, 17, this.zzfs, false);
        SafeParcelWriter.writeBoolean(parcel0, 18, this.zzgb);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final long zzu() {
        return this.durationMillis;
    }

    // 去混淆评级： 低(20)
    @Override  // com.google.android.gms.common.stats.StatsEvent
    public final String zzv() {
        String s = this.zzfu == null ? "" : TextUtils.join(",", this.zzfu);
        return "\t" + this.zzfq + "\t" + this.zzft + "\t" + s + "\t" + this.zzfx + "\t" + (this.zzfr == null ? "" : this.zzfr) + "\t" + (this.zzfy == null ? "" : this.zzfy) + "\t" + this.zzfz + "\t" + (this.zzfs == null ? "" : this.zzfs) + "\t" + this.zzgb;
    }
}

