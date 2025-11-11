package com.google.android.gms.dynamite;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.concurrent.GuardedBy;
import jeb.synthetic.TWR;

@KeepForSdk
public final class DynamiteModule {
    @DynamiteApi
    public static class DynamiteLoaderClassLoader {
        @GuardedBy("DynamiteLoaderClassLoader.class")
        public static ClassLoader sClassLoader;

    }

    @KeepForSdk
    public static class LoadingException extends Exception {
        private LoadingException(String s) {
            super(s);
        }

        LoadingException(String s, zza zza0) {
            this(s);
        }

        private LoadingException(String s, Throwable throwable0) {
            super(s, throwable0);
        }

        LoadingException(String s, Throwable throwable0, zza zza0) {
            this(s, throwable0);
        }
    }

    public interface VersionPolicy {
        public interface com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza {
            int getLocalVersion(Context arg1, String arg2);

            int zza(Context arg1, String arg2, boolean arg3) throws LoadingException;
        }

        public static final class zzb {
            public int zzir;
            public int zzis;
            public int zzit;

            public zzb() {
                this.zzir = 0;
                this.zzis = 0;
                this.zzit = 0;
            }
        }

        zzb zza(Context arg1, String arg2, com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza arg3) throws LoadingException;
    }

    static final class com.google.android.gms.dynamite.DynamiteModule.zza {
        public Cursor zzio;

        private com.google.android.gms.dynamite.DynamiteModule.zza() {
        }

        com.google.android.gms.dynamite.DynamiteModule.zza(zza zza0) {
        }
    }

    static final class com.google.android.gms.dynamite.DynamiteModule.zzb implements com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza {
        private final int zzip;
        private final int zziq;

        public com.google.android.gms.dynamite.DynamiteModule.zzb(int v, int v1) {
            this.zzip = v;
            this.zziq = 0;
        }

        @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zza
        public final int getLocalVersion(Context context0, String s) {
            return this.zzip;
        }

        @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy$zza
        public final int zza(Context context0, String s, boolean z) {
            return 0;
        }
    }

    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = null;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = null;
    @KeepForSdk
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = null;
    @KeepForSdk
    public static final VersionPolicy PREFER_LOCAL = null;
    @KeepForSdk
    public static final VersionPolicy PREFER_REMOTE = null;
    @GuardedBy("DynamiteModule.class")
    private static Boolean zzif = null;
    @GuardedBy("DynamiteModule.class")
    private static zzi zzig = null;
    @GuardedBy("DynamiteModule.class")
    private static zzk zzih = null;
    @GuardedBy("DynamiteModule.class")
    private static String zzii = null;
    @GuardedBy("DynamiteModule.class")
    private static int zzij = -1;
    private static final ThreadLocal zzik;
    private static final com.google.android.gms.dynamite.DynamiteModule.VersionPolicy.zza zzil;
    private static final VersionPolicy zzim;
    private final Context zzin;

    static {
        DynamiteModule.zzik = new ThreadLocal();
        DynamiteModule.zzil = new zza();
        DynamiteModule.PREFER_REMOTE = new com.google.android.gms.dynamite.zzb();
        DynamiteModule.PREFER_LOCAL = new zzc();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION = new zzd();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zze();
        DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION = new zzf();
        DynamiteModule.zzim = new zzg();
    }

    private DynamiteModule(Context context0) {
        this.zzin = (Context)Preconditions.checkNotNull(context0);
    }

    @KeepForSdk
    public static int getLocalVersion(Context context0, String s) {
        try {
            Class class0 = context0.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + s + ".ModuleDescriptor");
            Field field0 = class0.getDeclaredField("MODULE_ID");
            Field field1 = class0.getDeclaredField("MODULE_VERSION");
            if(!field0.get(null).equals(s)) {
                Log.e("DynamiteModule", "Module descriptor id \'" + field0.get(null) + "\' didn\'t match expected id \'" + s + "\'");
                return 0;
            }
            return field1.getInt(null);
        }
        catch(ClassNotFoundException unused_ex) {
            Log.w("DynamiteModule", "Local module descriptor class for " + s + " not found.");
            return 0;
        }
        catch(Exception exception0) {
            String s1 = exception0.getMessage();
            Log.e("DynamiteModule", (s1.length() == 0 ? new String("Failed to load module descriptor class: ") : "Failed to load module descriptor class: " + s1));
            return 0;
        }
    }

    @KeepForSdk
    public final Context getModuleContext() {
        return this.zzin;
    }

    @KeepForSdk
    public static int getRemoteVersion(Context context0, String s) {
        return DynamiteModule.zza(context0, s, false);
    }

    @KeepForSdk
    public final IBinder instantiate(String s) throws LoadingException {
        try {
            return (IBinder)this.zzin.getClassLoader().loadClass(s).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException classNotFoundException0) {
            String s1 = String.valueOf(s);
            throw new LoadingException((s1.length() == 0 ? new String("Failed to instantiate module class: ") : "Failed to instantiate module class: " + s1), classNotFoundException0, null);
        }
    }

    @KeepForSdk
    public static DynamiteModule load(Context context0, VersionPolicy dynamiteModule$VersionPolicy0, String s) throws LoadingException {
        DynamiteModule dynamiteModule2;
        DynamiteModule dynamiteModule1;
        DynamiteModule dynamiteModule0;
        zzb dynamiteModule$VersionPolicy$zzb0;
        com.google.android.gms.dynamite.DynamiteModule.zza dynamiteModule$zza0 = (com.google.android.gms.dynamite.DynamiteModule.zza)DynamiteModule.zzik.get();
        com.google.android.gms.dynamite.DynamiteModule.zza dynamiteModule$zza1 = new com.google.android.gms.dynamite.DynamiteModule.zza(null);
        DynamiteModule.zzik.set(dynamiteModule$zza1);
        try {
            dynamiteModule$VersionPolicy$zzb0 = dynamiteModule$VersionPolicy0.zza(context0, s, DynamiteModule.zzil);
            Log.i("DynamiteModule", "Considering local module " + s + ":" + dynamiteModule$VersionPolicy$zzb0.zzir + " and remote module " + s + ":" + dynamiteModule$VersionPolicy$zzb0.zzis);
            if(dynamiteModule$VersionPolicy$zzb0.zzit == 0 || dynamiteModule$VersionPolicy$zzb0.zzit == -1 && dynamiteModule$VersionPolicy$zzb0.zzir == 0 || dynamiteModule$VersionPolicy$zzb0.zzit == 1 && dynamiteModule$VersionPolicy$zzb0.zzis == 0) {
                throw new LoadingException("No acceptable module found. Local version is " + dynamiteModule$VersionPolicy$zzb0.zzir + " and remote version is " + dynamiteModule$VersionPolicy$zzb0.zzis + ".", null);
            }
            switch(dynamiteModule$VersionPolicy$zzb0.zzit) {
                case -1: {
                    dynamiteModule0 = DynamiteModule.zze(context0, s);
                    if(dynamiteModule$zza1.zzio != null) {
                        break;
                    }
                    goto label_12;
                }
                case 1: {
                    dynamiteModule1 = DynamiteModule.zza(context0, s, dynamiteModule$VersionPolicy$zzb0.zzis);
                    goto label_31;
                }
                default: {
                    throw new LoadingException("VersionPolicy returned invalid code:" + dynamiteModule$VersionPolicy$zzb0.zzit, null);
                }
            }
        }
        catch(Throwable throwable0) {
            goto label_27;
        }
        dynamiteModule$zza1.zzio.close();
    label_12:
        DynamiteModule.zzik.set(dynamiteModule$zza0);
        return dynamiteModule0;
        try {
            try {
                dynamiteModule1 = DynamiteModule.zza(context0, s, dynamiteModule$VersionPolicy$zzb0.zzis);
                goto label_31;
            }
            catch(LoadingException dynamiteModule$LoadingException0) {
            }
            String s1 = dynamiteModule$LoadingException0.getMessage();
            Log.w("DynamiteModule", (s1.length() == 0 ? new String("Failed to load remote module: ") : "Failed to load remote module: " + s1));
            if(dynamiteModule$VersionPolicy$zzb0.zzir == 0 || dynamiteModule$VersionPolicy0.zza(context0, s, new com.google.android.gms.dynamite.DynamiteModule.zzb(dynamiteModule$VersionPolicy$zzb0.zzir, 0)).zzit != -1) {
                throw new LoadingException("Remote load failed. No local fallback found.", dynamiteModule$LoadingException0, null);
            }
            dynamiteModule2 = DynamiteModule.zze(context0, s);
            if(dynamiteModule$zza1.zzio != null) {
                goto label_23;
            }
            goto label_24;
        }
        catch(Throwable throwable0) {
            goto label_27;
        }
    label_23:
        dynamiteModule$zza1.zzio.close();
    label_24:
        DynamiteModule.zzik.set(dynamiteModule$zza0);
        return dynamiteModule2;
    label_27:
        if(dynamiteModule$zza1.zzio != null) {
            dynamiteModule$zza1.zzio.close();
        }
        DynamiteModule.zzik.set(dynamiteModule$zza0);
        throw throwable0;
    label_31:
        if(dynamiteModule$zza1.zzio != null) {
            dynamiteModule$zza1.zzio.close();
        }
        DynamiteModule.zzik.set(dynamiteModule$zza0);
        return dynamiteModule1;
    }

    public static int zza(Context context0, String s, boolean z) {
        int v1;
        try {
            synchronized(DynamiteModule.class) {
                Boolean boolean0 = DynamiteModule.zzif;
                if(boolean0 == null) {
                    try {
                        Class class1 = context0.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader");
                        Field field0 = class1.getDeclaredField("sClassLoader");
                        synchronized(class1) {
                            ClassLoader classLoader0 = (ClassLoader)field0.get(null);
                            if(classLoader0 == null) {
                                goto label_17;
                            }
                            if(classLoader0 == ClassLoader.getSystemClassLoader()) {
                                boolean0 = Boolean.FALSE;
                            }
                            else {
                                try {
                                    DynamiteModule.zza(classLoader0);
                                }
                                catch(LoadingException unused_ex) {
                                }
                                Boolean boolean1 = Boolean.TRUE;
                                boolean0 = boolean1;
                                goto label_41;
                                try {
                                label_17:
                                    v1 = DynamiteModule.zzc(context0, s, z);
                                    if(DynamiteModule.zzii != null && !DynamiteModule.zzii.isEmpty()) {
                                        zzh zzh0 = new zzh(DynamiteModule.zzii, ClassLoader.getSystemClassLoader());
                                        DynamiteModule.zza(zzh0);
                                        field0.set(null, zzh0);
                                        DynamiteModule.zzif = Boolean.TRUE;
                                        return v1;
                                    }
                                    return v1;
                                }
                                catch(LoadingException unused_ex) {
                                    field0.set(null, ClassLoader.getSystemClassLoader());
                                    boolean1 = Boolean.FALSE;
                                }
                                boolean0 = boolean1;
                            }
                            goto label_41;
                        }
                        return v1;
                    }
                    catch(ClassNotFoundException | IllegalAccessException | NoSuchFieldException classNotFoundException0) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + classNotFoundException0);
                        boolean0 = Boolean.FALSE;
                    }
                label_41:
                    DynamiteModule.zzif = boolean0;
                }
                if(!boolean0.booleanValue()) {
                    return DynamiteModule.zzb(context0, s, z);
                }
                try {
                    return DynamiteModule.zzc(context0, s, z);
                }
                catch(LoadingException dynamiteModule$LoadingException0) {
                    String s1 = dynamiteModule$LoadingException0.getMessage();
                    Log.w("DynamiteModule", (s1.length() == 0 ? new String("Failed to retrieve remote module version: ") : "Failed to retrieve remote module version: " + s1));
                    return 0;
                }
            }
        }
        catch(Throwable throwable0) {
            CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
            throw throwable0;
        }
    }

    private static DynamiteModule zza(Context context0, String s, int v) throws LoadingException {
        IObjectWrapper iObjectWrapper0;
        try {
            synchronized(DynamiteModule.class) {
                Boolean boolean0 = DynamiteModule.zzif;
            }
            if(boolean0 == null) {
                throw new LoadingException("Failed to determine which loading route to use.", null);
            }
            if(boolean0.booleanValue()) {
                return DynamiteModule.zzb(context0, s, v);
            }
            Log.i("DynamiteModule", "Selected remote version of " + s + ", version >= " + v);
            zzi zzi0 = DynamiteModule.zzj(context0);
            if(zzi0 == null) {
                throw new LoadingException("Failed to create IDynamiteLoader.", null);
            }
            if(zzi0.zzak() >= 2) {
                iObjectWrapper0 = zzi0.zzb(ObjectWrapper.wrap(context0), s, v);
            }
            else {
                Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                iObjectWrapper0 = zzi0.zza(ObjectWrapper.wrap(context0), s, v);
            }
            if(ObjectWrapper.unwrap(iObjectWrapper0) == null) {
                throw new LoadingException("Failed to load remote module.", null);
            }
            return new DynamiteModule(((Context)ObjectWrapper.unwrap(iObjectWrapper0)));
        }
        catch(RemoteException remoteException0) {
            throw new LoadingException("Failed to load remote module.", remoteException0, null);
        }
        catch(LoadingException dynamiteModule$LoadingException0) {
            throw dynamiteModule$LoadingException0;
        }
        catch(Throwable throwable0) {
            CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
            throw new LoadingException("Failed to load remote module.", throwable0, null);
        }
    }

    @GuardedBy("DynamiteModule.class")
    private static void zza(ClassLoader classLoader0) throws LoadingException {
        zzk zzk0;
        try {
            IBinder iBinder0 = (IBinder)classLoader0.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor().newInstance();
            if(iBinder0 == null) {
                zzk0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzk0 = iInterface0 instanceof zzk ? ((zzk)iInterface0) : new zzl(iBinder0);
            }
            DynamiteModule.zzih = zzk0;
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException classNotFoundException0) {
            throw new LoadingException("Failed to instantiate dynamite loader", classNotFoundException0, null);
        }
    }

    private static Boolean zzaj() {
        synchronized(DynamiteModule.class) {
        }
        return Boolean.valueOf(DynamiteModule.zzij >= 2);
    }

    private static int zzb(Context context0, String s, boolean z) {
        zzi zzi0 = DynamiteModule.zzj(context0);
        if(zzi0 == null) {
            return 0;
        }
        try {
            if(zzi0.zzak() >= 2) {
                return zzi0.zzb(ObjectWrapper.wrap(context0), s, z);
            }
            Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
            return zzi0.zza(ObjectWrapper.wrap(context0), s, z);
        }
        catch(RemoteException remoteException0) {
            String s1 = remoteException0.getMessage();
            Log.w("DynamiteModule", (s1.length() == 0 ? new String("Failed to retrieve remote module version: ") : "Failed to retrieve remote module version: " + s1));
            return 0;
        }
    }

    private static DynamiteModule zzb(Context context0, String s, int v) throws LoadingException, RemoteException {
        zzk zzk0;
        IObjectWrapper iObjectWrapper0;
        Log.i("DynamiteModule", "Selected remote version of " + s + ", version >= " + v);
        synchronized(DynamiteModule.class) {
            zzk0 = DynamiteModule.zzih;
        }
        if(zzk0 == null) {
            throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
        }
        com.google.android.gms.dynamite.DynamiteModule.zza dynamiteModule$zza0 = (com.google.android.gms.dynamite.DynamiteModule.zza)DynamiteModule.zzik.get();
        if(dynamiteModule$zza0 == null || dynamiteModule$zza0.zzio == null) {
            throw new LoadingException("No result cursor", null);
        }
        Context context1 = context0.getApplicationContext();
        Cursor cursor0 = dynamiteModule$zza0.zzio;
        ObjectWrapper.wrap(null);
        if(DynamiteModule.zzaj().booleanValue()) {
            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
            iObjectWrapper0 = zzk0.zzb(ObjectWrapper.wrap(context1), s, v, ObjectWrapper.wrap(cursor0));
        }
        else {
            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
            iObjectWrapper0 = zzk0.zza(ObjectWrapper.wrap(context1), s, v, ObjectWrapper.wrap(cursor0));
        }
        Context context2 = (Context)ObjectWrapper.unwrap(iObjectWrapper0);
        if(context2 == null) {
            throw new LoadingException("Failed to get module context", null);
        }
        return new DynamiteModule(context2);
    }

    private static int zzc(Context context0, String s, boolean z) throws LoadingException {
        Cursor cursor2;
        int v;
        Cursor cursor1;
        Cursor cursor0;
        try {
            cursor0 = context0.getContentResolver().query(Uri.parse(("content://com.google.android.gms.chimera/" + (z ? "api_force_staging" : "api") + "/" + s)), null, null, null, null);
        }
        catch(Exception exception0) {
            cursor1 = null;
            goto label_34;
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(null, throwable0);
            throw throwable0;
        }
        if(cursor0 != null) {
            try {
                if(cursor0.moveToFirst()) {
                    v = cursor0.getInt(0);
                    if(v > 0) {
                        Class class0 = DynamiteModule.class;
                        synchronized(class0) {
                            DynamiteModule.zzii = cursor0.getString(2);
                            int v2 = cursor0.getColumnIndex("loaderVersion");
                            if(v2 >= 0) {
                                DynamiteModule.zzij = cursor0.getInt(v2);
                            }
                        }
                        com.google.android.gms.dynamite.DynamiteModule.zza dynamiteModule$zza0 = (com.google.android.gms.dynamite.DynamiteModule.zza)DynamiteModule.zzik.get();
                        if(dynamiteModule$zza0 != null && dynamiteModule$zza0.zzio == null) {
                            dynamiteModule$zza0.zzio = cursor0;
                            cursor0 = null;
                        }
                    }
                    goto label_26;
                }
                goto label_29;
            }
            catch(Exception exception1) {
                goto label_32;
            }
            catch(Throwable throwable1) {
                goto label_41;
            }
        label_26:
            if(cursor0 != null) {
                cursor0.close();
            }
            return v;
        }
        try {
        label_29:
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", null);
        }
        catch(Exception exception1) {
        label_32:
            cursor1 = cursor0;
            exception0 = exception1;
            try {
            label_34:
                if(!(exception0 instanceof LoadingException)) {
                    throw new LoadingException("V2 version check failed", exception0, null);
                }
                throw exception0;
            }
            catch(Throwable throwable0) {
                cursor2 = cursor1;
            }
        }
        catch(Throwable throwable1) {
        label_41:
            cursor2 = cursor0;
            throwable0 = throwable1;
        }
        if(cursor2 != null) {
            cursor2.close();
        }
        throw throwable0;
    }

    private static DynamiteModule zze(Context context0, String s) {
        String s1 = String.valueOf(s);
        Log.i("DynamiteModule", (s1.length() == 0 ? new String("Selected local version of ") : "Selected local version of " + s1));
        return new DynamiteModule(context0.getApplicationContext());
    }

    private static zzi zzj(Context context0) {
        zzi zzi0;
        synchronized(DynamiteModule.class) {
            if(DynamiteModule.zzig != null) {
                return DynamiteModule.zzig;
            }
            if(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0) != 0) {
                return null;
            }
            try {
                IBinder iBinder0 = (IBinder)context0.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if(iBinder0 == null) {
                    zzi0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzi0 = iInterface0 instanceof zzi ? ((zzi)iInterface0) : new zzj(iBinder0);
                }
                if(zzi0 != null) {
                    DynamiteModule.zzig = zzi0;
                    return zzi0;
                }
            }
            catch(Exception exception0) {
                String s = exception0.getMessage();
                Log.e("DynamiteModule", (s.length() == 0 ? new String("Failed to load IDynamiteLoader from GmsCore: ") : "Failed to load IDynamiteLoader from GmsCore: " + s));
            }
            return null;
        }
    }
}

