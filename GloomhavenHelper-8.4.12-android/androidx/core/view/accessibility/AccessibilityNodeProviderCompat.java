package androidx.core.view.accessibility;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.List;

public class AccessibilityNodeProviderCompat {
    @RequiresApi(16)
    static class AccessibilityNodeProviderApi16 extends AccessibilityNodeProvider {
        final AccessibilityNodeProviderCompat mCompat;

        AccessibilityNodeProviderApi16(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0) {
            this.mCompat = accessibilityNodeProviderCompat0;
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo createAccessibilityNodeInfo(int v) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.mCompat.createAccessibilityNodeInfo(v);
            return accessibilityNodeInfoCompat0 == null ? null : accessibilityNodeInfoCompat0.unwrap();
        }

        // 去混淆评级： 低(20)
        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public List findAccessibilityNodeInfosByText(String s, int v) {
            return null;
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public boolean performAction(int v, int v1, Bundle bundle0) {
            return this.mCompat.performAction(v, v1, bundle0);
        }
    }

    @RequiresApi(19)
    static class AccessibilityNodeProviderApi19 extends AccessibilityNodeProviderApi16 {
        AccessibilityNodeProviderApi19(AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0) {
            super(accessibilityNodeProviderCompat0);
        }

        @Override  // android.view.accessibility.AccessibilityNodeProvider
        public AccessibilityNodeInfo findFocus(int v) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = this.mCompat.findFocus(v);
            return accessibilityNodeInfoCompat0 == null ? null : accessibilityNodeInfoCompat0.unwrap();
        }
    }

    public static final int HOST_VIEW_ID = -1;
    private final Object mProvider;

    public AccessibilityNodeProviderCompat() {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mProvider = new AccessibilityNodeProviderApi19(this);
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            this.mProvider = new AccessibilityNodeProviderApi16(this);
            return;
        }
        this.mProvider = null;
    }

    public AccessibilityNodeProviderCompat(Object object0) {
        this.mProvider = object0;
    }

    @Nullable
    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int v) {
        return null;
    }

    @Nullable
    public List findAccessibilityNodeInfosByText(String s, int v) [...] // Inlined contents

    @Nullable
    public AccessibilityNodeInfoCompat findFocus(int v) {
        return null;
    }

    public Object getProvider() {
        return this.mProvider;
    }

    public boolean performAction(int v, int v1, Bundle bundle0) {
        return false;
    }
}

