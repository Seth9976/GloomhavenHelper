package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zza extends Fragment implements LifecycleFragment {
    private static WeakHashMap zzbe;
    private Map zzbf;
    private int zzbg;
    private Bundle zzbh;

    static {
        zza.zzbe = new WeakHashMap();
    }

    public zza() {
        this.zzbf = new ArrayMap();
        this.zzbg = 0;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String s, @NonNull LifecycleCallback lifecycleCallback0) {
        if(this.zzbf.containsKey(s)) {
            throw new IllegalArgumentException("LifecycleCallback with tag " + s + " already added to this fragment.");
        }
        this.zzbf.put(s, lifecycleCallback0);
        if(this.zzbg > 0) {
            new zze(Looper.getMainLooper()).post(new zzb(this, lifecycleCallback0, s));
        }
    }

    @Override  // android.app.Fragment
    public final void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        for(Object object0: this.zzbf.values()) {
            ((LifecycleCallback)object0).dump(s, fileDescriptor0, printWriter0, arr_s);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final LifecycleCallback getCallbackOrNull(String s, Class class0) {
        return (LifecycleCallback)class0.cast(this.zzbf.get(s));
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final Activity getLifecycleActivity() {
        return this.getActivity();
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzbg > 0;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzbg >= 2;
    }

    @Override  // android.app.Fragment
    public final void onActivityResult(int v, int v1, Intent intent0) {
        super.onActivityResult(v, v1, intent0);
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // android.app.Fragment
    public final void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.zzbg = 1;
        this.zzbh = bundle0;
        for(Object object0: this.zzbf.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            LifecycleCallback lifecycleCallback0 = (LifecycleCallback)map$Entry0.getValue();
            if(bundle0 != null) {
                bundle0.getBundle(((String)map$Entry0.getKey()));
            }
        }
    }

    @Override  // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        if(bundle0 == null) {
            return;
        }
        for(Object object0: this.zzbf.entrySet()) {
            Bundle bundle1 = new Bundle();
            ((LifecycleCallback)((Map.Entry)object0).getValue()).onSaveInstanceState(bundle1);
            bundle0.putBundle(((String)((Map.Entry)object0).getKey()), bundle1);
        }
    }

    @Override  // android.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // android.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        for(Object object0: this.zzbf.values()) {
            ((LifecycleCallback)object0).onStop();
        }
    }

    public static zza zza(Activity activity0) {
        zza zza1;
        WeakReference weakReference0 = (WeakReference)zza.zzbe.get(activity0);
        if(weakReference0 != null) {
            zza zza0 = (zza)weakReference0.get();
            if(zza0 != null) {
                return zza0;
            }
        }
        try {
            zza1 = (zza)activity0.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
        }
        catch(ClassCastException classCastException0) {
            throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", classCastException0);
        }
        if(zza1 == null || zza1.isRemoving()) {
            zza1 = new zza();
            activity0.getFragmentManager().beginTransaction().add(zza1, "LifecycleFragmentImpl").commitAllowingStateLoss();
        }
        zza.zzbe.put(activity0, new WeakReference(zza1));
        return zza1;
    }
}

