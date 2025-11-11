package com.badlogic.gdx.graphics.g3d.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

public class FirstPersonCameraController extends InputAdapter {
    private int BACKWARD;
    private int DOWN;
    private int FORWARD;
    private int STRAFE_LEFT;
    private int STRAFE_RIGHT;
    private int UP;
    private final Camera camera;
    private float degreesPerPixel;
    private final IntIntMap keys;
    private final Vector3 tmp;
    private float velocity;

    public FirstPersonCameraController(Camera camera0) {
        this.keys = new IntIntMap();
        this.STRAFE_LEFT = 29;
        this.STRAFE_RIGHT = 0x20;
        this.FORWARD = 51;
        this.BACKWARD = 0x2F;
        this.UP = 45;
        this.DOWN = 33;
        this.velocity = 5.0f;
        this.degreesPerPixel = 0.5f;
        this.tmp = new Vector3();
        this.camera = camera0;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyDown(int v) {
        this.keys.put(v, v);
        return true;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean keyUp(int v) {
        this.keys.remove(v, 0);
        return true;
    }

    public void setDegreesPerPixel(float f) {
        this.degreesPerPixel = f;
    }

    public void setVelocity(float f) {
        this.velocity = f;
    }

    @Override  // com.badlogic.gdx.InputAdapter
    public boolean touchDragged(int v, int v1, int v2) {
        float f = ((float)(-Gdx.input.getDeltaX())) * this.degreesPerPixel;
        float f1 = ((float)(-Gdx.input.getDeltaY())) * this.degreesPerPixel;
        this.camera.direction.rotate(this.camera.up, f);
        this.tmp.set(this.camera.direction).crs(this.camera.up).nor();
        this.camera.direction.rotate(this.tmp, f1);
        return true;
    }

    public void update() {
        this.update(Gdx.graphics.getDeltaTime());
    }

    public void update(float f) {
        if(this.keys.containsKey(this.FORWARD)) {
            this.tmp.set(this.camera.direction).nor().scl(this.velocity * f);
            this.camera.position.add(this.tmp);
        }
        if(this.keys.containsKey(this.BACKWARD)) {
            this.tmp.set(this.camera.direction).nor().scl(-f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if(this.keys.containsKey(this.STRAFE_LEFT)) {
            this.tmp.set(this.camera.direction).crs(this.camera.up).nor().scl(-f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        if(this.keys.containsKey(this.STRAFE_RIGHT)) {
            this.tmp.set(this.camera.direction).crs(this.camera.up).nor().scl(this.velocity * f);
            this.camera.position.add(this.tmp);
        }
        if(this.keys.containsKey(this.UP)) {
            this.tmp.set(this.camera.up).nor().scl(this.velocity * f);
            this.camera.position.add(this.tmp);
        }
        if(this.keys.containsKey(this.DOWN)) {
            this.tmp.set(this.camera.up).nor().scl(-f * this.velocity);
            this.camera.position.add(this.tmp);
        }
        this.camera.update(true);
    }
}

