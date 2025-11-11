package com.badlogic.gdx.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DataOutput extends DataOutputStream {
    public DataOutput(OutputStream outputStream0) {
        super(outputStream0);
    }

    public int writeInt(int v, boolean z) throws IOException {
        if(!z) {
            v = v >> 0x1F ^ v << 1;
        }
        if(v >>> 7 == 0) {
            this.write(((int)(((byte)v))));
            return 1;
        }
        this.write(((int)(((byte)(v & 0x7F | 0x80)))));
        if(v >>> 14 == 0) {
            this.write(((int)(((byte)(v >>> 7)))));
            return 2;
        }
        this.write(((int)(((byte)(v >>> 7 | 0x80)))));
        if(v >>> 21 == 0) {
            this.write(((int)(((byte)(v >>> 14)))));
            return 3;
        }
        this.write(((int)(((byte)(v >>> 14 | 0x80)))));
        if(v >>> 28 == 0) {
            this.write(((int)(((byte)(v >>> 21)))));
            return 4;
        }
        this.write(((int)(((byte)(v >>> 21 | 0x80)))));
        this.write(((int)(((byte)(v >>> 28)))));
        return 5;
    }

    public void writeString(@Null String s) throws IOException {
        int v = 0;
        if(s == null) {
            this.write(0);
            return;
        }
        int v1 = s.length();
        if(v1 == 0) {
            this.writeByte(1);
            return;
        }
        this.writeInt(v1 + 1, true);
        while(v < v1) {
            int v2 = s.charAt(v);
            if(v2 > 0x7F) {
                break;
            }
            this.write(((int)(((byte)v2))));
            ++v;
        }
        if(v < v1) {
            this.writeString_slow(s, v1, v);
        }
    }

    private void writeString_slow(String s, int v, int v1) throws IOException {
        while(v1 < v) {
            int v2 = s.charAt(v1);
            if(v2 <= 0x7F) {
                this.write(((int)(((byte)v2))));
            }
            else {
                if(v2 > 0x7FF) {
                    this.write(((int)(((byte)(v2 >> 12 & 15 | 0xE0)))));
                    this.write(((int)(((byte)(v2 >> 6 & 0x3F | 0x80)))));
                }
                else {
                    this.write(((int)(((byte)(v2 >> 6 & 0x1F | 0xC0)))));
                }
                this.write(((int)(((byte)(v2 & 0x3F | 0x80)))));
            }
            ++v1;
        }
    }
}

