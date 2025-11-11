package androidx.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ReportFragment extends Fragment {
    interface ActivityInitializationListener {
        void onCreate();

        void onResume();

        void onStart();
    }

    private static final String REPORT_FRAGMENT_TAG = "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag";
    private ActivityInitializationListener mProcessListener;

    private void dispatch(Event lifecycle$Event0) {
        Activity activity0 = this.getActivity();
        if(activity0 instanceof LifecycleRegistryOwner) {
            ((LifecycleRegistryOwner)activity0).getLifecycle().handleLifecycleEvent(lifecycle$Event0);
            return;
        }
        if(activity0 instanceof LifecycleOwner) {
            Lifecycle lifecycle0 = ((LifecycleOwner)activity0).getLifecycle();
            if(lifecycle0 instanceof LifecycleRegistry) {
                ((LifecycleRegistry)lifecycle0).handleLifecycleEvent(lifecycle$Event0);
            }
        }
    }

    private void dispatchCreate(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onCreate();
        }
    }

    private void dispatchResume(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onResume();
        }
    }

    private void dispatchStart(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        if(reportFragment$ActivityInitializationListener0 != null) {
            reportFragment$ActivityInitializationListener0.onStart();
        }
    }

    static ReportFragment get(Activity activity0) {
        return (ReportFragment)activity0.getFragmentManager().findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    public static void injectIfNeededIn(Activity activity0) {
        FragmentManager fragmentManager0 = activity0.getFragmentManager();
        if(fragmentManager0.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager0.beginTransaction().add(new ReportFragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager0.executePendingTransactions();
        }
    }

    @Override  // android.app.Fragment
    public void onActivityCreated(Bundle bundle0) {
        super.onActivityCreated(bundle0);
        this.dispatchCreate(this.mProcessListener);
        this.dispatch(Event.ON_CREATE);
    }

    @Override  // android.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.dispatch(Event.ON_DESTROY);
        this.mProcessListener = null;
    }

    @Override  // android.app.Fragment
    public void onPause() {
        super.onPause();
        this.dispatch(Event.ON_PAUSE);
    }

    @Override  // android.app.Fragment
    public void onResume() {
        super.onResume();
        this.dispatchResume(this.mProcessListener);
        this.dispatch(Event.ON_RESUME);
    }

    @Override  // android.app.Fragment
    public void onStart() {
        super.onStart();
        this.dispatchStart(this.mProcessListener);
        this.dispatch(Event.ON_START);
    }

    @Override  // android.app.Fragment
    public void onStop() {
        super.onStop();
        this.dispatch(Event.ON_STOP);
    }

    void setProcessListener(ActivityInitializationListener reportFragment$ActivityInitializationListener0) {
        this.mProcessListener = reportFragment$ActivityInitializationListener0;
    }
}

