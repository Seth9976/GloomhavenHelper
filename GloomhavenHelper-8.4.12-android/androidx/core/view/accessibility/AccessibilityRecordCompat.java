package androidx.core.view.accessibility;

import android.os.Build.VERSION;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import androidx.annotation.NonNull;
import java.util.List;

public class AccessibilityRecordCompat {
    private final AccessibilityRecord mRecord;

    @Deprecated
    public AccessibilityRecordCompat(Object object0) {
        this.mRecord = (AccessibilityRecord)object0;
    }

    @Override
    @Deprecated
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
        return this.mRecord == null ? ((AccessibilityRecordCompat)object0).mRecord == null : this.mRecord.equals(((AccessibilityRecordCompat)object0).mRecord);
    }

    @Deprecated
    public int getAddedCount() {
        return this.mRecord.getAddedCount();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.mRecord.getBeforeText();
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.mRecord.getClassName();
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.mRecord.getContentDescription();
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.mRecord.getCurrentItemIndex();
    }

    @Deprecated
    public int getFromIndex() {
        return this.mRecord.getFromIndex();
    }

    @Deprecated
    public Object getImpl() {
        return this.mRecord;
    }

    @Deprecated
    public int getItemCount() {
        return this.mRecord.getItemCount();
    }

    public static int getMaxScrollX(AccessibilityRecord accessibilityRecord0) {
        return Build.VERSION.SDK_INT < 15 ? 0 : accessibilityRecord0.getMaxScrollX();
    }

    @Deprecated
    public int getMaxScrollX() {
        return AccessibilityRecordCompat.getMaxScrollX(this.mRecord);
    }

    public static int getMaxScrollY(AccessibilityRecord accessibilityRecord0) {
        return Build.VERSION.SDK_INT < 15 ? 0 : accessibilityRecord0.getMaxScrollY();
    }

    @Deprecated
    public int getMaxScrollY() {
        return AccessibilityRecordCompat.getMaxScrollY(this.mRecord);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.mRecord.getParcelableData();
    }

    @Deprecated
    public int getRemovedCount() {
        return this.mRecord.getRemovedCount();
    }

    @Deprecated
    public int getScrollX() {
        return this.mRecord.getScrollX();
    }

    @Deprecated
    public int getScrollY() {
        return this.mRecord.getScrollY();
    }

    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mRecord.getSource());
    }

    @Deprecated
    public List getText() {
        return this.mRecord.getText();
    }

    @Deprecated
    public int getToIndex() {
        return this.mRecord.getToIndex();
    }

    @Deprecated
    public int getWindowId() {
        return this.mRecord.getWindowId();
    }

    @Override
    @Deprecated
    public int hashCode() {
        return this.mRecord == null ? 0 : this.mRecord.hashCode();
    }

    @Deprecated
    public boolean isChecked() {
        return this.mRecord.isChecked();
    }

    @Deprecated
    public boolean isEnabled() {
        return this.mRecord.isEnabled();
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.mRecord.isFullScreen();
    }

    @Deprecated
    public boolean isPassword() {
        return this.mRecord.isPassword();
    }

    @Deprecated
    public boolean isScrollable() {
        return this.mRecord.isScrollable();
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat0) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityRecordCompat0.mRecord));
    }

    @Deprecated
    public void recycle() {
        this.mRecord.recycle();
    }

    @Deprecated
    public void setAddedCount(int v) {
        this.mRecord.setAddedCount(v);
    }

    @Deprecated
    public void setBeforeText(CharSequence charSequence0) {
        this.mRecord.setBeforeText(charSequence0);
    }

    @Deprecated
    public void setChecked(boolean z) {
        this.mRecord.setChecked(z);
    }

    @Deprecated
    public void setClassName(CharSequence charSequence0) {
        this.mRecord.setClassName(charSequence0);
    }

    @Deprecated
    public void setContentDescription(CharSequence charSequence0) {
        this.mRecord.setContentDescription(charSequence0);
    }

    @Deprecated
    public void setCurrentItemIndex(int v) {
        this.mRecord.setCurrentItemIndex(v);
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.mRecord.setEnabled(z);
    }

    @Deprecated
    public void setFromIndex(int v) {
        this.mRecord.setFromIndex(v);
    }

    @Deprecated
    public void setFullScreen(boolean z) {
        this.mRecord.setFullScreen(z);
    }

    @Deprecated
    public void setItemCount(int v) {
        this.mRecord.setItemCount(v);
    }

    public static void setMaxScrollX(AccessibilityRecord accessibilityRecord0, int v) {
        if(Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord0.setMaxScrollX(v);
        }
    }

    @Deprecated
    public void setMaxScrollX(int v) {
        AccessibilityRecordCompat.setMaxScrollX(this.mRecord, v);
    }

    public static void setMaxScrollY(AccessibilityRecord accessibilityRecord0, int v) {
        if(Build.VERSION.SDK_INT >= 15) {
            accessibilityRecord0.setMaxScrollY(v);
        }
    }

    @Deprecated
    public void setMaxScrollY(int v) {
        AccessibilityRecordCompat.setMaxScrollY(this.mRecord, v);
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelable0) {
        this.mRecord.setParcelableData(parcelable0);
    }

    @Deprecated
    public void setPassword(boolean z) {
        this.mRecord.setPassword(z);
    }

    @Deprecated
    public void setRemovedCount(int v) {
        this.mRecord.setRemovedCount(v);
    }

    @Deprecated
    public void setScrollX(int v) {
        this.mRecord.setScrollX(v);
    }

    @Deprecated
    public void setScrollY(int v) {
        this.mRecord.setScrollY(v);
    }

    @Deprecated
    public void setScrollable(boolean z) {
        this.mRecord.setScrollable(z);
    }

    public static void setSource(@NonNull AccessibilityRecord accessibilityRecord0, View view0, int v) {
        if(Build.VERSION.SDK_INT >= 16) {
            accessibilityRecord0.setSource(view0, v);
        }
    }

    @Deprecated
    public void setSource(View view0) {
        this.mRecord.setSource(view0);
    }

    @Deprecated
    public void setSource(View view0, int v) {
        AccessibilityRecordCompat.setSource(this.mRecord, view0, v);
    }

    @Deprecated
    public void setToIndex(int v) {
        this.mRecord.setToIndex(v);
    }
}

