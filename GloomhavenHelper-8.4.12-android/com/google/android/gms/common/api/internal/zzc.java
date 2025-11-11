package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzc extends Fragment implements LifecycleFragment {
    private static WeakHashMap zzbe;
    private Map zzbf;
    private int zzbg;
    private Bundle zzbh;

    static {
        zzc.zzbe = new WeakHashMap();
    }

    public zzc() {
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
            new zze(Looper.getMainLooper()).post(new zzd(this, lifecycleCallback0, s));
        }
    }

    @Override  // androidx.fragment.app.Fragment
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

    @Override  // androidx.fragment.app.Fragment
    public final void onActivityResult(int v, int v1, Intent intent0) {
        super.onActivityResult(v, v1, intent0);
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // androidx.fragment.app.Fragment
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

    @Override  // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzbg = 5;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzbg = 3;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // androidx.fragment.app.Fragment
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

    @Override  // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzbg = 2;
        Iterator iterator0 = this.zzbf.values().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzbg = 4;
        for(Object object0: this.zzbf.values()) {
            ((LifecycleCallback)object0).onStop();
        }
    }

    public static zzc zza(FragmentActivity fragmentActivity0) {
        zzc zzc1;
        WeakReference weakReference0 = (WeakReference)zzc.zzbe.get(fragmentActivity0);
        if(weakReference0 != null) {
            zzc zzc0 = (zzc)weakReference0.get();
            if(zzc0 != null) {
                return zzc0;
            }
        }
        try {
            zzc1 = (zzc)fragmentActivity0.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
        }
        catch(ClassCastException classCastException0) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", classCastException0);
        }
        if(zzc1 == null || zzc1.isRemoving()) {
            zzc1 = new zzc();
            fragmentActivity0.getSupportFragmentManager().beginTransaction().add(zzc1, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
        }
        zzc.zzbe.put(fragmentActivity0, new WeakReference(zzc1));
        return zzc1;
    }
}

