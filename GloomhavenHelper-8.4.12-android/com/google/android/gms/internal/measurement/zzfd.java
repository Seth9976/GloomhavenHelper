package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzfd extends zzdl {
    public static class zza extends zzdn {
        protected zzfd zza;
        private final zzfd zzb;
        private boolean zzc;

        protected zza(zzfd zzfd0) {
            this.zzb = zzfd0;
            this.zza = (zzfd)zzfd0.zza(zzd.zzd, null, null);
            this.zzc = false;
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        public Object clone() throws CloneNotSupportedException {
            zza zzfd$zza0 = (zza)this.zzb.zza(zzd.zze, null, null);
            zzfd$zza0.zza(((zzfd)this.zzt()));
            return zzfd$zza0;
        }

        @Override  // com.google.android.gms.internal.measurement.zzgp
        public final boolean h_() {
            return zzfd.zza(this.zza, false);
        }

        private static void zza(zzfd zzfd0, zzfd zzfd1) {
            zzgy.zza().zza(zzfd0).zzb(zzfd0, zzfd1);
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        protected final zzdn zza(zzdl zzdl0) {
            return this.zza(((zzfd)zzdl0));
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        public final zzdn zza(zzeh zzeh0, zzeq zzeq0) throws IOException {
            return this.zzb(zzeh0, zzeq0);
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        public final zzdn zza(byte[] arr_b, int v, int v1) throws zzfn {
            return this.zzb(arr_b, 0, v1, zzeq.zza());
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        public final zzdn zza(byte[] arr_b, int v, int v1, zzeq zzeq0) throws zzfn {
            return this.zzb(arr_b, 0, v1, zzeq0);
        }

        public final zza zza(zzfd zzfd0) {
            this.zzp();
            zza.zza(this.zza, zzfd0);
            return this;
        }

        private final zza zzb(zzeh zzeh0, zzeq zzeq0) throws IOException {
            this.zzp();
            try {
                zzgy.zza().zza(this.zza).zza(this.zza, zzei.zza(zzeh0), zzeq0);
                return this;
            }
            catch(RuntimeException runtimeException0) {
                if(runtimeException0.getCause() instanceof IOException) {
                    throw (IOException)runtimeException0.getCause();
                }
                throw runtimeException0;
            }
        }

        private final zza zzb(byte[] arr_b, int v, int v1, zzeq zzeq0) throws zzfn {
            this.zzp();
            try {
                zzgy.zza().zza(this.zza).zza(this.zza, arr_b, 0, v1, new zzdq(zzeq0));
                return this;
            }
            catch(zzfn zzfn0) {
                throw zzfn0;
            }
            catch(IndexOutOfBoundsException unused_ex) {
                throw zzfn.zza();
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", iOException0);
            }
        }

        @Override  // com.google.android.gms.internal.measurement.zzdn
        public final zzdn zzo() {
            return (zza)this.clone();
        }

        protected final void zzp() {
            if(this.zzc) {
                zzfd zzfd0 = (zzfd)this.zza.zza(zzd.zzd, null, null);
                zza.zza(zzfd0, this.zza);
                this.zza = zzfd0;
                this.zzc = false;
            }
        }

        public zzfd zzr() {
            if(this.zzc) {
                return this.zza;
            }
            zzfd zzfd0 = this.zza;
            zzgy.zza().zza(zzfd0).zzc(zzfd0);
            this.zzc = true;
            return this.zza;
        }

        public final zzfd zzs() {
            zzfd zzfd0 = (zzfd)this.zzt();
            if(!zzfd0.h_()) {
                throw new zzhv(zzfd0);
            }
            return zzfd0;
        }

        @Override  // com.google.android.gms.internal.measurement.zzgm
        public zzgn zzt() {
            return this.zzr();
        }

        @Override  // com.google.android.gms.internal.measurement.zzgm
        public zzgn zzu() {
            return this.zzs();
        }

        @Override  // com.google.android.gms.internal.measurement.zzgp
        public final zzgn zzv() {
            return this.zzb;
        }
    }

    public static abstract class zzb extends zzfd implements zzgp {
        protected zzet zzc;

        public zzb() {
            this.zzc = zzet.zza();
        }

        final zzet zza() {
            if(this.zzc.zzc()) {
                this.zzc = (zzet)this.zzc.clone();
            }
            return this.zzc;
        }
    }

    public static final class zzc extends zzdm {
        private final zzfd zza;

        public zzc(zzfd zzfd0) {
            this.zza = zzfd0;
        }
    }

    public static final class zzd {
        public static final enum int zza = 1;
        public static final enum int zzb = 2;
        public static final enum int zzc = 3;
        public static final enum int zzd = 4;
        public static final enum int zze = 5;
        public static final enum int zzf = 6;
        public static final enum int zzg = 7;
        public static final enum int zzh;
        public static final enum int zzi;
        public static final enum int zzj;
        public static final enum int zzk;
        private static final int[] zzl;
        private static final int[] zzm;
        private static final int[] zzn;

        static {
            zzd.zzl = new int[]{zzd.zza, zzd.zzb, zzd.zzc, zzd.zzd, zzd.zze, zzd.zzf, zzd.zzg};
            zzd.zzh = 1;
            zzd.zzi = 2;
            zzd.zzm = new int[]{1, 2};
            zzd.zzj = 1;
            zzd.zzk = 2;
            zzd.zzn = new int[]{1, 2};
        }

        public static int[] zza() {
            return (int[])zzd.zzl.clone();
        }
    }

    public static final class zze extends zzeo {
    }

    protected zzhx zzb;
    private int zzc;
    private static Map zzd;

    static {
        zzfd.zzd = new ConcurrentHashMap();
    }

    public zzfd() {
        this.zzb = zzhx.zza();
        this.zzc = -1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return ((zzfd)this.zza(zzd.zzf, null, null)).getClass().isInstance(object0) ? zzgy.zza().zza(this).zza(this, ((zzfd)object0)) : false;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgp
    public final boolean h_() {
        return zzfd.zza(this, true);
    }

    @Override
    public int hashCode() {
        if(this.zza != 0) {
            return this.zza;
        }
        this.zza = zzgy.zza().zza(this).zza(this);
        return this.zza;
    }

    @Override
    public String toString() {
        return zzgo.zza(this, super.toString());
    }

    static zzfd zza(Class class0) {
        zzfd zzfd0 = (zzfd)zzfd.zzd.get(class0);
        if(zzfd0 == null) {
            try {
                Class.forName(class0.getName(), true, class0.getClassLoader());
            }
            catch(ClassNotFoundException classNotFoundException0) {
                throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException0);
            }
            zzfd0 = (zzfd)zzfd.zzd.get(class0);
        }
        if(zzfd0 == null) {
            zzfd0 = (zzfd)((zzfd)zzia.zza(class0)).zza(zzd.zzf, null, null);
            if(zzfd0 == null) {
                throw new IllegalStateException();
            }
            zzfd.zzd.put(class0, zzfd0);
            return zzfd0;
        }
        return zzfd0;
    }

    protected static zzfk zza(zzfk zzfk0) {
        int v = zzfk0.size();
        return v == 0 ? zzfk0.zza(10) : zzfk0.zza(v << 1);
    }

    protected static zzfl zza(zzfl zzfl0) {
        int v = zzfl0.size();
        return v == 0 ? zzfl0.zzc(10) : zzfl0.zzc(v << 1);
    }

    protected static Object zza(zzgn zzgn0, String s, Object[] arr_object) {
        return new zzha(zzgn0, s, arr_object);
    }

    static Object zza(Method method0, Object object0, Object[] arr_object) {
        try {
            return method0.invoke(object0, arr_object);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new RuntimeException("Couldn\'t use Java reflection to implement protocol message reflection.", illegalAccessException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            Throwable throwable0 = invocationTargetException0.getCause();
            if(throwable0 instanceof RuntimeException) {
                throw (RuntimeException)throwable0;
            }
            if(throwable0 instanceof Error) {
                throw (Error)throwable0;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", throwable0);
        }
    }

    protected static void zza(Class class0, zzfd zzfd0) {
        zzfd.zzd.put(class0, zzfd0);
    }

    protected static final boolean zza(zzfd zzfd0, boolean z) {
        int v = (byte)(((Byte)zzfd0.zza(zzd.zza, null, null)));
        if(v == 1) {
            return true;
        }
        if(v == 0) {
            return false;
        }
        boolean z1 = zzgy.zza().zza(zzfd0).zzd(zzfd0);
        if(z) {
            zzfd0.zza(2, (z1 ? zzfd0 : null), null);
        }
        return z1;
    }

    protected abstract Object zza(int arg1, Object arg2, Object arg3);

    @Override  // com.google.android.gms.internal.measurement.zzgn
    public final void zza(zzek zzek0) throws IOException {
        zzgy.zza().zza(this).zza(this, zzen.zza(zzek0));
    }

    @Override  // com.google.android.gms.internal.measurement.zzdl
    final int zzbi() {
        return this.zzc;
    }

    protected final zza zzbj() {
        return (zza)this.zza(zzd.zze, null, null);
    }

    public final zza zzbk() {
        zza zzfd$zza0 = (zza)this.zza(zzd.zze, null, null);
        zzfd$zza0.zza(this);
        return zzfd$zza0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgn
    public final int zzbl() {
        if(this.zzc == -1) {
            this.zzc = zzgy.zza().zza(this).zzb(this);
        }
        return this.zzc;
    }

    protected static zzfi zzbm() {
        return zzff.zzd();
    }

    protected static zzfl zzbn() {
        return zzgb.zzd();
    }

    protected static zzfk zzbo() {
        return zzhb.zzd();
    }

    @Override  // com.google.android.gms.internal.measurement.zzgn
    public final zzgm zzbp() {
        zzgm zzgm0 = (zza)this.zza(zzd.zze, null, null);
        ((zza)zzgm0).zza(this);
        return zzgm0;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgn
    public final zzgm zzbq() {
        return (zza)this.zza(zzd.zze, null, null);
    }

    @Override  // com.google.android.gms.internal.measurement.zzdl
    final void zzc(int v) {
        this.zzc = v;
    }

    @Override  // com.google.android.gms.internal.measurement.zzgp
    public final zzgn zzv() {
        return (zzfd)this.zza(zzd.zzf, null, null);
    }
}

