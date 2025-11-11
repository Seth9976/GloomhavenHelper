package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UBJsonReader implements BaseJsonReader {
    public boolean oldFormat;

    public UBJsonReader() {
        this.oldFormat = true;
    }

    @Override  // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(FileHandle fileHandle0) {
        try {
            return this.parse(fileHandle0.read(0x2000));
        }
        catch(Exception exception0) {
            throw new SerializationException("Error parsing file: " + fileHandle0, exception0);
        }
    }

    public JsonValue parse(DataInputStream dataInputStream0) throws IOException {
        try {
            return this.parse(dataInputStream0, dataInputStream0.readByte());
        }
        finally {
            StreamUtils.closeQuietly(dataInputStream0);
        }
    }

    protected JsonValue parse(DataInputStream dataInputStream0, byte b) throws IOException {
        switch(b) {
            case 66: {
                return new JsonValue(((long)this.readUChar(dataInputStream0)));
            }
            case 67: {
                return new JsonValue(((long)dataInputStream0.readChar()));
            }
            case 68: {
                return new JsonValue(dataInputStream0.readDouble());
            }
            case 70: {
                return new JsonValue(false);
            }
            case 73: {
                return this.oldFormat ? new JsonValue(((long)dataInputStream0.readInt())) : new JsonValue(((long)dataInputStream0.readShort()));
            }
            case 76: {
                return new JsonValue(dataInputStream0.readLong());
            }
            case 84: {
                return new JsonValue(true);
            }
            case 85: {
                return new JsonValue(((long)this.readUChar(dataInputStream0)));
            }
            case 90: {
                return new JsonValue(ValueType.nullValue);
            }
            case 91: {
                return this.parseArray(dataInputStream0);
            }
            case 65: 
            case 97: {
                return this.parseData(dataInputStream0, b);
            }
            case 100: {
                return new JsonValue(((double)dataInputStream0.readFloat()));
            }
            case 105: {
                return this.oldFormat ? new JsonValue(((long)dataInputStream0.readShort())) : new JsonValue(((long)dataInputStream0.readByte()));
            }
            case 108: {
                return new JsonValue(((long)dataInputStream0.readInt()));
            }
            case 83: 
            case 0x73: {
                return new JsonValue(this.parseString(dataInputStream0, b));
            }
            case 0x7B: {
                return this.parseObject(dataInputStream0);
            }
            default: {
                throw new GdxRuntimeException("Unrecognized data type");
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(InputStream inputStream0) {
        DataInputStream dataInputStream0;
        try {
            dataInputStream0 = new DataInputStream(inputStream0);
            return this.parse(dataInputStream0);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        finally {
            StreamUtils.closeQuietly(dataInputStream0);
        }
    }

    protected JsonValue parseArray(DataInputStream dataInputStream0) throws IOException {
        int v1;
        JsonValue jsonValue0 = new JsonValue(ValueType.array);
        int v = dataInputStream0.readByte();
        if(v == 36) {
            v1 = dataInputStream0.readByte();
            v = dataInputStream0.readByte();
        }
        else {
            v1 = 0;
        }
        long v2 = -1L;
        if(v == 35) {
            v2 = this.parseSize(dataInputStream0, false, -1L);
            if(v2 < 0L) {
                throw new GdxRuntimeException("Unrecognized data type");
            }
            if(v2 == 0L) {
                return jsonValue0;
            }
            v = v1 == 0 ? dataInputStream0.readByte() : v1;
        }
        JsonValue jsonValue1 = null;
        long v3 = 0L;
        while(dataInputStream0.available() > 0 && v != 93) {
            JsonValue jsonValue2 = this.parse(dataInputStream0, ((byte)v));
            jsonValue2.parent = jsonValue0;
            if(jsonValue1 == null) {
                jsonValue0.child = jsonValue2;
                jsonValue0.size = 1;
            }
            else {
                jsonValue2.prev = jsonValue1;
                jsonValue1.next = jsonValue2;
                ++jsonValue0.size;
            }
            if(v2 > 0L) {
                ++v3;
                if(v3 < v2) {
                    goto label_34;
                }
                break;
            }
        label_34:
            jsonValue1 = jsonValue2;
            v = v1 == 0 ? dataInputStream0.readByte() : v1;
        }
        return jsonValue0;
    }

    protected JsonValue parseData(DataInputStream dataInputStream0, byte b) throws IOException {
        int v = dataInputStream0.readByte();
        long v1 = b == 65 ? this.readUInt(dataInputStream0) : ((long)this.readUChar(dataInputStream0));
        JsonValue jsonValue0 = new JsonValue(ValueType.array);
        long v2 = 0L;
        for(JsonValue jsonValue1 = null; v2 < v1; jsonValue1 = jsonValue2) {
            JsonValue jsonValue2 = this.parse(dataInputStream0, ((byte)v));
            jsonValue2.parent = jsonValue0;
            if(jsonValue1 == null) {
                jsonValue0.child = jsonValue2;
                jsonValue0.size = 1;
            }
            else {
                jsonValue1.next = jsonValue2;
                ++jsonValue0.size;
            }
            ++v2;
        }
        return jsonValue0;
    }

    protected JsonValue parseObject(DataInputStream dataInputStream0) throws IOException {
        int v1;
        JsonValue jsonValue0 = new JsonValue(ValueType.object);
        int v = dataInputStream0.readByte();
        if(v == 36) {
            v1 = dataInputStream0.readByte();
            v = dataInputStream0.readByte();
        }
        else {
            v1 = 0;
        }
        long v2 = -1L;
        if(v == 35) {
            v2 = this.parseSize(dataInputStream0, false, -1L);
            if(v2 < 0L) {
                throw new GdxRuntimeException("Unrecognized data type");
            }
            if(v2 == 0L) {
                return jsonValue0;
            }
            v = dataInputStream0.readByte();
        }
        JsonValue jsonValue1 = null;
        long v3 = 0L;
        while(dataInputStream0.available() > 0 && v != 0x7D) {
            String s = this.parseString(dataInputStream0, true, ((byte)v));
            JsonValue jsonValue2 = this.parse(dataInputStream0, ((byte)(v1 == 0 ? dataInputStream0.readByte() : v1)));
            jsonValue2.setName(s);
            jsonValue2.parent = jsonValue0;
            if(jsonValue1 == null) {
                jsonValue0.child = jsonValue2;
                jsonValue0.size = 1;
            }
            else {
                jsonValue2.prev = jsonValue1;
                jsonValue1.next = jsonValue2;
                ++jsonValue0.size;
            }
            if(v2 > 0L) {
                ++v3;
                if(v3 < v2) {
                    goto label_33;
                }
                break;
            }
        label_33:
            v = dataInputStream0.readByte();
            jsonValue1 = jsonValue2;
        }
        return jsonValue0;
    }

    protected long parseSize(DataInputStream dataInputStream0, byte b, boolean z, long v) throws IOException {
        switch(b) {
            case 73: {
                return (long)this.readUShort(dataInputStream0);
            }
            case 76: {
                return dataInputStream0.readLong();
            }
            case 105: {
                return (long)this.readUChar(dataInputStream0);
            }
            case 108: {
                return this.readUInt(dataInputStream0);
            }
            default: {
                return z ? ((long)(((short)b) & 0xFF)) << 24 | ((long)(((short)dataInputStream0.readByte()) & 0xFF)) << 16 | ((long)(((short)dataInputStream0.readByte()) & 0xFF)) << 8 | ((long)(((short)dataInputStream0.readByte()) & 0xFF)) : v;
            }
        }
    }

    protected long parseSize(DataInputStream dataInputStream0, boolean z, long v) throws IOException {
        return this.parseSize(dataInputStream0, dataInputStream0.readByte(), z, v);
    }

    protected String parseString(DataInputStream dataInputStream0, byte b) throws IOException {
        return this.parseString(dataInputStream0, false, b);
    }

    protected String parseString(DataInputStream dataInputStream0, boolean z, byte b) throws IOException {
        long v = -1L;
        if(b == 83) {
            v = this.parseSize(dataInputStream0, true, -1L);
        }
        else if(b == 0x73) {
            v = (long)this.readUChar(dataInputStream0);
        }
        else if(z) {
            v = this.parseSize(dataInputStream0, b, false, -1L);
        }
        if(v < 0L) {
            throw new GdxRuntimeException("Unrecognized data type, string expected");
        }
        return v <= 0L ? "" : this.readString(dataInputStream0, v);
    }

    protected String readString(DataInputStream dataInputStream0, long v) throws IOException {
        byte[] arr_b = new byte[((int)v)];
        dataInputStream0.readFully(arr_b);
        return new String(arr_b, "UTF-8");
    }

    protected short readUChar(DataInputStream dataInputStream0) throws IOException {
        return (short)(((short)dataInputStream0.readByte()) & 0xFF);
    }

    protected long readUInt(DataInputStream dataInputStream0) throws IOException {
        return (long)dataInputStream0.readInt();
    }

    protected int readUShort(DataInputStream dataInputStream0) throws IOException {
        return dataInputStream0.readShort() & 0xFFFF;
    }
}

