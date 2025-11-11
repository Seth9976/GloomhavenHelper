package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public abstract class PluggableGroupStrategy implements GroupStrategy {
    private IntMap plugs;

    public PluggableGroupStrategy() {
        this.plugs = new IntMap();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int v) {
        ((GroupPlug)this.plugs.get(v)).afterGroup();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int v, Array array0) {
        ((GroupPlug)this.plugs.get(v)).beforeGroup(array0);
    }

    public void plugIn(GroupPlug groupPlug0, int v) {
        this.plugs.put(v, groupPlug0);
    }

    public GroupPlug unPlug(int v) {
        return (GroupPlug)this.plugs.remove(v);
    }
}

