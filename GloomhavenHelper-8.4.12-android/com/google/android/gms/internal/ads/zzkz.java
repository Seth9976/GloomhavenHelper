package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"InlinedApi"})
@TargetApi(16)
public final class zzkz {
    static final class zza {
        public final String mimeType;
        public final boolean zzazi;

        public zza(String s, boolean z) {
            this.mimeType = s;
            this.zzazi = z;
        }

        // 去混淆评级： 低(20)
        @Override
        public final boolean equals(Object object0) {
            return this == object0 ? true : object0 != null && object0.getClass() == zza.class && TextUtils.equals(this.mimeType, ((zza)object0).mimeType) && this.zzazi == ((zza)object0).zzazi;
        }

        @Override
        public final int hashCode() {
            int v = this.mimeType == null ? 0 : this.mimeType.hashCode();
            return this.zzazi ? (v + 0x1F) * 0x1F + 0x4CF : (v + 0x1F) * 0x1F + 0x4D5;
        }
    }

    private static final zzkw zzazo;
    private static final Pattern zzazp;
    private static final HashMap zzazq;
    private static final SparseIntArray zzazr;
    private static final SparseIntArray zzazs;
    private static final Map zzazt;
    private static int zzazu;

    static {
        zzkz.zzazo = zzkw.zzbe("OMX.google.raw.decoder");
        zzkz.zzazp = Pattern.compile("^\\D?(\\d+)$");
        zzkz.zzazq = new HashMap();
        zzkz.zzazu = -1;
        SparseIntArray sparseIntArray0 = new SparseIntArray();
        zzkz.zzazr = sparseIntArray0;
        sparseIntArray0.put(66, 1);
        zzkz.zzazr.put(77, 2);
        zzkz.zzazr.put(88, 4);
        zzkz.zzazr.put(100, 8);
        SparseIntArray sparseIntArray1 = new SparseIntArray();
        zzkz.zzazs = sparseIntArray1;
        sparseIntArray1.put(10, 1);
        zzkz.zzazs.put(11, 4);
        zzkz.zzazs.put(12, 8);
        zzkz.zzazs.put(13, 16);
        zzkz.zzazs.put(20, 0x20);
        zzkz.zzazs.put(21, 0x40);
        zzkz.zzazs.put(22, 0x80);
        zzkz.zzazs.put(30, 0x100);
        zzkz.zzazs.put(0x1F, 0x200);
        zzkz.zzazs.put(0x20, 0x400);
        zzkz.zzazs.put(40, 0x800);
        zzkz.zzazs.put(41, 0x1000);
        zzkz.zzazs.put(42, 0x2000);
        zzkz.zzazs.put(50, 0x4000);
        zzkz.zzazs.put(51, 0x8000);
        zzkz.zzazs.put(52, 0x10000);
        HashMap hashMap0 = new HashMap();
        zzkz.zzazt = hashMap0;
        hashMap0.put("L30", 1);
        zzkz.zzazt.put("L60", 4);
        zzkz.zzazt.put("L63", 16);
        zzkz.zzazt.put("L90", 0x40);
        zzkz.zzazt.put("L93", 0x100);
        zzkz.zzazt.put("L120", 0x400);
        zzkz.zzazt.put("L123", 0x1000);
        zzkz.zzazt.put("L150", 0x4000);
        zzkz.zzazt.put("L153", 0x10000);
        zzkz.zzazt.put("L156", 0x40000);
        zzkz.zzazt.put("L180", 0x100000);
        zzkz.zzazt.put("L183", 0x400000);
        zzkz.zzazt.put("L186", 0x1000000);
        zzkz.zzazt.put("H30", 2);
        zzkz.zzazt.put("H60", 8);
        zzkz.zzazt.put("H63", 0x20);
        zzkz.zzazt.put("H90", 0x80);
        zzkz.zzazt.put("H93", 0x200);
        zzkz.zzazt.put("H120", 0x800);
        zzkz.zzazt.put("H123", 0x2000);
        zzkz.zzazt.put("H150", 0x8000);
        zzkz.zzazt.put("H153", 0x20000);
        zzkz.zzazt.put("H156", 0x80000);
        zzkz.zzazt.put("H180", 0x200000);
        zzkz.zzazt.put("H183", 0x800000);
        zzkz.zzazt.put("H186", 0x2000000);
    }

    private static Pair zza(String s, String[] arr_s) {
        Integer integer1;
        Integer integer0;
        if(arr_s.length < 2) {
            String s1 = String.valueOf(s);
            Log.w("MediaCodecUtil", (s1.length() == 0 ? new String("Ignoring malformed AVC codec string: ") : "Ignoring malformed AVC codec string: " + s1));
            return null;
        }
        try {
            if(arr_s[1].length() == 6) {
                Integer integer2 = Integer.parseInt(arr_s[1].substring(0, 2), 16);
                integer1 = Integer.parseInt(arr_s[1].substring(4), 16);
                integer0 = integer2;
            }
            else {
                if(arr_s.length < 3) {
                    String s2 = String.valueOf(s);
                    Log.w("MediaCodecUtil", (s2.length() == 0 ? new String("Ignoring malformed AVC codec string: ") : "Ignoring malformed AVC codec string: " + s2));
                    return null;
                }
                integer0 = Integer.parseInt(arr_s[1]);
                integer1 = Integer.parseInt(arr_s[2]);
            }
        }
        catch(NumberFormatException unused_ex) {
            String s3 = String.valueOf(s);
            Log.w("MediaCodecUtil", (s3.length() == 0 ? new String("Ignoring malformed AVC codec string: ") : "Ignoring malformed AVC codec string: " + s3));
            return null;
        }
        Integer integer3 = zzkz.zzazr.get(((int)integer0));
        if(integer3 == null) {
            Log.w("MediaCodecUtil", "Unknown AVC profile: " + integer0);
            return null;
        }
        Integer integer4 = zzkz.zzazs.get(((int)integer1));
        if(integer4 == null) {
            Log.w("MediaCodecUtil", "Unknown AVC level: " + integer1);
            return null;
        }
        return new Pair(integer3, integer4);
    }

    private static List zza(zza zzkz$zza0, zzlc zzlc0) throws zzld {
        int v2;
        String[] arr_s;
        String s1;
        MediaCodecInfo mediaCodecInfo0;
        try {
            List list0 = new ArrayList();
            String s = zzkz$zza0.mimeType;
            int v = zzlc0.getCodecCount();
            boolean z = zzlc0.zzhi();
            for(int v1 = 0; true; ++v1) {
                if(v1 >= v) {
                    return list0;
                }
                mediaCodecInfo0 = zzlc0.getCodecInfoAt(v1);
                s1 = mediaCodecInfo0.getName();
                if(!mediaCodecInfo0.isEncoder() && (z || !s1.endsWith(".secure"))) {
                    arr_s = mediaCodecInfo0.getSupportedTypes();
                    v2 = 0;
                label_12:
                    if(v2 >= arr_s.length) {
                        continue;
                    }
                    break;
                }
            }
            String s2 = arr_s[v2];
            if(s2.equalsIgnoreCase(s)) {
                try {
                    MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = mediaCodecInfo0.getCapabilitiesForType(s2);
                    boolean z1 = zzlc0.zza(s, mediaCodecInfo$CodecCapabilities0);
                    if(z && zzkz$zza0.zzazi == z1) {
                        list0.add(zzkw.zza(s1, s, mediaCodecInfo$CodecCapabilities0, false, false));
                    }
                    else if(!z && !zzkz$zza0.zzazi) {
                        list0.add(zzkw.zza(s1, s, mediaCodecInfo$CodecCapabilities0, false, false));
                    }
                    else if(!z && z1) {
                        list0.add(zzkw.zza((s1 + ".secure"), s, mediaCodecInfo$CodecCapabilities0, false, true));
                        return list0;
                    }
                    ++v2;
                    goto label_12;
                }
                catch(Exception exception1) {
                }
            }
            else {
                ++v2;
                goto label_12;
            }
            Log.e("MediaCodecUtil", "Failed to query codec " + s1 + " (" + s2 + ")");
            throw exception1;
        }
        catch(Exception exception0) {
            throw new zzld(exception0, null);
        }
    }

    public static zzkw zzb(String s, boolean z) throws zzld {
        List list0 = zzkz.zzc(s, z);
        return list0.isEmpty() ? null : ((zzkw)list0.get(0));
    }

    public static Pair zzbh(String s) {
        if(s == null) {
            return null;
        }
        String[] arr_s = s.split("\\.");
        int v = 2;
        switch(arr_s[0]) {
            case "avc1": 
            case "avc2": {
                return zzkz.zza(s, arr_s);
            }
            case "hev1": 
            case "hvc1": {
                if(arr_s.length < 4) {
                    String s1 = String.valueOf(s);
                    Log.w("MediaCodecUtil", (s1.length() == 0 ? new String("Ignoring malformed HEVC codec string: ") : "Ignoring malformed HEVC codec string: " + s1));
                    return null;
                }
                Matcher matcher0 = zzkz.zzazp.matcher(arr_s[1]);
                if(!matcher0.matches()) {
                    String s2 = String.valueOf(s);
                    Log.w("MediaCodecUtil", (s2.length() == 0 ? new String("Ignoring malformed HEVC codec string: ") : "Ignoring malformed HEVC codec string: " + s2));
                    return null;
                }
                String s3 = matcher0.group(1);
                boolean z = false;
                if("1".equals(s3)) {
                    z = true;
                    v = 1;
                }
                else if("2".equals(s3)) {
                    z = true;
                }
                if(z) {
                    Integer integer0 = (Integer)zzkz.zzazt.get(arr_s[3]);
                    if(integer0 == null) {
                        String s4 = matcher0.group(1);
                        Log.w("MediaCodecUtil", (s4.length() == 0 ? new String("Unknown HEVC level string: ") : "Unknown HEVC level string: " + s4));
                        return null;
                    }
                    return new Pair(v, integer0);
                }
                String s5 = String.valueOf(s3);
                Log.w("MediaCodecUtil", (s5.length() == 0 ? new String("Unknown HEVC profile string: ") : "Unknown HEVC profile string: " + s5));
                return null;
            }
            default: {
                return null;
            }
        }
    }

    private static List zzc(String s, boolean z) throws zzld {
        synchronized(zzkz.class) {
            zza zzkz$zza0 = new zza(s, z);
            List list0 = (List)zzkz.zzazq.get(zzkz$zza0);
            if(list0 != null) {
                return list0;
            }
            List list1 = zzkz.zza(zzkz$zza0, new zzle(z));
            if(z) {
                boolean z1 = !list1.isEmpty();
            }
            List list2 = Collections.unmodifiableList(list1);
            zzkz.zzazq.put(zzkz$zza0, list2);
            return list2;
        }
    }

    public static zzkw zzhg() {
        return zzkz.zzazo;
    }

    public static int zzhh() throws zzld {
        int v2;
        if(zzkz.zzazu == -1) {
            int v = 0;
            zzkw zzkw0 = zzkz.zzb("video/avc", false);
            if(zzkw0 != null) {
                MediaCodecInfo.CodecProfileLevel[] arr_mediaCodecInfo$CodecProfileLevel = zzkw0.zzhf();
                int v1 = 0;
                while(v < arr_mediaCodecInfo$CodecProfileLevel.length) {
                    switch(arr_mediaCodecInfo$CodecProfileLevel[v].level) {
                        case 1: {
                            v2 = 0x6300;
                            break;
                        }
                        case 2: {
                            v2 = 0x6300;
                            break;
                        }
                        case 8: {
                            v2 = 0x18C00;
                            break;
                        }
                        case 16: {
                            v2 = 0x18C00;
                            break;
                        }
                        case 0x20: {
                            v2 = 0x18C00;
                            break;
                        }
                        case 0x40: {
                            v2 = 0x31800;
                            break;
                        }
                        case 0x80: {
                            v2 = 0x65400;
                            break;
                        }
                        case 0x100: {
                            v2 = 0x65400;
                            break;
                        }
                        case 0x200: {
                            v2 = 0xE1000;
                            break;
                        }
                        case 0x400: {
                            v2 = 0x140000;
                            break;
                        }
                        case 0x800: {
                            v2 = 0x200000;
                            break;
                        }
                        case 0x1000: {
                            v2 = 0x200000;
                            break;
                        }
                        case 0x2000: {
                            v2 = 0x220000;
                            break;
                        }
                        case 0x4000: {
                            v2 = 0x564000;
                            break;
                        }
                        case 0x8000: {
                            v2 = 0x900000;
                            break;
                        }
                        case 0x10000: {
                            v2 = 0x900000;
                            break;
                        }
                        default: {
                            v2 = -1;
                        }
                    }
                    v1 = Math.max(v2, v1);
                    ++v;
                }
                v = Math.max(v1, 0x54600);
            }
            zzkz.zzazu = v;
        }
        return zzkz.zzazu;
    }
}

