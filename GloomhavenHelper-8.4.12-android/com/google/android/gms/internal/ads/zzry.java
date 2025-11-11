package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.io.InputStream;
import javax.annotation.concurrent.GuardedBy;

@Class(creator = "CacheEntryParcelCreator")
@Reserved({1})
public final class zzry extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Nullable
    @Field(getter = "getContentFileDescriptor", id = 2)
    @GuardedBy("this")
    private ParcelFileDescriptor zzbrr;

    static {
        zzry.CREATOR = new zzrx();
    }

    public zzry() {
        this(null);
    }

    @Constructor
    public zzry(@Nullable @Param(id = 2) ParcelFileDescriptor parcelFileDescriptor0) {
        this.zzbrr = parcelFileDescriptor0;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzmw(), v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final boolean zzmu() {
        synchronized(this) {
        }
        return this.zzbrr != null;
    }

    @Nullable
    public final InputStream zzmv() {
        synchronized(this) {
            if(this.zzbrr == null) {
                return null;
            }
            InputStream inputStream0 = new ParcelFileDescriptor.AutoCloseInputStream(this.zzbrr);
            this.zzbrr = null;
            return inputStream0;
        }
    }

    private final ParcelFileDescriptor zzmw() {
        synchronized(this) {
        }
        return this.zzbrr;
    }
}

