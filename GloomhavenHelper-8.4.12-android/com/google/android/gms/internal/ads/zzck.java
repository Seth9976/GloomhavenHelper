package com.google.android.gms.internal.ads;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class zzck {
    private static boolean zzms = false;
    private static MessageDigest zzmt;
    private static final Object zzmu;
    private static final Object zzmv;
    static CountDownLatch zzmw;

    static {
        zzck.zzmu = new Object();
        zzck.zzmv = new Object();
        zzck.zzmw = new CountDownLatch(1);
    }

    private static zza zza(zzd zzbs$zza$zzd0) {
        zzb zzbs$zza$zzb0 = zza.zzao();
        zzbs$zza$zzb0.zzau(((long)zzbs$zza$zzd0.zzaf()));
        return (zza)(((zzdyz)zzbs$zza$zzb0.zzbcx()));
    }

    private static Vector zza(byte[] arr_b, int v) {
        if(arr_b != null && arr_b.length > 0) {
            int v1 = (arr_b.length + 0xFE) / 0xFF;
            Vector vector0 = new Vector();
            for(int v2 = 0; v2 < v1; ++v2) {
                try {
                    vector0.add(Arrays.copyOfRange(arr_b, v2 * 0xFF, (arr_b.length - v2 * 0xFF <= 0xFF ? arr_b.length : v2 * 0xFF + 0xFF)));
                }
                catch(IndexOutOfBoundsException unused_ex) {
                    return null;
                }
            }
            return vector0;
        }
        return null;
    }

    private static byte[] zza(byte[] arr_b, String s, boolean z) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] arr_b2;
        if(arr_b.length > (z ? 0xEF : 0xFF)) {
            arr_b = zzck.zza(zzd.zzje).toByteArray();
        }
        if(arr_b.length < (z ? 0xEF : 0xFF)) {
            byte[] arr_b1 = new byte[(z ? 0xEF : 0xFF) - arr_b.length];
            new SecureRandom().nextBytes(arr_b1);
            arr_b2 = ByteBuffer.allocate((z ? 0xEF : 0xFF) + 1).put(((byte)arr_b.length)).put(arr_b).put(arr_b1).array();
        }
        else {
            arr_b2 = ByteBuffer.allocate((z ? 0xEF : 0xFF) + 1).put(((byte)arr_b.length)).put(arr_b).array();
        }
        if(z) {
            byte[] arr_b3 = zzck.zzb(arr_b2);
            arr_b2 = ByteBuffer.allocate(0x100).put(arr_b3).put(arr_b2).array();
        }
        byte[] arr_b4 = new byte[0x100];
        zzcr[] arr_zzcr = new zzcp().zzup;
        for(int v = 0; v < arr_zzcr.length; ++v) {
            arr_zzcr[v].zza(arr_b2, arr_b4);
        }
        if(s != null && s.length() > 0) {
            if(s.length() > 0x20) {
                s = s.substring(0, 0x20);
            }
            new zzdxe(s.getBytes("UTF-8")).zzs(arr_b4);
        }
        return arr_b4;
    }

    public static byte[] zzb(byte[] arr_b) throws NoSuchAlgorithmException {
        synchronized(zzck.zzmu) {
            MessageDigest messageDigest0 = zzck.zzbn();
            if(messageDigest0 != null) {
                messageDigest0.reset();
                messageDigest0.update(arr_b);
                return zzck.zzmt.digest();
            }
        }
        throw new NoSuchAlgorithmException("Cannot compute hash");
    }

    static void zzbm() {
        synchronized(zzck.zzmv) {
            if(!zzck.zzms) {
                zzck.zzms = true;
                new Thread(new zzcm(null)).start();
            }
        }
    }

    private static MessageDigest zzbn() {
        zzck.zzbm();
        try {
            boolean z = false;
            z = zzck.zzmw.await(2L, TimeUnit.SECONDS);
        }
        catch(InterruptedException unused_ex) {
        }
        if(!z) {
            return null;
        }
        return zzck.zzmt == null ? null : zzck.zzmt;
    }

    static String zzj(zza zzbs$zza0, String s) throws GeneralSecurityException, UnsupportedEncodingException {
        byte[] arr_b = zzbs$zza0.toByteArray();
        if(!((Boolean)zzvh.zzpd().zzd(zzzx.zzcmd)).booleanValue()) {
            if(zzeo.zzyl == null) {
                throw new GeneralSecurityException();
            }
            byte[] arr_b1 = zzeo.zzyl.zzc(arr_b, (s == null ? new byte[0] : s.getBytes()));
            return zzci.zza(((zzf)(((zzdyz)zzf.zzbj().zzi(zzdxn.zzt(arr_b1)).zza(zzbz.zzkx).zzbcx()))).toByteArray(), true);
        }
        Vector vector0 = zzck.zza(arr_b, 0xFF);
        if(vector0 != null && vector0.size() != 0) {
            com.google.android.gms.internal.ads.zzbs.zzf.zza zzbs$zzf$zza0 = zzf.zzbj();
            for(Object object0: vector0) {
                zzbs$zzf$zza0.zzi(zzdxn.zzt(zzck.zza(((byte[])object0), s, false)));
            }
            zzbs$zzf$zza0.zzj(zzdxn.zzt(zzck.zzb(arr_b)));
            return zzci.zza(((zzf)(((zzdyz)zzbs$zzf$zza0.zzbcx()))).toByteArray(), true);
        }
        return zzci.zza(zzck.zza(zzck.zza(zzd.zzje).toByteArray(), s, true), true);
    }
}

