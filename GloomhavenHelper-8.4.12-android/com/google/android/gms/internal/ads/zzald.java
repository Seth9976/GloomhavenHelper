package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.List;
import java.util.Map;

public final class zzald extends zzbgg {
    private final AppMeasurementSdk zzdco;

    zzald(AppMeasurementSdk appMeasurementSdk0) {
        this.zzdco = appMeasurementSdk0;
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void beginAdUnitExposure(String s) throws RemoteException {
        this.zzdco.beginAdUnitExposure(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void clearConditionalUserProperty(String s, String s1, Bundle bundle0) throws RemoteException {
        this.zzdco.clearConditionalUserProperty(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void endAdUnitExposure(String s) throws RemoteException {
        this.zzdco.endAdUnitExposure(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final long generateEventId() throws RemoteException {
        return this.zzdco.generateEventId();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final String getAppIdOrigin() throws RemoteException {
        return this.zzdco.getAppIdOrigin();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final String getAppInstanceId() throws RemoteException {
        return this.zzdco.getAppInstanceId();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final List getConditionalUserProperties(String s, String s1) throws RemoteException {
        return this.zzdco.getConditionalUserProperties(s, s1);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final String getCurrentScreenClass() throws RemoteException {
        return this.zzdco.getCurrentScreenClass();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final String getCurrentScreenName() throws RemoteException {
        return this.zzdco.getCurrentScreenName();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final String getGmpAppId() throws RemoteException {
        return this.zzdco.getGmpAppId();
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final int getMaxUserProperties(String s) throws RemoteException {
        return this.zzdco.getMaxUserProperties(s);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final Map getUserProperties(String s, String s1, boolean z) throws RemoteException {
        return this.zzdco.getUserProperties(s, s1, z);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void logEvent(String s, String s1, Bundle bundle0) throws RemoteException {
        this.zzdco.logEvent(s, s1, bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void performAction(Bundle bundle0) throws RemoteException {
        this.zzdco.performAction(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final Bundle performActionWithResponse(Bundle bundle0) throws RemoteException {
        return this.zzdco.performActionWithResponse(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void setConditionalUserProperty(Bundle bundle0) throws RemoteException {
        this.zzdco.setConditionalUserProperty(bundle0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void zza(String s, String s1, IObjectWrapper iObjectWrapper0) throws RemoteException {
        Object object0 = iObjectWrapper0 == null ? null : ObjectWrapper.unwrap(iObjectWrapper0);
        this.zzdco.setUserProperty(s, s1, object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzbgd
    public final void zzb(IObjectWrapper iObjectWrapper0, String s, String s1) throws RemoteException {
        Activity activity0 = iObjectWrapper0 == null ? null : ((Activity)ObjectWrapper.unwrap(iObjectWrapper0));
        this.zzdco.setCurrentScreen(activity0, s, s1);
    }
}

