package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import androidx.annotation.Nullable;

@TargetApi(19)
public final class zzcer {
    @Nullable
    private PopupWindow zzfur;
    @Nullable
    private Context zzur;

    // 去混淆评级： 中等(60)
    public final void zza(Context context0, View view0) {
    }

    public final void zzamg() {
        Context context0 = this.zzur;
        if(context0 != null && this.zzfur != null) {
            if(context0 instanceof Activity && ((Activity)context0).isDestroyed()) {
                this.zzur = null;
                this.zzfur = null;
                return;
            }
            if(this.zzfur.isShowing()) {
                this.zzfur.dismiss();
            }
            this.zzur = null;
            this.zzfur = null;
        }
    }

    private static PopupWindow zzb(Context context0, View view0) {
        Window window0 = context0 instanceof Activity ? ((Activity)context0).getWindow() : null;
        if(window0 == null || window0.getDecorView() == null || ((Activity)context0).isDestroyed()) {
            return null;
        }
        FrameLayout frameLayout0 = new FrameLayout(context0);
        frameLayout0.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        frameLayout0.addView(view0, -1, -1);
        PopupWindow popupWindow0 = new PopupWindow(frameLayout0, 1, 1, false);
        popupWindow0.setOutsideTouchable(true);
        popupWindow0.setClippingEnabled(false);
        zzawf.zzeb("Displaying the 1x1 popup off the screen.");
        try {
            popupWindow0.showAtLocation(window0.getDecorView(), 0, -1, -1);
            return popupWindow0;
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

