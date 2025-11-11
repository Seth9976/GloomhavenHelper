package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class zzjk {
    public int zzaga;
    public int zzagb;
    private static final zzll zzani;
    private static final Pattern zzanj;

    static {
        zzjk.zzani = new zzjj();
        zzjk.zzanj = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    }

    public zzjk() {
        this.zzaga = -1;
        this.zzagb = -1;
    }

    private final boolean zzb(String s, String s1) {
        if(!"iTunSMPB".equals(s)) {
            return false;
        }
        Matcher matcher0 = zzjk.zzanj.matcher(s1);
        if(matcher0.find()) {
            try {
                int v = Integer.parseInt(matcher0.group(1), 16);
                int v1 = Integer.parseInt(matcher0.group(2), 16);
                if(v > 0 || v1 > 0) {
                    this.zzaga = v;
                    this.zzagb = v1;
                    return true;
                }
                return false;
            }
            catch(NumberFormatException unused_ex) {
            }
        }
        return false;
    }

    public final boolean zzb(zzlh zzlh0) {
        for(int v = 0; v < zzlh0.length(); ++v) {
            zza zzlh$zza0 = zzlh0.zzas(v);
            if(zzlh$zza0 instanceof zzlj && this.zzb(((zzlj)zzlh$zza0).description, ((zzlj)zzlh$zza0).text)) {
                return true;
            }
        }
        return false;
    }

    public final boolean zzgq() {
        return this.zzaga != -1 && this.zzagb != -1;
    }
}

