package com.google.android.gms.common.util;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@KeepForSdk
@VisibleForTesting
public final class ArrayUtils {
    @KeepForSdk
    public static Object[] appendToArray(Object[] arr_object, Object object0) {
        if(arr_object == null && object0 == null) {
            throw new IllegalArgumentException("Cannot generate array of generic type w/o class info");
        }
        Object[] arr_object1 = arr_object == null ? ((Object[])Array.newInstance(object0.getClass(), 1)) : Arrays.copyOf(arr_object, arr_object.length + 1);
        arr_object1[arr_object1.length - 1] = object0;
        return arr_object1;
    }

    @KeepForSdk
    public static Object[] concat(Object[][] arr2_object) {
        if(arr2_object.length == 0) {
            return (Object[])Array.newInstance(arr2_object.getClass(), 0);
        }
        int v1 = 0;
        for(int v = 0; v < arr2_object.length; ++v) {
            v1 += arr2_object[v].length;
        }
        Object[] arr_object = Arrays.copyOf(arr2_object[0], v1);
        int v2 = arr2_object[0].length;
        for(int v3 = 1; v3 < arr2_object.length; ++v3) {
            Object[] arr_object1 = arr2_object[v3];
            System.arraycopy(arr_object1, 0, arr_object, v2, arr_object1.length);
            v2 += arr_object1.length;
        }
        return arr_object;
    }

    @KeepForSdk
    public static byte[] concatByteArrays(byte[][] arr2_b) {
        if(arr2_b.length == 0) {
            return new byte[0];
        }
        int v1 = 0;
        for(int v = 0; v < arr2_b.length; ++v) {
            v1 += arr2_b[v].length;
        }
        byte[] arr_b = Arrays.copyOf(arr2_b[0], v1);
        int v2 = arr2_b[0].length;
        for(int v3 = 1; v3 < arr2_b.length; ++v3) {
            byte[] arr_b1 = arr2_b[v3];
            System.arraycopy(arr_b1, 0, arr_b, v2, arr_b1.length);
            v2 += arr_b1.length;
        }
        return arr_b;
    }

    @KeepForSdk
    public static boolean contains(int[] arr_v, int v) {
        if(arr_v == null) {
            return false;
        }
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            if(arr_v[v1] == v) {
                return true;
            }
        }
        return false;
    }

    @KeepForSdk
    public static boolean contains(Object[] arr_object, Object object0) {
        int v = arr_object == null ? 0 : arr_object.length;
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= v) {
                v1 = -1;
                break;
            }
            if(Objects.equal(arr_object[v1], object0)) {
                break;
            }
        }
        return v1 >= 0;
    }

    @KeepForSdk
    public static ArrayList newArrayList() {
        return new ArrayList();
    }

    @KeepForSdk
    public static Object[] removeAll(Object[] arr_object, Object[] arr_object1) {
        int v2;
        if(arr_object == null) {
            return null;
        }
        if(arr_object1 != null && arr_object1.length != 0) {
            Object[] arr_object2 = (Object[])Array.newInstance(arr_object1.getClass().getComponentType(), arr_object.length);
            if(arr_object1.length == 1) {
                v2 = 0;
                for(int v1 = 0; v1 < arr_object.length; ++v1) {
                    Object object0 = arr_object[v1];
                    if(!Objects.equal(arr_object1[0], object0)) {
                        arr_object2[v2] = object0;
                        ++v2;
                    }
                }
            }
            else {
                v2 = 0;
                for(int v = 0; v < arr_object.length; ++v) {
                    Object object1 = arr_object[v];
                    if(!ArrayUtils.contains(arr_object1, object1)) {
                        arr_object2[v2] = object1;
                        ++v2;
                    }
                }
            }
            if(arr_object2 == null) {
                return null;
            }
            return v2 == arr_object2.length ? arr_object2 : Arrays.copyOf(arr_object2, v2);
        }
        return Arrays.copyOf(arr_object, arr_object.length);
    }

    @KeepForSdk
    public static ArrayList toArrayList(Object[] arr_object) {
        ArrayList arrayList0 = new ArrayList(arr_object.length);
        for(int v = 0; v < arr_object.length; ++v) {
            arrayList0.add(arr_object[v]);
        }
        return arrayList0;
    }

    @KeepForSdk
    public static int[] toPrimitiveArray(Collection collection0) {
        int v = 0;
        if(collection0 != null && collection0.size() != 0) {
            int[] arr_v = new int[collection0.size()];
            for(Object object0: collection0) {
                arr_v[v] = (int)(((Integer)object0));
                ++v;
            }
            return arr_v;
        }
        return new int[0];
    }

    @KeepForSdk
    public static Integer[] toWrapperArray(int[] arr_v) {
        if(arr_v == null) {
            return null;
        }
        Integer[] arr_integer = new Integer[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_integer[v] = (int)arr_v[v];
        }
        return arr_integer;
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, double[] arr_f) {
        for(int v = 0; v < arr_f.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(Double.toString(arr_f[v]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, float[] arr_f) {
        for(int v = 0; v < arr_f.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(Float.toString(arr_f[v]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, int[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(Integer.toString(arr_v[v]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, long[] arr_v) {
        for(int v = 0; v < arr_v.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(Long.toString(arr_v[v]));
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, Object[] arr_object) {
        for(int v = 0; v < arr_object.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(arr_object[v].toString());
        }
    }

    @KeepForSdk
    public static void writeArray(StringBuilder stringBuilder0, boolean[] arr_z) {
        for(int v = 0; v < arr_z.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append(Boolean.toString(arr_z[v]));
        }
    }

    @KeepForSdk
    public static void writeStringArray(StringBuilder stringBuilder0, String[] arr_s) {
        for(int v = 0; v < arr_s.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(",");
            }
            stringBuilder0.append("\"");
            stringBuilder0.append(arr_s[v]);
            stringBuilder0.append("\"");
        }
    }
}

