package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.reflect.ClassReflection;

public class Actor {
    private final Array actions;
    private final DelayedRemovalArray captureListeners;
    final Color color;
    private boolean debug;
    float height;
    private final DelayedRemovalArray listeners;
    @Null
    private String name;
    float originX;
    float originY;
    @Null
    Group parent;
    float rotation;
    float scaleX;
    float scaleY;
    @Null
    private Stage stage;
    private Touchable touchable;
    @Null
    private Object userObject;
    private boolean visible;
    float width;
    float x;
    float y;

    public Actor() {
        this.listeners = new DelayedRemovalArray(0);
        this.captureListeners = new DelayedRemovalArray(0);
        this.actions = new Array(0);
        this.touchable = Touchable.enabled;
        this.visible = true;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.color = new Color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void act(float f) {
        Array array0 = this.actions;
        if(array0.size == 0) {
            return;
        }
        if(this.stage != null && this.stage.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
        }
        try {
            for(int v = 0; v < array0.size; ++v) {
                Action action0 = (Action)array0.get(v);
                if(action0.act(f) && v < array0.size) {
                    int v1 = ((Action)array0.get(v)) == action0 ? v : array0.indexOf(action0, true);
                    if(v1 != -1) {
                        array0.removeIndex(v1);
                        action0.setActor(null);
                        --v;
                    }
                }
            }
        }
        catch(RuntimeException runtimeException0) {
            throw new RuntimeException("Actor: Actor", runtimeException0);
        }
    }

    public void addAction(Action action0) {
        action0.setActor(this);
        this.actions.add(action0);
        if(this.stage != null && this.stage.getActionsRequestRendering()) {
            Gdx.graphics.requestRendering();
        }
    }

    public boolean addCaptureListener(EventListener eventListener0) {
        if(eventListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if(!this.captureListeners.contains(eventListener0, true)) {
            this.captureListeners.add(eventListener0);
        }
        return true;
    }

    public boolean addListener(EventListener eventListener0) {
        if(eventListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        if(!this.listeners.contains(eventListener0, true)) {
            this.listeners.add(eventListener0);
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean ancestorsVisible() {
        return this.ascendantsVisible();
    }

    public boolean ascendantsVisible() {
        do {
            if(!this.isVisible()) {
                return false;
            }
            this = this.parent;
        }
        while(this != null);
        return true;
    }

    public void clear() {
        this.clearActions();
        this.clearListeners();
    }

    public void clearActions() {
        for(int v = this.actions.size - 1; v >= 0; --v) {
            ((Action)this.actions.get(v)).setActor(null);
        }
        this.actions.clear();
    }

    public void clearListeners() {
        this.listeners.clear();
        this.captureListeners.clear();
    }

    public boolean clipBegin() {
        return this.clipBegin(this.x, this.y, this.width, this.height);
    }

    public boolean clipBegin(float f, float f1, float f2, float f3) {
        if(f2 > 0.0f && f3 > 0.0f) {
            Stage stage0 = this.stage;
            if(stage0 == null) {
                return false;
            }
            Rectangle.tmp.x = f;
            Rectangle.tmp.y = f1;
            Rectangle.tmp.width = f2;
            Rectangle.tmp.height = f3;
            Rectangle rectangle0 = (Rectangle)Pools.obtain(Rectangle.class);
            stage0.calculateScissors(Rectangle.tmp, rectangle0);
            if(ScissorStack.pushScissors(rectangle0)) {
                return true;
            }
            Pools.free(rectangle0);
            return false;
        }
        return false;
    }

    public void clipEnd() {
        Pools.free(ScissorStack.popScissors());
    }

    public Actor debug() {
        this.setDebug(true);
        return this;
    }

    public void draw(Batch batch0, float f) {
    }

    public void drawDebug(ShapeRenderer shapeRenderer0) {
        this.drawDebugBounds(shapeRenderer0);
    }

    protected void drawDebugBounds(ShapeRenderer shapeRenderer0) {
        if(!this.debug) {
            return;
        }
        shapeRenderer0.set(ShapeType.Line);
        Stage stage0 = this.stage;
        if(stage0 != null) {
            shapeRenderer0.setColor(stage0.getDebugColor());
        }
        shapeRenderer0.rect(this.x, this.y, this.originX, this.originY, this.width, this.height, this.scaleX, this.scaleY, this.rotation);
    }

    public boolean fire(Event event0) {
        if(event0.getStage() == null) {
            event0.setStage(this.getStage());
        }
        event0.setTarget(this);
        Array array0 = (Array)Pools.obtain(Array.class);
        for(Group group0 = this.parent; group0 != null; group0 = group0.parent) {
            array0.add(group0);
        }
        try {
            Object[] arr_object = array0.items;
            for(int v1 = array0.size - 1; v1 >= 0; --v1) {
                ((Group)arr_object[v1]).notify(event0, true);
                if(event0.isStopped()) {
                    return event0.isCancelled();
                }
            }
            this.notify(event0, true);
            if(event0.isStopped()) {
                return event0.isCancelled();
            }
            this.notify(event0, false);
            if(!event0.getBubbles()) {
                return event0.isCancelled();
            }
            if(event0.isStopped()) {
                return event0.isCancelled();
            }
            int v2 = array0.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                ((Group)arr_object[v3]).notify(event0, false);
                if(event0.isStopped()) {
                    return event0.isCancelled();
                }
            }
        }
        finally {
            array0.clear();
            Pools.free(array0);
        }
        return event0.isCancelled();
    }

    @Null
    public Actor firstAscendant(Class class0) {
        if(class0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor0 = this;
        do {
            if(ClassReflection.isInstance(class0, actor0)) {
                return actor0;
            }
            actor0 = actor0.parent;
        }
        while(actor0 != null);
        return null;
    }

    public Array getActions() {
        return this.actions;
    }

    public DelayedRemovalArray getCaptureListeners() {
        return this.captureListeners;
    }

    public Color getColor() {
        return this.color;
    }

    public boolean getDebug() {
        return this.debug;
    }

    public float getHeight() {
        return this.height;
    }

    public DelayedRemovalArray getListeners() {
        return this.listeners;
    }

    @Null
    public String getName() {
        return this.name;
    }

    public float getOriginX() {
        return this.originX;
    }

    public float getOriginY() {
        return this.originY;
    }

    @Null
    public Group getParent() {
        return this.parent;
    }

    public float getRight() {
        return this.x + this.width;
    }

    public float getRotation() {
        return this.rotation;
    }

    public float getScaleX() {
        return this.scaleX;
    }

    public float getScaleY() {
        return this.scaleY;
    }

    @Null
    public Stage getStage() {
        return this.stage;
    }

    public float getTop() {
        return this.y + this.height;
    }

    public Touchable getTouchable() {
        return this.touchable;
    }

    @Null
    public Object getUserObject() {
        return this.userObject;
    }

    public float getWidth() {
        return this.width;
    }

    public float getX() {
        return this.x;
    }

    public float getX(int v) {
        float f = this.x;
        if((v & 16) != 0) {
            return f + this.width;
        }
        return (v & 8) == 0 ? f + this.width / 2.0f : f;
    }

    public float getY() {
        return this.y;
    }

    public float getY(int v) {
        float f = this.y;
        if((v & 2) != 0) {
            return f + this.height;
        }
        return (v & 4) == 0 ? f + this.height / 2.0f : f;
    }

    public int getZIndex() {
        return this.parent == null ? -1 : this.parent.children.indexOf(this, true);
    }

    public boolean hasActions() {
        return this.actions.size > 0;
    }

    public boolean hasKeyboardFocus() {
        Stage stage0 = this.getStage();
        return stage0 != null && stage0.getKeyboardFocus() == this;
    }

    public boolean hasParent() {
        return this.parent != null;
    }

    public boolean hasScrollFocus() {
        Stage stage0 = this.getStage();
        return stage0 != null && stage0.getScrollFocus() == this;
    }

    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(z && this.touchable != Touchable.enabled) {
            return null;
        }
        if(!this.isVisible()) {
            return null;
        }
        return f < 0.0f || f >= this.width || f1 < 0.0f || f1 >= this.height ? null : this;
    }

    public boolean isAscendantOf(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        do {
            if(actor0 == this) {
                return true;
            }
            actor0 = actor0.parent;
        }
        while(actor0 != null);
        return false;
    }

    public boolean isDescendantOf(Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        Actor actor1 = this;
        do {
            if(actor1 == actor0) {
                return true;
            }
            actor1 = actor1.parent;
        }
        while(actor1 != null);
        return false;
    }

    public boolean isTouchFocusListener() {
        Stage stage0 = this.getStage();
        if(stage0 == null) {
            return false;
        }
        int v = stage0.touchFocuses.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((TouchFocus)stage0.touchFocuses.get(v1)).listenerActor == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchFocusTarget() {
        Stage stage0 = this.getStage();
        if(stage0 == null) {
            return false;
        }
        int v = stage0.touchFocuses.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((TouchFocus)stage0.touchFocuses.get(v1)).target == this) {
                return true;
            }
        }
        return false;
    }

    public boolean isTouchable() {
        return this.touchable == Touchable.enabled;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public Vector2 localToActorCoordinates(Actor actor0, Vector2 vector20) {
        this.localToStageCoordinates(vector20);
        return actor0.stageToLocalCoordinates(vector20);
    }

    public Vector2 localToAscendantCoordinates(@Null Actor actor0, Vector2 vector20) {
        do {
            this.localToParentCoordinates(vector20);
            this = this.parent;
        }
        while(this != actor0 && this != null);
        return vector20;
    }

    public Vector2 localToParentCoordinates(Vector2 vector20) {
        float f = -this.rotation;
        float f1 = this.scaleX;
        float f2 = this.scaleY;
        float f3 = this.x;
        float f4 = this.y;
        if(f == 0.0f) {
            if(f1 == 1.0f && f2 == 1.0f) {
                vector20.x += f3;
                vector20.y += f4;
                return vector20;
            }
            vector20.x = (vector20.x - this.originX) * f1 + this.originX + f3;
            vector20.y = (vector20.y - this.originY) * f2 + this.originY + f4;
            return vector20;
        }
        float f5 = (float)Math.cos(f * 0.017453f);
        float f6 = (float)Math.sin(f * 0.017453f);
        float f7 = (vector20.x - this.originX) * f1;
        float f8 = (vector20.y - this.originY) * f2;
        vector20.x = f7 * f5 + f8 * f6 + this.originX + f3;
        vector20.y = f7 * -f6 + f8 * f5 + this.originY + f4;
        return vector20;
    }

    public Vector2 localToScreenCoordinates(Vector2 vector20) {
        return this.stage == null ? vector20 : this.stage.stageToScreenCoordinates(this.localToAscendantCoordinates(null, vector20));
    }

    public Vector2 localToStageCoordinates(Vector2 vector20) {
        return this.localToAscendantCoordinates(null, vector20);
    }

    public void moveBy(float f, float f1) {
        if(f != 0.0f || f1 != 0.0f) {
            this.x += f;
            this.y += f1;
        }
    }

    public boolean notify(Event event0, boolean z) {
        if(event0.getTarget() != null) {
            DelayedRemovalArray delayedRemovalArray0 = z ? this.captureListeners : this.listeners;
            if(delayedRemovalArray0.size == 0) {
                return event0.isCancelled();
            }
            event0.setListenerActor(this);
            event0.setCapture(z);
            if(event0.getStage() == null) {
                event0.setStage(this.stage);
            }
            try {
                delayedRemovalArray0.begin();
                int v = delayedRemovalArray0.size;
                for(int v1 = 0; v1 < v; ++v1) {
                    if(((EventListener)delayedRemovalArray0.get(v1)).handle(event0)) {
                        event0.handle();
                    }
                }
                delayedRemovalArray0.end();
                return event0.isCancelled();
            }
            catch(RuntimeException runtimeException0) {
                throw new RuntimeException("Actor: Actor", runtimeException0);
            }
        }
        throw new IllegalArgumentException("The event target cannot be null.");
    }

    public Vector2 parentToLocalCoordinates(Vector2 vector20) {
        float f = this.rotation;
        float f1 = this.scaleX;
        float f2 = this.scaleY;
        float f3 = this.x;
        float f4 = this.y;
        if(f == 0.0f) {
            if(f1 == 1.0f && f2 == 1.0f) {
                vector20.x -= f3;
                vector20.y -= f4;
                return vector20;
            }
            float f5 = this.originY;
            vector20.x = (vector20.x - f3 - this.originX) / f1 + this.originX;
            vector20.y = (vector20.y - f4 - f5) / f2 + f5;
            return vector20;
        }
        float f6 = (float)Math.cos(f * 0.017453f);
        float f7 = (float)Math.sin(f * 0.017453f);
        float f8 = this.originY;
        float f9 = vector20.x - f3 - this.originX;
        float f10 = vector20.y - f4 - f8;
        vector20.x = (f9 * f6 + f10 * f7) / f1 + this.originX;
        vector20.y = (f9 * -f7 + f10 * f6) / f2 + f8;
        return vector20;
    }

    protected void positionChanged() {
    }

    public boolean remove() {
        return this.parent == null ? false : this.parent.removeActor(this, true);
    }

    public void removeAction(@Null Action action0) {
        if(action0 != null && this.actions.removeValue(action0, true)) {
            action0.setActor(null);
        }
    }

    public boolean removeCaptureListener(EventListener eventListener0) {
        if(eventListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.captureListeners.removeValue(eventListener0, true);
    }

    public boolean removeListener(EventListener eventListener0) {
        if(eventListener0 == null) {
            throw new IllegalArgumentException("listener cannot be null.");
        }
        return this.listeners.removeValue(eventListener0, true);
    }

    public void rotateBy(float f) {
        if(f != 0.0f) {
            this.rotation = (this.rotation + f) % 360.0f;
        }
    }

    protected void rotationChanged() {
    }

    public void scaleBy(float f) {
        if(f != 0.0f) {
            this.scaleX += f;
            this.scaleY += f;
            this.scaleChanged();
        }
    }

    public void scaleBy(float f, float f1) {
        if(f != 0.0f || f1 != 0.0f) {
            this.scaleX += f;
            this.scaleY += f1;
            this.scaleChanged();
        }
    }

    protected void scaleChanged() {
    }

    public Vector2 screenToLocalCoordinates(Vector2 vector20) {
        return this.stage == null ? vector20 : this.stageToLocalCoordinates(this.stage.screenToStageCoordinates(vector20));
    }

    public void setBounds(float f, float f1, float f2, float f3) {
        if(this.x != f || this.y != f1) {
            this.x = f;
            this.y = f1;
        }
        if(this.width != f2 || this.height != f3) {
            this.width = f2;
            this.height = f3;
            this.sizeChanged();
        }
    }

    public void setColor(float f, float f1, float f2, float f3) {
        this.color.set(f, f1, f2, f3);
    }

    public void setColor(Color color0) {
        this.color.set(color0);
    }

    public void setDebug(boolean z) {
        this.debug = z;
        if(z) {
            Stage.debug = true;
        }
    }

    public void setHeight(float f) {
        if(this.height != f) {
            this.height = f;
            this.sizeChanged();
        }
    }

    public void setName(@Null String s) {
        this.name = s;
    }

    public void setOrigin(float f, float f1) {
        this.originX = f;
        this.originY = f1;
    }

    public void setOrigin(int v) {
        if((v & 8) != 0) {
            this.originX = 0.0f;
        }
        else if((v & 16) == 0) {
            this.originX = this.width / 2.0f;
        }
        else {
            this.originX = this.width;
        }
        if((v & 4) != 0) {
            this.originY = 0.0f;
            return;
        }
        if((v & 2) != 0) {
            this.originY = this.height;
            return;
        }
        this.originY = this.height / 2.0f;
    }

    public void setOriginX(float f) {
        this.originX = f;
    }

    public void setOriginY(float f) {
        this.originY = f;
    }

    protected void setParent(@Null Group group0) {
        this.parent = group0;
    }

    public void setPosition(float f, float f1) {
        if(this.x != f || this.y != f1) {
            this.x = f;
            this.y = f1;
        }
    }

    public void setPosition(float f, float f1, int v) {
        if((v & 16) != 0) {
            f -= this.width;
        }
        else if((v & 8) == 0) {
            f -= this.width / 2.0f;
        }
        if((v & 2) != 0) {
            f1 -= this.height;
        }
        else if((v & 4) == 0) {
            f1 -= this.height / 2.0f;
        }
        if(this.x != f || this.y != f1) {
            this.x = f;
            this.y = f1;
        }
    }

    public void setRotation(float f) {
        if(this.rotation != f) {
            this.rotation = f;
        }
    }

    public void setScale(float f) {
        if(this.scaleX != f || this.scaleY != f) {
            this.scaleX = f;
            this.scaleY = f;
            this.scaleChanged();
        }
    }

    public void setScale(float f, float f1) {
        if(this.scaleX != f || this.scaleY != f1) {
            this.scaleX = f;
            this.scaleY = f1;
            this.scaleChanged();
        }
    }

    public void setScaleX(float f) {
        if(this.scaleX != f) {
            this.scaleX = f;
            this.scaleChanged();
        }
    }

    public void setScaleY(float f) {
        if(this.scaleY != f) {
            this.scaleY = f;
            this.scaleChanged();
        }
    }

    public void setSize(float f, float f1) {
        if(this.width != f || this.height != f1) {
            this.width = f;
            this.height = f1;
            this.sizeChanged();
        }
    }

    protected void setStage(Stage stage0) {
        this.stage = stage0;
    }

    public void setTouchable(Touchable touchable0) {
        this.touchable = touchable0;
    }

    public void setUserObject(@Null Object object0) {
        this.userObject = object0;
    }

    public void setVisible(boolean z) {
        this.visible = z;
    }

    public void setWidth(float f) {
        if(this.width != f) {
            this.width = f;
            this.sizeChanged();
        }
    }

    public void setX(float f) {
        if(this.x != f) {
            this.x = f;
        }
    }

    public void setX(float f, int v) {
        if((v & 16) != 0) {
            f -= this.width;
        }
        else if((v & 8) == 0) {
            f -= this.width / 2.0f;
        }
        if(this.x != f) {
            this.x = f;
        }
    }

    public void setY(float f) {
        if(this.y != f) {
            this.y = f;
        }
    }

    public void setY(float f, int v) {
        if((v & 2) != 0) {
            f -= this.height;
        }
        else if((v & 4) == 0) {
            f -= this.height / 2.0f;
        }
        if(this.y != f) {
            this.y = f;
        }
    }

    public boolean setZIndex(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("ZIndex cannot be < 0.");
        }
        Group group0 = this.parent;
        if(group0 == null) {
            return false;
        }
        SnapshotArray snapshotArray0 = group0.children;
        if(snapshotArray0.size == 1) {
            return false;
        }
        int v1 = Math.min(v, snapshotArray0.size - 1);
        if(snapshotArray0.get(v1) == this) {
            return false;
        }
        if(!snapshotArray0.removeValue(this, true)) {
            return false;
        }
        snapshotArray0.insert(v1, this);
        return true;
    }

    public void sizeBy(float f) {
        if(f != 0.0f) {
            this.width += f;
            this.height += f;
            this.sizeChanged();
        }
    }

    public void sizeBy(float f, float f1) {
        if(f != 0.0f || f1 != 0.0f) {
            this.width += f;
            this.height += f1;
            this.sizeChanged();
        }
    }

    protected void sizeChanged() {
    }

    public Vector2 stageToLocalCoordinates(Vector2 vector20) {
        Group group0 = this.parent;
        if(group0 != null) {
            group0.stageToLocalCoordinates(vector20);
        }
        this.parentToLocalCoordinates(vector20);
        return vector20;
    }

    public void toBack() {
        this.setZIndex(0);
    }

    public void toFront() {
        this.setZIndex(0x7FFFFFFF);
    }

    @Override
    public String toString() [...] // 潜在的解密器
}

