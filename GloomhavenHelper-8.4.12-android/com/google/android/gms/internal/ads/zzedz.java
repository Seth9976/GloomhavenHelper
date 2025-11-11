package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class zzedz extends AbstractList {
    private static final zzeeb zzcr;
    List zzifu;
    Iterator zzifv;

    static {
        zzedz.zzcr = zzeeb.zzn(zzedz.class);
    }

    public zzedz(List list0, Iterator iterator0) {
        this.zzifu = list0;
        this.zzifv = iterator0;
    }

    @Override
    public Object get(int v) {
        if(this.zzifu.size() > v) {
            return this.zzifu.get(v);
        }
        if(!this.zzifv.hasNext()) {
            throw new NoSuchElementException();
        }
        List list0 = this.zzifu;
        Object object0 = this.zzifv.next();
        list0.add(object0);
        return this.get(v);
    }

    @Override
    public Iterator iterator() {
        return new zzeec(this);
    }

    @Override
    public int size() {
        zzedz.zzcr.zzhs("potentially expensive size() call");
        zzedz.zzcr.zzhs("blowup running");
        while(this.zzifv.hasNext()) {
            List list0 = this.zzifu;
            Object object0 = this.zzifv.next();
            list0.add(object0);
        }
        return this.zzifu.size();
    }
}

