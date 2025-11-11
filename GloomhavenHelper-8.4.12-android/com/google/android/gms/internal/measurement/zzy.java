package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzy implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        long v1 = 0L;
        long v2 = 0L;
        String s = null;
        String s1 = null;
        String s2 = null;
        Bundle bundle0 = null;
        boolean z = false;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(v3 & 0xFFFF) {
                case 1: {
                    v1 = SafeParcelReader.readLong(parcel0, v3);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readLong(parcel0, v3);
                    break;
                }
                case 3: {
                    z = SafeParcelReader.readBoolean(parcel0, v3);
                    break;
                }
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 5: {
                    s1 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 6: {
                    s2 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 7: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v3);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzv(v1, v2, z, s, s1, s2, bundle0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzv[v];
    }
}

