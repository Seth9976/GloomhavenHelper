package com.badlogic.gdx.utils;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class JsonValue implements Iterable {
    public class JsonIterator implements Iterable, Iterator {
        JsonValue current;
        JsonValue entry;

        public JsonIterator() {
            this.entry = JsonValue.this.child;
        }

        @Override
        public boolean hasNext() {
            return this.entry != null;
        }

        @Override
        public Iterator iterator() {
            return this;
        }

        public JsonValue next() {
            this.current = this.entry;
            JsonValue jsonValue0 = this.current;
            if(jsonValue0 == null) {
                throw new NoSuchElementException();
            }
            this.entry = jsonValue0.next;
            return this.current;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override
        public void remove() {
            if(this.current.prev == null) {
                JsonValue.this.child = this.current.next;
                if(JsonValue.this.child != null) {
                    JsonValue.this.child.prev = null;
                }
            }
            else {
                this.current.prev.next = this.current.next;
                if(this.current.next != null) {
                    this.current.next.prev = this.current.prev;
                }
            }
            --JsonValue.this.size;
        }
    }

    public static class PrettyPrintSettings {
        public OutputType outputType;
        public int singleLineColumns;
        public boolean wrapNumericArrays;

    }

    public static enum ValueType {
        object,
        array,
        stringValue,
        doubleValue,
        longValue,
        booleanValue,
        nullValue;

    }

    public JsonValue child;
    private double doubleValue;
    private long longValue;
    public String name;
    public JsonValue next;
    public JsonValue parent;
    public JsonValue prev;
    public int size;
    private String stringValue;
    private ValueType type;

    public JsonValue(double f) {
        this.set(f, null);
    }

    public JsonValue(double f, String s) {
        this.set(f, s);
    }

    public JsonValue(long v) {
        this.set(v, null);
    }

    public JsonValue(long v, String s) {
        this.set(v, s);
    }

    public JsonValue(ValueType jsonValue$ValueType0) {
        this.type = jsonValue$ValueType0;
    }

    public JsonValue(@Null String s) {
        this.set(s);
    }

    public JsonValue(boolean z) {
        this.set(z);
    }

    public void addChild(JsonValue jsonValue0) {
        jsonValue0.parent = this;
        ++this.size;
        JsonValue jsonValue1 = this.child;
        if(jsonValue1 == null) {
            this.child = jsonValue0;
            return;
        }
        JsonValue jsonValue2;
        while((jsonValue2 = jsonValue1.next) != null) {
            jsonValue1 = jsonValue2;
        }
        jsonValue1.next = jsonValue0;
        jsonValue0.prev = jsonValue1;
    }

    public void addChild(String s, JsonValue jsonValue0) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        jsonValue0.name = s;
        this.addChild(jsonValue0);
    }

    public boolean asBoolean() {
        switch(this.type) {
            case stringValue: {
                return this.stringValue.equalsIgnoreCase("true");
            }
            case doubleValue: {
                return this.doubleValue != 0.0;
            }
            case longValue: {
                return this.longValue != 0L;
            }
            case booleanValue: {
                return this.longValue != 0L;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to boolean: " + this.type);
            }
        }
    }

    public boolean[] asBooleanArray() {
        boolean z;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        boolean[] arr_z = new boolean[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    z = Boolean.parseBoolean(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    z = jsonValue0.doubleValue == 0.0;
                    break;
                }
                case 3: {
                    z = jsonValue0.longValue == 0L;
                    break;
                }
                case 4: {
                    z = jsonValue0.longValue != 0L;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to boolean: " + jsonValue0.type);
                }
            }
            arr_z[v] = z;
            jsonValue0 = jsonValue0.next;
        }
        return arr_z;
    }

    public byte asByte() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Byte.parseByte(this.stringValue);
            }
            case 2: {
                return (byte)(((int)this.doubleValue));
            }
            case 3: {
                return (byte)(((int)this.longValue));
            }
            case 4: {
                return this.longValue == 0L ? 0 : 1;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to byte: " + this.type);
            }
        }
    }

    public byte[] asByteArray() {
        byte b;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        byte[] arr_b = new byte[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    b = Byte.parseByte(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    b = (byte)(((int)jsonValue0.doubleValue));
                    break;
                }
                case 3: {
                    b = (byte)(((int)jsonValue0.longValue));
                    break;
                }
                case 4: {
                    b = jsonValue0.longValue == 0L ? 0 : 1;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to byte: " + jsonValue0.type);
                }
            }
            arr_b[v] = b;
            jsonValue0 = jsonValue0.next;
        }
        return arr_b;
    }

    public char asChar() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return this.stringValue.length() == 0 ? '\u0000' : this.stringValue.charAt(0);
            }
            case 2: {
                return (char)(((int)this.doubleValue));
            }
            case 3: {
                return (char)(((int)this.longValue));
            }
            case 4: {
                return this.longValue == 0L ? '\u0000' : '\u0001';
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to char: " + this.type);
            }
        }
    }

    public char[] asCharArray() {
        char c;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        char[] arr_c = new char[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    c = jsonValue0.stringValue.length() == 0 ? '\u0000' : jsonValue0.stringValue.charAt(0);
                    break;
                }
                case 2: {
                    c = (char)(((int)jsonValue0.doubleValue));
                    break;
                }
                case 3: {
                    c = (char)(((int)jsonValue0.longValue));
                    break;
                }
                case 4: {
                    c = jsonValue0.longValue == 0L ? '\u0000' : '\u0001';
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to char: " + jsonValue0.type);
                }
            }
            arr_c[v] = c;
            jsonValue0 = jsonValue0.next;
        }
        return arr_c;
    }

    public double asDouble() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Double.parseDouble(this.stringValue);
            }
            case 2: {
                return this.doubleValue;
            }
            case 3: {
                return (double)this.longValue;
            }
            case 4: {
                return this.longValue == 0L ? 0.0 : 1.0;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to double: " + this.type);
            }
        }
    }

    public double[] asDoubleArray() {
        double f;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        double[] arr_f = new double[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    f = Double.parseDouble(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    f = jsonValue0.doubleValue;
                    break;
                }
                case 3: {
                    f = (double)jsonValue0.longValue;
                    break;
                }
                case 4: {
                    f = jsonValue0.longValue == 0L ? 0.0 : 1.0;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to double: " + jsonValue0.type);
                }
            }
            arr_f[v] = f;
            jsonValue0 = jsonValue0.next;
        }
        return arr_f;
    }

    public float asFloat() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Float.parseFloat(this.stringValue);
            }
            case 2: {
                return (float)this.doubleValue;
            }
            case 3: {
                return (float)this.longValue;
            }
            case 4: {
                return this.longValue == 0L ? 0.0f : 1.0f;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to float: " + this.type);
            }
        }
    }

    public float[] asFloatArray() {
        float f;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        float[] arr_f = new float[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    f = Float.parseFloat(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    f = (float)jsonValue0.doubleValue;
                    break;
                }
                case 3: {
                    f = (float)jsonValue0.longValue;
                    break;
                }
                case 4: {
                    f = jsonValue0.longValue == 0L ? 0.0f : 1.0f;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to float: " + jsonValue0.type);
                }
            }
            arr_f[v] = f;
            jsonValue0 = jsonValue0.next;
        }
        return arr_f;
    }

    public int asInt() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Integer.parseInt(this.stringValue);
            }
            case 2: {
                return (int)this.doubleValue;
            }
            case 3: {
                return (int)this.longValue;
            }
            case 4: {
                return this.longValue == 0L ? 0 : 1;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to int: " + this.type);
            }
        }
    }

    public int[] asIntArray() {
        int v1;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        int[] arr_v = new int[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    v1 = Integer.parseInt(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    v1 = (int)jsonValue0.doubleValue;
                    break;
                }
                case 3: {
                    v1 = (int)jsonValue0.longValue;
                    break;
                }
                case 4: {
                    v1 = jsonValue0.longValue == 0L ? 0 : 1;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to int: " + jsonValue0.type);
                }
            }
            arr_v[v] = v1;
            jsonValue0 = jsonValue0.next;
        }
        return arr_v;
    }

    public long asLong() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Long.parseLong(this.stringValue);
            }
            case 2: {
                return (long)this.doubleValue;
            }
            case 3: {
                return this.longValue;
            }
            case 4: {
                return this.longValue == 0L ? 0L : 1L;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to long: " + this.type);
            }
        }
    }

    public long[] asLongArray() {
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        long[] arr_v = new long[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            long v1 = 0L;
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    v1 = Long.parseLong(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    v1 = (long)jsonValue0.doubleValue;
                    break;
                }
                case 3: {
                    v1 = jsonValue0.longValue;
                    break;
                }
                case 4: {
                    if(jsonValue0.longValue != 0L) {
                        v1 = 1L;
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to long: " + jsonValue0.type);
                }
            }
            arr_v[v] = v1;
            jsonValue0 = jsonValue0.next;
        }
        return arr_v;
    }

    public short asShort() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return Short.parseShort(this.stringValue);
            }
            case 2: {
                return (short)(((int)this.doubleValue));
            }
            case 3: {
                return (short)(((int)this.longValue));
            }
            case 4: {
                return this.longValue == 0L ? 0 : 1;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to short: " + this.type);
            }
        }
    }

    public short[] asShortArray() {
        short v1;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        short[] arr_v = new short[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    v1 = Short.parseShort(jsonValue0.stringValue);
                    break;
                }
                case 2: {
                    v1 = (short)(((int)jsonValue0.doubleValue));
                    break;
                }
                case 3: {
                    v1 = (short)(((int)jsonValue0.longValue));
                    break;
                }
                case 4: {
                    v1 = jsonValue0.longValue == 0L ? 0 : 1;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to short: " + jsonValue0.type);
                }
            }
            arr_v[v] = v1;
            jsonValue0 = jsonValue0.next;
        }
        return arr_v;
    }

    @Null
    public String asString() {
        switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[this.type.ordinal()]) {
            case 1: {
                return this.stringValue;
            }
            case 2: {
                return this.stringValue == null ? Double.toString(this.doubleValue) : this.stringValue;
            }
            case 3: {
                return this.stringValue == null ? Long.toString(this.longValue) : this.stringValue;
            }
            case 4: {
                return this.longValue == 0L ? "false" : "true";
            }
            case 5: {
                return null;
            }
            default: {
                throw new IllegalStateException("Value cannot be converted to string: " + this.type);
            }
        }
    }

    public String[] asStringArray() {
        String s;
        if(this.type != ValueType.array) {
            throw new IllegalStateException("Value is not an array: " + this.type);
        }
        String[] arr_s = new String[this.size];
        JsonValue jsonValue0 = this.child;
        for(int v = 0; jsonValue0 != null; ++v) {
            switch(com.badlogic.gdx.utils.JsonValue.1.$SwitchMap$com$badlogic$gdx$utils$JsonValue$ValueType[jsonValue0.type.ordinal()]) {
                case 1: {
                    s = jsonValue0.stringValue;
                    break;
                }
                case 2: {
                    String s1 = this.stringValue;
                    s = s1 == null ? Double.toString(jsonValue0.doubleValue) : s1;
                    break;
                }
                case 3: {
                    String s2 = this.stringValue;
                    s = s2 == null ? Long.toString(jsonValue0.longValue) : s2;
                    break;
                }
                case 4: {
                    s = jsonValue0.longValue == 0L ? "false" : "true";
                    break;
                }
                case 5: {
                    s = null;
                    break;
                }
                default: {
                    throw new IllegalStateException("Value cannot be converted to string: " + jsonValue0.type);
                }
            }
            arr_s[v] = s;
            jsonValue0 = jsonValue0.next;
        }
        return arr_s;
    }

    @Null
    public JsonValue child() {
        return this.child;
    }

    @Null
    public JsonValue get(int v) {
        JsonValue jsonValue0;
        for(jsonValue0 = this.child; jsonValue0 != null && v > 0; jsonValue0 = jsonValue0.next) {
            --v;
        }
        return jsonValue0;
    }

    @Null
    public JsonValue get(String s) {
        JsonValue jsonValue0;
        for(jsonValue0 = this.child; jsonValue0 != null && (jsonValue0.name == null || !jsonValue0.name.equalsIgnoreCase(s)); jsonValue0 = jsonValue0.next) {
        }
        return jsonValue0;
    }

    public boolean getBoolean(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asBoolean();
    }

    public boolean getBoolean(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asBoolean();
    }

    public boolean getBoolean(String s, boolean z) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? z : jsonValue0.asBoolean();
    }

    public byte getByte(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asByte();
    }

    public byte getByte(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asByte();
    }

    public byte getByte(String s, byte b) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? b : jsonValue0.asByte();
    }

    public char getChar(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asChar();
    }

    public char getChar(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asChar();
    }

    public char getChar(String s, char c) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? c : jsonValue0.asChar();
    }

    @Null
    public JsonValue getChild(String s) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null ? null : jsonValue0.child;
    }

    public double getDouble(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asDouble();
    }

    public double getDouble(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asDouble();
    }

    public double getDouble(String s, double f) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? f : jsonValue0.asDouble();
    }

    public float getFloat(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asFloat();
    }

    public float getFloat(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asFloat();
    }

    public float getFloat(String s, float f) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? f : jsonValue0.asFloat();
    }

    public int getInt(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asInt();
    }

    public int getInt(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asInt();
    }

    public int getInt(String s, int v) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? v : jsonValue0.asInt();
    }

    public long getLong(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asLong();
    }

    public long getLong(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asLong();
    }

    public long getLong(String s, long v) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? v : jsonValue0.asLong();
    }

    public short getShort(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asShort();
    }

    public short getShort(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asShort();
    }

    public short getShort(String s, short v) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? v : jsonValue0.asShort();
    }

    public String getString(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Indexed value not found: " + this.name);
        }
        return jsonValue0.asString();
    }

    public String getString(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Named value not found: " + s);
        }
        return jsonValue0.asString();
    }

    public String getString(String s, @Null String s1) {
        JsonValue jsonValue0 = this.get(s);
        return jsonValue0 == null || !jsonValue0.isValue() || jsonValue0.isNull() ? s1 : jsonValue0.asString();
    }

    public boolean has(String s) {
        return this.get(s) != null;
    }

    public boolean hasChild(String s) {
        return this.getChild(s) != null;
    }

    private static void indent(int v, StringBuilder stringBuilder0) {
        for(int v1 = 0; v1 < v; ++v1) {
            stringBuilder0.append('\t');
        }
    }

    private static void indent(int v, Writer writer0) throws IOException {
        for(int v1 = 0; v1 < v; ++v1) {
            writer0.append('\t');
        }
    }

    public boolean isArray() {
        return this.type == ValueType.array;
    }

    public boolean isBoolean() {
        return this.type == ValueType.booleanValue;
    }

    public boolean isDouble() {
        return this.type == ValueType.doubleValue;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private static boolean isFlat(JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.child;
        while(jsonValue1 != null) {
            if(!jsonValue1.isObject() && !jsonValue1.isArray()) {
                jsonValue1 = jsonValue1.next;
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean isLong() {
        return this.type == ValueType.longValue;
    }

    public boolean isNull() {
        return this.type == ValueType.nullValue;
    }

    public boolean isNumber() {
        return this.type == ValueType.doubleValue || this.type == ValueType.longValue;
    }

    private static boolean isNumeric(JsonValue jsonValue0) {
        for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
            if(!jsonValue1.isNumber()) {
                return false;
            }
        }
        return true;
    }

    public boolean isObject() {
        return this.type == ValueType.object;
    }

    public boolean isString() {
        return this.type == ValueType.stringValue;
    }

    public boolean isValue() {
        switch(this.type) {
            case stringValue: 
            case doubleValue: 
            case longValue: 
            case booleanValue: 
            case nullValue: {
                return true;
            }
            default: {
                return false;
            }
        }
    }

    public JsonIterator iterator() {
        return new JsonIterator(this);
    }

    @Override
    public Iterator iterator() {
        return this.iterator();
    }

    private void json(JsonValue jsonValue0, StringBuilder stringBuilder0, OutputType jsonWriter$OutputType0) {
        if(jsonValue0.isObject()) {
            if(jsonValue0.child == null) {
                stringBuilder0.append("{}");
                return;
            }
            stringBuilder0.append('{');
            for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                stringBuilder0.append(jsonWriter$OutputType0.quoteName(jsonValue1.name));
                stringBuilder0.append(':');
                this.json(jsonValue1, stringBuilder0, jsonWriter$OutputType0);
                if(jsonValue1.next != null) {
                    stringBuilder0.append(',');
                }
            }
            stringBuilder0.append('}');
            return;
        }
        if(jsonValue0.isArray()) {
            if(jsonValue0.child == null) {
                stringBuilder0.append("[]");
                return;
            }
            stringBuilder0.append('[');
            for(JsonValue jsonValue2 = jsonValue0.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                this.json(jsonValue2, stringBuilder0, jsonWriter$OutputType0);
                if(jsonValue2.next != null) {
                    stringBuilder0.append(',');
                }
            }
            stringBuilder0.append(']');
            return;
        }
        if(jsonValue0.isString()) {
            stringBuilder0.append(jsonWriter$OutputType0.quoteValue(jsonValue0.asString()));
            return;
        }
        if(jsonValue0.isDouble()) {
            double f = jsonValue0.asDouble();
            double f1 = (double)jsonValue0.asLong();
            if(f == f1) {
                f = f1;
            }
            stringBuilder0.append(f);
            return;
        }
        if(jsonValue0.isLong()) {
            stringBuilder0.append(jsonValue0.asLong());
            return;
        }
        if(jsonValue0.isBoolean()) {
            stringBuilder0.append(jsonValue0.asBoolean());
            return;
        }
        if(!jsonValue0.isNull()) {
            throw new SerializationException("Unknown object type: " + jsonValue0);
        }
        stringBuilder0.append("null");
    }

    @Null
    public String name() {
        return this.name;
    }

    @Null
    public JsonValue next() {
        return this.next;
    }

    public boolean notEmpty() {
        return this.size > 0;
    }

    @Null
    public JsonValue parent() {
        return this.parent;
    }

    private void prettyPrint(JsonValue jsonValue0, StringBuilder stringBuilder0, int v, PrettyPrintSettings jsonValue$PrettyPrintSettings0) {
        OutputType jsonWriter$OutputType0 = jsonValue$PrettyPrintSettings0.outputType;
        if(jsonValue0.isObject()) {
            if(jsonValue0.child == null) {
                stringBuilder0.append("{}");
                return;
            }
            int v1 = !JsonValue.isFlat(jsonValue0);
            int v2 = stringBuilder0.length();
        alab1:
            while(true) {
                stringBuilder0.append((v1 == 0 ? "{ " : "{\n"));
                for(JsonValue jsonValue1 = jsonValue0.child; true; jsonValue1 = jsonValue1.next) {
                    if(jsonValue1 == null) {
                        break alab1;
                    }
                    if(v1 != 0) {
                        JsonValue.indent(v, stringBuilder0);
                    }
                    stringBuilder0.append(jsonWriter$OutputType0.quoteName(jsonValue1.name));
                    stringBuilder0.append(": ");
                    this.prettyPrint(jsonValue1, stringBuilder0, v + 1, jsonValue$PrettyPrintSettings0);
                    if((v1 == 0 || jsonWriter$OutputType0 != OutputType.minimal) && jsonValue1.next != null) {
                        stringBuilder0.append(',');
                    }
                    stringBuilder0.append(((char)(v1 == 0 ? 0x20 : 10)));
                    if(v1 == 0 && stringBuilder0.length() - v2 > jsonValue$PrettyPrintSettings0.singleLineColumns) {
                        stringBuilder0.setLength(v2);
                        v1 = 1;
                        break;
                    }
                }
            }
            if(v1 != 0) {
                JsonValue.indent(v - 1, stringBuilder0);
            }
            stringBuilder0.append('}');
            return;
        }
        if(jsonValue0.isArray()) {
            if(jsonValue0.child == null) {
                stringBuilder0.append("[]");
                return;
            }
            int v3 = !JsonValue.isFlat(jsonValue0);
            boolean z = jsonValue$PrettyPrintSettings0.wrapNumericArrays || !JsonValue.isNumeric(jsonValue0);
            int v4 = stringBuilder0.length();
            while(true) {
                stringBuilder0.append((v3 == 0 ? "[ " : "[\n"));
                JsonValue jsonValue2 = jsonValue0.child;
            label_37:
                if(jsonValue2 == null) {
                    break;
                }
                if(v3 != 0) {
                    JsonValue.indent(v, stringBuilder0);
                }
                this.prettyPrint(jsonValue2, stringBuilder0, v + 1, jsonValue$PrettyPrintSettings0);
                if((v3 == 0 || jsonWriter$OutputType0 != OutputType.minimal) && jsonValue2.next != null) {
                    stringBuilder0.append(',');
                }
                stringBuilder0.append(((char)(v3 == 0 ? 0x20 : 10)));
                if(z && v3 == 0 && stringBuilder0.length() - v4 > jsonValue$PrettyPrintSettings0.singleLineColumns) {
                    stringBuilder0.setLength(v4);
                    v3 = 1;
                    continue;
                }
                jsonValue2 = jsonValue2.next;
                goto label_37;
            }
            if(v3 != 0) {
                JsonValue.indent(v - 1, stringBuilder0);
            }
            stringBuilder0.append(']');
            return;
        }
        if(jsonValue0.isString()) {
            stringBuilder0.append(jsonWriter$OutputType0.quoteValue(jsonValue0.asString()));
            return;
        }
        if(jsonValue0.isDouble()) {
            double f = jsonValue0.asDouble();
            double f1 = (double)jsonValue0.asLong();
            if(f == f1) {
                f = f1;
            }
            stringBuilder0.append(f);
            return;
        }
        if(jsonValue0.isLong()) {
            stringBuilder0.append(jsonValue0.asLong());
            return;
        }
        if(jsonValue0.isBoolean()) {
            stringBuilder0.append(jsonValue0.asBoolean());
            return;
        }
        if(!jsonValue0.isNull()) {
            throw new SerializationException("Unknown object type: " + jsonValue0);
        }
        stringBuilder0.append("null");
    }

    private void prettyPrint(JsonValue jsonValue0, Writer writer0, int v, PrettyPrintSettings jsonValue$PrettyPrintSettings0) throws IOException {
        OutputType jsonWriter$OutputType0 = jsonValue$PrettyPrintSettings0.outputType;
        if(jsonValue0.isObject()) {
            if(jsonValue0.child == null) {
                writer0.append("{}");
                return;
            }
            boolean z = !JsonValue.isFlat(jsonValue0) || jsonValue0.size > 6;
            writer0.append((z ? "{\n" : "{ "));
            for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                if(z) {
                    JsonValue.indent(v, writer0);
                }
                writer0.append(jsonWriter$OutputType0.quoteName(jsonValue1.name));
                writer0.append(": ");
                this.prettyPrint(jsonValue1, writer0, v + 1, jsonValue$PrettyPrintSettings0);
                if((!z || jsonWriter$OutputType0 != OutputType.minimal) && jsonValue1.next != null) {
                    writer0.append(',');
                }
                writer0.append(((char)(z ? 10 : 0x20)));
            }
            if(z) {
                JsonValue.indent(v - 1, writer0);
            }
            writer0.append('}');
            return;
        }
        if(jsonValue0.isArray()) {
            if(jsonValue0.child == null) {
                writer0.append("[]");
                return;
            }
            boolean z1 = JsonValue.isFlat(jsonValue0);
            writer0.append((!z1 == 0 ? "[ " : "[\n"));
            for(JsonValue jsonValue2 = jsonValue0.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                if(!z1 != 0) {
                    JsonValue.indent(v, writer0);
                }
                this.prettyPrint(jsonValue2, writer0, v + 1, jsonValue$PrettyPrintSettings0);
                if((!z1 == 0 || jsonWriter$OutputType0 != OutputType.minimal) && jsonValue2.next != null) {
                    writer0.append(',');
                }
                writer0.append(((char)(!z1 == 0 ? 0x20 : 10)));
            }
            if(!z1 != 0) {
                JsonValue.indent(v - 1, writer0);
            }
            writer0.append(']');
            return;
        }
        if(jsonValue0.isString()) {
            writer0.append(jsonWriter$OutputType0.quoteValue(jsonValue0.asString()));
            return;
        }
        if(jsonValue0.isDouble()) {
            double f = jsonValue0.asDouble();
            double f1 = (double)jsonValue0.asLong();
            if(f == f1) {
                f = f1;
            }
            writer0.append(Double.toString(f));
            return;
        }
        if(jsonValue0.isLong()) {
            writer0.append(Long.toString(jsonValue0.asLong()));
            return;
        }
        if(jsonValue0.isBoolean()) {
            writer0.append(Boolean.toString(jsonValue0.asBoolean()));
            return;
        }
        if(!jsonValue0.isNull()) {
            throw new SerializationException("Unknown object type: " + jsonValue0);
        }
        writer0.append("null");
    }

    public String prettyPrint(PrettyPrintSettings jsonValue$PrettyPrintSettings0) {
        this.prettyPrint(this, new StringBuilder(0x200), 0, jsonValue$PrettyPrintSettings0);
        return "";
    }

    public String prettyPrint(OutputType jsonWriter$OutputType0, int v) {
        PrettyPrintSettings jsonValue$PrettyPrintSettings0 = new PrettyPrintSettings();
        jsonValue$PrettyPrintSettings0.outputType = jsonWriter$OutputType0;
        jsonValue$PrettyPrintSettings0.singleLineColumns = v;
        return this.prettyPrint(jsonValue$PrettyPrintSettings0);
    }

    public void prettyPrint(OutputType jsonWriter$OutputType0, Writer writer0) throws IOException {
        PrettyPrintSettings jsonValue$PrettyPrintSettings0 = new PrettyPrintSettings();
        jsonValue$PrettyPrintSettings0.outputType = jsonWriter$OutputType0;
        this.prettyPrint(this, writer0, 0, jsonValue$PrettyPrintSettings0);
    }

    @Null
    public JsonValue prev() {
        return this.prev;
    }

    @Null
    public JsonValue remove(int v) {
        JsonValue jsonValue0 = this.get(v);
        if(jsonValue0 == null) {
            return null;
        }
        JsonValue jsonValue1 = jsonValue0.prev;
        if(jsonValue1 == null) {
            this.child = jsonValue0.next;
            JsonValue jsonValue2 = this.child;
            if(jsonValue2 != null) {
                jsonValue2.prev = null;
            }
        }
        else {
            jsonValue1.next = jsonValue0.next;
            JsonValue jsonValue3 = jsonValue0.next;
            if(jsonValue3 != null) {
                jsonValue3.prev = jsonValue1;
            }
        }
        --this.size;
        return jsonValue0;
    }

    @Null
    public JsonValue remove(String s) {
        JsonValue jsonValue0 = this.get(s);
        if(jsonValue0 == null) {
            return null;
        }
        JsonValue jsonValue1 = jsonValue0.prev;
        if(jsonValue1 == null) {
            this.child = jsonValue0.next;
            JsonValue jsonValue2 = this.child;
            if(jsonValue2 != null) {
                jsonValue2.prev = null;
            }
        }
        else {
            jsonValue1.next = jsonValue0.next;
            JsonValue jsonValue3 = jsonValue0.next;
            if(jsonValue3 != null) {
                jsonValue3.prev = jsonValue1;
            }
        }
        --this.size;
        return jsonValue0;
    }

    public void remove() {
        JsonValue jsonValue0 = this.parent;
        if(jsonValue0 == null) {
            throw new IllegalStateException();
        }
        JsonValue jsonValue1 = this.prev;
        if(jsonValue1 == null) {
            jsonValue0.child = this.next;
            JsonValue jsonValue2 = jsonValue0.child;
            if(jsonValue2 != null) {
                jsonValue2.prev = null;
            }
        }
        else {
            jsonValue1.next = this.next;
            JsonValue jsonValue3 = this.next;
            if(jsonValue3 != null) {
                jsonValue3.prev = jsonValue1;
            }
        }
        --this.parent.size;
    }

    public JsonValue require(int v) {
        JsonValue jsonValue0;
        for(jsonValue0 = this.child; jsonValue0 != null && v > 0; jsonValue0 = jsonValue0.next) {
            --v;
        }
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Child not found with index: " + v);
        }
        return jsonValue0;
    }

    public JsonValue require(String s) {
        JsonValue jsonValue0;
        for(jsonValue0 = this.child; jsonValue0 != null && (jsonValue0.name == null || !jsonValue0.name.equalsIgnoreCase(s)); jsonValue0 = jsonValue0.next) {
        }
        if(jsonValue0 == null) {
            throw new IllegalArgumentException("Child not found with name: " + s);
        }
        return jsonValue0;
    }

    public void set(double f, @Null String s) {
        this.doubleValue = f;
        this.longValue = (long)f;
        this.stringValue = s;
        this.type = ValueType.doubleValue;
    }

    public void set(long v, @Null String s) {
        this.longValue = v;
        this.doubleValue = (double)v;
        this.stringValue = s;
        this.type = ValueType.longValue;
    }

    public void set(@Null String s) {
        this.stringValue = s;
        this.type = s == null ? ValueType.nullValue : ValueType.stringValue;
    }

    public void set(boolean z) {
        this.longValue = z ? 1L : 0L;
        this.type = ValueType.booleanValue;
    }

    public void setName(@Null String s) {
        this.name = s;
    }

    public void setNext(@Null JsonValue jsonValue0) {
        this.next = jsonValue0;
    }

    public void setPrev(@Null JsonValue jsonValue0) {
        this.prev = jsonValue0;
    }

    public void setType(ValueType jsonValue$ValueType0) {
        if(jsonValue$ValueType0 == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        this.type = jsonValue$ValueType0;
    }

    @Deprecated
    public int size() {
        return this.size;
    }

    public String toJson(OutputType jsonWriter$OutputType0) {
        if(this.isValue()) {
            return this.asString();
        }
        this.json(this, new StringBuilder(0x200), jsonWriter$OutputType0);
        return "";
    }

    @Override
    public String toString() {
        if(this.isValue()) {
            return this.name == null ? this.asString() : this.name + ": " + this.asString();
        }
        return (this.name == null ? "" : this.name + ": ") + this.prettyPrint(OutputType.minimal, 0);
    }

    public String trace() {
        String s;
        JsonValue jsonValue0 = this.parent;
        if(jsonValue0 == null) {
            if(this.type == ValueType.array) {
                return "[]";
            }
            return this.type == ValueType.object ? "{}" : "";
        }
        if(jsonValue0.type == ValueType.array) {
            JsonValue jsonValue1 = this.parent.child;
            for(int v = 0; jsonValue1 != null; ++v) {
                if(jsonValue1 == this) {
                    return this.parent.trace() + ("[" + v + "]");
                }
                jsonValue1 = jsonValue1.next;
            }
            return this.parent.trace() + "[]";
        }
        if(this.name.indexOf(46) != -1) {
            s = ".\"" + this.name.replace("\"", "\\\"") + "\"";
            return this.parent.trace() + s;
        }
        s = '.' + this.name;
        return this.parent.trace() + s;
    }

    public ValueType type() {
        return this.type;
    }
}

