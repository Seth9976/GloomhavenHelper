package com.google.android.gms.ads.identifier;

import android.net.Uri.Builder;
import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

final class zza extends Thread {
    private final Map zzl;

    zza(AdvertisingIdClient advertisingIdClient0, Map map0) {
        this.zzl = map0;
        super();
    }

    @Override
    public final void run() {
        String s2;
        StringBuilder stringBuilder0;
        String s1;
        Map map0 = this.zzl;
        Uri.Builder uri$Builder0 = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204?id=gmob-apps").buildUpon();
        for(Object object0: map0.keySet()) {
            uri$Builder0.appendQueryParameter(((String)object0), ((String)map0.get(((String)object0))));
        }
        String s = uri$Builder0.build().toString();
        try {
            HttpURLConnection httpURLConnection0 = (HttpURLConnection)new URL(s).openConnection();
            try {
                int v1 = httpURLConnection0.getResponseCode();
                if(v1 < 200 || v1 >= 300) {
                    Log.w("HttpUrlPinger", "Received non-success response code " + v1 + " from pinging URL: " + s);
                }
            }
            finally {
                httpURLConnection0.disconnect();
            }
            return;
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException0) {
            s1 = indexOutOfBoundsException0.getMessage();
            stringBuilder0 = new StringBuilder(String.valueOf(s).length() + 0x20 + String.valueOf(s1).length());
            s2 = "Error while parsing ping URL: ";
        }
        catch(IOException | RuntimeException indexOutOfBoundsException0) {
            s1 = indexOutOfBoundsException0.getMessage();
            stringBuilder0 = new StringBuilder(String.valueOf(s).length() + 27 + String.valueOf(s1).length());
            s2 = "Error while pinging URL: ";
        }
        stringBuilder0.append(s2);
        stringBuilder0.append(s);
        stringBuilder0.append(". ");
        stringBuilder0.append(s1);
        Log.w("HttpUrlPinger", stringBuilder0.toString(), indexOutOfBoundsException0);
    }
}

