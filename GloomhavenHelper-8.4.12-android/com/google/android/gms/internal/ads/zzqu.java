package com.google.android.gms.internal.ads;

import android.util.Base64OutputStream;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@VisibleForTesting
final class zzqu {
    @VisibleForTesting
    private ByteArrayOutputStream zzbqt;
    @VisibleForTesting
    private Base64OutputStream zzbqu;

    public zzqu() {
        this.zzbqt = new ByteArrayOutputStream(0x1000);
        this.zzbqu = new Base64OutputStream(this.zzbqt, 10);
    }

    @Override
    public final String toString() {
        try {
            this.zzbqu.close();
        }
        catch(IOException iOException0) {
            zzawf.zzc("HashManager: Unable to convert to Base64.", iOException0);
        }
        try {
            try {
                this.zzbqt.close();
                String s = this.zzbqt.toString();
                this.zzbqt = null;
                this.zzbqu = null;
                return s;
            }
            catch(IOException iOException1) {
                zzawf.zzc("HashManager: Unable to convert to Base64.", iOException1);
                this.zzbqt = null;
                this.zzbqu = null;
                return "";
            }
        }
        catch(Throwable throwable0) {
            this.zzbqt = null;
            this.zzbqu = null;
            throw throwable0;
        }
    }

    public final void write(byte[] arr_b) throws IOException {
        this.zzbqu.write(arr_b);
    }
}

