package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzdyg implements zzeax {
    private int tag;
    private final zzdxz zzhpa;
    private int zzhpb;
    private int zzhpc;

    private zzdyg(zzdxz zzdxz0) {
        this.zzhpc = 0;
        this.zzhpa = (zzdxz)zzdzc.zza(zzdxz0, "input");
        this.zzhpa.zzhop = this;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int getTag() {
        return this.tag;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final double readDouble() throws IOException {
        this.zzfr(1);
        return this.zzhpa.readDouble();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final float readFloat() throws IOException {
        this.zzfr(5);
        return this.zzhpa.readFloat();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final String readString() throws IOException {
        this.zzfr(2);
        return this.zzhpa.readString();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void readStringList(List list0) throws IOException {
        this.zza(list0, false);
    }

    public static zzdyg zza(zzdxz zzdxz0) {
        return zzdxz0.zzhop == null ? new zzdyg(zzdxz0) : zzdxz0.zzhop;
    }

    private final Object zza(zzecm zzecm0, Class class0, zzdym zzdym0) throws IOException {
        switch(zzdyf.zzhoz[zzecm0.ordinal()]) {
            case 1: {
                return Boolean.valueOf(this.zzbbh());
            }
            case 2: {
                return this.zzbbj();
            }
            case 3: {
                return this.readDouble();
            }
            case 4: {
                return this.zzbbl();
            }
            case 5: {
                return this.zzbbg();
            }
            case 6: {
                return this.zzbbf();
            }
            case 7: {
                return this.readFloat();
            }
            case 8: {
                return this.zzbbe();
            }
            case 9: {
                return this.zzbbd();
            }
            case 10: {
                this.zzfr(2);
                return this.zzc(zzeaw.zzbem().zzh(class0), zzdym0);
            }
            case 11: {
                return this.zzbbm();
            }
            case 12: {
                return this.zzbbn();
            }
            case 13: {
                return this.zzbbo();
            }
            case 14: {
                return this.zzbbp();
            }
            case 15: {
                return this.zzbbi();
            }
            case 16: {
                return this.zzbbk();
            }
            case 17: {
                return this.zzbbc();
            }
            default: {
                throw new RuntimeException("unsupported field type.");
            }
        }
    }

    private final void zza(List list0, boolean z) throws IOException {
        int v1;
        int v;
        if((this.tag & 7) != 2) {
            throw zzdzh.zzbdn();
        }
        if(list0 instanceof zzdzs && !z) {
            do {
                ((zzdzs)list0).zzaj(this.zzbbj());
                if(this.zzhpa.zzbbr()) {
                    return;
                }
                v = this.zzhpa.zzbbb();
            }
            while(v == this.tag);
            this.zzhpc = v;
            return;
        }
        do {
            list0.add((z ? this.zzbbi() : this.readString()));
            if(this.zzhpa.zzbbr()) {
                return;
            }
            v1 = this.zzhpa.zzbbb();
        }
        while(v1 == this.tag);
        this.zzhpc = v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final Object zza(zzebd zzebd0, zzdym zzdym0) throws IOException {
        this.zzfr(2);
        return this.zzc(zzebd0, zzdym0);
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zza(List list0, zzebd zzebd0, zzdym zzdym0) throws IOException {
        int v = this.tag;
        if((v & 7) != 2) {
            throw zzdzh.zzbdn();
        }
        while(true) {
            list0.add(this.zzc(zzebd0, zzdym0));
            if(this.zzhpa.zzbbr() || this.zzhpc != 0) {
                break;
            }
            int v1 = this.zzhpa.zzbbb();
            if(v1 != v) {
                this.zzhpc = v1;
                return;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zza(Map map0, zzeac zzeac0, zzdym zzdym0) throws IOException {
        this.zzfr(2);
        int v = this.zzhpa.zzbbk();
        int v1 = this.zzhpa.zzfj(v);
        Object object0 = zzeac0.zzhva;
        Object object1 = zzeac0.zzcgl;
        try {
        alab1:
            while(true) {
                while(true) {
                    int v3 = this.zzbbz();
                    if(v3 == 0x7FFFFFFF || this.zzhpa.zzbbr()) {
                        break alab1;
                    }
                    try {
                        switch(v3) {
                            case 1: {
                                object0 = this.zza(zzeac0.zzhuz, null, null);
                                continue;
                            }
                            case 2: {
                                goto label_14;
                            }
                            default: {
                                if(this.zzbca()) {
                                    continue;
                                }
                            }
                        }
                        throw new zzdzh("Unable to parse map entry.");
                    label_14:
                        Class class0 = zzeac0.zzcgl.getClass();
                        object1 = this.zza(zzeac0.zzhvb, class0, zzdym0);
                        continue;
                    }
                    catch(zzdzk unused_ex) {
                    }
                    break;
                }
                if(!this.zzbca()) {
                    throw new zzdzh("Unable to parse map entry.");
                }
            }
            map0.put(object0, object1);
        }
        finally {
            this.zzhpa.zzfk(v1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final Object zzb(zzebd zzebd0, zzdym zzdym0) throws IOException {
        this.zzfr(3);
        return this.zzd(zzebd0, zzdym0);
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzb(List list0, zzebd zzebd0, zzdym zzdym0) throws IOException {
        int v = this.tag;
        if((v & 7) != 3) {
            throw zzdzh.zzbdn();
        }
        while(true) {
            list0.add(this.zzd(zzebd0, zzdym0));
            if(this.zzhpa.zzbbr() || this.zzhpc != 0) {
                break;
            }
            int v1 = this.zzhpa.zzbbb();
            if(v1 != v) {
                this.zzhpc = v1;
                return;
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final long zzbbc() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbc();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final long zzbbd() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbd();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbe() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbe();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final long zzbbf() throws IOException {
        this.zzfr(1);
        return this.zzhpa.zzbbf();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbg() throws IOException {
        this.zzfr(5);
        return this.zzhpa.zzbbg();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final boolean zzbbh() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbh();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final String zzbbi() throws IOException {
        this.zzfr(2);
        return this.zzhpa.zzbbi();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final zzdxn zzbbj() throws IOException {
        this.zzfr(2);
        return this.zzhpa.zzbbj();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbk() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbk();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbl() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbl();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbm() throws IOException {
        this.zzfr(5);
        return this.zzhpa.zzbbm();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final long zzbbn() throws IOException {
        this.zzfr(1);
        return this.zzhpa.zzbbn();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbo() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbo();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final long zzbbp() throws IOException {
        this.zzfr(0);
        return this.zzhpa.zzbbp();
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final int zzbbz() throws IOException {
        int v = this.zzhpc;
        if(v != 0) {
            this.tag = v;
            this.zzhpc = 0;
            return this.tag == 0 || this.tag == this.zzhpb ? 0x7FFFFFFF : this.tag >>> 3;
        }
        this.tag = this.zzhpa.zzbbb();
        return this.tag == 0 || this.tag == this.zzhpb ? 0x7FFFFFFF : this.tag >>> 3;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final boolean zzbca() throws IOException {
        if(!this.zzhpa.zzbbr()) {
            return this.tag == this.zzhpb ? false : this.zzhpa.zzfi(this.tag);
        }
        return false;
    }

    private final Object zzc(zzebd zzebd0, zzdym zzdym0) throws IOException {
        int v = this.zzhpa.zzbbk();
        if(this.zzhpa.zzhom >= this.zzhpa.zzhon) {
            throw new zzdzh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int v1 = this.zzhpa.zzfj(v);
        Object object0 = zzebd0.newInstance();
        ++this.zzhpa.zzhom;
        zzebd0.zza(object0, this, zzdym0);
        zzebd0.zzan(object0);
        this.zzhpa.zzfh(0);
        --this.zzhpa.zzhom;
        this.zzhpa.zzfk(v1);
        return object0;
    }

    private final Object zzd(zzebd zzebd0, zzdym zzdym0) throws IOException {
        int v1;
        try {
            v1 = this.zzhpb;
            this.zzhpb = this.tag >>> 3 << 3 | 4;
            Object object0 = zzebd0.newInstance();
            zzebd0.zza(object0, this, zzdym0);
            zzebd0.zzan(object0);
            if(this.tag == this.zzhpb) {
                return object0;
            }
        }
        finally {
            this.zzhpb = v1;
        }
        throw zzdzh.zzbdp();
    }

    private final void zzfr(int v) throws IOException {
        if((this.tag & 7) != v) {
            throw zzdzh.zzbdn();
        }
    }

    private static void zzfs(int v) throws IOException {
        if((v & 7) != 0) {
            throw zzdzh.zzbdp();
        }
    }

    private static void zzft(int v) throws IOException {
        if((v & 3) != 0) {
            throw zzdzh.zzbdp();
        }
    }

    private final void zzfu(int v) throws IOException {
        if(this.zzhpa.zzbbs() != v) {
            throw zzdzh.zzbdi();
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzj(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdyl) {
            switch(this.tag & 7) {
                case 1: {
                    do {
                        ((zzdyl)list0).zzd(this.zzhpa.readDouble());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    zzdyg.zzfs(v1);
                    int v2 = this.zzhpa.zzbbs();
                    do {
                        ((zzdyl)list0).zzd(this.zzhpa.readDouble());
                    }
                    while(this.zzhpa.zzbbs() < v2 + v1);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 1: {
                do {
                    list0.add(this.zzhpa.readDouble());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                zzdyg.zzfs(v4);
                int v5 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.readDouble());
                }
                while(this.zzhpa.zzbbs() < v5 + v4);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzk(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzdyv) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.zzhpa.zzbbk();
                    zzdyg.zzft(v);
                    int v1 = this.zzhpa.zzbbs();
                    do {
                        ((zzdyv)list0).zzh(this.zzhpa.readFloat());
                    }
                    while(this.zzhpa.zzbbs() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
            do {
                ((zzdyv)list0).zzh(this.zzhpa.readFloat());
                if(this.zzhpa.zzbbr()) {
                    return;
                }
                v2 = this.zzhpa.zzbbb();
            }
            while(v2 == this.tag);
            this.zzhpc = v2;
            return;
        }
        switch(this.tag & 7) {
            case 2: {
                int v3 = this.zzhpa.zzbbk();
                zzdyg.zzft(v3);
                int v4 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.readFloat());
                }
                while(this.zzhpa.zzbbs() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
        do {
            list0.add(this.zzhpa.readFloat());
            if(this.zzhpa.zzbbr()) {
                return;
            }
            v5 = this.zzhpa.zzbbb();
        }
        while(v5 == this.tag);
        this.zzhpc = v5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzl(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdzv) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbc());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbc());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbc());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbc());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzm(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdzv) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbd());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbd());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbd());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbd());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzn(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbe());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbe());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbe());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbe());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzo(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdzv) {
            switch(this.tag & 7) {
                case 1: {
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbf());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    zzdyg.zzfs(v1);
                    int v2 = this.zzhpa.zzbbs();
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbf());
                    }
                    while(this.zzhpa.zzbbs() < v2 + v1);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 1: {
                do {
                    list0.add(this.zzhpa.zzbbf());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                zzdyg.zzfs(v4);
                int v5 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.zzbbf());
                }
                while(this.zzhpa.zzbbs() < v5 + v4);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzp(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.zzhpa.zzbbk();
                    zzdyg.zzft(v);
                    int v1 = this.zzhpa.zzbbs();
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbg());
                    }
                    while(this.zzhpa.zzbbs() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
            do {
                ((zzdza)list0).zzgl(this.zzhpa.zzbbg());
                if(this.zzhpa.zzbbr()) {
                    return;
                }
                v2 = this.zzhpa.zzbbb();
            }
            while(v2 == this.tag);
            this.zzhpc = v2;
            return;
        }
        switch(this.tag & 7) {
            case 2: {
                int v3 = this.zzhpa.zzbbk();
                zzdyg.zzft(v3);
                int v4 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.zzbbg());
                }
                while(this.zzhpa.zzbbs() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
        do {
            list0.add(this.zzhpa.zzbbg());
            if(this.zzhpa.zzbbr()) {
                return;
            }
            v5 = this.zzhpa.zzbbb();
        }
        while(v5 == this.tag);
        this.zzhpc = v5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzq(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdxl) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdxl)list0).addBoolean(this.zzhpa.zzbbh());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdxl)list0).addBoolean(this.zzhpa.zzbbh());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(Boolean.valueOf(this.zzhpa.zzbbh()));
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(Boolean.valueOf(this.zzhpa.zzbbh()));
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzr(List list0) throws IOException {
        this.zza(list0, true);
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzs(List list0) throws IOException {
        int v;
        if((this.tag & 7) != 2) {
            throw zzdzh.zzbdn();
        }
        do {
            list0.add(this.zzbbj());
            if(this.zzhpa.zzbbr()) {
                return;
            }
            v = this.zzhpa.zzbbb();
        }
        while(v == this.tag);
        this.zzhpc = v;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzt(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbk());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbk());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbk());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbk());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzu(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbl());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbl());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbl());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbl());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzv(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 2: {
                    int v = this.zzhpa.zzbbk();
                    zzdyg.zzft(v);
                    int v1 = this.zzhpa.zzbbs();
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbm());
                    }
                    while(this.zzhpa.zzbbs() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
            do {
                ((zzdza)list0).zzgl(this.zzhpa.zzbbm());
                if(this.zzhpa.zzbbr()) {
                    return;
                }
                v2 = this.zzhpa.zzbbb();
            }
            while(v2 == this.tag);
            this.zzhpc = v2;
            return;
        }
        switch(this.tag & 7) {
            case 2: {
                int v3 = this.zzhpa.zzbbk();
                zzdyg.zzft(v3);
                int v4 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.zzbbm());
                }
                while(this.zzhpa.zzbbs() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
        do {
            list0.add(this.zzhpa.zzbbm());
            if(this.zzhpa.zzbbr()) {
                return;
            }
            v5 = this.zzhpa.zzbbb();
        }
        while(v5 == this.tag);
        this.zzhpc = v5;
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzw(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdzv) {
            switch(this.tag & 7) {
                case 1: {
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbn());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    zzdyg.zzfs(v1);
                    int v2 = this.zzhpa.zzbbs();
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbn());
                    }
                    while(this.zzhpa.zzbbs() < v2 + v1);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 1: {
                do {
                    list0.add(this.zzhpa.zzbbn());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                zzdyg.zzfs(v4);
                int v5 = this.zzhpa.zzbbs();
                do {
                    list0.add(this.zzhpa.zzbbn());
                }
                while(this.zzhpa.zzbbs() < v5 + v4);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzx(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdza) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbo());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdza)list0).zzgl(this.zzhpa.zzbbo());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbo());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbo());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeax
    public final void zzy(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdzv) {
            switch(this.tag & 7) {
                case 0: {
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbp());
                        if(this.zzhpa.zzbbr()) {
                            return;
                        }
                        v = this.zzhpa.zzbbb();
                    }
                    while(v == this.tag);
                    this.zzhpc = v;
                    return;
                }
                case 2: {
                    int v1 = this.zzhpa.zzbbk();
                    int v2 = this.zzhpa.zzbbs() + v1;
                    do {
                        ((zzdzv)list0).zzfs(this.zzhpa.zzbbp());
                    }
                    while(this.zzhpa.zzbbs() < v2);
                    this.zzfu(v2);
                    return;
                }
                default: {
                    throw zzdzh.zzbdn();
                }
            }
        }
        switch(this.tag & 7) {
            case 0: {
                do {
                    list0.add(this.zzhpa.zzbbp());
                    if(this.zzhpa.zzbbr()) {
                        return;
                    }
                    v3 = this.zzhpa.zzbbb();
                }
                while(v3 == this.tag);
                this.zzhpc = v3;
                return;
            }
            case 2: {
                int v4 = this.zzhpa.zzbbk();
                int v5 = this.zzhpa.zzbbs() + v4;
                do {
                    list0.add(this.zzhpa.zzbbp());
                }
                while(this.zzhpa.zzbbs() < v5);
                this.zzfu(v5);
                return;
            }
            default: {
                throw zzdzh.zzbdn();
            }
        }
    }
}

