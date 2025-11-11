package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout.GlyphRun;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

public class TextField extends Widget implements Disableable {
    public static class DefaultOnscreenKeyboard implements OnscreenKeyboard {
        @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$OnscreenKeyboard
        public void show(boolean z) {
            Gdx.input.setOnscreenKeyboardVisible(z);
        }
    }

    class KeyRepeatTask extends Task {
        int keycode;

        @Override  // com.badlogic.gdx.utils.Timer$Task
        public void run() {
            if(TextField.this.getStage() == null) {
                this.cancel();
                return;
            }
            TextField.this.inputListener.keyDown(null, this.keycode);
        }
    }

    public interface OnscreenKeyboard {
        void show(boolean arg1);
    }

    public class TextFieldClickListener extends ClickListener {
        protected boolean checkFocusTraversal(char c) {
            if(TextField.this.focusTraversal) {
                switch(c) {
                    case 9: {
                        return true;
                    }
                    case 10: 
                    case 13: {
                        return UIUtils.isAndroid || UIUtils.isIos;
                    }
                    default: {
                        return false;
                    }
                }
            }
            return false;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public void clicked(InputEvent inputEvent0, float f, float f1) {
            int v = this.getTapCount();
            if(v % 4 == 0) {
                TextField.this.clearSelection();
            }
            if(v % 4 == 2) {
                int[] arr_v = TextField.this.wordUnderCursor(f);
                TextField.this.setSelection(arr_v[0], arr_v[1]);
            }
            if(v % 4 == 3) {
                TextField.this.selectAll();
            }
        }

        protected void goEnd(boolean z) {
            TextField.this.cursor = TextField.this.text.length();
        }

        protected void goHome(boolean z) {
            TextField.this.cursor = 0;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyDown(InputEvent inputEvent0, int v) {
            boolean z4;
            boolean z3;
            if(TextField.this.disabled) {
                return false;
            }
            TextField.this.cursorOn = TextField.this.focused;
            TextField.this.blinkTask.cancel();
            if(TextField.this.focused) {
                Timer.schedule(TextField.this.blinkTask, TextField.this.blinkTime, TextField.this.blinkTime);
            }
            if(!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            boolean z = UIUtils.ctrl();
            boolean z1 = true;
            boolean z2 = z && !TextField.this.passwordMode;
            if(z) {
                switch(v) {
                    case 29: {
                        TextField.this.selectAll();
                        return true;
                    }
                    case 50: {
                        String s1 = TextField.this.clipboard.getContents();
                        TextField.this.paste(s1, true);
                        z3 = true;
                        z4 = true;
                        break;
                    }
                    case 52: {
                        TextField.this.cut(true);
                        return true;
                    }
                    case 54: {
                        String s = TextField.this.text;
                        TextField.this.setText(TextField.this.undoText);
                        TextField.this.undoText = s;
                        TextField.this.updateDisplayText();
                        return true;
                    }
                    case 0x1F: 
                    case 0x7C: {
                        TextField.this.copy();
                        return true;
                    }
                    default: {
                        z3 = false;
                        z4 = false;
                        break;
                    }
                }
            }
            else {
                z3 = false;
                z4 = true;
            }
            if(UIUtils.shift()) {
                switch(v) {
                    case 0x70: {
                        TextField.this.cut(true);
                        break;
                    }
                    case 0x7C: {
                        String s2 = TextField.this.clipboard.getContents();
                        TextField.this.paste(s2, true);
                    }
                }
                int v1 = TextField.this.cursor;
                switch(v) {
                    case 3: {
                        this.goHome(z2);
                        goto label_55;
                    }
                    case 21: {
                        TextField.this.moveCursor(false, z2);
                        z3 = true;
                        goto label_55;
                    }
                    case 22: {
                        TextField.this.moveCursor(true, z2);
                        z3 = true;
                        goto label_55;
                    }
                    case 0x7B: {
                        this.goEnd(z2);
                    label_55:
                        if(!TextField.this.hasSelection) {
                            TextField.this.selectionStart = v1;
                            TextField.this.hasSelection = true;
                        }
                        break;
                    }
                    default: {
                        z1 = z4;
                    }
                }
            }
            else {
                switch(v) {
                    case 3: {
                        this.goHome(z2);
                        TextField.this.clearSelection();
                        break;
                    }
                    case 21: {
                        TextField.this.moveCursor(false, z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                    }
                    case 22: {
                        TextField.this.moveCursor(true, z2);
                        TextField.this.clearSelection();
                        z3 = true;
                        break;
                    }
                    case 0x7B: {
                        this.goEnd(z2);
                        TextField.this.clearSelection();
                        break;
                    }
                    default: {
                        z1 = z4;
                    }
                }
            }
            TextField.this.cursor = MathUtils.clamp(TextField.this.cursor, 0, TextField.this.text.length());
            if(z3) {
                this.scheduleKeyRepeatTask(v);
            }
            return z1;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyTyped(InputEvent inputEvent0, char c) {
            if(TextField.this.disabled) {
                return false;
            }
            if(c != 13 && (c != 8 && c != 9 && c != 10) && c < 0x20) {
                return false;
            }
            if(!TextField.this.hasKeyboardFocus()) {
                return false;
            }
            if(UIUtils.isMac && Gdx.input.isKeyPressed(0x3F)) {
                return true;
            }
            if(this.checkFocusTraversal(c)) {
                boolean z = UIUtils.shift();
                TextField.this.next(z);
            }
            else {
                boolean z1 = c == 10 || c == 13;
                boolean z2 = z1 ? TextField.this.writeEnters : !TextField.this.onlyFontChars || TextField.this.style.font.getData().hasGlyph(c);
                boolean z3 = c == 8 || c == 0x7F;
                if(z2 || z3) {
                    String s = TextField.this.text;
                    int v = TextField.this.cursor;
                    if(z3) {
                        if(TextField.this.hasSelection) {
                            TextField.this.cursor = TextField.this.delete(false);
                        }
                        else {
                            if(c == 8 && TextField.this.cursor > 0) {
                                int v1 = TextField.this.cursor;
                                TextField.this.cursor = v1 - 1;
                                TextField.this.text = TextField.this.text.substring(0, TextField.this.cursor - 1) + TextField.this.text.substring(v1);
                                TextField.this.renderOffset = 0.0f;
                            }
                            if(c == 0x7F && TextField.this.cursor < TextField.this.text.length()) {
                                TextField.this.text = TextField.this.text.substring(0, TextField.this.cursor) + TextField.this.text.substring(TextField.this.cursor + 1);
                            }
                        }
                    }
                    if(z2 && !z3) {
                        if(!z1 && TextField.this.filter != null && !TextField.this.filter.acceptChar(TextField.this, c)) {
                            return true;
                        }
                        if(!TextField.this.withinMaxLength(TextField.this.text.length() - (TextField.this.hasSelection ? Math.abs(TextField.this.cursor - TextField.this.selectionStart) : 0))) {
                            return true;
                        }
                        if(TextField.this.hasSelection) {
                            TextField.this.cursor = TextField.this.delete(false);
                        }
                        int v2 = TextField.this.cursor;
                        TextField.this.cursor = v2 + 1;
                        TextField.this.text = TextField.this.insert(v2, (z1 ? "\n" : String.valueOf(c)), TextField.this.text);
                    }
                    if(TextField.this.changeText(s, TextField.this.text)) {
                        long v3 = System.currentTimeMillis();
                        if(v3 - 750L > TextField.this.lastChangeTime) {
                            TextField.this.undoText = s;
                        }
                        TextField.this.lastChangeTime = v3;
                        TextField.this.updateDisplayText();
                    }
                    else {
                        TextField.this.cursor = v;
                    }
                }
            }
            if(TextField.this.listener != null) {
                TextField.this.listener.keyTyped(TextField.this, c);
            }
            return true;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
        public boolean keyUp(InputEvent inputEvent0, int v) {
            if(TextField.this.disabled) {
                return false;
            }
            TextField.this.keyRepeatTask.cancel();
            return true;
        }

        protected void scheduleKeyRepeatTask(int v) {
            if(!TextField.this.keyRepeatTask.isScheduled() || TextField.this.keyRepeatTask.keycode != v) {
                TextField.this.keyRepeatTask.keycode = v;
                TextField.this.keyRepeatTask.cancel();
                Timer.schedule(TextField.this.keyRepeatTask, TextField.keyRepeatInitialTime, TextField.keyRepeatTime);
            }
        }

        protected void setCursorPosition(float f, float f1) {
            TextField.this.cursor = TextField.this.letterUnderCursor(f);
            TextField.this.cursorOn = TextField.this.focused;
            TextField.this.blinkTask.cancel();
            if(TextField.this.focused) {
                Timer.schedule(TextField.this.blinkTask, TextField.this.blinkTime, TextField.this.blinkTime);
            }
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
            if(!super.touchDown(inputEvent0, f, f1, v, v1)) {
                return false;
            }
            if(v == 0 && v1 != 0) {
                return false;
            }
            if(TextField.this.disabled) {
                return true;
            }
            this.setCursorPosition(f, f1);
            TextField.this.selectionStart = TextField.this.cursor;
            Stage stage0 = TextField.this.getStage();
            if(stage0 != null) {
                stage0.setKeyboardFocus(TextField.this);
            }
            TextField.this.keyboard.show(true);
            TextField.this.hasSelection = true;
            return true;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
            super.touchDragged(inputEvent0, f, f1, v);
            this.setCursorPosition(f, f1);
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
        public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
            if(TextField.this.selectionStart == TextField.this.cursor) {
                TextField.this.hasSelection = false;
            }
            super.touchUp(inputEvent0, f, f1, v, v1);
        }
    }

    public interface TextFieldFilter {
        public static class DigitsOnlyFilter implements TextFieldFilter {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldFilter
            public boolean acceptChar(TextField textField0, char c) {
                return Character.isDigit(c);
            }
        }

        boolean acceptChar(TextField arg1, char arg2);
    }

    public interface TextFieldListener {
        void keyTyped(TextField arg1, char arg2);
    }

    public static class TextFieldStyle {
        @Null
        public Drawable background;
        @Null
        public Drawable cursor;
        @Null
        public Drawable disabledBackground;
        @Null
        public Color disabledFontColor;
        @Null
        public Drawable focusedBackground;
        @Null
        public Color focusedFontColor;
        public BitmapFont font;
        public Color fontColor;
        @Null
        public BitmapFont messageFont;
        @Null
        public Color messageFontColor;
        @Null
        public Drawable selection;

        public TextFieldStyle() {
        }

        public TextFieldStyle(BitmapFont bitmapFont0, Color color0, @Null Drawable drawable0, @Null Drawable drawable1, @Null Drawable drawable2) {
            this.font = bitmapFont0;
            this.fontColor = color0;
            this.cursor = drawable0;
            this.selection = drawable1;
            this.background = drawable2;
        }

        public TextFieldStyle(TextFieldStyle textField$TextFieldStyle0) {
            this.font = textField$TextFieldStyle0.font;
            Color color0 = textField$TextFieldStyle0.fontColor;
            if(color0 != null) {
                this.fontColor = new Color(color0);
            }
            Color color1 = textField$TextFieldStyle0.focusedFontColor;
            if(color1 != null) {
                this.focusedFontColor = new Color(color1);
            }
            Color color2 = textField$TextFieldStyle0.disabledFontColor;
            if(color2 != null) {
                this.disabledFontColor = new Color(color2);
            }
            this.background = textField$TextFieldStyle0.background;
            this.focusedBackground = textField$TextFieldStyle0.focusedBackground;
            this.disabledBackground = textField$TextFieldStyle0.disabledBackground;
            this.cursor = textField$TextFieldStyle0.cursor;
            this.selection = textField$TextFieldStyle0.selection;
            this.messageFont = textField$TextFieldStyle0.messageFont;
            Color color3 = textField$TextFieldStyle0.messageFontColor;
            if(color3 != null) {
                this.messageFontColor = new Color(color3);
            }
        }
    }

    protected static final char BACKSPACE = '\b';
    protected static final char BULLET = '\u0095';
    protected static final char CARRIAGE_RETURN = '\r';
    protected static final char DELETE = '\u007F';
    protected static final char NEWLINE = '\n';
    protected static final char TAB = '\t';
    final Task blinkTask;
    float blinkTime;
    Clipboard clipboard;
    protected int cursor;
    boolean cursorOn;
    boolean disabled;
    protected CharSequence displayText;
    @Null
    TextFieldFilter filter;
    boolean focusTraversal;
    boolean focused;
    protected float fontOffset;
    protected final FloatArray glyphPositions;
    protected boolean hasSelection;
    InputListener inputListener;
    public static float keyRepeatInitialTime;
    final KeyRepeatTask keyRepeatTask;
    public static float keyRepeatTime;
    OnscreenKeyboard keyboard;
    long lastChangeTime;
    protected final GlyphLayout layout;
    @Null
    TextFieldListener listener;
    private int maxLength;
    private String messageText;
    boolean onlyFontChars;
    private StringBuilder passwordBuffer;
    private char passwordCharacter;
    boolean passwordMode;
    boolean programmaticChangeEvents;
    float renderOffset;
    protected int selectionStart;
    private float selectionWidth;
    private float selectionX;
    TextFieldStyle style;
    protected String text;
    private int textHAlign;
    protected float textHeight;
    protected float textOffset;
    private static final Vector2 tmp1;
    private static final Vector2 tmp2;
    private static final Vector2 tmp3;
    String undoText;
    private int visibleTextEnd;
    private int visibleTextStart;
    protected boolean writeEnters;

    static {
        TextField.tmp1 = new Vector2();
        TextField.tmp2 = new Vector2();
        TextField.tmp3 = new Vector2();
        TextField.keyRepeatInitialTime = 0.4f;
        TextField.keyRepeatTime = 0.1f;
    }

    public TextField(@Null String s, Skin skin0) {
        this(s, ((TextFieldStyle)skin0.get(TextFieldStyle.class)));
    }

    public TextField(@Null String s, Skin skin0, String s1) {
        this(s, ((TextFieldStyle)skin0.get(s1, TextFieldStyle.class)));
    }

    public TextField(@Null String s, TextFieldStyle textField$TextFieldStyle0) {
        this.layout = new GlyphLayout();
        this.glyphPositions = new FloatArray();
        this.keyboard = new DefaultOnscreenKeyboard();
        this.focusTraversal = true;
        this.onlyFontChars = true;
        this.textHAlign = 8;
        this.undoText = "";
        this.passwordCharacter = '\u0095';
        this.blinkTime = 0.32f;
        this.blinkTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                if(TextField.this.getStage() == null) {
                    this.cancel();
                    return;
                }
                TextField.this.cursorOn = !TextField.this.cursorOn;
                Gdx.graphics.requestRendering();
            }
        };
        this.keyRepeatTask = new KeyRepeatTask(this);
        this.setStyle(textField$TextFieldStyle0);
        this.clipboard = Gdx.app.getClipboard();
        this.initialize();
        this.setText(s);
        this.setSize(150.0f, this.getPrefHeight());
    }

    public void appendText(@Null String s) {
        if(s == null) {
            s = "";
        }
        this.clearSelection();
        this.cursor = this.text.length();
        this.paste(s, this.programmaticChangeEvents);
    }

    protected void calculateOffsets() {
        float f = this.getWidth();
        Drawable drawable0 = this.getBackgroundDrawable();
        if(drawable0 != null) {
            f -= drawable0.getLeftWidth() + drawable0.getRightWidth();
        }
        int v = this.glyphPositions.size;
        float[] arr_f = this.glyphPositions.items;
        float f1 = this.renderOffset;
        float f2 = arr_f[Math.max(0, this.cursor - 1)] + f1;
        float f3 = 0.0f;
        if(f2 <= 0.0f) {
            this.renderOffset = f1 - f2;
        }
        else {
            float f4 = arr_f[Math.min(v - 1, this.cursor + 1)] - f;
            if(-this.renderOffset < f4) {
                this.renderOffset = -f4;
            }
        }
        float f5 = arr_f[v - 1];
        int v1 = v - 2;
        float f6;
        for(f6 = 0.0f; v1 >= 0; f6 = f7) {
            float f7 = arr_f[v1];
            if(f5 - f7 > f) {
                break;
            }
            --v1;
        }
        if(-this.renderOffset > f6) {
            this.renderOffset = -f6;
        }
        this.visibleTextStart = 0;
        for(int v2 = 0; v2 < v; ++v2) {
            if(arr_f[v2] >= -this.renderOffset) {
                this.visibleTextStart = v2;
                f3 = arr_f[v2];
                break;
            }
        }
        int v3 = this.visibleTextStart + 1;
        float f8 = f - this.renderOffset;
        int v4 = Math.min(this.displayText.length(), v);
        while(v3 <= v4 && arr_f[v3] <= f8) {
            ++v3;
        }
        this.visibleTextEnd = Math.max(0, v3 - 1);
        int v5 = this.textHAlign;
        if((v5 & 8) == 0) {
            this.textOffset = f - arr_f[this.visibleTextEnd] - this.fontOffset + f3;
            if((v5 & 1) != 0) {
                this.textOffset = (float)Math.round(this.textOffset * 0.5f);
            }
        }
        else {
            this.textOffset = f3 + this.renderOffset;
        }
        if(this.hasSelection) {
            int v6 = Math.max(this.cursor, this.selectionStart);
            float f9 = Math.max(arr_f[Math.min(this.cursor, this.selectionStart)] - arr_f[this.visibleTextStart], -this.textOffset);
            this.selectionX = f9;
            this.selectionWidth = Math.min(arr_f[v6] - arr_f[this.visibleTextStart], f - this.textOffset) - f9 - this.style.font.getData().cursorX;
        }
    }

    boolean changeText(String s, String s1) {
        if(s1.equals(s)) {
            return false;
        }
        this.text = s1;
        ChangeEvent changeListener$ChangeEvent0 = (ChangeEvent)Pools.obtain(ChangeEvent.class);
        boolean z = this.fire(changeListener$ChangeEvent0);
        if(z) {
            this.text = s;
        }
        Pools.free(changeListener$ChangeEvent0);
        return !z;
    }

    public void clearSelection() {
        this.hasSelection = false;
    }

    protected boolean continueCursor(int v, int v1) {
        return this.isWordCharacter(this.text.charAt(v + v1));
    }

    public void copy() {
        if(this.hasSelection && !this.passwordMode) {
            this.clipboard.setContents(this.text.substring(Math.min(this.cursor, this.selectionStart), Math.max(this.cursor, this.selectionStart)));
        }
    }

    protected InputListener createInputListener() {
        return new TextFieldClickListener(this);
    }

    public void cut() {
        this.cut(this.programmaticChangeEvents);
    }

    void cut(boolean z) {
        if(this.hasSelection && !this.passwordMode) {
            this.copy();
            this.cursor = this.delete(z);
            this.updateDisplayText();
        }
    }

    int delete(boolean z) {
        int v = Math.min(this.selectionStart, this.cursor);
        int v1 = Math.max(this.selectionStart, this.cursor);
        String s = (v <= 0 ? "" : this.text.substring(0, v)) + (v1 >= this.text.length() ? "" : this.text.substring(v1, this.text.length()));
        if(z) {
            this.changeText(this.text, s);
        }
        else {
            this.text = s;
        }
        this.clearSelection();
        return v;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public void draw(Batch batch0, float f) {
        float f6;
        float f5;
        Color color0;
        boolean z = this.hasKeyboardFocus();
        if(z != this.focused || z && !this.blinkTask.isScheduled()) {
            this.focused = z;
            this.blinkTask.cancel();
            this.cursorOn = z;
            if(z) {
                Timer.schedule(this.blinkTask, this.blinkTime, this.blinkTime);
            }
            else {
                this.keyRepeatTask.cancel();
            }
        }
        else if(!z) {
            this.cursorOn = false;
        }
        BitmapFont bitmapFont0 = this.style.font;
        if(!this.disabled || this.style.disabledFontColor == null) {
            color0 = !z || this.style.focusedFontColor == null ? this.style.fontColor : this.style.focusedFontColor;
        }
        else {
            color0 = this.style.disabledFontColor;
        }
        Drawable drawable0 = this.style.selection;
        Drawable drawable1 = this.style.cursor;
        Drawable drawable2 = this.getBackgroundDrawable();
        Color color1 = this.getColor();
        float f1 = this.getX();
        float f2 = this.getY();
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        batch0.setColor(color1.r, color1.g, color1.b, color1.a * f);
        if(drawable2 == null) {
            f5 = 0.0f;
            f6 = 0.0f;
        }
        else {
            drawable2.draw(batch0, f1, f2, f3, f4);
            f5 = drawable2.getLeftWidth();
            f6 = drawable2.getRightWidth();
        }
        float f7 = this.getTextY(bitmapFont0, drawable2);
        this.calculateOffsets();
        if(z && this.hasSelection && drawable0 != null) {
            this.drawSelection(drawable0, batch0, bitmapFont0, f1 + f5, f2 + f7);
        }
        float f8 = bitmapFont0.isFlipped() ? -this.textHeight : 0.0f;
        if(this.displayText.length() != 0) {
            bitmapFont0.setColor(color0.r, color0.g, color0.b, color0.a * color1.a * f);
            this.drawText(batch0, bitmapFont0, f1 + f5, f2 + f7 + f8);
        }
        else if(!z && this.messageText != null) {
            BitmapFont bitmapFont1 = this.style.messageFont == null ? bitmapFont0 : this.style.messageFont;
            if(this.style.messageFontColor == null) {
                bitmapFont1.setColor(0.7f, 0.7f, 0.7f, color1.a * f);
            }
            else {
                bitmapFont1.setColor(this.style.messageFontColor.r, this.style.messageFontColor.g, this.style.messageFontColor.b, this.style.messageFontColor.a * color1.a * f);
            }
            this.drawMessageText(batch0, bitmapFont1, f1 + f5, f2 + f7 + f8, f3 - f5 - f6);
        }
        if(!this.disabled && this.cursorOn && drawable1 != null) {
            this.drawCursor(drawable1, batch0, bitmapFont0, f1 + f5, f2 + f7);
        }
    }

    protected void drawCursor(Drawable drawable0, Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        drawable0.draw(batch0, f + this.textOffset + this.glyphPositions.get(this.cursor) - this.glyphPositions.get(this.visibleTextStart) + this.fontOffset + bitmapFont0.getData().cursorX, f1 - this.textHeight - bitmapFont0.getDescent(), drawable0.getMinWidth(), this.textHeight);
    }

    protected void drawMessageText(Batch batch0, BitmapFont bitmapFont0, float f, float f1, float f2) {
        bitmapFont0.draw(batch0, this.messageText, f, f1, 0, this.messageText.length(), f2, this.textHAlign, false, "...");
    }

    protected void drawSelection(Drawable drawable0, Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        drawable0.draw(batch0, f + this.textOffset + this.selectionX + this.fontOffset, f1 - this.textHeight - bitmapFont0.getDescent(), this.selectionWidth, this.textHeight);
    }

    protected void drawText(Batch batch0, BitmapFont bitmapFont0, float f, float f1) {
        bitmapFont0.draw(batch0, this.displayText, f + this.textOffset, f1, this.visibleTextStart, this.visibleTextEnd, 0.0f, 8, false);
    }

    // This method was un-flattened
    @Null
    private TextField findNextTextField(Array array0, @Null TextField textField0, Vector2 vector20, Vector2 vector21, boolean z) {
        int v = array0.size;
        TextField textField1 = textField0;
        for(int v1 = 0; v1 < v; ++v1) {
            Actor actor0 = (Actor)array0.get(v1);
            if(!(actor0 instanceof TextField)) {
                if(actor0 instanceof Group) {
                    textField1 = this.findNextTextField(((Group)actor0).getChildren(), textField1, vector20, vector21, z);
                }
            }
            else if(actor0 != this) {
                TextField textField2 = (TextField)actor0;
                if(!textField2.isDisabled() && textField2.focusTraversal && textField2.ascendantsVisible()) {
                    Vector2 vector22 = actor0.getParent().localToStageCoordinates(TextField.tmp3.set(actor0.getX(), actor0.getY()));
                    if(vector22.y != vector21.y && (vector22.y < vector21.y ^ z) != 0 || vector22.y == vector21.y && (vector22.x > vector21.x ^ z) != 0) {
                        if(textField1 != null && (vector22.y == vector20.y || (vector22.y > vector20.y ^ z) == 0)) {
                            boolean z1 = vector22.y == vector20.y && (vector22.x < vector20.x ^ z) != 0;
                        }
                        if(z1) {
                            vector20.set(vector22);
                        }
                        else {
                            textField2 = textField1;
                        }
                        textField1 = textField2;
                    }
                }
            }
        }
        return textField1;
    }

    public int getAlignment() {
        return this.textHAlign;
    }

    @Null
    protected Drawable getBackgroundDrawable() {
        if(this.disabled && this.style.disabledBackground != null) {
            return this.style.disabledBackground;
        }
        return this.style.focusedBackground == null || !this.hasKeyboardFocus() ? this.style.background : this.style.focusedBackground;
    }

    public int getCursorPosition() {
        return this.cursor;
    }

    public InputListener getDefaultInputListener() {
        return this.inputListener;
    }

    public int getMaxLength() {
        return this.maxLength;
    }

    @Null
    public String getMessageText() {
        return this.messageText;
    }

    public OnscreenKeyboard getOnscreenKeyboard() {
        return this.keyboard;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefHeight() {
        float f1;
        float f = 0.0f;
        if(this.style.background == null) {
            f1 = 0.0f;
        }
        else {
            f1 = Math.max(0.0f, this.style.background.getBottomHeight() + this.style.background.getTopHeight());
            f = Math.max(0.0f, this.style.background.getMinHeight());
        }
        if(this.style.focusedBackground != null) {
            f1 = Math.max(f1, this.style.focusedBackground.getBottomHeight() + this.style.focusedBackground.getTopHeight());
            f = Math.max(f, this.style.focusedBackground.getMinHeight());
        }
        if(this.style.disabledBackground != null) {
            f1 = Math.max(f1, this.style.disabledBackground.getBottomHeight() + this.style.disabledBackground.getTopHeight());
            f = Math.max(f, this.style.disabledBackground.getMinHeight());
        }
        return Math.max(f1 + this.textHeight, f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Widget
    public float getPrefWidth() [...] // Inlined contents

    public boolean getProgrammaticChangeEvents() {
        return this.programmaticChangeEvents;
    }

    // 去混淆评级： 低(20)
    public String getSelection() {
        return this.hasSelection ? this.text.substring(Math.min(this.selectionStart, this.cursor), Math.max(this.selectionStart, this.cursor)) : "";
    }

    public int getSelectionStart() {
        return this.selectionStart;
    }

    public TextFieldStyle getStyle() {
        return this.style;
    }

    public String getText() {
        return this.text;
    }

    @Null
    public TextFieldFilter getTextFieldFilter() {
        return this.filter;
    }

    protected float getTextY(BitmapFont bitmapFont0, @Null Drawable drawable0) {
        float f3;
        float f = this.getHeight();
        float f1 = this.textHeight / 2.0f + bitmapFont0.getDescent();
        if(drawable0 != null) {
            float f2 = drawable0.getBottomHeight();
            f3 = f1 + (f - drawable0.getTopHeight() - f2) / 2.0f + f2;
            return bitmapFont0.usesIntegerPositions() ? ((float)(((int)f3))) : f3;
        }
        f3 = f1 + f / 2.0f;
        return bitmapFont0.usesIntegerPositions() ? ((float)(((int)f3))) : f3;
    }

    protected void initialize() {
        InputListener inputListener0 = this.createInputListener();
        this.inputListener = inputListener0;
        this.addListener(inputListener0);
    }

    String insert(int v, CharSequence charSequence0, String s) {
        return s.length() == 0 ? charSequence0.toString() : s.substring(0, v) + charSequence0 + s.substring(v, s.length());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public boolean isDisabled() {
        return this.disabled;
    }

    public boolean isPasswordMode() {
        return this.passwordMode;
    }

    protected boolean isWordCharacter(char c) {
        return Character.isLetterOrDigit(c);
    }

    protected int letterUnderCursor(float f) {
        float f1 = f - (this.textOffset + this.fontOffset - this.style.font.getData().cursorX - this.glyphPositions.get(this.visibleTextStart));
        if(this.getBackgroundDrawable() != null) {
            f1 -= this.style.background.getLeftWidth();
        }
        int v = this.glyphPositions.size;
        float[] arr_f = this.glyphPositions.items;
        for(int v1 = 1; v1 < v; ++v1) {
            if(arr_f[v1] > f1) {
                return arr_f[v1] - f1 <= f1 - arr_f[v1 - 1] ? v1 : v1 - 1;
            }
        }
        return v - 1;
    }

    protected void moveCursor(boolean z, boolean z1) {
        int v = 0;
        int v1 = z ? this.text.length() : 0;
        if(!z) {
            v = -1;
        }
        do {
            if(z) {
                int v2 = this.cursor + 1;
                this.cursor = v2;
                if(v2 >= v1) {
                    break;
                }
            }
            else {
                int v3 = this.cursor - 1;
                this.cursor = v3;
                if(v3 > v1) {
                    continue;
                }
                break;
            }
        }
        while(z1 && this.continueCursor(this.cursor, v));
    }

    public void next(boolean z) {
        Stage stage0 = this.getStage();
        if(stage0 == null) {
            return;
        }
        Group group0 = this.getParent();
        float f = this.getX();
        float f1 = this.getY();
        Vector2 vector20 = group0.localToStageCoordinates(TextField.tmp2.set(f, f1));
        Vector2 vector21 = TextField.tmp1;
        TextField textField0 = this;
        while(true) {
            TextField textField1 = textField0.findNextTextField(stage0.getActors(), null, vector21, vector20, z);
            if(textField1 == null) {
                if(z) {
                    vector20.set(-3.402823E+38f, -3.402823E+38f);
                }
                else {
                    vector20.set(3.402823E+38f, 3.402823E+38f);
                }
                textField0 = textField0.findNextTextField(stage0.getActors(), null, vector21, vector20, z);
            }
            else {
                textField0 = textField1;
            }
            if(textField0 == null) {
                Gdx.input.setOnscreenKeyboardVisible(false);
                return;
            }
            if(stage0.setKeyboardFocus(textField0)) {
                textField0.selectAll();
                return;
            }
            vector20.set(vector21);
        }
    }

    void paste(@Null String s, boolean z) {
        if(s == null) {
            return;
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        int v = this.text.length();
        if(this.hasSelection) {
            v -= Math.abs(this.cursor - this.selectionStart);
        }
        BitmapFontData bitmapFont$BitmapFontData0 = this.style.font.getData();
        int v2 = s.length();
        for(int v1 = 0; v1 < v2 && this.withinMaxLength(stringBuilder0.length() + v); ++v1) {
            int v3 = s.charAt(v1);
            if(this.writeEnters && (v3 == 10 || v3 == 13) || v3 != 13 && v3 != 10 && (!this.onlyFontChars || bitmapFont$BitmapFontData0.hasGlyph(((char)v3))) && (this.filter == null || this.filter.acceptChar(this, ((char)v3)))) {
                stringBuilder0.append(((char)v3));
            }
        }
        String s1 = stringBuilder0.toString();
        if(this.hasSelection) {
            this.cursor = this.delete(z);
        }
        if(z) {
            this.changeText(this.text, this.insert(this.cursor, s1, this.text));
        }
        else {
            this.text = this.insert(this.cursor, s1, this.text);
        }
        this.updateDisplayText();
        this.cursor += s1.length();
    }

    public void selectAll() {
        this.setSelection(0, this.text.length());
    }

    public void setAlignment(int v) {
        this.textHAlign = v;
    }

    public void setBlinkTime(float f) {
        this.blinkTime = f;
    }

    public void setClipboard(Clipboard clipboard0) {
        this.clipboard = clipboard0;
    }

    public void setCursorPosition(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("cursorPosition must be >= 0");
        }
        this.clearSelection();
        this.cursor = Math.min(v, this.text.length());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Disableable
    public void setDisabled(boolean z) {
        this.disabled = z;
    }

    public void setFocusTraversal(boolean z) {
        this.focusTraversal = z;
    }

    public void setMaxLength(int v) {
        this.maxLength = v;
    }

    public void setMessageText(@Null String s) {
        this.messageText = s;
    }

    public void setOnlyFontChars(boolean z) {
        this.onlyFontChars = z;
    }

    public void setOnscreenKeyboard(OnscreenKeyboard textField$OnscreenKeyboard0) {
        this.keyboard = textField$OnscreenKeyboard0;
    }

    public void setPasswordCharacter(char c) {
        this.passwordCharacter = c;
        if(this.passwordMode) {
            this.updateDisplayText();
        }
    }

    public void setPasswordMode(boolean z) {
        this.passwordMode = z;
        this.updateDisplayText();
    }

    public void setProgrammaticChangeEvents(boolean z) {
        this.programmaticChangeEvents = z;
    }

    public void setSelection(int v, int v1) {
        if(v < 0) {
            throw new IllegalArgumentException("selectionStart must be >= 0");
        }
        if(v1 < 0) {
            throw new IllegalArgumentException("selectionEnd must be >= 0");
        }
        int v2 = Math.min(this.text.length(), v);
        int v3 = Math.min(this.text.length(), v1);
        if(v3 == v2) {
            this.clearSelection();
            return;
        }
        if(v3 < v2) {
            int v4 = v3;
            v3 = v2;
            v2 = v4;
        }
        this.hasSelection = true;
        this.selectionStart = v2;
        this.cursor = v3;
    }

    public void setStyle(TextFieldStyle textField$TextFieldStyle0) {
        if(textField$TextFieldStyle0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        this.style = textField$TextFieldStyle0;
        this.textHeight = textField$TextFieldStyle0.font.getCapHeight() - textField$TextFieldStyle0.font.getDescent() * 2.0f;
        if(this.text != null) {
            this.updateDisplayText();
        }
        this.invalidateHierarchy();
    }

    public void setText(@Null String s) {
        if(s == null) {
            s = "";
        }
        if(s.equals(this.text)) {
            return;
        }
        this.clearSelection();
        String s1 = this.text;
        this.text = "";
        this.paste(s, false);
        if(this.programmaticChangeEvents) {
            this.changeText(s1, this.text);
        }
        this.cursor = 0;
    }

    public void setTextFieldFilter(@Null TextFieldFilter textField$TextFieldFilter0) {
        this.filter = textField$TextFieldFilter0;
    }

    public void setTextFieldListener(@Null TextFieldListener textField$TextFieldListener0) {
        this.listener = textField$TextFieldListener0;
    }

    void updateDisplayText() {
        BitmapFont bitmapFont0 = this.style.font;
        BitmapFontData bitmapFont$BitmapFontData0 = bitmapFont0.getData();
        String s = this.text;
        int v = s.length();
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v1 = 0; true; ++v1) {
            int v2 = 0x20;
            if(v1 >= v) {
                break;
            }
            int v3 = s.charAt(v1);
            if(bitmapFont$BitmapFontData0.hasGlyph(((char)v3))) {
                v2 = v3;
            }
            stringBuilder0.append(((char)v2));
        }
        String s1 = stringBuilder0.toString();
        if(!this.passwordMode || !bitmapFont$BitmapFontData0.hasGlyph(this.passwordCharacter)) {
            this.displayText = s1;
        }
        else {
            if(this.passwordBuffer == null) {
                this.passwordBuffer = new StringBuilder(s1.length());
            }
            if(this.passwordBuffer.length() > v) {
                this.passwordBuffer.setLength(v);
            }
            else {
                for(int v4 = this.passwordBuffer.length(); v4 < v; ++v4) {
                    this.passwordBuffer.append(this.passwordCharacter);
                }
            }
            this.displayText = this.passwordBuffer;
        }
        this.layout.setText(bitmapFont0, this.displayText.toString().replace('\r', ' ').replace('\n', ' '));
        this.glyphPositions.clear();
        float f = 0.0f;
        if(this.layout.runs.size > 0) {
            FloatArray floatArray0 = ((GlyphRun)this.layout.runs.first()).xAdvances;
            this.fontOffset = floatArray0.first();
            int v5 = floatArray0.size;
            float f1 = 0.0f;
            for(int v6 = 1; v6 < v5; ++v6) {
                this.glyphPositions.add(f1);
                f1 += floatArray0.get(v6);
            }
            f = f1;
        }
        else {
            this.fontOffset = 0.0f;
        }
        this.glyphPositions.add(f);
        this.visibleTextStart = Math.min(this.visibleTextStart, this.glyphPositions.size - 1);
        this.visibleTextEnd = MathUtils.clamp(this.visibleTextEnd, this.visibleTextStart, this.glyphPositions.size - 1);
        if(this.selectionStart > s1.length()) {
            this.selectionStart = v;
        }
    }

    boolean withinMaxLength(int v) {
        return this.maxLength <= 0 || v < this.maxLength;
    }

    int[] wordUnderCursor(float f) {
        return this.wordUnderCursor(this.letterUnderCursor(f));
    }

    protected int[] wordUnderCursor(int v) {
        String s = this.text;
        int v1 = s.length();
        if(v >= s.length()) {
            return new int[]{s.length(), 0};
        }
        for(int v2 = v; v2 < v1; ++v2) {
            if(!this.isWordCharacter(s.charAt(v2))) {
                v1 = v2;
                break;
            }
        }
        for(int v3 = v - 1; v3 > -1; --v3) {
            if(!this.isWordCharacter(s.charAt(v3))) {
                return new int[]{v3 + 1, v1};
            }
        }
        return new int[]{0, v1};
    }
}

