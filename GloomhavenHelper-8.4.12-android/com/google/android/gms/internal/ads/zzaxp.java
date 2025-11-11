package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@Class(creator = "ExceptionParcelCreator")
public final class zzaxp extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final int errorCode;
    @Field(id = 1)
    public final String zzdux;

    static {
        zzaxp.CREATOR = new zzaxr();
    }

    @Constructor
    zzaxp(@Nullable @Param(id = 1) String s, @Param(id = 2) int v) {
        if(s == null) {
            s = "";
        }
        this.zzdux = s;
        this.errorCode = v;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.zzdux, false);
        SafeParcelWriter.writeInt(parcel0, 2, this.errorCode);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    public static zzaxp zza(Throwable throwable0, int v) {
        return new zzaxp(throwable0.getMessage(), v);
    }
}

