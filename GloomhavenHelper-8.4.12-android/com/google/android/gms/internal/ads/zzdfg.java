package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;

final class zzdfg implements Runnable {
    private final InputStream zzgrc;
    private final ParcelFileDescriptor zzgrd;

    zzdfg(InputStream inputStream0, ParcelFileDescriptor parcelFileDescriptor0) {
        this.zzgrc = inputStream0;
        this.zzgrd = parcelFileDescriptor0;
    }

    @Override
    public final void run() {
        InputStream inputStream0;
        try {
            inputStream0 = this.zzgrc;
            ParcelFileDescriptor.AutoCloseOutputStream parcelFileDescriptor$AutoCloseOutputStream0 = new ParcelFileDescriptor.AutoCloseOutputStream(this.zzgrd);
            try {
                IOUtils.copyStream(inputStream0, parcelFileDescriptor$AutoCloseOutputStream0);
            }
            catch(Throwable throwable1) {
                try {
                    parcelFileDescriptor$AutoCloseOutputStream0.close();
                }
                catch(Throwable throwable2) {
                    zzdww.zza(throwable1, throwable2);
                }
                throw throwable1;
            }
            parcelFileDescriptor$AutoCloseOutputStream0.close();
            goto label_19;
        }
        catch(Throwable throwable0) {
            if(inputStream0 != null) {
                try {
                    inputStream0.close();
                }
                catch(Throwable throwable3) {
                    try {
                        zzdww.zza(throwable0, throwable3);
                        throw throwable0;
                    label_19:
                        if(inputStream0 != null) {
                            inputStream0.close();
                            return;
                        }
                    }
                    catch(IOException unused_ex) {
                    }
                    return;
                }
            }
            throw throwable0;
        }
    }
}

