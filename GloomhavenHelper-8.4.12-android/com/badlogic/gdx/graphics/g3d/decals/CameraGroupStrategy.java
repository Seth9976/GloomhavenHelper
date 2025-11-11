package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;
import java.util.Comparator;

public class CameraGroupStrategy implements GroupStrategy, Disposable {
    private static final int GROUP_BLEND = 1;
    private static final int GROUP_OPAQUE;
    Pool arrayPool;
    Camera camera;
    private final Comparator cameraSorter;
    ObjectMap materialGroups;
    ShaderProgram shader;
    Array usedArrays;

    public CameraGroupStrategy(Camera camera0) {
        this(camera0, new Comparator() {
            public int compare(Decal decal0, Decal decal1) {
                float f = this.val$camera.position.dst(decal0.position);
                return (int)Math.signum(this.val$camera.position.dst(decal1.position) - f);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Decal)object0), ((Decal)object1));
            }
        });
    }

    public CameraGroupStrategy(Camera camera0, Comparator comparator0) {
        this.arrayPool = new Pool(16) {
            protected Array newObject() {
                return new Array();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.usedArrays = new Array();
        this.materialGroups = new ObjectMap();
        this.camera = camera0;
        this.cameraSorter = comparator0;
        this.createDefaultShader();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroup(int v) {
        if(v == 1) {
            Gdx.gl.glDisable(3042);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void afterGroups() {
        Gdx.gl.glDisable(0xB71);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroup(int v, Array array0) {
        if(v == 1) {
            Gdx.gl.glEnable(3042);
            array0.sort(this.cameraSorter);
            return;
        }
        int v2 = array0.size;
        for(int v1 = 0; v1 < v2; ++v1) {
            Decal decal0 = (Decal)array0.get(v1);
            Array array1 = (Array)this.materialGroups.get(decal0.material);
            if(array1 == null) {
                array1 = (Array)this.arrayPool.obtain();
                array1.clear();
                this.usedArrays.add(array1);
                this.materialGroups.put(decal0.material, array1);
            }
            array1.add(decal0);
        }
        array0.clear();
        for(Object object0: this.materialGroups.values()) {
            array0.addAll(((Array)object0));
        }
        this.materialGroups.clear();
        this.arrayPool.freeAll(this.usedArrays);
        this.usedArrays.clear();
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public void beforeGroups() {
        Gdx.gl.glEnable(0xB71);
        this.shader.bind();
        this.shader.setUniformMatrix("u_projectionViewMatrix", this.camera.combined);
        this.shader.setUniformi("u_texture", 0);
    }

    private void createDefaultShader() {
        this.shader = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projectionViewMatrix;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main()\n{\n   v_color = a_color;\n   v_color.a = v_color.a * (255.0/254.0);\n   v_texCoords = a_texCoord0;\n   gl_Position =  u_projectionViewMatrix * a_position;\n}\n", "#ifdef GL_ES\nprecision mediump float;\n#endif\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\nuniform sampler2D u_texture;\nvoid main()\n{\n  gl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n}");
        if(!this.shader.isCompiled()) {
            throw new IllegalArgumentException("couldn\'t compile shader: " + this.shader.getLog());
        }
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public int decideGroup(Decal decal0) {
        return !decal0.getMaterial().isOpaque();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        ShaderProgram shaderProgram0 = this.shader;
        if(shaderProgram0 != null) {
            shaderProgram0.dispose();
        }
    }

    public Camera getCamera() {
        return this.camera;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.decals.GroupStrategy
    public ShaderProgram getGroupShader(int v) {
        return this.shader;
    }

    public void setCamera(Camera camera0) {
        this.camera = camera0;
    }
}

