package com.google.android.gms.common.internal.safeparcel;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import android.util.SparseLongArray;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SafeParcelReader {
    public static class ParseException extends RuntimeException {
        public ParseException(String s, Parcel parcel0) {
            super(s + " Parcel: pos=" + parcel0.dataPosition() + " size=" + parcel0.dataSize());
        }
    }

    public static BigDecimal createBigDecimal(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        byte[] arr_b = parcel0.createByteArray();
        int v3 = parcel0.readInt();
        parcel0.setDataPosition(v2 + v1);
        return new BigDecimal(new BigInteger(arr_b), v3);
    }

    public static BigDecimal[] createBigDecimalArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        BigDecimal[] arr_bigDecimal = new BigDecimal[v3];
        for(int v4 = 0; v4 < v3; ++v4) {
            byte[] arr_b = parcel0.createByteArray();
            int v5 = parcel0.readInt();
            arr_bigDecimal[v4] = new BigDecimal(new BigInteger(arr_b), v5);
        }
        parcel0.setDataPosition(v2 + v1);
        return arr_bigDecimal;
    }

    public static BigInteger createBigInteger(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        byte[] arr_b = parcel0.createByteArray();
        parcel0.setDataPosition(v2 + v1);
        return new BigInteger(arr_b);
    }

    public static BigInteger[] createBigIntegerArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        BigInteger[] arr_bigInteger = new BigInteger[v3];
        for(int v4 = 0; v4 < v3; ++v4) {
            arr_bigInteger[v4] = new BigInteger(parcel0.createByteArray());
        }
        parcel0.setDataPosition(v2 + v1);
        return arr_bigInteger;
    }

    public static boolean[] createBooleanArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        boolean[] arr_z = parcel0.createBooleanArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_z;
    }

    public static ArrayList createBooleanList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            arrayList0.add(Boolean.valueOf(parcel0.readInt() != 0));
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static Bundle createBundle(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        Bundle bundle0 = parcel0.readBundle();
        parcel0.setDataPosition(v2 + v1);
        return bundle0;
    }

    public static byte[] createByteArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        byte[] arr_b = parcel0.createByteArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_b;
    }

    public static byte[][] createByteArrayArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        byte[][] arr2_b = new byte[v3][];
        for(int v4 = 0; v4 < v3; ++v4) {
            arr2_b[v4] = parcel0.createByteArray();
        }
        parcel0.setDataPosition(v2 + v1);
        return arr2_b;
    }

    public static SparseArray createByteArraySparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        SparseArray sparseArray0 = new SparseArray(v3);
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), parcel0.createByteArray());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static char[] createCharArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        char[] arr_c = parcel0.createCharArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_c;
    }

    public static double[] createDoubleArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        double[] arr_f = parcel0.createDoubleArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_f;
    }

    public static ArrayList createDoubleList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            arrayList0.add(parcel0.readDouble());
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createDoubleSparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseArray sparseArray0 = new SparseArray();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), parcel0.readDouble());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static float[] createFloatArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        float[] arr_f = parcel0.createFloatArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_f;
    }

    public static ArrayList createFloatList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            arrayList0.add(parcel0.readFloat());
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createFloatSparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseArray sparseArray0 = new SparseArray();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), parcel0.readFloat());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static IBinder[] createIBinderArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        IBinder[] arr_iBinder = parcel0.createBinderArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_iBinder;
    }

    public static ArrayList createIBinderList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = parcel0.createBinderArrayList();
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createIBinderSparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        SparseArray sparseArray0 = new SparseArray(v3);
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), parcel0.readStrongBinder());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static int[] createIntArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int[] arr_v = parcel0.createIntArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_v;
    }

    public static ArrayList createIntegerList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            arrayList0.add(parcel0.readInt());
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static long[] createLongArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        long[] arr_v = parcel0.createLongArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_v;
    }

    public static ArrayList createLongList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = new ArrayList();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            arrayList0.add(parcel0.readLong());
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static Parcel createParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        Parcel parcel1 = Parcel.obtain();
        parcel1.appendFrom(parcel0, v2, v1);
        parcel0.setDataPosition(v2 + v1);
        return parcel1;
    }

    public static Parcel[] createParcelArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        Parcel[] arr_parcel = new Parcel[v3];
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = parcel0.readInt();
            if(v5 == 0) {
                arr_parcel[v4] = null;
            }
            else {
                int v6 = parcel0.dataPosition();
                Parcel parcel1 = Parcel.obtain();
                parcel1.appendFrom(parcel0, v6, v5);
                arr_parcel[v4] = parcel1;
                parcel0.setDataPosition(v6 + v5);
            }
        }
        parcel0.setDataPosition(v2 + v1);
        return arr_parcel;
    }

    public static ArrayList createParcelList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        ArrayList arrayList0 = new ArrayList();
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = parcel0.readInt();
            if(v5 == 0) {
                arrayList0.add(null);
            }
            else {
                int v6 = parcel0.dataPosition();
                Parcel parcel1 = Parcel.obtain();
                parcel1.appendFrom(parcel0, v6, v5);
                arrayList0.add(parcel1);
                parcel0.setDataPosition(v6 + v5);
            }
        }
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createParcelSparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        SparseArray sparseArray0 = new SparseArray();
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = parcel0.readInt();
            int v6 = parcel0.readInt();
            if(v6 == 0) {
                sparseArray0.append(v5, null);
            }
            else {
                int v7 = parcel0.dataPosition();
                Parcel parcel1 = Parcel.obtain();
                parcel1.appendFrom(parcel0, v7, v6);
                sparseArray0.append(v5, parcel1);
                parcel0.setDataPosition(v7 + v6);
            }
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static Parcelable createParcelable(Parcel parcel0, int v, Parcelable.Creator parcelable$Creator0) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        Parcelable parcelable0 = (Parcelable)parcelable$Creator0.createFromParcel(parcel0);
        parcel0.setDataPosition(v2 + v1);
        return parcelable0;
    }

    public static SparseBooleanArray createSparseBooleanArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseBooleanArray sparseBooleanArray0 = parcel0.readSparseBooleanArray();
        parcel0.setDataPosition(v2 + v1);
        return sparseBooleanArray0;
    }

    public static SparseIntArray createSparseIntArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseIntArray sparseIntArray0 = new SparseIntArray();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseIntArray0.append(parcel0.readInt(), parcel0.readInt());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseIntArray0;
    }

    public static SparseLongArray createSparseLongArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseLongArray sparseLongArray0 = new SparseLongArray();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseLongArray0.append(parcel0.readInt(), parcel0.readLong());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseLongArray0;
    }

    public static String createString(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        String s = parcel0.readString();
        parcel0.setDataPosition(v2 + v1);
        return s;
    }

    public static String[] createStringArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        String[] arr_s = parcel0.createStringArray();
        parcel0.setDataPosition(v2 + v1);
        return arr_s;
    }

    public static ArrayList createStringList(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = parcel0.createStringArrayList();
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createStringSparseArray(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        SparseArray sparseArray0 = new SparseArray();
        int v3 = parcel0.readInt();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), parcel0.readString());
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static Object[] createTypedArray(Parcel parcel0, int v, Parcelable.Creator parcelable$Creator0) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        Object[] arr_object = parcel0.createTypedArray(parcelable$Creator0);
        parcel0.setDataPosition(v2 + v1);
        return arr_object;
    }

    public static ArrayList createTypedList(Parcel parcel0, int v, Parcelable.Creator parcelable$Creator0) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        ArrayList arrayList0 = parcel0.createTypedArrayList(parcelable$Creator0);
        parcel0.setDataPosition(v2 + v1);
        return arrayList0;
    }

    public static SparseArray createTypedSparseArray(Parcel parcel0, int v, Parcelable.Creator parcelable$Creator0) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        int v3 = parcel0.readInt();
        SparseArray sparseArray0 = new SparseArray();
        for(int v4 = 0; v4 < v3; ++v4) {
            sparseArray0.append(parcel0.readInt(), (parcel0.readInt() == 0 ? null : parcelable$Creator0.createFromParcel(parcel0)));
        }
        parcel0.setDataPosition(v2 + v1);
        return sparseArray0;
    }

    public static void ensureAtEnd(Parcel parcel0, int v) {
        if(parcel0.dataPosition() != v) {
            throw new ParseException("Overread allowed size end=" + v, parcel0);
        }
    }

    public static int getFieldId(int v) [...] // Inlined contents

    public static boolean readBoolean(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return parcel0.readInt() != 0;
    }

    public static Boolean readBooleanObject(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        if(v1 == 0) {
            return null;
        }
        SafeParcelReader.zza(parcel0, v, v1, 4);
        return parcel0.readInt() == 0 ? false : true;
    }

    public static byte readByte(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return (byte)parcel0.readInt();
    }

    public static char readChar(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return (char)parcel0.readInt();
    }

    public static double readDouble(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 8);
        return parcel0.readDouble();
    }

    public static Double readDoubleObject(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        if(v1 == 0) {
            return null;
        }
        SafeParcelReader.zza(parcel0, v, v1, 8);
        return parcel0.readDouble();
    }

    public static float readFloat(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return parcel0.readFloat();
    }

    public static Float readFloatObject(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        if(v1 == 0) {
            return null;
        }
        SafeParcelReader.zza(parcel0, v, v1, 4);
        return parcel0.readFloat();
    }

    public static int readHeader(Parcel parcel0) {
        return parcel0.readInt();
    }

    public static IBinder readIBinder(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return null;
        }
        IBinder iBinder0 = parcel0.readStrongBinder();
        parcel0.setDataPosition(v2 + v1);
        return iBinder0;
    }

    public static int readInt(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return parcel0.readInt();
    }

    public static Integer readIntegerObject(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        if(v1 == 0) {
            return null;
        }
        SafeParcelReader.zza(parcel0, v, v1, 4);
        return parcel0.readInt();
    }

    public static void readList(Parcel parcel0, int v, List list0, ClassLoader classLoader0) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if(v1 == 0) {
            return;
        }
        parcel0.readList(list0, classLoader0);
        parcel0.setDataPosition(v2 + v1);
    }

    public static long readLong(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 8);
        return parcel0.readLong();
    }

    public static Long readLongObject(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        if(v1 == 0) {
            return null;
        }
        SafeParcelReader.zza(parcel0, v, v1, 8);
        return parcel0.readLong();
    }

    public static short readShort(Parcel parcel0, int v) {
        SafeParcelReader.zza(parcel0, v, 4);
        return (short)parcel0.readInt();
    }

    public static int readSize(Parcel parcel0, int v) {
        return (v & 0xFFFF0000) == 0xFFFF0000 ? parcel0.readInt() : v >> 16 & 0xFFFF;
    }

    public static void skipUnknownField(Parcel parcel0, int v) {
        int v1 = SafeParcelReader.readSize(parcel0, v);
        parcel0.setDataPosition(parcel0.dataPosition() + v1);
    }

    public static int validateObjectHeader(Parcel parcel0) {
        int v = SafeParcelReader.readHeader(parcel0);
        int v1 = SafeParcelReader.readSize(parcel0, v);
        int v2 = parcel0.dataPosition();
        if((v & 0xFFFF) != 0x4F45) {
            String s = Integer.toHexString(v);
            throw new ParseException((s.length() == 0 ? new String("Expected object header. Got 0x") : "Expected object header. Got 0x" + s), parcel0);
        }
        int v3 = v1 + v2;
        if(v3 < v2 || v3 > parcel0.dataSize()) {
            throw new ParseException("Size read is invalid start=" + v2 + " end=" + v3, parcel0);
        }
        return v3;
    }

    private static void zza(Parcel parcel0, int v, int v1) {
        int v2 = SafeParcelReader.readSize(parcel0, v);
        if(v2 != v1) {
            throw new ParseException("Expected size " + v1 + " got " + v2 + " (0x" + Integer.toHexString(v2) + ")", parcel0);
        }
    }

    private static void zza(Parcel parcel0, int v, int v1, int v2) {
        if(v1 != v2) {
            throw new ParseException("Expected size " + v2 + " got " + v1 + " (0x" + Integer.toHexString(v1) + ")", parcel0);
        }
    }
}

