package androidx.browser.browseractions;

import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface.OnShowListener;
import android.content.DialogInterface;
import android.net.Uri;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.browser.R.id;
import androidx.browser.R.layout;
import androidx.core.widget.TextViewCompat;
import java.util.List;

class BrowserActionsFallbackMenuUi implements AdapterView.OnItemClickListener {
    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    interface BrowserActionsFallMenuUiListener {
        void onMenuShown(View arg1);
    }

    private static final String TAG = "BrowserActionskMenuUi";
    private BrowserActionsFallbackMenuDialog mBrowserActionsDialog;
    private final Context mContext;
    private final List mMenuItems;
    BrowserActionsFallMenuUiListener mMenuUiListener;
    private final Uri mUri;

    BrowserActionsFallbackMenuUi(Context context0, Uri uri0, List list0) {
        this.mContext = context0;
        this.mUri = uri0;
        this.mMenuItems = list0;
    }

    public void displayMenu() {
        View view0 = LayoutInflater.from(this.mContext).inflate(layout.browser_actions_context_menu_page, null);
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView0 = this.initMenuView(view0);
        this.mBrowserActionsDialog = new BrowserActionsFallbackMenuDialog(this.mContext, browserActionsFallbackMenuView0);
        this.mBrowserActionsDialog.setContentView(view0);
        if(this.mMenuUiListener != null) {
            this.mBrowserActionsDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override  // android.content.DialogInterface$OnShowListener
                public void onShow(DialogInterface dialogInterface0) {
                    BrowserActionsFallbackMenuUi.this.mMenuUiListener.onMenuShown(view0);
                }
            });
        }
        this.mBrowserActionsDialog.show();
    }

    private BrowserActionsFallbackMenuView initMenuView(View view0) {
        BrowserActionsFallbackMenuView browserActionsFallbackMenuView0 = (BrowserActionsFallbackMenuView)view0.findViewById(id.browser_actions_menu_view);
        TextView textView0 = (TextView)view0.findViewById(id.browser_actions_header_text);
        textView0.setText(this.mUri.toString());
        textView0.setOnClickListener(new View.OnClickListener() {
            @Override  // android.view.View$OnClickListener
            public void onClick(View view0) {
                if(TextViewCompat.getMaxLines(textView0) == 0x7FFFFFFF) {
                    textView0.setMaxLines(1);
                    textView0.setEllipsize(TextUtils.TruncateAt.END);
                    return;
                }
                textView0.setMaxLines(0x7FFFFFFF);
                textView0.setEllipsize(null);
            }
        });
        ListView listView0 = (ListView)view0.findViewById(id.browser_actions_menu_items);
        listView0.setAdapter(new BrowserActionsFallbackMenuAdapter(this.mMenuItems, this.mContext));
        listView0.setOnItemClickListener(this);
        return browserActionsFallbackMenuView0;
    }

    @Override  // android.widget.AdapterView$OnItemClickListener
    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
        PendingIntent pendingIntent0 = ((BrowserActionItem)this.mMenuItems.get(v)).getAction();
        try {
            pendingIntent0.send();
            this.mBrowserActionsDialog.dismiss();
        }
        catch(PendingIntent.CanceledException pendingIntent$CanceledException0) {
            Log.e("BrowserActionskMenuUi", "Failed to send custom item action", pendingIntent$CanceledException0);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @VisibleForTesting
    void setMenuUiListener(BrowserActionsFallMenuUiListener browserActionsFallbackMenuUi$BrowserActionsFallMenuUiListener0) {
        this.mMenuUiListener = browserActionsFallbackMenuUi$BrowserActionsFallMenuUiListener0;
    }
}

