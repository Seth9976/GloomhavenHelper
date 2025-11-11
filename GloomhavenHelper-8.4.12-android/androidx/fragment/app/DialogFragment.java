package androidx.fragment.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;

public class DialogFragment extends Fragment implements DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int mBackStackId;
    boolean mCancelable;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog;
    int mStyle;
    int mTheme;
    boolean mViewDestroyed;

    public DialogFragment() {
        this.mStyle = 0;
        this.mTheme = 0;
        this.mCancelable = true;
        this.mShowsDialog = true;
        this.mBackStackId = -1;
    }

    public void dismiss() {
        this.dismissInternal(false);
    }

    public void dismissAllowingStateLoss() {
        this.dismissInternal(true);
    }

    void dismissInternal(boolean z) {
        if(this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            dialog0.dismiss();
        }
        this.mViewDestroyed = true;
        if(this.mBackStackId >= 0) {
            this.getFragmentManager().popBackStack(this.mBackStackId, 1);
            this.mBackStackId = -1;
            return;
        }
        FragmentTransaction fragmentTransaction0 = this.getFragmentManager().beginTransaction();
        fragmentTransaction0.remove(this);
        if(z) {
            fragmentTransaction0.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction0.commit();
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    @StyleRes
    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    @Override  // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle0) {
        super.onActivityCreated(bundle0);
        if(!this.mShowsDialog) {
            return;
        }
        View view0 = this.getView();
        if(view0 != null) {
            if(view0.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            this.mDialog.setContentView(view0);
        }
        FragmentActivity fragmentActivity0 = this.getActivity();
        if(fragmentActivity0 != null) {
            this.mDialog.setOwnerActivity(fragmentActivity0);
        }
        this.mDialog.setCancelable(this.mCancelable);
        this.mDialog.setOnCancelListener(this);
        this.mDialog.setOnDismissListener(this);
        if(bundle0 != null) {
            Bundle bundle1 = bundle0.getBundle("android:savedDialogState");
            if(bundle1 != null) {
                this.mDialog.onRestoreInstanceState(bundle1);
            }
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public void onAttach(Context context0) {
        super.onAttach(context0);
        if(!this.mShownByMe) {
            this.mDismissed = false;
        }
    }

    @Override  // android.content.DialogInterface$OnCancelListener
    public void onCancel(DialogInterface dialogInterface0) {
    }

    @Override  // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle0) {
        super.onCreate(bundle0);
        this.mShowsDialog = this.mContainerId == 0;
        if(bundle0 != null) {
            this.mStyle = bundle0.getInt("android:style", 0);
            this.mTheme = bundle0.getInt("android:theme", 0);
            this.mCancelable = bundle0.getBoolean("android:cancelable", true);
            this.mShowsDialog = bundle0.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = bundle0.getInt("android:backStackId", -1);
        }
    }

    @NonNull
    public Dialog onCreateDialog(@Nullable Bundle bundle0) {
        return new Dialog(this.getActivity(), this.getTheme());
    }

    @Override  // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            this.mViewDestroyed = true;
            dialog0.dismiss();
            this.mDialog = null;
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        if(!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
    }

    @Override  // android.content.DialogInterface$OnDismissListener
    public void onDismiss(DialogInterface dialogInterface0) {
        if(!this.mViewDestroyed) {
            this.dismissInternal(true);
        }
    }

    @Override  // androidx.fragment.app.Fragment
    @NonNull
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle bundle0) {
        if(!this.mShowsDialog) {
            return super.onGetLayoutInflater(bundle0);
        }
        this.mDialog = this.onCreateDialog(bundle0);
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            this.setupDialog(dialog0, this.mStyle);
            return (LayoutInflater)this.mDialog.getContext().getSystemService("layout_inflater");
        }
        return (LayoutInflater)this.mHost.getContext().getSystemService("layout_inflater");
    }

    @Override  // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            Bundle bundle1 = dialog0.onSaveInstanceState();
            if(bundle1 != null) {
                bundle0.putBundle("android:savedDialogState", bundle1);
            }
        }
        int v = this.mStyle;
        if(v != 0) {
            bundle0.putInt("android:style", v);
        }
        int v1 = this.mTheme;
        if(v1 != 0) {
            bundle0.putInt("android:theme", v1);
        }
        if(!this.mCancelable) {
            bundle0.putBoolean("android:cancelable", false);
        }
        if(!this.mShowsDialog) {
            bundle0.putBoolean("android:showsDialog", false);
        }
        int v2 = this.mBackStackId;
        if(v2 != -1) {
            bundle0.putInt("android:backStackId", v2);
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            this.mViewDestroyed = false;
            dialog0.show();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            dialog0.hide();
        }
    }

    public void setCancelable(boolean z) {
        this.mCancelable = z;
        Dialog dialog0 = this.mDialog;
        if(dialog0 != null) {
            dialog0.setCancelable(z);
        }
    }

    public void setShowsDialog(boolean z) {
        this.mShowsDialog = z;
    }

    public void setStyle(int v, @StyleRes int v1) {
        this.mStyle = v;
        if(this.mStyle == 2 || this.mStyle == 3) {
            this.mTheme = 0x1030059;
        }
        if(v1 != 0) {
            this.mTheme = v1;
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public void setupDialog(Dialog dialog0, int v) {
        switch(v) {
            case 1: 
            case 2: {
                break;
            }
            case 3: {
                dialog0.getWindow().addFlags(24);
                break;
            }
            default: {
                return;
            }
        }
        dialog0.requestWindowFeature(1);
    }

    public int show(FragmentTransaction fragmentTransaction0, String s) {
        this.mDismissed = false;
        this.mShownByMe = true;
        fragmentTransaction0.add(this, s);
        this.mViewDestroyed = false;
        this.mBackStackId = fragmentTransaction0.commit();
        return this.mBackStackId;
    }

    public void show(FragmentManager fragmentManager0, String s) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        fragmentTransaction0.add(this, s);
        fragmentTransaction0.commit();
    }

    public void showNow(FragmentManager fragmentManager0, String s) {
        this.mDismissed = false;
        this.mShownByMe = true;
        FragmentTransaction fragmentTransaction0 = fragmentManager0.beginTransaction();
        fragmentTransaction0.add(this, s);
        fragmentTransaction0.commitNow();
    }
}

