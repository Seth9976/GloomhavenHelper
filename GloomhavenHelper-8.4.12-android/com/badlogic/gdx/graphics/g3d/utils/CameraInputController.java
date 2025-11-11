package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.input.GestureDetector.GestureAdapter;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraInputController extends GestureDetector {
    public static class CameraGestureListener extends GestureAdapter {
        public CameraInputController controller;
        private float previousZoom;

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean fling(float f, float f1, int v) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean longPress(float f, float f1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean pan(float f, float f1, float f2, float f3) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean pinch(Vector2 vector20, Vector2 vector21, Vector2 vector22, Vector2 vector23) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean tap(float f, float f1, int v, int v1) {
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean touchDown(float f, float f1, int v, int v1) {
            this.previousZoom = 0.0f;
            return false;
        }

        @Override  // com.badlogic.gdx.input.GestureDetector$GestureAdapter
        public boolean zoom(float f, float f1) {
            float f2 = f1 - f;
            float f3 = f2 - this.previousZoom;
            this.previousZoom = f2;
            float f4 = (float)Gdx.graphics.getWidth();
            float f5 = (float)Gdx.graphics.getHeight();
            CameraInputController cameraInputController0 = this.controller;
            if(f4 > f5) {
                f4 = f5;
            }
            return cameraInputController0.pinchZoom(f3 / f4);
        }
    }

    public int activateKey;
    protected boolean activatePressed;
    public boolean alwaysScroll;
    public boolean autoUpdate;
    public int backwardKey;
    protected boolean backwardPressed;
    protected int button;
    public Camera camera;
    public int forwardButton;
    public int forwardKey;
    protected boolean forwardPressed;
    public boolean forwardTarget;
    protected final CameraGestureListener gestureListener;
    private boolean multiTouch;
    public float pinchZoomFactor;
    public float rotateAngle;
    public int rotateButton;
    public int rotateLeftKey;
    protected boolean rotateLeftPressed;
    public int rotateRightKey;
    protected boolean rotateRightPressed;
    public float scrollFactor;
    public boolean scrollTarget;
    private float startX;
    private float startY;
    public Vector3 target;
    private final Vector3 tmpV1;
    private final Vector3 tmpV2;
    private int touched;
    public int translateButton;
    public boolean translateTarget;
    public float translateUnits;

    public CameraInputController(Camera camera0) {
        this(new CameraGestureListener(), camera0);
    }

    protected CameraInputController(CameraGestureListener cameraInputController$CameraGestureListener0, Camera camera0) {
        super(cameraInputController$CameraGestureListener0);
        this.rotateButton = 0;
        this.rotateAngle = 360.0f;
        this.translateButton = 1;
        this.translateUnits = 10.0f;
        this.forwardButton = 2;
        this.activateKey = 0;
        this.alwaysScroll = true;
        this.scrollFactor = -0.1f;
        this.pinchZoomFactor = 10.0f;
        this.autoUpdate = true;
        this.target = new Vector3();
        this.translateTarget = true;
        this.forwardTarget = true;
        this.scrollTarget = false;
        this.forwardKey = 51;
        this.backwardKey = 0x2F;
        this.rotateRightKey = 29;
        this.rotateLeftKey = 0x20;
        this.button = -1;
        this.tmpV1 = new Vector3();
        this.tmpV2 = new Vector3();
        this.gestureListener = cameraInputController$CameraGestureListener0;
        this.gestureListener.controller = this;
        this.camera = camera0;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyDown(int v) {
        if(v == this.activateKey) {
            this.activatePressed = true;
        }
        if(v == this.forwardKey) {
            this.forwardPressed = true;
            return false;
        }
        if(v == this.backwardKey) {
            this.backwardPressed = true;
            return false;
        }
        if(v == this.rotateRightKey) {
            this.rotateRightPressed = true;
            return false;
        }
        if(v == this.rotateLeftKey) {
            this.rotateLeftPressed = true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyUp(int v) {
        if(v == this.activateKey) {
            this.activatePressed = false;
            this.button = -1;
        }
        if(v == this.forwardKey) {
            this.forwardPressed = false;
            return false;
        }
        if(v == this.backwardKey) {
            this.backwardPressed = false;
            return false;
        }
        if(v == this.rotateRightKey) {
            this.rotateRightPressed = false;
            return false;
        }
        if(v == this.rotateLeftKey) {
            this.rotateLeftPressed = false;
        }
        return false;
    }

    protected boolean pinchZoom(float f) {
        return this.zoom(this.pinchZoomFactor * f);
    }

    protected boolean process(float f, float f1, int v) {
        if(v == this.rotateButton) {
            this.tmpV1.set(this.camera.direction).crs(this.camera.up).y = 0.0f;
            this.camera.rotateAround(this.target, this.tmpV1.nor(), f1 * this.rotateAngle);
            this.camera.rotateAround(this.target, Vector3.Y, f * -this.rotateAngle);
        }
        else if(v == this.translateButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).crs(this.camera.up).nor().scl(-f * this.translateUnits));
            this.camera.translate(this.tmpV2.set(this.camera.up).scl(-f1 * this.translateUnits));
            if(this.translateTarget) {
                this.target.add(this.tmpV1).add(this.tmpV2);
            }
        }
        else if(v == this.forwardButton) {
            this.camera.translate(this.tmpV1.set(this.camera.direction).scl(f1 * this.translateUnits));
            if(this.forwardTarget) {
                this.target.add(this.tmpV1);
            }
        }
        if(this.autoUpdate) {
            this.camera.update();
        }
        return true;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean scrolled(float f, float f1) {
        return this.zoom(f1 * this.scrollFactor * this.translateUnits);
    }

    @Override  // com.badlogic.gdx.input.GestureDetector
    public boolean touchDown(int v, int v1, int v2, int v3) {
        this.touched |= 1 << v2;
        this.multiTouch = !MathUtils.isPowerOfTwo(this.touched);
        if(this.multiTouch) {
            this.button = -1;
            return super.touchDown(v, v1, v2, v3) || this.activateKey == 0 || this.activatePressed;
        }
        if(this.button < 0 && (this.activateKey == 0 || this.activatePressed)) {
            this.startX = (float)v;
            this.startY = (float)v1;
            this.button = v3;
        }
        return super.touchDown(v, v1, v2, v3) || this.activateKey == 0 || this.activatePressed;
    }

    @Override  // com.badlogic.gdx.input.GestureDetector
    public boolean touchDragged(int v, int v1, int v2) {
        boolean z = super.touchDragged(v, v1, v2);
        if(!z && this.button >= 0) {
            float f = (((float)v) - this.startX) / ((float)Gdx.graphics.getWidth());
            float f1 = (this.startY - ((float)v1)) / ((float)Gdx.graphics.getHeight());
            this.startX = (float)v;
            this.startY = (float)v1;
            return this.process(f, f1, this.button);
        }
        return z;
    }

    @Override  // com.badlogic.gdx.input.GestureDetector
    public boolean touchUp(int v, int v1, int v2, int v3) {
        this.touched &= ~(1 << v2);
        this.multiTouch = !MathUtils.isPowerOfTwo(this.touched);
        if(v3 == this.button) {
            this.button = -1;
        }
        return super.touchUp(v, v1, v2, v3) || this.activatePressed;
    }

    public void update() {
        if(this.rotateRightPressed || this.rotateLeftPressed || this.forwardPressed || this.backwardPressed) {
            float f = Gdx.graphics.getDeltaTime();
            if(this.rotateRightPressed) {
                this.camera.rotate(this.camera.up, -f * this.rotateAngle);
            }
            if(this.rotateLeftPressed) {
                this.camera.rotate(this.camera.up, this.rotateAngle * f);
            }
            if(this.forwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl(this.translateUnits * f));
                if(this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if(this.backwardPressed) {
                this.camera.translate(this.tmpV1.set(this.camera.direction).scl(-f * this.translateUnits));
                if(this.forwardTarget) {
                    this.target.add(this.tmpV1);
                }
            }
            if(this.autoUpdate) {
                this.camera.update();
            }
        }
    }

    public boolean zoom(float f) {
        if(!this.alwaysScroll && this.activateKey != 0 && !this.activatePressed) {
            return false;
        }
        this.camera.translate(this.tmpV1.set(this.camera.direction).scl(f));
        if(this.scrollTarget) {
            this.target.add(this.tmpV1);
        }
        if(this.autoUpdate) {
            this.camera.update();
        }
        return true;
    }
}

