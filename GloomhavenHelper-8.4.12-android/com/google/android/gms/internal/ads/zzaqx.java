package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "NonagonRequestParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaqx extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 3)
    public final ApplicationInfo applicationInfo;
    @Field(id = 4)
    public final String packageName;
    @Field(id = 2)
    public final zzazo zzdjo;
    @Nullable
    @Field(id = 6)
    public final PackageInfo zzdju;
    @Field(id = 5)
    public final List zzdke;
    @Field(id = 7)
    public final String zzdko;
    @Field(id = 1)
    public final Bundle zzdmz;
    @Field(id = 8)
    public final boolean zzdna;
    @Field(id = 9)
    public final String zzdnb;
    @Nullable
    @Field(id = 10)
    public zzdgg zzdnc;
    @Nullable
    @Field(id = 11)
    public String zzdnd;

    static {
        zzaqx.CREATOR = new zzara();
    }

    @Constructor
    public zzaqx(@Param(id = 1) Bundle bundle0, @Param(id = 2) zzazo zzazo0, @Param(id = 3) ApplicationInfo applicationInfo0, @Param(id = 4) String s, @Param(id = 5) List list0, @Nullable @Param(id = 6) PackageInfo packageInfo0, @Param(id = 7) String s1, @Param(id = 8) boolean z, @Param(id = 9) String s2, @Param(id = 10) zzdgg zzdgg0, @Param(id = 11) String s3) {
        this.zzdmz = bundle0;
        this.zzdjo = zzazo0;
        this.packageName = s;
        this.applicationInfo = applicationInfo0;
        this.zzdke = list0;
        this.zzdju = packageInfo0;
        this.zzdko = s1;
        this.zzdna = z;
        this.zzdnb = s2;
        this.zzdnc = zzdgg0;
        this.zzdnd = s3;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBundle(parcel0, 1, this.zzdmz, false);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzdjo, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.applicationInfo, v, false);
        SafeParcelWriter.writeString(parcel0, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel0, 5, this.zzdke, false);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.zzdju, v, false);
        SafeParcelWriter.writeString(parcel0, 7, this.zzdko, false);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzdna);
        SafeParcelWriter.writeString(parcel0, 9, this.zzdnb, false);
        SafeParcelWriter.writeParcelable(parcel0, 10, this.zzdnc, v, false);
        SafeParcelWriter.writeString(parcel0, 11, this.zzdnd, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

