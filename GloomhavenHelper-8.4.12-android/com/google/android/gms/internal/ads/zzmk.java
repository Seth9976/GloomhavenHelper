package com.google.android.gms.internal.ads;

final class zzmk {
    private int length;
    private int[] zzane;
    private long[] zzanf;
    private long[] zzanh;
    private int[] zzavu;
    private int zzbce;
    private int[] zzbcf;
    private zzjq[] zzbcg;
    private zzgz[] zzbch;
    private int zzbci;
    private int zzbcj;
    private int zzbck;
    private long zzbcl;
    private long zzbcm;
    private boolean zzbcn;
    private boolean zzbco;
    private zzgz zzbcp;

    public zzmk() {
        this.zzbce = 1000;
        this.zzbcf = new int[1000];
        this.zzanf = new long[1000];
        this.zzanh = new long[1000];
        this.zzavu = new int[1000];
        this.zzane = new int[1000];
        this.zzbcg = new zzjq[1000];
        this.zzbch = new zzgz[1000];
        this.zzbcl = 0x8000000000000000L;
        this.zzbcm = 0x8000000000000000L;
        this.zzbco = true;
        this.zzbcn = true;
    }

    public final int zza(zzhb zzhb0, zziv zziv0, boolean z, boolean z1, zzgz zzgz0, zzmj zzmj0) {
        synchronized(this) {
            if(!this.zzib()) {
                if(z1) {
                    zziv0.setFlags(4);
                    return -4;
                }
                if(this.zzbcp != null && (z || this.zzbcp != zzgz0)) {
                    zzhb0.zzagi = this.zzbcp;
                    return -5;
                }
                return -3;
            }
            if(!z && this.zzbch[this.zzbcj] == zzgz0) {
                if(zziv0.zzcs == null) {
                    return -3;
                }
                zziv0.zzamu = this.zzanh[this.zzbcj];
                zziv0.setFlags(this.zzavu[this.zzbcj]);
                zzmj0.size = this.zzane[this.zzbcj];
                zzmj0.zzavf = this.zzanf[this.zzbcj];
                zzmj0.zzapz = this.zzbcg[this.zzbcj];
                this.zzbcl = Math.max(this.zzbcl, zziv0.zzamu);
                --this.length;
                ++this.zzbcj;
                ++this.zzbci;
                if(this.zzbcj == this.zzbce) {
                    this.zzbcj = 0;
                }
                zzmj0.zzbcd = this.length <= 0 ? zzmj0.zzavf + ((long)zzmj0.size) : this.zzanf[this.zzbcj];
                return -4;
            }
            zzhb0.zzagi = this.zzbch[this.zzbcj];
            return -5;
        }
    }

    public final void zza(long v, int v1, long v2, int v3, zzjq zzjq0) {
        synchronized(this) {
            if(this.zzbcn) {
                if((v1 & 1) == 0) {
                    return;
                }
                this.zzbcn = false;
            }
            zzob.checkState(!this.zzbco);
            this.zzei(v);
            this.zzanh[this.zzbck] = v;
            this.zzanf[this.zzbck] = v2;
            this.zzane[this.zzbck] = v3;
            this.zzavu[this.zzbck] = v1;
            this.zzbcg[this.zzbck] = zzjq0;
            this.zzbch[this.zzbck] = this.zzbcp;
            this.zzbcf[this.zzbck] = 0;
            ++this.length;
            if(this.length == this.zzbce) {
                int v5 = this.zzbce + 1000;
                int[] arr_v = new int[v5];
                long[] arr_v1 = new long[v5];
                long[] arr_v2 = new long[v5];
                int[] arr_v3 = new int[v5];
                int[] arr_v4 = new int[v5];
                zzjq[] arr_zzjq = new zzjq[v5];
                zzgz[] arr_zzgz = new zzgz[v5];
                int v6 = this.zzbce - this.zzbcj;
                System.arraycopy(this.zzanf, this.zzbcj, arr_v1, 0, v6);
                System.arraycopy(this.zzanh, this.zzbcj, arr_v2, 0, v6);
                System.arraycopy(this.zzavu, this.zzbcj, arr_v3, 0, v6);
                System.arraycopy(this.zzane, this.zzbcj, arr_v4, 0, v6);
                System.arraycopy(this.zzbcg, this.zzbcj, arr_zzjq, 0, v6);
                System.arraycopy(this.zzbch, this.zzbcj, arr_zzgz, 0, v6);
                System.arraycopy(this.zzbcf, this.zzbcj, arr_v, 0, v6);
                int v7 = this.zzbcj;
                System.arraycopy(this.zzanf, 0, arr_v1, v6, v7);
                System.arraycopy(this.zzanh, 0, arr_v2, v6, v7);
                System.arraycopy(this.zzavu, 0, arr_v3, v6, v7);
                System.arraycopy(this.zzane, 0, arr_v4, v6, v7);
                System.arraycopy(this.zzbcg, 0, arr_zzjq, v6, v7);
                System.arraycopy(this.zzbch, 0, arr_zzgz, v6, v7);
                System.arraycopy(this.zzbcf, 0, arr_v, v6, v7);
                this.zzanf = arr_v1;
                this.zzanh = arr_v2;
                this.zzavu = arr_v3;
                this.zzane = arr_v4;
                this.zzbcg = arr_zzjq;
                this.zzbch = arr_zzgz;
                this.zzbcf = arr_v;
                this.zzbcj = 0;
                this.zzbck = this.zzbce;
                this.length = this.zzbce;
                this.zzbce = v5;
                return;
            }
            ++this.zzbck;
            if(this.zzbck == this.zzbce) {
                this.zzbck = 0;
            }
        }
    }

    public final long zzd(long v, boolean z) {
        synchronized(this) {
            if(this.zzib() && v >= this.zzanh[this.zzbcj]) {
                if(v > this.zzbcm && !z) {
                    return -1L;
                }
                int v2 = this.zzbcj;
                int v3 = -1;
                for(int v4 = 0; v2 != this.zzbck && this.zzanh[v2] <= v; ++v4) {
                    if((this.zzavu[v2] & 1) != 0) {
                        v3 = v4;
                    }
                    v2 = (v2 + 1) % this.zzbce;
                }
                if(v3 == -1) {
                    return -1L;
                }
                this.zzbcj = (this.zzbcj + v3) % this.zzbce;
                this.zzbci += v3;
                this.length -= v3;
                return this.zzanf[this.zzbcj];
            }
            return -1L;
        }
    }

    public final void zzei(long v) {
        synchronized(this) {
            this.zzbcm = Math.max(this.zzbcm, v);
        }
    }

    public final boolean zzg(zzgz zzgz0) {
        synchronized(this) {
            if(zzgz0 == null) {
                this.zzbco = true;
                return false;
            }
            this.zzbco = false;
            if(zzop.zza(zzgz0, this.zzbcp)) {
                return false;
            }
            this.zzbcp = zzgz0;
            return true;
        }
    }

    public final long zzhs() {
        synchronized(this) {
        }
        return Math.max(this.zzbcl, this.zzbcm);
    }

    public final void zzhy() {
        this.zzbci = 0;
        this.zzbcj = 0;
        this.zzbck = 0;
        this.length = 0;
        this.zzbcn = true;
    }

    public final void zzhz() {
        this.zzbcl = 0x8000000000000000L;
        this.zzbcm = 0x8000000000000000L;
    }

    public final int zzia() {
        return this.zzbci + this.length;
    }

    public final boolean zzib() {
        synchronized(this) {
        }
        return this.length != 0;
    }

    public final zzgz zzic() {
        synchronized(this) {
            if(this.zzbco) {
                return null;
            }
        }
        return this.zzbcp;
    }

    public final long zzid() {
        int v3;
        long v2;
        synchronized(this) {
            if(!this.zzib()) {
                return -1L;
            }
            int v1 = (this.zzbcj + this.length - 1) % this.zzbce;
            this.zzbcj = (this.zzbcj + this.length) % this.zzbce;
            this.zzbci += this.length;
            this.length = 0;
            v2 = this.zzanf[v1];
            v3 = this.zzane[v1];
        }
        return v2 + ((long)v3);
    }
}

