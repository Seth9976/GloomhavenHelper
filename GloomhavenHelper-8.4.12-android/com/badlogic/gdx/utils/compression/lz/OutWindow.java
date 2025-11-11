package com.badlogic.gdx.utils.compression.lz;

import java.io.IOException;
import java.io.OutputStream;

public class OutWindow {
    byte[] _buffer;
    int _pos;
    OutputStream _stream;
    int _streamPos;
    int _windowSize;

    public OutWindow() {
        this._windowSize = 0;
    }

    public void CopyBlock(int v, int v1) throws IOException {
        int v2 = this._pos - v - 1;
        if(v2 < 0) {
            v2 += this._windowSize;
        }
        while(v1 != 0) {
            if(v2 >= this._windowSize) {
                v2 = 0;
            }
            int v3 = this._pos;
            this._pos = v3 + 1;
            this._buffer[v3] = this._buffer[v2];
            if(this._pos >= this._windowSize) {
                this.Flush();
            }
            --v1;
            ++v2;
        }
    }

    public void Create(int v) {
        if(this._buffer == null || this._windowSize != v) {
            this._buffer = new byte[v];
        }
        this._windowSize = v;
        this._pos = 0;
        this._streamPos = 0;
    }

    public void Flush() throws IOException {
        int v = this._streamPos;
        int v1 = this._pos - v;
        if(v1 == 0) {
            return;
        }
        this._stream.write(this._buffer, v, v1);
        if(this._pos >= this._windowSize) {
            this._pos = 0;
        }
        this._streamPos = this._pos;
    }

    public byte GetByte(int v) {
        int v1 = this._pos - v - 1;
        return this._buffer[(v1 >= 0 ? this._pos - v - 1 : v1 + this._windowSize)];
    }

    public void Init(boolean z) {
        if(!z) {
            this._streamPos = 0;
            this._pos = 0;
        }
    }

    public void PutByte(byte b) throws IOException {
        int v = this._pos;
        this._pos = v + 1;
        this._buffer[v] = b;
        if(this._pos >= this._windowSize) {
            this.Flush();
        }
    }

    public void ReleaseStream() throws IOException {
        this.Flush();
        this._stream = null;
    }

    public void SetStream(OutputStream outputStream0) throws IOException {
        this.ReleaseStream();
        this._stream = outputStream0;
    }
}

