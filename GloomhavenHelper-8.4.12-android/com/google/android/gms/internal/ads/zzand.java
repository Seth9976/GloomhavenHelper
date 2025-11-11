package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;

public final class zzand {
    public static int zza(ErrorCode adRequest$ErrorCode0) {
        switch(adRequest$ErrorCode0) {
            case INVALID_REQUEST: {
                return 1;
            }
            case NETWORK_ERROR: {
                return 2;
            }
            case NO_FILL: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }

    public static MediationAdRequest zza(zzuh zzuh0, boolean z) {
        HashSet hashSet0 = zzuh0.zzcco == null ? null : new HashSet(zzuh0.zzcco);
        Date date0 = new Date(zzuh0.zzccm);
        switch(zzuh0.zzccn) {
            case 1: {
                return new MediationAdRequest(date0, Gender.MALE, hashSet0, z, zzuh0.zzmk);
            }
            case 2: {
                return new MediationAdRequest(date0, Gender.FEMALE, hashSet0, z, zzuh0.zzmk);
            }
            default: {
                return new MediationAdRequest(date0, Gender.UNKNOWN, hashSet0, z, zzuh0.zzmk);
            }
        }
    }
}

