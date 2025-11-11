package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.annotation.KeepName;

@KeepForSdk
@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    private IBinder zzcz;

    static {
        BinderWrapper.CREATOR = new zza();
    }

    public BinderWrapper() {
        this.zzcz = null;
    }

    @KeepForSdk
    public BinderWrapper(IBinder iBinder0) {
        this.zzcz = iBinder0;
    }

    private BinderWrapper(Parcel parcel0) {
        this.zzcz = parcel0.readStrongBinder();
    }

    BinderWrapper(Parcel parcel0, zza zza0) {
        this(parcel0);
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeStrongBinder(this.zzcz);
    }
}

