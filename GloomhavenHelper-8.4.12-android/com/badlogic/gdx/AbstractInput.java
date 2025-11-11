package com.badlogic.gdx;

import com.badlogic.gdx.utils.IntSet;

public abstract class AbstractInput implements Input {
    protected final boolean[] justPressedKeys;
    protected boolean keyJustPressed;
    private final IntSet keysToCatch;
    protected int pressedKeyCount;
    protected final boolean[] pressedKeys;

    public AbstractInput() {
        this.keysToCatch = new IntSet();
        this.pressedKeys = new boolean[0x100];
        this.justPressedKeys = new boolean[0x100];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchBackKey() {
        return this.keysToCatch.contains(4);
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchKey(int v) {
        return this.keysToCatch.contains(v);
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isCatchMenuKey() {
        return this.keysToCatch.contains(82);
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isKeyJustPressed(int v) {
        if(v == -1) {
            return this.keyJustPressed;
        }
        return v < 0 || v > 0xFF ? false : this.justPressedKeys[v];
    }

    @Override  // com.badlogic.gdx.Input
    public boolean isKeyPressed(int v) {
        if(v == -1) {
            return this.pressedKeyCount > 0;
        }
        return v < 0 || v > 0xFF ? false : this.pressedKeys[v];
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchBackKey(boolean z) {
        this.setCatchKey(4, z);
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchKey(int v, boolean z) {
        if(!z) {
            this.keysToCatch.remove(v);
            return;
        }
        this.keysToCatch.add(v);
    }

    @Override  // com.badlogic.gdx.Input
    public void setCatchMenuKey(boolean z) {
        this.setCatchKey(82, z);
    }
}

