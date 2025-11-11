package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzj implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        float f = 0.0f;
        int v1 = 0;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        while(parcel0.dataPosition() < v) {
            int v2 = SafeParcelReader.readHeader(parcel0);
            switch(v2 & 0xFFFF) {
                case 2: {
                    z = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 3: {
                    z1 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v2);
                    break;
                }
                case 5: {
                    z2 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 6: {
                    f = SafeParcelReader.readFloat(parcel0, v2);
                    break;
                }
                case 7: {
                    v1 = SafeParcelReader.readInt(parcel0, v2);
                    break;
                }
                case 8: {
                    z3 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 9: {
                    z4 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                case 10: {
                    z5 = SafeParcelReader.readBoolean(parcel0, v2);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v2);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new zzg(z, z1, s, z2, f, v1, z3, z4, z5);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new zzg[v];
    }
}

