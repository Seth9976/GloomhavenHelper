package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzia {
    static final class zza extends zzd {
        zza(Unsafe unsafe0) {
            super(unsafe0);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final byte zza(Object object0, long v) {
            return zzia.zza ? zzia.zzk(object0, v) : zzia.zzl(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, byte b) {
            if(zzia.zza) {
                zzia.zzc(object0, v, b);
                return;
            }
            zzia.zzd(object0, v, b);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, double f) {
            this.zza(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, float f) {
            this.zza(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, boolean z) {
            if(zzia.zza) {
                zzia.zzd(object0, v, z);
                return;
            }
            zzia.zze(object0, v, z);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final boolean zzb(Object object0, long v) {
            return zzia.zza ? zzia.zzm(object0, v) : zzia.zzn(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final float zzc(Object object0, long v) {
            return Float.intBitsToFloat(this.zze(object0, v));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final double zzd(Object object0, long v) {
            return Double.longBitsToDouble(this.zzf(object0, v));
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final byte zza(Object object0, long v) {
            return this.zza.getByte(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, byte b) {
            this.zza.putByte(object0, v, b);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, double f) {
            this.zza.putDouble(object0, v, f);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, float f) {
            this.zza.putFloat(object0, v, f);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, boolean z) {
            this.zza.putBoolean(object0, v, z);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final boolean zzb(Object object0, long v) {
            return this.zza.getBoolean(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final float zzc(Object object0, long v) {
            return this.zza.getFloat(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final double zzd(Object object0, long v) {
            return this.zza.getDouble(object0, v);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe0) {
            super(unsafe0);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final byte zza(Object object0, long v) {
            return zzia.zza ? zzia.zzk(object0, v) : zzia.zzl(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, byte b) {
            if(zzia.zza) {
                zzia.zzc(object0, v, b);
                return;
            }
            zzia.zzd(object0, v, b);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, double f) {
            this.zza(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, float f) {
            this.zza(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final void zza(Object object0, long v, boolean z) {
            if(zzia.zza) {
                zzia.zzd(object0, v, z);
                return;
            }
            zzia.zze(object0, v, z);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final boolean zzb(Object object0, long v) {
            return zzia.zza ? zzia.zzm(object0, v) : zzia.zzn(object0, v);
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final float zzc(Object object0, long v) {
            return Float.intBitsToFloat(this.zze(object0, v));
        }

        @Override  // com.google.android.gms.internal.measurement.zzia$zzd
        public final double zzd(Object object0, long v) {
            return Double.longBitsToDouble(this.zzf(object0, v));
        }
    }

    static abstract class zzd {
        Unsafe zza;

        zzd(Unsafe unsafe0) {
            this.zza = unsafe0;
        }

        public abstract byte zza(Object arg1, long arg2);

        public abstract void zza(Object arg1, long arg2, byte arg3);

        public abstract void zza(Object arg1, long arg2, double arg3);

        public abstract void zza(Object arg1, long arg2, float arg3);

        public final void zza(Object object0, long v, int v1) {
            this.zza.putInt(object0, v, v1);
        }

        public final void zza(Object object0, long v, long v1) {
            this.zza.putLong(object0, v, v1);
        }

        public abstract void zza(Object arg1, long arg2, boolean arg3);

        public abstract boolean zzb(Object arg1, long arg2);

        public abstract float zzc(Object arg1, long arg2);

        public abstract double zzd(Object arg1, long arg2);

        public final int zze(Object object0, long v) {
            return this.zza.getInt(object0, v);
        }

        public final long zzf(Object object0, long v) {
            return this.zza.getLong(object0, v);
        }
    }

    static final boolean zza;
    private static final Logger zzb;
    private static final Unsafe zzc;
    private static final Class zzd;
    private static final boolean zze;
    private static final boolean zzf;
    private static final zzd zzg;
    private static final boolean zzh;
    private static final boolean zzi;
    private static final long zzj;
    private static final long zzk;
    private static final long zzl;
    private static final long zzm;
    private static final long zzn;
    private static final long zzo;
    private static final long zzp;
    private static final long zzq;
    private static final long zzr;
    private static final long zzs;
    private static final long zzt;
    private static final long zzu;
    private static final long zzv;
    private static final long zzw;
    private static final int zzx;

    static {
        long v;
        zzia.zzb = Logger.getLogger(zzia.class.getName());
        zzia.zzc = zzia.zzc();
        zzia.zzd = zzdo.zzb();
        zzia.zze = zzia.zzd(Long.TYPE);
        zzia.zzf = zzia.zzd(Integer.TYPE);
        zzd zzia$zzd0 = null;
        if(zzia.zzc != null) {
            zzia$zzd0 = new zzb(zzia.zzc);
        }
        zzia.zzg = zzia$zzd0;
        zzia.zzh = zzia.zze();
        zzia.zzi = zzia.zzd();
        zzia.zzj = (long)zzia.zzb(byte[].class);
        zzia.zzk = (long)zzia.zzb(boolean[].class);
        zzia.zzl = (long)zzia.zzc(boolean[].class);
        zzia.zzm = (long)zzia.zzb(int[].class);
        zzia.zzn = (long)zzia.zzc(int[].class);
        zzia.zzo = (long)zzia.zzb(long[].class);
        zzia.zzp = (long)zzia.zzc(long[].class);
        zzia.zzq = (long)zzia.zzb(float[].class);
        zzia.zzr = (long)zzia.zzc(float[].class);
        zzia.zzs = (long)zzia.zzb(double[].class);
        zzia.zzt = (long)zzia.zzc(double[].class);
        zzia.zzu = (long)zzia.zzb(Object[].class);
        zzia.zzv = (long)zzia.zzc(Object[].class);
        Field field0 = zzia.zzf();
        if(field0 == null) {
            v = -1L;
        }
        else {
            zzd zzia$zzd1 = zzia.zzg;
            v = zzia$zzd1 == null ? -1L : zzia$zzd1.zza.objectFieldOffset(field0);
        }
        zzia.zzw = v;
        zzia.zzx = (int)(zzia.zzj & 7L);
        zzia.zza = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    static byte zza(byte[] arr_b, long v) {
        return zzia.zzg.zza(arr_b, zzia.zzj + v);
    }

    static int zza(Object object0, long v) {
        return zzia.zzg.zze(object0, v);
    }

    static Object zza(Class class0) {
        try {
            return zzia.zzc.allocateInstance(class0);
        }
        catch(InstantiationException instantiationException0) {
            throw new IllegalStateException(instantiationException0);
        }
    }

    private static Field zza(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    static void zza(Object object0, long v, double f) {
        zzia.zzg.zza(object0, v, f);
    }

    static void zza(Object object0, long v, float f) {
        zzia.zzg.zza(object0, v, f);
    }

    static void zza(Object object0, long v, int v1) {
        zzia.zzg.zza(object0, v, v1);
    }

    static void zza(Object object0, long v, long v1) {
        zzia.zzg.zza(object0, v, v1);
    }

    static void zza(Object object0, long v, Object object1) {
        zzia.zzg.zza.putObject(object0, v, object1);
    }

    static void zza(Object object0, long v, boolean z) {
        zzia.zzg.zza(object0, v, z);
    }

    static void zza(byte[] arr_b, long v, byte b) {
        zzia.zzg.zza(arr_b, zzia.zzj + v, b);
    }

    static boolean zza() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    private static int zzb(Class class0) {
        return zzia.zzi ? zzia.zzg.zza.arrayBaseOffset(class0) : -1;
    }

    static long zzb(Object object0, long v) {
        return zzia.zzg.zzf(object0, v);
    }

    static boolean zzb() [...] // 潜在的解密器

    // 去混淆评级： 低(20)
    private static int zzc(Class class0) {
        return zzia.zzi ? zzia.zzg.zza.arrayIndexScale(class0) : -1;
    }

    static Unsafe zzc() {
        try {
            return (Unsafe)AccessController.doPrivileged(new zzic());
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static void zzc(Object object0, long v, byte b) {
        int v1 = (~((int)v) & 3) << 3;
        zzia.zza(object0, -4L & v, (0xFF & b) << v1 | zzia.zza(object0, -4L & v) & ~(0xFF << v1));
    }

    static boolean zzc(Object object0, long v) {
        return zzia.zzg.zzb(object0, v);
    }

    static float zzd(Object object0, long v) {
        return zzia.zzg.zzc(object0, v);
    }

    private static void zzd(Object object0, long v, byte b) {
        int v1 = (((int)v) & 3) << 3;
        zzia.zza(object0, -4L & v, (0xFF & b) << v1 | zzia.zza(object0, -4L & v) & ~(0xFF << v1));
    }

    private static void zzd(Object object0, long v, boolean z) {
        zzia.zzc(object0, v, ((byte)z));
    }

    private static boolean zzd() {
        Unsafe unsafe0 = zzia.zzc;
        if(unsafe0 == null) {
            return false;
        }
        try {
            Class class0 = unsafe0.getClass();
            class0.getMethod("objectFieldOffset", Field.class);
            class0.getMethod("arrayBaseOffset", Class.class);
            class0.getMethod("arrayIndexScale", Class.class);
            class0.getMethod("getInt", Object.class, Long.TYPE);
            class0.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            class0.getMethod("getLong", Object.class, Long.TYPE);
            class0.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            class0.getMethod("getObject", Object.class, Long.TYPE);
            class0.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            class0.getMethod("getByte", Object.class, Long.TYPE);
            class0.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            class0.getMethod("getBoolean", Object.class, Long.TYPE);
            class0.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            class0.getMethod("getFloat", Object.class, Long.TYPE);
            class0.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            class0.getMethod("getDouble", Object.class, Long.TYPE);
            class0.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        }
        catch(Throwable throwable0) {
            zzia.zzb.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", "platform method missing - proto runtime falling back to safer methods: " + throwable0);
            return false;
        }
    }

    // 去混淆评级： 低(30)
    private static boolean zzd(Class class0) {
        return false;
    }

    static double zze(Object object0, long v) {
        return zzia.zzg.zzd(object0, v);
    }

    private static void zze(Object object0, long v, boolean z) {
        zzia.zzd(object0, v, ((byte)z));
    }

    private static boolean zze() {
        Unsafe unsafe0 = zzia.zzc;
        if(unsafe0 == null) {
            return false;
        }
        try {
            Class class0 = unsafe0.getClass();
            class0.getMethod("objectFieldOffset", Field.class);
            class0.getMethod("getLong", Object.class, Long.TYPE);
            if(zzia.zzf() == null) {
                return false;
            }
            class0.getMethod("getByte", Long.TYPE);
            class0.getMethod("putByte", Long.TYPE, Byte.TYPE);
            class0.getMethod("getInt", Long.TYPE);
            class0.getMethod("putInt", Long.TYPE, Integer.TYPE);
            class0.getMethod("getLong", Long.TYPE);
            class0.getMethod("putLong", Long.TYPE, Long.TYPE);
            class0.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            class0.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        }
        catch(Throwable throwable0) {
            zzia.zzb.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", "platform method missing - proto runtime falling back to safer methods: " + throwable0);
            return false;
        }
    }

    static Object zzf(Object object0, long v) {
        return zzia.zzg.zza.getObject(object0, v);
    }

    private static Field zzf() {
        Field field0 = zzia.zza(Buffer.class, "address");
        return field0 == null || field0.getType() != Long.TYPE ? null : field0;
    }

    private static byte zzk(Object object0, long v) {
        return (byte)(zzia.zza(object0, -4L & v) >>> ((int)((~v & 3L) << 3)));
    }

    private static byte zzl(Object object0, long v) {
        return (byte)(zzia.zza(object0, -4L & v) >>> ((int)((v & 3L) << 3)));
    }

    private static boolean zzm(Object object0, long v) {
        return zzia.zzk(object0, v) != 0;
    }

    private static boolean zzn(Object object0, long v) {
        return zzia.zzl(object0, v) != 0;
    }
}

