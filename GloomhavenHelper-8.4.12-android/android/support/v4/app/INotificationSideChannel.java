package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface INotificationSideChannel extends IInterface {
    public static abstract class Stub extends Binder implements INotificationSideChannel {
        static class Proxy implements INotificationSideChannel {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void cancel(String s, int v, String s1) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    parcel0.writeString(s);
                    parcel0.writeInt(v);
                    parcel0.writeString(s1);
                    this.mRemote.transact(2, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void cancelAll(String s) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    parcel0.writeString(s);
                    this.mRemote.transact(3, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.support.v4.app.INotificationSideChannel";
            }

            @Override  // android.support.v4.app.INotificationSideChannel
            public void notify(String s, int v, String s1, Notification notification0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
                    parcel0.writeString(s);
                    parcel0.writeInt(v);
                    parcel0.writeString(s1);
                    if(notification0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        notification0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(1, parcel0, null, 1);
                }
                finally {
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.v4.app.INotificationSideChannel";
        static final int TRANSACTION_cancel = 2;
        static final int TRANSACTION_cancelAll = 3;
        static final int TRANSACTION_notify = 1;

        public Stub() {
            this.attachInterface(this, "android.support.v4.app.INotificationSideChannel");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static INotificationSideChannel asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.v4.app.INotificationSideChannel");
            return iInterface0 != null && iInterface0 instanceof INotificationSideChannel ? ((INotificationSideChannel)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v != 0x5F4E5446) {
                switch(v) {
                    case 1: {
                        parcel0.enforceInterface("android.support.v4.app.INotificationSideChannel");
                        this.notify(parcel0.readString(), parcel0.readInt(), parcel0.readString(), (parcel0.readInt() == 0 ? null : ((Notification)Notification.CREATOR.createFromParcel(parcel0))));
                        return true;
                    }
                    case 2: {
                        parcel0.enforceInterface("android.support.v4.app.INotificationSideChannel");
                        this.cancel(parcel0.readString(), parcel0.readInt(), parcel0.readString());
                        return true;
                    }
                    case 3: {
                        parcel0.enforceInterface("android.support.v4.app.INotificationSideChannel");
                        this.cancelAll(parcel0.readString());
                        return true;
                    }
                    default: {
                        return super.onTransact(v, parcel0, parcel1, v1);
                    }
                }
            }
            parcel1.writeString("android.support.v4.app.INotificationSideChannel");
            return true;
        }
    }

    void cancel(String arg1, int arg2, String arg3) throws RemoteException;

    void cancelAll(String arg1) throws RemoteException;

    void notify(String arg1, int arg2, String arg3, Notification arg4) throws RemoteException;
}

