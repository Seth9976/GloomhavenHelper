package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "AdRequestInfoParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaqi extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 6)
    private final ApplicationInfo applicationInfo;
    @Field(id = 1)
    private final int versionCode;
    @Field(id = 11)
    private final zzazo zzblu;
    @Field(id = 4)
    private final zzuk zzblv;
    @Field(id = 20)
    private final float zzboq;
    @Field(id = 5)
    private final String zzbri;
    @Field(id = 10)
    private final String zzcei;
    @Field(id = 42)
    private final boolean zzddu;
    @Field(id = 29)
    private final zzach zzdff;
    @Field(id = 14)
    private final List zzdfg;
    @Field(id = 18)
    private final int zzdhj;
    @Field(id = 19)
    private final int zzdhk;
    @Nullable
    @Field(id = 2)
    private final Bundle zzdjs;
    @Field(id = 3)
    private final zzuh zzdjt;
    @Nullable
    @Field(id = 7)
    private final PackageInfo zzdju;
    @Field(id = 8)
    private final String zzdjv;
    @Field(id = 9)
    private final String zzdjw;
    @Field(id = 12)
    private final Bundle zzdjx;
    @Field(id = 13)
    private final int zzdjy;
    @Field(id = 15)
    private final Bundle zzdjz;
    @Field(id = 16)
    private final boolean zzdka;
    @Field(id = 21)
    private final String zzdkb;
    @Field(id = 25)
    private final long zzdkc;
    @Field(id = 26)
    private final String zzdkd;
    @Nullable
    @Field(id = 27)
    private final List zzdke;
    @Field(id = 28)
    private final String zzdkf;
    @Field(id = 30)
    private final List zzdkg;
    @Field(id = 0x1F)
    private final long zzdkh;
    @Field(id = 33)
    private final String zzdki;
    @Field(id = 34)
    private final float zzdkj;
    @Field(id = 35)
    private final int zzdkk;
    @Field(id = 36)
    private final int zzdkl;
    @Field(id = 37)
    private final boolean zzdkm;
    @Field(id = 38)
    private final boolean zzdkn;
    @Field(id = 39)
    private final String zzdko;
    @Field(id = 40)
    private final boolean zzdkp;
    @Field(id = 41)
    private final String zzdkq;
    @Field(id = 43)
    private final int zzdkr;
    @Field(id = 44)
    private final Bundle zzdks;
    @Field(id = 45)
    private final String zzdkt;
    @Nullable
    @Field(id = 46)
    private final zzxp zzdku;
    @Field(id = 0x2F)
    private final boolean zzdkv;
    @Field(id = 0x30)
    private final Bundle zzdkw;
    @Nullable
    @Field(id = 49)
    private final String zzdkx;
    @Nullable
    @Field(id = 50)
    private final String zzdky;
    @Nullable
    @Field(id = 51)
    private final String zzdkz;
    @Field(id = 52)
    private final boolean zzdla;
    @Field(id = 53)
    private final List zzdlb;
    @Field(id = 54)
    private final String zzdlc;
    @Field(id = 55)
    private final List zzdld;
    @Field(id = 56)
    private final int zzdle;
    @Field(id = 57)
    private final boolean zzdlf;
    @Field(id = 58)
    private final boolean zzdlg;
    @Field(id = 59)
    private final boolean zzdlh;
    @Field(id = 60)
    private final ArrayList zzdli;
    @Field(id = 61)
    private final String zzdlj;
    @Field(id = 0x3F)
    private final zzahl zzdlk;
    @Nullable
    @Field(id = 0x40)
    private final String zzdll;
    @Field(id = 65)
    private final Bundle zzdlm;

    static {
        zzaqi.CREATOR = new zzaqh();
    }

    @Constructor
    zzaqi(@Param(id = 1) int v, @Param(id = 2) Bundle bundle0, @Param(id = 3) zzuh zzuh0, @Param(id = 4) zzuk zzuk0, @Param(id = 5) String s, @Param(id = 6) ApplicationInfo applicationInfo0, @Param(id = 7) PackageInfo packageInfo0, @Param(id = 8) String s1, @Param(id = 9) String s2, @Param(id = 10) String s3, @Param(id = 11) zzazo zzazo0, @Param(id = 12) Bundle bundle1, @Param(id = 13) int v1, @Param(id = 14) List list0, @Param(id = 15) Bundle bundle2, @Param(id = 16) boolean z, @Param(id = 18) int v2, @Param(id = 19) int v3, @Param(id = 20) float f, @Param(id = 21) String s4, @Param(id = 25) long v4, @Param(id = 26) String s5, @Param(id = 27) List list1, @Param(id = 28) String s6, @Param(id = 29) zzach zzach0, @Param(id = 30) List list2, @Param(id = 0x1F) long v5, @Param(id = 33) String s7, @Param(id = 34) float f1, @Param(id = 40) boolean z1, @Param(id = 35) int v6, @Param(id = 36) int v7, @Param(id = 37) boolean z2, @Param(id = 38) boolean z3, @Param(id = 39) String s8, @Param(id = 41) String s9, @Param(id = 42) boolean z4, @Param(id = 43) int v8, @Param(id = 44) Bundle bundle3, @Param(id = 45) String s10, @Param(id = 46) zzxp zzxp0, @Param(id = 0x2F) boolean z5, @Param(id = 0x30) Bundle bundle4, @Nullable @Param(id = 49) String s11, @Nullable @Param(id = 50) String s12, @Nullable @Param(id = 51) String s13, @Param(id = 52) boolean z6, @Param(id = 53) List list3, @Param(id = 54) String s14, @Param(id = 55) List list4, @Param(id = 56) int v9, @Param(id = 57) boolean z7, @Param(id = 58) boolean z8, @Param(id = 59) boolean z9, @Param(id = 60) ArrayList arrayList0, @Param(id = 61) String s15, @Param(id = 0x3F) zzahl zzahl0, @Nullable @Param(id = 0x40) String s16, @Param(id = 65) Bundle bundle5) {
        this.versionCode = v;
        this.zzdjs = bundle0;
        this.zzdjt = zzuh0;
        this.zzblv = zzuk0;
        this.zzbri = s;
        this.applicationInfo = applicationInfo0;
        this.zzdju = packageInfo0;
        this.zzdjv = s1;
        this.zzdjw = s2;
        this.zzcei = s3;
        this.zzblu = zzazo0;
        this.zzdjx = bundle1;
        this.zzdjy = v1;
        this.zzdfg = list0;
        this.zzdkg = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzdjz = bundle2;
        this.zzdka = z;
        this.zzdhj = v2;
        this.zzdhk = v3;
        this.zzboq = f;
        this.zzdkb = s4;
        this.zzdkc = v4;
        this.zzdkd = s5;
        this.zzdke = list1 == null ? Collections.emptyList() : Collections.unmodifiableList(list1);
        this.zzdkf = s6;
        this.zzdff = zzach0;
        this.zzdkh = v5;
        this.zzdki = s7;
        this.zzdkj = f1;
        this.zzdkp = z1;
        this.zzdkk = v6;
        this.zzdkl = v7;
        this.zzdkm = z2;
        this.zzdkn = z3;
        this.zzdko = s8;
        this.zzdkq = s9;
        this.zzddu = z4;
        this.zzdkr = v8;
        this.zzdks = bundle3;
        this.zzdkt = s10;
        this.zzdku = zzxp0;
        this.zzdkv = z5;
        this.zzdkw = bundle4;
        this.zzdkx = s11;
        this.zzdky = s12;
        this.zzdkz = s13;
        this.zzdla = z6;
        this.zzdlb = list3;
        this.zzdlc = s14;
        this.zzdld = list4;
        this.zzdle = v9;
        this.zzdlf = z7;
        this.zzdlg = z8;
        this.zzdlh = z9;
        this.zzdli = arrayList0;
        this.zzdlj = s15;
        this.zzdlk = zzahl0;
        this.zzdll = s16;
        this.zzdlm = bundle5;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeBundle(parcel0, 2, this.zzdjs, false);
        SafeParcelWriter.writeParcelable(parcel0, 3, this.zzdjt, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 4, this.zzblv, v, false);
        SafeParcelWriter.writeString(parcel0, 5, this.zzbri, false);
        SafeParcelWriter.writeParcelable(parcel0, 6, this.applicationInfo, v, false);
        SafeParcelWriter.writeParcelable(parcel0, 7, this.zzdju, v, false);
        SafeParcelWriter.writeString(parcel0, 8, this.zzdjv, false);
        SafeParcelWriter.writeString(parcel0, 9, this.zzdjw, false);
        SafeParcelWriter.writeString(parcel0, 10, this.zzcei, false);
        SafeParcelWriter.writeParcelable(parcel0, 11, this.zzblu, v, false);
        SafeParcelWriter.writeBundle(parcel0, 12, this.zzdjx, false);
        SafeParcelWriter.writeInt(parcel0, 13, this.zzdjy);
        SafeParcelWriter.writeStringList(parcel0, 14, this.zzdfg, false);
        SafeParcelWriter.writeBundle(parcel0, 15, this.zzdjz, false);
        SafeParcelWriter.writeBoolean(parcel0, 16, this.zzdka);
        SafeParcelWriter.writeInt(parcel0, 18, this.zzdhj);
        SafeParcelWriter.writeInt(parcel0, 19, this.zzdhk);
        SafeParcelWriter.writeFloat(parcel0, 20, this.zzboq);
        SafeParcelWriter.writeString(parcel0, 21, this.zzdkb, false);
        SafeParcelWriter.writeLong(parcel0, 25, this.zzdkc);
        SafeParcelWriter.writeString(parcel0, 26, this.zzdkd, false);
        SafeParcelWriter.writeStringList(parcel0, 27, this.zzdke, false);
        SafeParcelWriter.writeString(parcel0, 28, this.zzdkf, false);
        SafeParcelWriter.writeParcelable(parcel0, 29, this.zzdff, v, false);
        SafeParcelWriter.writeStringList(parcel0, 30, this.zzdkg, false);
        SafeParcelWriter.writeLong(parcel0, 0x1F, this.zzdkh);
        SafeParcelWriter.writeString(parcel0, 33, this.zzdki, false);
        SafeParcelWriter.writeFloat(parcel0, 34, this.zzdkj);
        SafeParcelWriter.writeInt(parcel0, 35, this.zzdkk);
        SafeParcelWriter.writeInt(parcel0, 36, this.zzdkl);
        SafeParcelWriter.writeBoolean(parcel0, 37, this.zzdkm);
        SafeParcelWriter.writeBoolean(parcel0, 38, this.zzdkn);
        SafeParcelWriter.writeString(parcel0, 39, this.zzdko, false);
        SafeParcelWriter.writeBoolean(parcel0, 40, this.zzdkp);
        SafeParcelWriter.writeString(parcel0, 41, this.zzdkq, false);
        SafeParcelWriter.writeBoolean(parcel0, 42, this.zzddu);
        SafeParcelWriter.writeInt(parcel0, 43, this.zzdkr);
        SafeParcelWriter.writeBundle(parcel0, 44, this.zzdks, false);
        SafeParcelWriter.writeString(parcel0, 45, this.zzdkt, false);
        SafeParcelWriter.writeParcelable(parcel0, 46, this.zzdku, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 0x2F, this.zzdkv);
        SafeParcelWriter.writeBundle(parcel0, 0x30, this.zzdkw, false);
        SafeParcelWriter.writeString(parcel0, 49, this.zzdkx, false);
        SafeParcelWriter.writeString(parcel0, 50, this.zzdky, false);
        SafeParcelWriter.writeString(parcel0, 51, this.zzdkz, false);
        SafeParcelWriter.writeBoolean(parcel0, 52, this.zzdla);
        SafeParcelWriter.writeIntegerList(parcel0, 53, this.zzdlb, false);
        SafeParcelWriter.writeString(parcel0, 54, this.zzdlc, false);
        SafeParcelWriter.writeStringList(parcel0, 55, this.zzdld, false);
        SafeParcelWriter.writeInt(parcel0, 56, this.zzdle);
        SafeParcelWriter.writeBoolean(parcel0, 57, this.zzdlf);
        SafeParcelWriter.writeBoolean(parcel0, 58, this.zzdlg);
        SafeParcelWriter.writeBoolean(parcel0, 59, this.zzdlh);
        SafeParcelWriter.writeStringList(parcel0, 60, this.zzdli, false);
        SafeParcelWriter.writeString(parcel0, 61, this.zzdlj, false);
        SafeParcelWriter.writeParcelable(parcel0, 0x3F, this.zzdlk, v, false);
        SafeParcelWriter.writeString(parcel0, 0x40, this.zzdll, false);
        SafeParcelWriter.writeBundle(parcel0, 65, this.zzdlm, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

