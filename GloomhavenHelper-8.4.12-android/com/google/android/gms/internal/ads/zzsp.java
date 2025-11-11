package com.google.android.gms.internal.ads;

public final class zzsp {
    public static final class zza extends zzdyz implements zzeaj {
        public static enum com.google.android.gms.internal.ads.zzsp.zza.zza implements zzdzb {
            UNKNOWN_EVENT_TYPE(0),
            AD_REQUEST(1),
            AD_LOADED(2),
            AD_IMPRESSION(5),
            AD_FIRST_CLICK(6),
            AD_SUBSEQUENT_CLICK(7),
            REQUEST_WILL_START(8),
            REQUEST_DID_END(9),
            REQUEST_WILL_UPDATE_SIGNALS(10),
            REQUEST_DID_UPDATE_SIGNALS(11),
            REQUEST_WILL_BUILD_URL(12),
            REQUEST_DID_BUILD_URL(13),
            REQUEST_WILL_MAKE_NETWORK_REQUEST(14),
            REQUEST_DID_RECEIVE_NETWORK_RESPONSE(15),
            REQUEST_WILL_PROCESS_RESPONSE(16),
            REQUEST_DID_PROCESS_RESPONSE(17),
            REQUEST_WILL_RENDER(18),
            REQUEST_DID_RENDER(19),
            AD_FAILED_TO_LOAD(3),
            AD_FAILED_TO_LOAD_NO_FILL(4),
            AD_FAILED_TO_LOAD_INVALID_REQUEST(100),
            AD_FAILED_TO_LOAD_NETWORK_ERROR(101),
            AD_FAILED_TO_LOAD_TIMEOUT(102),
            AD_FAILED_TO_LOAD_CANCELLED(103),
            AD_FAILED_TO_LOAD_NO_ERROR(104),
            AD_FAILED_TO_LOAD_NOT_FOUND(105),
            REQUEST_WILL_UPDATE_GMS_SIGNALS(1000),
            REQUEST_DID_UPDATE_GMS_SIGNALS(1001),
            REQUEST_FAILED_TO_UPDATE_GMS_SIGNALS(1002),
            REQUEST_FAILED_TO_BUILD_URL(1003),
            REQUEST_FAILED_TO_MAKE_NETWORK_REQUEST(1004),
            REQUEST_FAILED_TO_PROCESS_RESPONSE(1005),
            REQUEST_FAILED_TO_UPDATE_SIGNALS(1006),
            REQUEST_FAILED_TO_RENDER(1007),
            REQUEST_IS_PREFETCH(1100),
            REQUEST_SAVED_TO_CACHE(1101),
            REQUEST_LOADED_FROM_CACHE(1102),
            REQUEST_PREFETCH_INTERCEPTED(0x44F),
            REQUESTED_CACHE_KEY_FROM_SERVICE_SUCCEEDED(0x450),
            REQUESTED_CACHE_KEY_FROM_SERVICE_FAILED(1105),
            NOTIFIED_CACHE_HIT_TO_SERVICE_SUCCEEDED(1106),
            NOTIFIED_CACHE_HIT_TO_SERVICE_FAILED(1107),
            REQUEST_FAILED_TO_LOAD_FROM_CACHE(1108),
            BANNER_SIZE_INVALID(10000),
            BANNER_SIZE_VALID(10001),
            ANDROID_WEBVIEW_CRASH(10002),
            OFFLINE_UPLOAD(10003),
            DELAY_PAGE_LOAD_CANCELLED_AD(10004);

            private final int value;
            private static final zzdze zzen;

            static {
                com.google.android.gms.internal.ads.zzsp.zza.zza.zzen = new zzst();
            }

            private com.google.android.gms.internal.ads.zzsp.zza.zza(int v1) {
                this.value = v1;
            }

            @Override
            public final String toString() {
                return "<" + this.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + this.name() + '>';
            }

            @Override  // com.google.android.gms.internal.ads.zzdzb
            public final int zzaf() {
                return this.value;
            }
        }

        public static final class zzb extends com.google.android.gms.internal.ads.zzdyz.zza implements zzeaj {
            private zzb() {
                super(zza.zzbsn);
            }

            zzb(zzsr zzsr0) {
            }
        }

        private static final zza zzbsn;
        private static volatile zzeau zzdz;

        static {
            zza zzsp$zza0 = new zza();
            zza.zzbsn = zzsp$zza0;
            zzdyz.zza(zza.class, zzsp$zza0);
        }

        @Override  // com.google.android.gms.internal.ads.zzdyz
        protected final Object zza(int v, Object object0, Object object1) {
            switch(zzsr.zzdk[v - 1]) {
                case 1: {
                    return new zza();
                }
                case 2: {
                    return new zzb(null);
                }
                case 3: {
                    return zza.zza(zza.zzbsn, "\u0001\u0000", null);
                }
                case 4: {
                    return zza.zzbsn;
                }
                case 5: {
                    zzeau zzeau0 = zza.zzdz;
                    if(zzeau0 == null) {
                        Class class0 = zza.class;
                        synchronized(class0) {
                            zzeau0 = zza.zzdz;
                            if(zzeau0 == null) {
                                zzeau0 = new zzc(zza.zzbsn);
                                zza.zzdz = zzeau0;
                            }
                            return zzeau0;
                        }
                    }
                    return zzeau0;
                }
                case 6: {
                    return (byte)1;
                }
                case 7: {
                    return null;
                }
                default: {
                    throw new UnsupportedOperationException();
                }
            }
        }
    }

}

