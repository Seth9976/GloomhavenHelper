package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

public enum zzey {
    DOUBLE(0, zzfa.zza, zzfp.zze),
    FLOAT(1, zzfa.zza, zzfp.zzd),
    INT64(2, zzfa.zza, zzfp.zzc),
    UINT64(3, zzfa.zza, zzfp.zzc),
    INT32(4, zzfa.zza, zzfp.zzb),
    FIXED64(5, zzfa.zza, zzfp.zzc),
    FIXED32(6, zzfa.zza, zzfp.zzb),
    BOOL(7, zzfa.zza, zzfp.zzf),
    STRING(8, zzfa.zza, zzfp.zzg),
    MESSAGE(9, zzfa.zza, zzfp.zzj),
    BYTES(10, zzfa.zza, zzfp.zzh),
    UINT32(11, zzfa.zza, zzfp.zzb),
    ENUM(12, zzfa.zza, zzfp.zzi),
    SFIXED32(13, zzfa.zza, zzfp.zzb),
    SFIXED64(14, zzfa.zza, zzfp.zzc),
    SINT32(15, zzfa.zza, zzfp.zzb),
    SINT64(16, zzfa.zza, zzfp.zzc),
    GROUP(17, zzfa.zza, zzfp.zzj),
    DOUBLE_LIST(18, zzfa.zzb, zzfp.zze),
    FLOAT_LIST(19, zzfa.zzb, zzfp.zzd),
    INT64_LIST(20, zzfa.zzb, zzfp.zzc),
    UINT64_LIST(21, zzfa.zzb, zzfp.zzc),
    INT32_LIST(22, zzfa.zzb, zzfp.zzb),
    FIXED64_LIST(23, zzfa.zzb, zzfp.zzc),
    FIXED32_LIST(24, zzfa.zzb, zzfp.zzb),
    BOOL_LIST(25, zzfa.zzb, zzfp.zzf),
    STRING_LIST(26, zzfa.zzb, zzfp.zzg),
    MESSAGE_LIST(27, zzfa.zzb, zzfp.zzj),
    BYTES_LIST(28, zzfa.zzb, zzfp.zzh),
    UINT32_LIST(29, zzfa.zzb, zzfp.zzb),
    ENUM_LIST(30, zzfa.zzb, zzfp.zzi),
    SFIXED32_LIST(0x1F, zzfa.zzb, zzfp.zzb),
    SFIXED64_LIST(0x20, zzfa.zzb, zzfp.zzc),
    SINT32_LIST(33, zzfa.zzb, zzfp.zzb),
    SINT64_LIST(34, zzfa.zzb, zzfp.zzc),
    DOUBLE_LIST_PACKED(35, zzfa.zzc, zzfp.zze),
    FLOAT_LIST_PACKED(36, zzfa.zzc, zzfp.zzd),
    INT64_LIST_PACKED(37, zzfa.zzc, zzfp.zzc),
    UINT64_LIST_PACKED(38, zzfa.zzc, zzfp.zzc),
    INT32_LIST_PACKED(39, zzfa.zzc, zzfp.zzb),
    FIXED64_LIST_PACKED(40, zzfa.zzc, zzfp.zzc),
    FIXED32_LIST_PACKED(41, zzfa.zzc, zzfp.zzb),
    BOOL_LIST_PACKED(42, zzfa.zzc, zzfp.zzf),
    UINT32_LIST_PACKED(43, zzfa.zzc, zzfp.zzb),
    ENUM_LIST_PACKED(44, zzfa.zzc, zzfp.zzi),
    SFIXED32_LIST_PACKED(45, zzfa.zzc, zzfp.zzb),
    SFIXED64_LIST_PACKED(46, zzfa.zzc, zzfp.zzc),
    SINT32_LIST_PACKED(0x2F, zzfa.zzc, zzfp.zzb),
    SINT64_LIST_PACKED(0x30, zzfa.zzc, zzfp.zzc),
    GROUP_LIST(49, zzfa.zzb, zzfp.zzj),
    MAP(50, zzfa.zzd, zzfp.zza);

    private final zzfp zzaz;
    private final int zzba;
    private final zzfa zzbb;
    private final Class zzbc;
    private final boolean zzbd;
    private static final zzey[] zzbe;
    private static final Type[] zzbf;
    private static final zzey[] zzbg;

    static {
        arr_zzey[0] = zzey.zzc;
        arr_zzey[1] = zzey.zzd;
        arr_zzey[2] = zzey.zze;
        arr_zzey[3] = zzey.zzf;
        arr_zzey[4] = zzey.zzg;
        arr_zzey[5] = zzey.zzh;
        arr_zzey[6] = zzey.zzi;
        arr_zzey[7] = zzey.zzj;
        arr_zzey[8] = zzey.zzk;
        arr_zzey[9] = zzey.zzl;
        arr_zzey[10] = zzey.zzm;
        arr_zzey[11] = zzey.zzn;
        arr_zzey[12] = zzey.zzo;
        arr_zzey[13] = zzey.zzp;
        arr_zzey[14] = zzey.zzq;
        arr_zzey[15] = zzey.zzr;
        arr_zzey[16] = zzey.zzs;
        arr_zzey[17] = zzey.zzt;
        arr_zzey[18] = zzey.zzu;
        arr_zzey[19] = zzey.zzv;
        arr_zzey[20] = zzey.zzw;
        arr_zzey[21] = zzey.zzx;
        arr_zzey[22] = zzey.zzy;
        arr_zzey[23] = zzey.zzz;
        arr_zzey[24] = zzey.zzaa;
        arr_zzey[25] = zzey.zzab;
        arr_zzey[26] = zzey.zzac;
        arr_zzey[27] = zzey.zzad;
        arr_zzey[28] = zzey.zzae;
        arr_zzey[29] = zzey.zzaf;
        arr_zzey[30] = zzey.zzag;
        arr_zzey[0x1F] = zzey.zzah;
        arr_zzey[0x20] = zzey.zzai;
        arr_zzey[33] = zzey.zzaj;
        arr_zzey[34] = zzey.zzak;
        arr_zzey[35] = zzey.zza;
        arr_zzey[36] = zzey.zzal;
        arr_zzey[37] = zzey.zzam;
        arr_zzey[38] = zzey.zzan;
        arr_zzey[39] = zzey.zzao;
        arr_zzey[40] = zzey.zzap;
        arr_zzey[41] = zzey.zzaq;
        arr_zzey[42] = zzey.zzar;
        arr_zzey[43] = zzey.zzas;
        arr_zzey[44] = zzey.zzat;
        arr_zzey[45] = zzey.zzau;
        arr_zzey[46] = zzey.zzav;
        arr_zzey[0x2F] = zzey.zzaw;
        arr_zzey[0x30] = zzey.zzb;
        arr_zzey[49] = zzey.zzax;
        arr_zzey[50] = zzey.zzay;
        zzey.zzbg = arr_zzey;
        zzey.zzbf = new Type[0];
        zzey[] arr_zzey1 = (zzey[])zzey.zzbg.clone();
        zzey.zzbe = new zzey[arr_zzey1.length];
        for(int v = 0; v < arr_zzey1.length; ++v) {
            zzey zzey0 = arr_zzey1[v];
            zzey.zzbe[zzey0.zzba] = zzey0;
        }
    }

    private zzey(int v1, zzfa zzfa0, zzfp zzfp0) {
        this.zzba = v1;
        this.zzbb = zzfa0;
        this.zzaz = zzfp0;
        switch(zzex.zza[zzfa0.ordinal()]) {
            case 1: {
                this.zzbc = zzfp0.zza();
                break;
            }
            case 2: {
                this.zzbc = zzfp0.zza();
                break;
            }
            default: {
                this.zzbc = null;
            }
        }
        boolean z = false;
        if(zzfa0 == zzfa.zza) {
            switch(zzex.zzb[zzfp0.ordinal()]) {
                case 1: 
                case 2: 
                case 3: {
                    break;
                }
                default: {
                    z = true;
                }
            }
        }
        this.zzbd = z;
    }

    public final int zza() {
        return this.zzba;
    }
}

