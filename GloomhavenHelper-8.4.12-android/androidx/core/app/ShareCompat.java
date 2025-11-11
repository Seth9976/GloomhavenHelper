package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import androidx.annotation.StringRes;
import java.util.ArrayList;

public final class ShareCompat {
    public static class IntentBuilder {
        private Activity mActivity;
        private ArrayList mBccAddresses;
        private ArrayList mCcAddresses;
        private CharSequence mChooserTitle;
        private Intent mIntent;
        private ArrayList mStreams;
        private ArrayList mToAddresses;

        private IntentBuilder(Activity activity0) {
            this.mActivity = activity0;
            this.mIntent = new Intent().setAction("android.intent.action.SEND");
            this.mIntent.putExtra("androidx.core.app.EXTRA_CALLING_PACKAGE", "com.esotericsoftware.gloomhavenhelper");
            this.mIntent.putExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY", activity0.getComponentName());
            this.mIntent.addFlags(0x80000);
        }

        public IntentBuilder addEmailBcc(String s) {
            if(this.mBccAddresses == null) {
                this.mBccAddresses = new ArrayList();
            }
            this.mBccAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailBcc(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.BCC", arr_s);
            return this;
        }

        public IntentBuilder addEmailCc(String s) {
            if(this.mCcAddresses == null) {
                this.mCcAddresses = new ArrayList();
            }
            this.mCcAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailCc(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.CC", arr_s);
            return this;
        }

        public IntentBuilder addEmailTo(String s) {
            if(this.mToAddresses == null) {
                this.mToAddresses = new ArrayList();
            }
            this.mToAddresses.add(s);
            return this;
        }

        public IntentBuilder addEmailTo(String[] arr_s) {
            this.combineArrayExtra("android.intent.extra.EMAIL", arr_s);
            return this;
        }

        public IntentBuilder addStream(Uri uri0) {
            Uri uri1 = (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
            if(this.mStreams == null && uri1 == null) {
                return this.setStream(uri0);
            }
            if(this.mStreams == null) {
                this.mStreams = new ArrayList();
            }
            if(uri1 != null) {
                this.mIntent.removeExtra("android.intent.extra.STREAM");
                this.mStreams.add(uri1);
            }
            this.mStreams.add(uri0);
            return this;
        }

        private void combineArrayExtra(String s, ArrayList arrayList0) {
            String[] arr_s = this.mIntent.getStringArrayExtra(s);
            int v = arr_s == null ? 0 : arr_s.length;
            String[] arr_s1 = new String[arrayList0.size() + v];
            arrayList0.toArray(arr_s1);
            if(arr_s != null) {
                System.arraycopy(arr_s, 0, arr_s1, arrayList0.size(), v);
            }
            this.mIntent.putExtra(s, arr_s1);
        }

        private void combineArrayExtra(String s, String[] arr_s) {
            Intent intent0 = this.getIntent();
            String[] arr_s1 = intent0.getStringArrayExtra(s);
            int v = arr_s1 == null ? 0 : arr_s1.length;
            String[] arr_s2 = new String[arr_s.length + v];
            if(arr_s1 != null) {
                System.arraycopy(arr_s1, 0, arr_s2, 0, v);
            }
            System.arraycopy(arr_s, 0, arr_s2, v, arr_s.length);
            intent0.putExtra(s, arr_s2);
        }

        public Intent createChooserIntent() {
            return Intent.createChooser(this.getIntent(), this.mChooserTitle);
        }

        public static IntentBuilder from(Activity activity0) {
            return new IntentBuilder(activity0);
        }

        Activity getActivity() {
            return this.mActivity;
        }

        public Intent getIntent() {
            ArrayList arrayList0 = this.mToAddresses;
            if(arrayList0 != null) {
                this.combineArrayExtra("android.intent.extra.EMAIL", arrayList0);
                this.mToAddresses = null;
            }
            ArrayList arrayList1 = this.mCcAddresses;
            if(arrayList1 != null) {
                this.combineArrayExtra("android.intent.extra.CC", arrayList1);
                this.mCcAddresses = null;
            }
            ArrayList arrayList2 = this.mBccAddresses;
            if(arrayList2 != null) {
                this.combineArrayExtra("android.intent.extra.BCC", arrayList2);
                this.mBccAddresses = null;
            }
            boolean z = this.mStreams != null && this.mStreams.size() > 1;
            boolean z1 = this.mIntent.getAction().equals("android.intent.action.SEND_MULTIPLE");
            if(!z && z1) {
                this.mIntent.setAction("android.intent.action.SEND");
                if(this.mStreams == null || this.mStreams.isEmpty()) {
                    this.mIntent.removeExtra("android.intent.extra.STREAM");
                }
                else {
                    this.mIntent.putExtra("android.intent.extra.STREAM", ((Parcelable)this.mStreams.get(0)));
                }
                this.mStreams = null;
            }
            if(z && !z1) {
                this.mIntent.setAction("android.intent.action.SEND_MULTIPLE");
                if(this.mStreams != null && !this.mStreams.isEmpty()) {
                    this.mIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mStreams);
                    return this.mIntent;
                }
                this.mIntent.removeExtra("android.intent.extra.STREAM");
            }
            return this.mIntent;
        }

        public IntentBuilder setChooserTitle(@StringRes int v) {
            return this.setChooserTitle(this.mActivity.getText(v));
        }

        public IntentBuilder setChooserTitle(CharSequence charSequence0) {
            this.mChooserTitle = charSequence0;
            return this;
        }

        public IntentBuilder setEmailBcc(String[] arr_s) {
            this.mIntent.putExtra("android.intent.extra.BCC", arr_s);
            return this;
        }

        public IntentBuilder setEmailCc(String[] arr_s) {
            this.mIntent.putExtra("android.intent.extra.CC", arr_s);
            return this;
        }

        public IntentBuilder setEmailTo(String[] arr_s) {
            if(this.mToAddresses != null) {
                this.mToAddresses = null;
            }
            this.mIntent.putExtra("android.intent.extra.EMAIL", arr_s);
            return this;
        }

        public IntentBuilder setHtmlText(String s) {
            this.mIntent.putExtra("android.intent.extra.HTML_TEXT", s);
            if(!this.mIntent.hasExtra("android.intent.extra.TEXT")) {
                this.setText(Html.fromHtml(s));
            }
            return this;
        }

        public IntentBuilder setStream(Uri uri0) {
            if(!this.mIntent.getAction().equals("android.intent.action.SEND")) {
                this.mIntent.setAction("android.intent.action.SEND");
            }
            this.mStreams = null;
            this.mIntent.putExtra("android.intent.extra.STREAM", uri0);
            return this;
        }

        public IntentBuilder setSubject(String s) {
            this.mIntent.putExtra("android.intent.extra.SUBJECT", s);
            return this;
        }

        public IntentBuilder setText(CharSequence charSequence0) {
            this.mIntent.putExtra("android.intent.extra.TEXT", charSequence0);
            return this;
        }

        public IntentBuilder setType(String s) {
            this.mIntent.setType(s);
            return this;
        }

        public void startChooser() {
            this.mActivity.startActivity(this.createChooserIntent());
        }
    }

    public static class IntentReader {
        private static final String TAG = "IntentReader";
        private Activity mActivity;
        private ComponentName mCallingActivity;
        private String mCallingPackage;
        private Intent mIntent;
        private ArrayList mStreams;

        private IntentReader(Activity activity0) {
            this.mActivity = activity0;
            this.mIntent = activity0.getIntent();
            this.mCallingPackage = ShareCompat.getCallingPackage(activity0);
            this.mCallingActivity = ShareCompat.getCallingActivity(activity0);
        }

        public static IntentReader from(Activity activity0) {
            return new IntentReader(activity0);
        }

        public ComponentName getCallingActivity() {
            return this.mCallingActivity;
        }

        public Drawable getCallingActivityIcon() {
            if(this.mCallingActivity == null) {
                return null;
            }
            PackageManager packageManager0 = this.mActivity.getPackageManager();
            try {
                return packageManager0.getActivityIcon(this.mCallingActivity);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve icon for calling activity", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public Drawable getCallingApplicationIcon() {
            if(this.mCallingPackage == null) {
                return null;
            }
            PackageManager packageManager0 = this.mActivity.getPackageManager();
            try {
                return packageManager0.getApplicationIcon(this.mCallingPackage);
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve icon for calling application", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public CharSequence getCallingApplicationLabel() {
            if(this.mCallingPackage == null) {
                return null;
            }
            PackageManager packageManager0 = this.mActivity.getPackageManager();
            try {
                return packageManager0.getApplicationLabel(packageManager0.getApplicationInfo(this.mCallingPackage, 0));
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.e("IntentReader", "Could not retrieve label for calling application", packageManager$NameNotFoundException0);
                return null;
            }
        }

        public String getCallingPackage() {
            return this.mCallingPackage;
        }

        public String[] getEmailBcc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.BCC");
        }

        public String[] getEmailCc() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.CC");
        }

        public String[] getEmailTo() {
            return this.mIntent.getStringArrayExtra("android.intent.extra.EMAIL");
        }

        public String getHtmlText() {
            String s = this.mIntent.getStringExtra("android.intent.extra.HTML_TEXT");
            if(s == null) {
                CharSequence charSequence0 = this.getText();
                if(charSequence0 instanceof Spanned) {
                    return Html.toHtml(((Spanned)charSequence0));
                }
                if(charSequence0 != null) {
                    if(Build.VERSION.SDK_INT >= 16) {
                        return Html.escapeHtml(charSequence0);
                    }
                    StringBuilder stringBuilder0 = new StringBuilder();
                    IntentReader.withinStyle(stringBuilder0, charSequence0, 0, charSequence0.length());
                    return stringBuilder0.toString();
                }
            }
            return s;
        }

        public Uri getStream() {
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public Uri getStream(int v) {
            if(this.mStreams == null && this.isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList arrayList0 = this.mStreams;
            if(arrayList0 != null) {
                return (Uri)arrayList0.get(v);
            }
            if(v != 0) {
                throw new IndexOutOfBoundsException("Stream items available: " + this.getStreamCount() + " index requested: " + v);
            }
            return (Uri)this.mIntent.getParcelableExtra("android.intent.extra.STREAM");
        }

        public int getStreamCount() {
            if(this.mStreams == null && this.isMultipleShare()) {
                this.mStreams = this.mIntent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            }
            ArrayList arrayList0 = this.mStreams;
            return arrayList0 != null ? arrayList0.size() : this.mIntent.hasExtra("android.intent.extra.STREAM");
        }

        public String getSubject() {
            return this.mIntent.getStringExtra("android.intent.extra.SUBJECT");
        }

        public CharSequence getText() {
            return this.mIntent.getCharSequenceExtra("android.intent.extra.TEXT");
        }

        public String getType() {
            return this.mIntent.getType();
        }

        public boolean isMultipleShare() {
            return "android.intent.action.SEND_MULTIPLE".equals(this.mIntent.getAction());
        }

        public boolean isShareIntent() {
            String s = this.mIntent.getAction();
            return "android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s);
        }

        public boolean isSingleShare() {
            return "android.intent.action.SEND".equals(this.mIntent.getAction());
        }

        private static void withinStyle(StringBuilder stringBuilder0, CharSequence charSequence0, int v, int v1) {
            while(v < v1) {
                int v2 = charSequence0.charAt(v);
                if(v2 == 60) {
                    stringBuilder0.append("&lt;");
                }
                else {
                    switch(v2) {
                        case 38: {
                            stringBuilder0.append("&amp;");
                            break;
                        }
                        case 62: {
                            stringBuilder0.append("&gt;");
                            break;
                        }
                        default: {
                            if(v2 > 0x7E || v2 < 0x20) {
                                stringBuilder0.append("&#" + v2 + ";");
                            }
                            else if(v2 == 0x20) {
                                while(v + 1 < v1 && charSequence0.charAt(v + 1) == 0x20) {
                                    stringBuilder0.append("&nbsp;");
                                    ++v;
                                }
                                stringBuilder0.append(' ');
                            }
                            else {
                                stringBuilder0.append(((char)v2));
                            }
                        }
                    }
                }
                ++v;
            }
        }
    }

    public static final String EXTRA_CALLING_ACTIVITY = "androidx.core.app.EXTRA_CALLING_ACTIVITY";
    public static final String EXTRA_CALLING_PACKAGE = "androidx.core.app.EXTRA_CALLING_PACKAGE";
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    public static void configureMenuItem(Menu menu0, int v, IntentBuilder shareCompat$IntentBuilder0) {
        MenuItem menuItem0 = menu0.findItem(v);
        if(menuItem0 == null) {
            throw new IllegalArgumentException("Could not find menu item with id " + v + " in the supplied menu");
        }
        ShareCompat.configureMenuItem(menuItem0, shareCompat$IntentBuilder0);
    }

    public static void configureMenuItem(MenuItem menuItem0, IntentBuilder shareCompat$IntentBuilder0) {
        ActionProvider actionProvider0 = menuItem0.getActionProvider();
        ShareActionProvider shareActionProvider0 = actionProvider0 instanceof ShareActionProvider ? ((ShareActionProvider)actionProvider0) : new ShareActionProvider(shareCompat$IntentBuilder0.getActivity());
        shareActionProvider0.setShareHistoryFileName(".sharecompat_" + shareCompat$IntentBuilder0.getActivity().getClass().getName());
        shareActionProvider0.setShareIntent(shareCompat$IntentBuilder0.getIntent());
        menuItem0.setActionProvider(shareActionProvider0);
        if(Build.VERSION.SDK_INT < 16 && !menuItem0.hasSubMenu()) {
            menuItem0.setIntent(shareCompat$IntentBuilder0.createChooserIntent());
        }
    }

    public static ComponentName getCallingActivity(Activity activity0) {
        ComponentName componentName0 = activity0.getCallingActivity();
        return componentName0 == null ? ((ComponentName)activity0.getIntent().getParcelableExtra("androidx.core.app.EXTRA_CALLING_ACTIVITY")) : componentName0;
    }

    public static String getCallingPackage(Activity activity0) {
        String s = activity0.getCallingPackage();
        return s == null ? activity0.getIntent().getStringExtra("androidx.core.app.EXTRA_CALLING_PACKAGE") : s;
    }
}

