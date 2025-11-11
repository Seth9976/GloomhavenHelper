package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "AdLauncherIntentInfoCreator")
@Reserved({1})
public final class zzb extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 9)
    public final Intent intent;
    @Field(id = 4)
    public final String mimeType;
    @Field(id = 5)
    public final String packageName;
    @Field(id = 3)
    public final String url;
    @Field(id = 2)
    private final String zzdhp;
    @Field(id = 6)
    public final String zzdhq;
    @Field(id = 7)
    public final String zzdhr;
    @Field(id = 8)
    private final String zzdhs;

    static {
        zzb.CREATOR = new zzc();
    }

    public zzb(Intent intent0) {
        this(null, null, null, null, null, null, null, intent0);
    }

    public zzb(String s, String s1, String s2, String s3, String s4, String s5, String s6) {
        this(s, s1, s2, s3, s4, s5, s6, null);
    }

    @Constructor
    public zzb(@Param(id = 2) String s, @Param(id = 3) String s1, @Param(id = 4) String s2, @Param(id = 5) String s3, @Param(id = 6) String s4, @Param(id = 7) String s5, @Param(id = 8) String s6, @Param(id = 9) Intent intent0) {
        this.zzdhp = s;
        this.url = s1;
        this.mimeType = s2;
        this.packageName = s3;
        this.zzdhq = s4;
        this.zzdhr = s5;
        this.zzdhs = s6;
        this.intent = intent0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zzdhp, false);
        SafeParcelWriter.writeString(parcel0, 3, this.url, false);
        SafeParcelWriter.writeString(parcel0, 4, this.mimeType, false);
        SafeParcelWriter.writeString(parcel0, 5, this.packageName, false);
        SafeParcelWriter.writeString(parcel0, 6, this.zzdhq, false);
        SafeParcelWriter.writeString(parcel0, 7, this.zzdhr, false);
        SafeParcelWriter.writeString(parcel0, 8, this.zzdhs, false);
        SafeParcelWriter.writeParcelable(parcel0, 9, this.intent, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

