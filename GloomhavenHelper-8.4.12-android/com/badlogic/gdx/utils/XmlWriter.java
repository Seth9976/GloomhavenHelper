package com.badlogic.gdx.utils;

import java.io.IOException;
import java.io.Writer;

public class XmlWriter extends Writer {
    private String currentElement;
    public int indent;
    private boolean indentNextClose;
    private final Array stack;
    private final Writer writer;

    public XmlWriter(Writer writer0) {
        this.stack = new Array();
        this.writer = writer0;
    }

    public XmlWriter attribute(String s, Object object0) throws IOException {
        if(this.currentElement == null) {
            throw new IllegalStateException();
        }
        this.writer.write(0x20);
        this.writer.write(s);
        this.writer.write("=\"");
        this.writer.write((object0 == null ? "null" : object0.toString()));
        this.writer.write(34);
        return this;
    }

    @Override
    public void close() throws IOException {
        while(this.stack.size != 0) {
            this.pop();
        }
        this.writer.close();
    }

    public XmlWriter element(String s) throws IOException {
        if(this.startElementContent()) {
            this.writer.write(10);
        }
        this.indent();
        this.writer.write(60);
        this.writer.write(s);
        this.currentElement = s;
        return this;
    }

    public XmlWriter element(String s, Object object0) throws IOException {
        return this.element(s).text(object0).pop();
    }

    @Override
    public void flush() throws IOException {
        this.writer.flush();
    }

    private void indent() throws IOException {
        int v = this.currentElement == null ? this.indent : this.indent + 1;
        for(int v1 = 0; v1 < v; ++v1) {
            this.writer.write(9);
        }
    }

    public XmlWriter pop() throws IOException {
        if(this.currentElement == null) {
            this.indent = Math.max(this.indent - 1, 0);
            if(this.indentNextClose) {
                this.indent();
            }
            this.writer.write("</");
            String s = (String)this.stack.pop();
            this.writer.write(s);
            this.writer.write(">\n");
        }
        else {
            this.writer.write("/>\n");
            this.currentElement = null;
        }
        this.indentNextClose = true;
        return this;
    }

    private boolean startElementContent() throws IOException {
        String s = this.currentElement;
        if(s == null) {
            return false;
        }
        ++this.indent;
        this.stack.add(s);
        this.currentElement = null;
        this.writer.write(">");
        return true;
    }

    public XmlWriter text(Object object0) throws IOException {
        this.startElementContent();
        String s = object0 == null ? "null" : object0.toString();
        this.indentNextClose = s.length() > 0x40;
        if(this.indentNextClose) {
            this.writer.write(10);
            this.indent();
        }
        this.writer.write(s);
        if(this.indentNextClose) {
            this.writer.write(10);
        }
        return this;
    }

    @Override
    public void write(char[] arr_c, int v, int v1) throws IOException {
        this.startElementContent();
        this.writer.write(arr_c, v, v1);
    }
}

