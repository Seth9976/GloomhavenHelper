package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzl extends zzc implements zzm {
    public zzl() {
        super("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
    }

    public static zzm asInterface(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.measurement.api.internal.IAppMeasurementDynamiteService");
        return iInterface0 instanceof zzm ? ((zzm)iInterface0) : new zzo(iBinder0);
    }

    @Override  // com.google.android.gms.internal.measurement.zzc
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzn zzn1;
        zzn zzn0 = null;
        switch(v) {
            case 1: {
                this.initialize(Stub.asInterface(parcel0.readStrongBinder()), ((zzv)zzb.zza(parcel0, zzv.CREATOR)), parcel0.readLong());
                break;
            }
            case 2: {
                this.logEvent(parcel0.readString(), parcel0.readString(), ((Bundle)zzb.zza(parcel0, Bundle.CREATOR)), zzb.zza(parcel0), zzb.zza(parcel0), parcel0.readLong());
                break;
            }
            case 3: {
                String s = parcel0.readString();
                String s1 = parcel0.readString();
                Bundle bundle0 = (Bundle)zzb.zza(parcel0, Bundle.CREATOR);
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 == null) {
                    zzn1 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn1 = iInterface0 instanceof zzn ? ((zzn)iInterface0) : new zzp(iBinder0);
                }
                this.logEventAndBundle(s, s1, bundle0, zzn1, parcel0.readLong());
                break;
            }
            case 4: {
                this.setUserProperty(parcel0.readString(), parcel0.readString(), Stub.asInterface(parcel0.readStrongBinder()), zzb.zza(parcel0), parcel0.readLong());
                break;
            }
            case 5: {
                String s2 = parcel0.readString();
                String s3 = parcel0.readString();
                boolean z = zzb.zza(parcel0);
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface1 instanceof zzn ? ((zzn)iInterface1) : new zzp(iBinder1);
                }
                this.getUserProperties(s2, s3, z, zzn0);
                break;
            }
            case 6: {
                String s4 = parcel0.readString();
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 != null) {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface2 instanceof zzn ? ((zzn)iInterface2) : new zzp(iBinder2);
                }
                this.getMaxUserProperties(s4, zzn0);
                break;
            }
            case 7: {
                this.setUserId(parcel0.readString(), parcel0.readLong());
                break;
            }
            case 8: {
                this.setConditionalUserProperty(((Bundle)zzb.zza(parcel0, Bundle.CREATOR)), parcel0.readLong());
                break;
            }
            case 9: {
                this.clearConditionalUserProperty(parcel0.readString(), parcel0.readString(), ((Bundle)zzb.zza(parcel0, Bundle.CREATOR)));
                break;
            }
            case 10: {
                String s5 = parcel0.readString();
                String s6 = parcel0.readString();
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 != null) {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface3 instanceof zzn ? ((zzn)iInterface3) : new zzp(iBinder3);
                }
                this.getConditionalUserProperties(s5, s6, zzn0);
                break;
            }
            case 11: {
                this.setMeasurementEnabled(zzb.zza(parcel0), parcel0.readLong());
                break;
            }
            case 12: {
                this.resetAnalyticsData(parcel0.readLong());
                break;
            }
            case 13: {
                this.setMinimumSessionDuration(parcel0.readLong());
                break;
            }
            case 14: {
                this.setSessionTimeoutDuration(parcel0.readLong());
                break;
            }
            case 15: {
                this.setCurrentScreen(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readString(), parcel0.readString(), parcel0.readLong());
                break;
            }
            case 16: {
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 != null) {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface4 instanceof zzn ? ((zzn)iInterface4) : new zzp(iBinder4);
                }
                this.getCurrentScreenName(zzn0);
                break;
            }
            case 17: {
                IBinder iBinder5 = parcel0.readStrongBinder();
                if(iBinder5 != null) {
                    IInterface iInterface5 = iBinder5.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface5 instanceof zzn ? ((zzn)iInterface5) : new zzp(iBinder5);
                }
                this.getCurrentScreenClass(zzn0);
                break;
            }
            case 18: {
                IBinder iBinder6 = parcel0.readStrongBinder();
                if(iBinder6 != null) {
                    IInterface iInterface6 = iBinder6.queryLocalInterface("com.google.android.gms.measurement.api.internal.IStringProvider");
                    zzn0 = iInterface6 instanceof zzt ? ((zzt)iInterface6) : new zzw(iBinder6);
                }
                this.setInstanceIdProvider(((zzt)zzn0));
                break;
            }
            case 19: {
                IBinder iBinder7 = parcel0.readStrongBinder();
                if(iBinder7 != null) {
                    IInterface iInterface7 = iBinder7.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface7 instanceof zzn ? ((zzn)iInterface7) : new zzp(iBinder7);
                }
                this.getCachedAppInstanceId(zzn0);
                break;
            }
            case 20: {
                IBinder iBinder8 = parcel0.readStrongBinder();
                if(iBinder8 != null) {
                    IInterface iInterface8 = iBinder8.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface8 instanceof zzn ? ((zzn)iInterface8) : new zzp(iBinder8);
                }
                this.getAppInstanceId(zzn0);
                break;
            }
            case 21: {
                IBinder iBinder9 = parcel0.readStrongBinder();
                if(iBinder9 != null) {
                    IInterface iInterface9 = iBinder9.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface9 instanceof zzn ? ((zzn)iInterface9) : new zzp(iBinder9);
                }
                this.getGmpAppId(zzn0);
                break;
            }
            case 22: {
                IBinder iBinder10 = parcel0.readStrongBinder();
                if(iBinder10 != null) {
                    IInterface iInterface10 = iBinder10.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface10 instanceof zzn ? ((zzn)iInterface10) : new zzp(iBinder10);
                }
                this.generateEventId(zzn0);
                break;
            }
            case 23: {
                this.beginAdUnitExposure(parcel0.readString(), parcel0.readLong());
                break;
            }
            case 24: {
                this.endAdUnitExposure(parcel0.readString(), parcel0.readLong());
                break;
            }
            case 25: {
                this.onActivityStarted(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readLong());
                break;
            }
            case 26: {
                this.onActivityStopped(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readLong());
                break;
            }
            case 27: {
                this.onActivityCreated(Stub.asInterface(parcel0.readStrongBinder()), ((Bundle)zzb.zza(parcel0, Bundle.CREATOR)), parcel0.readLong());
                break;
            }
            case 28: {
                this.onActivityDestroyed(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readLong());
                break;
            }
            case 29: {
                this.onActivityPaused(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readLong());
                break;
            }
            case 30: {
                this.onActivityResumed(Stub.asInterface(parcel0.readStrongBinder()), parcel0.readLong());
                break;
            }
            case 0x1F: {
                IObjectWrapper iObjectWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                IBinder iBinder11 = parcel0.readStrongBinder();
                if(iBinder11 != null) {
                    IInterface iInterface11 = iBinder11.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface11 instanceof zzn ? ((zzn)iInterface11) : new zzp(iBinder11);
                }
                this.onActivitySaveInstanceState(iObjectWrapper0, zzn0, parcel0.readLong());
                break;
            }
            case 0x20: {
                Bundle bundle1 = (Bundle)zzb.zza(parcel0, Bundle.CREATOR);
                IBinder iBinder12 = parcel0.readStrongBinder();
                if(iBinder12 != null) {
                    IInterface iInterface12 = iBinder12.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface12 instanceof zzn ? ((zzn)iInterface12) : new zzp(iBinder12);
                }
                this.performAction(bundle1, zzn0, parcel0.readLong());
                break;
            }
            case 33: {
                this.logHealthData(parcel0.readInt(), parcel0.readString(), Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()), Stub.asInterface(parcel0.readStrongBinder()));
                break;
            }
            case 34: {
                IBinder iBinder13 = parcel0.readStrongBinder();
                if(iBinder13 != null) {
                    IInterface iInterface13 = iBinder13.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzn0 = iInterface13 instanceof zzs ? ((zzs)iInterface13) : new zzu(iBinder13);
                }
                this.setEventInterceptor(((zzs)zzn0));
                break;
            }
            case 35: {
                IBinder iBinder14 = parcel0.readStrongBinder();
                if(iBinder14 != null) {
                    IInterface iInterface14 = iBinder14.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzn0 = iInterface14 instanceof zzs ? ((zzs)iInterface14) : new zzu(iBinder14);
                }
                this.registerOnMeasurementEventListener(((zzs)zzn0));
                break;
            }
            case 36: {
                IBinder iBinder15 = parcel0.readStrongBinder();
                if(iBinder15 != null) {
                    IInterface iInterface15 = iBinder15.queryLocalInterface("com.google.android.gms.measurement.api.internal.IEventHandlerProxy");
                    zzn0 = iInterface15 instanceof zzs ? ((zzs)iInterface15) : new zzu(iBinder15);
                }
                this.unregisterOnMeasurementEventListener(((zzs)zzn0));
                break;
            }
            case 37: {
                this.initForTests(zzb.zzb(parcel0));
                break;
            }
            case 38: {
                IBinder iBinder16 = parcel0.readStrongBinder();
                if(iBinder16 != null) {
                    IInterface iInterface16 = iBinder16.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface16 instanceof zzn ? ((zzn)iInterface16) : new zzp(iBinder16);
                }
                this.getTestFlag(zzn0, parcel0.readInt());
                break;
            }
            case 39: {
                this.setDataCollectionEnabled(zzb.zza(parcel0));
                break;
            }
            case 40: {
                IBinder iBinder17 = parcel0.readStrongBinder();
                if(iBinder17 != null) {
                    IInterface iInterface17 = iBinder17.queryLocalInterface("com.google.android.gms.measurement.api.internal.IBundleReceiver");
                    zzn0 = iInterface17 instanceof zzn ? ((zzn)iInterface17) : new zzp(iBinder17);
                }
                this.isDataCollectionEnabled(zzn0);
                break;
            }
            default: {
                return false;
            }
        }
        parcel1.writeNoException();
        return true;
    }
}

