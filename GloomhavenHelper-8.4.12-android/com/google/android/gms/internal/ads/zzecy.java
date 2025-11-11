package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzecy implements Cloneable {
    private Object value;
    private zzecw zziaa;
    private List zziab;

    zzecy() {
        this.zziab = new ArrayList();
    }

    @Override
    public final Object clone() throws CloneNotSupportedException {
        return this.zzbfr();
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof zzecy)) {
            return false;
        }
        if(this.value != null && ((zzecy)object0).value != null) {
            zzecw zzecw0 = this.zziaa;
            if(zzecw0 != ((zzecy)object0).zziaa) {
                return false;
            }
            if(!zzecw0.zzhdy.isArray()) {
                return this.value.equals(((zzecy)object0).value);
            }
            Object object1 = this.value;
            if(object1 instanceof byte[]) {
                return Arrays.equals(((byte[])object1), ((byte[])((zzecy)object0).value));
            }
            if(object1 instanceof int[]) {
                return Arrays.equals(((int[])object1), ((int[])((zzecy)object0).value));
            }
            if(object1 instanceof long[]) {
                return Arrays.equals(((long[])object1), ((long[])((zzecy)object0).value));
            }
            if(object1 instanceof float[]) {
                return Arrays.equals(((float[])object1), ((float[])((zzecy)object0).value));
            }
            if(object1 instanceof double[]) {
                return Arrays.equals(((double[])object1), ((double[])((zzecy)object0).value));
            }
            return object1 instanceof boolean[] ? Arrays.equals(((boolean[])object1), ((boolean[])((zzecy)object0).value)) : Arrays.deepEquals(((Object[])object1), ((Object[])((zzecy)object0).value));
        }
        List list0 = this.zziab;
        if(list0 != null) {
            List list1 = ((zzecy)object0).zziab;
            if(list1 != null) {
                return list0.equals(list1);
            }
        }
        try {
            return Arrays.equals(new byte[0], new byte[0]);
        }
        catch(IOException iOException0) {
            throw new IllegalStateException(iOException0);
        }
    }

    @Override
    public final int hashCode() {
        try {
            return Arrays.hashCode(new byte[0]) + 0x20F;
        }
        catch(IOException iOException0) {
            throw new IllegalStateException(iOException0);
        }
    }

    private final byte[] toByteArray() throws IOException [...] // 潜在的解密器

    final void zza(zzecr zzecr0) throws IOException {
        if(this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator iterator0 = this.zziab.iterator();
        if(!iterator0.hasNext()) {
            return;
        }
        Object object0 = iterator0.next();
        zzedc zzedc0 = (zzedc)object0;
        throw new NoSuchMethodError();
    }

    private final zzecy zzbfr() {
        zzecy zzecy0 = new zzecy();
        try {
            zzecy0.zziaa = this.zziaa;
            if(this.zziab == null) {
                zzecy0.zziab = null;
            }
            else {
                zzecy0.zziab.addAll(this.zziab);
            }
            int v = 0;
            if(this.value == null) {
                return zzecy0;
            }
            if(this.value instanceof zzeda) {
                zzecy0.value = (zzeda)((zzeda)this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof byte[]) {
                zzecy0.value = ((byte[])this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof byte[][]) {
                byte[][] arr2_b = (byte[][])this.value;
                byte[][] arr2_b1 = new byte[arr2_b.length][];
                zzecy0.value = arr2_b1;
                while(true) {
                    if(v >= arr2_b.length) {
                        return zzecy0;
                    }
                    arr2_b1[v] = (byte[])arr2_b[v].clone();
                    ++v;
                }
            }
            if(this.value instanceof boolean[]) {
                zzecy0.value = ((boolean[])this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof int[]) {
                zzecy0.value = ((int[])this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof long[]) {
                zzecy0.value = ((long[])this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof float[]) {
                zzecy0.value = ((float[])this.value).clone();
                return zzecy0;
            }
            if(this.value instanceof double[]) {
                zzecy0.value = ((double[])this.value).clone();
                return zzecy0;
            }
            if(!(this.value instanceof zzeda[])) {
                return zzecy0;
            }
            zzeda[] arr_zzeda = (zzeda[])this.value;
            zzeda[] arr_zzeda1 = new zzeda[arr_zzeda.length];
            zzecy0.value = arr_zzeda1;
            while(v < arr_zzeda.length) {
                arr_zzeda1[v] = (zzeda)arr_zzeda[v].clone();
                ++v;
            }
            return zzecy0;
        }
        catch(CloneNotSupportedException cloneNotSupportedException0) {
            throw new AssertionError(cloneNotSupportedException0);
        }
    }

    final int zzon() {
        if(this.value != null) {
            throw new NoSuchMethodError();
        }
        Iterator iterator0 = this.zziab.iterator();
        if(!iterator0.hasNext()) {
            return 0;
        }
        Object object0 = iterator0.next();
        zzedc zzedc0 = (zzedc)object0;
        throw new NoSuchMethodError();
    }
}

