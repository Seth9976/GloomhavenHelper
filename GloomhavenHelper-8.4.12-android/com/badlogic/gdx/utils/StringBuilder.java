package com.badlogic.gdx.utils;

import java.io.IOException;
import java.util.Arrays;

public class StringBuilder implements Appendable, CharSequence {
    static final int INITIAL_CAPACITY = 16;
    public char[] chars;
    private static final char[] digits;
    public int length;

    static {
        StringBuilder.digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    }

    public StringBuilder() {
        this.chars = new char[16];
    }

    public StringBuilder(int v) {
        if(v < 0) {
            throw new NegativeArraySizeException();
        }
        this.chars = new char[v];
    }

    public StringBuilder(StringBuilder stringBuilder0) {
        this.length = stringBuilder0.length;
        int v = this.length;
        this.chars = new char[v + 16];
        System.arraycopy(stringBuilder0.chars, 0, this.chars, 0, v);
    }

    public StringBuilder(CharSequence charSequence0) {
        this(charSequence0.toString());
    }

    public StringBuilder(String s) {
        this.length = s.length();
        this.chars = new char[this.length + 16];
    }

    public StringBuilder append(char c) {
        this.append0(c);
        return this;
    }

    public StringBuilder append(double f) {
        this.append0(Double.toString(f));
        return this;
    }

    public StringBuilder append(float f) {
        this.append0(Float.toString(f));
        return this;
    }

    public StringBuilder append(int v) {
        return this.append(v, 0);
    }

    public StringBuilder append(int v, int v1) {
        return this.append(v, v1, '0');
    }

    public StringBuilder append(int v, int v1, char c) {
        if(v == 0x80000000) {
            this.append0("-2147483648");
            return this;
        }
        if(v < 0) {
            this.append0('-');
            v = -v;
        }
        if(v1 > 1) {
            for(int v2 = v1 - StringBuilder.numChars(v, 10); v2 > 0; --v2) {
                this.append(c);
            }
        }
        if(v >= 10000) {
            if(v >= 1000000000) {
                this.append0(StringBuilder.digits[((int)(((long)v) % 10000000000L / 1000000000L))]);
            }
            if(v >= 100000000) {
                this.append0(StringBuilder.digits[v % 1000000000 / 100000000]);
            }
            if(v >= 10000000) {
                this.append0(StringBuilder.digits[v % 100000000 / 10000000]);
            }
            if(v >= 1000000) {
                this.append0(StringBuilder.digits[v % 10000000 / 1000000]);
            }
            if(v >= 100000) {
                this.append0(StringBuilder.digits[v % 1000000 / 100000]);
            }
            this.append0(StringBuilder.digits[v % 100000 / 10000]);
        }
        if(v >= 1000) {
            this.append0(StringBuilder.digits[v % 10000 / 1000]);
        }
        if(v >= 100) {
            this.append0(StringBuilder.digits[v % 1000 / 100]);
        }
        if(v >= 10) {
            this.append0(StringBuilder.digits[v % 100 / 10]);
        }
        this.append0(StringBuilder.digits[v % 10]);
        return this;
    }

    public StringBuilder append(long v) {
        return this.append(v, 0);
    }

    public StringBuilder append(long v, int v1) {
        return this.append(v, v1, '0');
    }

    public StringBuilder append(long v, int v1, char c) {
        if(v == 0x8000000000000000L) {
            this.append0("-9223372036854775808");
            return this;
        }
        if(v < 0L) {
            this.append0('-');
            v = -v;
        }
        if(v1 > 1) {
            for(int v2 = v1 - StringBuilder.numChars(v, 10); v2 > 0; --v2) {
                this.append(c);
            }
        }
        if(v >= 10000L) {
            if(v >= 1000000000000000000L) {
                this.append0(StringBuilder.digits[((int)(((double)v) % 10000000000000000000.0 / 1000000000000000000.0))]);
            }
            if(v >= 100000000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 1000000000000000000L / 100000000000000000L))]);
            }
            if(v >= 10000000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 100000000000000000L / 10000000000000000L))]);
            }
            if(v >= 1000000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 10000000000000000L / 1000000000000000L))]);
            }
            if(v >= 100000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 1000000000000000L / 100000000000000L))]);
            }
            if(v >= 10000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 100000000000000L / 10000000000000L))]);
            }
            if(v >= 1000000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 10000000000000L / 1000000000000L))]);
            }
            if(v >= 100000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 1000000000000L / 100000000000L))]);
            }
            if(v >= 10000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 100000000000L / 10000000000L))]);
            }
            if(v >= 1000000000L) {
                this.append0(StringBuilder.digits[((int)(v % 10000000000L / 1000000000L))]);
            }
            if(v >= 100000000L) {
                this.append0(StringBuilder.digits[((int)(v % 1000000000L / 100000000L))]);
            }
            if(v >= 10000000L) {
                this.append0(StringBuilder.digits[((int)(v % 100000000L / 10000000L))]);
            }
            if(v >= 1000000L) {
                this.append0(StringBuilder.digits[((int)(v % 10000000L / 1000000L))]);
            }
            if(v >= 100000L) {
                this.append0(StringBuilder.digits[((int)(v % 1000000L / 100000L))]);
            }
            this.append0(StringBuilder.digits[((int)(v % 100000L / 10000L))]);
        }
        if(v >= 1000L) {
            this.append0(StringBuilder.digits[((int)(v % 10000L / 1000L))]);
        }
        if(v >= 100L) {
            this.append0(StringBuilder.digits[((int)(v % 1000L / 100L))]);
        }
        if(v >= 10L) {
            this.append0(StringBuilder.digits[((int)(v % 100L / 10L))]);
        }
        this.append0(StringBuilder.digits[((int)(v % 10L))]);
        return this;
    }

    public StringBuilder append(StringBuilder stringBuilder0) {
        if(stringBuilder0 == null) {
            this.appendNull();
            return this;
        }
        this.append0(stringBuilder0.chars, 0, stringBuilder0.length);
        return this;
    }

    public StringBuilder append(StringBuilder stringBuilder0, int v, int v1) {
        if(stringBuilder0 == null) {
            this.appendNull();
            return this;
        }
        this.append0(stringBuilder0.chars, v, v1);
        return this;
    }

    public StringBuilder append(CharSequence charSequence0) {
        if(charSequence0 == null) {
            this.appendNull();
            return this;
        }
        if(charSequence0 instanceof StringBuilder) {
            this.append0(((StringBuilder)charSequence0).chars, 0, ((StringBuilder)charSequence0).length);
            return this;
        }
        this.append0(charSequence0.toString());
        return this;
    }

    public StringBuilder append(CharSequence charSequence0, int v, int v1) {
        this.append0(charSequence0, v, v1);
        return this;
    }

    public StringBuilder append(Object object0) {
        if(object0 == null) {
            this.appendNull();
            return this;
        }
        this.append0(object0.toString());
        return this;
    }

    public StringBuilder append(String s) {
        this.append0(s);
        return this;
    }

    public StringBuilder append(boolean z) {
        this.append0((z ? "true" : "false"));
        return this;
    }

    public StringBuilder append(char[] arr_c) {
        this.append0(arr_c);
        return this;
    }

    public StringBuilder append(char[] arr_c, int v, int v1) {
        this.append0(arr_c, v, v1);
        return this;
    }

    @Override
    public Appendable append(char c) throws IOException {
        return this.append(c);
    }

    @Override
    public Appendable append(CharSequence charSequence0) throws IOException {
        return this.append(charSequence0);
    }

    @Override
    public Appendable append(CharSequence charSequence0, int v, int v1) throws IOException {
        return this.append(charSequence0, v, v1);
    }

    final void append0(char c) {
        int v = this.length;
        if(v == this.chars.length) {
            this.enlargeBuffer(v + 1);
        }
        int v1 = this.length;
        this.length = v1 + 1;
        this.chars[v1] = c;
    }

    final void append0(CharSequence charSequence0, int v, int v1) {
        if(charSequence0 == null) {
            charSequence0 = "null";
        }
        if(v < 0 || v1 < 0 || v > v1 || v1 > charSequence0.length()) {
            throw new IndexOutOfBoundsException();
        }
        this.append0(charSequence0.subSequence(v, v1).toString());
    }

    final void append0(String s) {
        if(s == null) {
            this.appendNull();
            return;
        }
        int v = this.length + s.length();
        if(v > this.chars.length) {
            this.enlargeBuffer(v);
        }
        this.length = v;
    }

    final void append0(char[] arr_c) {
        int v = this.length + arr_c.length;
        if(v > this.chars.length) {
            this.enlargeBuffer(v);
        }
        System.arraycopy(arr_c, 0, this.chars, this.length, arr_c.length);
        this.length = v;
    }

    final void append0(char[] arr_c, int v, int v1) {
        if(v > arr_c.length || v < 0) {
            throw new ArrayIndexOutOfBoundsException("Offset out of bounds: " + v);
        }
        if(v1 < 0 || arr_c.length - v < v1) {
            throw new ArrayIndexOutOfBoundsException("Length out of bounds: " + v1);
        }
        int v2 = this.length + v1;
        if(v2 > this.chars.length) {
            this.enlargeBuffer(v2);
        }
        System.arraycopy(arr_c, v, this.chars, this.length, v1);
        this.length = v2;
    }

    public StringBuilder appendCodePoint(int v) {
        this.append0(Character.toChars(v));
        return this;
    }

    public StringBuilder appendLine(String s) {
        this.append0(s);
        this.append0('\n');
        return this;
    }

    final void appendNull() {
        int v = this.length + 4;
        if(v > this.chars.length) {
            this.enlargeBuffer(v);
        }
        char[] arr_c = this.chars;
        int v1 = this.length;
        this.length = v1 + 1;
        arr_c[v1] = 'n';
        int v2 = this.length;
        this.length = v2 + 1;
        arr_c[v2] = 'u';
        int v3 = this.length;
        this.length = v3 + 1;
        arr_c[v3] = 'l';
        int v4 = this.length;
        this.length = v4 + 1;
        arr_c[v4] = 'l';
    }

    public int capacity() {
        return this.chars.length;
    }

    @Override
    public char charAt(int v) {
        if(v < 0 || v >= this.length) {
            throw new StringIndexOutOfBoundsException(v);
        }
        return this.chars[v];
    }

    public void clear() {
        this.length = 0;
    }

    public int codePointAt(int v) {
        if(v >= 0) {
            int v1 = this.length;
            if(v < v1) {
                return Character.codePointAt(this.chars, v, v1);
            }
        }
        throw new StringIndexOutOfBoundsException(v);
    }

    public int codePointBefore(int v) {
        if(v < 1 || v > this.length) {
            throw new StringIndexOutOfBoundsException(v);
        }
        return Character.codePointBefore(this.chars, v);
    }

    public int codePointCount(int v, int v1) {
        if(v < 0 || v1 > this.length || v > v1) {
            throw new StringIndexOutOfBoundsException();
        }
        return Character.codePointCount(this.chars, v, v1 - v);
    }

    public boolean contains(String s) {
        return this.indexOf(s, 0) != -1;
    }

    public boolean containsIgnoreCase(String s) {
        return this.indexOfIgnoreCase(s, 0) != -1;
    }

    public StringBuilder delete(int v, int v1) {
        this.delete0(v, v1);
        return this;
    }

    final void delete0(int v, int v1) {
        if(v >= 0) {
            int v2 = this.length;
            if(v1 > v2) {
                v1 = v2;
            }
            if(v1 == v) {
                return;
            }
            if(v1 > v) {
                int v3 = this.length - v1;
                if(v3 >= 0) {
                    System.arraycopy(this.chars, v1, this.chars, v, v3);
                }
                this.length -= v1 - v;
                return;
            }
        }
        throw new StringIndexOutOfBoundsException();
    }

    public StringBuilder deleteCharAt(int v) {
        this.deleteCharAt0(v);
        return this;
    }

    final void deleteCharAt0(int v) {
        if(v >= 0) {
            int v1 = this.length;
            if(v < v1) {
                int v2 = v1 - v - 1;
                if(v2 > 0) {
                    System.arraycopy(this.chars, v + 1, this.chars, v, v2);
                }
                --this.length;
                return;
            }
        }
        throw new StringIndexOutOfBoundsException(v);
    }

    private void enlargeBuffer(int v) {
        int v1 = (this.chars.length >> 1) + this.chars.length + 2;
        if(v <= v1) {
            v = v1;
        }
        char[] arr_c = new char[v];
        System.arraycopy(this.chars, 0, arr_c, 0, this.length);
        this.chars = arr_c;
    }

    public void ensureCapacity(int v) {
        char[] arr_c = this.chars;
        if(v > arr_c.length) {
            int v1 = (arr_c.length << 1) + 2;
            if(v1 > v) {
                v = v1;
            }
            this.enlargeBuffer(v);
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        int v = this.length;
        if(v != ((StringBuilder)object0).length) {
            return false;
        }
        char[] arr_c = this.chars;
        char[] arr_c1 = ((StringBuilder)object0).chars;
        for(int v1 = 0; v1 < v; ++v1) {
            if(arr_c[v1] != arr_c1[v1]) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(@Null StringBuilder stringBuilder0) {
        if(this == stringBuilder0) {
            return true;
        }
        if(stringBuilder0 == null) {
            return false;
        }
        int v = this.length;
        if(v != stringBuilder0.length) {
            return false;
        }
        char[] arr_c = this.chars;
        char[] arr_c1 = stringBuilder0.chars;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = arr_c[v1];
            int v3 = Character.toUpperCase(arr_c1[v1]);
            if(v2 != v3 && v2 != Character.toLowerCase(((char)v3))) {
                return false;
            }
        }
        return true;
    }

    public boolean equalsIgnoreCase(@Null String s) {
        if(s == null) {
            return false;
        }
        int v = this.length;
        if(v != s.length()) {
            return false;
        }
        char[] arr_c = this.chars;
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = arr_c[v1];
            int v3 = Character.toUpperCase(s.charAt(v1));
            if(v2 != v3 && v2 != Character.toLowerCase(((char)v3))) {
                return false;
            }
        }
        return true;
    }

    public void getChars(int v, int v1, char[] arr_c, int v2) {
        if(v > this.length || v1 > this.length || v > v1) {
            throw new StringIndexOutOfBoundsException();
        }
        System.arraycopy(this.chars, v, arr_c, v2, v1 - v);
    }

    final char[] getValue() {
        return this.chars;
    }

    @Override
    public int hashCode() {
        int v = this.length + 0x1F;
        for(int v1 = 0; v1 < this.length; ++v1) {
            v = v * 0x1F + this.chars[v1];
        }
        return v;
    }

    public int indexOf(String s) {
        return this.indexOf(s, 0);
    }

    public int indexOf(String s, int v) {
        boolean z;
        if(v < 0) {
            v = 0;
        }
        int v1 = s.length();
        if(v1 == 0) {
            return v >= this.length && v != 0 ? this.length : v;
        }
        int v2 = this.length - v1;
        if(v > v2) {
            return -1;
        }
        int v3 = s.charAt(0);
        while(true) {
            if(v <= v2) {
                if(this.chars[v] == v3) {
                    z = true;
                    goto label_16;
                }
                else {
                    ++v;
                    continue;
                }
                goto label_15;
            }
            else {
            label_15:
                z = false;
            }
        label_16:
            if(!z) {
                return -1;
            }
            int v4 = v;
            int v5 = 0;
            while(true) {
                ++v5;
                if(v5 >= v1 || this.chars[v4 + 1] != s.charAt(v5)) {
                    break;
                }
                ++v4;
            }
            if(v5 == v1) {
                return v;
            }
            ++v;
        }
    }

    public int indexOfIgnoreCase(String s, int v) {
        boolean z;
        if(v < 0) {
            v = 0;
        }
        int v1 = s.length();
        if(v1 == 0) {
            return v >= this.length && v != 0 ? this.length : v;
        }
        int v2 = this.length - v1;
        if(v > v2) {
            return -1;
        }
        int v3 = Character.toUpperCase(s.charAt(0));
        int v4 = Character.toLowerCase(((char)v3));
        while(true) {
            if(v <= v2) {
                int v5 = this.chars[v];
                if(v5 == v3 || v5 == v4) {
                    z = true;
                    goto label_18;
                }
                else {
                    ++v;
                    continue;
                }
                goto label_17;
            }
            else {
            label_17:
                z = false;
            }
        label_18:
            if(!z) {
                return -1;
            }
            int v6 = v;
            int v7 = 0;
            do {
                ++v7;
                if(v7 >= v1) {
                    break;
                }
                ++v6;
                int v8 = this.chars[v6];
                int v9 = Character.toUpperCase(s.charAt(v7));
            }
            while(v8 == v9 || v8 == Character.toLowerCase(((char)v9)));
            if(v7 == v1) {
                return v;
            }
            ++v;
        }
    }

    public StringBuilder insert(int v, char c) {
        this.insert0(v, c);
        return this;
    }

    public StringBuilder insert(int v, double f) {
        this.insert0(v, Double.toString(f));
        return this;
    }

    public StringBuilder insert(int v, float f) {
        this.insert0(v, Float.toString(f));
        return this;
    }

    public StringBuilder insert(int v, int v1) {
        this.insert0(v, Integer.toString(v1));
        return this;
    }

    public StringBuilder insert(int v, long v1) {
        this.insert0(v, Long.toString(v1));
        return this;
    }

    public StringBuilder insert(int v, CharSequence charSequence0) {
        this.insert0(v, (charSequence0 == null ? "null" : charSequence0.toString()));
        return this;
    }

    public StringBuilder insert(int v, CharSequence charSequence0, int v1, int v2) {
        this.insert0(v, charSequence0, v1, v2);
        return this;
    }

    public StringBuilder insert(int v, Object object0) {
        this.insert0(v, (object0 == null ? "null" : object0.toString()));
        return this;
    }

    public StringBuilder insert(int v, String s) {
        this.insert0(v, s);
        return this;
    }

    public StringBuilder insert(int v, boolean z) {
        this.insert0(v, (z ? "true" : "false"));
        return this;
    }

    public StringBuilder insert(int v, char[] arr_c) {
        this.insert0(v, arr_c);
        return this;
    }

    public StringBuilder insert(int v, char[] arr_c, int v1, int v2) {
        this.insert0(v, arr_c, v1, v2);
        return this;
    }

    final void insert0(int v, char c) {
        if(v < 0 || v > this.length) {
            throw new ArrayIndexOutOfBoundsException(v);
        }
        this.move(1, v);
        this.chars[v] = c;
        ++this.length;
    }

    final void insert0(int v, CharSequence charSequence0, int v1, int v2) {
        if(charSequence0 == null) {
            charSequence0 = "null";
        }
        if(v < 0 || v > this.length || v1 < 0 || v2 < 0 || v1 > v2 || v2 > charSequence0.length()) {
            throw new IndexOutOfBoundsException();
        }
        this.insert0(v, charSequence0.subSequence(v1, v2).toString());
    }

    final void insert0(int v, String s) {
        if(v < 0 || v > this.length) {
            throw new StringIndexOutOfBoundsException(v);
        }
        if(s == null) {
            s = "null";
        }
        int v1 = s.length();
        if(v1 != 0) {
            this.move(v1, v);
            this.length += v1;
        }
    }

    final void insert0(int v, char[] arr_c) {
        if(v < 0 || v > this.length) {
            throw new StringIndexOutOfBoundsException(v);
        }
        if(arr_c.length != 0) {
            this.move(arr_c.length, v);
            System.arraycopy(arr_c, 0, arr_c, v, arr_c.length);
            this.length += arr_c.length;
        }
    }

    final void insert0(int v, char[] arr_c, int v1, int v2) {
        if(v < 0 || v > v2) {
            throw new StringIndexOutOfBoundsException(v);
        }
        if(v1 < 0 || v2 < 0 || v2 > arr_c.length - v1) {
            throw new StringIndexOutOfBoundsException("offset " + v1 + ", length " + v2 + ", char[].length " + arr_c.length);
        }
        if(v2 != 0) {
            this.move(v2, v);
            System.arraycopy(arr_c, v1, this.chars, v, v2);
            this.length += v2;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    public int lastIndexOf(String s) {
        return this.lastIndexOf(s, this.length);
    }

    public int lastIndexOf(String s, int v) {
        boolean z;
        int v1 = s.length();
        int v2 = this.length;
        if(v1 <= v2 && v >= 0) {
            if(v1 > 0) {
                if(v > v2 - v1) {
                    v = v2 - v1;
                }
                int v3 = s.charAt(0);
                while(true) {
                    if(v >= 0) {
                        if(this.chars[v] == v3) {
                            z = true;
                            goto label_14;
                        }
                        else {
                            --v;
                            continue;
                        }
                        goto label_13;
                    }
                    else {
                    label_13:
                        z = false;
                    }
                label_14:
                    if(!z) {
                        return -1;
                    }
                    int v4 = v;
                    int v5 = 0;
                    while(true) {
                        ++v5;
                        if(v5 >= v1 || this.chars[v4 + 1] != s.charAt(v5)) {
                            break;
                        }
                        ++v4;
                    }
                    if(v5 == v1) {
                        return v;
                    }
                    --v;
                }
            }
            return v < v2 ? v : v2;
        }
        return -1;
    }

    @Override
    public int length() {
        return this.length;
    }

    private void move(int v, int v1) {
        char[] arr_c = this.chars;
        int v2 = this.length;
        if(arr_c.length - v2 >= v) {
            System.arraycopy(arr_c, v1, arr_c, v + v1, v2 - v1);
            return;
        }
        int v3 = v2 + v;
        char[] arr_c1 = new char[(v3 <= (arr_c.length << 1) + 2 ? (arr_c.length << 1) + 2 : v3)];
        System.arraycopy(this.chars, 0, arr_c1, 0, v1);
        System.arraycopy(this.chars, v1, arr_c1, v + v1, this.length - v1);
        this.chars = arr_c1;
    }

    public boolean notEmpty() {
        return this.length != 0;
    }

    public static int numChars(int v, int v1) {
        int v2;
        for(v2 = v >= 0 ? 1 : 2; true; ++v2) {
            v /= v1;
            if(v == 0) {
                break;
            }
        }
        return v2;
    }

    public static int numChars(long v, int v1) {
        int v2;
        for(v2 = v >= 0L ? 1 : 2; true; ++v2) {
            v /= (long)v1;
            if(v == 0L) {
                break;
            }
        }
        return v2;
    }

    public int offsetByCodePoints(int v, int v1) {
        return Character.offsetByCodePoints(this.chars, 0, this.length, v, v1);
    }

    public StringBuilder replace(char c, String s) {
        int v = s.length();
        int v1 = 0;
        while(v1 != this.length) {
            if(this.chars[v1] == c) {
                this.replace0(v1, v1 + 1, s);
                v1 += v;
            }
            else {
                ++v1;
            }
        }
        return this;
    }

    public StringBuilder replace(int v, int v1, String s) {
        this.replace0(v, v1, s);
        return this;
    }

    public StringBuilder replace(String s, String s1) {
        int v = s.length();
        int v1 = s1.length();
        int v3;
        for(int v2 = 0; (v3 = this.indexOf(s, v2)) != -1; v2 = v3 + v1) {
            this.replace0(v3, v3 + v, s1);
        }
        return this;
    }

    final void replace0(int v, int v1, String s) {
        if(v >= 0) {
            int v2 = this.length;
            if(v1 > v2) {
                v1 = v2;
            }
            if(v1 > v) {
                int v3 = s.length();
                int v4 = v1 - v - v3;
                if(v4 > 0) {
                    System.arraycopy(this.chars, v1, this.chars, v + v3, this.length - v1);
                }
                else if(v4 < 0) {
                    this.move(-v4, v1);
                }
                this.length -= v4;
                return;
            }
            if(v == v1) {
                if(s == null) {
                    throw new NullPointerException();
                }
                this.insert0(v, s);
                return;
            }
        }
        throw new StringIndexOutOfBoundsException();
    }

    public StringBuilder reverse() {
        this.reverse0();
        return this;
    }

    final void reverse0() {
        int v = this.length;
        if(v < 2) {
            return;
        }
        char[] arr_c = this.chars;
        char c = arr_c[0];
        char c1 = arr_c[v - 1];
        char c2 = c;
        boolean z = true;
        boolean z1 = true;
        int v1 = v - 1;
        int v2 = 0;
        while(v2 < v / 2) {
            char[] arr_c1 = this.chars;
            char c3 = arr_c1[v2 + 1];
            char c4 = arr_c1[v1 - 1];
            int v3 = !z || c3 < 0xDC00 || c3 > 0xDFFF || c2 < 0xD800 || c2 > 0xDBFF ? 0 : 1;
            if(v3 != 0 && this.length < 3) {
                return;
            }
            if(v3 == (!z1 || c4 < 0xD800 || c4 > 0xDBFF || c1 < 0xDC00 || c1 > 0xDFFF ? 0 : 1)) {
                if(v3 == 0) {
                    char[] arr_c3 = this.chars;
                    arr_c3[v1] = c2;
                    arr_c3[v2] = c1;
                    c1 = c4;
                    c2 = c3;
                }
                else {
                    char[] arr_c2 = this.chars;
                    arr_c2[v1] = c3;
                    arr_c2[v1 - 1] = c2;
                    arr_c2[v2] = c4;
                    arr_c2[v2 + 1] = c1;
                    char c5 = arr_c2[v2 + 2];
                    char c6 = arr_c2[v1 - 2];
                    --v1;
                    c1 = c6;
                    c2 = c5;
                    ++v2;
                }
                z = true;
                z1 = true;
            }
            else if(v3 == 0) {
                char[] arr_c5 = this.chars;
                arr_c5[v1] = c2;
                arr_c5[v2] = c4;
                c2 = c3;
                z = true;
                z1 = false;
            }
            else {
                char[] arr_c4 = this.chars;
                arr_c4[v1] = c3;
                arr_c4[v2] = c1;
                c1 = c4;
                z = false;
                z1 = true;
            }
            ++v2;
            --v1;
        }
        if((this.length & 1) == 1 && (!z || !z1)) {
            char[] arr_c6 = this.chars;
            if(z) {
                c2 = c1;
            }
            arr_c6[v1] = c2;
        }
    }

    public void setCharAt(int v, char c) {
        if(v < 0 || v >= this.length) {
            throw new StringIndexOutOfBoundsException(v);
        }
        this.chars[v] = c;
    }

    public void setLength(int v) {
        if(v < 0) {
            throw new StringIndexOutOfBoundsException(v);
        }
        char[] arr_c = this.chars;
        if(v > arr_c.length) {
            this.enlargeBuffer(v);
        }
        else {
            int v1 = this.length;
            if(v1 < v) {
                Arrays.fill(arr_c, v1, v, '\u0000');
            }
        }
        this.length = v;
    }

    @Override
    public CharSequence subSequence(int v, int v1) {
        return this.substring(v, v1);
    }

    public String substring(int v) {
        if(v >= 0) {
            int v1 = this.length;
            if(v <= v1) {
                return v == v1 ? "" : new String(this.chars, v, v1 - v);
            }
        }
        throw new StringIndexOutOfBoundsException(v);
    }

    public String substring(int v, int v1) {
        if(v < 0 || v > v1 || v1 > this.length) {
            throw new StringIndexOutOfBoundsException();
        }
        return v == v1 ? "" : new String(this.chars, v, v1 - v);
    }

    @Override
    public String toString() [...] // 潜在的解密器

    public void trimToSize() {
        int v = this.length;
        char[] arr_c = this.chars;
        if(v < arr_c.length) {
            char[] arr_c1 = new char[v];
            System.arraycopy(arr_c, 0, arr_c1, 0, v);
            this.chars = arr_c1;
        }
    }
}

