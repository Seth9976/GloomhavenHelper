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

@Class(creator = "GassResponseParcelCreator")
public final class zzdjx extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(getter = "getAfmaSignalsAsBytes", id = 2, type = "byte[]")
    private zza zzgxv;
    private byte[] zzgxw;

    static {
        zzdjx.CREATOR = new zzdjw();
    }

    @Constructor
    zzdjx(@Param(id = 1) int v, @Param(id = 2) byte[] arr_b) {
        this.versionCode = v;
        this.zzgxv = null;
        this.zzgxw = arr_b;
        this.zzatm();
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeByteArray(parcel0, 2, (this.zzgxw == null ? this.zzgxv.toByteArray() : this.zzgxw), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final zza zzatl() {
        if(this.zzgxv == null) {
            try {
                this.zzgxv = zza.zza(this.zzgxw, zzdym.zzbch());
                this.zzgxw = null;
            }
            catch(zzdzh zzdzh0) {
                throw new IllegalStateException(zzdzh0);
            }
        }
        this.zzatm();
        return this.zzgxv;
    }

    private final void zzatm() {
        if(this.zzgxv == null && this.zzgxw != null) {
            return;
        }
        if(this.zzgxv != null && this.zzgxw == null) {
            return;
        }
        if(this.zzgxv == null || this.zzgxw == null) {
            throw this.zzgxv != null || this.zzgxw != null ? new IllegalStateException("Impossible") : new IllegalStateException("Invalid internal representation - empty");
        }
        throw new IllegalStateException("Invalid internal representation - full");
    }
}

