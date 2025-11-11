package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@KeepForSdk
public class LibraryVersion {
    private static final GmsLogger zzel;
    private static LibraryVersion zzem;
    private ConcurrentHashMap zzen;

    static {
        LibraryVersion.zzel = new GmsLogger("LibraryVersion", "");
        LibraryVersion.zzem = new LibraryVersion();
    }

    @VisibleForTesting
    protected LibraryVersion() {
        this.zzen = new ConcurrentHashMap();
    }

    @KeepForSdk
    public static LibraryVersion getInstance() {
        return LibraryVersion.zzem;
    }

    @KeepForSdk
    public String getVersion(@NonNull String s) {
        Preconditions.checkNotEmpty(s, "Please provide a valid libraryName");
        if(this.zzen.containsKey(s)) {
            return (String)this.zzen.get(s);
        }
        Properties properties0 = new Properties();
        String s1 = null;
        try {
            InputStream inputStream0 = LibraryVersion.class.getResourceAsStream(String.format("/%s.properties", s));
            if(inputStream0 == null) {
                String s2 = String.valueOf(s);
                LibraryVersion.zzel.e("LibraryVersion", (s2.length() == 0 ? new String("Failed to get app version for libraryName: ") : "Failed to get app version for libraryName: " + s2));
            }
            else {
                properties0.load(inputStream0);
                s1 = properties0.getProperty("version", null);
                LibraryVersion.zzel.v("LibraryVersion", s + " version is " + s1);
            }
        }
        catch(IOException iOException0) {
            String s3 = String.valueOf(s);
            LibraryVersion.zzel.e("LibraryVersion", (s3.length() == 0 ? new String("Failed to get app version for libraryName: ") : "Failed to get app version for libraryName: " + s3), iOException0);
        }
        if(s1 == null) {
            s1 = "UNKNOWN";
            LibraryVersion.zzel.d("LibraryVersion", ".properties file is dropped during release process. Failure to read app version isexpected druing Google internal testing where locally-built libraries are used");
        }
        this.zzen.put(s, s1);
        return s1;
    }
}

