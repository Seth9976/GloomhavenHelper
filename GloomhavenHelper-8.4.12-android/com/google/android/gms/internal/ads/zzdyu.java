package com.google.android.gms.internal.ads;

import java.lang.reflect.Type;

public enum zzdyu {
    DOUBLE(0, zzdyw.zzhsl, zzdzj.zzhty),
    FLOAT(1, zzdyw.zzhsl, zzdzj.zzhtx),
    INT64(2, zzdyw.zzhsl, zzdzj.zzhtw),
    UINT64(3, zzdyw.zzhsl, zzdzj.zzhtw),
    INT32(4, zzdyw.zzhsl, zzdzj.zzhtv),
    FIXED64(5, zzdyw.zzhsl, zzdzj.zzhtw),
    FIXED32(6, zzdyw.zzhsl, zzdzj.zzhtv),
    BOOL(7, zzdyw.zzhsl, zzdzj.zzhtz),
    STRING(8, zzdyw.zzhsl, zzdzj.zzhua),
    MESSAGE(9, zzdyw.zzhsl, zzdzj.zzhud),
    BYTES(10, zzdyw.zzhsl, zzdzj.zzhub),
    UINT32(11, zzdyw.zzhsl, zzdzj.zzhtv),
    ENUM(12, zzdyw.zzhsl, zzdzj.zzhuc),
    SFIXED32(13, zzdyw.zzhsl, zzdzj.zzhtv),
    SFIXED64(14, zzdyw.zzhsl, zzdzj.zzhtw),
    SINT32(15, zzdyw.zzhsl, zzdzj.zzhtv),
    SINT64(16, zzdyw.zzhsl, zzdzj.zzhtw),
    GROUP(17, zzdyw.zzhsl, zzdzj.zzhud),
    DOUBLE_LIST(18, zzdyw.zzhsm, zzdzj.zzhty),
    FLOAT_LIST(19, zzdyw.zzhsm, zzdzj.zzhtx),
    INT64_LIST(20, zzdyw.zzhsm, zzdzj.zzhtw),
    UINT64_LIST(21, zzdyw.zzhsm, zzdzj.zzhtw),
    INT32_LIST(22, zzdyw.zzhsm, zzdzj.zzhtv),
    FIXED64_LIST(23, zzdyw.zzhsm, zzdzj.zzhtw),
    FIXED32_LIST(24, zzdyw.zzhsm, zzdzj.zzhtv),
    BOOL_LIST(25, zzdyw.zzhsm, zzdzj.zzhtz),
    STRING_LIST(26, zzdyw.zzhsm, zzdzj.zzhua),
    MESSAGE_LIST(27, zzdyw.zzhsm, zzdzj.zzhud),
    BYTES_LIST(28, zzdyw.zzhsm, zzdzj.zzhub),
    UINT32_LIST(29, zzdyw.zzhsm, zzdzj.zzhtv),
    ENUM_LIST(30, zzdyw.zzhsm, zzdzj.zzhuc),
    SFIXED32_LIST(0x1F, zzdyw.zzhsm, zzdzj.zzhtv),
    SFIXED64_LIST(0x20, zzdyw.zzhsm, zzdzj.zzhtw),
    SINT32_LIST(33, zzdyw.zzhsm, zzdzj.zzhtv),
    SINT64_LIST(34, zzdyw.zzhsm, zzdzj.zzhtw),
    DOUBLE_LIST_PACKED(35, zzdyw.zzhsn, zzdzj.zzhty),
    FLOAT_LIST_PACKED(36, zzdyw.zzhsn, zzdzj.zzhtx),
    INT64_LIST_PACKED(37, zzdyw.zzhsn, zzdzj.zzhtw),
    UINT64_LIST_PACKED(38, zzdyw.zzhsn, zzdzj.zzhtw),
    INT32_LIST_PACKED(39, zzdyw.zzhsn, zzdzj.zzhtv),
    FIXED64_LIST_PACKED(40, zzdyw.zzhsn, zzdzj.zzhtw),
    FIXED32_LIST_PACKED(41, zzdyw.zzhsn, zzdzj.zzhtv),
    BOOL_LIST_PACKED(42, zzdyw.zzhsn, zzdzj.zzhtz),
    UINT32_LIST_PACKED(43, zzdyw.zzhsn, zzdzj.zzhtv),
    ENUM_LIST_PACKED(44, zzdyw.zzhsn, zzdzj.zzhuc),
    SFIXED32_LIST_PACKED(45, zzdyw.zzhsn, zzdzj.zzhtv),
    SFIXED64_LIST_PACKED(46, zzdyw.zzhsn, zzdzj.zzhtw),
    SINT32_LIST_PACKED(0x2F, zzdyw.zzhsn, zzdzj.zzhtv),
    SINT64_LIST_PACKED(0x30, zzdyw.zzhsn, zzdzj.zzhtw),
    GROUP_LIST(49, zzdyw.zzhsm, zzdzj.zzhud),
    MAP(50, zzdyw.zzhso, zzdzj.zzhtu);

    private final int id;
    private final zzdzj zzhsc;
    private final zzdyw zzhsd;
    private final Class zzhse;
    private final boolean zzhsf;
    private static final zzdyu[] zzhsg;
    private static final Type[] zzhsh;
    private static final zzdyu[] zzhsi;

    static {
        arr_zzdyu[0] = zzdyu.zzhqd;
        arr_zzdyu[1] = zzdyu.zzhqe;
        arr_zzdyu[2] = zzdyu.zzhqf;
        arr_zzdyu[3] = zzdyu.zzhqg;
        arr_zzdyu[4] = zzdyu.zzhqh;
        arr_zzdyu[5] = zzdyu.zzhqi;
        arr_zzdyu[6] = zzdyu.zzhqj;
        arr_zzdyu[7] = zzdyu.zzhqk;
        arr_zzdyu[8] = zzdyu.zzhql;
        arr_zzdyu[9] = zzdyu.zzhqm;
        arr_zzdyu[10] = zzdyu.zzhqn;
        arr_zzdyu[11] = zzdyu.zzhqo;
        arr_zzdyu[12] = zzdyu.zzhqp;
        arr_zzdyu[13] = zzdyu.zzhqq;
        arr_zzdyu[14] = zzdyu.zzhqr;
        arr_zzdyu[15] = zzdyu.zzhqs;
        arr_zzdyu[16] = zzdyu.zzhqt;
        arr_zzdyu[17] = zzdyu.zzhqu;
        arr_zzdyu[18] = zzdyu.zzhqv;
        arr_zzdyu[19] = zzdyu.zzhqw;
        arr_zzdyu[20] = zzdyu.zzhqx;
        arr_zzdyu[21] = zzdyu.zzhqy;
        arr_zzdyu[22] = zzdyu.zzhqz;
        arr_zzdyu[23] = zzdyu.zzhra;
        arr_zzdyu[24] = zzdyu.zzhrb;
        arr_zzdyu[25] = zzdyu.zzhrc;
        arr_zzdyu[26] = zzdyu.zzhrd;
        arr_zzdyu[27] = zzdyu.zzhre;
        arr_zzdyu[28] = zzdyu.zzhrf;
        arr_zzdyu[29] = zzdyu.zzhrg;
        arr_zzdyu[30] = zzdyu.zzhrh;
        arr_zzdyu[0x1F] = zzdyu.zzhri;
        arr_zzdyu[0x20] = zzdyu.zzhrj;
        arr_zzdyu[33] = zzdyu.zzhrk;
        arr_zzdyu[34] = zzdyu.zzhrl;
        arr_zzdyu[35] = zzdyu.zzhrm;
        arr_zzdyu[36] = zzdyu.zzhrn;
        arr_zzdyu[37] = zzdyu.zzhro;
        arr_zzdyu[38] = zzdyu.zzhrp;
        arr_zzdyu[39] = zzdyu.zzhrq;
        arr_zzdyu[40] = zzdyu.zzhrr;
        arr_zzdyu[41] = zzdyu.zzhrs;
        arr_zzdyu[42] = zzdyu.zzhrt;
        arr_zzdyu[43] = zzdyu.zzhru;
        arr_zzdyu[44] = zzdyu.zzhrv;
        arr_zzdyu[45] = zzdyu.zzhrw;
        arr_zzdyu[46] = zzdyu.zzhrx;
        arr_zzdyu[0x2F] = zzdyu.zzhry;
        arr_zzdyu[0x30] = zzdyu.zzhrz;
        arr_zzdyu[49] = zzdyu.zzhsa;
        arr_zzdyu[50] = zzdyu.zzhsb;
        zzdyu.zzhsi = arr_zzdyu;
        zzdyu.zzhsh = new Type[0];
        zzdyu[] arr_zzdyu1 = (zzdyu[])zzdyu.zzhsi.clone();
        zzdyu.zzhsg = new zzdyu[arr_zzdyu1.length];
        for(int v = 0; v < arr_zzdyu1.length; ++v) {
            zzdyu zzdyu0 = arr_zzdyu1[v];
            zzdyu.zzhsg[zzdyu0.id] = zzdyu0;
        }
    }

    private zzdyu(int v1, zzdyw zzdyw0, zzdzj zzdzj0) {
        this.id = v1;
        this.zzhsd = zzdyw0;
        this.zzhsc = zzdzj0;
        switch(zzdyt.zzhqb[zzdyw0.ordinal()]) {
            case 1: {
                this.zzhse = zzdzj0.zzbdr();
                break;
            }
            case 2: {
                this.zzhse = zzdzj0.zzbdr();
                break;
            }
            default: {
                this.zzhse = null;
            }
        }
        boolean z = false;
        if(zzdyw0 == zzdyw.zzhsl) {
            switch(zzdyt.zzhqc[zzdzj0.ordinal()]) {
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
        this.zzhsf = z;
    }

    public final int id() {
        return this.id;
    }
}

