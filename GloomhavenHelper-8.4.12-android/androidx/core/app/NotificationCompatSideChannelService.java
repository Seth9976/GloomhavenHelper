package androidx.core.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.INotificationSideChannel.Stub;

public abstract class NotificationCompatSideChannelService extends Service {
    class NotificationSideChannelStub extends Stub {
        @Override  // android.support.v4.app.INotificationSideChannel
        public void cancel(String s, int v, String s1) throws RemoteException {
            int v1 = NotificationSideChannelStub.getCallingUid();
            NotificationCompatSideChannelService.this.checkPermission(v1, s);
            long v2 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancel(s, v, s1);
            }
            finally {
                NotificationSideChannelStub.restoreCallingIdentity(v2);
            }
        }

        @Override  // android.support.v4.app.INotificationSideChannel
        public void cancelAll(String s) {
            int v = NotificationSideChannelStub.getCallingUid();
            NotificationCompatSideChannelService.this.checkPermission(v, s);
            long v1 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.cancelAll(s);
            }
            finally {
                NotificationSideChannelStub.restoreCallingIdentity(v1);
            }
        }

        @Override  // android.support.v4.app.INotificationSideChannel
        public void notify(String s, int v, String s1, Notification notification0) throws RemoteException {
            int v1 = NotificationSideChannelStub.getCallingUid();
            NotificationCompatSideChannelService.this.checkPermission(v1, s);
            long v2 = NotificationSideChannelStub.clearCallingIdentity();
            try {
                NotificationCompatSideChannelService.this.notify(s, v, s1, notification0);
            }
            finally {
                NotificationSideChannelStub.restoreCallingIdentity(v2);
            }
        }
    }

    public abstract void cancel(String arg1, int arg2, String arg3);

    public abstract void cancelAll(String arg1);

    void checkPermission(int v, String s) {
        String[] arr_s = this.getPackageManager().getPackagesForUid(v);
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            if(arr_s[v1].equals(s)) {
                return;
            }
        }
        throw new SecurityException("NotificationSideChannelService: Uid " + v + " is not authorized for package " + s);
    }

    public abstract void notify(String arg1, int arg2, String arg3, Notification arg4);

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        return !intent0.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL") || Build.VERSION.SDK_INT > 19 ? null : new NotificationSideChannelStub(this);
    }
}

