package com.google.android.gms.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzapb;
import com.google.android.gms.internal.ads.zzazh;
import com.google.android.gms.internal.ads.zzvh;

@KeepForSdk
public final class AdActivity extends Activity {
    @KeepForSdk
    public static final String CLASS_NAME = "com.google.android.gms.ads.AdActivity";
    @KeepForSdk
    public static final String SIMPLE_CLASS_NAME = "AdActivity";
    private zzapb zzabc;

    @Override  // android.app.Activity
    protected final void onActivityResult(int v, int v1, Intent intent0) {
        try {
            this.zzabc.onActivityResult(v, v1, intent0);
        }
        catch(Exception exception0) {
            zzazh.zze("#007 Could not call remote method.", exception0);
        }
        super.onActivityResult(v, v1, intent0);
    }

    @Override  // android.app.Activity
    public final void onBackPressed() {
        boolean z = true;
        if(this.zzabc != null) {
            try {
                z = this.zzabc.zztr();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        if(z) {
            super.onBackPressed();
        }
    }

    @Override  // android.app.Activity
    public final void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        try {
            this.zzabc.zzad(ObjectWrapper.wrap(configuration0));
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
        }
    }

    @Override  // android.app.Activity
    protected final void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.zzabc = zzvh.zzpa().zzb(this);
        zzapb zzapb0 = this.zzabc;
        if(zzapb0 == null) {
            zzazh.zze("#007 Could not call remote method.", null);
            this.finish();
            return;
        }
        try {
            zzapb0.onCreate(bundle0);
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            this.finish();
        }
    }

    @Override  // android.app.Activity
    protected final void onDestroy() {
        if(this.zzabc != null) {
            try {
                this.zzabc.onDestroy();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
        super.onDestroy();
    }

    @Override  // android.app.Activity
    protected final void onPause() {
        if(this.zzabc != null) {
            try {
                this.zzabc.onPause();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
                this.finish();
            }
        }
        super.onPause();
    }

    @Override  // android.app.Activity
    protected final void onRestart() {
        super.onRestart();
        try {
            if(this.zzabc != null) {
                this.zzabc.onRestart();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            this.finish();
        }
    }

    @Override  // android.app.Activity
    protected final void onResume() {
        super.onResume();
        try {
            if(this.zzabc != null) {
                this.zzabc.onResume();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            this.finish();
        }
    }

    @Override  // android.app.Activity
    protected final void onSaveInstanceState(Bundle bundle0) {
        if(this.zzabc != null) {
            try {
                this.zzabc.onSaveInstanceState(bundle0);
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
                this.finish();
            }
        }
        super.onSaveInstanceState(bundle0);
    }

    @Override  // android.app.Activity
    protected final void onStart() {
        super.onStart();
        try {
            if(this.zzabc != null) {
                this.zzabc.onStart();
            }
        }
        catch(RemoteException remoteException0) {
            zzazh.zze("#007 Could not call remote method.", remoteException0);
            this.finish();
        }
    }

    @Override  // android.app.Activity
    protected final void onStop() {
        if(this.zzabc != null) {
            try {
                this.zzabc.onStop();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
                this.finish();
            }
        }
        super.onStop();
    }

    @Override  // android.app.Activity
    public final void setContentView(int v) {
        super.setContentView(v);
        this.zzdk();
    }

    @Override  // android.app.Activity
    public final void setContentView(View view0) {
        super.setContentView(view0);
        this.zzdk();
    }

    @Override  // android.app.Activity
    public final void setContentView(View view0, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        super.setContentView(view0, viewGroup$LayoutParams0);
        this.zzdk();
    }

    private final void zzdk() {
        zzapb zzapb0 = this.zzabc;
        if(zzapb0 != null) {
            try {
                zzapb0.zzdk();
            }
            catch(RemoteException remoteException0) {
                zzazh.zze("#007 Could not call remote method.", remoteException0);
            }
        }
    }
}

