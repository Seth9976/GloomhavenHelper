package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.scenes.scene2d.utils.Selection;
import com.badlogic.gdx.scenes.scene2d.utils.UIUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Null;

public class Tree extends WidgetGroup {
   private static final Vector2 tmp = new Vector2();
   Tree.TreeStyle style;
   final Array rootNodes = new Array();
   final Selection selection;
   float ySpacing = 4.0F;
   float iconSpacingLeft = 2.0F;
   float iconSpacingRight = 2.0F;
   float paddingLeft;
   float paddingRight;
   float indentSpacing;
   private float prefWidth;
   private float prefHeight;
   private boolean sizeInvalid = true;
   private Tree.Node foundNode;
   private Tree.Node overNode;
   Tree.Node rangeStart;
   private ClickListener clickListener;

   public Tree(Skin skin) {
      this((Tree.TreeStyle)skin.get(Tree.TreeStyle.class));
   }

   public Tree(Skin skin, String styleName) {
      this((Tree.TreeStyle)skin.get(styleName, Tree.TreeStyle.class));
   }

   public Tree(Tree.TreeStyle style) {
      this.selection = new Selection() {
         @Override
         protected void changed() {
            switch (this.size()) {
               case 0:
                  Tree.this.rangeStart = null;
                  break;
               case 1:
                  Tree.this.rangeStart = (Tree.Node)this.first();
            }
         }
      };
      this.selection.setActor(this);
      this.selection.setMultiple(true);
      this.setStyle(style);
      this.initialize();
   }

   private void initialize() {
      this.addListener(this.clickListener = new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            Tree.Node node = (N)Tree.this.getNodeAt(y);
            if (node != null) {
               if (node == Tree.this.getNodeAt(this.getTouchDownY())) {
                  if (Tree.this.selection.getMultiple() && Tree.this.selection.notEmpty() && UIUtils.shift()) {
                     if (Tree.this.rangeStart == null) {
                        Tree.this.rangeStart = node;
                     }

                     Tree.Node rangeStart = (N)Tree.this.rangeStart;
                     if (!UIUtils.ctrl()) {
                        Tree.this.selection.clear();
                     }

                     float start = rangeStart.actor.getY();
                     float end = node.actor.getY();
                     if (start > end) {
                        Tree.this.selectNodes(Tree.this.rootNodes, end, start);
                     } else {
                        Tree.this.selectNodes(Tree.this.rootNodes, start, end);
                        Tree.this.selection.items().orderedItems().reverse();
                     }

                     Tree.this.selection.fireChangeEvent();
                     Tree.this.rangeStart = rangeStart;
                  } else {
                     if (node.children.size > 0 && (!Tree.this.selection.getMultiple() || !UIUtils.ctrl())) {
                        float rowX = node.actor.getX();
                        if (node.icon != null) {
                           rowX -= Tree.this.iconSpacingRight + node.icon.getMinWidth();
                        }

                        if (x < rowX) {
                           node.setExpanded(!node.expanded);
                           return;
                        }
                     }

                     if (node.isSelectable()) {
                        Tree.this.selection.choose(node);
                        if (!Tree.this.selection.isEmpty()) {
                           Tree.this.rangeStart = node;
                        }
                     }
                  }
               }
            }
         }

         @Override
         public boolean mouseMoved(InputEvent event, float x, float y) {
            Tree.this.setOverNode(Tree.this.getNodeAt(y));
            return false;
         }

         @Override
         public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
            super.enter(event, x, y, pointer, fromActor);
            Tree.this.setOverNode(Tree.this.getNodeAt(y));
         }

         @Override
         public void exit(InputEvent event, float x, float y, int pointer, @Null Actor toActor) {
            super.exit(event, x, y, pointer, toActor);
            if (toActor == null || !toActor.isDescendantOf(Tree.this)) {
               Tree.this.setOverNode(null);
            }
         }
      });
   }

   public void setStyle(Tree.TreeStyle style) {
      this.style = style;
      if (this.indentSpacing == 0.0F) {
         this.indentSpacing = this.plusMinusWidth();
      }
   }

   public void add(Tree.Node node) {
      this.insert(this.rootNodes.size, node);
   }

   public void insert(int index, Tree.Node node) {
      if (node.parent != null) {
         node.parent.remove(node);
         node.parent = null;
      } else {
         int existingIndex = this.rootNodes.indexOf(node, true);
         if (existingIndex != -1) {
            if (existingIndex == index) {
               return;
            }

            if (existingIndex < index) {
               index--;
            }

            this.rootNodes.removeIndex(existingIndex);
            int actorIndex = node.actor.getZIndex();
            if (actorIndex != -1) {
               node.removeFromTree(this, actorIndex);
            }
         }
      }

      this.rootNodes.insert(index, node);
      int actorIndex;
      if (index == 0) {
         actorIndex = 0;
      } else if (index < this.rootNodes.size - 1) {
         actorIndex = ((Tree.Node)this.rootNodes.get(index + 1)).actor.getZIndex();
      } else {
         Tree.Node before = (N)((Tree.Node)this.rootNodes.get(index - 1));
         actorIndex = before.actor.getZIndex() + before.countActors();
      }

      node.addToTree(this, actorIndex);
   }

   public void remove(Tree.Node node) {
      if (node.parent != null) {
         node.parent.remove(node);
      } else if (this.rootNodes.removeValue(node, true)) {
         int actorIndex = node.actor.getZIndex();
         if (actorIndex != -1) {
            node.removeFromTree(this, actorIndex);
         }
      }
   }

   @Override
   public void clearChildren(boolean unfocus) {
      super.clearChildren(unfocus);
      this.setOverNode(null);
      this.rootNodes.clear();
      this.selection.clear();
   }

   @Override
   public void invalidate() {
      super.invalidate();
      this.sizeInvalid = true;
   }

   private float plusMinusWidth() {
      float width = Math.max(this.style.plus.getMinWidth(), this.style.minus.getMinWidth());
      if (this.style.plusOver != null) {
         width = Math.max(width, this.style.plusOver.getMinWidth());
      }

      if (this.style.minusOver != null) {
         width = Math.max(width, this.style.minusOver.getMinWidth());
      }

      return width;
   }

   private void computeSize() {
      this.sizeInvalid = false;
      this.prefWidth = this.plusMinusWidth();
      this.prefHeight = 0.0F;
      this.computeSize(this.rootNodes, 0.0F, this.prefWidth);
      this.prefWidth = this.prefWidth + (this.paddingLeft + this.paddingRight);
   }

   private void computeSize(Array nodes, float indent, float plusMinusWidth) {
      float ySpacing = this.ySpacing;
      float spacing = this.iconSpacingLeft + this.iconSpacingRight;
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)nodes.get(i));
         float rowWidth = indent + plusMinusWidth;
         Actor actor = node.actor;
         if (actor instanceof Layout) {
            Layout layout = (Layout)actor;
            rowWidth += layout.getPrefWidth();
            node.height = layout.getPrefHeight();
         } else {
            rowWidth += actor.getWidth();
            node.height = actor.getHeight();
         }

         if (node.icon != null) {
            rowWidth += spacing + node.icon.getMinWidth();
            node.height = Math.max(node.height, node.icon.getMinHeight());
         }

         this.prefWidth = Math.max(this.prefWidth, rowWidth);
         this.prefHeight = this.prefHeight + (node.height + ySpacing);
         if (node.expanded) {
            this.computeSize(node.children, indent + this.indentSpacing, plusMinusWidth);
         }
      }
   }

   @Override
   public void layout() {
      if (this.sizeInvalid) {
         this.computeSize();
      }

      this.layout(this.rootNodes, this.paddingLeft, this.getHeight() - this.ySpacing / 2.0F, this.plusMinusWidth());
   }

   private float layout(Array nodes, float indent, float y, float plusMinusWidth) {
      float ySpacing = this.ySpacing;
      float iconSpacingLeft = this.iconSpacingLeft;
      float spacing = iconSpacingLeft + this.iconSpacingRight;
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)nodes.get(i));
         float x = indent + plusMinusWidth;
         if (node.icon != null) {
            x += spacing + node.icon.getMinWidth();
         } else {
            x += iconSpacingLeft;
         }

         if (node.actor instanceof Layout) {
            ((Layout)node.actor).pack();
         }

         y -= node.getHeight();
         node.actor.setPosition(x, y);
         y -= ySpacing;
         if (node.expanded) {
            y = this.layout(node.children, indent + this.indentSpacing, y, plusMinusWidth);
         }
      }

      return y;
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      this.drawBackground(batch, parentAlpha);
      Color color = this.getColor();
      float a = color.a * parentAlpha;
      batch.setColor(color.r, color.g, color.b, a);
      this.draw(batch, color.r, color.g, color.b, a, this.rootNodes, this.paddingLeft, this.plusMinusWidth());
      super.draw(batch, parentAlpha);
   }

   protected void drawBackground(Batch batch, float parentAlpha) {
      if (this.style.background != null) {
         Color color = this.getColor();
         batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
         this.style.background.draw(batch, this.getX(), this.getY(), this.getWidth(), this.getHeight());
      }
   }

   private void draw(Batch batch, float r, float g, float b, float a, Array nodes, float indent, float plusMinusWidth) {
      Rectangle cullingArea = this.getCullingArea();
      float cullBottom = 0.0F;
      float cullTop = 0.0F;
      if (cullingArea != null) {
         cullBottom = cullingArea.y;
         cullTop = cullBottom + cullingArea.height;
      }

      Tree.TreeStyle style = this.style;
      float x = this.getX();
      float y = this.getY();
      float expandX = x + indent;
      float iconX = expandX + plusMinusWidth + this.iconSpacingLeft;
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)nodes.get(i));
         Actor actor = node.actor;
         float actorY = actor.getY();
         float height = node.height;
         if (cullingArea == null || actorY + height >= cullBottom && actorY <= cullTop) {
            if (this.selection.contains(node) && style.selection != null) {
               this.drawSelection(node, style.selection, batch, x, y + actorY - this.ySpacing / 2.0F, this.getWidth(), height + this.ySpacing);
            } else if (node == this.overNode && style.over != null) {
               this.drawOver(node, style.over, batch, x, y + actorY - this.ySpacing / 2.0F, this.getWidth(), height + this.ySpacing);
            }

            if (node.icon != null) {
               float iconY = y + actorY + Math.round((height - node.icon.getMinHeight()) / 2.0F);
               Color actorColor = actor.getColor();
               batch.setColor(actorColor.r, actorColor.g, actorColor.b, actorColor.a * a);
               this.drawIcon(node, node.icon, batch, iconX, iconY);
               batch.setColor(r, g, b, a);
            }

            if (node.children.size > 0) {
               Drawable expandIcon = this.getExpandIcon(node, iconX);
               float iconY = y + actorY + Math.round((height - expandIcon.getMinHeight()) / 2.0F);
               this.drawExpandIcon(node, expandIcon, batch, expandX, iconY);
            }
         } else if (actorY < cullBottom) {
            return;
         }

         if (node.expanded && node.children.size > 0) {
            this.draw(batch, r, g, b, a, node.children, indent + this.indentSpacing, plusMinusWidth);
         }
      }
   }

   protected void drawSelection(Tree.Node node, Drawable selection, Batch batch, float x, float y, float width, float height) {
      selection.draw(batch, x, y, width, height);
   }

   protected void drawOver(Tree.Node node, Drawable over, Batch batch, float x, float y, float width, float height) {
      over.draw(batch, x, y, width, height);
   }

   protected void drawExpandIcon(Tree.Node node, Drawable expandIcon, Batch batch, float x, float y) {
      expandIcon.draw(batch, x, y, expandIcon.getMinWidth(), expandIcon.getMinHeight());
   }

   protected void drawIcon(Tree.Node node, Drawable icon, Batch batch, float x, float y) {
      icon.draw(batch, x, y, icon.getMinWidth(), icon.getMinHeight());
   }

   protected Drawable getExpandIcon(Tree.Node node, float iconX) {
      boolean over = false;
      if (node == this.overNode
         && Gdx.app.getType() == Application.ApplicationType.Desktop
         && (!this.selection.getMultiple() || !UIUtils.ctrl() && !UIUtils.shift())) {
         float mouseX = this.screenToLocalCoordinates(tmp.set(Gdx.input.getX(), 0.0F)).x;
         if (mouseX >= 0.0F && mouseX < iconX) {
            over = true;
         }
      }

      if (over) {
         Drawable icon = node.expanded ? this.style.minusOver : this.style.plusOver;
         if (icon != null) {
            return icon;
         }
      }

      return node.expanded ? this.style.minus : this.style.plus;
   }

   @Null
   public Tree.Node getNodeAt(float y) {
      this.foundNode = null;
      this.getNodeAt(this.rootNodes, y, this.getHeight());
      return this.foundNode;
   }

   private float getNodeAt(Array nodes, float y, float rowY) {
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)nodes.get(i));
         float height = node.height;
         rowY -= node.getHeight() - height;
         if (y >= rowY - height - this.ySpacing && y < rowY) {
            this.foundNode = node;
            return -1.0F;
         }

         rowY -= height + this.ySpacing;
         if (node.expanded) {
            rowY = this.getNodeAt(node.children, y, rowY);
            if (rowY == -1.0F) {
               return -1.0F;
            }
         }
      }

      return rowY;
   }

   void selectNodes(Array nodes, float low, float high) {
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)nodes.get(i));
         if (node.actor.getY() < low) {
            break;
         }

         if (node.isSelectable()) {
            if (node.actor.getY() <= high) {
               this.selection.add(node);
            }

            if (node.expanded) {
               this.selectNodes(node.children, low, high);
            }
         }
      }
   }

   public Selection getSelection() {
      return this.selection;
   }

   @Null
   public Tree.Node getSelectedNode() {
      return (Tree.Node)this.selection.first();
   }

   @Null
   public Object getSelectedValue() {
      Tree.Node node = (N)((Tree.Node)this.selection.first());
      return node == null ? null : node.getValue();
   }

   public Tree.TreeStyle getStyle() {
      return this.style;
   }

   public Array getRootNodes() {
      return this.rootNodes;
   }

   @Deprecated
   public Array getNodes() {
      return this.rootNodes;
   }

   public void updateRootNodes() {
      int i = 0;

      for (int n = this.rootNodes.size; i < n; i++) {
         Tree.Node node = (N)((Tree.Node)this.rootNodes.get(i));
         int actorIndex = node.actor.getZIndex();
         if (actorIndex != -1) {
            node.removeFromTree(this, actorIndex);
         }
      }

      i = 0;
      int nx = this.rootNodes.size;

      for (int actorIndex = 0; i < nx; i++) {
         actorIndex += ((Tree.Node)this.rootNodes.get(i)).addToTree(this, actorIndex);
      }
   }

   @Null
   public Tree.Node getOverNode() {
      return this.overNode;
   }

   @Null
   public Object getOverValue() {
      return this.overNode == null ? null : this.overNode.getValue();
   }

   public void setOverNode(@Null Tree.Node overNode) {
      this.overNode = overNode;
   }

   public void setPadding(float padding) {
      this.paddingLeft = padding;
      this.paddingRight = padding;
   }

   public void setPadding(float left, float right) {
      this.paddingLeft = left;
      this.paddingRight = right;
   }

   public void setIndentSpacing(float indentSpacing) {
      this.indentSpacing = indentSpacing;
   }

   public float getIndentSpacing() {
      return this.indentSpacing;
   }

   public void setYSpacing(float ySpacing) {
      this.ySpacing = ySpacing;
   }

   public float getYSpacing() {
      return this.ySpacing;
   }

   public void setIconSpacing(float left, float right) {
      this.iconSpacingLeft = left;
      this.iconSpacingRight = right;
   }

   @Override
   public float getPrefWidth() {
      if (this.sizeInvalid) {
         this.computeSize();
      }

      return this.prefWidth;
   }

   @Override
   public float getPrefHeight() {
      if (this.sizeInvalid) {
         this.computeSize();
      }

      return this.prefHeight;
   }

   public void findExpandedValues(Array values) {
      findExpandedValues(this.rootNodes, values);
   }

   public void restoreExpandedValues(Array values) {
      int i = 0;

      for (int n = values.size; i < n; i++) {
         Tree.Node node = (N)this.findNode(values.get(i));
         if (node != null) {
            node.setExpanded(true);
            node.expandTo();
         }
      }
   }

   static boolean findExpandedValues(Array nodes, Array values) {
      boolean expanded = false;
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (Tree.Node)nodes.get(i);
         if (node.expanded && !findExpandedValues(node.children, values)) {
            values.add(node.value);
         }
      }

      return expanded;
   }

   @Null
   public Tree.Node findNode(Object value) {
      if (value == null) {
         throw new IllegalArgumentException("value cannot be null.");
      } else {
         return findNode(this.rootNodes, value);
      }
   }

   @Null
   static Tree.Node findNode(Array nodes, Object value) {
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (Tree.Node)nodes.get(i);
         if (value.equals(node.value)) {
            return node;
         }
      }

      i = 0;

      for (int nx = nodes.size; i < nx; i++) {
         Tree.Node node = (Tree.Node)nodes.get(i);
         Tree.Node found = findNode(node.children, value);
         if (found != null) {
            return found;
         }
      }

      return null;
   }

   public void collapseAll() {
      collapseAll(this.rootNodes);
   }

   static void collapseAll(Array nodes) {
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         Tree.Node node = (Tree.Node)nodes.get(i);
         node.setExpanded(false);
         collapseAll(node.children);
      }
   }

   public void expandAll() {
      expandAll(this.rootNodes);
   }

   static void expandAll(Array nodes) {
      int i = 0;

      for (int n = nodes.size; i < n; i++) {
         ((Tree.Node)nodes.get(i)).expandAll();
      }
   }

   public ClickListener getClickListener() {
      return this.clickListener;
   }

   public abstract static class Node {
      Actor actor;
      Tree.Node parent;
      final Array children = new Array(0);
      boolean selectable = true;
      boolean expanded;
      Drawable icon;
      float height;
      Object value;

      public Node(Actor actor) {
         if (actor == null) {
            throw new IllegalArgumentException("actor cannot be null.");
         } else {
            this.actor = actor;
         }
      }

      public Node() {
      }

      public void setExpanded(boolean expanded) {
         if (expanded != this.expanded) {
            this.expanded = expanded;
            if (this.children.size != 0) {
               Tree tree = this.getTree();
               if (tree != null) {
                  Object[] children = this.children.items;
                  int actorIndex = this.actor.getZIndex() + 1;
                  if (expanded) {
                     int i = 0;

                     for (int n = this.children.size; i < n; i++) {
                        actorIndex += ((Tree.Node)children[i]).addToTree(tree, actorIndex);
                     }
                  } else {
                     int i = 0;

                     for (int n = this.children.size; i < n; i++) {
                        ((Tree.Node)children[i]).removeFromTree(tree, actorIndex);
                     }
                  }
               }
            }
         }
      }

      protected int addToTree(Tree tree, int actorIndex) {
         tree.addActorAt(actorIndex, this.actor);
         if (!this.expanded) {
            return 1;
         } else {
            int childIndex = actorIndex + 1;
            Object[] children = this.children.items;
            int i = 0;

            for (int n = this.children.size; i < n; i++) {
               childIndex += ((Tree.Node)children[i]).addToTree(tree, childIndex);
            }

            return childIndex - actorIndex;
         }
      }

      protected void removeFromTree(Tree tree, int actorIndex) {
         Actor removeActorAt = tree.removeActorAt(actorIndex, true);
         if (this.expanded) {
            Object[] children = this.children.items;
            int i = 0;

            for (int n = this.children.size; i < n; i++) {
               ((Tree.Node)children[i]).removeFromTree(tree, actorIndex);
            }
         }
      }

      public void add(Tree.Node node) {
         this.insert(this.children.size, node);
      }

      public void addAll(Array nodes) {
         int i = 0;

         for (int n = nodes.size; i < n; i++) {
            this.insert(this.children.size, (Tree.Node)nodes.get(i));
         }
      }

      public void insert(int childIndex, Tree.Node node) {
         node.parent = this;
         this.children.insert(childIndex, node);
         if (this.expanded) {
            Tree tree = this.getTree();
            if (tree != null) {
               int actorIndex;
               if (childIndex == 0) {
                  actorIndex = this.actor.getZIndex() + 1;
               } else if (childIndex < this.children.size - 1) {
                  actorIndex = ((Tree.Node)this.children.get(childIndex + 1)).actor.getZIndex();
               } else {
                  Tree.Node before = (N)((Tree.Node)this.children.get(childIndex - 1));
                  actorIndex = before.actor.getZIndex() + before.countActors();
               }

               node.addToTree(tree, actorIndex);
            }
         }
      }

      int countActors() {
         if (!this.expanded) {
            return 1;
         } else {
            int count = 1;
            Object[] children = this.children.items;
            int i = 0;

            for (int n = this.children.size; i < n; i++) {
               count += ((Tree.Node)children[i]).countActors();
            }

            return count;
         }
      }

      public void remove() {
         Tree tree = this.getTree();
         if (tree != null) {
            tree.remove(this);
         } else if (this.parent != null) {
            this.parent.remove(this);
         }
      }

      public void remove(Tree.Node node) {
         if (this.children.removeValue(node, true)) {
            if (this.expanded) {
               Tree tree = this.getTree();
               if (tree != null) {
                  node.removeFromTree(tree, node.actor.getZIndex());
               }
            }
         }
      }

      public void clearChildren() {
         if (this.expanded) {
            Tree tree = this.getTree();
            if (tree != null) {
               int actorIndex = this.actor.getZIndex() + 1;
               Object[] children = this.children.items;
               int i = 0;

               for (int n = this.children.size; i < n; i++) {
                  ((Tree.Node)children[i]).removeFromTree(tree, actorIndex);
               }
            }
         }

         this.children.clear();
      }

      @Null
      public Tree getTree() {
         Group parent = this.actor.getParent();
         return parent instanceof Tree ? (Tree)parent : null;
      }

      public void setActor(Actor newActor) {
         if (this.actor != null) {
            Tree tree = this.getTree();
            if (tree != null) {
               int index = this.actor.getZIndex();
               tree.removeActorAt(index, true);
               tree.addActorAt(index, newActor);
            }
         }

         this.actor = newActor;
      }

      public Actor getActor() {
         return this.actor;
      }

      public boolean isExpanded() {
         return this.expanded;
      }

      public Array getChildren() {
         return this.children;
      }

      public boolean hasChildren() {
         return this.children.size > 0;
      }

      public void updateChildren() {
         if (this.expanded) {
            Tree tree = this.getTree();
            if (tree != null) {
               Object[] children = this.children.items;
               int n = this.children.size;
               int actorIndex = this.actor.getZIndex() + 1;

               for (int i = 0; i < n; i++) {
                  ((Tree.Node)children[i]).removeFromTree(tree, actorIndex);
               }

               for (int i = 0; i < n; i++) {
                  actorIndex += ((Tree.Node)children[i]).addToTree(tree, actorIndex);
               }
            }
         }
      }

      @Null
      public Tree.Node getParent() {
         return this.parent;
      }

      public void setIcon(@Null Drawable icon) {
         this.icon = icon;
      }

      @Null
      public Object getValue() {
         return this.value;
      }

      public void setValue(@Null Object value) {
         this.value = value;
      }

      @Null
      public Drawable getIcon() {
         return this.icon;
      }

      public int getLevel() {
         int level = 0;
         Tree.Node current = this;

         do {
            level++;
            current = current.getParent();
         } while (current != null);

         return level;
      }

      @Null
      public Tree.Node findNode(Object value) {
         if (value == null) {
            throw new IllegalArgumentException("value cannot be null.");
         } else {
            return value.equals(this.value) ? this : Tree.findNode(this.children, value);
         }
      }

      public void collapseAll() {
         this.setExpanded(false);
         Tree.collapseAll(this.children);
      }

      public void expandAll() {
         this.setExpanded(true);
         if (this.children.size > 0) {
            Tree.expandAll(this.children);
         }
      }

      public void expandTo() {
         for (Tree.Node node = this.parent; node != null; node = node.parent) {
            node.setExpanded(true);
         }
      }

      public boolean isSelectable() {
         return this.selectable;
      }

      public void setSelectable(boolean selectable) {
         this.selectable = selectable;
      }

      public void findExpandedValues(Array values) {
         if (this.expanded && !Tree.findExpandedValues(this.children, values)) {
            values.add(this.value);
         }
      }

      public void restoreExpandedValues(Array values) {
         int i = 0;

         for (int n = values.size; i < n; i++) {
            Tree.Node node = (N)this.findNode(values.get(i));
            if (node != null) {
               node.setExpanded(true);
               node.expandTo();
            }
         }
      }

      public float getHeight() {
         return this.height;
      }

      public boolean isAscendantOf(Tree.Node node) {
         if (node == null) {
            throw new IllegalArgumentException("node cannot be null.");
         } else {
            Tree.Node current = node;

            while (current != this) {
               current = current.parent;
               if (current == null) {
                  return false;
               }
            }

            return true;
         }
      }

      public boolean isDescendantOf(Tree.Node node) {
         if (node == null) {
            throw new IllegalArgumentException("node cannot be null.");
         } else {
            Tree.Node parent = this;

            while (parent != node) {
               parent = parent.parent;
               if (parent == null) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static class TreeStyle {
      public Drawable plus;
      public Drawable minus;
      @Null
      public Drawable plusOver;
      @Null
      public Drawable minusOver;
      @Null
      public Drawable over;
      @Null
      public Drawable selection;
      @Null
      public Drawable background;

      public TreeStyle() {
      }

      public TreeStyle(Drawable plus, Drawable minus, @Null Drawable selection) {
         this.plus = plus;
         this.minus = minus;
         this.selection = selection;
      }

      public TreeStyle(Tree.TreeStyle style) {
         this.plus = style.plus;
         this.minus = style.minus;
         this.plusOver = style.plusOver;
         this.minusOver = style.minusOver;
         this.over = style.over;
         this.selection = style.selection;
         this.background = style.background;
      }
   }
}
