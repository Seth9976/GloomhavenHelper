package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class zzkd {
    private static final int zzaui;
    private static final int zzauw;
    private static final int zzaux;
    private static final int zzauy;
    private static final int zzauz;
    private static final int zzava;
    private static final int zzavb;
    private static final int zzavc;

    static {
        zzkd.zzauw = zzop.zzbo("vide");
        zzkd.zzaux = zzop.zzbo("soun");
        zzkd.zzauy = zzop.zzbo("text");
        zzkd.zzauz = zzop.zzbo("sbtl");
        zzkd.zzava = zzop.zzbo("subt");
        zzkd.zzavb = zzop.zzbo("clcp");
        zzkd.zzavc = zzop.zzbo("cenc");
        zzkd.zzaui = zzop.zzbo("meta");
    }

    private static int zza(zzom zzom0, int v, int v1, zzki zzki0, int v2) {
        zzku zzku1;
        int v3 = zzom0.getPosition();
        while(true) {
            boolean z = false;
            if(v3 - v >= v1) {
                break;
            }
            zzom0.zzbh(v3);
            int v4 = zzom0.readInt();
            zzob.checkArgument(v4 > 0, "childAtomSize should be positive");
            if(zzom0.readInt() == zzkc.zzatc) {
                int v5 = v3 + 8;
                Pair pair0 = null;
                Integer integer0 = null;
                zzku zzku0 = null;
                boolean z1 = false;
                while(v5 - v3 < v4) {
                    zzom0.zzbh(v5);
                    int v6 = zzom0.readInt();
                    int v7 = zzom0.readInt();
                    if(v7 == zzkc.zzati) {
                        integer0 = zzom0.readInt();
                    }
                    else if(v7 == zzkc.zzatd) {
                        zzom0.zzbi(4);
                        z1 = zzom0.readInt() == zzkd.zzavc;
                    }
                    else if(v7 == zzkc.zzate) {
                        int v8 = v5 + 8;
                        while(true) {
                            zzku1 = null;
                            if(v8 - v5 < v6) {
                                zzom0.zzbh(v8);
                                int v9 = zzom0.readInt();
                                if(zzom0.readInt() == zzkc.zzatf) {
                                    zzom0.zzbi(6);
                                    boolean z2 = zzom0.readUnsignedByte() == 1;
                                    int v10 = zzom0.readUnsignedByte();
                                    byte[] arr_b = new byte[16];
                                    zzom0.zze(arr_b, 0, 16);
                                    zzku1 = new zzku(z2, v10, arr_b);
                                }
                                else {
                                    v8 += v9;
                                    continue;
                                }
                            }
                            break;
                        }
                        zzku0 = zzku1;
                    }
                    v5 += v6;
                }
                if(z1) {
                    zzob.checkArgument(integer0 != null, "frma atom is mandatory");
                    if(zzku0 != null) {
                        z = true;
                    }
                    zzob.checkArgument(z, "schi->tenc atom is mandatory");
                    pair0 = Pair.create(integer0, zzku0);
                }
                if(pair0 != null) {
                    zzki0.zzavn[v2] = (zzku)pair0.second;
                    return (int)(((Integer)pair0.first));
                }
            }
            v3 += v4;
        }
        return 0;
    }

    public static zzkr zza(zzkb zzkb0, zzke zzke0, long v, zziu zziu0, boolean z) throws zzhc {
        Pair pair6;
        int v43;
        boolean z4;
        int v44;
        boolean z3;
        zzkj zzkj2;
        Pair pair2;
        String s2;
        int v36;
        int v35;
        int v33;
        int v31;
        int v30;
        zzkj zzkj1;
        Pair pair1;
        long v29;
        List list0;
        long v32;
        String s1;
        byte[] arr_b4;
        boolean z2;
        int v27;
        int v14;
        long v9;
        int v2;
        zzkb zzkb1 = zzkb0.zzam(zzkc.zzasl);
        zzke zzke1 = zzkb1.zzal(zzkc.zzasz);
        zzke1.zzavd.zzbh(16);
        int v1 = zzke1.zzavd.readInt();
        if(v1 == zzkd.zzaux) {
            v2 = 1;
        }
        else if(v1 == zzkd.zzauw) {
            v2 = 2;
        }
        else if(v1 == zzkd.zzauy || v1 == zzkd.zzauz || v1 == zzkd.zzava || v1 == zzkd.zzavb) {
            v2 = 3;
        }
        else if(v1 == zzkd.zzaui) {
            v2 = 4;
        }
        else {
            v2 = -1;
        }
        if(v2 == -1) {
            return null;
        }
        zzom zzom0 = zzkb0.zzal(zzkc.zzasv).zzavd;
        zzom0.zzbh(8);
        int v3 = zzkc.zzan(zzom0.readInt());
        zzom0.zzbi((v3 == 0 ? 8 : 16));
        int v4 = zzom0.readInt();
        zzom0.zzbi(4);
        int v5 = zzom0.getPosition();
        int v6 = v3 == 0 ? 4 : 8;
        for(int v7 = 0; true; ++v7) {
            boolean z1 = true;
            if(v7 >= v6) {
                break;
            }
            if(zzom0.data[v5 + v7] != -1) {
                z1 = false;
                break;
            }
        }
        long v8 = 0x8000000000000001L;
        if(z1) {
            zzom0.zzbi(v6);
            v9 = 0x8000000000000001L;
        }
        else {
            v9 = v3 == 0 ? zzom0.zziz() : zzom0.zzjd();
            if(v9 == 0L) {
                v9 = 0x8000000000000001L;
            }
        }
        zzom0.zzbi(16);
        int v10 = zzom0.readInt();
        int v11 = zzom0.readInt();
        zzom0.zzbi(4);
        int v12 = zzom0.readInt();
        int v13 = zzom0.readInt();
        if(v10 == 0 && v11 == 0x10000 && v12 == 0xFFFF0000 && v13 == 0) {
            v14 = 90;
        }
        else if(v10 != 0 || v11 != 0xFFFF0000 || v12 != 0x10000 || v13 != 0) {
            v14 = v10 != 0xFFFF0000 || v11 != 0 || v12 != 0 || v13 != 0xFFFF0000 ? 0 : 180;
        }
        else {
            v14 = 270;
        }
        zzkj zzkj0 = new zzkj(v4, v9, v14);
        long v15 = zzkj0.zzcw;
        zzke0.zzavd.zzbh(8);
        int v16 = zzkc.zzan(zzke0.zzavd.readInt()) == 0 ? 8 : 16;
        zzke0.zzavd.zzbi(v16);
        long v17 = zzke0.zzavd.zziz();
        if(v15 != 0x8000000000000001L) {
            v8 = zzop.zza(v15, 1000000L, v17);
        }
        zzkb zzkb2 = zzkb1.zzam(zzkc.zzasm).zzam(zzkc.zzasn);
        zzom zzom1 = zzkb1.zzal(zzkc.zzasy).zzavd;
        zzom1.zzbh(8);
        int v18 = zzkc.zzan(zzom1.readInt());
        zzom1.zzbi((v18 == 0 ? 8 : 16));
        long v19 = zzom1.zziz();
        zzom1.zzbi((v18 == 0 ? 4 : 8));
        int v20 = zzom1.readUnsignedShort();
        Pair pair0 = Pair.create(v19, ((char)((v20 >> 10 & 0x1F) + 0x60)) + ((char)((v20 >> 5 & 0x1F) + 0x60)) + ((char)((v20 & 0x1F) + 0x60)));
        zzom zzom2 = zzkb2.zzal(zzkc.zzata).zzavd;
        int v21 = zzkj0.id;
        int v22 = zzkj0.zzafs;
        String s = (String)pair0.second;
        zzom2.zzbh(12);
        int v23 = zzom2.readInt();
        zzki zzki0 = new zzki(v23);
        int v24 = 0;
        while(v24 < v23) {
            int v25 = zzom2.getPosition();
            int v26 = zzom2.readInt();
            if(v26 > 0) {
                v27 = v23;
                z2 = true;
            }
            else {
                v27 = v23;
                z2 = false;
            }
            zzob.checkArgument(z2, "childAtomSize should be positive");
            int v28 = zzom2.readInt();
            if(v28 == zzkc.zzari || v28 == zzkc.zzarj || v28 == zzkc.zzatg || v28 == zzkc.zzats || v28 == zzkc.zzark || v28 == zzkc.zzarl || v28 == zzkc.zzarm || v28 == zzkc.zzaur || v28 == zzkc.zzaus) {
                pair1 = pair0;
                zzkj1 = zzkj0;
                v29 = v17;
                v30 = v22;
                v31 = v2;
                zzom2.zzbh(v25 + 16);
                zzom2.zzbi(16);
                int v46 = zzom2.readUnsignedShort();
                int v47 = zzom2.readUnsignedShort();
                zzom2.zzbi(50);
                int v48 = zzom2.getPosition();
                if(v28 == zzkc.zzatg) {
                    v28 = zzkd.zza(zzom2, v25, v26, zzki0, v24);
                    zzom2.zzbh(v48);
                }
                boolean z5 = false;
                String s4 = null;
                List list1 = null;
                float f1 = 1.0f;
                byte[] arr_b3 = null;
                int v49 = -1;
                while(v48 - v25 < v26) {
                    zzom2.zzbh(v48);
                    int v50 = zzom2.getPosition();
                    int v51 = zzom2.readInt();
                    if(v51 == 0 && zzom2.getPosition() - v25 == v26) {
                        break;
                    }
                    zzob.checkArgument(v51 > 0, "childAtomSize should be positive");
                    int v52 = zzom2.readInt();
                    if(v52 == zzkc.zzaso) {
                        zzob.checkState(s4 == null);
                        zzom2.zzbh(v50 + 8);
                        zzor zzor0 = zzor.zzf(zzom2);
                        List list2 = zzor0.zzafp;
                        zzki0.zzara = zzor0.zzara;
                        if(!z5) {
                            f1 = zzor0.zzbgt;
                        }
                        s4 = "video/avc";
                        list1 = list2;
                    }
                    else if(v52 == zzkc.zzasp) {
                        zzob.checkState(s4 == null);
                        zzom2.zzbh(v50 + 8);
                        zzox zzox0 = zzox.zzh(zzom2);
                        zzki0.zzara = zzox0.zzara;
                        s4 = "video/hevc";
                        list1 = zzox0.zzafp;
                    }
                    else if(v52 == zzkc.zzaut) {
                        zzob.checkState(s4 == null);
                        s4 = v28 == zzkc.zzaur ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                    }
                    else if(v52 == zzkc.zzarn) {
                        zzob.checkState(s4 == null);
                        s4 = "video/3gpp";
                    }
                    else if(v52 == zzkc.zzasq) {
                        zzob.checkState(s4 == null);
                        Pair pair5 = zzkd.zzb(zzom2, v50);
                        String s5 = (String)pair5.first;
                        list1 = Collections.singletonList(((byte[])pair5.second));
                        s4 = s5;
                    }
                    else if(v52 == zzkc.zzatp) {
                        zzom2.zzbh(v50 + 8);
                        f1 = ((float)zzom2.zzjc()) / ((float)zzom2.zzjc());
                        z5 = true;
                    }
                    else if(v52 == zzkc.zzaup) {
                        int v53 = v50 + 8;
                        while(true) {
                            arr_b4 = null;
                            if(v53 - v50 < v51) {
                                zzom2.zzbh(v53);
                                int v54 = zzom2.readInt();
                                if(zzom2.readInt() == zzkc.zzauq) {
                                    arr_b4 = Arrays.copyOfRange(zzom2.data, v53, v54 + v53);
                                }
                                else {
                                    v53 += v54;
                                    continue;
                                }
                            }
                            break;
                        }
                        arr_b3 = arr_b4;
                    }
                    else if(v52 == zzkc.zzauo) {
                        int v55 = zzom2.readUnsignedByte();
                        zzom2.zzbi(3);
                        if(v55 == 0) {
                            switch(zzom2.readUnsignedByte()) {
                                case 0: {
                                    v49 = 0;
                                    break;
                                }
                                case 1: {
                                    v49 = 1;
                                    break;
                                }
                                case 2: {
                                    v49 = 2;
                                    break;
                                }
                                case 3: {
                                    v49 = 3;
                                }
                            }
                        }
                    }
                    v48 += v51;
                }
                if(s4 != null) {
                    zzki0.zzagi = zzgz.zza(Integer.toString(v21), s4, null, -1, -1, v46, v47, -1.0f, list1, v30, f1, arr_b3, v49, null, null);
                }
            }
            else if(v28 != zzkc.zzarp && v28 != zzkc.zzath && v28 != zzkc.zzaru && v28 != zzkc.zzarw && v28 != zzkc.zzary && v28 != zzkc.zzasb && v28 != zzkc.zzarz && v28 != zzkc.zzasa && v28 != zzkc.zzauf && v28 != zzkc.zzaug && v28 != zzkc.zzars && v28 != zzkc.zzart && v28 != zzkc.zzarq && v28 != zzkc.zzauv) {
                if(v28 == zzkc.zzatq || v28 == zzkc.zzaub || v28 == zzkc.zzauc || v28 == zzkc.zzaud || v28 == zzkc.zzaue) {
                    v29 = v17;
                    zzom2.zzbh(v25 + 16);
                    if(v28 == zzkc.zzatq) {
                        s1 = "application/ttml+xml";
                        v32 = 0x7FFFFFFFFFFFFFFFL;
                        list0 = null;
                        goto label_139;
                    }
                    else if(v28 == zzkc.zzaub) {
                        byte[] arr_b = new byte[v26 - 16];
                        zzom2.zze(arr_b, 0, v26 - 16);
                        list0 = Collections.singletonList(arr_b);
                        s1 = "application/x-quicktime-tx3g";
                        v32 = 0x7FFFFFFFFFFFFFFFL;
                        goto label_139;
                    }
                    else if(v28 == zzkc.zzauc) {
                        s1 = "application/x-mp4-vtt";
                        v32 = 0x7FFFFFFFFFFFFFFFL;
                        list0 = null;
                        goto label_139;
                    }
                    else if(v28 == zzkc.zzaud) {
                        s1 = "application/ttml+xml";
                        v32 = 0L;
                        list0 = null;
                        goto label_139;
                    }
                    else {
                        if(v28 != zzkc.zzaue) {
                            throw new IllegalStateException();
                        }
                        zzki0.zzavo = 1;
                        s1 = "application/x-mp4-cea-608";
                        v32 = 0x7FFFFFFFFFFFFFFFL;
                        list0 = null;
                    label_139:
                        zzki0.zzagi = zzgz.zza(Integer.toString(v21), s1, null, -1, 0, s, -1, null, v32, list0);
                        pair1 = pair0;
                        zzkj1 = zzkj0;
                        v30 = v22;
                        v31 = v2;
                        goto label_387;
                    }
                }
                else {
                    if(v28 == zzkc.zzauu) {
                        v29 = v17;
                        zzki0.zzagi = zzgz.zza(Integer.toString(v21), "application/x-camera-motion", null, -1, null);
                    }
                    else {
                        v29 = v17;
                    }
                    pair1 = pair0;
                    zzkj1 = zzkj0;
                    v30 = v22;
                    v31 = v2;
                    goto label_387;
                }
                goto label_146;
            }
            else {
            label_146:
                v29 = v17;
                zzom2.zzbh(v25 + 16);
                if(z) {
                    v33 = zzom2.readUnsignedShort();
                    zzom2.zzbi(6);
                }
                else {
                    zzom2.zzbi(8);
                    v33 = 0;
                }
                switch(v33) {
                    case 0: 
                    case 1: {
                        int v34 = zzom2.readUnsignedShort();
                        zzom2.zzbi(6);
                        v35 = zzom2.zzjb();
                        v36 = v34;
                        if(v33 == 1) {
                            zzom2.zzbi(16);
                        }
                        break;
                    }
                    case 2: {
                        zzom2.zzbi(16);
                        double f = Double.longBitsToDouble(zzom2.readLong());
                        int v37 = zzom2.zzjc();
                        zzom2.zzbi(20);
                        v36 = v37;
                        v35 = (int)Math.round(f);
                        break;
                    }
                    default: {
                        pair1 = pair0;
                        zzkj1 = zzkj0;
                        v30 = v22;
                        v31 = v2;
                        goto label_387;
                    }
                }
                int v38 = zzom2.getPosition();
                if(v28 == zzkc.zzath) {
                    v28 = zzkd.zza(zzom2, v25, v26, zzki0, v24);
                    zzom2.zzbh(v38);
                }
                if(v28 == zzkc.zzaru) {
                    s2 = "audio/ac3";
                }
                else if(v28 == zzkc.zzarw) {
                    s2 = "audio/eac3";
                }
                else if(v28 == zzkc.zzary) {
                    s2 = "audio/vnd.dts";
                }
                else if(v28 == zzkc.zzarz || v28 == zzkc.zzasa) {
                    s2 = "audio/vnd.dts.hd";
                }
                else if(v28 == zzkc.zzasb) {
                    s2 = "audio/vnd.dts.hd;profile=lbr";
                }
                else if(v28 == zzkc.zzauf) {
                    s2 = "audio/3gpp";
                }
                else if(v28 == zzkc.zzaug) {
                    s2 = "audio/amr-wb";
                }
                else if(v28 == zzkc.zzars || v28 == zzkc.zzart) {
                    s2 = "audio/raw";
                }
                else if(v28 == zzkc.zzarq) {
                    s2 = "audio/mpeg";
                }
                else if(v28 == zzkc.zzauv) {
                    s2 = "audio/alac";
                }
                else {
                    s2 = null;
                }
                int v39 = v38;
                v31 = v2;
                int v40 = v36;
                byte[] arr_b1 = null;
                while(v39 - v25 < v26) {
                    zzom2.zzbh(v39);
                    int v41 = zzom2.readInt();
                    if(v41 > 0) {
                        pair2 = pair0;
                        zzkj2 = zzkj0;
                        z3 = true;
                    }
                    else {
                        pair2 = pair0;
                        zzkj2 = zzkj0;
                        z3 = false;
                    }
                    zzob.checkArgument(z3, "childAtomSize should be positive");
                    int v42 = zzom2.readInt();
                    if(v42 == zzkc.zzasq || z && v42 == zzkc.zzarr) {
                        if(v42 == zzkc.zzasq) {
                            v44 = v39;
                            v43 = v22;
                        }
                        else {
                            v44 = zzom2.getPosition();
                            while(true) {
                                if(v44 - v39 < v41) {
                                    zzom2.zzbh(v44);
                                    int v45 = zzom2.readInt();
                                    if(v45 > 0) {
                                        v43 = v22;
                                        z4 = true;
                                    }
                                    else {
                                        v43 = v22;
                                        z4 = false;
                                    }
                                    zzob.checkArgument(z4, "childAtomSize should be positive");
                                    if(zzom2.readInt() == zzkc.zzasq) {
                                        break;
                                    }
                                    else {
                                        v44 += v45;
                                        v22 = v43;
                                        continue;
                                    }
                                }
                                v43 = v22;
                                v44 = -1;
                                break;
                            }
                        }
                        if(v44 != -1) {
                            Pair pair3 = zzkd.zzb(zzom2, v44);
                            String s3 = (String)pair3.first;
                            arr_b1 = (byte[])pair3.second;
                            if("audio/mp4a-latm".equals(s3)) {
                                Pair pair4 = zzoe.zze(arr_b1);
                                s2 = s3;
                                v35 = (int)(((Integer)pair4.first));
                                v40 = (int)(((Integer)pair4.second));
                            }
                            else {
                                s2 = s3;
                            }
                        }
                    }
                    else {
                        if(v42 == zzkc.zzarv) {
                            zzom2.zzbh(v39 + 8);
                            zzki0.zzagi = zzhn.zza(zzom2, Integer.toString(v21), s, null);
                        }
                        else if(v42 == zzkc.zzarx) {
                            zzom2.zzbh(v39 + 8);
                            zzki0.zzagi = zzhn.zzb(zzom2, Integer.toString(v21), s, null);
                        }
                        else if(v42 == zzkc.zzasc) {
                            zzki0.zzagi = zzgz.zza(Integer.toString(v21), s2, null, -1, -1, v40, v35, null, null, 0, s);
                        }
                        else if(v42 == zzkc.zzauv) {
                            byte[] arr_b2 = new byte[v41];
                            zzom2.zzbh(v39);
                            zzom2.zze(arr_b2, 0, v41);
                            arr_b1 = arr_b2;
                            v43 = v22;
                            goto label_279;
                        }
                        v43 = v22;
                    }
                label_279:
                    v39 += v41;
                    pair0 = pair2;
                    zzkj0 = zzkj2;
                    v22 = v43;
                }
                pair1 = pair0;
                zzkj1 = zzkj0;
                v30 = v22;
                if(zzki0.zzagi == null && s2 != null) {
                    zzki0.zzagi = zzgz.zza(Integer.toString(v21), s2, null, -1, -1, v40, v35, ("audio/raw".equals(s2) ? 2 : -1), (arr_b1 == null ? null : Collections.singletonList(arr_b1)), null, 0, s);
                }
            }
        label_387:
            zzom2.zzbh(v25 + v26);
            ++v24;
            v23 = v27;
            v17 = v29;
            v2 = v31;
            pair0 = pair1;
            zzkj0 = zzkj1;
            v22 = v30;
        }
        zzkb zzkb3 = zzkb0.zzam(zzkc.zzasw);
        if(zzkb3 != null) {
            zzke zzke2 = zzkb3.zzal(zzkc.zzasx);
            if(zzke2 != null) {
                zzom zzom3 = zzke2.zzavd;
                zzom3.zzbh(8);
                int v56 = zzkc.zzan(zzom3.readInt());
                int v57 = zzom3.zzjc();
                long[] arr_v = new long[v57];
                long[] arr_v1 = new long[v57];
                for(int v58 = 0; v58 < v57; ++v58) {
                    arr_v[v58] = v56 == 1 ? zzom3.zzjd() : zzom3.zziz();
                    arr_v1[v58] = v56 == 1 ? zzom3.readLong() : ((long)zzom3.readInt());
                    if(zzom3.readShort() != 1) {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                    zzom3.zzbi(2);
                }
                pair6 = Pair.create(arr_v, arr_v1);
                return zzki0.zzagi == null ? null : new zzkr(zzkj0.id, v2, ((long)(((Long)pair0.first))), v17, v8, zzki0.zzagi, zzki0.zzavo, zzki0.zzavn, zzki0.zzara, ((long[])pair6.first), ((long[])pair6.second));
            }
        }
        pair6 = Pair.create(null, null);
        return zzki0.zzagi == null ? null : new zzkr(zzkj0.id, v2, ((long)(((Long)pair0.first))), v17, v8, zzki0.zzagi, zzki0.zzavo, zzki0.zzavn, zzki0.zzara, ((long[])pair6.first), ((long[])pair6.second));
    }

    public static zzkt zza(zzkr zzkr0, zzkb zzkb0, zzjk zzjk0) throws zzhc {
        int[] arr_v19;
        long[] arr_v18;
        int[] arr_v17;
        boolean z2;
        int[] arr_v16;
        int v64;
        int[] arr_v10;
        int v32;
        long v31;
        int v30;
        int v28;
        int v27;
        int v26;
        int v25;
        zzkh zzkh1;
        int v24;
        int v23;
        int[] arr_v3;
        long[] arr_v2;
        int[] arr_v1;
        long[] arr_v;
        int v6;
        int v4;
        boolean z;
        zzkh zzkh0;
        zzke zzke0 = zzkb0.zzal(zzkc.zzatx);
        if(zzke0 == null) {
            zzke zzke1 = zzkb0.zzal(zzkc.zzaty);
            if(zzke1 == null) {
                throw new zzhc("Track has no sample table size information");
            }
            zzkh0 = new zzkk(zzke1);
        }
        else {
            zzkh0 = new zzkh(zzke0);
        }
        int v = zzkh0.zzgu();
        if(v == 0) {
            return new zzkt(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        zzke zzke2 = zzkb0.zzal(zzkc.zzatz);
        if(zzke2 == null) {
            zzke2 = zzkb0.zzal(zzkc.zzaua);
            z = true;
        }
        else {
            z = false;
        }
        zzom zzom0 = zzkb0.zzal(zzkc.zzatw).zzavd;
        zzom zzom1 = zzkb0.zzal(zzkc.zzatt).zzavd;
        zzke zzke3 = zzkb0.zzal(zzkc.zzatu);
        zzom zzom2 = zzke3 == null ? null : zzke3.zzavd;
        zzke zzke4 = zzkb0.zzal(zzkc.zzatv);
        zzom zzom3 = zzke4 == null ? null : zzke4.zzavd;
        zzkg zzkg0 = new zzkg(zzom0, zzke2.zzavd, z);
        zzom1.zzbh(12);
        int v1 = zzom1.zzjc();
        int v2 = zzom1.zzjc();
        int v3 = zzom1.zzjc();
        if(zzom3 == null) {
            v4 = 0;
        }
        else {
            zzom3.zzbh(12);
            v4 = zzom3.zzjc();
        }
        int v5 = -1;
        if(zzom2 == null) {
            v6 = 0;
        }
        else {
            zzom2.zzbh(12);
            v6 = zzom2.zzjc();
            if(v6 > 0) {
                v5 = zzom2.zzjc() - 1;
            }
            else {
                zzom2 = null;
            }
        }
        long v7 = 0L;
        if(!zzkh0.zzgw() || !"audio/raw".equals(zzkr0.zzagi.zzafn) || v1 - 1 != 0 || v4 != 0 || v6 != 0) {
            arr_v = new long[v];
            arr_v1 = new int[v];
            arr_v2 = new long[v];
            arr_v3 = new int[v];
            int v8 = v6;
            int v9 = v3;
            int v10 = v4;
            int v11 = v5;
            long v12 = 0L;
            long v13 = 0L;
            int v14 = 0;
            int v15 = 0;
            int v16 = 0;
            int v17 = 0;
            int v18 = v2;
            int v19 = v1 - 1;
            int v20 = 0;
            while(v20 < v) {
                long v21 = v12;
                int v22;
                for(v22 = v14; v22 == 0; v22 = zzkg0.zzave) {
                    zzob.checkState(zzkg0.zzgx());
                    v21 = zzkg0.zzavf;
                }
                if(zzom3 != null) {
                    while(v17 == 0 && v10 > 0) {
                        v17 = zzom3.zzjc();
                        v16 = zzom3.readInt();
                        --v10;
                    }
                    --v17;
                }
                v23 = v16;
                arr_v[v20] = v21;
                arr_v1[v20] = zzkh0.zzgv();
                if(arr_v1[v20] > v15) {
                    v24 = v;
                    v15 = arr_v1[v20];
                }
                else {
                    v24 = v;
                }
                zzkh1 = zzkh0;
                arr_v2[v20] = v13 + ((long)v23);
                arr_v3[v20] = zzom2 == null ? 1 : 0;
                if(v20 == v11) {
                    arr_v3[v20] = 1;
                    if(v8 - 1 > 0) {
                        v25 = v8 - 1;
                        v26 = zzom2.zzjc() - 1;
                    }
                    else {
                        v25 = v8 - 1;
                        v26 = v11;
                    }
                }
                else {
                    v25 = v8;
                    v26 = v11;
                }
                v27 = v9;
                v13 += (long)v27;
                --v18;
                if(v18 != 0 || v19 <= 0) {
                    v28 = v19;
                }
                else {
                    v28 = v19 - 1;
                    v18 = zzom1.zzjc();
                    v27 = zzom1.zzjc();
                }
                long v29 = v21 + ((long)arr_v1[v20]);
                ++v20;
                v11 = v26;
                v = v24;
                v14 = v22 - 1;
                v16 = v23;
                v19 = v28;
                v12 = v29;
                v9 = v27;
                v8 = v25;
                zzkh0 = zzkh1;
            }
            v30 = v;
            zzob.checkArgument(v17 == 0);
            while(v10 > 0) {
                zzob.checkArgument(zzom3.zzjc() == 0);
                zzom3.readInt();
                --v10;
            }
            if(v8 != 0 || v18 != 0 || v14 != 0 || v19 != 0) {
                Log.w("AtomParsers", "Inconsistent stbl box for track " + zzkr0.id + ": remainingSynchronizationSamples " + v8 + ", remainingSamplesAtTimestampDelta " + v18 + ", remainingSamplesInChunk " + v14 + ", remainingTimestampDeltaChanges " + v19);
            }
            v31 = v13;
            v32 = v15;
        }
        else {
            v30 = v;
            long[] arr_v4 = new long[zzkg0.length];
            int[] arr_v5 = new int[zzkg0.length];
            while(zzkg0.zzgx()) {
                arr_v4[zzkg0.index] = zzkg0.zzavf;
                arr_v5[zzkg0.index] = zzkg0.zzave;
            }
            int v33 = zzkh0.zzgv();
            int v35 = 0;
            for(int v34 = 0; v34 < arr_v5.length; ++v34) {
                v35 += zzop.zzf(arr_v5[v34], 0x2000 / v33);
            }
            long[] arr_v6 = new long[v35];
            int[] arr_v7 = new int[v35];
            long[] arr_v8 = new long[v35];
            int[] arr_v9 = new int[v35];
            int v36 = 0;
            int v39 = 0;
            for(int v37 = 0; v36 < arr_v5.length; v37 = v42) {
                int v40 = arr_v5[v36];
                long v41 = arr_v4[v36];
                int v42 = v37;
                int v43 = v39;
                for(int v38 = 0; v40 > 0; ++v38) {
                    int v44 = Math.min(0x2000 / v33, v40);
                    arr_v6[v38] = v41;
                    arr_v7[v38] = v33 * v44;
                    v43 = Math.max(v43, arr_v7[v38]);
                    arr_v8[v38] = ((long)v42) * ((long)v3);
                    arr_v9[v38] = 1;
                    v41 += (long)arr_v7[v38];
                    v42 += v44;
                    v40 -= v44;
                }
                ++v36;
                v39 = v43;
            }
            zzkl zzkl0 = new zzkl(arr_v6, arr_v7, v39, arr_v8, arr_v9, null);
            arr_v = zzkl0.zzanf;
            arr_v1 = zzkl0.zzane;
            arr_v2 = zzkl0.zzavt;
            arr_v3 = zzkl0.zzavu;
            v32 = zzkl0.zzavs;
            v31 = 0L;
        }
        if(zzkr0.zzaxp != null && !zzjk0.zzgq()) {
            if(zzkr0.zzaxp.length == 1 && zzkr0.type == 1 && arr_v2.length >= 2) {
                long v45 = zzkr0.zzaxq[0];
                long v46 = zzop.zza(zzkr0.zzaxp[0], zzkr0.zzcv, zzkr0.zzaxm) + v45;
                if(arr_v2[0] <= v45 && v45 < arr_v2[1] && arr_v2[arr_v2.length - 1] < v46 && v46 <= v31) {
                    long v47 = zzop.zza(v45 - arr_v2[0], zzkr0.zzagi.zzafy, zzkr0.zzcv);
                    long v48 = zzop.zza(v31 - v46, zzkr0.zzagi.zzafy, zzkr0.zzcv);
                    if((v47 != 0L || v48 != 0L) && v47 <= 0x7FFFFFFFL && v48 <= 0x7FFFFFFFL) {
                        zzjk0.zzaga = (int)v47;
                        zzjk0.zzagb = (int)v48;
                        zzop.zza(arr_v2, 1000000L, zzkr0.zzcv);
                        return new zzkt(arr_v, arr_v1, v32, arr_v2, arr_v3);
                    }
                }
            }
            if(zzkr0.zzaxp.length == 1 && zzkr0.zzaxp[0] == 0L) {
                for(int v49 = 0; v49 < arr_v2.length; ++v49) {
                    arr_v2[v49] = zzop.zza(arr_v2[v49] - zzkr0.zzaxq[0], 1000000L, zzkr0.zzcv);
                }
                return new zzkt(arr_v, arr_v1, v32, arr_v2, arr_v3);
            }
            boolean z1 = zzkr0.type == 1;
            int v50 = 0;
            int v51 = 0;
            int v52 = 0;
            int v53 = 0;
            while(v50 < zzkr0.zzaxp.length) {
                long v54 = zzkr0.zzaxq[v50];
                if(v54 == -1L) {
                    arr_v10 = arr_v1;
                }
                else {
                    arr_v10 = arr_v1;
                    long v55 = zzop.zza(zzkr0.zzaxp[v50], zzkr0.zzcv, zzkr0.zzaxm);
                    int v56 = zzop.zzb(arr_v2, v54, true, true);
                    int v57 = zzop.zzb(arr_v2, v54 + v55, z1, false);
                    v52 += v57 - v56;
                    v51 |= (v53 == v56 ? 0 : 1);
                    v53 = v57;
                }
                ++v50;
                arr_v1 = arr_v10;
            }
            int[] arr_v11 = arr_v1;
            int v58 = (v52 == v30 ? 0 : 1) | v51;
            long[] arr_v12 = v58 == 0 ? arr_v : new long[v52];
            int[] arr_v13 = v58 == 0 ? arr_v11 : new int[v52];
            if(v58 != 0) {
                v32 = 0;
            }
            int[] arr_v14 = v58 == 0 ? arr_v3 : new int[v52];
            long[] arr_v15 = new long[v52];
            int v59 = v32;
            int v60 = 0;
            int v61 = 0;
            while(v60 < zzkr0.zzaxp.length) {
                long v62 = zzkr0.zzaxq[v60];
                long v63 = zzkr0.zzaxp[v60];
                if(v62 == -1L) {
                    arr_v16 = arr_v11;
                    z2 = z1;
                    arr_v18 = arr_v;
                    arr_v19 = arr_v3;
                    arr_v17 = arr_v14;
                    v64 = v60;
                }
                else {
                    v64 = v60;
                    long v65 = zzop.zza(v63, zzkr0.zzcv, zzkr0.zzaxm);
                    int v66 = zzop.zzb(arr_v2, v62, true, true);
                    int v67 = zzop.zzb(arr_v2, v65 + v62, z1, false);
                    if(v58 == 0) {
                        arr_v16 = arr_v11;
                        z2 = z1;
                        arr_v17 = arr_v14;
                    }
                    else {
                        int v68 = v67 - v66;
                        System.arraycopy(arr_v, v66, arr_v12, v61, v68);
                        arr_v16 = arr_v11;
                        System.arraycopy(arr_v16, v66, arr_v13, v61, v68);
                        z2 = z1;
                        arr_v17 = arr_v14;
                        System.arraycopy(arr_v3, v66, arr_v17, v61, v68);
                    }
                    int v69 = v59;
                    while(v66 < v67) {
                        arr_v15[v61] = zzop.zza(v7, 1000000L, zzkr0.zzaxm) + zzop.zza(arr_v2[v66] - v62, 1000000L, zzkr0.zzcv);
                        if(v58 != 0 && arr_v13[v61] > v69) {
                            v69 = arr_v16[v66];
                        }
                        ++v61;
                        ++v66;
                    }
                    arr_v18 = arr_v;
                    arr_v19 = arr_v3;
                    v59 = v69;
                }
                v7 += v63;
                v60 = v64 + 1;
                arr_v14 = arr_v17;
                arr_v = arr_v18;
                arr_v3 = arr_v19;
                z1 = z2;
                arr_v11 = arr_v16;
            }
            int v71 = 0;
            for(int v70 = 0; v70 < arr_v14.length && v71 == 0; ++v70) {
                v71 = (arr_v14[v70] & 1) == 0 ? 0 : 1;
            }
            if(v71 == 0) {
                throw new zzhc("The edited sample sequence does not contain a sync sample.");
            }
            return new zzkt(arr_v12, arr_v13, v59, arr_v15, arr_v14);
        }
        zzop.zza(arr_v2, 1000000L, zzkr0.zzcv);
        return new zzkt(arr_v, arr_v1, v32, arr_v2, arr_v3);
    }

    public static zzlh zza(zzke zzke0, boolean z) {
        if(z) {
            return null;
        }
        zzom zzom0 = zzke0.zzavd;
        zzom0.zzbh(8);
        while(zzom0.zzix() >= 8) {
            int v = zzom0.getPosition();
            int v1 = zzom0.readInt();
            if(zzom0.readInt() == zzkc.zzaui) {
                zzom0.zzbh(v);
                zzom0.zzbi(12);
                while(zzom0.getPosition() < v + v1) {
                    int v2 = zzom0.getPosition();
                    int v3 = zzom0.readInt();
                    if(zzom0.readInt() == zzkc.zzauj) {
                        zzom0.zzbh(v2);
                        zzom0.zzbi(8);
                        ArrayList arrayList0 = new ArrayList();
                        while(zzom0.getPosition() < v2 + v3) {
                            zza zzlh$zza0 = zzko.zzd(zzom0);
                            if(zzlh$zza0 != null) {
                                arrayList0.add(zzlh$zza0);
                            }
                        }
                        return arrayList0.isEmpty() ? null : new zzlh(arrayList0);
                    }
                    zzom0.zzbi(v3 - 8);
                }
                return null;
            }
            zzom0.zzbi(v1 - 8);
        }
        return null;
    }

    private static Pair zzb(zzom zzom0, int v) {
        zzom0.zzbh(v + 12);
        zzom0.zzbi(1);
        zzkd.zzc(zzom0);
        zzom0.zzbi(2);
        int v1 = zzom0.readUnsignedByte();
        if((v1 & 0x80) != 0) {
            zzom0.zzbi(2);
        }
        if((v1 & 0x40) != 0) {
            zzom0.zzbi(zzom0.readUnsignedShort());
        }
        if((v1 & 0x20) != 0) {
            zzom0.zzbi(2);
        }
        zzom0.zzbi(1);
        zzkd.zzc(zzom0);
        String s = null;
        switch(zzom0.readUnsignedByte()) {
            case 0x20: {
                s = "video/mp4v-es";
                break;
            }
            case 33: {
                s = "video/avc";
                break;
            }
            case 35: {
                s = "video/hevc";
                break;
            }
            case 0x40: 
            case 102: 
            case 103: 
            case 104: {
                s = "audio/mp4a-latm";
                break;
            }
            case 107: {
                return Pair.create("audio/mpeg", null);
            }
            case 0xA5: {
                s = "audio/ac3";
                break;
            }
            case 0xA6: {
                s = "audio/eac3";
                break;
            }
            case 170: 
            case 0xAB: {
                return Pair.create("audio/vnd.dts.hd", null);
            }
            case 0xA9: 
            case 0xAC: {
                return Pair.create("audio/vnd.dts", null);
            }
        }
        zzom0.zzbi(12);
        zzom0.zzbi(1);
        int v2 = zzkd.zzc(zzom0);
        byte[] arr_b = new byte[v2];
        zzom0.zze(arr_b, 0, v2);
        return Pair.create(s, arr_b);
    }

    private static int zzc(zzom zzom0) {
        int v = zzom0.readUnsignedByte();
        int v1;
        for(v1 = v & 0x7F; (v & 0x80) == 0x80; v1 = v1 << 7 | v & 0x7F) {
            v = zzom0.readUnsignedByte();
        }
        return v1;
    }
}

