package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@Class(creator = "AdValueParcelCreator")
@ParametersAreNonnullByDefault
public final class zzum extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    public final int type;
    @Field(id = 2)
    public final int zzabo;
    @Field(id = 3)
    public final String zzabp;
    @Field(id = 4)
    public final long zzabq;

    static {
        zzum.CREATOR = new zzup();
    }

    @Constructor
    public zzum(@Param(id = 1) int v, @Param(id = 2) int v1, @Param(id = 3) String s, @Param(id = 4) long v2) {
        this.type = v;
        this.zzabo = v1;
        this.zzabp = s;
        this.zzabq = v2;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.type);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzabo);
        SafeParcelWriter.writeString(parcel0, 3, this.zzabp, false);
        SafeParcelWriter.writeLong(parcel0, 4, this.zzabq);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static zzum zza(JSONObject jSONObject0) throws JSONException {
        return new zzum(jSONObject0.getInt("type_num"), jSONObject0.getInt("precision_num"), jSONObject0.getString("currency"), jSONObject0.getLong("value"));
    }
}

