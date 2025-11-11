package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.utils.Array;

public class NodeAnimation {
    public Node node;
    public Array rotation;
    public Array scaling;
    public Array translation;

    public NodeAnimation() {
        this.translation = null;
        this.rotation = null;
        this.scaling = null;
    }
}

