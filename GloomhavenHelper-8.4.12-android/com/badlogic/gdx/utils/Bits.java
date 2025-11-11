package com.badlogic.gdx.utils;

import java.util.Arrays;

public class Bits {
    long[] bits;

    public Bits() {
        this.bits = new long[]{0L};
    }

    public Bits(int v) {
        this.bits = new long[]{0L};
        this.checkCapacity(v >>> 6);
    }

    public void and(Bits bits0) {
        int v = Math.min(this.bits.length, bits0.bits.length);
        for(int v1 = 0; v > v1; ++v1) {
            this.bits[v1] &= bits0.bits[v1];
        }
        long[] arr_v = this.bits;
        if(arr_v.length > v) {
            while(arr_v.length > v) {
                this.bits[v] = 0L;
                ++v;
            }
        }
    }

    public void andNot(Bits bits0) {
        for(int v = 0; v < this.bits.length && v < bits0.bits.length; ++v) {
            this.bits[v] &= ~bits0.bits[v];
        }
    }

    private void checkCapacity(int v) {
        long[] arr_v = this.bits;
        if(v >= arr_v.length) {
            long[] arr_v1 = new long[v + 1];
            System.arraycopy(arr_v, 0, arr_v1, 0, arr_v.length);
            this.bits = arr_v1;
        }
    }

    public void clear() {
        Arrays.fill(this.bits, 0L);
    }

    public void clear(int v) {
        long[] arr_v = this.bits;
        if(v >>> 6 >= arr_v.length) {
            return;
        }
        arr_v[v >>> 6] &= ~(1L << (v & 0x3F));
    }

    public boolean containsAll(Bits bits0) {
        long[] arr_v = this.bits;
        long[] arr_v1 = bits0.bits;
        for(int v = arr_v.length; v < arr_v1.length; ++v) {
            if(arr_v1[v] != 0L) {
                return false;
            }
        }
        for(int v1 = Math.min(arr_v.length, arr_v1.length) - 1; v1 >= 0; --v1) {
            if((arr_v[v1] & arr_v1[v1]) != arr_v1[v1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        long[] arr_v = ((Bits)object0).bits;
        int v = Math.min(this.bits.length, arr_v.length);
        for(int v1 = 0; v > v1; ++v1) {
            if(this.bits[v1] != arr_v[v1]) {
                return false;
            }
        }
        return this.bits.length == arr_v.length ? true : this.length() == ((Bits)object0).length();
    }

    public void flip(int v) {
        this.checkCapacity(v >>> 6);
        this.bits[v >>> 6] ^= 1L << (v & 0x3F);
    }

    public boolean get(int v) {
        return v >>> 6 < this.bits.length ? (this.bits[v >>> 6] & 1L << (v & 0x3F)) != 0L : false;
    }

    public boolean getAndClear(int v) {
        long[] arr_v = this.bits;
        if(v >>> 6 >= arr_v.length) {
            return false;
        }
        long v1 = arr_v[v >>> 6];
        arr_v[v >>> 6] &= ~(1L << (v & 0x3F));
        return arr_v[v >>> 6] != v1;
    }

    public boolean getAndSet(int v) {
        this.checkCapacity(v >>> 6);
        long[] arr_v = this.bits;
        long v1 = arr_v[v >>> 6];
        arr_v[v >>> 6] |= 1L << (v & 0x3F);
        return arr_v[v >>> 6] == v1;
    }

    @Override
    public int hashCode() {
        int v = this.length();
        int v2 = 0;
        for(int v1 = 0; v >>> 6 >= v1; ++v1) {
            v2 = v2 * 0x7F + ((int)(this.bits[v1] ^ this.bits[v1] >>> 0x20));
        }
        return v2;
    }

    public boolean intersects(Bits bits0) {
        long[] arr_v = this.bits;
        long[] arr_v1 = bits0.bits;
        for(int v = Math.min(arr_v.length, arr_v1.length) - 1; v >= 0; --v) {
            if((arr_v[v] & arr_v1[v]) != 0L) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        long[] arr_v = this.bits;
        for(int v = 0; v < arr_v.length; ++v) {
            if(arr_v[v] != 0L) {
                return false;
            }
        }
        return true;
    }

    public int length() {
        long[] arr_v = this.bits;
        for(int v = arr_v.length - 1; v >= 0; --v) {
            long v1 = arr_v[v];
            if(v1 != 0L) {
                for(int v2 = 0x3F; v2 >= 0; --v2) {
                    if((1L << (v2 & 0x3F) & v1) != 0L) {
                        return (v << 6) + v2 + 1;
                    }
                }
            }
        }
        return 0;
    }

    public int nextClearBit(int v) {
        long[] arr_v = this.bits;
        int v1 = v >>> 6;
        if(v1 >= arr_v.length) {
            return arr_v.length << 6;
        }
        long v2 = arr_v[v1];
        for(int v3 = v & 0x3F; v3 < 0x40; ++v3) {
            if((1L << (v3 & 0x3F) & v2) == 0L) {
                return (v1 << 6) + v3;
            }
        }
        while(true) {
            ++v1;
            if(v1 >= arr_v.length) {
                break;
            }
            if(v1 == 0) {
                return 0;
            }
            long v4 = arr_v[v1];
            for(int v5 = 0; v5 < 0x40; ++v5) {
                if((1L << (v5 & 0x3F) & v4) == 0L) {
                    return (v1 << 6) + v5;
                }
            }
        }
        return arr_v.length << 6;
    }

    public int nextSetBit(int v) {
        int v5;
        long[] arr_v = this.bits;
        int v1 = v >>> 6;
        if(v1 >= arr_v.length) {
            return -1;
        }
        long v2 = arr_v[v1];
        if(v2 != 0L) {
            for(int v3 = v & 0x3F; v3 < 0x40; ++v3) {
                if((1L << (v3 & 0x3F) & v2) != 0L) {
                    return (v1 << 6) + v3;
                }
            }
        }
    alab1:
        while(true) {
            do {
                do {
                    ++v1;
                    if(v1 >= arr_v.length) {
                        break alab1;
                    }
                    if(v1 == 0) {
                        continue alab1;
                    }
                    long v4 = arr_v[v1];
                }
                while(v4 == 0L);
                v5 = 0;
            label_18:
            }
            while(v5 >= 0x40);
            if((1L << (v5 & 0x3F) & v4) == 0L) {
                ++v5;
                goto label_18;
            }
            return (v1 << 6) + v5;
        }
        return -1;
    }

    public boolean notEmpty() {
        return !this.isEmpty();
    }

    public int numBits() {
        return this.bits.length << 6;
    }

    public void or(Bits bits0) {
        int v = Math.min(this.bits.length, bits0.bits.length);
        for(int v1 = 0; v > v1; ++v1) {
            this.bits[v1] |= bits0.bits[v1];
        }
        long[] arr_v = bits0.bits;
        if(v < arr_v.length) {
            this.checkCapacity(arr_v.length);
            while(bits0.bits.length > v) {
                this.bits[v] = bits0.bits[v];
                ++v;
            }
        }
    }

    public void set(int v) {
        this.checkCapacity(v >>> 6);
        this.bits[v >>> 6] |= 1L << (v & 0x3F);
    }

    public void xor(Bits bits0) {
        int v = Math.min(this.bits.length, bits0.bits.length);
        for(int v1 = 0; v > v1; ++v1) {
            this.bits[v1] ^= bits0.bits[v1];
        }
        long[] arr_v = bits0.bits;
        if(v < arr_v.length) {
            this.checkCapacity(arr_v.length);
            while(bits0.bits.length > v) {
                this.bits[v] = bits0.bits[v];
                ++v;
            }
        }
    }
}

