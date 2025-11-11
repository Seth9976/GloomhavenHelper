package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzdgh implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        while(parcel0.dataPosition() < v) {
            int v7 = SafeParcelReader.readHeader(parcel0);
            switch(v7 & 0xFFFF) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                case 3: {
                    v3 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                case 4: {
                    v4 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                case 5: {
                    s = SafeParcelReader.createString(parcel0, v7);
                    break;
                }
                case 6: {
                    v5 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                case 7: {
                    v6 = SafeParcelReader.readInt(parcel0, v7);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v7);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzdgg(v1, v2, v3, v4, s, v5, v6);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzdgg[v];
    }
}

