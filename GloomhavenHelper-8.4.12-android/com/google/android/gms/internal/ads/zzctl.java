package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public final class zzctl implements zzcye {
    private final zzdeu zzfir;
    private final zzdoe zzghq;
    private final View zzghr;
    private final Context zzur;

    public zzctl(zzdoe zzdoe0, Context context0, zzdeu zzdeu0, @Nullable ViewGroup viewGroup0) {
        this.zzghq = zzdoe0;
        this.zzur = context0;
        this.zzfir = zzdeu0;
        this.zzghr = viewGroup0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcye
    public final zzdof zzapb() {
        zzcto zzcto0 = () -> {
            Context context0 = this.zzur;
            zzuk zzuk0 = this.zzfir.zzblv;
            ArrayList arrayList0 = new ArrayList();
            for(View view0 = this.zzghr; view0 != null; view0 = (View)viewParent0) {
                int v = -1;
                ViewParent viewParent0 = view0.getParent();
                if(viewParent0 == null) {
                    break;
                }
                if(viewParent0 instanceof ViewGroup) {
                    v = ((ViewGroup)viewParent0).indexOfChild(view0);
                }
                Bundle bundle0 = new Bundle();
                bundle0.putString("type", viewParent0.getClass().getName());
                bundle0.putInt("index_of_child", v);
                arrayList0.add(bundle0);
                if(!(viewParent0 instanceof View)) {
                    break;
                }
            }
            return new zzctm(context0, zzuk0, arrayList0);
        };
        return this.zzghq.zzd(zzcto0);
    }

    // 检测为 Lambda 实现
    final zzctm zzapc() throws Exception [...]
}

