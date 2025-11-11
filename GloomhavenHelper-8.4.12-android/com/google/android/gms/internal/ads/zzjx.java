package com.google.android.gms.internal.ads;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzjx {
    public int height;
    public int number;
    public int type;
    public int width;
    public zziu zzafq;
    public int zzafu;
    public byte[] zzafv;
    public int zzafx;
    public int zzafy;
    private String zzage;
    public String zzapv;
    public int zzapw;
    public boolean zzapx;
    public byte[] zzapy;
    public zzjq zzapz;
    public byte[] zzaqa;
    public int zzaqb;
    public int zzaqc;
    public int zzaqd;
    public boolean zzaqe;
    public int zzaqf;
    public int zzaqg;
    public int zzaqh;
    public int zzaqi;
    public int zzaqj;
    public float zzaqk;
    public float zzaql;
    public float zzaqm;
    public float zzaqn;
    public float zzaqo;
    public float zzaqp;
    public float zzaqq;
    public float zzaqr;
    public float zzaqs;
    public float zzaqt;
    public int zzaqu;
    public long zzaqv;
    public long zzaqw;
    public boolean zzaqx;
    public boolean zzaqy;
    public zzjn zzaqz;
    public int zzara;

    private zzjx() {
        this.width = -1;
        this.height = -1;
        this.zzaqb = -1;
        this.zzaqc = -1;
        this.zzaqd = 0;
        this.zzafv = null;
        this.zzafu = -1;
        this.zzaqe = false;
        this.zzaqf = -1;
        this.zzaqg = -1;
        this.zzaqh = -1;
        this.zzaqi = 1000;
        this.zzaqj = 200;
        this.zzaqk = -1.0f;
        this.zzaql = -1.0f;
        this.zzaqm = -1.0f;
        this.zzaqn = -1.0f;
        this.zzaqo = -1.0f;
        this.zzaqp = -1.0f;
        this.zzaqq = -1.0f;
        this.zzaqr = -1.0f;
        this.zzaqs = -1.0f;
        this.zzaqt = -1.0f;
        this.zzafx = 1;
        this.zzaqu = -1;
        this.zzafy = 8000;
        this.zzaqv = 0L;
        this.zzaqw = 0L;
        this.zzaqy = true;
        this.zzage = "eng";
    }

    zzjx(zzjv zzjv0) {
    }

    private static List zza(zzom zzom0) throws zzhc {
        try {
            zzom0.zzbi(16);
            if(zzom0.zzja() != 0x31435657L) {
                return null;
            }
            int v = zzom0.getPosition() + 20;
            byte[] arr_b = zzom0.data;
            while(v < arr_b.length - 4) {
                if(arr_b[v] == 0 && arr_b[v + 1] == 0 && arr_b[v + 2] == 1 && arr_b[v + 3] == 15) {
                    return Collections.singletonList(Arrays.copyOfRange(arr_b, v, arr_b.length));
                }
                ++v;
            }
            throw new zzhc("Failed to find FourCC VC1 initialization data");
        }
        catch(ArrayIndexOutOfBoundsException unused_ex) {
            throw new zzhc("Error parsing FourCC VC1 codec private");
        }
    }

    public final void zza(zzji zzji0, int v) throws zzhc {
        zzou zzou0;
        float f;
        zzgz zzgz0;
        int v4;
        int v3;
        List list0;
        String s;
        int v2;
        int v1 = 3;
        switch(this.zzapv) {
            case "A_AAC": {
                v2 = 12;
                break;
            }
            case "A_AC3": {
                v2 = 15;
                break;
            }
            case "A_DTS": {
                v2 = 18;
                break;
            }
            case "A_DTS/EXPRESS": {
                v2 = 19;
                break;
            }
            case "A_DTS/LOSSLESS": {
                v2 = 20;
                break;
            }
            case "A_EAC3": {
                v2 = 16;
                break;
            }
            case "A_FLAC": {
                v2 = 21;
                break;
            }
            case "A_MPEG/L2": {
                v2 = 13;
                break;
            }
            case "A_MPEG/L3": {
                v2 = 14;
                break;
            }
            case "A_MS/ACM": {
                v2 = 22;
                break;
            }
            case "A_OPUS": {
                v2 = 11;
                break;
            }
            case "A_PCM/INT/LIT": {
                v2 = 23;
                break;
            }
            case "A_TRUEHD": {
                v2 = 17;
                break;
            }
            case "A_VORBIS": {
                v2 = 10;
                break;
            }
            case "S_DVBSUB": {
                v2 = 27;
                break;
            }
            case "S_HDMV/PGS": {
                v2 = 26;
                break;
            }
            case "S_TEXT/UTF8": {
                v2 = 24;
                break;
            }
            case "S_VOBSUB": {
                v2 = 25;
                break;
            }
            case "V_MPEG2": {
                v2 = 2;
                break;
            }
            case "V_MPEG4/ISO/AP": {
                v2 = 5;
                break;
            }
            case "V_MPEG4/ISO/ASP": {
                v2 = 4;
                break;
            }
            case "V_MPEG4/ISO/AVC": {
                v2 = 6;
                break;
            }
            case "V_MPEG4/ISO/SP": {
                v2 = 3;
                break;
            }
            case "V_MPEGH/ISO/HEVC": {
                v2 = 7;
                break;
            }
            case "V_MS/VFW/FOURCC": {
                v2 = 8;
                break;
            }
            case "V_THEORA": {
                v2 = 9;
                break;
            }
            case "V_VP8": {
                v2 = 0;
                break;
            }
            case "V_VP9": {
                v2 = 1;
                break;
            }
            default: {
                v2 = -1;
            }
        }
        byte[] arr_b = null;
        switch(v2) {
            case 0: {
                s = "video/x-vnd.on2.vp8";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 1: {
                s = "video/x-vnd.on2.vp9";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 2: {
                s = "video/mpeg2";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 3: 
            case 4: 
            case 5: {
                list0 = this.zzaqa == null ? null : Collections.singletonList(this.zzaqa);
                s = "video/mp4v-es";
                v3 = -1;
                v4 = -1;
                break;
            }
            case 6: {
                zzor zzor0 = zzor.zzf(new zzom(this.zzaqa));
                this.zzara = zzor0.zzara;
                s = "video/avc";
                list0 = zzor0.zzafp;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 7: {
                zzox zzox0 = zzox.zzh(new zzom(this.zzaqa));
                this.zzara = zzox0.zzara;
                s = "video/hevc";
                list0 = zzox0.zzafp;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 8: {
                List list1 = zzjx.zza(new zzom(this.zzaqa));
                if(list1 == null) {
                    Log.w("MatroskaExtractor", "Unsupported FourCC. Setting mimeType to video/x-unknown");
                    s = "video/x-unknown";
                    v3 = -1;
                    v4 = -1;
                    list0 = null;
                }
                else {
                    s = "video/wvc1";
                    v3 = -1;
                    v4 = -1;
                    list0 = list1;
                }
                break;
            }
            case 9: {
                s = "video/x-unknown";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 10: {
                s = "audio/vorbis";
                list0 = zzjx.zzd(this.zzaqa);
                v3 = 0x2000;
                v4 = -1;
                break;
            }
            case 11: {
                ArrayList arrayList0 = new ArrayList(3);
                arrayList0.add(this.zzaqa);
                arrayList0.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzaqv).array());
                arrayList0.add(ByteBuffer.allocate(8).order(ByteOrder.nativeOrder()).putLong(this.zzaqw).array());
                s = "audio/opus";
                list0 = arrayList0;
                v3 = 5760;
                v4 = -1;
                break;
            }
            case 12: {
                list0 = Collections.singletonList(this.zzaqa);
                s = "audio/mp4a-latm";
                v3 = -1;
                v4 = -1;
                break;
            }
            case 13: {
                s = "audio/mpeg-L2";
                list0 = null;
                v3 = 0x1000;
                v4 = -1;
                break;
            }
            case 14: {
                s = "audio/mpeg";
                list0 = null;
                v3 = 0x1000;
                v4 = -1;
                break;
            }
            case 15: {
                s = "audio/ac3";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 16: {
                s = "audio/eac3";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 17: {
                s = "audio/true-hd";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 18: 
            case 19: {
                s = "audio/vnd.dts";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 20: {
                s = "audio/vnd.dts.hd";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 21: {
                list0 = Collections.singletonList(this.zzaqa);
                s = "audio/x-flac";
                v3 = -1;
                v4 = -1;
                break;
            }
            case 22: {
                if(zzjx.zzb(new zzom(this.zzaqa))) {
                    int v5 = zzop.zzbk(this.zzaqu);
                    if(v5 == 0) {
                        Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.zzaqu + ". Setting mimeType to " + "audio/x-unknown");
                        s = "audio/x-unknown";
                        list0 = null;
                        v3 = -1;
                        v4 = -1;
                    }
                    else {
                        s = "audio/raw";
                        v4 = v5;
                        list0 = null;
                        v3 = -1;
                    }
                }
                else {
                    Log.w("MatroskaExtractor", "Non-PCM MS/ACM is unsupported. Setting mimeType to audio/x-unknown");
                    s = "audio/x-unknown";
                    list0 = null;
                    v3 = -1;
                    v4 = -1;
                }
                break;
            }
            case 23: {
                int v6 = zzop.zzbk(this.zzaqu);
                if(v6 == 0) {
                    Log.w("MatroskaExtractor", "Unsupported PCM bit depth: " + this.zzaqu + ". Setting mimeType to " + "audio/x-unknown");
                    s = "audio/x-unknown";
                    list0 = null;
                    v3 = -1;
                    v4 = -1;
                }
                else {
                    s = "audio/raw";
                    v4 = v6;
                    list0 = null;
                    v3 = -1;
                }
                break;
            }
            case 24: {
                s = "application/x-subrip";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 25: {
                list0 = Collections.singletonList(this.zzaqa);
                s = "application/vobsub";
                v3 = -1;
                v4 = -1;
                break;
            }
            case 26: {
                s = "application/pgs";
                list0 = null;
                v3 = -1;
                v4 = -1;
                break;
            }
            case 27: {
                byte[] arr_b1 = new byte[4];
                byte[] arr_b2 = this.zzaqa;
                arr_b1[0] = arr_b2[0];
                arr_b1[1] = arr_b2[1];
                arr_b1[2] = arr_b2[2];
                arr_b1[3] = arr_b2[3];
                list0 = Collections.singletonList(arr_b1);
                s = "application/dvbsubs";
                v3 = -1;
                v4 = -1;
                break;
            }
            default: {
                throw new zzhc("Unrecognized codec identifier.");
            }
        }
        int v7 = this.zzaqy | (this.zzaqx ? 2 : 0);
        if(zzoi.zzbi(s)) {
            zzgz0 = zzgz.zza(Integer.toString(v), s, null, -1, v3, this.zzafx, this.zzafy, v4, list0, this.zzafq, v7, this.zzage);
            v1 = 1;
        }
        else if(zzoi.zzbj(s)) {
            if(this.zzaqd == 0) {
                this.zzaqb = this.zzaqb == -1 ? this.width : this.zzaqb;
                this.zzaqc = this.zzaqc == -1 ? this.height : this.zzaqc;
            }
            int v8 = this.zzaqb;
            if(v8 == -1) {
                f = -1.0f;
            }
            else {
                int v9 = this.zzaqc;
                f = v9 == -1 ? -1.0f : ((float)(this.height * v8)) / ((float)(this.width * v9));
            }
            if(this.zzaqe) {
                if(this.zzaqk != -1.0f && this.zzaql != -1.0f && this.zzaqm != -1.0f && this.zzaqn != -1.0f && this.zzaqo != -1.0f && this.zzaqp != -1.0f && this.zzaqq != -1.0f && this.zzaqr != -1.0f && this.zzaqs != -1.0f && this.zzaqt != -1.0f) {
                    arr_b = new byte[25];
                    ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
                    byteBuffer0.put(0);
                    byteBuffer0.putShort(((short)(((int)(this.zzaqk * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaql * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqm * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqn * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqo * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqp * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqq * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqr * 50000.0f + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqs + 0.5f)))));
                    byteBuffer0.putShort(((short)(((int)(this.zzaqt + 0.5f)))));
                    byteBuffer0.putShort(((short)this.zzaqi));
                    byteBuffer0.putShort(((short)this.zzaqj));
                }
                zzou0 = new zzou(this.zzaqf, this.zzaqh, this.zzaqg, arr_b);
            }
            else {
                zzou0 = null;
            }
            zzgz0 = zzgz.zza(Integer.toString(v), s, null, -1, v3, this.width, this.height, -1.0f, list0, -1, f, this.zzafv, this.zzafu, zzou0, this.zzafq);
            v1 = 2;
        }
        else if("application/x-subrip".equals(s)) {
            zzgz0 = zzgz.zza(Integer.toString(v), s, null, -1, v7, this.zzage, this.zzafq);
        }
        else {
            if(!"application/vobsub".equals(s) && !"application/pgs".equals(s) && !"application/dvbsubs".equals(s)) {
                throw new zzhc("Unexpected MIME type.");
            }
            zzgz0 = zzgz.zza(Integer.toString(v), s, null, -1, list0, this.zzage, this.zzafq);
        }
        this.zzaqz = zzji0.zzc(this.number, v1);
        this.zzaqz.zze(zzgz0);
    }

    private static boolean zzb(zzom zzom0) throws zzhc {
        try {
            int v = zzom0.zziy();
            if(v == 1) {
                return true;
            }
            if(v != 0xFFFE) {
                return false;
            }
            zzom0.zzbh(24);
            return zzom0.readLong() == zzjw.zzgs().getMostSignificantBits() && zzom0.readLong() == zzjw.zzgs().getLeastSignificantBits();
        }
        catch(ArrayIndexOutOfBoundsException unused_ex) {
            throw new zzhc("Error parsing MS/ACM codec private");
        }
    }

    private static List zzd(byte[] arr_b) throws zzhc {
        try {
            if(arr_b[0] != 2) {
                throw new zzhc("Error parsing vorbis codec private");
            }
            int v = 1;
            int v1 = 0;
            while(arr_b[v] == -1) {
                v1 += 0xFF;
                ++v;
            }
            int v2 = v + 1;
            int v3 = v1 + arr_b[v];
            int v4 = 0;
            while(arr_b[v2] == -1) {
                v4 += 0xFF;
                ++v2;
            }
            int v5 = v4 + arr_b[v2];
            if(arr_b[v2 + 1] != 1) {
                throw new zzhc("Error parsing vorbis codec private");
            }
            byte[] arr_b1 = new byte[v3];
            System.arraycopy(arr_b, v2 + 1, arr_b1, 0, v3);
            int v6 = v2 + 1 + v3;
            if(arr_b[v6] != 3) {
                throw new zzhc("Error parsing vorbis codec private");
            }
            int v7 = v6 + v5;
            if(arr_b[v7] != 5) {
                throw new zzhc("Error parsing vorbis codec private");
            }
            byte[] arr_b2 = new byte[arr_b.length - v7];
            System.arraycopy(arr_b, v7, arr_b2, 0, arr_b.length - v7);
            List list0 = new ArrayList(2);
            list0.add(arr_b1);
            list0.add(arr_b2);
            return list0;
        }
        catch(ArrayIndexOutOfBoundsException unused_ex) {
            throw new zzhc("Error parsing vorbis codec private");
        }
    }
}

