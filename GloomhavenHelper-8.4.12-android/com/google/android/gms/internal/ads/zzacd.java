package com.google.android.gms.internal.ads;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzacd extends zzacq {
    private final int height;
    private final Uri uri;
    private final int width;
    private final Drawable zzcwq;
    private final double zzcwr;

    public zzacd(Drawable drawable0, Uri uri0, double f, int v, int v1) {
        this.zzcwq = drawable0;
        this.uri = uri0;
        this.zzcwr = f;
        this.width = v;
        this.height = v1;
    }

    @Override  // com.google.android.gms.internal.ads.zzacr
    public final int getHeight() {
        return this.height;
    }

    @Override  // com.google.android.gms.internal.ads.zzacr
    public final double getScale() {
        return this.zzcwr;
    }

    @Override  // com.google.android.gms.internal.ads.zzacr
    public final Uri getUri() throws RemoteException {
        return this.uri;
    }

    @Override  // com.google.android.gms.internal.ads.zzacr
    public final int getWidth() {
        return this.width;
    }

    @Override  // com.google.android.gms.internal.ads.zzacr
    public final IObjectWrapper zzrg() throws RemoteException {
        return ObjectWrapper.wrap(this.zzcwq);
    }
}

