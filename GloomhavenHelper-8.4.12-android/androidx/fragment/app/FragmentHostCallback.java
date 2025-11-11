package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class FragmentHostCallback extends FragmentContainer {
    @Nullable
    private final Activity mActivity;
    @NonNull
    private final Context mContext;
    final FragmentManagerImpl mFragmentManager;
    @NonNull
    private final Handler mHandler;
    private final int mWindowAnimations;

    FragmentHostCallback(@Nullable Activity activity0, @NonNull Context context0, @NonNull Handler handler0, int v) {
        this.mFragmentManager = new FragmentManagerImpl();
        this.mActivity = activity0;
        this.mContext = (Context)Preconditions.checkNotNull(context0, "context == null");
        this.mHandler = (Handler)Preconditions.checkNotNull(handler0, "handler == null");
        this.mWindowAnimations = v;
    }

    public FragmentHostCallback(@NonNull Context context0, @NonNull Handler handler0, int v) {
        this((context0 instanceof Activity ? ((Activity)context0) : null), context0, handler0, v);
    }

    FragmentHostCallback(@NonNull FragmentActivity fragmentActivity0) {
        this(fragmentActivity0, fragmentActivity0, fragmentActivity0.mHandler, 0);
    }

    @Nullable
    Activity getActivity() {
        return this.mActivity;
    }

    @NonNull
    Context getContext() {
        return this.mContext;
    }

    FragmentManagerImpl getFragmentManagerImpl() {
        return this.mFragmentManager;
    }

    @NonNull
    Handler getHandler() {
        return this.mHandler;
    }

    void onAttachFragment(Fragment fragment0) {
    }

    public void onDump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    @Override  // androidx.fragment.app.FragmentContainer
    @Nullable
    public View onFindViewById(int v) {
        return null;
    }

    @Nullable
    public abstract Object onGetHost();

    @NonNull
    public LayoutInflater onGetLayoutInflater() {
        return LayoutInflater.from(this.mContext);
    }

    public int onGetWindowAnimations() {
        return this.mWindowAnimations;
    }

    @Override  // androidx.fragment.app.FragmentContainer
    public boolean onHasView() {
        return true;
    }

    public boolean onHasWindowAnimations() {
        return true;
    }

    public void onRequestPermissionsFromFragment(@NonNull Fragment fragment0, @NonNull String[] arr_s, int v) {
    }

    public boolean onShouldSaveFragmentState(Fragment fragment0) {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(@NonNull String s) {
        return false;
    }

    public void onStartActivityFromFragment(Fragment fragment0, Intent intent0, int v) {
        this.onStartActivityFromFragment(fragment0, intent0, v, null);
    }

    public void onStartActivityFromFragment(Fragment fragment0, Intent intent0, int v, @Nullable Bundle bundle0) {
        if(v != -1) {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        }
        this.mContext.startActivity(intent0);
    }

    public void onStartIntentSenderFromFragment(Fragment fragment0, IntentSender intentSender0, int v, @Nullable Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        if(v != -1) {
            throw new IllegalStateException("Starting intent sender with a requestCode requires a FragmentActivity host");
        }
        ActivityCompat.startIntentSenderForResult(this.mActivity, intentSender0, -1, intent0, v1, v2, v3, bundle0);
    }

    public void onSupportInvalidateOptionsMenu() {
    }
}

