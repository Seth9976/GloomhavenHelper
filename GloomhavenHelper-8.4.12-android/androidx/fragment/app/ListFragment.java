package androidx.fragment.app;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ListFragment extends Fragment {
    static final int INTERNAL_EMPTY_ID = 0xFF0001;
    static final int INTERNAL_LIST_CONTAINER_ID = 0xFF0003;
    static final int INTERNAL_PROGRESS_CONTAINER_ID = 0xFF0002;
    ListAdapter mAdapter;
    CharSequence mEmptyText;
    View mEmptyView;
    private final Handler mHandler;
    ListView mList;
    View mListContainer;
    boolean mListShown;
    private final AdapterView.OnItemClickListener mOnClickListener;
    View mProgressContainer;
    private final Runnable mRequestFocus;
    TextView mStandardEmptyView;

    public ListFragment() {
        this.mHandler = new Handler();
        this.mRequestFocus = new Runnable() {
            @Override
            public void run() {
                ListFragment.this.mList.focusableViewAvailable(ListFragment.this.mList);
            }
        };
        this.mOnClickListener = new AdapterView.OnItemClickListener() {
            @Override  // android.widget.AdapterView$OnItemClickListener
            public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
            }
        };
    }

    private void ensureList() {
        if(this.mList != null) {
            return;
        }
        View view0 = this.getView();
        if(view0 == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if(view0 instanceof ListView) {
            this.mList = (ListView)view0;
        }
        else {
            this.mStandardEmptyView = (TextView)view0.findViewById(0xFF0001);
            TextView textView0 = this.mStandardEmptyView;
            if(textView0 == null) {
                this.mEmptyView = view0.findViewById(0x1020004);
            }
            else {
                textView0.setVisibility(8);
            }
            this.mProgressContainer = view0.findViewById(0xFF0002);
            this.mListContainer = view0.findViewById(0xFF0003);
            View view1 = view0.findViewById(0x102000A);
            if(!(view1 instanceof ListView)) {
                throw view1 == null ? new RuntimeException("Your content must have a ListView whose id attribute is \'android.R.id.list\'") : new RuntimeException("Content has view with id attribute \'android.R.id.list\' that is not a ListView class");
            }
            this.mList = (ListView)view1;
            View view2 = this.mEmptyView;
            if(view2 == null) {
                CharSequence charSequence0 = this.mEmptyText;
                if(charSequence0 != null) {
                    this.mStandardEmptyView.setText(charSequence0);
                    this.mList.setEmptyView(this.mStandardEmptyView);
                }
            }
            else {
                this.mList.setEmptyView(view2);
            }
        }
        this.mListShown = true;
        this.mList.setOnItemClickListener(this.mOnClickListener);
        ListAdapter listAdapter0 = this.mAdapter;
        if(listAdapter0 != null) {
            this.mAdapter = null;
            this.setListAdapter(listAdapter0);
        }
        else if(this.mProgressContainer != null) {
            this.setListShown(false, false);
        }
        this.mHandler.post(this.mRequestFocus);
    }

    public ListAdapter getListAdapter() {
        return this.mAdapter;
    }

    public ListView getListView() {
        this.ensureList();
        return this.mList;
    }

    public long getSelectedItemId() {
        this.ensureList();
        return this.mList.getSelectedItemId();
    }

    public int getSelectedItemPosition() {
        this.ensureList();
        return this.mList.getSelectedItemPosition();
    }

    @Override  // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        Context context0 = this.getContext();
        View view0 = new FrameLayout(context0);
        LinearLayout linearLayout0 = new LinearLayout(context0);
        linearLayout0.setId(0xFF0002);
        linearLayout0.setOrientation(1);
        linearLayout0.setVisibility(8);
        linearLayout0.setGravity(17);
        linearLayout0.addView(new ProgressBar(context0, null, 0x101007A), new FrameLayout.LayoutParams(-2, -2));
        ((FrameLayout)view0).addView(linearLayout0, new FrameLayout.LayoutParams(-1, -1));
        FrameLayout frameLayout0 = new FrameLayout(context0);
        frameLayout0.setId(0xFF0003);
        TextView textView0 = new TextView(context0);
        textView0.setId(0xFF0001);
        textView0.setGravity(17);
        frameLayout0.addView(textView0, new FrameLayout.LayoutParams(-1, -1));
        ListView listView0 = new ListView(context0);
        listView0.setId(0x102000A);
        listView0.setDrawSelectorOnTop(false);
        frameLayout0.addView(listView0, new FrameLayout.LayoutParams(-1, -1));
        ((FrameLayout)view0).addView(frameLayout0, new FrameLayout.LayoutParams(-1, -1));
        ((FrameLayout)view0).setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        return view0;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.mHandler.removeCallbacks(this.mRequestFocus);
        this.mList = null;
        this.mListShown = false;
        this.mListContainer = null;
        this.mProgressContainer = null;
        this.mEmptyView = null;
        this.mStandardEmptyView = null;
        super.onDestroyView();
    }

    public void onListItemClick(ListView listView0, View view0, int v, long v1) {
    }

    @Override  // androidx.fragment.app.Fragment
    public void onViewCreated(@NonNull View view0, @Nullable Bundle bundle0) {
        super.onViewCreated(view0, bundle0);
        this.ensureList();
    }

    public void setEmptyText(CharSequence charSequence0) {
        this.ensureList();
        TextView textView0 = this.mStandardEmptyView;
        if(textView0 == null) {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }
        textView0.setText(charSequence0);
        if(this.mEmptyText == null) {
            this.mList.setEmptyView(this.mStandardEmptyView);
        }
        this.mEmptyText = charSequence0;
    }

    public void setListAdapter(ListAdapter listAdapter0) {
        boolean z = false;
        boolean z1 = this.mAdapter != null;
        this.mAdapter = listAdapter0;
        ListView listView0 = this.mList;
        if(listView0 != null) {
            listView0.setAdapter(listAdapter0);
            if(!this.mListShown && !z1) {
                if(this.getView().getWindowToken() != null) {
                    z = true;
                }
                this.setListShown(true, z);
            }
        }
    }

    private void setListShown(boolean z, boolean z1) {
        this.ensureList();
        View view0 = this.mProgressContainer;
        if(view0 == null) {
            throw new IllegalStateException("Can\'t be used with a custom content view");
        }
        if(this.mListShown == z) {
            return;
        }
        this.mListShown = z;
        if(z) {
            if(z1) {
                view0.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 0x10A0001));
                this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 0x10A0000));
            }
            else {
                view0.clearAnimation();
                this.mListContainer.clearAnimation();
            }
            this.mProgressContainer.setVisibility(8);
            this.mListContainer.setVisibility(0);
            return;
        }
        if(z1) {
            view0.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 0x10A0000));
            this.mListContainer.startAnimation(AnimationUtils.loadAnimation(this.getContext(), 0x10A0001));
        }
        else {
            view0.clearAnimation();
            this.mListContainer.clearAnimation();
        }
        this.mProgressContainer.setVisibility(0);
        this.mListContainer.setVisibility(8);
    }

    public void setListShown(boolean z) {
        this.setListShown(z, true);
    }

    public void setListShownNoAnimation(boolean z) {
        this.setListShown(z, false);
    }

    public void setSelection(int v) {
        this.ensureList();
        this.mList.setSelection(v);
    }
}

