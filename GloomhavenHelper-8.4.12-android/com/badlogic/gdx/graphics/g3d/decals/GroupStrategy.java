package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

public interface GroupStrategy {
    void afterGroup(int arg1);

    void afterGroups();

    void beforeGroup(int arg1, Array arg2);

    void beforeGroups();

    int decideGroup(Decal arg1);

    ShaderProgram getGroupShader(int arg1);
}

