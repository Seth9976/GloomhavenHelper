package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import java.util.Arrays;

public class Table extends WidgetGroup {
    public static enum Debug {
        none,
        all,
        table,
        cell,
        actor;

    }

    public static class DebugRect extends Rectangle {
        Color color;
        static Pool pool;

        static {
            DebugRect.pool = Pools.get(DebugRect.class);
        }
    }

    int align;
    @Null
    Drawable background;
    public static Value backgroundBottom;
    public static Value backgroundLeft;
    public static Value backgroundRight;
    public static Value backgroundTop;
    private final Cell cellDefaults;
    static final Pool cellPool;
    private final Array cells;
    private boolean clip;
    private final Array columnDefaults;
    private float[] columnMinWidth;
    private float[] columnPrefWidth;
    private static float[] columnWeightedWidth;
    private float[] columnWidth;
    private int columns;
    Debug debug;
    public static Color debugActorColor;
    public static Color debugCellColor;
    Array debugRects;
    public static Color debugTableColor;
    private float[] expandHeight;
    private float[] expandWidth;
    private boolean implicitEndRow;
    Value padBottom;
    Value padLeft;
    Value padRight;
    Value padTop;
    boolean round;
    private Cell rowDefaults;
    private float[] rowHeight;
    private float[] rowMinHeight;
    private float[] rowPrefHeight;
    private static float[] rowWeightedHeight;
    private int rows;
    private boolean sizeInvalid;
    @Null
    private Skin skin;
    private float tableMinHeight;
    private float tableMinWidth;
    private float tablePrefHeight;
    private float tablePrefWidth;

    static {
        Table.debugTableColor = new Color(0.0f, 0.0f, 1.0f, 1.0f);
        Table.debugCellColor = new Color(1.0f, 0.0f, 0.0f, 1.0f);
        Table.debugActorColor = new Color(0.0f, 1.0f, 0.0f, 1.0f);
        Table.cellPool = new Pool() {
            protected Cell newObject() {
                return new Cell();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        Table.backgroundTop = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                Drawable drawable0 = ((Table)actor0).background;
                return drawable0 == null ? 0.0f : drawable0.getTopHeight();
            }
        };
        Table.backgroundLeft = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                Drawable drawable0 = ((Table)actor0).background;
                return drawable0 == null ? 0.0f : drawable0.getLeftWidth();
            }
        };
        Table.backgroundBottom = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                Drawable drawable0 = ((Table)actor0).background;
                return drawable0 == null ? 0.0f : drawable0.getBottomHeight();
            }
        };
        Table.backgroundRight = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                Drawable drawable0 = ((Table)actor0).background;
                return drawable0 == null ? 0.0f : drawable0.getRightWidth();
            }
        };
    }

    public Table() {
        this(null);
    }

    public Table(@Null Skin skin0) {
        this.cells = new Array(4);
        this.columnDefaults = new Array(2);
        this.sizeInvalid = true;
        this.padTop = Table.backgroundTop;
        this.padLeft = Table.backgroundLeft;
        this.padBottom = Table.backgroundBottom;
        this.padRight = Table.backgroundRight;
        this.align = 1;
        this.debug = Debug.none;
        this.round = true;
        this.skin = skin0;
        this.cellDefaults = this.obtainCell();
        this.setTransform(false);
        this.setTouchable(Touchable.childrenOnly);
    }

    public Cell add() {
        return this.add(null);
    }

    public Cell add(@Null Actor actor0) {
        Cell cell0 = this.obtainCell();
        cell0.actor = actor0;
        if(this.implicitEndRow) {
            this.implicitEndRow = false;
            --this.rows;
            ((Cell)this.cells.peek()).endRow = false;
        }
        int v = this.cells.size;
        if(v > 0) {
            Cell cell1 = (Cell)this.cells.peek();
            if(cell1.endRow) {
                cell0.column = 0;
                cell0.row = cell1.row + 1;
            }
            else {
                cell0.column = cell1.column + ((int)cell1.colspan);
                cell0.row = cell1.row;
            }
            if(cell0.row > 0) {
                Object[] arr_object = this.cells.items;
                int v1 = v - 1;
            alab1:
                while(v1 >= 0) {
                    Cell cell2 = (Cell)arr_object[v1];
                    int v2 = cell2.column;
                    int v3 = ((int)cell2.colspan) + v2;
                    while(v2 < v3) {
                        if(v2 == cell0.column) {
                            cell0.cellAboveIndex = v1;
                            break alab1;
                        }
                        ++v2;
                    }
                    --v1;
                }
            }
        }
        else {
            cell0.column = 0;
            cell0.row = 0;
        }
        this.cells.add(cell0);
        cell0.set(this.cellDefaults);
        if(cell0.column < this.columnDefaults.size) {
            cell0.merge(((Cell)this.columnDefaults.get(cell0.column)));
        }
        cell0.merge(this.rowDefaults);
        if(actor0 != null) {
            this.addActor(actor0);
        }
        return cell0;
    }

    public Cell add(@Null CharSequence charSequence0) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return this.add(new Label(charSequence0, skin0));
    }

    public Cell add(@Null CharSequence charSequence0, String s) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return this.add(new Label(charSequence0, ((LabelStyle)skin0.get(s, LabelStyle.class))));
    }

    public Cell add(@Null CharSequence charSequence0, String s, @Null Color color0) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return this.add(new Label(charSequence0, new LabelStyle(skin0.getFont(s), color0)));
    }

    public Cell add(@Null CharSequence charSequence0, String s, String s1) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        return this.add(new Label(charSequence0, new LabelStyle(skin0.getFont(s), this.skin.getColor(s1))));
    }

    public Table add(Actor[] arr_actor) {
        for(int v = 0; v < arr_actor.length; ++v) {
            this.add(arr_actor[v]);
        }
        return this;
    }

    private void addDebugRect(float f, float f1, float f2, float f3, Color color0) {
        DebugRect table$DebugRect0 = (DebugRect)DebugRect.pool.obtain();
        table$DebugRect0.color = color0;
        table$DebugRect0.set(f, f1, f2, f3);
        this.debugRects.add(table$DebugRect0);
    }

    private void addDebugRects(float f, float f1, float f2, float f3) {
        this.clearDebugRects();
        if(this.debug == Debug.table || this.debug == Debug.all) {
            this.addDebugRect(0.0f, 0.0f, this.getWidth(), this.getHeight(), Table.debugTableColor);
            this.addDebugRect(f, this.getHeight() - f1, f2, -f3, Table.debugTableColor);
        }
        int v1 = this.cells.size;
        float f4 = f1;
        float f5 = f;
        for(int v = 0; v < v1; ++v) {
            Cell cell0 = (Cell)this.cells.get(v);
            if(this.debug == Debug.actor || this.debug == Debug.all) {
                this.addDebugRect(cell0.actorX, cell0.actorY, cell0.actorWidth, cell0.actorHeight, Table.debugActorColor);
            }
            float f6 = 0.0f;
            int v2 = cell0.column;
            int v3 = ((int)cell0.colspan) + v2;
            while(v2 < v3) {
                f6 += this.columnWidth[v2];
                ++v2;
            }
            float f7 = f6 - (cell0.computedPadLeft + cell0.computedPadRight);
            float f8 = f5 + cell0.computedPadLeft;
            if(this.debug == Debug.cell || this.debug == Debug.all) {
                float f9 = this.rowHeight[cell0.row] - cell0.computedPadTop - cell0.computedPadBottom;
                float f10 = cell0.computedPadTop + f4;
                this.addDebugRect(f8, this.getHeight() - f10, f7, -f9, Table.debugCellColor);
            }
            if(cell0.endRow) {
                f4 += this.rowHeight[cell0.row];
                f5 = f;
            }
            else {
                f5 = f8 + (f7 + cell0.computedPadRight);
            }
        }
    }

    public Table align(int v) {
        this.align = v;
        return this;
    }

    public Table background(@Null Drawable drawable0) {
        this.setBackground(drawable0);
        return this;
    }

    public Table background(String s) {
        this.setBackground(s);
        return this;
    }

    public Table bottom() {
        this.align &= -3;
        return this;
    }

    public Table center() {
        this.align = 1;
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren(boolean z) {
        Object[] arr_object = this.cells.items;
        for(int v = this.cells.size - 1; v >= 0; --v) {
            Actor actor0 = ((Cell)arr_object[v]).actor;
            if(actor0 != null) {
                actor0.remove();
            }
        }
        Table.cellPool.freeAll(this.cells);
        this.cells.clear();
        this.rows = 0;
        this.columns = 0;
        Cell cell0 = this.rowDefaults;
        if(cell0 != null) {
            Table.cellPool.free(cell0);
        }
        this.rowDefaults = null;
        this.implicitEndRow = false;
        super.clearChildren(z);
    }

    private void clearDebugRects() {
        if(this.debugRects == null) {
            this.debugRects = new Array();
        }
        DebugRect.pool.freeAll(this.debugRects);
        this.debugRects.clear();
    }

    public Table clip() {
        this.setClip(true);
        return this;
    }

    public Table clip(boolean z) {
        this.setClip(z);
        return this;
    }

    public Cell columnDefaults(int v) {
        Cell cell0 = this.columnDefaults.size <= v ? null : ((Cell)this.columnDefaults.get(v));
        if(cell0 == null) {
            cell0 = this.obtainCell();
            cell0.clear();
            if(v >= this.columnDefaults.size) {
                for(int v1 = this.columnDefaults.size; v1 < v; ++v1) {
                    this.columnDefaults.add(null);
                }
                this.columnDefaults.add(cell0);
                return cell0;
            }
            this.columnDefaults.set(v, cell0);
        }
        return cell0;
    }

    private void computeSize() {
        float f2;
        float[] arr_f6;
        this.sizeInvalid = false;
        Object[] arr_object = this.cells.items;
        int v = this.cells.size;
        if(v > 0 && !((Cell)arr_object[v - 1]).endRow) {
            this.endRow();
            this.implicitEndRow = true;
        }
        int v1 = this.columns;
        int v2 = this.rows;
        float[] arr_f = this.ensureSize(this.columnMinWidth, v1);
        this.columnMinWidth = arr_f;
        float[] arr_f1 = this.ensureSize(this.rowMinHeight, v2);
        this.rowMinHeight = arr_f1;
        float[] arr_f2 = this.ensureSize(this.columnPrefWidth, v1);
        this.columnPrefWidth = arr_f2;
        float[] arr_f3 = this.ensureSize(this.rowPrefHeight, v2);
        this.rowPrefHeight = arr_f3;
        this.columnWidth = this.ensureSize(this.columnWidth, v1);
        this.rowHeight = this.ensureSize(this.rowHeight, v2);
        float[] arr_f4 = this.ensureSize(this.expandWidth, v1);
        this.expandWidth = arr_f4;
        float[] arr_f5 = this.ensureSize(this.expandHeight, v2);
        this.expandHeight = arr_f5;
        int v3 = 0;
        for(float f = 0.0f; v3 < v; f = f3) {
            Cell cell0 = (Cell)arr_object[v3];
            int v4 = cell0.column;
            int v5 = cell0.row;
            int v6 = (int)cell0.colspan;
            Actor actor0 = cell0.actor;
            if(((int)cell0.expandY) != 0 && arr_f5[v5] == 0.0f) {
                arr_f5[v5] = (float)(((int)cell0.expandY));
            }
            if(v6 == 1 && ((int)cell0.expandX) != 0 && arr_f4[v4] == 0.0f) {
                arr_f4[v4] = (float)(((int)cell0.expandX));
            }
            float f1 = cell0.padLeft.get(actor0);
            if(v4 == 0) {
                arr_f6 = arr_f5;
                f2 = 0.0f;
            }
            else {
                arr_f6 = arr_f5;
                f2 = Math.max(0.0f, cell0.spaceLeft.get(actor0) - f);
            }
            cell0.computedPadLeft = f1 + f2;
            cell0.computedPadTop = cell0.padTop.get(actor0);
            if(cell0.cellAboveIndex != -1) {
                Cell cell1 = (Cell)arr_object[cell0.cellAboveIndex];
                cell0.computedPadTop += Math.max(0.0f, cell0.spaceTop.get(actor0) - cell1.spaceBottom.get(actor0));
            }
            float f3 = cell0.spaceRight.get(actor0);
            cell0.computedPadRight = cell0.padRight.get(actor0) + (v4 + v6 == v1 ? 0.0f : f3);
            cell0.computedPadBottom = cell0.padBottom.get(actor0) + (v5 == v2 - 1 ? 0.0f : cell0.spaceBottom.get(actor0));
            float f4 = cell0.prefWidth.get(actor0);
            float f5 = cell0.prefHeight.get(actor0);
            float f6 = cell0.minWidth.get(actor0);
            float f7 = cell0.minHeight.get(actor0);
            float f8 = cell0.maxWidth.get(actor0);
            float f9 = cell0.maxHeight.get(actor0);
            if(f4 < f6) {
                f4 = f6;
            }
            if(f5 < f7) {
                f5 = f7;
            }
            if(f8 <= 0.0f || f4 <= f8) {
                f8 = f4;
            }
            if(f9 <= 0.0f || f5 <= f9) {
                f9 = f5;
            }
            if(this.round) {
                f7 = (float)Math.ceil(f7);
                f8 = (float)Math.ceil(f8);
                f9 = (float)Math.ceil(f9);
                f6 = (float)Math.ceil(f6);
            }
            if(v6 == 1) {
                float f10 = cell0.computedPadLeft + cell0.computedPadRight;
                arr_f2[v4] = Math.max(arr_f2[v4], f8 + f10);
                arr_f[v4] = Math.max(arr_f[v4], f6 + f10);
            }
            float f11 = cell0.computedPadTop + cell0.computedPadBottom;
            arr_f3[v5] = Math.max(arr_f3[v5], f9 + f11);
            arr_f1[v5] = Math.max(arr_f1[v5], f7 + f11);
            ++v3;
            arr_f5 = arr_f6;
        }
        int v7 = 0;
        float f12 = 0.0f;
        float f13 = 0.0f;
        float f14 = 0.0f;
        float f15 = 0.0f;
        while(v7 < v) {
            Cell cell2 = (Cell)arr_object[v7];
            int v8 = cell2.column;
            int v9 = (int)cell2.expandX;
            if(v9 != 0) {
                int v10 = ((int)cell2.colspan) + v8;
                int v11 = v8;
                while(v11 < v10) {
                    if(arr_f4[v11] != 0.0f) {
                        goto label_100;
                    }
                    ++v11;
                }
                for(int v12 = v8; v12 < v10; ++v12) {
                    arr_f4[v12] = (float)v9;
                }
            }
        label_100:
            if(cell2.uniformX == Boolean.TRUE && ((int)cell2.colspan) == 1) {
                float f16 = cell2.computedPadLeft + cell2.computedPadRight;
                f14 = Math.max(f14, arr_f[v8] - f16);
                f12 = Math.max(f12, arr_f2[v8] - f16);
            }
            if(cell2.uniformY == Boolean.TRUE) {
                float f17 = cell2.computedPadTop + cell2.computedPadBottom;
                f15 = Math.max(f15, arr_f1[cell2.row] - f17);
                f13 = Math.max(f13, arr_f3[cell2.row] - f17);
            }
            ++v7;
        }
        if(f12 > 0.0f || f13 > 0.0f) {
            for(int v13 = 0; v13 < v; ++v13) {
                Cell cell3 = (Cell)arr_object[v13];
                if(f12 > 0.0f && cell3.uniformX == Boolean.TRUE && ((int)cell3.colspan) == 1) {
                    float f18 = cell3.computedPadLeft + cell3.computedPadRight;
                    arr_f[cell3.column] = f14 + f18;
                    arr_f2[cell3.column] = f18 + f12;
                }
                if(f13 > 0.0f && cell3.uniformY == Boolean.TRUE) {
                    float f19 = cell3.computedPadTop + cell3.computedPadBottom;
                    arr_f1[cell3.row] = f15 + f19;
                    arr_f3[cell3.row] = f19 + f13;
                }
            }
        }
        for(int v14 = 0; v14 < v; ++v14) {
            Cell cell4 = (Cell)arr_object[v14];
            int v15 = (int)cell4.colspan;
            if(v15 != 1) {
                int v16 = cell4.column;
                Actor actor1 = cell4.actor;
                float f20 = cell4.minWidth.get(actor1);
                float f21 = cell4.prefWidth.get(actor1);
                float f22 = cell4.maxWidth.get(actor1);
                if(f21 < f20) {
                    f21 = f20;
                }
                if(f22 <= 0.0f || f21 <= f22) {
                    f22 = f21;
                }
                if(this.round) {
                    f20 = (float)Math.ceil(f20);
                    f22 = (float)Math.ceil(f22);
                }
                int v17 = v16 + v15;
                float f23 = -(cell4.computedPadLeft + cell4.computedPadRight);
                float f24 = f23;
                int v18 = v16;
                float f25 = 0.0f;
                while(v18 < v17) {
                    f23 += arr_f[v18];
                    f24 += arr_f2[v18];
                    f25 += arr_f4[v18];
                    ++v18;
                }
                float f26 = Math.max(0.0f, f20 - f23);
                float f27 = Math.max(0.0f, f22 - f24);
                while(v16 < v17) {
                    float f28 = f25 == 0.0f ? 1.0f / ((float)v15) : arr_f4[v16] / f25;
                    arr_f[v16] += f26 * f28;
                    arr_f2[v16] += f28 * f27;
                    ++v16;
                }
            }
        }
        float f29 = this.padLeft.get(this) + this.padRight.get(this);
        float f30 = this.padTop.get(this) + this.padBottom.get(this);
        this.tableMinWidth = f29;
        this.tablePrefWidth = f29;
        for(int v19 = 0; v19 < v1; ++v19) {
            this.tableMinWidth += arr_f[v19];
            this.tablePrefWidth += arr_f2[v19];
        }
        this.tableMinHeight = f30;
        this.tablePrefHeight = f30;
        for(int v20 = 0; v20 < v2; ++v20) {
            this.tableMinHeight += arr_f1[v20];
            this.tablePrefHeight += Math.max(arr_f1[v20], arr_f3[v20]);
        }
        this.tablePrefWidth = Math.max(this.tableMinWidth, this.tablePrefWidth);
        this.tablePrefHeight = Math.max(this.tableMinHeight, this.tablePrefHeight);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public Actor debug() {
        return this.debug();
    }

    public Table debug() {
        super.debug();
        return this;
    }

    public Table debug(Debug table$Debug0) {
        super.setDebug(table$Debug0 != Debug.none);
        if(this.debug != table$Debug0) {
            this.debug = table$Debug0;
            if(table$Debug0 == Debug.none) {
                this.clearDebugRects();
                return this;
            }
            this.invalidate();
        }
        return this;
    }

    public Table debugActor() {
        super.setDebug(true);
        if(this.debug != Debug.actor) {
            this.debug = Debug.actor;
            this.invalidate();
        }
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public Group debugAll() {
        return this.debugAll();
    }

    public Table debugAll() {
        super.debugAll();
        return this;
    }

    public Table debugCell() {
        super.setDebug(true);
        if(this.debug != Debug.cell) {
            this.debug = Debug.cell;
            this.invalidate();
        }
        return this;
    }

    public Table debugTable() {
        super.setDebug(true);
        if(this.debug != Debug.table) {
            this.debug = Debug.table;
            this.invalidate();
        }
        return this;
    }

    public Cell defaults() {
        return this.cellDefaults;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void draw(Batch batch0, float f) {
        this.validate();
        if(this.isTransform()) {
            this.applyTransform(batch0, this.computeTransform());
            this.drawBackground(batch0, f, 0.0f, 0.0f);
            if(this.clip) {
                batch0.flush();
                float f1 = this.padLeft.get(this);
                float f2 = this.padBottom.get(this);
                if(this.clipBegin(f1, f2, this.getWidth() - f1 - this.padRight.get(this), this.getHeight() - f2 - this.padTop.get(this))) {
                    this.drawChildren(batch0, f);
                    batch0.flush();
                    this.clipEnd();
                }
            }
            else {
                this.drawChildren(batch0, f);
            }
            this.resetTransform(batch0);
            return;
        }
        this.drawBackground(batch0, f, this.getX(), this.getY());
        super.draw(batch0, f);
    }

    protected void drawBackground(Batch batch0, float f, float f1, float f2) {
        if(this.background == null) {
            return;
        }
        Color color0 = this.getColor();
        batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
        this.background.draw(batch0, f1, f2, this.getWidth(), this.getHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void drawDebug(ShapeRenderer shapeRenderer0) {
        float f3;
        if(this.isTransform()) {
            this.applyTransform(shapeRenderer0, this.computeTransform());
            this.drawDebugRects(shapeRenderer0);
            if(this.clip) {
                shapeRenderer0.flush();
                float f = this.getWidth();
                float f1 = this.getHeight();
                float f2 = 0.0f;
                if(this.background == null) {
                    f3 = 0.0f;
                }
                else {
                    f2 = this.padLeft.get(this);
                    f3 = this.padBottom.get(this);
                    f -= this.padRight.get(this) + f2;
                    f1 -= this.padTop.get(this) + f3;
                }
                if(this.clipBegin(f2, f3, f, f1)) {
                    this.drawDebugChildren(shapeRenderer0);
                    this.clipEnd();
                }
            }
            else {
                this.drawDebugChildren(shapeRenderer0);
            }
            this.resetTransform(shapeRenderer0);
            return;
        }
        this.drawDebugRects(shapeRenderer0);
        super.drawDebug(shapeRenderer0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void drawDebugBounds(ShapeRenderer shapeRenderer0) {
    }

    private void drawDebugRects(ShapeRenderer shapeRenderer0) {
        float f1;
        if(this.debugRects != null && this.getDebug()) {
            shapeRenderer0.set(ShapeType.Line);
            if(this.getStage() != null) {
                shapeRenderer0.setColor(this.getStage().getDebugColor());
            }
            float f = 0.0f;
            if(this.isTransform()) {
                f1 = 0.0f;
            }
            else {
                f = this.getX();
                f1 = this.getY();
            }
            int v1 = this.debugRects.size;
            for(int v = 0; v < v1; ++v) {
                DebugRect table$DebugRect0 = (DebugRect)this.debugRects.get(v);
                shapeRenderer0.setColor(table$DebugRect0.color);
                shapeRenderer0.rect(table$DebugRect0.x + f, table$DebugRect0.y + f1, table$DebugRect0.width, table$DebugRect0.height);
            }
        }
    }

    private void endRow() {
        Object[] arr_object = this.cells.items;
        int v = this.cells.size - 1;
        int v1 = 0;
        while(v >= 0) {
            Cell cell0 = (Cell)arr_object[v];
            if(cell0.endRow) {
                break;
            }
            v1 += (int)cell0.colspan;
            --v;
        }
        this.columns = Math.max(this.columns, v1);
        ++this.rows;
        ((Cell)this.cells.peek()).endRow = true;
    }

    private float[] ensureSize(float[] arr_f, int v) {
        if(arr_f != null && arr_f.length >= v) {
            Arrays.fill(arr_f, 0, v, 0.0f);
            return arr_f;
        }
        return new float[v];
    }

    public int getAlign() {
        return this.align;
    }

    @Null
    public Drawable getBackground() {
        return this.background;
    }

    @Null
    public Cell getCell(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Object[] arr_object = this.cells.items;
        int v1 = this.cells.size;
        for(int v = 0; v < v1; ++v) {
            Cell cell0 = (Cell)arr_object[v];
            if(cell0.actor == actor0) {
                return cell0;
            }
        }
        return null;
    }

    public Array getCells() {
        return this.cells;
    }

    public boolean getClip() {
        return this.clip;
    }

    public float getColumnMinWidth(int v) {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.columnMinWidth[v];
    }

    public float getColumnPrefWidth(int v) {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.columnPrefWidth[v];
    }

    public float getColumnWidth(int v) {
        return this.columnWidth == null ? 0.0f : this.columnWidth[v];
    }

    public int getColumns() {
        return this.columns;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.tableMinHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getMinWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.tableMinWidth;
    }

    public float getPadBottom() {
        return this.padBottom.get(this);
    }

    public Value getPadBottomValue() {
        return this.padBottom;
    }

    public float getPadLeft() {
        return this.padLeft.get(this);
    }

    public Value getPadLeftValue() {
        return this.padLeft;
    }

    public float getPadRight() {
        return this.padRight.get(this);
    }

    public Value getPadRightValue() {
        return this.padRight;
    }

    public float getPadTop() {
        return this.padTop.get(this);
    }

    public Value getPadTopValue() {
        return this.padTop;
    }

    public float getPadX() {
        return this.padLeft.get(this) + this.padRight.get(this);
    }

    public float getPadY() {
        return this.padTop.get(this) + this.padBottom.get(this);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.background == null ? this.tablePrefHeight : Math.max(this.tablePrefHeight, this.background.getMinHeight());
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.background == null ? this.tablePrefWidth : Math.max(this.tablePrefWidth, this.background.getMinWidth());
    }

    public int getRow(float f) {
        int v = this.cells.size;
        if(v == 0) {
            return -1;
        }
        float f1 = this.getPadTop();
        Object[] arr_object = this.cells.items;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Cell cell0 = (Cell)arr_object[v1];
            if(cell0.actorY + cell0.computedPadTop < f + f1) {
                return v2;
            }
            if(cell0.endRow) {
                ++v2;
            }
        }
        return -1;
    }

    public float getRowHeight(int v) {
        return this.rowHeight == null ? 0.0f : this.rowHeight[v];
    }

    public float getRowMinHeight(int v) {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.rowMinHeight[v];
    }

    public float getRowPrefHeight(int v) {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.rowPrefHeight[v];
    }

    public int getRows() {
        return this.rows;
    }

    @Null
    public Skin getSkin() {
        return this.skin;
    }

    public Debug getTableDebug() {
        return this.debug;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(this.clip) {
            if(z && this.getTouchable() == Touchable.disabled) {
                return null;
            }
            return f >= 0.0f && f < this.getWidth() && f1 >= 0.0f && f1 < this.getHeight() ? super.hit(f, f1, z) : null;
        }
        return super.hit(f, f1, z);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void invalidate() {
        this.sizeInvalid = true;
        super.invalidate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        float[] arr_f11;
        int v31;
        float f34;
        float f33;
        float f11;
        float[] arr_f5;
        float[] arr_f2;
        float f8;
        if(this.sizeInvalid) {
            this.computeSize();
        }
        float f = this.getWidth();
        float f1 = this.getHeight();
        int v = this.columns;
        int v1 = this.rows;
        float[] arr_f = this.columnWidth;
        float[] arr_f1 = this.rowHeight;
        float f2 = this.padLeft.get(this);
        float f3 = this.padRight.get(this) + f2;
        float f4 = this.padTop.get(this);
        float f5 = this.padBottom.get(this) + f4;
        float f6 = this.tableMinWidth;
        float f7 = this.tablePrefWidth - f6;
        if(f7 == 0.0f) {
            f8 = f4;
            arr_f2 = this.columnMinWidth;
        }
        else {
            float f9 = Math.min(f7, Math.max(0.0f, f - f6));
            arr_f2 = this.ensureSize(Table.columnWeightedWidth, v);
            Table.columnWeightedWidth = arr_f2;
            float[] arr_f3 = this.columnMinWidth;
            float[] arr_f4 = this.columnPrefWidth;
            f8 = f4;
            for(int v2 = 0; v2 < v; ++v2) {
                arr_f2[v2] = arr_f3[v2] + (arr_f4[v2] - arr_f3[v2]) / f7 * f9;
            }
        }
        float f10 = this.tablePrefHeight - this.tableMinHeight;
        if(f10 == 0.0f) {
            arr_f5 = this.rowMinHeight;
            f11 = f2;
        }
        else {
            float[] arr_f6 = this.ensureSize(Table.rowWeightedHeight, v1);
            Table.rowWeightedHeight = arr_f6;
            float f12 = Math.min(f10, Math.max(0.0f, f1 - this.tableMinHeight));
            float[] arr_f7 = this.rowMinHeight;
            float[] arr_f8 = this.rowPrefHeight;
            f11 = f2;
            for(int v3 = 0; v3 < v1; ++v3) {
                arr_f6[v3] = arr_f7[v3] + (arr_f8[v3] - arr_f7[v3]) / f10 * f12;
            }
            arr_f5 = arr_f6;
        }
        Object[] arr_object = this.cells.items;
        int v4 = this.cells.size;
        for(int v5 = 0; v5 < v4; ++v5) {
            Cell cell0 = (Cell)arr_object[v5];
            int v6 = cell0.column;
            int v7 = cell0.row;
            Actor actor0 = cell0.actor;
            int v8 = (int)cell0.colspan;
            int v9 = v6;
            float f13 = 0.0f;
            while(v9 < v6 + v8) {
                f13 += arr_f2[v9];
                ++v9;
            }
            float f14 = arr_f5[v7];
            float f15 = cell0.prefWidth.get(actor0);
            float f16 = cell0.prefHeight.get(actor0);
            float f17 = cell0.minWidth.get(actor0);
            float f18 = cell0.minHeight.get(actor0);
            float f19 = cell0.maxWidth.get(actor0);
            float f20 = cell0.maxHeight.get(actor0);
            if(f15 >= f17) {
                f17 = f15;
            }
            if(f16 >= f18) {
                f18 = f16;
            }
            if(f19 > 0.0f && f17 > f19) {
                f17 = f19;
            }
            if(f20 <= 0.0f || f18 <= f20) {
                f20 = f18;
            }
            cell0.actorWidth = Math.min(f13 - cell0.computedPadLeft - cell0.computedPadRight, f17);
            cell0.actorHeight = Math.min(f14 - cell0.computedPadTop - cell0.computedPadBottom, f20);
            if(v8 == 1) {
                arr_f[v6] = Math.max(arr_f[v6], f13);
            }
            arr_f1[v7] = Math.max(arr_f1[v7], f14);
        }
        float[] arr_f9 = this.expandWidth;
        float[] arr_f10 = this.expandHeight;
        float f21 = 0.0f;
        for(int v10 = 0; v10 < v; ++v10) {
            f21 += arr_f9[v10];
        }
        if(f21 > 0.0f) {
            float f22 = f - f3;
            for(int v11 = 0; v11 < v; ++v11) {
                f22 -= arr_f[v11];
            }
            if(f22 > 0.0f) {
                int v13 = 0;
                float f23 = 0.0f;
                for(int v12 = 0; v12 < v; ++v12) {
                    if(arr_f9[v12] != 0.0f) {
                        float f24 = arr_f9[v12] * f22 / f21;
                        arr_f[v12] += f24;
                        f23 += f24;
                        v13 = v12;
                    }
                }
                arr_f[v13] += f22 - f23;
            }
        }
        float f25 = 0.0f;
        for(int v14 = 0; v14 < v1; ++v14) {
            f25 += arr_f10[v14];
        }
        if(f25 > 0.0f) {
            float f26 = f1 - f5;
            for(int v15 = 0; v15 < v1; ++v15) {
                f26 -= arr_f1[v15];
            }
            if(f26 > 0.0f) {
                int v17 = 0;
                float f27 = 0.0f;
                for(int v16 = 0; v16 < v1; ++v16) {
                    if(arr_f10[v16] != 0.0f) {
                        float f28 = arr_f10[v16] * f26 / f25;
                        arr_f1[v16] += f28;
                        f27 += f28;
                        v17 = v16;
                    }
                }
                arr_f1[v17] += f26 - f27;
            }
        }
        int v18 = v4;
        for(int v19 = 0; v19 < v18; ++v19) {
            Cell cell1 = (Cell)arr_object[v19];
            int v20 = (int)cell1.colspan;
            if(v20 != 1) {
                int v21 = cell1.column;
                int v22 = v21 + v20;
                float f29 = 0.0f;
                while(v21 < v22) {
                    f29 += arr_f2[v21] - arr_f[v21];
                    ++v21;
                }
                float f30 = (f29 - Math.max(0.0f, cell1.computedPadLeft + cell1.computedPadRight)) / ((float)v20);
                if(f30 > 0.0f) {
                    int v23 = cell1.column;
                    int v24 = v20 + v23;
                    while(v23 < v24) {
                        arr_f[v23] += f30;
                        ++v23;
                    }
                }
            }
        }
        float f31 = f3;
        for(int v25 = 0; v25 < v; ++v25) {
            f31 += arr_f[v25];
        }
        float f32 = f5;
        for(int v26 = 0; v26 < v1; ++v26) {
            f32 += arr_f1[v26];
        }
        int v27 = this.align;
        if((v27 & 16) == 0) {
            f33 = (v27 & 8) == 0 ? f11 + (f - f31) / 2.0f : f11;
        }
        else {
            f33 = f11 + (f - f31);
        }
        if((v27 & 4) == 0) {
            f34 = (v27 & 2) == 0 ? f8 + (f1 - f32) / 2.0f : f8;
        }
        else {
            f34 = f8 + (f1 - f32);
        }
        float f35 = f33;
        float f36 = f34;
        int v28 = 0;
        while(v28 < v18) {
            Cell cell2 = (Cell)arr_object[v28];
            int v29 = cell2.column;
            int v30 = ((int)cell2.colspan) + v29;
            float f37 = 0.0f;
            while(v29 < v30) {
                f37 += arr_f[v29];
                ++v29;
            }
            float f38 = f37 - (cell2.computedPadLeft + cell2.computedPadRight);
            float f39 = f35 + cell2.computedPadLeft;
            float f40 = (float)cell2.fillX;
            float f41 = (float)cell2.fillY;
            if(f40 > 0.0f) {
                v31 = v18;
                cell2.actorWidth = Math.max(f40 * f38, cell2.minWidth.get(cell2.actor));
                float f42 = cell2.maxWidth.get(cell2.actor);
                if(f42 > 0.0f) {
                    cell2.actorWidth = Math.min(cell2.actorWidth, f42);
                }
            }
            else {
                v31 = v18;
            }
            if(f41 > 0.0f) {
                cell2.actorHeight = Math.max(arr_f1[cell2.row] * f41 - cell2.computedPadTop - cell2.computedPadBottom, cell2.minHeight.get(cell2.actor));
                float f43 = cell2.maxHeight.get(cell2.actor);
                if(f43 > 0.0f) {
                    cell2.actorHeight = Math.min(cell2.actorHeight, f43);
                }
            }
            int v32 = (int)cell2.align;
            if((v32 & 8) != 0) {
                cell2.actorX = f39;
            }
            else if((v32 & 16) == 0) {
                cell2.actorX = (f38 - cell2.actorWidth) / 2.0f + f39;
            }
            else {
                cell2.actorX = f39 + f38 - cell2.actorWidth;
            }
            if((v32 & 2) != 0) {
                cell2.actorY = cell2.computedPadTop;
            }
            else if((v32 & 4) == 0) {
                cell2.actorY = (arr_f1[cell2.row] - cell2.actorHeight + cell2.computedPadTop - cell2.computedPadBottom) / 2.0f;
            }
            else {
                cell2.actorY = arr_f1[cell2.row] - cell2.actorHeight - cell2.computedPadBottom;
            }
            cell2.actorY = f1 - f36 - cell2.actorY - cell2.actorHeight;
            if(this.round) {
                cell2.actorWidth = (float)Math.ceil(cell2.actorWidth);
                cell2.actorHeight = (float)Math.ceil(cell2.actorHeight);
                cell2.actorX = (float)Math.floor(cell2.actorX);
                cell2.actorY = (float)Math.floor(cell2.actorY);
            }
            if(cell2.actor == null) {
                arr_f11 = arr_f;
            }
            else {
                arr_f11 = arr_f;
                cell2.actor.setBounds(cell2.actorX, cell2.actorY, cell2.actorWidth, cell2.actorHeight);
            }
            if(cell2.endRow) {
                f36 += arr_f1[cell2.row];
                f35 = f33;
            }
            else {
                f35 = f39 + (f38 + cell2.computedPadRight);
            }
            ++v28;
            arr_f = arr_f11;
            v18 = v31;
        }
        SnapshotArray snapshotArray0 = this.getChildren();
        Actor[] arr_actor = (Actor[])snapshotArray0.items;
        int v33 = snapshotArray0.size;
        for(int v34 = 0; v34 < v33; ++v34) {
            Actor actor1 = arr_actor[v34];
            if(actor1 instanceof Layout) {
                ((Layout)actor1).validate();
            }
        }
        if(this.debug != Debug.none) {
            this.addDebugRects(f33, f34, f31 - f3, f32 - f5);
        }
    }

    public Table left() {
        this.align &= -17;
        return this;
    }

    private Cell obtainCell() {
        Cell cell0 = (Cell)Table.cellPool.obtain();
        cell0.setTable(this);
        return cell0;
    }

    public Table pad(float f) {
        this.pad(Fixed.valueOf(f));
        return this;
    }

    public Table pad(float f, float f1, float f2, float f3) {
        this.padTop = Fixed.valueOf(f);
        this.padLeft = Fixed.valueOf(f1);
        this.padBottom = Fixed.valueOf(f2);
        this.padRight = Fixed.valueOf(f3);
        this.sizeInvalid = true;
        return this;
    }

    public Table pad(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("pad cannot be null.");
        }
        this.padTop = value0;
        this.padLeft = value0;
        this.padBottom = value0;
        this.padRight = value0;
        this.sizeInvalid = true;
        return this;
    }

    public Table pad(Value value0, Value value1, Value value2, Value value3) {
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
        this.sizeInvalid = true;
        return this;
    }

    public Table padBottom(float f) {
        this.padBottom = Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padBottom(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padBottom cannot be null.");
        }
        this.padBottom = value0;
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(float f) {
        this.padLeft = Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padLeft(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padLeft cannot be null.");
        }
        this.padLeft = value0;
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(float f) {
        this.padRight = Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padRight(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padRight cannot be null.");
        }
        this.padRight = value0;
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(float f) {
        this.padTop = Fixed.valueOf(f);
        this.sizeInvalid = true;
        return this;
    }

    public Table padTop(Value value0) {
        if(value0 == null) {
            throw new IllegalArgumentException("padTop cannot be null.");
        }
        this.padTop = value0;
        this.sizeInvalid = true;
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0) {
        return this.removeActor(actor0, true);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public boolean removeActor(Actor actor0, boolean z) {
        if(!super.removeActor(actor0, z)) {
            return false;
        }
        Cell cell0 = this.getCell(actor0);
        if(cell0 != null) {
            cell0.actor = null;
        }
        return true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public Actor removeActorAt(int v, boolean z) {
        Actor actor0 = super.removeActorAt(v, z);
        Cell cell0 = this.getCell(actor0);
        if(cell0 != null) {
            cell0.actor = null;
        }
        return actor0;
    }

    public void reset() {
        this.clearChildren();
        this.padTop = Table.backgroundTop;
        this.padLeft = Table.backgroundLeft;
        this.padBottom = Table.backgroundBottom;
        this.padRight = Table.backgroundRight;
        this.align = 1;
        this.debug(Debug.none);
        this.cellDefaults.reset();
        int v = this.columnDefaults.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Cell cell0 = (Cell)this.columnDefaults.get(v1);
            if(cell0 != null) {
                Table.cellPool.free(cell0);
            }
        }
        this.columnDefaults.clear();
    }

    public Table right() {
        this.align &= -9;
        return this;
    }

    public Cell row() {
        if(this.cells.size > 0) {
            if(!this.implicitEndRow) {
                if(((Cell)this.cells.peek()).endRow) {
                    return this.rowDefaults;
                }
                this.endRow();
            }
            this.invalidate();
        }
        this.implicitEndRow = false;
        Cell cell0 = this.rowDefaults;
        if(cell0 != null) {
            Table.cellPool.free(cell0);
        }
        this.rowDefaults = this.obtainCell();
        this.rowDefaults.clear();
        return this.rowDefaults;
    }

    public void setBackground(@Null Drawable drawable0) {
        if(this.background == drawable0) {
            return;
        }
        float f = this.getPadTop();
        float f1 = this.getPadLeft();
        float f2 = this.getPadBottom();
        float f3 = this.getPadRight();
        this.background = drawable0;
        float f4 = this.getPadTop();
        float f5 = this.getPadLeft();
        float f6 = this.getPadBottom();
        float f7 = this.getPadRight();
        if(f + f2 != f4 + f6 || f1 + f3 != f5 + f7) {
            this.invalidateHierarchy();
        }
        else if(f != f4 || f1 != f5 || f2 != f6 || f3 != f7) {
            this.invalidate();
        }
    }

    public void setBackground(String s) {
        Skin skin0 = this.skin;
        if(skin0 == null) {
            throw new IllegalStateException("Table must have a skin set to use this method.");
        }
        this.setBackground(skin0.getDrawable(s));
    }

    public void setClip(boolean z) {
        this.clip = z;
        this.setTransform(z);
        this.invalidate();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void setDebug(boolean z) {
        this.debug((z ? Debug.all : Debug.none));
    }

    public void setRound(boolean z) {
        this.round = z;
    }

    public void setSkin(@Null Skin skin0) {
        this.skin = skin0;
    }

    public Cell stack(@Null Actor[] arr_actor) {
        Stack stack0 = new Stack();
        if(arr_actor != null) {
            for(int v = 0; v < arr_actor.length; ++v) {
                stack0.addActor(arr_actor[v]);
            }
        }
        return this.add(stack0);
    }

    public Table top() {
        this.align &= -5;
        return this;
    }
}

