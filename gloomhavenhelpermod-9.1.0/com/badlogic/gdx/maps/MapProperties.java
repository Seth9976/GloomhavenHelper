package com.badlogic.gdx.maps;

import com.badlogic.gdx.utils.ObjectMap;
import java.util.Iterator;

public class MapProperties {
   private ObjectMap properties = new ObjectMap();

   public boolean containsKey(String key) {
      return this.properties.containsKey(key);
   }

   public Object get(String key) {
      return this.properties.get(key);
   }

   public Object get(String key, Class clazz) {
      return this.get(key);
   }

   public Object get(String key, Object defaultValue, Class clazz) {
      Object object = this.get(key);
      return object == null ? defaultValue : object;
   }

   public void put(String key, Object value) {
      this.properties.put(key, value);
   }

   public void putAll(MapProperties properties) {
      this.properties.putAll(properties.properties);
   }

   public void remove(String key) {
      this.properties.remove(key);
   }

   public void clear() {
      this.properties.clear();
   }

   public Iterator getKeys() {
      return this.properties.keys();
   }

   public Iterator getValues() {
      return this.properties.values();
   }
}
