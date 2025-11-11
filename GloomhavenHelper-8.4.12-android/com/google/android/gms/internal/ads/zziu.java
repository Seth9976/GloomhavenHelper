package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

public final class zziu implements Parcelable, Comparator {
    public static final class zza implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        private final byte[] data;
        private final String mimeType;
        private final UUID uuid;
        private int zzagg;
        public final boolean zzamw;

        static {
            zza.CREATOR = new zziy();
        }

        zza(Parcel parcel0) {
            this.uuid = new UUID(parcel0.readLong(), parcel0.readLong());
            this.mimeType = parcel0.readString();
            this.data = parcel0.createByteArray();
            this.zzamw = parcel0.readByte() != 0;
        }

        public zza(UUID uUID0, String s, byte[] arr_b) {
            this(uUID0, s, arr_b, false);
        }

        private zza(UUID uUID0, String s, byte[] arr_b, boolean z) {
            this.uuid = (UUID)zzob.checkNotNull(uUID0);
            this.mimeType = (String)zzob.checkNotNull(s);
            this.data = (byte[])zzob.checkNotNull(arr_b);
            this.zzamw = false;
        }

        @Override  // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override
        public final boolean equals(Object object0) {
            if(!(object0 instanceof zza)) {
                return false;
            }
            return object0 == this ? true : this.mimeType.equals(((zza)object0).mimeType) && zzop.zza(this.uuid, ((zza)object0).uuid) && Arrays.equals(this.data, ((zza)object0).data);
        }

        @Override
        public final int hashCode() {
            if(this.zzagg == 0) {
                int v = Arrays.hashCode(this.data);
                this.zzagg = (this.uuid.hashCode() * 0x1F + this.mimeType.hashCode()) * 0x1F + v;
            }
            return this.zzagg;
        }

        @Override  // android.os.Parcelable
        public final void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeLong(this.uuid.getMostSignificantBits());
            parcel0.writeLong(this.uuid.getLeastSignificantBits());
            parcel0.writeString(this.mimeType);
            parcel0.writeByteArray(this.data);
            parcel0.writeByte(((byte)this.zzamw));
        }

        static UUID zza(zza zziu$zza0) {
            return zziu$zza0.uuid;
        }
    }

    public static final Parcelable.Creator CREATOR;
    private int zzagg;
    private final zza[] zzamr;
    public final int zzams;

    static {
        zziu.CREATOR = new zziw();
    }

    zziu(Parcel parcel0) {
        this.zzamr = (zza[])parcel0.createTypedArray(zza.CREATOR);
        this.zzams = this.zzamr.length;
    }

    private zziu(boolean z, zza[] arr_zziu$zza) {
        zza[] arr_zziu$zza1 = (zza[])arr_zziu$zza.clone();
        Arrays.sort(arr_zziu$zza1, this);
        for(int v = 1; v < arr_zziu$zza1.length; ++v) {
            if(zza.zza(arr_zziu$zza1[v - 1]).equals(zza.zza(arr_zziu$zza1[v]))) {
                throw new IllegalArgumentException("Duplicate data for uuid: " + zza.zza(arr_zziu$zza1[v]));
            }
        }
        this.zzamr = arr_zziu$zza1;
        this.zzams = arr_zziu$zza1.length;
    }

    public zziu(zza[] arr_zziu$zza) {
        this(true, arr_zziu$zza);
    }

    @Override
    public final int compare(Object object0, Object object1) {
        UUID uUID0 = zza.zza(((zza)object0));
        if(zzgl.zzacq.equals(uUID0)) {
            UUID uUID1 = zza.zza(((zza)object1));
            return zzgl.zzacq.equals(uUID1) ? 0 : 1;
        }
        return zza.zza(((zza)object0)).compareTo(zza.zza(((zza)object1)));
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
        return object0 == null || this.getClass() != object0.getClass() ? false : Arrays.equals(this.zzamr, ((zziu)object0).zzamr);
    }

    @Override
    public final int hashCode() {
        if(this.zzagg == 0) {
            this.zzagg = Arrays.hashCode(this.zzamr);
        }
        return this.zzagg;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        parcel0.writeTypedArray(this.zzamr, 0);
    }

    public final zza zzy(int v) {
        return this.zzamr[v];
    }
}

