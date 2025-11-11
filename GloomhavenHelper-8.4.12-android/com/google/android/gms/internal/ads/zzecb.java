package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzecb {
    static final class zza extends zzd {
        zza(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(long v, byte b) {
            Memory.pokeByte(((int)v), b);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, double f) {
            this.zza(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, float f) {
            this.zzb(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, boolean z) {
            if(zzecb.zzhyj) {
                zzecb.zzb(object0, v, z);
                return;
            }
            zzecb.zzc(object0, v, z);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(byte[] arr_b, long v, long v1, long v2) {
            Memory.pokeByteArray(((int)v1), arr_b, ((int)v), ((int)v2));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zze(Object object0, long v, byte b) {
            if(zzecb.zzhyj) {
                zzecb.zza(object0, v, b);
                return;
            }
            zzecb.zzb(object0, v, b);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final boolean zzm(Object object0, long v) {
            return zzecb.zzhyj ? zzecb.zzs(object0, v) : zzecb.zzt(object0, v);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final float zzn(Object object0, long v) {
            return Float.intBitsToFloat(this.zzk(object0, v));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final double zzo(Object object0, long v) {
            return Double.longBitsToDouble(this.zzl(object0, v));
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final byte zzy(Object object0, long v) {
            return zzecb.zzhyj ? zzecb.zzq(object0, v) : zzecb.zzr(object0, v);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(long v, byte b) {
            this.zzhym.putByte(v, b);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, double f) {
            this.zzhym.putDouble(object0, v, f);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, float f) {
            this.zzhym.putFloat(object0, v, f);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, boolean z) {
            this.zzhym.putBoolean(object0, v, z);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(byte[] arr_b, long v, long v1, long v2) {
            this.zzhym.copyMemory(arr_b, zzecb.zzhxu + v, null, v1, v2);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zze(Object object0, long v, byte b) {
            this.zzhym.putByte(object0, v, b);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final boolean zzm(Object object0, long v) {
            return this.zzhym.getBoolean(object0, v);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final float zzn(Object object0, long v) {
            return this.zzhym.getFloat(object0, v);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final double zzo(Object object0, long v) {
            return this.zzhym.getDouble(object0, v);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final byte zzy(Object object0, long v) {
            return this.zzhym.getByte(object0, v);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe0) {
            super(unsafe0);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(long v, byte b) {
            Memory.pokeByte(v, b);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, double f) {
            this.zza(object0, v, Double.doubleToLongBits(f));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, float f) {
            this.zzb(object0, v, Float.floatToIntBits(f));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(Object object0, long v, boolean z) {
            if(zzecb.zzhyj) {
                zzecb.zzb(object0, v, z);
                return;
            }
            zzecb.zzc(object0, v, z);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zza(byte[] arr_b, long v, long v1, long v2) {
            Memory.pokeByteArray(v1, arr_b, ((int)v), ((int)v2));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final void zze(Object object0, long v, byte b) {
            if(zzecb.zzhyj) {
                zzecb.zza(object0, v, b);
                return;
            }
            zzecb.zzb(object0, v, b);
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final boolean zzm(Object object0, long v) {
            return zzecb.zzhyj ? zzecb.zzs(object0, v) : zzecb.zzt(object0, v);
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final float zzn(Object object0, long v) {
            return Float.intBitsToFloat(this.zzk(object0, v));
        }

        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final double zzo(Object object0, long v) {
            return Double.longBitsToDouble(this.zzl(object0, v));
        }

        // 去混淆评级： 低(20)
        @Override  // com.google.android.gms.internal.ads.zzecb$zzd
        public final byte zzy(Object object0, long v) {
            return zzecb.zzhyj ? zzecb.zzq(object0, v) : zzecb.zzr(object0, v);
        }
    }

    static abstract class zzd {
        Unsafe zzhym;

        zzd(Unsafe unsafe0) {
            this.zzhym = unsafe0;
        }

        public abstract void zza(long arg1, byte arg2);

        public abstract void zza(Object arg1, long arg2, double arg3);

        public abstract void zza(Object arg1, long arg2, float arg3);

        public final void zza(Object object0, long v, long v1) {
            this.zzhym.putLong(object0, v, v1);
        }

        public abstract void zza(Object arg1, long arg2, boolean arg3);

        public abstract void zza(byte[] arg1, long arg2, long arg3, long arg4);

        public final void zzb(Object object0, long v, int v1) {
            this.zzhym.putInt(object0, v, v1);
        }

        public abstract void zze(Object arg1, long arg2, byte arg3);

        public final int zzk(Object object0, long v) {
            return this.zzhym.getInt(object0, v);
        }

        public final long zzl(Object object0, long v) {
            return this.zzhym.getLong(object0, v);
        }

        public abstract boolean zzm(Object arg1, long arg2);

        public abstract float zzn(Object arg1, long arg2);

        public abstract double zzo(Object arg1, long arg2);

        public abstract byte zzy(Object arg1, long arg2);
    }

    private static final Logger logger;
    private static final Unsafe zzhbt;
    private static final Class zzhnw;
    private static final boolean zzhpd;
    private static final boolean zzhxq;
    private static final boolean zzhxr;
    private static final zzd zzhxs;
    private static final boolean zzhxt;
    static final long zzhxu;
    private static final long zzhxv;
    private static final long zzhxw;
    private static final long zzhxx;
    private static final long zzhxy;
    private static final long zzhxz;
    private static final long zzhya;
    private static final long zzhyb;
    private static final long zzhyc;
    private static final long zzhyd;
    private static final long zzhye;
    private static final long zzhyf;
    private static final long zzhyg;
    private static final long zzhyh;
    private static final int zzhyi;
    static final boolean zzhyj;

    static {
        long v;
        zzecb.logger = Logger.getLogger(zzecb.class.getName());
        zzecb.zzhbt = zzecb.zzbfk();
        zzecb.zzhnw = zzdxk.zzbaq();
        zzecb.zzhxq = zzecb.zzm(Long.TYPE);
        zzecb.zzhxr = zzecb.zzm(Integer.TYPE);
        zzd zzecb$zzd0 = null;
        if(zzecb.zzhbt != null) {
            zzecb$zzd0 = new zzb(zzecb.zzhbt);
        }
        zzecb.zzhxs = zzecb$zzd0;
        zzecb.zzhxt = zzecb.zzbfm();
        zzecb.zzhpd = zzecb.zzbfl();
        zzecb.zzhxu = (long)zzecb.zzk(byte[].class);
        zzecb.zzhxv = (long)zzecb.zzk(boolean[].class);
        zzecb.zzhxw = (long)zzecb.zzl(boolean[].class);
        zzecb.zzhxx = (long)zzecb.zzk(int[].class);
        zzecb.zzhxy = (long)zzecb.zzl(int[].class);
        zzecb.zzhxz = (long)zzecb.zzk(long[].class);
        zzecb.zzhya = (long)zzecb.zzl(long[].class);
        zzecb.zzhyb = (long)zzecb.zzk(float[].class);
        zzecb.zzhyc = (long)zzecb.zzl(float[].class);
        zzecb.zzhyd = (long)zzecb.zzk(double[].class);
        zzecb.zzhye = (long)zzecb.zzl(double[].class);
        zzecb.zzhyf = (long)zzecb.zzk(Object[].class);
        zzecb.zzhyg = (long)zzecb.zzl(Object[].class);
        Field field0 = zzecb.zzbfn();
        if(field0 == null) {
            v = -1L;
        }
        else {
            zzd zzecb$zzd1 = zzecb.zzhxs;
            v = zzecb$zzd1 == null ? -1L : zzecb$zzd1.zzhym.objectFieldOffset(field0);
        }
        zzecb.zzhyh = v;
        zzecb.zzhyi = (int)(zzecb.zzhxu & 7L);
        zzecb.zzhyj = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    static byte zza(byte[] arr_b, long v) {
        return zzecb.zzhxs.zzy(arr_b, zzecb.zzhxu + v);
    }

    static void zza(long v, byte b) {
        zzecb.zzhxs.zza(v, b);
    }

    private static void zza(Object object0, long v, byte b) {
        int v1 = (~((int)v) & 3) << 3;
        zzecb.zzb(object0, -4L & v, (0xFF & b) << v1 | zzecb.zzk(object0, -4L & v) & ~(0xFF << v1));
    }

    static void zza(Object object0, long v, double f) {
        zzecb.zzhxs.zza(object0, v, f);
    }

    static void zza(Object object0, long v, float f) {
        zzecb.zzhxs.zza(object0, v, f);
    }

    static void zza(Object object0, long v, long v1) {
        zzecb.zzhxs.zza(object0, v, v1);
    }

    static void zza(Object object0, long v, Object object1) {
        zzecb.zzhxs.zzhym.putObject(object0, v, object1);
    }

    static void zza(Object object0, long v, boolean z) {
        zzecb.zzhxs.zza(object0, v, z);
    }

    static void zza(byte[] arr_b, long v, byte b) {
        zzecb.zzhxs.zze(arr_b, zzecb.zzhxu + v, b);
    }

    static void zza(byte[] arr_b, long v, long v1, long v2) {
        zzecb.zzhxs.zza(arr_b, v, v1, v2);
    }

    private static Field zzb(Class class0, String s) {
        try {
            return class0.getDeclaredField(s);
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static void zzb(Object object0, long v, byte b) {
        int v1 = (((int)v) & 3) << 3;
        zzecb.zzb(object0, -4L & v, (0xFF & b) << v1 | zzecb.zzk(object0, -4L & v) & ~(0xFF << v1));
    }

    static void zzb(Object object0, long v, int v1) {
        zzecb.zzhxs.zzb(object0, v, v1);
    }

    private static void zzb(Object object0, long v, boolean z) {
        zzecb.zza(object0, v, ((byte)z));
    }

    static boolean zzbfi() [...] // 潜在的解密器

    static boolean zzbfj() [...] // 潜在的解密器

    static Unsafe zzbfk() {
        try {
            return (Unsafe)AccessController.doPrivileged(new zzecd());
        }
        catch(Throwable unused_ex) {
            return null;
        }
    }

    private static boolean zzbfl() {
        Unsafe unsafe0 = zzecb.zzhbt;
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
            zzecb.logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", "platform method missing - proto runtime falling back to safer methods: " + throwable0);
            return false;
        }
    }

    private static boolean zzbfm() {
        Unsafe unsafe0 = zzecb.zzhbt;
        if(unsafe0 == null) {
            return false;
        }
        try {
            Class class0 = unsafe0.getClass();
            class0.getMethod("objectFieldOffset", Field.class);
            class0.getMethod("getLong", Object.class, Long.TYPE);
            if(zzecb.zzbfn() == null) {
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
            zzecb.logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", "platform method missing - proto runtime falling back to safer methods: " + throwable0);
            return false;
        }
    }

    private static Field zzbfn() {
        Field field0 = zzecb.zzb(Buffer.class, "address");
        return field0 == null || field0.getType() != Long.TYPE ? null : field0;
    }

    private static void zzc(Object object0, long v, boolean z) {
        zzecb.zzb(object0, v, ((byte)z));
    }

    static Object zzj(Class class0) {
        try {
            return zzecb.zzhbt.allocateInstance(class0);
        }
        catch(InstantiationException instantiationException0) {
            throw new IllegalStateException(instantiationException0);
        }
    }

    // 去混淆评级： 低(20)
    private static int zzk(Class class0) {
        return zzecb.zzhpd ? zzecb.zzhxs.zzhym.arrayBaseOffset(class0) : -1;
    }

    static int zzk(Object object0, long v) {
        return zzecb.zzhxs.zzk(object0, v);
    }

    // 去混淆评级： 低(20)
    private static int zzl(Class class0) {
        return zzecb.zzhpd ? zzecb.zzhxs.zzhym.arrayIndexScale(class0) : -1;
    }

    static long zzl(Object object0, long v) {
        return zzecb.zzhxs.zzl(object0, v);
    }

    // 去混淆评级： 低(30)
    private static boolean zzm(Class class0) {
        return false;
    }

    static boolean zzm(Object object0, long v) {
        return zzecb.zzhxs.zzm(object0, v);
    }

    static float zzn(Object object0, long v) {
        return zzecb.zzhxs.zzn(object0, v);
    }

    static long zzn(ByteBuffer byteBuffer0) {
        return zzecb.zzhxs.zzl(byteBuffer0, zzecb.zzhyh);
    }

    static double zzo(Object object0, long v) {
        return zzecb.zzhxs.zzo(object0, v);
    }

    static Object zzp(Object object0, long v) {
        return zzecb.zzhxs.zzhym.getObject(object0, v);
    }

    private static byte zzq(Object object0, long v) {
        return (byte)(zzecb.zzk(object0, -4L & v) >>> ((int)((~v & 3L) << 3)));
    }

    private static byte zzr(Object object0, long v) {
        return (byte)(zzecb.zzk(object0, -4L & v) >>> ((int)((v & 3L) << 3)));
    }

    private static boolean zzs(Object object0, long v) {
        return zzecb.zzq(object0, v) != 0;
    }

    private static boolean zzt(Object object0, long v) {
        return zzecb.zzr(object0, v) != 0;
    }
}

