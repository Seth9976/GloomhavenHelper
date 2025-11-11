package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;

@RequiresApi(28)
public class AppComponentFactory extends android.app.AppComponentFactory {
    @Override  // android.app.AppComponentFactory
    public final Activity instantiateActivity(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity)CoreComponentFactory.checkCompatWrapper(this.instantiateActivityCompat(classLoader0, s, intent0));
    }

    @NonNull
    public Activity instantiateActivityCompat(@NonNull ClassLoader classLoader0, @NonNull String s, @Nullable Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Activity)classLoader0.loadClass(s).getDeclaredConstructor().newInstance();
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final Application instantiateApplication(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application)CoreComponentFactory.checkCompatWrapper(this.instantiateApplicationCompat(classLoader0, s));
    }

    @NonNull
    public Application instantiateApplicationCompat(@NonNull ClassLoader classLoader0, @NonNull String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Application)classLoader0.loadClass(s).getDeclaredConstructor().newInstance();
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final ContentProvider instantiateProvider(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider)CoreComponentFactory.checkCompatWrapper(this.instantiateProviderCompat(classLoader0, s));
    }

    @NonNull
    public ContentProvider instantiateProviderCompat(@NonNull ClassLoader classLoader0, @NonNull String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (ContentProvider)classLoader0.loadClass(s).getDeclaredConstructor().newInstance();
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final BroadcastReceiver instantiateReceiver(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver)CoreComponentFactory.checkCompatWrapper(this.instantiateReceiverCompat(classLoader0, s, intent0));
    }

    @NonNull
    public BroadcastReceiver instantiateReceiverCompat(@NonNull ClassLoader classLoader0, @NonNull String s, @Nullable Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (BroadcastReceiver)classLoader0.loadClass(s).getDeclaredConstructor().newInstance();
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }

    @Override  // android.app.AppComponentFactory
    public final Service instantiateService(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service)CoreComponentFactory.checkCompatWrapper(this.instantiateServiceCompat(classLoader0, s, intent0));
    }

    @NonNull
    public Service instantiateServiceCompat(@NonNull ClassLoader classLoader0, @NonNull String s, @Nullable Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        try {
            return (Service)classLoader0.loadClass(s).getDeclaredConstructor().newInstance();
        }
        catch(InvocationTargetException | NoSuchMethodException invocationTargetException0) {
            throw new RuntimeException("Couldn\'t call constructor", invocationTargetException0);
        }
    }
}

