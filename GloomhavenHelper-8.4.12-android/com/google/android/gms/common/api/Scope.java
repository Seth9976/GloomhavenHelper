package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@KeepForSdk
@Class(creator = "ScopeCreator")
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(getter = "getScopeUri", id = 2)
    private final String zzaq;
    @VersionField(id = 1)
    private final int zzg;

    static {
        Scope.CREATOR = new zza();
    }

    @Constructor
    Scope(@Param(id = 1) int v, @Param(id = 2) String s) {
        Preconditions.checkNotEmpty(s, "scopeUri must not be null or empty");
        this.zzg = v;
        this.zzaq = s;
    }

    public Scope(String s) {
        this(1, s);
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof Scope ? this.zzaq.equals(((Scope)object0).zzaq) : false;
    }

    @KeepForSdk
    public final String getScopeUri() {
        return this.zzaq;
    }

    @Override
    public final int hashCode() {
        return this.zzaq.hashCode();
    }

    @Override
    public final String toString() {
        return this.zzaq;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zzg);
        SafeParcelWriter.writeString(parcel0, 2, this.getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

