package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.annotation.KeepForSdk;

public interface IGmsServiceBroker extends IInterface {
    public static abstract class Stub extends Binder implements IGmsServiceBroker {
        static final class zza implements IGmsServiceBroker {
            private final IBinder zza;

            zza(IBinder iBinder0) {
                this.zza = iBinder0;
            }

            @Override  // android.os.IInterface
            public final IBinder asBinder() {
                return this.zza;
            }

            @Override  // com.google.android.gms.common.internal.IGmsServiceBroker
            public final void getService(IGmsCallbacks iGmsCallbacks0, GetServiceRequest getServiceRequest0) throws RemoteException {
                Parcel parcel0 = Parcel.obtain();
                Parcel parcel1 = Parcel.obtain();
                try {
                    parcel0.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    parcel0.writeStrongBinder((iGmsCallbacks0 == null ? null : iGmsCallbacks0.asBinder()));
                    if(getServiceRequest0 == null) {
                        parcel0.writeInt(0);
                    }
                    else {
                        parcel0.writeInt(1);
                        getServiceRequest0.writeToParcel(parcel0, 0);
                    }
                    this.zza.transact(46, parcel0, parcel1, 0);
                    parcel1.readException();
                }
                finally {
                    parcel1.recycle();
                    parcel0.recycle();
                }
            }
        }

        public Stub() {
            this.attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        @Override  // android.os.IInterface
        @KeepForSdk
        public IBinder asBinder() {
            return this;
        }

        @Override  // android.os.Binder
        public boolean onTransact(int v, Parcel parcel0, Parcel parcel1, int v1) throws RemoteException {
            IGmsCallbacks iGmsCallbacks0;
            if(v > 0xFFFFFF) {
                return super.onTransact(v, parcel0, parcel1, v1);
            }
            parcel0.enforceInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            IBinder iBinder0 = parcel0.readStrongBinder();
            GetServiceRequest getServiceRequest0 = null;
            if(iBinder0 == null) {
                iGmsCallbacks0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.common.internal.IGmsCallbacks");
                iGmsCallbacks0 = iInterface0 instanceof IGmsCallbacks ? ((IGmsCallbacks)iInterface0) : new zzl(iBinder0);
            }
            switch(v) {
                case 46: {
                    if(parcel0.readInt() != 0) {
                        getServiceRequest0 = (GetServiceRequest)GetServiceRequest.CREATOR.createFromParcel(parcel0);
                    }
                    this.getService(iGmsCallbacks0, getServiceRequest0);
                    parcel1.writeNoException();
                    return true;
                }
                case 0x2F: {
                    if(parcel0.readInt() != 0) {
                        zzr.CREATOR.createFromParcel(parcel0);
                    }
                    throw new UnsupportedOperationException();
                }
                default: {
                    parcel0.readInt();
                    if(v != 4) {
                        parcel0.readString();
                    }
                    switch(v) {
                        case 1: {
                            parcel0.readString();
                            parcel0.createStringArray();
                            parcel0.readString();
                            if(parcel0.readInt() != 0) {
                                Bundle.CREATOR.createFromParcel(parcel0);
                            }
                            break;
                        }
                        case 9: {
                            parcel0.readString();
                            parcel0.createStringArray();
                            parcel0.readString();
                            parcel0.readStrongBinder();
                            parcel0.readString();
                            if(parcel0.readInt() != 0) {
                                Bundle.CREATOR.createFromParcel(parcel0);
                            }
                            break;
                        }
                        case 10: {
                            parcel0.readString();
                            parcel0.createStringArray();
                            break;
                        }
                        case 19: {
                            parcel0.readStrongBinder();
                            if(parcel0.readInt() != 0) {
                                Bundle.CREATOR.createFromParcel(parcel0);
                            }
                            break;
                        }
                        case 20: 
                        case 30: {
                            parcel0.createStringArray();
                            parcel0.readString();
                            if(parcel0.readInt() != 0) {
                                Bundle.CREATOR.createFromParcel(parcel0);
                            }
                            break;
                        }
                        case 34: {
                            parcel0.readString();
                            break;
                        }
                        case 2: 
                        case 5: 
                        case 6: 
                        case 7: 
                        case 8: 
                        case 11: 
                        case 12: 
                        case 13: 
                        case 14: 
                        case 15: 
                        case 16: 
                        case 17: 
                        case 18: 
                        case 23: 
                        case 25: 
                        case 27: 
                        case 41: 
                        case 43: {
                        label_26:
                            if(parcel0.readInt() != 0) {
                                Bundle.CREATOR.createFromParcel(parcel0);
                            }
                            break;
                        }
                        default: {
                            if(v == 37 || v == 38) {
                                goto label_26;
                            }
                        }
                    }
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

    @KeepForSdk
    void getService(IGmsCallbacks arg1, GetServiceRequest arg2) throws RemoteException;
}

