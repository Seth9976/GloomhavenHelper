package com.google.android.gms.internal.ads;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class zzdmq implements FilenameFilter {
    private final Pattern zzhbi;

    public zzdmq(Pattern pattern0) {
        this.zzhbi = (Pattern)zzdlg.checkNotNull(pattern0);
    }

    @Override
    public final boolean accept(@NullableDecl File file0, String s) {
        return this.zzhbi.matcher(s).matches();
    }
}

