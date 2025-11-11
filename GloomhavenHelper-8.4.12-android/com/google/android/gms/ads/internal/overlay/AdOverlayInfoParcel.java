package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzg;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.dynamic.IObjectWrapper.Stub;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzafe;
import com.google.android.gms.internal.ads.zzafg;
import com.google.android.gms.internal.ads.zzazo;
import com.google.android.gms.internal.ads.zzbdv;
import com.google.android.gms.internal.ads.zztz;

@Class(creator = "AdOverlayInfoCreator")
@Reserved({1})
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 11)
    public final int orientation;
    @Field(id = 13)
    public final String url;
    @Field(id = 14)
    public final zzazo zzblu;
    @Field(getter = "getAdClickListenerAsBinder", id = 3, type = "android.os.IBinder")
    public final zztz zzcch;
    @Field(getter = "getAdMetadataGmsgListenerAsBinder", id = 18, type = "android.os.IBinder")
    public final zzafe zzcxu;
    @Field(getter = "getAppEventGmsgListenerAsBinder", id = 6, type = "android.os.IBinder")
    public final zzafg zzcxv;
    @Field(getter = "getAdWebViewAsBinder", id = 5, type = "android.os.IBinder")
    public final zzbdv zzdae;
    @Field(id = 2)
    public final zzb zzdis;
    @Field(getter = "getAdOverlayListenerAsBinder", id = 4, type = "android.os.IBinder")
    public final zzp zzdit;
    @Field(id = 7)
    public final String zzdiu;
    @Field(id = 8)
    public final boolean zzdiv;
    @Field(id = 9)
    public final String zzdiw;
    @Field(getter = "getLeaveApplicationListenerAsBinder", id = 10, type = "android.os.IBinder")
    public final zzv zzdix;
    @Field(id = 12)
    public final int zzdiy;
    @Field(id = 16)
    public final String zzdiz;
    @Field(id = 17)
    public final zzg zzdja;

    static {
        AdOverlayInfoParcel.CREATOR = new zzn();
    }

    @Constructor
    AdOverlayInfoParcel(@Param(id = 2) zzb zzb0, @Param(id = 3) IBinder iBinder0, @Param(id = 4) IBinder iBinder1, @Param(id = 5) IBinder iBinder2, @Param(id = 6) IBinder iBinder3, @Param(id = 7) String s, @Param(id = 8) boolean z, @Param(id = 9) String s1, @Param(id = 10) IBinder iBinder4, @Param(id = 11) int v, @Param(id = 12) int v1, @Param(id = 13) String s2, @Param(id = 14) zzazo zzazo0, @Param(id = 16) String s3, @Param(id = 17) zzg zzg0, @Param(id = 18) IBinder iBinder5) {
        this.zzdis = zzb0;
        this.zzcch = (zztz)ObjectWrapper.unwrap(Stub.asInterface(iBinder0));
        this.zzdit = (zzp)ObjectWrapper.unwrap(Stub.asInterface(iBinder1));
        this.zzdae = (zzbdv)ObjectWrapper.unwrap(Stub.asInterface(iBinder2));
        this.zzcxu = (zzafe)ObjectWrapper.unwrap(Stub.asInterface(iBinder5));
        this.zzcxv = (zzafg)ObjectWrapper.unwrap(Stub.asInterface(iBinder3));
        this.zzdiu = s;
        this.zzdiv = z;
        this.zzdiw = s1;
        this.zzdix = (zzv)ObjectWrapper.unwrap(Stub.asInterface(iBinder4));
        this.orientation = v;
        this.zzdiy = v1;
        this.url = s2;
        this.zzblu = zzazo0;
        this.zzdiz = s3;
        this.zzdja = zzg0;
    }

    public AdOverlayInfoParcel(zzb zzb0, zztz zztz0, zzp zzp0, zzv zzv0, zzazo zzazo0) {
        this.zzdis = zzb0;
        this.zzcch = zztz0;
        this.zzdit = zzp0;
        this.zzdae = null;
        this.zzcxu = null;
        this.zzcxv = null;
        this.zzdiu = null;
        this.zzdiv = false;
        this.zzdiw = null;
        this.zzdix = zzv0;
        this.orientation = -1;
        this.zzdiy = 4;
        this.url = null;
        this.zzblu = zzazo0;
        this.zzdiz = null;
        this.zzdja = null;
    }

    public AdOverlayInfoParcel(zztz zztz0, zzp zzp0, zzv zzv0, zzbdv zzbdv0, int v, zzazo zzazo0, String s, zzg zzg0, String s1, String s2) {
        this.zzdis = null;
        this.zzcch = null;
        this.zzdit = zzp0;
        this.zzdae = zzbdv0;
        this.zzcxu = null;
        this.zzcxv = null;
        this.zzdiu = s1;
        this.zzdiv = false;
        this.zzdiw = s2;
        this.zzdix = null;
        this.orientation = v;
        this.zzdiy = 1;
        this.url = null;
        this.zzblu = zzazo0;
        this.zzdiz = s;
        this.zzdja = zzg0;
    }

    public AdOverlayInfoParcel(zztz zztz0, zzp zzp0, zzv zzv0, zzbdv zzbdv0, boolean z, int v, zzazo zzazo0) {
        this.zzdis = null;
        this.zzcch = zztz0;
        this.zzdit = zzp0;
        this.zzdae = zzbdv0;
        this.zzcxu = null;
        this.zzcxv = null;
        this.zzdiu = null;
        this.zzdiv = z;
        this.zzdiw = null;
        this.zzdix = zzv0;
        this.orientation = v;
        this.zzdiy = 2;
        this.url = null;
        this.zzblu = zzazo0;
        this.zzdiz = null;
        this.zzdja = null;
    }

    public AdOverlayInfoParcel(zztz zztz0, zzp zzp0, zzafe zzafe0, zzafg zzafg0, zzv zzv0, zzbdv zzbdv0, boolean z, int v, String s, zzazo zzazo0) {
        this.zzdis = null;
        this.zzcch = zztz0;
        this.zzdit = zzp0;
        this.zzdae = zzbdv0;
        this.zzcxu = zzafe0;
        this.zzcxv = zzafg0;
        this.zzdiu = null;
        this.zzdiv = z;
        this.zzdiw = null;
        this.zzdix = zzv0;
        this.orientation = v;
        this.zzdiy = 3;
        this.url = s;
        this.zzblu = zzazo0;
        this.zzdiz = null;
        this.zzdja = null;
    }

    public AdOverlayInfoParcel(zztz zztz0, zzp zzp0, zzafe zzafe0, zzafg zzafg0, zzv zzv0, zzbdv zzbdv0, boolean z, int v, String s, String s1, zzazo zzazo0) {
        this.zzdis = null;
        this.zzcch = zztz0;
        this.zzdit = zzp0;
        this.zzdae = zzbdv0;
        this.zzcxu = zzafe0;
        this.zzcxv = zzafg0;
        this.zzdiu = s1;
        this.zzdiv = z;
        this.zzdiw = s;
        this.zzdix = zzv0;
        this.orientation = v;
        this.zzdiy = 3;
        this.url = null;
        this.zzblu = zzazo0;
        this.zzdiz = null;
        this.zzdja = null;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 2, this.zzdis, v, false);
        SafeParcelWriter.writeIBinder(parcel0, 3, ObjectWrapper.wrap(this.zzcch).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel0, 4, ObjectWrapper.wrap(this.zzdit).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel0, 5, ObjectWrapper.wrap(this.zzdae).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel0, 6, ObjectWrapper.wrap(this.zzcxv).asBinder(), false);
        SafeParcelWriter.writeString(parcel0, 7, this.zzdiu, false);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzdiv);
        SafeParcelWriter.writeString(parcel0, 9, this.zzdiw, false);
        SafeParcelWriter.writeIBinder(parcel0, 10, ObjectWrapper.wrap(this.zzdix).asBinder(), false);
        SafeParcelWriter.writeInt(parcel0, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel0, 12, this.zzdiy);
        SafeParcelWriter.writeString(parcel0, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel0, 14, this.zzblu, v, false);
        SafeParcelWriter.writeString(parcel0, 16, this.zzdiz, false);
        SafeParcelWriter.writeParcelable(parcel0, 17, this.zzdja, v, false);
        SafeParcelWriter.writeIBinder(parcel0, 18, ObjectWrapper.wrap(this.zzcxu).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public static void zza(Intent intent0, AdOverlayInfoParcel adOverlayInfoParcel0) {
        Bundle bundle0 = new Bundle(1);
        bundle0.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel0);
        intent0.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle0);
    }

    public static AdOverlayInfoParcel zzc(Intent intent0) {
        try {
            Bundle bundle0 = intent0.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundle0.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel)bundle0.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        }
        catch(Exception unused_ex) {
            return null;
        }
    }
}

