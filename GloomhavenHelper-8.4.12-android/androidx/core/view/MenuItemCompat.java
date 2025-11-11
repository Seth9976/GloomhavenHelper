package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem;
import android.view.View;
import androidx.core.internal.view.SupportMenuItem;

public final class MenuItemCompat {
    @Deprecated
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem arg1);

        boolean onMenuItemActionExpand(MenuItem arg1);
    }

    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    @Deprecated
    public static boolean collapseActionView(MenuItem menuItem0) {
        return menuItem0.collapseActionView();
    }

    @Deprecated
    public static boolean expandActionView(MenuItem menuItem0) {
        return menuItem0.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getSupportActionProvider();
        }
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @Deprecated
    public static View getActionView(MenuItem menuItem0) {
        return menuItem0.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getAlphabeticModifiers();
        }
        return Build.VERSION.SDK_INT < 26 ? 0 : menuItem0.getAlphabeticModifiers();
    }

    public static CharSequence getContentDescription(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getContentDescription();
        }
        return Build.VERSION.SDK_INT < 26 ? null : menuItem0.getContentDescription();
    }

    public static ColorStateList getIconTintList(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getIconTintList();
        }
        return Build.VERSION.SDK_INT < 26 ? null : menuItem0.getIconTintList();
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getIconTintMode();
        }
        return Build.VERSION.SDK_INT < 26 ? null : menuItem0.getIconTintMode();
    }

    public static int getNumericModifiers(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getNumericModifiers();
        }
        return Build.VERSION.SDK_INT < 26 ? 0 : menuItem0.getNumericModifiers();
    }

    public static CharSequence getTooltipText(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getTooltipText();
        }
        return Build.VERSION.SDK_INT < 26 ? null : menuItem0.getTooltipText();
    }

    @Deprecated
    public static boolean isActionViewExpanded(MenuItem menuItem0) {
        return menuItem0.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuItem0, ActionProvider actionProvider0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).setSupportActionProvider(actionProvider0);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem0;
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem0, int v) {
        return menuItem0.setActionView(v);
    }

    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem0, View view0) {
        return menuItem0.setActionView(view0);
    }

    public static void setAlphabeticShortcut(MenuItem menuItem0, char c, int v) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setAlphabeticShortcut(c, v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setAlphabeticShortcut(c, v);
        }
    }

    public static void setContentDescription(MenuItem menuItem0, CharSequence charSequence0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setContentDescription(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setContentDescription(charSequence0);
        }
    }

    public static void setIconTintList(MenuItem menuItem0, ColorStateList colorStateList0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setIconTintList(colorStateList0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setIconTintList(colorStateList0);
        }
    }

    public static void setIconTintMode(MenuItem menuItem0, PorterDuff.Mode porterDuff$Mode0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setIconTintMode(porterDuff$Mode0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setIconTintMode(porterDuff$Mode0);
        }
    }

    public static void setNumericShortcut(MenuItem menuItem0, char c, int v) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setNumericShortcut(c, v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setNumericShortcut(c, v);
        }
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem menuItem0, OnActionExpandListener menuItemCompat$OnActionExpandListener0) {
        return menuItem0.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override  // android.view.MenuItem$OnActionExpandListener
            public boolean onMenuItemActionCollapse(MenuItem menuItem0) {
                return menuItemCompat$OnActionExpandListener0.onMenuItemActionCollapse(menuItem0);
            }

            @Override  // android.view.MenuItem$OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem0) {
                return menuItemCompat$OnActionExpandListener0.onMenuItemActionExpand(menuItem0);
            }
        });
    }

    public static void setShortcut(MenuItem menuItem0, char c, char c1, int v, int v1) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setShortcut(c, c1, v, v1);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setShortcut(c, c1, v, v1);
        }
    }

    @Deprecated
    public static void setShowAsAction(MenuItem menuItem0, int v) {
        menuItem0.setShowAsAction(v);
    }

    public static void setTooltipText(MenuItem menuItem0, CharSequence charSequence0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setTooltipText(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            menuItem0.setTooltipText(charSequence0);
        }
    }
}

