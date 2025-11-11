package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzln extends zzlo {
    public static final Parcelable.Creator CREATOR;
    private final String description;
    private final String value;

    static {
        zzln.CREATOR = new zzlq();
    }

    zzln(Parcel parcel0) {
        super(parcel0.readString());
        this.description = parcel0.readString();
        this.value = parcel0.readString();
    }

    public zzln(String s, String s1, String s2) {
        super(s);
        this.description = null;
        this.value = s2;
    }

    // 去混淆评级： 低(40)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzafi.equals(((zzln)object0).zzafi) && zzop.zza(this.description, ((zzln)object0).description) && zzop.zza(this.value, ((zzln)object0).value);
    }

    @Override
    public final int hashCode() {
        int v = this.zzafi.hashCode();
        int v1 = 0;
        int v2 = this.description == null ? 0 : this.description.hashCode();
        String s = this.value;
        if(s != null) {
            v1 = s.hashCode();
        }
        return ((v + 0x20F) * 0x1F + v2) * 0x1F + v1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zzafi);
        parcel0.writeString(this.description);
        parcel0.writeString(this.value);
    }
}

