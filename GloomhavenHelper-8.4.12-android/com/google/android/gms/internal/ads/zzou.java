package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Arrays;

public final class zzou implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    private int zzagg;
    public final int zzaqf;
    public final int zzaqg;
    public final int zzaqh;
    public final byte[] zzbhi;

    static {
        zzou.CREATOR = new zzot();
    }

    public zzou(int v, int v1, int v2, byte[] arr_b) {
        this.zzaqf = v;
        this.zzaqh = v1;
        this.zzaqg = v2;
        this.zzbhi = arr_b;
    }

    zzou(Parcel parcel0) {
        this.zzaqf = parcel0.readInt();
        this.zzaqh = parcel0.readInt();
        this.zzaqg = parcel0.readInt();
        this.zzbhi = parcel0.readInt() == 0 ? parcel0.createByteArray() : null;
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzaqf == ((zzou)object0).zzaqf && this.zzaqh == ((zzou)object0).zzaqh && this.zzaqg == ((zzou)object0).zzaqg && Arrays.equals(this.zzbhi, ((zzou)object0).zzbhi);
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            int v = Arrays.hashCode(this.zzbhi);
            this.zzagg = (((this.zzaqf + 0x20F) * 0x1F + this.zzaqh) * 0x1F + this.zzaqg) * 0x1F + v;
        }
        return this.zzagg;
    }

    @Override
    public final String toString() {
        return this.zzbhi == null ? "ColorInfo(" + this.zzaqf + ", " + this.zzaqh + ", " + this.zzaqg + ", " + false + ")" : "ColorInfo(" + this.zzaqf + ", " + this.zzaqh + ", " + this.zzaqg + ", " + true + ")";
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeInt(this.zzaqf);
        parcel0.writeInt(this.zzaqh);
        parcel0.writeInt(this.zzaqg);
        parcel0.writeInt((this.zzbhi == null ? 0 : 1));
        byte[] arr_b = this.zzbhi;
        if(arr_b != null) {
            parcel0.writeByteArray(arr_b);
        }
    }
}

