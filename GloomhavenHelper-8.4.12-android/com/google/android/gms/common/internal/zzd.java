package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzd implements Parcelable.Creator {
    @Override  // android.os.Parcelable$Creator
    public final Object createFromParcel(Parcel parcel0) {
        int v = SafeParcelReader.validateObjectHeader(parcel0);
        String s = null;
        IBinder iBinder0 = null;
        Scope[] arr_scope = null;
        Bundle bundle0 = null;
        Account account0 = null;
        Feature[] arr_feature = null;
        Feature[] arr_feature1 = null;
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        boolean z = false;
        while(parcel0.dataPosition() < v) {
            int v4 = SafeParcelReader.readHeader(parcel0);
            switch(v4 & 0xFFFF) {
                case 1: {
                    v1 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 2: {
                    v2 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 3: {
                    v3 = SafeParcelReader.readInt(parcel0, v4);
                    break;
                }
                case 4: {
                    s = SafeParcelReader.createString(parcel0, v4);
                    break;
                }
                case 5: {
                    iBinder0 = SafeParcelReader.readIBinder(parcel0, v4);
                    break;
                }
                case 6: {
                    arr_scope = (Scope[])SafeParcelReader.createTypedArray(parcel0, v4, Scope.CREATOR);
                    break;
                }
                case 7: {
                    bundle0 = SafeParcelReader.createBundle(parcel0, v4);
                    break;
                }
                case 8: {
                    account0 = (Account)SafeParcelReader.createParcelable(parcel0, v4, Account.CREATOR);
                    break;
                }
                case 10: {
                    arr_feature = (Feature[])SafeParcelReader.createTypedArray(parcel0, v4, Feature.CREATOR);
                    break;
                }
                case 11: {
                    arr_feature1 = (Feature[])SafeParcelReader.createTypedArray(parcel0, v4, Feature.CREATOR);
                    break;
                }
                case 12: {
                    z = SafeParcelReader.readBoolean(parcel0, v4);
                    break;
                }
                default: {
                    SafeParcelReader.skipUnknownField(parcel0, v4);
                }
            }
        }
        SafeParcelReader.ensureAtEnd(parcel0, v);
        return new GetServiceRequest(v1, v2, v3, s, iBinder0, arr_scope, bundle0, account0, arr_feature, arr_feature1, z);
    }

    @Override  // android.os.Parcelable$Creator
    public final Object[] newArray(int v) {
        return new GetServiceRequest[v];
    }
}

