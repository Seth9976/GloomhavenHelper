package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzun implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        zzuk[] arr_zzuk = null;
        int v1 = 0;
        int v2 = 0;
        boolean z = false;
        int v3 = 0;
        int v4 = 0;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        while(parcel0.dataPosition() < v) {
            int v5 = SafeParcelReader.readHeader(parcel0);
            switch(v5 & 0xFFFF) {
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v5);
                    break;
                }
                case 3: {
                    v1 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 4: {
                    v2 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 5: {
                    z = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 6: {
                    v3 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 7: {
                    v4 = SafeParcelReader.readInt(parcel0, v5);
                    break;
                }
                case 8: {
                    arr_zzuk = (zzuk[])SafeParcelReader.createTypedArray(parcel0, v5, zzuk.CREATOR);
                    break;
                }
                case 9: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 10: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 11: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 12: {
                    z4 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 13: {
                    z5 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 14: {
                    z6 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                case 15: {
                    z7 = SafeParcelReader.readBoolean(parcel0, v5);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v5);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzuk(s, v1, v2, z, v3, v4, arr_zzuk, z1, z2, z3, z4, z5, z6, z7);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzuk[v];
    }
}

