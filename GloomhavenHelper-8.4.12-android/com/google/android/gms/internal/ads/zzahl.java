package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "InstreamAdConfigurationParcelCreator")
@ParametersAreNonnullByDefault
public final class zzahl extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @VersionField(id = 1000)
    public final int versionCode;
    @Field(id = 3)
    public final int zzbkf;
    @Field(id = 1)
    public final int zzczl;
    @Field(id = 2)
    public final String zzczm;

    static {
        zzahl.CREATOR = new zzahk();
    }

    @Constructor
    public zzahl(@Param(id = 1000) int v, @Param(id = 1) int v1, @Param(id = 2) String s, @Param(id = 3) int v2) {
        this.versionCode = v;
        this.zzczl = v1;
        this.zzczm = s;
        this.zzbkf = v2;
    }

    public zzahl(zzahx zzahx0) {
        this(2, 1, zzahx0.zzsc(), zzahx0.getMediaAspectRatio());
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zzczl);
        SafeParcelWriter.writeString(parcel0, 2, this.zzczm, false);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzbkf);
        SafeParcelWriter.writeInt(parcel0, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

