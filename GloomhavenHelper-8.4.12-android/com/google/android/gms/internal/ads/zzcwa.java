package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.os.Debug;
import com.google.android.gms.ads.internal.zzq;
import java.util.concurrent.Callable;

final class zzcwa implements Callable {
    static final Callable zzgjg;

    static {
        zzcwa.zzgjg = new zzcwa();
    }

    @Override
    public final Object call() {
        Bundle bundle0 = new Bundle();
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzcib)).booleanValue()) {
            Debug.MemoryInfo debug$MemoryInfo0 = new Debug.MemoryInfo();
            Debug.getMemoryInfo(debug$MemoryInfo0);
            bundle0.putString("debug_info_dalvik_private_dirty", Integer.toString(debug$MemoryInfo0.dalvikPrivateDirty));
            bundle0.putString("debug_info_dalvik_pss", Integer.toString(debug$MemoryInfo0.dalvikPss));
            bundle0.putString("debug_info_dalvik_shared_dirty", Integer.toString(debug$MemoryInfo0.dalvikSharedDirty));
            bundle0.putString("debug_info_native_private_dirty", Integer.toString(debug$MemoryInfo0.nativePrivateDirty));
            bundle0.putString("debug_info_native_pss", Integer.toString(debug$MemoryInfo0.nativePss));
            bundle0.putString("debug_info_native_shared_dirty", Integer.toString(debug$MemoryInfo0.nativeSharedDirty));
            bundle0.putString("debug_info_other_private_dirty", Integer.toString(debug$MemoryInfo0.otherPrivateDirty));
            bundle0.putString("debug_info_other_pss", Integer.toString(debug$MemoryInfo0.otherPss));
            bundle0.putString("debug_info_other_shared_dirty", Integer.toString(debug$MemoryInfo0.otherSharedDirty));
        }
        Runtime runtime0 = Runtime.getRuntime();
        bundle0.putLong("runtime_free", runtime0.freeMemory());
        bundle0.putLong("runtime_max", runtime0.maxMemory());
        bundle0.putLong("runtime_total", runtime0.totalMemory());
        bundle0.putInt("web_view_count", zzq.zzkz().zzvj());
        return new zzcvy(bundle0);
    }
}

