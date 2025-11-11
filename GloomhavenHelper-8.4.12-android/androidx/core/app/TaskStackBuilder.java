package androidx.core.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Iterator;

public final class TaskStackBuilder implements Iterable {
    public interface SupportParentable {
        @Nullable
        Intent getSupportParentActivityIntent();
    }

    private static final String TAG = "TaskStackBuilder";
    private final ArrayList mIntents;
    private final Context mSourceContext;

    private TaskStackBuilder(Context context0) {
        this.mIntents = new ArrayList();
        this.mSourceContext = context0;
    }

    @NonNull
    public TaskStackBuilder addNextIntent(@NonNull Intent intent0) {
        this.mIntents.add(intent0);
        return this;
    }

    @NonNull
    public TaskStackBuilder addNextIntentWithParentStack(@NonNull Intent intent0) {
        ComponentName componentName0 = intent0.getComponent();
        if(componentName0 == null) {
            componentName0 = intent0.resolveActivity(this.mSourceContext.getPackageManager());
        }
        if(componentName0 != null) {
            this.addParentStack(componentName0);
        }
        this.addNextIntent(intent0);
        return this;
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Activity activity0) {
        Intent intent0 = activity0 instanceof SupportParentable ? ((SupportParentable)activity0).getSupportParentActivityIntent() : null;
        if(intent0 == null) {
            intent0 = NavUtils.getParentActivityIntent(activity0);
        }
        if(intent0 != null) {
            ComponentName componentName0 = intent0.getComponent();
            if(componentName0 == null) {
                componentName0 = intent0.resolveActivity(this.mSourceContext.getPackageManager());
            }
            this.addParentStack(componentName0);
            this.addNextIntent(intent0);
        }
        return this;
    }

    public TaskStackBuilder addParentStack(ComponentName componentName0) {
        int v = this.mIntents.size();
        try {
            for(Intent intent0 = NavUtils.getParentActivityIntent(this.mSourceContext, componentName0); intent0 != null; intent0 = NavUtils.getParentActivityIntent(this.mSourceContext, componentName1)) {
                this.mIntents.add(v, intent0);
                ComponentName componentName1 = intent0.getComponent();
            }
            return this;
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(packageManager$NameNotFoundException0);
        }
    }

    @NonNull
    public TaskStackBuilder addParentStack(@NonNull Class class0) {
        return this.addParentStack(new ComponentName(this.mSourceContext, class0));
    }

    @NonNull
    public static TaskStackBuilder create(@NonNull Context context0) {
        return new TaskStackBuilder(context0);
    }

    @Nullable
    public Intent editIntentAt(int v) {
        return (Intent)this.mIntents.get(v);
    }

    @Deprecated
    public static TaskStackBuilder from(Context context0) {
        return TaskStackBuilder.create(context0);
    }

    @Deprecated
    public Intent getIntent(int v) {
        return this.editIntentAt(v);
    }

    public int getIntentCount() {
        return this.mIntents.size();
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] arr_intent = new Intent[this.mIntents.size()];
        if(arr_intent.length == 0) {
            return arr_intent;
        }
        arr_intent[0] = new Intent(((Intent)this.mIntents.get(0))).addFlags(0x1000C000);
        for(int v = 1; v < arr_intent.length; ++v) {
            arr_intent[v] = new Intent(((Intent)this.mIntents.get(v)));
        }
        return arr_intent;
    }

    @Nullable
    public PendingIntent getPendingIntent(int v, int v1) {
        return this.getPendingIntent(v, v1, null);
    }

    @Nullable
    public PendingIntent getPendingIntent(int v, int v1, @Nullable Bundle bundle0) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot getPendingIntent");
        }
        Intent[] arr_intent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
        arr_intent[0] = new Intent(arr_intent[0]).addFlags(0x1000C000);
        return Build.VERSION.SDK_INT < 16 ? PendingIntent.getActivities(this.mSourceContext, v, arr_intent, v1) : PendingIntent.getActivities(this.mSourceContext, v, arr_intent, v1, bundle0);
    }

    @Override
    @Deprecated
    public Iterator iterator() {
        return this.mIntents.iterator();
    }

    public void startActivities() {
        this.startActivities(null);
    }

    public void startActivities(@Nullable Bundle bundle0) {
        if(this.mIntents.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] arr_intent = (Intent[])this.mIntents.toArray(new Intent[this.mIntents.size()]);
        arr_intent[0] = new Intent(arr_intent[0]).addFlags(0x1000C000);
        if(!ContextCompat.startActivities(this.mSourceContext, arr_intent, bundle0)) {
            Intent intent0 = new Intent(arr_intent[arr_intent.length - 1]);
            intent0.addFlags(0x10000000);
            this.mSourceContext.startActivity(intent0);
        }
    }
}

