package com.google.android.gms.internal.ads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class zzdms extends zzdnn implements Runnable {
    @NullableDecl
    private zzdof zzhbj;
    @NullableDecl
    private Class zzhbk;
    @NullableDecl
    private Object zzhbl;

    zzdms(zzdof zzdof0, Class class0, Object object0) {
        this.zzhbj = (zzdof)zzdlg.checkNotNull(zzdof0);
        this.zzhbk = (Class)zzdlg.checkNotNull(class0);
        this.zzhbl = zzdlg.checkNotNull(object0);
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final void afterDone() {
        this.maybePropagateCancellationTo(this.zzhbj);
        this.zzhbj = null;
        this.zzhbk = null;
        this.zzhbl = null;
    }

    @Override  // com.google.android.gms.internal.ads.zzdmt
    protected final String pendingToString() {
        zzdof zzdof0 = this.zzhbj;
        Class class0 = this.zzhbk;
        Object object0 = this.zzhbl;
        String s = super.pendingToString();
        String s1 = zzdof0 == null ? "" : "inputFuture=[" + zzdof0 + "], ";
        if(class0 != null && object0 != null) {
            return s1 + "exceptionType=[" + class0 + "], fallback=[" + object0 + "]";
        }
        if(s != null) {
            String s2 = String.valueOf(s1);
            String s3 = String.valueOf(s);
            return s3.length() == 0 ? new String(s2) : s2 + s3;
        }
        return null;
    }

    @Override
    public final void run() {
        Object object2;
        Object object1;
        zzdof zzdof0 = this.zzhbj;
        Class class0 = this.zzhbk;
        Object object0 = this.zzhbl;
        if(((object0 == null ? 1 : 0) | ((zzdof0 == null ? 1 : 0) | (class0 == null ? 1 : 0))) == 0 && !this.isCancelled()) {
            try {
                this.zzhbj = null;
                throwable0 = zzdof0 instanceof zzdow ? zzdoz.zza(((zzdow)zzdof0)) : null;
                if(throwable0 == null) {
                    object1 = zzdnt.zza(zzdof0);
                }
                else {
                    goto label_19;
                }
                goto label_20;
            }
            catch(ExecutionException executionException0) {
                Throwable throwable1 = executionException0.getCause();
                throwable0 = throwable1 == null ? new NullPointerException("Future type " + zzdof0.getClass() + " threw " + executionException0.getClass() + " without a cause") : throwable1;
            }
            catch(Throwable throwable0) {
                object1 = null;
                goto label_20;
            }
        label_19:
            object1 = null;
        label_20:
            if(throwable0 == null) {
                this.set(object1);
                return;
            }
            if(!class0.isInstance(throwable0)) {
                this.setFuture(zzdof0);
                return;
            }
            try {
                object2 = this.zza(object0, throwable0);
            }
            catch(Throwable throwable2) {
                try {
                    this.setException(throwable2);
                    this.zzhbk = null;
                    this.zzhbl = null;
                    return;
                }
                catch(Throwable throwable3) {
                    this.zzhbk = null;
                    this.zzhbl = null;
                    throw throwable3;
                }
            }
            this.zzhbk = null;
            this.zzhbl = null;
            this.setResult(object2);
        }
    }

    abstract void setResult(@NullableDecl Object arg1);

    static zzdof zza(zzdof zzdof0, Class class0, zzdng zzdng0, Executor executor0) {
        zzdof zzdof1 = new zzdmr(zzdof0, class0, zzdng0);
        zzdof0.addListener(((Runnable)zzdof1), zzdoh.zza(executor0, ((zzdmt)zzdof1)));
        return zzdof1;
    }

    @NullableDecl
    abstract Object zza(Object arg1, Throwable arg2) throws Exception;
}

