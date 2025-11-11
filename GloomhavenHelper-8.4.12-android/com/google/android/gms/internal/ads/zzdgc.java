package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.concurrent.GuardedBy;

final class zzdgc implements zzdfz {
    @GuardedBy("this")
    private final ConcurrentHashMap zzgry;
    private zzdgg zzgrz;
    private zzdge zzgsa;

    public zzdgc(zzdgg zzdgg0) {
        this.zzgry = new ConcurrentHashMap(zzdgg0.zzgst);
        this.zzgrz = zzdgg0;
        this.zzgsa = new zzdge();
    }

    private final void dumpToLog() {
        if(zzdgg.zzarx()) {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(this.zzgrz.zzgsr);
            stringBuilder0.append(" PoolCollection");
            stringBuilder0.append("\n\tPool does not exist: 0\n\tNew pools created: 0\n\tPools removed: 0\n\tEntries added: 0\n\tNo entries retrieved: 0\n");
            int v = 0;
            for(Object object0: this.zzgry.entrySet()) {
                ++v;
                stringBuilder0.append(v);
                stringBuilder0.append(". ");
                stringBuilder0.append(((Map.Entry)object0).getValue());
                stringBuilder0.append("#");
                stringBuilder0.append(((zzdgj)((Map.Entry)object0).getKey()).hashCode());
                stringBuilder0.append("    ");
                for(int v1 = 0; v1 < ((zzdga)((Map.Entry)object0).getValue()).size(); ++v1) {
                    stringBuilder0.append("[O]");
                }
                for(int v2 = ((zzdga)((Map.Entry)object0).getValue()).size(); v2 < this.zzgrz.zzgst; ++v2) {
                    stringBuilder0.append("[ ]");
                }
                stringBuilder0.append("\n");
                stringBuilder0.append(((zzdga)((Map.Entry)object0).getValue()).zzarm());
                stringBuilder0.append("\n");
            }
            while(v < this.zzgrz.zzgss) {
                ++v;
                stringBuilder0.append(v);
                stringBuilder0.append(".\n");
            }
            zzawf.zzeb(stringBuilder0.toString());
        }
    }

    private final void zza(zzdgk zzdgk0, zzdha zzdha0) {
        if(zzdgk0 != null) {
            zza zzsz$zza0 = (zza)(((zzdyz)zza.zzne().zza(com.google.android.gms.internal.ads.zzsz.zza.zza.zznc().zzb(zzc.zzbuz).zza(zzd.zzng().zzt(zzdha0.zzguh).zzbu(zzdha0.zzgug))).zzbcx()));
            zzdgk0.zzgtk.zzahr().zza(zzsz$zza0);
        }
        this.dumpToLog();
    }

    @Override  // com.google.android.gms.internal.ads.zzdfz
    @Deprecated
    public final zzdgj zza(zzuh zzuh0, String s, zzur zzur0) {
        return new zzdgm(zzuh0, s, new zzarf(this.zzgrz.zzur).zzul().zzdnt, this.zzgrz.zzgsv, zzur0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdfz
    @Nullable
    public final zzdgk zza(zzdgj zzdgj0) {
        synchronized(this) {
            zzdga zzdga0 = (zzdga)this.zzgry.get(zzdgj0);
            zzdgk zzdgk0 = null;
            if(zzdga0 == null) {
                this.zzgsa.zzarq();
                this.zza(null, null);
            }
            else {
                zzdgk0 = zzdga0.zzarj();
                if(zzdgk0 == null) {
                    this.zzgsa.zzarr();
                }
                this.zza(zzdgk0, zzdga0.zzarn());
            }
            return zzdgk0;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdfz
    public final boolean zza(zzdgj zzdgj0, zzdgk zzdgk0) {
        synchronized(this) {
            zzdga zzdga0 = (zzdga)this.zzgry.get(zzdgj0);
            zzdgk0.zzgtm = zzq.zzlc().currentTimeMillis();
            if(zzdga0 == null) {
                zzdga0 = new zzdga(this.zzgrz.zzgst, this.zzgrz.zzgsu * 1000);
                if(this.zzgry.size() == this.zzgrz.zzgss) {
                    long v1 = 0x7FFFFFFFFFFFFFFFL;
                    zzdgj zzdgj1 = null;
                    switch(zzdgb.zzgrx[this.zzgrz.zzgsx - 1]) {
                        case 1: {
                            for(Object object0: this.zzgry.entrySet()) {
                                Map.Entry map$Entry0 = (Map.Entry)object0;
                                if(((zzdga)map$Entry0.getValue()).getCreationTimeMillis() < v1) {
                                    v1 = ((zzdga)map$Entry0.getValue()).getCreationTimeMillis();
                                    zzdgj1 = (zzdgj)map$Entry0.getKey();
                                }
                            }
                            if(zzdgj1 != null) {
                                this.zzgry.remove(zzdgj1);
                            }
                            break;
                        }
                        case 2: {
                            for(Object object1: this.zzgry.entrySet()) {
                                Map.Entry map$Entry1 = (Map.Entry)object1;
                                if(((zzdga)map$Entry1.getValue()).zzark() < v1) {
                                    v1 = ((zzdga)map$Entry1.getValue()).zzark();
                                    zzdgj1 = (zzdgj)map$Entry1.getKey();
                                }
                            }
                            if(zzdgj1 != null) {
                                this.zzgry.remove(zzdgj1);
                            }
                            break;
                        }
                        case 3: {
                            int v2 = 0x7FFFFFFF;
                            for(Object object2: this.zzgry.entrySet()) {
                                Map.Entry map$Entry2 = (Map.Entry)object2;
                                if(((zzdga)map$Entry2.getValue()).zzarl() < v2) {
                                    v2 = ((zzdga)map$Entry2.getValue()).zzarl();
                                    zzdgj1 = (zzdgj)map$Entry2.getKey();
                                }
                            }
                            if(zzdgj1 != null) {
                                this.zzgry.remove(zzdgj1);
                            }
                        }
                    }
                    this.zzgsa.zzart();
                }
                this.zzgry.put(zzdgj0, zzdga0);
                this.zzgsa.zzars();
            }
            boolean z = zzdga0.zzb(zzdgk0);
            this.zzgsa.zzaru();
            zzdgd zzdgd0 = this.zzgsa.zzarv();
            zzdha zzdha0 = zzdga0.zzarn();
            if(zzdgk0 != null) {
                zza zzsz$zza0 = (zza)(((zzdyz)zza.zzne().zza(com.google.android.gms.internal.ads.zzsz.zza.zza.zznc().zzb(zzc.zzbuz).zza(zze.zzni().zzu(zzdgd0.zzgsb).zzv(zzdgd0.zzgsc).zzbv(zzdha0.zzgug))).zzbcx()));
                zzdgk0.zzgtk.zzahr().zzb(zzsz$zza0);
            }
            this.dumpToLog();
            return z;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzdfz
    public final zzdgg zzari() {
        return this.zzgrz;
    }

    @Override  // com.google.android.gms.internal.ads.zzdfz
    public final boolean zzb(zzdgj zzdgj0) {
        synchronized(this) {
            zzdga zzdga0 = (zzdga)this.zzgry.get(zzdgj0);
            return zzdga0 != null ? zzdga0.size() < this.zzgrz.zzgst : true;
        }
    }
}

