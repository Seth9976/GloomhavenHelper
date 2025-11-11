package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;

public class TiledMap extends Map {
   private TiledMapTileSets tilesets = new TiledMapTileSets();
   private Array ownedResources;

   public TiledMapTileSets getTileSets() {
      return this.tilesets;
   }

   public void setOwnedResources(Array resources) {
      this.ownedResources = resources;
   }

   @Override
   public void dispose() {
      if (this.ownedResources != null) {
         for (Disposable resource : this.ownedResources) {
            resource.dispose();
         }
      }
   }
}
