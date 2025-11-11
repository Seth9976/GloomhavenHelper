package com.google.android.gms.measurement.internal;

public class zzgn {
    public static final String[] zza;
    public static final String[] zzb;

    static {
        zzgn.zza = new String[]{"firebase_last_notification", "first_open_time", "first_visit_time", "last_deep_link_referrer", "user_id", "first_open_after_install", "lifetime_user_engagement", "session_user_engagement", "non_personalized_ads", "session_number", "ga_session_number", "session_id", "ga_session_id", "last_gclid"};
        zzgn.zzb = new String[]{"_ln", "_fot", "_fvt", "_ldl", "_id", "_fi", "_lte", "_se", "_npa", "_sno", "_sno", "_sid", "_sid", "_lgclid"};
    }

    public static String zza(String s) {
        return zzhw.zza(s, zzgn.zza, zzgn.zzb);
    }
}

