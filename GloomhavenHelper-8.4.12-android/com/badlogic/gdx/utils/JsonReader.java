package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import jeb.synthetic.FIN;

public class JsonReader implements BaseJsonReader {
    private static final byte[] _json_actions = null;
    private static final byte[] _json_eof_actions = null;
    private static final short[] _json_index_offsets = null;
    private static final byte[] _json_indicies = null;
    private static final short[] _json_key_offsets = null;
    private static final byte[] _json_range_lengths = null;
    private static final byte[] _json_single_lengths = null;
    private static final byte[] _json_trans_actions = null;
    private static final char[] _json_trans_keys = null;
    private static final byte[] _json_trans_targs = null;
    private JsonValue current;
    private final Array elements;
    static final int json_en_array = 23;
    static final int json_en_main = 1;
    static final int json_en_object = 5;
    static final int json_error = 0;
    static final int json_first_final = 35;
    static final int json_start = 1;
    private final Array lastChild;
    private JsonValue root;

    static {
        JsonReader._json_actions = new byte[]{0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 2, 0, 7, 2, 0, 8, 2, 1, 3, 2, 1, 5};
        JsonReader._json_key_offsets = JsonReader.init__json_key_offsets_0();
        JsonReader._json_trans_keys = JsonReader.init__json_trans_keys_0();
        JsonReader._json_single_lengths = new byte[]{0, 9, 2, 1, 2, 7, 4, 4, 2, 9, 7, 7, 7, 1, 7, 2, 2, 7, 2, 2, 1, 2, 2, 9, 7, 7, 9, 1, 9, 2, 2, 9, 2, 2, 2, 3, 3, 0, 0};
        JsonReader._json_range_lengths = new byte[]{0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0};
        JsonReader._json_index_offsets = JsonReader.init__json_index_offsets_0();
        JsonReader._json_indicies = new byte[]{1, 1, 2, 3, 4, 3, 5, 3, 6, 1, 0, 7, 7, 3, 8, 3, 9, 9, 3, 11, 11, 12, 13, 14, 3, 15, 11, 10, 16, 16, 17, 18, 16, 3, 19, 19, 20, 21, 19, 3, 22, 22, 3, 21, 21, 24, 3, 25, 3, 26, 3, 27, 21, 23, 28, 29, 29, 28, 30, 0x1F, 0x20, 3, 33, 34, 34, 33, 13, 35, 15, 3, 34, 34, 12, 36, 37, 3, 15, 34, 10, 16, 3, 36, 36, 12, 3, 38, 3, 3, 36, 10, 39, 39, 3, 40, 40, 3, 13, 13, 12, 3, 41, 3, 15, 13, 10, 42, 42, 3, 43, 43, 3, 28, 3, 44, 44, 3, 45, 45, 3, 0x2F, 0x2F, 0x30, 49, 50, 3, 51, 52, 53, 0x2F, 46, 54, 55, 55, 54, 56, 57, 58, 3, 59, 60, 60, 59, 49, 61, 52, 3, 60, 60, 0x30, 62, 0x3F, 3, 51, 52, 53, 60, 46, 54, 3, 62, 62, 0x30, 3, 0x40, 3, 51, 3, 53, 62, 46, 65, 65, 3, 66, 66, 3, 49, 49, 0x30, 3, 67, 3, 51, 52, 53, 49, 46, 68, 68, 3, 69, 69, 3, 70, 70, 3, 8, 8, 71, 8, 3, 72, 72, 73, 72, 3, 3, 3, 0};
        JsonReader._json_trans_targs = new byte[]{35, 1, 3, 0, 4, 36, 36, 36, 36, 1, 6, 5, 13, 17, 22, 37, 7, 8, 9, 7, 8, 9, 7, 10, 20, 21, 11, 11, 11, 12, 17, 19, 37, 11, 12, 19, 14, 16, 15, 14, 12, 18, 17, 11, 9, 5, 24, 23, 27, 0x1F, 34, 25, 38, 25, 25, 26, 0x1F, 33, 38, 25, 26, 33, 28, 30, 29, 28, 26, 0x20, 0x1F, 25, 23, 2, 36, 2};
        JsonReader._json_trans_actions = new byte[]{13, 0, 15, 0, 0, 7, 3, 11, 1, 11, 17, 0, 20, 0, 0, 5, 1, 1, 1, 0, 0, 0, 11, 13, 15, 0, 7, 3, 1, 1, 1, 1, 23, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 11, 13, 0, 15, 0, 0, 7, 9, 3, 1, 1, 1, 1, 26, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 1, 0, 0};
        JsonReader._json_eof_actions = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
    }

    public JsonReader() {
        this.elements = new Array(8);
        this.lastChild = new Array(8);
    }

    private void addChild(@Null String s, JsonValue jsonValue0) {
        jsonValue0.setName(s);
        JsonValue jsonValue1 = this.current;
        if(jsonValue1 == null) {
            this.current = jsonValue0;
            this.root = jsonValue0;
            return;
        }
        if(!jsonValue1.isArray() && !this.current.isObject()) {
            this.root = this.current;
            return;
        }
        jsonValue0.parent = this.current;
        if(this.current.size == 0) {
            this.current.child = jsonValue0;
        }
        else {
            JsonValue jsonValue2 = (JsonValue)this.lastChild.pop();
            jsonValue2.next = jsonValue0;
            jsonValue0.prev = jsonValue2;
        }
        this.lastChild.add(jsonValue0);
        ++this.current.size;
    }

    protected void bool(String s, boolean z) {
        this.addChild(s, new JsonValue(z));
    }

    private static byte[] init__json_actions_0() {
        return new byte[]{0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 2, 0, 7, 2, 0, 8, 2, 1, 3, 2, 1, 5};
    }

    private static byte[] init__json_eof_actions_0() {
        return new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
    }

    private static short[] init__json_index_offsets_0() {
        return new short[]{0, 0, 11, 14, 16, 19, 28, 34, 40, 43, 54, 62, 70, 0x4F, 81, 90, 93, 0x60, 105, 108, 0x6F, 0x71, 0x74, 0x77, 130, 0x8A, 0x92, 0x9D, 0x9F, 170, 0xAD, 0xB0, 0xBB, 190, 0xC1, 0xC4, 201, 206, 0xCF};
    }

    private static byte[] init__json_indicies_0() {
        return new byte[]{1, 1, 2, 3, 4, 3, 5, 3, 6, 1, 0, 7, 7, 3, 8, 3, 9, 9, 3, 11, 11, 12, 13, 14, 3, 15, 11, 10, 16, 16, 17, 18, 16, 3, 19, 19, 20, 21, 19, 3, 22, 22, 3, 21, 21, 24, 3, 25, 3, 26, 3, 27, 21, 23, 28, 29, 29, 28, 30, 0x1F, 0x20, 3, 33, 34, 34, 33, 13, 35, 15, 3, 34, 34, 12, 36, 37, 3, 15, 34, 10, 16, 3, 36, 36, 12, 3, 38, 3, 3, 36, 10, 39, 39, 3, 40, 40, 3, 13, 13, 12, 3, 41, 3, 15, 13, 10, 42, 42, 3, 43, 43, 3, 28, 3, 44, 44, 3, 45, 45, 3, 0x2F, 0x2F, 0x30, 49, 50, 3, 51, 52, 53, 0x2F, 46, 54, 55, 55, 54, 56, 57, 58, 3, 59, 60, 60, 59, 49, 61, 52, 3, 60, 60, 0x30, 62, 0x3F, 3, 51, 52, 53, 60, 46, 54, 3, 62, 62, 0x30, 3, 0x40, 3, 51, 3, 53, 62, 46, 65, 65, 3, 66, 66, 3, 49, 49, 0x30, 3, 67, 3, 51, 52, 53, 49, 46, 68, 68, 3, 69, 69, 3, 70, 70, 3, 8, 8, 71, 8, 3, 72, 72, 73, 72, 3, 3, 3, 0};
    }

    private static short[] init__json_key_offsets_0() {
        return new short[]{0, 0, 11, 13, 14, 16, 25, 0x1F, 37, 39, 50, 57, 0x40, 73, 74, 83, 85, 87, 0x60, 98, 100, 101, 103, 105, 0x74, 0x7B, 130, 0x8D, 0x8E, 0x99, 0x9B, 0x9D, 0xA8, 170, 0xAC, 0xAE, 0xB3, 0xB8, 0xB8};
    }

    private static byte[] init__json_range_lengths_0() {
        return new byte[]{0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0};
    }

    private static byte[] init__json_single_lengths_0() {
        return new byte[]{0, 9, 2, 1, 2, 7, 4, 4, 2, 9, 7, 7, 7, 1, 7, 2, 2, 7, 2, 2, 1, 2, 2, 9, 7, 7, 9, 1, 9, 2, 2, 9, 2, 2, 2, 3, 3, 0, 0};
    }

    private static byte[] init__json_trans_actions_0() {
        return new byte[]{13, 0, 15, 0, 0, 7, 3, 11, 1, 11, 17, 0, 20, 0, 0, 5, 1, 1, 1, 0, 0, 0, 11, 13, 15, 0, 7, 3, 1, 1, 1, 1, 23, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 11, 13, 0, 15, 0, 0, 7, 9, 3, 1, 1, 1, 1, 26, 0, 0, 0, 0, 0, 0, 11, 11, 0, 11, 11, 11, 1, 0, 0};
    }

    private static char[] init__json_trans_keys_0() {
        return new char[]{'\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '\"', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '\r', ' ', '/', ':', '\t', '\n', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', '}', '\t', '\n', '\r', ' ', ',', '/', '}', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '}', '\t', '\n', '*', '/', '*', '/', '\"', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\t', '\n', '\r', ' ', ',', '/', ']', '\t', '\n', '\r', ' ', ',', '/', ']', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '\"', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '\r', ' ', '\"', ',', '/', ':', '[', ']', '{', '\t', '\n', '*', '/', '*', '/', '*', '/', '\r', ' ', '/', '\t', '\n', '\r', ' ', '/', '\t', '\n', '\u0000'};
    }

    private static byte[] init__json_trans_targs_0() {
        return new byte[]{35, 1, 3, 0, 4, 36, 36, 36, 36, 1, 6, 5, 13, 17, 22, 37, 7, 8, 9, 7, 8, 9, 7, 10, 20, 21, 11, 11, 11, 12, 17, 19, 37, 11, 12, 19, 14, 16, 15, 14, 12, 18, 17, 11, 9, 5, 24, 23, 27, 0x1F, 34, 25, 38, 25, 25, 26, 0x1F, 33, 38, 25, 26, 33, 28, 30, 29, 28, 26, 0x20, 0x1F, 25, 23, 2, 36, 2};
    }

    protected void number(String s, double f, String s1) {
        this.addChild(s, new JsonValue(f, s1));
    }

    protected void number(String s, long v, String s1) {
        this.addChild(s, new JsonValue(v, s1));
    }

    @Override  // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(FileHandle fileHandle0) {
        Reader reader0;
        try {
            reader0 = fileHandle0.reader("UTF-8");
        }
        catch(Exception exception0) {
            throw new SerializationException("Error reading file: " + fileHandle0, exception0);
        }
        try {
            return this.parse(reader0);
        }
        catch(Exception exception1) {
            throw new SerializationException("Error parsing file: " + fileHandle0, exception1);
        }
    }

    @Override  // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(InputStream inputStream0) {
        try {
            return this.parse(new InputStreamReader(inputStream0, "UTF-8"));
        }
        catch(Exception exception0) {
            throw new SerializationException("Error reading stream.", exception0);
        }
    }

    public JsonValue parse(Reader reader0) {
        int v2;
        int v1;
        int v;
        char[] arr_c;
        try {
            arr_c = new char[0x400];
            v = 0;
            v1 = FIN.finallyOpen$NT();
            while(true) {
            label_3:
                v2 = reader0.read(arr_c, v, arr_c.length - v);
                switch(v2) {
                    case -1: {
                        goto label_6;
                    }
                    case 0: {
                        goto label_12;
                    }
                }
            }
        }
        catch(IOException iOException0) {
            goto label_17;
        }
        goto label_10;
    label_6:
        FIN.finallyCodeBegin$NT(v1);
        StreamUtils.closeQuietly(reader0);
        FIN.finallyCodeEnd$NT(v1);
        return this.parse(arr_c, 0, v);
    label_10:
        v += v2;
        goto label_19;
        try {
        label_12:
            char[] arr_c1 = new char[arr_c.length * 2];
            System.arraycopy(arr_c, 0, arr_c1, 0, arr_c.length);
            arr_c = arr_c1;
        }
        catch(IOException iOException0) {
        label_17:
            FIN.finallyExec$NT(v1);
            throw new SerializationException("Error reading input.", iOException0);
        }
    label_19:
        goto label_3;
    }

    public JsonValue parse(String s) {
        char[] arr_c = s.toCharArray();
        return this.parse(arr_c, 0, arr_c.length);
    }

    // This method was un-flattened
    public JsonValue parse(char[] arr_c, int v, int v1) {
        RuntimeException runtimeException3;
        int v30;
        int v29;
        int v28;
        int v27;
        int v26;
        int v25;
        int v24;
        int v23;
        int v22;
        int v21;
        Array array0 = new Array(8);
        int v2 = v;
        int[] arr_v = new int[4];
        int v3 = 0;
        int v4 = 1;
        int v5 = 0;
        int v6 = 0;
        int v7 = 0;
        boolean z = false;
    alab2:
        while(true) {
        label_9:
            switch(v8) {
                case 0: {
                    if(v2 == v1) {
                        goto label_254;
                    }
                    else if(v4 != 0) {
                        goto label_13;
                    }
                    runtimeException3 = null;
                    break alab2;
                }
                case 1: {
                    try {
                    label_13:
                        int v9 = JsonReader._json_key_offsets[v4];
                        int v10 = JsonReader._json_index_offsets[v4];
                        int v11 = JsonReader._json_single_lengths[v4];
                        if(v11 > 0) {
                            int v12 = v9 + v11;
                            int v13 = v9;
                            int v14 = v12 - 1;
                            while(v14 >= v13) {
                                int v15 = v13 + (v14 - v13 >> 1);
                                if(arr_c[v2] < JsonReader._json_trans_keys[v15]) {
                                    v14 = v15 - 1;
                                    continue;
                                }
                                if(arr_c[v2] > JsonReader._json_trans_keys[v15]) {
                                    v13 = v15 + 1;
                                    continue;
                                }
                                v10 += v15 - v9;
                                goto label_47;
                            }
                            v10 += v11;
                            v9 = v12;
                        }
                        int v16 = JsonReader._json_range_lengths[v4];
                        if(v16 > 0) {
                            int v17 = (v16 << 1) + v9 - 2;
                            int v18 = v9;
                            while(v17 >= v18) {
                                int v19 = (v17 - v18 >> 1 & -2) + v18;
                                if(arr_c[v2] < JsonReader._json_trans_keys[v19]) {
                                    v17 = v19 - 2;
                                    continue;
                                }
                                if(arr_c[v2] > JsonReader._json_trans_keys[v19 + 1]) {
                                    v18 = v19 + 2;
                                    continue;
                                }
                                v10 += v19 - v9 >> 1;
                                goto label_47;
                            }
                            v10 += v16;
                        }
                    label_47:
                        int v20 = JsonReader._json_indicies[v10];
                        v21 = JsonReader._json_trans_targs[v20];
                        if(JsonReader._json_trans_actions[v20] == 0) {
                            v25 = v3;
                            v4 = v21;
                            goto label_251;
                        }
                        else {
                            v22 = JsonReader._json_trans_actions[v20] + 1;
                            v23 = JsonReader._json_actions[JsonReader._json_trans_actions[v20]];
                            while(true) {
                            label_52:
                                v24 = v23 - 1;
                                if(v23 <= 0) {
                                    v25 = v3;
                                    v4 = v21;
                                    goto label_251;
                                }
                                switch(JsonReader._json_actions[v22]) {
                                    case 0: {
                                        v26 = v21;
                                        v27 = v3;
                                        v28 = v24;
                                        v7 = 1;
                                        goto label_242;
                                    }
                                    case 1: {
                                        v28 = v24;
                                        String s = new String(arr_c, v5, v2 - v5);
                                        if(v6 != 0) {
                                            s = this.unescape(s);
                                        }
                                        if(v7 == 0) {
                                            String s1 = array0.size <= 0 ? null : ((String)array0.pop());
                                            if(!z) {
                                                goto label_112;
                                            }
                                            else if(s.equals("true")) {
                                                this.bool(s1, true);
                                                v26 = v21;
                                                v27 = v3;
                                                v7 = 0;
                                                v5 = v2;
                                                z = false;
                                                goto label_242;
                                            }
                                            else if(s.equals("false")) {
                                                this.bool(s1, false);
                                                v26 = v21;
                                                v27 = v3;
                                                v7 = 0;
                                                v5 = v2;
                                                z = false;
                                                goto label_242;
                                            }
                                            else if(s.equals("null")) {
                                                this.string(s1, null);
                                                v26 = v21;
                                                v27 = v3;
                                                v7 = 0;
                                                v5 = v2;
                                                z = false;
                                                goto label_242;
                                            }
                                            else {
                                                boolean z1 = false;
                                                boolean z2 = true;
                                            alab1:
                                                while(true) {
                                                    if(v5 < v2) {
                                                        v26 = v21;
                                                        switch(arr_c[v5]) {
                                                            case 43: 
                                                            case 45: 
                                                            case 0x30: 
                                                            case 49: 
                                                            case 50: 
                                                            case 51: 
                                                            case 52: 
                                                            case 53: 
                                                            case 54: 
                                                            case 55: 
                                                            case 56: 
                                                            case 57: {
                                                                ++v5;
                                                                v21 = v26;
                                                                continue;
                                                            }
                                                            case 46: 
                                                            case 69: 
                                                            case 101: {
                                                                z1 = true;
                                                                z2 = false;
                                                                ++v5;
                                                                v21 = v26;
                                                                continue;
                                                            }
                                                            default: {
                                                                z1 = false;
                                                                z2 = false;
                                                                break alab1;
                                                            }
                                                        }
                                                    }
                                                    v26 = v21;
                                                    break;
                                                }
                                                try {
                                                    if(z1) {
                                                        v27 = v3;
                                                        this.number(s1, Double.parseDouble(s), s);
                                                        v7 = 0;
                                                        v5 = v2;
                                                        z = false;
                                                        goto label_242;
                                                    }
                                                    else {
                                                        v27 = v3;
                                                        if(z2) {
                                                            this.number(s1, Long.parseLong(s), s);
                                                            v7 = 0;
                                                            v5 = v2;
                                                            z = false;
                                                            goto label_242;
                                                        label_112:
                                                            v26 = v21;
                                                            v27 = v3;
                                                        }
                                                    }
                                                }
                                                catch(NumberFormatException unused_ex) {
                                                }
                                            }
                                            this.string(s1, s);
                                        }
                                        else {
                                            array0.add(s);
                                            v26 = v21;
                                            v27 = v3;
                                        }
                                        v7 = 0;
                                        v5 = v2;
                                        z = false;
                                        goto label_242;
                                    }
                                    case 2: {
                                        this.startObject((array0.size <= 0 ? null : ((String)array0.pop())));
                                        if(v3 == arr_v.length) {
                                            int[] arr_v1 = new int[arr_v.length * 2];
                                            System.arraycopy(arr_v, 0, arr_v1, 0, arr_v.length);
                                            arr_v = arr_v1;
                                        }
                                        arr_v[v3] = v21;
                                        ++v3;
                                        v8 = 2;
                                        v4 = 5;
                                        continue alab2;
                                    }
                                    case 3: {
                                        this.pop();
                                        --v3;
                                        v4 = arr_v[v3];
                                        v8 = 2;
                                        continue alab2;
                                    }
                                    case 4: {
                                        this.startArray((array0.size <= 0 ? null : ((String)array0.pop())));
                                        if(v3 == arr_v.length) {
                                            int[] arr_v2 = new int[arr_v.length * 2];
                                            System.arraycopy(arr_v, 0, arr_v2, 0, arr_v.length);
                                            arr_v = arr_v2;
                                        }
                                        arr_v[v3] = v21;
                                        v4 = 23;
                                        ++v3;
                                        v8 = 2;
                                        continue alab2;
                                    }
                                    case 5: {
                                        this.pop();
                                        --v3;
                                        v4 = arr_v[v3];
                                        v8 = 2;
                                        continue alab2;
                                    }
                                    case 6: {
                                        goto label_153;
                                    }
                                    case 7: {
                                        goto label_176;
                                    }
                                    case 8: {
                                        goto label_222;
                                    }
                                    default: {
                                        v26 = v21;
                                        v27 = v3;
                                        v28 = v24;
                                        goto label_242;
                                    }
                                }
                            }
                        }
                        goto label_250;
                    }
                    catch(RuntimeException runtimeException0) {
                        break;
                    }
                    try {
                    label_153:
                        v29 = v2 + 1;
                        if(arr_c[v2] == 0x2F) {
                            goto label_155;
                        }
                        else {
                            goto label_162;
                        }
                        v26 = v21;
                        v27 = v3;
                        goto label_242;
                    }
                    catch(RuntimeException runtimeException1) {
                        runtimeException3 = runtimeException1;
                        v2 = v29;
                        break alab2;
                    }
                label_155:
                    v2 = v29;
                    try {
                        while(v2 != v1 && arr_c[v2] != 10) {
                            ++v2;
                        }
                        --v2;
                        v28 = v24;
                        v26 = v21;
                        v27 = v3;
                        goto label_242;
                    label_162:
                        v2 = v29;
                        while(true) {
                            if(v2 + 1 < v1) {
                                v28 = v24;
                                if(arr_c[v2] != 42) {
                                    goto label_169;
                                }
                            }
                            else {
                                v28 = v24;
                            }
                            if(arr_c[v2 + 1] == 0x2F) {
                                break;
                            }
                        label_169:
                            ++v2;
                            v24 = v28;
                        }
                        ++v2;
                        v26 = v21;
                        v27 = v3;
                        goto label_242;
                    }
                    catch(RuntimeException runtimeException0) {
                        break;
                    }
                label_176:
                    if(v7 == 0) {
                        v29 = v2;
                        int v32 = 0;
                        try {
                        alab3:
                            while(true) {
                                int v33 = arr_c[v29];
                                if(v33 == 10 || v33 == 13) {
                                    goto label_205;
                                }
                                switch(v33) {
                                    case 0x2F: {
                                        if(v29 + 1 != v1) {
                                            int v34 = arr_c[v29 + 1];
                                            if(v34 == 0x2F || v34 == 42) {
                                                goto label_205;
                                            }
                                            else {
                                                goto label_200;
                                            }
                                            goto label_199;
                                        }
                                        goto label_200;
                                    }
                                    case 92: {
                                    label_199:
                                        v32 = 1;
                                    label_200:
                                        ++v29;
                                        if(v29 != v1) {
                                            break;
                                        }
                                        break alab3;
                                    }
                                    case 44: 
                                    case 93: 
                                    case 0x7D: {
                                        goto label_205;
                                    }
                                    default: {
                                        goto label_200;
                                    }
                                }
                            }
                            v6 = v32;
                            v30 = v29;
                            goto label_207;
                        label_205:
                            v6 = v32;
                            v30 = v29;
                        }
                        catch(RuntimeException runtimeException1) {
                            runtimeException3 = runtimeException1;
                            v2 = v29;
                            break alab2;
                        }
                    }
                    else {
                        v30 = v2;
                        v6 = 0;
                        try {
                        alab4:
                            while(true) {
                                switch(arr_c[v30]) {
                                    case 0x2F: {
                                        if(v30 + 1 != v1) {
                                            int v31 = arr_c[v30 + 1];
                                            if(v31 != 0x2F && v31 != 42) {
                                                goto label_186;
                                            }
                                            break alab4;
                                        }
                                    label_186:
                                        ++v30;
                                        if(v30 != v1) {
                                            break;
                                        }
                                        break alab4;
                                    }
                                    case 10: 
                                    case 13: 
                                    case 58: {
                                        break alab4;
                                    }
                                    case 92: {
                                        v6 = 1;
                                        goto label_186;
                                    }
                                    default: {
                                        goto label_186;
                                    }
                                }
                            }
                        }
                        catch(RuntimeException runtimeException2) {
                            runtimeException3 = runtimeException2;
                            v2 = v30;
                            break alab2;
                        }
                    }
                    try {
                    label_207:
                        --v30;
                        while(Character.isSpace(arr_c[v30])) {
                            --v30;
                        }
                    }
                    catch(RuntimeException runtimeException2) {
                        runtimeException3 = runtimeException2;
                        v2 = v30;
                        break alab2;
                    }
                    v26 = v21;
                    v27 = v3;
                    v28 = v24;
                    z = true;
                    v5 = v2;
                    v2 = v30;
                    goto label_242;
                label_222:
                    v29 = v2 + 1;
                    int v35 = 0;
                    while(true) {
                        try {
                        label_224:
                            switch(arr_c[v29]) {
                                case 34: {
                                    v6 = v35;
                                    goto label_237;
                                }
                                case 92: {
                                    goto label_232;
                                }
                                default: {
                                    goto label_234;
                                }
                            }
                        }
                        catch(RuntimeException runtimeException1) {
                            try {
                                runtimeException3 = runtimeException1;
                                v2 = v29;
                                break alab2;
                            label_232:
                                ++v29;
                                v35 = 1;
                            label_234:
                                ++v29;
                                if(v29 != v1) {
                                    goto label_224;
                                }
                                v6 = v35;
                            label_237:
                                v26 = v21;
                                v27 = v3;
                                v28 = v24;
                                v5 = v2 + 1;
                                v2 = v29 - 1;
                            label_242:
                                v3 = v27;
                                ++v22;
                                v23 = v28;
                                v21 = v26;
                                goto label_52;
                            label_250:
                                v25 = v3;
                            label_251:
                                if(v4 != 0) {
                                    ++v2;
                                    if(v2 != v1) {
                                        v3 = v25;
                                        v8 = 1;
                                        continue alab2;
                                    }
                                label_254:
                                    if(v2 == v1) {
                                        int v36 = JsonReader._json_eof_actions[v4] + 1;
                                        int v37 = JsonReader._json_actions[JsonReader._json_eof_actions[v4]];
                                        int v38 = v7;
                                        while(v37 > 0) {
                                            if(JsonReader._json_actions[v36] == 1) {
                                                String s2 = new String(arr_c, v5, v2 - v5);
                                                if(v6 != 0) {
                                                    s2 = this.unescape(s2);
                                                }
                                                if(v38 == 0) {
                                                    String s3 = array0.size <= 0 ? null : ((String)array0.pop());
                                                    if(z) {
                                                        if(s2.equals("true")) {
                                                            this.bool(s3, true);
                                                            goto label_296;
                                                        }
                                                        else if(s2.equals("false")) {
                                                            this.bool(s3, false);
                                                            goto label_296;
                                                        }
                                                        else if(!s2.equals("null")) {
                                                            boolean z3 = false;
                                                            boolean z4 = true;
                                                            while(v5 < v2) {
                                                                switch(arr_c[v5]) {
                                                                    case 43: 
                                                                    case 45: 
                                                                    case 0x30: 
                                                                    case 49: 
                                                                    case 50: 
                                                                    case 51: 
                                                                    case 52: 
                                                                    case 53: 
                                                                    case 54: 
                                                                    case 55: 
                                                                    case 56: 
                                                                    case 57: {
                                                                        goto label_278;
                                                                    }
                                                                    case 46: 
                                                                    case 69: 
                                                                    case 101: {
                                                                        goto label_276;
                                                                    }
                                                                }
                                                                z3 = false;
                                                                z4 = false;
                                                                break;
                                                            label_276:
                                                                z3 = true;
                                                                z4 = false;
                                                            label_278:
                                                                ++v5;
                                                            }
                                                            try {
                                                                if(z3) {
                                                                    this.number(s3, Double.parseDouble(s2), s2);
                                                                    goto label_296;
                                                                }
                                                                else if(z4) {
                                                                    this.number(s3, Long.parseLong(s2), s2);
                                                                    goto label_296;
                                                                }
                                                            }
                                                            catch(NumberFormatException unused_ex) {
                                                            }
                                                        }
                                                        else {
                                                            this.string(s3, null);
                                                            goto label_296;
                                                        }
                                                    }
                                                    this.string(s3, s2);
                                                }
                                                else {
                                                    array0.add(s2);
                                                    v38 = 0;
                                                }
                                            label_296:
                                                v5 = v2;
                                                z = false;
                                            }
                                            --v37;
                                            ++v36;
                                        }
                                        runtimeException3 = null;
                                        break alab2;
                                    }
                                }
                                runtimeException3 = null;
                                break alab2;
                            }
                            catch(RuntimeException runtimeException0) {
                            }
                            break label_9;
                        }
                    }
                }
                case 2: {
                    goto label_250;
                }
                default: {
                    runtimeException3 = null;
                    break alab2;
                }
            }
            runtimeException3 = runtimeException0;
            break;
        }
        JsonValue jsonValue0 = this.root;
        this.root = null;
        this.current = null;
        this.lastChild.clear();
        if(v2 < v1) {
            int v40 = 1;
            for(int v39 = 0; v39 < v2; ++v39) {
                if(arr_c[v39] == 10) {
                    ++v40;
                }
            }
            int v41 = Math.max(0, v2 - 0x20);
            throw new SerializationException("Error parsing JSON on line " + v40 + " near: " + new String(arr_c, v41, v2 - v41) + "*ERROR*" + new String(arr_c, v2, Math.min(0x40, v1 - v2)), runtimeException3);
        }
        if(this.elements.size != 0) {
            JsonValue jsonValue1 = (JsonValue)this.elements.peek();
            this.elements.clear();
            throw jsonValue1 == null || !jsonValue1.isObject() ? new SerializationException("Error parsing JSON, unmatched bracket.") : new SerializationException("Error parsing JSON, unmatched brace.");
        }
        if(runtimeException3 != null) {
            throw new SerializationException("Error parsing JSON: " + new String(arr_c), runtimeException3);
        }
        return jsonValue0;
    }

    protected void pop() {
        this.root = (JsonValue)this.elements.pop();
        if(this.current.size > 0) {
            this.lastChild.pop();
        }
        this.current = this.elements.size <= 0 ? null : ((JsonValue)this.elements.peek());
    }

    protected void startArray(@Null String s) {
        JsonValue jsonValue0 = new JsonValue(ValueType.array);
        if(this.current != null) {
            this.addChild(s, jsonValue0);
        }
        this.elements.add(jsonValue0);
        this.current = jsonValue0;
    }

    protected void startObject(@Null String s) {
        JsonValue jsonValue0 = new JsonValue(ValueType.object);
        if(this.current != null) {
            this.addChild(s, jsonValue0);
        }
        this.elements.add(jsonValue0);
        this.current = jsonValue0;
    }

    protected void string(String s, String s1) {
        this.addChild(s, new JsonValue(s1));
    }

    private String unescape(String s) {
        int v = s.length();
        StringBuilder stringBuilder0 = new StringBuilder(v + 16);
        int v1 = 0;
        while(v1 < v) {
            int v2 = v1 + 1;
            int v3 = s.charAt(v1);
            if(v3 == 92) {
                if(v2 == v) {
                    break;
                }
                v1 = v2 + 1;
                int v4 = s.charAt(v2);
                switch(v4) {
                    case 34: 
                    case 0x2F: 
                    case 92: {
                        break;
                    }
                    case 98: {
                        v4 = 8;
                        break;
                    }
                    case 102: {
                        v4 = 12;
                        break;
                    }
                    case 110: {
                        v4 = 10;
                        break;
                    }
                    case 0x72: {
                        v4 = 13;
                        break;
                    }
                    case 0x74: {
                        v4 = 9;
                        break;
                    }
                    case 0x75: {
                        stringBuilder0.append(Character.toChars(Integer.parseInt(s.substring(v1, v1 + 4), 16)));
                        v1 += 4;
                        continue;
                    }
                    default: {
                        throw new SerializationException("Illegal escaped character: \\" + ((char)v4));
                    }
                }
                stringBuilder0.append(((char)v4));
            }
            else {
                stringBuilder0.append(((char)v3));
                v1 = v2;
            }
        }
        return "";
    }
}

