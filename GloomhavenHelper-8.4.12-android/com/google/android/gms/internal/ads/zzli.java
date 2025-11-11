package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzli extends zzlo {
    public static final Parcelable.Creator CREATOR;
    private final String description;
    private final String mimeType;
    private final int zzazy;
    private final byte[] zzazz;

    static {
        zzli.CREATOR = new zzlk();
    }

    zzli(Parcel parcel0) {
        super("APIC");
        this.mimeType = parcel0.readString();
        this.description = parcel0.readString();
        this.zzazy = parcel0.readInt();
        this.zzazz = parcel0.createByteArray();
    }

    public zzli(String s, String s1, int v, byte[] arr_b) {
        super("APIC");
        this.mimeType = s;
        this.description = null;
        this.zzazy = 3;
        this.zzazz = arr_b;
    }

    // 去混淆评级： 低(40)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && this.zzazy == ((zzli)object0).zzazy && zzop.zza(this.mimeType, ((zzli)object0).mimeType) && zzop.zza(this.description, ((zzli)object0).description) && Arrays.equals(this.zzazz, ((zzli)object0).zzazz);
    }

    @Override
    public final int hashCode() {
        int v = (this.zzazy + 0x20F) * 0x1F;
        int v1 = 0;
        int v2 = this.mimeType == null ? 0 : this.mimeType.hashCode();
        String s = this.description;
        if(s != null) {
            v1 = s.hashCode();
        }
        return ((v + v2) * 0x1F + v1) * 0x1F + Arrays.hashCode(this.zzazz);
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.mimeType);
        parcel0.writeString(this.description);
        parcel0.writeInt(this.zzazy);
        parcel0.writeByteArray(this.zzazz);
    }
}

