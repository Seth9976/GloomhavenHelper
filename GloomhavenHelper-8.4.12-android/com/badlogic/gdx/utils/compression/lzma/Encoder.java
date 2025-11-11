package com.badlogic.gdx.utils.compression.lzma;

import com.badlogic.gdx.utils.compression.ICodeProgress;
import com.badlogic.gdx.utils.compression.lz.BinTree;
import com.badlogic.gdx.utils.compression.rangecoder.BitTreeEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Encoder {
    class LenEncoder {
        short[] _choice;
        BitTreeEncoder _highCoder;
        BitTreeEncoder[] _lowCoder;
        BitTreeEncoder[] _midCoder;

        public LenEncoder() {
            this._choice = new short[2];
            this._lowCoder = new BitTreeEncoder[16];
            this._midCoder = new BitTreeEncoder[16];
            this._highCoder = new BitTreeEncoder(8);
            for(int v = 0; v < 16; ++v) {
                BitTreeEncoder[] arr_bitTreeEncoder = this._lowCoder;
                arr_bitTreeEncoder[v] = new BitTreeEncoder(3);
                BitTreeEncoder[] arr_bitTreeEncoder1 = this._midCoder;
                arr_bitTreeEncoder1[v] = new BitTreeEncoder(3);
            }
        }

        public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder0, int v, int v1) throws IOException {
            if(v < 8) {
                encoder0.Encode(this._choice, 0, 0);
                this._lowCoder[v1].Encode(encoder0, v);
                return;
            }
            encoder0.Encode(this._choice, 0, 1);
            if(v - 8 < 8) {
                encoder0.Encode(this._choice, 1, 0);
                this._midCoder[v1].Encode(encoder0, v - 8);
                return;
            }
            encoder0.Encode(this._choice, 1, 1);
            this._highCoder.Encode(encoder0, v - 16);
        }

        public void Init(int v) {
            com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._choice);
            for(int v1 = 0; v1 < v; ++v1) {
                this._lowCoder[v1].Init();
                this._midCoder[v1].Init();
            }
            this._highCoder.Init();
        }

        public void SetPrices(int v, int v1, int[] arr_v, int v2) {
            int v3 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._choice[0]);
            int v4 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._choice[0]);
            int v5 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._choice[1]);
            int v6 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._choice[1]);
            int v7;
            for(v7 = 0; v7 < 8; ++v7) {
                if(v7 >= v1) {
                    return;
                }
                arr_v[v2 + v7] = this._lowCoder[v].GetPrice(v7) + v3;
            }
            while(v7 < 16) {
                if(v7 >= v1) {
                    return;
                }
                arr_v[v2 + v7] = this._midCoder[v].GetPrice(v7 - 8) + (v5 + v4);
                ++v7;
            }
            while(v7 < v1) {
                arr_v[v2 + v7] = this._highCoder.GetPrice(v7 - 16) + (v4 + v6);
                ++v7;
            }
        }
    }

    class LenPriceTableEncoder extends LenEncoder {
        int[] _counters;
        int[] _prices;
        int _tableSize;

        LenPriceTableEncoder() {
            this._prices = new int[0x1100];
            this._counters = new int[16];
        }

        @Override  // com.badlogic.gdx.utils.compression.lzma.Encoder$LenEncoder
        public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder0, int v, int v1) throws IOException {
            super.Encode(encoder0, v, v1);
            int[] arr_v = this._counters;
            int v2 = arr_v[v1] - 1;
            arr_v[v1] = v2;
            if(v2 == 0) {
                this.UpdateTable(v1);
            }
        }

        public int GetPrice(int v, int v1) {
            return this._prices[v1 * 0x110 + v];
        }

        public void SetTableSize(int v) {
            this._tableSize = v;
        }

        void UpdateTable(int v) {
            this.SetPrices(v, this._tableSize, this._prices, v * 0x110);
            this._counters[v] = this._tableSize;
        }

        public void UpdateTables(int v) {
            for(int v1 = 0; v1 < v; ++v1) {
                this.UpdateTable(v1);
            }
        }
    }

    class LiteralEncoder {
        class Encoder2 {
            short[] m_Encoders;

            Encoder2() {
                this.m_Encoders = new short[0x300];
            }

            public void Encode(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder0, byte b) throws IOException {
                int v = 1;
                for(int v1 = 7; v1 >= 0; --v1) {
                    int v2 = b >> v1 & 1;
                    encoder0.Encode(this.m_Encoders, v, v2);
                    v = v << 1 | v2;
                }
            }

            public void EncodeMatched(com.badlogic.gdx.utils.compression.rangecoder.Encoder encoder0, byte b, byte b1) throws IOException {
                int v4;
                int v = 1;
                boolean z = true;
                for(int v1 = 7; v1 >= 0; --v1) {
                    int v2 = b1 >> v1 & 1;
                    if(z) {
                        int v3 = b >> v1 & 1;
                        v4 = (v3 + 1 << 8) + v;
                        z = v3 == v2;
                    }
                    else {
                        v4 = v;
                    }
                    encoder0.Encode(this.m_Encoders, v4, v2);
                    v = v << 1 | v2;
                }
            }

            public int GetPrice(boolean z, byte b, byte b1) {
                int v2;
                int v = 0;
                int v1 = 7;
                if(z) {
                    v2 = 1;
                    while(v1 >= 0) {
                        int v3 = b >> v1 & 1;
                        int v4 = b1 >> v1 & 1;
                        v += com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this.m_Encoders[(v3 + 1 << 8) + v2], v4);
                        v2 = v2 << 1 | v4;
                        if(v3 != v4) {
                            --v1;
                            break;
                        }
                        --v1;
                    }
                }
                else {
                    v2 = 1;
                }
                while(v1 >= 0) {
                    int v5 = b1 >> v1 & 1;
                    v += com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this.m_Encoders[v2], v5);
                    v2 = v2 << 1 | v5;
                    --v1;
                }
                return v;
            }

            public void Init() {
                com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this.m_Encoders);
            }
        }

        Encoder2[] m_Coders;
        int m_NumPosBits;
        int m_NumPrevBits;
        int m_PosMask;

        public void Create(int v, int v1) {
            if(this.m_Coders != null && this.m_NumPrevBits == v1 && this.m_NumPosBits == v) {
                return;
            }
            this.m_NumPosBits = v;
            this.m_PosMask = (1 << v) - 1;
            this.m_NumPrevBits = v1;
            int v2 = 1 << this.m_NumPrevBits + this.m_NumPosBits;
            this.m_Coders = new Encoder2[v2];
            for(int v3 = 0; v3 < v2; ++v3) {
                Encoder2[] arr_encoder$LiteralEncoder$Encoder2 = this.m_Coders;
                arr_encoder$LiteralEncoder$Encoder2[v3] = new Encoder2(this);
            }
        }

        public Encoder2 GetSubCoder(int v, byte b) {
            return this.m_Coders[((v & this.m_PosMask) << this.m_NumPrevBits) + ((b & 0xFF) >>> 8 - this.m_NumPrevBits)];
        }

        public void Init() {
            int v = 1 << this.m_NumPrevBits + this.m_NumPosBits;
            for(int v1 = 0; v1 < v; ++v1) {
                this.m_Coders[v1].Init();
            }
        }
    }

    class Optimal {
        public int BackPrev;
        public int BackPrev2;
        public int Backs0;
        public int Backs1;
        public int Backs2;
        public int Backs3;
        public int PosPrev;
        public int PosPrev2;
        public boolean Prev1IsChar;
        public boolean Prev2;
        public int Price;
        public int State;

        public boolean IsShortRep() {
            return this.BackPrev == 0;
        }

        public void MakeAsChar() {
            this.BackPrev = -1;
            this.Prev1IsChar = false;
        }

        public void MakeAsShortRep() {
            this.BackPrev = 0;
            this.Prev1IsChar = false;
        }
    }

    public static final int EMatchFinderTypeBT2 = 0;
    public static final int EMatchFinderTypeBT4 = 1;
    int _additionalOffset;
    int _alignPriceCount;
    int[] _alignPrices;
    int _dictionarySize;
    int _dictionarySizePrev;
    int _distTableSize;
    int[] _distancesPrices;
    boolean _finished;
    InputStream _inStream;
    short[] _isMatch;
    short[] _isRep;
    short[] _isRep0Long;
    short[] _isRepG0;
    short[] _isRepG1;
    short[] _isRepG2;
    LenPriceTableEncoder _lenEncoder;
    LiteralEncoder _literalEncoder;
    int _longestMatchLength;
    boolean _longestMatchWasFound;
    int[] _matchDistances;
    BinTree _matchFinder;
    int _matchFinderType;
    int _matchPriceCount;
    boolean _needReleaseMFStream;
    int _numDistancePairs;
    int _numFastBytes;
    int _numFastBytesPrev;
    int _numLiteralContextBits;
    int _numLiteralPosStateBits;
    Optimal[] _optimum;
    int _optimumCurrentIndex;
    int _optimumEndIndex;
    BitTreeEncoder _posAlignEncoder;
    short[] _posEncoders;
    BitTreeEncoder[] _posSlotEncoder;
    int[] _posSlotPrices;
    int _posStateBits;
    int _posStateMask;
    byte _previousByte;
    com.badlogic.gdx.utils.compression.rangecoder.Encoder _rangeEncoder;
    int[] _repDistances;
    LenPriceTableEncoder _repMatchLenEncoder;
    int _state;
    boolean _writeEndMark;
    int backRes;
    boolean[] finished;
    static byte[] g_FastPos = null;
    static final int kDefaultDictionaryLogSize = 22;
    static final int kIfinityPrice = 0xFFFFFFF;
    static final int kNumFastBytesDefault = 0x20;
    public static final int kNumLenSpecSymbols = 16;
    static final int kNumOpts = 0x1000;
    public static final int kPropSize = 5;
    long nowPos64;
    long[] processedInSize;
    long[] processedOutSize;
    byte[] properties;
    int[] repLens;
    int[] reps;
    int[] tempPrices;

    static {
        Encoder.g_FastPos = new byte[0x800];
        byte[] arr_b = Encoder.g_FastPos;
        arr_b[0] = 0;
        arr_b[1] = 1;
        int v1 = 2;
        for(int v = 2; v1 < 22; v = v3) {
            int v2 = 1 << (v1 >> 1) - 1;
            int v3 = v;
            int v4 = 0;
            while(v4 < v2) {
                Encoder.g_FastPos[v3] = (byte)v1;
                ++v4;
                ++v3;
            }
            ++v1;
        }
    }

    public Encoder() {
        this._state = 0;
        this._repDistances = new int[4];
        this._optimum = new Optimal[0x1000];
        this._matchFinder = null;
        this._rangeEncoder = new com.badlogic.gdx.utils.compression.rangecoder.Encoder();
        this._isMatch = new short[0xC0];
        this._isRep = new short[12];
        this._isRepG0 = new short[12];
        this._isRepG1 = new short[12];
        this._isRepG2 = new short[12];
        this._isRep0Long = new short[0xC0];
        this._posSlotEncoder = new BitTreeEncoder[4];
        this._posEncoders = new short[0x72];
        this._posAlignEncoder = new BitTreeEncoder(4);
        this._lenEncoder = new LenPriceTableEncoder(this);
        this._repMatchLenEncoder = new LenPriceTableEncoder(this);
        this._literalEncoder = new LiteralEncoder(this);
        this._matchDistances = new int[548];
        this._numFastBytes = 0x20;
        this._posSlotPrices = new int[0x100];
        this._distancesPrices = new int[0x200];
        this._alignPrices = new int[16];
        this._distTableSize = 44;
        this._posStateBits = 2;
        this._posStateMask = 3;
        this._numLiteralPosStateBits = 0;
        this._numLiteralContextBits = 3;
        this._dictionarySize = 0x400000;
        this._dictionarySizePrev = -1;
        this._numFastBytesPrev = -1;
        this._matchFinderType = 1;
        this._writeEndMark = false;
        this._needReleaseMFStream = false;
        this.reps = new int[4];
        this.repLens = new int[4];
        this.processedInSize = new long[1];
        this.processedOutSize = new long[1];
        this.finished = new boolean[1];
        this.properties = new byte[5];
        this.tempPrices = new int[0x80];
        for(int v1 = 0; v1 < 0x1000; ++v1) {
            Optimal[] arr_encoder$Optimal = this._optimum;
            arr_encoder$Optimal[v1] = new Optimal(this);
        }
        for(int v = 0; v < 4; ++v) {
            BitTreeEncoder[] arr_bitTreeEncoder = this._posSlotEncoder;
            arr_bitTreeEncoder[v] = new BitTreeEncoder(6);
        }
    }

    int Backward(int v) {
        Optimal[] arr_encoder$Optimal2;
        this._optimumEndIndex = v;
        int v1 = this._optimum[v].PosPrev;
        int v2 = this._optimum[v].BackPrev;
        while(true) {
            if(this._optimum[v].Prev1IsChar) {
                this._optimum[v1].MakeAsChar();
                Optimal[] arr_encoder$Optimal = this._optimum;
                arr_encoder$Optimal[v1].PosPrev = v1 - 1;
                if(arr_encoder$Optimal[v].Prev2) {
                    Optimal[] arr_encoder$Optimal1 = this._optimum;
                    arr_encoder$Optimal1[v1 - 1].Prev1IsChar = false;
                    arr_encoder$Optimal1[v1 - 1].PosPrev = arr_encoder$Optimal1[v].PosPrev2;
                    this._optimum[v1 - 1].BackPrev = this._optimum[v].BackPrev2;
                }
            }
            int v3 = this._optimum[v1].BackPrev;
            int v4 = this._optimum[v1].PosPrev;
            arr_encoder$Optimal2 = this._optimum;
            arr_encoder$Optimal2[v1].BackPrev = v2;
            arr_encoder$Optimal2[v1].PosPrev = v;
            if(v1 <= 0) {
                break;
            }
            v = v1;
            v2 = v3;
            v1 = v4;
        }
        this.backRes = arr_encoder$Optimal2[0].BackPrev;
        this._optimumCurrentIndex = this._optimum[0].PosPrev;
        return this._optimumCurrentIndex;
    }

    void BaseInit() {
        this._state = 0;
        this._previousByte = 0;
        for(int v = 0; v < 4; ++v) {
            this._repDistances[v] = 0;
        }
    }

    boolean ChangePair(int v, int v1) {
        return v < 0x2000000 && v1 >= v << 7;
    }

    public void Code(InputStream inputStream0, OutputStream outputStream0, long v, long v1, ICodeProgress iCodeProgress0) throws IOException {
        try {
            this._needReleaseMFStream = false;
            this.SetStreams(inputStream0, outputStream0, v, v1);
            while(true) {
                this.CodeOneBlock(this.processedInSize, this.processedOutSize, this.finished);
                if(this.finished[0]) {
                    break;
                }
                if(iCodeProgress0 != null) {
                    iCodeProgress0.SetProgress(this.processedInSize[0], this.processedOutSize[0]);
                }
            }
        }
        finally {
            this.ReleaseStreams();
        }
    }

    public void CodeOneBlock(long[] arr_v, long[] arr_v1, boolean[] arr_z) throws IOException {
        arr_v[0] = 0L;
        arr_v1[0] = 0L;
        arr_z[0] = true;
        InputStream inputStream0 = this._inStream;
        if(inputStream0 != null) {
            this._matchFinder.SetStream(inputStream0);
            this._matchFinder.Init();
            this._needReleaseMFStream = true;
            this._inStream = null;
        }
        if(this._finished) {
            return;
        }
        this._finished = true;
        long v = this.nowPos64;
        if(v == 0L) {
            if(this._matchFinder.GetNumAvailableBytes() == 0) {
                this.Flush(((int)this.nowPos64));
                return;
            }
            this.ReadMatchDistances();
            this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + (this._posStateMask & ((int)this.nowPos64)), 0);
            this._state = Base.StateUpdateChar(this._state);
            byte b = this._matchFinder.GetIndexByte(-this._additionalOffset);
            this._literalEncoder.GetSubCoder(((int)this.nowPos64), this._previousByte).Encode(this._rangeEncoder, b);
            this._previousByte = b;
            --this._additionalOffset;
            ++this.nowPos64;
        }
        if(this._matchFinder.GetNumAvailableBytes() == 0) {
            this.Flush(((int)this.nowPos64));
            return;
        }
        do {
            do {
                int v1 = this.GetOptimum(((int)this.nowPos64));
                int v2 = this.backRes;
                int v3 = this._posStateMask & ((int)this.nowPos64);
                int v4 = (this._state << 4) + v3;
                if(v1 != 1 || v2 != -1) {
                    this._rangeEncoder.Encode(this._isMatch, v4, 1);
                    if(v2 < 4) {
                        this._rangeEncoder.Encode(this._isRep, this._state, 1);
                        if(v2 == 0) {
                            this._rangeEncoder.Encode(this._isRepG0, this._state, 0);
                            if(v1 == 1) {
                                this._rangeEncoder.Encode(this._isRep0Long, v4, 0);
                            }
                            else {
                                this._rangeEncoder.Encode(this._isRep0Long, v4, 1);
                            }
                        }
                        else {
                            this._rangeEncoder.Encode(this._isRepG0, this._state, 1);
                            if(v2 == 1) {
                                this._rangeEncoder.Encode(this._isRepG1, this._state, 0);
                            }
                            else {
                                this._rangeEncoder.Encode(this._isRepG1, this._state, 1);
                                this._rangeEncoder.Encode(this._isRepG2, this._state, v2 - 2);
                            }
                        }
                        if(v1 == 1) {
                            this._state = Base.StateUpdateShortRep(this._state);
                        }
                        else {
                            this._repMatchLenEncoder.Encode(this._rangeEncoder, v1 - 2, v3);
                            this._state = Base.StateUpdateRep(this._state);
                        }
                        int v6 = this._repDistances[v2];
                        if(v2 != 0) {
                            while(v2 >= 1) {
                                this._repDistances[v2] = this._repDistances[v2 - 1];
                                --v2;
                            }
                            this._repDistances[0] = v6;
                        }
                    }
                    else {
                        this._rangeEncoder.Encode(this._isRep, this._state, 0);
                        this._state = Base.StateUpdateMatch(this._state);
                        this._lenEncoder.Encode(this._rangeEncoder, v1 - 2, v3);
                        int v7 = Encoder.GetPosSlot(v2 - 4);
                        this._posSlotEncoder[Base.GetLenToPosState(v1)].Encode(this._rangeEncoder, v7);
                        if(v7 >= 4) {
                            int v8 = (v7 >> 1) - 1;
                            int v9 = (v7 & 1 | 2) << v8;
                            int v10 = v2 - 4 - v9;
                            if(v7 < 14) {
                                BitTreeEncoder.ReverseEncode(this._posEncoders, v9 - v7 - 1, this._rangeEncoder, v8, v10);
                            }
                            else {
                                this._rangeEncoder.EncodeDirectBits(v10 >> 4, v8 - 4);
                                this._posAlignEncoder.ReverseEncode(this._rangeEncoder, v10 & 15);
                                ++this._alignPriceCount;
                            }
                        }
                        for(int v11 = 3; v11 >= 1; --v11) {
                            this._repDistances[v11] = this._repDistances[v11 - 1];
                        }
                        this._repDistances[0] = v2 - 4;
                        ++this._matchPriceCount;
                    }
                    this._previousByte = this._matchFinder.GetIndexByte(v1 - 1 - this._additionalOffset);
                }
                else {
                    this._rangeEncoder.Encode(this._isMatch, v4, 0);
                    byte b1 = this._matchFinder.GetIndexByte(-this._additionalOffset);
                    Encoder2 encoder$LiteralEncoder$Encoder20 = this._literalEncoder.GetSubCoder(((int)this.nowPos64), this._previousByte);
                    if(Base.StateIsCharState(this._state)) {
                        encoder$LiteralEncoder$Encoder20.Encode(this._rangeEncoder, b1);
                    }
                    else {
                        int v5 = this._matchFinder.GetIndexByte(-1 - this._repDistances[0] - this._additionalOffset);
                        encoder$LiteralEncoder$Encoder20.EncodeMatched(this._rangeEncoder, ((byte)v5), b1);
                    }
                    this._previousByte = b1;
                    this._state = Base.StateUpdateChar(this._state);
                }
                this._additionalOffset -= v1;
                this.nowPos64 += (long)v1;
            }
            while(this._additionalOffset != 0);
            if(this._matchPriceCount >= 0x80) {
                this.FillDistancesPrices();
            }
            if(this._alignPriceCount >= 16) {
                this.FillAlignPrices();
            }
            arr_v[0] = this.nowPos64;
            arr_v1[0] = this._rangeEncoder.GetProcessedSizeAdd();
            if(this._matchFinder.GetNumAvailableBytes() == 0) {
                this.Flush(((int)this.nowPos64));
                return;
            }
        }
        while(this.nowPos64 - v < 0x1000L);
        this._finished = false;
        arr_z[0] = false;
    }

    void Create() {
        if(this._matchFinder == null) {
            BinTree binTree0 = new BinTree();
            binTree0.SetType((this._matchFinderType == 0 ? 2 : 4));
            this._matchFinder = binTree0;
        }
        this._literalEncoder.Create(this._numLiteralPosStateBits, this._numLiteralContextBits);
        if(this._dictionarySize == this._dictionarySizePrev && this._numFastBytesPrev == this._numFastBytes) {
            return;
        }
        this._matchFinder.Create(this._dictionarySize, 0x1000, this._numFastBytes, 274);
        this._dictionarySizePrev = this._dictionarySize;
        this._numFastBytesPrev = this._numFastBytes;
    }

    void FillAlignPrices() {
        for(int v = 0; v < 16; ++v) {
            this._alignPrices[v] = this._posAlignEncoder.ReverseGetPrice(v);
        }
        this._alignPriceCount = 0;
    }

    void FillDistancesPrices() {
        for(int v = 4; v < 0x80; ++v) {
            int v1 = Encoder.GetPosSlot(v);
            int v2 = (v1 >> 1) - 1;
            int v3 = (v1 & 1 | 2) << v2;
            this.tempPrices[v] = BitTreeEncoder.ReverseGetPrice(this._posEncoders, v3 - v1 - 1, v2, v - v3);
        }
        for(int v4 = 0; v4 < 4; ++v4) {
            BitTreeEncoder bitTreeEncoder0 = this._posSlotEncoder[v4];
            int v5 = v4 << 6;
            for(int v6 = 0; v6 < this._distTableSize; ++v6) {
                this._posSlotPrices[v5 + v6] = bitTreeEncoder0.GetPrice(v6);
            }
            for(int v7 = 14; v7 < this._distTableSize; ++v7) {
                this._posSlotPrices[v5 + v7] += (v7 >> 1) - 5 << 6;
            }
            int v8 = v4 * 0x80;
            int v9;
            for(v9 = 0; v9 < 4; ++v9) {
                this._distancesPrices[v8 + v9] = this._posSlotPrices[v5 + v9];
            }
            while(v9 < 0x80) {
                this._distancesPrices[v8 + v9] = this._posSlotPrices[Encoder.GetPosSlot(v9) + v5] + this.tempPrices[v9];
                ++v9;
            }
        }
        this._matchPriceCount = 0;
    }

    void Flush(int v) throws IOException {
        this.ReleaseMFStream();
        this.WriteEndMarker(v & this._posStateMask);
        this._rangeEncoder.FlushData();
        this._rangeEncoder.FlushStream();
    }

    int GetOptimum(int v) throws IOException {
        int v96;
        int v95;
        int v94;
        int v93;
        int v110;
        int v108;
        int v87;
        int[] arr_v6;
        int v82;
        int v66;
        int v65;
        int v60;
        int v58;
        int v49;
        boolean z;
        int v45;
        int v36;
        int v38;
        int v37;
        int v35;
        int v34;
        int v4;
        int v1 = this._optimumCurrentIndex;
        if(this._optimumEndIndex != v1) {
            int v2 = this._optimumCurrentIndex;
            int v3 = this._optimum[v1].PosPrev - v2;
            this.backRes = this._optimum[v2].BackPrev;
            this._optimumCurrentIndex = this._optimum[this._optimumCurrentIndex].PosPrev;
            return v3;
        }
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        if(this._longestMatchWasFound) {
            v4 = this._longestMatchLength;
            this._longestMatchWasFound = false;
        }
        else {
            v4 = this.ReadMatchDistances();
        }
        int v5 = this._numDistancePairs;
        if(this._matchFinder.GetNumAvailableBytes() + 1 < 2) {
            this.backRes = -1;
            return 1;
        }
        int v6 = 0;
        for(int v7 = 0; v7 < 4; ++v7) {
            int[] arr_v = this.reps;
            arr_v[v7] = this._repDistances[v7];
            int[] arr_v1 = this.repLens;
            arr_v1[v7] = this._matchFinder.GetMatchLen(-1, arr_v[v7], 273);
            if(this.repLens[v7] > this.repLens[v6]) {
                v6 = v7;
            }
        }
        int[] arr_v2 = this.repLens;
        int v8 = this._numFastBytes;
        if(arr_v2[v6] >= v8) {
            this.backRes = v6;
            int v9 = arr_v2[v6];
            this.MovePos(v9 - 1);
            return v9;
        }
        if(v4 >= v8) {
            this.backRes = this._matchDistances[v5 - 1] + 4;
            this.MovePos(v4 - 1);
            return v4;
        }
        int v10 = this._matchFinder.GetIndexByte(-1);
        int v11 = this._matchFinder.GetIndexByte(-2 - this._repDistances[0]);
        if(v4 < 2 && v10 != v11 && this.repLens[v6] < 2) {
            this.backRes = -1;
            return 1;
        }
        Optimal[] arr_encoder$Optimal = this._optimum;
        int v12 = this._state;
        arr_encoder$Optimal[0].State = v12;
        int v13 = this._posStateMask & v;
        arr_encoder$Optimal[1].Price = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(v12 << 4) + v13]) + this._literalEncoder.GetSubCoder(v, this._previousByte).GetPrice(!Base.StateIsCharState(this._state), ((byte)v11), ((byte)v10));
        this._optimum[1].MakeAsChar();
        int v14 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(this._state << 4) + v13]);
        int v15 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[this._state]) + v14;
        if(v11 == v10) {
            int v16 = this.GetRepLen1Price(this._state, v13) + v15;
            if(v16 < this._optimum[1].Price) {
                Optimal[] arr_encoder$Optimal1 = this._optimum;
                arr_encoder$Optimal1[1].Price = v16;
                arr_encoder$Optimal1[1].MakeAsShortRep();
            }
        }
        int v17 = v4 < this.repLens[v6] ? this.repLens[v6] : v4;
        if(v17 < 2) {
            this.backRes = this._optimum[1].BackPrev;
            return 1;
        }
        Optimal[] arr_encoder$Optimal2 = this._optimum;
        arr_encoder$Optimal2[1].PosPrev = 0;
        int[] arr_v3 = this.reps;
        arr_encoder$Optimal2[0].Backs0 = arr_v3[0];
        arr_encoder$Optimal2[0].Backs1 = arr_v3[1];
        arr_encoder$Optimal2[0].Backs2 = arr_v3[2];
        arr_encoder$Optimal2[0].Backs3 = arr_v3[3];
        for(int v18 = v17; true; --v18) {
            this._optimum[v18].Price = 0xFFFFFFF;
            if(v18 - 1 < 2) {
                break;
            }
        }
        for(int v19 = 0; v19 < 4; ++v19) {
            int v20 = this.repLens[v19];
            if(v20 >= 2) {
                int v21 = this.GetPureRepPrice(v19, this._state, v13);
                while(true) {
                    int v22 = this._repMatchLenEncoder.GetPrice(v20 - 2, v13) + (v21 + v15);
                    Optimal encoder$Optimal0 = this._optimum[v20];
                    if(v22 < encoder$Optimal0.Price) {
                        encoder$Optimal0.Price = v22;
                        encoder$Optimal0.PosPrev = 0;
                        encoder$Optimal0.BackPrev = v19;
                        encoder$Optimal0.Prev1IsChar = false;
                    }
                    if(v20 - 1 < 2) {
                        break;
                    }
                    --v20;
                }
            }
        }
        int v23 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep[this._state]);
        int v24 = this.repLens[0] < 2 ? 2 : this.repLens[0] + 1;
        if(v24 <= v4) {
            int v25;
            for(v25 = 0; v24 > this._matchDistances[v25]; v25 += 2) {
            }
            while(true) {
                int v26 = this._matchDistances[v25 + 1];
                int v27 = this.GetPosLenPrice(v26, v24, v13) + (v14 + v23);
                Optimal encoder$Optimal1 = this._optimum[v24];
                if(v27 < encoder$Optimal1.Price) {
                    encoder$Optimal1.Price = v27;
                    encoder$Optimal1.PosPrev = 0;
                    encoder$Optimal1.BackPrev = v26 + 4;
                    encoder$Optimal1.Prev1IsChar = false;
                }
                if(v24 == this._matchDistances[v25]) {
                    v25 += 2;
                    if(v25 != v5) {
                        goto label_110;
                    }
                    break;
                }
            label_110:
                ++v24;
            }
        }
        int v28 = v;
        int v29 = 0;
        while(true) {
            ++v29;
            if(v29 == v17) {
                return this.Backward(v29);
            }
            int v30 = this.ReadMatchDistances();
            int v31 = this._numDistancePairs;
            if(v30 >= this._numFastBytes) {
                this._longestMatchLength = v30;
                this._longestMatchWasFound = true;
                return this.Backward(v29);
            }
            ++v28;
            int v32 = this._optimum[v29].PosPrev;
            if(this._optimum[v29].Prev1IsChar) {
                --v32;
                if(this._optimum[v29].Prev2) {
                    int v33 = this._optimum[this._optimum[v29].PosPrev2].State;
                    v34 = this._optimum[v29].BackPrev2 < 4 ? Base.StateUpdateRep(v33) : Base.StateUpdateMatch(v33);
                }
                else {
                    v34 = this._optimum[v32].State;
                }
                v35 = Base.StateUpdateChar(v34);
            }
            else {
                v35 = this._optimum[v32].State;
            }
            if(v32 != v29 - 1) {
                if(!this._optimum[v29].Prev1IsChar || !this._optimum[v29].Prev2) {
                    v37 = this._optimum[v29].BackPrev;
                    v38 = v37 >= 4 ? Base.StateUpdateMatch(v35) : Base.StateUpdateRep(v35);
                }
                else {
                    v32 = this._optimum[v29].PosPrev2;
                    v37 = this._optimum[v29].BackPrev2;
                    v38 = Base.StateUpdateRep(v35);
                }
                Optimal encoder$Optimal2 = this._optimum[v32];
                if(v37 >= 4) {
                    int[] arr_v4 = this.reps;
                    arr_v4[0] = v37 - 4;
                    arr_v4[1] = encoder$Optimal2.Backs0;
                    this.reps[2] = encoder$Optimal2.Backs1;
                    this.reps[3] = encoder$Optimal2.Backs2;
                }
                else if(v37 == 0) {
                    this.reps[0] = encoder$Optimal2.Backs0;
                    this.reps[1] = encoder$Optimal2.Backs1;
                    this.reps[2] = encoder$Optimal2.Backs2;
                    this.reps[3] = encoder$Optimal2.Backs3;
                }
                else {
                    switch(v37) {
                        case 1: {
                            this.reps[0] = encoder$Optimal2.Backs1;
                            this.reps[1] = encoder$Optimal2.Backs0;
                            this.reps[2] = encoder$Optimal2.Backs2;
                            this.reps[3] = encoder$Optimal2.Backs3;
                            break;
                        }
                        case 2: {
                            this.reps[0] = encoder$Optimal2.Backs2;
                            this.reps[1] = encoder$Optimal2.Backs0;
                            this.reps[2] = encoder$Optimal2.Backs1;
                            this.reps[3] = encoder$Optimal2.Backs3;
                            break;
                        }
                        default: {
                            this.reps[0] = encoder$Optimal2.Backs3;
                            this.reps[1] = encoder$Optimal2.Backs0;
                            this.reps[2] = encoder$Optimal2.Backs1;
                            this.reps[3] = encoder$Optimal2.Backs2;
                        }
                    }
                }
                v36 = v38;
            }
            else if(this._optimum[v29].IsShortRep()) {
                v36 = Base.StateUpdateShortRep(v35);
            }
            else {
                v36 = Base.StateUpdateChar(v35);
            }
            Optimal[] arr_encoder$Optimal3 = this._optimum;
            arr_encoder$Optimal3[v29].State = v36;
            int[] arr_v5 = this.reps;
            arr_encoder$Optimal3[v29].Backs0 = arr_v5[0];
            arr_encoder$Optimal3[v29].Backs1 = arr_v5[1];
            arr_encoder$Optimal3[v29].Backs2 = arr_v5[2];
            arr_encoder$Optimal3[v29].Backs3 = arr_v5[3];
            int v39 = arr_encoder$Optimal3[v29].Price;
            int v40 = this._matchFinder.GetIndexByte(-1);
            int v41 = this._matchFinder.GetIndexByte(-2 - this.reps[0]);
            int v42 = this._posStateMask & v28;
            int v43 = (v36 << 4) + v42;
            int v44 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[v43]) + v39 + this._literalEncoder.GetSubCoder(v28, this._matchFinder.GetIndexByte(-2)).GetPrice(!Base.StateIsCharState(v36), ((byte)v41), ((byte)v40));
            Optimal encoder$Optimal3 = this._optimum[v29 + 1];
            if(v44 < encoder$Optimal3.Price) {
                encoder$Optimal3.Price = v44;
                encoder$Optimal3.PosPrev = v29;
                encoder$Optimal3.MakeAsChar();
                v45 = v17;
                z = true;
            }
            else {
                v45 = v17;
                z = false;
            }
            int v46 = v39 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[v43]);
            int v47 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[v36]) + v46;
            if(v41 != v40 || encoder$Optimal3.PosPrev < v29 && encoder$Optimal3.BackPrev == 0) {
                v49 = v31;
            }
            else {
                int v48 = this.GetRepLen1Price(v36, v42) + v47;
                v49 = v31;
                if(v48 <= encoder$Optimal3.Price) {
                    encoder$Optimal3.Price = v48;
                    encoder$Optimal3.PosPrev = v29;
                    encoder$Optimal3.MakeAsShortRep();
                    z = true;
                }
            }
            int v50 = Math.min(0xFFF - v29, this._matchFinder.GetNumAvailableBytes() + 1);
            if(v50 < 2) {
                v17 = v45;
            }
            else {
                int v51 = v50 > this._numFastBytes ? this._numFastBytes : v50;
                if(z || v41 == v40) {
                    v60 = v30;
                    v58 = v46;
                    v59 = v45;
                }
                else {
                    int v52 = this._matchFinder.GetMatchLen(0, this.reps[0], Math.min(v50 - 1, this._numFastBytes));
                    if(v52 >= 2) {
                        int v53 = Base.StateUpdateChar(v36);
                        int v54 = v28 + 1 & this._posStateMask;
                        int v55 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(v53 << 4) + v54]);
                        int v56 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[v53]);
                        int v57 = v29 + 1 + v52;
                        v58 = v46;
                        int v59;
                        for(v59 = v45; v59 < v57; ++v59) {
                            this._optimum[v59 + 1].Price = 0xFFFFFFF;
                        }
                        v60 = v30;
                        int v61 = v44 + v55 + v56 + this.GetRepPrice(0, v52, v53, v54);
                        Optimal encoder$Optimal4 = this._optimum[v57];
                        if(v61 < encoder$Optimal4.Price) {
                            encoder$Optimal4.Price = v61;
                            encoder$Optimal4.PosPrev = v29 + 1;
                            encoder$Optimal4.BackPrev = 0;
                            encoder$Optimal4.Prev1IsChar = true;
                            encoder$Optimal4.Prev2 = false;
                        }
                    }
                    else {
                        v60 = v30;
                        v58 = v46;
                        v59 = v45;
                    }
                }
                int v62 = 2;
                int v63 = 0;
                while(v63 < 4) {
                    int v64 = this._matchFinder.GetMatchLen(-1, this.reps[v63], v51);
                    if(v64 < 2) {
                        v65 = v28;
                        v66 = v47;
                    }
                    else {
                        int v67 = v64;
                        while(true) {
                            int v68 = v29 + v67;
                            if(v59 < v68) {
                                ++v59;
                                this._optimum[v59].Price = 0xFFFFFFF;
                            }
                            else {
                                int v69 = this.GetRepPrice(v63, v67, v36, v42) + v47;
                                Optimal encoder$Optimal5 = this._optimum[v68];
                                if(v69 < encoder$Optimal5.Price) {
                                    encoder$Optimal5.Price = v69;
                                    encoder$Optimal5.PosPrev = v29;
                                    encoder$Optimal5.BackPrev = v63;
                                    encoder$Optimal5.Prev1IsChar = false;
                                }
                                if(v67 - 1 < 2) {
                                    break;
                                }
                                --v67;
                            }
                        }
                        if(v63 == 0) {
                            v62 = v64 + 1;
                        }
                        if(v64 < v50) {
                            int v70 = this._matchFinder.GetMatchLen(v64, this.reps[v63], Math.min(v50 - 1 - v64, this._numFastBytes));
                            if(v70 >= 2) {
                                int v71 = Base.StateUpdateRep(v36);
                                int v72 = v28 + v64;
                                int v73 = this._posStateMask & v72;
                                int v74 = this.GetRepPrice(v63, v64, v36, v42);
                                v66 = v47;
                                int v75 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(v71 << 4) + v73]);
                                v65 = v28;
                                int v76 = this._literalEncoder.GetSubCoder(v72, this._matchFinder.GetIndexByte(v64 - 2)).GetPrice(true, this._matchFinder.GetIndexByte(v64 - 1 - (this.reps[v63] + 1)), this._matchFinder.GetIndexByte(v64 - 1));
                                int v77 = Base.StateUpdateChar(v71);
                                int v78 = this._posStateMask & v72 + 1;
                                int v79 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(v77 << 4) + v78]);
                                int v80 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[v77]);
                                int v81 = v64 + 1 + v70;
                                while(true) {
                                    v82 = v29 + v81;
                                    if(v59 >= v82) {
                                        break;
                                    }
                                    ++v59;
                                    this._optimum[v59].Price = 0xFFFFFFF;
                                }
                                int v83 = v47 + v74 + v75 + v76 + v79 + v80 + this.GetRepPrice(0, v70, v77, v78);
                                Optimal encoder$Optimal6 = this._optimum[v82];
                                if(v83 < encoder$Optimal6.Price) {
                                    encoder$Optimal6.Price = v83;
                                    encoder$Optimal6.PosPrev = v64 + v29 + 1;
                                    encoder$Optimal6.BackPrev = 0;
                                    encoder$Optimal6.Prev1IsChar = true;
                                    encoder$Optimal6.Prev2 = true;
                                    encoder$Optimal6.PosPrev2 = v29;
                                    encoder$Optimal6.BackPrev2 = v63;
                                }
                            }
                            else {
                                v65 = v28;
                                v66 = v47;
                            }
                        }
                        else {
                            v65 = v28;
                            v66 = v47;
                        }
                    }
                    ++v63;
                    v47 = v66;
                    v28 = v65;
                }
                int v84 = v28;
                int v85 = v60;
                if(v85 > v51) {
                    int v86;
                    for(v86 = 0; true; v86 += 2) {
                        arr_v6 = this._matchDistances;
                        if(v51 <= arr_v6[v86]) {
                            break;
                        }
                    }
                    arr_v6[v86] = v51;
                    v87 = v86 + 2;
                    v85 = v51;
                }
                else {
                    v87 = v49;
                }
                if(v85 >= v62) {
                    int v88 = v58 + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep[v36]);
                    while(v59 < v29 + v85) {
                        ++v59;
                        this._optimum[v59].Price = 0xFFFFFFF;
                    }
                    int v89;
                    for(v89 = 0; v62 > this._matchDistances[v89]; v89 += 2) {
                    }
                    while(true) {
                        int v90 = this._matchDistances[v89 + 1];
                        int v91 = this.GetPosLenPrice(v90, v62, v42) + v88;
                        int v92 = v29 + v62;
                        Optimal encoder$Optimal7 = this._optimum[v92];
                        if(v91 < encoder$Optimal7.Price) {
                            encoder$Optimal7.Price = v91;
                            encoder$Optimal7.PosPrev = v29;
                            encoder$Optimal7.BackPrev = v90 + 4;
                            encoder$Optimal7.Prev1IsChar = false;
                        }
                        if(v62 == this._matchDistances[v89]) {
                            if(v62 < v50) {
                                int v97 = this._matchFinder.GetMatchLen(v62, v90, Math.min(v50 - 1 - v62, this._numFastBytes));
                                if(v97 >= 2) {
                                    int v98 = Base.StateUpdateMatch(v36);
                                    int v99 = v84 + v62;
                                    v93 = v50;
                                    v94 = v36;
                                    int v100 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isMatch[(v98 << 4) + (this._posStateMask & v99)]);
                                    v95 = v88;
                                    v96 = v42;
                                    int v101 = this._literalEncoder.GetSubCoder(v99, this._matchFinder.GetIndexByte(v62 - 2)).GetPrice(true, this._matchFinder.GetIndexByte(v62 - v90 - 2), this._matchFinder.GetIndexByte(v62 - 1));
                                    int v102 = Base.StateUpdateChar(v98);
                                    int v103 = this._posStateMask & v99 + 1;
                                    int v104 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isMatch[(v102 << 4) + v103]);
                                    int v105 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep[v102]);
                                    int v106 = v62 + 1 + v97;
                                    int v107 = v59;
                                    while(true) {
                                        v108 = v29 + v106;
                                        if(v107 >= v108) {
                                            break;
                                        }
                                        ++v107;
                                        this._optimum[v107].Price = 0xFFFFFFF;
                                    }
                                    int v109 = v91 + v100 + v101 + v104 + v105 + this.GetRepPrice(0, v97, v102, v103);
                                    Optimal encoder$Optimal8 = this._optimum[v108];
                                    if(v109 < encoder$Optimal8.Price) {
                                        encoder$Optimal8.Price = v109;
                                        encoder$Optimal8.PosPrev = v92 + 1;
                                        encoder$Optimal8.BackPrev = 0;
                                        encoder$Optimal8.Prev1IsChar = true;
                                        encoder$Optimal8.Prev2 = true;
                                        encoder$Optimal8.PosPrev2 = v29;
                                        encoder$Optimal8.BackPrev2 = v90 + 4;
                                    }
                                    v110 = v107;
                                }
                                else {
                                    v93 = v50;
                                    v94 = v36;
                                    v95 = v88;
                                    v110 = v59;
                                    v96 = v42;
                                }
                            }
                            else {
                                v93 = v50;
                                v94 = v36;
                                v95 = v88;
                                v110 = v59;
                                v96 = v42;
                            }
                            v89 += 2;
                            if(v89 == v87) {
                                break;
                            }
                            else {
                                v59 = v110;
                            }
                        }
                        else {
                            v93 = v50;
                            v94 = v36;
                            v95 = v88;
                            v96 = v42;
                        }
                        ++v62;
                        v50 = v93;
                        v36 = v94;
                        v88 = v95;
                        v42 = v96;
                    }
                    v17 = v110;
                }
                else {
                    v17 = v59;
                }
            }
        }
    }

    int GetPosLenPrice(int v, int v1, int v2) {
        int v3 = Base.GetLenToPosState(v1);
        if(v < 0x80) {
            return this._distancesPrices[v3 * 0x80 + v] + this._lenEncoder.GetPrice(v1 - 2, v2);
        }
        int[] arr_v = this._posSlotPrices;
        int v4 = Encoder.GetPosSlot2(v);
        return this._alignPrices[v & 15] + arr_v[(v3 << 6) + v4] + this._lenEncoder.GetPrice(v1 - 2, v2);
    }

    static int GetPosSlot(int v) {
        if(v < 0x800) {
            return Encoder.g_FastPos[v];
        }
        return v >= 0x200000 ? Encoder.g_FastPos[v >> 20] + 40 : Encoder.g_FastPos[v >> 10] + 20;
    }

    static int GetPosSlot2(int v) {
        if(v < 0x20000) {
            return Encoder.g_FastPos[v >> 6] + 12;
        }
        return v >= 0x8000000 ? Encoder.g_FastPos[v >> 26] + 52 : Encoder.g_FastPos[v >> 16] + 0x20;
    }

    int GetPureRepPrice(int v, int v1, int v2) {
        if(v == 0) {
            return com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG0[v1]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRep0Long[(v1 << 4) + v2]);
        }
        int v3 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRepG0[v1]);
        if(v == 1) {
            return com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG1[v1]) + v3;
        }
        int v4 = com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice1(this._isRepG1[v1]);
        return com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice(this._isRepG2[v1], v - 2) + (v3 + v4);
    }

    int GetRepLen1Price(int v, int v1) {
        return com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRepG0[v]) + com.badlogic.gdx.utils.compression.rangecoder.Encoder.GetPrice0(this._isRep0Long[(v << 4) + v1]);
    }

    int GetRepPrice(int v, int v1, int v2, int v3) {
        return this._repMatchLenEncoder.GetPrice(v1 - 2, v3) + this.GetPureRepPrice(v, v2, v3);
    }

    void Init() {
        this.BaseInit();
        this._rangeEncoder.Init();
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isMatch);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRep0Long);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRep);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG0);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG1);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._isRepG2);
        com.badlogic.gdx.utils.compression.rangecoder.Encoder.InitBitModels(this._posEncoders);
        this._literalEncoder.Init();
        for(int v = 0; v < 4; ++v) {
            this._posSlotEncoder[v].Init();
        }
        this._lenEncoder.Init(1 << this._posStateBits);
        this._repMatchLenEncoder.Init(1 << this._posStateBits);
        this._posAlignEncoder.Init();
        this._longestMatchWasFound = false;
        this._optimumEndIndex = 0;
        this._optimumCurrentIndex = 0;
        this._additionalOffset = 0;
    }

    void MovePos(int v) throws IOException {
        if(v > 0) {
            this._matchFinder.Skip(v);
            this._additionalOffset += v;
        }
    }

    int ReadMatchDistances() throws IOException {
        int v1;
        this._numDistancePairs = this._matchFinder.GetMatches(this._matchDistances);
        int v = this._numDistancePairs;
        if(v > 0) {
            int[] arr_v = this._matchDistances;
            v1 = arr_v[v - 2];
            if(v1 == this._numFastBytes) {
                v1 += this._matchFinder.GetMatchLen(v1 - 1, arr_v[v - 1], 273 - v1);
            }
        }
        else {
            v1 = 0;
        }
        ++this._additionalOffset;
        return v1;
    }

    void ReleaseMFStream() {
        BinTree binTree0 = this._matchFinder;
        if(binTree0 != null && this._needReleaseMFStream) {
            binTree0.ReleaseStream();
            this._needReleaseMFStream = false;
        }
    }

    void ReleaseOutStream() {
        this._rangeEncoder.ReleaseStream();
    }

    void ReleaseStreams() {
        this.ReleaseMFStream();
        this.ReleaseOutStream();
    }

    public boolean SetAlgorithm(int v) [...] // Inlined contents

    public boolean SetDictionarySize(int v) {
        int v1 = 0;
        if(v >= 1 && v <= 0x20000000) {
            this._dictionarySize = v;
            while(v > 1 << v1) {
                ++v1;
            }
            this._distTableSize = v1 * 2;
            return true;
        }
        return false;
    }

    public void SetEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    public boolean SetLcLpPb(int v, int v1, int v2) {
        if(v1 >= 0 && v1 <= 4 && v >= 0 && v <= 8 && v2 >= 0 && v2 <= 4) {
            this._numLiteralPosStateBits = v1;
            this._numLiteralContextBits = v;
            this._posStateBits = v2;
            this._posStateMask = (1 << this._posStateBits) - 1;
            return true;
        }
        return false;
    }

    public boolean SetMatchFinder(int v) {
        if(v >= 0 && v <= 2) {
            int v1 = this._matchFinderType;
            this._matchFinderType = v;
            if(this._matchFinder != null && v1 != this._matchFinderType) {
                this._dictionarySizePrev = -1;
                this._matchFinder = null;
            }
            return true;
        }
        return false;
    }

    public boolean SetNumFastBytes(int v) {
        if(v >= 5 && v <= 273) {
            this._numFastBytes = v;
            return true;
        }
        return false;
    }

    void SetOutStream(OutputStream outputStream0) {
        this._rangeEncoder.SetStream(outputStream0);
    }

    void SetStreams(InputStream inputStream0, OutputStream outputStream0, long v, long v1) {
        this._inStream = inputStream0;
        this._finished = false;
        this.Create();
        this.SetOutStream(outputStream0);
        this.Init();
        this.FillDistancesPrices();
        this.FillAlignPrices();
        this._lenEncoder.SetTableSize(this._numFastBytes - 1);
        this._lenEncoder.UpdateTables(1 << this._posStateBits);
        this._repMatchLenEncoder.SetTableSize(this._numFastBytes - 1);
        this._repMatchLenEncoder.UpdateTables(1 << this._posStateBits);
        this.nowPos64 = 0L;
    }

    void SetWriteEndMarkerMode(boolean z) {
        this._writeEndMark = z;
    }

    public void WriteCoderProperties(OutputStream outputStream0) throws IOException {
        this.properties[0] = (byte)((this._posStateBits * 5 + this._numLiteralPosStateBits) * 9 + this._numLiteralContextBits);
        for(int v = 0; v < 4; ++v) {
            this.properties[v + 1] = (byte)(this._dictionarySize >> v * 8);
        }
        outputStream0.write(this.properties, 0, 5);
    }

    void WriteEndMarker(int v) throws IOException {
        if(!this._writeEndMark) {
            return;
        }
        this._rangeEncoder.Encode(this._isMatch, (this._state << 4) + v, 1);
        this._rangeEncoder.Encode(this._isRep, this._state, 0);
        this._state = Base.StateUpdateMatch(this._state);
        this._lenEncoder.Encode(this._rangeEncoder, 0, v);
        this._posSlotEncoder[0].Encode(this._rangeEncoder, 0x3F);
        this._rangeEncoder.EncodeDirectBits(0x3FFFFFF, 26);
        this._posAlignEncoder.ReverseEncode(this._rangeEncoder, 15);
    }
}

