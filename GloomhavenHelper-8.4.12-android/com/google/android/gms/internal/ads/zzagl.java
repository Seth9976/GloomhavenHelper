package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Map.Entry;
import java.util.Map;

@Class(creator = "HttpRequestParcelCreator")
public final class zzagl extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 1)
    private final String url;
    @Field(id = 2)
    private final String[] zzcyy;
    @Field(id = 3)
    private final String[] zzcyz;

    static {
        zzagl.CREATOR = new zzagk();
    }

    @Constructor
    zzagl(@Param(id = 1) String s, @Param(id = 2) String[] arr_s, @Param(id = 3) String[] arr_s1) {
        this.url = s;
        this.zzcyy = arr_s;
        this.zzcyz = arr_s1;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.url, false);
        SafeParcelWriter.writeStringArray(parcel0, 2, this.zzcyy, false);
        SafeParcelWriter.writeStringArray(parcel0, 3, this.zzcyz, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static zzagl zzh(zzq zzq0) throws zzb {
        Map map0 = zzq0.getHeaders();
        int v = map0.size();
        String[] arr_s = new String[v];
        String[] arr_s1 = new String[v];
        int v1 = 0;
        for(Object object0: map0.entrySet()) {
            arr_s[v1] = (String)((Map.Entry)object0).getKey();
            arr_s1[v1] = (String)((Map.Entry)object0).getValue();
            ++v1;
        }
        return new zzagl(zzq0.getUrl(), arr_s, arr_s1);
    }
}

