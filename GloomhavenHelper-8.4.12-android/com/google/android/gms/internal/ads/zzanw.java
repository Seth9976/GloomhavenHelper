package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;

public class zzanw {
    public static zzanq zzdp(String s) throws RemoteException {
        try {
            return new zzanz(((RtbAdapter)Class.forName(s, false, zzanw.class.getClassLoader()).getDeclaredConstructor().newInstance()));
        }
        catch(Throwable unused_ex) {
            throw new RemoteException();
        }
    }
}

