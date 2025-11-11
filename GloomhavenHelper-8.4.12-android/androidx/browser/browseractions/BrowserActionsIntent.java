package androidx.browser.browseractions;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrowserActionsIntent {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    interface BrowserActionsFallDialogListener {
        void onDialogShown();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsItemId {
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserActionsUrlType {
    }

    public static final class Builder {
        private Context mContext;
        private final Intent mIntent;
        private ArrayList mMenuItems;
        private PendingIntent mOnItemSelectedPendingIntent;
        private int mType;
        private Uri mUri;

        public Builder(Context context0, Uri uri0) {
            this.mIntent = new Intent("androidx.browser.browseractions.browser_action_open");
            this.mOnItemSelectedPendingIntent = null;
            this.mContext = context0;
            this.mUri = uri0;
            this.mType = 0;
            this.mMenuItems = new ArrayList();
        }

        public BrowserActionsIntent build() {
            this.mIntent.setData(this.mUri);
            this.mIntent.putExtra("androidx.browser.browseractions.extra.TYPE", this.mType);
            this.mIntent.putParcelableArrayListExtra("androidx.browser.browseractions.extra.MENU_ITEMS", this.mMenuItems);
            PendingIntent pendingIntent0 = PendingIntent.getActivity(this.mContext, 0, new Intent(), 0);
            this.mIntent.putExtra("androidx.browser.browseractions.APP_ID", pendingIntent0);
            PendingIntent pendingIntent1 = this.mOnItemSelectedPendingIntent;
            if(pendingIntent1 != null) {
                this.mIntent.putExtra("androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT", pendingIntent1);
            }
            return new BrowserActionsIntent(this.mIntent);
        }

        private Bundle getBundleFromItem(BrowserActionItem browserActionItem0) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("androidx.browser.browseractions.TITLE", browserActionItem0.getTitle());
            bundle0.putParcelable("androidx.browser.browseractions.ACTION", browserActionItem0.getAction());
            if(browserActionItem0.getIconId() != 0) {
                bundle0.putInt("androidx.browser.browseractions.ICON_ID", browserActionItem0.getIconId());
            }
            return bundle0;
        }

        public Builder setCustomItems(ArrayList arrayList0) {
            if(arrayList0.size() > 5) {
                throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
            }
            for(int v = 0; v < arrayList0.size(); ++v) {
                if(TextUtils.isEmpty(((BrowserActionItem)arrayList0.get(v)).getTitle()) || ((BrowserActionItem)arrayList0.get(v)).getAction() == null) {
                    throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
                }
                this.mMenuItems.add(this.getBundleFromItem(((BrowserActionItem)arrayList0.get(v))));
            }
            return this;
        }

        public Builder setCustomItems(BrowserActionItem[] arr_browserActionItem) {
            return this.setCustomItems(new ArrayList(Arrays.asList(arr_browserActionItem)));
        }

        public Builder setOnItemSelectedAction(PendingIntent pendingIntent0) {
            this.mOnItemSelectedPendingIntent = pendingIntent0;
            return this;
        }

        public Builder setUrlType(int v) {
            this.mType = v;
            return this;
        }
    }

    public static final String ACTION_BROWSER_ACTIONS_OPEN = "androidx.browser.browseractions.browser_action_open";
    public static final String EXTRA_APP_ID = "androidx.browser.browseractions.APP_ID";
    public static final String EXTRA_MENU_ITEMS = "androidx.browser.browseractions.extra.MENU_ITEMS";
    public static final String EXTRA_SELECTED_ACTION_PENDING_INTENT = "androidx.browser.browseractions.extra.SELECTED_ACTION_PENDING_INTENT";
    public static final String EXTRA_TYPE = "androidx.browser.browseractions.extra.TYPE";
    public static final int ITEM_COPY = 3;
    public static final int ITEM_DOWNLOAD = 2;
    public static final int ITEM_INVALID_ITEM = -1;
    public static final int ITEM_OPEN_IN_INCOGNITO = 1;
    public static final int ITEM_OPEN_IN_NEW_TAB = 0;
    public static final int ITEM_SHARE = 4;
    public static final String KEY_ACTION = "androidx.browser.browseractions.ACTION";
    public static final String KEY_ICON_ID = "androidx.browser.browseractions.ICON_ID";
    public static final String KEY_TITLE = "androidx.browser.browseractions.TITLE";
    public static final int MAX_CUSTOM_ITEMS = 5;
    private static final String TAG = "BrowserActions";
    private static final String TEST_URL = "https://www.example.com";
    public static final int URL_TYPE_AUDIO = 3;
    public static final int URL_TYPE_FILE = 4;
    public static final int URL_TYPE_IMAGE = 1;
    public static final int URL_TYPE_NONE = 0;
    public static final int URL_TYPE_PLUGIN = 5;
    public static final int URL_TYPE_VIDEO = 2;
    @NonNull
    private final Intent mIntent;
    private static BrowserActionsFallDialogListener sDialogListenter;

    BrowserActionsIntent(@NonNull Intent intent0) {
        this.mIntent = intent0;
    }

    private static List getBrowserActionsIntentHandlers(Context context0) {
        Intent intent0 = new Intent("androidx.browser.browseractions.browser_action_open", Uri.parse("https://www.example.com"));
        return context0.getPackageManager().queryIntentActivities(intent0, 0x20000);
    }

    public static String getCreatorPackageName(Intent intent0) {
        PendingIntent pendingIntent0 = (PendingIntent)intent0.getParcelableExtra("androidx.browser.browseractions.APP_ID");
        if(pendingIntent0 != null) {
            return Build.VERSION.SDK_INT < 17 ? pendingIntent0.getTargetPackage() : pendingIntent0.getCreatorPackage();
        }
        return null;
    }

    @NonNull
    public Intent getIntent() {
        return this.mIntent;
    }

    public static void launchIntent(Context context0, Intent intent0) {
        BrowserActionsIntent.launchIntent(context0, intent0, BrowserActionsIntent.getBrowserActionsIntentHandlers(context0));
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    static void launchIntent(Context context0, Intent intent0, List list0) {
        if(list0 != null) {
            switch(list0.size()) {
                case 0: {
                    break;
                }
                case 1: {
                    intent0.setPackage(((ResolveInfo)list0.get(0)).activityInfo.packageName);
                    ContextCompat.startActivity(context0, intent0, null);
                    return;
                }
                default: {
                    Intent intent1 = new Intent("android.intent.action.VIEW", Uri.parse("https://www.example.com"));
                    ResolveInfo resolveInfo0 = context0.getPackageManager().resolveActivity(intent1, 0x10000);
                    if(resolveInfo0 != null) {
                        String s = resolveInfo0.activityInfo.packageName;
                        for(int v = 0; v < list0.size(); ++v) {
                            if(s.equals(((ResolveInfo)list0.get(v)).activityInfo.packageName)) {
                                intent0.setPackage(s);
                                break;
                            }
                        }
                    }
                    ContextCompat.startActivity(context0, intent0, null);
                    return;
                }
            }
        }
        BrowserActionsIntent.openFallbackBrowserActionsMenu(context0, intent0);
    }

    public static void openBrowserAction(Context context0, Uri uri0) {
        BrowserActionsIntent.launchIntent(context0, new Builder(context0, uri0).build().getIntent());
    }

    public static void openBrowserAction(Context context0, Uri uri0, int v, ArrayList arrayList0, PendingIntent pendingIntent0) {
        BrowserActionsIntent.launchIntent(context0, new Builder(context0, uri0).setUrlType(v).setCustomItems(arrayList0).setOnItemSelectedAction(pendingIntent0).build().getIntent());
    }

    private static void openFallbackBrowserActionsMenu(Context context0, Intent intent0) {
        Uri uri0 = intent0.getData();
        int v = intent0.getIntExtra("androidx.browser.browseractions.extra.TYPE", 0);
        ArrayList arrayList0 = intent0.getParcelableArrayListExtra("androidx.browser.browseractions.extra.MENU_ITEMS");
        BrowserActionsIntent.openFallbackBrowserActionsMenu(context0, uri0, v, (arrayList0 == null ? null : BrowserActionsIntent.parseBrowserActionItems(arrayList0)));
    }

    private static void openFallbackBrowserActionsMenu(Context context0, Uri uri0, int v, List list0) {
        new BrowserActionsFallbackMenuUi(context0, uri0, list0).displayMenu();
        BrowserActionsFallDialogListener browserActionsIntent$BrowserActionsFallDialogListener0 = BrowserActionsIntent.sDialogListenter;
        if(browserActionsIntent$BrowserActionsFallDialogListener0 != null) {
            browserActionsIntent$BrowserActionsFallDialogListener0.onDialogShown();
        }
    }

    public static List parseBrowserActionItems(ArrayList arrayList0) {
        List list0 = new ArrayList();
        for(int v = 0; v < arrayList0.size(); ++v) {
            Bundle bundle0 = (Bundle)arrayList0.get(v);
            String s = bundle0.getString("androidx.browser.browseractions.TITLE");
            PendingIntent pendingIntent0 = (PendingIntent)bundle0.getParcelable("androidx.browser.browseractions.ACTION");
            int v1 = bundle0.getInt("androidx.browser.browseractions.ICON_ID");
            if(TextUtils.isEmpty(s) || pendingIntent0 == null) {
                throw new IllegalArgumentException("Custom item should contain a non-empty title and non-null intent.");
            }
            list0.add(new BrowserActionItem(s, pendingIntent0, v1));
        }
        return list0;
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    static void setDialogShownListenter(BrowserActionsFallDialogListener browserActionsIntent$BrowserActionsFallDialogListener0) {
        BrowserActionsIntent.sDialogListenter = browserActionsIntent$BrowserActionsFallDialogListener0;
    }
}

