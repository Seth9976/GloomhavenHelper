package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public final class zzacb extends RelativeLayout {
    private static final float[] zzcwo;
    @Nullable
    private AnimationDrawable zzcwp;

    static {
        zzacb.zzcwo = new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    }

    public zzacb(Context context0, zzaby zzaby0, RelativeLayout.LayoutParams relativeLayout$LayoutParams0) {
        super(context0);
        Preconditions.checkNotNull(zzaby0);
        ShapeDrawable shapeDrawable0 = new ShapeDrawable(new RoundRectShape(zzacb.zzcwo, null, null));
        shapeDrawable0.getPaint().setColor(zzaby0.getBackgroundColor());
        this.setLayoutParams(relativeLayout$LayoutParams0);
        this.setBackground(shapeDrawable0);
        RelativeLayout.LayoutParams relativeLayout$LayoutParams1 = new RelativeLayout.LayoutParams(-2, -2);
        if(!TextUtils.isEmpty(zzaby0.getText())) {
            RelativeLayout.LayoutParams relativeLayout$LayoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            TextView textView0 = new TextView(context0);
            textView0.setLayoutParams(relativeLayout$LayoutParams2);
            textView0.setId(0x47470001);
            textView0.setTypeface(Typeface.DEFAULT);
            textView0.setText(zzaby0.getText());
            textView0.setTextColor(zzaby0.getTextColor());
            textView0.setTextSize(((float)zzaby0.getTextSize()));
            textView0.setPadding(zzayx.zzb(context0, 4), 0, zzayx.zzb(context0, 4), 0);
            this.addView(textView0);
            relativeLayout$LayoutParams1.addRule(1, textView0.getId());
        }
        ImageView imageView0 = new ImageView(context0);
        imageView0.setLayoutParams(relativeLayout$LayoutParams1);
        imageView0.setId(0x47470002);
        List list0 = zzaby0.zzrc();
        if(list0 != null && list0.size() > 1) {
            this.zzcwp = new AnimationDrawable();
            for(Object object0: list0) {
                zzacd zzacd0 = (zzacd)object0;
                try {
                    Drawable drawable0 = (Drawable)ObjectWrapper.unwrap(zzacd0.zzrg());
                    this.zzcwp.addFrame(drawable0, zzaby0.zzrd());
                }
                catch(Exception exception0) {
                    zzawf.zzc("Error while getting drawable.", exception0);
                }
            }
            imageView0.setBackground(this.zzcwp);
        }
        else if(list0.size() == 1) {
            try {
                imageView0.setImageDrawable(((Drawable)ObjectWrapper.unwrap(((zzacd)list0.get(0)).zzrg())));
            }
            catch(Exception exception1) {
                zzawf.zzc("Error while getting drawable.", exception1);
            }
        }
        this.addView(imageView0);
    }

    @Override  // android.view.ViewGroup
    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable0 = this.zzcwp;
        if(animationDrawable0 != null) {
            animationDrawable0.start();
        }
        super.onAttachedToWindow();
    }
}

