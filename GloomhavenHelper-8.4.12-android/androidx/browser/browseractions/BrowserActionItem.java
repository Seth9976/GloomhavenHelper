package androidx.browser.browseractions;

import android.app.PendingIntent;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class BrowserActionItem {
    private final PendingIntent mAction;
    @DrawableRes
    private final int mIconId;
    private final String mTitle;

    public BrowserActionItem(@NonNull String s, @NonNull PendingIntent pendingIntent0) {
        this(s, pendingIntent0, 0);
    }

    public BrowserActionItem(@NonNull String s, @NonNull PendingIntent pendingIntent0, @DrawableRes int v) {
        this.mTitle = s;
        this.mAction = pendingIntent0;
        this.mIconId = v;
    }

    public PendingIntent getAction() {
        return this.mAction;
    }

    public int getIconId() {
        return this.mIconId;
    }

    public String getTitle() {
        return this.mTitle;
    }
}

