package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class SafeParcelWriter {
    public static int beginObjectHeader(Parcel parcel0) {
        return SafeParcelWriter.zza(parcel0, 0x4F45);
    }

    public static void finishObjectHeader(Parcel parcel0, int v) {
        SafeParcelWriter.zzb(parcel0, v);
    }

    public static void writeBigDecimal(Parcel parcel0, int v, BigDecimal bigDecimal0, boolean z) {
        if(bigDecimal0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeByteArray(bigDecimal0.unscaledValue().toByteArray());
        parcel0.writeInt(bigDecimal0.scale());
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeBigDecimalArray(Parcel parcel0, int v, BigDecimal[] arr_bigDecimal, boolean z) {
        if(arr_bigDecimal == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeInt(arr_bigDecimal.length);
        for(int v1 = 0; v1 < arr_bigDecimal.length; ++v1) {
            parcel0.writeByteArray(arr_bigDecimal[v1].unscaledValue().toByteArray());
            parcel0.writeInt(arr_bigDecimal[v1].scale());
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeBigInteger(Parcel parcel0, int v, BigInteger bigInteger0, boolean z) {
        if(bigInteger0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeByteArray(bigInteger0.toByteArray());
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeBigIntegerArray(Parcel parcel0, int v, BigInteger[] arr_bigInteger, boolean z) {
        if(arr_bigInteger == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeInt(arr_bigInteger.length);
        for(int v1 = 0; v1 < arr_bigInteger.length; ++v1) {
            parcel0.writeByteArray(arr_bigInteger[v1].toByteArray());
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeBoolean(Parcel parcel0, int v, boolean z) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)z));
    }

    public static void writeBooleanArray(Parcel parcel0, int v, boolean[] arr_z, boolean z) {
        if(arr_z == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeBooleanArray(arr_z);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeBooleanList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = list0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(((int)((Boolean)list0.get(v1)).booleanValue()));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeBooleanObject(Parcel parcel0, int v, Boolean boolean0, boolean z) {
        if(boolean0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)boolean0.booleanValue()));
    }

    public static void writeBundle(Parcel parcel0, int v, Bundle bundle0, boolean z) {
        if(bundle0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeBundle(bundle0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeByte(Parcel parcel0, int v, byte b) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)b));
    }

    public static void writeByteArray(Parcel parcel0, int v, byte[] arr_b, boolean z) {
        if(arr_b == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeByteArray(arr_b);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeByteArrayArray(Parcel parcel0, int v, byte[][] arr2_b, boolean z) {
        if(arr2_b == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeInt(arr2_b.length);
        for(int v1 = 0; v1 < arr2_b.length; ++v1) {
            parcel0.writeByteArray(arr2_b[v1]);
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeByteArraySparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseArray0.keyAt(v1));
            parcel0.writeByteArray(((byte[])sparseArray0.valueAt(v1)));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeChar(Parcel parcel0, int v, char c) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)c));
    }

    public static void writeCharArray(Parcel parcel0, int v, char[] arr_c, boolean z) {
        if(arr_c == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeCharArray(arr_c);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeDouble(Parcel parcel0, int v, double f) {
        SafeParcelWriter.zzb(parcel0, v, 8);
        parcel0.writeDouble(f);
    }

    public static void writeDoubleArray(Parcel parcel0, int v, double[] arr_f, boolean z) {
        if(arr_f == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeDoubleArray(arr_f);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeDoubleList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = list0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeDouble(((double)(((Double)list0.get(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeDoubleObject(Parcel parcel0, int v, Double double0, boolean z) {
        if(double0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        SafeParcelWriter.zzb(parcel0, v, 8);
        parcel0.writeDouble(((double)double0));
    }

    public static void writeDoubleSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseArray0.keyAt(v1));
            parcel0.writeDouble(((double)(((Double)sparseArray0.valueAt(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeFloat(Parcel parcel0, int v, float f) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeFloat(f);
    }

    public static void writeFloatArray(Parcel parcel0, int v, float[] arr_f, boolean z) {
        if(arr_f == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeFloatArray(arr_f);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeFloatList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = list0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeFloat(((float)(((Float)list0.get(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeFloatObject(Parcel parcel0, int v, Float float0, boolean z) {
        if(float0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeFloat(((float)float0));
    }

    public static void writeFloatSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseArray0.keyAt(v1));
            parcel0.writeFloat(((float)(((Float)sparseArray0.valueAt(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeIBinder(Parcel parcel0, int v, IBinder iBinder0, boolean z) {
        if(iBinder0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeStrongBinder(iBinder0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeIBinderArray(Parcel parcel0, int v, IBinder[] arr_iBinder, boolean z) {
        if(arr_iBinder == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeBinderArray(arr_iBinder);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeIBinderList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeBinderList(list0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeIBinderSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseArray0.keyAt(v1));
            parcel0.writeStrongBinder(((IBinder)sparseArray0.valueAt(v1)));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeInt(Parcel parcel0, int v, int v1) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(v1);
    }

    public static void writeIntArray(Parcel parcel0, int v, int[] arr_v, boolean z) {
        if(arr_v == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeIntArray(arr_v);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeIntegerList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = list0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(((int)(((Integer)list0.get(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeIntegerObject(Parcel parcel0, int v, Integer integer0, boolean z) {
        if(integer0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)integer0));
    }

    public static void writeList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeList(list0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeLong(Parcel parcel0, int v, long v1) {
        SafeParcelWriter.zzb(parcel0, v, 8);
        parcel0.writeLong(v1);
    }

    public static void writeLongArray(Parcel parcel0, int v, long[] arr_v, boolean z) {
        if(arr_v == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeLongArray(arr_v);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeLongList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = list0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeLong(((long)(((Long)list0.get(v1)))));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeLongObject(Parcel parcel0, int v, Long long0, boolean z) {
        if(long0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        SafeParcelWriter.zzb(parcel0, v, 8);
        parcel0.writeLong(((long)long0));
    }

    public static void writeParcel(Parcel parcel0, int v, Parcel parcel1, boolean z) {
        if(parcel1 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.appendFrom(parcel1, 0, parcel1.dataSize());
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeParcelArray(Parcel parcel0, int v, Parcel[] arr_parcel, boolean z) {
        if(arr_parcel == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeInt(arr_parcel.length);
        for(int v2 = 0; v2 < arr_parcel.length; ++v2) {
            Parcel parcel1 = arr_parcel[v2];
            if(parcel1 == null) {
                parcel0.writeInt(0);
            }
            else {
                parcel0.writeInt(parcel1.dataSize());
                parcel0.appendFrom(parcel1, 0, parcel1.dataSize());
            }
        }
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeParcelList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        int v2 = list0.size();
        parcel0.writeInt(v2);
        for(int v3 = 0; v3 < v2; ++v3) {
            Parcel parcel1 = (Parcel)list0.get(v3);
            if(parcel1 == null) {
                parcel0.writeInt(0);
            }
            else {
                parcel0.writeInt(parcel1.dataSize());
                parcel0.appendFrom(parcel1, 0, parcel1.dataSize());
            }
        }
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeParcelSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        int v2 = sparseArray0.size();
        parcel0.writeInt(v2);
        for(int v3 = 0; v3 < v2; ++v3) {
            parcel0.writeInt(sparseArray0.keyAt(v3));
            Parcel parcel1 = (Parcel)sparseArray0.valueAt(v3);
            if(parcel1 == null) {
                parcel0.writeInt(0);
            }
            else {
                parcel0.writeInt(parcel1.dataSize());
                parcel0.appendFrom(parcel1, 0, parcel1.dataSize());
            }
        }
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeParcelable(Parcel parcel0, int v, Parcelable parcelable0, int v1, boolean z) {
        if(parcelable0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        parcelable0.writeToParcel(parcel0, v1);
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeShort(Parcel parcel0, int v, short v1) {
        SafeParcelWriter.zzb(parcel0, v, 4);
        parcel0.writeInt(((int)v1));
    }

    public static void writeSparseBooleanArray(Parcel parcel0, int v, SparseBooleanArray sparseBooleanArray0, boolean z) {
        if(sparseBooleanArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeSparseBooleanArray(sparseBooleanArray0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeSparseIntArray(Parcel parcel0, int v, SparseIntArray sparseIntArray0, boolean z) {
        if(sparseIntArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseIntArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseIntArray0.keyAt(v1));
            parcel0.writeInt(sparseIntArray0.valueAt(v1));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeSparseLongArray(Parcel parcel0, int v, SparseLongArray sparseLongArray0, boolean z) {
        if(sparseLongArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseLongArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseLongArray0.keyAt(v1));
            parcel0.writeLong(sparseLongArray0.valueAt(v1));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeString(Parcel parcel0, int v, String s, boolean z) {
        if(s == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeString(s);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeStringArray(Parcel parcel0, int v, String[] arr_s, boolean z) {
        if(arr_s == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeStringArray(arr_s);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeStringList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeStringList(list0);
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeStringSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        int v3 = sparseArray0.size();
        parcel0.writeInt(v3);
        for(int v1 = 0; v1 < v3; ++v1) {
            parcel0.writeInt(sparseArray0.keyAt(v1));
            parcel0.writeString(((String)sparseArray0.valueAt(v1)));
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeTypedArray(Parcel parcel0, int v, Parcelable[] arr_parcelable, int v1, boolean z) {
        if(arr_parcelable == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v2 = SafeParcelWriter.zza(parcel0, v);
        parcel0.writeInt(arr_parcelable.length);
        for(int v3 = 0; v3 < arr_parcelable.length; ++v3) {
            Parcelable parcelable0 = arr_parcelable[v3];
            if(parcelable0 == null) {
                parcel0.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(parcel0, parcelable0, v1);
            }
        }
        SafeParcelWriter.zzb(parcel0, v2);
    }

    public static void writeTypedList(Parcel parcel0, int v, List list0, boolean z) {
        if(list0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        int v2 = list0.size();
        parcel0.writeInt(v2);
        for(int v3 = 0; v3 < v2; ++v3) {
            Parcelable parcelable0 = (Parcelable)list0.get(v3);
            if(parcelable0 == null) {
                parcel0.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(parcel0, parcelable0, 0);
            }
        }
        SafeParcelWriter.zzb(parcel0, v1);
    }

    public static void writeTypedSparseArray(Parcel parcel0, int v, SparseArray sparseArray0, boolean z) {
        if(sparseArray0 == null) {
            if(z) {
                SafeParcelWriter.zzb(parcel0, v, 0);
            }
            return;
        }
        int v1 = SafeParcelWriter.zza(parcel0, v);
        int v2 = sparseArray0.size();
        parcel0.writeInt(v2);
        for(int v3 = 0; v3 < v2; ++v3) {
            parcel0.writeInt(sparseArray0.keyAt(v3));
            Parcelable parcelable0 = (Parcelable)sparseArray0.valueAt(v3);
            if(parcelable0 == null) {
                parcel0.writeInt(0);
            }
            else {
                SafeParcelWriter.zza(parcel0, parcelable0, 0);
            }
        }
        SafeParcelWriter.zzb(parcel0, v1);
    }

    private static int zza(Parcel parcel0, int v) {
        parcel0.writeInt(v | 0xFFFF0000);
        parcel0.writeInt(0);
        return parcel0.dataPosition();
    }

    private static void zza(Parcel parcel0, Parcelable parcelable0, int v) {
        int v1 = parcel0.dataPosition();
        parcel0.writeInt(1);
        int v2 = parcel0.dataPosition();
        parcelable0.writeToParcel(parcel0, v);
        int v3 = parcel0.dataPosition();
        parcel0.setDataPosition(v1);
        parcel0.writeInt(v3 - v2);
        parcel0.setDataPosition(v3);
    }

    private static void zzb(Parcel parcel0, int v) {
        int v1 = parcel0.dataPosition();
        parcel0.setDataPosition(v - 4);
        parcel0.writeInt(v1 - v);
        parcel0.setDataPosition(v1);
    }

    private static void zzb(Parcel parcel0, int v, int v1) {
        if(v1 >= 0xFFFF) {
            parcel0.writeInt(v | 0xFFFF0000);
            parcel0.writeInt(v1);
            return;
        }
        parcel0.writeInt(v | v1 << 16);
    }
}

