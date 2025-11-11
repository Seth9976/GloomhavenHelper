package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "SafeBrowsingConfigParcelCreator")
@Reserved({1})
public final class zzaua extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 2)
    public final String zzdpx;
    @Field(id = 3)
    public final String zzdpy;
    @Field(id = 4)
    public final boolean zzdpz;
    @Field(id = 5)
    public final boolean zzdqa;
    @Field(id = 6)
    public final List zzdqb;
    @Field(id = 7)
    public final boolean zzdqc;
    @Field(id = 8)
    public final boolean zzdqd;
    @Field(id = 9)
    public final List zzdqe;

    static {
        zzaua.CREATOR = new zzatz();
    }

    @Constructor
    public zzaua(@Param(id = 2) String s, @Param(id = 3) String s1, @Param(id = 4) boolean z, @Param(id = 5) boolean z1, @Param(id = 6) List list0, @Param(id = 7) boolean z2, @Param(id = 8) boolean z3, @Param(id = 9) List list1) {
        this.zzdpx = s;
        this.zzdpy = s1;
        this.zzdpz = z;
        this.zzdqa = z1;
        this.zzdqb = list0;
        this.zzdqc = z2;
        this.zzdqd = z3;
        if(list1 == null) {
            list1 = new ArrayList();
        }
        this.zzdqe = list1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 2, this.zzdpx, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzdpy, false);
        SafeParcelWriter.writeBoolean(parcel0, 4, this.zzdpz);
        SafeParcelWriter.writeBoolean(parcel0, 5, this.zzdqa);
        SafeParcelWriter.writeStringList(parcel0, 6, this.zzdqb, false);
        SafeParcelWriter.writeBoolean(parcel0, 7, this.zzdqc);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzdqd);
        SafeParcelWriter.writeStringList(parcel0, 9, this.zzdqe, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    @Nullable
    public static zzaua zzg(JSONObject jSONObject0) throws JSONException {
        if(jSONObject0 == null) {
            return null;
        }
        String s = jSONObject0.optString("click_string", "");
        String s1 = jSONObject0.optString("report_url", "");
        boolean z = jSONObject0.optBoolean("rendered_ad_enabled", false);
        boolean z1 = jSONObject0.optBoolean("non_malicious_reporting_enabled", false);
        List list0 = zzayf.zza(jSONObject0.optJSONArray("allowed_headers"), null);
        List list1 = zzayf.zza(jSONObject0.optJSONArray("webview_permissions"), null);
        return new zzaua(s, s1, z, z1, list0, jSONObject0.optBoolean("protection_enabled", false), jSONObject0.optBoolean("malicious_reporting_enabled", false), list1);
    }
}

