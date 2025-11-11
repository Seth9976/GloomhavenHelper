package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Application.ApplicationType;
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
    public static abstract class Node {
        Actor actor;
        final Array children;
        boolean expanded;
        float height;
        Drawable icon;
        Node parent;
        boolean selectable;
        Object value;

        public Node() {
            this.children = new Array(0);
            this.selectable = true;
        }

        public Node(Actor actor0) {
            this.children = new Array(0);
            this.selectable = true;
            if(actor0 == null) {
                throw new IllegalArgumentException("actor cannot be null.");
            }
            this.actor = actor0;
        }

        public void add(Node tree$Node0) {
            this.insert(this.children.size, tree$Node0);
        }

        public void addAll(Array array0) {
            int v = array0.size;
            for(int v1 = 0; v1 < v; ++v1) {
                this.insert(this.children.size, ((Node)array0.get(v1)));
            }
        }

        protected int addToTree(Tree tree0, int v) {
            tree0.addActorAt(v, this.actor);
            if(!this.expanded) {
                return 1;
            }
            int v1 = v + 1;
            Object[] arr_object = this.children.items;
            int v3 = this.children.size;
            for(int v2 = 0; v2 < v3; ++v2) {
                v1 += ((Node)arr_object[v2]).addToTree(tree0, v1);
            }
            return v1 - v;
        }

        public void clearChildren() {
            if(this.expanded) {
                Tree tree0 = this.getTree();
                if(tree0 != null) {
                    int v = this.actor.getZIndex();
                    Object[] arr_object = this.children.items;
                    int v2 = this.children.size;
                    for(int v1 = 0; v1 < v2; ++v1) {
                        ((Node)arr_object[v1]).removeFromTree(tree0, v + 1);
                    }
                }
            }
            this.children.clear();
        }

        public void collapseAll() {
            this.setExpanded(false);
            Tree.collapseAll(this.children);
        }

        int countActors() {
            int v = 1;
            if(!this.expanded) {
                return 1;
            }
            Object[] arr_object = this.children.items;
            int v2 = this.children.size;
            for(int v1 = 0; v1 < v2; ++v1) {
                v += ((Node)arr_object[v1]).countActors();
            }
            return v;
        }

        public void expandAll() {
            this.setExpanded(true);
            if(this.children.size > 0) {
                Tree.expandAll(this.children);
            }
        }

        public void expandTo() {
            for(Node tree$Node0 = this.parent; tree$Node0 != null; tree$Node0 = tree$Node0.parent) {
                tree$Node0.setExpanded(true);
            }
        }

        public void findExpandedValues(Array array0) {
            if(this.expanded && !Tree.findExpandedValues(this.children, array0)) {
                array0.add(this.value);
            }
        }

        @Null
        public Node findNode(Object object0) {
            if(object0 == null) {
                throw new IllegalArgumentException("value cannot be null.");
            }
            return object0.equals(this.value) ? this : Tree.findNode(this.children, object0);
        }

        public Actor getActor() {
            return this.actor;
        }

        public Array getChildren() {
            return this.children;
        }

        public float getHeight() {
            return this.height;
        }

        @Null
        public Drawable getIcon() {
            return this.icon;
        }

        public int getLevel() {
            int v = 0;
            Node tree$Node0 = this;
            do {
                ++v;
                tree$Node0 = tree$Node0.getParent();
            }
            while(tree$Node0 != null);
            return v;
        }

        @Null
        public Node getParent() {
            return this.parent;
        }

        @Null
        public Tree getTree() {
            Group group0 = this.actor.getParent();
            return group0 instanceof Tree ? ((Tree)group0) : null;
        }

        @Null
        public Object getValue() {
            return this.value;
        }

        public boolean hasChildren() {
            return this.children.size > 0;
        }

        public void insert(int v, Node tree$Node0) {
            int v1;
            tree$Node0.parent = this;
            this.children.insert(v, tree$Node0);
            if(!this.expanded) {
                return;
            }
            Tree tree0 = this.getTree();
            if(tree0 != null) {
                if(v == 0) {
                    v1 = this.actor.getZIndex() + 1;
                }
                else if(v < this.children.size - 1) {
                    v1 = ((Node)this.children.get(v + 1)).actor.getZIndex();
                }
                else {
                    Node tree$Node1 = (Node)this.children.get(v - 1);
                    int v2 = tree$Node1.actor.getZIndex();
                    v1 = tree$Node1.countActors() + v2;
                }
                tree$Node0.addToTree(tree0, v1);
            }
        }

        public boolean isAscendantOf(Node tree$Node0) {
            if(tree$Node0 == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            do {
                if(tree$Node0 == this) {
                    return true;
                }
                tree$Node0 = tree$Node0.parent;
            }
            while(tree$Node0 != null);
            return false;
        }

        public boolean isDescendantOf(Node tree$Node0) {
            if(tree$Node0 == null) {
                throw new IllegalArgumentException("node cannot be null.");
            }
            Node tree$Node1 = this;
            do {
                if(tree$Node1 == tree$Node0) {
                    return true;
                }
                tree$Node1 = tree$Node1.parent;
            }
            while(tree$Node1 != null);
            return false;
        }

        public boolean isExpanded() {
            return this.expanded;
        }

        public boolean isSelectable() {
            return this.selectable;
        }

        public void remove() {
            Tree tree0 = this.getTree();
            if(tree0 != null) {
                tree0.remove(this);
                return;
            }
            Node tree$Node0 = this.parent;
            if(tree$Node0 != null) {
                tree$Node0.remove(this);
            }
        }

        public void remove(Node tree$Node0) {
            if(!this.children.removeValue(tree$Node0, true)) {
                return;
            }
            if(!this.expanded) {
                return;
            }
            Tree tree0 = this.getTree();
            if(tree0 != null) {
                tree$Node0.removeFromTree(tree0, tree$Node0.actor.getZIndex());
            }
        }

        protected void removeFromTree(Tree tree0, int v) {
            tree0.removeActorAt(v, true);
            if(!this.expanded) {
                return;
            }
            Object[] arr_object = this.children.items;
            int v2 = this.children.size;
            for(int v1 = 0; v1 < v2; ++v1) {
                ((Node)arr_object[v1]).removeFromTree(tree0, v);
            }
        }

        public void restoreExpandedValues(Array array0) {
            int v = array0.size;
            for(int v1 = 0; v1 < v; ++v1) {
                Node tree$Node0 = this.findNode(array0.get(v1));
                if(tree$Node0 != null) {
                    tree$Node0.setExpanded(true);
                    tree$Node0.expandTo();
                }
            }
        }

        public void setActor(Actor actor0) {
            if(this.actor != null) {
                Tree tree0 = this.getTree();
                if(tree0 != null) {
                    int v = this.actor.getZIndex();
                    tree0.removeActorAt(v, true);
                    tree0.addActorAt(v, actor0);
                }
            }
            this.actor = actor0;
        }

        public void setExpanded(boolean z) {
            if(z == this.expanded) {
                return;
            }
            this.expanded = z;
            if(this.children.size == 0) {
                return;
            }
            Tree tree0 = this.getTree();
            if(tree0 == null) {
                return;
            }
            Object[] arr_object = this.children.items;
            int v = this.actor.getZIndex() + 1;
            int v1 = 0;
            if(z) {
                int v2 = this.children.size;
                while(v1 < v2) {
                    v += ((Node)arr_object[v1]).addToTree(tree0, v);
                    ++v1;
                }
                return;
            }
            int v3 = this.children.size;
            while(v1 < v3) {
                ((Node)arr_object[v1]).removeFromTree(tree0, v);
                ++v1;
            }
        }

        public void setIcon(@Null Drawable drawable0) {
            this.icon = drawable0;
        }

        public void setSelectable(boolean z) {
            this.selectable = z;
        }

        public void setValue(@Null Object object0) {
            this.value = object0;
        }

        public void updateChildren() {
            if(!this.expanded) {
                return;
            }
            Tree tree0 = this.getTree();
            if(tree0 == null) {
                return;
            }
            Object[] arr_object = this.children.items;
            int v = this.children.size;
            int v1 = this.actor.getZIndex() + 1;
            for(int v3 = 0; v3 < v; ++v3) {
                ((Node)arr_object[v3]).removeFromTree(tree0, v1);
            }
            for(int v2 = 0; v2 < v; ++v2) {
                v1 += ((Node)arr_object[v2]).addToTree(tree0, v1);
            }
        }
    }

    public static class TreeStyle {
        @Null
        public Drawable background;
        public Drawable minus;
        @Null
        public Drawable minusOver;
        @Null
        public Drawable over;
        public Drawable plus;
        @Null
        public Drawable plusOver;
        @Null
        public Drawable selection;

        public TreeStyle() {
        }

        public TreeStyle(TreeStyle tree$TreeStyle0) {
            this.plus = tree$TreeStyle0.plus;
            this.minus = tree$TreeStyle0.minus;
            this.plusOver = tree$TreeStyle0.plusOver;
            this.minusOver = tree$TreeStyle0.minusOver;
            this.over = tree$TreeStyle0.over;
            this.selection = tree$TreeStyle0.selection;
            this.background = tree$TreeStyle0.background;
        }

        public TreeStyle(Drawable drawable0, Drawable drawable1, @Null Drawable drawable2) {
            this.plus = drawable0;
            this.minus = drawable1;
            this.selection = drawable2;
        }
    }

    private ClickListener clickListener;
    private Node foundNode;
    float iconSpacingLeft;
    float iconSpacingRight;
    float indentSpacing;
    private Node overNode;
    float paddingLeft;
    float paddingRight;
    private float prefHeight;
    private float prefWidth;
    Node rangeStart;
    final Array rootNodes;
    final Selection selection;
    private boolean sizeInvalid;
    TreeStyle style;
    private static final Vector2 tmp;
    float ySpacing;

    static {
        Tree.tmp = new Vector2();
    }

    public Tree(Skin skin0) {
        this(((TreeStyle)skin0.get(TreeStyle.class)));
    }

    public Tree(Skin skin0, String s) {
        this(((TreeStyle)skin0.get(s, TreeStyle.class)));
    }

    public Tree(TreeStyle tree$TreeStyle0) {
        this.rootNodes = new Array();
        this.ySpacing = 4.0f;
        this.iconSpacingLeft = 2.0f;
        this.iconSpacingRight = 2.0f;
        this.sizeInvalid = true;
        this.selection = new Selection() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.Selection
            protected void changed() {
                switch(this.size()) {
                    case 0: {
                        Tree.this.rangeStart = null;
                        return;
                    }
                    case 1: {
                        Tree.this.rangeStart = (Node)this.first();
                    }
                }
            }
        };
        this.selection.setActor(this);
        this.selection.setMultiple(true);
        this.setStyle(tree$TreeStyle0);
        this.initialize();
    }

    public void add(Node tree$Node0) {
        this.insert(this.rootNodes.size, tree$Node0);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void clearChildren(boolean z) {
        super.clearChildren(z);
        this.setOverNode(null);
        this.rootNodes.clear();
        this.selection.clear();
    }

    static void collapseAll(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            tree$Node0.setExpanded(false);
            Tree.collapseAll(tree$Node0.children);
        }
    }

    public void collapseAll() {
        Tree.collapseAll(this.rootNodes);
    }

    private void computeSize() {
        this.sizeInvalid = false;
        this.prefWidth = this.plusMinusWidth();
        this.prefHeight = 0.0f;
        this.computeSize(this.rootNodes, 0.0f, this.prefWidth);
        this.prefWidth += this.paddingLeft + this.paddingRight;
    }

    private void computeSize(Array array0, float f, float f1) {
        float f5;
        float f2 = this.ySpacing;
        float f3 = this.iconSpacingLeft + this.iconSpacingRight;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            float f4 = f + f1;
            Actor actor0 = tree$Node0.actor;
            if(actor0 instanceof Layout) {
                f5 = f4 + ((Layout)actor0).getPrefWidth();
                tree$Node0.height = ((Layout)actor0).getPrefHeight();
            }
            else {
                f5 = f4 + actor0.getWidth();
                tree$Node0.height = actor0.getHeight();
            }
            if(tree$Node0.icon != null) {
                f5 += tree$Node0.icon.getMinWidth() + f3;
                tree$Node0.height = Math.max(tree$Node0.height, tree$Node0.icon.getMinHeight());
            }
            this.prefWidth = Math.max(this.prefWidth, f5);
            this.prefHeight += tree$Node0.height + f2;
            if(tree$Node0.expanded) {
                this.computeSize(tree$Node0.children, this.indentSpacing + f, f1);
            }
        }
    }

    private void draw(Batch batch0, float f, float f1, float f2, float f3, Array array0, float f4, float f5) {
        float f16;
        int v3;
        int v2;
        Node tree$Node1;
        Actor actor1;
        float f14;
        float f7;
        float f6;
        Rectangle rectangle0 = this.getCullingArea();
        if(rectangle0 == null) {
            f6 = 0.0f;
            f7 = 0.0f;
        }
        else {
            f6 = rectangle0.y;
            f7 = rectangle0.height + rectangle0.y;
        }
        TreeStyle tree$TreeStyle0 = this.style;
        float f8 = this.getX();
        float f9 = this.getY();
        float f10 = f8 + f4;
        float f11 = f10 + f5 + this.iconSpacingLeft;
        int v = array0.size;
        int v1 = 0;
        while(v1 < v) {
            Node tree$Node0 = (Node)array0.get(v1);
            Actor actor0 = tree$Node0.actor;
            float f12 = actor0.getY();
            float f13 = tree$Node0.height;
            if(rectangle0 == null || f12 + f13 >= f6 && f12 <= f7) {
                if(!this.selection.contains(tree$Node0) || tree$TreeStyle0.selection == null) {
                    f14 = f13;
                    actor1 = actor0;
                    tree$Node1 = tree$Node0;
                    v2 = v1;
                    v3 = v;
                    if(tree$Node1 == this.overNode && tree$TreeStyle0.over != null) {
                        this.drawOver(tree$Node1, tree$TreeStyle0.over, batch0, f8, f9 + f12 - this.ySpacing / 2.0f, this.getWidth(), f14 + this.ySpacing);
                    }
                }
                else {
                    f14 = f13;
                    actor1 = actor0;
                    tree$Node1 = tree$Node0;
                    v2 = v1;
                    v3 = v;
                    this.drawSelection(tree$Node0, tree$TreeStyle0.selection, batch0, f8, f9 + f12 - this.ySpacing / 2.0f, this.getWidth(), f13 + this.ySpacing);
                }
                if(tree$Node1.icon != null) {
                    float f15 = tree$Node1.icon.getMinHeight();
                    Color color0 = actor1.getColor();
                    batch0.setColor(color0.r, color0.g, color0.b, color0.a * f3);
                    this.drawIcon(tree$Node1, tree$Node1.icon, batch0, f11, f9 + f12 + ((float)Math.round((f14 - f15) / 2.0f)));
                    batch0.setColor(f, f1, f2, f3);
                }
                if(tree$Node1.children.size > 0) {
                    Drawable drawable0 = this.getExpandIcon(tree$Node1, f11);
                    this.drawExpandIcon(tree$Node1, drawable0, batch0, f10, f9 + f12 + ((float)Math.round((f14 - drawable0.getMinHeight()) / 2.0f)));
                }
            }
            else {
                if(f12 < f6) {
                    return;
                }
                tree$Node1 = tree$Node0;
                v2 = v1;
                v3 = v;
            }
            if(!tree$Node1.expanded || tree$Node1.children.size <= 0) {
                f16 = f11;
            }
            else {
                f16 = f11;
                this.draw(batch0, f, f1, f2, f3, tree$Node1.children, f4 + this.indentSpacing, f5);
            }
            v1 = v2 + 1;
            f11 = f16;
            v = v3;
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void draw(Batch batch0, float f) {
        this.drawBackground(batch0, f);
        Color color0 = this.getColor();
        float f1 = color0.a * f;
        batch0.setColor(color0.r, color0.g, color0.b, f1);
        float f2 = color0.r;
        float f3 = color0.g;
        float f4 = color0.b;
        float f5 = this.paddingLeft;
        float f6 = this.plusMinusWidth();
        this.draw(batch0, f2, f3, f4, f1, this.rootNodes, f5, f6);
        super.draw(batch0, f);
    }

    protected void drawBackground(Batch batch0, float f) {
        if(this.style.background != null) {
            Color color0 = this.getColor();
            batch0.setColor(color0.r, color0.g, color0.b, color0.a * f);
            this.style.background.draw(batch0, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }
    }

    protected void drawExpandIcon(Node tree$Node0, Drawable drawable0, Batch batch0, float f, float f1) {
        drawable0.draw(batch0, f, f1, drawable0.getMinWidth(), drawable0.getMinHeight());
    }

    protected void drawIcon(Node tree$Node0, Drawable drawable0, Batch batch0, float f, float f1) {
        drawable0.draw(batch0, f, f1, drawable0.getMinWidth(), drawable0.getMinHeight());
    }

    protected void drawOver(Node tree$Node0, Drawable drawable0, Batch batch0, float f, float f1, float f2, float f3) {
        drawable0.draw(batch0, f, f1, f2, f3);
    }

    protected void drawSelection(Node tree$Node0, Drawable drawable0, Batch batch0, float f, float f1, float f2, float f3) {
        drawable0.draw(batch0, f, f1, f2, f3);
    }

    static void expandAll(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            ((Node)array0.get(v1)).expandAll();
        }
    }

    public void expandAll() {
        Tree.expandAll(this.rootNodes);
    }

    static boolean findExpandedValues(Array array0, Array array1) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            if(tree$Node0.expanded && !Tree.findExpandedValues(tree$Node0.children, array1)) {
                array1.add(tree$Node0.value);
            }
        }
        return false;
    }

    public void findExpandedValues(Array array0) {
        Tree.findExpandedValues(this.rootNodes, array0);
    }

    @Null
    static Node findNode(Array array0, Object object0) {
        int v = array0.size;
        for(int v2 = 0; v2 < v; ++v2) {
            Node tree$Node0 = (Node)array0.get(v2);
            if(object0.equals(tree$Node0.value)) {
                return tree$Node0;
            }
        }
        int v3 = array0.size;
        for(int v1 = 0; v1 < v3; ++v1) {
            Node tree$Node1 = Tree.findNode(((Node)array0.get(v1)).children, object0);
            if(tree$Node1 != null) {
                return tree$Node1;
            }
        }
        return null;
    }

    @Null
    public Node findNode(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        return Tree.findNode(this.rootNodes, object0);
    }

    public ClickListener getClickListener() {
        return this.clickListener;
    }

    protected Drawable getExpandIcon(Node tree$Node0, float f) {
        boolean z;
        if(tree$Node0 != this.overNode || Gdx.app.getType() != ApplicationType.Desktop || this.selection.getMultiple() && (UIUtils.ctrl() || UIUtils.shift())) {
            z = false;
        }
        else {
            float f1 = (float)Gdx.input.getX();
            float f2 = this.screenToLocalCoordinates(Tree.tmp.set(f1, 0.0f)).x;
            z = f2 < 0.0f || f2 >= f ? false : true;
        }
        if(z) {
            Drawable drawable0 = tree$Node0.expanded ? this.style.minusOver : this.style.plusOver;
            if(drawable0 != null) {
                return drawable0;
            }
        }
        return tree$Node0.expanded ? this.style.minus : this.style.plus;
    }

    public float getIndentSpacing() {
        return this.indentSpacing;
    }

    private float getNodeAt(Array array0, float f, float f1) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            float f2 = tree$Node0.height;
            float f3 = f1 - (tree$Node0.getHeight() - f2);
            if(f >= f3 - f2 - this.ySpacing && f < f3) {
                this.foundNode = tree$Node0;
                return -1.0f;
            }
            f1 = f3 - (f2 + this.ySpacing);
            if(tree$Node0.expanded) {
                f1 = this.getNodeAt(tree$Node0.children, f, f1);
                if(f1 == -1.0f) {
                    return -1.0f;
                }
            }
        }
        return f1;
    }

    @Null
    public Node getNodeAt(float f) {
        this.foundNode = null;
        float f1 = this.getHeight();
        this.getNodeAt(this.rootNodes, f, f1);
        return this.foundNode;
    }

    @Deprecated
    public Array getNodes() {
        return this.rootNodes;
    }

    @Null
    public Node getOverNode() {
        return this.overNode;
    }

    @Null
    public Object getOverValue() {
        return this.overNode == null ? null : this.overNode.getValue();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefHeight() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefHeight;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public float getPrefWidth() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        return this.prefWidth;
    }

    public Array getRootNodes() {
        return this.rootNodes;
    }

    @Null
    public Node getSelectedNode() {
        return (Node)this.selection.first();
    }

    @Null
    public Object getSelectedValue() {
        Node tree$Node0 = (Node)this.selection.first();
        return tree$Node0 == null ? null : tree$Node0.getValue();
    }

    public Selection getSelection() {
        return this.selection;
    }

    public TreeStyle getStyle() {
        return this.style;
    }

    public float getYSpacing() {
        return this.ySpacing;
    }

    private void initialize() {
        com.badlogic.gdx.scenes.scene2d.ui.Tree.2 tree$20 = new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                Node tree$Node0 = Tree.this.getNodeAt(f1);
                if(tree$Node0 == null) {
                    return;
                }
                float f2 = this.getTouchDownY();
                if(tree$Node0 != Tree.this.getNodeAt(f2)) {
                    return;
                }
                if(Tree.this.selection.getMultiple() && Tree.this.selection.notEmpty() && UIUtils.shift()) {
                    if(Tree.this.rangeStart == null) {
                        Tree.this.rangeStart = tree$Node0;
                    }
                    Node tree$Node1 = Tree.this.rangeStart;
                    if(!UIUtils.ctrl()) {
                        Tree.this.selection.clear();
                    }
                    float f3 = tree$Node1.actor.getY();
                    float f4 = tree$Node0.actor.getY();
                    if(f3 > f4) {
                        Tree.this.selectNodes(Tree.this.rootNodes, f4, f3);
                    }
                    else {
                        Tree.this.selectNodes(Tree.this.rootNodes, f3, f4);
                        Tree.this.selection.items().orderedItems().reverse();
                    }
                    Tree.this.selection.fireChangeEvent();
                    Tree.this.rangeStart = tree$Node1;
                    return;
                }
                if(tree$Node0.children.size > 0 && (!Tree.this.selection.getMultiple() || !UIUtils.ctrl())) {
                    float f5 = tree$Node0.actor.getX();
                    if(tree$Node0.icon != null) {
                        f5 -= Tree.this.iconSpacingRight + tree$Node0.icon.getMinWidth();
                    }
                    if(f < f5) {
                        tree$Node0.setExpanded(!tree$Node0.expanded);
                        return;
                    }
                }
                if(!tree$Node0.isSelectable()) {
                    return;
                }
                Tree.this.selection.choose(tree$Node0);
                if(!Tree.this.selection.isEmpty()) {
                    Tree.this.rangeStart = tree$Node0;
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void enter(InputEvent inputEvent0, float f, float f1, int v, Actor actor0) {
                super.enter(inputEvent0, f, f1, v, actor0);
                Node tree$Node0 = Tree.this.getNodeAt(f1);
                Tree.this.setOverNode(tree$Node0);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void exit(InputEvent inputEvent0, float f, float f1, int v, @Null Actor actor0) {
                super.exit(inputEvent0, f, f1, v, actor0);
                if(actor0 == null || !actor0.isDescendantOf(Tree.this)) {
                    Tree.this.setOverNode(null);
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean mouseMoved(InputEvent inputEvent0, float f, float f1) {
                Node tree$Node0 = Tree.this.getNodeAt(f1);
                Tree.this.setOverNode(tree$Node0);
                return false;
            }
        };
        this.clickListener = tree$20;
        this.addListener(tree$20);
    }

    public void insert(int v, Node tree$Node0) {
        int v3;
        if(tree$Node0.parent == null) {
            int v1 = this.rootNodes.indexOf(tree$Node0, true);
            if(v1 != -1) {
                if(v1 == v) {
                    return;
                }
                if(v1 < v) {
                    --v;
                }
                this.rootNodes.removeIndex(v1);
                int v2 = tree$Node0.actor.getZIndex();
                if(v2 != -1) {
                    tree$Node0.removeFromTree(this, v2);
                }
            }
        }
        else {
            tree$Node0.parent.remove(tree$Node0);
            tree$Node0.parent = null;
        }
        this.rootNodes.insert(v, tree$Node0);
        if(v == 0) {
            v3 = 0;
        }
        else if(v < this.rootNodes.size - 1) {
            v3 = ((Node)this.rootNodes.get(v + 1)).actor.getZIndex();
        }
        else {
            Node tree$Node1 = (Node)this.rootNodes.get(v - 1);
            int v4 = tree$Node1.actor.getZIndex();
            v3 = tree$Node1.countActors() + v4;
        }
        tree$Node0.addToTree(this, v3);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void invalidate() {
        super.invalidate();
        this.sizeInvalid = true;
    }

    private float layout(Array array0, float f, float f1, float f2) {
        float f3 = this.ySpacing;
        float f4 = this.iconSpacingLeft;
        float f5 = this.iconSpacingRight + f4;
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            float f6 = f + f2;
            float f7 = tree$Node0.icon == null ? f6 + f4 : f6 + (tree$Node0.icon.getMinWidth() + f5);
            if(tree$Node0.actor instanceof Layout) {
                ((Layout)tree$Node0.actor).pack();
            }
            float f8 = f1 - tree$Node0.getHeight();
            tree$Node0.actor.setPosition(f7, f8);
            f1 = tree$Node0.expanded ? this.layout(tree$Node0.children, this.indentSpacing + f, f8 - f3, f2) : f8 - f3;
        }
        return f1;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
    public void layout() {
        if(this.sizeInvalid) {
            this.computeSize();
        }
        float f = this.paddingLeft;
        float f1 = this.getHeight() - this.ySpacing / 2.0f;
        float f2 = this.plusMinusWidth();
        this.layout(this.rootNodes, f, f1, f2);
    }

    private float plusMinusWidth() {
        float f = Math.max(this.style.plus.getMinWidth(), this.style.minus.getMinWidth());
        if(this.style.plusOver != null) {
            f = Math.max(f, this.style.plusOver.getMinWidth());
        }
        return this.style.minusOver == null ? f : Math.max(f, this.style.minusOver.getMinWidth());
    }

    public void remove(Node tree$Node0) {
        if(tree$Node0.parent != null) {
            tree$Node0.parent.remove(tree$Node0);
            return;
        }
        if(!this.rootNodes.removeValue(tree$Node0, true)) {
            return;
        }
        int v = tree$Node0.actor.getZIndex();
        if(v != -1) {
            tree$Node0.removeFromTree(this, v);
        }
    }

    public void restoreExpandedValues(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = this.findNode(array0.get(v1));
            if(tree$Node0 != null) {
                tree$Node0.setExpanded(true);
                tree$Node0.expandTo();
            }
        }
    }

    void selectNodes(Array array0, float f, float f1) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Node tree$Node0 = (Node)array0.get(v1);
            if(tree$Node0.actor.getY() < f) {
                break;
            }
            if(tree$Node0.isSelectable()) {
                if(tree$Node0.actor.getY() <= f1) {
                    this.selection.add(tree$Node0);
                }
                if(tree$Node0.expanded) {
                    this.selectNodes(tree$Node0.children, f, f1);
                }
            }
        }
    }

    public void setIconSpacing(float f, float f1) {
        this.iconSpacingLeft = f;
        this.iconSpacingRight = f1;
    }

    public void setIndentSpacing(float f) {
        this.indentSpacing = f;
    }

    public void setOverNode(@Null Node tree$Node0) {
        this.overNode = tree$Node0;
    }

    public void setPadding(float f) {
        this.paddingLeft = f;
        this.paddingRight = f;
    }

    public void setPadding(float f, float f1) {
        this.paddingLeft = f;
        this.paddingRight = f1;
    }

    public void setStyle(TreeStyle tree$TreeStyle0) {
        this.style = tree$TreeStyle0;
        if(this.indentSpacing == 0.0f) {
            this.indentSpacing = this.plusMinusWidth();
        }
    }

    public void setYSpacing(float f) {
        this.ySpacing = f;
    }

    public void updateRootNodes() {
        int v = this.rootNodes.size;
        for(int v2 = 0; v2 < v; ++v2) {
            Node tree$Node0 = (Node)this.rootNodes.get(v2);
            int v3 = tree$Node0.actor.getZIndex();
            if(v3 != -1) {
                tree$Node0.removeFromTree(this, v3);
            }
        }
        int v4 = this.rootNodes.size;
        int v5 = 0;
        for(int v1 = 0; v1 < v4; ++v1) {
            v5 += ((Node)this.rootNodes.get(v1)).addToTree(this, v5);
        }
    }
}

