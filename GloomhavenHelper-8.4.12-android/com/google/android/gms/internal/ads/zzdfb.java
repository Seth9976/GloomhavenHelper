package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

public final class zzdfb {
    private final zzalp zzgqz;

    public zzdfb(zzalp zzalp0) {
        this.zzgqz = zzalp0;
    }

    public final void destroy() throws zzdfa {
        try {
            this.zzgqz.destroy();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final zzxj getVideoController() throws zzdfa {
        try {
            return this.zzgqz.getVideoController();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final View getView() throws zzdfa {
        try {
            return (View)ObjectWrapper.unwrap(this.zzgqz.zzsp());
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final boolean isInitialized() throws zzdfa {
        try {
            return this.zzgqz.isInitialized();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void onContextChanged(Context context0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zzs(iObjectWrapper0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void pause() throws zzdfa {
        try {
            this.zzgqz.pause();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void resume() throws zzdfa {
        try {
            this.zzgqz.resume();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void setImmersiveMode(boolean z) throws zzdfa {
        try {
            this.zzgqz.setImmersiveMode(z);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void showInterstitial() throws zzdfa {
        try {
            this.zzgqz.showInterstitial();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void showVideo() throws zzdfa {
        try {
            this.zzgqz.showVideo();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzahb zzahb0, List list0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzahb0, list0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzasm zzasm0, List list0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzasm0, list0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuh zzuh0, String s, zzalq zzalq0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuh0, s, zzalq0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuh zzuh0, String s, zzasm zzasm0, String s1) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuh0, null, zzasm0, s1);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuh0, s, s1, zzalq0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuh zzuh0, String s, String s1, zzalq zzalq0, zzach zzach0, List list0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuh0, s, s1, zzalq0, zzach0, list0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuk zzuk0, zzuh zzuh0, String s, zzalq zzalq0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuk0, zzuh0, s, zzalq0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(Context context0, zzuk zzuk0, zzuh zzuh0, String s, String s1, zzalq zzalq0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zza(iObjectWrapper0, zzuk0, zzuh0, s, s1, zzalq0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zza(zzuh zzuh0, String s) throws zzdfa {
        try {
            this.zzgqz.zza(zzuh0, s);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zzb(Context context0, zzuh zzuh0, String s, zzalq zzalq0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zzb(iObjectWrapper0, zzuh0, s, zzalq0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final void zzcg(Context context0) throws zzdfa {
        try {
            IObjectWrapper iObjectWrapper0 = ObjectWrapper.wrap(context0);
            this.zzgqz.zzt(iObjectWrapper0);
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final zzalx zzsq() throws zzdfa {
        try {
            return this.zzgqz.zzsq();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final zzaly zzsr() throws zzdfa {
        try {
            return this.zzgqz.zzsr();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final boolean zzsu() throws zzdfa {
        try {
            return this.zzgqz.zzsu();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }

    public final zzamd zzsw() throws zzdfa {
        try {
            return this.zzgqz.zzsw();
        }
        catch(Throwable throwable0) {
            throw new zzdfa(throwable0);
        }
    }
}

