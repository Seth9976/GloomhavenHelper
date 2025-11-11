package com.badlogic.gdx.graphics.g3d.model;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Node {
   public String id;
   public boolean inheritTransform = true;
   public boolean isAnimated;
   public final Vector3 translation = new Vector3();
   public final Quaternion rotation = new Quaternion(0.0F, 0.0F, 0.0F, 1.0F);
   public final Vector3 scale = new Vector3(1.0F, 1.0F, 1.0F);
   public final Matrix4 localTransform = new Matrix4();
   public final Matrix4 globalTransform = new Matrix4();
   public Array parts = new Array(2);
   protected Node parent;
   private final Array children = new Array(2);

   public Matrix4 calculateLocalTransform() {
      if (!this.isAnimated) {
         this.localTransform.set(this.translation, this.rotation, this.scale);
      }

      return this.localTransform;
   }

   public Matrix4 calculateWorldTransform() {
      if (this.inheritTransform && this.parent != null) {
         this.globalTransform.set(this.parent.globalTransform).mul(this.localTransform);
      } else {
         this.globalTransform.set(this.localTransform);
      }

      return this.globalTransform;
   }

   public void calculateTransforms(boolean recursive) {
      this.calculateLocalTransform();
      this.calculateWorldTransform();
      if (recursive) {
         for (Node child : this.children) {
            child.calculateTransforms(true);
         }
      }
   }

   public void calculateBoneTransforms(boolean recursive) {
      for (NodePart part : this.parts) {
         if (part.invBoneBindTransforms != null && part.bones != null && part.invBoneBindTransforms.size == part.bones.length) {
            int n = part.invBoneBindTransforms.size;

            for (int i = 0; i < n; i++) {
               part.bones[i].set(((Node[])part.invBoneBindTransforms.keys)[i].globalTransform).mul(((Matrix4[])part.invBoneBindTransforms.values)[i]);
            }
         }
      }

      if (recursive) {
         for (Node child : this.children) {
            child.calculateBoneTransforms(true);
         }
      }
   }

   public BoundingBox calculateBoundingBox(BoundingBox out) {
      out.inf();
      return this.extendBoundingBox(out);
   }

   public BoundingBox calculateBoundingBox(BoundingBox out, boolean transform) {
      out.inf();
      return this.extendBoundingBox(out, transform);
   }

   public BoundingBox extendBoundingBox(BoundingBox out) {
      return this.extendBoundingBox(out, true);
   }

   public BoundingBox extendBoundingBox(BoundingBox out, boolean transform) {
      int partCount = this.parts.size;

      for (int i = 0; i < partCount; i++) {
         NodePart part = (NodePart)this.parts.get(i);
         if (part.enabled) {
            MeshPart meshPart = part.meshPart;
            if (transform) {
               meshPart.mesh.extendBoundingBox(out, meshPart.offset, meshPart.size, this.globalTransform);
            } else {
               meshPart.mesh.extendBoundingBox(out, meshPart.offset, meshPart.size);
            }
         }
      }

      int childCount = this.children.size;

      for (int ix = 0; ix < childCount; ix++) {
         ((Node)this.children.get(ix)).extendBoundingBox(out);
      }

      return out;
   }

   public void attachTo(Node parent) {
      parent.addChild(this);
   }

   public void detach() {
      if (this.parent != null) {
         this.parent.removeChild(this);
         this.parent = null;
      }
   }

   public boolean hasChildren() {
      return this.children != null && this.children.size > 0;
   }

   public int getChildCount() {
      return this.children.size;
   }

   public Node getChild(int index) {
      return (Node)this.children.get(index);
   }

   public Node getChild(String id, boolean recursive, boolean ignoreCase) {
      return getNode(this.children, id, recursive, ignoreCase);
   }

   public int addChild(Node child) {
      return this.insertChild(-1, child);
   }

   public int addChildren(Iterable nodes) {
      return this.insertChildren(-1, nodes);
   }

   public int insertChild(int index, Node child) {
      for (Node p = this; p != null; p = p.getParent()) {
         if (p == child) {
            throw new GdxRuntimeException("Cannot add a parent as a child");
         }
      }

      Node px = child.getParent();
      if (px != null && !px.removeChild(child)) {
         throw new GdxRuntimeException("Could not remove child from its current parent");
      } else {
         if (index >= 0 && index < this.children.size) {
            this.children.insert(index, child);
         } else {
            index = this.children.size;
            this.children.add(child);
         }

         child.parent = this;
         return index;
      }
   }

   public int insertChildren(int index, Iterable nodes) {
      if (index < 0 || index > this.children.size) {
         index = this.children.size;
      }

      int i = index;

      for (Node child : nodes) {
         this.insertChild(i++, child);
      }

      return index;
   }

   public boolean removeChild(Node child) {
      if (!this.children.removeValue(child, true)) {
         return false;
      } else {
         child.parent = null;
         return true;
      }
   }

   public Iterable getChildren() {
      return this.children;
   }

   public Node getParent() {
      return this.parent;
   }

   public boolean hasParent() {
      return this.parent != null;
   }

   public Node copy() {
      return new Node().set(this);
   }

   protected Node set(Node other) {
      this.detach();
      this.id = other.id;
      this.isAnimated = other.isAnimated;
      this.inheritTransform = other.inheritTransform;
      this.translation.set(other.translation);
      this.rotation.set(other.rotation);
      this.scale.set(other.scale);
      this.localTransform.set(other.localTransform);
      this.globalTransform.set(other.globalTransform);
      this.parts.clear();

      for (NodePart nodePart : other.parts) {
         this.parts.add(nodePart.copy());
      }

      this.children.clear();

      for (Node child : other.getChildren()) {
         this.addChild(child.copy());
      }

      return this;
   }

   public static Node getNode(Array nodes, String id, boolean recursive, boolean ignoreCase) {
      int n = nodes.size;
      if (ignoreCase) {
         for (int i = 0; i < n; i++) {
            Node node;
            if ((node = (Node)nodes.get(i)).id.equalsIgnoreCase(id)) {
               return node;
            }
         }
      } else {
         for (int ix = 0; ix < n; ix++) {
            Node node;
            if ((node = (Node)nodes.get(ix)).id.equals(id)) {
               return node;
            }
         }
      }

      if (recursive) {
         for (int ixx = 0; ixx < n; ixx++) {
            Node node;
            if ((node = getNode(((Node)nodes.get(ixx)).children, id, true, ignoreCase)) != null) {
               return node;
            }
         }
      }

      return null;
   }
}
