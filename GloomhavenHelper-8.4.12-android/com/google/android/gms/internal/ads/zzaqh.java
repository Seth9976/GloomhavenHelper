package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.List;

public final class zzaqh implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        long v1 = 0L;
        long v2 = 0L;
        Bundle bundle0 = null;
        zzuh zzuh0 = null;
        zzuk zzuk0 = null;
        String s = null;
        ApplicationInfo applicationInfo0 = null;
        PackageInfo packageInfo0 = null;
        String s1 = null;
        String s2 = null;
        String s3 = null;
        zzazo zzazo0 = null;
        Bundle bundle1 = null;
        List list0 = null;
        Bundle bundle2 = null;
        String s4 = null;
        String s5 = null;
        List list1 = null;
        String s6 = null;
        zzach zzach0 = null;
        List list2 = null;
        String s7 = null;
        String s8 = null;
        String s9 = null;
        Bundle bundle3 = null;
        String s10 = null;
        zzxp zzxp0 = null;
        Bundle bundle4 = null;
        String s11 = null;
        String s12 = null;
        String s13 = null;
        List list3 = null;
        String s14 = null;
        List list4 = null;
        ArrayList arrayList0 = null;
        String s15 = null;
        zzahl zzahl0 = null;
        String s16 = null;
        Bundle bundle5 = null;
        int v3 = 0;
        int v4 = 0;
        boolean z = false;
        int v5 = 0;
        int v6 = 0;
        float f = 0.0f;
        float f1 = 0.0f;
        boolean z1 = false;
        int v7 = 0;
        int v8 = 0;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        int v9 = 0;
        boolean z5 = false;
        boolean z6 = false;
        int v10 = 0;
        boolean z7 = false;
        boolean z8 = false;
        boolean z9 = false;
        while(parcel0.dataPosition() < v) {
            int v11 = SafeParcelReader.readHeader(parcel0);
            switch(v11 & 0xFFFF) {
                case 1: {
                    v3 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 2: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                case 3: {
                    zzuh0 = (zzuh)SafeParcelReader.createParcelable(parcel0, v11, zzuh.CREATOR);
                    break;
                }
                case 4: {
                    zzuk0 = (zzuk)SafeParcelReader.createParcelable(parcel0, v11, zzuk.CREATOR);
                    break;
                }
                case 5: {
                    s = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 6: {
                    applicationInfo0 = (ApplicationInfo)SafeParcelReader.createParcelable(parcel0, v11, ApplicationInfo.CREATOR);
                    break;
                }
                case 7: {
                    packageInfo0 = (PackageInfo)SafeParcelReader.createParcelable(parcel0, v11, PackageInfo.CREATOR);
                    break;
                }
                case 8: {
                    s1 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 9: {
                    s2 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 10: {
                    s3 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 11: {
                    zzazo0 = (zzazo)SafeParcelReader.createParcelable(parcel0, v11, zzazo.CREATOR);
                    break;
                }
                case 12: {
                    bundle1 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                case 13: {
                    v4 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 14: {
                    list0 = SafeParcelReader.createStringList(parcel0, v11);
                    break;
                }
                case 15: {
                    bundle2 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                case 16: {
                    z = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 18: {
                    v5 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 19: {
                    v6 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 20: {
                    f = SafeParcelReader.readFloat(parcel0, v11);
                    break;
                }
                case 21: {
                    s4 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 25: {
                    v1 = SafeParcelReader.readLong(parcel0, v11);
                    break;
                }
                case 26: {
                    s5 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 27: {
                    list1 = SafeParcelReader.createStringList(parcel0, v11);
                    break;
                }
                case 28: {
                    s6 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 29: {
                    zzach0 = (zzach)SafeParcelReader.createParcelable(parcel0, v11, zzach.CREATOR);
                    break;
                }
                case 30: {
                    list2 = SafeParcelReader.createStringList(parcel0, v11);
                    break;
                }
                case 0x1F: {
                    v2 = SafeParcelReader.readLong(parcel0, v11);
                    break;
                }
                case 33: {
                    s7 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 34: {
                    f1 = SafeParcelReader.readFloat(parcel0, v11);
                    break;
                }
                case 35: {
                    v7 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 36: {
                    v8 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 37: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 38: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 39: {
                    s8 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 40: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 41: {
                    s9 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 42: {
                    z4 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 43: {
                    v9 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 44: {
                    bundle3 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                case 45: {
                    s10 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 46: {
                    zzxp0 = (zzxp)SafeParcelReader.createParcelable(parcel0, v11, zzxp.CREATOR);
                    break;
                }
                case 0x2F: {
                    z5 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 0x30: {
                    bundle4 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                case 49: {
                    s11 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 50: {
                    s12 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 51: {
                    s13 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 52: {
                    z6 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 53: {
                    list3 = SafeParcelReader.createIntegerList(parcel0, v11);
                    break;
                }
                case 54: {
                    s14 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 55: {
                    list4 = SafeParcelReader.createStringList(parcel0, v11);
                    break;
                }
                case 56: {
                    v10 = SafeParcelReader.readInt(parcel0, v11);
                    break;
                }
                case 57: {
                    z7 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 58: {
                    z8 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 59: {
                    z9 = SafeParcelReader.readBoolean(parcel0, v11);
                    break;
                }
                case 60: {
                    arrayList0 = SafeParcelReader.createStringList(parcel0, v11);
                    break;
                }
                case 61: {
                    s15 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 0x3F: {
                    zzahl0 = (zzahl)SafeParcelReader.createParcelable(parcel0, v11, zzahl.CREATOR);
                    break;
                }
                case 0x40: {
                    s16 = SafeParcelReader.createString(parcel0, v11);
                    break;
                }
                case 65: {
                    bundle5 = SafeParcelReader.createBundle(parcel0, v11);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v11);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzaqi(v3, bundle0, zzuh0, zzuk0, s, applicationInfo0, packageInfo0, s1, s2, s3, zzazo0, bundle1, v4, list0, bundle2, z, v5, v6, f, s4, v1, s5, list1, s6, zzach0, list2, v2, s7, f1, z1, v7, v8, z2, z3, s8, s9, z4, v9, bundle3, s10, zzxp0, z5, bundle4, s11, s12, s13, z6, list3, s14, list4, v10, z7, z8, z9, arrayList0, s15, zzahl0, s16, bundle5);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzaqi[v];
    }
}

