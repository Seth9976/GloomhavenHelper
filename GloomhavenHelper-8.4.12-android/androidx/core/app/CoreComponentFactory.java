package androidx.core.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RequiresApi(api = 28)
@RestrictTo({Scope.LIBRARY_GROUP})
public class CoreComponentFactory extends AppComponentFactory {
    @RestrictTo({Scope.LIBRARY_GROUP})
    public interface CompatWrapped {
        Object getWrapper();
    }

    private static final String TAG = "CoreComponentFactory";

    static Object checkCompatWrapper(Object object0) {
        if(object0 instanceof CompatWrapped) {
            Object object1 = ((CompatWrapped)object0).getWrapper();
            return object1 == null ? object0 : object1;
        }
        return object0;
    }

    @Override  // android.app.AppComponentFactory
    public Activity instantiateActivity(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Activity)CoreComponentFactory.checkCompatWrapper(super.instantiateActivity(classLoader0, s, intent0));
    }

    @Override  // android.app.AppComponentFactory
    public Application instantiateApplication(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Application)CoreComponentFactory.checkCompatWrapper(super.instantiateApplication(classLoader0, s));
    }

    @Override  // android.app.AppComponentFactory
    public ContentProvider instantiateProvider(ClassLoader classLoader0, String s) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (ContentProvider)CoreComponentFactory.checkCompatWrapper(super.instantiateProvider(classLoader0, s));
    }

    @Override  // android.app.AppComponentFactory
    public BroadcastReceiver instantiateReceiver(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (BroadcastReceiver)CoreComponentFactory.checkCompatWrapper(super.instantiateReceiver(classLoader0, s, intent0));
    }

    @Override  // android.app.AppComponentFactory
    public Service instantiateService(ClassLoader classLoader0, String s, Intent intent0) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return (Service)CoreComponentFactory.checkCompatWrapper(super.instantiateService(classLoader0, s, intent0));
    }
}

