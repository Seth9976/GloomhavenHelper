package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.model.MeshPart;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderableSorter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FlushablePool;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Pool;
import java.util.Comparator;

public class ModelCache implements RenderableProvider, Disposable {
    public interface MeshPool extends Disposable {
        void flush();

        Mesh obtain(VertexAttributes arg1, int arg2, int arg3);
    }

    public static class SimpleMeshPool implements MeshPool {
        private Array freeMeshes;
        private Array usedMeshes;

        public SimpleMeshPool() {
            this.freeMeshes = new Array();
            this.usedMeshes = new Array();
        }

        @Override  // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            for(Object object0: this.usedMeshes) {
                ((Mesh)object0).dispose();
            }
            this.usedMeshes.clear();
            for(Object object1: this.freeMeshes) {
                ((Mesh)object1).dispose();
            }
            this.freeMeshes.clear();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.ModelCache$MeshPool
        public void flush() {
            this.freeMeshes.addAll(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.ModelCache$MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes0, int v, int v1) {
            int v2 = this.freeMeshes.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                Mesh mesh0 = (Mesh)this.freeMeshes.get(v3);
                if(mesh0.getVertexAttributes().equals(vertexAttributes0) && mesh0.getMaxVertices() >= v && mesh0.getMaxIndices() >= v1) {
                    this.freeMeshes.removeIndex(v3);
                    this.usedMeshes.add(mesh0);
                    return mesh0;
                }
            }
            Mesh mesh1 = new Mesh(false, 0x10000, Math.max(0x10000, 1 << 0x20 - Integer.numberOfLeadingZeros(v1 - 1)), vertexAttributes0);
            this.usedMeshes.add(mesh1);
            return mesh1;
        }
    }

    public static class Sorter implements RenderableSorter, Comparator {
        public int compare(Renderable renderable0, Renderable renderable1) {
            int v = renderable0.meshPart.mesh.getVertexAttributes().compareTo(renderable1.meshPart.mesh.getVertexAttributes());
            if(v == 0) {
                int v1 = renderable0.material.compareTo(renderable1.material);
                return v1 == 0 ? renderable0.meshPart.primitiveType - renderable1.meshPart.primitiveType : v1;
            }
            return v;
        }

        @Override
        public int compare(Object object0, Object object1) {
            return this.compare(((Renderable)object0), ((Renderable)object1));
        }

        @Override  // com.badlogic.gdx.graphics.g3d.utils.RenderableSorter
        public void sort(Camera camera0, Array array0) {
            array0.sort(this);
        }
    }

    public static class TightMeshPool implements MeshPool {
        private Array freeMeshes;
        private Array usedMeshes;

        public TightMeshPool() {
            this.freeMeshes = new Array();
            this.usedMeshes = new Array();
        }

        @Override  // com.badlogic.gdx.utils.Disposable
        public void dispose() {
            for(Object object0: this.usedMeshes) {
                ((Mesh)object0).dispose();
            }
            this.usedMeshes.clear();
            for(Object object1: this.freeMeshes) {
                ((Mesh)object1).dispose();
            }
            this.freeMeshes.clear();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.ModelCache$MeshPool
        public void flush() {
            this.freeMeshes.addAll(this.usedMeshes);
            this.usedMeshes.clear();
        }

        @Override  // com.badlogic.gdx.graphics.g3d.ModelCache$MeshPool
        public Mesh obtain(VertexAttributes vertexAttributes0, int v, int v1) {
            int v2 = this.freeMeshes.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                Mesh mesh0 = (Mesh)this.freeMeshes.get(v3);
                if(mesh0.getVertexAttributes().equals(vertexAttributes0) && mesh0.getMaxVertices() == v && mesh0.getMaxIndices() == v1) {
                    this.freeMeshes.removeIndex(v3);
                    this.usedMeshes.add(mesh0);
                    return mesh0;
                }
            }
            Mesh mesh1 = new Mesh(true, v, v1, vertexAttributes0);
            this.usedMeshes.add(mesh1);
            return mesh1;
        }
    }

    private boolean building;
    private Camera camera;
    private Array items;
    private MeshBuilder meshBuilder;
    private FlushablePool meshPartPool;
    private MeshPool meshPool;
    private Array renderables;
    private FlushablePool renderablesPool;
    private RenderableSorter sorter;
    private Array tmp;

    public ModelCache() {
        this(new Sorter(), new SimpleMeshPool());
    }

    public ModelCache(RenderableSorter renderableSorter0, MeshPool modelCache$MeshPool0) {
        this.renderables = new Array();
        this.renderablesPool = new FlushablePool() {
            protected Renderable newObject() {
                return new Renderable();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.meshPartPool = new FlushablePool() {
            protected MeshPart newObject() {
                return new MeshPart();
            }

            @Override  // com.badlogic.gdx.utils.Pool
            protected Object newObject() {
                return this.newObject();
            }
        };
        this.items = new Array();
        this.tmp = new Array();
        this.sorter = renderableSorter0;
        this.meshPool = modelCache$MeshPool0;
        this.meshBuilder = new MeshBuilder();
    }

    public void add(Renderable renderable0) {
        if(!this.building) {
            throw new GdxRuntimeException("Can only add items to the ModelCache in between .begin() and .end()");
        }
        if(renderable0.bones == null) {
            this.items.add(renderable0);
            return;
        }
        this.renderables.add(renderable0);
    }

    public void add(RenderableProvider renderableProvider0) {
        renderableProvider0.getRenderables(this.tmp, this.renderablesPool);
        int v = this.tmp.size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.add(((Renderable)this.tmp.get(v1)));
        }
        this.tmp.clear();
    }

    public void add(Iterable iterable0) {
        for(Object object0: iterable0) {
            this.add(((RenderableProvider)object0));
        }
    }

    public void begin() {
        this.begin(null);
    }

    public void begin(Camera camera0) {
        if(this.building) {
            throw new GdxRuntimeException("Call end() after calling begin()");
        }
        this.building = true;
        this.camera = camera0;
        this.renderablesPool.flush();
        this.renderables.clear();
        this.items.clear();
        this.meshPartPool.flush();
        this.meshPool.flush();
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if(this.building) {
            throw new GdxRuntimeException("Cannot dispose a ModelCache in between .begin() and .end()");
        }
        this.meshPool.dispose();
    }

    public void end() {
        if(!this.building) {
            throw new GdxRuntimeException("Call begin() prior to calling end()");
        }
        this.building = false;
        if(this.items.size == 0) {
            return;
        }
        this.sorter.sort(this.camera, this.items);
        Renderable renderable0 = (Renderable)this.items.get(0);
        VertexAttributes vertexAttributes0 = renderable0.meshPart.mesh.getVertexAttributes();
        Material material0 = renderable0.material;
        int v = renderable0.meshPart.primitiveType;
        int v1 = this.renderables.size;
        this.meshBuilder.begin(vertexAttributes0);
        MeshPart meshPart0 = this.meshBuilder.part("", v, ((MeshPart)this.meshPartPool.obtain()));
        this.renderables.add(this.obtainRenderable(material0, v));
        int v2 = this.items.size;
        Material material1 = material0;
        int v3 = v;
        for(int v4 = 0; v4 < v2; ++v4) {
            Renderable renderable1 = (Renderable)this.items.get(v4);
            VertexAttributes vertexAttributes1 = renderable1.meshPart.mesh.getVertexAttributes();
            Material material2 = renderable1.material;
            int v5 = renderable1.meshPart.primitiveType;
            boolean z = vertexAttributes1.equals(vertexAttributes0);
            int v6 = renderable1.meshPart.mesh.getNumIndices() <= 0 ? renderable1.meshPart.mesh.getNumVertices() : renderable1.meshPart.size;
            boolean z1 = z && this.meshBuilder.getNumVertices() + v6 <= 0x10000;
            if(!z1 || v5 != v3 || !material2.same(material1, true)) {
                if(!z1) {
                    Mesh mesh0 = this.meshBuilder.end(this.meshPool.obtain(vertexAttributes0, this.meshBuilder.getNumVertices(), this.meshBuilder.getNumIndices()));
                    while(v1 < this.renderables.size) {
                        ((Renderable)this.renderables.get(v1)).meshPart.mesh = mesh0;
                        ++v1;
                    }
                    this.meshBuilder.begin(vertexAttributes1);
                    vertexAttributes0 = vertexAttributes1;
                }
                MeshPart meshPart1 = this.meshBuilder.part("", v5, ((MeshPart)this.meshPartPool.obtain()));
                Renderable renderable2 = (Renderable)this.renderables.get(this.renderables.size - 1);
                renderable2.meshPart.offset = meshPart0.offset;
                renderable2.meshPart.size = meshPart0.size;
                this.renderables.add(this.obtainRenderable(material2, v5));
                meshPart0 = meshPart1;
                material1 = material2;
                v3 = v5;
            }
            this.meshBuilder.setVertexTransform(renderable1.worldTransform);
            this.meshBuilder.addMesh(renderable1.meshPart.mesh, renderable1.meshPart.offset, renderable1.meshPart.size);
        }
        Mesh mesh1 = this.meshBuilder.end(this.meshPool.obtain(vertexAttributes0, this.meshBuilder.getNumVertices(), this.meshBuilder.getNumIndices()));
        while(v1 < this.renderables.size) {
            ((Renderable)this.renderables.get(v1)).meshPart.mesh = mesh1;
            ++v1;
        }
        Renderable renderable3 = (Renderable)this.renderables.get(this.renderables.size - 1);
        renderable3.meshPart.offset = meshPart0.offset;
        renderable3.meshPart.size = meshPart0.size;
    }

    @Override  // com.badlogic.gdx.graphics.g3d.RenderableProvider
    public void getRenderables(Array array0, Pool pool0) {
        if(this.building) {
            throw new GdxRuntimeException("Cannot render a ModelCache in between .begin() and .end()");
        }
        for(Object object0: this.renderables) {
            ((Renderable)object0).shader = null;
            ((Renderable)object0).environment = null;
        }
        array0.addAll(this.renderables);
    }

    private Renderable obtainRenderable(Material material0, int v) {
        Renderable renderable0 = (Renderable)this.renderablesPool.obtain();
        renderable0.bones = null;
        renderable0.environment = null;
        renderable0.material = material0;
        renderable0.meshPart.mesh = null;
        renderable0.meshPart.offset = 0;
        renderable0.meshPart.size = 0;
        renderable0.meshPart.primitiveType = v;
        renderable0.meshPart.center.set(0.0f, 0.0f, 0.0f);
        renderable0.meshPart.halfExtents.set(0.0f, 0.0f, 0.0f);
        renderable0.meshPart.radius = -1.0f;
        renderable0.shader = null;
        renderable0.userData = null;
        renderable0.worldTransform.idt();
        return renderable0;
    }
}

