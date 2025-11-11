package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@KeepForSdk
@Class(creator = "GetServiceRequestCreator")
@Reserved({9})
public class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @VersionField(id = 1)
    private final int version;
    @Field(id = 2)
    private final int zzdg;
    @Field(id = 3)
    private int zzdh;
    @Field(id = 5)
    IBinder zzdi;
    @Field(id = 6)
    Scope[] zzdj;
    @Field(id = 7)
    Bundle zzdk;
    @Field(id = 8)
    Account zzdl;
    @Field(id = 10)
    Feature[] zzdm;
    @Field(id = 11)
    Feature[] zzdn;
    @Field(id = 12)
    private boolean zzdo;
    @Field(id = 4)
    String zzy;

    static {
        GetServiceRequest.CREATOR = new zzd();
    }

    public GetServiceRequest(int v) {
        this.version = 4;
        this.zzdh = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzdg = v;
        this.zzdo = true;
    }

    @Constructor
    GetServiceRequest(@Param(id = 1) int v, @Param(id = 2) int v1, @Param(id = 3) int v2, @Param(id = 4) String s, @Param(id = 5) IBinder iBinder0, @Param(id = 6) Scope[] arr_scope, @Param(id = 7) Bundle bundle0, @Param(id = 8) Account account0, @Param(id = 10) Feature[] arr_feature, @Param(id = 11) Feature[] arr_feature1, @Param(id = 12) boolean z) {
        this.version = v;
        this.zzdg = v1;
        this.zzdh = v2;
        this.zzy = "com.google.android.gms".equals(s) ? "com.google.android.gms" : s;
        Account account1 = null;
        if(v < 2) {
            if(iBinder0 != null) {
                account1 = AccountAccessor.getAccountBinderSafe(Stub.asInterface(iBinder0));
            }
            this.zzdl = account1;
        }
        else {
            this.zzdi = iBinder0;
            this.zzdl = account0;
        }
        this.zzdj = arr_scope;
        this.zzdk = bundle0;
        this.zzdm = arr_feature;
        this.zzdn = arr_feature1;
        this.zzdo = z;
    }

    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.zzdk;
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.version);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzdg);
        SafeParcelWriter.writeInt(parcel0, 3, this.zzdh);
        SafeParcelWriter.writeString(parcel0, 4, this.zzy, false);
        SafeParcelWriter.writeIBinder(parcel0, 5, this.zzdi, false);
        SafeParcelWriter.writeTypedArray(parcel0, 6, this.zzdj, v, false);
        SafeParcelWriter.writeBundle(parcel0, 7, this.zzdk, false);
        SafeParcelWriter.writeParcelable(parcel0, 8, this.zzdl, v, false);
        SafeParcelWriter.writeTypedArray(parcel0, 10, this.zzdm, v, false);
        SafeParcelWriter.writeTypedArray(parcel0, 11, this.zzdn, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 12, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

