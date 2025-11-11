package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@ParametersAreNonnullByDefault
public final class zzzt implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final Object lock;
    private Bundle metaData;
    private final ConditionVariable zzcgt;
    @VisibleForTesting
    private volatile boolean zzcgu;
    @Nullable
    private SharedPreferences zzcgv;
    private Context zzcgw;
    private JSONObject zzcgx;
    private volatile boolean zzyb;

    public zzzt() {
        this.lock = new Object();
        this.zzcgt = new ConditionVariable();
        this.zzyb = false;
        this.zzcgu = false;
        this.zzcgv = null;
        this.metaData = new Bundle();
        this.zzcgx = new JSONObject();
    }

    public final void initialize(Context context0) {
        if(this.zzyb) {
            return;
        }
        synchronized(this.lock) {
            if(this.zzyb) {
                return;
            }
            if(!this.zzcgu) {
                this.zzcgu = true;
            }
            Context context1 = context0.getApplicationContext() == null ? context0 : context0.getApplicationContext();
            try {
                this.zzcgw = context1;
                this.metaData = Wrappers.packageManager(this.zzcgw).getApplicationInfo("com.esotericsoftware.gloomhavenhelper", 0x80).metaData;
            }
            catch(PackageManager.NameNotFoundException | NullPointerException unused_ex) {
            }
            try {
                Context context2 = GooglePlayServicesUtilLight.getRemoteContext(context0);
                if(context2 == null) {
                    context2 = context0.getApplicationContext();
                    if(context2 == null) {
                        context2 = context0;
                    }
                }
                if(context2 == null) {
                    return;
                }
                this.zzcgv = context2.getSharedPreferences("google_ads_flags", 0);
                if(this.zzcgv != null) {
                    this.zzcgv.registerOnSharedPreferenceChangeListener(this);
                }
                zzabw.zza(new zzzu(this));
                this.zzql();
                this.zzyb = true;
            }
            finally {
                this.zzcgu = false;
                this.zzcgt.open();
            }
        }
    }

    @Override  // android.content.SharedPreferences$OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences0, String s) {
        if("flag_configuration".equals(s)) {
            this.zzql();
        }
    }

    public final Object zzd(zzzi zzzi0) {
        if(!this.zzcgt.block(5000L)) {
            Object object0 = this.lock;
            synchronized(object0) {
                if(this.zzcgu) {
                    goto label_11;
                }
            }
            throw new IllegalStateException("Flags.initialize() was not called!");
        }
    label_11:
        if(!this.zzyb || this.zzcgv == null) {
            synchronized(this.lock) {
                if(this.zzyb && this.zzcgv != null) {
                    goto label_19;
                }
            }
            return zzzi0.zzqi();
        }
    label_19:
        switch(zzzi0.getSource()) {
            case 1: {
                return this.zzcgx.has(zzzi0.getKey()) ? zzzi0.zzb(this.zzcgx) : zzayp.zza(new zzzs(this, zzzi0));
            }
            case 2: {
                return this.metaData == null ? zzzi0.zzqi() : zzzi0.zza(this.metaData);
            }
            default: {
                return zzayp.zza(new zzzs(this, zzzi0));
            }
        }
    }

    final Object zze(zzzi zzzi0) {
        return zzzi0.zza(this.zzcgv);
    }

    private final void zzql() {
        if(this.zzcgv == null) {
            return;
        }
        try {
            this.zzcgx = new JSONObject(((String)zzayp.zza(new zzzv(this))));
        }
        catch(JSONException unused_ex) {
        }
    }

    final String zzqm() {
        return this.zzcgv.getString("flag_configuration", "{}");
    }
}

