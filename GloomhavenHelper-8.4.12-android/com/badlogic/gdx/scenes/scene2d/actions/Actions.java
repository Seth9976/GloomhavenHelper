package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Actions {
    public static Action action(Class class0) {
        Pool pool0 = Pools.get(class0);
        Action action0 = (Action)pool0.obtain();
        action0.setPool(pool0);
        return action0;
    }

    public static AddAction addAction(Action action0) {
        AddAction addAction0 = (AddAction)Actions.action(AddAction.class);
        addAction0.setAction(action0);
        return addAction0;
    }

    public static AddAction addAction(Action action0, Actor actor0) {
        AddAction addAction0 = (AddAction)Actions.action(AddAction.class);
        addAction0.setTarget(actor0);
        addAction0.setAction(action0);
        return addAction0;
    }

    public static AddListenerAction addListener(EventListener eventListener0, boolean z) {
        AddListenerAction addListenerAction0 = (AddListenerAction)Actions.action(AddListenerAction.class);
        addListenerAction0.setListener(eventListener0);
        addListenerAction0.setCapture(z);
        return addListenerAction0;
    }

    public static AddListenerAction addListener(EventListener eventListener0, boolean z, Actor actor0) {
        AddListenerAction addListenerAction0 = (AddListenerAction)Actions.action(AddListenerAction.class);
        addListenerAction0.setTarget(actor0);
        addListenerAction0.setListener(eventListener0);
        addListenerAction0.setCapture(z);
        return addListenerAction0;
    }

    public static AfterAction after(Action action0) {
        AfterAction afterAction0 = (AfterAction)Actions.action(AfterAction.class);
        afterAction0.setAction(action0);
        return afterAction0;
    }

    public static AlphaAction alpha(float f) {
        return Actions.alpha(f, 0.0f, null);
    }

    public static AlphaAction alpha(float f, float f1) {
        return Actions.alpha(f, f1, null);
    }

    public static AlphaAction alpha(float f, float f1, @Null Interpolation interpolation0) {
        AlphaAction alphaAction0 = (AlphaAction)Actions.action(AlphaAction.class);
        alphaAction0.setAlpha(f);
        alphaAction0.setDuration(f1);
        alphaAction0.setInterpolation(interpolation0);
        return alphaAction0;
    }

    public static ColorAction color(Color color0) {
        return Actions.color(color0, 0.0f, null);
    }

    public static ColorAction color(Color color0, float f) {
        return Actions.color(color0, f, null);
    }

    public static ColorAction color(Color color0, float f, @Null Interpolation interpolation0) {
        ColorAction colorAction0 = (ColorAction)Actions.action(ColorAction.class);
        colorAction0.setEndColor(color0);
        colorAction0.setDuration(f);
        colorAction0.setInterpolation(interpolation0);
        return colorAction0;
    }

    public static DelayAction delay(float f) {
        DelayAction delayAction0 = (DelayAction)Actions.action(DelayAction.class);
        delayAction0.setDuration(f);
        return delayAction0;
    }

    public static DelayAction delay(float f, Action action0) {
        DelayAction delayAction0 = (DelayAction)Actions.action(DelayAction.class);
        delayAction0.setDuration(f);
        delayAction0.setAction(action0);
        return delayAction0;
    }

    public static AlphaAction fadeIn(float f) {
        return Actions.alpha(1.0f, f, null);
    }

    public static AlphaAction fadeIn(float f, @Null Interpolation interpolation0) {
        AlphaAction alphaAction0 = (AlphaAction)Actions.action(AlphaAction.class);
        alphaAction0.setAlpha(1.0f);
        alphaAction0.setDuration(f);
        alphaAction0.setInterpolation(interpolation0);
        return alphaAction0;
    }

    public static AlphaAction fadeOut(float f) {
        return Actions.alpha(0.0f, f, null);
    }

    public static AlphaAction fadeOut(float f, @Null Interpolation interpolation0) {
        AlphaAction alphaAction0 = (AlphaAction)Actions.action(AlphaAction.class);
        alphaAction0.setAlpha(0.0f);
        alphaAction0.setDuration(f);
        alphaAction0.setInterpolation(interpolation0);
        return alphaAction0;
    }

    public static RepeatAction forever(Action action0) {
        RepeatAction repeatAction0 = (RepeatAction)Actions.action(RepeatAction.class);
        repeatAction0.setCount(-1);
        repeatAction0.setAction(action0);
        return repeatAction0;
    }

    public static VisibleAction hide() {
        return Actions.visible(false);
    }

    public static LayoutAction layout(boolean z) {
        LayoutAction layoutAction0 = (LayoutAction)Actions.action(LayoutAction.class);
        layoutAction0.setLayoutEnabled(z);
        return layoutAction0;
    }

    public static MoveByAction moveBy(float f, float f1) {
        return Actions.moveBy(f, f1, 0.0f, null);
    }

    public static MoveByAction moveBy(float f, float f1, float f2) {
        return Actions.moveBy(f, f1, f2, null);
    }

    public static MoveByAction moveBy(float f, float f1, float f2, @Null Interpolation interpolation0) {
        MoveByAction moveByAction0 = (MoveByAction)Actions.action(MoveByAction.class);
        moveByAction0.setAmount(f, f1);
        moveByAction0.setDuration(f2);
        moveByAction0.setInterpolation(interpolation0);
        return moveByAction0;
    }

    public static MoveToAction moveTo(float f, float f1) {
        return Actions.moveTo(f, f1, 0.0f, null);
    }

    public static MoveToAction moveTo(float f, float f1, float f2) {
        return Actions.moveTo(f, f1, f2, null);
    }

    public static MoveToAction moveTo(float f, float f1, float f2, @Null Interpolation interpolation0) {
        MoveToAction moveToAction0 = (MoveToAction)Actions.action(MoveToAction.class);
        moveToAction0.setPosition(f, f1);
        moveToAction0.setDuration(f2);
        moveToAction0.setInterpolation(interpolation0);
        return moveToAction0;
    }

    public static MoveToAction moveToAligned(float f, float f1, int v) {
        return Actions.moveToAligned(f, f1, v, 0.0f, null);
    }

    public static MoveToAction moveToAligned(float f, float f1, int v, float f2) {
        return Actions.moveToAligned(f, f1, v, f2, null);
    }

    public static MoveToAction moveToAligned(float f, float f1, int v, float f2, @Null Interpolation interpolation0) {
        MoveToAction moveToAction0 = (MoveToAction)Actions.action(MoveToAction.class);
        moveToAction0.setPosition(f, f1, v);
        moveToAction0.setDuration(f2);
        moveToAction0.setInterpolation(interpolation0);
        return moveToAction0;
    }

    public static ParallelAction parallel() {
        return (ParallelAction)Actions.action(ParallelAction.class);
    }

    public static ParallelAction parallel(Action action0) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        parallelAction0.addAction(action0);
        return parallelAction0;
    }

    public static ParallelAction parallel(Action action0, Action action1) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        parallelAction0.addAction(action0);
        parallelAction0.addAction(action1);
        return parallelAction0;
    }

    public static ParallelAction parallel(Action action0, Action action1, Action action2) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        parallelAction0.addAction(action0);
        parallelAction0.addAction(action1);
        parallelAction0.addAction(action2);
        return parallelAction0;
    }

    public static ParallelAction parallel(Action action0, Action action1, Action action2, Action action3) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        parallelAction0.addAction(action0);
        parallelAction0.addAction(action1);
        parallelAction0.addAction(action2);
        parallelAction0.addAction(action3);
        return parallelAction0;
    }

    public static ParallelAction parallel(Action action0, Action action1, Action action2, Action action3, Action action4) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        parallelAction0.addAction(action0);
        parallelAction0.addAction(action1);
        parallelAction0.addAction(action2);
        parallelAction0.addAction(action3);
        parallelAction0.addAction(action4);
        return parallelAction0;
    }

    public static ParallelAction parallel(Action[] arr_action) {
        ParallelAction parallelAction0 = (ParallelAction)Actions.action(ParallelAction.class);
        for(int v = 0; v < arr_action.length; ++v) {
            parallelAction0.addAction(arr_action[v]);
        }
        return parallelAction0;
    }

    public static RemoveAction removeAction(Action action0) {
        RemoveAction removeAction0 = (RemoveAction)Actions.action(RemoveAction.class);
        removeAction0.setAction(action0);
        return removeAction0;
    }

    public static RemoveAction removeAction(Action action0, Actor actor0) {
        RemoveAction removeAction0 = (RemoveAction)Actions.action(RemoveAction.class);
        removeAction0.setTarget(actor0);
        removeAction0.setAction(action0);
        return removeAction0;
    }

    public static RemoveActorAction removeActor() {
        return (RemoveActorAction)Actions.action(RemoveActorAction.class);
    }

    public static RemoveActorAction removeActor(Actor actor0) {
        RemoveActorAction removeActorAction0 = (RemoveActorAction)Actions.action(RemoveActorAction.class);
        removeActorAction0.setTarget(actor0);
        return removeActorAction0;
    }

    public static RemoveListenerAction removeListener(EventListener eventListener0, boolean z) {
        RemoveListenerAction removeListenerAction0 = (RemoveListenerAction)Actions.action(RemoveListenerAction.class);
        removeListenerAction0.setListener(eventListener0);
        removeListenerAction0.setCapture(z);
        return removeListenerAction0;
    }

    public static RemoveListenerAction removeListener(EventListener eventListener0, boolean z, Actor actor0) {
        RemoveListenerAction removeListenerAction0 = (RemoveListenerAction)Actions.action(RemoveListenerAction.class);
        removeListenerAction0.setTarget(actor0);
        removeListenerAction0.setListener(eventListener0);
        removeListenerAction0.setCapture(z);
        return removeListenerAction0;
    }

    public static RepeatAction repeat(int v, Action action0) {
        RepeatAction repeatAction0 = (RepeatAction)Actions.action(RepeatAction.class);
        repeatAction0.setCount(v);
        repeatAction0.setAction(action0);
        return repeatAction0;
    }

    public static RotateByAction rotateBy(float f) {
        return Actions.rotateBy(f, 0.0f, null);
    }

    public static RotateByAction rotateBy(float f, float f1) {
        return Actions.rotateBy(f, f1, null);
    }

    public static RotateByAction rotateBy(float f, float f1, @Null Interpolation interpolation0) {
        RotateByAction rotateByAction0 = (RotateByAction)Actions.action(RotateByAction.class);
        rotateByAction0.setAmount(f);
        rotateByAction0.setDuration(f1);
        rotateByAction0.setInterpolation(interpolation0);
        return rotateByAction0;
    }

    public static RotateToAction rotateTo(float f) {
        return Actions.rotateTo(f, 0.0f, null);
    }

    public static RotateToAction rotateTo(float f, float f1) {
        return Actions.rotateTo(f, f1, null);
    }

    public static RotateToAction rotateTo(float f, float f1, @Null Interpolation interpolation0) {
        RotateToAction rotateToAction0 = (RotateToAction)Actions.action(RotateToAction.class);
        rotateToAction0.setRotation(f);
        rotateToAction0.setDuration(f1);
        rotateToAction0.setInterpolation(interpolation0);
        return rotateToAction0;
    }

    public static RunnableAction run(Runnable runnable0) {
        RunnableAction runnableAction0 = (RunnableAction)Actions.action(RunnableAction.class);
        runnableAction0.setRunnable(runnable0);
        return runnableAction0;
    }

    public static ScaleByAction scaleBy(float f, float f1) {
        return Actions.scaleBy(f, f1, 0.0f, null);
    }

    public static ScaleByAction scaleBy(float f, float f1, float f2) {
        return Actions.scaleBy(f, f1, f2, null);
    }

    public static ScaleByAction scaleBy(float f, float f1, float f2, @Null Interpolation interpolation0) {
        ScaleByAction scaleByAction0 = (ScaleByAction)Actions.action(ScaleByAction.class);
        scaleByAction0.setAmount(f, f1);
        scaleByAction0.setDuration(f2);
        scaleByAction0.setInterpolation(interpolation0);
        return scaleByAction0;
    }

    public static ScaleToAction scaleTo(float f, float f1) {
        return Actions.scaleTo(f, f1, 0.0f, null);
    }

    public static ScaleToAction scaleTo(float f, float f1, float f2) {
        return Actions.scaleTo(f, f1, f2, null);
    }

    public static ScaleToAction scaleTo(float f, float f1, float f2, @Null Interpolation interpolation0) {
        ScaleToAction scaleToAction0 = (ScaleToAction)Actions.action(ScaleToAction.class);
        scaleToAction0.setScale(f, f1);
        scaleToAction0.setDuration(f2);
        scaleToAction0.setInterpolation(interpolation0);
        return scaleToAction0;
    }

    public static SequenceAction sequence() {
        return (SequenceAction)Actions.action(SequenceAction.class);
    }

    public static SequenceAction sequence(Action action0) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        sequenceAction0.addAction(action0);
        return sequenceAction0;
    }

    public static SequenceAction sequence(Action action0, Action action1) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        sequenceAction0.addAction(action0);
        sequenceAction0.addAction(action1);
        return sequenceAction0;
    }

    public static SequenceAction sequence(Action action0, Action action1, Action action2) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        sequenceAction0.addAction(action0);
        sequenceAction0.addAction(action1);
        sequenceAction0.addAction(action2);
        return sequenceAction0;
    }

    public static SequenceAction sequence(Action action0, Action action1, Action action2, Action action3) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        sequenceAction0.addAction(action0);
        sequenceAction0.addAction(action1);
        sequenceAction0.addAction(action2);
        sequenceAction0.addAction(action3);
        return sequenceAction0;
    }

    public static SequenceAction sequence(Action action0, Action action1, Action action2, Action action3, Action action4) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        sequenceAction0.addAction(action0);
        sequenceAction0.addAction(action1);
        sequenceAction0.addAction(action2);
        sequenceAction0.addAction(action3);
        sequenceAction0.addAction(action4);
        return sequenceAction0;
    }

    public static SequenceAction sequence(Action[] arr_action) {
        SequenceAction sequenceAction0 = (SequenceAction)Actions.action(SequenceAction.class);
        for(int v = 0; v < arr_action.length; ++v) {
            sequenceAction0.addAction(arr_action[v]);
        }
        return sequenceAction0;
    }

    public static VisibleAction show() {
        return Actions.visible(true);
    }

    public static SizeByAction sizeBy(float f, float f1) {
        return Actions.sizeBy(f, f1, 0.0f, null);
    }

    public static SizeByAction sizeBy(float f, float f1, float f2) {
        return Actions.sizeBy(f, f1, f2, null);
    }

    public static SizeByAction sizeBy(float f, float f1, float f2, @Null Interpolation interpolation0) {
        SizeByAction sizeByAction0 = (SizeByAction)Actions.action(SizeByAction.class);
        sizeByAction0.setAmount(f, f1);
        sizeByAction0.setDuration(f2);
        sizeByAction0.setInterpolation(interpolation0);
        return sizeByAction0;
    }

    public static SizeToAction sizeTo(float f, float f1) {
        return Actions.sizeTo(f, f1, 0.0f, null);
    }

    public static SizeToAction sizeTo(float f, float f1, float f2) {
        return Actions.sizeTo(f, f1, f2, null);
    }

    public static SizeToAction sizeTo(float f, float f1, float f2, @Null Interpolation interpolation0) {
        SizeToAction sizeToAction0 = (SizeToAction)Actions.action(SizeToAction.class);
        sizeToAction0.setSize(f, f1);
        sizeToAction0.setDuration(f2);
        sizeToAction0.setInterpolation(interpolation0);
        return sizeToAction0;
    }

    public static Action targeting(Actor actor0, Action action0) {
        action0.setTarget(actor0);
        return action0;
    }

    public static TimeScaleAction timeScale(float f, Action action0) {
        TimeScaleAction timeScaleAction0 = (TimeScaleAction)Actions.action(TimeScaleAction.class);
        timeScaleAction0.setScale(f);
        timeScaleAction0.setAction(action0);
        return timeScaleAction0;
    }

    public static TouchableAction touchable(Touchable touchable0) {
        TouchableAction touchableAction0 = (TouchableAction)Actions.action(TouchableAction.class);
        touchableAction0.setTouchable(touchable0);
        return touchableAction0;
    }

    public static VisibleAction visible(boolean z) {
        VisibleAction visibleAction0 = (VisibleAction)Actions.action(VisibleAction.class);
        visibleAction0.setVisible(z);
        return visibleAction0;
    }
}

