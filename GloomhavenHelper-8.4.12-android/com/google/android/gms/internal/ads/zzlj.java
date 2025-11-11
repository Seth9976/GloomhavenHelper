package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzlj extends zzlo {
    public static final Parcelable.Creator CREATOR;
    public final String description;
    public final String text;
    private final String zzage;

    static {
        zzlj.CREATOR = new zzlm();
    }

    zzlj(Parcel parcel0) {
        super("COMM");
        this.zzage = parcel0.readString();
        this.description = parcel0.readString();
        this.text = parcel0.readString();
    }

    public zzlj(String s, String s1, String s2) {
        super("COMM");
        this.zzage = s;
        this.description = s1;
        this.text = s2;
    }

    // 去混淆评级： 低(40)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && zzop.zza(this.description, ((zzlj)object0).description) && zzop.zza(this.zzage, ((zzlj)object0).zzage) && zzop.zza(this.text, ((zzlj)object0).text);
    }

    @Override
    public final int hashCode() {
        int v = 0;
        int v1 = this.zzage == null ? 0 : this.zzage.hashCode();
        int v2 = this.description == null ? 0 : this.description.hashCode();
        String s = this.text;
        if(s != null) {
            v = s.hashCode();
        }
        return ((v1 + 0x20F) * 0x1F + v2) * 0x1F + v;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zzafi);
        parcel0.writeString(this.zzage);
        parcel0.writeString(this.text);
    }
}

