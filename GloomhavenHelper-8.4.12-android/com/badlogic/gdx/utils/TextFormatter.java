package com.badlogic.gdx.utils;

import java.text.MessageFormat;
import java.util.Locale;

class TextFormatter {
    private StringBuilder buffer;
    private MessageFormat messageFormat;

    public TextFormatter(Locale locale0, boolean z) {
        this.buffer = new StringBuilder();
        if(z) {
            this.messageFormat = new MessageFormat("", locale0);
        }
    }

    public String format(String s, Object[] arr_object) {
        MessageFormat messageFormat0 = this.messageFormat;
        if(messageFormat0 != null) {
            messageFormat0.applyPattern(this.replaceEscapeChars(s));
            return this.messageFormat.format(arr_object);
        }
        return this.simpleFormat(s, arr_object);
    }

    private String replaceEscapeChars(String s) {
        this.buffer.setLength(0);
        int v1 = s.length();
        boolean z = false;
        for(int v = 0; v < v1; ++v) {
            int v2 = s.charAt(v);
            if(v2 == 39) {
                this.buffer.append("\'\'");
                z = true;
            }
            else if(v2 == 0x7B) {
                int v3;
                for(v3 = v + 1; v3 < v1 && s.charAt(v3) == 0x7B; ++v3) {
                }
                int v4 = v3 - v;
                int v5 = v4 / 2;
                if(v5 > 0) {
                    this.buffer.append('\'');
                    do {
                        this.buffer.append('{');
                        --v5;
                    }
                    while(v5 > 0);
                    this.buffer.append('\'');
                    z = true;
                }
                if(v4 % 2 != 0) {
                    this.buffer.append('{');
                }
                v = v3 - 1;
            }
            else {
                this.buffer.append(((char)v2));
            }
        }
        return z ? "" : s;
    }

    private String simpleFormat(String s, Object[] arr_object) {
        this.buffer.setLength(0);
        int v = s.length();
        int v2 = -1;
        boolean z = false;
        for(int v1 = 0; v1 < v; ++v1) {
            int v3 = s.charAt(v1);
            if(v2 < 0) {
                if(v3 == 0x7B) {
                    if(v1 + 1 >= v || s.charAt(v1 + 1) != 0x7B) {
                        v2 = 0;
                    }
                    else {
                        this.buffer.append('{');
                        ++v1;
                    }
                    z = true;
                }
                else {
                    this.buffer.append(((char)v3));
                }
            }
            else if(v3 == 0x7D) {
                if(v2 >= arr_object.length) {
                    throw new IllegalArgumentException("Argument index out of bounds: " + v2);
                }
                if(s.charAt(v1 - 1) == 0x7B) {
                    throw new IllegalArgumentException("Missing argument index after a left curly brace");
                }
                if(arr_object[v2] == null) {
                    this.buffer.append("null");
                }
                else {
                    this.buffer.append(arr_object[v2].toString());
                }
                v2 = -1;
                continue;
            }
            else {
                if(v3 < 0x30 || v3 > 57) {
                    throw new IllegalArgumentException("Unexpected \'" + ((char)v3) + "\' while parsing argument index");
                }
                v2 = v2 * 10 + (v3 - 0x30);
            }
        }
        if(v2 >= 0) {
            throw new IllegalArgumentException("Unmatched braces in the pattern.");
        }
        return z ? "" : s;
    }
}

