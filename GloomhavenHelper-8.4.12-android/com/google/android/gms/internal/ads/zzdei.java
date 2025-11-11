package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import androidx.annotation.Nullable;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzdei {
    public final String zzafi;
    public final boolean zzblo;
    public final String zzdcu;
    public final String zzddf;
    public final String zzddg;
    public final List zzddp;
    public final List zzddq;
    public final String zzddv;
    public final boolean zzdec;
    public final boolean zzded;
    public final boolean zzdee;
    public final String zzdfr;
    public final List zzdlr;
    public final String zzdlu;
    public final String zzdlx;
    @Nullable
    public final zzasq zzdmd;
    public final List zzdme;
    public final List zzdmf;
    public final boolean zzdmn;
    public final boolean zzdmq;
    public final boolean zzdmr;
    public final boolean zzdnk;
    public final boolean zzehg;
    public final String zzejm;
    public final int zzffs;
    public final boolean zzfft;
    public final String zzfkf;
    public final int zzfmh;
    public final List zzgpk;
    public final int zzgpl;
    public final List zzgpm;
    public final List zzgpn;
    public final List zzgpo;
    public final List zzgpp;
    @Nullable
    public final zzdem zzgpq;
    public final List zzgpr;
    public final List zzgps;
    public final JSONObject zzgpt;
    public final zzaua zzgpu;
    public final JSONObject zzgpv;
    public final JSONObject zzgpw;
    public final boolean zzgpx;
    public final int zzgpy;
    public final int zzgpz;
    public final JSONObject zzgqa;
    public final int zzgqb;
    public final boolean zzgqc;
    public final zzapk zzgqd;
    @Nullable
    public final zzum zzgqe;

    // This method was un-flattened
    zzdei(JsonReader jsonReader0) throws IllegalStateException, IOException, JSONException, NumberFormatException {
        List list24;
        List list26;
        zzasq zzasq2;
        List list25;
        zzasq zzasq1;
        List list0 = Collections.emptyList();
        List list1 = Collections.emptyList();
        List list2 = Collections.emptyList();
        List list3 = Collections.emptyList();
        List list4 = Collections.emptyList();
        Collections.emptyList();
        List list5 = Collections.emptyList();
        List list6 = Collections.emptyList();
        List list7 = Collections.emptyList();
        List list8 = Collections.emptyList();
        List list9 = Collections.emptyList();
        List list10 = Collections.emptyList();
        List list11 = Collections.emptyList();
        JSONObject jSONObject0 = new JSONObject();
        JSONObject jSONObject1 = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jsonReader0.beginObject();
        List list12 = list9;
        List list13 = list10;
        List list14 = list11;
        JSONObject jSONObject4 = jSONObject0;
        String s = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String s4 = "";
        JSONObject jSONObject5 = jSONObject1;
        JSONObject jSONObject6 = jSONObject2;
        String s5 = "";
        JSONObject jSONObject7 = jSONObject3;
        String s6 = "";
        String s7 = "";
        zzasq zzasq0 = null;
        zzdem zzdem0 = null;
        zzaua zzaua0 = null;
        zzapk zzapk0 = null;
        zzum zzum0 = null;
        boolean z = false;
        boolean z1 = false;
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        int v = -1;
        int v1 = 0;
        boolean z6 = false;
        boolean z7 = false;
        boolean z8 = false;
        int v2 = 0;
        boolean z9 = false;
        int v3 = -1;
        boolean z10 = false;
        boolean z11 = true;
        String s8 = "";
        List list15 = list7;
        List list16 = list8;
        List list17 = list5;
        List list18 = list6;
        String s9 = "";
        int v4 = 0;
        List list19 = list3;
        List list20 = list4;
        List list21 = list1;
        List list22 = list2;
        List list23 = list0;
        int v5 = 0;
        while(jsonReader0.hasNext()) {
            String s10 = jsonReader0.nextName();
            String s11 = s10 == null ? "" : s10;
            switch(s11.hashCode()) {
                case 0x89F2A0DF: {
                    list24 = list16;
                    if(s11.equals("debug_signals")) {
                        jSONObject5 = zzayf.zzc(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x8AD8AA29: {
                    list24 = list16;
                    if(s11.equals("omid_settings")) {
                        jSONObject7 = zzayf.zzc(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x93FE39FC: {
                    list24 = list16;
                    if(s11.equals("play_prewarm_options")) {
                        JSONObject jSONObject8 = zzayf.zzc(jsonReader0);
                        if(jSONObject8 == null) {
                            zzasq1 = zzasq0;
                            list25 = list15;
                            zzapk0 = null;
                        }
                        else {
                            list25 = list15;
                            zzasq1 = zzasq0;
                            zzapk0 = new zzapk(jSONObject8.optBoolean("enable_prewarming", false), jSONObject8.optString("prefetch_url", ""));
                        }
                        list15 = list25;
                        list16 = list24;
                        zzasq0 = zzasq1;
                        continue;
                    }
                    break;
                }
                case -1620470467: {
                    list24 = list16;
                    if(s11.equals("backend_query_id")) {
                        s7 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1440104884: {
                    list24 = list16;
                    if(s11.equals("is_custom_close_blocked")) {
                        z4 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xAA32F5D0: {
                    list24 = list16;
                    if(s11.equals("orientation")) {
                        String s12 = jsonReader0.nextString();
                        if("landscape".equalsIgnoreCase(s12)) {
                            v = 6;
                        }
                        else if("portrait".equalsIgnoreCase(s12)) {
                            v = 7;
                        }
                        else {
                            v = -1;
                        }
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xAAD3A8B5: {
                    list24 = list16;
                    if(s11.equals("enable_omid")) {
                        z6 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xAC540548: {
                    list24 = list16;
                    if(s11.equals("showable_impression_type")) {
                        v2 = jsonReader0.nextInt();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1360811658: {
                    list24 = list16;
                    if(s11.equals("ad_sizes")) {
                        list14 = zzdeh.zze(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1306015996: {
                    list24 = list16;
                    if(s11.equals("adapters")) {
                        list13 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1289032093: {
                    list24 = list16;
                    if(s11.equals("extras")) {
                        jSONObject6 = zzayf.zzc(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1240082064: {
                    list24 = list16;
                    if(s11.equals("ad_event_value")) {
                        zzum0 = zzum.zza(zzayf.zzc(jsonReader0));
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xB66FE42D: {
                    list24 = list16;
                    if(s11.equals("allow_pub_rendered_attribution")) {
                        z = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1181000426: {
                    list24 = list16;
                    if(s11.equals("is_augmented_reality_ad")) {
                        z9 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1152230954: {
                    list24 = list16;
                    if(s11.equals("ad_type")) {
                        String s13 = jsonReader0.nextString();
                        if("banner".equals(s13)) {
                            v5 = 1;
                        }
                        else if("interstitial".equals(s13)) {
                            v5 = 2;
                        }
                        else if("native_express".equals(s13)) {
                            v5 = 3;
                        }
                        else if("native".equals(s13)) {
                            v5 = 4;
                        }
                        else {
                            v5 = "rewarded".equals(s13) ? 5 : 0;
                        }
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xBBA94761: {
                    list24 = list16;
                    if(s11.equals("is_scroll_aware")) {
                        z8 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xBD7DA620: {
                    list24 = list16;
                    if(s11.equals("fill_urls")) {
                        list15 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xBF82F4DA: {
                    list24 = list16;
                    if(s11.equals("allocation_id")) {
                        s = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1078050970: {
                    list24 = list16;
                    if(s11.equals("video_complete_urls")) {
                        list18 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -1051269058: {
                    list24 = list16;
                    if(s11.equals("active_view")) {
                        s3 = zzayf.zzc(jsonReader0).toString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -982608540: {
                    list24 = list16;
                    if(s11.equals("valid_from_timestamp")) {
                        s8 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xD1B2113B: {
                    list24 = list16;
                    if(s11.equals("click_urls")) {
                        list21 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xDF8FE939: {
                    list24 = list16;
                    if(s11.equals("safe_browsing")) {
                        zzaua0 = zzaua.zzg(zzayf.zzc(jsonReader0));
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xE5F30977: {
                    list24 = list16;
                    if(s11.equals("imp_urls")) {
                        list22 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -404326515: {
                    list24 = list16;
                    if(s11.equals("render_timeout_ms")) {
                        v1 = jsonReader0.nextInt();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xE84B81F5: {
                    list24 = list16;
                    if(s11.equals("ad_close_time_ms")) {
                        v3 = jsonReader0.nextInt();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xE9F5B450: {
                    list24 = list16;
                    if(s11.equals("is_close_button_enabled")) {
                        z11 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case -213424028: {
                    list24 = list16;
                    if(s11.equals("watermark")) {
                        s6 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xFE40547A: {
                    list24 = list16;
                    if(s11.equals("allow_custom_click_gesture")) {
                        z2 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 3107: {
                    list24 = list16;
                    if(s11.equals("ad")) {
                        zzdem0 = new zzdem(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0xD1B: {
                    list24 = list16;
                    if(s11.equals("id")) {
                        s1 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 3076010: {
                    list24 = list16;
                    if(s11.equals("data")) {
                        jSONObject4 = zzayf.zzc(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x3C44B50: {
                    list24 = list16;
                    if(s11.equals("render_test_label")) {
                        z3 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x6674F9B: {
                    list24 = list16;
                    if(s11.equals("qdata")) {
                        s2 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 230323073: {
                    list24 = list16;
                    if(s11.equals("ad_load_urls")) {
                        list19 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x18F0294B: {
                    list24 = list16;
                    if(s11.equals("is_closable_area_disabled")) {
                        z5 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x239CB9FC: {
                    list24 = list16;
                    if(s11.equals("debug_dialog_string")) {
                        s4 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x282126F8: {
                    list24 = list16;
                    if(s11.equals("reward_granted_urls")) {
                        zzasq2 = zzasq0;
                        list26 = list15;
                        zzayf.zza(jsonReader0);
                        list15 = list26;
                        list16 = list24;
                        zzasq0 = zzasq2;
                        continue;
                    }
                    break;
                }
                case 0x2CFEAB54: {
                    list24 = list16;
                    if(s11.equals("container_sizes")) {
                        list12 = zzdeh.zze(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x2F2793B0: {
                    list24 = list16;
                    if(s11.equals("impression_type")) {
                        int v6 = jsonReader0.nextInt();
                        v4 = v6 == 0 || v6 == 1 ? v6 : 0;
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 1010584092: {
                    list24 = list16;
                    if(s11.equals("transaction_id")) {
                        s9 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 1100650276: {
                    list24 = list16;
                    if(s11.equals("rewards")) {
                        zzasq0 = zzasq.zza(zzayf.zzd(jsonReader0));
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x4EC7DC6F: {
                    list24 = list16;
                    if(s11.equals("allow_pub_owned_ad_view")) {
                        z1 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x619B1543: {
                    list24 = list16;
                    if(s11.equals("bid_response")) {
                        s5 = jsonReader0.nextString();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x61B080E5: {
                    list24 = list16;
                    if(s11.equals("video_start_urls")) {
                        list20 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x64A20A30: {
                    list24 = list16;
                    if(s11.equals("video_reward_urls")) {
                        list17 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 1799285870: {
                    list24 = list16;
                    if(s11.equals("use_third_party_container_height")) {
                        z10 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x6DA6D810: {
                    list24 = list16;
                    if(s11.equals("renderers")) {
                        list23 = zzayf.zza(jsonReader0);
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 0x6FC8B8D3: {
                    list24 = list16;
                    if(s11.equals("is_analytics_logging_enabled")) {
                        z7 = jsonReader0.nextBoolean();
                        list16 = list24;
                        continue;
                    }
                    break;
                }
                case 2072888499: {
                    if(s11.equals("manual_tracking_urls")) {
                        goto label_358;
                    }
                    else {
                        list24 = list16;
                    }
                    break;
                }
                default: {
                    list24 = list16;
                }
            }
            zzasq2 = zzasq0;
            list26 = list15;
            jsonReader0.skipValue();
            list15 = list26;
            list16 = list24;
            zzasq0 = zzasq2;
            continue;
        label_358:
            list16 = zzayf.zza(jsonReader0);
        }
        jsonReader0.endObject();
        this.zzgpk = list23;
        this.zzfmh = v5;
        this.zzddp = list21;
        this.zzddq = list22;
        this.zzgpm = list19;
        this.zzgpl = v4;
        this.zzdme = list20;
        this.zzdmf = list17;
        this.zzgpn = list18;
        this.zzddf = s9;
        this.zzddg = s8;
        this.zzdmd = zzasq0;
        this.zzgpo = list15;
        this.zzdlr = list16;
        this.zzgpp = list12;
        this.zzgpq = zzdem0;
        this.zzgpr = list13;
        this.zzgps = list14;
        this.zzdcu = s;
        this.zzgpt = jSONObject4;
        this.zzafi = s1;
        this.zzddv = s2;
        this.zzdlx = s3;
        this.zzgpu = zzaua0;
        this.zzdlu = s4;
        this.zzgpv = jSONObject5;
        this.zzgpw = jSONObject6;
        this.zzdec = z;
        this.zzded = z1;
        this.zzdee = z2;
        this.zzdnk = z3;
        this.zzgpx = z4;
        this.zzblo = z5;
        this.zzgpy = v;
        this.zzgpz = v1;
        this.zzdmn = z6;
        this.zzejm = s5;
        this.zzgqa = jSONObject7;
        this.zzdmq = z7;
        this.zzdmr = z8;
        this.zzgqb = v2;
        this.zzehg = z9;
        this.zzdfr = s6;
        this.zzffs = v3;
        this.zzfkf = s7;
        this.zzgqc = z10;
        this.zzgqd = zzapk0;
        this.zzfft = z11;
        this.zzgqe = zzum0;
    }
}

