package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzatz implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        String s1 = null;
        List list0 = null;
        List list1 = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        while(parcel0.dataPosition() < v) {
            int v1 = SafeParcelReader.readHeader(parcel0);
            switch(v1 & 0xFFFF) {
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 3: {
                    s1 = SafeParcelReader.createString(parcel0, v1);
                    break;
                }
                case 4: {
                    z = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 5: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 6: {
                    list0 = SafeParcelReader.createStringList(parcel0, v1);
                    break;
                }
                case 7: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 8: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v1);
                    break;
                }
                case 9: {
                    list1 = SafeParcelReader.createStringList(parcel0, v1);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v1);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzaua(s, s1, z, z1, list0, z2, z3, list1);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzaua[v];
    }
}

