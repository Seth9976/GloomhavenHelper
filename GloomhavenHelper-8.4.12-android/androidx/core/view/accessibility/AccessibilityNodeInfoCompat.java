package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction;
import android.view.accessibility.AccessibilityNodeInfo.CollectionInfo;
import android.view.accessibility.AccessibilityNodeInfo.CollectionItemInfo;
import android.view.accessibility.AccessibilityNodeInfo.RangeInfo;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccessibilityNodeInfoCompat {
    public static class AccessibilityActionCompat {
        public static final AccessibilityActionCompat ACTION_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_ACCESSIBILITY_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_FOCUS;
        public static final AccessibilityActionCompat ACTION_CLEAR_SELECTION;
        public static final AccessibilityActionCompat ACTION_CLICK;
        public static final AccessibilityActionCompat ACTION_COLLAPSE;
        public static final AccessibilityActionCompat ACTION_CONTEXT_CLICK;
        public static final AccessibilityActionCompat ACTION_COPY;
        public static final AccessibilityActionCompat ACTION_CUT;
        public static final AccessibilityActionCompat ACTION_DISMISS;
        public static final AccessibilityActionCompat ACTION_EXPAND;
        public static final AccessibilityActionCompat ACTION_FOCUS;
        public static final AccessibilityActionCompat ACTION_HIDE_TOOLTIP;
        public static final AccessibilityActionCompat ACTION_LONG_CLICK;
        public static final AccessibilityActionCompat ACTION_MOVE_WINDOW;
        public static final AccessibilityActionCompat ACTION_NEXT_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_NEXT_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_PASTE;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY;
        public static final AccessibilityActionCompat ACTION_PREVIOUS_HTML_ELEMENT;
        public static final AccessibilityActionCompat ACTION_SCROLL_BACKWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_DOWN;
        public static final AccessibilityActionCompat ACTION_SCROLL_FORWARD;
        public static final AccessibilityActionCompat ACTION_SCROLL_LEFT;
        public static final AccessibilityActionCompat ACTION_SCROLL_RIGHT;
        public static final AccessibilityActionCompat ACTION_SCROLL_TO_POSITION;
        public static final AccessibilityActionCompat ACTION_SCROLL_UP;
        public static final AccessibilityActionCompat ACTION_SELECT;
        public static final AccessibilityActionCompat ACTION_SET_PROGRESS;
        public static final AccessibilityActionCompat ACTION_SET_SELECTION;
        public static final AccessibilityActionCompat ACTION_SET_TEXT;
        public static final AccessibilityActionCompat ACTION_SHOW_ON_SCREEN;
        public static final AccessibilityActionCompat ACTION_SHOW_TOOLTIP;
        final Object mAction;

        static {
            AccessibilityNodeInfo.AccessibilityAction accessibilityNodeInfo$AccessibilityAction0 = null;
            AccessibilityActionCompat.ACTION_FOCUS = new AccessibilityActionCompat(1, null);
            AccessibilityActionCompat.ACTION_CLEAR_FOCUS = new AccessibilityActionCompat(2, null);
            AccessibilityActionCompat.ACTION_SELECT = new AccessibilityActionCompat(4, null);
            AccessibilityActionCompat.ACTION_CLEAR_SELECTION = new AccessibilityActionCompat(8, null);
            AccessibilityActionCompat.ACTION_CLICK = new AccessibilityActionCompat(16, null);
            AccessibilityActionCompat.ACTION_LONG_CLICK = new AccessibilityActionCompat(0x20, null);
            AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x40, null);
            AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS = new AccessibilityActionCompat(0x80, null);
            AccessibilityActionCompat.ACTION_NEXT_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x100, null);
            AccessibilityActionCompat.ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = new AccessibilityActionCompat(0x200, null);
            AccessibilityActionCompat.ACTION_NEXT_HTML_ELEMENT = new AccessibilityActionCompat(0x400, null);
            AccessibilityActionCompat.ACTION_PREVIOUS_HTML_ELEMENT = new AccessibilityActionCompat(0x800, null);
            AccessibilityActionCompat.ACTION_SCROLL_FORWARD = new AccessibilityActionCompat(0x1000, null);
            AccessibilityActionCompat.ACTION_SCROLL_BACKWARD = new AccessibilityActionCompat(0x2000, null);
            AccessibilityActionCompat.ACTION_COPY = new AccessibilityActionCompat(0x4000, null);
            AccessibilityActionCompat.ACTION_PASTE = new AccessibilityActionCompat(0x8000, null);
            AccessibilityActionCompat.ACTION_CUT = new AccessibilityActionCompat(0x10000, null);
            AccessibilityActionCompat.ACTION_SET_SELECTION = new AccessibilityActionCompat(0x20000, null);
            AccessibilityActionCompat.ACTION_EXPAND = new AccessibilityActionCompat(0x40000, null);
            AccessibilityActionCompat.ACTION_COLLAPSE = new AccessibilityActionCompat(0x80000, null);
            AccessibilityActionCompat.ACTION_DISMISS = new AccessibilityActionCompat(0x100000, null);
            AccessibilityActionCompat.ACTION_SET_TEXT = new AccessibilityActionCompat(0x200000, null);
            AccessibilityActionCompat.ACTION_SHOW_ON_SCREEN = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN));
            AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION));
            AccessibilityActionCompat.ACTION_SCROLL_UP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP));
            AccessibilityActionCompat.ACTION_SCROLL_LEFT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT));
            AccessibilityActionCompat.ACTION_SCROLL_DOWN = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN));
            AccessibilityActionCompat.ACTION_SCROLL_RIGHT = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT));
            AccessibilityActionCompat.ACTION_CONTEXT_CLICK = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 23 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK));
            AccessibilityActionCompat.ACTION_SET_PROGRESS = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 24 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS));
            AccessibilityActionCompat.ACTION_MOVE_WINDOW = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 26 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW));
            AccessibilityActionCompat.ACTION_SHOW_TOOLTIP = new AccessibilityActionCompat((Build.VERSION.SDK_INT < 28 ? null : AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP));
            if(Build.VERSION.SDK_INT >= 28) {
                accessibilityNodeInfo$AccessibilityAction0 = AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP;
            }
            AccessibilityActionCompat.ACTION_HIDE_TOOLTIP = new AccessibilityActionCompat(accessibilityNodeInfo$AccessibilityAction0);
        }

        public AccessibilityActionCompat(int v, CharSequence charSequence0) {
            this((Build.VERSION.SDK_INT < 21 ? null : new AccessibilityNodeInfo.AccessibilityAction(v, charSequence0)));
        }

        AccessibilityActionCompat(Object object0) {
            this.mAction = object0;
        }

        public int getId() {
            return Build.VERSION.SDK_INT < 21 ? 0 : ((AccessibilityNodeInfo.AccessibilityAction)this.mAction).getId();
        }

        public CharSequence getLabel() {
            return Build.VERSION.SDK_INT < 21 ? null : ((AccessibilityNodeInfo.AccessibilityAction)this.mAction).getLabel();
        }
    }

    public static class CollectionInfoCompat {
        public static final int SELECTION_MODE_MULTIPLE = 2;
        public static final int SELECTION_MODE_NONE = 0;
        public static final int SELECTION_MODE_SINGLE = 1;
        final Object mInfo;

        CollectionInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public int getColumnCount() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getColumnCount();
        }

        public int getRowCount() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getRowCount();
        }

        public int getSelectionMode() {
            return Build.VERSION.SDK_INT < 21 ? 0 : ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).getSelectionMode();
        }

        public boolean isHierarchical() {
            return Build.VERSION.SDK_INT < 19 ? false : ((AccessibilityNodeInfo.CollectionInfo)this.mInfo).isHierarchical();
        }

        public static CollectionInfoCompat obtain(int v, int v1, boolean z) {
            return Build.VERSION.SDK_INT < 19 ? new CollectionInfoCompat(null) : new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(v, v1, z));
        }

        public static CollectionInfoCompat obtain(int v, int v1, boolean z, int v2) {
            if(Build.VERSION.SDK_INT >= 21) {
                return new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(v, v1, z, v2));
            }
            return Build.VERSION.SDK_INT < 19 ? new CollectionInfoCompat(null) : new CollectionInfoCompat(AccessibilityNodeInfo.CollectionInfo.obtain(v, v1, z));
        }
    }

    public static class CollectionItemInfoCompat {
        final Object mInfo;

        CollectionItemInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public int getColumnIndex() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getColumnIndex();
        }

        public int getColumnSpan() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getColumnSpan();
        }

        public int getRowIndex() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getRowIndex();
        }

        public int getRowSpan() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).getRowSpan();
        }

        public boolean isHeading() {
            return Build.VERSION.SDK_INT < 19 ? false : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).isHeading();
        }

        public boolean isSelected() {
            return Build.VERSION.SDK_INT < 21 ? false : ((AccessibilityNodeInfo.CollectionItemInfo)this.mInfo).isSelected();
        }

        public static CollectionItemInfoCompat obtain(int v, int v1, int v2, int v3, boolean z) {
            return Build.VERSION.SDK_INT < 19 ? new CollectionItemInfoCompat(null) : new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(v, v1, v2, v3, z));
        }

        public static CollectionItemInfoCompat obtain(int v, int v1, int v2, int v3, boolean z, boolean z1) {
            if(Build.VERSION.SDK_INT >= 21) {
                return new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(v, v1, v2, v3, z, z1));
            }
            return Build.VERSION.SDK_INT < 19 ? new CollectionItemInfoCompat(null) : new CollectionItemInfoCompat(AccessibilityNodeInfo.CollectionItemInfo.obtain(v, v1, v2, v3, z));
        }
    }

    public static class RangeInfoCompat {
        public static final int RANGE_TYPE_FLOAT = 1;
        public static final int RANGE_TYPE_INT = 0;
        public static final int RANGE_TYPE_PERCENT = 2;
        final Object mInfo;

        RangeInfoCompat(Object object0) {
            this.mInfo = object0;
        }

        public float getCurrent() {
            return Build.VERSION.SDK_INT < 19 ? 0.0f : ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getCurrent();
        }

        public float getMax() {
            return Build.VERSION.SDK_INT < 19 ? 0.0f : ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getMax();
        }

        public float getMin() {
            return Build.VERSION.SDK_INT < 19 ? 0.0f : ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getMin();
        }

        public int getType() {
            return Build.VERSION.SDK_INT < 19 ? 0 : ((AccessibilityNodeInfo.RangeInfo)this.mInfo).getType();
        }

        public static RangeInfoCompat obtain(int v, float f, float f1, float f2) {
            return Build.VERSION.SDK_INT < 19 ? new RangeInfoCompat(null) : new RangeInfoCompat(AccessibilityNodeInfo.RangeInfo.obtain(v, f, f1, f2));
        }
    }

    public static final int ACTION_ACCESSIBILITY_FOCUS = 0x40;
    public static final String ACTION_ARGUMENT_COLUMN_INT = "android.view.accessibility.action.ARGUMENT_COLUMN_INT";
    public static final String ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN";
    public static final String ACTION_ARGUMENT_HTML_ELEMENT_STRING = "ACTION_ARGUMENT_HTML_ELEMENT_STRING";
    public static final String ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_X = "ACTION_ARGUMENT_MOVE_WINDOW_X";
    public static final String ACTION_ARGUMENT_MOVE_WINDOW_Y = "ACTION_ARGUMENT_MOVE_WINDOW_Y";
    public static final String ACTION_ARGUMENT_PROGRESS_VALUE = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE";
    public static final String ACTION_ARGUMENT_ROW_INT = "android.view.accessibility.action.ARGUMENT_ROW_INT";
    public static final String ACTION_ARGUMENT_SELECTION_END_INT = "ACTION_ARGUMENT_SELECTION_END_INT";
    public static final String ACTION_ARGUMENT_SELECTION_START_INT = "ACTION_ARGUMENT_SELECTION_START_INT";
    public static final String ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE";
    public static final int ACTION_CLEAR_ACCESSIBILITY_FOCUS = 0x80;
    public static final int ACTION_CLEAR_FOCUS = 2;
    public static final int ACTION_CLEAR_SELECTION = 8;
    public static final int ACTION_CLICK = 16;
    public static final int ACTION_COLLAPSE = 0x80000;
    public static final int ACTION_COPY = 0x4000;
    public static final int ACTION_CUT = 0x10000;
    public static final int ACTION_DISMISS = 0x100000;
    public static final int ACTION_EXPAND = 0x40000;
    public static final int ACTION_FOCUS = 1;
    public static final int ACTION_LONG_CLICK = 0x20;
    public static final int ACTION_NEXT_AT_MOVEMENT_GRANULARITY = 0x100;
    public static final int ACTION_NEXT_HTML_ELEMENT = 0x400;
    public static final int ACTION_PASTE = 0x8000;
    public static final int ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY = 0x200;
    public static final int ACTION_PREVIOUS_HTML_ELEMENT = 0x800;
    public static final int ACTION_SCROLL_BACKWARD = 0x2000;
    public static final int ACTION_SCROLL_FORWARD = 0x1000;
    public static final int ACTION_SELECT = 4;
    public static final int ACTION_SET_SELECTION = 0x20000;
    public static final int ACTION_SET_TEXT = 0x200000;
    private static final int BOOLEAN_PROPERTY_IS_HEADING = 2;
    private static final int BOOLEAN_PROPERTY_IS_SHOWING_HINT = 4;
    private static final String BOOLEAN_PROPERTY_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY";
    private static final int BOOLEAN_PROPERTY_SCREEN_READER_FOCUSABLE = 1;
    public static final int FOCUS_ACCESSIBILITY = 2;
    public static final int FOCUS_INPUT = 1;
    private static final String HINT_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY";
    public static final int MOVEMENT_GRANULARITY_CHARACTER = 1;
    public static final int MOVEMENT_GRANULARITY_LINE = 4;
    public static final int MOVEMENT_GRANULARITY_PAGE = 16;
    public static final int MOVEMENT_GRANULARITY_PARAGRAPH = 8;
    public static final int MOVEMENT_GRANULARITY_WORD = 2;
    private static final String PANE_TITLE_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY";
    private static final String ROLE_DESCRIPTION_KEY = "AccessibilityNodeInfo.roleDescription";
    private static final String TOOLTIP_TEXT_KEY = "androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY";
    private final AccessibilityNodeInfo mInfo;
    @RestrictTo({Scope.LIBRARY_GROUP})
    public int mParentVirtualDescendantId;

    private AccessibilityNodeInfoCompat(AccessibilityNodeInfo accessibilityNodeInfo0) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo = accessibilityNodeInfo0;
    }

    @Deprecated
    public AccessibilityNodeInfoCompat(Object object0) {
        this.mParentVirtualDescendantId = -1;
        this.mInfo = (AccessibilityNodeInfo)object0;
    }

    public void addAction(int v) {
        this.mInfo.addAction(v);
    }

    public void addAction(AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0) {
        if(Build.VERSION.SDK_INT >= 21) {
            this.mInfo.addAction(((AccessibilityNodeInfo.AccessibilityAction)accessibilityNodeInfoCompat$AccessibilityActionCompat0.mAction));
        }
    }

    public void addChild(View view0) {
        this.mInfo.addChild(view0);
    }

    public void addChild(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.addChild(view0, v);
        }
    }

    public boolean canOpenPopup() {
        return Build.VERSION.SDK_INT < 19 ? false : this.mInfo.canOpenPopup();
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        return this.mInfo == null ? ((AccessibilityNodeInfoCompat)object0).mInfo == null : this.mInfo.equals(((AccessibilityNodeInfoCompat)object0).mInfo);
    }

    public List findAccessibilityNodeInfosByText(String s) {
        List list0 = new ArrayList();
        List list1 = this.mInfo.findAccessibilityNodeInfosByText(s);
        int v = list1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            list0.add(AccessibilityNodeInfoCompat.wrap(((AccessibilityNodeInfo)list1.get(v1))));
        }
        return list0;
    }

    public List findAccessibilityNodeInfosByViewId(String s) {
        if(Build.VERSION.SDK_INT >= 18) {
            List list0 = this.mInfo.findAccessibilityNodeInfosByViewId(s);
            List list1 = new ArrayList();
            for(Object object0: list0) {
                list1.add(AccessibilityNodeInfoCompat.wrap(((AccessibilityNodeInfo)object0)));
            }
            return list1;
        }
        return Collections.emptyList();
    }

    public AccessibilityNodeInfoCompat findFocus(int v) {
        return Build.VERSION.SDK_INT < 16 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.findFocus(v));
    }

    public AccessibilityNodeInfoCompat focusSearch(int v) {
        return Build.VERSION.SDK_INT < 16 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.focusSearch(v));
    }

    public List getActionList() {
        List list0 = Build.VERSION.SDK_INT < 21 ? null : this.mInfo.getActionList();
        if(list0 != null) {
            List list1 = new ArrayList();
            int v = list0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                list1.add(new AccessibilityActionCompat(list0.get(v1)));
            }
            return list1;
        }
        return Collections.emptyList();
    }

    private static String getActionSymbolicName(int v) {
        switch(v) {
            case 1: {
                return "ACTION_FOCUS";
            }
            case 2: {
                return "ACTION_CLEAR_FOCUS";
            }
            case 4: {
                return "ACTION_SELECT";
            }
            case 8: {
                return "ACTION_CLEAR_SELECTION";
            }
            case 16: {
                return "ACTION_CLICK";
            }
            case 0x20: {
                return "ACTION_LONG_CLICK";
            }
            case 0x40: {
                return "ACTION_ACCESSIBILITY_FOCUS";
            }
            case 0x80: {
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            }
            case 0x100: {
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            }
            case 0x200: {
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            }
            case 0x400: {
                return "ACTION_NEXT_HTML_ELEMENT";
            }
            case 0x800: {
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            }
            case 0x1000: {
                return "ACTION_SCROLL_FORWARD";
            }
            case 0x2000: {
                return "ACTION_SCROLL_BACKWARD";
            }
            case 0x4000: {
                return "ACTION_COPY";
            }
            case 0x8000: {
                return "ACTION_PASTE";
            }
            case 0x10000: {
                return "ACTION_CUT";
            }
            case 0x20000: {
                return "ACTION_SET_SELECTION";
            }
            default: {
                return "ACTION_UNKNOWN";
            }
        }
    }

    public int getActions() {
        return this.mInfo.getActions();
    }

    private boolean getBooleanProperty(int v) {
        Bundle bundle0 = this.getExtras();
        return bundle0 == null ? false : (bundle0.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & v) == v;
    }

    public void getBoundsInParent(Rect rect0) {
        this.mInfo.getBoundsInParent(rect0);
    }

    public void getBoundsInScreen(Rect rect0) {
        this.mInfo.getBoundsInScreen(rect0);
    }

    public AccessibilityNodeInfoCompat getChild(int v) {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getChild(v));
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public CharSequence getClassName() {
        return this.mInfo.getClassName();
    }

    public CollectionInfoCompat getCollectionInfo() {
        if(Build.VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfo.CollectionInfo accessibilityNodeInfo$CollectionInfo0 = this.mInfo.getCollectionInfo();
            return accessibilityNodeInfo$CollectionInfo0 == null ? null : new CollectionInfoCompat(accessibilityNodeInfo$CollectionInfo0);
        }
        return null;
    }

    public CollectionItemInfoCompat getCollectionItemInfo() {
        if(Build.VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfo.CollectionItemInfo accessibilityNodeInfo$CollectionItemInfo0 = this.mInfo.getCollectionItemInfo();
            return accessibilityNodeInfo$CollectionItemInfo0 == null ? null : new CollectionItemInfoCompat(accessibilityNodeInfo$CollectionItemInfo0);
        }
        return null;
    }

    public CharSequence getContentDescription() {
        return this.mInfo.getContentDescription();
    }

    public int getDrawingOrder() {
        return Build.VERSION.SDK_INT < 24 ? 0 : this.mInfo.getDrawingOrder();
    }

    public CharSequence getError() {
        return Build.VERSION.SDK_INT < 21 ? null : this.mInfo.getError();
    }

    public Bundle getExtras() {
        return Build.VERSION.SDK_INT < 19 ? new Bundle() : this.mInfo.getExtras();
    }

    @Nullable
    public CharSequence getHintText() {
        if(Build.VERSION.SDK_INT >= 26) {
            return this.mInfo.getHintText();
        }
        return Build.VERSION.SDK_INT < 19 ? null : this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY");
    }

    @Deprecated
    public Object getInfo() {
        return this.mInfo;
    }

    public int getInputType() {
        return Build.VERSION.SDK_INT < 19 ? 0 : this.mInfo.getInputType();
    }

    public AccessibilityNodeInfoCompat getLabelFor() {
        return Build.VERSION.SDK_INT < 17 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getLabelFor());
    }

    public AccessibilityNodeInfoCompat getLabeledBy() {
        return Build.VERSION.SDK_INT < 17 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getLabeledBy());
    }

    public int getLiveRegion() {
        return Build.VERSION.SDK_INT < 19 ? 0 : this.mInfo.getLiveRegion();
    }

    public int getMaxTextLength() {
        return Build.VERSION.SDK_INT < 21 ? -1 : this.mInfo.getMaxTextLength();
    }

    public int getMovementGranularities() {
        return Build.VERSION.SDK_INT < 16 ? 0 : this.mInfo.getMovementGranularities();
    }

    public CharSequence getPackageName() {
        return this.mInfo.getPackageName();
    }

    @Nullable
    public CharSequence getPaneTitle() {
        if(Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.getPaneTitle();
        }
        return Build.VERSION.SDK_INT < 19 ? null : this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY");
    }

    public AccessibilityNodeInfoCompat getParent() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getParent());
    }

    public RangeInfoCompat getRangeInfo() {
        if(Build.VERSION.SDK_INT >= 19) {
            AccessibilityNodeInfo.RangeInfo accessibilityNodeInfo$RangeInfo0 = this.mInfo.getRangeInfo();
            return accessibilityNodeInfo$RangeInfo0 == null ? null : new RangeInfoCompat(accessibilityNodeInfo$RangeInfo0);
        }
        return null;
    }

    @Nullable
    public CharSequence getRoleDescription() {
        return Build.VERSION.SDK_INT < 19 ? null : this.mInfo.getExtras().getCharSequence("AccessibilityNodeInfo.roleDescription");
    }

    public CharSequence getText() {
        return this.mInfo.getText();
    }

    public int getTextSelectionEnd() {
        return Build.VERSION.SDK_INT < 18 ? -1 : this.mInfo.getTextSelectionEnd();
    }

    public int getTextSelectionStart() {
        return Build.VERSION.SDK_INT < 18 ? -1 : this.mInfo.getTextSelectionStart();
    }

    @Nullable
    public CharSequence getTooltipText() {
        if(Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.getTooltipText();
        }
        return Build.VERSION.SDK_INT < 19 ? null : this.mInfo.getExtras().getCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY");
    }

    public AccessibilityNodeInfoCompat getTraversalAfter() {
        return Build.VERSION.SDK_INT < 22 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getTraversalAfter());
    }

    public AccessibilityNodeInfoCompat getTraversalBefore() {
        return Build.VERSION.SDK_INT < 22 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getTraversalBefore());
    }

    public String getViewIdResourceName() {
        return Build.VERSION.SDK_INT < 18 ? null : this.mInfo.getViewIdResourceName();
    }

    public AccessibilityWindowInfoCompat getWindow() {
        return Build.VERSION.SDK_INT < 21 ? null : AccessibilityWindowInfoCompat.wrapNonNullInstance(this.mInfo.getWindow());
    }

    public int getWindowId() {
        return this.mInfo.getWindowId();
    }

    @Override
    public int hashCode() {
        return this.mInfo == null ? 0 : this.mInfo.hashCode();
    }

    public boolean isAccessibilityFocused() {
        return Build.VERSION.SDK_INT < 16 ? false : this.mInfo.isAccessibilityFocused();
    }

    public boolean isCheckable() {
        return this.mInfo.isCheckable();
    }

    public boolean isChecked() {
        return this.mInfo.isChecked();
    }

    public boolean isClickable() {
        return this.mInfo.isClickable();
    }

    public boolean isContentInvalid() {
        return Build.VERSION.SDK_INT < 19 ? false : this.mInfo.isContentInvalid();
    }

    public boolean isContextClickable() {
        return Build.VERSION.SDK_INT < 23 ? false : this.mInfo.isContextClickable();
    }

    public boolean isDismissable() {
        return Build.VERSION.SDK_INT < 19 ? false : this.mInfo.isDismissable();
    }

    public boolean isEditable() {
        return Build.VERSION.SDK_INT < 18 ? false : this.mInfo.isEditable();
    }

    public boolean isEnabled() {
        return this.mInfo.isEnabled();
    }

    public boolean isFocusable() {
        return this.mInfo.isFocusable();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isHeading() {
        if(Build.VERSION.SDK_INT >= 28) {
            return this.mInfo.isHeading();
        }
        if(this.getBooleanProperty(2)) {
            return true;
        }
        CollectionItemInfoCompat accessibilityNodeInfoCompat$CollectionItemInfoCompat0 = this.getCollectionItemInfo();
        return accessibilityNodeInfoCompat$CollectionItemInfoCompat0 != null && accessibilityNodeInfoCompat$CollectionItemInfoCompat0.isHeading();
    }

    public boolean isImportantForAccessibility() {
        return Build.VERSION.SDK_INT < 24 ? true : this.mInfo.isImportantForAccessibility();
    }

    public boolean isLongClickable() {
        return this.mInfo.isLongClickable();
    }

    public boolean isMultiLine() {
        return Build.VERSION.SDK_INT < 19 ? false : this.mInfo.isMultiLine();
    }

    public boolean isPassword() {
        return this.mInfo.isPassword();
    }

    public boolean isScreenReaderFocusable() {
        return Build.VERSION.SDK_INT < 28 ? this.getBooleanProperty(1) : this.mInfo.isScreenReaderFocusable();
    }

    public boolean isScrollable() {
        return this.mInfo.isScrollable();
    }

    public boolean isSelected() {
        return this.mInfo.isSelected();
    }

    public boolean isShowingHintText() {
        return Build.VERSION.SDK_INT < 26 ? this.getBooleanProperty(4) : this.mInfo.isShowingHintText();
    }

    public boolean isVisibleToUser() {
        return Build.VERSION.SDK_INT < 16 ? false : this.mInfo.isVisibleToUser();
    }

    public static AccessibilityNodeInfoCompat obtain() {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain());
    }

    public static AccessibilityNodeInfoCompat obtain(View view0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(view0));
    }

    public static AccessibilityNodeInfoCompat obtain(View view0, int v) {
        return Build.VERSION.SDK_INT < 16 ? null : AccessibilityNodeInfoCompat.wrapNonNullInstance(AccessibilityNodeInfo.obtain(view0, v));
    }

    public static AccessibilityNodeInfoCompat obtain(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
        return AccessibilityNodeInfoCompat.wrap(AccessibilityNodeInfo.obtain(accessibilityNodeInfoCompat0.mInfo));
    }

    public boolean performAction(int v) {
        return this.mInfo.performAction(v);
    }

    public boolean performAction(int v, Bundle bundle0) {
        return Build.VERSION.SDK_INT < 16 ? false : this.mInfo.performAction(v, bundle0);
    }

    public void recycle() {
        this.mInfo.recycle();
    }

    public boolean refresh() {
        return Build.VERSION.SDK_INT < 18 ? false : this.mInfo.refresh();
    }

    public boolean removeAction(AccessibilityActionCompat accessibilityNodeInfoCompat$AccessibilityActionCompat0) {
        return Build.VERSION.SDK_INT < 21 ? false : this.mInfo.removeAction(((AccessibilityNodeInfo.AccessibilityAction)accessibilityNodeInfoCompat$AccessibilityActionCompat0.mAction));
    }

    public boolean removeChild(View view0) {
        return Build.VERSION.SDK_INT < 21 ? false : this.mInfo.removeChild(view0);
    }

    public boolean removeChild(View view0, int v) {
        return Build.VERSION.SDK_INT < 21 ? false : this.mInfo.removeChild(view0, v);
    }

    public void setAccessibilityFocused(boolean z) {
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setAccessibilityFocused(z);
        }
    }

    private void setBooleanProperty(int v, boolean z) {
        Bundle bundle0 = this.getExtras();
        if(bundle0 != null) {
            int v1 = bundle0.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & ~v;
            if(!z) {
                v = 0;
            }
            bundle0.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", v | v1);
        }
    }

    public void setBoundsInParent(Rect rect0) {
        this.mInfo.setBoundsInParent(rect0);
    }

    public void setBoundsInScreen(Rect rect0) {
        this.mInfo.setBoundsInScreen(rect0);
    }

    public void setCanOpenPopup(boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCanOpenPopup(z);
        }
    }

    public void setCheckable(boolean z) {
        this.mInfo.setCheckable(z);
    }

    public void setChecked(boolean z) {
        this.mInfo.setChecked(z);
    }

    public void setClassName(CharSequence charSequence0) {
        this.mInfo.setClassName(charSequence0);
    }

    public void setClickable(boolean z) {
        this.mInfo.setClickable(z);
    }

    public void setCollectionInfo(Object object0) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionInfo((object0 == null ? null : ((AccessibilityNodeInfo.CollectionInfo)((CollectionInfoCompat)object0).mInfo)));
        }
    }

    public void setCollectionItemInfo(Object object0) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setCollectionItemInfo((object0 == null ? null : ((AccessibilityNodeInfo.CollectionItemInfo)((CollectionItemInfoCompat)object0).mInfo)));
        }
    }

    public void setContentDescription(CharSequence charSequence0) {
        this.mInfo.setContentDescription(charSequence0);
    }

    public void setContentInvalid(boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setContentInvalid(z);
        }
    }

    public void setContextClickable(boolean z) {
        if(Build.VERSION.SDK_INT >= 23) {
            this.mInfo.setContextClickable(z);
        }
    }

    public void setDismissable(boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setDismissable(z);
        }
    }

    public void setDrawingOrder(int v) {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setDrawingOrder(v);
        }
    }

    public void setEditable(boolean z) {
        if(Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setEditable(z);
        }
    }

    public void setEnabled(boolean z) {
        this.mInfo.setEnabled(z);
    }

    public void setError(CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setError(charSequence0);
        }
    }

    public void setFocusable(boolean z) {
        this.mInfo.setFocusable(z);
    }

    public void setFocused(boolean z) {
        this.mInfo.setFocused(z);
    }

    public void setHeading(boolean z) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setHeading(z);
            return;
        }
        this.setBooleanProperty(2, z);
    }

    public void setHintText(@Nullable CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setHintText(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence0);
        }
    }

    public void setImportantForAccessibility(boolean z) {
        if(Build.VERSION.SDK_INT >= 24) {
            this.mInfo.setImportantForAccessibility(z);
        }
    }

    public void setInputType(int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setInputType(v);
        }
    }

    public void setLabelFor(View view0) {
        if(Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view0);
        }
    }

    public void setLabelFor(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabelFor(view0, v);
        }
    }

    public void setLabeledBy(View view0) {
        if(Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view0);
        }
    }

    public void setLabeledBy(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 17) {
            this.mInfo.setLabeledBy(view0, v);
        }
    }

    public void setLiveRegion(int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setLiveRegion(v);
        }
    }

    public void setLongClickable(boolean z) {
        this.mInfo.setLongClickable(z);
    }

    public void setMaxTextLength(int v) {
        if(Build.VERSION.SDK_INT >= 21) {
            this.mInfo.setMaxTextLength(v);
        }
    }

    public void setMovementGranularities(int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setMovementGranularities(v);
        }
    }

    public void setMultiLine(boolean z) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setMultiLine(z);
        }
    }

    public void setPackageName(CharSequence charSequence0) {
        this.mInfo.setPackageName(charSequence0);
    }

    public void setPaneTitle(@Nullable CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setPaneTitle(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence0);
        }
    }

    public void setParent(View view0) {
        this.mInfo.setParent(view0);
    }

    public void setParent(View view0, int v) {
        this.mParentVirtualDescendantId = v;
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setParent(view0, v);
        }
    }

    public void setPassword(boolean z) {
        this.mInfo.setPassword(z);
    }

    public void setRangeInfo(RangeInfoCompat accessibilityNodeInfoCompat$RangeInfoCompat0) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.setRangeInfo(((AccessibilityNodeInfo.RangeInfo)accessibilityNodeInfoCompat$RangeInfoCompat0.mInfo));
        }
    }

    public void setRoleDescription(@Nullable CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence0);
        }
    }

    public void setScreenReaderFocusable(boolean z) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setScreenReaderFocusable(z);
            return;
        }
        this.setBooleanProperty(1, z);
    }

    public void setScrollable(boolean z) {
        this.mInfo.setScrollable(z);
    }

    public void setSelected(boolean z) {
        this.mInfo.setSelected(z);
    }

    public void setShowingHintText(boolean z) {
        if(Build.VERSION.SDK_INT >= 26) {
            this.mInfo.setShowingHintText(z);
            return;
        }
        this.setBooleanProperty(4, z);
    }

    public void setSource(View view0) {
        this.mInfo.setSource(view0);
    }

    public void setSource(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setSource(view0, v);
        }
    }

    public void setText(CharSequence charSequence0) {
        this.mInfo.setText(charSequence0);
    }

    public void setTextSelection(int v, int v1) {
        if(Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setTextSelection(v, v1);
        }
    }

    public void setTooltipText(@Nullable CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 28) {
            this.mInfo.setTooltipText(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 19) {
            this.mInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.TOOLTIP_TEXT_KEY", charSequence0);
        }
    }

    public void setTraversalAfter(View view0) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view0);
        }
    }

    public void setTraversalAfter(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalAfter(view0, v);
        }
    }

    public void setTraversalBefore(View view0) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view0);
        }
    }

    public void setTraversalBefore(View view0, int v) {
        if(Build.VERSION.SDK_INT >= 22) {
            this.mInfo.setTraversalBefore(view0, v);
        }
    }

    public void setViewIdResourceName(String s) {
        if(Build.VERSION.SDK_INT >= 18) {
            this.mInfo.setViewIdResourceName(s);
        }
    }

    public void setVisibleToUser(boolean z) {
        if(Build.VERSION.SDK_INT >= 16) {
            this.mInfo.setVisibleToUser(z);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(super.toString());
        Rect rect0 = new Rect();
        this.getBoundsInParent(rect0);
        stringBuilder0.append("; boundsInParent: " + rect0);
        this.getBoundsInScreen(rect0);
        stringBuilder0.append("; boundsInScreen: " + rect0);
        stringBuilder0.append("; packageName: ");
        stringBuilder0.append(this.getPackageName());
        stringBuilder0.append("; className: ");
        stringBuilder0.append(this.getClassName());
        stringBuilder0.append("; text: ");
        stringBuilder0.append(this.getText());
        stringBuilder0.append("; contentDescription: ");
        stringBuilder0.append(this.getContentDescription());
        stringBuilder0.append("; viewId: ");
        stringBuilder0.append(this.getViewIdResourceName());
        stringBuilder0.append("; checkable: ");
        stringBuilder0.append(this.isCheckable());
        stringBuilder0.append("; checked: ");
        stringBuilder0.append(this.isChecked());
        stringBuilder0.append("; focusable: ");
        stringBuilder0.append(this.isFocusable());
        stringBuilder0.append("; focused: ");
        stringBuilder0.append(this.isFocused());
        stringBuilder0.append("; selected: ");
        stringBuilder0.append(this.isSelected());
        stringBuilder0.append("; clickable: ");
        stringBuilder0.append(this.isClickable());
        stringBuilder0.append("; longClickable: ");
        stringBuilder0.append(this.isLongClickable());
        stringBuilder0.append("; enabled: ");
        stringBuilder0.append(this.isEnabled());
        stringBuilder0.append("; password: ");
        stringBuilder0.append(this.isPassword());
        stringBuilder0.append("; scrollable: " + this.isScrollable());
        stringBuilder0.append("; [");
        int v = this.getActions();
        while(v != 0) {
            int v1 = Integer.numberOfTrailingZeros(v);
            v &= ~(1 << v1);
            stringBuilder0.append(AccessibilityNodeInfoCompat.getActionSymbolicName(1 << v1));
            if(v != 0) {
                stringBuilder0.append(", ");
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    public AccessibilityNodeInfo unwrap() {
        return this.mInfo;
    }

    public static AccessibilityNodeInfoCompat wrap(@NonNull AccessibilityNodeInfo accessibilityNodeInfo0) {
        return new AccessibilityNodeInfoCompat(accessibilityNodeInfo0);
    }

    static AccessibilityNodeInfoCompat wrapNonNullInstance(Object object0) {
        return object0 == null ? null : new AccessibilityNodeInfoCompat(object0);
    }
}

