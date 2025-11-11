package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzgz implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    public final int height;
    public final int width;
    private final String zzafi;
    public final int zzafj;
    public final String zzafk;
    private final zzlh zzafl;
    private final String zzafm;
    public final String zzafn;
    public final int zzafo;
    public final List zzafp;
    public final zziu zzafq;
    public final float zzafr;
    public final int zzafs;
    public final float zzaft;
    private final int zzafu;
    private final byte[] zzafv;
    private final zzou zzafw;
    public final int zzafx;
    public final int zzafy;
    public final int zzafz;
    private final int zzaga;
    private final int zzagb;
    public final long zzagc;
    public final int zzagd;
    public final String zzage;
    private final int zzagf;
    private int zzagg;

    static {
        zzgz.CREATOR = new zzgy();
    }

    zzgz(Parcel parcel0) {
        this.zzafi = parcel0.readString();
        this.zzafm = parcel0.readString();
        this.zzafn = parcel0.readString();
        this.zzafk = parcel0.readString();
        this.zzafj = parcel0.readInt();
        this.zzafo = parcel0.readInt();
        this.width = parcel0.readInt();
        this.height = parcel0.readInt();
        this.zzafr = parcel0.readFloat();
        this.zzafs = parcel0.readInt();
        this.zzaft = parcel0.readFloat();
        this.zzafv = parcel0.readInt() == 0 ? parcel0.createByteArray() : null;
        this.zzafu = parcel0.readInt();
        this.zzafw = (zzou)parcel0.readParcelable(zzou.class.getClassLoader());
        this.zzafx = parcel0.readInt();
        this.zzafy = parcel0.readInt();
        this.zzafz = parcel0.readInt();
        this.zzaga = parcel0.readInt();
        this.zzagb = parcel0.readInt();
        this.zzagd = parcel0.readInt();
        this.zzage = parcel0.readString();
        this.zzagf = parcel0.readInt();
        this.zzagc = parcel0.readLong();
        int v1 = parcel0.readInt();
        this.zzafp = new ArrayList(v1);
        for(int v = 0; v < v1; ++v) {
            byte[] arr_b = parcel0.createByteArray();
            this.zzafp.add(arr_b);
        }
        this.zzafq = (zziu)parcel0.readParcelable(zziu.class.getClassLoader());
        this.zzafl = (zzlh)parcel0.readParcelable(zzlh.class.getClassLoader());
    }

    private zzgz(String s, String s1, String s2, String s3, int v, int v1, int v2, int v3, float f, int v4, float f1, byte[] arr_b, int v5, zzou zzou0, int v6, int v7, int v8, int v9, int v10, int v11, String s4, int v12, long v13, List list0, zziu zziu0, zzlh zzlh0) {
        this.zzafi = s;
        this.zzafm = s1;
        this.zzafn = s2;
        this.zzafk = s3;
        this.zzafj = v;
        this.zzafo = v1;
        this.width = v2;
        this.height = v3;
        this.zzafr = f;
        this.zzafs = v4;
        this.zzaft = f1;
        this.zzafv = arr_b;
        this.zzafu = v5;
        this.zzafw = zzou0;
        this.zzafx = v6;
        this.zzafy = v7;
        this.zzafz = v8;
        this.zzaga = v9;
        this.zzagb = v10;
        this.zzagd = v11;
        this.zzage = s4;
        this.zzagf = v12;
        this.zzagc = v13;
        this.zzafp = list0 == null ? Collections.emptyList() : list0;
        this.zzafq = zziu0;
        this.zzafl = zzlh0;
    }

    @Override  // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 != null && this.getClass() == object0.getClass()) {
            zzgz zzgz0 = (zzgz)object0;
            if(this.zzafj == zzgz0.zzafj && this.zzafo == zzgz0.zzafo && this.width == zzgz0.width && this.height == zzgz0.height && this.zzafr == zzgz0.zzafr && this.zzafs == zzgz0.zzafs && this.zzaft == zzgz0.zzaft && this.zzafu == zzgz0.zzafu && this.zzafx == zzgz0.zzafx && this.zzafy == zzgz0.zzafy && this.zzafz == zzgz0.zzafz && this.zzaga == zzgz0.zzaga && this.zzagb == zzgz0.zzagb && this.zzagc == zzgz0.zzagc && this.zzagd == zzgz0.zzagd && zzop.zza(this.zzafi, zzgz0.zzafi) && zzop.zza(this.zzage, zzgz0.zzage) && this.zzagf == zzgz0.zzagf && zzop.zza(this.zzafm, zzgz0.zzafm) && zzop.zza(this.zzafn, zzgz0.zzafn) && zzop.zza(this.zzafk, zzgz0.zzafk) && zzop.zza(this.zzafq, zzgz0.zzafq) && zzop.zza(this.zzafl, zzgz0.zzafl) && zzop.zza(this.zzafw, zzgz0.zzafw) && Arrays.equals(this.zzafv, zzgz0.zzafv) && this.zzafp.size() == zzgz0.zzafp.size()) {
                for(int v = 0; v < this.zzafp.size(); ++v) {
                    if(!Arrays.equals(((byte[])this.zzafp.get(v)), ((byte[])zzgz0.zzafp.get(v)))) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            int v = 0;
            int v1 = ((((((((((((this.zzafi == null ? 0 : this.zzafi.hashCode()) + 0x20F) * 0x1F + (this.zzafm == null ? 0 : this.zzafm.hashCode())) * 0x1F + (this.zzafn == null ? 0 : this.zzafn.hashCode())) * 0x1F + (this.zzafk == null ? 0 : this.zzafk.hashCode())) * 0x1F + this.zzafj) * 0x1F + this.width) * 0x1F + this.height) * 0x1F + this.zzafx) * 0x1F + this.zzafy) * 0x1F + (this.zzage == null ? 0 : this.zzage.hashCode())) * 0x1F + this.zzagf) * 0x1F;
            int v2 = this.zzafq == null ? 0 : this.zzafq.hashCode();
            zzlh zzlh0 = this.zzafl;
            if(zzlh0 != null) {
                v = zzlh0.hashCode();
            }
            this.zzagg = (v1 + v2) * 0x1F + v;
        }
        return this.zzagg;
    }

    @Override
    public final String toString() {
        return "Format(" + this.zzafi + ", " + this.zzafm + ", " + this.zzafn + ", " + this.zzafj + ", " + this.zzage + ", [" + this.width + ", " + this.height + ", " + this.zzafr + "], [" + this.zzafx + ", " + this.zzafy + "])";
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeString(this.zzafi);
        parcel0.writeString(this.zzafm);
        parcel0.writeString(this.zzafn);
        parcel0.writeString(this.zzafk);
        parcel0.writeInt(this.zzafj);
        parcel0.writeInt(this.zzafo);
        parcel0.writeInt(this.width);
        parcel0.writeInt(this.height);
        parcel0.writeFloat(this.zzafr);
        parcel0.writeInt(this.zzafs);
        parcel0.writeFloat(this.zzaft);
        parcel0.writeInt((this.zzafv == null ? 0 : 1));
        byte[] arr_b = this.zzafv;
        if(arr_b != null) {
            parcel0.writeByteArray(arr_b);
        }
        parcel0.writeInt(this.zzafu);
        parcel0.writeParcelable(this.zzafw, v);
        parcel0.writeInt(this.zzafx);
        parcel0.writeInt(this.zzafy);
        parcel0.writeInt(this.zzafz);
        parcel0.writeInt(this.zzaga);
        parcel0.writeInt(this.zzagb);
        parcel0.writeInt(this.zzagd);
        parcel0.writeString(this.zzage);
        parcel0.writeInt(this.zzagf);
        parcel0.writeLong(this.zzagc);
        int v1 = this.zzafp.size();
        parcel0.writeInt(v1);
        for(int v2 = 0; v2 < v1; ++v2) {
            parcel0.writeByteArray(((byte[])this.zzafp.get(v2)));
        }
        parcel0.writeParcelable(this.zzafq, 0);
        parcel0.writeParcelable(this.zzafl, 0);
    }

    public static zzgz zza(String s, String s1, String s2, int v, int v1, int v2, int v3, float f, List list0, int v4, float f1, byte[] arr_b, int v5, zzou zzou0, zziu zziu0) {
        return new zzgz(s, null, s1, null, -1, v1, v2, v3, -1.0f, v4, f1, arr_b, v5, zzou0, -1, -1, -1, -1, -1, 0, null, -1, 0x7FFFFFFFFFFFFFFFL, list0, zziu0, null);
    }

    public static zzgz zza(String s, String s1, String s2, int v, int v1, int v2, int v3, int v4, List list0, zziu zziu0, int v5, String s3) {
        return new zzgz(s, null, s1, null, -1, v1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, v2, v3, v4, -1, -1, v5, s3, -1, 0x7FFFFFFFFFFFFFFFL, list0, zziu0, null);
    }

    public static zzgz zza(String s, String s1, String s2, int v, int v1, int v2, int v3, List list0, zziu zziu0, int v4, String s3) {
        return zzgz.zza(s, s1, null, -1, -1, v2, v3, -1, null, zziu0, 0, s3);
    }

    public static zzgz zza(String s, String s1, String s2, int v, int v1, String s3, int v2, zziu zziu0, long v3, List list0) {
        return new zzgz(s, null, s1, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, v1, s3, -1, v3, list0, zziu0, null);
    }

    public static zzgz zza(String s, String s1, String s2, int v, int v1, String s3, zziu zziu0) {
        return zzgz.zza(s, s1, null, -1, v1, s3, -1, zziu0, 0x7FFFFFFFFFFFFFFFL, Collections.emptyList());
    }

    public static zzgz zza(String s, String s1, String s2, int v, zziu zziu0) {
        return new zzgz(s, null, s1, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, 0x7FFFFFFFFFFFFFFFL, null, null, null);
    }

    public static zzgz zza(String s, String s1, String s2, int v, List list0, String s3, zziu zziu0) {
        return new zzgz(s, null, s1, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, s3, -1, 0x7FFFFFFFFFFFFFFFL, list0, zziu0, null);
    }

    @TargetApi(16)
    private static void zza(MediaFormat mediaFormat0, String s, int v) {
        if(v != -1) {
            mediaFormat0.setInteger(s, v);
        }
    }

    public final zzgz zza(zzlh zzlh0) {
        return new zzgz(this.zzafi, this.zzafm, this.zzafn, this.zzafk, this.zzafj, this.zzafo, this.width, this.height, this.zzafr, this.zzafs, this.zzaft, this.zzafv, this.zzafu, this.zzafw, this.zzafx, this.zzafy, this.zzafz, this.zzaga, this.zzagb, this.zzagd, this.zzage, this.zzagf, this.zzagc, this.zzafp, this.zzafq, zzlh0);
    }

    public final zzgz zzb(int v, int v1) {
        return new zzgz(this.zzafi, this.zzafm, this.zzafn, this.zzafk, this.zzafj, this.zzafo, this.width, this.height, this.zzafr, this.zzafs, this.zzaft, this.zzafv, this.zzafu, this.zzafw, this.zzafx, this.zzafy, this.zzafz, v, v1, this.zzagd, this.zzage, this.zzagf, this.zzagc, this.zzafp, this.zzafq, this.zzafl);
    }

    public final zzgz zzds(long v) {
        return new zzgz(this.zzafi, this.zzafm, this.zzafn, this.zzafk, this.zzafj, this.zzafo, this.width, this.height, this.zzafr, this.zzafs, this.zzaft, this.zzafv, this.zzafu, this.zzafw, this.zzafx, this.zzafy, this.zzafz, this.zzaga, this.zzagb, this.zzagd, this.zzage, this.zzagf, v, this.zzafp, this.zzafq, this.zzafl);
    }

    public final int zzeu() {
        int v = this.width;
        if(v != -1) {
            return this.height == -1 ? -1 : v * this.height;
        }
        return -1;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat zzev() {
        MediaFormat mediaFormat0 = new MediaFormat();
        mediaFormat0.setString("mime", this.zzafn);
        String s = this.zzage;
        if(s != null) {
            mediaFormat0.setString("language", s);
        }
        zzgz.zza(mediaFormat0, "max-input-size", this.zzafo);
        zzgz.zza(mediaFormat0, "width", this.width);
        zzgz.zza(mediaFormat0, "height", this.height);
        float f = this.zzafr;
        if(f != -1.0f) {
            mediaFormat0.setFloat("frame-rate", f);
        }
        zzgz.zza(mediaFormat0, "rotation-degrees", this.zzafs);
        zzgz.zza(mediaFormat0, "channel-count", this.zzafx);
        zzgz.zza(mediaFormat0, "sample-rate", this.zzafy);
        zzgz.zza(mediaFormat0, "encoder-delay", this.zzaga);
        zzgz.zza(mediaFormat0, "encoder-padding", this.zzagb);
        for(int v = 0; v < this.zzafp.size(); ++v) {
            mediaFormat0.setByteBuffer("csd-" + v, ByteBuffer.wrap(((byte[])this.zzafp.get(v))));
        }
        zzou zzou0 = this.zzafw;
        if(zzou0 != null) {
            zzgz.zza(mediaFormat0, "color-transfer", zzou0.zzaqg);
            zzgz.zza(mediaFormat0, "color-standard", zzou0.zzaqf);
            zzgz.zza(mediaFormat0, "color-range", zzou0.zzaqh);
            byte[] arr_b = zzou0.zzbhi;
            if(arr_b != null) {
                mediaFormat0.setByteBuffer("hdr-static-info", ByteBuffer.wrap(arr_b));
            }
        }
        return mediaFormat0;
    }

    public final zzgz zzp(int v) {
        return new zzgz(this.zzafi, this.zzafm, this.zzafn, this.zzafk, this.zzafj, v, this.width, this.height, this.zzafr, this.zzafs, this.zzaft, this.zzafv, this.zzafu, this.zzafw, this.zzafx, this.zzafy, this.zzafz, this.zzaga, this.zzagb, this.zzagd, this.zzage, this.zzagf, this.zzagc, this.zzafp, this.zzafq, this.zzafl);
    }
}

