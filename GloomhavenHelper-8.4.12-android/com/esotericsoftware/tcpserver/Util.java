package com.esotericsoftware.tcpserver;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
    public static void closeQuietly(Closeable closeable0) {
        if(closeable0 == null) {
            return;
        }
        try {
            closeable0.close();
        }
        catch(Throwable unused_ex) {
        }
    }

    public static int readVarint(InputStream inputStream0) throws IOException {
        int v = inputStream0.read();
        if((v & 0x80) == 0) {
            return v & 0x7F;
        }
        int v1 = inputStream0.read();
        int v2 = v & 0x7F | (v1 & 0x7F) << 7;
        if((v1 & 0x80) == 0) {
            return v2;
        }
        int v3 = inputStream0.read();
        int v4 = v2 | (v3 & 0x7F) << 14;
        if((v3 & 0x80) == 0) {
            return v4;
        }
        int v5 = inputStream0.read();
        int v6 = v4 | (v5 & 0x7F) << 21;
        return (v5 & 0x80) == 0 ? v6 : (inputStream0.read() & 0x7F) << 28 | v6;
    }

    public static void writeVarint(int v, OutputStream outputStream0) throws IOException {
        if(v >>> 7 == 0) {
            outputStream0.write(v);
            return;
        }
        if(v >>> 14 == 0) {
            outputStream0.write(v & 0x7F | 0x80);
            outputStream0.write(v >>> 7);
            return;
        }
        if(v >>> 21 == 0) {
            outputStream0.write(v & 0x7F | 0x80);
            outputStream0.write(v >>> 7 | 0x80);
            outputStream0.write(v >>> 14);
            return;
        }
        if(v >>> 28 == 0) {
            outputStream0.write(v & 0x7F | 0x80);
            outputStream0.write(v >>> 7 | 0x80);
            outputStream0.write(v >>> 14 | 0x80);
            outputStream0.write(v >>> 21);
            return;
        }
        outputStream0.write(v & 0x7F | 0x80);
        outputStream0.write(v >>> 7 | 0x80);
        outputStream0.write(v >>> 14 | 0x80);
        outputStream0.write(v >>> 21 | 0x80);
        outputStream0.write(v >>> 28);
    }
}

