package androidx.browser.browseractions;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.browser.R.dimen;

@RestrictTo({Scope.LIBRARY_GROUP})
public class BrowserActionsFallbackMenuView extends LinearLayout {
    private final int mBrowserActionsMenuMaxWidthPx;
    private final int mBrowserActionsMenuMinPaddingPx;

    public BrowserActionsFallbackMenuView(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mBrowserActionsMenuMinPaddingPx = this.getResources().getDimensionPixelOffset(dimen.browser_actions_context_menu_min_padding);
        this.mBrowserActionsMenuMaxWidthPx = this.getResources().getDimensionPixelOffset(dimen.browser_actions_context_menu_max_width);
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(Math.min(this.getResources().getDisplayMetrics().widthPixels - this.mBrowserActionsMenuMinPaddingPx * 2, this.mBrowserActionsMenuMaxWidthPx), 0x40000000), v1);
    }
}

