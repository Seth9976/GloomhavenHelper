package com.badlogic.gdx.utils.compression;

import com.badlogic.gdx.utils.compression.lzma.Decoder;
import com.badlogic.gdx.utils.compression.lzma.Encoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Lzma {
    static class CommandLine {
        public int Algorithm;
        public int Command;
        public int DictionarySize;
        public boolean DictionarySizeIsDefined;
        public boolean Eos;
        public int Fb;
        public boolean FbIsDefined;
        public String InFile;
        public int Lc;
        public int Lp;
        public int MatchFinder;
        public int NumBenchmarkPasses;
        public String OutFile;
        public int Pb;
        public static final int kBenchmak = 2;
        public static final int kDecode = 1;
        public static final int kEncode;

        CommandLine() {
            this.Command = -1;
            this.NumBenchmarkPasses = 10;
            this.DictionarySize = 0x800000;
            this.DictionarySizeIsDefined = false;
            this.Lc = 3;
            this.Lp = 0;
            this.Pb = 2;
            this.Fb = 0x80;
            this.FbIsDefined = false;
            this.Eos = false;
            this.Algorithm = 2;
            this.MatchFinder = 1;
        }
    }

    public static void compress(InputStream inputStream0, OutputStream outputStream0) throws IOException {
        CommandLine lzma$CommandLine0 = new CommandLine();
        boolean z = lzma$CommandLine0.Eos;
        Encoder encoder0 = new Encoder();
        if(!encoder0.SetDictionarySize(lzma$CommandLine0.DictionarySize)) {
            throw new RuntimeException("Incorrect dictionary size");
        }
        if(!encoder0.SetNumFastBytes(lzma$CommandLine0.Fb)) {
            throw new RuntimeException("Incorrect -fb value");
        }
        if(!encoder0.SetMatchFinder(lzma$CommandLine0.MatchFinder)) {
            throw new RuntimeException("Incorrect -mf value");
        }
        if(!encoder0.SetLcLpPb(lzma$CommandLine0.Lc, lzma$CommandLine0.Lp, lzma$CommandLine0.Pb)) {
            throw new RuntimeException("Incorrect -lc or -lp or -pb value");
        }
        encoder0.SetEndMarkerMode(z);
        encoder0.WriteCoderProperties(outputStream0);
        long v = -1L;
        if(!z) {
            long v1 = (long)inputStream0.available();
            if(v1 != 0L) {
                v = v1;
            }
        }
        for(int v2 = 0; v2 < 8; ++v2) {
            outputStream0.write(((int)(v >>> v2 * 8)) & 0xFF);
        }
        encoder0.Code(inputStream0, outputStream0, -1L, -1L, null);
    }

    public static void decompress(InputStream inputStream0, OutputStream outputStream0) throws IOException {
        byte[] arr_b = new byte[5];
        if(inputStream0.read(arr_b, 0, 5) != 5) {
            throw new RuntimeException("input .lzma file is too short");
        }
        Decoder decoder0 = new Decoder();
        if(!decoder0.SetDecoderProperties(arr_b)) {
            throw new RuntimeException("Incorrect stream properties");
        }
        long v1 = 0L;
        for(int v = 0; v < 8; ++v) {
            int v2 = inputStream0.read();
            if(v2 < 0) {
                throw new RuntimeException("Can\'t read stream size");
            }
            v1 |= ((long)v2) << v * 8;
        }
        if(!decoder0.Code(inputStream0, outputStream0, v1)) {
            throw new RuntimeException("Error in data stream");
        }
    }
}

