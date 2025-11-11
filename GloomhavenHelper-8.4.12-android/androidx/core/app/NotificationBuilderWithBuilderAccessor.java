package androidx.core.app;

import android.app.Notification.Builder;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

@RestrictTo({Scope.LIBRARY_GROUP})
public interface NotificationBuilderWithBuilderAccessor {
    Notification.Builder getBuilder();
}

