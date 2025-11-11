package com.badlogic.gdx.utils;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataInput extends DataInputStream {
    private char[] chars;

    public DataInput(InputStream inputStream0) {
        super(inputStream0);
        this.chars = new char[0x20];
    }

    public int readInt(boolean z) throws IOException {
        int v = this.read();
        int v1 = v & 0x7F;
        if((v & 0x80) != 0) {
            int v2 = this.read();
            v1 |= (v2 & 0x7F) << 7;
            if((v2 & 0x80) != 0) {
                int v3 = this.read();
                v1 |= (v3 & 0x7F) << 14;
                if((v3 & 0x80) != 0) {
                    int v4 = this.read();
                    v1 |= (v4 & 0x7F) << 21;
                    if((v4 & 0x80) != 0) {
                        v1 |= (this.read() & 0x7F) << 28;
                    }
                }
            }
        }
        return z ? v1 : v1 >>> 1 ^ -(v1 & 1);
    }

    @Null
    public String readString() throws IOException {
        int v = this.readInt(true);
        switch(v) {
            case 0: {
                return null;
            }
            case 1: {
                return "";
            }
            default: {
                if(this.chars.length < v - 1) {
                    this.chars = new char[v - 1];
                }
                char[] arr_c = this.chars;
                int v1 = 0;
                int v2 = 0;
                while(v1 < v - 1) {
                    v2 = this.read();
                    if(v2 > 0x7F) {
                        break;
                    }
                    arr_c[v1] = (char)v2;
                    ++v1;
                }
                if(v1 < v - 1) {
                    this.readUtf8_slow(v - 1, v1, v2);
                }
                return new String(arr_c, 0, v - 1);
            }
        }
    }

    private void readUtf8_slow(int v, int v1, int v2) throws IOException {
        char[] arr_c = this.chars;
        while(true) {
            switch(v2 >> 4) {
                case 0: 
                case 1: 
                case 2: 
                case 3: 
                case 4: 
                case 5: 
                case 6: 
                case 7: {
                    arr_c[v1] = (char)v2;
                    break;
                }
                default: {
                    switch(v2 >> 4) {
                        case 12: 
                        case 13: {
                            arr_c[v1] = (char)((v2 & 0x1F) << 6 | this.read() & 0x3F);
                            break;
                        }
                        case 14: {
                            arr_c[v1] = (char)((v2 & 15) << 12 | (this.read() & 0x3F) << 6 | this.read() & 0x3F);
                        }
                    }
                }
            }
            ++v1;
            if(v1 >= v) {
                break;
            }
            v2 = this.read() & 0xFF;
        }
    }
}

