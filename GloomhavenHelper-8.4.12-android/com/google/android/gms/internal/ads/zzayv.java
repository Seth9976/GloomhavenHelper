package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaCodecInfo.VideoCapabilities;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.os.Build.VERSION;
import android.util.Range;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jeb.synthetic.FIN;

public final class zzayv {
    private static Map zzdwj;
    private static List zzdwk;
    private static final Object zzdwl;

    static {
        zzayv.zzdwj = new HashMap();
        zzayv.zzdwl = new Object();
    }

    @TargetApi(21)
    private static Integer[] zza(Range range0) {
        return new Integer[]{((Integer)range0.getLower()), ((Integer)range0.getUpper())};
    }

    @TargetApi(16)
    public static List zzes(String s) {
        Object object0 = zzayv.zzdwl;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(zzayv.zzdwj.containsKey(s)) {
            List list0 = (List)zzayv.zzdwj.get(s);
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return list0;
        }
        try {
            Object object1 = zzayv.zzdwl;
            __monitor_enter(object1);
            int v1 = FIN.finallyOpen$NT();
            if(zzayv.zzdwk == null) {
                if(Build.VERSION.SDK_INT >= 21) {
                    zzayv.zzdwk = Arrays.asList(new MediaCodecList(0).getCodecInfos());
                }
                else {
                    int v2 = MediaCodecList.getCodecCount();
                    zzayv.zzdwk = new ArrayList(v2);
                    for(int v3 = 0; v3 < v2; ++v3) {
                        MediaCodecInfo mediaCodecInfo0 = MediaCodecList.getCodecInfoAt(v3);
                        zzayv.zzdwk.add(mediaCodecInfo0);
                    }
                }
                FIN.finallyCodeBegin$NT(v1);
                __monitor_exit(object1);
                FIN.finallyCodeEnd$NT(v1);
            }
            else {
                FIN.finallyExec$NT(v1);
            }
            List list1 = new ArrayList();
            for(Object object2: zzayv.zzdwk) {
                MediaCodecInfo mediaCodecInfo1 = (MediaCodecInfo)object2;
                if(!mediaCodecInfo1.isEncoder() && Arrays.asList(mediaCodecInfo1.getSupportedTypes()).contains(s)) {
                    HashMap hashMap0 = new HashMap();
                    hashMap0.put("codecName", mediaCodecInfo1.getName());
                    MediaCodecInfo.CodecCapabilities mediaCodecInfo$CodecCapabilities0 = mediaCodecInfo1.getCapabilitiesForType(s);
                    ArrayList arrayList0 = new ArrayList();
                    MediaCodecInfo.CodecProfileLevel[] arr_mediaCodecInfo$CodecProfileLevel = mediaCodecInfo$CodecCapabilities0.profileLevels;
                    for(int v4 = 0; v4 < arr_mediaCodecInfo$CodecProfileLevel.length; ++v4) {
                        MediaCodecInfo.CodecProfileLevel mediaCodecInfo$CodecProfileLevel0 = arr_mediaCodecInfo$CodecProfileLevel[v4];
                        arrayList0.add(new Integer[]{mediaCodecInfo$CodecProfileLevel0.profile, mediaCodecInfo$CodecProfileLevel0.level});
                    }
                    hashMap0.put("profileLevels", arrayList0);
                    if(Build.VERSION.SDK_INT >= 21) {
                        MediaCodecInfo.VideoCapabilities mediaCodecInfo$VideoCapabilities0 = mediaCodecInfo$CodecCapabilities0.getVideoCapabilities();
                        hashMap0.put("bitRatesBps", zzayv.zza(mediaCodecInfo$VideoCapabilities0.getBitrateRange()));
                        hashMap0.put("widthAlignment", mediaCodecInfo$VideoCapabilities0.getWidthAlignment());
                        hashMap0.put("heightAlignment", mediaCodecInfo$VideoCapabilities0.getHeightAlignment());
                        hashMap0.put("frameRates", zzayv.zza(mediaCodecInfo$VideoCapabilities0.getSupportedFrameRates()));
                        hashMap0.put("widths", zzayv.zza(mediaCodecInfo$VideoCapabilities0.getSupportedWidths()));
                        hashMap0.put("heights", zzayv.zza(mediaCodecInfo$VideoCapabilities0.getSupportedHeights()));
                    }
                    if(Build.VERSION.SDK_INT >= 23) {
                        hashMap0.put("instancesLimit", mediaCodecInfo$CodecCapabilities0.getMaxSupportedInstances());
                    }
                    ((ArrayList)list1).add(hashMap0);
                }
            }
            zzayv.zzdwj.put(s, list1);
            FIN.finallyExec$NT(v);
            return list1;
        }
        catch(RuntimeException | LinkageError runtimeException0) {
            HashMap hashMap1 = new HashMap();
            hashMap1.put("error", runtimeException0.getClass().getSimpleName());
            List list2 = new ArrayList();
            ((ArrayList)list2).add(hashMap1);
            zzayv.zzdwj.put(s, list2);
            FIN.finallyExec$NT(v);
            return list2;
        }
    }
}

