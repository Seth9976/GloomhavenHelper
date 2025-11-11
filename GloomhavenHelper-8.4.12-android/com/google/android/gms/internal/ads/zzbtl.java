package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.ads.reward.AdMetadataListener;
import com.google.android.gms.common.util.Clock;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

public class zzbtl {
    public static final class zza {
        private zzdcl zzfhw;
        private Set zzfkx;
        private Set zzfky;
        private Set zzfkz;
        private Set zzfla;
        private Set zzflb;
        private Set zzflc;
        private Set zzfld;
        private Set zzfle;
        private Set zzflf;
        private Set zzflj;

        public zza() {
            this.zzfkx = new HashSet();
            this.zzfky = new HashSet();
            this.zzfkz = new HashSet();
            this.zzfla = new HashSet();
            this.zzflb = new HashSet();
            this.zzflc = new HashSet();
            this.zzfle = new HashSet();
            this.zzflf = new HashSet();
            this.zzfld = new HashSet();
            this.zzflj = new HashSet();
        }

        public final zza zza(AppEventListener appEventListener0, Executor executor0) {
            this.zzflf.add(new zzbuv(appEventListener0, executor0));
            return this;
        }

        public final zza zza(AdMetadataListener adMetadataListener0, Executor executor0) {
            this.zzfle.add(new zzbuv(adMetadataListener0, executor0));
            return this;
        }

        public final zza zza(zzbqh zzbqh0, Executor executor0) {
            this.zzfky.add(new zzbuv(zzbqh0, executor0));
            return this;
        }

        public final zza zza(zzbqm zzbqm0, Executor executor0) {
            this.zzflc.add(new zzbuv(zzbqm0, executor0));
            return this;
        }

        public final zza zza(zzbqq zzbqq0, Executor executor0) {
            this.zzfld.add(new zzbuv(zzbqq0, executor0));
            return this;
        }

        public final zza zza(zzbqu zzbqu0, Executor executor0) {
            this.zzfkz.add(new zzbuv(zzbqu0, executor0));
            return this;
        }

        public final zza zza(zzbrn zzbrn0, Executor executor0) {
            this.zzflb.add(new zzbuv(zzbrn0, executor0));
            return this;
        }

        public final zza zza(zzbrw zzbrw0, Executor executor0) {
            this.zzfla.add(new zzbuv(zzbrw0, executor0));
            return this;
        }

        public final zza zza(zzbsg zzbsg0, Executor executor0) {
            this.zzflj.add(new zzbuv(zzbsg0, executor0));
            return this;
        }

        public final zza zza(zzdcl zzdcl0) {
            this.zzfhw = zzdcl0;
            return this;
        }

        public final zza zza(zztz zztz0, Executor executor0) {
            this.zzfkx.add(new zzbuv(zztz0, executor0));
            return this;
        }

        public final zza zza(@Nullable zzwf zzwf0, Executor executor0) {
            if(this.zzflf != null) {
                zzcsj zzcsj0 = new zzcsj();
                zzcsj0.zzb(zzwf0);
                this.zzflf.add(new zzbuv(zzcsj0, executor0));
            }
            return this;
        }

        public final zzbtl zzais() {
            return new zzbtl(this, null);
        }
    }

    @Nullable
    private final zzdcl zzfhw;
    private final Set zzfkx;
    private final Set zzfky;
    private final Set zzfkz;
    private final Set zzfla;
    private final Set zzflb;
    private final Set zzflc;
    private final Set zzfld;
    private final Set zzfle;
    private final Set zzflf;
    private final Set zzflg;
    private zzbqk zzflh;
    private zzcpc zzfli;

    private zzbtl(zza zzbtl$zza0) {
        this.zzfkx = zzbtl$zza0.zzfkx;
        this.zzfkz = zzbtl$zza0.zzfkz;
        this.zzfla = zzbtl$zza0.zzfla;
        this.zzfky = zzbtl$zza0.zzfky;
        this.zzflb = zzbtl$zza0.zzflb;
        this.zzflc = zzbtl$zza0.zzflc;
        this.zzfld = zzbtl$zza0.zzfld;
        this.zzfle = zzbtl$zza0.zzfle;
        this.zzflf = zzbtl$zza0.zzflf;
        this.zzflg = zzbtl$zza0.zzflj;
        this.zzfhw = zzbtl$zza0.zzfhw;
    }

    zzbtl(zza zzbtl$zza0, zzbtn zzbtn0) {
        this(zzbtl$zza0);
    }

    public final zzcpc zza(Clock clock0) {
        if(this.zzfli == null) {
            this.zzfli = new zzcpc(clock0);
        }
        return this.zzfli;
    }

    public final Set zzaih() {
        return this.zzfky;
    }

    public final Set zzaii() {
        return this.zzflb;
    }

    public final Set zzaij() {
        return this.zzflc;
    }

    public final Set zzaik() {
        return this.zzfld;
    }

    public final Set zzail() {
        return this.zzfle;
    }

    public final Set zzaim() {
        return this.zzflf;
    }

    public final Set zzain() {
        return this.zzfkx;
    }

    public final Set zzaio() {
        return this.zzfkz;
    }

    public final Set zzaip() {
        return this.zzfla;
    }

    public final Set zzaiq() {
        return this.zzflg;
    }

    @Nullable
    public final zzdcl zzair() {
        return this.zzfhw;
    }

    public final zzbqk zzc(Set set0) {
        if(this.zzflh == null) {
            this.zzflh = new zzbqk(set0);
        }
        return this.zzflh;
    }
}

