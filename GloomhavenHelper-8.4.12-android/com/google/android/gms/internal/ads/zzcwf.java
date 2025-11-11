package com.google.android.gms.internal.ads;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public final class zzcwf implements zzcye {
    private final PackageInfo zzdju;
    private final zzawh zzdsq;
    private final zzdeu zzfir;
    private final zzdoe zzfrv;

    public zzcwf(zzdoe zzdoe0, zzdeu zzdeu0, @Nullable PackageInfo packageInfo0, zzawh zzawh0) {
        this.zzfrv = zzdoe0;
        this.zzfir = zzdeu0;
        this.zzdju = packageInfo0;
        this.zzdsq = zzawh0;
    }

    final void zza(ArrayList arrayList0, Bundle bundle0) {
        String s3;
        String s2;
        String s1;
        String s;
        bundle0.putInt("native_version", 3);
        bundle0.putStringArrayList("native_templates", arrayList0);
        bundle0.putStringArrayList("native_custom_templates", this.zzfir.zzgqt);
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcmy)).booleanValue() && this.zzfir.zzdff.versionCode > 3) {
            bundle0.putBoolean("enable_native_media_orientation", true);
            switch(this.zzfir.zzdff.zzbkf) {
                case 1: {
                    s = "any";
                    break;
                }
                case 2: {
                    s = "landscape";
                    break;
                }
                case 3: {
                    s = "portrait";
                    break;
                }
                case 4: {
                    s = "square";
                    break;
                }
                default: {
                    s = "unknown";
                }
            }
            if(!"unknown".equals(s)) {
                bundle0.putString("native_media_orientation", s);
            }
        }
        switch(this.zzfir.zzdff.zzbke) {
            case 0: {
                s1 = "any";
                break;
            }
            case 1: {
                s1 = "portrait";
                break;
            }
            case 2: {
                s1 = "landscape";
                break;
            }
            default: {
                s1 = "unknown";
            }
        }
        if(!"unknown".equals(s1)) {
            bundle0.putString("native_image_orientation", s1);
        }
        bundle0.putBoolean("native_multiple_images", this.zzfir.zzdff.zzbkg);
        bundle0.putBoolean("use_custom_mute", this.zzfir.zzdff.zzbkj);
        int v = this.zzdju == null ? 0 : this.zzdju.versionCode;
        if(v > this.zzdsq.zzwe()) {
            this.zzdsq.zzwk();
            this.zzdsq.zzcp(v);
        }
        JSONObject jSONObject0 = this.zzdsq.zzwj();
        if(jSONObject0 == null) {
            s2 = null;
        }
        else {
            JSONArray jSONArray0 = jSONObject0.optJSONArray(this.zzfir.zzgqr);
            s2 = jSONArray0 == null ? null : jSONArray0.toString();
        }
        if(!TextUtils.isEmpty(s2)) {
            bundle0.putString("native_advanced_settings", s2);
        }
        if(this.zzfir.zzggu > 1) {
            bundle0.putInt("max_num_ads", this.zzfir.zzggu);
        }
        zzahl zzahl0 = this.zzfir.zzdlk;
        if(zzahl0 != null) {
            if(TextUtils.isEmpty(zzahl0.zzczm)) {
                if(zzahl0.versionCode >= 2) {
                    switch(zzahl0.zzbkf) {
                        case 2: {
                            s3 = "l";
                            break;
                        }
                        case 3: {
                            s3 = "p";
                            break;
                        }
                        default: {
                            s3 = "l";
                        }
                    }
                }
                else {
                    switch(zzahl0.zzczl) {
                        case 1: {
                            s3 = "l";
                            break;
                        }
                        case 2: {
                            s3 = "p";
                            break;
                        }
                        default: {
                            zzazh.zzey(("Instream ad video aspect ratio " + zzahl0.zzczl + " is wrong."));
                            s3 = "l";
                        }
                    }
                }
                bundle0.putString("ia_var", s3);
            }
            else {
                bundle0.putString("ad_tag", zzahl0.zzczm);
            }
            bundle0.putBoolean("instr", true);
        }
        if(this.zzfir.zzaqx() != null) {
            bundle0.putBoolean("has_delayed_banner_listener", true);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcwi zzcwi0 = () -> {
            ArrayList arrayList0 = this.zzfir.zzgqs;
            if(arrayList0 == null) {
                return zzcwh.zzgji;
            }
            return arrayList0.isEmpty() ? zzcwk.zzgji : new zzcwj(this, arrayList0);
        };
        return this.zzfrv.zzd(zzcwi0);
    }

    // 检测为 Lambda 实现
    final zzcwg zzapi() throws Exception [...]
}

