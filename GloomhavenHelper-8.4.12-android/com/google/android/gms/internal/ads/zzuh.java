package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;
import java.util.List;

@Class(creator = "AdRequestParcelCreator")
public final class zzuh extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 3)
    public final Bundle extras;
    @Field(id = 1)
    public final int versionCode;
    @Field(id = 7)
    public final int zzabv;
    @Field(id = 20)
    public final int zzabw;
    @Nullable
    @Field(id = 21)
    public final String zzabx;
    @Field(id = 8)
    public final boolean zzbkp;
    @Field(id = 2)
    @Deprecated
    public final long zzccm;
    @Field(id = 4)
    @Deprecated
    public final int zzccn;
    @Field(id = 5)
    public final List zzcco;
    @Field(id = 6)
    public final boolean zzccp;
    @Field(id = 9)
    public final String zzccq;
    @Field(id = 10)
    public final zzyy zzccr;
    @Field(id = 12)
    public final String zzccs;
    @Field(id = 13)
    public final Bundle zzcct;
    @Field(id = 14)
    public final Bundle zzccu;
    @Field(id = 15)
    public final List zzccv;
    @Field(id = 16)
    public final String zzccw;
    @Field(id = 17)
    public final String zzccx;
    @Field(id = 18)
    @Deprecated
    public final boolean zzccy;
    @Field(id = 22)
    public final List zzccz;
    @Nullable
    @Field(id = 19)
    public final zzub zzcda;
    @Field(id = 11)
    public final Location zzmk;

    static {
        zzuh.CREATOR = new zzuj();
    }

    @Constructor
    public zzuh(@Param(id = 1) int v, @Param(id = 2) long v1, @Param(id = 3) Bundle bundle0, @Param(id = 4) int v2, @Param(id = 5) List list0, @Param(id = 6) boolean z, @Param(id = 7) int v3, @Param(id = 8) boolean z1, @Param(id = 9) String s, @Param(id = 10) zzyy zzyy0, @Param(id = 11) Location location0, @Param(id = 12) String s1, @Param(id = 13) Bundle bundle1, @Param(id = 14) Bundle bundle2, @Param(id = 15) List list1, @Param(id = 16) String s2, @Param(id = 17) String s3, @Param(id = 18) boolean z2, @Param(id = 19) zzub zzub0, @Param(id = 20) int v4, @Nullable @Param(id = 21) String s4, @Param(id = 22) List list2) {
        this.versionCode = v;
        this.zzccm = v1;
        this.extras = bundle0 == null ? new Bundle() : bundle0;
        this.zzccn = v2;
        this.zzcco = list0;
        this.zzccp = z;
        this.zzabv = v3;
        this.zzbkp = z1;
        this.zzccq = s;
        this.zzccr = zzyy0;
        this.zzmk = location0;
        this.zzccs = s1;
        this.zzcct = bundle1 == null ? new Bundle() : bundle1;
        this.zzccu = bundle2;
        this.zzccv = list1;
        this.zzccw = s2;
        this.zzccx = s3;
        this.zzccy = z2;
        this.zzcda = zzub0;
        this.zzabw = v4;
        this.zzabx = s4;
        List list3 = list2 == null ? new ArrayList() : list2;
        this.zzccz = list3;
    }

    // 去混淆评级： 低(35)
    @Override
    public final boolean equals(Object object0) {
        if(!(object0 instanceof zzuh)) {
            return false;
        }
        zzuh zzuh0 = (zzuh)object0;
        return this.versionCode == zzuh0.versionCode && this.zzccm == zzuh0.zzccm && Objects.equal(this.extras, zzuh0.extras) && this.zzccn == zzuh0.zzccn && Objects.equal(this.zzcco, zzuh0.zzcco) && this.zzccp == zzuh0.zzccp && this.zzabv == zzuh0.zzabv && this.zzbkp == zzuh0.zzbkp && Objects.equal(this.zzccq, zzuh0.zzccq) && Objects.equal(this.zzccr, zzuh0.zzccr) && Objects.equal(this.zzmk, zzuh0.zzmk) && Objects.equal(this.zzccs, zzuh0.zzccs) && Objects.equal(this.zzcct, zzuh0.zzcct) && Objects.equal(this.zzccu, zzuh0.zzccu) && Objects.equal(this.zzccv, zzuh0.zzccv) && Objects.equal(this.zzccw, zzuh0.zzccw) && Objects.equal(this.zzccx, zzuh0.zzccx) && this.zzccy == zzuh0.zzccy && this.zzabw == zzuh0.zzabw && Objects.equal(this.zzabx, zzuh0.zzabx) && Objects.equal(this.zzccz, zzuh0.zzccz);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.versionCode, this.zzccm, this.extras, this.zzccn, this.zzcco, Boolean.valueOf(this.zzccp), this.zzabv, Boolean.valueOf(this.zzbkp), this.zzccq, this.zzccr, this.zzmk, this.zzccs, this.zzcct, this.zzccu, this.zzccv, this.zzccw, this.zzccx, Boolean.valueOf(this.zzccy), this.zzabw, this.zzabx, this.zzccz});
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel0, 2, this.zzccm);
        SafeParcelWriter.writeBundle(parcel0, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel0, 4, this.zzccn);
        SafeParcelWriter.writeStringList(parcel0, 5, this.zzcco, false);
        SafeParcelWriter.writeBoolean(parcel0, 6, this.zzccp);
        SafeParcelWriter.writeInt(parcel0, 7, this.zzabv);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzbkp);
        SafeParcelWriter.writeString(parcel0, 9, this.zzccq, false);
        SafeParcelWriter.writeParcelable(parcel0, 10, this.zzccr, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 11, this.zzmk, v, false);
        SafeParcelWriter.writeString(parcel0, 12, this.zzccs, false);
        SafeParcelWriter.writeBundle(parcel0, 13, this.zzcct, false);
        SafeParcelWriter.writeBundle(parcel0, 14, this.zzccu, false);
        SafeParcelWriter.writeStringList(parcel0, 15, this.zzccv, false);
        SafeParcelWriter.writeString(parcel0, 16, this.zzccw, false);
        SafeParcelWriter.writeString(parcel0, 17, this.zzccx, false);
        SafeParcelWriter.writeBoolean(parcel0, 18, this.zzccy);
        SafeParcelWriter.writeParcelable(parcel0, 19, this.zzcda, v, false);
        SafeParcelWriter.writeInt(parcel0, 20, this.zzabw);
        SafeParcelWriter.writeString(parcel0, 21, this.zzabx, false);
        SafeParcelWriter.writeStringList(parcel0, 22, this.zzccz, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

