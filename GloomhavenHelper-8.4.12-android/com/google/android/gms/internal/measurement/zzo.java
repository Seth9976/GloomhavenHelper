package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public final class zzo extends zza implements zzm {
    zzo(IBinder iBinder0) {
        super(iBinder0, "com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void beginAdUnitExposure(String s, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzb(23, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void clearConditionalUserProperty(String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, bundle0);
        this.zzb(9, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void endAdUnitExposure(String s, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzb(24, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void generateEventId(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(22, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getAppInstanceId(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(20, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getCachedAppInstanceId(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(19, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getConditionalUserProperties(String s, String s1, zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, zzn0);
        this.zzb(10, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getCurrentScreenClass(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(17, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getCurrentScreenName(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(16, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getGmpAppId(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(21, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getMaxUserProperties(String s, zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        zzb.zza(parcel0, zzn0);
        this.zzb(6, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getTestFlag(zzn zzn0, int v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        parcel0.writeInt(v);
        this.zzb(38, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void getUserProperties(String s, String s1, boolean z, zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, z);
        zzb.zza(parcel0, zzn0);
        this.zzb(5, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void initForTests(Map map0) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeMap(map0);
        this.zzb(37, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void initialize(IObjectWrapper iObjectWrapper0, zzv zzv0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        zzb.zza(parcel0, zzv0);
        parcel0.writeLong(v);
        this.zzb(1, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void isDataCollectionEnabled(zzn zzn0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzn0);
        this.zzb(40, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void logEvent(String s, String s1, Bundle bundle0, boolean z, boolean z1, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, bundle0);
        zzb.zza(parcel0, z);
        zzb.zza(parcel0, z1);
        parcel0.writeLong(v);
        this.zzb(2, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void logEventAndBundle(String s, String s1, Bundle bundle0, zzn zzn0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, bundle0);
        zzb.zza(parcel0, zzn0);
        parcel0.writeLong(v);
        this.zzb(3, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void logHealthData(int v, String s, IObjectWrapper iObjectWrapper0, IObjectWrapper iObjectWrapper1, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeInt(v);
        parcel0.writeString(s);
        zzb.zza(parcel0, iObjectWrapper0);
        zzb.zza(parcel0, iObjectWrapper1);
        zzb.zza(parcel0, iObjectWrapper2);
        this.zzb(33, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityCreated(IObjectWrapper iObjectWrapper0, Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        zzb.zza(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzb(27, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityDestroyed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzb(28, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityPaused(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzb(29, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityResumed(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzb(30, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivitySaveInstanceState(IObjectWrapper iObjectWrapper0, zzn zzn0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        zzb.zza(parcel0, zzn0);
        parcel0.writeLong(v);
        this.zzb(0x1F, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityStarted(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzb(25, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void onActivityStopped(IObjectWrapper iObjectWrapper0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeLong(v);
        this.zzb(26, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void performAction(Bundle bundle0, zzn zzn0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, bundle0);
        zzb.zza(parcel0, zzn0);
        parcel0.writeLong(v);
        this.zzb(0x20, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void registerOnMeasurementEventListener(zzs zzs0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzs0);
        this.zzb(35, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void resetAnalyticsData(long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeLong(v);
        this.zzb(12, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setConditionalUserProperty(Bundle bundle0, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, bundle0);
        parcel0.writeLong(v);
        this.zzb(8, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setCurrentScreen(IObjectWrapper iObjectWrapper0, String s, String s1, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, iObjectWrapper0);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeLong(v);
        this.zzb(15, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setDataCollectionEnabled(boolean z) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, z);
        this.zzb(39, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setEventInterceptor(zzs zzs0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzs0);
        this.zzb(34, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setInstanceIdProvider(zzt zzt0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzt0);
        this.zzb(18, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setMeasurementEnabled(boolean z, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, z);
        parcel0.writeLong(v);
        this.zzb(11, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setMinimumSessionDuration(long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeLong(v);
        this.zzb(13, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setSessionTimeoutDuration(long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeLong(v);
        this.zzb(14, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setUserId(String s, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeLong(v);
        this.zzb(7, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void setUserProperty(String s, String s1, IObjectWrapper iObjectWrapper0, boolean z, long v) throws RemoteException {
        Parcel parcel0 = this.a_();
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzb.zza(parcel0, iObjectWrapper0);
        zzb.zza(parcel0, z);
        parcel0.writeLong(v);
        this.zzb(4, parcel0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzm
    public final void unregisterOnMeasurementEventListener(zzs zzs0) throws RemoteException {
        Parcel parcel0 = this.a_();
        zzb.zza(parcel0, zzs0);
        this.zzb(36, parcel0);
    }
}

