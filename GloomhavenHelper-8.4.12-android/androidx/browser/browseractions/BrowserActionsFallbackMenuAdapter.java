package androidx.browser.browseractions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.browser.R.id;
import androidx.browser.R.layout;
import androidx.core.content.res.ResourcesCompat;
import java.util.List;

class BrowserActionsFallbackMenuAdapter extends BaseAdapter {
    static class ViewHolderItem {
        ImageView mIcon;
        TextView mText;

    }

    private final Context mContext;
    private final List mMenuItems;

    BrowserActionsFallbackMenuAdapter(List list0, Context context0) {
        this.mMenuItems = list0;
        this.mContext = context0;
    }

    @Override  // android.widget.Adapter
    public int getCount() {
        return this.mMenuItems.size();
    }

    @Override  // android.widget.Adapter
    public Object getItem(int v) {
        return this.mMenuItems.get(v);
    }

    @Override  // android.widget.Adapter
    public long getItemId(int v) {
        return (long)v;
    }

    @Override  // android.widget.Adapter
    public View getView(int v, View view0, ViewGroup viewGroup0) {
        ViewHolderItem browserActionsFallbackMenuAdapter$ViewHolderItem0;
        BrowserActionItem browserActionItem0 = (BrowserActionItem)this.mMenuItems.get(v);
        if(view0 == null) {
            view0 = LayoutInflater.from(this.mContext).inflate(layout.browser_actions_context_menu_row, null);
            browserActionsFallbackMenuAdapter$ViewHolderItem0 = new ViewHolderItem();
            browserActionsFallbackMenuAdapter$ViewHolderItem0.mIcon = (ImageView)view0.findViewById(id.browser_actions_menu_item_icon);
            browserActionsFallbackMenuAdapter$ViewHolderItem0.mText = (TextView)view0.findViewById(id.browser_actions_menu_item_text);
            view0.setTag(browserActionsFallbackMenuAdapter$ViewHolderItem0);
        }
        else {
            browserActionsFallbackMenuAdapter$ViewHolderItem0 = (ViewHolderItem)view0.getTag();
        }
        browserActionsFallbackMenuAdapter$ViewHolderItem0.mText.setText(browserActionItem0.getTitle());
        if(browserActionItem0.getIconId() != 0) {
            Drawable drawable0 = ResourcesCompat.getDrawable(this.mContext.getResources(), browserActionItem0.getIconId(), null);
            browserActionsFallbackMenuAdapter$ViewHolderItem0.mIcon.setImageDrawable(drawable0);
            return view0;
        }
        browserActionsFallbackMenuAdapter$ViewHolderItem0.mIcon.setImageDrawable(null);
        return view0;
    }
}

