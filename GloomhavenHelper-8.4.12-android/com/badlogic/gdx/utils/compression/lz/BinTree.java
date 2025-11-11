package com.badlogic.gdx.utils.compression.lz;

import java.io.IOException;

public class BinTree extends InWindow {
    private static final int[] CrcTable = null;
    boolean HASH_ARRAY;
    int _cutValue;
    int _cyclicBufferPos;
    int _cyclicBufferSize;
    int[] _hash;
    int _hashMask;
    int _hashSizeSum;
    int _matchMaxLen;
    int[] _son;
    static final int kBT2HashSize = 0x10000;
    static final int kEmptyHashValue = 0;
    int kFixHashSize;
    static final int kHash2Size = 0x400;
    static final int kHash3Offset = 0x400;
    static final int kHash3Size = 0x10000;
    static final int kMaxValForNormalize = 0x3FFFFFFF;
    int kMinMatchCheck;
    int kNumHashDirectBytes;
    static final int kStartMaxLen = 1;

    static {
        BinTree.CrcTable = new int[0x100];
        for(int v = 0; v < 0x100; ++v) {
            int v1 = v;
            for(int v2 = 0; v2 < 8; ++v2) {
                v1 = (v1 & 1) == 0 ? v1 >>> 1 : v1 >>> 1 ^ 0xEDB88320;
            }
            BinTree.CrcTable[v] = v1;
        }
    }

    public BinTree() {
        this._cyclicBufferSize = 0;
        this._cutValue = 0xFF;
        this._hashSizeSum = 0;
        this.HASH_ARRAY = true;
        this.kNumHashDirectBytes = 0;
        this.kMinMatchCheck = 4;
        this.kFixHashSize = 0x10400;
    }

    public boolean Create(int v, int v1, int v2, int v3) {
        if(v > 0x3FFFFEFF) {
            return false;
        }
        this._cutValue = (v2 >> 1) + 16;
        super.Create(v1 + v, v3 + v2, (v1 + v + v2 + v3) / 2 + 0x100);
        this._matchMaxLen = v2;
        if(this._cyclicBufferSize != v + 1) {
            this._cyclicBufferSize = v + 1;
            this._son = new int[(v + 1) * 2];
        }
        int v4 = 0x10000;
        if(this.HASH_ARRAY) {
            int v5 = v - 1 | v - 1 >> 1;
            int v6 = v5 | v5 >> 2;
            int v7 = v6 | v6 >> 4;
            int v8 = (v7 | v7 >> 8) >> 1 | 0xFFFF;
            v8 = v8 <= 0x1000000 ? (v7 | v7 >> 8) >> 1 | 0xFFFF : v8 >> 1;
            this._hashMask = v8;
            v4 = this.kFixHashSize + (v8 + 1);
        }
        if(v4 != this._hashSizeSum) {
            this._hashSizeSum = v4;
            this._hash = new int[v4];
        }
        return true;
    }

    public int GetMatches(int[] arr_v) throws IOException {
        int v27;
        int v26;
        int v17;
        int v12;
        int v11;
        int v7;
        int v6;
        int v4;
        int v;
        if(this._pos + this._matchMaxLen <= this._streamPos) {
            v = this._matchMaxLen;
        }
        else {
            v = this._streamPos - this._pos;
            if(v < this.kMinMatchCheck) {
                this.MovePos();
                return 0;
            }
        }
        int v1 = this._pos <= this._cyclicBufferSize ? 0 : this._pos - this._cyclicBufferSize;
        int v2 = this._bufferOffset + this._pos;
        if(this.HASH_ARRAY) {
            int v3 = BinTree.CrcTable[this._bufferBase[v2] & 0xFF] ^ this._bufferBase[v2 + 1] & 0xFF;
            v4 = v3 & 0x3FF;
            int v5 = v3 ^ (this._bufferBase[v2 + 2] & 0xFF) << 8;
            v6 = 0xFFFF & v5;
            v7 = (v5 ^ BinTree.CrcTable[this._bufferBase[v2 + 3] & 0xFF] << 5) & this._hashMask;
        }
        else {
            v7 = this._bufferBase[v2] & 0xFF ^ (this._bufferBase[v2 + 1] & 0xFF) << 8;
            v4 = 0;
            v6 = 0;
        }
        int[] arr_v1 = this._hash;
        int v8 = arr_v1[this.kFixHashSize + v7];
        if(this.HASH_ARRAY) {
            int v9 = arr_v1[v4];
            int v10 = arr_v1[v6 + 0x400];
            arr_v1[v4] = this._pos;
            this._hash[v6 + 0x400] = this._pos;
            v11 = 2;
            if(v9 <= v1 || this._bufferBase[this._bufferOffset + v9] != this._bufferBase[v2]) {
                v11 = 0;
                v12 = 1;
            }
            else {
                arr_v[0] = 2;
                arr_v[1] = this._pos - v9 - 1;
                v12 = 2;
            }
            if(v10 > v1 && this._bufferBase[this._bufferOffset + v10] == this._bufferBase[v2]) {
                if(v10 == v9) {
                    v11 -= 2;
                }
                int v13 = v11 + 1;
                arr_v[v11] = 3;
                v11 = v13 + 1;
                arr_v[v13] = this._pos - v10 - 1;
                v9 = v10;
                v12 = 3;
            }
            if(v11 != 0 && v9 == v8) {
                v11 -= 2;
                v12 = 1;
            }
        }
        else {
            v11 = 0;
            v12 = 1;
        }
        this._hash[this.kFixHashSize + v7] = this._pos;
        int v14 = (this._cyclicBufferPos << 1) + 1;
        int v15 = this._cyclicBufferPos << 1;
        int v16 = this.kNumHashDirectBytes;
        if(v16 == 0 || v8 <= v1) {
            v17 = v12;
        }
        else {
            v17 = this.kNumHashDirectBytes;
            if(this._bufferBase[this._bufferOffset + v8 + this.kNumHashDirectBytes] == this._bufferBase[v2 + v17]) {
                v17 = v12;
            }
            else {
                int v18 = v11 + 1;
                arr_v[v11] = v17;
                v11 = v18 + 1;
                arr_v[v18] = this._pos - v8 - 1;
            }
        }
        int v19 = this._cutValue;
        int v20 = v11;
        int v21 = v16;
        while(true) {
            if(v8 <= v1 || v19 == 0) {
                int[] arr_v5 = this._son;
                arr_v5[v15] = 0;
                arr_v5[v14] = 0;
                break;
            }
            int v22 = this._pos - v8;
            int v23 = v22 > this._cyclicBufferPos ? this._cyclicBufferPos - v22 + this._cyclicBufferSize : this._cyclicBufferPos - v22;
            int v24 = this._bufferOffset + v8;
            int v25 = Math.min(v16, v21);
            if(this._bufferBase[v24 + v25] == this._bufferBase[v2 + v25]) {
                while(true) {
                    v26 = v25 + 1;
                    if(v26 != v) {
                        v27 = v21;
                        if(this._bufferBase[v24 + v26] == this._bufferBase[v2 + v26]) {
                            v25 = v26;
                            v21 = v27;
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    v27 = v21;
                    break;
                }
                if(v17 < v26) {
                    int v28 = v20 + 1;
                    arr_v[v20] = v26;
                    v20 = v28 + 1;
                    arr_v[v28] = v22 - 1;
                    if(v26 == v) {
                        int[] arr_v2 = this._son;
                        arr_v2[v15] = arr_v2[v23 << 1];
                        arr_v2[v14] = arr_v2[(v23 << 1) + 1];
                        break;
                    }
                    v17 = v26;
                }
            }
            else {
                v27 = v21;
                v26 = v25;
            }
            if((this._bufferBase[v24 + v26] & 0xFF) < (this._bufferBase[v2 + v26] & 0xFF)) {
                int[] arr_v3 = this._son;
                arr_v3[v15] = v8;
                int v29 = (v23 << 1) + 1;
                v8 = arr_v3[v29];
                v21 = v26;
                v15 = v29;
            }
            else {
                int[] arr_v4 = this._son;
                arr_v4[v14] = v8;
                v14 = v23 << 1;
                v8 = arr_v4[v23 << 1];
                v16 = v26;
                v21 = v27;
            }
            --v19;
        }
        this.MovePos();
        return v20;
    }

    @Override  // com.badlogic.gdx.utils.compression.lz.InWindow
    public void Init() throws IOException {
        super.Init();
        for(int v = 0; v < this._hashSizeSum; ++v) {
            this._hash[v] = 0;
        }
        this._cyclicBufferPos = 0;
        this.ReduceOffsets(-1);
    }

    @Override  // com.badlogic.gdx.utils.compression.lz.InWindow
    public void MovePos() throws IOException {
        int v = this._cyclicBufferPos + 1;
        this._cyclicBufferPos = v;
        if(v >= this._cyclicBufferSize) {
            this._cyclicBufferPos = 0;
        }
        super.MovePos();
        if(this._pos == 0x3FFFFFFF) {
            this.Normalize();
        }
    }

    void Normalize() {
        int v = this._pos - this._cyclicBufferSize;
        this.NormalizeLinks(this._son, this._cyclicBufferSize * 2, v);
        this.NormalizeLinks(this._hash, this._hashSizeSum, v);
        this.ReduceOffsets(v);
    }

    void NormalizeLinks(int[] arr_v, int v, int v1) {
        for(int v2 = 0; v2 < v; ++v2) {
            int v3 = arr_v[v2];
            arr_v[v2] = v3 > v1 ? v3 - v1 : 0;
        }
    }

    public void SetCutValue(int v) {
        this._cutValue = v;
    }

    public void SetType(int v) {
        this.HASH_ARRAY = v > 2;
        if(this.HASH_ARRAY) {
            this.kNumHashDirectBytes = 0;
            this.kMinMatchCheck = 4;
            this.kFixHashSize = 0x10400;
            return;
        }
        this.kNumHashDirectBytes = 2;
        this.kMinMatchCheck = 3;
        this.kFixHashSize = 0;
    }

    public void Skip(int v) throws IOException {
        int v7;
        int v1;
        do {
            if(this._pos + this._matchMaxLen <= this._streamPos) {
                v1 = this._matchMaxLen;
                goto label_6;
            }
            else {
                int v2 = this._streamPos - this._pos;
                if(v2 >= this.kMinMatchCheck) {
                    v1 = v2;
                label_6:
                    int v3 = this._pos <= this._cyclicBufferSize ? 0 : this._pos - this._cyclicBufferSize;
                    int v4 = this._bufferOffset + this._pos;
                    if(this.HASH_ARRAY) {
                        int v5 = BinTree.CrcTable[this._bufferBase[v4] & 0xFF] ^ this._bufferBase[v4 + 1] & 0xFF;
                        this._hash[v5 & 0x3FF] = this._pos;
                        int v6 = v5 ^ (this._bufferBase[v4 + 2] & 0xFF) << 8;
                        this._hash[(0xFFFF & v6) + 0x400] = this._pos;
                        v7 = (v6 ^ BinTree.CrcTable[this._bufferBase[v4 + 3] & 0xFF] << 5) & this._hashMask;
                    }
                    else {
                        v7 = this._bufferBase[v4] & 0xFF ^ (this._bufferBase[v4 + 1] & 0xFF) << 8;
                    }
                    int[] arr_v = this._hash;
                    int v8 = this.kFixHashSize;
                    int v9 = arr_v[v8 + v7];
                    arr_v[v8 + v7] = this._pos;
                    int v10 = (this._cyclicBufferPos << 1) + 1;
                    int v11 = this._cyclicBufferPos << 1;
                    int v12 = this.kNumHashDirectBytes;
                    int v13 = this._cutValue;
                    int v14 = v12;
                    while(true) {
                        if(v9 <= v3 || v13 == 0) {
                            int[] arr_v4 = this._son;
                            arr_v4[v11] = 0;
                            arr_v4[v10] = 0;
                            break;
                        }
                        int v15 = this._pos - v9;
                        int v16 = v15 > this._cyclicBufferPos ? this._cyclicBufferPos - v15 + this._cyclicBufferSize : this._cyclicBufferPos - v15;
                        int v17 = this._bufferOffset + v9;
                        int v18 = Math.min(v12, v14);
                        if(this._bufferBase[v17 + v18] == this._bufferBase[v4 + v18]) {
                            do {
                                ++v18;
                            }
                            while(v18 != v1 && this._bufferBase[v17 + v18] == this._bufferBase[v4 + v18]);
                            if(v18 == v1) {
                                int[] arr_v1 = this._son;
                                arr_v1[v11] = arr_v1[v16 << 1];
                                arr_v1[v10] = arr_v1[(v16 << 1) + 1];
                                break;
                            }
                        }
                        if((this._bufferBase[v17 + v18] & 0xFF) < (this._bufferBase[v4 + v18] & 0xFF)) {
                            int[] arr_v2 = this._son;
                            arr_v2[v11] = v9;
                            int v19 = (v16 << 1) + 1;
                            v9 = arr_v2[v19];
                            v11 = v19;
                            v14 = v18;
                        }
                        else {
                            int[] arr_v3 = this._son;
                            arr_v3[v10] = v9;
                            v9 = arr_v3[v16 << 1];
                            v10 = v16 << 1;
                            v12 = v18;
                        }
                        --v13;
                    }
                }
            }
            this.MovePos();
            --v;
        }
        while(v != 0);
    }
}

