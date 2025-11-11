package com.badlogic.gdx.utils.compression.lz;

import java.io.IOException;
import java.io.InputStream;

public class InWindow {
    public int _blockSize;
    public byte[] _bufferBase;
    public int _bufferOffset;
    int _keepSizeAfter;
    int _keepSizeBefore;
    int _pointerToLastSafePosition;
    public int _pos;
    int _posLimit;
    InputStream _stream;
    boolean _streamEndWasReached;
    public int _streamPos;

    public void Create(int v, int v1, int v2) {
        this._keepSizeBefore = v;
        this._keepSizeAfter = v1;
        int v3 = v + v1 + v2;
        if(this._bufferBase == null || this._blockSize != v3) {
            this.Free();
            this._blockSize = v3;
            this._bufferBase = new byte[this._blockSize];
        }
        this._pointerToLastSafePosition = this._blockSize - v1;
    }

    void Free() {
        this._bufferBase = null;
    }

    public byte GetIndexByte(int v) {
        return this._bufferBase[this._bufferOffset + this._pos + v];
    }

    public int GetMatchLen(int v, int v1, int v2) {
        if(this._streamEndWasReached) {
            int v3 = this._pos;
            int v4 = this._streamPos;
            if(v3 + v + v2 > v4) {
                v2 = v4 - (v3 + v);
            }
        }
        int v5 = this._bufferOffset + this._pos + v;
        int v6;
        for(v6 = 0; v6 < v2 && this._bufferBase[v5 + v6] == this._bufferBase[v5 + v6 - (v1 + 1)]; ++v6) {
        }
        return v6;
    }

    public int GetNumAvailableBytes() {
        return this._streamPos - this._pos;
    }

    public void Init() throws IOException {
        this._bufferOffset = 0;
        this._pos = 0;
        this._streamPos = 0;
        this._streamEndWasReached = false;
        this.ReadBlock();
    }

    public void MoveBlock() {
        int v = this._bufferOffset + this._pos - this._keepSizeBefore;
        v = v <= 0 ? this._bufferOffset + this._pos - this._keepSizeBefore : v - 1;
        int v1 = this._bufferOffset + this._streamPos - v;
        for(int v2 = 0; v2 < v1; ++v2) {
            this._bufferBase[v2] = this._bufferBase[v + v2];
        }
        this._bufferOffset -= v;
    }

    public void MovePos() throws IOException {
        ++this._pos;
        int v = this._pos;
        if(v > this._posLimit) {
            if(this._bufferOffset + v > this._pointerToLastSafePosition) {
                this.MoveBlock();
            }
            this.ReadBlock();
        }
    }

    public void ReadBlock() throws IOException {
        if(this._streamEndWasReached) {
            return;
        }
        while(true) {
            int v = this._bufferOffset;
            int v1 = this._streamPos;
            int v2 = this._blockSize - v - v1;
            if(v2 == 0) {
                return;
            }
            int v3 = this._stream.read(this._bufferBase, v + v1, v2);
            if(v3 == -1) {
                this._posLimit = this._streamPos;
                int v4 = this._bufferOffset;
                int v5 = this._pointerToLastSafePosition;
                if(this._posLimit + v4 > v5) {
                    this._posLimit = v5 - v4;
                }
                this._streamEndWasReached = true;
                return;
            }
            this._streamPos += v3;
            int v6 = this._streamPos;
            int v7 = this._keepSizeAfter;
            if(v6 >= this._pos + v7) {
                this._posLimit = v6 - v7;
            }
        }
    }

    public void ReduceOffsets(int v) {
        this._bufferOffset += v;
        this._posLimit -= v;
        this._pos -= v;
        this._streamPos -= v;
    }

    public void ReleaseStream() {
        this._stream = null;
    }

    public void SetStream(InputStream inputStream0) {
        this._stream = inputStream0;
    }
}

