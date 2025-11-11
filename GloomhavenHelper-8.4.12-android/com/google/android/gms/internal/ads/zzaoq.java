package com.google.android.gms.internal.ads;

import android.app.DownloadManager.Request;
import android.app.DownloadManager;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;

final class zzaoq implements DialogInterface.OnClickListener {
    private final String zzdgz;
    private final String zzdha;
    private final zzaor zzdhb;

    zzaoq(zzaor zzaor0, String s, String s1) {
        this.zzdhb = zzaor0;
        this.zzdgz = s;
        this.zzdha = s1;
        super();
    }

    @Override  // android.content.DialogInterface$OnClickListener
    public final void onClick(DialogInterface dialogInterface0, int v) {
        DownloadManager downloadManager0 = (DownloadManager)this.zzdhb.zzur.getSystemService("download");
        try {
            DownloadManager.Request downloadManager$Request0 = new DownloadManager.Request(Uri.parse(this.zzdgz));
            downloadManager$Request0.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, this.zzdha);
            downloadManager$Request0.allowScanningByMediaScanner();
            downloadManager$Request0.setNotificationVisibility(1);
            downloadManager0.enqueue(downloadManager$Request0);
        }
        catch(IllegalStateException unused_ex) {
            this.zzdhb.zzdt("Could not store picture.");
        }
    }
}

