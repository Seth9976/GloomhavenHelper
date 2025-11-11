package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R.string;
import com.google.android.gms.common.annotation.KeepForSdk;
import javax.annotation.Nullable;

@KeepForSdk
public class StringResourceValueReader {
    private final Resources zzeu;
    private final String zzev;

    public StringResourceValueReader(Context context0) {
        Preconditions.checkNotNull(context0);
        this.zzeu = context0.getResources();
        this.zzev = this.zzeu.getResourcePackageName(string.common_google_play_services_unknown_issue);
    }

    @KeepForSdk
    @Nullable
    public String getString(String s) {
        int v = this.zzeu.getIdentifier(s, "string", this.zzev);
        return v == 0 ? null : this.zzeu.getString(v);
    }
}

