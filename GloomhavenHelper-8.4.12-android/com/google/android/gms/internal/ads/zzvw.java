package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public abstract class zzvw extends zzge implements zzvx {
    public zzvw() {
        super("com.google.android.gms.ads.internal.client.IAdManager");
    }

    @Override  // com.google.android.gms.internal.ads.zzge
    protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
        zzvk zzvk0 = null;
        switch(v) {
            case 1: {
                IObjectWrapper iObjectWrapper0 = this.zzkc();
                parcel1.writeNoException();
                zzgd.zza(parcel1, iObjectWrapper0);
                return true;
            }
            case 2: {
                this.destroy();
                parcel1.writeNoException();
                return true;
            }
            case 3: {
                boolean z = this.isReady();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z);
                return true;
            }
            case 4: {
                boolean z1 = this.zza(((zzuh)zzgd.zza(parcel0, zzuh.CREATOR)));
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z1);
                return true;
            }
            case 5: {
                this.pause();
                parcel1.writeNoException();
                return true;
            }
            case 6: {
                this.resume();
                parcel1.writeNoException();
                return true;
            }
            case 7: {
                IBinder iBinder0 = parcel0.readStrongBinder();
                if(iBinder0 != null) {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdListener");
                    zzvk0 = iInterface0 instanceof zzvk ? ((zzvk)iInterface0) : new zzvm(iBinder0);
                }
                this.zza(zzvk0);
                parcel1.writeNoException();
                return true;
            }
            case 8: {
                IBinder iBinder1 = parcel0.readStrongBinder();
                if(iBinder1 != null) {
                    IInterface iInterface1 = iBinder1.queryLocalInterface("com.google.android.gms.ads.internal.client.IAppEventListener");
                    zzvk0 = iInterface1 instanceof zzwf ? ((zzwf)iInterface1) : new zzwh(iBinder1);
                }
                this.zza(((zzwf)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            case 9: {
                this.showInterstitial();
                parcel1.writeNoException();
                return true;
            }
            case 10: {
                this.stopLoading();
                parcel1.writeNoException();
                return true;
            }
            case 11: {
                this.zzkd();
                parcel1.writeNoException();
                return true;
            }
            case 12: {
                zzuk zzuk0 = this.zzke();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, zzuk0);
                return true;
            }
            case 13: {
                this.zza(((zzuk)zzgd.zza(parcel0, zzuk.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 14: {
                this.zza(zzapo.zzah(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 15: {
                this.zza(zzapu.zzaj(parcel0.readStrongBinder()), parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 18: {
                String s = this.getMediationAdapterClassName();
                parcel1.writeNoException();
                parcel1.writeString(s);
                return true;
            }
            case 19: {
                this.zza(zzaat.zzl(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 20: {
                IBinder iBinder2 = parcel0.readStrongBinder();
                if(iBinder2 != null) {
                    IInterface iInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdClickListener");
                    zzvk0 = iInterface2 instanceof zzvj ? ((zzvj)iInterface2) : new zzvl(iBinder2);
                }
                this.zza(((zzvj)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            case 21: {
                IBinder iBinder3 = parcel0.readStrongBinder();
                if(iBinder3 != null) {
                    IInterface iInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.ads.internal.client.ICorrelationIdProvider");
                    zzvk0 = iInterface3 instanceof zzwl ? ((zzwl)iInterface3) : new zzwk(iBinder3);
                }
                this.zza(((zzwl)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            case 22: {
                this.setManualImpressionsEnabled(zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 23: {
                boolean z2 = this.isLoading();
                parcel1.writeNoException();
                zzgd.writeBoolean(parcel1, z2);
                return true;
            }
            case 24: {
                this.zza(zzase.zzal(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 25: {
                this.setUserId(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 26: {
                zzxj zzxj0 = this.getVideoController();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxj0);
                return true;
            }
            case 29: {
                this.zza(((zzzc)zzgd.zza(parcel0, zzzc.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 30: {
                this.zza(((zzxp)zzgd.zza(parcel0, zzxp.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 0x1F: {
                String s1 = this.getAdUnitId();
                parcel1.writeNoException();
                parcel1.writeString(s1);
                return true;
            }
            case 0x20: {
                zzwf zzwf0 = this.zzkh();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzwf0);
                return true;
            }
            case 33: {
                zzvk zzvk1 = this.zzki();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzvk1);
                return true;
            }
            case 34: {
                this.setImmersiveMode(zzgd.zza(parcel0));
                parcel1.writeNoException();
                return true;
            }
            case 35: {
                String s2 = this.zzkf();
                parcel1.writeNoException();
                parcel1.writeString(s2);
                return true;
            }
            case 36: {
                IBinder iBinder4 = parcel0.readStrongBinder();
                if(iBinder4 != null) {
                    IInterface iInterface4 = iBinder4.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdMetadataListener");
                    zzvk0 = iInterface4 instanceof zzwa ? ((zzwa)iInterface4) : new zzwc(iBinder4);
                }
                this.zza(((zzwa)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            case 37: {
                Bundle bundle0 = this.getAdMetadata();
                parcel1.writeNoException();
                zzgd.zzb(parcel1, bundle0);
                return true;
            }
            case 38: {
                this.zzbs(parcel0.readString());
                parcel1.writeNoException();
                return true;
            }
            case 39: {
                this.zza(((zzur)zzgd.zza(parcel0, zzur.CREATOR)));
                parcel1.writeNoException();
                return true;
            }
            case 40: {
                this.zza(zzrk.zzb(parcel0.readStrongBinder()));
                parcel1.writeNoException();
                return true;
            }
            case 41: {
                zzxe zzxe0 = this.zzkg();
                parcel1.writeNoException();
                zzgd.zza(parcel1, zzxe0);
                return true;
            }
            case 42: {
                IBinder iBinder5 = parcel0.readStrongBinder();
                if(iBinder5 != null) {
                    IInterface iInterface5 = iBinder5.queryLocalInterface("com.google.android.gms.ads.internal.client.IOnPaidEventListener");
                    zzvk0 = iInterface5 instanceof zzxd ? ((zzxd)iInterface5) : new zzxf(iBinder5);
                }
                this.zza(((zzxd)zzvk0));
                parcel1.writeNoException();
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public static zzvx zzc(IBinder iBinder0) {
        if(iBinder0 == null) {
            return null;
        }
        IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
        return iInterface0 instanceof zzvx ? ((zzvx)iInterface0) : new zzvz(iBinder0);
    }
}

