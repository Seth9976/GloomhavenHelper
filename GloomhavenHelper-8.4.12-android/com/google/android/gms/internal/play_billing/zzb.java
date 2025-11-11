package com.google.android.gms.internal.play_billing;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzb extends zze implements zzd {
    zzb(IBinder iBinder0) {
        super(iBinder0, "com.android.vending.billing.IInAppBillingService");
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final int zza(int v, String s, String s1) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(3);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        Parcel parcel1 = this.zzo(5, parcel0);
        int v1 = parcel1.readInt();
        parcel1.recycle();
        return v1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final int zzb(int v, String s, String s1) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(v);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        Parcel parcel1 = this.zzo(1, parcel0);
        int v1 = parcel1.readInt();
        parcel1.recycle();
        return v1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final int zzc(int v, String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(7);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(10, parcel0);
        int v1 = parcel1.readInt();
        parcel1.recycle();
        return v1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzd(int v, String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(9);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(902, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zze(int v, String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(9);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(12, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzf(int v, String s, String s1, String s2, String s3) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(3);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        parcel0.writeString(null);
        Parcel parcel1 = this.zzo(3, parcel0);
        Bundle bundle0 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzg(int v, String s, String s1, String s2, String s3, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(v);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        parcel0.writeString(null);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(8, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzh(int v, String s, String s1, String s2, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(6);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(9, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzi(int v, String s, String s1, String s2) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(3);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        Parcel parcel1 = this.zzo(4, parcel0);
        Bundle bundle0 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle0;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzj(int v, String s, String s1, String s2, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(9);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString(s2);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(11, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzk(int v, String s, String s1, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(3);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(2, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzl(int v, String s, String s1, Bundle bundle0, Bundle bundle1) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(10);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        zzg.zzb(parcel0, bundle0);
        zzg.zzb(parcel0, bundle1);
        Parcel parcel1 = this.zzo(901, parcel0);
        Bundle bundle2 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle2;
    }

    @Override  // com.google.android.gms.internal.play_billing.zzd
    public final Bundle zzm(int v, String s, String s1, String s2, Bundle bundle0) throws RemoteException {
        Parcel parcel0 = this.zzn();
        parcel0.writeInt(8);
        parcel0.writeString(s);
        parcel0.writeString(s1);
        parcel0.writeString("subs");
        zzg.zzb(parcel0, bundle0);
        Parcel parcel1 = this.zzo(801, parcel0);
        Bundle bundle1 = (Bundle)zzg.zza(parcel1, Bundle.CREATOR);
        parcel1.recycle();
        return bundle1;
    }
}

