package com.badlogic.gdx.utils.compression.lzma;

import com.badlogic.gdx.utils.compression.lz.OutWindow;
import com.badlogic.gdx.utils.compression.rangecoder.BitTreeDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Decoder {
    class LenDecoder {
        short[] m_Choice;
        BitTreeDecoder m_HighCoder;
        BitTreeDecoder[] m_LowCoder;
        BitTreeDecoder[] m_MidCoder;
        int m_NumPosStates;

        LenDecoder() {
            this.m_Choice = new short[2];
            this.m_LowCoder = new BitTreeDecoder[16];
            this.m_MidCoder = new BitTreeDecoder[16];
            this.m_HighCoder = new BitTreeDecoder(8);
            this.m_NumPosStates = 0;
        }

        public void Create(int v) {
            int v1;
            while((v1 = this.m_NumPosStates) < v) {
                BitTreeDecoder[] arr_bitTreeDecoder = this.m_LowCoder;
                arr_bitTreeDecoder[v1] = new BitTreeDecoder(3);
                BitTreeDecoder[] arr_bitTreeDecoder1 = this.m_MidCoder;
                int v2 = this.m_NumPosStates;
                arr_bitTreeDecoder1[v2] = new BitTreeDecoder(3);
                ++this.m_NumPosStates;
            }
        }

        public int Decode(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder0, int v) throws IOException {
            if(decoder0.DecodeBit(this.m_Choice, 0) == 0) {
                return this.m_LowCoder[v].Decode(decoder0);
            }
            return decoder0.DecodeBit(this.m_Choice, 1) == 0 ? this.m_MidCoder[v].Decode(decoder0) + 8 : this.m_HighCoder.Decode(decoder0) + 16;
        }

        public void Init() {
            com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Choice);
            for(int v = 0; v < this.m_NumPosStates; ++v) {
                this.m_LowCoder[v].Init();
                this.m_MidCoder[v].Init();
            }
            this.m_HighCoder.Init();
        }
    }

    class LiteralDecoder {
        class Decoder2 {
            short[] m_Decoders;

            Decoder2() {
                this.m_Decoders = new short[0x300];
            }

            public byte DecodeNormal(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder0) throws IOException {
                int v = 1;
                do {
                    v = decoder0.DecodeBit(this.m_Decoders, v) | v << 1;
                }
                while(v < 0x100);
                return (byte)v;
            }

            public byte DecodeWithMatchByte(com.badlogic.gdx.utils.compression.rangecoder.Decoder decoder0, byte b) throws IOException {
                int v = 1;
                do {
                    int v1 = b >> 7 & 1;
                    b = (byte)(b << 1);
                    int v2 = decoder0.DecodeBit(this.m_Decoders, (v1 + 1 << 8) + v);
                    v = v << 1 | v2;
                    if(v1 != v2) {
                        while(v < 0x100) {
                            v = v << 1 | decoder0.DecodeBit(this.m_Decoders, v);
                        }
                        return (byte)v;
                    }
                }
                while(v < 0x100);
                return (byte)v;
            }

            public void Init() {
                com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_Decoders);
            }
        }

        Decoder2[] m_Coders;
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
            this.m_Coders = new Decoder2[v2];
            for(int v3 = 0; v3 < v2; ++v3) {
                Decoder2[] arr_decoder$LiteralDecoder$Decoder2 = this.m_Coders;
                arr_decoder$LiteralDecoder$Decoder2[v3] = new Decoder2(this);
            }
        }

        Decoder2 GetDecoder(int v, byte b) {
            return this.m_Coders[((v & this.m_PosMask) << this.m_NumPrevBits) + ((b & 0xFF) >>> 8 - this.m_NumPrevBits)];
        }

        public void Init() {
            int v = 1 << this.m_NumPrevBits + this.m_NumPosBits;
            for(int v1 = 0; v1 < v; ++v1) {
                this.m_Coders[v1].Init();
            }
        }
    }

    int m_DictionarySize;
    int m_DictionarySizeCheck;
    short[] m_IsMatchDecoders;
    short[] m_IsRep0LongDecoders;
    short[] m_IsRepDecoders;
    short[] m_IsRepG0Decoders;
    short[] m_IsRepG1Decoders;
    short[] m_IsRepG2Decoders;
    LenDecoder m_LenDecoder;
    LiteralDecoder m_LiteralDecoder;
    OutWindow m_OutWindow;
    BitTreeDecoder m_PosAlignDecoder;
    short[] m_PosDecoders;
    BitTreeDecoder[] m_PosSlotDecoder;
    int m_PosStateMask;
    com.badlogic.gdx.utils.compression.rangecoder.Decoder m_RangeDecoder;
    LenDecoder m_RepLenDecoder;

    public Decoder() {
        this.m_OutWindow = new OutWindow();
        this.m_RangeDecoder = new com.badlogic.gdx.utils.compression.rangecoder.Decoder();
        this.m_IsMatchDecoders = new short[0xC0];
        this.m_IsRepDecoders = new short[12];
        this.m_IsRepG0Decoders = new short[12];
        this.m_IsRepG1Decoders = new short[12];
        this.m_IsRepG2Decoders = new short[12];
        this.m_IsRep0LongDecoders = new short[0xC0];
        this.m_PosSlotDecoder = new BitTreeDecoder[4];
        this.m_PosDecoders = new short[0x72];
        this.m_PosAlignDecoder = new BitTreeDecoder(4);
        this.m_LenDecoder = new LenDecoder(this);
        this.m_RepLenDecoder = new LenDecoder(this);
        this.m_LiteralDecoder = new LiteralDecoder(this);
        this.m_DictionarySize = -1;
        this.m_DictionarySizeCheck = -1;
        for(int v = 0; v < 4; ++v) {
            BitTreeDecoder[] arr_bitTreeDecoder = this.m_PosSlotDecoder;
            arr_bitTreeDecoder[v] = new BitTreeDecoder(6);
        }
    }

    // This method was un-flattened
    public boolean Code(InputStream inputStream0, OutputStream outputStream0, long v) throws IOException {
        int v18;
        int v13;
        int v11;
        this.m_RangeDecoder.SetStream(inputStream0);
        this.m_OutWindow.SetStream(outputStream0);
        this.Init();
        int v1 = 0;
        long v2 = 0L;
        int v3 = 0;
        int v4 = 0;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        while(v < 0L || v2 < v) {
            int v8 = this.m_PosStateMask & ((int)v2);
            int v9 = (v1 << 4) + v8;
            if(this.m_RangeDecoder.DecodeBit(this.m_IsMatchDecoders, v9) == 0) {
                Decoder2 decoder$LiteralDecoder$Decoder20 = this.m_LiteralDecoder.GetDecoder(((int)v2), ((byte)v3));
                int v10 = Base.StateIsCharState(v1) ? decoder$LiteralDecoder$Decoder20.DecodeNormal(this.m_RangeDecoder) : decoder$LiteralDecoder$Decoder20.DecodeWithMatchByte(this.m_RangeDecoder, this.m_OutWindow.GetByte(v6));
                this.m_OutWindow.PutByte(((byte)v10));
                v1 = Base.StateUpdateChar(v1);
                ++v2;
                v3 = v10;
                continue;
            }
            if(this.m_RangeDecoder.DecodeBit(this.m_IsRepDecoders, v1) == 1) {
                if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG0Decoders, v1) != 0) {
                    if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG1Decoders, v1) == 0) {
                        v11 = v7;
                        v7 = v5;
                    }
                    else {
                        if(this.m_RangeDecoder.DecodeBit(this.m_IsRepG2Decoders, v1) == 0) {
                            int v12 = v7;
                            v7 = v4;
                            v4 = v12;
                        }
                        v11 = v4;
                        v4 = v5;
                    }
                    v5 = v6;
                    v6 = v7;
                    v7 = v11;
                }
                else if(this.m_RangeDecoder.DecodeBit(this.m_IsRep0LongDecoders, v9) == 0) {
                    v13 = 1;
                    v1 = Base.StateUpdateShortRep(v1);
                    goto label_68;
                }
                v13 = this.m_RepLenDecoder.Decode(this.m_RangeDecoder, v8) + 2;
                v1 = Base.StateUpdateRep(v1);
            }
            else {
                int v14 = this.m_LenDecoder.Decode(this.m_RangeDecoder, v8) + 2;
                v1 = Base.StateUpdateMatch(v1);
                int v15 = this.m_PosSlotDecoder[Base.GetLenToPosState(v14)].Decode(this.m_RangeDecoder);
                if(v15 >= 4) {
                    int v16 = (v15 >> 1) - 1;
                    int v17 = (v15 & 1 | 2) << v16;
                    if(v15 < 14) {
                        v18 = v17 + BitTreeDecoder.ReverseDecode(this.m_PosDecoders, v17 - v15 - 1, this.m_RangeDecoder, v16);
                    }
                    else {
                        v18 = v17 + (this.m_RangeDecoder.DecodeDirectBits(v16 - 4) << 4) + this.m_PosAlignDecoder.ReverseDecode(this.m_RangeDecoder);
                        if(v18 < 0) {
                            if(v18 == -1) {
                                break;
                            }
                            return false;
                        }
                    }
                    v13 = v14;
                    v7 = v4;
                    v4 = v5;
                    v5 = v6;
                    v6 = v18;
                }
                else {
                    v13 = v14;
                    v7 = v4;
                    v4 = v5;
                    v5 = v6;
                    v6 = v15;
                }
            }
        label_68:
            if(((long)v6) < v2 && v6 < this.m_DictionarySizeCheck) {
                this.m_OutWindow.CopyBlock(v6, v13);
                v2 += (long)v13;
                v3 = this.m_OutWindow.GetByte(0);
                continue;
            }
            return false;
        }
        this.m_OutWindow.Flush();
        this.m_OutWindow.ReleaseStream();
        this.m_RangeDecoder.ReleaseStream();
        return true;
    }

    void Init() throws IOException {
        this.m_OutWindow.Init(false);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsMatchDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRep0LongDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepDecoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG0Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG1Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_IsRepG2Decoders);
        com.badlogic.gdx.utils.compression.rangecoder.Decoder.InitBitModels(this.m_PosDecoders);
        this.m_LiteralDecoder.Init();
        for(int v = 0; v < 4; ++v) {
            this.m_PosSlotDecoder[v].Init();
        }
        this.m_LenDecoder.Init();
        this.m_RepLenDecoder.Init();
        this.m_PosAlignDecoder.Init();
        this.m_RangeDecoder.Init();
    }

    public boolean SetDecoderProperties(byte[] arr_b) {
        if(arr_b.length < 5) {
            return false;
        }
        int v = arr_b[0] & 0xFF;
        int v1 = 0;
        for(int v2 = 0; v2 < 4; ++v2) {
            v1 += (arr_b[v2 + 1] & 0xFF) << v2 * 8;
        }
        return this.SetLcLpPb(v % 9, v / 9 % 5, v / 9 / 5) ? this.SetDictionarySize(v1) : false;
    }

    boolean SetDictionarySize(int v) {
        if(v < 0) {
            return false;
        }
        if(this.m_DictionarySize != v) {
            this.m_DictionarySize = v;
            this.m_DictionarySizeCheck = Math.max(this.m_DictionarySize, 1);
            this.m_OutWindow.Create(Math.max(this.m_DictionarySizeCheck, 0x1000));
        }
        return true;
    }

    boolean SetLcLpPb(int v, int v1, int v2) {
        if(v <= 8 && v1 <= 4 && v2 <= 4) {
            this.m_LiteralDecoder.Create(v1, v);
            this.m_LenDecoder.Create(1 << v2);
            this.m_RepLenDecoder.Create(1 << v2);
            this.m_PosStateMask = (1 << v2) - 1;
            return true;
        }
        return false;
    }
}

