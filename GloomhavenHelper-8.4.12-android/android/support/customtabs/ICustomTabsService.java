package android.support.customtabs;

import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface ICustomTabsService extends IInterface {
    public static abstract class Stub extends Binder implements ICustomTabsService {
        static class Proxy implements ICustomTabsService {
            private IBinder mRemote;

            Proxy(IBinder iBinder0) {
                this.mRemote = iBinder0;
            }

            @Override  // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public Bundle extraCommand(String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
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
                    return parcel1.readInt() == 0 ? null : ((Bundle)Bundle.CREATOR.createFromParcel(parcel1));
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            public String getInterfaceDescriptor() {
                return "android.support.customtabs.ICustomTabsService";
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean mayLaunchUrl(ICustomTabsCallback iCustomTabsCallback0, Uri uri0, Bundle bundle0, List list0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    boolean z = true;
                    if(uri0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        uri0.writeToParcel(parcel0, 0);
                    }
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    parcel0.writeTypedList(list0);
                    this.mRemote.transact(4, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() == 0) {
                        z = false;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean newSession(ICustomTabsCallback iCustomTabsCallback0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    boolean z = false;
                    this.mRemote.transact(3, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() != 0) {
                        z = true;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public int postMessage(ICustomTabsCallback iCustomTabsCallback0, String s, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    parcel0.writeString(s);
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(8, parcel0, parcel1, 0);
                    parcel1.readException();
                    return parcel1.readInt();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean requestPostMessageChannel(ICustomTabsCallback iCustomTabsCallback0, Uri uri0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    boolean z = true;
                    if(uri0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        uri0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(7, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() == 0) {
                        z = false;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean updateVisuals(ICustomTabsCallback iCustomTabsCallback0, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    boolean z = true;
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(6, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() == 0) {
                        z = false;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean validateRelationship(ICustomTabsCallback iCustomTabsCallback0, int v, Uri uri0, Bundle bundle0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeStrongBinder((iCustomTabsCallback0 == null ? null : iCustomTabsCallback0.asBinder()));
                    parcel0.writeInt(v);
                    boolean z = true;
                    if(uri0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        uri0.writeToParcel(parcel0, 0);
                    }
                    if(bundle0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        bundle0.writeToParcel(parcel0, 0);
                    }
                    this.mRemote.transact(9, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() == 0) {
                        z = false;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }

            @Override  // android.support.customtabs.ICustomTabsService
            public boolean warmup(long v) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");
                    parcel0.writeLong(v);
                    boolean z = false;
                    this.mRemote.transact(2, parcel0, parcel1, 0);
                    parcel1.readException();
                    if(parcel1.readInt() != 0) {
                        z = true;
                    }
                    return z;
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        private static final String DESCRIPTOR = "android.support.customtabs.ICustomTabsService";
        static final int TRANSACTION_extraCommand = 5;
        static final int TRANSACTION_mayLaunchUrl = 4;
        static final int TRANSACTION_newSession = 3;
        static final int TRANSACTION_postMessage = 8;
        static final int TRANSACTION_requestPostMessageChannel = 7;
        static final int TRANSACTION_updateVisuals = 6;
        static final int TRANSACTION_validateRelationship = 9;
        static final int TRANSACTION_warmup = 2;

        public Stub() {
            this.attachInterface(this, "android.support.customtabs.ICustomTabsService");
        }

        @Override  // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static ICustomTabsService asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("android.support.customtabs.ICustomTabsService");
            return iInterface0 != null && iInterface0 instanceof ICustomTabsService ? ((ICustomTabsService)iInterface0) : new Proxy(iBinder0);
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            if(v != 0x5F4E5446) {
                Bundle bundle0 = null;
                switch(v) {
                    case 2: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        boolean z = this.warmup(parcel0.readLong());
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z));
                        return true;
                    }
                    case 3: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        boolean z1 = this.newSession(android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder()));
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z1));
                        return true;
                    }
                    case 4: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        ICustomTabsCallback iCustomTabsCallback0 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        Uri uri0 = parcel0.readInt() == 0 ? null : ((Uri)Uri.CREATOR.createFromParcel(parcel0));
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        boolean z2 = this.mayLaunchUrl(iCustomTabsCallback0, uri0, bundle0, parcel0.createTypedArrayList(Bundle.CREATOR));
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z2));
                        return true;
                    }
                    case 5: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        String s = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        Bundle bundle1 = this.extraCommand(s, bundle0);
                        parcel1.writeNoException();
                        if(bundle1 != null) {
                            parcel1.writeInt(1);
                            bundle1.writeToParcel(parcel1, 1);
                            return true;
                        }
                        parcel1.writeInt(0);
                        return true;
                    }
                    case 6: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        ICustomTabsCallback iCustomTabsCallback1 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        boolean z3 = this.updateVisuals(iCustomTabsCallback1, bundle0);
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z3));
                        return true;
                    }
                    case 7: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        ICustomTabsCallback iCustomTabsCallback2 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Uri)Uri.CREATOR.createFromParcel(parcel0);
                        }
                        boolean z4 = this.requestPostMessageChannel(iCustomTabsCallback2, ((Uri)bundle0));
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z4));
                        return true;
                    }
                    case 8: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        ICustomTabsCallback iCustomTabsCallback3 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        String s1 = parcel0.readString();
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        int v2 = this.postMessage(iCustomTabsCallback3, s1, bundle0);
                        parcel1.writeNoException();
                        parcel1.writeInt(v2);
                        return true;
                    }
                    case 9: {
                        parcel0.enforceInterface("android.support.customtabs.ICustomTabsService");
                        ICustomTabsCallback iCustomTabsCallback4 = android.support.customtabs.ICustomTabsCallback.Stub.asInterface(parcel0.readStrongBinder());
                        int v3 = parcel0.readInt();
                        Uri uri1 = parcel0.readInt() == 0 ? null : ((Uri)Uri.CREATOR.createFromParcel(parcel0));
                        if(parcel0.readInt() != 0) {
                            bundle0 = (Bundle)Bundle.CREATOR.createFromParcel(parcel0);
                        }
                        boolean z5 = this.validateRelationship(iCustomTabsCallback4, v3, uri1, bundle0);
                        parcel1.writeNoException();
                        parcel1.writeInt(((int)z5));
                        return true;
                    }
                    default: {
                        return super.onTransact(v, parcel0, parcel1, v1);
                    }
                }
            }
            parcel1.writeString("android.support.customtabs.ICustomTabsService");
            return true;
        }
    }

    Bundle extraCommand(String arg1, Bundle arg2) throws RemoteException;

    boolean mayLaunchUrl(ICustomTabsCallback arg1, Uri arg2, Bundle arg3, List arg4) throws RemoteException;

    boolean newSession(ICustomTabsCallback arg1) throws RemoteException;

    int postMessage(ICustomTabsCallback arg1, String arg2, Bundle arg3) throws RemoteException;

    boolean requestPostMessageChannel(ICustomTabsCallback arg1, Uri arg2) throws RemoteException;

    boolean updateVisuals(ICustomTabsCallback arg1, Bundle arg2) throws RemoteException;

    boolean validateRelationship(ICustomTabsCallback arg1, int arg2, Uri arg3, Bundle arg4) throws RemoteException;

    boolean warmup(long arg1) throws RemoteException;
}

