package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public final class zzceo extends FrameLayout {
    private final zzaxg zzdim;

    public zzceo(Context context0, @NonNull View view0, @NonNull zzaxg zzaxg0) {
        super(context0);
        this.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.addView(view0);
        this.zzdim = zzaxg0;
    }

    @Override  // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent0) {
        this.zzdim.zzd(motionEvent0);
        return false;
    }

    @Override  // android.view.ViewGroup
    public final void removeAllViews() {
        ArrayList arrayList0 = new ArrayList();
        int v = 0;
        for(int v1 = 0; v1 < this.getChildCount(); ++v1) {
            View view0 = this.getChildAt(v1);
            if(view0 != null && view0 instanceof zzbdv) {
                arrayList0.add(((zzbdv)view0));
            }
        }
        super.removeAllViews();
        int v2 = arrayList0.size();
        while(v < v2) {
            Object object0 = arrayList0.get(v);
            ++v;
            ((zzbdv)object0).destroy();
        }
    }
}

