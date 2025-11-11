package com.google.android.gms.internal.ads;

import android.webkit.ConsoleMessage.MessageLevel;

final class zzbdw {
    static final int[] zzefj;

    static {
        zzbdw.zzefj = new int[ConsoleMessage.MessageLevel.values().length];
        try {
            zzbdw.zzefj[ConsoleMessage.MessageLevel.ERROR.ordinal()] = 1;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzbdw.zzefj[ConsoleMessage.MessageLevel.WARNING.ordinal()] = 2;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzbdw.zzefj[ConsoleMessage.MessageLevel.LOG.ordinal()] = 3;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzbdw.zzefj[ConsoleMessage.MessageLevel.TIP.ordinal()] = 4;
        }
        catch(NoSuchFieldError unused_ex) {
        }
        try {
            zzbdw.zzefj[ConsoleMessage.MessageLevel.DEBUG.ordinal()] = 5;
        }
        catch(NoSuchFieldError unused_ex) {
        }
    }
}

