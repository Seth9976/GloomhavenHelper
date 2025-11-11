package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzdyz extends zzdxd {
    public static class zza extends zzdxg {
        private final zzdyz zzhst;
        protected zzdyz zzhsu;
        protected boolean zzhsv;

        protected zza(zzdyz zzdyz0) {
            this.zzhst = zzdyz0;
            this.zzhsu = (zzdyz)zzdyz0.zza(zzf.zzhtf, null, null);
            this.zzhsv = false;
        }

        @Override  // com.google.android.gms.internal.ads.zzdxg
        public Object clone() throws CloneNotSupportedException {
            zza zzdyz$zza0 = (zza)this.zzhst.zza(zzf.zzhtg, null, null);
            zzdyz$zza0.zza(((zzdyz)this.zzbcw()));
            return zzdyz$zza0;
        }

        @Override  // com.google.android.gms.internal.ads.zzeaj
        public final boolean isInitialized() {
            return zzdyz.zza(this.zzhsu, false);
        }

        private static void zza(zzdyz zzdyz0, zzdyz zzdyz1) {
            zzeaw.zzbem().zzba(zzdyz0).zzf(zzdyz0, zzdyz1);
        }

        @Override  // com.google.android.gms.internal.ads.zzdxg
        protected final zzdxg zza(zzdxd zzdxd0) {
            return this.zza(((zzdyz)zzdxd0));
        }

        @Override  // com.google.android.gms.internal.ads.zzdxg
        public final zzdxg zza(zzdxz zzdxz0, zzdym zzdym0) throws IOException {
            return this.zzb(zzdxz0, zzdym0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdxg
        public final zzdxg zza(byte[] arr_b, int v, int v1, zzdym zzdym0) throws zzdzh {
            return this.zzb(arr_b, 0, v1, zzdym0);
        }

        public final zza zza(zzdyz zzdyz0) {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            zza.zza(this.zzhsu, zzdyz0);
            return this;
        }

        private final zza zzb(zzdxz zzdxz0, zzdym zzdym0) throws IOException {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            try {
                zzeaw.zzbem().zzba(this.zzhsu).zza(this.zzhsu, zzdyg.zza(zzdxz0), zzdym0);
                return this;
            }
            catch(RuntimeException runtimeException0) {
                if(runtimeException0.getCause() instanceof IOException) {
                    throw (IOException)runtimeException0.getCause();
                }
                throw runtimeException0;
            }
        }

        private final zza zzb(byte[] arr_b, int v, int v1, zzdym zzdym0) throws zzdzh {
            if(this.zzhsv) {
                this.zzbct();
                this.zzhsv = false;
            }
            try {
                zzeaw.zzbem().zzba(this.zzhsu).zza(this.zzhsu, arr_b, 0, v1, new zzdxm(zzdym0));
                return this;
            }
            catch(zzdzh zzdzh0) {
                throw zzdzh0;
            }
            catch(IndexOutOfBoundsException unused_ex) {
                throw zzdzh.zzbdi();
            }
            catch(IOException iOException0) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", iOException0);
            }
        }

        @Override  // com.google.android.gms.internal.ads.zzdxg
        public final zzdxg zzbal() {
            return (zza)this.clone();
        }

        protected void zzbct() {
            zzdyz zzdyz0 = (zzdyz)this.zzhsu.zza(zzf.zzhtf, null, null);
            zza.zza(zzdyz0, this.zzhsu);
            this.zzhsu = zzdyz0;
        }

        public zzdyz zzbcu() {
            if(this.zzhsv) {
                return this.zzhsu;
            }
            zzdyz zzdyz0 = this.zzhsu;
            zzeaw.zzbem().zzba(zzdyz0).zzan(zzdyz0);
            this.zzhsv = true;
            return this.zzhsu;
        }

        public final zzdyz zzbcv() {
            zzdyz zzdyz0 = (zzdyz)this.zzbcw();
            if(!zzdyz0.isInitialized()) {
                throw new zzebw(zzdyz0);
            }
            return zzdyz0;
        }

        @Override  // com.google.android.gms.internal.ads.zzeak
        public zzeah zzbcw() {
            return this.zzbcu();
        }

        @Override  // com.google.android.gms.internal.ads.zzeak
        public zzeah zzbcx() {
            return this.zzbcv();
        }

        @Override  // com.google.android.gms.internal.ads.zzeaj
        public final zzeah zzbcy() {
            return this.zzhst;
        }
    }

    public static abstract class zzb extends zzdyz implements zzeaj {
        protected zzdyp zzhsz;

        public zzb() {
            this.zzhsz = zzdyp.zzbci();
        }

        final zzdyp zzbdf() {
            if(this.zzhsz.isImmutable()) {
                this.zzhsz = (zzdyp)this.zzhsz.clone();
            }
            return this.zzhsz;
        }
    }

    public static final class zzc extends zzdxi {
        private final zzdyz zzhst;

        public zzc(zzdyz zzdyz0) {
            this.zzhst = zzdyz0;
        }
    }

    public static final class zzd extends zzdyk {
    }

    static final class zze implements zzdyr {
        @Override
        public final int compareTo(Object object0) {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final zzeak zza(zzeak zzeak0, zzeah zzeah0) {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final zzeaq zza(zzeaq zzeaq0, zzeaq zzeaq1) {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final int zzaf() {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final zzecm zzbcn() {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final zzecp zzbco() {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final boolean zzbcp() {
            throw new NoSuchMethodError();
        }

        @Override  // com.google.android.gms.internal.ads.zzdyr
        public final boolean zzbcq() {
            throw new NoSuchMethodError();
        }
    }

    public static final class zzf {
        public static final enum int zzhtc = 1;
        public static final enum int zzhtd = 2;
        public static final enum int zzhte = 3;
        public static final enum int zzhtf = 4;
        public static final enum int zzhtg = 5;
        public static final enum int zzhth = 6;
        public static final enum int zzhti = 7;
        private static final int[] zzhtj;
        public static final enum int zzhtk;
        public static final enum int zzhtl;
        private static final int[] zzhtm;
        public static final enum int zzhtn;
        public static final enum int zzhto;
        private static final int[] zzhtp;

        static {
            zzf.zzhtj = new int[]{zzf.zzhtc, zzf.zzhtd, zzf.zzhte, zzf.zzhtf, zzf.zzhtg, zzf.zzhth, zzf.zzhti};
            zzf.zzhtk = 1;
            zzf.zzhtl = 2;
            zzf.zzhtm = new int[]{1, 2};
            zzf.zzhtn = 1;
            zzf.zzhto = 2;
            zzf.zzhtp = new int[]{1, 2};
        }

        public static int[] zzbdh() {
            return (int[])zzf.zzhtj.clone();
        }
    }

    protected zzeby zzhsw;
    private int zzhsx;
    private static Map zzhsy;

    static {
        zzdyz.zzhsy = new ConcurrentHashMap();
    }

    public zzdyz() {
        this.zzhsw = zzeby.zzbff();
        this.zzhsx = -1;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return ((zzdyz)this.zza(zzf.zzhth, null, null)).getClass().isInstance(object0) ? zzeaw.zzbem().zzba(this).equals(this, ((zzdyz)object0)) : false;
    }

    @Override
    public int hashCode() {
        if(this.zzhnp != 0) {
            return this.zzhnp;
        }
        this.zzhnp = zzeaw.zzbem().zzba(this).hashCode(this);
        return this.zzhnp;
    }

    @Override  // com.google.android.gms.internal.ads.zzeaj
    public final boolean isInitialized() {
        return zzdyz.zza(this, true);
    }

    @Override
    public String toString() {
        return zzeam.zza(this, super.toString());
    }

    protected static zzdyz zza(zzdyz zzdyz0, zzdxn zzdxn0) throws zzdzh {
        return zzdyz.zzb(zzdyz.zzb(zzdyz.zzb(zzdyz0, zzdxn0, zzdym.zzbcg())));
    }

    protected static zzdyz zza(zzdyz zzdyz0, zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        return zzdyz.zzb(zzdyz.zzb(zzdyz0, zzdxn0, zzdym0));
    }

    private static zzdyz zza(zzdyz zzdyz0, zzdxz zzdxz0, zzdym zzdym0) throws zzdzh {
        zzdyz zzdyz1 = (zzdyz)zzdyz0.zza(zzf.zzhtf, null, null);
        try {
            zzebd zzebd0 = zzeaw.zzbem().zzba(zzdyz1);
            zzebd0.zza(zzdyz1, zzdyg.zza(zzdxz0), zzdym0);
            zzebd0.zzan(zzdyz1);
            return zzdyz1;
        }
        catch(IOException iOException0) {
            throw iOException0.getCause() instanceof zzdzh ? ((zzdzh)iOException0.getCause()) : new zzdzh(iOException0.getMessage()).zzl(zzdyz1);
        }
        catch(RuntimeException runtimeException0) {
            if(runtimeException0.getCause() instanceof zzdzh) {
                throw (zzdzh)runtimeException0.getCause();
            }
            throw runtimeException0;
        }
    }

    protected static zzdyz zza(zzdyz zzdyz0, byte[] arr_b) throws zzdzh {
        zzdym zzdym0 = zzdym.zzbcg();
        return zzdyz.zzb(zzdyz.zza(zzdyz0, arr_b, 0, arr_b.length, zzdym0));
    }

    private static zzdyz zza(zzdyz zzdyz0, byte[] arr_b, int v, int v1, zzdym zzdym0) throws zzdzh {
        zzdyz zzdyz1 = (zzdyz)zzdyz0.zza(zzf.zzhtf, null, null);
        try {
            zzebd zzebd0 = zzeaw.zzbem().zzba(zzdyz1);
            zzebd0.zza(zzdyz1, arr_b, 0, v1, new zzdxm(zzdym0));
            zzebd0.zzan(zzdyz1);
            if(zzdyz1.zzhnp != 0) {
                throw new RuntimeException();
            }
            return zzdyz1;
        }
        catch(IOException iOException0) {
            throw iOException0.getCause() instanceof zzdzh ? ((zzdzh)iOException0.getCause()) : new zzdzh(iOException0.getMessage()).zzl(zzdyz1);
        }
        catch(IndexOutOfBoundsException unused_ex) {
            throw zzdzh.zzbdi().zzl(zzdyz1);
        }
    }

    protected static zzdyz zza(zzdyz zzdyz0, byte[] arr_b, zzdym zzdym0) throws zzdzh {
        return zzdyz.zzb(zzdyz.zza(zzdyz0, arr_b, 0, arr_b.length, zzdym0));
    }

    protected static zzdzg zza(zzdzg zzdzg0) {
        int v = zzdzg0.size();
        return v == 0 ? zzdzg0.zzgk(10) : zzdzg0.zzgk(v << 1);
    }

    protected static zzdzi zza(zzdzi zzdzi0) {
        int v = zzdzi0.size();
        return v == 0 ? zzdzi0.zzfd(10) : zzdzi0.zzfd(v << 1);
    }

    protected static Object zza(zzeah zzeah0, String s, Object[] arr_object) {
        return new zzeay(zzeah0, s, arr_object);
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

    protected static void zza(Class class0, zzdyz zzdyz0) {
        zzdyz.zzhsy.put(class0, zzdyz0);
    }

    protected static final boolean zza(zzdyz zzdyz0, boolean z) {
        int v = (byte)(((Byte)zzdyz0.zza(zzf.zzhtc, null, null)));
        if(v == 1) {
            return true;
        }
        if(v == 0) {
            return false;
        }
        boolean z1 = zzeaw.zzbem().zzba(zzdyz0).zzaz(zzdyz0);
        if(z) {
            zzdyz0.zza(2, (z1 ? zzdyz0 : null), null);
        }
        return z1;
    }

    protected abstract Object zza(int arg1, Object arg2, Object arg3);

    private static zzdyz zzb(zzdyz zzdyz0) throws zzdzh {
        if(zzdyz0 != null && !zzdyz0.isInitialized()) {
            throw new zzdzh(new zzebw(zzdyz0).getMessage()).zzl(zzdyz0);
        }
        return zzdyz0;
    }

    private static zzdyz zzb(zzdyz zzdyz0, zzdxn zzdxn0, zzdym zzdym0) throws zzdzh {
        zzdxz zzdxz0 = zzdxn0.zzbau();
        zzdyz zzdyz1 = zzdyz.zza(zzdyz0, zzdxz0, zzdym0);
        try {
            zzdxz0.zzfh(0);
            return zzdyz1;
        }
        catch(zzdzh zzdzh0) {
            throw zzdzh0.zzl(zzdyz1);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final void zzb(zzdyi zzdyi0) throws IOException {
        zzeaw.zzbem().zzba(this).zza(this, zzdyj.zza(zzdyi0));
    }

    @Override  // com.google.android.gms.internal.ads.zzdxd
    final int zzbaj() {
        return this.zzhsx;
    }

    @Override  // com.google.android.gms.internal.ads.zzeaj
    public final zzeah zzbcy() {
        return (zzdyz)this.zza(zzf.zzhth, null, null);
    }

    protected final zza zzbcz() {
        return (zza)this.zza(zzf.zzhtg, null, null);
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final int zzbda() {
        if(this.zzhsx == -1) {
            this.zzhsx = zzeaw.zzbem().zzba(this).zzax(this);
        }
        return this.zzhsx;
    }

    protected static zzdzg zzbdb() {
        return zzdza.zzbdg();
    }

    protected static zzdzi zzbdc() {
        return zzeav.zzbel();
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final zzeak zzbdd() {
        zzeak zzeak0 = (zza)this.zza(zzf.zzhtg, null, null);
        ((zza)zzeak0).zza(this);
        return zzeak0;
    }

    @Override  // com.google.android.gms.internal.ads.zzeah
    public final zzeak zzbde() {
        return (zza)this.zza(zzf.zzhtg, null, null);
    }

    static zzdyz zzf(Class class0) {
        zzdyz zzdyz0 = (zzdyz)zzdyz.zzhsy.get(class0);
        if(zzdyz0 == null) {
            try {
                Class.forName(class0.getName(), true, class0.getClassLoader());
            }
            catch(ClassNotFoundException classNotFoundException0) {
                throw new IllegalStateException("Class initialization cannot fail.", classNotFoundException0);
            }
            zzdyz0 = (zzdyz)zzdyz.zzhsy.get(class0);
        }
        if(zzdyz0 == null) {
            zzdyz0 = (zzdyz)((zzdyz)zzecb.zzj(class0)).zza(zzf.zzhth, null, null);
            if(zzdyz0 == null) {
                throw new IllegalStateException();
            }
            zzdyz.zzhsy.put(class0, zzdyz0);
            return zzdyz0;
        }
        return zzdyz0;
    }

    @Override  // com.google.android.gms.internal.ads.zzdxd
    final void zzfa(int v) {
        this.zzhsx = v;
    }
}

