package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Cell implements Poolable {
    @Null
    Actor actor;
    float actorHeight;
    float actorWidth;
    float actorX;
    float actorY;
    Integer align;
    private static final Integer bottomi;
    int cellAboveIndex;
    private static final Integer centeri;
    Integer colspan;
    int column;
    float computedPadBottom;
    float computedPadLeft;
    float computedPadRight;
    float computedPadTop;
    private static Cell defaults;
    boolean endRow;
    Integer expandX;
    Integer expandY;
    private static Files files;
    Float fillX;
    Float fillY;
    private static final Integer lefti;
    Value maxHeight;
    Value maxWidth;
    Value minHeight;
    Value minWidth;
    private static final Float onef;
    private static final Integer onei;
    Value padBottom;
    Value padLeft;
    Value padRight;
    Value padTop;
    Value prefHeight;
    Value prefWidth;
    private static final Integer righti;
    int row;
    Value spaceBottom;
    Value spaceLeft;
    Value spaceRight;
    Value spaceTop;
    private Table table;
    private static final Integer topi;
    Boolean uniformX;
    Boolean uniformY;
    private static final Float zerof;
    private static final Integer zeroi;

    static {
        Cell.zerof = 0.0f;
        Cell.onef = 1.0f;
        Cell.zeroi = 0;
        Cell.onei = 1;
        Cell.centeri = 1;
        Cell.topi = 2;
        Cell.bottomi = 4;
        Cell.lefti = 8;
        Cell.righti = 16;
    }

    public Cell() {
        this.cellAboveIndex = -1;
        Cell cell0 = Cell.defaults();
        if(cell0 != null) {
            this.set(cell0);
        }
    }

    public Cell align(int v) {
        this.align = v;
        return this;
    }

    public Cell bottom() {
        Integer integer0 = this.align;
        if(integer0 == null) {
            this.align = Cell.bottomi;
            return this;
        }
        this.align = (int)((((int)integer0) | 4) & -3);
        return this;
    }

    public Cell center() {
        this.align = Cell.centeri;
        return this;
    }

    void clear() {
        this.minWidth = null;
        this.minHeight = null;
        this.prefWidth = null;
        this.prefHeight = null;
        this.maxWidth = null;
        this.maxHeight = null;
        this.spaceTop = null;
        this.spaceLeft = null;
        this.spaceBottom = null;
        this.spaceRight = null;
        this.padTop = null;
        this.padLeft = null;
        this.padBottom = null;
        this.padRight = null;
        this.fillX = null;
        this.fillY = null;
        this.align = null;
        this.expandX = null;
        this.expandY = null;
        this.colspan = null;
        this.uniformX = null;
        this.uniformY = null;
    }

    public Cell clearActor() {
        this.setActor(null);
        return this;
    }

    public Cell colspan(int v) {
        this.colspan = v;
        return this;
    }

    public static Cell defaults() {
        if(Cell.files == null || Cell.files != Gdx.files) {
            Cell.files = Gdx.files;
            Cell.defaults = new Cell();
            Cell.defaults.minWidth = Value.minWidth;
            Cell.defaults.minHeight = Value.minHeight;
            Cell.defaults.prefWidth = Value.prefWidth;
            Cell.defaults.prefHeight = Value.prefHeight;
            Cell.defaults.maxWidth = Value.maxWidth;
            Cell.defaults.maxHeight = Value.maxHeight;
            Cell.defaults.spaceTop = Value.zero;
            Cell.defaults.spaceLeft = Value.zero;
            Cell.defaults.spaceBottom = Value.zero;
            Cell.defaults.spaceRight = Value.zero;
            Cell.defaults.padTop = Value.zero;
            Cell.defaults.padLeft = Value.zero;
            Cell.defaults.padBottom = Value.zero;
            Cell.defaults.padRight = Value.zero;
            Cell.defaults.fillX = Cell.zerof;
            Cell.defaults.fillY = Cell.zerof;
            Cell.defaults.align = Cell.centeri;
            Cell.defaults.expandX = Cell.zeroi;
            Cell.defaults.expandY = Cell.zeroi;
            Cell.defaults.colspan = Cell.onei;
            Cell.defaults.uniformX = null;
            Cell.defaults.uniformY = null;
        }
        return Cell.defaults;
    }

    public Cell expand() {
        this.expandX = Cell.onei;
        this.expandY = Cell.onei;
        return this;
    }

    public Cell expand(int v, int v1) {
        this.expandX = v;
        this.expandY = v1;
        return this;
    }

    public Cell expand(boolean z, boolean z1) {
        this.expandX = z ? Cell.onei : Cell.zeroi;
        this.expandY = z1 ? Cell.onei : Cell.zeroi;
        return this;
    }

    public Cell expandX() {
        this.expandX = Cell.onei;
        return this;
    }

    public Cell expandY() {
        this.expandY = Cell.onei;
        return this;
    }

    public Cell fill() {
        this.fillX = Cell.onef;
        this.fillY = Cell.onef;
        return this;
    }

    public Cell fill(float f, float f1) {
        this.fillX = f;
        this.fillY = f1;
        return this;
    }

    public Cell fill(boolean z) {
        this.fillX = z ? Cell.onef : Cell.zerof;
        this.fillY = z ? Cell.onef : Cell.zerof;
        return this;
    }

    public Cell fill(boolean z, boolean z1) {
        this.fillX = z ? Cell.onef : Cell.zerof;
        this.fillY = z1 ? Cell.onef : Cell.zerof;
        return this;
    }

    public Cell fillX() {
        this.fillX = Cell.onef;
        return this;
    }

    public Cell fillY() {
        this.fillY = Cell.onef;
        return this;
    }

    @Null
    public Actor getActor() {
        return this.actor;
    }

    public float getActorHeight() {
        return this.actorHeight;
    }

    public float getActorWidth() {
        return this.actorWidth;
    }

    public float getActorX() {
        return this.actorX;
    }

    public float getActorY() {
        return this.actorY;
    }

    public int getAlign() {
        return (int)this.align;
    }

    public int getColspan() {
        return (int)this.colspan;
    }

    public int getColumn() {
        return this.column;
    }

    public float getComputedPadBottom() {
        return this.computedPadBottom;
    }

    public float getComputedPadLeft() {
        return this.computedPadLeft;
    }

    public float getComputedPadRight() {
        return this.computedPadRight;
    }

    public float getComputedPadTop() {
        return this.computedPadTop;
    }

    public int getExpandX() {
        return (int)this.expandX;
    }

    public int getExpandY() {
        return (int)this.expandY;
    }

    public float getFillX() {
        return (float)this.fillX;
    }

    public float getFillY() {
        return (float)this.fillY;
    }

    public float getMaxHeight() {
        return this.maxHeight.get(this.actor);
    }

    @Null
    public Value getMaxHeightValue() {
        return this.maxHeight;
    }

    public float getMaxWidth() {
        return this.maxWidth.get(this.actor);
    }

    @Null
    public Value getMaxWidthValue() {
        return this.maxWidth;
    }

    public float getMinHeight() {
        return this.minHeight.get(this.actor);
    }

    @Null
    public Value getMinHeightValue() {
        return this.minHeight;
    }

    public float getMinWidth() {
        return this.minWidth.get(this.actor);
    }

    @Null
    public Value getMinWidthValue() {
        return this.minWidth;
    }

    public float getPadBottom() {
        return this.padBottom.get(this.actor);
    }

    @Null
    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft.get(this.actor);
    }

    @Null
    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight.get(this.actor);
    }

    @Null
    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop.get(this.actor);
    }

    @Null
    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadX() {
        return this.padLeft.get(this.actor) + this.padRight.get(this.actor);
    }

    public float getPadY() {
        return this.padTop.get(this.actor) + this.padBottom.get(this.actor);
    }

    public float getPrefHeight() {
        return this.prefHeight.get(this.actor);
    }

    @Null
    public Value getPrefHeightValue() {
        return this.prefHeight;
    }

    public float getPrefWidth() {
        return this.prefWidth.get(this.actor);
    }

    @Null
    public Value getPrefWidthValue() {
        return this.prefWidth;
    }

    public int getRow() {
        return this.row;
    }

    public float getSpaceBottom() {
        return this.spaceBottom.get(this.actor);
    }

    @Null
    public Value getSpaceBottomValue() {
        return this.spaceBottom;
    }

    public float getSpaceLeft() {
        return this.spaceLeft.get(this.actor);
    }

    @Null
    public Value getSpaceLeftValue() {
        return this.spaceLeft;
    }

    public float getSpaceRight() {
        return this.spaceRight.get(this.actor);
    }

    @Null
    public Value getSpaceRightValue() {
        return this.spaceRight;
    }

    public float getSpaceTop() {
        return this.spaceTop.get(this.actor);
    }

    @Null
    public Value getSpaceTopValue() {
        return this.spaceTop;
    }

    public Table getTable() {
        return this.table;
    }

    public boolean getUniformX() {
        return this.uniformX.booleanValue();
    }

    public boolean getUniformY() {
        return this.uniformY.booleanValue();
    }

    public Cell grow() {
        this.expandX = Cell.onei;
        this.expandY = Cell.onei;
        this.fillX = Cell.onef;
        this.fillY = Cell.onef;
        return this;
    }

    public Cell growX() {
        this.expandX = Cell.onei;
        this.fillX = Cell.onef;
        return this;
    }

    public Cell growY() {
        this.expandY = Cell.onei;
        this.fillY = Cell.onef;
        return this;
    }

    public boolean hasActor() {
        return this.actor != null;
    }

    public Cell height(float f) {
        this.height(Fixed.valueOf(f));
        return this;
    }

    public Cell height(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minHeight = value0;
        this.prefHeight = value0;
        this.maxHeight = value0;
        return this;
    }

    public boolean isEndRow() {
        return this.endRow;
    }

    public Cell left() {
        Integer integer0 = this.align;
        if(integer0 == null) {
            this.align = Cell.lefti;
            return this;
        }
        this.align = (int)((((int)integer0) | 8) & -17);
        return this;
    }

    public Cell maxHeight(float f) {
        this.maxHeight = Fixed.valueOf(f);
        return this;
    }

    public Cell maxHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("maxHeight cannot be null.");
        }
        this.maxHeight = value0;
        return this;
    }

    public Cell maxSize(float f) {
        this.maxSize(Fixed.valueOf(f));
        return this;
    }

    public Cell maxSize(float f, float f1) {
        this.maxSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Cell maxSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.maxWidth = value0;
        this.maxHeight = value0;
        return this;
    }

    public Cell maxSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.maxWidth = value0;
        this.maxHeight = value1;
        return this;
    }

    public Cell maxWidth(float f) {
        this.maxWidth = Fixed.valueOf(f);
        return this;
    }

    public Cell maxWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("maxWidth cannot be null.");
        }
        this.maxWidth = value0;
        return this;
    }

    void merge(@Null Cell cell0) {
        if(cell0 == null) {
            return;
        }
        Value value0 = cell0.minWidth;
        if(value0 != null) {
            this.minWidth = value0;
        }
        Value value1 = cell0.minHeight;
        if(value1 != null) {
            this.minHeight = value1;
        }
        Value value2 = cell0.prefWidth;
        if(value2 != null) {
            this.prefWidth = value2;
        }
        Value value3 = cell0.prefHeight;
        if(value3 != null) {
            this.prefHeight = value3;
        }
        Value value4 = cell0.maxWidth;
        if(value4 != null) {
            this.maxWidth = value4;
        }
        Value value5 = cell0.maxHeight;
        if(value5 != null) {
            this.maxHeight = value5;
        }
        Value value6 = cell0.spaceTop;
        if(value6 != null) {
            this.spaceTop = value6;
        }
        Value value7 = cell0.spaceLeft;
        if(value7 != null) {
            this.spaceLeft = value7;
        }
        Value value8 = cell0.spaceBottom;
        if(value8 != null) {
            this.spaceBottom = value8;
        }
        Value value9 = cell0.spaceRight;
        if(value9 != null) {
            this.spaceRight = value9;
        }
        Value value10 = cell0.padTop;
        if(value10 != null) {
            this.padTop = value10;
        }
        Value value11 = cell0.padLeft;
        if(value11 != null) {
            this.padLeft = value11;
        }
        Value value12 = cell0.padBottom;
        if(value12 != null) {
            this.padBottom = value12;
        }
        Value value13 = cell0.padRight;
        if(value13 != null) {
            this.padRight = value13;
        }
        Float float0 = cell0.fillX;
        if(float0 != null) {
            this.fillX = float0;
        }
        Float float1 = cell0.fillY;
        if(float1 != null) {
            this.fillY = float1;
        }
        Integer integer0 = cell0.align;
        if(integer0 != null) {
            this.align = integer0;
        }
        Integer integer1 = cell0.expandX;
        if(integer1 != null) {
            this.expandX = integer1;
        }
        Integer integer2 = cell0.expandY;
        if(integer2 != null) {
            this.expandY = integer2;
        }
        Integer integer3 = cell0.colspan;
        if(integer3 != null) {
            this.colspan = integer3;
        }
        Boolean boolean0 = cell0.uniformX;
        if(boolean0 != null) {
            this.uniformX = boolean0;
        }
        Boolean boolean1 = cell0.uniformY;
        if(boolean1 != null) {
            this.uniformY = boolean1;
        }
    }

    public Cell minHeight(float f) {
        this.minHeight = Fixed.valueOf(f);
        return this;
    }

    public Cell minHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("minHeight cannot be null.");
        }
        this.minHeight = value0;
        return this;
    }

    public Cell minSize(float f) {
        this.minSize(Fixed.valueOf(f));
        return this;
    }

    public Cell minSize(float f, float f1) {
        this.minSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Cell minSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value0;
        return this;
    }

    public Cell minSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value1;
        return this;
    }

    public Cell minWidth(float f) {
        this.minWidth = Fixed.valueOf(f);
        return this;
    }

    public Cell minWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("minWidth cannot be null.");
        }
        this.minWidth = value0;
        return this;
    }

    public Cell pad(float f) {
        this.pad(Fixed.valueOf(f));
        return this;
    }

    public Cell pad(float f, float f1, float f2, float f3) {
        this.pad(Fixed.valueOf(f), Fixed.valueOf(f1), Fixed.valueOf(f2), Fixed.valueOf(f3));
        return this;
    }

    public Cell pad(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value0;
        this.padLeft = value0;
        this.padBottom = value0;
        this.padRight = value0;
        return this;
    }

    public Cell pad(Value value0, Value value1, Value value2, Value value3) {
        if(value0 == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if(value2 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if(value3 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.padTop = value0;
        this.padLeft = value1;
        this.padBottom = value2;
        this.padRight = value3;
        return this;
    }

    public Cell padBottom(float f) {
        this.padBottom = Fixed.valueOf(f);
        return this;
    }

    public Cell padBottom(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value0;
        return this;
    }

    public Cell padLeft(float f) {
        this.padLeft = Fixed.valueOf(f);
        return this;
    }

    public Cell padLeft(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value0;
        return this;
    }

    public Cell padRight(float f) {
        this.padRight = Fixed.valueOf(f);
        return this;
    }

    public Cell padRight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value0;
        return this;
    }

    public Cell padTop(float f) {
        this.padTop = Fixed.valueOf(f);
        return this;
    }

    public Cell padTop(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value0;
        return this;
    }

    public Cell prefHeight(float f) {
        this.prefHeight = Fixed.valueOf(f);
        return this;
    }

    public Cell prefHeight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("prefHeight cannot be null.");
        }
        this.prefHeight = value0;
        return this;
    }

    public Cell prefSize(float f) {
        this.prefSize(Fixed.valueOf(f));
        return this;
    }

    public Cell prefSize(float f, float f1) {
        this.prefSize(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Cell prefSize(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.prefWidth = value0;
        this.prefHeight = value0;
        return this;
    }

    public Cell prefSize(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.prefWidth = value0;
        this.prefHeight = value1;
        return this;
    }

    public Cell prefWidth(float f) {
        this.prefWidth = Fixed.valueOf(f);
        return this;
    }

    public Cell prefWidth(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("prefWidth cannot be null.");
        }
        this.prefWidth = value0;
        return this;
    }

    @Override  // com.badlogic.gdx.utils.Pool$Poolable
    public void reset() {
        this.actor = null;
        this.table = null;
        this.endRow = false;
        this.cellAboveIndex = -1;
        this.set(Cell.defaults());
    }

    public Cell right() {
        Integer integer0 = this.align;
        if(integer0 == null) {
            this.align = Cell.righti;
            return this;
        }
        this.align = (int)((((int)integer0) | 16) & -9);
        return this;
    }

    public void row() {
        this.table.row();
    }

    void set(Cell cell0) {
        this.minWidth = cell0.minWidth;
        this.minHeight = cell0.minHeight;
        this.prefWidth = cell0.prefWidth;
        this.prefHeight = cell0.prefHeight;
        this.maxWidth = cell0.maxWidth;
        this.maxHeight = cell0.maxHeight;
        this.spaceTop = cell0.spaceTop;
        this.spaceLeft = cell0.spaceLeft;
        this.spaceBottom = cell0.spaceBottom;
        this.spaceRight = cell0.spaceRight;
        this.padTop = cell0.padTop;
        this.padLeft = cell0.padLeft;
        this.padBottom = cell0.padBottom;
        this.padRight = cell0.padRight;
        this.fillX = cell0.fillX;
        this.fillY = cell0.fillY;
        this.align = cell0.align;
        this.expandX = cell0.expandX;
        this.expandY = cell0.expandY;
        this.colspan = cell0.colspan;
        this.uniformX = cell0.uniformX;
        this.uniformY = cell0.uniformY;
    }

    public Cell setActor(@Null Actor actor0) {
        Actor actor1 = this.actor;
        if(actor1 != actor0) {
            if(actor1 != null && actor1.getParent() == this.table) {
                this.actor.remove();
            }
            this.actor = actor0;
            if(actor0 != null) {
                this.table.addActor(actor0);
            }
        }
        return this;
    }

    public void setActorBounds(float f, float f1, float f2, float f3) {
        this.actorX = f;
        this.actorY = f1;
        this.actorWidth = f2;
        this.actorHeight = f3;
    }

    public void setActorHeight(float f) {
        this.actorHeight = f;
    }

    public void setActorWidth(float f) {
        this.actorWidth = f;
    }

    public void setActorX(float f) {
        this.actorX = f;
    }

    public void setActorY(float f) {
        this.actorY = f;
    }

    public void setTable(Table table0) {
        this.table = table0;
    }

    public Cell size(float f) {
        this.size(Fixed.valueOf(f));
        return this;
    }

    public Cell size(float f, float f1) {
        this.size(Fixed.valueOf(f), Fixed.valueOf(f1));
        return this;
    }

    public Cell size(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("size cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value0;
        this.prefWidth = value0;
        this.prefHeight = value0;
        this.maxWidth = value0;
        this.maxHeight = value0;
        return this;
    }

    public Cell size(Value value0, Value value1) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("height cannot be null.");
        }
        this.minWidth = value0;
        this.minHeight = value1;
        this.prefWidth = value0;
        this.prefHeight = value1;
        this.maxWidth = value0;
        this.maxHeight = value1;
        return this;
    }

    public Cell space(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("space cannot be < 0: " + f);
        }
        this.space(Fixed.valueOf(f));
        return this;
    }

    public Cell space(float f, float f1, float f2, float f3) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("top cannot be < 0: " + f);
        }
        if(f1 < 0.0f) {
            throw new IllegalArgumentException("left cannot be < 0: " + f1);
        }
        if(f2 < 0.0f) {
            throw new IllegalArgumentException("bottom cannot be < 0: " + f2);
        }
        if(f3 < 0.0f) {
            throw new IllegalArgumentException("right cannot be < 0: " + f3);
        }
        this.space(Fixed.valueOf(f), Fixed.valueOf(f1), Fixed.valueOf(f2), Fixed.valueOf(f3));
        return this;
    }

    public Cell space(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("space cannot be null.");
        }
        this.spaceTop = value0;
        this.spaceLeft = value0;
        this.spaceBottom = value0;
        this.spaceRight = value0;
        return this;
    }

    public Cell space(Value value0, Value value1, Value value2, Value value3) {
        if(value0 == null) {
            throw new IllegalArgumentException("top cannot be null.");
        }
        if(value1 == null) {
            throw new IllegalArgumentException("left cannot be null.");
        }
        if(value2 == null) {
            throw new IllegalArgumentException("bottom cannot be null.");
        }
        if(value3 == null) {
            throw new IllegalArgumentException("right cannot be null.");
        }
        this.spaceTop = value0;
        this.spaceLeft = value1;
        this.spaceBottom = value2;
        this.spaceRight = value3;
        return this;
    }

    public Cell spaceBottom(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("spaceBottom cannot be < 0: " + f);
        }
        this.spaceBottom = Fixed.valueOf(f);
        return this;
    }

    public Cell spaceBottom(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("spaceBottom cannot be null.");
        }
        this.spaceBottom = value0;
        return this;
    }

    public Cell spaceLeft(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("spaceLeft cannot be < 0: " + f);
        }
        this.spaceLeft = Fixed.valueOf(f);
        return this;
    }

    public Cell spaceLeft(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("spaceLeft cannot be null.");
        }
        this.spaceLeft = value0;
        return this;
    }

    public Cell spaceRight(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("spaceRight cannot be < 0: " + f);
        }
        this.spaceRight = Fixed.valueOf(f);
        return this;
    }

    public Cell spaceRight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("spaceRight cannot be null.");
        }
        this.spaceRight = value0;
        return this;
    }

    public Cell spaceTop(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("spaceTop cannot be < 0: " + f);
        }
        this.spaceTop = Fixed.valueOf(f);
        return this;
    }

    public Cell spaceTop(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("spaceTop cannot be null.");
        }
        this.spaceTop = value0;
        return this;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.actor == null ? super.toString() : "Actor";
    }

    public Cell top() {
        Integer integer0 = this.align;
        if(integer0 == null) {
            this.align = Cell.topi;
            return this;
        }
        this.align = (int)((((int)integer0) | 2) & -5);
        return this;
    }

    public Cell uniform() {
        this.uniformX = Boolean.TRUE;
        this.uniformY = Boolean.TRUE;
        return this;
    }

    public Cell uniform(boolean z) {
        this.uniformX = Boolean.valueOf(z);
        this.uniformY = Boolean.valueOf(z);
        return this;
    }

    public Cell uniform(boolean z, boolean z1) {
        this.uniformX = Boolean.valueOf(z);
        this.uniformY = Boolean.valueOf(z1);
        return this;
    }

    public Cell uniformX() {
        this.uniformX = Boolean.TRUE;
        return this;
    }

    public Cell uniformY() {
        this.uniformY = Boolean.TRUE;
        return this;
    }

    public Cell width(float f) {
        this.width(Fixed.valueOf(f));
        return this;
    }

    public Cell width(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("width cannot be null.");
        }
        this.minWidth = value0;
        this.prefWidth = value0;
        this.maxWidth = value0;
        return this;
    }
}

