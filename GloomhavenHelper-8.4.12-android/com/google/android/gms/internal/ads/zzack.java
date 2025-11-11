package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import java.util.ArrayList;
import java.util.List;

public final class zzack extends AdChoicesInfo {
    private String text;
    private final List zzcwj;
    private final zzacj zzcwu;

    public zzack(zzacj zzacj0) {
        zzacr zzacr0;
        this.zzcwj = new ArrayList();
        this.zzcwu = zzacj0;
        try {
            this.text = this.zzcwu.getText();
        }
        catch(RemoteException remoteException0) {
            zzazh.zzc("", remoteException0);
            this.text = "";
        }
        try {
            for(Object object0: zzacj0.zzrb()) {
                if(!(object0 instanceof IBinder) || ((IBinder)object0) == null) {
                    zzacr0 = null;
                }
                else {
                    IInterface iInterface0 = ((IBinder)object0).queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzacr0 = iInterface0 instanceof zzacr ? ((zzacr)iInterface0) : new zzact(((IBinder)object0));
                }
                if(zzacr0 != null) {
                    zzacs zzacs0 = new zzacs(zzacr0);
                    this.zzcwj.add(zzacs0);
                }
            }
        }
        catch(RemoteException remoteException1) {
            zzazh.zzc("", remoteException1);
        }
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$AdChoicesInfo
    public final List getImages() {
        return this.zzcwj;
    }

    @Override  // com.google.android.gms.ads.formats.NativeAd$AdChoicesInfo
    public final CharSequence getText() {
        return this.text;
    }
}

