package com.google.android.gms.internal.ads;

public class zzcid extends Exception {
    private final int errorCode;

    public zzcid(int v) {
        this.errorCode = v;
    }

    public zzcid(String s, int v) {
        super(s);
        this.errorCode = v;
    }

    public zzcid(String s, Throwable throwable0, int v) {
        super(s, throwable0);
        this.errorCode = 0;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public static int zzd(Throwable throwable0) {
        if(throwable0 instanceof zzcid) {
            return ((zzcid)throwable0).errorCode;
        }
        return throwable0 instanceof zzaxs ? ((zzaxs)throwable0).getErrorCode() : 0;
    }
}

