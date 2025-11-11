package com.google.android.gms.internal.ads;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public interface zzedv extends Closeable {
    @Override
    void close() throws IOException;

    long position() throws IOException;

    int read(ByteBuffer arg1) throws IOException;

    long size() throws IOException;

    void zzfc(long arg1) throws IOException;

    ByteBuffer zzh(long arg1, long arg2) throws IOException;
}

