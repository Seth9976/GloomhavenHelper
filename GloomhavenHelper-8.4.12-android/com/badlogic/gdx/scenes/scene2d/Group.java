package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.Cullable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.SnapshotArray;

public class Group extends Actor implements Cullable {
    final SnapshotArray children;
    private final Matrix4 computedTransform;
    @Null
    private Rectangle cullingArea;
    private final Matrix4 oldTransform;
    private static final Vector2 tmp;
    boolean transform;
    private final Affine2 worldTransform;

    static {
        Group.tmp = new Vector2();
    }

    public Group() {
        this.children = new SnapshotArray(true, 4, Actor.class);
        this.worldTransform = new Affine2();
        this.computedTransform = new Matrix4();
        this.oldTransform = new Matrix4();
        this.transform = true;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void act(float f) {
        super.act(f);
        Actor[] arr_actor = (Actor[])this.children.begin();
        int v = this.children.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_actor[v1].act(f);
        }
        this.children.end();
    }

    public void addActor(Actor actor0) {
        if(actor0.parent != null) {
            if(actor0.parent == this) {
                return;
            }
            actor0.parent.removeActor(actor0, false);
        }
        this.children.add(actor0);
        actor0.setParent(this);
        actor0.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorAfter(Actor actor0, Actor actor1) {
        if(actor1.parent != null) {
            if(actor1.parent == this) {
                return;
            }
            actor1.parent.removeActor(actor1, false);
        }
        int v = this.children.indexOf(actor0, true);
        if(v == this.children.size || v == -1) {
            this.children.add(actor1);
        }
        else {
            this.children.insert(v + 1, actor1);
        }
        actor1.setParent(this);
        actor1.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorAt(int v, Actor actor0) {
        if(actor0.parent != null) {
            if(actor0.parent == this) {
                return;
            }
            actor0.parent.removeActor(actor0, false);
        }
        if(v >= this.children.size) {
            this.children.add(actor0);
        }
        else {
            this.children.insert(v, actor0);
        }
        actor0.setParent(this);
        actor0.setStage(this.getStage());
        this.childrenChanged();
    }

    public void addActorBefore(Actor actor0, Actor actor1) {
        if(actor1.parent != null) {
            if(actor1.parent == this) {
                return;
            }
            actor1.parent.removeActor(actor1, false);
        }
        int v = this.children.indexOf(actor0, true);
        this.children.insert(v, actor1);
        actor1.setParent(this);
        actor1.setStage(this.getStage());
        this.childrenChanged();
    }

    protected void applyTransform(Batch batch0, Matrix4 matrix40) {
        Matrix4 matrix41 = batch0.getTransformMatrix();
        this.oldTransform.set(matrix41);
        batch0.setTransformMatrix(matrix40);
    }

    protected void applyTransform(ShapeRenderer shapeRenderer0, Matrix4 matrix40) {
        this.oldTransform.set(shapeRenderer0.getTransformMatrix());
        shapeRenderer0.setTransformMatrix(matrix40);
        shapeRenderer0.flush();
    }

    protected void childrenChanged() {
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void clear() {
        super.clear();
        this.clearChildren(true);
    }

    public void clear(boolean z) {
        super.clear();
        this.clearChildren(z);
    }

    public void clearChildren() {
        this.clearChildren(true);
    }

    public void clearChildren(boolean z) {
        Actor[] arr_actor = (Actor[])this.children.begin();
        int v = this.children.size;
        for(int v1 = 0; v1 < v; ++v1) {
            Actor actor0 = arr_actor[v1];
            if(z) {
                Stage stage0 = this.getStage();
                if(stage0 != null) {
                    stage0.unfocus(actor0);
                }
            }
            actor0.setStage(null);
            actor0.setParent(null);
        }
        this.children.end();
        this.children.clear();
        this.childrenChanged();
    }

    protected Matrix4 computeTransform() {
        Affine2 affine20 = this.worldTransform;
        float f = this.originX;
        float f1 = this.originY;
        affine20.setToTrnRotScl(this.x + f, this.y + f1, this.rotation, this.scaleX, this.scaleY);
        if(f != 0.0f || f1 != 0.0f) {
            affine20.translate(-f, -f1);
        }
        Group group0;
        for(group0 = this.parent; group0 != null && !group0.transform; group0 = group0.parent) {
        }
        if(group0 != null) {
            affine20.preMul(group0.worldTransform);
        }
        this.computedTransform.set(affine20);
        return this.computedTransform;
    }

    public Group debugAll() {
        this.setDebug(true, true);
        return this;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void draw(Batch batch0, float f) {
        if(this.transform) {
            this.applyTransform(batch0, this.computeTransform());
        }
        this.drawChildren(batch0, f);
        if(this.transform) {
            this.resetTransform(batch0);
        }
    }

    protected void drawChildren(Batch batch0, float f) {
        float f8;
        float f1 = this.color.a * f;
        SnapshotArray snapshotArray0 = this.children;
        Actor[] arr_actor = (Actor[])snapshotArray0.begin();
        Rectangle rectangle0 = this.cullingArea;
        int v = 0;
        if(rectangle0 != null) {
            float f2 = rectangle0.x;
            float f3 = rectangle0.width + f2;
            float f4 = rectangle0.y;
            float f5 = rectangle0.height + f4;
            if(this.transform) {
                int v1 = snapshotArray0.size;
                while(v < v1) {
                    Actor actor0 = arr_actor[v];
                    if(actor0.isVisible() && (actor0.x <= f3 && actor0.y <= f5 && actor0.x + actor0.width >= f2 && actor0.y + actor0.height >= f4)) {
                        actor0.draw(batch0, f1);
                    }
                    ++v;
                }
            }
            else {
                float f6 = this.x;
                float f7 = this.y;
                this.x = 0.0f;
                this.y = 0.0f;
                int v2 = snapshotArray0.size;
                while(v < v2) {
                    Actor actor1 = arr_actor[v];
                    if(actor1.isVisible()) {
                        float f9 = actor1.x;
                        float f10 = actor1.y;
                        if(f9 > f3 || f10 > f5) {
                            f8 = f5;
                        }
                        else {
                            f8 = f5;
                            if(actor1.width + f9 >= f2 && actor1.height + f10 >= f4) {
                                actor1.x = f9 + f6;
                                actor1.y = f10 + f7;
                                actor1.draw(batch0, f1);
                                actor1.x = f9;
                                actor1.y = f10;
                            }
                        }
                    }
                    else {
                        f8 = f5;
                    }
                    ++v;
                    f5 = f8;
                }
                this.x = f6;
                this.y = f7;
            }
        }
        else if(this.transform) {
            int v3 = snapshotArray0.size;
            while(v < v3) {
                Actor actor2 = arr_actor[v];
                if(actor2.isVisible()) {
                    actor2.draw(batch0, f1);
                }
                ++v;
            }
        }
        else {
            float f11 = this.x;
            float f12 = this.y;
            this.x = 0.0f;
            this.y = 0.0f;
            int v4 = snapshotArray0.size;
            while(v < v4) {
                Actor actor3 = arr_actor[v];
                if(actor3.isVisible()) {
                    float f13 = actor3.x;
                    float f14 = actor3.y;
                    actor3.x = f13 + f11;
                    actor3.y = f14 + f12;
                    actor3.draw(batch0, f1);
                    actor3.x = f13;
                    actor3.y = f14;
                }
                ++v;
            }
            this.x = f11;
            this.y = f12;
        }
        snapshotArray0.end();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public void drawDebug(ShapeRenderer shapeRenderer0) {
        this.drawDebugBounds(shapeRenderer0);
        if(this.transform) {
            this.applyTransform(shapeRenderer0, this.computeTransform());
        }
        this.drawDebugChildren(shapeRenderer0);
        if(this.transform) {
            this.resetTransform(shapeRenderer0);
        }
    }

    protected void drawDebugChildren(ShapeRenderer shapeRenderer0) {
        SnapshotArray snapshotArray0 = this.children;
        Actor[] arr_actor = (Actor[])snapshotArray0.begin();
        int v = 0;
        if(this.transform) {
            int v1 = snapshotArray0.size;
            while(v < v1) {
                Actor actor0 = arr_actor[v];
                if(actor0.isVisible() && (actor0.getDebug() || actor0 instanceof Group)) {
                    actor0.drawDebug(shapeRenderer0);
                }
                ++v;
            }
            shapeRenderer0.flush();
        }
        else {
            float f = this.x;
            float f1 = this.y;
            this.x = 0.0f;
            this.y = 0.0f;
            int v2 = snapshotArray0.size;
            while(v < v2) {
                Actor actor1 = arr_actor[v];
                if(actor1.isVisible() && (actor1.getDebug() || actor1 instanceof Group)) {
                    float f2 = actor1.x;
                    float f3 = actor1.y;
                    actor1.x = f2 + f;
                    actor1.y = f3 + f1;
                    actor1.drawDebug(shapeRenderer0);
                    actor1.x = f2;
                    actor1.y = f3;
                }
                ++v;
            }
            this.x = f;
            this.y = f1;
        }
        snapshotArray0.end();
    }

    @Null
    public Actor findActor(String s) {
        SnapshotArray snapshotArray0 = this.children;
        int v = snapshotArray0.size;
        for(int v2 = 0; v2 < v; ++v2) {
            if(s.equals(((Actor)snapshotArray0.get(v2)).getName())) {
                return (Actor)snapshotArray0.get(v2);
            }
        }
        int v3 = snapshotArray0.size;
        for(int v1 = 0; v1 < v3; ++v1) {
            Actor actor0 = (Actor)snapshotArray0.get(v1);
            if(actor0 instanceof Group) {
                Actor actor1 = ((Group)actor0).findActor(s);
                if(actor1 != null) {
                    return actor1;
                }
            }
        }
        return null;
    }

    public Actor getChild(int v) {
        return (Actor)this.children.get(v);
    }

    public SnapshotArray getChildren() {
        return this.children;
    }

    @Null
    public Rectangle getCullingArea() {
        return this.cullingArea;
    }

    public boolean hasChildren() {
        return this.children.size > 0;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    @Null
    public Actor hit(float f, float f1, boolean z) {
        if(z && this.getTouchable() == Touchable.disabled) {
            return null;
        }
        if(!this.isVisible()) {
            return null;
        }
        Vector2 vector20 = Group.tmp;
        Actor[] arr_actor = (Actor[])this.children.items;
        for(int v = this.children.size - 1; v >= 0; --v) {
            Actor actor0 = arr_actor[v];
            actor0.parentToLocalCoordinates(vector20.set(f, f1));
            Actor actor1 = actor0.hit(vector20.x, vector20.y, z);
            if(actor1 != null) {
                return actor1;
            }
        }
        return super.hit(f, f1, z);
    }

    public boolean isTransform() {
        return this.transform;
    }

    public Vector2 localToDescendantCoordinates(Actor actor0, Vector2 vector20) {
        Group group0 = actor0.parent;
        if(group0 == null) {
            throw new IllegalArgumentException("Child is not a descendant: " + actor0);
        }
        if(group0 != this) {
            this.localToDescendantCoordinates(group0, vector20);
        }
        actor0.parentToLocalCoordinates(vector20);
        return vector20;
    }

    public boolean removeActor(Actor actor0) {
        return this.removeActor(actor0, true);
    }

    public boolean removeActor(Actor actor0, boolean z) {
        int v = this.children.indexOf(actor0, true);
        if(v == -1) {
            return false;
        }
        this.removeActorAt(v, z);
        return true;
    }

    public Actor removeActorAt(int v, boolean z) {
        Actor actor0 = (Actor)this.children.removeIndex(v);
        if(z) {
            Stage stage0 = this.getStage();
            if(stage0 != null) {
                stage0.unfocus(actor0);
            }
        }
        actor0.setParent(null);
        actor0.setStage(null);
        this.childrenChanged();
        return actor0;
    }

    protected void resetTransform(Batch batch0) {
        batch0.setTransformMatrix(this.oldTransform);
    }

    protected void resetTransform(ShapeRenderer shapeRenderer0) {
        shapeRenderer0.setTransformMatrix(this.oldTransform);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.utils.Cullable
    public void setCullingArea(@Null Rectangle rectangle0) {
        this.cullingArea = rectangle0;
    }

    public void setDebug(boolean z, boolean z1) {
        this.setDebug(z);
        if(z1) {
            for(Object object0: this.children) {
                Actor actor0 = (Actor)object0;
                if(actor0 instanceof Group) {
                    ((Group)actor0).setDebug(z, true);
                }
                else {
                    actor0.setDebug(z);
                }
            }
        }
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setStage(Stage stage0) {
        super.setStage(stage0);
        Actor[] arr_actor = (Actor[])this.children.items;
        int v = this.children.size;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_actor[v1].setStage(stage0);
        }
    }

    public void setTransform(boolean z) {
        this.transform = z;
    }

    public boolean swapActor(int v, int v1) {
        int v2 = this.children.size;
        if(v >= 0 && v < v2 && v1 >= 0 && v1 < v2) {
            this.children.swap(v, v1);
            return true;
        }
        return false;
    }

    public boolean swapActor(Actor actor0, Actor actor1) {
        int v = this.children.indexOf(actor0, true);
        int v1 = this.children.indexOf(actor1, true);
        if(v != -1 && v1 != -1) {
            this.children.swap(v, v1);
            return true;
        }
        return false;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        this.toString(stringBuilder0, 1);
        stringBuilder0.setLength(stringBuilder0.length() - 1);
        return stringBuilder0.toString();
    }

    void toString(StringBuilder stringBuilder0, int v) {
        stringBuilder0.append("Actor");
        stringBuilder0.append('\n');
        Actor[] arr_actor = (Actor[])this.children.begin();
        int v1 = this.children.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            for(int v3 = 0; v3 < v; ++v3) {
                stringBuilder0.append("|  ");
            }
            Actor actor0 = arr_actor[v2];
            if(actor0 instanceof Group) {
                ((Group)actor0).toString(stringBuilder0, v + 1);
            }
            else {
                stringBuilder0.append(actor0);
                stringBuilder0.append('\n');
            }
        }
        this.children.end();
    }
}

