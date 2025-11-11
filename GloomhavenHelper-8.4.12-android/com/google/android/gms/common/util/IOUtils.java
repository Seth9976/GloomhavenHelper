package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.annotation.Nullable;

@KeepForSdk
@ShowFirstParty
@Deprecated
public final class IOUtils {
    @KeepForSdk
    public static void closeQuietly(@Nullable ParcelFileDescriptor parcelFileDescriptor0) {
        if(parcelFileDescriptor0 != null) {
            try {
                parcelFileDescriptor0.close();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    @KeepForSdk
    public static void closeQuietly(@Nullable Closeable closeable0) {
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(IOException unused_ex) {
            }
        }
    }

    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream0, OutputStream outputStream0) throws IOException {
        return IOUtils.zza(inputStream0, outputStream0, false);
    }

    @KeepForSdk
    @Deprecated
    public static long copyStream(InputStream inputStream0, OutputStream outputStream0, boolean z, int v) throws IOException {
        byte[] arr_b = new byte[v];
        long v1 = 0L;
        try {
            int v3;
            while((v3 = inputStream0.read(arr_b, 0, v)) != -1) {
                v1 += (long)v3;
                outputStream0.write(arr_b, 0, v3);
            }
            return v1;
        }
        finally {
            if(z) {
                IOUtils.closeQuietly(inputStream0);
                IOUtils.closeQuietly(outputStream0);
            }
        }
    }

    @KeepForSdk
    public static boolean isGzipByteBuffer(byte[] arr_b) {
        return arr_b.length > 1 && ((arr_b[1] & 0xFF) << 8 | arr_b[0] & 0xFF) == 0x8B1F;
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream0) throws IOException {
        return IOUtils.readInputStreamFully(inputStream0, true);
    }

    @KeepForSdk
    @Deprecated
    public static byte[] readInputStreamFully(InputStream inputStream0, boolean z) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        IOUtils.zza(inputStream0, byteArrayOutputStream0, z);
        return byteArrayOutputStream0.toByteArray();
    }

    @KeepForSdk
    @Deprecated
    public static byte[] toByteArray(InputStream inputStream0) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        Preconditions.checkNotNull(inputStream0);
        Preconditions.checkNotNull(byteArrayOutputStream0);
        byte[] arr_b = new byte[0x1000];
        int v;
        while((v = inputStream0.read(arr_b)) != -1) {
            byteArrayOutputStream0.write(arr_b, 0, v);
        }
        return byteArrayOutputStream0.toByteArray();
    }

    @Deprecated
    private static long zza(InputStream inputStream0, OutputStream outputStream0, boolean z) throws IOException {
        return IOUtils.copyStream(inputStream0, outputStream0, z, 0x400);
    }
}

