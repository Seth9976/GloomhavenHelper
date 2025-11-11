package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;

final class zzei implements zzhd {
    private final zzeh zza;
    private int zzb;
    private int zzc;
    private int zzd;

    private zzei(zzeh zzeh0) {
        this.zzd = 0;
        this.zza = (zzeh)zzfe.zza(zzeh0, "input");
        this.zza.zzc = this;
    }

    public static zzei zza(zzeh zzeh0) {
        return zzeh0.zzc == null ? new zzei(zzeh0) : zzeh0.zzc;
    }

    private final Object zza(zzil zzil0, Class class0, zzeq zzeq0) throws IOException {
        switch(zzel.zza[zzil0.ordinal()]) {
            case 1: {
                return Boolean.valueOf(this.zzk());
            }
            case 2: {
                return this.zzn();
            }
            case 3: {
                return this.zzd();
            }
            case 4: {
                return this.zzp();
            }
            case 5: {
                return this.zzj();
            }
            case 6: {
                return this.zzi();
            }
            case 7: {
                return this.zze();
            }
            case 8: {
                return this.zzh();
            }
            case 9: {
                return this.zzg();
            }
            case 10: {
                this.zza(2);
                return this.zzc(zzgy.zza().zza(class0), zzeq0);
            }
            case 11: {
                return this.zzq();
            }
            case 12: {
                return this.zzr();
            }
            case 13: {
                return this.zzs();
            }
            case 14: {
                return this.zzt();
            }
            case 15: {
                return this.zzm();
            }
            case 16: {
                return this.zzo();
            }
            case 17: {
                return this.zzf();
            }
            default: {
                throw new RuntimeException("unsupported field type.");
            }
        }
    }

    private final void zza(int v) throws IOException {
        if((this.zzb & 7) != v) {
            throw zzfn.zzf();
        }
    }

    private final void zza(List list0, boolean z) throws IOException {
        int v1;
        int v;
        if((this.zzb & 7) != 2) {
            throw zzfn.zzf();
        }
        if(list0 instanceof zzfu && !z) {
            do {
                ((zzfu)list0).zza(this.zzn());
                if(this.zza.zzt()) {
                    return;
                }
                v = this.zza.zza();
            }
            while(v == this.zzb);
            this.zzd = v;
            return;
        }
        do {
            list0.add((z ? this.zzm() : this.zzl()));
            if(this.zza.zzt()) {
                return;
            }
            v1 = this.zza.zza();
        }
        while(v1 == this.zzb);
        this.zzd = v1;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zza() throws IOException {
        int v = this.zzd;
        if(v != 0) {
            this.zzb = v;
            this.zzd = 0;
            return this.zzb == 0 || this.zzb == this.zzc ? 0x7FFFFFFF : this.zzb >>> 3;
        }
        this.zzb = this.zza.zza();
        return this.zzb == 0 || this.zzb == this.zzc ? 0x7FFFFFFF : this.zzb >>> 3;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final Object zza(zzhc zzhc0, zzeq zzeq0) throws IOException {
        this.zza(2);
        return this.zzc(zzhc0, zzeq0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zza(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzep) {
            switch(this.zzb & 7) {
                case 1: {
                    do {
                        ((zzep)list0).zza(this.zza.zzb());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    zzei.zzb(v1);
                    int v2 = this.zza.zzu();
                    do {
                        ((zzep)list0).zza(this.zza.zzb());
                    }
                    while(this.zza.zzu() < v2 + v1);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 1: {
                do {
                    list0.add(this.zza.zzb());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                zzei.zzb(v4);
                int v5 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzb());
                }
                while(this.zza.zzu() < v5 + v4);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zza(List list0, zzhc zzhc0, zzeq zzeq0) throws IOException {
        int v = this.zzb;
        if((v & 7) != 2) {
            throw zzfn.zzf();
        }
        while(true) {
            list0.add(this.zzc(zzhc0, zzeq0));
            if(this.zza.zzt() || this.zzd != 0) {
                break;
            }
            int v1 = this.zza.zza();
            if(v1 != v) {
                this.zzd = v1;
                return;
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zza(Map map0, zzge zzge0, zzeq zzeq0) throws IOException {
        this.zza(2);
        int v = this.zza.zzm();
        int v1 = this.zza.zzc(v);
        Object object0 = zzge0.zzb;
        Object object1 = zzge0.zzd;
        try {
        alab1:
            while(true) {
                while(true) {
                    int v3 = this.zza();
                    if(v3 == 0x7FFFFFFF || this.zza.zzt()) {
                        break alab1;
                    }
                    try {
                        switch(v3) {
                            case 1: {
                                object0 = this.zza(zzge0.zza, null, null);
                                continue;
                            }
                            case 2: {
                                goto label_14;
                            }
                            default: {
                                if(this.zzc()) {
                                    continue;
                                }
                            }
                        }
                        throw new zzfn("Unable to parse map entry.");
                    label_14:
                        Class class0 = zzge0.zzd.getClass();
                        object1 = this.zza(zzge0.zzc, class0, zzeq0);
                        continue;
                    }
                    catch(zzfm unused_ex) {
                    }
                    break;
                }
                if(!this.zzc()) {
                    throw new zzfn("Unable to parse map entry.");
                }
            }
            map0.put(object0, object1);
        }
        finally {
            this.zza.zzd(v1);
        }
    }

    private static void zzb(int v) throws IOException {
        if((v & 7) != 0) {
            throw zzfn.zzg();
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzb() {
        return this.zzb;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final Object zzb(zzhc zzhc0, zzeq zzeq0) throws IOException {
        this.zza(3);
        return this.zzd(zzhc0, zzeq0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzb(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzez) {
            switch(this.zzb & 7) {
                case 2: {
                    int v = this.zza.zzm();
                    zzei.zzc(v);
                    int v1 = this.zza.zzu();
                    do {
                        ((zzez)list0).zza(this.zza.zzc());
                    }
                    while(this.zza.zzu() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
            do {
                ((zzez)list0).zza(this.zza.zzc());
                if(this.zza.zzt()) {
                    return;
                }
                v2 = this.zza.zza();
            }
            while(v2 == this.zzb);
            this.zzd = v2;
            return;
        }
        switch(this.zzb & 7) {
            case 2: {
                int v3 = this.zza.zzm();
                zzei.zzc(v3);
                int v4 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzc());
                }
                while(this.zza.zzu() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzfn.zzf();
            }
        }
        do {
            list0.add(this.zza.zzc());
            if(this.zza.zzt()) {
                return;
            }
            v5 = this.zza.zza();
        }
        while(v5 == this.zzb);
        this.zzd = v5;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzb(List list0, zzhc zzhc0, zzeq zzeq0) throws IOException {
        int v = this.zzb;
        if((v & 7) != 3) {
            throw zzfn.zzf();
        }
        while(true) {
            list0.add(this.zzd(zzhc0, zzeq0));
            if(this.zza.zzt() || this.zzd != 0) {
                break;
            }
            int v1 = this.zza.zza();
            if(v1 != v) {
                this.zzd = v1;
                return;
            }
        }
    }

    private final Object zzc(zzhc zzhc0, zzeq zzeq0) throws IOException {
        int v = this.zza.zzm();
        if(this.zza.zza >= this.zza.zzb) {
            throw new zzfn("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int v1 = this.zza.zzc(v);
        Object object0 = zzhc0.zza();
        ++this.zza.zza;
        zzhc0.zza(object0, this, zzeq0);
        zzhc0.zzc(object0);
        this.zza.zza(0);
        --this.zza.zza;
        this.zza.zzd(v1);
        return object0;
    }

    private static void zzc(int v) throws IOException {
        if((v & 3) != 0) {
            throw zzfn.zzg();
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzc(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzgb) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzgb)list0).zza(this.zza.zzd());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzgb)list0).zza(this.zza.zzd());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzd());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzd());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final boolean zzc() throws IOException {
        if(!this.zza.zzt()) {
            return this.zzb == this.zzc ? false : this.zza.zzb(this.zzb);
        }
        return false;
    }

    private final Object zzd(zzhc zzhc0, zzeq zzeq0) throws IOException {
        int v1;
        try {
            v1 = this.zzc;
            this.zzc = this.zzb >>> 3 << 3 | 4;
            Object object0 = zzhc0.zza();
            zzhc0.zza(object0, this, zzeq0);
            zzhc0.zzc(object0);
            if(this.zzb == this.zzc) {
                return object0;
            }
        }
        finally {
            this.zzc = v1;
        }
        throw zzfn.zzg();
    }

    private final void zzd(int v) throws IOException {
        if(this.zza.zzu() != v) {
            throw zzfn.zza();
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final double zzd() throws IOException {
        this.zza(1);
        return this.zza.zzb();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzd(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzgb) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzgb)list0).zza(this.zza.zze());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzgb)list0).zza(this.zza.zze());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zze());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zze());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final float zze() throws IOException {
        this.zza(5);
        return this.zza.zzc();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zze(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzff)list0).zzd(this.zza.zzf());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzff)list0).zzd(this.zza.zzf());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzf());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzf());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final long zzf() throws IOException {
        this.zza(0);
        return this.zza.zzd();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzf(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzgb) {
            switch(this.zzb & 7) {
                case 1: {
                    do {
                        ((zzgb)list0).zza(this.zza.zzg());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    zzei.zzb(v1);
                    int v2 = this.zza.zzu();
                    do {
                        ((zzgb)list0).zza(this.zza.zzg());
                    }
                    while(this.zza.zzu() < v2 + v1);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 1: {
                do {
                    list0.add(this.zza.zzg());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                zzei.zzb(v4);
                int v5 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzg());
                }
                while(this.zza.zzu() < v5 + v4);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final long zzg() throws IOException {
        this.zza(0);
        return this.zza.zze();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzg(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 2: {
                    int v = this.zza.zzm();
                    zzei.zzc(v);
                    int v1 = this.zza.zzu();
                    do {
                        ((zzff)list0).zzd(this.zza.zzh());
                    }
                    while(this.zza.zzu() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
            do {
                ((zzff)list0).zzd(this.zza.zzh());
                if(this.zza.zzt()) {
                    return;
                }
                v2 = this.zza.zza();
            }
            while(v2 == this.zzb);
            this.zzd = v2;
            return;
        }
        switch(this.zzb & 7) {
            case 2: {
                int v3 = this.zza.zzm();
                zzei.zzc(v3);
                int v4 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzh());
                }
                while(this.zza.zzu() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzfn.zzf();
            }
        }
        do {
            list0.add(this.zza.zzh());
            if(this.zza.zzt()) {
                return;
            }
            v5 = this.zza.zza();
        }
        while(v5 == this.zzb);
        this.zzd = v5;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzh() throws IOException {
        this.zza(0);
        return this.zza.zzf();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzh(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzdt) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzdt)list0).zza(this.zza.zzi());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzdt)list0).zza(this.zza.zzi());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(Boolean.valueOf(this.zza.zzi()));
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(Boolean.valueOf(this.zza.zzi()));
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final long zzi() throws IOException {
        this.zza(1);
        return this.zza.zzg();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzi(List list0) throws IOException {
        this.zza(list0, false);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzj() throws IOException {
        this.zza(5);
        return this.zza.zzh();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzj(List list0) throws IOException {
        this.zza(list0, true);
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzk(List list0) throws IOException {
        int v;
        if((this.zzb & 7) != 2) {
            throw zzfn.zzf();
        }
        do {
            list0.add(this.zzn());
            if(this.zza.zzt()) {
                return;
            }
            v = this.zza.zza();
        }
        while(v == this.zzb);
        this.zzd = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final boolean zzk() throws IOException {
        this.zza(0);
        return this.zza.zzi();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final String zzl() throws IOException {
        this.zza(2);
        return this.zza.zzj();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzl(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzff)list0).zzd(this.zza.zzm());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzff)list0).zzd(this.zza.zzm());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzm());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzm());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final String zzm() throws IOException {
        this.zza(2);
        return this.zza.zzk();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzm(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzff)list0).zzd(this.zza.zzn());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzff)list0).zzd(this.zza.zzn());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzn());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzn());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final zzdv zzn() throws IOException {
        this.zza(2);
        return this.zza.zzl();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzn(List list0) throws IOException {
        int v5;
        int v2;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 2: {
                    int v = this.zza.zzm();
                    zzei.zzc(v);
                    int v1 = this.zza.zzu();
                    do {
                        ((zzff)list0).zzd(this.zza.zzo());
                    }
                    while(this.zza.zzu() < v1 + v);
                    return;
                }
                case 5: {
                    break;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
            do {
                ((zzff)list0).zzd(this.zza.zzo());
                if(this.zza.zzt()) {
                    return;
                }
                v2 = this.zza.zza();
            }
            while(v2 == this.zzb);
            this.zzd = v2;
            return;
        }
        switch(this.zzb & 7) {
            case 2: {
                int v3 = this.zza.zzm();
                zzei.zzc(v3);
                int v4 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzo());
                }
                while(this.zza.zzu() < v4 + v3);
                return;
            }
            case 5: {
                break;
            }
            default: {
                throw zzfn.zzf();
            }
        }
        do {
            list0.add(this.zza.zzo());
            if(this.zza.zzt()) {
                return;
            }
            v5 = this.zza.zza();
        }
        while(v5 == this.zzb);
        this.zzd = v5;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzo() throws IOException {
        this.zza(0);
        return this.zza.zzm();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzo(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzgb) {
            switch(this.zzb & 7) {
                case 1: {
                    do {
                        ((zzgb)list0).zza(this.zza.zzp());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    zzei.zzb(v1);
                    int v2 = this.zza.zzu();
                    do {
                        ((zzgb)list0).zza(this.zza.zzp());
                    }
                    while(this.zza.zzu() < v2 + v1);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 1: {
                do {
                    list0.add(this.zza.zzp());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                zzei.zzb(v4);
                int v5 = this.zza.zzu();
                do {
                    list0.add(this.zza.zzp());
                }
                while(this.zza.zzu() < v5 + v4);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzp() throws IOException {
        this.zza(0);
        return this.zza.zzn();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzp(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzff) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzff)list0).zzd(this.zza.zzq());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzff)list0).zzd(this.zza.zzq());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzq());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzq());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzq() throws IOException {
        this.zza(5);
        return this.zza.zzo();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final void zzq(List list0) throws IOException {
        int v3;
        int v;
        if(list0 instanceof zzgb) {
            switch(this.zzb & 7) {
                case 0: {
                    do {
                        ((zzgb)list0).zza(this.zza.zzr());
                        if(this.zza.zzt()) {
                            return;
                        }
                        v = this.zza.zza();
                    }
                    while(v == this.zzb);
                    this.zzd = v;
                    return;
                }
                case 2: {
                    int v1 = this.zza.zzm();
                    int v2 = this.zza.zzu() + v1;
                    do {
                        ((zzgb)list0).zza(this.zza.zzr());
                    }
                    while(this.zza.zzu() < v2);
                    this.zzd(v2);
                    return;
                }
                default: {
                    throw zzfn.zzf();
                }
            }
        }
        switch(this.zzb & 7) {
            case 0: {
                do {
                    list0.add(this.zza.zzr());
                    if(this.zza.zzt()) {
                        return;
                    }
                    v3 = this.zza.zza();
                }
                while(v3 == this.zzb);
                this.zzd = v3;
                return;
            }
            case 2: {
                int v4 = this.zza.zzm();
                int v5 = this.zza.zzu() + v4;
                do {
                    list0.add(this.zza.zzr());
                }
                while(this.zza.zzu() < v5);
                this.zzd(v5);
                return;
            }
            default: {
                throw zzfn.zzf();
            }
        }
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final long zzr() throws IOException {
        this.zza(1);
        return this.zza.zzp();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final int zzs() throws IOException {
        this.zza(0);
        return this.zza.zzq();
    }

    @Override  // com.google.android.gms.internal.measurement.zzhd
    public final long zzt() throws IOException {
        this.zza(0);
        return this.zza.zzr();
    }
}

