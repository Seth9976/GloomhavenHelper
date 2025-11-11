package androidx.fragment.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View.BaseSavedState;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost;
import android.widget.TabWidget;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context0) {
            this.mContext = context0;
        }

        @Override  // android.widget.TabHost$TabContentFactory
        public View createTabContent(String s) {
            View view0 = new View(this.mContext);
            view0.setMinimumWidth(0);
            view0.setMinimumHeight(0);
            return view0;
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR;
        String curTab;

        static {
            SavedState.CREATOR = new Parcelable.Creator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Parcel parcel0) {
            super(parcel0);
            this.curTab = parcel0.readString();
        }

        SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override
        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.curTab + "}";
        }

        @Override  // android.view.View$BaseSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.curTab);
        }
    }

    static final class TabInfo {
        @Nullable
        final Bundle args;
        @NonNull
        final Class clss;
        Fragment fragment;
        @NonNull
        final String tag;

        TabInfo(@NonNull String s, @NonNull Class class0, @Nullable Bundle bundle0) {
            this.tag = s;
            this.clss = class0;
            this.args = bundle0;
        }
    }

    private boolean mAttached;
    private int mContainerId;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private TabInfo mLastTab;
    private TabHost.OnTabChangeListener mOnTabChangeListener;
    private FrameLayout mRealTabContent;
    private final ArrayList mTabs;

    public FragmentTabHost(Context context0) {
        super(context0, null);
        this.mTabs = new ArrayList();
        this.initFragmentTabHost(context0, null);
    }

    public FragmentTabHost(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mTabs = new ArrayList();
        this.initFragmentTabHost(context0, attributeSet0);
    }

    public void addTab(@NonNull TabHost.TabSpec tabHost$TabSpec0, @NonNull Class class0, @Nullable Bundle bundle0) {
        tabHost$TabSpec0.setContent(new DummyTabFactory(this.mContext));
        String s = tabHost$TabSpec0.getTag();
        TabInfo fragmentTabHost$TabInfo0 = new TabInfo(s, class0, bundle0);
        if(this.mAttached) {
            fragmentTabHost$TabInfo0.fragment = this.mFragmentManager.findFragmentByTag(s);
            if(fragmentTabHost$TabInfo0.fragment != null && !fragmentTabHost$TabInfo0.fragment.isDetached()) {
                FragmentTransaction fragmentTransaction0 = this.mFragmentManager.beginTransaction();
                fragmentTransaction0.detach(fragmentTabHost$TabInfo0.fragment);
                fragmentTransaction0.commit();
            }
        }
        this.mTabs.add(fragmentTabHost$TabInfo0);
        this.addTab(tabHost$TabSpec0);
    }

    @Nullable
    private FragmentTransaction doTabChanged(@Nullable String s, @Nullable FragmentTransaction fragmentTransaction0) {
        TabInfo fragmentTabHost$TabInfo0 = this.getTabInfoForTag(s);
        if(this.mLastTab != fragmentTabHost$TabInfo0) {
            if(fragmentTransaction0 == null) {
                fragmentTransaction0 = this.mFragmentManager.beginTransaction();
            }
            if(this.mLastTab != null && this.mLastTab.fragment != null) {
                fragmentTransaction0.detach(this.mLastTab.fragment);
            }
            if(fragmentTabHost$TabInfo0 != null) {
                if(fragmentTabHost$TabInfo0.fragment == null) {
                    fragmentTabHost$TabInfo0.fragment = Fragment.instantiate(this.mContext, fragmentTabHost$TabInfo0.clss.getName(), fragmentTabHost$TabInfo0.args);
                    fragmentTransaction0.add(this.mContainerId, fragmentTabHost$TabInfo0.fragment, fragmentTabHost$TabInfo0.tag);
                }
                else {
                    fragmentTransaction0.attach(fragmentTabHost$TabInfo0.fragment);
                }
            }
            this.mLastTab = fragmentTabHost$TabInfo0;
        }
        return fragmentTransaction0;
    }

    private void ensureContent() {
        if(this.mRealTabContent == null) {
            this.mRealTabContent = (FrameLayout)this.findViewById(this.mContainerId);
            if(this.mRealTabContent == null) {
                throw new IllegalStateException("No tab content FrameLayout found for id " + this.mContainerId);
            }
        }
    }

    private void ensureHierarchy(Context context0) {
        if(this.findViewById(0x1020013) == null) {
            LinearLayout linearLayout0 = new LinearLayout(context0);
            linearLayout0.setOrientation(1);
            this.addView(linearLayout0, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget0 = new TabWidget(context0);
            tabWidget0.setId(0x1020013);
            tabWidget0.setOrientation(0);
            linearLayout0.addView(tabWidget0, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            FrameLayout frameLayout0 = new FrameLayout(context0);
            frameLayout0.setId(0x1020011);
            linearLayout0.addView(frameLayout0, new LinearLayout.LayoutParams(0, 0, 0.0f));
            FrameLayout frameLayout1 = new FrameLayout(context0);
            this.mRealTabContent = frameLayout1;
            this.mRealTabContent.setId(this.mContainerId);
            linearLayout0.addView(frameLayout1, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Nullable
    private TabInfo getTabInfoForTag(String s) {
        int v = this.mTabs.size();
        for(int v1 = 0; v1 < v; ++v1) {
            TabInfo fragmentTabHost$TabInfo0 = (TabInfo)this.mTabs.get(v1);
            if(fragmentTabHost$TabInfo0.tag.equals(s)) {
                return fragmentTabHost$TabInfo0;
            }
        }
        return null;
    }

    private void initFragmentTabHost(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, new int[]{0x10100F3}, 0, 0);
        this.mContainerId = typedArray0.getResourceId(0, 0);
        typedArray0.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        String s = this.getCurrentTabTag();
        int v = this.mTabs.size();
        FragmentTransaction fragmentTransaction0 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            TabInfo fragmentTabHost$TabInfo0 = (TabInfo)this.mTabs.get(v1);
            fragmentTabHost$TabInfo0.fragment = this.mFragmentManager.findFragmentByTag(fragmentTabHost$TabInfo0.tag);
            if(fragmentTabHost$TabInfo0.fragment != null && !fragmentTabHost$TabInfo0.fragment.isDetached()) {
                if(fragmentTabHost$TabInfo0.tag.equals(s)) {
                    this.mLastTab = fragmentTabHost$TabInfo0;
                }
                else {
                    if(fragmentTransaction0 == null) {
                        fragmentTransaction0 = this.mFragmentManager.beginTransaction();
                    }
                    fragmentTransaction0.detach(fragmentTabHost$TabInfo0.fragment);
                }
            }
        }
        this.mAttached = true;
        FragmentTransaction fragmentTransaction1 = this.doTabChanged(s, fragmentTransaction0);
        if(fragmentTransaction1 != null) {
            fragmentTransaction1.commit();
            this.mFragmentManager.executePendingTransactions();
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttached = false;
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setCurrentTabByTag(((SavedState)parcelable0).curTab);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.curTab = this.getCurrentTabTag();
        return parcelable0;
    }

    @Override  // android.widget.TabHost$OnTabChangeListener
    public void onTabChanged(String s) {
        if(this.mAttached) {
            FragmentTransaction fragmentTransaction0 = this.doTabChanged(s, null);
            if(fragmentTransaction0 != null) {
                fragmentTransaction0.commit();
            }
        }
        TabHost.OnTabChangeListener tabHost$OnTabChangeListener0 = this.mOnTabChangeListener;
        if(tabHost$OnTabChangeListener0 != null) {
            tabHost$OnTabChangeListener0.onTabChanged(s);
        }
    }

    @Override  // android.widget.TabHost
    public void setOnTabChangedListener(TabHost.OnTabChangeListener tabHost$OnTabChangeListener0) {
        this.mOnTabChangeListener = tabHost$OnTabChangeListener0;
    }

    @Override  // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    public void setup(Context context0, FragmentManager fragmentManager0) {
        this.ensureHierarchy(context0);
        super.setup();
        this.mContext = context0;
        this.mFragmentManager = fragmentManager0;
        this.ensureContent();
    }

    public void setup(Context context0, FragmentManager fragmentManager0, int v) {
        this.ensureHierarchy(context0);
        super.setup();
        this.mContext = context0;
        this.mFragmentManager = fragmentManager0;
        this.mContainerId = v;
        this.ensureContent();
        this.mRealTabContent.setId(v);
        if(this.getId() == -1) {
            this.setId(0x1020012);
        }
    }
}

