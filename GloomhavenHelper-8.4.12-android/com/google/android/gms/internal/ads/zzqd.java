package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzqd {
    private final Object lock;
    private int score;
    private final int zzbos;
    private final int zzbot;
    private final int zzbou;
    private final boolean zzbov;
    private final zzqs zzbow;
    private final zzqz zzbox;
    private ArrayList zzboy;
    private ArrayList zzboz;
    private ArrayList zzbpa;
    private int zzbpb;
    private int zzbpc;
    private int zzbpd;
    private String zzbpe;
    private String zzbpf;
    private String zzbpg;

    public zzqd(int v, int v1, int v2, int v3, int v4, int v5, int v6, boolean z) {
        this.lock = new Object();
        this.zzboy = new ArrayList();
        this.zzboz = new ArrayList();
        this.zzbpa = new ArrayList();
        this.zzbpb = 0;
        this.zzbpc = 0;
        this.zzbpd = 0;
        this.zzbpe = "";
        this.zzbpf = "";
        this.zzbpg = "";
        this.zzbos = v;
        this.zzbot = v1;
        this.zzbou = v2;
        this.zzbov = z;
        this.zzbow = new zzqs(v3);
        this.zzbox = new zzqz(v4, v5, v6);
    }

    @Override
    public final boolean equals(Object object0) {
        if(!(object0 instanceof zzqd)) {
            return false;
        }
        if(object0 == this) {
            return true;
        }
        String s = ((zzqd)object0).zzbpe;
        return s != null && s.equals(this.zzbpe);
    }

    public final int getScore() {
        return this.score;
    }

    @Override
    public final int hashCode() {
        return this.zzbpe.hashCode();
    }

    @Override
    public final String toString() {
        return "ActivityContent fetchId: " + this.zzbpc + " score:" + this.score + " total_length:" + this.zzbpb + "\n text: " + zzqd.zza(this.zzboy, 100) + "\n viewableText" + zzqd.zza(this.zzboz, 100) + "\n signture: " + this.zzbpe + "\n viewableSignture: " + this.zzbpf + "\n viewableSignatureForVertical: " + this.zzbpg;
    }

    private static String zza(ArrayList arrayList0, int v) {
        if(arrayList0.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        int v1 = arrayList0.size();
        int v2 = 0;
        while(v2 < v1) {
            Object object0 = arrayList0.get(v2);
            ++v2;
            stringBuilder0.append(((String)object0));
            stringBuilder0.append(' ');
            if(stringBuilder0.length() > 100) {
                break;
            }
        }
        stringBuilder0.deleteCharAt(stringBuilder0.length() - 1);
        String s = stringBuilder0.toString();
        return s.length() >= 100 ? s.substring(0, 100) : s;
    }

    public final void zza(String s, boolean z, float f, float f1, float f2, float f3) {
        this.zzc(s, z, f, f1, f2, f3);
        synchronized(this.lock) {
            if(this.zzbpd < 0) {
                zzawf.zzeb("ActivityContent: negative number of WebViews.");
            }
            this.zzme();
        }
    }

    public final void zzb(String s, boolean z, float f, float f1, float f2, float f3) {
        this.zzc(s, z, f, f1, f2, f3);
    }

    public final void zzbp(int v) {
        this.zzbpc = v;
    }

    private final void zzc(@Nullable String s, boolean z, float f, float f1, float f2, float f3) {
        if(s != null && s.length() >= this.zzbou) {
            Object object0 = this.lock;
            synchronized(object0) {
                this.zzboy.add(s);
                this.zzbpb += s.length();
                if(z) {
                    this.zzboz.add(s);
                    this.zzbpa.add(new zzqq(f, f1, f2, f3, this.zzboz.size() - 1));
                }
            }
        }
    }

    // 去混淆评级： 低(20)
    @VisibleForTesting
    private final int zzh(int v, int v1) {
        return this.zzbov ? this.zzbot : v * this.zzbos + v1 * this.zzbot;
    }

    public final boolean zzlw() {
        synchronized(this.lock) {
        }
        return this.zzbpd == 0;
    }

    public final String zzlx() {
        return this.zzbpe;
    }

    public final String zzly() {
        return this.zzbpf;
    }

    public final String zzlz() {
        return this.zzbpg;
    }

    public final void zzma() {
        synchronized(this.lock) {
            this.score -= 100;
        }
    }

    public final void zzmb() {
        synchronized(this.lock) {
            --this.zzbpd;
        }
    }

    public final void zzmc() {
        synchronized(this.lock) {
            ++this.zzbpd;
        }
    }

    public final void zzmd() {
        synchronized(this.lock) {
            int v1 = this.zzh(this.zzbpb, this.zzbpc);
            if(v1 > this.score) {
                this.score = v1;
            }
        }
    }

    public final void zzme() {
        synchronized(this.lock) {
            int v1 = this.zzh(this.zzbpb, this.zzbpc);
            if(v1 > this.score) {
                this.score = v1;
                if(!zzq.zzkz().zzvk().zzvz()) {
                    this.zzbpe = this.zzbow.zza(this.zzboy);
                    this.zzbpf = this.zzbow.zza(this.zzboz);
                }
                if(!zzq.zzkz().zzvk().zzwb()) {
                    this.zzbpg = this.zzbox.zza(this.zzboz, this.zzbpa);
                }
            }
        }
    }

    @VisibleForTesting
    final int zzmf() {
        return this.zzbpb;
    }
}

