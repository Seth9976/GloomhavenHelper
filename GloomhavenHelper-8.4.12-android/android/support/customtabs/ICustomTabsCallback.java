package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICustomTabsCallback extends IInterface {
    public static abstract class Stub extends Binder implements ICustomTabsCallback {
        static class Proxy implements ICustomTabsCallback {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void extraCallback(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
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

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.ICustomTabsCallback";
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onMessageChannelReady(Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(4, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onNavigationEvent(int v, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeInt(v);
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

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onPostMessage(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(5, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsCallback
            public void onRelationshipValidationResult(int v, Uri uri0, boolean z, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsCallback");
                    parcel0.writeInt(v);
                    if(uri0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        uri0.writeToParcel(parcel0, 0);
                    }
                    parcel0.writeInt((z ? 1 : 0));
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(6, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsCallback";
        static final int TRANSACTION_extraCallback = 3;
        static final int TRANSACTION_onMessageChannelReady = 4;
        static final int TRANSACTION_onNavigationEvent = 2;
        static final int TRANSACTION_onPostMessage = 5;
        static final int TRANSACTION_onRelationshipValidationResult = 6;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.ICustomTabsCallback");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static ICustomTabsCallback asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.ICustomTabsCallback");
            return iInterface0 != null && iInterface0 instanceof ICustomTabsCallback ? ((ICustomTabsCallback)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v != 0x5F4E5446) {
                Bundle bundle0 = null;
                switch(v) {
                    case 2: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        int v2 = parcel0.readInt();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onNavigationEvent(v2, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 3: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        String s = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.extraCallback(s, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 4: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onMessageChannelReady(bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 5: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        String s1 = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onPostMessage(s1, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    case 6: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsCallback");
                        int v3 = parcel0.readInt();
                        Uri uri0 = parcel0.readInt() == 0 ? null : ((Uri)Uri.CREATOR.createFromParcel(parcel0));
                        boolean z = parcel0.readInt() != 0;
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        this.onRelationshipValidationResult(v3, uri0, z, bundle0);
                        parcel1.writeNoException();
                        return true;
                    }
                    default: {
                        return super.onTransact(v, parcel0, parcel1, v1);
                    }
                }
            }
            parcel1.writeString("android.support.customtabs.ICustomTabsCallback");
            return true;
        }
    }

    void extraCallback(String arg1, Bundle arg2) throws RemoteException;

    void onMessageChannelReady(Bundle arg1) throws RemoteException;

    void onNavigationEvent(int arg1, Bundle arg2) throws RemoteException;

    void onPostMessage(String arg1, Bundle arg2) throws RemoteException;

    void onRelationshipValidationResult(int arg1, Uri arg2, boolean arg3, Bundle arg4) throws RemoteException;
}

