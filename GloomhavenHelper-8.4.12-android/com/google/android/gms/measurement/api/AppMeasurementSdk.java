package com.google.android.gms.measurement.api;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.Size;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.internal.measurement.zzx;
import com.google.android.gms.measurement.internal.zzgp;
import com.google.android.gms.measurement.internal.zzgq;
import java.util.List;
import java.util.Map;

@KeepForSdk
public class AppMeasurementSdk {
    @KeepForSdk
    public static final class ConditionalUserProperty {
        @KeepForSdk
        public static final String ACTIVE = "active";
        @KeepForSdk
        public static final String CREATION_TIMESTAMP = "creation_timestamp";
        @KeepForSdk
        public static final String EXPIRED_EVENT_NAME = "expired_event_name";
        @KeepForSdk
        public static final String EXPIRED_EVENT_PARAMS = "expired_event_params";
        @KeepForSdk
        public static final String NAME = "name";
        @KeepForSdk
        public static final String ORIGIN = "origin";
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_NAME = "timed_out_event_name";
        @KeepForSdk
        public static final String TIMED_OUT_EVENT_PARAMS = "timed_out_event_params";
        @KeepForSdk
        public static final String TIME_TO_LIVE = "time_to_live";
        @KeepForSdk
        public static final String TRIGGERED_EVENT_NAME = "triggered_event_name";
        @KeepForSdk
        public static final String TRIGGERED_EVENT_PARAMS = "triggered_event_params";
        @KeepForSdk
        public static final String TRIGGERED_TIMESTAMP = "triggered_timestamp";
        @KeepForSdk
        public static final String TRIGGER_EVENT_NAME = "trigger_event_name";
        @KeepForSdk
        public static final String TRIGGER_TIMEOUT = "trigger_timeout";
        @KeepForSdk
        public static final String VALUE = "value";

    }

    @KeepForSdk
    @ShowFirstParty
    public interface EventInterceptor extends zzgq {
        @Override  // com.google.android.gms.measurement.internal.zzgq
        @WorkerThread
        @KeepForSdk
        @ShowFirstParty
        void interceptEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    @KeepForSdk
    @ShowFirstParty
    public interface OnEventListener extends zzgp {
        @Override  // com.google.android.gms.measurement.internal.zzgp
        @WorkerThread
        @KeepForSdk
        @ShowFirstParty
        void onEvent(String arg1, String arg2, Bundle arg3, long arg4);
    }

    private final zzx zza;

    public AppMeasurementSdk(zzx zzx0) {
        this.zza = zzx0;
    }

    @KeepForSdk
    public void beginAdUnitExposure(@NonNull @Size(min = 1L) String s) {
        this.zza.zzb(s);
    }

    @KeepForSdk
    public void clearConditionalUserProperty(@NonNull @Size(max = 24L, min = 1L) String s, @Nullable String s1, @Nullable Bundle bundle0) {
        this.zza.zzb(s, s1, bundle0);
    }

    @KeepForSdk
    public void endAdUnitExposure(@NonNull @Size(min = 1L) String s) {
        this.zza.zzc(s);
    }

    @KeepForSdk
    public long generateEventId() {
        return this.zza.zze();
    }

    @KeepForSdk
    public String getAppIdOrigin() {
        return this.zza.zzi();
    }

    @Nullable
    @KeepForSdk
    public String getAppInstanceId() {
        return this.zza.zzd();
    }

    @WorkerThread
    @KeepForSdk
    public List getConditionalUserProperties(@Nullable String s, @Nullable @Size(max = 23L, min = 1L) String s1) {
        return this.zza.zzb(s, s1);
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenClass() {
        return this.zza.zzg();
    }

    @Nullable
    @KeepForSdk
    public String getCurrentScreenName() {
        return this.zza.zzf();
    }

    @Nullable
    @KeepForSdk
    public String getGmpAppId() {
        return this.zza.zzc();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context0) {
        return zzx.zza(context0).zza();
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AppMeasurementSdk getInstance(@NonNull Context context0, @NonNull String s, @NonNull String s1, @NonNull String s2, Bundle bundle0) {
        return zzx.zza(context0, s, s1, s2, bundle0).zza();
    }

    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@NonNull @Size(min = 1L) String s) {
        return this.zza.zzd(s);
    }

    @WorkerThread
    @KeepForSdk
    public Map getUserProperties(@Nullable String s, @Nullable @Size(max = 24L, min = 1L) String s1, boolean z) {
        return this.zza.zza(s, s1, z);
    }

    @KeepForSdk
    public void logEvent(String s, String s1, Bundle bundle0) {
        this.zza.zza(s, s1, bundle0);
    }

    @KeepForSdk
    public void logEventNoInterceptor(String s, String s1, Bundle bundle0, long v) {
        this.zza.zza(s, s1, bundle0, v);
    }

    @KeepForSdk
    public void performAction(Bundle bundle0) {
        this.zza.zza(bundle0, false);
    }

    @KeepForSdk
    public Bundle performActionWithResponse(Bundle bundle0) {
        return this.zza.zza(bundle0, true);
    }

    @KeepForSdk
    @ShowFirstParty
    public void registerOnMeasurementEventListener(OnEventListener appMeasurementSdk$OnEventListener0) {
        this.zza.zza(appMeasurementSdk$OnEventListener0);
    }

    @KeepForSdk
    public void setConditionalUserProperty(@NonNull Bundle bundle0) {
        this.zza.zza(bundle0);
    }

    @KeepForSdk
    public void setCurrentScreen(@NonNull Activity activity0, @Nullable @Size(max = 36L, min = 1L) String s, @Nullable @Size(max = 36L, min = 1L) String s1) {
        this.zza.zza(activity0, s, s1);
    }

    @WorkerThread
    @KeepForSdk
    @ShowFirstParty
    public void setEventInterceptor(EventInterceptor appMeasurementSdk$EventInterceptor0) {
        this.zza.zza(appMeasurementSdk$EventInterceptor0);
    }

    @KeepForSdk
    public void setMeasurementEnabled(boolean z) {
        this.zza.zza(z);
    }

    @KeepForSdk
    public void setUserProperty(String s, String s1, Object object0) {
        this.zza.zza(s, s1, object0);
    }

    @KeepForSdk
    @ShowFirstParty
    public void unregisterOnMeasurementEventListener(OnEventListener appMeasurementSdk$OnEventListener0) {
        this.zza.zzb(appMeasurementSdk$OnEventListener0);
    }
}

