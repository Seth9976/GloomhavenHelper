package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapLayers implements Iterable {
   private Array layers = new Array();

   public MapLayer get(int index) {
      return (MapLayer)this.layers.get(index);
   }

   public MapLayer get(String name) {
      int i = 0;

      for (int n = this.layers.size; i < n; i++) {
         MapLayer layer = (MapLayer)this.layers.get(i);
         if (name.equals(layer.getName())) {
            return layer;
         }
      }

      return null;
   }

   public int getIndex(String name) {
      return this.getIndex(this.get(name));
   }

   public int getIndex(MapLayer layer) {
      return this.layers.indexOf(layer, true);
   }

   public int getCount() {
      return this.layers.size;
   }

   public void add(MapLayer layer) {
      this.layers.add(layer);
   }

   public void remove(int index) {
      this.layers.removeIndex(index);
   }

   public void remove(MapLayer layer) {
      this.layers.removeValue(layer, true);
   }

   public int size() {
      return this.layers.size;
   }

   public Array getByType(Class type) {
      return this.getByType(type, new Array());
   }

   public Array getByType(Class type, Array fill) {
      fill.clear();
      int i = 0;

      for (int n = this.layers.size; i < n; i++) {
         MapLayer layer = (MapLayer)this.layers.get(i);
         if (ClassReflection.isInstance(type, layer)) {
            fill.add(layer);
         }
      }

      return fill;
   }

   @Override
   public Iterator iterator() {
      return this.layers.iterator();
   }
}
