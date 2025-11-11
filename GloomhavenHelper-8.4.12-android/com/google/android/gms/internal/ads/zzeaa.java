package com.google.android.gms.internal.ads;

final class zzeaa implements zzeai {
    private zzeai[] zzhux;

    zzeaa(zzeai[] arr_zzeai) {
        this.zzhux = arr_zzeai;
    }

    @Override  // com.google.android.gms.internal.ads.zzeai
    public final boolean zzc(Class class0) {
        zzeai[] arr_zzeai = this.zzhux;
        for(int v = 0; v < arr_zzeai.length; ++v) {
            if(arr_zzeai[v].zzc(class0)) {
                return true;
            }
        }
        return false;
    }

    @Override  // com.google.android.gms.internal.ads.zzeai
    public final zzeaf zzd(Class class0) {
        zzeai[] arr_zzeai = this.zzhux;
        for(int v = 0; v < arr_zzeai.length; ++v) {
            zzeai zzeai0 = arr_zzeai[v];
            if(zzeai0.zzc(class0)) {
                return zzeai0.zzd(class0);
            }
        }
        String s = class0.getName();
        throw new UnsupportedOperationException((s.length() == 0 ? new String("No factory is available for message type: ") : "No factory is available for message type: " + s));
    }
}

