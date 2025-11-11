package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class zzazl implements zzazc {
    @Nullable
    private final String zzbfa;

    public zzazl() {
        this(null);
    }

    public zzazl(@Nullable String s) {
        this.zzbfa = s;
    }

    @Override  // com.google.android.gms.internal.ads.zzazc
    @WorkerThread
    public final void zzeo(String s) {
        try {
            String s1 = String.valueOf(s);
            zzazh.zzeb((s1.length() == 0 ? new String("Pinging URL: ") : "Pinging URL: " + s1));
            HttpURLConnection httpURLConnection0 = (HttpURLConnection)new URL(s).openConnection();
            try {
                zzayx.zza(true, httpURLConnection0, this.zzbfa);
                zzazb zzazb0 = new zzazb();
                zzazb0.zza(httpURLConnection0, null);
                int v1 = httpURLConnection0.getResponseCode();
                zzazb0.zza(httpURLConnection0, v1);
                if(v1 < 200 || v1 >= 300) {
                    zzazh.zzfa(("Received non-success response code " + v1 + " from pinging URL: " + s));
                }
            }
            finally {
                httpURLConnection0.disconnect();
            }
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            zzazh.zzfa(("Error while parsing ping URL: " + s + ". " + indexOutOfBoundsException0.getMessage()));
        }
        catch(IOException iOException0) {
            zzazh.zzfa(("Error while pinging URL: " + s + ". " + iOException0.getMessage()));
        }
        catch(RuntimeException runtimeException0) {
            zzazh.zzfa(("Error while pinging URL: " + s + ". " + runtimeException0.getMessage()));
        }
    }
}

