package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class zzin implements zzhp {
    private int zzafx;
    private int zzain;
    private ByteBuffer zzajs;
    private int zzakp;
    private ByteBuffer zzakt;
    private boolean zzaku;

    public zzin() {
        this.zzakp = -1;
        this.zzafx = -1;
        this.zzain = 0;
        this.zzakt = zzin.zzahl;
        this.zzajs = zzin.zzahl;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void flush() {
        this.zzajs = zzin.zzahl;
        this.zzaku = false;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean isActive() {
        return this.zzain != 0 && this.zzain != 2;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void reset() {
        this.flush();
        this.zzakt = zzin.zzahl;
        this.zzakp = -1;
        this.zzafx = -1;
        this.zzain = 0;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzb(int v, int v1, int v2) throws zzho {
        if(v2 != 2 && v2 != 3 && (v2 != 0x80000000 && v2 != 0x40000000)) {
            throw new zzho(v, v1, v2);
        }
        if(this.zzakp == v && this.zzafx == v1 && this.zzain == v2) {
            return false;
        }
        this.zzakp = v;
        this.zzafx = v1;
        this.zzain = v2;
        if(v2 == 2) {
            this.zzakt = zzin.zzahl;
        }
        return true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final boolean zzez() {
        return this.zzaku && this.zzajs == zzin.zzahl;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzfe() {
        return this.zzafx;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final int zzff() {
        return 2;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzfg() {
        this.zzaku = true;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final ByteBuffer zzfh() {
        ByteBuffer byteBuffer0 = this.zzajs;
        this.zzajs = zzin.zzahl;
        return byteBuffer0;
    }

    @Override  // com.google.android.gms.internal.ads.zzhp
    public final void zzi(ByteBuffer byteBuffer0) {
        int v3;
        int v = byteBuffer0.position();
        int v1 = byteBuffer0.limit();
        int v2 = v1 - v;
        switch(this.zzain) {
            case 0x80000000: {
                v3 = v2 / 3 << 1;
                break;
            }
            case 3: {
                v3 = v2 << 1;
                break;
            }
            case 0x40000000: {
                v3 = v2 / 2;
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
        if(this.zzakt.capacity() < v3) {
            this.zzakt = ByteBuffer.allocateDirect(v3).order(ByteOrder.nativeOrder());
        }
        else {
            this.zzakt.clear();
        }
    alab1:
        switch(this.zzain) {
            case 0x80000000: {
                while(v < v1) {
                    this.zzakt.put(byteBuffer0.get(v + 1));
                    this.zzakt.put(byteBuffer0.get(v + 2));
                    v += 3;
                }
                break;
            }
            case 3: {
                while(v < v1) {
                    this.zzakt.put(0);
                    this.zzakt.put(((byte)((byteBuffer0.get(v) & 0xFF) - 0x80)));
                    ++v;
                }
                break;
            }
            case 0x40000000: {
                while(true) {
                    if(v >= v1) {
                        break alab1;
                    }
                    this.zzakt.put(byteBuffer0.get(v + 2));
                    this.zzakt.put(byteBuffer0.get(v + 3));
                    v += 4;
                }
            }
            default: {
                throw new IllegalStateException();
            }
        }
        byteBuffer0.position(byteBuffer0.limit());
        this.zzakt.flip();
        this.zzajs = this.zzakt;
    }
}

