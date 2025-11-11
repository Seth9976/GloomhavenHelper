package com.badlogic.gdx.utils.compression;

public class CRC {
    public static int[] Table;
    int _value;

    static {
        CRC.Table = new int[0x100];
        for(int v = 0; v < 0x100; ++v) {
            int v1 = v;
            for(int v2 = 0; v2 < 8; ++v2) {
                v1 = (v1 & 1) == 0 ? v1 >>> 1 : v1 >>> 1 ^ 0xEDB88320;
            }
            CRC.Table[v] = v1;
        }
    }

    public CRC() {
        this._value = -1;
    }

    public int GetDigest() {
        return ~this._value;
    }

    public void Init() {
        this._value = -1;
    }

    public void Update(byte[] arr_b) {
        for(int v = 0; v < arr_b.length; ++v) {
            this._value = CRC.Table[(arr_b[v] ^ this._value) & 0xFF] ^ this._value >>> 8;
        }
    }

    public void Update(byte[] arr_b, int v, int v1) {
        for(int v2 = 0; v2 < v1; ++v2) {
            this._value = CRC.Table[(arr_b[v + v2] ^ this._value) & 0xFF] ^ this._value >>> 8;
        }
    }

    public void UpdateByte(int v) {
        this._value = CRC.Table[(v ^ this._value) & 0xFF] ^ this._value >>> 8;
    }
}

