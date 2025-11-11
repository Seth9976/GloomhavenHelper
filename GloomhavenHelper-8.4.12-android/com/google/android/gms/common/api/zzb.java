package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzb implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        int v1 = 0;
        String s = null;
        PendingIntent pendingIntent0 = null;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            int v4 = v3 & 0xFFFF;
            if(v4 == 1000) {
                v1 = SafeParcelReader.readInt(parcel0, v3);
            }
            else {
                switch(v4) {
                    case 1: {
                        v2 = SafeParcelReader.readInt(parcel0, v3);
                        break;
                    }
                    case 2: {
                        s = SafeParcelReader.createString(parcel0, v3);
                        break;
                    }
                    case 3: {
                        pendingIntent0 = (PendingIntent)SafeParcelReader.createParcelable(parcel0, v3, PendingIntent.CREATOR);
                        break;
                    }
                    default: {
                        SafeParcelReader.skipUnknownField(parcel0, v3);
                    }
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new Status(v1, v2, s, pendingIntent0);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new Status[v];
    }
}

