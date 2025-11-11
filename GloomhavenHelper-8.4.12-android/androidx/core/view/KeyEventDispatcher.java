package androidx.core.view;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window.Callback;
import android.view.Window;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({Scope.LIBRARY_GROUP})
public class KeyEventDispatcher {
    public interface Component {
        boolean superDispatchKeyEvent(KeyEvent arg1);
    }

    private static boolean sActionBarFieldsFetched = false;
    private static Method sActionBarOnMenuKeyMethod = null;
    private static boolean sDialogFieldsFetched = false;
    private static Field sDialogKeyListenerField;

    static {
    }

    private static boolean actionBarOnMenuKeyEventPre28(ActionBar actionBar0, KeyEvent keyEvent0) {
        if(!KeyEventDispatcher.sActionBarFieldsFetched) {
            try {
                KeyEventDispatcher.sActionBarOnMenuKeyMethod = actionBar0.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
            }
            catch(NoSuchMethodException unused_ex) {
            }
            KeyEventDispatcher.sActionBarFieldsFetched = true;
        }
        Method method0 = KeyEventDispatcher.sActionBarOnMenuKeyMethod;
        if(method0 != null) {
            try {
                return ((Boolean)method0.invoke(actionBar0, keyEvent0)).booleanValue();
            }
            catch(IllegalAccessException | InvocationTargetException unused_ex) {
            }
        }
        return false;
    }

    private static boolean activitySuperDispatchKeyEventPre28(Activity activity0, KeyEvent keyEvent0) {
        activity0.onUserInteraction();
        Window window0 = activity0.getWindow();
        if(window0.hasFeature(8)) {
            ActionBar actionBar0 = activity0.getActionBar();
            if(keyEvent0.getKeyCode() == 82 && actionBar0 != null && KeyEventDispatcher.actionBarOnMenuKeyEventPre28(actionBar0, keyEvent0)) {
                return true;
            }
        }
        if(window0.superDispatchKeyEvent(keyEvent0)) {
            return true;
        }
        View view0 = window0.getDecorView();
        if(ViewCompat.dispatchUnhandledKeyEventBeforeCallback(view0, keyEvent0)) {
            return true;
        }
        return view0 == null ? keyEvent0.dispatch(activity0, null, activity0) : keyEvent0.dispatch(activity0, view0.getKeyDispatcherState(), activity0);
    }

    private static boolean dialogSuperDispatchKeyEventPre28(Dialog dialog0, KeyEvent keyEvent0) {
        DialogInterface.OnKeyListener dialogInterface$OnKeyListener0 = KeyEventDispatcher.getDialogKeyListenerPre28(dialog0);
        if(dialogInterface$OnKeyListener0 != null && dialogInterface$OnKeyListener0.onKey(dialog0, keyEvent0.getKeyCode(), keyEvent0)) {
            return true;
        }
        Window window0 = dialog0.getWindow();
        if(window0.superDispatchKeyEvent(keyEvent0)) {
            return true;
        }
        View view0 = window0.getDecorView();
        if(ViewCompat.dispatchUnhandledKeyEventBeforeCallback(view0, keyEvent0)) {
            return true;
        }
        return view0 == null ? keyEvent0.dispatch(dialog0, null, dialog0) : keyEvent0.dispatch(dialog0, view0.getKeyDispatcherState(), dialog0);
    }

    public static boolean dispatchBeforeHierarchy(@NonNull View view0, @NonNull KeyEvent keyEvent0) {
        return ViewCompat.dispatchUnhandledKeyEventBeforeHierarchy(view0, keyEvent0);
    }

    public static boolean dispatchKeyEvent(@NonNull Component keyEventDispatcher$Component0, @Nullable View view0, @Nullable Window.Callback window$Callback0, @NonNull KeyEvent keyEvent0) {
        if(keyEventDispatcher$Component0 == null) {
            return false;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            return keyEventDispatcher$Component0.superDispatchKeyEvent(keyEvent0);
        }
        if(window$Callback0 instanceof Activity) {
            return KeyEventDispatcher.activitySuperDispatchKeyEventPre28(((Activity)window$Callback0), keyEvent0);
        }
        return window$Callback0 instanceof Dialog ? KeyEventDispatcher.dialogSuperDispatchKeyEventPre28(((Dialog)window$Callback0), keyEvent0) : view0 != null && ViewCompat.dispatchUnhandledKeyEventBeforeCallback(view0, keyEvent0) || keyEventDispatcher$Component0.superDispatchKeyEvent(keyEvent0);
    }

    private static DialogInterface.OnKeyListener getDialogKeyListenerPre28(Dialog dialog0) {
        if(!KeyEventDispatcher.sDialogFieldsFetched) {
            try {
                KeyEventDispatcher.sDialogKeyListenerField = Dialog.class.getDeclaredField("mOnKeyListener");
                KeyEventDispatcher.sDialogKeyListenerField.setAccessible(true);
            }
            catch(NoSuchFieldException unused_ex) {
            }
            KeyEventDispatcher.sDialogFieldsFetched = true;
        }
        Field field0 = KeyEventDispatcher.sDialogKeyListenerField;
        if(field0 != null) {
            try {
                return (DialogInterface.OnKeyListener)field0.get(dialog0);
            }
            catch(IllegalAccessException unused_ex) {
            }
        }
        return null;
    }
}

