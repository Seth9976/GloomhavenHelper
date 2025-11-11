package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class Shaders {
    public static ShaderProgram desat() {
        ShaderProgram shaderProgram0 = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n#define LOWP lowp\nprecision mediump float;\n#else\n#define LOWP \n#endif\nconst vec3 coef = vec3(0.3, 0.59, 0.11);\nvarying LOWP vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nuniform float u_desat;\nvoid main()\n{\n  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n  vec3 gray = vec3(dot(vec3(color), coef));\n  gl_FragColor = vec4(mix(color.rgb, gray, u_desat), color.a);\n}");
        if(!shaderProgram0.isCompiled()) {
            throw new IllegalArgumentException("Error compiling shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }
}

