package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.play_billing.zza;

public class ProxyBillingActivity extends Activity {
    @Nullable
    private ResultReceiver zza;
    private boolean zzb;

    @Override  // android.app.Activity
    protected void onActivityResult(int v, int v1, @Nullable Intent intent0) {
        super.onActivityResult(v, v1, intent0);
        if(v == 100) {
            int v2 = zza.zzg(intent0, "ProxyBillingActivity").getResponseCode();
            if(v1 != -1) {
                zza.zzk("ProxyBillingActivity", "Activity finished with resultCode " + v1 + " and billing\'s responseCode: " + v2);
            }
            else if(v2 != 0) {
                zza.zzk("ProxyBillingActivity", "Activity finished with resultCode " + -1 + " and billing\'s responseCode: " + v2);
            }
            else {
                v2 = 0;
            }
            ResultReceiver resultReceiver0 = this.zza;
            if(resultReceiver0 == null) {
                Intent intent1 = this.zza();
                if(intent0 != null) {
                    if(intent0.getExtras() == null) {
                        zza.zzk("ProxyBillingActivity", "Got null bundle!");
                        intent1.putExtra("RESPONSE_CODE", 6);
                        intent1.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    }
                    else {
                        intent1.putExtras(intent0.getExtras());
                    }
                }
                this.sendBroadcast(intent1);
            }
            else {
                resultReceiver0.send(v2, (intent0 == null ? null : intent0.getExtras()));
            }
        }
        else {
            zza.zzk("ProxyBillingActivity", "Got onActivityResult with wrong requestCode: " + v + "; skipping...");
        }
        this.zzb = false;
        this.finish();
    }

    @Override  // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle0) {
        PendingIntent pendingIntent0;
        super.onCreate(bundle0);
        if(bundle0 == null) {
            zza.zzj("ProxyBillingActivity", "Launching Play Store billing flow");
            if(this.getIntent().hasExtra("BUY_INTENT")) {
                pendingIntent0 = (PendingIntent)this.getIntent().getParcelableExtra("BUY_INTENT");
            }
            else if(this.getIntent().hasExtra("SUBS_MANAGEMENT_INTENT")) {
                pendingIntent0 = (PendingIntent)this.getIntent().getParcelableExtra("SUBS_MANAGEMENT_INTENT");
                this.zza = (ResultReceiver)this.getIntent().getParcelableExtra("result_receiver");
            }
            else {
                pendingIntent0 = null;
            }
            try {
                this.zzb = true;
                this.startIntentSenderForResult(pendingIntent0.getIntentSender(), 100, new Intent(), 0, 0, 0);
            }
            catch(IntentSender.SendIntentException intentSender$SendIntentException0) {
                zza.zzk("ProxyBillingActivity", "Got exception while trying to start a purchase flow: " + intentSender$SendIntentException0);
                ResultReceiver resultReceiver0 = this.zza;
                if(resultReceiver0 == null) {
                    Intent intent0 = this.zza();
                    intent0.putExtra("RESPONSE_CODE", 6);
                    intent0.putExtra("DEBUG_MESSAGE", "An internal error occurred.");
                    this.sendBroadcast(intent0);
                }
                else {
                    resultReceiver0.send(6, null);
                }
                this.zzb = false;
                this.finish();
            }
            return;
        }
        zza.zzj("ProxyBillingActivity", "Launching Play Store billing flow from savedInstanceState");
        this.zzb = bundle0.getBoolean("send_cancelled_broadcast_if_finished", false);
        if(bundle0.containsKey("result_receiver")) {
            this.zza = (ResultReceiver)bundle0.getParcelable("result_receiver");
        }
    }

    @Override  // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if(!this.isFinishing()) {
            return;
        }
        if(!this.zzb) {
            return;
        }
        Intent intent0 = this.zza();
        intent0.putExtra("RESPONSE_CODE", 1);
        intent0.putExtra("DEBUG_MESSAGE", "Billing dialog closed.");
        this.sendBroadcast(intent0);
    }

    @Override  // android.app.Activity
    protected void onSaveInstanceState(@NonNull Bundle bundle0) {
        ResultReceiver resultReceiver0 = this.zza;
        if(resultReceiver0 != null) {
            bundle0.putParcelable("result_receiver", resultReceiver0);
        }
        bundle0.putBoolean("send_cancelled_broadcast_if_finished", this.zzb);
    }

    private final Intent zza() {
        Intent intent0 = new Intent("com.android.vending.billing.PURCHASES_UPDATED");
        intent0.setPackage("com.esotericsoftware.gloomhavenhelper");
        return intent0;
    }
}

