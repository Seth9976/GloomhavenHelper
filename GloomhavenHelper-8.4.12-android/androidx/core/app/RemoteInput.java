package androidx.core.app;

import android.app.RemoteInput.Builder;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class RemoteInput {
    public static final class Builder {
        private boolean mAllowFreeFormTextInput;
        private final Set mAllowedDataTypes;
        private CharSequence[] mChoices;
        private final Bundle mExtras;
        private CharSequence mLabel;
        private final String mResultKey;

        public Builder(@NonNull String s) {
            this.mAllowedDataTypes = new HashSet();
            this.mExtras = new Bundle();
            this.mAllowFreeFormTextInput = true;
            if(s == null) {
                throw new IllegalArgumentException("Result key can\'t be null");
            }
            this.mResultKey = s;
        }

        @NonNull
        public Builder addExtras(@NonNull Bundle bundle0) {
            if(bundle0 != null) {
                this.mExtras.putAll(bundle0);
            }
            return this;
        }

        @NonNull
        public RemoteInput build() {
            return new RemoteInput(this.mResultKey, this.mLabel, this.mChoices, this.mAllowFreeFormTextInput, this.mExtras, this.mAllowedDataTypes);
        }

        @NonNull
        public Bundle getExtras() {
            return this.mExtras;
        }

        @NonNull
        public Builder setAllowDataType(@NonNull String s, boolean z) {
            if(z) {
                this.mAllowedDataTypes.add(s);
                return this;
            }
            this.mAllowedDataTypes.remove(s);
            return this;
        }

        @NonNull
        public Builder setAllowFreeFormInput(boolean z) {
            this.mAllowFreeFormTextInput = z;
            return this;
        }

        @NonNull
        public Builder setChoices(@Nullable CharSequence[] arr_charSequence) {
            this.mChoices = arr_charSequence;
            return this;
        }

        @NonNull
        public Builder setLabel(@Nullable CharSequence charSequence0) {
            this.mLabel = charSequence0;
            return this;
        }
    }

    private static final String EXTRA_DATA_TYPE_RESULTS_DATA = "android.remoteinput.dataTypeResultsData";
    public static final String EXTRA_RESULTS_DATA = "android.remoteinput.resultsData";
    public static final String RESULTS_CLIP_LABEL = "android.remoteinput.results";
    private static final String TAG = "RemoteInput";
    private final boolean mAllowFreeFormTextInput;
    private final Set mAllowedDataTypes;
    private final CharSequence[] mChoices;
    private final Bundle mExtras;
    private final CharSequence mLabel;
    private final String mResultKey;

    RemoteInput(String s, CharSequence charSequence0, CharSequence[] arr_charSequence, boolean z, Bundle bundle0, Set set0) {
        this.mResultKey = s;
        this.mLabel = charSequence0;
        this.mChoices = arr_charSequence;
        this.mAllowFreeFormTextInput = z;
        this.mExtras = bundle0;
        this.mAllowedDataTypes = set0;
    }

    public static void addDataResultToIntent(RemoteInput remoteInput0, Intent intent0, Map map0) {
        if(Build.VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addDataResultToIntent(RemoteInput.fromCompat(remoteInput0), intent0, map0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
            if(intent1 == null) {
                intent1 = new Intent();
            }
            for(Object object0: map0.entrySet()) {
                String s = (String)((Map.Entry)object0).getKey();
                Uri uri0 = (Uri)((Map.Entry)object0).getValue();
                if(s != null) {
                    Bundle bundle0 = intent1.getBundleExtra("android.remoteinput.dataTypeResultsData" + s);
                    if(bundle0 == null) {
                        bundle0 = new Bundle();
                    }
                    bundle0.putString(remoteInput0.getResultKey(), uri0.toString());
                    intent1.putExtra("android.remoteinput.dataTypeResultsData" + s, bundle0);
                }
            }
            intent0.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
            return;
        }
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }

    public static void addResultsToIntent(RemoteInput[] arr_remoteInput, Intent intent0, Bundle bundle0) {
        if(Build.VERSION.SDK_INT >= 26) {
            android.app.RemoteInput.addResultsToIntent(RemoteInput.fromCompat(arr_remoteInput), intent0, bundle0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 20) {
            Bundle bundle1 = RemoteInput.getResultsFromIntent(intent0);
            if(bundle1 != null) {
                bundle1.putAll(bundle0);
                bundle0 = bundle1;
            }
            for(int v1 = 0; v1 < arr_remoteInput.length; ++v1) {
                RemoteInput remoteInput0 = arr_remoteInput[v1];
                Map map0 = RemoteInput.getDataResultsFromIntent(intent0, remoteInput0.getResultKey());
                android.app.RemoteInput.addResultsToIntent(RemoteInput.fromCompat(new RemoteInput[]{remoteInput0}), intent0, bundle0);
                if(map0 != null) {
                    RemoteInput.addDataResultToIntent(remoteInput0, intent0, map0);
                }
            }
            return;
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
            if(intent1 == null) {
                intent1 = new Intent();
            }
            Bundle bundle2 = intent1.getBundleExtra("android.remoteinput.resultsData");
            if(bundle2 == null) {
                bundle2 = new Bundle();
            }
            for(int v = 0; v < arr_remoteInput.length; ++v) {
                RemoteInput remoteInput1 = arr_remoteInput[v];
                Object object0 = bundle0.get(remoteInput1.getResultKey());
                if(object0 instanceof CharSequence) {
                    bundle2.putCharSequence(remoteInput1.getResultKey(), ((CharSequence)object0));
                }
            }
            intent1.putExtra("android.remoteinput.resultsData", bundle2);
            intent0.setClipData(ClipData.newIntent("android.remoteinput.results", intent1));
            return;
        }
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
    }

    @RequiresApi(20)
    static android.app.RemoteInput fromCompat(RemoteInput remoteInput0) {
        return new RemoteInput.Builder(remoteInput0.getResultKey()).setLabel(remoteInput0.getLabel()).setChoices(remoteInput0.getChoices()).setAllowFreeFormInput(remoteInput0.getAllowFreeFormInput()).addExtras(remoteInput0.getExtras()).build();
    }

    @RequiresApi(20)
    static android.app.RemoteInput[] fromCompat(RemoteInput[] arr_remoteInput) {
        if(arr_remoteInput == null) {
            return null;
        }
        android.app.RemoteInput[] arr_remoteInput1 = new android.app.RemoteInput[arr_remoteInput.length];
        for(int v = 0; v < arr_remoteInput.length; ++v) {
            arr_remoteInput1[v] = RemoteInput.fromCompat(arr_remoteInput[v]);
        }
        return arr_remoteInput1;
    }

    public boolean getAllowFreeFormInput() {
        return this.mAllowFreeFormTextInput;
    }

    public Set getAllowedDataTypes() {
        return this.mAllowedDataTypes;
    }

    public CharSequence[] getChoices() {
        return this.mChoices;
    }

    @RequiresApi(16)
    private static Intent getClipDataIntentFromIntent(Intent intent0) {
        ClipData clipData0 = intent0.getClipData();
        if(clipData0 == null) {
            return null;
        }
        ClipDescription clipDescription0 = clipData0.getDescription();
        if(!clipDescription0.hasMimeType("text/vnd.android.intent")) {
            return null;
        }
        return clipDescription0.getLabel().equals("android.remoteinput.results") ? clipData0.getItemAt(0).getIntent() : null;
    }

    public static Map getDataResultsFromIntent(Intent intent0, String s) {
        if(Build.VERSION.SDK_INT >= 26) {
            return android.app.RemoteInput.getDataResultsFromIntent(intent0, s);
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
            if(intent1 == null) {
                return null;
            }
            Map map0 = new HashMap();
            for(Object object0: intent1.getExtras().keySet()) {
                String s1 = (String)object0;
                if(s1.startsWith("android.remoteinput.dataTypeResultsData")) {
                    String s2 = s1.substring(39);
                    if(!s2.isEmpty()) {
                        String s3 = intent1.getBundleExtra(s1).getString(s);
                        if(s3 != null && !s3.isEmpty()) {
                            map0.put(s2, Uri.parse(s3));
                        }
                    }
                }
            }
            return map0.isEmpty() ? null : map0;
        }
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return null;
    }

    private static String getExtraResultsKeyForData(String s) [...] // Inlined contents

    public Bundle getExtras() {
        return this.mExtras;
    }

    public CharSequence getLabel() {
        return this.mLabel;
    }

    public String getResultKey() {
        return this.mResultKey;
    }

    public static Bundle getResultsFromIntent(Intent intent0) {
        if(Build.VERSION.SDK_INT >= 20) {
            return android.app.RemoteInput.getResultsFromIntent(intent0);
        }
        if(Build.VERSION.SDK_INT >= 16) {
            Intent intent1 = RemoteInput.getClipDataIntentFromIntent(intent0);
            return intent1 == null ? null : ((Bundle)intent1.getExtras().getParcelable("android.remoteinput.resultsData"));
        }
        Log.w("RemoteInput", "RemoteInput is only supported from API Level 16");
        return null;
    }

    // 去混淆评级： 低(30)
    public boolean isDataOnly() {
        return !this.getAllowFreeFormInput() && (this.getChoices() == null || this.getChoices().length == 0) && this.getAllowedDataTypes() != null && !this.getAllowedDataTypes().isEmpty();
    }
}

