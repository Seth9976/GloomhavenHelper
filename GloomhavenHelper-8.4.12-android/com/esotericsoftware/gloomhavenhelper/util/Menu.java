package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent.Type;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.esotericsoftware.gloomhavenhelper.App;

public class Menu extends Table {
    public boolean animate;
    private final TextureRegion arrow;
    public boolean arrowFlip;
    private float arrowX;
    private float arrowY;
    public boolean autoHide;
    private final TiledDrawable bg;
    private FloatAction bgAlpha;
    private float bgHeight;
    public float bgOffsetHeight;
    public float bgOffsetWidth;
    public float bgOffsetX;
    public float bgOffsetY;
    private float bgWidth;
    private float bgX;
    private float bgY;
    private final TiledDrawable bottom;
    private final TextureRegion corner;
    private final NinePatch dimCorners;
    private boolean hidden;
    final InputListener hideListener;
    private final TiledDrawable left;
    public static int menusShown;
    private Actor positionActor;
    private float positionHeight;
    private float positionWidth;
    private float positionX;
    private float positionY;
    public boolean preferRight;
    private final TiledDrawable right;
    public boolean showArrow;
    private final TiledDrawable top;
    private final TextureRegion vignette;
    private final TextureRegion white;

    public Menu() {
        super(App.skin);
        this.showArrow = true;
        this.animate = true;
        this.autoHide = true;
        this.hideListener = new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean handle(Event event0) {
                if(!(event0 instanceof InputEvent)) {
                    return false;
                }
                if(Menu.this.isAscendantOf(event0.getTarget())) {
                    return false;
                }
                if(((InputEvent)event0).getType() == Type.touchDown) {
                    Menu.this.hide();
                }
                event0.cancel();
                return true;
            }
        };
        this.vignette = App.skin.getRegion("menu/vignette");
        this.arrow = App.skin.getRegion("menu/arrow");
        this.corner = App.skin.getRegion("menu/corner");
        this.white = App.skin.getRegion("white");
        this.dimCorners = App.skin.getPatch("dim-corners");
        this.bg = App.skin.getTiledDrawable("menu/bg");
        this.top = App.skin.getTiledDrawable("menu/top");
        this.bottom = App.skin.getTiledDrawable("menu/bottom");
        this.left = App.skin.getTiledDrawable("menu/left");
        this.right = App.skin.getTiledDrawable("menu/right");
        this.setTransform(true);
        this.pad(12.0f);
    }

    protected boolean dir(boolean z) {
        return z;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        if(this.bgAlpha != null) {
            if(!this.hidden) {
                this.updatePosition();
            }
            batch0.setColor(0.0f, 0.0f, 0.0f, this.bgAlpha.getValue() * 0.5f * this.getColor().a);
            float f1 = App.stage.getWidth();
            batch0.draw(this.white, 0.0f, 0.0f, f1, this.bgY);
            float f2 = this.bgY + this.bgHeight;
            float f3 = App.stage.getWidth();
            float f4 = App.stage.getHeight();
            batch0.draw(this.white, 0.0f, f2, f3, f4 - (this.bgY + this.bgHeight));
            batch0.draw(this.white, 0.0f, this.bgY, this.bgX, this.bgHeight);
            float f5 = this.bgX + this.bgWidth;
            float f6 = this.bgY;
            float f7 = App.stage.getWidth();
            batch0.draw(this.white, f5, f6, f7 - (this.bgX + this.bgWidth), this.bgHeight);
            this.dimCorners.draw(batch0, this.bgX, this.bgY, this.bgWidth, this.bgHeight);
        }
        super.draw(batch0, f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    protected void drawBackground(Batch batch0, float f, float f1, float f2) {
        float f3 = this.getWidth();
        float f4 = this.getHeight();
        batch0.setColor(1.0f, 1.0f, 1.0f, this.getColor().a * f);
        this.bg.draw(batch0, f1 + 9.0f, f2 + 9.0f, f3 - 18.0f, f4 - 18.0f);
        batch0.draw(this.vignette, f1 + 9.0f, f2 + 9.0f, f3 - 18.0f, f4 - 18.0f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    protected void drawChildren(Batch batch0, float f) {
        super.drawChildren(batch0, f);
        float f1 = this.getWidth();
        float f2 = this.getHeight();
        batch0.setColor(1.0f, 1.0f, 1.0f, this.getColor().a * f);
        if(this.showArrow) {
            if(this.arrowFlip) {
                batch0.draw(this.arrow.getTexture(), this.arrowX, this.arrowY, ((float)this.arrow.getRegionWidth()), ((float)this.arrow.getRegionHeight()), this.arrow.getU2(), this.arrow.getV2(), this.arrow.getU(), this.arrow.getV());
            }
            else {
                batch0.draw(this.arrow, this.arrowX, this.arrowY);
            }
        }
        this.bottom.draw(batch0, 0.0f, 0.0f, f1, 21.0f);
        this.top.draw(batch0, 0.0f, f2 - 17.0f, f1, 17.0f);
        this.left.draw(batch0, 0.0f, 0.0f, 21.0f, f2);
        this.right.draw(batch0, f1 - 18.0f, 0.0f, 18.0f, f2);
        float f3 = f2 - 27.0f - 5.0f;
        batch0.draw(this.corner, -5.0f, f3);
        float f4 = this.corner.getU();
        float f5 = this.corner.getV();
        float f6 = this.corner.getU2();
        float f7 = this.corner.getV2();
        float f8 = f1 - 27.0f - 5.0f;
        batch0.draw(this.corner.getTexture(), f8, f3, 37.0f, 37.0f, f6, f7, f4, f5);
        batch0.draw(this.corner.getTexture(), f8, -5.0f, 37.0f, 37.0f, f6, f5, f4, f7);
        batch0.draw(this.corner.getTexture(), -5.0f, -5.0f, 37.0f, 37.0f, f4, f5, f6, f7);
    }

    public boolean hide() {
        Actor actor0 = this.positionActor;
        if(actor0 instanceof Button) {
            ((Button)actor0).setChecked(false);
        }
        App.stage.removeCaptureListener(this.hideListener);
        App.stage.setScrollFocus(App.gloom.rowsScroll);
        this.setTouchable(Touchable.disabled);
        if(this.hidden) {
            return false;
        }
        this.hidden = true;
        if(!this.hasParent()) {
            return false;
        }
        --Menu.menusShown;
        this.clearActions();
        if(this.animate) {
            this.addAction(Actions.sequence(Actions.parallel(Actions.fadeOut(0.35f, Interpolation.fastSlow), Actions.moveTo(this.getX(), this.getY() - 50.0f, 0.35f, Interpolation.fastSlow)), Actions.removeActor()));
            return true;
        }
        this.remove();
        return true;
    }

    public void setBackgroundOffset(float f, float f1, float f2, float f3) {
        this.bgOffsetX = f;
        this.bgOffsetY = f1;
        this.bgOffsetWidth = f2;
        this.bgOffsetHeight = f3;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setParent(Group group0) {
        if(group0 == null) {
            App.stage.removeCaptureListener(this.hideListener);
        }
        super.setParent(group0);
    }

    public void setPosition(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        this.positionX = f;
        this.positionY = f1;
        this.positionWidth = f2;
        this.positionHeight = f3;
        this.bgX = f4;
        this.bgY = f5;
        this.bgWidth = f6;
        this.bgHeight = f7;
    }

    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        this.setTouchable(Touchable.enabled);
        this.pack();
        this.positionActor = actor0;
        this.positionX = f;
        this.positionY = f1;
        this.positionWidth = f2;
        this.positionHeight = f3;
        this.preferRight = z;
        this.updatePosition();
        if(actor0 instanceof Button) {
            ((Button)actor0).setChecked(true);
        }
        if(!this.hasParent()) {
            this.hidden = true;
        }
        if(!this.hidden) {
            return false;
        }
        this.hidden = false;
        this.clearActions();
        App.stage.mouseMoved(0x7FFFFFFF, 0x7FFFFFFF);
        App.stage.act(0.0f);
        App.stage.setKeyboardFocus(this);
        if(this.autoHide) {
            App.stage.addCaptureListener(this.hideListener);
        }
        App.stage.addActor(this);
        this.getColor().a = 1.0f;
        if(this.animate) {
            this.setScale(0.0f, 0.0f);
            this.addAction(Actions.scaleTo(1.0f, 1.0f, 0.2f, Interpolation.fastSlow));
            FloatAction floatAction0 = new FloatAction(0.0f, 1.0f, 0.2f, Interpolation.slowFast);
            this.bgAlpha = floatAction0;
            this.addAction(floatAction0);
        }
        else {
            this.setScale(1.0f, 1.0f);
            this.bgAlpha = new FloatAction(1.0f, 1.0f, 0.0f);
            this.bgAlpha.act(0.0f);
        }
        ++Menu.menusShown;
        return true;
    }

    public boolean show(Menu menu0) {
        boolean z = this.show(menu0.positionActor, menu0.positionX, menu0.positionY, menu0.positionWidth, menu0.positionHeight, menu0.preferRight);
        this.bgX = menu0.bgX;
        this.bgY = menu0.bgY;
        this.bgWidth = menu0.bgWidth;
        this.bgHeight = menu0.bgHeight;
        this.setBackgroundOffset(menu0.bgOffsetX, menu0.bgOffsetY, menu0.bgOffsetWidth, menu0.bgOffsetHeight);
        return z;
    }

    protected void updatePosition() {
        float f7;
        float f6;
        float f5;
        float f4;
        float f = this.getPrefWidth();
        float f1 = this.getPrefHeight();
        if(f > App.stage.getWidth() - 18.0f) {
            f = App.stage.getWidth() - 18.0f;
        }
        if(f1 > App.stage.getHeight() - 18.0f) {
            f1 = App.stage.getHeight() - 18.0f;
        }
        if(this.positionActor == null || this.positionActor.getParent() == null) {
            f4 = this.positionX;
            f5 = this.positionY;
            f6 = this.positionWidth;
            f7 = this.positionHeight;
        }
        else {
            float f2 = this.positionActor.getX();
            float f3 = this.positionActor.getY();
            Actor actor0 = this.positionActor;
            if(actor0 instanceof HasAnimator) {
                Animator animator0 = ((HasAnimator)actor0).getAnimator();
                if(animator0.animating) {
                    f2 = animator0.target.x;
                    f3 = animator0.target.y;
                }
            }
            Vector2 vector20 = this.positionActor.getParent().localToStageCoordinates(App.v2.set(this.bgOffsetX + f2, this.bgOffsetY + f3));
            this.bgX = vector20.x;
            this.bgY = vector20.y;
            this.positionActor.getParent().localToStageCoordinates(vector20.set(this.positionActor.getWidth() + f2 + this.bgOffsetWidth, this.positionActor.getHeight() + f3 + this.bgOffsetHeight)).sub(this.bgX, this.bgY);
            this.bgWidth = vector20.x;
            this.bgHeight = vector20.y;
            Vector2 vector21 = this.positionActor.getParent().localToStageCoordinates(App.v2.set(this.positionX + f2, this.positionY + f3));
            f4 = vector21.x;
            f5 = vector21.y;
            Vector2 vector22 = this.positionActor.getParent().localToStageCoordinates(App.v2.set(f2 + this.positionActor.getWidth() + this.positionWidth, f3 + this.positionActor.getHeight() + this.positionHeight)).sub(f4, f5);
            f6 = vector22.x;
            f7 = vector22.y;
        }
        float f8 = f5 + ((float)Math.round(f7 / 2.0f));
        float f9 = f4 - 44.0f - f;
        float f10 = App.stage.getWidth() - f4 - f6 - 44.0f - f;
        boolean z = true;
        if(f9 >= 0.0f || f10 >= 0.0f) {
            if((!this.preferRight || f10 < 0.0f) && (this.preferRight || f9 >= 0.0f)) {
                z = false;
            }
        }
        else if(f10 <= f9) {
            z = false;
        }
        boolean z1 = this.dir(z);
        this.arrowFlip = !z1;
        this.arrowY = f8 - 50.0f;
        this.arrowX = MathUtils.clamp(this.arrowX, (z1 ? 0.0f : f + 10.0f), App.stage.getWidth() - 44.0f);
        this.arrowY = MathUtils.clamp(this.arrowY, -14.0f, App.stage.getHeight() - 10.0f);
        float f11 = this.arrowY + 117.0f - f1;
        float f12 = MathUtils.clamp((z1 ? this.arrowX + 44.0f : this.arrowX - f), 10.0f, App.stage.getWidth() - f - 10.0f);
        float f13 = MathUtils.clamp(f11, 10.0f, App.stage.getHeight() - f1 - 10.0f);
        this.arrowX = z1 ? -44.0f : f;
        this.arrowY -= f13;
        if(this.arrowY < 10.0f) {
            this.arrowY = 10.0f;
        }
        this.setBounds(f12, f13, f, f1);
        this.setOrigin((z1 ? this.arrowX : this.arrowX + 44.0f), this.arrowY + 50.0f);
        this.arrowY = MathUtils.clamp(this.arrowY, 5.0f, f1 - 87.0f);
    }
}

