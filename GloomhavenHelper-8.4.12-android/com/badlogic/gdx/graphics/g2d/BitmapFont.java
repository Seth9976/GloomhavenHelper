package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BitmapFont implements Disposable {
    public static class BitmapFontData {
        public float ascent;
        public float blankLineScale;
        public char[] breakChars;
        public char[] capChars;
        public float capHeight;
        public float cursorX;
        public float descent;
        public float down;
        public boolean flipped;
        public FileHandle fontFile;
        public final Glyph[][] glyphs;
        public String[] imagePaths;
        public float lineHeight;
        public boolean markupEnabled;
        public Glyph missingGlyph;
        public String name;
        public float padBottom;
        public float padLeft;
        public float padRight;
        public float padTop;
        public float scaleX;
        public float scaleY;
        public float spaceXadvance;
        public char[] xChars;
        public float xHeight;

        public BitmapFontData() {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[0x80][];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }

        public BitmapFontData(FileHandle fileHandle0, boolean z) {
            this.capHeight = 1.0f;
            this.blankLineScale = 1.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.glyphs = new Glyph[0x80][];
            this.xHeight = 1.0f;
            this.xChars = new char[]{'x', 'e', 'a', 'o', 'n', 's', 'r', 'c', 'u', 'm', 'v', 'w', 'z'};
            this.capChars = new char[]{'M', 'N', 'B', 'D', 'C', 'E', 'F', 'K', 'A', 'G', 'H', 'I', 'J', 'L', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
            this.fontFile = fileHandle0;
            this.flipped = z;
            this.load(fileHandle0, z);
        }

        public Glyph getFirstGlyph() {
            Glyph[][] arr2_bitmapFont$Glyph = this.glyphs;
            for(int v = 0; v < arr2_bitmapFont$Glyph.length; ++v) {
                Glyph[] arr_bitmapFont$Glyph = arr2_bitmapFont$Glyph[v];
                if(arr_bitmapFont$Glyph != null) {
                    int v1 = arr_bitmapFont$Glyph.length;
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Glyph bitmapFont$Glyph0 = arr_bitmapFont$Glyph[v2];
                        if(bitmapFont$Glyph0 != null && bitmapFont$Glyph0.height != 0 && bitmapFont$Glyph0.width != 0) {
                            return bitmapFont$Glyph0;
                        }
                    }
                }
            }
            throw new GdxRuntimeException("No glyphs found.");
        }

        public FileHandle getFontFile() {
            return this.fontFile;
        }

        public Glyph getGlyph(char c) {
            Glyph[] arr_bitmapFont$Glyph = this.glyphs[c / 0x200];
            return arr_bitmapFont$Glyph == null ? null : arr_bitmapFont$Glyph[c & 0x1FF];
        }

        public void getGlyphs(GlyphRun glyphLayout$GlyphRun0, CharSequence charSequence0, int v, int v1, Glyph bitmapFont$Glyph0) {
            float f1;
            int v2 = v1 - v;
            if(v2 == 0) {
                return;
            }
            boolean z = this.markupEnabled;
            float f = this.scaleX;
            Array array0 = glyphLayout$GlyphRun0.glyphs;
            FloatArray floatArray0 = glyphLayout$GlyphRun0.xAdvances;
            array0.ensureCapacity(v2);
            glyphLayout$GlyphRun0.xAdvances.ensureCapacity(v2 + 1);
            do {
                int v3 = charSequence0.charAt(v);
                if(v3 == 13) {
                    ++v;
                }
                else {
                    Glyph bitmapFont$Glyph1 = this.getGlyph(((char)v3));
                    if(bitmapFont$Glyph1 == null) {
                        Glyph bitmapFont$Glyph2 = this.missingGlyph;
                        if(bitmapFont$Glyph2 != null) {
                            bitmapFont$Glyph1 = bitmapFont$Glyph2;
                            goto label_16;
                        }
                    }
                    else {
                    label_16:
                        array0.add(bitmapFont$Glyph1);
                        if(bitmapFont$Glyph0 != null) {
                            f1 = ((float)(bitmapFont$Glyph0.xadvance + bitmapFont$Glyph0.getKerning(((char)v3)))) * f;
                        }
                        else if(bitmapFont$Glyph1.fixedWidth) {
                            f1 = 0.0f;
                        }
                        else {
                            f1 = ((float)(-bitmapFont$Glyph1.xoffset)) * f - this.padLeft;
                        }
                        floatArray0.add(f1);
                        if(!z || v3 != 91 || v + 1 >= v1 || charSequence0.charAt(v + 1) != 91) {
                            ++v;
                        }
                        else {
                            v += 2;
                        }
                        bitmapFont$Glyph0 = bitmapFont$Glyph1;
                    }
                }
            }
            while(v < v1);
            if(bitmapFont$Glyph0 != null) {
                floatArray0.add((bitmapFont$Glyph0.fixedWidth ? ((float)bitmapFont$Glyph0.xadvance) * f : ((float)(bitmapFont$Glyph0.width + bitmapFont$Glyph0.xoffset)) * f - this.padRight));
            }
        }

        public String getImagePath(int v) {
            return this.imagePaths[v];
        }

        public String[] getImagePaths() {
            return this.imagePaths;
        }

        public int getWrapIndex(Array array0, int v) {
            int v1 = v - 1;
            Object[] arr_object = array0.items;
            int v2 = (char)((Glyph)arr_object[v1]).id;
            if(this.isWhitespace(((char)v2))) {
                return v1;
            }
            if(this.isBreakChar(((char)v2))) {
                --v1;
            }
            while(v1 > 0) {
                int v3 = (char)((Glyph)arr_object[v1]).id;
                if(!this.isWhitespace(((char)v3)) && !this.isBreakChar(((char)v3))) {
                    --v1;
                    continue;
                }
                return v1 + 1;
            }
            return 0;
        }

        public boolean hasGlyph(char c) {
            return this.missingGlyph == null ? this.getGlyph(c) != null : true;
        }

        public boolean isBreakChar(char c) {
            char[] arr_c = this.breakChars;
            if(arr_c == null) {
                return false;
            }
            for(int v = 0; v < arr_c.length; ++v) {
                if(c == arr_c[v]) {
                    return true;
                }
            }
            return false;
        }

        public boolean isWhitespace(char c) {
            return c == 13 || c == 0x20 || (c == 9 || c == 10);
        }

        public void load(FileHandle fileHandle0, boolean z) {
            Glyph[] arr_bitmapFont$Glyph1;
            int v7;
            Glyph[][] arr2_bitmapFont$Glyph1;
            BufferedReader bufferedReader1;
            Glyph[] arr_bitmapFont$Glyph;
            int v5;
            Glyph[][] arr2_bitmapFont$Glyph;
            Glyph bitmapFont$Glyph3;
            float f9;
            float f8;
            float f7;
            float f6;
            float f5;
            float f4;
            String s7;
            String s6;
            float f2;
            float f1;
            float f;
            if(this.imagePaths != null) {
                throw new IllegalStateException("Already loaded.");
            }
            this.name = fileHandle0.nameWithoutExtension();
            BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(fileHandle0.read()), 0x200);
            try {
                String s = bufferedReader0.readLine();
                if(s != null) {
                    String s1 = s.substring(s.indexOf("padding=") + 8);
                    String[] arr_s = s1.substring(0, s1.indexOf(0x20)).split(",", 4);
                    if(arr_s.length == 4) {
                        this.padTop = (float)Integer.parseInt(arr_s[0]);
                        boolean z1 = true;
                        this.padRight = (float)Integer.parseInt(arr_s[1]);
                        this.padBottom = (float)Integer.parseInt(arr_s[2]);
                        this.padLeft = (float)Integer.parseInt(arr_s[3]);
                        f = this.padTop + this.padBottom;
                        String s2 = bufferedReader0.readLine();
                        if(s2 != null) {
                            String[] arr_s1 = s2.split(" ", 9);
                            if(arr_s1.length >= 3) {
                                if(arr_s1[1].startsWith("lineHeight=")) {
                                    this.lineHeight = (float)Integer.parseInt(arr_s1[1].substring(11));
                                    if(arr_s1[2].startsWith("base=")) {
                                        f1 = (float)Integer.parseInt(arr_s1[2].substring(5));
                                        int v = 1;
                                        if(arr_s1.length >= 6 && arr_s1[5] != null && arr_s1[5].startsWith("pages=")) {
                                            try {
                                                v = Math.max(1, Integer.parseInt(arr_s1[5].substring(6)));
                                            }
                                            catch(NumberFormatException unused_ex) {
                                            }
                                        }
                                        this.imagePaths = new String[v];
                                        for(int v1 = 0; v1 < v; ++v1) {
                                            String s3 = bufferedReader0.readLine();
                                            if(s3 == null) {
                                                throw new GdxRuntimeException("Missing additional page definitions.");
                                            }
                                            Matcher matcher0 = Pattern.compile(".*id=(\\d+)").matcher(s3);
                                            if(matcher0.find()) {
                                                String s4 = matcher0.group(1);
                                                try {
                                                    if(Integer.parseInt(s4) != v1) {
                                                        throw new GdxRuntimeException("Page IDs must be indices starting at 0: " + s4);
                                                    }
                                                }
                                                catch(NumberFormatException numberFormatException0) {
                                                    throw new GdxRuntimeException("Invalid page id: " + s4, numberFormatException0);
                                                }
                                            }
                                            Matcher matcher1 = Pattern.compile(".*file=\"?([^\"]+)\"?").matcher(s3);
                                            if(!matcher1.find()) {
                                                throw new GdxRuntimeException("Missing: file");
                                            }
                                            String s5 = matcher1.group(1);
                                            String[] arr_s2 = this.imagePaths;
                                            arr_s2[v1] = fileHandle0.parent().child(s5).path().replaceAll("\\\\", "/");
                                        }
                                        f2 = 0.0f;
                                        this.descent = 0.0f;
                                        while(true) {
                                        label_48:
                                            s6 = bufferedReader0.readLine();
                                            if(s6 == null || s6.startsWith("kernings ") || s6.startsWith("metrics ")) {
                                                this.descent += this.padBottom;
                                                while(true) {
                                                label_51:
                                                    s7 = bufferedReader0.readLine();
                                                    if(s7 == null || !s7.startsWith("kerning ")) {
                                                        if(s7 == null || !s7.startsWith("metrics ")) {
                                                            z1 = false;
                                                            f9 = 0.0f;
                                                            f4 = 0.0f;
                                                            f5 = 0.0f;
                                                            f6 = 0.0f;
                                                            f7 = 0.0f;
                                                            f8 = 0.0f;
                                                        }
                                                        else {
                                                            StringTokenizer stringTokenizer0 = new StringTokenizer(s7, " =");
                                                            stringTokenizer0.nextToken();
                                                            stringTokenizer0.nextToken();
                                                            float f3 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f4 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f5 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f6 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f7 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f8 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            stringTokenizer0.nextToken();
                                                            f9 = Float.parseFloat(stringTokenizer0.nextToken());
                                                            f2 = f3;
                                                        }
                                                        Glyph bitmapFont$Glyph0 = this.getGlyph(' ');
                                                        if(bitmapFont$Glyph0 == null) {
                                                            bitmapFont$Glyph0 = new Glyph();
                                                            bitmapFont$Glyph0.id = 0x20;
                                                            Glyph bitmapFont$Glyph1 = this.getGlyph('l');
                                                            if(bitmapFont$Glyph1 == null) {
                                                                bitmapFont$Glyph1 = this.getFirstGlyph();
                                                            }
                                                            bitmapFont$Glyph0.xadvance = bitmapFont$Glyph1.xadvance;
                                                            this.setGlyph(0x20, bitmapFont$Glyph0);
                                                        }
                                                        if(bitmapFont$Glyph0.width == 0) {
                                                            bitmapFont$Glyph0.width = (int)(this.padLeft + ((float)bitmapFont$Glyph0.xadvance) + this.padRight);
                                                            bitmapFont$Glyph0.xoffset = (int)(-this.padLeft);
                                                        }
                                                        this.spaceXadvance = (float)bitmapFont$Glyph0.xadvance;
                                                        char[] arr_c = this.xChars;
                                                        Glyph bitmapFont$Glyph2 = null;
                                                        for(int v2 = 0; v2 < arr_c.length; ++v2) {
                                                            bitmapFont$Glyph2 = this.getGlyph(arr_c[v2]);
                                                            if(bitmapFont$Glyph2 != null) {
                                                                break;
                                                            }
                                                        }
                                                        if(bitmapFont$Glyph2 == null) {
                                                            bitmapFont$Glyph2 = this.getFirstGlyph();
                                                        }
                                                        bitmapFont$Glyph3 = null;
                                                        this.xHeight = ((float)bitmapFont$Glyph2.height) - f;
                                                        char[] arr_c1 = this.capChars;
                                                        for(int v3 = 0; v3 < arr_c1.length; ++v3) {
                                                            Glyph bitmapFont$Glyph4 = this.getGlyph(arr_c1[v3]);
                                                            if(bitmapFont$Glyph4 != null) {
                                                                bitmapFont$Glyph3 = bitmapFont$Glyph4;
                                                                break;
                                                            }
                                                        }
                                                        if(bitmapFont$Glyph3 == null) {
                                                            arr2_bitmapFont$Glyph = this.glyphs;
                                                            int v4 = arr2_bitmapFont$Glyph.length;
                                                            v5 = 0;
                                                            while(true) {
                                                            label_116:
                                                                if(v5 >= v4) {
                                                                    bufferedReader1 = bufferedReader0;
                                                                    break;
                                                                }
                                                                arr_bitmapFont$Glyph = arr2_bitmapFont$Glyph[v5];
                                                                if(arr_bitmapFont$Glyph == null) {
                                                                    bufferedReader1 = bufferedReader0;
                                                                    arr2_bitmapFont$Glyph1 = arr2_bitmapFont$Glyph;
                                                                }
                                                                else {
                                                                    arr2_bitmapFont$Glyph1 = arr2_bitmapFont$Glyph;
                                                                    int v6 = arr_bitmapFont$Glyph.length;
                                                                    bufferedReader1 = bufferedReader0;
                                                                    v7 = 0;
                                                                    goto label_133;
                                                                }
                                                                ++v5;
                                                                arr2_bitmapFont$Glyph = arr2_bitmapFont$Glyph1;
                                                                bufferedReader0 = bufferedReader1;
                                                            }
                                                        }
                                                        else {
                                                            goto label_150;
                                                        }
                                                        goto label_152;
                                                    }
                                                    goto label_168;
                                                }
                                            }
                                            goto label_185;
                                        }
                                    }
                                    bufferedReader1 = bufferedReader0;
                                    throw new GdxRuntimeException("Missing: base");
                                }
                                bufferedReader1 = bufferedReader0;
                                throw new GdxRuntimeException("Missing: lineHeight");
                            }
                            bufferedReader1 = bufferedReader0;
                            throw new GdxRuntimeException("Invalid common header.");
                        }
                        bufferedReader1 = bufferedReader0;
                        throw new GdxRuntimeException("Missing common header.");
                    }
                    bufferedReader1 = bufferedReader0;
                    throw new GdxRuntimeException("Invalid padding.");
                }
                bufferedReader1 = bufferedReader0;
                throw new GdxRuntimeException("File is empty.");
            }
            catch(Exception exception0) {
                bufferedReader1 = bufferedReader0;
                throw new GdxRuntimeException("Error loading font file: " + fileHandle0, exception0);
            }
            catch(Throwable throwable0) {
                bufferedReader1 = bufferedReader0;
                StreamUtils.closeQuietly(bufferedReader1);
                throw throwable0;
            }
            try {
            label_133:
                while(v7 < v6) {
                    Glyph bitmapFont$Glyph5 = arr_bitmapFont$Glyph[v7];
                    if(bitmapFont$Glyph5 == null) {
                        arr_bitmapFont$Glyph1 = arr_bitmapFont$Glyph;
                    }
                    else {
                        arr_bitmapFont$Glyph1 = arr_bitmapFont$Glyph;
                        if(bitmapFont$Glyph5.height != 0 && bitmapFont$Glyph5.width != 0) {
                            this.capHeight = Math.max(this.capHeight, bitmapFont$Glyph5.height);
                        }
                    }
                    ++v7;
                    arr_bitmapFont$Glyph = arr_bitmapFont$Glyph1;
                }
                ++v5;
                arr2_bitmapFont$Glyph = arr2_bitmapFont$Glyph1;
                bufferedReader0 = bufferedReader1;
                goto label_116;
            label_150:
                bufferedReader1 = bufferedReader0;
                this.capHeight = (float)bitmapFont$Glyph3.height;
            label_152:
                this.capHeight -= f;
                this.ascent = f1 - this.capHeight;
                this.down = -this.lineHeight;
                if(z) {
                    this.ascent = -this.ascent;
                    this.down = -this.down;
                }
                if(z1) {
                    this.ascent = f2;
                    this.descent = f4;
                    this.down = f5;
                    this.capHeight = f6;
                    this.lineHeight = f7;
                    this.spaceXadvance = f8;
                    this.xHeight = f9;
                }
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error loading font file: " + fileHandle0, exception0);
            }
            catch(Throwable throwable0) {
                StreamUtils.closeQuietly(bufferedReader1);
                throw throwable0;
            }
            StreamUtils.closeQuietly(bufferedReader1);
            return;
            try {
            label_168:
                bufferedReader1 = bufferedReader0;
                StringTokenizer stringTokenizer1 = new StringTokenizer(s7, " =");
                stringTokenizer1.nextToken();
                stringTokenizer1.nextToken();
                int v8 = Integer.parseInt(stringTokenizer1.nextToken());
                stringTokenizer1.nextToken();
                int v9 = Integer.parseInt(stringTokenizer1.nextToken());
                if(v8 >= 0 && v8 <= 0xFFFF && v9 >= 0 && v9 <= 0xFFFF) {
                    Glyph bitmapFont$Glyph6 = this.getGlyph(((char)v8));
                    stringTokenizer1.nextToken();
                    int v10 = Integer.parseInt(stringTokenizer1.nextToken());
                    if(bitmapFont$Glyph6 != null) {
                        bitmapFont$Glyph6.setKerning(v9, v10);
                    }
                    bufferedReader0 = bufferedReader1;
                    goto label_51;
                }
                bufferedReader0 = bufferedReader1;
                goto label_51;
            label_185:
                bufferedReader1 = bufferedReader0;
                if(s6.startsWith("char ")) {
                    Glyph bitmapFont$Glyph7 = new Glyph();
                    StringTokenizer stringTokenizer2 = new StringTokenizer(s6, " =");
                    stringTokenizer2.nextToken();
                    stringTokenizer2.nextToken();
                    int v11 = Integer.parseInt(stringTokenizer2.nextToken());
                    if(v11 <= 0) {
                        this.missingGlyph = bitmapFont$Glyph7;
                        goto label_197;
                    }
                    else {
                        if(v11 <= 0xFFFF) {
                            this.setGlyph(v11, bitmapFont$Glyph7);
                        label_197:
                            bitmapFont$Glyph7.id = v11;
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.srcX = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.srcY = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.width = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.height = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.xoffset = Integer.parseInt(stringTokenizer2.nextToken());
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.yoffset = z ? Integer.parseInt(stringTokenizer2.nextToken()) : -(bitmapFont$Glyph7.height + Integer.parseInt(stringTokenizer2.nextToken()));
                            stringTokenizer2.nextToken();
                            bitmapFont$Glyph7.xadvance = Integer.parseInt(stringTokenizer2.nextToken());
                            if(stringTokenizer2.hasMoreTokens()) {
                                stringTokenizer2.nextToken();
                            }
                            if(stringTokenizer2.hasMoreTokens()) {
                                try {
                                    bitmapFont$Glyph7.page = Integer.parseInt(stringTokenizer2.nextToken());
                                }
                                catch(NumberFormatException unused_ex) {
                                }
                            }
                            if(bitmapFont$Glyph7.width > 0 && bitmapFont$Glyph7.height > 0) {
                                this.descent = Math.min(((float)bitmapFont$Glyph7.yoffset) + f1, this.descent);
                            }
                        }
                        bufferedReader0 = bufferedReader1;
                        goto label_48;
                    }
                }
                bufferedReader0 = bufferedReader1;
                goto label_48;
            }
            catch(Exception exception0) {
                try {
                    throw new GdxRuntimeException("Error loading font file: " + fileHandle0, exception0);
                }
                catch(Throwable throwable0) {
                }
            }
            catch(Throwable throwable0) {
            }
            StreamUtils.closeQuietly(bufferedReader1);
            throw throwable0;
        }

        public void scale(float f) {
            this.setScale(this.scaleX + f, this.scaleY + f);
        }

        public void setGlyph(int v, Glyph bitmapFont$Glyph0) {
            Glyph[][] arr2_bitmapFont$Glyph = this.glyphs;
            Glyph[] arr_bitmapFont$Glyph = arr2_bitmapFont$Glyph[v / 0x200];
            if(arr_bitmapFont$Glyph == null) {
                arr_bitmapFont$Glyph = new Glyph[0x200];
                arr2_bitmapFont$Glyph[v / 0x200] = arr_bitmapFont$Glyph;
            }
            arr_bitmapFont$Glyph[v & 0x1FF] = bitmapFont$Glyph0;
        }

        public void setGlyphRegion(Glyph bitmapFont$Glyph0, TextureRegion textureRegion0) {
            float f14;
            float f7;
            float f6;
            Texture texture0 = textureRegion0.getTexture();
            float f = 1.0f / ((float)texture0.getWidth());
            float f1 = 1.0f / ((float)texture0.getHeight());
            float f2 = textureRegion0.u;
            float f3 = textureRegion0.v;
            float f4 = (float)textureRegion0.getRegionWidth();
            float f5 = (float)textureRegion0.getRegionHeight();
            if(textureRegion0 instanceof AtlasRegion) {
                f6 = ((AtlasRegion)textureRegion0).offsetX;
                f7 = ((float)(((AtlasRegion)textureRegion0).originalHeight - ((AtlasRegion)textureRegion0).packedHeight)) - ((AtlasRegion)textureRegion0).offsetY;
            }
            else {
                f7 = 0.0f;
                f6 = 0.0f;
            }
            float f8 = (float)bitmapFont$Glyph0.srcX;
            float f9 = (float)(bitmapFont$Glyph0.srcX + bitmapFont$Glyph0.width);
            float f10 = (float)bitmapFont$Glyph0.srcY;
            float f11 = (float)(bitmapFont$Glyph0.srcY + bitmapFont$Glyph0.height);
            if(f6 > 0.0f) {
                f8 -= f6;
                if(f8 < 0.0f) {
                    bitmapFont$Glyph0.width = (int)(((float)bitmapFont$Glyph0.width) + f8);
                    bitmapFont$Glyph0.xoffset = (int)(((float)bitmapFont$Glyph0.xoffset) - f8);
                    f8 = 0.0f;
                }
                float f12 = f9 - f6;
                if(f12 > f4) {
                    bitmapFont$Glyph0.width = (int)(((float)bitmapFont$Glyph0.width) - (f12 - f4));
                }
                else {
                    f4 = f12;
                }
            }
            else {
                f4 = f9;
            }
            if(f7 > 0.0f) {
                float f13 = f10 - f7;
                if(f13 < 0.0f) {
                    bitmapFont$Glyph0.height = (int)(((float)bitmapFont$Glyph0.height) + f13);
                    if(bitmapFont$Glyph0.height < 0) {
                        bitmapFont$Glyph0.height = 0;
                    }
                    f10 = 0.0f;
                }
                else {
                    f10 = f13;
                }
                f14 = f11 - f7;
                if(f14 > f5) {
                    float f15 = f14 - f5;
                    bitmapFont$Glyph0.height = (int)(((float)bitmapFont$Glyph0.height) - f15);
                    bitmapFont$Glyph0.yoffset = (int)(((float)bitmapFont$Glyph0.yoffset) + f15);
                    f14 = f5;
                }
            }
            else {
                f14 = f11;
            }
            bitmapFont$Glyph0.u = f8 * f + f2;
            bitmapFont$Glyph0.u2 = f2 + f4 * f;
            if(this.flipped) {
                bitmapFont$Glyph0.v = f10 * f1 + f3;
                bitmapFont$Glyph0.v2 = f3 + f14 * f1;
                return;
            }
            bitmapFont$Glyph0.v2 = f10 * f1 + f3;
            bitmapFont$Glyph0.v = f3 + f14 * f1;
        }

        public void setLineHeight(float f) {
            this.lineHeight = f * this.scaleY;
            this.down = this.flipped ? this.lineHeight : -this.lineHeight;
        }

        public void setScale(float f) {
            this.setScale(f, f);
        }

        public void setScale(float f, float f1) {
            if(f == 0.0f) {
                throw new IllegalArgumentException("scaleX cannot be 0.");
            }
            if(f1 == 0.0f) {
                throw new IllegalArgumentException("scaleY cannot be 0.");
            }
            float f2 = f / this.scaleX;
            float f3 = f1 / this.scaleY;
            this.lineHeight *= f3;
            this.spaceXadvance *= f2;
            this.xHeight *= f3;
            this.capHeight *= f3;
            this.ascent *= f3;
            this.descent *= f3;
            this.down *= f3;
            this.padLeft *= f2;
            this.padRight *= f2;
            this.padTop *= f3;
            this.padBottom *= f3;
            this.scaleX = f;
            this.scaleY = f1;
        }

        @Override
        public String toString() {
            return this.name == null ? super.toString() : this.name;
        }
    }

    public static class Glyph {
        public boolean fixedWidth;
        public int height;
        public int id;
        public byte[][] kerning;
        public int page;
        public int srcX;
        public int srcY;
        public float u;
        public float u2;
        public float v;
        public float v2;
        public int width;
        public int xadvance;
        public int xoffset;
        public int yoffset;

        public Glyph() {
            this.page = 0;
        }

        public int getKerning(char c) {
            byte[][] arr2_b = this.kerning;
            if(arr2_b != null) {
                byte[] arr_b = arr2_b[c >>> 9];
                if(arr_b != null) {
                    return arr_b[c & 0x1FF];
                }
            }
            return 0;
        }

        public void setKerning(int v, int v1) {
            if(this.kerning == null) {
                this.kerning = new byte[0x80][];
            }
            byte[][] arr2_b = this.kerning;
            byte[] arr_b = arr2_b[v >>> 9];
            if(arr_b == null) {
                arr_b = new byte[0x200];
                arr2_b[v >>> 9] = arr_b;
            }
            arr_b[v & 0x1FF] = (byte)v1;
        }

        @Override
        public String toString() {
            return Character.toString(((char)this.id));
        }
    }

    private static final int LOG2_PAGE_SIZE = 9;
    private static final int PAGES = 0x80;
    private static final int PAGE_SIZE = 0x200;
    private final BitmapFontCache cache;
    final BitmapFontData data;
    private boolean flipped;
    boolean integer;
    private boolean ownsTexture;
    Array regions;

    public BitmapFont() {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), false, true);
    }

    public BitmapFont(FileHandle fileHandle0) {
        this(fileHandle0, false);
    }

    public BitmapFont(FileHandle fileHandle0, FileHandle fileHandle1, boolean z) {
        this(fileHandle0, fileHandle1, z, true);
    }

    public BitmapFont(FileHandle fileHandle0, FileHandle fileHandle1, boolean z, boolean z1) {
        this(new BitmapFontData(fileHandle0, z), new TextureRegion(new Texture(fileHandle1, false)), z1);
        this.ownsTexture = true;
    }

    public BitmapFont(FileHandle fileHandle0, TextureRegion textureRegion0) {
        this(fileHandle0, textureRegion0, false);
    }

    public BitmapFont(FileHandle fileHandle0, TextureRegion textureRegion0, boolean z) {
        this(new BitmapFontData(fileHandle0, z), textureRegion0, true);
    }

    public BitmapFont(FileHandle fileHandle0, boolean z) {
        this(new BitmapFontData(fileHandle0, z), null, true);
    }

    public BitmapFont(BitmapFontData bitmapFont$BitmapFontData0, TextureRegion textureRegion0, boolean z) {
        this(bitmapFont$BitmapFontData0, (textureRegion0 == null ? null : Array.with(new TextureRegion[]{textureRegion0})), z);
    }

    public BitmapFont(BitmapFontData bitmapFont$BitmapFontData0, Array array0, boolean z) {
        this.flipped = bitmapFont$BitmapFontData0.flipped;
        this.data = bitmapFont$BitmapFontData0;
        this.integer = z;
        if(array0 != null && array0.size != 0) {
            this.regions = array0;
            this.ownsTexture = false;
        }
        else if(bitmapFont$BitmapFontData0.imagePaths != null) {
            int v = bitmapFont$BitmapFontData0.imagePaths.length;
            this.regions = new Array(v);
            for(int v1 = 0; v1 < v; ++v1) {
                FileHandle fileHandle0 = bitmapFont$BitmapFontData0.fontFile == null ? Gdx.files.internal(bitmapFont$BitmapFontData0.imagePaths[v1]) : Gdx.files.getFileHandle(bitmapFont$BitmapFontData0.imagePaths[v1], bitmapFont$BitmapFontData0.fontFile.type());
                this.regions.add(new TextureRegion(new Texture(fileHandle0, false)));
            }
            this.ownsTexture = true;
        }
        else {
            throw new IllegalArgumentException("If no regions are specified, the font data must have an images path.");
        }
        this.cache = this.newFontCache();
        this.load(bitmapFont$BitmapFontData0);
    }

    public BitmapFont(boolean z) {
        this(Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.fnt"), Gdx.files.classpath("com/badlogic/gdx/utils/arial-15.png"), z, true);
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.ownsTexture) {
            for(int v = 0; v < this.regions.size; ++v) {
                ((TextureRegion)this.regions.get(v)).getTexture().dispose();
            }
        }
    }

    public GlyphLayout draw(Batch batch0, CharSequence charSequence0, float f, float f1) {
        this.cache.clear();
        GlyphLayout glyphLayout0 = this.cache.addText(charSequence0, f, f1);
        this.cache.draw(batch0);
        return glyphLayout0;
    }

    public GlyphLayout draw(Batch batch0, CharSequence charSequence0, float f, float f1, float f2, int v, boolean z) {
        this.cache.clear();
        GlyphLayout glyphLayout0 = this.cache.addText(charSequence0, f, f1, f2, v, z);
        this.cache.draw(batch0);
        return glyphLayout0;
    }

    public GlyphLayout draw(Batch batch0, CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z) {
        this.cache.clear();
        GlyphLayout glyphLayout0 = this.cache.addText(charSequence0, f, f1, v, v1, f2, v2, z);
        this.cache.draw(batch0);
        return glyphLayout0;
    }

    public GlyphLayout draw(Batch batch0, CharSequence charSequence0, float f, float f1, int v, int v1, float f2, int v2, boolean z, String s) {
        this.cache.clear();
        GlyphLayout glyphLayout0 = this.cache.addText(charSequence0, f, f1, v, v1, f2, v2, z, s);
        this.cache.draw(batch0);
        return glyphLayout0;
    }

    public void draw(Batch batch0, GlyphLayout glyphLayout0, float f, float f1) {
        this.cache.clear();
        this.cache.addText(glyphLayout0, f, f1);
        this.cache.draw(batch0);
    }

    public float getAscent() {
        return this.data.ascent;
    }

    public BitmapFontCache getCache() {
        return this.cache;
    }

    public float getCapHeight() {
        return this.data.capHeight;
    }

    public Color getColor() {
        return this.cache.getColor();
    }

    public BitmapFontData getData() {
        return this.data;
    }

    public float getDescent() {
        return this.data.descent;
    }

    public float getLineHeight() {
        return this.data.lineHeight;
    }

    public TextureRegion getRegion() {
        return (TextureRegion)this.regions.first();
    }

    public TextureRegion getRegion(int v) {
        return (TextureRegion)this.regions.get(v);
    }

    public Array getRegions() {
        return this.regions;
    }

    public float getScaleX() {
        return this.data.scaleX;
    }

    public float getScaleY() {
        return this.data.scaleY;
    }

    public float getSpaceXadvance() {
        return this.data.spaceXadvance;
    }

    public float getXHeight() {
        return this.data.xHeight;
    }

    static int indexOf(CharSequence charSequence0, char c, int v) {
        int v1 = charSequence0.length();
        while(v < v1) {
            if(charSequence0.charAt(v) == c) {
                return v;
            }
            ++v;
        }
        return v1;
    }

    public boolean isFlipped() {
        return this.flipped;
    }

    protected void load(BitmapFontData bitmapFont$BitmapFontData0) {
        Glyph[][] arr2_bitmapFont$Glyph = bitmapFont$BitmapFontData0.glyphs;
        for(int v = 0; v < arr2_bitmapFont$Glyph.length; ++v) {
            Glyph[] arr_bitmapFont$Glyph = arr2_bitmapFont$Glyph[v];
            if(arr_bitmapFont$Glyph != null) {
                int v1 = arr_bitmapFont$Glyph.length;
                for(int v2 = 0; v2 < v1; ++v2) {
                    Glyph bitmapFont$Glyph0 = arr_bitmapFont$Glyph[v2];
                    if(bitmapFont$Glyph0 != null) {
                        bitmapFont$BitmapFontData0.setGlyphRegion(bitmapFont$Glyph0, ((TextureRegion)this.regions.get(bitmapFont$Glyph0.page)));
                    }
                }
            }
        }
        if(bitmapFont$BitmapFontData0.missingGlyph != null) {
            bitmapFont$BitmapFontData0.setGlyphRegion(bitmapFont$BitmapFontData0.missingGlyph, ((TextureRegion)this.regions.get(bitmapFont$BitmapFontData0.missingGlyph.page)));
        }
    }

    public BitmapFontCache newFontCache() {
        return new BitmapFontCache(this, this.integer);
    }

    public boolean ownsTexture() {
        return this.ownsTexture;
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.cache.getColor().set(f, f1, f2, f3);
    }

    public void setColor(Color color0) {
        this.cache.getColor().set(color0);
    }

    public void setFixedWidthGlyphs(CharSequence charSequence0) {
        BitmapFontData bitmapFont$BitmapFontData0 = this.data;
        int v = charSequence0.length();
        int v3 = 0;
        for(int v2 = 0; v2 < v; ++v2) {
            Glyph bitmapFont$Glyph0 = bitmapFont$BitmapFontData0.getGlyph(charSequence0.charAt(v2));
            if(bitmapFont$Glyph0 != null && bitmapFont$Glyph0.xadvance > v3) {
                v3 = bitmapFont$Glyph0.xadvance;
            }
        }
        int v4 = charSequence0.length();
        for(int v1 = 0; v1 < v4; ++v1) {
            Glyph bitmapFont$Glyph1 = bitmapFont$BitmapFontData0.getGlyph(charSequence0.charAt(v1));
            if(bitmapFont$Glyph1 != null) {
                bitmapFont$Glyph1.xoffset += (v3 - bitmapFont$Glyph1.xadvance) / 2;
                bitmapFont$Glyph1.xadvance = v3;
                bitmapFont$Glyph1.kerning = null;
                bitmapFont$Glyph1.fixedWidth = true;
            }
        }
    }

    public void setOwnsTexture(boolean z) {
        this.ownsTexture = z;
    }

    public void setUseIntegerPositions(boolean z) {
        this.integer = z;
        this.cache.setUseIntegerPositions(z);
    }

    @Override
    public String toString() {
        return this.data.name == null ? super.toString() : this.data.name;
    }

    public boolean usesIntegerPositions() {
        return this.integer;
    }
}

