package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzacs extends Image {
    private final int height;
    private final Uri uri;
    private final int width;
    private final double zzcwr;
    private final zzacr zzcww;
    private final Drawable zzcwx;

    public zzacs(zzacr zzacr0) {
        int v1;
        int v;
        double f;
        Drawable drawable0;
        this.zzcww = zzacr0;
        Uri uri0 = null;
        try {
            IObjectWrapper iObjectWrapper0 = this.zzcww.zzrg();
            drawable0 = iObjectWrapper0 == null ? null : ((Drawable)ObjectWrapper.unwrap(iObjectWrapper0));
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            drawable0 = null;
        }
        try {
            this.zzcwx = drawable0;
            uri0 = this.zzcww.getUri();
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
        }
        try {
            this.uri = uri0;
            f = this.zzcww.getScale();
        }
        catch(RemoteException remoteException2) {
            f = 1.0;
            zzazh.zzc("", remoteException2);
        }
        try {
            this.zzcwr = f;
            v = -1;
            v1 = this.zzcww.getWidth();
        }
        catch(RemoteException remoteException3) {
            zzazh.zzc("", remoteException3);
            v1 = -1;
        }
        try {
            this.width = v1;
            v = this.zzcww.getHeight();
        }
        catch(RemoteException remoteException4) {
            zzazh.zzc("", remoteException4);
        }
        this.height = v;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$Image
    public final Drawable getDrawable() {
        return this.zzcwx;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$Image
    public final int getHeight() {
        return this.height;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$Image
    public final double getScale() {
        return this.zzcwr;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$Image
    public final Uri getUri() {
        return this.uri;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$Image
    public final int getWidth() {
        return this.width;
    }
}

