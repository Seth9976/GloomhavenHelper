package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.common.zze;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public abstract class BaseGmsClient {
    @KeepForSdk
    public interface BaseConnectionCallbacks {
        @KeepForSdk
        void onConnected(@Nullable Bundle arg1);

        @KeepForSdk
        void onConnectionSuspended(int arg1);
    }

    @KeepForSdk
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(@NonNull ConnectionResult arg1);
    }

    @KeepForSdk
    public interface ConnectionProgressReportCallbacks {
        @KeepForSdk
        void onReportServiceBinding(@NonNull ConnectionResult arg1);
    }

    public class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        private final BaseGmsClient zzct;

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks
        public void onReportServiceBinding(@NonNull ConnectionResult connectionResult0) {
            if(connectionResult0.isSuccess()) {
                BaseGmsClient.this.getRemoteService(null, BaseGmsClient.this.getScopes());
                return;
            }
            if(BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult0);
            }
        }
    }

    @KeepForSdk
    public interface SignOutCallbacks {
        @KeepForSdk
        void onSignOutComplete();
    }

    abstract class zza extends zzc {
        private final int statusCode;
        private final Bundle zzcs;
        private final BaseGmsClient zzct;

        @BinderThread
        protected zza(int v, Bundle bundle0) {
            super(Boolean.TRUE);
            this.statusCode = v;
            this.zzcs = bundle0;
        }

        protected abstract void zza(ConnectionResult arg1);

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zzc
        protected final void zza(Object object0) {
            PendingIntent pendingIntent0 = null;
            if(((Boolean)object0) == null) {
                BaseGmsClient.zza(BaseGmsClient.this, 1, null);
                return;
            }
            switch(this.statusCode) {
                case 0: {
                    if(!this.zzm()) {
                        BaseGmsClient.zza(BaseGmsClient.this, 1, null);
                        this.zza(new ConnectionResult(8, null));
                        return;
                    }
                    return;
                }
                case 10: {
                    BaseGmsClient.zza(BaseGmsClient.this, 1, null);
                    throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", this.getClass().getSimpleName(), BaseGmsClient.this.getStartServiceAction(), BaseGmsClient.this.getServiceDescriptor()));
                }
                default: {
                    BaseGmsClient.zza(BaseGmsClient.this, 1, null);
                    Bundle bundle0 = this.zzcs;
                    if(bundle0 != null) {
                        pendingIntent0 = (PendingIntent)bundle0.getParcelable("pendingIntent");
                    }
                    this.zza(new ConnectionResult(this.statusCode, pendingIntent0));
                }
            }
        }

        protected abstract boolean zzm();

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zzc
        protected final void zzn() {
        }
    }

    final class zzb extends zze {
        private final BaseGmsClient zzct;

        public zzb(Looper looper0) {
            super(looper0);
        }

        @Override  // android.os.Handler
        public final void handleMessage(Message message0) {
            PendingIntent pendingIntent0 = null;
            if(BaseGmsClient.this.zzcr.get() != message0.arg1) {
                if(zzb.zzb(message0)) {
                    zzb.zza(message0);
                }
                return;
            }
            if((message0.what == 1 || message0.what == 4 || message0.what == 7 || message0.what == 5) && !BaseGmsClient.this.isConnecting()) {
                zzb.zza(message0);
                return;
            }
            if(message0.what == 4) {
                ConnectionResult connectionResult0 = new ConnectionResult(message0.arg2);
                BaseGmsClient.this.zzco = connectionResult0;
                if(BaseGmsClient.this.zzl() && !BaseGmsClient.this.zzcp) {
                    BaseGmsClient.zza(BaseGmsClient.this, 3, null);
                    return;
                }
                ConnectionResult connectionResult1 = BaseGmsClient.this.zzco == null ? new ConnectionResult(8) : BaseGmsClient.this.zzco;
                BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult1);
                BaseGmsClient.this.onConnectionFailed(connectionResult1);
                return;
            }
            switch(message0.what) {
                case 2: {
                    if(!BaseGmsClient.this.isConnected()) {
                        zzb.zza(message0);
                        return;
                    }
                    break;
                }
                case 3: {
                    if(message0.obj instanceof PendingIntent) {
                        pendingIntent0 = (PendingIntent)message0.obj;
                    }
                    ConnectionResult connectionResult2 = new ConnectionResult(message0.arg2, pendingIntent0);
                    BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult2);
                    BaseGmsClient.this.onConnectionFailed(connectionResult2);
                    return;
                }
                case 5: {
                    ConnectionResult connectionResult3 = BaseGmsClient.this.zzco == null ? new ConnectionResult(8) : BaseGmsClient.this.zzco;
                    BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult3);
                    BaseGmsClient.this.onConnectionFailed(connectionResult3);
                    return;
                }
                case 6: {
                    BaseGmsClient.zza(BaseGmsClient.this, 5, null);
                    if(BaseGmsClient.this.zzck != null) {
                        BaseGmsClient.this.zzck.onConnectionSuspended(message0.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message0.arg2);
                    BaseGmsClient.this.zza(5, 1, null);
                    return;
                }
            }
            if(zzb.zzb(message0)) {
                ((zzc)message0.obj).zzo();
                return;
            }
            Log.wtf("GmsClient", "Don\'t know how to handle message: " + message0.what, new Exception());
        }

        private static void zza(Message message0) {
            zzc baseGmsClient$zzc0 = (zzc)message0.obj;
            baseGmsClient$zzc0.zzn();
            baseGmsClient$zzc0.unregister();
        }

        private static boolean zzb(Message message0) {
            return message0.what == 1 || message0.what == 2 || message0.what == 7;
        }
    }

    public abstract class zzc {
        private final BaseGmsClient zzct;
        private Object zzcu;
        private boolean zzcv;

        public zzc(Object object0) {
            this.zzcu = object0;
            this.zzcv = false;
        }

        public final void removeListener() {
            synchronized(this) {
                this.zzcu = null;
            }
        }

        public final void unregister() {
            this.removeListener();
            synchronized(BaseGmsClient.this.zzch) {
                BaseGmsClient.this.zzch.remove(this);
            }
        }

        protected abstract void zza(Object arg1);

        protected abstract void zzn();

        public final void zzo() {
            Object object0;
            synchronized(this) {
                object0 = this.zzcu;
                if(this.zzcv) {
                    Log.w("GmsClient", "Callback proxy " + this + " being reused. This is not safe.");
                }
            }
            if(object0 == null) {
                this.zzn();
            }
            else {
                try {
                    this.zza(object0);
                }
                catch(RuntimeException runtimeException0) {
                    this.zzn();
                    throw runtimeException0;
                }
            }
            synchronized(this) {
                this.zzcv = true;
            }
            this.unregister();
        }
    }

    @VisibleForTesting
    public static final class zzd extends com.google.android.gms.common.internal.IGmsCallbacks.zza {
        private BaseGmsClient zzcw;
        private final int zzcx;

        public zzd(@NonNull BaseGmsClient baseGmsClient0, int v) {
            this.zzcw = baseGmsClient0;
            this.zzcx = v;
        }

        @Override  // com.google.android.gms.common.internal.IGmsCallbacks
        @BinderThread
        public final void onPostInitComplete(int v, @NonNull IBinder iBinder0, @Nullable Bundle bundle0) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzcw.onPostInitHandler(v, iBinder0, bundle0, this.zzcx);
            this.zzcw = null;
        }

        @Override  // com.google.android.gms.common.internal.IGmsCallbacks
        @BinderThread
        public final void zza(int v, @Nullable Bundle bundle0) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @Override  // com.google.android.gms.common.internal.IGmsCallbacks
        @BinderThread
        public final void zza(int v, @NonNull IBinder iBinder0, @NonNull com.google.android.gms.common.internal.zzb zzb0) {
            Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzb0);
            this.zzcw.zza(zzb0);
            this.onPostInitComplete(v, iBinder0, zzb0.zzda);
        }
    }

    @VisibleForTesting
    public final class com.google.android.gms.common.internal.BaseGmsClient.zze implements ServiceConnection {
        private final BaseGmsClient zzct;
        private final int zzcx;

        public com.google.android.gms.common.internal.BaseGmsClient.zze(int v) {
            this.zzcx = v;
        }

        @Override  // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
            IGmsServiceBroker iGmsServiceBroker0;
            if(iBinder0 == null) {
                BaseGmsClient.zza(BaseGmsClient.this, 16);
                return;
            }
            synchronized(BaseGmsClient.this.zzcd) {
                BaseGmsClient baseGmsClient0 = BaseGmsClient.this;
                if(iBinder0 == null) {
                    iGmsServiceBroker0 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    iGmsServiceBroker0 = iInterface0 == null || !(iInterface0 instanceof IGmsServiceBroker) ? new com.google.android.gms.common.internal.IGmsServiceBroker.Stub.zza(iBinder0) : ((IGmsServiceBroker)iInterface0);
                }
                baseGmsClient0.zzce = iGmsServiceBroker0;
            }
            BaseGmsClient.this.zza(0, null, this.zzcx);
        }

        @Override  // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName0) {
            synchronized(BaseGmsClient.this.zzcd) {
                BaseGmsClient.this.zzce = null;
            }
            Message message0 = BaseGmsClient.this.mHandler.obtainMessage(6, this.zzcx, 1);
            BaseGmsClient.this.mHandler.sendMessage(message0);
        }
    }

    public final class zzf extends zza {
        private final BaseGmsClient zzct;
        private final IBinder zzcy;

        @BinderThread
        public zzf(int v, IBinder iBinder0, Bundle bundle0) {
            super(v, bundle0);
            this.zzcy = iBinder0;
        }

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zza
        protected final void zza(ConnectionResult connectionResult0) {
            if(BaseGmsClient.this.zzcl != null) {
                BaseGmsClient.this.zzcl.onConnectionFailed(connectionResult0);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult0);
        }

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zza
        protected final boolean zzm() {
            String s;
            try {
                s = this.zzcy.getInterfaceDescriptor();
            }
            catch(RemoteException unused_ex) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
            if(!BaseGmsClient.this.getServiceDescriptor().equals(s)) {
                Log.e("GmsClient", "service descriptor mismatch: " + BaseGmsClient.this.getServiceDescriptor() + " vs. " + s);
                return false;
            }
            IInterface iInterface0 = BaseGmsClient.this.createServiceInterface(this.zzcy);
            if(iInterface0 != null && (BaseGmsClient.this.zza(2, 4, iInterface0) || BaseGmsClient.this.zza(3, 4, iInterface0))) {
                BaseGmsClient.this.zzco = null;
                if(BaseGmsClient.this.zzck != null) {
                    BaseGmsClient.this.zzck.onConnected(null);
                }
                return true;
            }
            return false;
        }
    }

    public final class zzg extends zza {
        private final BaseGmsClient zzct;

        @BinderThread
        public zzg(int v, @Nullable Bundle bundle0) {
            super(v, null);
        }

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zza
        protected final void zza(ConnectionResult connectionResult0) {
            BaseGmsClient.this.zzcf.onReportServiceBinding(connectionResult0);
            BaseGmsClient.this.onConnectionFailed(connectionResult0);
        }

        @Override  // com.google.android.gms.common.internal.BaseGmsClient$zza
        protected final boolean zzm() {
            BaseGmsClient.this.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    @KeepForSdk
    public static final int CONNECT_STATE_CONNECTED = 4;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    @KeepForSdk
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    @KeepForSdk
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = null;
    @KeepForSdk
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private static final Feature[] zzbt;
    private int zzbu;
    private long zzbv;
    private long zzbw;
    private int zzbx;
    private long zzby;
    @VisibleForTesting
    private zzh zzbz;
    private final Looper zzca;
    private final GmsClientSupervisor zzcb;
    private final GoogleApiAvailabilityLight zzcc;
    private final Object zzcd;
    @GuardedBy("mServiceBrokerLock")
    private IGmsServiceBroker zzce;
    @VisibleForTesting
    protected ConnectionProgressReportCallbacks zzcf;
    @GuardedBy("mLock")
    private IInterface zzcg;
    private final ArrayList zzch;
    @GuardedBy("mLock")
    private com.google.android.gms.common.internal.BaseGmsClient.zze zzci;
    @GuardedBy("mLock")
    private int zzcj;
    private final BaseConnectionCallbacks zzck;
    private final BaseOnConnectionFailedListener zzcl;
    private final int zzcm;
    private final String zzcn;
    private ConnectionResult zzco;
    private boolean zzcp;
    private volatile com.google.android.gms.common.internal.zzb zzcq;
    @VisibleForTesting
    protected AtomicInteger zzcr;

    static {
        BaseGmsClient.zzbt = new Feature[0];
        BaseGmsClient.GOOGLE_PLUS_REQUIRED_FEATURES = new String[]{"service_esmobile", "service_googleme"};
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context0, Handler handler0, GmsClientSupervisor gmsClientSupervisor0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, int v, BaseConnectionCallbacks baseGmsClient$BaseConnectionCallbacks0, BaseOnConnectionFailedListener baseGmsClient$BaseOnConnectionFailedListener0) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context)Preconditions.checkNotNull(context0, "Context must not be null");
        this.mHandler = (Handler)Preconditions.checkNotNull(handler0, "Handler must not be null");
        this.zzca = handler0.getLooper();
        this.zzcb = (GmsClientSupervisor)Preconditions.checkNotNull(gmsClientSupervisor0, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight)Preconditions.checkNotNull(googleApiAvailabilityLight0, "API availability must not be null");
        this.zzcm = v;
        this.zzck = baseGmsClient$BaseConnectionCallbacks0;
        this.zzcl = baseGmsClient$BaseOnConnectionFailedListener0;
        this.zzcn = null;
    }

    @KeepForSdk
    protected BaseGmsClient(Context context0, Looper looper0, int v, BaseConnectionCallbacks baseGmsClient$BaseConnectionCallbacks0, BaseOnConnectionFailedListener baseGmsClient$BaseOnConnectionFailedListener0, String s) {
        this(context0, looper0, GmsClientSupervisor.getInstance(context0), GoogleApiAvailabilityLight.getInstance(), v, ((BaseConnectionCallbacks)Preconditions.checkNotNull(baseGmsClient$BaseConnectionCallbacks0)), ((BaseOnConnectionFailedListener)Preconditions.checkNotNull(baseGmsClient$BaseOnConnectionFailedListener0)), s);
    }

    @KeepForSdk
    @VisibleForTesting
    protected BaseGmsClient(Context context0, Looper looper0, GmsClientSupervisor gmsClientSupervisor0, GoogleApiAvailabilityLight googleApiAvailabilityLight0, int v, BaseConnectionCallbacks baseGmsClient$BaseConnectionCallbacks0, BaseOnConnectionFailedListener baseGmsClient$BaseOnConnectionFailedListener0, String s) {
        this.mLock = new Object();
        this.zzcd = new Object();
        this.zzch = new ArrayList();
        this.zzcj = 1;
        this.zzco = null;
        this.zzcp = false;
        this.zzcq = null;
        this.zzcr = new AtomicInteger(0);
        this.mContext = (Context)Preconditions.checkNotNull(context0, "Context must not be null");
        this.zzca = (Looper)Preconditions.checkNotNull(looper0, "Looper must not be null");
        this.zzcb = (GmsClientSupervisor)Preconditions.checkNotNull(gmsClientSupervisor0, "Supervisor must not be null");
        this.zzcc = (GoogleApiAvailabilityLight)Preconditions.checkNotNull(googleApiAvailabilityLight0, "API availability must not be null");
        this.mHandler = new zzb(this, looper0);
        this.zzcm = v;
        this.zzck = baseGmsClient$BaseConnectionCallbacks0;
        this.zzcl = baseGmsClient$BaseOnConnectionFailedListener0;
        this.zzcn = s;
    }

    @KeepForSdk
    public void checkAvailabilityAndConnect() {
        int v = this.zzcc.isGooglePlayServicesAvailable(this.mContext, 12451000);
        if(v != 0) {
            this.zza(1, null);
            this.triggerNotAvailable(new LegacyClientCallbackAdapter(this), v, null);
            return;
        }
        this.connect(new LegacyClientCallbackAdapter(this));
    }

    @KeepForSdk
    protected final void checkConnected() {
        if(!this.isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    @KeepForSdk
    public void connect(@NonNull ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks0) {
        this.zzcf = (ConnectionProgressReportCallbacks)Preconditions.checkNotNull(baseGmsClient$ConnectionProgressReportCallbacks0, "Connection progress callbacks cannot be null.");
        this.zza(2, null);
    }

    @Nullable
    @KeepForSdk
    protected abstract IInterface createServiceInterface(IBinder arg1);

    @KeepForSdk
    public void disconnect() {
        this.zzcr.incrementAndGet();
        synchronized(this.zzch) {
            int v1 = this.zzch.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                ((zzc)this.zzch.get(v2)).removeListener();
            }
            this.zzch.clear();
        }
        synchronized(this.zzcd) {
            this.zzce = null;
        }
        this.zza(1, null);
    }

    @KeepForSdk
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        IGmsServiceBroker iGmsServiceBroker0;
        IInterface iInterface0;
        synchronized(this.mLock) {
            int v = this.zzcj;
            iInterface0 = this.zzcg;
        }
        synchronized(this.zzcd) {
            iGmsServiceBroker0 = this.zzce;
        }
        printWriter0.append(s).append("mConnectState=");
        switch(v) {
            case 1: {
                printWriter0.print("DISCONNECTED");
                break;
            }
            case 2: {
                printWriter0.print("REMOTE_CONNECTING");
                break;
            }
            case 3: {
                printWriter0.print("LOCAL_CONNECTING");
                break;
            }
            case 4: {
                printWriter0.print("CONNECTED");
                break;
            }
            case 5: {
                printWriter0.print("DISCONNECTING");
                break;
            }
            default: {
                printWriter0.print("UNKNOWN");
            }
        }
        printWriter0.append(" mService=");
        if(iInterface0 == null) {
            printWriter0.append("null");
        }
        else {
            printWriter0.append(this.getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(iInterface0.asBinder())));
        }
        printWriter0.append(" mServiceBroker=");
        if(iGmsServiceBroker0 == null) {
            printWriter0.println("null");
        }
        else {
            printWriter0.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker0.asBinder())));
        }
        SimpleDateFormat simpleDateFormat0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if(this.zzbw > 0L) {
            printWriter0.append(s).append("lastConnectedTime=").println(this.zzbw + " " + simpleDateFormat0.format(new Date(this.zzbw)));
        }
        if(this.zzbv > 0L) {
            printWriter0.append(s).append("lastSuspendedCause=");
            int v1 = this.zzbu;
            switch(v1) {
                case 1: {
                    printWriter0.append("CAUSE_SERVICE_DISCONNECTED");
                    break;
                }
                case 2: {
                    printWriter0.append("CAUSE_NETWORK_LOST");
                    break;
                }
                default: {
                    printWriter0.append(String.valueOf(v1));
                }
            }
            printWriter0.append(" lastSuspendedTime=").println(this.zzbv + " " + simpleDateFormat0.format(new Date(this.zzbv)));
        }
        if(this.zzby > 0L) {
            printWriter0.append(s).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
            printWriter0.append(" lastFailedTime=").println(this.zzby + " " + simpleDateFormat0.format(new Date(this.zzby)));
        }
    }

    @KeepForSdk
    protected boolean enableLocalFallback() [...] // Inlined contents

    @KeepForSdk
    public Account getAccount() [...] // Inlined contents

    @KeepForSdk
    public Feature[] getApiFeatures() {
        return BaseGmsClient.zzbt;
    }

    @Nullable
    @KeepForSdk
    public final Feature[] getAvailableFeatures() {
        return this.zzcq == null ? null : this.zzcq.zzdb;
    }

    @KeepForSdk
    public Bundle getConnectionHint() [...] // Inlined contents

    @KeepForSdk
    public final Context getContext() {
        return this.mContext;
    }

    @KeepForSdk
    public String getEndpointPackageName() {
        if(this.isConnected()) {
            zzh zzh0 = this.zzbz;
            if(zzh0 != null) {
                return zzh0.getPackageName();
            }
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    @KeepForSdk
    protected Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    @Nullable
    @KeepForSdk
    protected String getLocalStartServiceAction() [...] // Inlined contents

    @KeepForSdk
    public final Looper getLooper() {
        return this.zzca;
    }

    @KeepForSdk
    public int getMinApkVersion() [...] // 潜在的解密器

    @WorkerThread
    @KeepForSdk
    public void getRemoteService(IAccountAccessor iAccountAccessor0, Set set0) {
        Bundle bundle0 = this.getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest0 = new GetServiceRequest(this.zzcm);
        getServiceRequest0.zzy = "com.esotericsoftware.gloomhavenhelper";
        getServiceRequest0.zzdk = bundle0;
        if(set0 != null) {
            getServiceRequest0.zzdj = (Scope[])set0.toArray(new Scope[set0.size()]);
        }
        try {
            getServiceRequest0.zzdm = BaseGmsClient.zzbt;
            getServiceRequest0.zzdn = this.getApiFeatures();
            synchronized(this.zzcd) {
                if(this.zzce == null) {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
                else {
                    this.zzce.getService(new zzd(this, this.zzcr.get()), getServiceRequest0);
                }
            }
        }
        catch(DeadObjectException deadObjectException0) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", deadObjectException0);
            this.triggerConnectionSuspended(1);
        }
        catch(SecurityException securityException0) {
            throw securityException0;
        }
        catch(RemoteException | RuntimeException remoteException0) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", remoteException0);
            this.onPostInitHandler(8, null, null, this.zzcr.get());
        }
    }

    @KeepForSdk
    protected Set getScopes() {
        return Collections.EMPTY_SET;
    }

    @KeepForSdk
    public final IInterface getService() throws DeadObjectException {
        synchronized(this.mLock) {
            if(this.zzcj != 5) {
                this.checkConnected();
                Preconditions.checkState(this.zzcg != null, "Client is connected but service is null");
                return this.zzcg;
            }
        }
        throw new DeadObjectException();
    }

    @Nullable
    @KeepForSdk
    public IBinder getServiceBrokerBinder() {
        synchronized(this.zzcd) {
            if(this.zzce == null) {
                return null;
            }
        }
        return this.zzce.asBinder();
    }

    @NonNull
    @KeepForSdk
    protected abstract String getServiceDescriptor();

    @KeepForSdk
    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    @NonNull
    @KeepForSdk
    protected abstract String getStartServiceAction();

    @KeepForSdk
    protected String getStartServicePackage() [...] // Inlined contents

    @KeepForSdk
    public boolean isConnected() {
        synchronized(this.mLock) {
        }
        return this.zzcj == 4;
    }

    @KeepForSdk
    public boolean isConnecting() {
        synchronized(this.mLock) {
        }
        return this.zzcj == 2 || this.zzcj == 3;
    }

    @CallSuper
    @KeepForSdk
    protected void onConnectedLocked(@NonNull IInterface iInterface0) {
        this.zzbw = System.currentTimeMillis();
    }

    @CallSuper
    @KeepForSdk
    protected void onConnectionFailed(ConnectionResult connectionResult0) {
        this.zzbx = connectionResult0.getErrorCode();
        this.zzby = System.currentTimeMillis();
    }

    @CallSuper
    @KeepForSdk
    protected void onConnectionSuspended(int v) {
        this.zzbu = v;
        this.zzbv = System.currentTimeMillis();
    }

    @KeepForSdk
    protected void onPostInitHandler(int v, IBinder iBinder0, Bundle bundle0, int v1) {
        zzf baseGmsClient$zzf0 = new zzf(this, v, iBinder0, bundle0);
        Message message0 = this.mHandler.obtainMessage(1, v1, -1, baseGmsClient$zzf0);
        this.mHandler.sendMessage(message0);
    }

    @KeepForSdk
    void onSetConnectState(int v, IInterface iInterface0) {
    }

    @KeepForSdk
    public void onUserSignOut(@NonNull SignOutCallbacks baseGmsClient$SignOutCallbacks0) {
        baseGmsClient$SignOutCallbacks0.onSignOutComplete();
    }

    @KeepForSdk
    public boolean providesSignIn() {
        return false;
    }

    @KeepForSdk
    public boolean requiresAccount() [...] // Inlined contents

    @KeepForSdk
    public boolean requiresGooglePlayServices() {
        return true;
    }

    @KeepForSdk
    public boolean requiresSignIn() [...] // Inlined contents

    @KeepForSdk
    public void triggerConnectionSuspended(int v) {
        int v1 = this.zzcr.get();
        Message message0 = this.mHandler.obtainMessage(6, v1, v);
        this.mHandler.sendMessage(message0);
    }

    @KeepForSdk
    @VisibleForTesting
    protected void triggerNotAvailable(@NonNull ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks0, int v, @Nullable PendingIntent pendingIntent0) {
        this.zzcf = (ConnectionProgressReportCallbacks)Preconditions.checkNotNull(baseGmsClient$ConnectionProgressReportCallbacks0, "Connection progress callbacks cannot be null.");
        int v1 = this.zzcr.get();
        Message message0 = this.mHandler.obtainMessage(3, v1, v, pendingIntent0);
        this.mHandler.sendMessage(message0);
    }

    private final void zza(int v, IInterface iInterface0) {
        Preconditions.checkArgument((v == 4 ? 1 : 0) == (iInterface0 == null ? 0 : 1));
        synchronized(this.mLock) {
            this.zzcj = v;
            this.zzcg = iInterface0;
            switch(v) {
                case 1: {
                    if(this.zzci != null) {
                        String s = this.zzbz.zzt();
                        String s1 = this.zzbz.getPackageName();
                        int v2 = this.zzbz.zzq();
                        com.google.android.gms.common.internal.BaseGmsClient.zze baseGmsClient$zze0 = this.zzci;
                        String s2 = this.zzj();
                        this.zzcb.zza(s, s1, v2, baseGmsClient$zze0, s2);
                        this.zzci = null;
                    }
                    break;
                }
                case 2: 
                case 3: {
                    if(this.zzci != null && this.zzbz != null) {
                        Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + this.zzbz.zzt() + " on " + this.zzbz.getPackageName());
                        String s3 = this.zzbz.zzt();
                        String s4 = this.zzbz.getPackageName();
                        int v3 = this.zzbz.zzq();
                        com.google.android.gms.common.internal.BaseGmsClient.zze baseGmsClient$zze1 = this.zzci;
                        String s5 = this.zzj();
                        this.zzcb.zza(s3, s4, v3, baseGmsClient$zze1, s5);
                        this.zzcr.incrementAndGet();
                    }
                    this.zzci = new com.google.android.gms.common.internal.BaseGmsClient.zze(this, this.zzcr.get());
                    this.zzbz = new zzh("com.google.android.gms", this.getStartServiceAction(), false, 0x81);
                    String s6 = this.zzbz.zzt();
                    String s7 = this.zzbz.getPackageName();
                    int v4 = this.zzbz.zzq();
                    com.google.android.gms.common.internal.BaseGmsClient.zze baseGmsClient$zze2 = this.zzci;
                    String s8 = this.zzj();
                    com.google.android.gms.common.internal.GmsClientSupervisor.zza gmsClientSupervisor$zza0 = new com.google.android.gms.common.internal.GmsClientSupervisor.zza(s6, s7, v4);
                    if(!this.zzcb.zza(gmsClientSupervisor$zza0, baseGmsClient$zze2, s8)) {
                        Log.e("GmsClient", "unable to connect to service: " + this.zzbz.zzt() + " on " + this.zzbz.getPackageName());
                        this.zza(16, null, this.zzcr.get());
                    }
                    break;
                }
                case 4: {
                    this.onConnectedLocked(iInterface0);
                }
            }
        }
    }

    static void zza(BaseGmsClient baseGmsClient0, int v) {
        baseGmsClient0.zzb(16);
    }

    static void zza(BaseGmsClient baseGmsClient0, int v, IInterface iInterface0) {
        baseGmsClient0.zza(v, null);
    }

    private final void zza(com.google.android.gms.common.internal.zzb zzb0) {
        this.zzcq = zzb0;
    }

    private final boolean zza(int v, int v1, IInterface iInterface0) {
        synchronized(this.mLock) {
            if(this.zzcj != v) {
                return false;
            }
            this.zza(v1, iInterface0);
            return true;
        }
    }

    protected final void zza(int v, @Nullable Bundle bundle0, int v1) {
        zzg baseGmsClient$zzg0 = new zzg(this, v, null);
        Message message0 = this.mHandler.obtainMessage(7, v1, -1, baseGmsClient$zzg0);
        this.mHandler.sendMessage(message0);
    }

    private final void zzb(int v) {
        int v1;
        if(this.zzk()) {
            v1 = 5;
            this.zzcp = true;
        }
        else {
            v1 = 4;
        }
        int v2 = this.zzcr.get();
        Message message0 = this.mHandler.obtainMessage(v1, v2, 16);
        this.mHandler.sendMessage(message0);
    }

    @Nullable
    private final String zzj() {
        return this.zzcn == null ? this.mContext.getClass().getName() : this.zzcn;
    }

    private final boolean zzk() {
        synchronized(this.mLock) {
        }
        return this.zzcj == 3;
    }

    private final boolean zzl() {
        if(this.zzcp) {
            return false;
        }
        if(TextUtils.isEmpty(this.getServiceDescriptor())) {
            return false;
        }
        if(TextUtils.isEmpty(null)) {
            return false;
        }
        try {
            Class.forName(this.getServiceDescriptor());
            return true;
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }
}

