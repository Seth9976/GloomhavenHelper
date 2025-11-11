package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;

public class TooltipManager {
    public boolean animations;
    public float edgeDistance;
    public boolean enabled;
    private static Files files;
    public float initialTime;
    private static TooltipManager instance;
    public float maxWidth;
    public float offsetX;
    public float offsetY;
    final Task resetTask;
    public float resetTime;
    final Task showTask;
    Tooltip showTooltip;
    final Array shown;
    public float subsequentTime;
    float time;

    public TooltipManager() {
        this.initialTime = 2.0f;
        this.subsequentTime = 0.0f;
        this.resetTime = 1.5f;
        this.enabled = true;
        this.animations = true;
        this.maxWidth = 2147483648.0f;
        this.offsetX = 15.0f;
        this.offsetY = 19.0f;
        this.edgeDistance = 7.0f;
        this.shown = new Array();
        this.time = this.initialTime;
        this.resetTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                TooltipManager.this.time = TooltipManager.this.initialTime;
            }
        };
        this.showTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                if(TooltipManager.this.showTooltip != null && TooltipManager.this.showTooltip.targetActor != null) {
                    Stage stage0 = TooltipManager.this.showTooltip.targetActor.getStage();
                    if(stage0 == null) {
                        return;
                    }
                    stage0.addActor(TooltipManager.this.showTooltip.container);
                    TooltipManager.this.showTooltip.container.toFront();
                    TooltipManager.this.shown.add(TooltipManager.this.showTooltip);
                    TooltipManager.this.showTooltip.container.clearActions();
                    TooltipManager.this.showAction(TooltipManager.this.showTooltip);
                    if(!TooltipManager.this.showTooltip.instant) {
                        TooltipManager.this.time = TooltipManager.this.subsequentTime;
                        TooltipManager.this.resetTask.cancel();
                    }
                }
            }
        };
    }

    public void enter(Tooltip tooltip0) {
        this.showTooltip = tooltip0;
        this.showTask.cancel();
        if(this.enabled || tooltip0.always) {
            if(this.time != 0.0f && !tooltip0.instant) {
                Timer.schedule(this.showTask, this.time);
                return;
            }
            this.showTask.run();
        }
    }

    public static TooltipManager getInstance() {
        if(TooltipManager.files == null || TooltipManager.files != Gdx.files) {
            TooltipManager.files = Gdx.files;
            TooltipManager.instance = new TooltipManager();
        }
        return TooltipManager.instance;
    }

    public void hide(Tooltip tooltip0) {
        this.showTooltip = null;
        this.showTask.cancel();
        if(tooltip0.container.hasParent()) {
            this.shown.removeValue(tooltip0, true);
            this.hideAction(tooltip0);
            this.resetTask.cancel();
            Timer.schedule(this.resetTask, this.resetTime);
        }
    }

    protected void hideAction(Tooltip tooltip0) {
        SequenceAction sequenceAction0 = Actions.sequence(Actions.parallel(Actions.alpha(0.2f, 0.2f, Interpolation.fade), Actions.scaleTo(0.05f, 0.05f, 0.2f, Interpolation.fade)), Actions.removeActor());
        tooltip0.container.addAction(sequenceAction0);
    }

    public void hideAll() {
        this.resetTask.cancel();
        this.showTask.cancel();
        this.time = this.initialTime;
        this.showTooltip = null;
        for(Object object0: this.shown) {
            ((Tooltip)object0).hide();
        }
        this.shown.clear();
    }

    public void instant() {
        this.time = 0.0f;
        this.showTask.run();
        this.showTask.cancel();
    }

    protected void showAction(Tooltip tooltip0) {
        float f;
        if(!this.animations) {
            f = 0.1f;
        }
        else if(this.time > 0.0f) {
            f = 0.5f;
        }
        else {
            f = 0.15f;
        }
        tooltip0.container.setTransform(true);
        tooltip0.container.getColor().a = 0.2f;
        tooltip0.container.setScale(0.05f);
        ParallelAction parallelAction0 = Actions.parallel(Actions.fadeIn(f, Interpolation.fade), Actions.scaleTo(1.0f, 1.0f, f, Interpolation.fade));
        tooltip0.container.addAction(parallelAction0);
    }

    public void touchDown(Tooltip tooltip0) {
        this.showTask.cancel();
        if(tooltip0.container.remove()) {
            this.resetTask.cancel();
        }
        this.resetTask.run();
        if(this.enabled || tooltip0.always) {
            this.showTooltip = tooltip0;
            Timer.schedule(this.showTask, this.time);
        }
    }
}

