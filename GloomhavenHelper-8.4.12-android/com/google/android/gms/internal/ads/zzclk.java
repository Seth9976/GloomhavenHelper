package com.google.android.gms.internal.ads;

public final class zzclk {
    private int responseCode;
    private long zzgab;
    private long zzgac;
    private long zzgad;
    private final Object zzgae;
    private final Object zzgaf;
    private final Object zzgag;
    private final Object zzgah;

    public zzclk() {
        this.responseCode = 0;
        this.zzgab = 0L;
        this.zzgac = 0L;
        this.zzgad = 0L;
        this.zzgae = new Object();
        this.zzgaf = new Object();
        this.zzgag = new Object();
        this.zzgah = new Object();
    }

    public final int getResponseCode() {
        synchronized(this.zzgae) {
        }
        return this.responseCode;
    }

    public final long zzanw() {
        synchronized(this.zzgaf) {
        }
        return this.zzgab;
    }

    public final long zzanx() {
        synchronized(this) {
        }
        return this.zzgac;
    }

    public final long zzany() {
        synchronized(this) {
        }
        return this.zzgad;
    }

    public final void zzdk(int v) {
        synchronized(this.zzgae) {
            this.responseCode = v;
        }
    }

    public final void zzeq(long v) {
        synchronized(this.zzgaf) {
            this.zzgab = v;
        }
    }

    public final void zzer(long v) {
        synchronized(this) {
            synchronized(this.zzgah) {
                this.zzgad = v;
            }
        }
    }

    public final void zzfe(long v) {
        synchronized(this) {
            synchronized(this.zzgag) {
                this.zzgac = v;
            }
        }
    }
}

