package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;

public class SimpleOrthoGroupStrategy implements GroupStrategy {
    class Comparator implements java.util.Comparator {
        public int compare(Decal decal0, Decal decal1) {
            if(decal0.getZ() == decal1.getZ()) {
                return 0;
            }
            return decal0.getZ() - decal1.getZ() < 0.0f ? -1 : 1;
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((Decal)object0), ((Decal)object1));
        }
    }

    private static final int GROUP_BLEND = 1;
    private static final int GROUP_OPAQUE;
    private Comparator comparator;

    public SimpleOrthoGroupStrategy() {
        this.comparator = new Comparator(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int v) {
        if(v == 1) {
            Gdx.gl.glDepthMask(true);
            Gdx.gl.glDisable(3042);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(0xDE1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int v, Array array0) {
        if(v == 1) {
            Sort.instance().sort(array0, this.comparator);
            Gdx.gl.glEnable(3042);
            Gdx.gl.glDepthMask(false);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(0xDE1);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal0) {
        return !decal0.getMaterial().isOpaque();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int v) {
        return null;
    }
}

