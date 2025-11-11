package com.badlogic.gdx.utils;

import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class UBJsonWriter implements Closeable {
    class JsonObject {
        final boolean array;

        JsonObject(boolean z) throws IOException {
            this.array = z;
            uBJsonWriter0.out.writeByte((z ? 91 : 0x7B));
        }

        void close() throws IOException {
            UBJsonWriter.this.out.writeByte((this.array ? 93 : 0x7D));
        }
    }

    private JsonObject current;
    private boolean named;
    final DataOutputStream out;
    private final Array stack;

    public UBJsonWriter(OutputStream outputStream0) {
        this.stack = new Array();
        if(!(outputStream0 instanceof DataOutputStream)) {
            outputStream0 = new DataOutputStream(outputStream0);
        }
        this.out = (DataOutputStream)outputStream0;
    }

    public UBJsonWriter array() throws IOException {
        if(this.current != null && !this.current.array) {
            if(!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
        JsonObject uBJsonWriter$JsonObject0 = new JsonObject(this, true);
        this.current = uBJsonWriter$JsonObject0;
        this.stack.add(uBJsonWriter$JsonObject0);
        return this;
    }

    public UBJsonWriter array(String s) throws IOException {
        this.name(s).array();
        return this;
    }

    private void checkName() {
        if(this.current != null && !this.current.array) {
            if(!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
    }

    @Override
    public void close() throws IOException {
        while(this.stack.size > 0) {
            this.pop();
        }
        this.out.close();
    }

    public void flush() throws IOException {
        this.out.flush();
    }

    public UBJsonWriter name(String s) throws IOException {
        if(this.current == null || this.current.array) {
            throw new IllegalStateException("Current item must be an object.");
        }
        byte[] arr_b = s.getBytes("UTF-8");
        if(arr_b.length <= 0x7F) {
            this.out.writeByte(105);
            this.out.writeByte(arr_b.length);
        }
        else if(arr_b.length <= 0x7FFF) {
            this.out.writeByte(73);
            this.out.writeShort(arr_b.length);
        }
        else {
            this.out.writeByte(108);
            this.out.writeInt(arr_b.length);
        }
        this.out.write(arr_b);
        this.named = true;
        return this;
    }

    public UBJsonWriter object() throws IOException {
        if(this.current != null && !this.current.array) {
            if(!this.named) {
                throw new IllegalStateException("Name must be set.");
            }
            this.named = false;
        }
        JsonObject uBJsonWriter$JsonObject0 = new JsonObject(this, false);
        this.current = uBJsonWriter$JsonObject0;
        this.stack.add(uBJsonWriter$JsonObject0);
        return this;
    }

    public UBJsonWriter object(String s) throws IOException {
        this.name(s).object();
        return this;
    }

    public UBJsonWriter pop() throws IOException {
        return this.pop(false);
    }

    protected UBJsonWriter pop(boolean z) throws IOException {
        if(this.named) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        if(z) {
            this.stack.pop();
        }
        else {
            ((JsonObject)this.stack.pop()).close();
        }
        this.current = this.stack.size == 0 ? null : ((JsonObject)this.stack.peek());
        return this;
    }

    public UBJsonWriter set(String s) throws IOException {
        return this.name(s).value();
    }

    public UBJsonWriter set(String s, byte b) throws IOException {
        return this.name(s).value(b);
    }

    public UBJsonWriter set(String s, char c) throws IOException {
        return this.name(s).value(c);
    }

    public UBJsonWriter set(String s, double f) throws IOException {
        return this.name(s).value(f);
    }

    public UBJsonWriter set(String s, float f) throws IOException {
        return this.name(s).value(f);
    }

    public UBJsonWriter set(String s, int v) throws IOException {
        return this.name(s).value(v);
    }

    public UBJsonWriter set(String s, long v) throws IOException {
        return this.name(s).value(v);
    }

    public UBJsonWriter set(String s, String s1) throws IOException {
        return this.name(s).value(s1);
    }

    public UBJsonWriter set(String s, short v) throws IOException {
        return this.name(s).value(v);
    }

    public UBJsonWriter set(String s, boolean z) throws IOException {
        return this.name(s).value(z);
    }

    public UBJsonWriter set(String s, byte[] arr_b) throws IOException {
        return this.name(s).value(arr_b);
    }

    public UBJsonWriter set(String s, char[] arr_c) throws IOException {
        return this.name(s).value(arr_c);
    }

    public UBJsonWriter set(String s, double[] arr_f) throws IOException {
        return this.name(s).value(arr_f);
    }

    public UBJsonWriter set(String s, float[] arr_f) throws IOException {
        return this.name(s).value(arr_f);
    }

    public UBJsonWriter set(String s, int[] arr_v) throws IOException {
        return this.name(s).value(arr_v);
    }

    public UBJsonWriter set(String s, long[] arr_v) throws IOException {
        return this.name(s).value(arr_v);
    }

    public UBJsonWriter set(String s, String[] arr_s) throws IOException {
        return this.name(s).value(arr_s);
    }

    public UBJsonWriter set(String s, short[] arr_v) throws IOException {
        return this.name(s).value(arr_v);
    }

    public UBJsonWriter set(String s, boolean[] arr_z) throws IOException {
        return this.name(s).value(arr_z);
    }

    public UBJsonWriter value() throws IOException {
        this.checkName();
        this.out.writeByte(90);
        return this;
    }

    public UBJsonWriter value(byte b) throws IOException {
        this.checkName();
        this.out.writeByte(105);
        this.out.writeByte(((int)b));
        return this;
    }

    public UBJsonWriter value(char c) throws IOException {
        this.checkName();
        this.out.writeByte(73);
        this.out.writeChar(((int)c));
        return this;
    }

    public UBJsonWriter value(double f) throws IOException {
        this.checkName();
        this.out.writeByte(68);
        this.out.writeDouble(f);
        return this;
    }

    public UBJsonWriter value(float f) throws IOException {
        this.checkName();
        this.out.writeByte(100);
        this.out.writeFloat(f);
        return this;
    }

    public UBJsonWriter value(int v) throws IOException {
        this.checkName();
        this.out.writeByte(108);
        this.out.writeInt(v);
        return this;
    }

    public UBJsonWriter value(long v) throws IOException {
        this.checkName();
        this.out.writeByte(76);
        this.out.writeLong(v);
        return this;
    }

    public UBJsonWriter value(JsonValue jsonValue0) throws IOException {
        if(jsonValue0.isObject()) {
            if(jsonValue0.name == null) {
                this.object();
            }
            else {
                this.object(jsonValue0.name);
            }
            for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                this.value(jsonValue1);
            }
            this.pop();
            return this;
        }
        if(jsonValue0.isArray()) {
            if(jsonValue0.name == null) {
                this.array();
            }
            else {
                this.array(jsonValue0.name);
            }
            for(JsonValue jsonValue2 = jsonValue0.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                this.value(jsonValue2);
            }
            this.pop();
            return this;
        }
        if(jsonValue0.isBoolean()) {
            if(jsonValue0.name != null) {
                this.name(jsonValue0.name);
            }
            this.value(jsonValue0.asBoolean());
            return this;
        }
        if(jsonValue0.isDouble()) {
            if(jsonValue0.name != null) {
                this.name(jsonValue0.name);
            }
            this.value(jsonValue0.asDouble());
            return this;
        }
        if(jsonValue0.isLong()) {
            if(jsonValue0.name != null) {
                this.name(jsonValue0.name);
            }
            this.value(jsonValue0.asLong());
            return this;
        }
        if(jsonValue0.isString()) {
            if(jsonValue0.name != null) {
                this.name(jsonValue0.name);
            }
            this.value(jsonValue0.asString());
            return this;
        }
        if(!jsonValue0.isNull()) {
            throw new IOException("Unhandled JsonValue type");
        }
        if(jsonValue0.name != null) {
            this.name(jsonValue0.name);
        }
        this.value();
        return this;
    }

    public UBJsonWriter value(Object object0) throws IOException {
        if(object0 == null) {
            return this.value();
        }
        if(object0 instanceof Number) {
            if(object0 instanceof Byte) {
                return this.value(((Number)object0).byteValue());
            }
            if(object0 instanceof Short) {
                return this.value(((Number)object0).shortValue());
            }
            if(object0 instanceof Integer) {
                return this.value(((Number)object0).intValue());
            }
            if(object0 instanceof Long) {
                return this.value(((Number)object0).longValue());
            }
            if(object0 instanceof Float) {
                return this.value(((Number)object0).floatValue());
            }
            return object0 instanceof Double ? this.value(((Number)object0).doubleValue()) : this;
        }
        if(object0 instanceof Character) {
            return this.value(((Character)object0).charValue());
        }
        if(!(object0 instanceof CharSequence)) {
            throw new IOException("Unknown object type.");
        }
        return this.value(object0.toString());
    }

    public UBJsonWriter value(String s) throws IOException {
        this.checkName();
        byte[] arr_b = s.getBytes("UTF-8");
        this.out.writeByte(83);
        if(arr_b.length <= 0x7F) {
            this.out.writeByte(105);
            this.out.writeByte(arr_b.length);
        }
        else if(arr_b.length <= 0x7FFF) {
            this.out.writeByte(73);
            this.out.writeShort(arr_b.length);
        }
        else {
            this.out.writeByte(108);
            this.out.writeInt(arr_b.length);
        }
        this.out.write(arr_b);
        return this;
    }

    public UBJsonWriter value(short v) throws IOException {
        this.checkName();
        this.out.writeByte(73);
        this.out.writeShort(((int)v));
        return this;
    }

    public UBJsonWriter value(boolean z) throws IOException {
        this.checkName();
        this.out.writeByte((z ? 84 : 70));
        return this;
    }

    public UBJsonWriter value(byte[] arr_b) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(105);
        this.out.writeByte(35);
        this.value(arr_b.length);
        for(int v = 0; v < arr_b.length; ++v) {
            this.out.writeByte(((int)arr_b[v]));
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(char[] arr_c) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(67);
        this.out.writeByte(35);
        this.value(arr_c.length);
        for(int v = 0; v < arr_c.length; ++v) {
            this.out.writeChar(((int)arr_c[v]));
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(double[] arr_f) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(68);
        this.out.writeByte(35);
        this.value(arr_f.length);
        for(int v = 0; v < arr_f.length; ++v) {
            this.out.writeDouble(arr_f[v]);
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(float[] arr_f) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(100);
        this.out.writeByte(35);
        this.value(arr_f.length);
        for(int v = 0; v < arr_f.length; ++v) {
            this.out.writeFloat(arr_f[v]);
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(int[] arr_v) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(108);
        this.out.writeByte(35);
        this.value(arr_v.length);
        for(int v = 0; v < arr_v.length; ++v) {
            this.out.writeInt(arr_v[v]);
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(long[] arr_v) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(76);
        this.out.writeByte(35);
        this.value(arr_v.length);
        for(int v = 0; v < arr_v.length; ++v) {
            this.out.writeLong(arr_v[v]);
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(String[] arr_s) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(83);
        this.out.writeByte(35);
        this.value(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            byte[] arr_b = arr_s[v].getBytes("UTF-8");
            if(arr_b.length <= 0x7F) {
                this.out.writeByte(105);
                this.out.writeByte(arr_b.length);
            }
            else if(arr_b.length <= 0x7FFF) {
                this.out.writeByte(73);
                this.out.writeShort(arr_b.length);
            }
            else {
                this.out.writeByte(108);
                this.out.writeInt(arr_b.length);
            }
            this.out.write(arr_b);
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(short[] arr_v) throws IOException {
        this.array();
        this.out.writeByte(36);
        this.out.writeByte(73);
        this.out.writeByte(35);
        this.value(arr_v.length);
        for(int v = 0; v < arr_v.length; ++v) {
            this.out.writeShort(((int)arr_v[v]));
        }
        this.pop(true);
        return this;
    }

    public UBJsonWriter value(boolean[] arr_z) throws IOException {
        this.array();
        for(int v = 0; v < arr_z.length; ++v) {
            this.out.writeByte((arr_z[v] ? 84 : 70));
        }
        this.pop();
        return this;
    }
}

