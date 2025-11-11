package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

public final class zzjw implements zzjg {
    private long zzagy;
    private final zzjz zzanm;
    private static final zzjh zzanu;
    private static final byte[] zzanv;
    private static final byte[] zzanw;
    private static final UUID zzanx;
    private final zzju zzany;
    private final SparseArray zzanz;
    private final boolean zzaoa;
    private final zzom zzaob;
    private final zzom zzaoc;
    private final zzom zzaod;
    private final zzom zzaoe;
    private final zzom zzaof;
    private final zzom zzaog;
    private final zzom zzaoh;
    private final zzom zzaoi;
    private final zzom zzaoj;
    private ByteBuffer zzaok;
    private long zzaol;
    private long zzaom;
    private long zzaon;
    private long zzaoo;
    private zzjx zzaop;
    private boolean zzaoq;
    private int zzaor;
    private long zzaos;
    private boolean zzaot;
    private long zzaou;
    private long zzaov;
    private long zzaow;
    private zzog zzaox;
    private zzog zzaoy;
    private boolean zzaoz;
    private int zzapa;
    private long zzapb;
    private long zzapc;
    private int zzapd;
    private int zzape;
    private int[] zzapf;
    private int zzapg;
    private int zzaph;
    private int zzapi;
    private int zzapj;
    private boolean zzapk;
    private boolean zzapl;
    private boolean zzapm;
    private boolean zzapn;
    private byte zzapo;
    private int zzapp;
    private int zzapq;
    private int zzapr;
    private boolean zzaps;
    private boolean zzapt;
    private zzji zzapu;

    static {
        zzjw.zzanu = new zzjv();
        zzjw.zzanv = new byte[]{49, 10, 0x30, 0x30, 58, 0x30, 0x30, 58, 0x30, 0x30, 44, 0x30, 0x30, 0x30, 0x20, 45, 45, 62, 0x20, 0x30, 0x30, 58, 0x30, 0x30, 58, 0x30, 0x30, 44, 0x30, 0x30, 0x30, 10};
        zzjw.zzanw = new byte[]{0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20};
        zzjw.zzanx = new UUID(0x100000000001000L, 0x800000AA00389B71L);
    }

    public zzjw() {
        this(0);
    }

    private zzjw(int v) {
        this(new zzjp(), 0);
    }

    private zzjw(zzju zzju0, int v) {
        this.zzaom = -1L;
        this.zzaon = 0x8000000000000001L;
        this.zzaoo = 0x8000000000000001L;
        this.zzagy = 0x8000000000000001L;
        this.zzaou = -1L;
        this.zzaov = -1L;
        this.zzaow = 0x8000000000000001L;
        this.zzany = zzju0;
        zzjy zzjy0 = new zzjy(this, null);
        this.zzany.zza(zzjy0);
        this.zzaoa = true;
        this.zzanm = new zzjz();
        this.zzanz = new SparseArray();
        this.zzaod = new zzom(4);
        this.zzaoe = new zzom(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzaof = new zzom(4);
        this.zzaob = new zzom(zzoh.zzbgi);
        this.zzaoc = new zzom(4);
        this.zzaog = new zzom();
        this.zzaoh = new zzom();
        this.zzaoi = new zzom(8);
        this.zzaoj = new zzom();
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void release() {
    }

    private final int zza(zzjf zzjf0, zzjn zzjn0, int v) throws IOException, InterruptedException {
        int v2;
        int v1 = this.zzaog.zzix();
        if(v1 > 0) {
            v2 = Math.min(v, v1);
            zzjn0.zza(this.zzaog, v2);
        }
        else {
            v2 = zzjn0.zza(zzjf0, v, false);
        }
        this.zzapj += v2;
        this.zzapr += v2;
        return v2;
    }

    private final void zza(zzjf zzjf0, zzjx zzjx0, int v) throws IOException, InterruptedException {
        if("S_TEXT/UTF8".equals(zzjx0.zzapv)) {
            int v1 = zzjw.zzanv.length + v;
            if(this.zzaoh.capacity() < v1) {
                this.zzaoh.data = Arrays.copyOf(zzjw.zzanv, v1 + v);
            }
            zzjf0.readFully(this.zzaoh.data, zzjw.zzanv.length, v);
            this.zzaoh.zzbh(0);
            this.zzaoh.zzbg(v1);
            return;
        }
        zzjn zzjn0 = zzjx0.zzaqz;
        if(!this.zzapk) {
            if(zzjx0.zzapx) {
                this.zzapi &= 0xBFFFFFFF;
                int v2 = 0x80;
                if(!this.zzapl) {
                    zzjf0.readFully(this.zzaod.data, 0, 1);
                    ++this.zzapj;
                    if((this.zzaod.data[0] & 0x80) == 0x80) {
                        throw new zzhc("Extension bit is set in signal byte");
                    }
                    this.zzapo = this.zzaod.data[0];
                    this.zzapl = true;
                }
                int v3 = this.zzapo;
                if((v3 & 1) == 1) {
                    boolean z = (v3 & 2) == 2;
                    this.zzapi |= 0x40000000;
                    if(!this.zzapm) {
                        zzjf0.readFully(this.zzaoi.data, 0, 8);
                        this.zzapj += 8;
                        this.zzapm = true;
                        byte[] arr_b = this.zzaod.data;
                        if(!z) {
                            v2 = 0;
                        }
                        arr_b[0] = (byte)(v2 | 8);
                        this.zzaod.zzbh(0);
                        zzjn0.zza(this.zzaod, 1);
                        ++this.zzapr;
                        this.zzaoi.zzbh(0);
                        zzjn0.zza(this.zzaoi, 8);
                        this.zzapr += 8;
                    }
                    if(z) {
                        if(!this.zzapn) {
                            zzjf0.readFully(this.zzaod.data, 0, 1);
                            ++this.zzapj;
                            this.zzaod.zzbh(0);
                            this.zzapp = this.zzaod.readUnsignedByte();
                            this.zzapn = true;
                        }
                        int v4 = this.zzapp << 2;
                        this.zzaod.reset(v4);
                        zzjf0.readFully(this.zzaod.data, 0, v4);
                        this.zzapj += v4;
                        int v5 = (short)(this.zzapp / 2 + 1);
                        int v6 = v5 * 6 + 2;
                        if(this.zzaok == null || this.zzaok.capacity() < v6) {
                            this.zzaok = ByteBuffer.allocate(v6);
                        }
                        this.zzaok.position(0);
                        this.zzaok.putShort(((short)v5));
                        int v7 = 0;
                        int v8;
                        for(v8 = 0; true; v8 = v10) {
                            int v9 = this.zzapp;
                            if(v7 >= v9) {
                                break;
                            }
                            int v10 = this.zzaod.zzjc();
                            if(v7 % 2 == 0) {
                                this.zzaok.putShort(((short)(v10 - v8)));
                            }
                            else {
                                this.zzaok.putInt(v10 - v8);
                            }
                            ++v7;
                        }
                        int v11 = v - this.zzapj - v8;
                        if(v9 % 2 == 1) {
                            this.zzaok.putInt(v11);
                        }
                        else {
                            this.zzaok.putShort(((short)v11));
                            this.zzaok.putInt(0);
                        }
                        byte[] arr_b1 = this.zzaok.array();
                        this.zzaoj.zzb(arr_b1, v6);
                        zzjn0.zza(this.zzaoj, v6);
                        this.zzapr += v6;
                    }
                }
            }
            else if(zzjx0.zzapy != null) {
                this.zzaog.zzb(zzjx0.zzapy, zzjx0.zzapy.length);
            }
            this.zzapk = true;
        }
        int v12 = v + this.zzaog.limit();
        if("V_MPEG4/ISO/AVC".equals(zzjx0.zzapv) || "V_MPEGH/ISO/HEVC".equals(zzjx0.zzapv)) {
            byte[] arr_b2 = this.zzaoc.data;
            arr_b2[0] = 0;
            arr_b2[1] = 0;
            arr_b2[2] = 0;
            int v14 = zzjx0.zzara;
            int v15 = 4 - zzjx0.zzara;
            while(this.zzapj < v12) {
                int v16 = this.zzapq;
                if(v16 == 0) {
                    int v17 = Math.min(v14, this.zzaog.zzix());
                    zzjf0.readFully(arr_b2, v15 + v17, v14 - v17);
                    if(v17 > 0) {
                        this.zzaog.zze(arr_b2, v15, v17);
                    }
                    this.zzapj += v14;
                    this.zzaoc.zzbh(0);
                    this.zzapq = this.zzaoc.zzjc();
                    this.zzaob.zzbh(0);
                    zzjn0.zza(this.zzaob, 4);
                    this.zzapr += 4;
                }
                else {
                    this.zzapq = v16 - this.zza(zzjf0, zzjn0, v16);
                }
            }
        }
        else {
            int v13;
            while((v13 = this.zzapj) < v12) {
                this.zza(zzjf0, zzjn0, v12 - v13);
            }
        }
        if("A_VORBIS".equals(zzjx0.zzapv)) {
            this.zzaoe.zzbh(0);
            zzjn0.zza(this.zzaoe, 4);
            this.zzapr += 4;
        }
    }

    private final void zza(zzjx zzjx0, long v) {
        byte[] arr_b1;
        if("S_TEXT/UTF8".equals(zzjx0.zzapv)) {
            byte[] arr_b = this.zzaoh.data;
            long v1 = this.zzapc;
            if(v1 == 0x8000000000000001L) {
                arr_b1 = zzjw.zzanw;
            }
            else {
                long v2 = v1 - ((long)(((int)(v1 / 3600000000L)))) * 3600000000L;
                long v3 = v2 - ((long)(60000000 * ((int)(v2 / 60000000L))));
                arr_b1 = zzop.zzbn(String.format(Locale.US, "%02d:%02d:%02d,%03d", ((int)(v1 / 3600000000L)), ((int)(v2 / 60000000L)), ((int)(v3 / 1000000L)), ((int)((v3 - ((long)(1000000 * ((int)(v3 / 1000000L))))) / 1000L))));
            }
            System.arraycopy(arr_b1, 0, arr_b, 19, 12);
            zzjx0.zzaqz.zza(this.zzaoh, this.zzaoh.limit());
            this.zzapr += this.zzaoh.limit();
        }
        zzjx0.zzaqz.zza(v, this.zzapi, this.zzapr, 0, zzjx0.zzapz);
        this.zzaps = true;
        this.zzgr();
    }

    private static int[] zza(int[] arr_v, int v) {
        if(arr_v == null) {
            return new int[v];
        }
        return arr_v.length < v ? new int[Math.max(arr_v.length << 1, v)] : arr_v;
    }

    // This method was un-flattened
    @Override  // com.google.android.gms.internal.ads.zzjg
    public final int zza(zzjf zzjf0, zzjm zzjm0) throws IOException, InterruptedException {
        this.zzaps = false;
        boolean z = true;
        while(z) {
            z = this.zzany.zzb(zzjf0);
            if(z) {
                long v = zzjf0.getPosition();
                if(this.zzaot) {
                    this.zzaov = v;
                    zzjm0.zzana = this.zzaou;
                    this.zzaot = false;
                    return 1;
                }
                if(this.zzaoq) {
                    long v1 = this.zzaov;
                    if(v1 != -1L) {
                        zzjm0.zzana = v1;
                        this.zzaov = -1L;
                        return 1;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return -1;
    }

    final void zza(int v, double f) {
        switch(v) {
            case 0xB5: {
                this.zzaop.zzafy = (int)f;
                return;
            }
            case 0x4489: {
                this.zzaoo = (long)f;
                return;
            }
            case 0x55D1: {
                this.zzaop.zzaqk = (float)f;
                return;
            }
            case 21970: {
                this.zzaop.zzaql = (float)f;
                return;
            }
            case 0x55D3: {
                this.zzaop.zzaqm = (float)f;
                return;
            }
            case 0x55D4: {
                this.zzaop.zzaqn = (float)f;
                return;
            }
            case 0x55D5: {
                this.zzaop.zzaqo = (float)f;
                return;
            }
            case 0x55D6: {
                this.zzaop.zzaqp = (float)f;
                return;
            }
            case 0x55D7: {
                this.zzaop.zzaqq = (float)f;
                return;
            }
            case 0x55D8: {
                this.zzaop.zzaqr = (float)f;
                return;
            }
            case 0x55D9: {
                this.zzaop.zzaqs = (float)f;
                return;
            }
            case 0x55DA: {
                this.zzaop.zzaqt = (float)f;
            }
        }
    }

    final void zza(int v, int v1, zzjf zzjf0) throws IOException, InterruptedException {
        long v14;
        int v11;
        int[] arr_v;
        int v6;
        if(v != 0xA1 && v != 0xA3) {
            switch(v) {
                case 0x4255: {
                    zzjx zzjx3 = this.zzaop;
                    zzjx3.zzapy = new byte[v1];
                    zzjf0.readFully(zzjx3.zzapy, 0, v1);
                    return;
                }
                case 18402: {
                    byte[] arr_b = new byte[v1];
                    zzjf0.readFully(arr_b, 0, v1);
                    zzjx zzjx2 = this.zzaop;
                    zzjx2.zzapz = new zzjq(1, arr_b);
                    return;
                }
                case 0x53AB: {
                    Arrays.fill(this.zzaof.data, 0);
                    zzjf0.readFully(this.zzaof.data, 4 - v1, v1);
                    this.zzaof.zzbh(0);
                    this.zzaor = (int)this.zzaof.zziz();
                    return;
                }
                case 25506: {
                    zzjx zzjx1 = this.zzaop;
                    zzjx1.zzaqa = new byte[v1];
                    zzjf0.readFully(zzjx1.zzaqa, 0, v1);
                    return;
                }
                case 30322: {
                    zzjx zzjx0 = this.zzaop;
                    zzjx0.zzafv = new byte[v1];
                    zzjf0.readFully(zzjx0.zzafv, 0, v1);
                    return;
                }
                default: {
                    throw new zzhc("Unexpected id: " + v);
                }
            }
        }
        if(this.zzapa == 0) {
            this.zzapg = (int)this.zzanm.zza(zzjf0, false, true, 8);
            this.zzaph = this.zzanm.zzgt();
            this.zzapc = 0x8000000000000001L;
            this.zzapa = 1;
            this.zzaod.reset();
        }
        zzjx zzjx4 = (zzjx)this.zzanz.get(this.zzapg);
        if(zzjx4 == null) {
            zzjf0.zzac(v1 - this.zzaph);
            this.zzapa = 0;
            return;
        }
        if(this.zzapa == 1) {
            this.zzb(zzjf0, 3);
            int v2 = (this.zzaod.data[2] & 6) >> 1;
            if(v2 == 0) {
                this.zzape = 1;
                this.zzapf = zzjw.zza(this.zzapf, 1);
                this.zzapf[0] = v1 - this.zzaph - 3;
            }
            else {
                if(v != 0xA3) {
                    throw new zzhc("Lacing only supported in SimpleBlocks.");
                }
                this.zzb(zzjf0, 4);
                this.zzape = (this.zzaod.data[3] & 0xFF) + 1;
                this.zzapf = zzjw.zza(this.zzapf, this.zzape);
                if(v2 == 2) {
                    Arrays.fill(this.zzapf, 0, this.zzape, (v1 - this.zzaph - 4) / this.zzape);
                }
                else if(v2 == 1) {
                    int v4 = 4;
                    int v5 = 0;
                    for(int v3 = 0; true; ++v3) {
                        v6 = this.zzape;
                        if(v3 >= v6 - 1) {
                            break;
                        }
                        this.zzapf[v3] = 0;
                        do {
                            ++v4;
                            this.zzb(zzjf0, v4);
                            int v7 = this.zzaod.data[v4 - 1] & 0xFF;
                            arr_v = this.zzapf;
                            arr_v[v3] += v7;
                        }
                        while(v7 == 0xFF);
                        v5 += arr_v[v3];
                    }
                    this.zzapf[v6 - 1] = v1 - this.zzaph - v4 - v5;
                }
                else {
                    if(v2 != 3) {
                        throw new zzhc("Unexpected lacing value: " + v2);
                    }
                    int v8 = 0;
                    int v9 = 4;
                    int v10 = 0;
                    while(true) {
                        v11 = this.zzape;
                        if(v8 >= v11 - 1) {
                            goto label_113;
                        }
                        this.zzapf[v8] = 0;
                        ++v9;
                        this.zzb(zzjf0, v9);
                        int v12 = v9 - 1;
                        if(this.zzaod.data[v12] == 0) {
                            throw new zzhc("No valid varint length mask found");
                        }
                        int v13 = 0;
                        while(true) {
                            v14 = 0L;
                            if(v13 < 8) {
                                int v15 = 1 << 7 - v13;
                                if((this.zzaod.data[v12] & v15) == 0) {
                                    ++v13;
                                    continue;
                                }
                                else {
                                    v9 += v13;
                                    this.zzb(zzjf0, v9);
                                    int v16 = v12 + 1;
                                    long v17;
                                    for(v17 = (long)(this.zzaod.data[v12] & 0xFF & ~v15); v16 < v9; v17 = v18) {
                                        long v18 = v17 << 8 | ((long)(this.zzaod.data[v16] & 0xFF));
                                        ++v16;
                                    }
                                    v14 = v8 > 0 ? v17 - ((1L << v13 * 7 + 6) - 1L) : v17;
                                }
                            }
                            break;
                        }
                        if(v14 < 0xFFFFFFFF80000000L || v14 > 0x7FFFFFFFL) {
                            break;
                        }
                        int v19 = (int)v14;
                        int[] arr_v1 = this.zzapf;
                        if(v8 != 0) {
                            v19 += arr_v1[v8 - 1];
                        }
                        arr_v1[v8] = v19;
                        v10 += this.zzapf[v8];
                        ++v8;
                    }
                    throw new zzhc("EBML lacing sample size out of range.");
                label_113:
                    this.zzapf[v11 - 1] = v1 - this.zzaph - v9 - v10;
                }
            }
            this.zzapb = this.zzaow + this.zzea(((long)(this.zzaod.data[0] << 8 | this.zzaod.data[1] & 0xFF)));
            this.zzapi = (zzjx4.type == 2 || v == 0xA3 && (this.zzaod.data[2] & 0x80) == 0x80 ? 1 : 0) | ((this.zzaod.data[2] & 8) == 8 ? 0 : 0x80000000);
            this.zzapa = 2;
            this.zzapd = 0;
        }
        if(v == 0xA3) {
            int v20;
            while((v20 = this.zzapd) < this.zzape) {
                this.zza(zzjf0, zzjx4, this.zzapf[v20]);
                this.zza(zzjx4, this.zzapb + ((long)(this.zzapd * zzjx4.zzapw / 1000)));
                ++this.zzapd;
            }
            this.zzapa = 0;
            return;
        }
        this.zza(zzjf0, zzjx4, this.zzapf[0]);
    }

    final void zza(int v, String s) throws zzhc {
        switch(v) {
            case 0x86: {
                this.zzaop.zzapv = s;
                return;
            }
            case 17026: {
                if(!"webm".equals(s) && !"matroska".equals(s)) {
                    throw new zzhc("DocType " + s + " not supported");
                }
                return;
            }
            case 0x22B59C: {
                this.zzaop.zzage = s;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void zza(zzji zzji0) {
        this.zzapu = zzji0;
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final boolean zza(zzjf zzjf0) throws IOException, InterruptedException {
        return new zzka().zza(zzjf0);
    }

    static int zzah(int v) {
        switch(v) {
            case 0xB5: 
            case 0x4489: 
            case 0x55D1: 
            case 21970: 
            case 0x55D3: 
            case 0x55D4: 
            case 0x55D5: 
            case 0x55D6: 
            case 0x55D7: 
            case 0x55D8: 
            case 0x55D9: 
            case 0x55DA: {
                return 5;
            }
            case 0xA1: 
            case 0xA3: 
            case 0x4255: 
            case 18402: 
            case 0x53AB: 
            case 25506: 
            case 30322: {
                return 4;
            }
            case 0x86: 
            case 17026: 
            case 0x22B59C: {
                return 3;
            }
            case 0x83: 
            case 0x88: 
            case 0x9B: 
            case 0x9F: 
            case 0xB0: 
            case 0xB3: 
            case 0xBA: 
            case 0xD7: 
            case 0xE7: 
            case 0xF1: 
            case 0xFB: 
            case 16980: 
            case 17029: 
            case 0x42F7: 
            case 18401: 
            case 18408: 
            case 0x5031: 
            case 20530: 
            case 21420: 
            case 0x53B8: 
            case 0x54B0: 
            case 0x54B2: 
            case 21690: 
            case 21930: 
            case 0x55B9: 
            case 0x55BA: 
            case 0x55BB: 
            case 0x55BC: 
            case 0x55BD: 
            case 0x56AA: 
            case 22203: 
            case 0x6264: 
            case 2352003: 
            case 2807729: {
                return 2;
            }
            case 0xA0: 
            case 0xAE: 
            case 0xB7: 
            case 0xBB: 
            case 0xE0: 
            case 0xE1: 
            case 18407: 
            case 0x4DBB: 
            case 0x5034: 
            case 0x5035: 
            case 0x55B0: 
            case 0x55D0: 
            case 0x6240: 
            case 0x6D80: 
            case 30320: 
            case 290298740: 
            case 357149030: 
            case 0x1654AE6B: 
            case 0x18538067: 
            case 0x1A45DFA3: 
            case 0x1C53BB6B: 
            case 0x1F43B675: {
                return 1;
            }
            default: {
                return 0;
            }
        }
    }

    static boolean zzai(int v) {
        return v == 357149030 || v == 0x1654AE6B || v == 0x1C53BB6B || v == 0x1F43B675;
    }

    final void zzaj(int v) throws zzhc {
        zzjo zzjo0;
        switch(v) {
            case 0xA0: {
                if(this.zzapa != 2) {
                    return;
                }
                if(!this.zzapt) {
                    this.zzapi |= 1;
                }
                this.zza(((zzjx)this.zzanz.get(this.zzapg)), this.zzapb);
                this.zzapa = 0;
                return;
            }
            case 0xAE: {
                String s = this.zzaop.zzapv;
                if("V_VP8".equals(s) || "V_VP9".equals(s) || "V_MPEG2".equals(s) || "V_MPEG4/ISO/SP".equals(s) || "V_MPEG4/ISO/ASP".equals(s) || "V_MPEG4/ISO/AP".equals(s) || "V_MPEG4/ISO/AVC".equals(s) || "V_MPEGH/ISO/HEVC".equals(s) || "V_MS/VFW/FOURCC".equals(s) || "V_THEORA".equals(s) || "A_OPUS".equals(s) || "A_VORBIS".equals(s) || "A_AAC".equals(s) || "A_MPEG/L2".equals(s) || "A_MPEG/L3".equals(s) || "A_AC3".equals(s) || "A_EAC3".equals(s) || "A_TRUEHD".equals(s) || "A_DTS".equals(s) || "A_DTS/EXPRESS".equals(s) || "A_DTS/LOSSLESS".equals(s) || "A_FLAC".equals(s) || "A_MS/ACM".equals(s) || "A_PCM/INT/LIT".equals(s) || "S_TEXT/UTF8".equals(s) || "S_VOBSUB".equals(s) || "S_HDMV/PGS".equals(s) || "S_DVBSUB".equals(s)) {
                    this.zzaop.zza(this.zzapu, this.zzaop.number);
                    this.zzanz.put(this.zzaop.number, this.zzaop);
                }
                this.zzaop = null;
                return;
            }
            case 0x4DBB: {
                int v2 = this.zzaor;
                if(v2 == -1) {
                    throw new zzhc("Mandatory element SeekID or SeekPosition not found");
                }
                long v3 = this.zzaos;
                if(v3 == -1L) {
                    throw new zzhc("Mandatory element SeekID or SeekPosition not found");
                }
                if(v2 == 0x1C53BB6B) {
                    this.zzaou = v3;
                    return;
                }
                break;
            }
            case 0x6240: {
                if(this.zzaop.zzapx) {
                    if(this.zzaop.zzapz == null) {
                        throw new zzhc("Encrypted Track found but ContentEncKeyID was not found");
                    }
                    zzjx zzjx0 = this.zzaop;
                    zzjx0.zzafq = new zziu(new zza[]{new zza(zzgl.zzacq, "video/webm", this.zzaop.zzapz.zzans)});
                    return;
                }
                break;
            }
            case 0x6D80: {
                if(this.zzaop.zzapx && this.zzaop.zzapy != null) {
                    throw new zzhc("Combining encryption and compression is not supported");
                }
                break;
            }
            case 357149030: {
                if(this.zzaon == 0x8000000000000001L) {
                    this.zzaon = 1000000L;
                }
                long v4 = this.zzaoo;
                if(v4 != 0x8000000000000001L) {
                    this.zzagy = this.zzea(v4);
                    return;
                }
                break;
            }
            case 0x1654AE6B: {
                if(this.zzanz.size() == 0) {
                    throw new zzhc("No valid tracks were found");
                }
                this.zzapu.zzgp();
                return;
            }
            case 0x1C53BB6B: {
                if(!this.zzaoq) {
                    zzji zzji0 = this.zzapu;
                    if(this.zzaom == -1L || this.zzagy == 0x8000000000000001L || (this.zzaox == null || this.zzaox.size() == 0) || (this.zzaoy == null || this.zzaoy.size() != this.zzaox.size())) {
                        this.zzaox = null;
                        this.zzaoy = null;
                        zzjo0 = new zzjo(this.zzagy);
                    }
                    else {
                        int v5 = this.zzaox.size();
                        int[] arr_v = new int[v5];
                        long[] arr_v1 = new long[v5];
                        long[] arr_v2 = new long[v5];
                        long[] arr_v3 = new long[v5];
                        for(int v6 = 0; v6 < v5; ++v6) {
                            arr_v3[v6] = this.zzaox.get(v6);
                            arr_v1[v6] = this.zzaom + this.zzaoy.get(v6);
                        }
                        for(int v1 = 0; v1 < v5 - 1; ++v1) {
                            arr_v[v1] = (int)(arr_v1[v1 + 1] - arr_v1[v1]);
                            arr_v2[v1] = arr_v3[v1 + 1] - arr_v3[v1];
                        }
                        arr_v[v5 - 1] = (int)(this.zzaom + this.zzaol - arr_v1[v5 - 1]);
                        arr_v2[v5 - 1] = this.zzagy - arr_v3[v5 - 1];
                        this.zzaox = null;
                        this.zzaoy = null;
                        zzjo0 = new zzje(arr_v, arr_v1, arr_v2, arr_v3);
                    }
                    zzji0.zza(zzjo0);
                    this.zzaoq = true;
                    return;
                }
                break;
            }
        }
    }

    private final void zzb(zzjf zzjf0, int v) throws IOException, InterruptedException {
        if(this.zzaod.limit() >= v) {
            return;
        }
        if(this.zzaod.capacity() < v) {
            byte[] arr_b = Arrays.copyOf(this.zzaod.data, Math.max(this.zzaod.data.length << 1, v));
            this.zzaod.zzb(arr_b, this.zzaod.limit());
        }
        zzjf0.readFully(this.zzaod.data, this.zzaod.limit(), v - this.zzaod.limit());
        this.zzaod.zzbg(v);
    }

    final void zzc(int v, long v1) throws zzhc {
        boolean z = false;
        switch(v) {
            case 0x83: {
                this.zzaop.type = (int)v1;
                return;
            }
            case 0x88: {
                zzjx zzjx0 = this.zzaop;
                if(v1 == 1L) {
                    z = true;
                }
                zzjx0.zzaqx = z;
                return;
            }
            case 0x9B: {
                this.zzapc = this.zzea(v1);
                return;
            }
            case 0x9F: {
                this.zzaop.zzafx = (int)v1;
                return;
            }
            case 0xB0: {
                this.zzaop.width = (int)v1;
                return;
            }
            case 0xB3: {
                this.zzaox.add(this.zzea(v1));
                return;
            }
            case 0xBA: {
                this.zzaop.height = (int)v1;
                return;
            }
            case 0xD7: {
                this.zzaop.number = (int)v1;
                return;
            }
            case 0xE7: {
                this.zzaow = this.zzea(v1);
                return;
            }
            case 0xF1: {
                if(!this.zzaoz) {
                    this.zzaoy.add(v1);
                    this.zzaoz = true;
                    return;
                }
                break;
            }
            case 0xFB: {
                this.zzapt = true;
                return;
            }
            case 16980: {
                if(v1 != 3L) {
                    throw new zzhc("ContentCompAlgo " + v1 + " not supported");
                }
                break;
            }
            case 17029: {
                if(v1 < 1L || v1 > 2L) {
                    throw new zzhc("DocTypeReadVersion " + v1 + " not supported");
                }
                break;
            }
            case 0x42F7: {
                if(v1 != 1L) {
                    throw new zzhc("EBMLReadVersion " + v1 + " not supported");
                }
                break;
            }
            case 18401: {
                if(v1 != 5L) {
                    throw new zzhc("ContentEncAlgo " + v1 + " not supported");
                }
                break;
            }
            case 18408: {
                if(v1 != 1L) {
                    throw new zzhc("AESSettingsCipherMode " + v1 + " not supported");
                }
                break;
            }
            case 0x5031: {
                if(v1 != 0L) {
                    throw new zzhc("ContentEncodingOrder " + v1 + " not supported");
                }
                break;
            }
            case 20530: {
                if(v1 != 1L) {
                    throw new zzhc("ContentEncodingScope " + v1 + " not supported");
                }
                break;
            }
            case 21420: {
                this.zzaos = v1 + this.zzaom;
                return;
            }
            case 0x53B8: {
                switch(((int)v1)) {
                    case 3: {
                        this.zzaop.zzafu = 1;
                        return;
                    }
                    case 15: {
                        this.zzaop.zzafu = 3;
                        return;
                    }
                    default: {
                        switch(((int)v1)) {
                            case 0: {
                                this.zzaop.zzafu = 0;
                                return;
                            }
                            case 1: {
                                this.zzaop.zzafu = 2;
                                return;
                            }
                            default: {
                                return;
                            }
                        }
                    }
                }
            }
            case 0x54B0: {
                this.zzaop.zzaqb = (int)v1;
                return;
            }
            case 0x54B2: {
                this.zzaop.zzaqd = (int)v1;
                return;
            }
            case 21690: {
                this.zzaop.zzaqc = (int)v1;
                return;
            }
            case 21930: {
                zzjx zzjx1 = this.zzaop;
                if(v1 == 1L) {
                    z = true;
                }
                zzjx1.zzaqy = z;
                return;
            }
            case 0x55B9: {
                switch(((int)v1)) {
                    case 1: {
                        this.zzaop.zzaqh = 2;
                        return;
                    }
                    case 2: {
                        this.zzaop.zzaqh = 1;
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case 0x55BA: {
                switch(((int)v1)) {
                    case 1: {
                        break;
                    }
                    case 16: {
                        this.zzaop.zzaqg = 6;
                        return;
                    }
                    case 18: {
                        this.zzaop.zzaqg = 7;
                        return;
                    label_62:
                        if(((int)v1) != 6 && ((int)v1) != 7) {
                            return;
                        }
                        break;
                    }
                    default: {
                        goto label_62;
                    }
                }
                this.zzaop.zzaqg = 3;
                return;
            }
            case 0x55BB: {
                zzjx zzjx2 = this.zzaop;
                zzjx2.zzaqe = true;
                switch(((int)v1)) {
                    case 1: {
                        zzjx2.zzaqf = 1;
                        return;
                    }
                    case 9: {
                        zzjx2.zzaqf = 6;
                        return;
                    }
                    default: {
                        if(((int)v1) != 4 && ((int)v1) != 5 && ((int)v1) != 6 && ((int)v1) != 7) {
                            return;
                        }
                        zzjx2.zzaqf = 2;
                        return;
                    }
                }
            }
            case 0x55BC: {
                this.zzaop.zzaqi = (int)v1;
                return;
            }
            case 0x55BD: {
                this.zzaop.zzaqj = (int)v1;
                return;
            }
            case 0x56AA: {
                this.zzaop.zzaqv = v1;
                return;
            }
            case 22203: {
                this.zzaop.zzaqw = v1;
                return;
            }
            case 0x6264: {
                this.zzaop.zzaqu = (int)v1;
                return;
            }
            case 2352003: {
                this.zzaop.zzapw = (int)v1;
                return;
            }
            case 2807729: {
                this.zzaon = v1;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzjg
    public final void zzc(long v, long v1) {
        this.zzaow = 0x8000000000000001L;
        this.zzapa = 0;
        this.zzany.reset();
        this.zzanm.reset();
        this.zzgr();
    }

    final void zzd(int v, long v1, long v2) throws zzhc {
        switch(v) {
            case 0xA0: {
                this.zzapt = false;
                return;
            }
            case 0xAE: {
                this.zzaop = new zzjx(null);
                return;
            }
            case 0xBB: {
                this.zzaoz = false;
                return;
            }
            case 0x4DBB: {
                this.zzaor = -1;
                this.zzaos = -1L;
                return;
            }
            case 0x5035: {
                this.zzaop.zzapx = true;
                return;
            }
            case 0x55D0: {
                this.zzaop.zzaqe = true;
                break;
            }
            case 0x6240: {
                return;
            }
            case 0x18538067: {
                if(this.zzaom != -1L && this.zzaom != v1) {
                    throw new zzhc("Multiple Segment elements not supported");
                }
                this.zzaom = v1;
                this.zzaol = v2;
                return;
            }
            case 0x1C53BB6B: {
                this.zzaox = new zzog();
                this.zzaoy = new zzog();
                return;
            }
            case 0x1F43B675: {
                if(!this.zzaoq) {
                    if(this.zzaoa && this.zzaou != -1L) {
                        this.zzaot = true;
                        return;
                    }
                    this.zzapu.zza(new zzjo(this.zzagy));
                    this.zzaoq = true;
                    return;
                }
                break;
            }
        }
    }

    private final long zzea(long v) throws zzhc {
        long v1 = this.zzaon;
        if(v1 == 0x8000000000000001L) {
            throw new zzhc("Can\'t scale timecode prior to timecodeScale being set.");
        }
        return zzop.zza(v, v1, 1000L);
    }

    private final void zzgr() {
        this.zzapj = 0;
        this.zzapr = 0;
        this.zzapq = 0;
        this.zzapk = false;
        this.zzapl = false;
        this.zzapn = false;
        this.zzapp = 0;
        this.zzapo = 0;
        this.zzapm = false;
        this.zzaog.reset();
    }

    static UUID zzgs() {
        return zzjw.zzanx;
    }
}

