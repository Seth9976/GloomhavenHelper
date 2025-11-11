package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class XmlReader {
    public static class Element {
        private ObjectMap attributes;
        private Array children;
        private final String name;
        private Element parent;
        private String text;

        public Element(String s, Element xmlReader$Element0) {
            this.name = s;
            this.parent = xmlReader$Element0;
        }

        public void addChild(Element xmlReader$Element0) {
            if(this.children == null) {
                this.children = new Array(8);
            }
            this.children.add(xmlReader$Element0);
        }

        public String get(String s) {
            String s1 = this.get(s, null);
            if(s1 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute or child: " + s);
            }
            return s1;
        }

        public String get(String s, String s1) {
            ObjectMap objectMap0 = this.attributes;
            if(objectMap0 != null) {
                String s2 = (String)objectMap0.get(s);
                if(s2 != null) {
                    return s2;
                }
            }
            Element xmlReader$Element0 = this.getChildByName(s);
            if(xmlReader$Element0 == null) {
                return s1;
            }
            String s3 = xmlReader$Element0.getText();
            return s3 == null ? s1 : s3;
        }

        public String getAttribute(String s) {
            ObjectMap objectMap0 = this.attributes;
            if(objectMap0 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute: " + s);
            }
            String s1 = (String)objectMap0.get(s);
            if(s1 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute: " + s);
            }
            return s1;
        }

        public String getAttribute(String s, String s1) {
            ObjectMap objectMap0 = this.attributes;
            if(objectMap0 == null) {
                return s1;
            }
            String s2 = (String)objectMap0.get(s);
            return s2 == null ? s1 : s2;
        }

        public ObjectMap getAttributes() {
            return this.attributes;
        }

        public boolean getBoolean(String s) {
            String s1 = this.get(s, null);
            if(s1 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute or child: " + s);
            }
            return Boolean.parseBoolean(s1);
        }

        public boolean getBoolean(String s, boolean z) {
            String s1 = this.get(s, null);
            return s1 == null ? z : Boolean.parseBoolean(s1);
        }

        public boolean getBooleanAttribute(String s) {
            return Boolean.parseBoolean(this.getAttribute(s));
        }

        public boolean getBooleanAttribute(String s, boolean z) {
            String s1 = this.getAttribute(s, null);
            return s1 == null ? z : Boolean.parseBoolean(s1);
        }

        public Element getChild(int v) {
            Array array0 = this.children;
            if(array0 == null) {
                throw new GdxRuntimeException("Element has no children: " + this.name);
            }
            return (Element)array0.get(v);
        }

        @Null
        public Element getChildByName(String s) {
            if(this.children == null) {
                return null;
            }
            for(int v = 0; v < this.children.size; ++v) {
                Element xmlReader$Element0 = (Element)this.children.get(v);
                if(xmlReader$Element0.name.equals(s)) {
                    return xmlReader$Element0;
                }
            }
            return null;
        }

        @Null
        public Element getChildByNameRecursive(String s) {
            if(this.children == null) {
                return null;
            }
            for(int v = 0; v < this.children.size; ++v) {
                Element xmlReader$Element0 = (Element)this.children.get(v);
                if(xmlReader$Element0.name.equals(s)) {
                    return xmlReader$Element0;
                }
                Element xmlReader$Element1 = xmlReader$Element0.getChildByNameRecursive(s);
                if(xmlReader$Element1 != null) {
                    return xmlReader$Element1;
                }
            }
            return null;
        }

        public int getChildCount() {
            return this.children == null ? 0 : this.children.size;
        }

        public Array getChildrenByName(String s) {
            Array array0 = new Array();
            if(this.children == null) {
                return array0;
            }
            for(int v = 0; v < this.children.size; ++v) {
                Element xmlReader$Element0 = (Element)this.children.get(v);
                if(xmlReader$Element0.name.equals(s)) {
                    array0.add(xmlReader$Element0);
                }
            }
            return array0;
        }

        private void getChildrenByNameRecursively(String s, Array array0) {
            if(this.children == null) {
                return;
            }
            for(int v = 0; v < this.children.size; ++v) {
                Element xmlReader$Element0 = (Element)this.children.get(v);
                if(xmlReader$Element0.name.equals(s)) {
                    array0.add(xmlReader$Element0);
                }
                xmlReader$Element0.getChildrenByNameRecursively(s, array0);
            }
        }

        public Array getChildrenByNameRecursively(String s) {
            Array array0 = new Array();
            this.getChildrenByNameRecursively(s, array0);
            return array0;
        }

        public float getFloat(String s) {
            String s1 = this.get(s, null);
            if(s1 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute or child: " + s);
            }
            return Float.parseFloat(s1);
        }

        public float getFloat(String s, float f) {
            String s1 = this.get(s, null);
            return s1 == null ? f : Float.parseFloat(s1);
        }

        public float getFloatAttribute(String s) {
            return Float.parseFloat(this.getAttribute(s));
        }

        public float getFloatAttribute(String s, float f) {
            String s1 = this.getAttribute(s, null);
            return s1 == null ? f : Float.parseFloat(s1);
        }

        public int getInt(String s) {
            String s1 = this.get(s, null);
            if(s1 == null) {
                throw new GdxRuntimeException("Element " + this.name + " doesn\'t have attribute or child: " + s);
            }
            return Integer.parseInt(s1);
        }

        public int getInt(String s, int v) {
            String s1 = this.get(s, null);
            return s1 == null ? v : Integer.parseInt(s1);
        }

        public int getIntAttribute(String s) {
            return Integer.parseInt(this.getAttribute(s));
        }

        public int getIntAttribute(String s, int v) {
            String s1 = this.getAttribute(s, null);
            return s1 == null ? v : Integer.parseInt(s1);
        }

        public String getName() {
            return this.name;
        }

        public Element getParent() {
            return this.parent;
        }

        public String getText() {
            return this.text;
        }

        public boolean hasAttribute(String s) {
            return this.attributes == null ? false : this.attributes.containsKey(s);
        }

        public boolean hasChild(String s) {
            return this.children == null ? false : this.getChildByName(s) != null;
        }

        public boolean hasChildRecursive(String s) {
            return this.children == null ? false : this.getChildByNameRecursive(s) != null;
        }

        public void remove() {
            this.parent.removeChild(this);
        }

        public void removeChild(int v) {
            Array array0 = this.children;
            if(array0 != null) {
                array0.removeIndex(v);
            }
        }

        public void removeChild(Element xmlReader$Element0) {
            Array array0 = this.children;
            if(array0 != null) {
                array0.removeValue(xmlReader$Element0, true);
            }
        }

        public void setAttribute(String s, String s1) {
            if(this.attributes == null) {
                this.attributes = new ObjectMap(8);
            }
            this.attributes.put(s, s1);
        }

        public void setText(String s) {
            this.text = s;
        }

        @Override
        public String toString() {
            return this.toString("");
        }

        public String toString(String s) {
            StringBuilder stringBuilder0 = new StringBuilder(0x80);
            stringBuilder0.append(s);
            stringBuilder0.append('<');
            stringBuilder0.append(this.name);
            ObjectMap objectMap0 = this.attributes;
            if(objectMap0 != null) {
                for(Object object0: objectMap0.entries()) {
                    stringBuilder0.append(' ');
                    stringBuilder0.append(((String)((Entry)object0).key));
                    stringBuilder0.append("=\"");
                    stringBuilder0.append(((String)((Entry)object0).value));
                    stringBuilder0.append('\"');
                }
            }
            if(this.children == null && (this.text == null || this.text.length() == 0)) {
                stringBuilder0.append("/>");
                return "";
            }
            stringBuilder0.append(">\n");
            if(this.text != null && this.text.length() > 0) {
                stringBuilder0.append(s + '\t');
                stringBuilder0.append(this.text);
                stringBuilder0.append('\n');
            }
            Array array0 = this.children;
            if(array0 != null) {
                for(Object object1: array0) {
                    stringBuilder0.append(((Element)object1).toString(s + '\t'));
                    stringBuilder0.append('\n');
                }
            }
            stringBuilder0.append(s);
            stringBuilder0.append("</");
            stringBuilder0.append(this.name);
            stringBuilder0.append('>');
            return "";
        }
    }

    private static final byte[] _xml_actions = null;
    private static final short[] _xml_index_offsets = null;
    private static final byte[] _xml_indicies = null;
    private static final byte[] _xml_key_offsets = null;
    private static final byte[] _xml_range_lengths = null;
    private static final byte[] _xml_single_lengths = null;
    private static final byte[] _xml_trans_actions = null;
    private static final char[] _xml_trans_keys = null;
    private static final byte[] _xml_trans_targs = null;
    private Element current;
    private final Array elements;
    private Element root;
    private final StringBuilder textBuffer;
    static final int xml_en_elementBody = 15;
    static final int xml_en_main = 1;
    static final int xml_error = 0;
    static final int xml_first_final = 34;
    static final int xml_start = 1;

    static {
        XmlReader._xml_actions = new byte[]{0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 2, 0, 6, 2, 1, 4, 2, 2, 4};
        XmlReader._xml_key_offsets = new byte[]{0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 0x4F, 83, 88, 89, 90, 0x5F, 99, 103, 104, 108, 109, 110, 0x6F, 0x70, 0x73};
        XmlReader._xml_trans_keys = XmlReader.init__xml_trans_keys_0();
        XmlReader._xml_single_lengths = new byte[]{0, 2, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 1, 2, 1, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 2, 1, 1, 1, 1, 1, 0};
        XmlReader._xml_range_lengths = new byte[]{0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0};
        XmlReader._xml_index_offsets = XmlReader.init__xml_index_offsets_0();
        XmlReader._xml_indicies = new byte[]{0, 2, 0, 1, 2, 1, 1, 2, 3, 5, 6, 7, 5, 4, 9, 10, 1, 11, 9, 8, 13, 1, 14, 1, 13, 12, 15, 16, 15, 1, 16, 17, 18, 16, 1, 20, 19, 22, 21, 9, 10, 11, 9, 1, 23, 24, 23, 1, 25, 11, 25, 1, 20, 26, 22, 27, 29, 30, 29, 28, 0x20, 0x1F, 30, 34, 1, 30, 33, 36, 37, 38, 36, 35, 40, 41, 1, 42, 40, 39, 44, 1, 45, 1, 44, 43, 46, 0x2F, 46, 1, 0x2F, 0x30, 49, 0x2F, 1, 51, 50, 53, 52, 40, 41, 42, 40, 1, 54, 55, 54, 1, 56, 42, 56, 1, 57, 1, 57, 34, 57, 1, 1, 58, 59, 58, 51, 60, 53, 61, 62, 62, 1, 1, 0};
        XmlReader._xml_trans_targs = new byte[]{1, 0, 2, 3, 3, 4, 11, 34, 5, 4, 11, 34, 5, 6, 7, 6, 7, 8, 13, 9, 10, 9, 10, 12, 34, 12, 14, 14, 16, 15, 17, 16, 17, 18, 30, 18, 19, 26, 28, 20, 19, 26, 28, 20, 21, 22, 21, 22, 23, 0x20, 24, 25, 24, 25, 27, 28, 27, 29, 0x1F, 35, 33, 33, 34};
        XmlReader._xml_trans_actions = new byte[]{0, 0, 0, 1, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 1, 0, 1, 0, 0, 0, 15, 1, 0, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 0, 0, 7, 1, 0, 0};
    }

    public XmlReader() {
        this.elements = new Array(8);
        this.textBuffer = new StringBuilder(0x40);
    }

    protected void attribute(String s, String s1) {
        this.current.setAttribute(s, s1);
    }

    protected void close() {
        this.root = (Element)this.elements.pop();
        this.current = this.elements.size <= 0 ? null : ((Element)this.elements.peek());
    }

    @Null
    protected String entity(String s) {
        if(s.equals("lt")) {
            return "<";
        }
        if(s.equals("gt")) {
            return ">";
        }
        if(s.equals("amp")) {
            return "&";
        }
        if(s.equals("apos")) {
            return "\'";
        }
        if(s.equals("quot")) {
            return "\"";
        }
        return s.startsWith("#x") ? Character.toString(((char)Integer.parseInt(s.substring(2), 16))) : null;
    }

    private static byte[] init__xml_actions_0() {
        return new byte[]{0, 1, 0, 1, 1, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 2, 0, 6, 2, 1, 4, 2, 2, 4};
    }

    private static short[] init__xml_index_offsets_0() {
        return new short[]{0, 0, 4, 9, 14, 20, 26, 30, 35, 37, 39, 44, 0x30, 52, 54, 56, 60, 62, 67, 72, 78, 84, 88, 93, 0x5F, 97, 102, 106, 110, 0x70, 0x74, 0x76, 120, 0x7A, 0x7C, 0x7F};
    }

    private static byte[] init__xml_indicies_0() {
        return new byte[]{0, 2, 0, 1, 2, 1, 1, 2, 3, 5, 6, 7, 5, 4, 9, 10, 1, 11, 9, 8, 13, 1, 14, 1, 13, 12, 15, 16, 15, 1, 16, 17, 18, 16, 1, 20, 19, 22, 21, 9, 10, 11, 9, 1, 23, 24, 23, 1, 25, 11, 25, 1, 20, 26, 22, 27, 29, 30, 29, 28, 0x20, 0x1F, 30, 34, 1, 30, 33, 36, 37, 38, 36, 35, 40, 41, 1, 42, 40, 39, 44, 1, 45, 1, 44, 43, 46, 0x2F, 46, 1, 0x2F, 0x30, 49, 0x2F, 1, 51, 50, 53, 52, 40, 41, 42, 40, 1, 54, 55, 54, 1, 56, 42, 56, 1, 57, 1, 57, 34, 57, 1, 1, 58, 59, 58, 51, 60, 53, 61, 62, 62, 1, 1, 0};
    }

    private static byte[] init__xml_key_offsets_0() {
        return new byte[]{0, 0, 4, 9, 14, 20, 26, 30, 35, 36, 37, 42, 46, 50, 51, 52, 56, 57, 62, 67, 73, 0x4F, 83, 88, 89, 90, 0x5F, 99, 103, 104, 108, 109, 110, 0x6F, 0x70, 0x73};
    }

    private static byte[] init__xml_range_lengths_0() {
        return new byte[]{0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0};
    }

    private static byte[] init__xml_single_lengths_0() {
        return new byte[]{0, 2, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 1, 2, 1, 3, 3, 4, 4, 2, 3, 1, 1, 3, 2, 2, 1, 2, 1, 1, 1, 1, 1, 0};
    }

    private static byte[] init__xml_trans_actions_0() {
        return new byte[]{0, 0, 0, 1, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 1, 0, 1, 0, 0, 0, 15, 1, 0, 0, 3, 3, 20, 1, 0, 0, 9, 0, 11, 11, 0, 0, 0, 0, 1, 17, 0, 13, 5, 23, 0, 0, 0, 7, 1, 0, 0};
    }

    private static char[] init__xml_trans_keys_0() {
        return new char[]{' ', '<', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '\'', '\'', ' ', '<', '\t', '\r', '<', ' ', '/', '>', '\t', '\r', ' ', '/', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '/', '=', '>', '\t', '\r', ' ', '=', '\t', '\r', ' ', '\"', '\'', '\t', '\r', '\"', '\"', ' ', '/', '>', '\t', '\r', ' ', '>', '\t', '\r', ' ', '>', '\t', '\r', '<', ' ', '/', '\t', '\r', '>', '>', '\'', '\'', ' ', '\t', '\r', '\u0000'};
    }

    private static byte[] init__xml_trans_targs_0() {
        return new byte[]{1, 0, 2, 3, 3, 4, 11, 34, 5, 4, 11, 34, 5, 6, 7, 6, 7, 8, 13, 9, 10, 9, 10, 12, 34, 12, 14, 14, 16, 15, 17, 16, 17, 18, 30, 18, 19, 26, 28, 20, 19, 26, 28, 20, 21, 22, 21, 22, 23, 0x20, 24, 25, 24, 25, 27, 28, 27, 29, 0x1F, 35, 33, 33, 34};
    }

    protected void open(String s) {
        Element xmlReader$Element0 = new Element(s, this.current);
        Element xmlReader$Element1 = this.current;
        if(xmlReader$Element1 != null) {
            xmlReader$Element1.addChild(xmlReader$Element0);
        }
        this.elements.add(xmlReader$Element0);
        this.current = xmlReader$Element0;
    }

    public Element parse(FileHandle fileHandle0) {
        try {
            return this.parse(fileHandle0.reader("UTF-8"));
        }
        catch(Exception exception0) {
            throw new SerializationException("Error parsing file: " + fileHandle0, exception0);
        }
    }

    public Element parse(InputStream inputStream0) {
        try {
            return this.parse(new InputStreamReader(inputStream0, "UTF-8"));
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
        }
    }

    public Element parse(Reader reader0) {
        Element xmlReader$Element0;
        int v2;
        int v1;
        char[] arr_c;
        try {
            arr_c = new char[0x400];
            v1 = 0;
        alab1:
            while(true) {
            label_3:
                v2 = reader0.read(arr_c, v1, arr_c.length - v1);
                switch(v2) {
                    case -1: {
                        xmlReader$Element0 = this.parse(arr_c, 0, v1);
                        break alab1;
                    }
                    case 0: {
                        goto label_13;
                    }
                    default: {
                        v1 += v2;
                    }
                }
            }
            return xmlReader$Element0;
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        finally {
            StreamUtils.closeQuietly(reader0);
        }
        try {
            v1 += v2;
            goto label_3;
        label_13:
            char[] arr_c1 = new char[arr_c.length * 2];
            System.arraycopy(arr_c, 0, arr_c1, 0, arr_c.length);
            arr_c = arr_c1;
            goto label_3;
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public Element parse(String s) {
        char[] arr_c = s.toCharArray();
        return this.parse(arr_c, 0, arr_c.length);
    }

    // This method was un-flattened
    public Element parse(char[] arr_c, int v, int v1) {
        int v17;
        int v10;
        int v7;
        int v2 = 1;
        String s = null;
        int v3 = 0;
        boolean z = false;
        if(v != v1) {
            while(true) {
                int v4 = XmlReader._xml_key_offsets[v2];
                int v5 = XmlReader._xml_index_offsets[v2];
                int v6 = XmlReader._xml_single_lengths[v2];
                if(v6 > 0) {
                    v7 = v4 + v6;
                    int v8 = v4;
                    int v9 = v7 - 1;
                    while(true) {
                        if(v9 < v8) {
                            v5 += v6;
                            goto label_26;
                        }
                        v10 = v8 + (v9 - v8 >> 1);
                        char[] arr_c1 = XmlReader._xml_trans_keys;
                        if(arr_c[v] < arr_c1[v10]) {
                            v9 = v10 - 1;
                        }
                        else {
                            if(arr_c[v] <= arr_c1[v10]) {
                                break;
                            }
                            v8 = v10 + 1;
                        }
                    }
                    v5 += v10 - v4;
                    goto label_43;
                }
                else {
                    v7 = v4;
                }
            label_26:
                int v11 = XmlReader._xml_range_lengths[v2];
                if(v11 > 0) {
                    int v12 = v7 + (v11 << 1) - 2;
                    int v13 = v7;
                    while(true) {
                        if(v12 < v13) {
                            v5 += v11;
                            break;
                        }
                        int v14 = (v12 - v13 >> 1 & -2) + v13;
                        char[] arr_c2 = XmlReader._xml_trans_keys;
                        if(arr_c[v] < arr_c2[v14]) {
                            v12 = v14 - 2;
                        }
                        else {
                            if(arr_c[v] <= arr_c2[v14 + 1]) {
                                v5 += v14 - v7 >> 1;
                                break;
                            }
                            v13 = v14 + 2;
                        }
                    }
                }
            label_43:
                int v15 = XmlReader._xml_indicies[v5];
                int v16 = XmlReader._xml_trans_targs[v15];
                byte[] arr_b = XmlReader._xml_trans_actions;
                if(arr_b[v15] != 0) {
                    v17 = 0;
                    int v18 = arr_b[v15];
                    int v19 = v18 + 1;
                    int v20 = XmlReader._xml_actions[v18];
                alab1:
                    while(v20 > 0) {
                        switch(XmlReader._xml_actions[v19]) {
                            case 0: {
                                v3 = v;
                                break;
                            }
                            case 1: {
                                int v21 = arr_c[v3];
                                if(v21 == 33 || v21 == 0x3F) {
                                    if(arr_c[v3 + 1] == 91 && arr_c[v3 + 2] == 67 && arr_c[v3 + 3] == 68 && arr_c[v3 + 4] == 65 && arr_c[v3 + 5] == 84 && arr_c[v3 + 6] == 65 && arr_c[v3 + 7] == 91) {
                                        int v22;
                                        for(v22 = v3 + 10; arr_c[v22 - 2] != 93 || arr_c[v22 - 1] != 93 || arr_c[v22] != 62; ++v22) {
                                        }
                                        this.text(new String(arr_c, v3 + 8, v22 - v3 - 10));
                                        v = v22;
                                        v3 += 8;
                                    }
                                    else if(v21 != 33 || arr_c[v3 + 1] != 45 || arr_c[v3 + 2] != 45) {
                                        while(arr_c[v] != 62) {
                                            ++v;
                                        }
                                    }
                                    else {
                                        int v23;
                                        for(v23 = v3 + 3; arr_c[v23] != 45 || arr_c[v23 + 1] != 45 || arr_c[v23 + 2] != 62; ++v23) {
                                        }
                                        v = v23 + 2;
                                    }
                                    v16 = 15;
                                    break alab1;
                                }
                                else {
                                    this.open(new String(arr_c, v3, v - v3));
                                    z = true;
                                    break;
                                }
                                this.close();
                                z = false;
                                v16 = 15;
                                break alab1;
                            }
                            case 2: {
                                this.close();
                                z = false;
                                v16 = 15;
                                break alab1;
                            }
                            case 3: {
                                this.close();
                                v16 = 15;
                                break alab1;
                            }
                            case 4: {
                                if(z) {
                                    v16 = 15;
                                    break alab1;
                                }
                                break;
                            }
                            case 5: {
                                s = new String(arr_c, v3, v - v3);
                                break;
                            }
                            case 6: {
                                this.attribute(s, new String(arr_c, v3, v - v3));
                                break;
                            }
                            case 7: {
                                int v24;
                                for(v24 = v; v24 != v3; --v24) {
                                    int v25 = arr_c[v24 - 1];
                                    if(v25 != 13 && v25 != 0x20 && (v25 != 9 && v25 != 10)) {
                                        break;
                                    }
                                }
                                int v26 = v3;
                                boolean z1 = false;
                                while(v3 != v24) {
                                    int v27 = v3 + 1;
                                    if(arr_c[v3] == 38) {
                                        for(v3 = v27; v3 != v24; ++v3) {
                                            if(arr_c[v3] == 59) {
                                                this.textBuffer.append(arr_c, v26, v27 - v26 - 1);
                                                String s1 = new String(arr_c, v27, v3 + 1 - v27 - 1);
                                                String s2 = this.entity(s1);
                                                StringBuilder stringBuilder0 = this.textBuffer;
                                                if(s2 != null) {
                                                    s1 = s2;
                                                }
                                                stringBuilder0.append(s1);
                                                v26 = v3 + 1;
                                                v3 = v26;
                                                z1 = true;
                                                break;
                                            }
                                        }
                                    }
                                    else {
                                        v3 = v27;
                                    }
                                }
                                if(z1) {
                                    if(v26 < v24) {
                                        this.textBuffer.append(arr_c, v26, v24 - v26);
                                    }
                                    this.text("");
                                    this.textBuffer.setLength(0);
                                }
                                else {
                                    this.text(new String(arr_c, v26, v24 - v26));
                                }
                                v3 = v26;
                            }
                        }
                        --v20;
                        ++v19;
                    }
                }
                if(v16 == 0) {
                    break;
                }
                else {
                    ++v;
                    if(v != v1) {
                        v2 = v16;
                        continue;
                    }
                }
                goto label_144;
            }
        }
        v17 = 0;
    label_144:
        if(v < v1) {
            int v28 = 1;
            while(v17 < v) {
                if(arr_c[v17] == 10) {
                    ++v28;
                }
                ++v17;
            }
            throw new SerializationException("Error parsing XML on line " + v28 + " near: " + new String(arr_c, v, Math.min(0x20, v1 - v)));
        }
        if(this.elements.size == 0) {
            Element xmlReader$Element0 = this.root;
            this.root = null;
            return xmlReader$Element0;
        }
        Element xmlReader$Element1 = (Element)this.elements.peek();
        this.elements.clear();
        throw new SerializationException("Error parsing XML, unclosed element: " + xmlReader$Element1.getName());
    }

    protected void text(String s) {
        String s1 = this.current.getText();
        Element xmlReader$Element0 = this.current;
        if(s1 != null) {
            s = s1 + s;
        }
        xmlReader$Element0.setText(s);
    }
}

