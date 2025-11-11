package com.badlogic.gdx.utils;

import java.util.Arrays;

public class BinaryHeap {
   public int size;
   private BinaryHeap.Node[] nodes;
   private final boolean isMaxHeap;

   public BinaryHeap() {
      this(16, false);
   }

   public BinaryHeap(int capacity, boolean isMaxHeap) {
      this.isMaxHeap = isMaxHeap;
      this.nodes = new BinaryHeap.Node[capacity];
   }

   public BinaryHeap.Node add(BinaryHeap.Node node) {
      if (this.size == this.nodes.length) {
         BinaryHeap.Node[] newNodes = new BinaryHeap.Node[this.size << 1];
         System.arraycopy(this.nodes, 0, newNodes, 0, this.size);
         this.nodes = newNodes;
      }

      node.index = this.size;
      this.nodes[this.size] = node;
      this.up(this.size++);
      return node;
   }

   public BinaryHeap.Node add(BinaryHeap.Node node, float value) {
      node.value = value;
      return this.add(node);
   }

   public boolean contains(BinaryHeap.Node node, boolean identity) {
      if (node == null) {
         throw new IllegalArgumentException("node cannot be null.");
      } else {
         if (identity) {
            for (BinaryHeap.Node n : this.nodes) {
               if (n == node) {
                  return true;
               }
            }
         } else {
            for (BinaryHeap.Node other : this.nodes) {
               if (other.equals(node)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public BinaryHeap.Node peek() {
      if (this.size == 0) {
         throw new IllegalStateException("The heap is empty.");
      } else {
         return this.nodes[0];
      }
   }

   public BinaryHeap.Node pop() {
      BinaryHeap.Node removed = this.nodes[0];
      if (--this.size > 0) {
         this.nodes[0] = this.nodes[this.size];
         this.nodes[this.size] = null;
         this.down(0);
      } else {
         this.nodes[0] = null;
      }

      return removed;
   }

   public BinaryHeap.Node remove(BinaryHeap.Node node) {
      if (--this.size > 0) {
         BinaryHeap.Node moved = this.nodes[this.size];
         this.nodes[this.size] = null;
         this.nodes[node.index] = moved;
         if (moved.value < node.value ^ this.isMaxHeap) {
            this.up(node.index);
         } else {
            this.down(node.index);
         }
      } else {
         this.nodes[0] = null;
      }

      return node;
   }

   public boolean notEmpty() {
      return this.size > 0;
   }

   public boolean isEmpty() {
      return this.size == 0;
   }

   public void clear() {
      Arrays.fill(this.nodes, 0, this.size, null);
      this.size = 0;
   }

   public void setValue(BinaryHeap.Node node, float value) {
      float oldValue = node.value;
      node.value = value;
      if (value < oldValue ^ this.isMaxHeap) {
         this.up(node.index);
      } else {
         this.down(node.index);
      }
   }

   private void up(int index) {
      BinaryHeap.Node[] nodes = this.nodes;
      BinaryHeap.Node node = nodes[index];
      float value = node.value;

      while (index > 0) {
         int parentIndex = index - 1 >> 1;
         BinaryHeap.Node parent = nodes[parentIndex];
         if (!(value < parent.value ^ this.isMaxHeap)) {
            break;
         }

         nodes[index] = parent;
         parent.index = index;
         index = parentIndex;
      }

      nodes[index] = node;
      node.index = index;
   }

   private void down(int index) {
      BinaryHeap.Node[] nodes = this.nodes;
      int size = this.size;
      BinaryHeap.Node node = nodes[index];
      float value = node.value;

      while (true) {
         int leftIndex = 1 + (index << 1);
         if (leftIndex >= size) {
            break;
         }

         int rightIndex = leftIndex + 1;
         BinaryHeap.Node leftNode = nodes[leftIndex];
         float leftValue = leftNode.value;
         BinaryHeap.Node rightNode;
         float rightValue;
         if (rightIndex >= size) {
            rightNode = null;
            rightValue = this.isMaxHeap ? -Float.MAX_VALUE : Float.MAX_VALUE;
         } else {
            rightNode = nodes[rightIndex];
            rightValue = rightNode.value;
         }

         if (leftValue < rightValue ^ this.isMaxHeap) {
            if (leftValue == value || leftValue > value ^ this.isMaxHeap) {
               break;
            }

            nodes[index] = leftNode;
            leftNode.index = index;
            index = leftIndex;
         } else {
            if (rightValue == value || rightValue > value ^ this.isMaxHeap) {
               break;
            }

            nodes[index] = rightNode;
            if (rightNode != null) {
               rightNode.index = index;
            }

            index = rightIndex;
         }
      }

      nodes[index] = node;
      node.index = index;
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof BinaryHeap)) {
         return false;
      } else {
         BinaryHeap other = (BinaryHeap)obj;
         if (other.size != this.size) {
            return false;
         } else {
            BinaryHeap.Node[] nodes1 = this.nodes;
            BinaryHeap.Node[] nodes2 = other.nodes;
            int i = 0;

            for (int n = this.size; i < n; i++) {
               if (nodes1[i].value != nodes2[i].value) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public int hashCode() {
      int h = 1;
      BinaryHeap.Node[] nodes = this.nodes;
      int i = 0;

      for (int n = this.size; i < n; i++) {
         h = h * 31 + Float.floatToIntBits(nodes[i].value);
      }

      return h;
   }

   @Override
   public String toString() {
      if (this.size == 0) {
         return "[]";
      } else {
         BinaryHeap.Node[] nodes = this.nodes;
         StringBuilder buffer = new StringBuilder(32);
         buffer.append('[');
         buffer.append(nodes[0].value);

         for (int i = 1; i < this.size; i++) {
            buffer.append(", ");
            buffer.append(nodes[i].value);
         }

         buffer.append(']');
         return buffer.toString();
      }
   }

   public static class Node {
      float value;
      int index;

      public Node(float value) {
         this.value = value;
      }

      public float getValue() {
         return this.value;
      }

      @Override
      public String toString() {
         return Float.toString(this.value);
      }
   }
}
