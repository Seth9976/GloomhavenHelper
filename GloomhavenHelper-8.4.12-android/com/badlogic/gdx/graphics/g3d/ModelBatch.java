package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.utils.DefaultRenderableSorter;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.g3d.utils.RenderableSorter;
import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FlushablePool;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ModelBatch implements Disposable {
    public static class RenderablePool extends FlushablePool {
        protected Renderable newObject() {
            return new Renderable();
        }

        @Override  // com.badlogic.gdx.utils.Pool
        protected Object newObject() {
            return this.newObject();
        }

        public Renderable obtain() {
            Renderable renderable0 = (Renderable)super.obtain();
            renderable0.environment = null;
            renderable0.material = null;
            renderable0.meshPart.set("", null, 0, 0, 0);
            renderable0.shader = null;
            renderable0.userData = null;
            return renderable0;
        }

        @Override  // com.badlogic.gdx.utils.FlushablePool
        public Object obtain() {
            return this.obtain();
        }
    }

    protected Camera camera;
    protected final RenderContext context;
    private final boolean ownContext;
    protected final Array renderables;
    protected final RenderablePool renderablesPool;
    protected final ShaderProvider shaderProvider;
    protected final RenderableSorter sorter;

    public ModelBatch() {
        this(null, null, null);
    }

    public ModelBatch(FileHandle fileHandle0, FileHandle fileHandle1) {
        this(null, new DefaultShaderProvider(fileHandle0, fileHandle1), null);
    }

    public ModelBatch(RenderContext renderContext0) {
        this(renderContext0, null, null);
    }

    public ModelBatch(RenderContext renderContext0, RenderableSorter renderableSorter0) {
        this(renderContext0, null, renderableSorter0);
    }

    public ModelBatch(RenderContext renderContext0, ShaderProvider shaderProvider0) {
        this(renderContext0, shaderProvider0, null);
    }

    public ModelBatch(RenderContext renderContext0, ShaderProvider shaderProvider0, RenderableSorter renderableSorter0) {
        this.renderablesPool = new RenderablePool();
        this.renderables = new Array();
        if(renderableSorter0 == null) {
            renderableSorter0 = new DefaultRenderableSorter();
        }
        this.sorter = renderableSorter0;
        this.ownContext = renderContext0 == null;
        if(renderContext0 == null) {
            renderContext0 = new RenderContext(new DefaultTextureBinder(1, 1));
        }
        this.context = renderContext0;
        if(shaderProvider0 == null) {
            shaderProvider0 = new DefaultShaderProvider();
        }
        this.shaderProvider = shaderProvider0;
    }

    public ModelBatch(RenderableSorter renderableSorter0) {
        this(null, null, renderableSorter0);
    }

    public ModelBatch(ShaderProvider shaderProvider0) {
        this(null, shaderProvider0, null);
    }

    public ModelBatch(ShaderProvider shaderProvider0, RenderableSorter renderableSorter0) {
        this(null, shaderProvider0, renderableSorter0);
    }

    public ModelBatch(String s, String s1) {
        this(null, new DefaultShaderProvider(s, s1), null);
    }

    public void begin(Camera camera0) {
        if(this.camera != null) {
            throw new GdxRuntimeException("Call end() first.");
        }
        this.camera = camera0;
        if(this.ownContext) {
            this.context.begin();
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        this.shaderProvider.dispose();
    }

    public void end() {
        this.flush();
        if(this.ownContext) {
            this.context.end();
        }
        this.camera = null;
    }

    public void flush() {
        this.sorter.sort(this.camera, this.renderables);
        Shader shader0 = null;
        for(int v = 0; v < this.renderables.size; ++v) {
            Renderable renderable0 = (Renderable)this.renderables.get(v);
            if(shader0 != renderable0.shader) {
                if(shader0 != null) {
                    shader0.end();
                }
                shader0 = renderable0.shader;
                shader0.begin(this.camera, this.context);
            }
            shader0.render(renderable0);
        }
        if(shader0 != null) {
            shader0.end();
        }
        this.renderablesPool.flush();
        this.renderables.clear();
    }

    public Camera getCamera() {
        return this.camera;
    }

    public RenderContext getRenderContext() {
        return this.context;
    }

    public RenderableSorter getRenderableSorter() {
        return this.sorter;
    }

    public ShaderProvider getShaderProvider() {
        return this.shaderProvider;
    }

    public boolean ownsRenderContext() {
        return this.ownContext;
    }

    public void render(Renderable renderable0) {
        renderable0.shader = this.shaderProvider.getShader(renderable0);
        this.renderables.add(renderable0);
    }

    public void render(RenderableProvider renderableProvider0) {
        int v = this.renderables.size;
        renderableProvider0.getRenderables(this.renderables, this.renderablesPool);
        while(v < this.renderables.size) {
            Renderable renderable0 = (Renderable)this.renderables.get(v);
            renderable0.shader = this.shaderProvider.getShader(renderable0);
            ++v;
        }
    }

    public void render(RenderableProvider renderableProvider0, Environment environment0) {
        int v = this.renderables.size;
        renderableProvider0.getRenderables(this.renderables, this.renderablesPool);
        while(v < this.renderables.size) {
            Renderable renderable0 = (Renderable)this.renderables.get(v);
            renderable0.environment = environment0;
            renderable0.shader = this.shaderProvider.getShader(renderable0);
            ++v;
        }
    }

    public void render(RenderableProvider renderableProvider0, Environment environment0, Shader shader0) {
        int v = this.renderables.size;
        renderableProvider0.getRenderables(this.renderables, this.renderablesPool);
        while(v < this.renderables.size) {
            Renderable renderable0 = (Renderable)this.renderables.get(v);
            renderable0.environment = environment0;
            renderable0.shader = this.shaderProvider.getShader(renderable0);
            ++v;
        }
    }

    public void render(RenderableProvider renderableProvider0, Shader shader0) {
        int v = this.renderables.size;
        renderableProvider0.getRenderables(this.renderables, this.renderablesPool);
        while(v < this.renderables.size) {
            Renderable renderable0 = (Renderable)this.renderables.get(v);
            renderable0.shader = this.shaderProvider.getShader(renderable0);
            ++v;
        }
    }

    public void render(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.render(((RenderableProvider)object0));
        }
    }

    public void render(Iterable iterable0, Environment environment0) {
        for(Object object0: iterable0) {
            this.render(((RenderableProvider)object0), environment0);
        }
    }

    public void render(Iterable iterable0, Environment environment0, Shader shader0) {
        for(Object object0: iterable0) {
            this.render(((RenderableProvider)object0), environment0, shader0);
        }
    }

    public void render(Iterable iterable0, Shader shader0) {
        for(Object object0: iterable0) {
            this.render(((RenderableProvider)object0), shader0);
        }
    }

    public void setCamera(Camera camera0) {
        if(this.camera == null) {
            throw new GdxRuntimeException("Call begin() first.");
        }
        if(this.renderables.size > 0) {
            this.flush();
        }
        this.camera = camera0;
    }
}

