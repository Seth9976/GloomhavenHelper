package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Table.Debug;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Stage extends InputAdapter implements Disposable {
    public static final class TouchFocus implements Poolable {
        int button;
        EventListener listener;
        Actor listenerActor;
        int pointer;
        Actor target;

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
        public void reset() {
            this.listenerActor = null;
            this.listener = null;
            this.target = null;
        }
    }

    private boolean actionsRequestRendering;
    private final Batch batch;
    static boolean debug;
    private boolean debugAll;
    private final Color debugColor;
    private boolean debugInvisible;
    private boolean debugParentUnderMouse;
    private ShapeRenderer debugShapes;
    private Debug debugTableUnderMouse;
    private boolean debugUnderMouse;
    @Null
    private Actor keyboardFocus;
    @Null
    private Actor mouseOverActor;
    private int mouseScreenX;
    private int mouseScreenY;
    private boolean ownsBatch;
    private final Actor[] pointerOverActors;
    private final int[] pointerScreenX;
    private final int[] pointerScreenY;
    private final boolean[] pointerTouched;
    private Group root;
    @Null
    private Actor scrollFocus;
    private final Vector2 tempCoords;
    final SnapshotArray touchFocuses;
    private Viewport viewport;

    public Stage() {
        float f = (float)Gdx.graphics.getWidth();
        float f1 = (float)Gdx.graphics.getHeight();
        OrthographicCamera orthographicCamera0 = new OrthographicCamera();
        this(new ScalingViewport(Scaling.stretch, f, f1, orthographicCamera0), new SpriteBatch());
        this.ownsBatch = true;
    }

    public Stage(Viewport viewport0) {
        this(viewport0, new SpriteBatch());
        this.ownsBatch = true;
    }

    public Stage(Viewport viewport0, Batch batch0) {
        this.tempCoords = new Vector2();
        this.pointerOverActors = new Actor[20];
        this.pointerTouched = new boolean[20];
        this.pointerScreenX = new int[20];
        this.pointerScreenY = new int[20];
        this.touchFocuses = new SnapshotArray(true, 4, TouchFocus.class);
        this.actionsRequestRendering = true;
        this.debugTableUnderMouse = Debug.none;
        this.debugColor = new Color(0.0f, 1.0f, 0.0f, 0.85f);
        if(viewport0 == null) {
            throw new IllegalArgumentException("viewport cannot be null.");
        }
        if(batch0 == null) {
            throw new IllegalArgumentException("batch cannot be null.");
        }
        this.viewport = viewport0;
        this.batch = batch0;
        this.root = new Group();
        this.root.setStage(this);
        viewport0.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    public void act() {
        this.act(Math.min(Gdx.graphics.getDeltaTime(), 0.033333f));
    }

    public void act(float f) {
        for(int v = 0; v < this.pointerOverActors.length; ++v) {
            Actor[] arr_actor = this.pointerOverActors;
            Actor actor0 = arr_actor[v];
            if(this.pointerTouched[v]) {
                arr_actor[v] = this.fireEnterAndExit(actor0, this.pointerScreenX[v], this.pointerScreenY[v], v);
            }
            else if(actor0 != null) {
                arr_actor[v] = null;
                this.screenToStageCoordinates(this.tempCoords.set(((float)this.pointerScreenX[v]), ((float)this.pointerScreenY[v])));
                InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
                inputEvent0.setType(Type.exit);
                inputEvent0.setStage(this);
                inputEvent0.setStageX(this.tempCoords.x);
                inputEvent0.setStageY(this.tempCoords.y);
                inputEvent0.setRelatedActor(actor0);
                inputEvent0.setPointer(v);
                actor0.fire(inputEvent0);
                Pools.free(inputEvent0);
            }
        }
        ApplicationType application$ApplicationType0 = Gdx.app.getType();
        if(application$ApplicationType0 == ApplicationType.Desktop || application$ApplicationType0 == ApplicationType.Applet || application$ApplicationType0 == ApplicationType.WebGL) {
            this.mouseOverActor = this.fireEnterAndExit(this.mouseOverActor, this.mouseScreenX, this.mouseScreenY, -1);
        }
        this.root.act(f);
    }

    public void addAction(Action action0) {
        this.root.addAction(action0);
    }

    public void addActor(Actor actor0) {
        this.root.addActor(actor0);
    }

    public boolean addCaptureListener(EventListener eventListener0) {
        return this.root.addCaptureListener(eventListener0);
    }

    public boolean addListener(EventListener eventListener0) {
        return this.root.addListener(eventListener0);
    }

    public void addTouchFocus(EventListener eventListener0, Actor actor0, Actor actor1, int v, int v1) {
        TouchFocus stage$TouchFocus0 = (TouchFocus)Pools.obtain(TouchFocus.class);
        stage$TouchFocus0.listenerActor = actor0;
        stage$TouchFocus0.target = actor1;
        stage$TouchFocus0.listener = eventListener0;
        stage$TouchFocus0.pointer = v;
        stage$TouchFocus0.button = v1;
        this.touchFocuses.add(stage$TouchFocus0);
    }

    public void calculateScissors(Rectangle rectangle0, Rectangle rectangle1) {
        Matrix4 matrix40 = this.debugShapes == null || !this.debugShapes.isDrawing() ? this.batch.getTransformMatrix() : this.debugShapes.getTransformMatrix();
        this.viewport.calculateScissors(matrix40, rectangle0, rectangle1);
    }

    public void cancelTouchFocus() {
        this.cancelTouchFocusExcept(null, null);
    }

    public void cancelTouchFocus(Actor actor0) {
        SnapshotArray snapshotArray0 = this.touchFocuses;
        TouchFocus[] arr_stage$TouchFocus = (TouchFocus[])snapshotArray0.begin();
        int v = snapshotArray0.size;
        InputEvent inputEvent0 = null;
        for(int v1 = 0; v1 < v; ++v1) {
            TouchFocus stage$TouchFocus0 = arr_stage$TouchFocus[v1];
            if(stage$TouchFocus0.listenerActor == actor0 && snapshotArray0.removeValue(stage$TouchFocus0, true)) {
                if(inputEvent0 == null) {
                    inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
                    inputEvent0.setStage(this);
                    inputEvent0.setType(Type.touchUp);
                    inputEvent0.setStageX(-2147483648.0f);
                    inputEvent0.setStageY(-2147483648.0f);
                }
                inputEvent0.setTarget(stage$TouchFocus0.target);
                inputEvent0.setListenerActor(stage$TouchFocus0.listenerActor);
                inputEvent0.setPointer(stage$TouchFocus0.pointer);
                inputEvent0.setButton(stage$TouchFocus0.button);
                stage$TouchFocus0.listener.handle(inputEvent0);
            }
        }
        snapshotArray0.end();
        if(inputEvent0 != null) {
            Pools.free(inputEvent0);
        }
    }

    public void cancelTouchFocusExcept(@Null EventListener eventListener0, @Null Actor actor0) {
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.touchUp);
        inputEvent0.setStageX(-2147483648.0f);
        inputEvent0.setStageY(-2147483648.0f);
        SnapshotArray snapshotArray0 = this.touchFocuses;
        TouchFocus[] arr_stage$TouchFocus = (TouchFocus[])snapshotArray0.begin();
        int v = snapshotArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            TouchFocus stage$TouchFocus0 = arr_stage$TouchFocus[v1];
            if((stage$TouchFocus0.listener != eventListener0 || stage$TouchFocus0.listenerActor != actor0) && snapshotArray0.removeValue(stage$TouchFocus0, true)) {
                inputEvent0.setTarget(stage$TouchFocus0.target);
                inputEvent0.setListenerActor(stage$TouchFocus0.listenerActor);
                inputEvent0.setPointer(stage$TouchFocus0.pointer);
                inputEvent0.setButton(stage$TouchFocus0.button);
                stage$TouchFocus0.listener.handle(inputEvent0);
            }
        }
        snapshotArray0.end();
        Pools.free(inputEvent0);
    }

    public void clear() {
        this.unfocusAll();
        this.root.clear();
    }

    private void disableDebug(Actor actor0, Actor actor1) {
        if(actor0 == actor1) {
            return;
        }
        actor0.setDebug(false);
        if(actor0 instanceof Group) {
            SnapshotArray snapshotArray0 = ((Group)actor0).children;
            int v1 = snapshotArray0.size;
            for(int v = 0; v < v1; ++v) {
                this.disableDebug(((Actor)snapshotArray0.get(v)), actor1);
            }
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.clear();
        if(this.ownsBatch) {
            this.batch.dispose();
        }
        ShapeRenderer shapeRenderer0 = this.debugShapes;
        if(shapeRenderer0 != null) {
            shapeRenderer0.dispose();
        }
    }

    public void draw() {
        Camera camera0 = this.viewport.getCamera();
        camera0.update();
        if(!this.root.isVisible()) {
            return;
        }
        this.batch.setProjectionMatrix(camera0.combined);
        this.batch.begin();
        this.root.draw(this.batch, 1.0f);
        this.batch.end();
        if(Stage.debug) {
            this.drawDebug();
        }
    }

    private void drawDebug() {
        if(this.debugShapes == null) {
            this.debugShapes = new ShapeRenderer();
            this.debugShapes.setAutoShapeType(true);
        }
        if(this.debugUnderMouse || this.debugParentUnderMouse || this.debugTableUnderMouse != Debug.none) {
            float f = (float)Gdx.input.getX();
            float f1 = (float)Gdx.input.getY();
            this.screenToStageCoordinates(this.tempCoords.set(f, f1));
            Actor actor0 = this.hit(this.tempCoords.x, this.tempCoords.y, true);
            if(actor0 == null) {
                return;
            }
            if(this.debugParentUnderMouse && actor0.parent != null) {
                actor0 = actor0.parent;
            }
            if(this.debugTableUnderMouse == Debug.none) {
                actor0.setDebug(true);
            }
            else {
                while(actor0 != null && !(actor0 instanceof Table)) {
                    actor0 = actor0.parent;
                }
                if(actor0 == null) {
                    return;
                }
                ((Table)actor0).debug(this.debugTableUnderMouse);
            }
            if(this.debugAll && actor0 instanceof Group) {
                ((Group)actor0).debugAll();
            }
            this.disableDebug(this.root, actor0);
        }
        else if(this.debugAll) {
            this.root.debugAll();
        }
        Gdx.gl.glEnable(3042);
        this.debugShapes.setProjectionMatrix(this.viewport.getCamera().combined);
        this.debugShapes.begin();
        this.root.drawDebug(this.debugShapes);
        this.debugShapes.end();
        Gdx.gl.glDisable(3042);
    }

    @Null
    private Actor fireEnterAndExit(@Null Actor actor0, int v, int v1, int v2) {
        this.screenToStageCoordinates(this.tempCoords.set(((float)v), ((float)v1)));
        Actor actor1 = this.hit(this.tempCoords.x, this.tempCoords.y, true);
        if(actor1 == actor0) {
            return actor0;
        }
        if(actor0 != null) {
            InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
            inputEvent0.setStage(this);
            inputEvent0.setStageX(this.tempCoords.x);
            inputEvent0.setStageY(this.tempCoords.y);
            inputEvent0.setPointer(v2);
            inputEvent0.setType(Type.exit);
            inputEvent0.setRelatedActor(actor1);
            actor0.fire(inputEvent0);
            Pools.free(inputEvent0);
        }
        if(actor1 != null) {
            InputEvent inputEvent1 = (InputEvent)Pools.obtain(InputEvent.class);
            inputEvent1.setStage(this);
            inputEvent1.setStageX(this.tempCoords.x);
            inputEvent1.setStageY(this.tempCoords.y);
            inputEvent1.setPointer(v2);
            inputEvent1.setType(Type.enter);
            inputEvent1.setRelatedActor(actor0);
            actor1.fire(inputEvent1);
            Pools.free(inputEvent1);
        }
        return actor1;
    }

    public boolean getActionsRequestRendering() {
        return this.actionsRequestRendering;
    }

    public Array getActors() {
        return this.root.children;
    }

    public Batch getBatch() {
        return this.batch;
    }

    public Camera getCamera() {
        return this.viewport.getCamera();
    }

    public Color getDebugColor() {
        return this.debugColor;
    }

    public float getHeight() {
        return this.viewport.getWorldHeight();
    }

    @Null
    public Actor getKeyboardFocus() {
        return this.keyboardFocus;
    }

    public Group getRoot() {
        return this.root;
    }

    @Null
    public Actor getScrollFocus() {
        return this.scrollFocus;
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    public float getWidth() {
        return this.viewport.getWorldWidth();
    }

    @Null
    public Actor hit(float f, float f1, boolean z) {
        this.root.parentToLocalCoordinates(this.tempCoords.set(f, f1));
        return this.root.hit(this.tempCoords.x, this.tempCoords.y, z);
    }

    public boolean isDebugAll() {
        return this.debugAll;
    }

    protected boolean isInsideViewport(int v, int v1) {
        int v2 = this.viewport.getScreenX();
        int v3 = this.viewport.getScreenWidth();
        int v4 = this.viewport.getScreenY();
        int v5 = this.viewport.getScreenHeight();
        int v6 = Gdx.graphics.getHeight() - 1 - v1;
        return v >= v2 && v < v3 + v2 && v6 >= v4 && v6 < v5 + v4;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyDown(int v) {
        Actor actor0 = this.keyboardFocus;
        if(actor0 == null) {
            actor0 = this.root;
        }
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.keyDown);
        inputEvent0.setKeyCode(v);
        actor0.fire(inputEvent0);
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyTyped(char c) {
        Actor actor0 = this.keyboardFocus;
        if(actor0 == null) {
            actor0 = this.root;
        }
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.keyTyped);
        inputEvent0.setCharacter(c);
        actor0.fire(inputEvent0);
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyUp(int v) {
        Actor actor0 = this.keyboardFocus;
        if(actor0 == null) {
            actor0 = this.root;
        }
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.keyUp);
        inputEvent0.setKeyCode(v);
        actor0.fire(inputEvent0);
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean mouseMoved(int v, int v1) {
        this.mouseScreenX = v;
        this.mouseScreenY = v1;
        if(!this.isInsideViewport(v, v1)) {
            return false;
        }
        this.screenToStageCoordinates(this.tempCoords.set(((float)v), ((float)v1)));
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.mouseMoved);
        inputEvent0.setStageX(this.tempCoords.x);
        inputEvent0.setStageY(this.tempCoords.y);
        Actor actor0 = this.hit(this.tempCoords.x, this.tempCoords.y, true);
        if(actor0 == null) {
            actor0 = this.root;
        }
        actor0.fire(inputEvent0);
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    public boolean removeCaptureListener(EventListener eventListener0) {
        return this.root.removeCaptureListener(eventListener0);
    }

    public boolean removeListener(EventListener eventListener0) {
        return this.root.removeListener(eventListener0);
    }

    public void removeTouchFocus(EventListener eventListener0, Actor actor0, Actor actor1, int v, int v1) {
        SnapshotArray snapshotArray0 = this.touchFocuses;
        for(int v2 = snapshotArray0.size - 1; v2 >= 0; --v2) {
            TouchFocus stage$TouchFocus0 = (TouchFocus)snapshotArray0.get(v2);
            if(stage$TouchFocus0.listener == eventListener0 && stage$TouchFocus0.listenerActor == actor0 && stage$TouchFocus0.target == actor1 && stage$TouchFocus0.pointer == v && stage$TouchFocus0.button == v1) {
                snapshotArray0.removeIndex(v2);
                Pools.free(stage$TouchFocus0);
            }
        }
    }

    public Vector2 screenToStageCoordinates(Vector2 vector20) {
        this.viewport.unproject(vector20);
        return vector20;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean scrolled(float f, float f1) {
        Actor actor0 = this.scrollFocus;
        if(actor0 == null) {
            actor0 = this.root;
        }
        this.screenToStageCoordinates(this.tempCoords.set(((float)this.mouseScreenX), ((float)this.mouseScreenY)));
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setStage(this);
        inputEvent0.setType(Type.scrolled);
        inputEvent0.setScrollAmountX(f);
        inputEvent0.setScrollAmountY(f1);
        inputEvent0.setStageX(this.tempCoords.x);
        inputEvent0.setStageY(this.tempCoords.y);
        actor0.fire(inputEvent0);
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    public void setActionsRequestRendering(boolean z) {
        this.actionsRequestRendering = z;
    }

    public void setDebugAll(boolean z) {
        if(this.debugAll == z) {
            return;
        }
        this.debugAll = z;
        if(z) {
            Stage.debug = true;
            return;
        }
        this.root.setDebug(false, true);
    }

    public void setDebugInvisible(boolean z) {
        this.debugInvisible = z;
    }

    public void setDebugParentUnderMouse(boolean z) {
        if(this.debugParentUnderMouse == z) {
            return;
        }
        this.debugParentUnderMouse = z;
        if(z) {
            Stage.debug = true;
            return;
        }
        this.root.setDebug(false, true);
    }

    public void setDebugTableUnderMouse(@Null Debug table$Debug0) {
        if(table$Debug0 == null) {
            table$Debug0 = Debug.none;
        }
        if(this.debugTableUnderMouse == table$Debug0) {
            return;
        }
        this.debugTableUnderMouse = table$Debug0;
        if(table$Debug0 != Debug.none) {
            Stage.debug = true;
            return;
        }
        this.root.setDebug(false, true);
    }

    public void setDebugTableUnderMouse(boolean z) {
        this.setDebugTableUnderMouse((z ? Debug.all : Debug.none));
    }

    public void setDebugUnderMouse(boolean z) {
        if(this.debugUnderMouse == z) {
            return;
        }
        this.debugUnderMouse = z;
        if(z) {
            Stage.debug = true;
            return;
        }
        this.root.setDebug(false, true);
    }

    public boolean setKeyboardFocus(@Null Actor actor0) {
        if(this.keyboardFocus == actor0) {
            return true;
        }
        FocusEvent focusListener$FocusEvent0 = (FocusEvent)Pools.obtain(FocusEvent.class);
        focusListener$FocusEvent0.setStage(this);
        focusListener$FocusEvent0.setType(com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent.Type.keyboard);
        Actor actor1 = this.keyboardFocus;
        if(actor1 != null) {
            focusListener$FocusEvent0.setFocused(false);
            focusListener$FocusEvent0.setRelatedActor(actor0);
            actor1.fire(focusListener$FocusEvent0);
        }
        boolean z = !focusListener$FocusEvent0.isCancelled();
        if(z) {
            this.keyboardFocus = actor0;
            if(actor0 != null) {
                focusListener$FocusEvent0.setFocused(true);
                focusListener$FocusEvent0.setRelatedActor(actor1);
                actor0.fire(focusListener$FocusEvent0);
                z = !focusListener$FocusEvent0.isCancelled();
                if(!z) {
                    this.keyboardFocus = actor1;
                }
            }
        }
        Pools.free(focusListener$FocusEvent0);
        return z;
    }

    public void setRoot(Group group0) {
        if(group0.parent != null) {
            group0.parent.removeActor(group0, false);
        }
        this.root = group0;
        group0.setParent(null);
        group0.setStage(this);
    }

    public boolean setScrollFocus(@Null Actor actor0) {
        if(this.scrollFocus == actor0) {
            return true;
        }
        FocusEvent focusListener$FocusEvent0 = (FocusEvent)Pools.obtain(FocusEvent.class);
        focusListener$FocusEvent0.setStage(this);
        focusListener$FocusEvent0.setType(com.badlogic.gdx.scenes.scene2d.utils.FocusListener.FocusEvent.Type.scroll);
        Actor actor1 = this.scrollFocus;
        if(actor1 != null) {
            focusListener$FocusEvent0.setFocused(false);
            focusListener$FocusEvent0.setRelatedActor(actor0);
            actor1.fire(focusListener$FocusEvent0);
        }
        boolean z = !focusListener$FocusEvent0.isCancelled();
        if(z) {
            this.scrollFocus = actor0;
            if(actor0 != null) {
                focusListener$FocusEvent0.setFocused(true);
                focusListener$FocusEvent0.setRelatedActor(actor1);
                actor0.fire(focusListener$FocusEvent0);
                z = !focusListener$FocusEvent0.isCancelled();
                if(!z) {
                    this.scrollFocus = actor1;
                }
            }
        }
        Pools.free(focusListener$FocusEvent0);
        return z;
    }

    public void setViewport(Viewport viewport0) {
        this.viewport = viewport0;
    }

    public Vector2 stageToScreenCoordinates(Vector2 vector20) {
        this.viewport.project(vector20);
        vector20.y = ((float)Gdx.graphics.getHeight()) - vector20.y;
        return vector20;
    }

    public Vector2 toScreenCoordinates(Vector2 vector20, Matrix4 matrix40) {
        return this.viewport.toScreenCoordinates(vector20, matrix40);
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchDown(int v, int v1, int v2, int v3) {
        if(!this.isInsideViewport(v, v1)) {
            return false;
        }
        this.pointerTouched[v2] = true;
        this.pointerScreenX[v2] = v;
        this.pointerScreenY[v2] = v1;
        this.screenToStageCoordinates(this.tempCoords.set(((float)v), ((float)v1)));
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setType(Type.touchDown);
        inputEvent0.setStage(this);
        inputEvent0.setStageX(this.tempCoords.x);
        inputEvent0.setStageY(this.tempCoords.y);
        inputEvent0.setPointer(v2);
        inputEvent0.setButton(v3);
        Actor actor0 = this.hit(this.tempCoords.x, this.tempCoords.y, true);
        if(actor0 != null) {
            actor0.fire(inputEvent0);
        }
        else if(this.root.getTouchable() == Touchable.enabled) {
            this.root.fire(inputEvent0);
        }
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchDragged(int v, int v1, int v2) {
        this.pointerScreenX[v2] = v;
        this.pointerScreenY[v2] = v1;
        this.mouseScreenX = v;
        this.mouseScreenY = v1;
        if(this.touchFocuses.size == 0) {
            return false;
        }
        this.screenToStageCoordinates(this.tempCoords.set(((float)v), ((float)v1)));
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setType(Type.touchDragged);
        inputEvent0.setStage(this);
        inputEvent0.setStageX(this.tempCoords.x);
        inputEvent0.setStageY(this.tempCoords.y);
        inputEvent0.setPointer(v2);
        SnapshotArray snapshotArray0 = this.touchFocuses;
        TouchFocus[] arr_stage$TouchFocus = (TouchFocus[])snapshotArray0.begin();
        int v4 = snapshotArray0.size;
        for(int v3 = 0; v3 < v4; ++v3) {
            TouchFocus stage$TouchFocus0 = arr_stage$TouchFocus[v3];
            if(stage$TouchFocus0.pointer == v2 && snapshotArray0.contains(stage$TouchFocus0, true)) {
                inputEvent0.setTarget(stage$TouchFocus0.target);
                inputEvent0.setListenerActor(stage$TouchFocus0.listenerActor);
                if(stage$TouchFocus0.listener.handle(inputEvent0)) {
                    inputEvent0.handle();
                }
            }
        }
        snapshotArray0.end();
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchUp(int v, int v1, int v2, int v3) {
        this.pointerTouched[v2] = false;
        this.pointerScreenX[v2] = v;
        this.pointerScreenY[v2] = v1;
        if(this.touchFocuses.size == 0) {
            return false;
        }
        this.screenToStageCoordinates(this.tempCoords.set(((float)v), ((float)v1)));
        InputEvent inputEvent0 = (InputEvent)Pools.obtain(InputEvent.class);
        inputEvent0.setType(Type.touchUp);
        inputEvent0.setStage(this);
        inputEvent0.setStageX(this.tempCoords.x);
        inputEvent0.setStageY(this.tempCoords.y);
        inputEvent0.setPointer(v2);
        inputEvent0.setButton(v3);
        SnapshotArray snapshotArray0 = this.touchFocuses;
        TouchFocus[] arr_stage$TouchFocus = (TouchFocus[])snapshotArray0.begin();
        int v5 = snapshotArray0.size;
        for(int v4 = 0; v4 < v5; ++v4) {
            TouchFocus stage$TouchFocus0 = arr_stage$TouchFocus[v4];
            if(stage$TouchFocus0.pointer == v2 && stage$TouchFocus0.button == v3 && snapshotArray0.removeValue(stage$TouchFocus0, true)) {
                inputEvent0.setTarget(stage$TouchFocus0.target);
                inputEvent0.setListenerActor(stage$TouchFocus0.listenerActor);
                if(stage$TouchFocus0.listener.handle(inputEvent0)) {
                    inputEvent0.handle();
                }
                Pools.free(stage$TouchFocus0);
            }
        }
        snapshotArray0.end();
        boolean z = inputEvent0.isHandled();
        Pools.free(inputEvent0);
        return z;
    }

    public void unfocus(Actor actor0) {
        this.cancelTouchFocus(actor0);
        if(this.scrollFocus != null && this.scrollFocus.isDescendantOf(actor0)) {
            this.setScrollFocus(null);
        }
        if(this.keyboardFocus != null && this.keyboardFocus.isDescendantOf(actor0)) {
            this.setKeyboardFocus(null);
        }
    }

    public void unfocusAll() {
        this.setScrollFocus(null);
        this.setKeyboardFocus(null);
        this.cancelTouchFocus();
    }
}

