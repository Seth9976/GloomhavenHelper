package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import java.util.Iterator;

public class MapObjects implements Iterable {
   private Array objects = new Array();

   public MapObject get(int index) {
      return (MapObject)this.objects.get(index);
   }

   public MapObject get(String name) {
      int i = 0;

      for (int n = this.objects.size; i < n; i++) {
         MapObject object = (MapObject)this.objects.get(i);
         if (name.equals(object.getName())) {
            return object;
         }
      }

      return null;
   }

   public int getIndex(String name) {
      return this.getIndex(this.get(name));
   }

   public int getIndex(MapObject object) {
      return this.objects.indexOf(object, true);
   }

   public int getCount() {
      return this.objects.size;
   }

   public void add(MapObject object) {
      this.objects.add(object);
   }

   public void remove(int index) {
      this.objects.removeIndex(index);
   }

   public void remove(MapObject object) {
      this.objects.removeValue(object, true);
   }

   public Array getByType(Class type) {
      return this.getByType(type, new Array());
   }

   public Array getByType(Class type, Array fill) {
      fill.clear();
      int i = 0;

      for (int n = this.objects.size; i < n; i++) {
         MapObject object = (MapObject)this.objects.get(i);
         if (ClassReflection.isInstance(type, object)) {
            fill.add(object);
         }
      }

      return fill;
   }

   @Override
   public Iterator iterator() {
      return this.objects.iterator();
   }
}
