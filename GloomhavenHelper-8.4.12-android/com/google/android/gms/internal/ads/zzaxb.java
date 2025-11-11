package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager.LayoutParams;
import com.google.android.gms.ads.internal.zzq;
import java.util.Locale;
import javax.annotation.ParametersAreNonnullByDefault;

@TargetApi(28)
@ParametersAreNonnullByDefault
public final class zzaxb extends zzaxc {
    // 检测为 Lambda 实现
    static WindowInsets zza(Activity activity0, View view0, WindowInsets windowInsets0) [...]

    private static void zza(boolean z, Activity activity0) {
        Window window0 = activity0.getWindow();
        WindowManager.LayoutParams windowManager$LayoutParams0 = window0.getAttributes();
        if((z ? 1 : 2) != windowManager$LayoutParams0.layoutInDisplayCutoutMode) {
            windowManager$LayoutParams0.layoutInDisplayCutoutMode = z ? 1 : 2;
            window0.setAttributes(windowManager$LayoutParams0);
        }
    }

    @Override  // com.google.android.gms.internal.ads.zzawu
    public final void zzg(Activity activity0) {
        if(((Boolean)zzvh.zzpd().zzd(zzzx.zzckd)).booleanValue() && zzq.zzkz().zzvk().zzwl() == null && !activity0.isInMultiWindowMode()) {
            zzaxb.zza(true, activity0);
            activity0.getWindow().getDecorView().setOnApplyWindowInsetsListener((View view0, WindowInsets windowInsets0) -> {
                if(zzq.zzkz().zzvk().zzwl() == null) {
                    DisplayCutout displayCutout0 = windowInsets0.getDisplayCutout();
                    if(displayCutout0 == null) {
                        zzq.zzkz().zzvk().zzei("");
                    }
                    else {
                        zzawh zzawh0 = zzq.zzkz().zzvk();
                        String s = "";
                        for(Object object0: displayCutout0.getBoundingRects()) {
                            String s1 = String.format(Locale.US, "%d,%d,%d,%d", ((Rect)object0).left, ((Rect)object0).top, ((Rect)object0).right, ((Rect)object0).bottom);
                            if(!TextUtils.isEmpty(s)) {
                                s = s + "|";
                            }
                            String s2 = String.valueOf(s);
                            String s3 = String.valueOf(s1);
                            s = s3.length() == 0 ? new String(s2) : s2 + s3;
                        }
                        zzawh0.zzei(s);
                    }
                }
                zzaxb.zza(false, activity0);
                return view0.onApplyWindowInsets(windowInsets0);
            });
        }
    }
}

