package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.List;

public final class zzaqj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        long v1 = 0L;
        long v2 = 0L;
        long v3 = 0L;
        long v4 = 0L;
        String s = null;
        String s1 = null;
        List list0 = null;
        List list1 = null;
        List list2 = null;
        String s2 = null;
        String s3 = null;
        String s4 = null;
        String s5 = null;
        zzaqw zzaqw0 = null;
        String s6 = null;
        String s7 = null;
        zzasq zzasq0 = null;
        List list3 = null;
        List list4 = null;
        zzaqm zzaqm0 = null;
        String s8 = null;
        List list5 = null;
        String s9 = null;
        zzaua zzaua0 = null;
        String s10 = null;
        Bundle bundle0 = null;
        List list6 = null;
        String s11 = null;
        String s12 = null;
        int v5 = 0;
        int v6 = 0;
        boolean z = false;
        int v7 = 0;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        boolean z12 = false;
        boolean z13 = false;
        boolean z14 = false;
        int v8 = 0;
        boolean z15 = false;
        boolean z16 = false;
        boolean z17 = false;
        boolean z18 = false;
        while(parcel0.dataPosition() < v) {
            int v9 = SafeParcelReader.readHeader(parcel0);
            switch(v9 & 0xFFFF) {
                case 1: {
                    v5 = SafeParcelReader.readInt(parcel0, v9);
                    break;
                }
                case 2: {
                    s = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 3: {
                    s1 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 4: {
                    list0 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 5: {
                    v6 = SafeParcelReader.readInt(parcel0, v9);
                    break;
                }
                case 6: {
                    list1 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 7: {
                    v1 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 8: {
                    z = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 9: {
                    v2 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 10: {
                    list2 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 11: {
                    v3 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 12: {
                    v7 = SafeParcelReader.readInt(parcel0, v9);
                    break;
                }
                case 13: {
                    s2 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 14: {
                    v4 = SafeParcelReader.readLong(parcel0, v9);
                    break;
                }
                case 15: {
                    s3 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 18: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 19: {
                    s4 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 21: {
                    s5 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 22: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 23: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 24: {
                    z4 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 25: {
                    z5 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 26: {
                    z6 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 28: {
                    zzaqw0 = (zzaqw)SafeParcelReader.createParcelable(parcel0, v9, zzaqw.CREATOR);
                    break;
                }
                case 29: {
                    s6 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 30: {
                    s7 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 0x1F: {
                    z7 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 0x20: {
                    z8 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 33: {
                    zzasq0 = (zzasq)SafeParcelReader.createParcelable(parcel0, v9, zzasq.CREATOR);
                    break;
                }
                case 34: {
                    list3 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 35: {
                    list4 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 36: {
                    z9 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 37: {
                    zzaqm0 = (zzaqm)SafeParcelReader.createParcelable(parcel0, v9, zzaqm.CREATOR);
                    break;
                }
                case 38: {
                    z10 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 39: {
                    s8 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 40: {
                    list5 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 42: {
                    z11 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 43: {
                    s9 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 44: {
                    zzaua0 = (zzaua)SafeParcelReader.createParcelable(parcel0, v9, zzaua.CREATOR);
                    break;
                }
                case 45: {
                    s10 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 46: {
                    z12 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 0x2F: {
                    z13 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 0x30: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v9);
                    break;
                }
                case 49: {
                    z14 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 50: {
                    v8 = SafeParcelReader.readInt(parcel0, v9);
                    break;
                }
                case 51: {
                    z15 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 52: {
                    list6 = SafeParcelReader.createStringList(parcel0, v9);
                    break;
                }
                case 53: {
                    z16 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 54: {
                    s11 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 55: {
                    s12 = SafeParcelReader.createString(parcel0, v9);
                    break;
                }
                case 56: {
                    z17 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                case 57: {
                    z18 = SafeParcelReader.readBoolean(parcel0, v9);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v9);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzaqk(v5, s, s1, list0, v6, list1, v1, z, v2, list2, v3, v7, s2, v4, s3, z1, s4, s5, z2, z3, z4, z5, z6, zzaqw0, s6, s7, z7, z8, zzasq0, list3, list4, z9, zzaqm0, z10, s8, list5, z11, s9, zzaua0, s10, z12, z13, bundle0, z14, v8, z15, list6, z16, s11, s12, z17, z18);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzaqk[v];
    }
}

