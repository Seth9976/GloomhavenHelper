package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;
import java.io.IOException;
import java.io.InputStream;

public final class zzdfd {
    public static ParcelFileDescriptor zze(InputStream inputStream0) throws IOException {
        ParcelFileDescriptor[] arr_parcelFileDescriptor = ParcelFileDescriptor.createPipe();
        ParcelFileDescriptor parcelFileDescriptor0 = arr_parcelFileDescriptor[0];
        zzdfg zzdfg0 = new zzdfg(inputStream0, arr_parcelFileDescriptor[1]);
        zzazq.zzdxk.execute(zzdfg0);
        return parcelFileDescriptor0;
    }
}

