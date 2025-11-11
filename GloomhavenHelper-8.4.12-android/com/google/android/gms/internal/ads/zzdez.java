package com.google.android.gms.internal.ads;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public final class zzdez {
    public static Bundle zza(Bundle bundle0, String s) {
        Bundle bundle1 = bundle0.getBundle(s);
        return bundle1 == null ? new Bundle() : bundle1;
    }

    public static void zza(Bundle bundle0, String s, Bundle bundle1) {
        if(bundle1 != null) {
            bundle0.putBundle(s, bundle1);
        }
    }

    public static void zza(Bundle bundle0, String s, Boolean boolean0, boolean z) {
        if(z) {
            bundle0.putBoolean(s, boolean0.booleanValue());
        }
    }

    public static void zza(Bundle bundle0, String s, Integer integer0, boolean z) {
        if(z) {
            bundle0.putInt(s, ((int)integer0));
        }
    }

    public static void zza(Bundle bundle0, String s, String s1) {
        if(s1 != null) {
            bundle0.putString(s, s1);
        }
    }

    public static void zza(Bundle bundle0, String s, String s1, boolean z) {
        if(z) {
            bundle0.putString(s, s1);
        }
    }

    public static void zza(Bundle bundle0, String s, List list0) {
        if(list0 != null) {
            bundle0.putStringArrayList(s, new ArrayList(list0));
        }
    }
}

