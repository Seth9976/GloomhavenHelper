package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;

@KeepForSdk
@Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(getter = "getName", id = 1)
    private final String name;
    @Field(getter = "getOldVersion", id = 2)
    @Deprecated
    private final int zzk;
    @Field(defaultValue = "-1", getter = "getVersion", id = 3)
    private final long zzl;

    static {
        Feature.CREATOR = new zzb();
    }

    @Constructor
    public Feature(@Param(id = 1) String s, @Param(id = 2) int v, @Param(id = 3) long v1) {
        this.name = s;
        this.zzk = v;
        this.zzl = v1;
    }

    @KeepForSdk
    public Feature(String s, long v) {
        this.name = s;
        this.zzl = v;
        this.zzk = -1;
    }

    // 去混淆评级： 低(30)
    @Override
    public boolean equals(@Nullable Object object0) {
        return object0 instanceof Feature && (this.getName() != null && this.getName().equals(((Feature)object0).getName()) || this.getName() == null && ((Feature)object0).getName() == null) && this.getVersion() == ((Feature)object0).getVersion();
    }

    @KeepForSdk
    public String getName() {
        return this.name;
    }

    @KeepForSdk
    public long getVersion() {
        return this.zzl == -1L ? ((long)this.zzk) : this.zzl;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[]{this.getName(), this.getVersion()});
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.getName()).add("version", this.getVersion()).toString();
    }

    @Override  // android.os.Parcelable
    public void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeString(parcel0, 1, this.getName(), false);
        SafeParcelWriter.writeInt(parcel0, 2, this.zzk);
        SafeParcelWriter.writeLong(parcel0, 3, this.getVersion());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

