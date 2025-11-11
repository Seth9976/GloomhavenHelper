package com.badlogic.gdx.maps;

public class MapGroupLayer extends MapLayer {
   private MapLayers layers = new MapLayers();

   public MapLayers getLayers() {
      return this.layers;
   }

   @Override
   public void invalidateRenderOffset() {
      super.invalidateRenderOffset();

      for (int i = 0; i < this.layers.size(); i++) {
         MapLayer child = this.layers.get(i);
         child.invalidateRenderOffset();
      }
   }
}
