package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzuj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        Bundle bundle0 = null;
        List list0 = null;
        String s = null;
        zzyy zzyy0 = null;
        Location location0 = null;
        String s1 = null;
        Bundle bundle1 = null;
        Bundle bundle2 = null;
        List list1 = null;
        String s2 = null;
        String s3 = null;
        zzub zzub0 = null;
        String s4 = null;
        List list2 = null;
        long v1 = 0L;
        int v2 = 0;
        int v3 = 0;
        boolean z = false;
        int v4 = 0;
        boolean z1 = false;
        boolean z2 = false;
        int v5 = 0;
        while(parcel0.dataPosition() < v) {
            int v6 = SafeParcelReader.readHeader(parcel0);
            switch(v6 & 0xFFFF) {
                case 1: {
                    v2 = SafeParcelReader.readInt(parcel0, v6);
                    break;
                }
                case 2: {
                    v1 = SafeParcelReader.readLong(parcel0, v6);
                    break;
                }
                case 3: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v6);
                    break;
                }
                case 4: {
                    v3 = SafeParcelReader.readInt(parcel0, v6);
                    break;
                }
                case 5: {
                    list0 = SafeParcelReader.createStringList(parcel0, v6);
                    break;
                }
                case 6: {
                    z = SafeParcelReader.readBoolean(parcel0, v6);
                    break;
                }
                case 7: {
                    v4 = SafeParcelReader.readInt(parcel0, v6);
                    break;
                }
                case 8: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v6);
                    break;
                }
                case 9: {
                    s = SafeParcelReader.createString(parcel0, v6);
                    break;
                }
                case 10: {
                    zzyy0 = (zzyy)SafeParcelReader.createParcelable(parcel0, v6, zzyy.CREATOR);
                    break;
                }
                case 11: {
                    location0 = (Location)SafeParcelReader.createParcelable(parcel0, v6, Location.CREATOR);
                    break;
                }
                case 12: {
                    s1 = SafeParcelReader.createString(parcel0, v6);
                    break;
                }
                case 13: {
                    bundle1 = SafeParcelReader.createBundle(parcel0, v6);
                    break;
                }
                case 14: {
                    bundle2 = SafeParcelReader.createBundle(parcel0, v6);
                    break;
                }
                case 15: {
                    list1 = SafeParcelReader.createStringList(parcel0, v6);
                    break;
                }
                case 16: {
                    s2 = SafeParcelReader.createString(parcel0, v6);
                    break;
                }
                case 17: {
                    s3 = SafeParcelReader.createString(parcel0, v6);
                    break;
                }
                case 18: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v6);
                    break;
                }
                case 19: {
                    zzub0 = (zzub)SafeParcelReader.createParcelable(parcel0, v6, zzub.CREATOR);
                    break;
                }
                case 20: {
                    v5 = SafeParcelReader.readInt(parcel0, v6);
                    break;
                }
                case 21: {
                    s4 = SafeParcelReader.createString(parcel0, v6);
                    break;
                }
                case 22: {
                    list2 = SafeParcelReader.createStringList(parcel0, v6);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v6);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzuh(v2, v1, bundle0, v3, list0, z, v4, z1, s, zzyy0, location0, s1, bundle1, bundle2, list1, s2, s3, z2, zzub0, v5, s4, list2);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzuh[v];
    }
}

