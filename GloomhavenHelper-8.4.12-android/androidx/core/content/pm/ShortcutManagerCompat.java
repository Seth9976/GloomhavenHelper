package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;

public class ShortcutManagerCompat {
    @VisibleForTesting
    static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
    @VisibleForTesting
    static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";

    @NonNull
    public static Intent createShortcutResultIntent(@NonNull Context context0, @NonNull ShortcutInfoCompat shortcutInfoCompat0) {
        Intent intent0 = Build.VERSION.SDK_INT < 26 ? null : ((ShortcutManager)context0.getSystemService(ShortcutManager.class)).createShortcutResultIntent(shortcutInfoCompat0.toShortcutInfo());
        if(intent0 == null) {
            intent0 = new Intent();
        }
        return shortcutInfoCompat0.addToIntent(intent0);
    }

    public static boolean isRequestPinShortcutSupported(@NonNull Context context0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager)context0.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported();
        }
        if(ContextCompat.checkSelfPermission(context0, "com.android.launcher.permission.INSTALL_SHORTCUT") != 0) {
            return false;
        }
        for(Object object0: context0.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0)) {
            String s = ((ResolveInfo)object0).activityInfo.permission;
            if(TextUtils.isEmpty(s) || "com.android.launcher.permission.INSTALL_SHORTCUT".equals(s)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public static boolean requestPinShortcut(@NonNull Context context0, @NonNull ShortcutInfoCompat shortcutInfoCompat0, @Nullable IntentSender intentSender0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return ((ShortcutManager)context0.getSystemService(ShortcutManager.class)).requestPinShortcut(shortcutInfoCompat0.toShortcutInfo(), intentSender0);
        }
        if(!ShortcutManagerCompat.isRequestPinShortcutSupported(context0)) {
            return false;
        }
        Intent intent0 = shortcutInfoCompat0.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
        if(intentSender0 == null) {
            context0.sendBroadcast(intent0);
            return true;
        }
        context0.sendOrderedBroadcast(intent0, null, new BroadcastReceiver() {
            @Override  // android.content.BroadcastReceiver
            public void onReceive(Context context0, Intent intent0) {
                try {
                    intentSender0.sendIntent(context0, 0, null, null, null);
                }
                catch(IntentSender.SendIntentException unused_ex) {
                }
            }
        }, null, -1, null, null);
        return true;
    }
}

