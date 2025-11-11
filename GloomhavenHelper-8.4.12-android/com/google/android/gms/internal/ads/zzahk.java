package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzahk implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        int v1 = 0;
        String s = null;
        int v2 = 0;
        int v3 = 0;
        while(parcel0.dataPosition() < v) {
            int v4 = SafeParcelReader.readHeader(parcel0);
            int v5 = v4 & 0xFFFF;
            if(v5 == 1000) {
                v1 = SafeParcelReader.readInt(parcel0, v4);
            }
            else {
                switch(v5) {
                    case 1: {
                        v2 = SafeParcelReader.readInt(parcel0, v4);
                        break;
                    }
                    case 2: {
                        s = SafeParcelReader.createString(parcel0, v4);
                        break;
                    }
                    case 3: {
                        v3 = SafeParcelReader.readInt(parcel0, v4);
                        break;
                    }
                    default: {
                        SafeParcelReader.skipUnknownField(parcel0, v4);
                    }
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzahl(v1, v2, s, v3);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzahl[v];
    }
}

