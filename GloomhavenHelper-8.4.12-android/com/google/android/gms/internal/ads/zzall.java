package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.NetworkExtras;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.Adapter;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import java.util.Map;

public final class zzall extends zzaln {
    private Map zzdei;
    private static final zzanw zzdej;

    static {
        zzall.zzdej = new zzanw();
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final zzalp zzdf(String s) throws RemoteException {
        return this.zzdi(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final boolean zzdg(String s) throws RemoteException {
        try {
            Class class0 = Class.forName(s, false, zzall.class.getClassLoader());
            return CustomEvent.class.isAssignableFrom(class0);
        }
        catch(Throwable unused_ex) {
            zzazh.zzfa(("Could not load custom event implementation class: " + s + ", assuming old implementation."));
            return false;
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzalk
    public final zzanq zzdh(String s) throws RemoteException {
        return zzanw.zzdp(s);
    }

    private final zzalp zzdi(String s) throws RemoteException {
        try {
            Class class0 = Class.forName(s, false, zzall.class.getClassLoader());
            if(MediationAdapter.class.isAssignableFrom(class0)) {
                MediationAdapter mediationAdapter0 = (MediationAdapter)class0.getDeclaredConstructor().newInstance();
                return new zzamo(mediationAdapter0, ((NetworkExtras)this.zzdei.get(mediationAdapter0.getAdditionalParametersType())));
            }
            if(com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(class0)) {
                return new zzamg(((com.google.android.gms.ads.mediation.MediationAdapter)class0.getDeclaredConstructor().newInstance()));
            }
            if(Adapter.class.isAssignableFrom(class0)) {
                return new zzamg(((Adapter)class0.getDeclaredConstructor().newInstance()));
            }
            zzazh.zzfa(("Could not instantiate mediation adapter: " + s + " (not a valid adapter)."));
        }
        catch(Throwable unused_ex) {
        }
        return this.zzdj(s);
    }

    private final zzalp zzdj(String s) throws RemoteException {
        try {
            zzazh.zzeb("Reflection failed, retrying using direct instantiation");
            if("com.google.ads.mediation.admob.AdMobAdapter".equals(s)) {
                return new zzamg(new AdMobAdapter());
            }
            if("com.google.ads.mediation.AdUrlAdapter".equals(s)) {
                return new zzamg(new AdUrlAdapter());
            }
            if("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
                return new zzamg(new CustomEventAdapter());
            }
            if("com.google.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
                com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter0 = new com.google.ads.mediation.customevent.CustomEventAdapter();
                return new zzamo(customEventAdapter0, ((CustomEventExtras)this.zzdei.get(customEventAdapter0.getAdditionalParametersType())));
            }
        }
        catch(Throwable throwable0) {
            zzazh.zzd(("Could not instantiate mediation adapter: " + s + ". "), throwable0);
        }
        throw new RemoteException();
    }

    public final void zzf(Map map0) {
        this.zzdei = map0;
    }
}

