package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GmsClientSupervisor {
    public static final class zza {
        private final ComponentName mComponentName;
        private final String zzdr;
        private final String zzds;
        private final int zzdt;

        public zza(ComponentName componentName0, int v) {
            this.zzdr = null;
            this.zzds = null;
            this.mComponentName = (ComponentName)Preconditions.checkNotNull(componentName0);
            this.zzdt = 0x81;
        }

        public zza(String s, int v) {
            this.zzdr = Preconditions.checkNotEmpty(s);
            this.zzds = "com.google.android.gms";
            this.mComponentName = null;
            this.zzdt = 0x81;
        }

        public zza(String s, String s1, int v) {
            this.zzdr = Preconditions.checkNotEmpty(s);
            this.zzds = Preconditions.checkNotEmpty(s1);
            this.mComponentName = null;
            this.zzdt = v;
        }

        @Override
        public final boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof zza ? Objects.equal(this.zzdr, ((zza)object0).zzdr) && Objects.equal(this.zzds, ((zza)object0).zzds) && Objects.equal(this.mComponentName, ((zza)object0).mComponentName) && this.zzdt == ((zza)object0).zzdt : false;
        }

        public final ComponentName getComponentName() {
            return this.mComponentName;
        }

        public final String getPackage() {
            return this.zzds;
        }

        @Override
        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.zzdr, this.zzds, this.mComponentName, this.zzdt});
        }

        @Override
        public final String toString() {
            return this.zzdr == null ? this.mComponentName.flattenToString() : this.zzdr;
        }

        public final Intent zzb(Context context0) {
            return this.zzdr == null ? new Intent().setComponent(this.mComponentName) : new Intent(this.zzdr).setPackage(this.zzds);
        }

        public final int zzq() {
            return this.zzdt;
        }
    }

    private static final Object zzdp;
    private static GmsClientSupervisor zzdq;

    static {
        GmsClientSupervisor.zzdp = new Object();
    }

    @KeepForSdk
    public boolean bindService(ComponentName componentName0, ServiceConnection serviceConnection0, String s) {
        return this.zza(new zza(componentName0, 0x81), serviceConnection0, s);
    }

    @KeepForSdk
    public boolean bindService(String s, ServiceConnection serviceConnection0, String s1) {
        return this.zza(new zza(s, 0x81), serviceConnection0, s1);
    }

    @KeepForSdk
    public static GmsClientSupervisor getInstance(Context context0) {
        synchronized(GmsClientSupervisor.zzdp) {
            if(GmsClientSupervisor.zzdq == null) {
                GmsClientSupervisor.zzdq = new zze(context0.getApplicationContext());
            }
            return GmsClientSupervisor.zzdq;
        }
    }

    @KeepForSdk
    public void unbindService(ComponentName componentName0, ServiceConnection serviceConnection0, String s) {
        this.zzb(new zza(componentName0, 0x81), serviceConnection0, s);
    }

    @KeepForSdk
    public void unbindService(String s, ServiceConnection serviceConnection0, String s1) {
        this.zzb(new zza(s, 0x81), serviceConnection0, s1);
    }

    public final void zza(String s, String s1, int v, ServiceConnection serviceConnection0, String s2) {
        this.zzb(new zza(s, s1, v), serviceConnection0, s2);
    }

    protected abstract boolean zza(zza arg1, ServiceConnection arg2, String arg3);

    protected abstract void zzb(zza arg1, ServiceConnection arg2, String arg3);
}

