package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzagm implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        byte[] arr_b = null;
        String[] arr_s = null;
        String[] arr_s1 = null;
        long v1 = 0L;
        boolean z = false;
        int v2 = 0;
        boolean z1 = false;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(v3 & 0xFFFF) {
                case 1: {
                    z = SafeParcelReader.readBoolean(parcel0, v3);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 3: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 4: {
                    arr_b = SafeParcelReader.createByteArray(parcel0, v3);
                    break;
                }
                case 5: {
                    arr_s = SafeParcelReader.createStringArray(parcel0, v3);
                    break;
                }
                case 6: {
                    arr_s1 = SafeParcelReader.createStringArray(parcel0, v3);
                    break;
                }
                case 7: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v3);
                    break;
                }
                case 8: {
                    v1 = SafeParcelReader.readLong(parcel0, v3);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzagn(z, s, v2, arr_b, arr_s, arr_s1, z1, v1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzagn[v];
    }
}

