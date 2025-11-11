package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class zzedt implements zzbi, Closeable, Iterator {
    long zzare;
    long zzbcq;
    private static zzeeb zzcr;
    protected zzedv zzifn;
    private static final zzbf zzifp;
    protected zzbe zzifq;
    private zzbf zzifr;
    long zzifs;
    private List zzift;

    static {
        zzedt.zzifp = new zzedw("eof ");
        zzedt.zzcr = zzeeb.zzn(zzedt.class);
    }

    public zzedt() {
        this.zzifr = null;
        this.zzifs = 0L;
        this.zzbcq = 0L;
        this.zzare = 0L;
        this.zzift = new ArrayList();
    }

    @Override
    public void close() throws IOException {
        this.zzifn.close();
    }

    @Override
    public boolean hasNext() {
        zzbf zzbf0 = this.zzifr;
        if(zzbf0 == zzedt.zzifp) {
            return false;
        }
        if(zzbf0 != null) {
            return true;
        }
        try {
            this.zzifr = (zzbf)this.next();
            return true;
        }
        catch(NoSuchElementException unused_ex) {
            this.zzifr = zzedt.zzifp;
            return false;
        }
    }

    @Override
    public Object next() {
        return this.zzbgl();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(this.getClass().getSimpleName());
        stringBuilder0.append("[");
        for(int v = 0; v < this.zzift.size(); ++v) {
            if(v > 0) {
                stringBuilder0.append(";");
            }
            stringBuilder0.append(((zzbf)this.zzift.get(v)).toString());
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public void zza(zzedv zzedv0, long v, zzbe zzbe0) throws IOException {
        this.zzifn = zzedv0;
        long v1 = zzedv0.position();
        this.zzbcq = v1;
        this.zzifs = v1;
        zzedv0.zzfc(zzedv0.position() + v);
        this.zzare = zzedv0.position();
        this.zzifq = zzbe0;
    }

    public final List zzbgk() {
        return this.zzifn != null && this.zzifr != zzedt.zzifp ? new zzedz(this.zzift, this) : this.zzift;
    }

    private final zzbf zzbgl() {
        zzbf zzbf0 = this.zzifr;
        if(zzbf0 != null && zzbf0 != zzedt.zzifp) {
            this.zzifr = null;
            return zzbf0;
        }
        zzedv zzedv0 = this.zzifn;
        if(zzedv0 != null && this.zzifs < this.zzare) {
            try {
                synchronized(zzedv0) {
                    this.zzifn.zzfc(this.zzifs);
                    zzbf zzbf1 = this.zzifq.zza(this.zzifn, this);
                    this.zzifs = this.zzifn.position();
                    return zzbf1;
                }
            }
            catch(EOFException unused_ex) {
                throw new NoSuchElementException();
            }
            catch(IOException unused_ex) {
                throw new NoSuchElementException();
            }
        }
        this.zzifr = zzedt.zzifp;
        throw new NoSuchElementException();
    }
}

