package androidx.core.app;

import android.app.Notification.Action;
import android.app.Notification.Builder;
import android.app.Notification;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import jeb.synthetic.FIN;

@RequiresApi(16)
class NotificationCompatJellybean {
    static final String EXTRA_ALLOW_GENERATED_REPLIES = "android.support.allowGeneratedReplies";
    static final String EXTRA_DATA_ONLY_REMOTE_INPUTS = "android.support.dataRemoteInputs";
    private static final String KEY_ACTION_INTENT = "actionIntent";
    private static final String KEY_ALLOWED_DATA_TYPES = "allowedDataTypes";
    private static final String KEY_ALLOW_FREE_FORM_INPUT = "allowFreeFormInput";
    private static final String KEY_CHOICES = "choices";
    private static final String KEY_DATA_ONLY_REMOTE_INPUTS = "dataOnlyRemoteInputs";
    private static final String KEY_EXTRAS = "extras";
    private static final String KEY_ICON = "icon";
    private static final String KEY_LABEL = "label";
    private static final String KEY_REMOTE_INPUTS = "remoteInputs";
    private static final String KEY_RESULT_KEY = "resultKey";
    private static final String KEY_SEMANTIC_ACTION = "semanticAction";
    private static final String KEY_SHOWS_USER_INTERFACE = "showsUserInterface";
    private static final String KEY_TITLE = "title";
    public static final String TAG = "NotificationCompat";
    private static Class sActionClass;
    private static Field sActionIconField;
    private static Field sActionIntentField;
    private static Field sActionTitleField;
    private static boolean sActionsAccessFailed;
    private static Field sActionsField;
    private static final Object sActionsLock;
    private static Field sExtrasField;
    private static boolean sExtrasFieldAccessFailed;
    private static final Object sExtrasLock;

    static {
        NotificationCompatJellybean.sExtrasLock = new Object();
        NotificationCompatJellybean.sActionsLock = new Object();
    }

    public static SparseArray buildActionExtrasMap(List list0) {
        int v = list0.size();
        SparseArray sparseArray0 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            Bundle bundle0 = (Bundle)list0.get(v1);
            if(bundle0 != null) {
                if(sparseArray0 == null) {
                    sparseArray0 = new SparseArray();
                }
                sparseArray0.put(v1, bundle0);
            }
        }
        return sparseArray0;
    }

    private static boolean ensureActionReflectionReadyLocked() {
        if(NotificationCompatJellybean.sActionsAccessFailed) {
            return false;
        }
        if(NotificationCompatJellybean.sActionsField == null) {
            try {
                NotificationCompatJellybean.sActionClass = Notification.Action.class;
                NotificationCompatJellybean.sActionIconField = NotificationCompatJellybean.sActionClass.getDeclaredField("icon");
                NotificationCompatJellybean.sActionTitleField = NotificationCompatJellybean.sActionClass.getDeclaredField("title");
                NotificationCompatJellybean.sActionIntentField = NotificationCompatJellybean.sActionClass.getDeclaredField("actionIntent");
                NotificationCompatJellybean.sActionsField = Notification.class.getDeclaredField("actions");
                NotificationCompatJellybean.sActionsField.setAccessible(true);
                return true ^ NotificationCompatJellybean.sActionsAccessFailed;
            }
            catch(ClassNotFoundException classNotFoundException0) {
                Log.e("NotificationCompat", "Unable to access notification actions", classNotFoundException0);
                NotificationCompatJellybean.sActionsAccessFailed = true;
                return true ^ NotificationCompatJellybean.sActionsAccessFailed;
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("NotificationCompat", "Unable to access notification actions", noSuchFieldException0);
                NotificationCompatJellybean.sActionsAccessFailed = true;
                return 0;
            }
        }
        return true ^ NotificationCompatJellybean.sActionsAccessFailed;
    }

    private static RemoteInput fromBundle(Bundle bundle0) {
        ArrayList arrayList0 = bundle0.getStringArrayList("allowedDataTypes");
        HashSet hashSet0 = new HashSet();
        if(arrayList0 != null) {
            for(Object object0: arrayList0) {
                hashSet0.add(((String)object0));
            }
        }
        return new RemoteInput(bundle0.getString("resultKey"), bundle0.getCharSequence("label"), bundle0.getCharSequenceArray("choices"), bundle0.getBoolean("allowFreeFormInput"), bundle0.getBundle("extras"), hashSet0);
    }

    private static RemoteInput[] fromBundleArray(Bundle[] arr_bundle) {
        if(arr_bundle == null) {
            return null;
        }
        RemoteInput[] arr_remoteInput = new RemoteInput[arr_bundle.length];
        for(int v = 0; v < arr_bundle.length; ++v) {
            arr_remoteInput[v] = NotificationCompatJellybean.fromBundle(arr_bundle[v]);
        }
        return arr_remoteInput;
    }

    public static Action getAction(Notification notification0, int v) {
        Bundle bundle1;
        synchronized(NotificationCompatJellybean.sActionsLock) {
            try {
                Object[] arr_object = NotificationCompatJellybean.getActionObjectsLocked(notification0);
                if(arr_object != null) {
                    Object object1 = arr_object[v];
                    Bundle bundle0 = NotificationCompatJellybean.getExtras(notification0);
                    if(bundle0 == null) {
                        bundle1 = null;
                    }
                    else {
                        SparseArray sparseArray0 = bundle0.getSparseParcelableArray("android.support.actionExtras");
                        bundle1 = sparseArray0 == null ? null : ((Bundle)sparseArray0.get(v));
                    }
                    return NotificationCompatJellybean.readAction(NotificationCompatJellybean.sActionIconField.getInt(object1), ((CharSequence)NotificationCompatJellybean.sActionTitleField.get(object1)), ((PendingIntent)NotificationCompatJellybean.sActionIntentField.get(object1)), bundle1);
                }
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("NotificationCompat", "Unable to access notification actions", illegalAccessException0);
                NotificationCompatJellybean.sActionsAccessFailed = true;
            }
        }
        return null;
    }

    public static int getActionCount(Notification notification0) {
        synchronized(NotificationCompatJellybean.sActionsLock) {
            Object[] arr_object = NotificationCompatJellybean.getActionObjectsLocked(notification0);
            return arr_object == null ? 0 : arr_object.length;
        }
    }

    static Action getActionFromBundle(Bundle bundle0) {
        Bundle bundle1 = bundle0.getBundle("extras");
        if(bundle1 != null) {
            boolean z = bundle1.getBoolean("android.support.allowGeneratedReplies", false);
            return new Action(bundle0.getInt("icon"), bundle0.getCharSequence("title"), ((PendingIntent)bundle0.getParcelable("actionIntent")), bundle0.getBundle("extras"), NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "remoteInputs")), NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "dataOnlyRemoteInputs")), z, bundle0.getInt("semanticAction"), bundle0.getBoolean("showsUserInterface"));
        }
        return new Action(bundle0.getInt("icon"), bundle0.getCharSequence("title"), ((PendingIntent)bundle0.getParcelable("actionIntent")), bundle0.getBundle("extras"), NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "remoteInputs")), NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "dataOnlyRemoteInputs")), false, bundle0.getInt("semanticAction"), bundle0.getBoolean("showsUserInterface"));
    }

    private static Object[] getActionObjectsLocked(Notification notification0) {
        Object object0 = NotificationCompatJellybean.sActionsLock;
        __monitor_enter(object0);
        int v = FIN.finallyOpen$NT();
        if(!NotificationCompatJellybean.ensureActionReflectionReadyLocked()) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(object0);
            FIN.finallyCodeEnd$NT(v);
            return null;
        }
        try {
            Object[] arr_object = (Object[])NotificationCompatJellybean.sActionsField.get(notification0);
            FIN.finallyExec$NT(v);
            return arr_object;
        }
        catch(IllegalAccessException illegalAccessException0) {
            Log.e("NotificationCompat", "Unable to access notification actions", illegalAccessException0);
            NotificationCompatJellybean.sActionsAccessFailed = true;
            FIN.finallyExec$NT(v);
            return null;
        }
    }

    private static Bundle[] getBundleArrayFromBundle(Bundle bundle0, String s) {
        Parcelable[] arr_parcelable = bundle0.getParcelableArray(s);
        if(!(arr_parcelable instanceof Bundle[]) && arr_parcelable != null) {
            Bundle[] arr_bundle = (Bundle[])Arrays.copyOf(arr_parcelable, arr_parcelable.length, Bundle[].class);
            bundle0.putParcelableArray(s, arr_bundle);
            return arr_bundle;
        }
        return (Bundle[])arr_parcelable;
    }

    static Bundle getBundleForAction(Action notificationCompat$Action0) {
        Bundle bundle0 = new Bundle();
        bundle0.putInt("icon", notificationCompat$Action0.getIcon());
        bundle0.putCharSequence("title", notificationCompat$Action0.getTitle());
        bundle0.putParcelable("actionIntent", notificationCompat$Action0.getActionIntent());
        Bundle bundle1 = notificationCompat$Action0.getExtras() == null ? new Bundle() : new Bundle(notificationCompat$Action0.getExtras());
        bundle1.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
        bundle0.putBundle("extras", bundle1);
        bundle0.putParcelableArray("remoteInputs", NotificationCompatJellybean.toBundleArray(notificationCompat$Action0.getRemoteInputs()));
        bundle0.putBoolean("showsUserInterface", notificationCompat$Action0.getShowsUserInterface());
        bundle0.putInt("semanticAction", notificationCompat$Action0.getSemanticAction());
        return bundle0;
    }

    public static Bundle getExtras(Notification notification0) {
        synchronized(NotificationCompatJellybean.sExtrasLock) {
            if(NotificationCompatJellybean.sExtrasFieldAccessFailed) {
                return null;
            }
            try {
                if(NotificationCompatJellybean.sExtrasField == null) {
                    Field field0 = Notification.class.getDeclaredField("extras");
                    Class class0 = field0.getType();
                    if(!Bundle.class.isAssignableFrom(class0)) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
                        return null;
                    }
                    field0.setAccessible(true);
                    NotificationCompatJellybean.sExtrasField = field0;
                }
                Bundle bundle0 = (Bundle)NotificationCompatJellybean.sExtrasField.get(notification0);
                if(bundle0 == null) {
                    bundle0 = new Bundle();
                    NotificationCompatJellybean.sExtrasField.set(notification0, bundle0);
                }
                return bundle0;
            }
            catch(IllegalAccessException illegalAccessException0) {
                Log.e("NotificationCompat", "Unable to access notification extras", illegalAccessException0);
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                Log.e("NotificationCompat", "Unable to access notification extras", noSuchFieldException0);
            }
            NotificationCompatJellybean.sExtrasFieldAccessFailed = true;
            return null;
        }
    }

    public static Action readAction(int v, CharSequence charSequence0, PendingIntent pendingIntent0, Bundle bundle0) {
        return bundle0 == null ? new Action(v, charSequence0, pendingIntent0, null, null, null, false, 0, true) : new Action(v, charSequence0, pendingIntent0, bundle0, NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "android.support.remoteInputs")), NotificationCompatJellybean.fromBundleArray(NotificationCompatJellybean.getBundleArrayFromBundle(bundle0, "android.support.dataRemoteInputs")), bundle0.getBoolean("android.support.allowGeneratedReplies"), 0, true);
    }

    private static Bundle toBundle(RemoteInput remoteInput0) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("resultKey", remoteInput0.getResultKey());
        bundle0.putCharSequence("label", remoteInput0.getLabel());
        bundle0.putCharSequenceArray("choices", remoteInput0.getChoices());
        bundle0.putBoolean("allowFreeFormInput", remoteInput0.getAllowFreeFormInput());
        bundle0.putBundle("extras", remoteInput0.getExtras());
        Set set0 = remoteInput0.getAllowedDataTypes();
        if(set0 != null && !set0.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(set0.size());
            for(Object object0: set0) {
                arrayList0.add(((String)object0));
            }
            bundle0.putStringArrayList("allowedDataTypes", arrayList0);
        }
        return bundle0;
    }

    private static Bundle[] toBundleArray(RemoteInput[] arr_remoteInput) {
        if(arr_remoteInput == null) {
            return null;
        }
        Bundle[] arr_bundle = new Bundle[arr_remoteInput.length];
        for(int v = 0; v < arr_remoteInput.length; ++v) {
            arr_bundle[v] = NotificationCompatJellybean.toBundle(arr_remoteInput[v]);
        }
        return arr_bundle;
    }

    public static Bundle writeActionAndGetExtras(Notification.Builder notification$Builder0, Action notificationCompat$Action0) {
        notification$Builder0.addAction(notificationCompat$Action0.getIcon(), notificationCompat$Action0.getTitle(), notificationCompat$Action0.getActionIntent());
        Bundle bundle0 = new Bundle(notificationCompat$Action0.getExtras());
        if(notificationCompat$Action0.getRemoteInputs() != null) {
            bundle0.putParcelableArray("android.support.remoteInputs", NotificationCompatJellybean.toBundleArray(notificationCompat$Action0.getRemoteInputs()));
        }
        if(notificationCompat$Action0.getDataOnlyRemoteInputs() != null) {
            bundle0.putParcelableArray("android.support.dataRemoteInputs", NotificationCompatJellybean.toBundleArray(notificationCompat$Action0.getDataOnlyRemoteInputs()));
        }
        bundle0.putBoolean("android.support.allowGeneratedReplies", notificationCompat$Action0.getAllowGeneratedReplies());
        return bundle0;
    }
}

