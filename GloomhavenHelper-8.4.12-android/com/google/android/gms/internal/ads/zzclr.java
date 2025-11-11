package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.NetworkInfo.DetailedState;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.SparseArray;
import com.google.android.gms.ads.internal.zzq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class zzclr {
    private final zzbpm zzfiy;
    private final zzclk zzfya;
    private final TelephonyManager zzgao;
    private final zzcle zzgap;
    private zztf zzgaq;
    private static final SparseArray zzgar;
    private final Context zzur;

    static {
        SparseArray sparseArray0 = new SparseArray();
        zzclr.zzgar = sparseArray0;
        sparseArray0.put(NetworkInfo.DetailedState.CONNECTED.ordinal(), zzc.zzbyf);
        zzclr.zzgar.put(NetworkInfo.DetailedState.AUTHENTICATING.ordinal(), zzc.zzbye);
        zzclr.zzgar.put(NetworkInfo.DetailedState.CONNECTING.ordinal(), zzc.zzbye);
        zzclr.zzgar.put(NetworkInfo.DetailedState.OBTAINING_IPADDR.ordinal(), zzc.zzbye);
        zzclr.zzgar.put(NetworkInfo.DetailedState.DISCONNECTING.ordinal(), zzc.zzbyg);
        zzclr.zzgar.put(NetworkInfo.DetailedState.BLOCKED.ordinal(), zzc.zzbyh);
        zzclr.zzgar.put(NetworkInfo.DetailedState.DISCONNECTED.ordinal(), zzc.zzbyh);
        zzclr.zzgar.put(NetworkInfo.DetailedState.FAILED.ordinal(), zzc.zzbyh);
        zzclr.zzgar.put(NetworkInfo.DetailedState.IDLE.ordinal(), zzc.zzbyh);
        zzclr.zzgar.put(NetworkInfo.DetailedState.SCANNING.ordinal(), zzc.zzbyh);
        zzclr.zzgar.put(NetworkInfo.DetailedState.SUSPENDED.ordinal(), zzc.zzbyi);
        if(Build.VERSION.SDK_INT >= 17) {
            zzclr.zzgar.put(NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK.ordinal(), zzc.zzbye);
        }
        zzclr.zzgar.put(NetworkInfo.DetailedState.VERIFYING_POOR_LINK.ordinal(), zzc.zzbye);
    }

    zzclr(Context context0, zzbpm zzbpm0, zzclk zzclk0, zzcle zzcle0) {
        this.zzur = context0;
        this.zzfiy = zzbpm0;
        this.zzfya = zzclk0;
        this.zzgap = zzcle0;
        this.zzgao = (TelephonyManager)context0.getSystemService("phone");
    }

    static ArrayList zza(zzclr zzclr0, Bundle bundle0) {
        return zzclr.zzl(bundle0);
    }

    private final byte[] zza(boolean z, ArrayList arrayList0, zzh zzsz$zzh0, zzc zzsz$zzj$zzc0) {
        boolean z1 = true;
        zza zzsz$zzj$zza$zza0 = com.google.android.gms.internal.ads.zzsz.zzj.zza.zznw().zzd(arrayList0).zzh(zzclr.zzbk(zzq.zzkx().zzb(this.zzur.getContentResolver()) != 0)).zzi(zzq.zzkx().zza(this.zzur, this.zzgao)).zzev(this.zzfya.zzanw()).zzew(this.zzfya.zzany()).zzcf(this.zzfya.getResponseCode()).zzb(zzsz$zzj$zzc0).zzb(zzsz$zzh0).zzj(this.zzgaq).zzf(zzclr.zzbk(z)).zzeu(zzq.zzlc().currentTimeMillis());
        if(zzq.zzkx().zza(this.zzur.getContentResolver()) == 0) {
            z1 = false;
        }
        return ((com.google.android.gms.internal.ads.zzsz.zzj.zza)(((zzdyz)zzsz$zzj$zza$zza0.zzg(zzclr.zzbk(z1)).zzbcx()))).toByteArray();
    }

    static zzc zzb(zzclr zzclr0, Bundle bundle0) {
        return zzclr.zzk(bundle0);
    }

    // 去混淆评级： 低(20)
    private static zztf zzbk(boolean z) {
        return z ? zztf.zzbwi : zztf.zzbwh;
    }

    public final void zzbl(boolean z) {
        zzdnt.zza(this.zzfiy.zzahs(), new zzclq(this, z), zzazq.zzdxp);
    }

    private final zzh zzj(Bundle bundle0) {
        com.google.android.gms.internal.ads.zzsz.zzh.zza zzsz$zzh$zza0;
        zzb zzsz$zzh$zzb0 = zzh.zznq();
        int v = bundle0.getInt("cnt", -2);
        int v1 = bundle0.getInt("gnt", 0);
        if(v == -1) {
            this.zzgaq = zztf.zzbwi;
            return (zzh)(((zzdyz)zzsz$zzh$zzb0.zzbcx()));
        }
        this.zzgaq = zztf.zzbwh;
        switch(v) {
            case 0: {
                zzsz$zzh$zzb0.zzb(com.google.android.gms.internal.ads.zzsz.zzh.zzc.zzbxd);
                break;
            }
            case 1: {
                zzsz$zzh$zzb0.zzb(com.google.android.gms.internal.ads.zzsz.zzh.zzc.zzbxe);
                break;
            }
            default: {
                zzsz$zzh$zzb0.zzb(com.google.android.gms.internal.ads.zzsz.zzh.zzc.zzbxc);
            }
        }
        switch(v1) {
            case 13: {
                zzsz$zzh$zza0 = com.google.android.gms.internal.ads.zzsz.zzh.zza.zzbxa;
                break;
            }
            case 1: 
            case 2: 
            case 4: 
            case 7: 
            case 11: 
            case 16: {
                zzsz$zzh$zza0 = com.google.android.gms.internal.ads.zzsz.zzh.zza.zzbwy;
                break;
            }
            case 3: 
            case 5: 
            case 6: 
            case 8: 
            case 9: 
            case 10: 
            case 12: 
            case 14: 
            case 15: 
            case 17: {
                zzsz$zzh$zza0 = com.google.android.gms.internal.ads.zzsz.zzh.zza.zzbwz;
                break;
            }
            default: {
                zzsz$zzh$zza0 = com.google.android.gms.internal.ads.zzsz.zzh.zza.zzbwx;
            }
        }
        zzsz$zzh$zzb0.zzb(zzsz$zzh$zza0);
        return (zzh)(((zzdyz)zzsz$zzh$zzb0.zzbcx()));
    }

    private static zzc zzk(Bundle bundle0) {
        int v = zzdez.zza(zzdez.zza(bundle0, "device"), "network").getInt("active_network_state", -1);
        return (zzc)zzclr.zzgar.get(v, zzc.zzbyd);
    }

    private static ArrayList zzl(Bundle bundle0) {
        com.google.android.gms.internal.ads.zzsz.zzb.zza zzsz$zzb$zza0;
        List list1;
        Iterator iterator0;
        ArrayList arrayList0;
        Object object0 = bundle0.get("ad_types");
        if(object0 instanceof List) {
            arrayList0 = new ArrayList(((List)object0).size());
            iterator0 = ((List)object0).iterator();
            goto label_9;
        }
        else if(object0 instanceof String[]) {
            List list0 = Arrays.asList(((String[])object0));
            arrayList0 = new ArrayList(list0.size());
            iterator0 = list0.iterator();
        label_9:
            while(iterator0.hasNext()) {
                Object object1 = iterator0.next();
                if(object1 instanceof String) {
                    arrayList0.add(((String)object1));
                }
            }
            list1 = Collections.unmodifiableList(arrayList0);
        }
        else {
            list1 = Collections.emptyList();
        }
        ArrayList arrayList1 = new ArrayList();
        for(Object object2: list1) {
            switch(((String)object2)) {
                case "banner": {
                    zzsz$zzb$zza0 = com.google.android.gms.internal.ads.zzsz.zzb.zza.zzbvl;
                    break;
                }
                case "interstitial": {
                    zzsz$zzb$zza0 = com.google.android.gms.internal.ads.zzsz.zzb.zza.zzbvm;
                    break;
                }
                case "native": {
                    zzsz$zzb$zza0 = com.google.android.gms.internal.ads.zzsz.zzb.zza.zzbvp;
                    break;
                }
                case "rewarded": {
                    zzsz$zzb$zza0 = com.google.android.gms.internal.ads.zzsz.zzb.zza.zzbvt;
                    break;
                }
                default: {
                    zzsz$zzb$zza0 = com.google.android.gms.internal.ads.zzsz.zzb.zza.zzbvk;
                }
            }
            arrayList1.add(zzsz$zzb$zza0);
        }
        return arrayList1;
    }
}

