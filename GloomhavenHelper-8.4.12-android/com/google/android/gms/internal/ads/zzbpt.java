package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class zzbpt {
    public static final class zza {
        private zzdeu zzfir;
        private Bundle zzfjz;
        @Nullable
        private String zzfka;
        @Nullable
        private zzdep zzfkb;
        private Context zzur;

        public final zza zza(zzdep zzdep0) {
            this.zzfkb = zzdep0;
            return this;
        }

        public final zza zza(zzdeu zzdeu0) {
            this.zzfir = zzdeu0;
            return this;
        }

        public final zzbpt zzahz() {
            return new zzbpt(this, null);
        }

        public final zza zzcc(Context context0) {
            this.zzur = context0;
            return this;
        }

        public final zza zze(Bundle bundle0) {
            this.zzfjz = bundle0;
            return this;
        }

        public final zza zzft(String s) {
            this.zzfka = s;
            return this;
        }
    }

    private final zzdeu zzfir;
    private Bundle zzfjz;
    @Nullable
    private final String zzfka;
    @Nullable
    private final zzdep zzfkb;
    private final Context zzur;

    private zzbpt(zza zzbpt$zza0) {
        this.zzur = zzbpt$zza0.zzur;
        this.zzfir = zzbpt$zza0.zzfir;
        this.zzfjz = zzbpt$zza0.zzfjz;
        this.zzfka = zzbpt$zza0.zzfka;
        this.zzfkb = zzbpt$zza0.zzfkb;
    }

    zzbpt(zza zzbpt$zza0, zzbps zzbps0) {
        this(zzbpt$zza0);
    }

    final zza zzahu() {
        return new zza().zzcc(this.zzur).zza(this.zzfir).zzft(this.zzfka).zze(this.zzfjz);
    }

    final zzdeu zzahv() {
        return this.zzfir;
    }

    @Nullable
    final zzdep zzahw() {
        return this.zzfkb;
    }

    @Nullable
    final Bundle zzahx() {
        return this.zzfjz;
    }

    @Nullable
    final String zzahy() {
        return this.zzfka;
    }

    final Context zzcb(Context context0) {
        return this.zzfka == null ? this.zzur : context0;
    }
}

