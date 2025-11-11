package androidx.core.view;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View.AccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;

public class AccessibilityDelegateCompat {
    static final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {
        private final AccessibilityDelegateCompat mCompat;

        AccessibilityDelegateAdapter(AccessibilityDelegateCompat accessibilityDelegateCompat0) {
            this.mCompat = accessibilityDelegateCompat0;
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            return this.mCompat.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        @RequiresApi(16)
        public AccessibilityNodeProvider getAccessibilityNodeProvider(View view0) {
            AccessibilityNodeProviderCompat accessibilityNodeProviderCompat0 = this.mCompat.getAccessibilityNodeProvider(view0);
            return accessibilityNodeProviderCompat0 == null ? null : ((AccessibilityNodeProvider)accessibilityNodeProviderCompat0.getProvider());
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfo accessibilityNodeInfo0) {
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            this.mCompat.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
            return this.mCompat.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
            return this.mCompat.performAccessibilityAction(view0, v, bundle0);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void sendAccessibilityEvent(View view0, int v) {
            this.mCompat.sendAccessibilityEvent(view0, v);
        }

        @Override  // android.view.View$AccessibilityDelegate
        public void sendAccessibilityEventUnchecked(View view0, AccessibilityEvent accessibilityEvent0) {
            this.mCompat.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
        }
    }

    private static final View.AccessibilityDelegate DEFAULT_DELEGATE;
    private final View.AccessibilityDelegate mBridge;

    static {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    }

    public AccessibilityDelegateCompat() {
        this.mBridge = new AccessibilityDelegateAdapter(this);
    }

    public boolean dispatchPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        return AccessibilityDelegateCompat.DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(view0, accessibilityEvent0);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view0) {
        if(Build.VERSION.SDK_INT >= 16) {
            AccessibilityNodeProvider accessibilityNodeProvider0 = AccessibilityDelegateCompat.DEFAULT_DELEGATE.getAccessibilityNodeProvider(view0);
            return accessibilityNodeProvider0 == null ? null : new AccessibilityNodeProviderCompat(accessibilityNodeProvider0);
        }
        return null;
    }

    View.AccessibilityDelegate getBridge() {
        return this.mBridge;
    }

    public void onInitializeAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onInitializeAccessibilityEvent(view0, accessibilityEvent0);
    }

    public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        AccessibilityNodeInfo accessibilityNodeInfo0 = accessibilityNodeInfoCompat0.unwrap();
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfo0);
    }

    public void onPopulateAccessibilityEvent(View view0, AccessibilityEvent accessibilityEvent0) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.onPopulateAccessibilityEvent(view0, accessibilityEvent0);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup0, View view0, AccessibilityEvent accessibilityEvent0) {
        return AccessibilityDelegateCompat.DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(viewGroup0, view0, accessibilityEvent0);
    }

    public boolean performAccessibilityAction(View view0, int v, Bundle bundle0) {
        return Build.VERSION.SDK_INT < 16 ? false : AccessibilityDelegateCompat.DEFAULT_DELEGATE.performAccessibilityAction(view0, v, bundle0);
    }

    public void sendAccessibilityEvent(View view0, int v) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(view0, v);
    }

    public void sendAccessibilityEventUnchecked(View view0, AccessibilityEvent accessibilityEvent0) {
        AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(view0, accessibilityEvent0);
    }
}

