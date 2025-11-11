package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public final class zzcty implements zzcye {
    private final zzavr zzbmv;
    private final zzdeu zzfir;
    private final zzcye zzgia;
    private final Context zzur;

    public zzcty(zzcvg zzcvg0, zzdeu zzdeu0, Context context0, zzavr zzavr0) {
        this.zzgia = zzcvg0;
        this.zzfir = zzdeu0;
        this.zzur = context0;
        this.zzbmv = zzavr0;
    }

    // 检测为 Lambda 实现
    final zzctv zza(zzcyh zzcyh0) [...]

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        return zzdnt.zzb(this.zzgia.zzapb(), (zzcyh zzcyh0) -> {
            float f1;
            int v3;
            String s1;
            int v2;
            boolean z;
            String s;
            zzuk zzuk0 = this.zzfir.zzblv;
            if(zzuk0.zzcdd == null) {
                s = zzuk0.zzabk;
                z = zzuk0.zzcde;
            }
            else {
                zzuk[] arr_zzuk = zzuk0.zzcdd;
                s = null;
                boolean z1 = false;
                boolean z2 = false;
                z = false;
                for(int v = 0; v < arr_zzuk.length; ++v) {
                    zzuk zzuk1 = arr_zzuk[v];
                    if(!zzuk1.zzcde && !z1) {
                        s = zzuk1.zzabk;
                        z1 = true;
                    }
                    if(zzuk1.zzcde && !z2) {
                        z2 = true;
                        z = true;
                    }
                    if(z1 && z2) {
                        break;
                    }
                }
            }
            Resources resources0 = this.zzur.getResources();
            if(resources0 == null) {
            label_33:
                s1 = null;
                f1 = 0.0f;
                v3 = 0;
                v2 = 0;
            }
            else {
                DisplayMetrics displayMetrics0 = resources0.getDisplayMetrics();
                if(displayMetrics0 != null) {
                    float f = displayMetrics0.density;
                    int v1 = displayMetrics0.widthPixels;
                    v2 = displayMetrics0.heightPixels;
                    s1 = this.zzbmv.zzvk().zzwl();
                    v3 = v1;
                    f1 = f;
                    goto label_37;
                }
                goto label_33;
            }
        label_37:
            StringBuilder stringBuilder0 = new StringBuilder();
            if(zzuk0.zzcdd != null) {
                zzuk[] arr_zzuk1 = zzuk0.zzcdd;
                boolean z3 = false;
                for(int v4 = 0; v4 < arr_zzuk1.length; ++v4) {
                    zzuk zzuk2 = arr_zzuk1[v4];
                    if(zzuk2.zzcde) {
                        z3 = true;
                    }
                    else {
                        if(stringBuilder0.length() != 0) {
                            stringBuilder0.append("|");
                        }
                        stringBuilder0.append((zzuk2.width != -1 || f1 == 0.0f ? zzuk2.width : ((int)(((float)zzuk2.widthPixels) / f1))));
                        stringBuilder0.append("x");
                        stringBuilder0.append((zzuk2.height != -2 || f1 == 0.0f ? zzuk2.height : ((int)(((float)zzuk2.heightPixels) / f1))));
                    }
                }
                if(z3) {
                    if(stringBuilder0.length() != 0) {
                        stringBuilder0.insert(0, "|");
                    }
                    stringBuilder0.insert(0, "320x50");
                }
            }
            return new zzctv(zzuk0, s, z, stringBuilder0.toString(), f1, v3, v2, s1);
        }, zzazq.zzdxp);
    }
}

