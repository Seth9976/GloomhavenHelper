package com.badlogic.gdx.graphics.g3d.environment;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g3d.utils.TextureDescriptor;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;

public class DirectionalShadowLight extends DirectionalLight implements ShadowMap, Disposable {
    protected Camera cam;
    protected FrameBuffer fbo;
    protected float halfDepth;
    protected float halfHeight;
    protected final TextureDescriptor textureDesc;
    protected final Vector3 tmpV;

    public DirectionalShadowLight(int v, int v1, float f, float f1, float f2, float f3) {
        this.tmpV = new Vector3();
        this.fbo = new FrameBuffer(Format.RGBA8888, v, v1, true);
        this.cam = new OrthographicCamera(f, f1);
        this.cam.near = f2;
        this.cam.far = f3;
        this.halfHeight = f1 * 0.5f;
        this.halfDepth = f2 + (f3 - f2) * 0.5f;
        this.textureDesc = new TextureDescriptor();
        this.textureDesc.magFilter = TextureFilter.Nearest;
        this.textureDesc.minFilter = TextureFilter.Nearest;
        this.textureDesc.vWrap = TextureWrap.ClampToEdge;
        this.textureDesc.uWrap = TextureWrap.ClampToEdge;
    }

    public void begin() {
        int v = this.fbo.getWidth();
        int v1 = this.fbo.getHeight();
        this.fbo.begin();
        Gdx.gl.glViewport(0, 0, v, v1);
        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        Gdx.gl.glClear(0x4100);
        Gdx.gl.glEnable(3089);
        Gdx.gl.glScissor(1, 1, v - 2, v1 - 2);
    }

    public void begin(Camera camera0) {
        this.update(camera0);
        this.begin();
    }

    public void begin(Vector3 vector30, Vector3 vector31) {
        this.update(vector30, vector31);
        this.begin();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        FrameBuffer frameBuffer0 = this.fbo;
        if(frameBuffer0 != null) {
            frameBuffer0.dispose();
        }
        this.fbo = null;
    }

    public void end() {
        Gdx.gl.glDisable(3089);
        this.fbo.end();
    }

    public Camera getCamera() {
        return this.cam;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public TextureDescriptor getDepthMap() {
        this.textureDesc.texture = this.fbo.getColorBufferTexture();
        return this.textureDesc;
    }

    public FrameBuffer getFrameBuffer() {
        return this.fbo;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.environment.ShadowMap
    public Matrix4 getProjViewTrans() {
        return this.cam.combined;
    }

    public void update(Camera camera0) {
        this.update(this.tmpV.set(camera0.direction).scl(this.halfHeight), camera0.direction);
    }

    public void update(Vector3 vector30, Vector3 vector31) {
        this.cam.position.set(this.direction).scl(-this.halfDepth).add(vector30);
        this.cam.direction.set(this.direction).nor();
        this.cam.normalizeUp();
        this.cam.update();
    }
}

