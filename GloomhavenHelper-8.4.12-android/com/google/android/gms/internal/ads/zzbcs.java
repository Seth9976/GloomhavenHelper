package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

public final class zzbcs {
    private long zzcw;

    public final long zzl(ByteBuffer byteBuffer0) {
        zzbk zzbk0;
        long v = this.zzcw;
        if(v > 0L) {
            return v;
        }
        try {
            zzbh zzbh0 = null;
            ByteBuffer byteBuffer1 = byteBuffer0.duplicate();
            byteBuffer1.flip();
            Iterator iterator0 = new zzbd(new zzbcp(byteBuffer1), zzbcu.zzeed).zzbgk().iterator();
            while(true) {
                zzbk0 = null;
                if(!iterator0.hasNext()) {
                    break;
                }
                Object object0 = iterator0.next();
                zzbf zzbf0 = (zzbf)object0;
                if(zzbf0 instanceof zzbh) {
                    zzbh0 = (zzbh)zzbf0;
                    break;
                }
            }
            for(Object object1: zzbh0.zzbgk()) {
                zzbf zzbf1 = (zzbf)object1;
                if(zzbf1 instanceof zzbk) {
                    zzbk0 = (zzbk)zzbf1;
                    break;
                }
                if(false) {
                    break;
                }
            }
            this.zzcw = zzbk0.getDuration() * 1000L / zzbk0.zzs();
            return this.zzcw;
        }
        catch(RuntimeException | IOException unused_ex) {
            return 0L;
        }
    }
}

