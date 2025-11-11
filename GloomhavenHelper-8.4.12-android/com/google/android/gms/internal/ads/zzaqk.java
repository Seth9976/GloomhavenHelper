package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@Class(creator = "AdResponseParcelCreator")
@ParametersAreNonnullByDefault
public final class zzaqk extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    @Field(id = 5)
    private final int errorCode;
    @Field(id = 12)
    private final int orientation;
    @Field(id = 1)
    private final int versionCode;
    @Nullable
    @Field(id = 37)
    private final zzaqm zzbld;
    @Field(id = 49)
    private final boolean zzbln;
    @Field(id = 53)
    private final boolean zzblo;
    @Field(id = 23)
    private final boolean zzbml;
    @Field(id = 30)
    private final String zzcac;
    @Field(id = 0x1F)
    private final boolean zzcde;
    @Field(id = 0x20)
    private final boolean zzcdf;
    @Field(id = 4)
    private final List zzddp;
    @Field(id = 6)
    private final List zzddq;
    @Field(id = 52)
    private final List zzddr;
    @Field(id = 40)
    private final List zzddt;
    @Field(id = 42)
    private final boolean zzddu;
    @Field(id = 11)
    private final long zzddw;
    @Field(id = 2)
    private final String zzdiu;
    @Field(id = 24)
    private final boolean zzdka;
    @Field(id = 38)
    private final boolean zzdkn;
    @Nullable
    @Field(id = 39)
    private String zzdko;
    @Field(id = 0x2F)
    private final boolean zzdla;
    @Field(id = 3)
    private String zzdln;
    @Field(id = 7)
    private final long zzdlo;
    @Field(id = 8)
    private final boolean zzdlp;
    @Field(id = 9)
    private final long zzdlq;
    @Field(id = 10)
    private final List zzdlr;
    @Field(id = 13)
    private final String zzdls;
    @Field(id = 14)
    private final long zzdlt;
    @Field(id = 15)
    private final String zzdlu;
    @Field(id = 18)
    private final boolean zzdlv;
    @Field(id = 19)
    private final String zzdlw;
    @Field(id = 21)
    private final String zzdlx;
    @Field(id = 22)
    private final boolean zzdly;
    @Field(id = 25)
    private final boolean zzdlz;
    @Field(id = 26)
    private final boolean zzdma;
    @Field(id = 28)
    private zzaqw zzdmb;
    @Field(id = 29)
    private String zzdmc;
    @Nullable
    @Field(id = 33)
    private final zzasq zzdmd;
    @Nullable
    @Field(id = 34)
    private final List zzdme;
    @Nullable
    @Field(id = 35)
    private final List zzdmf;
    @Field(id = 36)
    private final boolean zzdmg;
    @Nullable
    @Field(id = 43)
    private final String zzdmh;
    @Nullable
    @Field(id = 44)
    private final zzaua zzdmi;
    @Nullable
    @Field(id = 45)
    private final String zzdmj;
    @Field(id = 46)
    private final boolean zzdmk;
    @Field(id = 0x30)
    private Bundle zzdml;
    @Field(id = 50)
    private final int zzdmm;
    @Field(id = 51)
    private final boolean zzdmn;
    @Nullable
    @Field(id = 54)
    private final String zzdmo;
    @Nullable
    @Field(id = 55)
    private String zzdmp;
    @Field(id = 56)
    private boolean zzdmq;
    @Field(id = 57)
    private boolean zzdmr;

    static {
        zzaqk.CREATOR = new zzaqj();
    }

    @Constructor
    zzaqk(@Param(id = 1) int v, @Param(id = 2) String s, @Param(id = 3) String s1, @Param(id = 4) List list0, @Param(id = 5) int v1, @Param(id = 6) List list1, @Param(id = 7) long v2, @Param(id = 8) boolean z, @Param(id = 9) long v3, @Param(id = 10) List list2, @Param(id = 11) long v4, @Param(id = 12) int v5, @Param(id = 13) String s2, @Param(id = 14) long v6, @Param(id = 15) String s3, @Param(id = 18) boolean z1, @Param(id = 19) String s4, @Param(id = 21) String s5, @Param(id = 22) boolean z2, @Param(id = 23) boolean z3, @Param(id = 24) boolean z4, @Param(id = 25) boolean z5, @Param(id = 26) boolean z6, @Param(id = 28) zzaqw zzaqw0, @Param(id = 29) String s6, @Param(id = 30) String s7, @Param(id = 0x1F) boolean z7, @Param(id = 0x20) boolean z8, @Param(id = 33) zzasq zzasq0, @Param(id = 34) List list3, @Param(id = 35) List list4, @Param(id = 36) boolean z9, @Param(id = 37) zzaqm zzaqm0, @Param(id = 38) boolean z10, @Param(id = 39) String s8, @Param(id = 40) List list5, @Param(id = 42) boolean z11, @Param(id = 43) String s9, @Param(id = 44) zzaua zzaua0, @Param(id = 45) String s10, @Param(id = 46) boolean z12, @Param(id = 0x2F) boolean z13, @Param(id = 0x30) Bundle bundle0, @Param(id = 49) boolean z14, @Param(id = 50) int v7, @Param(id = 51) boolean z15, @Param(id = 52) List list6, @Param(id = 53) boolean z16, @Param(id = 54) String s11, @Nullable @Param(id = 55) String s12, @Param(id = 56) boolean z17, @Param(id = 57) boolean z18) {
        this.versionCode = v;
        this.zzdiu = s;
        this.zzdln = s1;
        List list7 = null;
        this.zzddp = list0 == null ? null : Collections.unmodifiableList(list0);
        this.errorCode = v1;
        this.zzddq = list1 == null ? null : Collections.unmodifiableList(list1);
        this.zzdlo = v2;
        this.zzdlp = z;
        this.zzdlq = v3;
        this.zzdlr = list2 == null ? null : Collections.unmodifiableList(list2);
        this.zzddw = v4;
        this.orientation = v5;
        this.zzdls = s2;
        this.zzdlt = v6;
        this.zzdlu = s3;
        this.zzdlv = z1;
        this.zzdlw = s4;
        this.zzdlx = s5;
        this.zzdly = z2;
        this.zzbml = z3;
        this.zzdka = z4;
        this.zzdlz = z5;
        this.zzdmk = z12;
        this.zzdma = z6;
        this.zzdmb = zzaqw0;
        this.zzdmc = s6;
        this.zzcac = s7;
        if(this.zzdln == null) {
            zzaqw zzaqw1 = this.zzdmb;
            if(zzaqw1 != null) {
                zzaqz zzaqz0 = (zzaqz)zzaqw1.zza(zzaqz.CREATOR);
                if(zzaqz0 != null && !TextUtils.isEmpty(zzaqz0.zzdne)) {
                    this.zzdln = zzaqz0.zzdne;
                }
            }
        }
        this.zzcde = z7;
        this.zzcdf = z8;
        this.zzdmd = zzasq0;
        this.zzdme = list3;
        this.zzdmf = list4;
        this.zzdmg = z9;
        this.zzbld = zzaqm0;
        this.zzdkn = z10;
        this.zzdko = s8;
        this.zzddt = list5;
        this.zzddu = z11;
        this.zzdmh = s9;
        this.zzdmi = zzaua0;
        this.zzdmj = s10;
        this.zzdla = z13;
        this.zzdml = bundle0;
        this.zzbln = z14;
        this.zzdmm = v7;
        this.zzdmn = z15;
        if(list6 != null) {
            list7 = Collections.unmodifiableList(list6);
        }
        this.zzddr = list7;
        this.zzblo = z16;
        this.zzdmo = s11;
        this.zzdmp = s12;
        this.zzdmq = z17;
        this.zzdmr = z18;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel0, 2, this.zzdiu, false);
        SafeParcelWriter.writeString(parcel0, 3, this.zzdln, false);
        SafeParcelWriter.writeStringList(parcel0, 4, this.zzddp, false);
        SafeParcelWriter.writeInt(parcel0, 5, this.errorCode);
        SafeParcelWriter.writeStringList(parcel0, 6, this.zzddq, false);
        SafeParcelWriter.writeLong(parcel0, 7, this.zzdlo);
        SafeParcelWriter.writeBoolean(parcel0, 8, this.zzdlp);
        SafeParcelWriter.writeLong(parcel0, 9, this.zzdlq);
        SafeParcelWriter.writeStringList(parcel0, 10, this.zzdlr, false);
        SafeParcelWriter.writeLong(parcel0, 11, this.zzddw);
        SafeParcelWriter.writeInt(parcel0, 12, this.orientation);
        SafeParcelWriter.writeString(parcel0, 13, this.zzdls, false);
        SafeParcelWriter.writeLong(parcel0, 14, this.zzdlt);
        SafeParcelWriter.writeString(parcel0, 15, this.zzdlu, false);
        SafeParcelWriter.writeBoolean(parcel0, 18, this.zzdlv);
        SafeParcelWriter.writeString(parcel0, 19, this.zzdlw, false);
        SafeParcelWriter.writeString(parcel0, 21, this.zzdlx, false);
        SafeParcelWriter.writeBoolean(parcel0, 22, this.zzdly);
        SafeParcelWriter.writeBoolean(parcel0, 23, this.zzbml);
        SafeParcelWriter.writeBoolean(parcel0, 24, this.zzdka);
        SafeParcelWriter.writeBoolean(parcel0, 25, this.zzdlz);
        SafeParcelWriter.writeBoolean(parcel0, 26, this.zzdma);
        SafeParcelWriter.writeParcelable(parcel0, 28, this.zzdmb, v, false);
        SafeParcelWriter.writeString(parcel0, 29, this.zzdmc, false);
        SafeParcelWriter.writeString(parcel0, 30, this.zzcac, false);
        SafeParcelWriter.writeBoolean(parcel0, 0x1F, this.zzcde);
        SafeParcelWriter.writeBoolean(parcel0, 0x20, this.zzcdf);
        SafeParcelWriter.writeParcelable(parcel0, 33, this.zzdmd, v, false);
        SafeParcelWriter.writeStringList(parcel0, 34, this.zzdme, false);
        SafeParcelWriter.writeStringList(parcel0, 35, this.zzdmf, false);
        SafeParcelWriter.writeBoolean(parcel0, 36, this.zzdmg);
        SafeParcelWriter.writeParcelable(parcel0, 37, this.zzbld, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 38, this.zzdkn);
        SafeParcelWriter.writeString(parcel0, 39, this.zzdko, false);
        SafeParcelWriter.writeStringList(parcel0, 40, this.zzddt, false);
        SafeParcelWriter.writeBoolean(parcel0, 42, this.zzddu);
        SafeParcelWriter.writeString(parcel0, 43, this.zzdmh, false);
        SafeParcelWriter.writeParcelable(parcel0, 44, this.zzdmi, v, false);
        SafeParcelWriter.writeString(parcel0, 45, this.zzdmj, false);
        SafeParcelWriter.writeBoolean(parcel0, 46, this.zzdmk);
        SafeParcelWriter.writeBoolean(parcel0, 0x2F, this.zzdla);
        SafeParcelWriter.writeBundle(parcel0, 0x30, this.zzdml, false);
        SafeParcelWriter.writeBoolean(parcel0, 49, this.zzbln);
        SafeParcelWriter.writeInt(parcel0, 50, this.zzdmm);
        SafeParcelWriter.writeBoolean(parcel0, 51, this.zzdmn);
        SafeParcelWriter.writeStringList(parcel0, 52, this.zzddr, false);
        SafeParcelWriter.writeBoolean(parcel0, 53, this.zzblo);
        SafeParcelWriter.writeString(parcel0, 54, this.zzdmo, false);
        SafeParcelWriter.writeString(parcel0, 55, this.zzdmp, false);
        SafeParcelWriter.writeBoolean(parcel0, 56, this.zzdmq);
        SafeParcelWriter.writeBoolean(parcel0, 57, this.zzdmr);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

