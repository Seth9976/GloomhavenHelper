package com.badlogic.gdx.math;

import java.util.Random;

public class RandomXS128 extends Random {
    private static final double NORM_DOUBLE = 1.110223E-16;
    private static final double NORM_FLOAT = 5.960464E-08;
    private long seed0;
    private long seed1;

    public RandomXS128() {
        this.setSeed(new Random().nextLong());
    }

    public RandomXS128(long v) {
        this.setSeed(v);
    }

    public RandomXS128(long v, long v1) {
        this.setState(v, v1);
    }

    public long getState(int v) {
        return v == 0 ? this.seed0 : this.seed1;
    }

    private static final long murmurHash3(long v) {
        long v1 = (v ^ v >>> 33) * 0xFF51AFD7ED558CCDL;
        long v2 = (v1 ^ v1 >>> 33) * 0xC4CEB9FE1A85EC53L;
        return v2 ^ v2 >>> 33;
    }

    @Override
    protected final int next(int v) {
        return (int)(this.nextLong() & (1L << v) - 1L);
    }

    @Override
    public boolean nextBoolean() {
        return (this.nextLong() & 1L) != 0L;
    }

    @Override
    public void nextBytes(byte[] arr_b) {
        int v = arr_b.length;
        while(v != 0) {
            int v1 = v >= 8 ? 8 : v;
            long v2 = this.nextLong();
            while(v1 != 0) {
                --v;
                arr_b[v] = (byte)(((int)v2));
                v2 >>= 8;
                --v1;
            }
        }
    }

    @Override
    public double nextDouble() {
        return ((double)(this.nextLong() >>> 11)) * 1.110223E-16;
    }

    @Override
    public float nextFloat() {
        return (float)(((double)(this.nextLong() >>> 40)) * 5.960464E-08);
    }

    @Override
    public int nextInt() {
        return (int)this.nextLong();
    }

    @Override
    public int nextInt(int v) {
        return (int)this.nextLong(((long)v));
    }

    @Override
    public long nextLong() {
        long v = this.seed0;
        long v1 = this.seed1;
        this.seed0 = v1;
        long v2 = v ^ v << 23;
        long v3 = v2 >>> 17 ^ (v2 ^ v1) ^ v1 >>> 26;
        this.seed1 = v3;
        return v3 + v1;
    }

    @Override
    public long nextLong(long v) {
        long v2;
        if(v <= 0L) {
            throw new IllegalArgumentException("n must be positive");
        }
        do {
            long v1 = this.nextLong();
            v2 = (v1 >>> 1) % v;
        }
        while((v1 >>> 1) - v2 + (v - 1L) < 0L);
        return v2;
    }

    @Override
    public void setSeed(long v) {
        if(v == 0L) {
            v = 0x8000000000000000L;
        }
        long v1 = RandomXS128.murmurHash3(v);
        this.setState(v1, RandomXS128.murmurHash3(v1));
    }

    public void setState(long v, long v1) {
        this.seed0 = v;
        this.seed1 = v1;
    }
}

