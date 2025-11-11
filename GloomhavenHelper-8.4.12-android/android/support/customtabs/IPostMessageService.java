package android.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPostMessageService extends IInterface {
    public static abstract class Stub extends Binder implements IPostMessageService {
        static class Proxy implements IPostMessageService {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.IPostMessageService";
            }

            @Override  // android.support.customtabs.IPostMessageService
            public void onMessageChannelReady(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(2, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.IPostMessageService
            public void onPostMessage(ICustomTabsCallback iCustomTabsCallback0, String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.IPostMessageService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(3, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.IPostMessageService";
        static final int TRANSACTION_onMessageChannelReady = 2;
        static final int TRANSACTION_onPostMessage = 3;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.IPostMessageService");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static IPostMessageService asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.IPostMessageService");
            return iInterface0 != null && iInterface0 instanceof IPostMessageService ? ((IPostMessageService)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v != 0x5F4E5446) {
                Bundle bundle0 = null;
                switch(v) {
                    case 2: {
                        parcel0.enforceInterface("android.support.customtabs.IPostMessageService");
                        ICustomTabsCallback iCustomTabsCallback0 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onMessageChannelReady(iCustomTabsCallback0, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 3: {
                        parcel0.enforceInterface("android.support.customtabs.IPostMessageService");
                        ICustomTabsCallback iCustomTabsCallback1 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        String s = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onPostMessage(iCustomTabsCallback1, s, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    default: {
                        return super.onTransact(v, parcel0, parcel1, v1);
                    }
                }
            }
            parcel1.writeString("android.support.customtabs.IPostMessageService");
            return true;
        }
    }

    void onMessageChannelReady(ICustomTabsCallback arg1, Bundle arg2) throws RemoteException;

    void onPostMessage(ICustomTabsCallback arg1, String arg2, Bundle arg3) throws RemoteException;
}

