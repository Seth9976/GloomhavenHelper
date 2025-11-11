package com.badlogic.gdx.maps;

public class MapGroupLayer extends MapLayer {
    private MapLayers layers;

    public MapGroupLayer() {
        this.layers = new MapLayers();
    }

    public MapLayers getLayers() {
        return this.layers;
    }

    @Override  // com.badlogic.gdx.maps.MapLayer
    public void invalidateRenderOffset() {
        super.invalidateRenderOffset();
        for(int v = 0; v < this.layers.size(); ++v) {
            this.layers.get(v).invalidateRenderOffset();
        }
    }
}

