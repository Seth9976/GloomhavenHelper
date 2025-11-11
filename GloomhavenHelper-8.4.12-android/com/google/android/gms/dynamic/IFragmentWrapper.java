package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.common.zzb;
import com.google.android.gms.internal.common.zzc;

public interface IFragmentWrapper extends IInterface {
    public static abstract class Stub extends zzb implements IFragmentWrapper {
        public static final class zza extends com.google.android.gms.internal.common.zza implements IFragmentWrapper {
            zza(IBinder iBinder0) {
                super(iBinder0, "com.google.android.gms.dynamic.IFragmentWrapper");
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final Bundle getArguments() throws RemoteException {
                Parcel parcel0 = this.zza(3, this.zza());
                Bundle bundle0 = (Bundle)zzc.zza(parcel0, Bundle.CREATOR);
                parcel0.recycle();
                return bundle0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getId() throws RemoteException {
                Parcel parcel0 = this.zza(4, this.zza());
                int v = parcel0.readInt();
                parcel0.recycle();
                return v;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getRetainInstance() throws RemoteException {
                Parcel parcel0 = this.zza(7, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final String getTag() throws RemoteException {
                Parcel parcel0 = this.zza(8, this.zza());
                String s = parcel0.readString();
                parcel0.recycle();
                return s;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final int getTargetRequestCode() throws RemoteException {
                Parcel parcel0 = this.zza(10, this.zza());
                int v = parcel0.readInt();
                parcel0.recycle();
                return v;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean getUserVisibleHint() throws RemoteException {
                Parcel parcel0 = this.zza(11, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isAdded() throws RemoteException {
                Parcel parcel0 = this.zza(13, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isDetached() throws RemoteException {
                Parcel parcel0 = this.zza(14, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isHidden() throws RemoteException {
                Parcel parcel0 = this.zza(15, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isInLayout() throws RemoteException {
                Parcel parcel0 = this.zza(16, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isRemoving() throws RemoteException {
                Parcel parcel0 = this.zza(17, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isResumed() throws RemoteException {
                Parcel parcel0 = this.zza(18, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final boolean isVisible() throws RemoteException {
                Parcel parcel0 = this.zza(19, this.zza());
                boolean z = zzc.zza(parcel0);
                parcel0.recycle();
                return z;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setHasOptionsMenu(boolean z) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.writeBoolean(parcel0, z);
                this.zzb(21, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setMenuVisibility(boolean z) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.writeBoolean(parcel0, z);
                this.zzb(22, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setRetainInstance(boolean z) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.writeBoolean(parcel0, z);
                this.zzb(23, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void setUserVisibleHint(boolean z) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.writeBoolean(parcel0, z);
                this.zzb(24, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivity(Intent intent0) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.zza(parcel0, intent0);
                this.zzb(25, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void startActivityForResult(Intent intent0, int v) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.zza(parcel0, intent0);
                parcel0.writeInt(v);
                this.zzb(26, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zza(IObjectWrapper iObjectWrapper0) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.zza(parcel0, iObjectWrapper0);
                this.zzb(20, parcel0);
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzae() throws RemoteException {
                Parcel parcel0 = this.zza(2, this.zza());
                IObjectWrapper iObjectWrapper0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel0.readStrongBinder());
                parcel0.recycle();
                return iObjectWrapper0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzaf() throws RemoteException {
                Parcel parcel0 = this.zza(5, this.zza());
                IFragmentWrapper iFragmentWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                parcel0.recycle();
                return iFragmentWrapper0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzag() throws RemoteException {
                Parcel parcel0 = this.zza(6, this.zza());
                IObjectWrapper iObjectWrapper0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel0.readStrongBinder());
                parcel0.recycle();
                return iObjectWrapper0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final IFragmentWrapper zzah() throws RemoteException {
                Parcel parcel0 = this.zza(9, this.zza());
                IFragmentWrapper iFragmentWrapper0 = Stub.asInterface(parcel0.readStrongBinder());
                parcel0.recycle();
                return iFragmentWrapper0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final IObjectWrapper zzai() throws RemoteException {
                Parcel parcel0 = this.zza(12, this.zza());
                IObjectWrapper iObjectWrapper0 = com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel0.readStrongBinder());
                parcel0.recycle();
                return iObjectWrapper0;
            }

            @Override  // com.google.android.gms.dynamic.IFragmentWrapper
            public final void zzb(IObjectWrapper iObjectWrapper0) throws RemoteException {
                Parcel parcel0 = this.zza();
                zzc.zza(parcel0, iObjectWrapper0);
                this.zzb(27, parcel0);
            }
        }

        public Stub() {
            super("com.google.android.gms.dynamic.IFragmentWrapper");
        }

        public static IFragmentWrapper asInterface(IBinder iBinder0) {
            if(iBinder0 == null) {
                return null;
            }
            IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamic.IFragmentWrapper");
            return iInterface0 instanceof IFragmentWrapper ? ((IFragmentWrapper)iInterface0) : new zza(iBinder0);
        }

        @Override  // com.google.android.gms.internal.common.zzb
        protected final boolean zza(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            switch(v) {
                case 2: {
                    IObjectWrapper iObjectWrapper0 = this.zzae();
                    parcel1.writeNoException();
                    zzc.zza(parcel1, iObjectWrapper0);
                    return true;
                }
                case 3: {
                    Bundle bundle0 = this.getArguments();
                    parcel1.writeNoException();
                    zzc.zzb(parcel1, bundle0);
                    return true;
                }
                case 4: {
                    int v2 = this.getId();
                    parcel1.writeNoException();
                    parcel1.writeInt(v2);
                    return true;
                }
                case 5: {
                    IFragmentWrapper iFragmentWrapper0 = this.zzaf();
                    parcel1.writeNoException();
                    zzc.zza(parcel1, iFragmentWrapper0);
                    return true;
                }
                case 6: {
                    IObjectWrapper iObjectWrapper1 = this.zzag();
                    parcel1.writeNoException();
                    zzc.zza(parcel1, iObjectWrapper1);
                    return true;
                }
                case 7: {
                    boolean z = this.getRetainInstance();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z);
                    return true;
                }
                case 8: {
                    String s = this.getTag();
                    parcel1.writeNoException();
                    parcel1.writeString(s);
                    return true;
                }
                case 9: {
                    IFragmentWrapper iFragmentWrapper1 = this.zzah();
                    parcel1.writeNoException();
                    zzc.zza(parcel1, iFragmentWrapper1);
                    return true;
                }
                case 10: {
                    int v3 = this.getTargetRequestCode();
                    parcel1.writeNoException();
                    parcel1.writeInt(v3);
                    return true;
                }
                case 11: {
                    boolean z1 = this.getUserVisibleHint();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z1);
                    return true;
                }
                case 12: {
                    IObjectWrapper iObjectWrapper2 = this.zzai();
                    parcel1.writeNoException();
                    zzc.zza(parcel1, iObjectWrapper2);
                    return true;
                }
                case 13: {
                    boolean z2 = this.isAdded();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z2);
                    return true;
                }
                case 14: {
                    boolean z3 = this.isDetached();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z3);
                    return true;
                }
                case 15: {
                    boolean z4 = this.isHidden();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z4);
                    return true;
                }
                case 16: {
                    boolean z5 = this.isInLayout();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z5);
                    return true;
                }
                case 17: {
                    boolean z6 = this.isRemoving();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z6);
                    return true;
                }
                case 18: {
                    boolean z7 = this.isResumed();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z7);
                    return true;
                }
                case 19: {
                    boolean z8 = this.isVisible();
                    parcel1.writeNoException();
                    zzc.writeBoolean(parcel1, z8);
                    return true;
                }
                case 20: {
                    this.zza(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                case 21: {
                    this.setHasOptionsMenu(zzc.zza(parcel0));
                    parcel1.writeNoException();
                    return true;
                }
                case 22: {
                    this.setMenuVisibility(zzc.zza(parcel0));
                    parcel1.writeNoException();
                    return true;
                }
                case 23: {
                    this.setRetainInstance(zzc.zza(parcel0));
                    parcel1.writeNoException();
                    return true;
                }
                case 24: {
                    this.setUserVisibleHint(zzc.zza(parcel0));
                    parcel1.writeNoException();
                    return true;
                }
                case 25: {
                    this.startActivity(((Intent)zzc.zza(parcel0, Intent.CREATOR)));
                    parcel1.writeNoException();
                    return true;
                }
                case 26: {
                    this.startActivityForResult(((Intent)zzc.zza(parcel0, Intent.CREATOR)), parcel0.readInt());
                    parcel1.writeNoException();
                    return true;
                }
                case 27: {
                    this.zzb(com.google.android.gms.dynamic.IObjectWrapper.Stub.asInterface(parcel0.readStrongBinder()));
                    parcel1.writeNoException();
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
    }

    Bundle getArguments() throws RemoteException;

    int getId() throws RemoteException;

    boolean getRetainInstance() throws RemoteException;

    String getTag() throws RemoteException;

    int getTargetRequestCode() throws RemoteException;

    boolean getUserVisibleHint() throws RemoteException;

    boolean isAdded() throws RemoteException;

    boolean isDetached() throws RemoteException;

    boolean isHidden() throws RemoteException;

    boolean isInLayout() throws RemoteException;

    boolean isRemoving() throws RemoteException;

    boolean isResumed() throws RemoteException;

    boolean isVisible() throws RemoteException;

    void setHasOptionsMenu(boolean arg1) throws RemoteException;

    void setMenuVisibility(boolean arg1) throws RemoteException;

    void setRetainInstance(boolean arg1) throws RemoteException;

    void setUserVisibleHint(boolean arg1) throws RemoteException;

    void startActivity(Intent arg1) throws RemoteException;

    void startActivityForResult(Intent arg1, int arg2) throws RemoteException;

    void zza(IObjectWrapper arg1) throws RemoteException;

    IObjectWrapper zzae() throws RemoteException;

    IFragmentWrapper zzaf() throws RemoteException;

    IObjectWrapper zzag() throws RemoteException;

    IFragmentWrapper zzah() throws RemoteException;

    IObjectWrapper zzai() throws RemoteException;

    void zzb(IObjectWrapper arg1) throws RemoteException;
}

