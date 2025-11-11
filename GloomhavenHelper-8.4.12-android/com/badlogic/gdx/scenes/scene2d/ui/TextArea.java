package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class TextArea extends TextField {
    public class TextAreaListener extends TextFieldClickListener {
        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        protected boolean checkFocusTraversal(char c) {
            return TextArea.this.focusTraversal && c == 9;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        protected void goEnd(boolean z) {
            if(z || TextArea.this.cursorLine >= TextArea.this.getLines()) {
                TextArea.this.cursor = TextArea.this.text.length();
            }
            else if(TextArea.this.cursorLine * 2 + 1 < TextArea.this.linesBreak.size) {
                TextArea.this.cursor = TextArea.this.linesBreak.get(TextArea.this.cursorLine * 2 + 1);
            }
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        protected void goHome(boolean z) {
            if(z) {
                TextArea.this.cursor = 0;
                return;
            }
            if(TextArea.this.cursorLine * 2 < TextArea.this.linesBreak.size) {
                TextArea.this.cursor = TextArea.this.linesBreak.get(TextArea.this.cursorLine * 2);
            }
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        public boolean keyDown(InputEvent inputEvent0, int v) {
            boolean z = false;
            boolean z1 = super.keyDown(inputEvent0, v);
            if(TextArea.this.hasKeyboardFocus()) {
                boolean z2 = Gdx.input.isKeyPressed(59) || Gdx.input.isKeyPressed(60);
                switch(v) {
                    case 19: {
                        if(!z2) {
                            TextArea.this.clearSelection();
                        }
                        else if(!TextArea.this.hasSelection) {
                            TextArea.this.selectionStart = TextArea.this.cursor;
                            TextArea.this.hasSelection = true;
                        }
                        TextArea.this.moveCursorLine(TextArea.this.cursorLine - 1);
                        z = true;
                        break;
                    }
                    case 20: {
                        if(!z2) {
                            TextArea.this.clearSelection();
                        }
                        else if(!TextArea.this.hasSelection) {
                            TextArea.this.selectionStart = TextArea.this.cursor;
                            TextArea.this.hasSelection = true;
                        }
                        TextArea.this.moveCursorLine(TextArea.this.cursorLine + 1);
                        z = true;
                        break;
                    }
                    default: {
                        TextArea.this.moveOffset = -1.0f;
                    }
                }
                if(z) {
                    this.scheduleKeyRepeatTask(v);
                }
                TextArea.this.showCursor();
                return true;
            }
            return z1;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        public boolean keyTyped(InputEvent inputEvent0, char c) {
            boolean z = super.keyTyped(inputEvent0, c);
            TextArea.this.showCursor();
            return z;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldClickListener
        protected void setCursorPosition(float f, float f1) {
            TextArea.this.moveOffset = -1.0f;
            Drawable drawable0 = TextArea.this.style.background;
            BitmapFont bitmapFont0 = TextArea.this.style.font;
            float f2 = TextArea.this.getHeight();
            if(drawable0 != null) {
                f2 -= drawable0.getTopHeight();
                f -= drawable0.getLeftWidth();
            }
            float f3 = Math.max(0.0f, f);
            if(drawable0 != null) {
                f1 -= drawable0.getTopHeight();
            }
            TextArea.this.cursorLine = ((int)Math.floor((f2 - f1) / bitmapFont0.getLineHeight())) + TextArea.this.firstLineShowing;
            TextArea.this.cursorLine = Math.max(0, Math.min(TextArea.this.cursorLine, TextArea.this.getLines() - 1));
            super.setCursorPosition(f3, f1);
            TextArea.this.updateCurrentLine();
        }
    }

    int cursorLine;
    int firstLineShowing;
    private String lastText;
    IntArray linesBreak;
    private int linesShowing;
    float moveOffset;
    private float prefRows;

    public TextArea(String s, Skin skin0) {
        super(s, skin0);
    }

    public TextArea(String s, Skin skin0, String s1) {
        super(s, skin0, s1);
    }

    public TextArea(String s, TextFieldStyle textField$TextFieldStyle0) {
        super(s, textField$TextFieldStyle0);
    }

    private int calculateCurrentLineIndex(int v) {
        int v1;
        for(v1 = 0; v1 < this.linesBreak.size && v > this.linesBreak.items[v1]; ++v1) {
        }
        return v1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void calculateOffsets() {
        super.calculateOffsets();
        if(!this.text.equals(this.lastText)) {
            this.lastText = this.text;
            BitmapFont bitmapFont0 = this.style.font;
            float f = this.getWidth();
            float f1 = this.style.background == null ? 0.0f : this.style.background.getLeftWidth() + this.style.background.getRightWidth();
            this.linesBreak.clear();
            Pool pool0 = Pools.get(GlyphLayout.class);
            GlyphLayout glyphLayout0 = (GlyphLayout)pool0.obtain();
            int v1 = 0;
            int v2 = 0;
            for(int v = 0; v < this.text.length(); ++v) {
                switch(this.text.charAt(v)) {
                    case 10: 
                    case 13: {
                        this.linesBreak.add(v1);
                        this.linesBreak.add(v);
                        v1 = v + 1;
                        break;
                    }
                    default: {
                        if(!this.continueCursor(v, 0)) {
                            v2 = v;
                        }
                        glyphLayout0.setText(bitmapFont0, this.text.subSequence(v1, v + 1));
                        if(glyphLayout0.width > f - f1) {
                            if(v1 >= v2) {
                                v2 = v - 1;
                            }
                            this.linesBreak.add(v1);
                            ++v2;
                            this.linesBreak.add(v2);
                            v1 = v2;
                        }
                    }
                }
            }
            pool0.free(glyphLayout0);
            if(v1 < this.text.length()) {
                this.linesBreak.add(v1);
                this.linesBreak.add(this.text.length());
            }
            this.showCursor();
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected boolean continueCursor(int v, int v1) {
        int v2 = this.calculateCurrentLineIndex(v + v1);
        return super.continueCursor(v, v1) && (v2 < 0 || v2 >= this.linesBreak.size - 2 || this.linesBreak.items[v2 + 1] != v || this.linesBreak.items[v2 + 1] == this.linesBreak.items[v2 + 2]);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected InputListener createInputListener() {
        return new TextAreaListener(this);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawCursor(Drawable drawable0, Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        drawable0.draw(batch0, f + this.getCursorX(), f1 + this.getCursorY(), drawable0.getMinWidth(), bitmapFont0.getLineHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawSelection(Drawable drawable0, Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        float f5;
        float f4;
        int v = this.firstLineShowing * 2;
        int v1 = Math.min(this.cursor, this.selectionStart);
        int v2 = Math.max(this.cursor, this.selectionStart);
        BitmapFontData bitmapFont$BitmapFontData0 = bitmapFont0.getData();
        float f2 = this.style.font.getLineHeight();
        float f3 = 0.0f;
        while(v + 1 < this.linesBreak.size && v < (this.firstLineShowing + this.linesShowing) * 2) {
            int v3 = this.linesBreak.get(v);
            int v4 = this.linesBreak.get(v + 1);
            if((v1 >= v3 || v1 >= v4 || v2 >= v3 || v2 >= v4) && (v1 <= v3 || v1 <= v4 || v2 <= v3 || v2 <= v4)) {
                int v5 = Math.max(v3, v1);
                int v6 = Math.min(v4, v2);
                Glyph bitmapFont$Glyph0 = bitmapFont$BitmapFontData0.getGlyph(this.displayText.charAt(v3));
                if(bitmapFont$Glyph0 == null) {
                    f5 = 0.0f;
                    f4 = 0.0f;
                }
                else if(v5 == v3) {
                    f4 = bitmapFont$Glyph0.fixedWidth ? 0.0f : ((float)(-bitmapFont$Glyph0.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padLeft;
                    f5 = 0.0f;
                }
                else {
                    f5 = bitmapFont$Glyph0.fixedWidth ? 0.0f : ((float)(-bitmapFont$Glyph0.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padLeft;
                    f4 = 0.0f;
                }
                drawable0.draw(batch0, f + (this.glyphPositions.get(v5) - this.glyphPositions.get(v3)) + f5, f1 - f2 - f3, this.glyphPositions.get(v6) - this.glyphPositions.get(v5) + f4, bitmapFont0.getLineHeight());
            }
            f3 += bitmapFont0.getLineHeight();
            v += 2;
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void drawText(Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        float f2 = -(this.style.font.getLineHeight() - this.textHeight) / 2.0f;
        for(int v = this.firstLineShowing * 2; v < (this.firstLineShowing + this.linesShowing) * 2 && v < this.linesBreak.size; v += 2) {
            bitmapFont0.draw(batch0, this.displayText, f, f1 + f2, this.linesBreak.items[v], this.linesBreak.items[v + 1], 0.0f, 8, false);
            f2 -= bitmapFont0.getLineHeight();
        }
    }

    public int getCursorLine() {
        return this.cursorLine;
    }

    public float getCursorX() {
        BitmapFontData bitmapFont$BitmapFontData0 = this.style.font.getData();
        float f = 0.0f;
        if(this.cursor < this.glyphPositions.size && this.cursorLine * 2 < this.linesBreak.size) {
            int v = this.linesBreak.items[this.cursorLine * 2];
            Glyph bitmapFont$Glyph0 = bitmapFont$BitmapFontData0.getGlyph(this.displayText.charAt(v));
            if(bitmapFont$Glyph0 != null && !bitmapFont$Glyph0.fixedWidth) {
                f = ((float)(-bitmapFont$Glyph0.xoffset)) * bitmapFont$BitmapFontData0.scaleX - bitmapFont$BitmapFontData0.padLeft;
            }
            f += this.glyphPositions.get(this.cursor) - this.glyphPositions.get(v);
        }
        return f + bitmapFont$BitmapFontData0.cursorX;
    }

    public float getCursorY() {
        return ((float)(-(this.cursorLine - this.firstLineShowing + 1))) * this.style.font.getLineHeight();
    }

    public int getFirstLineShowing() {
        return this.firstLineShowing;
    }

    public int getLines() {
        return this.linesBreak.size / 2 + this.newLineAtEnd();
    }

    public int getLinesShowing() {
        return this.linesShowing;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public float getPrefHeight() {
        if(this.prefRows <= 0.0f) {
            return super.getPrefHeight();
        }
        float f = (float)MathUtils.ceil(this.style.font.getLineHeight() * this.prefRows);
        return this.style.background == null ? f : Math.max(f + this.style.background.getBottomHeight() + this.style.background.getTopHeight(), this.style.background.getMinHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected float getTextY(BitmapFont bitmapFont0, @Null Drawable drawable0) {
        float f = this.getHeight();
        if(drawable0 != null) {
            f -= drawable0.getTopHeight();
        }
        return bitmapFont0.usesIntegerPositions() ? ((float)(((int)f))) : f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void initialize() {
        super.initialize();
        this.writeEnters = true;
        this.linesBreak = new IntArray();
        this.cursorLine = 0;
        this.firstLineShowing = 0;
        this.moveOffset = -1.0f;
        this.linesShowing = 0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected int letterUnderCursor(float f) {
        if(this.linesBreak.size > 0) {
            if(this.cursorLine * 2 >= this.linesBreak.size) {
                return this.text.length();
            }
            float[] arr_f = this.glyphPositions.items;
            int v = this.linesBreak.items[this.cursorLine * 2];
            float f1 = f + arr_f[v];
            int v1 = this.linesBreak.items[this.cursorLine * 2 + 1];
            while(v < v1 && arr_f[v] <= f1) {
                ++v;
            }
            return v <= 0 || arr_f[v] - f1 > f1 - arr_f[v - 1] ? Math.max(0, v - 1) : v;
        }
        return 0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    protected void moveCursor(boolean z, boolean z1) {
        int v = this.cursorLine * 2 + (z ? 1 : -1);
        if(v < 0 || v + 1 >= this.linesBreak.size || this.linesBreak.items[v] != this.cursor || this.linesBreak.items[v + 1] != this.cursor) {
            super.moveCursor(z, z1);
        }
        else {
            this.cursorLine += (z ? 1 : -1);
            if(z1) {
                super.moveCursor(z, true);
            }
            this.showCursor();
        }
        this.updateCurrentLine();
    }

    public void moveCursorLine(int v) {
        float f = 0.0f;
        if(v < 0) {
            this.cursorLine = 0;
            this.cursor = 0;
            this.moveOffset = -1.0f;
            return;
        }
        if(v >= this.getLines()) {
            int v1 = this.getLines();
            this.cursor = this.text.length();
            if(v > this.getLines() || v1 - 1 == this.cursorLine) {
                this.moveOffset = -1.0f;
            }
            this.cursorLine = v1 - 1;
            return;
        }
        if(v != this.cursorLine) {
            if(this.moveOffset < 0.0f) {
                if(this.linesBreak.size > this.cursorLine * 2) {
                    f = this.glyphPositions.get(this.cursor) - this.glyphPositions.get(this.linesBreak.get(this.cursorLine * 2));
                }
                this.moveOffset = f;
            }
            this.cursorLine = v;
            this.cursor = this.cursorLine * 2 < this.linesBreak.size ? this.linesBreak.get(this.cursorLine * 2) : this.text.length();
            while(this.cursor < this.text.length() && this.cursor <= this.linesBreak.get(this.cursorLine * 2 + 1) - 1 && this.glyphPositions.get(this.cursor) - this.glyphPositions.get(this.linesBreak.get(this.cursorLine * 2)) < this.moveOffset) {
                ++this.cursor;
            }
            this.showCursor();
        }
    }

    public boolean newLineAtEnd() {
        if(this.text.length() != 0) {
            switch(this.text.charAt(this.text.length() - 1)) {
                case 10: 
                case 13: {
                    return true;
                }
                default: {
                    return false;
                }
            }
        }
        return false;
    }

    public void setPrefRows(float f) {
        this.prefRows = f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setSelection(int v, int v1) {
        super.setSelection(v, v1);
        this.updateCurrentLine();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField
    public void setStyle(TextFieldStyle textField$TextFieldStyle0) {
        if(textField$TextFieldStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textField$TextFieldStyle0;
        this.textHeight = textField$TextFieldStyle0.font.getCapHeight() - textField$TextFieldStyle0.font.getDescent();
        if(this.text != null) {
            this.updateDisplayText();
        }
        this.invalidateHierarchy();
    }

    void showCursor() {
        this.updateCurrentLine();
        this.updateFirstLineShowing();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    protected void sizeChanged() {
        float f1;
        this.lastText = null;
        BitmapFont bitmapFont0 = this.style.font;
        Drawable drawable0 = this.style.background;
        float f = this.getHeight();
        if(drawable0 == null) {
            f1 = 0.0f;
        }
        else {
            float f2 = drawable0.getBottomHeight();
            f1 = drawable0.getTopHeight() + f2;
        }
        this.linesShowing = (int)Math.floor((f - f1) / bitmapFont0.getLineHeight());
    }

    void updateCurrentLine() {
        int v = this.calculateCurrentLineIndex(this.cursor);
        if(v % 2 == 0 || v + 1 >= this.linesBreak.size || this.cursor != this.linesBreak.items[v] || this.linesBreak.items[v + 1] != this.linesBreak.items[v]) {
            if(v / 2 < this.linesBreak.size / 2 || this.text.length() == 0) {
                this.cursorLine = v / 2;
            }
            else {
                switch(this.text.charAt(this.text.length() - 1)) {
                    case 10: 
                    case 13: {
                        this.cursorLine = v / 2;
                    }
                }
            }
        }
        this.updateFirstLineShowing();
    }

    void updateFirstLineShowing() {
        int v = this.cursorLine;
        int v1 = this.firstLineShowing;
        if(v != v1) {
            while(this.firstLineShowing > this.cursorLine || this.firstLineShowing + this.linesShowing - 1 < this.cursorLine) {
                this.firstLineShowing += (v < v1 ? -1 : 1);
            }
        }
    }
}

