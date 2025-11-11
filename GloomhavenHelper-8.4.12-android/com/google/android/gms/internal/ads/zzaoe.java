package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.mediation.VersionInfo;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "RtbVersionInfoParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaoe extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    private final int major;
    @Field(id = 2)
    private final int minor;
    @Field(id = 3)
    private final int zzdfx;

    static {
        zzaoe.CREATOR = new zzaoh();
    }

    @Constructor
    zzaoe(@Param(id = 1) int v, @Param(id = 2) int v1, @Param(id = 3) int v2) {
        this.major = v;
        this.minor = v1;
        this.zzdfx = v2;
    }

    @Override
    public final String toString() {
        return this.major + "." + this.minor + "." + this.zzdfx;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.major);
        SafeParcelWriter.writeInt(parcel0, 2, this.minor);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzdfx);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static zzaoe zza(VersionInfo versionInfo0) {
        return new zzaoe(versionInfo0.getMajorVersion(), versionInfo0.getMinorVersion(), versionInfo0.getMicroVersion());
    }
}

