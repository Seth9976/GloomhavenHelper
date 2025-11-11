package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GloomScrollPane extends ScrollPane {
    public GloomScrollPane(Actor actor0) {
        super(actor0);
    }

    public GloomScrollPane(Actor actor0, ScrollPaneStyle scrollPane$ScrollPaneStyle0) {
        super(actor0, scrollPane$ScrollPaneStyle0);
    }

    public GloomScrollPane(Actor actor0, Skin skin0) {
        super(actor0, skin0);
    }

    public GloomScrollPane(Actor actor0, Skin skin0, String s) {
        super(actor0, skin0, s);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
    protected float getMouseWheelY() {
        return this.getScrollHeight() * 0.05f;
    }
}

