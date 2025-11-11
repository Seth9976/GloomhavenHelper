package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private final DataSetObservable mObservable;
    private DataSetObserver mViewPagerObserver;

    public PagerAdapter() {
        this.mObservable = new DataSetObservable();
    }

    @Deprecated
    public void destroyItem(@NonNull View view0, int v, @NonNull Object object0) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(@NonNull ViewGroup viewGroup0, int v, @NonNull Object object0) {
        this.destroyItem(viewGroup0, v, object0);
    }

    @Deprecated
    public void finishUpdate(@NonNull View view0) {
    }

    public void finishUpdate(@NonNull ViewGroup viewGroup0) {
    }

    public abstract int getCount();

    public int getItemPosition(@NonNull Object object0) [...] // Inlined contents

    @Nullable
    public CharSequence getPageTitle(int v) [...] // Inlined contents

    public float getPageWidth(int v) [...] // Inlined contents

    @NonNull
    @Deprecated
    public Object instantiateItem(@NonNull View view0, int v) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup0, int v) {
        return this.instantiateItem(viewGroup0, v);
    }

    public abstract boolean isViewFromObject(@NonNull View arg1, @NonNull Object arg2);

    public void notifyDataSetChanged() {
        synchronized(this) {
            if(this.mViewPagerObserver != null) {
                this.mViewPagerObserver.onChanged();
            }
        }
        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(@NonNull DataSetObserver dataSetObserver0) {
        this.mObservable.registerObserver(dataSetObserver0);
    }

    public void restoreState(@Nullable Parcelable parcelable0, @Nullable ClassLoader classLoader0) {
    }

    @Nullable
    public Parcelable saveState() {
        return null;
    }

    @Deprecated
    public void setPrimaryItem(@NonNull View view0, int v, @NonNull Object object0) {
    }

    public void setPrimaryItem(@NonNull ViewGroup viewGroup0, int v, @NonNull Object object0) {
    }

    void setViewPagerObserver(DataSetObserver dataSetObserver0) {
        synchronized(this) {
            this.mViewPagerObserver = dataSetObserver0;
        }
    }

    @Deprecated
    public void startUpdate(@NonNull View view0) {
    }

    public void startUpdate(@NonNull ViewGroup viewGroup0) {
    }

    public void unregisterDataSetObserver(@NonNull DataSetObserver dataSetObserver0) {
        this.mObservable.unregisterObserver(dataSetObserver0);
    }
}

