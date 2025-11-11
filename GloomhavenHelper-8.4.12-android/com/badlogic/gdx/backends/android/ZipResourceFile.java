package com.badlogic.gdx.backends.android;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipResourceFile {
    public static final class ZipEntryRO {
        public long mCRC32;
        public long mCompressedLength;
        public final File mFile;
        public final String mFileName;
        public long mLocalHdrOffset;
        public int mMethod;
        public long mOffset;
        public long mUncompressedLength;
        public long mWhenModified;
        public final String mZipFileName;

        public ZipEntryRO(String s, File file0, String s1) {
            this.mOffset = -1L;
            this.mFileName = s1;
            this.mZipFileName = s;
            this.mFile = file0;
        }

        public AssetFileDescriptor getAssetFileDescriptor() {
            if(this.mMethod == 0) {
                try {
                    return new AssetFileDescriptor(ParcelFileDescriptor.open(this.mFile, 0x10000000), this.getOffset(), this.mUncompressedLength);
                }
                catch(FileNotFoundException fileNotFoundException0) {
                    fileNotFoundException0.printStackTrace();
                }
            }
            return null;
        }

        public long getOffset() {
            return this.mOffset;
        }

        public File getZipFile() {
            return this.mFile;
        }

        public String getZipFileName() {
            return this.mZipFileName;
        }

        public boolean isUncompressed() {
            return this.mMethod == 0;
        }

        public void setOffsetFromFile(RandomAccessFile randomAccessFile0, ByteBuffer byteBuffer0) throws IOException {
            try {
                long v = this.mLocalHdrOffset;
                randomAccessFile0.seek(v);
                randomAccessFile0.readFully(byteBuffer0.array());
                if(byteBuffer0.getInt(0) == 0x4034B50) {
                    this.mOffset = v + 30L + ((long)(byteBuffer0.getShort(26) & 0xFFFF)) + ((long)(byteBuffer0.getShort(28) & 0xFFFF));
                    return;
                }
                Log.w("zipro", "didn\'t find signature at start of lfh");
                throw new IOException();
            }
            catch(FileNotFoundException fileNotFoundException0) {
                fileNotFoundException0.printStackTrace();
            }
            catch(IOException iOException0) {
                iOException0.printStackTrace();
            }
        }
    }

    static final boolean LOGV = false;
    static final String LOG_TAG = "zipro";
    static final int kCDECRC = 16;
    static final int kCDECommentLen = 0x20;
    static final int kCDECompLen = 20;
    static final int kCDEExtraLen = 30;
    static final int kCDELen = 46;
    static final int kCDELocalOffset = 42;
    static final int kCDEMethod = 10;
    static final int kCDEModWhen = 12;
    static final int kCDENameLen = 28;
    static final int kCDESignature = 0x2014B50;
    static final int kCDEUncompLen = 24;
    static final int kCompressDeflated = 8;
    static final int kCompressStored = 0;
    static final int kEOCDFileOffset = 16;
    static final int kEOCDLen = 22;
    static final int kEOCDNumEntries = 8;
    static final int kEOCDSignature = 101010256;
    static final int kEOCDSize = 12;
    static final int kLFHExtraLen = 28;
    static final int kLFHLen = 30;
    static final int kLFHNameLen = 26;
    static final int kLFHSignature = 0x4034B50;
    static final int kMaxCommentLen = 0xFFFF;
    static final int kMaxEOCDSearch = 0x10015;
    static final int kZipEntryAdj = 10000;
    private HashMap mHashMap;
    ByteBuffer mLEByteBuffer;
    public HashMap mZipFiles;

    public ZipResourceFile(String s) throws IOException {
        this.mHashMap = new HashMap();
        this.mZipFiles = new HashMap();
        this.mLEByteBuffer = ByteBuffer.allocate(4);
        this.addPatchFile(s);
    }

    void addPatchFile(String s) throws IOException {
        long v = 0x10015L;
        File file0 = new File(s);
        RandomAccessFile randomAccessFile0 = new RandomAccessFile(file0, "r");
        long v1 = randomAccessFile0.length();
        if(v1 >= 22L) {
            if(0x10015L > v1) {
                v = v1;
            }
            randomAccessFile0.seek(0L);
            switch(ZipResourceFile.read4LE(randomAccessFile0)) {
                case 0x4034B50: {
                    randomAccessFile0.seek(v1 - v);
                    ByteBuffer byteBuffer0 = ByteBuffer.allocate(((int)v));
                    byte[] arr_b = byteBuffer0.array();
                    randomAccessFile0.readFully(arr_b);
                    byteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
                    int v2;
                    for(v2 = arr_b.length - 22; v2 >= 0 && (arr_b[v2] != 80 || byteBuffer0.getInt(v2) != 101010256); --v2) {
                    }
                    if(v2 < 0) {
                        Log.d("zipro", "Zip: EOCD not found, " + s + " is not zip");
                    }
                    int v3 = byteBuffer0.getShort(v2 + 8);
                    long v4 = ((long)byteBuffer0.getInt(v2 + 12)) & 0xFFFFFFFFL;
                    long v5 = ((long)byteBuffer0.getInt(v2 + 16)) & 0xFFFFFFFFL;
                    if(v5 + v4 <= v1) {
                        if(v3 != 0) {
                            MappedByteBuffer mappedByteBuffer0 = randomAccessFile0.getChannel().map(FileChannel.MapMode.READ_ONLY, v5, v4);
                            mappedByteBuffer0.order(ByteOrder.LITTLE_ENDIAN);
                            byte[] arr_b1 = new byte[0xFFFF];
                            ByteBuffer byteBuffer1 = ByteBuffer.allocate(30);
                            byteBuffer1.order(ByteOrder.LITTLE_ENDIAN);
                            int v6 = 0;
                            int v7 = 0;
                            while(v6 < v3) {
                                if(mappedByteBuffer0.getInt(v7) == 0x2014B50) {
                                    int v8 = mappedByteBuffer0.getShort(v7 + 28);
                                    int v9 = mappedByteBuffer0.getShort(v7 + 30);
                                    int v10 = mappedByteBuffer0.getShort(v7 + 0x20);
                                    mappedByteBuffer0.position(v7 + 46);
                                    mappedByteBuffer0.get(arr_b1, 0, v8 & 0xFFFF);
                                    mappedByteBuffer0.position(0);
                                    String s1 = new String(arr_b1, 0, v8 & 0xFFFF);
                                    ZipEntryRO zipResourceFile$ZipEntryRO0 = new ZipEntryRO(s, file0, s1);
                                    zipResourceFile$ZipEntryRO0.mMethod = mappedByteBuffer0.getShort(v7 + 10) & 0xFFFF;
                                    zipResourceFile$ZipEntryRO0.mWhenModified = ((long)mappedByteBuffer0.getInt(v7 + 12)) & 0xFFFFFFFFL;
                                    zipResourceFile$ZipEntryRO0.mCRC32 = mappedByteBuffer0.getLong(v7 + 16) & 0xFFFFFFFFL;
                                    zipResourceFile$ZipEntryRO0.mCompressedLength = mappedByteBuffer0.getLong(v7 + 20) & 0xFFFFFFFFL;
                                    zipResourceFile$ZipEntryRO0.mUncompressedLength = mappedByteBuffer0.getLong(v7 + 24) & 0xFFFFFFFFL;
                                    zipResourceFile$ZipEntryRO0.mLocalHdrOffset = ((long)mappedByteBuffer0.getInt(v7 + 42)) & 0xFFFFFFFFL;
                                    byteBuffer1.clear();
                                    zipResourceFile$ZipEntryRO0.setOffsetFromFile(randomAccessFile0, byteBuffer1);
                                    this.mHashMap.put(s1, zipResourceFile$ZipEntryRO0);
                                    v7 += (v8 & 0xFFFF) + 46 + (v9 & 0xFFFF) + (v10 & 0xFFFF);
                                    ++v6;
                                    continue;
                                }
                                Log.w("zipro", "Missed a central dir sig (at " + v7 + ")");
                                throw new IOException();
                            }
                            return;
                        }
                        Log.w("zipro", "empty archive?");
                        throw new IOException();
                    }
                    Log.w("zipro", "bad offsets (dir " + v5 + ", size " + v4 + ", eocd " + v2 + ")");
                    throw new IOException();
                }
                case 101010256: {
                    Log.i("zipro", "Found Zip archive, but it looks empty");
                    throw new IOException();
                }
                default: {
                    Log.v("zipro", "Not a Zip archive");
                    throw new IOException();
                }
            }
        }
        randomAccessFile0.close();
        throw new IOException();
    }

    public ZipEntryRO[] getAllEntries() {
        Collection collection0 = this.mHashMap.values();
        return (ZipEntryRO[])collection0.toArray(new ZipEntryRO[collection0.size()]);
    }

    public AssetFileDescriptor getAssetFileDescriptor(String s) {
        ZipEntryRO zipResourceFile$ZipEntryRO0 = (ZipEntryRO)this.mHashMap.get(s);
        return zipResourceFile$ZipEntryRO0 == null ? null : zipResourceFile$ZipEntryRO0.getAssetFileDescriptor();
    }

    ZipEntryRO[] getEntriesAt(String s) {
        Vector vector0 = new Vector();
        Collection collection0 = this.mHashMap.values();
        if(s == null) {
            s = "";
        }
        int v = s.length();
        for(Object object0: collection0) {
            ZipEntryRO zipResourceFile$ZipEntryRO0 = (ZipEntryRO)object0;
            if(zipResourceFile$ZipEntryRO0.mFileName.startsWith(s) && -1 == zipResourceFile$ZipEntryRO0.mFileName.indexOf(0x2F, v)) {
                vector0.add(zipResourceFile$ZipEntryRO0);
            }
        }
        return (ZipEntryRO[])vector0.toArray(new ZipEntryRO[vector0.size()]);
    }

    public InputStream getInputStream(String s) throws IOException {
        ZipEntryRO zipResourceFile$ZipEntryRO0 = (ZipEntryRO)this.mHashMap.get(s);
        if(zipResourceFile$ZipEntryRO0 != null) {
            if(zipResourceFile$ZipEntryRO0.isUncompressed()) {
                return zipResourceFile$ZipEntryRO0.getAssetFileDescriptor().createInputStream();
            }
            ZipFile zipFile0 = (ZipFile)this.mZipFiles.get(zipResourceFile$ZipEntryRO0.getZipFile());
            if(zipFile0 == null) {
                zipFile0 = new ZipFile(zipResourceFile$ZipEntryRO0.getZipFile(), 1);
                this.mZipFiles.put(zipResourceFile$ZipEntryRO0.getZipFile(), zipFile0);
            }
            ZipEntry zipEntry0 = zipFile0.getEntry(s);
            return zipEntry0 == null ? null : zipFile0.getInputStream(zipEntry0);
        }
        return null;
    }

    private static int read4LE(RandomAccessFile randomAccessFile0) throws EOFException, IOException {
        return ZipResourceFile.swapEndian(randomAccessFile0.readInt());
    }

    private static int swapEndian(int v) {
        return ((v & 0xFF) << 24) + ((0xFF00 & v) << 8) + ((0xFF0000 & v) >>> 8) + (v >>> 24 & 0xFF);
    }

    private static int swapEndian(short v) {
        return (v & 0xFF00) >>> 8 | (v & 0xFF) << 8;
    }
}

