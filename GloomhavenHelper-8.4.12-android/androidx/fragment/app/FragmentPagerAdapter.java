package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public abstract class FragmentPagerAdapter extends PagerAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private final FragmentManager mFragmentManager;

    public FragmentPagerAdapter(FragmentManager fragmentManager0) {
        this.mCurTransaction = null;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = fragmentManager0;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(@NonNull ViewGroup viewGroup0, int v, @NonNull Object object0) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach(((Fragment)object0));
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(@NonNull ViewGroup viewGroup0) {
        FragmentTransaction fragmentTransaction0 = this.mCurTransaction;
        if(fragmentTransaction0 != null) {
            fragmentTransaction0.commitNowAllowingStateLoss();
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int arg1);

    public long getItemId(int v) [...] // Inlined contents

    @Override  // androidx.viewpager.widget.PagerAdapter
    @NonNull
    public Object instantiateItem(@NonNull ViewGroup viewGroup0, int v) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        String s = FragmentPagerAdapter.makeFragmentName(viewGroup0.getId(), ((long)v));
        Fragment fragment0 = this.mFragmentManager.findFragmentByTag(s);
        if(fragment0 == null) {
            fragment0 = this.getItem(v);
            this.mCurTransaction.add(viewGroup0.getId(), fragment0, FragmentPagerAdapter.makeFragmentName(viewGroup0.getId(), ((long)v)));
        }
        else {
            this.mCurTransaction.attach(fragment0);
        }
        if(fragment0 != this.mCurrentPrimaryItem) {
            fragment0.setMenuVisibility(false);
            fragment0.setUserVisibleHint(false);
        }
        return fragment0;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(@NonNull View view0, @NonNull Object object0) {
        return ((Fragment)object0).getView() == view0;
    }

    private static String makeFragmentName(int v, long v1) {
        return "android:switcher:" + v + ":" + v1;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable0, ClassLoader classLoader0) {
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return null;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(@NonNull ViewGroup viewGroup0, int v, @NonNull Object object0) {
        Fragment fragment0 = this.mCurrentPrimaryItem;
        if(((Fragment)object0) != fragment0) {
            if(fragment0 != null) {
                fragment0.setMenuVisibility(false);
                this.mCurrentPrimaryItem.setUserVisibleHint(false);
            }
            ((Fragment)object0).setMenuVisibility(true);
            ((Fragment)object0).setUserVisibleHint(true);
            this.mCurrentPrimaryItem = (Fragment)object0;
        }
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(@NonNull ViewGroup viewGroup0) {
        if(viewGroup0.getId() == -1) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }
}

