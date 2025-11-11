package com.badlogic.gdx.graphics.g3d.model.data;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class ModelData {
   public String id;
   public final short[] version = new short[2];
   public final Array meshes = new Array();
   public final Array materials = new Array();
   public final Array nodes = new Array();
   public final Array animations = new Array();

   public void addMesh(ModelMesh mesh) {
      for (ModelMesh other : this.meshes) {
         if (other.id.equals(mesh.id)) {
            throw new GdxRuntimeException("Mesh with id '" + other.id + "' already in model");
         }
      }

      this.meshes.add(mesh);
   }
}
