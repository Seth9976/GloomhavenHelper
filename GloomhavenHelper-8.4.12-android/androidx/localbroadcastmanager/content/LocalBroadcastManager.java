package androidx.localbroadcastmanager.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import jeb.synthetic.FIN;

public final class LocalBroadcastManager {
    static final class BroadcastRecord {
        final Intent intent;
        final ArrayList receivers;

        BroadcastRecord(Intent intent0, ArrayList arrayList0) {
            this.intent = intent0;
            this.receivers = arrayList0;
        }
    }

    static final class ReceiverRecord {
        boolean broadcasting;
        boolean dead;
        final IntentFilter filter;
        final BroadcastReceiver receiver;

        ReceiverRecord(IntentFilter intentFilter0, BroadcastReceiver broadcastReceiver0) {
            this.filter = intentFilter0;
            this.receiver = broadcastReceiver0;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder(0x80);
            stringBuilder0.append("Receiver{");
            stringBuilder0.append(this.receiver);
            stringBuilder0.append(" filter=");
            stringBuilder0.append(this.filter);
            if(this.dead) {
                stringBuilder0.append(" DEAD");
            }
            stringBuilder0.append("}");
            return stringBuilder0.toString();
        }
    }

    private static final boolean DEBUG = false;
    static final int MSG_EXEC_PENDING_BROADCASTS = 1;
    private static final String TAG = "LocalBroadcastManager";
    private final HashMap mActions;
    private final Context mAppContext;
    private final Handler mHandler;
    private static LocalBroadcastManager mInstance;
    private static final Object mLock;
    private final ArrayList mPendingBroadcasts;
    private final HashMap mReceivers;

    static {
        LocalBroadcastManager.mLock = new Object();
    }

    private LocalBroadcastManager(Context context0) {
        this.mReceivers = new HashMap();
        this.mActions = new HashMap();
        this.mPendingBroadcasts = new ArrayList();
        this.mAppContext = context0;
        this.mHandler = new Handler(context0.getMainLooper()) {
            @Override  // android.os.Handler
            public void handleMessage(Message message0) {
                if(message0.what != 1) {
                    super.handleMessage(message0);
                    return;
                }
                LocalBroadcastManager.this.executePendingBroadcasts();
            }
        };
    }

    void executePendingBroadcasts() {
        int v;
        HashMap hashMap0;
        while(true) {
            hashMap0 = this.mReceivers;
            __monitor_enter(hashMap0);
            v = FIN.finallyOpen$NT();
            int v1 = this.mPendingBroadcasts.size();
            if(v1 <= 0) {
                break;
            }
            BroadcastRecord[] arr_localBroadcastManager$BroadcastRecord = new BroadcastRecord[v1];
            this.mPendingBroadcasts.toArray(arr_localBroadcastManager$BroadcastRecord);
            this.mPendingBroadcasts.clear();
            FIN.finallyExec$NT(v);
            for(int v2 = 0; v2 < arr_localBroadcastManager$BroadcastRecord.length; ++v2) {
                BroadcastRecord localBroadcastManager$BroadcastRecord0 = arr_localBroadcastManager$BroadcastRecord[v2];
                int v3 = localBroadcastManager$BroadcastRecord0.receivers.size();
                for(int v4 = 0; v4 < v3; ++v4) {
                    ReceiverRecord localBroadcastManager$ReceiverRecord0 = (ReceiverRecord)localBroadcastManager$BroadcastRecord0.receivers.get(v4);
                    if(!localBroadcastManager$ReceiverRecord0.dead) {
                        localBroadcastManager$ReceiverRecord0.receiver.onReceive(this.mAppContext, localBroadcastManager$BroadcastRecord0.intent);
                    }
                }
            }
        }
        FIN.finallyCodeBegin$NT(v);
        __monitor_exit(hashMap0);
        FIN.finallyCodeEnd$NT(v);
    }

    @NonNull
    public static LocalBroadcastManager getInstance(@NonNull Context context0) {
        synchronized(LocalBroadcastManager.mLock) {
            if(LocalBroadcastManager.mInstance == null) {
                LocalBroadcastManager.mInstance = new LocalBroadcastManager(context0.getApplicationContext());
            }
            return LocalBroadcastManager.mInstance;
        }
    }

    public void registerReceiver(@NonNull BroadcastReceiver broadcastReceiver0, @NonNull IntentFilter intentFilter0) {
        synchronized(this.mReceivers) {
            ReceiverRecord localBroadcastManager$ReceiverRecord0 = new ReceiverRecord(intentFilter0, broadcastReceiver0);
            ArrayList arrayList0 = (ArrayList)this.mReceivers.get(broadcastReceiver0);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList(1);
                this.mReceivers.put(broadcastReceiver0, arrayList0);
            }
            arrayList0.add(localBroadcastManager$ReceiverRecord0);
            for(int v1 = 0; v1 < intentFilter0.countActions(); ++v1) {
                String s = intentFilter0.getAction(v1);
                ArrayList arrayList1 = (ArrayList)this.mActions.get(s);
                if(arrayList1 == null) {
                    arrayList1 = new ArrayList(1);
                    this.mActions.put(s, arrayList1);
                }
                arrayList1.add(localBroadcastManager$ReceiverRecord0);
            }
        }
    }

    public boolean sendBroadcast(@NonNull Intent intent0) {
        ArrayList arrayList3;
        ArrayList arrayList2;
        int v2;
        String s4;
        synchronized(this.mReceivers) {
            String s = intent0.getAction();
            String s1 = intent0.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
            Uri uri0 = intent0.getData();
            String s2 = intent0.getScheme();
            Set set0 = intent0.getCategories();
            boolean z = (intent0.getFlags() & 8) != 0;
            if(z) {
                Log.v("LocalBroadcastManager", "Resolving type " + s1 + " scheme " + s2 + " of intent " + intent0);
            }
            String s3 = intent0.getAction();
            ArrayList arrayList0 = (ArrayList)this.mActions.get(s3);
            if(arrayList0 != null) {
                if(z) {
                    Log.v("LocalBroadcastManager", "Action list: " + arrayList0);
                }
                ArrayList arrayList1 = null;
                int v1 = 0;
                while(v1 < arrayList0.size()) {
                    ReceiverRecord localBroadcastManager$ReceiverRecord0 = (ReceiverRecord)arrayList0.get(v1);
                    if(z) {
                        Log.v("LocalBroadcastManager", "Matching against filter " + localBroadcastManager$ReceiverRecord0.filter);
                    }
                    if(!localBroadcastManager$ReceiverRecord0.broadcasting) {
                        arrayList3 = arrayList1;
                        v2 = v1;
                        arrayList2 = arrayList0;
                        int v3 = localBroadcastManager$ReceiverRecord0.filter.match(s, s1, s2, uri0, set0, "LocalBroadcastManager");
                        if(v3 >= 0) {
                            if(z) {
                                Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(v3));
                            }
                            arrayList1 = arrayList3 == null ? new ArrayList() : arrayList3;
                            arrayList1.add(localBroadcastManager$ReceiverRecord0);
                            localBroadcastManager$ReceiverRecord0.broadcasting = true;
                            goto label_57;
                        }
                        else if(z) {
                            switch(v3) {
                                case -4: {
                                    s4 = "category";
                                    break;
                                }
                                case -3: {
                                    s4 = "action";
                                    break;
                                }
                                case -2: {
                                    s4 = "data";
                                    break;
                                }
                                case -1: {
                                    s4 = "type";
                                    break;
                                }
                                default: {
                                    s4 = "unknown reason";
                                }
                            }
                            Log.v("LocalBroadcastManager", "  Filter did not match: " + s4);
                        }
                    }
                    else if(z) {
                        Log.v("LocalBroadcastManager", "  Filter\'s target already added");
                        v2 = v1;
                        arrayList2 = arrayList0;
                        arrayList3 = arrayList1;
                    }
                    else {
                        v2 = v1;
                        arrayList2 = arrayList0;
                        arrayList3 = arrayList1;
                    }
                    arrayList1 = arrayList3;
                label_57:
                    v1 = v2 + 1;
                    arrayList0 = arrayList2;
                }
                if(arrayList1 != null) {
                    for(int v4 = 0; v4 < arrayList1.size(); ++v4) {
                        ((ReceiverRecord)arrayList1.get(v4)).broadcasting = false;
                    }
                    BroadcastRecord localBroadcastManager$BroadcastRecord0 = new BroadcastRecord(intent0, arrayList1);
                    this.mPendingBroadcasts.add(localBroadcastManager$BroadcastRecord0);
                    if(!this.mHandler.hasMessages(1)) {
                        this.mHandler.sendEmptyMessage(1);
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public void sendBroadcastSync(@NonNull Intent intent0) {
        if(this.sendBroadcast(intent0)) {
            this.executePendingBroadcasts();
        }
    }

    public void unregisterReceiver(@NonNull BroadcastReceiver broadcastReceiver0) {
        synchronized(this.mReceivers) {
            ArrayList arrayList0 = (ArrayList)this.mReceivers.remove(broadcastReceiver0);
            if(arrayList0 == null) {
                return;
            }
            for(int v1 = arrayList0.size() - 1; v1 >= 0; --v1) {
                ReceiverRecord localBroadcastManager$ReceiverRecord0 = (ReceiverRecord)arrayList0.get(v1);
                localBroadcastManager$ReceiverRecord0.dead = true;
                for(int v2 = 0; v2 < localBroadcastManager$ReceiverRecord0.filter.countActions(); ++v2) {
                    String s = localBroadcastManager$ReceiverRecord0.filter.getAction(v2);
                    ArrayList arrayList1 = (ArrayList)this.mActions.get(s);
                    if(arrayList1 != null) {
                        for(int v3 = arrayList1.size() - 1; v3 >= 0; --v3) {
                            ReceiverRecord localBroadcastManager$ReceiverRecord1 = (ReceiverRecord)arrayList1.get(v3);
                            if(localBroadcastManager$ReceiverRecord1.receiver == broadcastReceiver0) {
                                localBroadcastManager$ReceiverRecord1.dead = true;
                                arrayList1.remove(v3);
                            }
                        }
                        if(arrayList1.size() <= 0) {
                            this.mActions.remove(s);
                        }
                    }
                }
            }
        }
    }
}

