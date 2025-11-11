package com.badlogic.gdx.utils;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.regex.Pattern;

public class JsonWriter extends Writer {
    class JsonObject {
        final boolean array;
        boolean needsComma;

        JsonObject(boolean z) throws IOException {
            this.array = z;
            jsonWriter0.writer.write((z ? 91 : 0x7B));
        }

        void close() throws IOException {
            JsonWriter.this.writer.write((this.array ? 93 : 0x7D));
        }
    }

    public static enum OutputType {
        json,
        javascript,
        minimal;

        private static Pattern javascriptPattern;
        private static Pattern minimalNamePattern;
        private static Pattern minimalValuePattern;

        static {
            OutputType.javascriptPattern = Pattern.compile("^[a-zA-Z_$][a-zA-Z_$0-9]*$");
            OutputType.minimalNamePattern = Pattern.compile("^[^\":,}/ ][^:]*$");
            OutputType.minimalValuePattern = Pattern.compile("^[^\":,{\\[\\]/ ][^}\\],]*$");
        }

        public String quoteName(String s) {
            StringBuilder stringBuilder0 = new StringBuilder(s);
            stringBuilder0.replace('\\', "\\\\").replace('\r', "\\r").replace('\n', "\\n").replace('\t', "\\t");
            switch(this) {
                case minimal: {
                    if(!s.contains("//") && !s.contains("/*") && OutputType.minimalNamePattern.matcher(stringBuilder0).matches()) {
                        return "";
                    }
                    break;
                }
                case javascript: {
                    break;
                }
                default: {
                    return '\"' + "" + '\"';
                }
            }
            return OutputType.javascriptPattern.matcher(stringBuilder0).matches() ? "" : '\"' + "" + '\"';
        }

        public String quoteValue(Object object0) {
            if(object0 == null) {
                return "null";
            }
            String s = object0.toString();
            if(!(object0 instanceof Number) && !(object0 instanceof Boolean)) {
                StringBuilder stringBuilder0 = new StringBuilder(s);
                stringBuilder0.replace('\\', "\\\\").replace('\r', "\\r").replace('\n', "\\n").replace('\t', "\\t");
                if(this == OutputType.minimal && !s.equals("true") && !s.equals("false") && !s.equals("null") && !s.contains("//") && !s.contains("/*")) {
                    int v = stringBuilder0.length();
                    return v <= 0 || stringBuilder0.charAt(v - 1) == 0x20 || !OutputType.minimalValuePattern.matcher(stringBuilder0).matches() ? '\"' + "" + '\"' : "";
                }
                return '\"' + "" + '\"';
            }
            return s;
        }
    }

    private JsonObject current;
    private boolean named;
    private OutputType outputType;
    private boolean quoteLongValues;
    private final Array stack;
    final Writer writer;

    public JsonWriter(Writer writer0) {
        this.stack = new Array();
        this.outputType = OutputType.json;
        this.quoteLongValues = false;
        this.writer = writer0;
    }

    public JsonWriter array() throws IOException {
        this.requireCommaOrName();
        JsonObject jsonWriter$JsonObject0 = new JsonObject(this, true);
        this.current = jsonWriter$JsonObject0;
        this.stack.add(jsonWriter$JsonObject0);
        return this;
    }

    public JsonWriter array(String s) throws IOException {
        return this.name(s).array();
    }

    @Override
    public void close() throws IOException {
        while(this.stack.size > 0) {
            this.pop();
        }
        this.writer.close();
    }

    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }

    public Writer getWriter() {
        return this.writer;
    }

    public JsonWriter json(String s) throws IOException {
        this.requireCommaOrName();
        this.writer.write(s);
        return this;
    }

    public JsonWriter json(String s, String s1) throws IOException {
        return this.name(s).json(s1);
    }

    public JsonWriter name(String s) throws IOException {
        if(this.current == null || this.current.array) {
            throw new IllegalStateException("Current item must be an object.");
        }
        if(this.current.needsComma) {
            this.writer.write(44);
        }
        else {
            this.current.needsComma = true;
        }
        String s1 = this.outputType.quoteName(s);
        this.writer.write(s1);
        this.writer.write(58);
        this.named = true;
        return this;
    }

    public JsonWriter object() throws IOException {
        this.requireCommaOrName();
        JsonObject jsonWriter$JsonObject0 = new JsonObject(this, false);
        this.current = jsonWriter$JsonObject0;
        this.stack.add(jsonWriter$JsonObject0);
        return this;
    }

    public JsonWriter object(String s) throws IOException {
        return this.name(s).object();
    }

    public JsonWriter pop() throws IOException {
        if(this.named) {
            throw new IllegalStateException("Expected an object, array, or value since a name was set.");
        }
        ((JsonObject)this.stack.pop()).close();
        this.current = this.stack.size == 0 ? null : ((JsonObject)this.stack.peek());
        return this;
    }

    private void requireCommaOrName() throws IOException {
        JsonObject jsonWriter$JsonObject0 = this.current;
        if(jsonWriter$JsonObject0 == null) {
            return;
        }
        if(jsonWriter$JsonObject0.array) {
            if(!this.current.needsComma) {
                this.current.needsComma = true;
                return;
            }
            this.writer.write(44);
            return;
        }
        if(!this.named) {
            throw new IllegalStateException("Name must be set.");
        }
        this.named = false;
    }

    public JsonWriter set(String s, Object object0) throws IOException {
        return this.name(s).value(object0);
    }

    public void setOutputType(OutputType jsonWriter$OutputType0) {
        this.outputType = jsonWriter$OutputType0;
    }

    public void setQuoteLongValues(boolean z) {
        this.quoteLongValues = z;
    }

    public JsonWriter value(Object object0) throws IOException {
        if(this.quoteLongValues && (object0 instanceof Long || object0 instanceof Double || object0 instanceof BigDecimal || object0 instanceof BigInteger)) {
            object0 = object0.toString();
        }
        else if(object0 instanceof Number) {
            long v = ((Number)object0).longValue();
            if(((Number)object0).doubleValue() == ((double)v)) {
                object0 = v;
            }
        }
        this.requireCommaOrName();
        String s = this.outputType.quoteValue(object0);
        this.writer.write(s);
        return this;
    }

    @Override
    public void write(char[] arr_c, int v, int v1) throws IOException {
        this.writer.write(arr_c, v, v1);
    }
}

