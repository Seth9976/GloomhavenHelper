package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.IOUtils;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Class(creator = "LargeParcelTeleporterCreator")
@Reserved({1})
public final class zzaqw extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    private ParcelFileDescriptor zzdmw;
    private Parcelable zzdmx;
    private boolean zzdmy;

    static {
        zzaqw.CREATOR = new zzaqy();
    }

    @Constructor
    public zzaqw(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor0) {
        this.zzdmw = parcelFileDescriptor0;
        this.zzdmx = null;
        this.zzdmy = true;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        this.zzud();
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzdmw, v, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    // 检测为 Lambda 实现
    static final void zza(OutputStream outputStream0, byte[] arr_b) [...]

    public final SafeParcelable zza(Parcelable.Creator parcelable$Creator0) {
        byte[] arr_b;
        if(this.zzdmy) {
            ParcelFileDescriptor parcelFileDescriptor0 = this.zzdmw;
            if(parcelFileDescriptor0 == null) {
                zzawf.zzey("File descriptor is empty, returning null.");
                return null;
            }
            DataInputStream dataInputStream0 = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor0));
            try {
                arr_b = new byte[dataInputStream0.readInt()];
                dataInputStream0.readFully(arr_b, 0, arr_b.length);
            }
            catch(IOException iOException0) {
                zzawf.zzc("Could not read from parcel file descriptor", iOException0);
                return null;
            }
            finally {
                IOUtils.closeQuietly(dataInputStream0);
            }
            Parcel parcel0 = Parcel.obtain();
            try {
                parcel0.unmarshall(arr_b, 0, arr_b.length);
                parcel0.setDataPosition(0);
                this.zzdmx = (Parcelable)parcelable$Creator0.createFromParcel(parcel0);
            }
            finally {
                parcel0.recycle();
            }
            this.zzdmy = false;
            return (SafeParcelable)this.zzdmx;
        }
        return (SafeParcelable)this.zzdmx;
    }

    private static ParcelFileDescriptor zzh(byte[] arr_b) {
        ParcelFileDescriptor.AutoCloseOutputStream parcelFileDescriptor$AutoCloseOutputStream0;
        try {
            ParcelFileDescriptor[] arr_parcelFileDescriptor = ParcelFileDescriptor.createPipe();
            parcelFileDescriptor$AutoCloseOutputStream0 = null;
            parcelFileDescriptor$AutoCloseOutputStream0 = new ParcelFileDescriptor.AutoCloseOutputStream(arr_parcelFileDescriptor[1]);
            zzaqv zzaqv0 = () -> {
                DataOutputStream dataOutputStream0;
                try {
                    try {
                        dataOutputStream0 = null;
                        dataOutputStream0 = new DataOutputStream(parcelFileDescriptor$AutoCloseOutputStream0);
                        dataOutputStream0.writeInt(arr_b.length);
                        dataOutputStream0.write(arr_b);
                        goto label_19;
                    }
                    catch(IOException iOException0) {
                    }
                    zzawf.zzc("Error transporting the ad response", iOException0);
                    zzq.zzkz().zza(iOException0, "LargeParcelTeleporter.pipeData.1");
                    if(dataOutputStream0 == null) {
                        goto label_9;
                    }
                    goto label_11;
                }
                catch(Throwable throwable0) {
                    goto label_14;
                }
            label_9:
                IOUtils.closeQuietly(parcelFileDescriptor$AutoCloseOutputStream0);
                return;
            label_11:
                IOUtils.closeQuietly(dataOutputStream0);
                return;
            label_14:
                if(dataOutputStream0 == null) {
                    IOUtils.closeQuietly(parcelFileDescriptor$AutoCloseOutputStream0);
                }
                else {
                    IOUtils.closeQuietly(dataOutputStream0);
                }
                throw throwable0;
            label_19:
                IOUtils.closeQuietly(dataOutputStream0);
            };
            zzazq.zzdxk.execute(zzaqv0);
            return arr_parcelFileDescriptor[0];
        }
        catch(IOException iOException0) {
            zzawf.zzc("Error transporting the ad response", iOException0);
            zzq.zzkz().zza(iOException0, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly(parcelFileDescriptor$AutoCloseOutputStream0);
            return null;
        }
    }

    private final ParcelFileDescriptor zzud() {
        byte[] arr_b;
        if(this.zzdmw == null) {
            Parcel parcel0 = Parcel.obtain();
            try {
                this.zzdmx.writeToParcel(parcel0, 0);
                arr_b = parcel0.marshall();
            }
            finally {
                parcel0.recycle();
            }
            this.zzdmw = zzaqw.zzh(arr_b);
            return this.zzdmw;
        }
        return this.zzdmw;
    }
}

