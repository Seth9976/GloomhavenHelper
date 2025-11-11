package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.google.android.gms.ads.impl.R.string;
import com.google.android.gms.ads.internal.zzq;
import java.util.Map;

public final class zzaor extends zzaow {
    private final Map zzcsz;
    private final Context zzur;

    public zzaor(zzbdv zzbdv0, Map map0) {
        super(zzbdv0, "storePicture");
        this.zzcsz = map0;
        this.zzur = zzbdv0.zzys();
    }

    public final void execute() {
        if(this.zzur == null) {
            this.zzdt("Activity context is not available");
            return;
        }
        if(!zzawo.zzau(this.zzur).zzqg()) {
            this.zzdt("Feature is not supported by the device.");
            return;
        }
        String s = (String)this.zzcsz.get("iurl");
        if(TextUtils.isEmpty(s)) {
            this.zzdt("Image url cannot be empty.");
            return;
        }
        if(!URLUtil.isValidUrl(s)) {
            String s1 = String.valueOf(s);
            this.zzdt((s1.length() == 0 ? new String("Invalid image url: ") : "Invalid image url: " + s1));
            return;
        }
        String s2 = Uri.parse(s).getLastPathSegment();
        if(!zzawo.zzek(s2)) {
            String s3 = String.valueOf(s2);
            this.zzdt((s3.length() == 0 ? new String("Image type not recognized: ") : "Image type not recognized: " + s3));
            return;
        }
        Resources resources0 = zzq.zzkz().getResources();
        AlertDialog.Builder alertDialog$Builder0 = zzawo.zzat(this.zzur);
        alertDialog$Builder0.setTitle((resources0 == null ? "Save image" : resources0.getString(string.s1)));
        alertDialog$Builder0.setMessage((resources0 == null ? "Allow Ad to store image in Picture gallery?" : resources0.getString(string.s2)));
        alertDialog$Builder0.setPositiveButton((resources0 == null ? "Accept" : resources0.getString(string.s3)), new zzaoq(this, s, s2));
        alertDialog$Builder0.setNegativeButton((resources0 == null ? "Decline" : resources0.getString(string.s4)), new zzaot(this));
        alertDialog$Builder0.create().show();
    }
}

