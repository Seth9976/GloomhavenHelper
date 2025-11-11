package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.common.util.VisibleForTesting;

@KeepForSdk
@Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    @KeepForSdk
    public static final Status RESULT_CANCELED;
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT;
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR;
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED;
    @KeepForSdk
    @VisibleForTesting
    public static final Status RESULT_SUCCESS;
    @KeepForSdk
    public static final Status RESULT_TIMEOUT;
    private static final Status zzar;
    @VersionField(id = 1000)
    private final int zzg;
    @Field(getter = "getStatusCode", id = 1)
    private final int zzh;
    @Nullable
    @Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent zzi;
    @Nullable
    @Field(getter = "getStatusMessage", id = 2)
    private final String zzj;

    static {
        Status.RESULT_SUCCESS = new Status(0);
        Status.RESULT_INTERRUPTED = new Status(14);
        Status.RESULT_INTERNAL_ERROR = new Status(8);
        Status.RESULT_TIMEOUT = new Status(15);
        Status.RESULT_CANCELED = new Status(16);
        Status.zzar = new Status(17);
        Status.RESULT_DEAD_CLIENT = new Status(18);
        Status.CREATOR = new zzb();
    }

    @KeepForSdk
    public Status(int v) {
        this(v, null);
    }

    @KeepForSdk
    @Constructor
    Status(@Param(id = 1000) int v, @Param(id = 1) int v1, @Nullable @Param(id = 2) String s, @Nullable @Param(id = 3) PendingIntent pendingIntent0) {
        this.zzg = v;
        this.zzh = v1;
        this.zzj = s;
        this.zzi = pendingIntent0;
    }

    @KeepForSdk
    public Status(int v, @Nullable String s) {
        this(1, v, s, null);
    }

    @KeepForSdk
    public Status(int v, @Nullable String s, @Nullable PendingIntent pendingIntent0) {
        this(1, v, s, pendingIntent0);
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof Status ? this.zzg == ((Status)object0).zzg && this.zzh == ((Status)object0).zzh && Objects.equal(this.zzj, ((Status)object0).zzj) && Objects.equal(this.zzi, ((Status)object0).zzi) : false;
    }

    public final PendingIntent getResolution() {
        return this.zzi;
    }

    @Override  // com.google.android.gms.common.api.Result
    @KeepForSdk
    public final Status getStatus() {
        return this;
    }

    public final int getStatusCode() {
        return this.zzh;
    }

    @Nullable
    public final String getStatusMessage() {
        return this.zzj;
    }

    @VisibleForTesting
    public final boolean hasResolution() {
        return this.zzi != null;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzg, this.zzh, this.zzj, this.zzi});
    }

    public final boolean isCanceled() {
        return this.zzh == 16;
    }

    public final boolean isInterrupted() {
        return this.zzh == 14;
    }

    public final boolean isSuccess() {
        return this.zzh <= 0;
    }

    public final void startResolutionForResult(Activity activity0, int v) throws IntentSender.SendIntentException {
        if(!this.hasResolution()) {
            return;
        }
        activity0.startIntentSenderForResult(this.zzi.getIntentSender(), v, null, 0, 0, 0);
    }

    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", this.zzg()).add("resolution", this.zzi).toString();
    }

    @Override  // android.os.Parcelable
    @KeepForSdk
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getStatusCode());
        SafeParcelWriter.writeString(parcel0, 2, this.getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zzi, v, false);
        SafeParcelWriter.writeInt(parcel0, 1000, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final String zzg() {
        return this.zzj == null ? CommonStatusCodes.getStatusCodeString(this.zzh) : this.zzj;
    }
}

