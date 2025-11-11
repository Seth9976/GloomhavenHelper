package com.google.android.gms.internal.ads;

import android.graphics.Point;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public final class zzmx extends zznc {
    private static final int[] zzbdq;
    private final zzng zzbdr;
    private final AtomicReference zzbds;

    static {
        zzmx.zzbdq = new int[0];
    }

    public zzmx() {
        this(null);
    }

    private zzmx(zzng zzng0) {
        this.zzbdr = null;
        this.zzbds = new AtomicReference(new zzna());
    }

    private static boolean zza(zzgz zzgz0, String s) {
        return s != null && TextUtils.equals(s, zzop.zzbm(zzgz0.zzage));
    }

    @Override  // com.google.android.gms.internal.ads.zznc
    protected final zznd[] zza(zzhh[] arr_zzhh, zzmu[] arr_zzmu, int[][][] arr3_v) throws zzgk {
        int v58;
        int v57;
        zzna zzna3;
        int v48;
        int v76;
        boolean z17;
        zzmu zzmu4;
        boolean z16;
        int v75;
        int v68;
        boolean z14;
        int[][] arr2_v3;
        int v43;
        boolean z9;
        int[] arr_v1;
        int v41;
        boolean z8;
        int v40;
        boolean z7;
        int v33;
        boolean z6;
        int v32;
        int v31;
        zzmr zzmr2;
        int v28;
        int v26;
        int v25;
        int v24;
        int v23;
        int v22;
        int v21;
        boolean z5;
        boolean z4;
        int v17;
        int v16;
        int v9;
        zzna zzna1;
        int v8;
        int v = arr_zzhh.length;
        zznd[] arr_zznd = new zznd[v];
        zzna zzna0 = (zzna)this.zzbds.get();
        int v1 = 0;
        boolean z = false;
        int v2 = 0;
        while(v1 < v) {
            if(2 == arr_zzhh[v1].getTrackType()) {
                if(z) {
                    v8 = v;
                    zzna1 = zzna0;
                    v43 = v1;
                    v9 = v2;
                }
                else {
                    zzmu zzmu0 = arr_zzmu[v1];
                    int[][] arr2_v = arr3_v[v1];
                    int v3 = zzna0.zzbdx;
                    int v4 = zzna0.zzbdy;
                    int v5 = zzna0.zzbdz;
                    int v6 = zzna0.viewportWidth;
                    int v7 = zzna0.viewportHeight;
                    boolean z1 = zzna0.zzbec;
                    boolean z2 = zzna0.zzbea;
                    boolean z3 = zzna0.zzbeb;
                    v8 = v;
                    zzna1 = zzna0;
                    v9 = v2;
                    zzmr zzmr0 = null;
                    int v10 = 0;
                    int v11 = 0;
                    int v12 = 0;
                    int v13 = -1;
                    int v14 = -1;
                    while(v11 < zzmu0.length) {
                        zzmr zzmr1 = zzmu0.zzaw(v11);
                        ArrayList arrayList0 = new ArrayList(zzmr1.length);
                        for(int v15 = 0; true; ++v15) {
                            v16 = 0x7FFFFFFF;
                            if(v15 >= zzmr1.length) {
                                break;
                            }
                            arrayList0.add(v15);
                        }
                        if(v6 == 0x7FFFFFFF || v7 == 0x7FFFFFFF) {
                            zzmr2 = zzmr0;
                            v17 = v10;
                            v31 = v7;
                            v32 = v6;
                            z6 = z1;
                            v33 = v5;
                            z7 = z2;
                        }
                        else {
                            v17 = v10;
                            int v18 = 0;
                            while(v18 < zzmr1.length) {
                                zzgz zzgz0 = zzmr1.zzav(v18);
                                if(zzgz0.width <= 0 || zzgz0.height <= 0) {
                                    v22 = v7;
                                    v25 = v6;
                                    z5 = z1;
                                    v28 = v5;
                                    z4 = z2;
                                }
                                else {
                                    int v19 = zzgz0.width;
                                    z4 = z2;
                                    int v20 = zzgz0.height;
                                    if(z1) {
                                        if(v19 > v20) {
                                            z5 = true;
                                            v21 = 1;
                                        }
                                        else {
                                            z5 = true;
                                            v21 = 0;
                                        }
                                        if(v6 > v7) {
                                            v22 = v7;
                                            v23 = 1;
                                        }
                                        else {
                                            v22 = v7;
                                            v23 = 0;
                                        }
                                        if(v21 != v23) {
                                            v24 = v6;
                                            v25 = v24;
                                            v26 = v22;
                                            goto label_69;
                                        }
                                    }
                                    else {
                                        v22 = v7;
                                        z5 = false;
                                    }
                                    v26 = v6;
                                    v25 = v26;
                                    v24 = v22;
                                label_69:
                                    int v27 = v19 * v24;
                                    v28 = v5;
                                    int v29 = v20 * v26;
                                    Point point0 = v27 < v29 ? new Point((v27 + v20 - 1) / v20, v24) : new Point(v26, (v29 + v19 - 1) / v19);
                                    int v30 = zzgz0.width * zzgz0.height;
                                    if(zzgz0.width >= ((int)(((float)point0.x) * 0.98f)) && zzgz0.height >= ((int)(((float)point0.y) * 0.98f)) && v30 < v16) {
                                        v16 = v30;
                                    }
                                }
                                ++v18;
                                z2 = z4;
                                z1 = z5;
                                v7 = v22;
                                v6 = v25;
                                v5 = v28;
                            }
                            zzmr2 = zzmr0;
                            v31 = v7;
                            v32 = v6;
                            z6 = z1;
                            v33 = v5;
                            z7 = z2;
                            if(v16 != 0x7FFFFFFF) {
                                for(int v34 = arrayList0.size() - 1; v34 >= 0; --v34) {
                                    int v35 = zzmr1.zzav(((int)(((Integer)arrayList0.get(v34))))).zzeu();
                                    if(v35 == -1 || v35 > v16) {
                                        arrayList0.remove(v34);
                                    }
                                }
                            }
                        }
                        int[] arr_v = arr2_v[v11];
                        int v36 = v12;
                        int v37 = v13;
                        int v38 = v14;
                        int v39 = 0;
                        while(v39 < zzmr1.length) {
                            if(zzmx.zze(arr_v[v39], z3)) {
                                zzgz zzgz1 = zzmr1.zzav(v39);
                                if(!arrayList0.contains(v39) || zzgz1.width != -1 && zzgz1.width > v3 || zzgz1.height != -1 && zzgz1.height > v4) {
                                    v40 = v33;
                                }
                                else if(zzgz1.zzafj != -1) {
                                    v40 = v33;
                                    if(zzgz1.zzafj <= v40) {
                                        z8 = true;
                                        goto label_130;
                                    }
                                }
                                else {
                                    v40 = v33;
                                    z8 = true;
                                    goto label_130;
                                }
                                z8 = false;
                            label_130:
                                if(z8 || z7) {
                                    if(z8) {
                                        z9 = z3;
                                        v41 = 2;
                                    }
                                    else {
                                        z9 = z3;
                                        v41 = 1;
                                    }
                                    arr_v1 = arr_v;
                                    boolean z10 = zzmx.zze(arr_v[v39], false);
                                    if(z10) {
                                        v41 += 1000;
                                    }
                                    boolean z11 = v41 > v36;
                                    if(v41 == v36) {
                                        int v42 = zzgz1.zzeu() == v37 ? zzmx.zze(zzgz1.zzafj, v38) : zzmx.zze(zzgz1.zzeu(), v37);
                                        if(!z10 || !z8) {
                                            if(v42 < 0) {
                                                z11 = true;
                                                goto label_155;
                                            }
                                        }
                                        else if(v42 > 0) {
                                            z11 = true;
                                            goto label_155;
                                        }
                                        z11 = false;
                                    }
                                label_155:
                                    if(z11) {
                                        v38 = zzgz1.zzafj;
                                        v37 = zzgz1.zzeu();
                                        v17 = v39;
                                        zzmr2 = zzmr1;
                                        v36 = v41;
                                    }
                                }
                                else {
                                    arr_v1 = arr_v;
                                    z9 = z3;
                                }
                            }
                            else {
                                arr_v1 = arr_v;
                                z9 = z3;
                                v40 = v33;
                            }
                            ++v39;
                            v33 = v40;
                            z3 = z9;
                            arr_v = arr_v1;
                        }
                        v5 = v33;
                        ++v11;
                        v13 = v37;
                        v14 = v38;
                        v10 = v17;
                        zzmr0 = zzmr2;
                        z2 = z7;
                        z1 = z6;
                        v7 = v31;
                        v6 = v32;
                        v12 = v36;
                    }
                    v43 = v1;
                    arr_zznd[v43] = zzmr0 == null ? null : new zzmz(zzmr0, v10);
                    z = arr_zznd[v43] == null ? false : true;
                }
                v2 = v9 | (arr_zzmu[v43].length <= 0 ? 0 : 1);
            }
            else {
                v8 = v;
                zzna1 = zzna0;
                v43 = v1;
            }
            v1 = v43 + 1;
            zzna0 = zzna1;
            v = v8;
        }
        zzna zzna2 = zzna0;
        int v44 = v;
        int v45 = 0;
        int v46 = 0;
        int v47 = 0;
        while(v45 < v44) {
            switch(arr_zzhh[v45].getTrackType()) {
                case 1: {
                    v48 = v44;
                    zzna3 = zzna2;
                    if(v46 == 0) {
                        zzmu zzmu2 = arr_zzmu[v45];
                        int[][] arr2_v2 = arr3_v[v45];
                        boolean z13 = zzna3.zzbeb;
                        int v59 = 0;
                        int v60 = -1;
                        int v61 = -1;
                        for(int v62 = 0; v59 < zzmu2.length; v62 = v63) {
                            zzmr zzmr6 = zzmu2.zzaw(v59);
                            int[] arr_v3 = arr2_v2[v59];
                            int v63 = v62;
                            int v64 = v61;
                            int v65 = v60;
                            int v66 = 0;
                            while(v66 < zzmr6.length) {
                                if(zzmx.zze(arr_v3[v66], z13)) {
                                    zzgz zzgz2 = zzmr6.zzav(v66);
                                    int v67 = arr_v3[v66];
                                    if((zzgz2.zzagd & 1) == 0) {
                                        arr2_v3 = arr2_v2;
                                        z14 = false;
                                    }
                                    else {
                                        arr2_v3 = arr2_v2;
                                        z14 = true;
                                    }
                                    if(!zzmx.zza(zzgz2, null)) {
                                        v68 = z14 ? 2 : 1;
                                    }
                                    else if(z14) {
                                        v68 = 4;
                                    }
                                    else {
                                        v68 = 3;
                                    }
                                    if(zzmx.zze(v67, false)) {
                                        v68 += 1000;
                                    }
                                    if(v68 > v63) {
                                        v65 = v59;
                                        v64 = v66;
                                        v63 = v68;
                                    }
                                }
                                else {
                                    arr2_v3 = arr2_v2;
                                }
                                ++v66;
                                arr2_v2 = arr2_v3;
                            }
                            ++v59;
                            v60 = v65;
                            v61 = v64;
                        }
                        arr_zznd[v45] = v60 == -1 ? null : new zzmz(zzmu2.zzaw(v60), v61);
                        v46 = arr_zznd[v45] == null ? 0 : 1;
                        goto label_380;
                    }
                    else {
                        v58 = v46;
                        break;
                    }
                    v48 = v44;
                    zzna3 = zzna2;
                    v58 = v46;
                    break;
                }
                case 2: {
                    v48 = v44;
                    zzna3 = zzna2;
                    v58 = v46;
                    break;
                }
                case 3: {
                    if(v47 == 0) {
                        zzmu zzmu3 = arr_zzmu[v45];
                        int[][] arr2_v4 = arr3_v[v45];
                        zzna3 = zzna2;
                        boolean z15 = zzna3.zzbeb;
                        int v69 = 0;
                        zzmr zzmr7 = null;
                        int v70 = 0;
                        int v71 = 0;
                        while(v69 < zzmu3.length) {
                            zzmr zzmr8 = zzmu3.zzaw(v69);
                            int[] arr_v4 = arr2_v4[v69];
                            int v72 = v71;
                            int v73 = v70;
                            zzmr zzmr9 = zzmr7;
                            int v74 = 0;
                            while(v74 < zzmr8.length) {
                                if(zzmx.zze(arr_v4[v74], z15)) {
                                    zzgz zzgz3 = zzmr8.zzav(v74);
                                    if((zzgz3.zzagd & 1) == 0) {
                                        v75 = v44;
                                        z16 = false;
                                    }
                                    else {
                                        v75 = v44;
                                        z16 = true;
                                    }
                                    if((zzgz3.zzagd & 2) == 0) {
                                        zzmu4 = zzmu3;
                                        z17 = false;
                                    }
                                    else {
                                        zzmu4 = zzmu3;
                                        z17 = true;
                                    }
                                    if(zzmx.zza(zzgz3, null)) {
                                        if(z16) {
                                            v76 = 6;
                                        }
                                        else if(z17) {
                                            v76 = 4;
                                        }
                                        else {
                                            v76 = 5;
                                        }
                                        goto label_354;
                                    }
                                    else if(z16) {
                                        v76 = 3;
                                        goto label_354;
                                    }
                                    else if(z17) {
                                        v76 = zzmx.zza(zzgz3, null) ? 2 : 1;
                                    label_354:
                                        if(zzmx.zze(arr_v4[v74], false)) {
                                            v76 += 1000;
                                        }
                                        if(v76 > v72) {
                                            v72 = v76;
                                            v73 = v74;
                                            zzmr9 = zzmr8;
                                        }
                                    }
                                }
                                else {
                                    v75 = v44;
                                    zzmu4 = zzmu3;
                                }
                                ++v74;
                                v44 = v75;
                                zzmu3 = zzmu4;
                            }
                            ++v69;
                            zzmr7 = zzmr9;
                            v70 = v73;
                            v71 = v72;
                        }
                        v48 = v44;
                        arr_zznd[v45] = zzmr7 == null ? null : new zzmz(zzmr7, v70);
                        v47 = arr_zznd[v45] == null ? 0 : 1;
                        goto label_380;
                    }
                    else {
                        v48 = v44;
                        zzna3 = zzna2;
                        v58 = v46;
                    }
                    break;
                }
                default: {
                    v48 = v44;
                    int v49 = v46;
                    zzna3 = zzna2;
                    arr_zzhh[v45].getTrackType();
                    zzmu zzmu1 = arr_zzmu[v45];
                    int[][] arr2_v1 = arr3_v[v45];
                    boolean z12 = zzna3.zzbeb;
                    zzmr zzmr3 = null;
                    int v50 = 0;
                    int v51 = 0;
                    for(int v52 = 0; v50 < zzmu1.length; v52 = v53) {
                        zzmr zzmr4 = zzmu1.zzaw(v50);
                        int[] arr_v2 = arr2_v1[v50];
                        int v53 = v52;
                        int v54 = v51;
                        zzmr zzmr5 = zzmr3;
                        int v55 = 0;
                        while(v55 < zzmr4.length) {
                            if(zzmx.zze(arr_v2[v55], z12)) {
                                int v56 = (zzmr4.zzav(v55).zzagd & 1) == 0 ? 2 : 1;
                                v57 = v49;
                                if(zzmx.zze(arr_v2[v55], false)) {
                                    v56 += 1000;
                                }
                                if(v56 > v53) {
                                    v53 = v56;
                                    v54 = v55;
                                    zzmr5 = zzmr4;
                                }
                            }
                            else {
                                v57 = v49;
                            }
                            ++v55;
                            v49 = v57;
                        }
                        ++v50;
                        zzmr3 = zzmr5;
                        v51 = v54;
                    }
                    v58 = v49;
                    arr_zznd[v45] = zzmr3 == null ? null : new zzmz(zzmr3, v51);
                }
            }
            v46 = v58;
        label_380:
            ++v45;
            zzna2 = zzna3;
            v44 = v48;
        }
        return arr_zznd;
    }

    private static int zze(int v, int v1) {
        if(v == -1) {
            return v1 == -1 ? 0 : -1;
        }
        return v1 == -1 ? 1 : v - v1;
    }

    private static boolean zze(int v, boolean z) {
        return (v & 3) == 3 || z && (v & 3) == 2;
    }
}

