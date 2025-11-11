package com.google.android.gms.ads.internal.overlay;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzg;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.internal.ads.zzazo;

public final class zzn implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        zzb zzb0 = null;
        IBinder iBinder0 = null;
        IBinder iBinder1 = null;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        String s = null;
        String s1 = null;
        IBinder iBinder4 = null;
        String s2 = null;
        zzazo zzazo0 = null;
        String s3 = null;
        zzg zzg0 = null;
        IBinder iBinder5 = null;
        boolean z = false;
        int v1 = 0;
        int v2 = 0;
        while(parcel0.dataPosition() < v) {
            int v3 = SafeParcelReader.readHeader(parcel0);
            switch(v3 & 0xFFFF) {
                case 2: {
                    zzb0 = (zzb)SafeParcelReader.createParcelable(parcel0, v3, zzb.CREATOR);
                    break;
                }
                case 3: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                case 4: {
                    iBinder1 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                case 5: {
                    iBinder2 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                case 6: {
                    iBinder3 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                case 7: {
                    s = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 8: {
                    z = SafeParcelReader.readBoolean(parcel0, v3);
                    break;
                }
                case 9: {
                    s1 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 10: {
                    iBinder4 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                case 11: {
                    v1 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 12: {
                    v2 = SafeParcelReader.readInt(parcel0, v3);
                    break;
                }
                case 13: {
                    s2 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 14: {
                    zzazo0 = (zzazo)SafeParcelReader.createParcelable(parcel0, v3, zzazo.CREATOR);
                    break;
                }
                case 16: {
                    s3 = SafeParcelReader.createString(parcel0, v3);
                    break;
                }
                case 17: {
                    zzg0 = (zzg)SafeParcelReader.createParcelable(parcel0, v3, zzg.CREATOR);
                    break;
                }
                case 18: {
                    iBinder5 = SafeParcelReader.readIBinder(parcel0, v3);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v3);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new AdOverlayInfoParcel(zzb0, iBinder0, iBinder1, iBinder2, iBinder3, s, z, s1, iBinder4, v1, v2, s2, zzazo0, s3, zzg0, iBinder5);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new AdOverlayInfoParcel[v];
    }
}

