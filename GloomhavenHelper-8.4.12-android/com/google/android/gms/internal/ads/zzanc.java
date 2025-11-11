package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;

final class zzanc {
    private static final int[] zzdfk;
    static final int[] zzdfl;

    static {
        zzanc.zzdfl = new int[ErrorCode.values().length];
        try {
            zzanc.zzdfl[ErrorCode.INTERNAL_ERROR.ordinal()] = 1;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzanc.zzdfl[ErrorCode.INVALID_REQUEST.ordinal()] = 2;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzanc.zzdfl[ErrorCode.NETWORK_ERROR.ordinal()] = 3;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzanc.zzdfl[ErrorCode.NO_FILL.ordinal()] = 4;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        zzanc.zzdfk = new int[Gender.values().length];
        try {
            zzanc.zzdfk[Gender.FEMALE.ordinal()] = 1;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzanc.zzdfk[Gender.MALE.ordinal()] = 2;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzanc.zzdfk[Gender.UNKNOWN.ordinal()] = 3;
        }
        catch(NoSuchFieldError unused_ex) {
        }
    }
}

