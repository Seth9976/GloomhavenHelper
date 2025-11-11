package com.esotericsoftware.gloomhavenhelper.util;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.esotericsoftware.gloomhavenhelper.App;

public class DesatDrawable extends CompositeDrawable {
    public DesatDrawable(Drawable[] arr_drawable) {
        super(arr_drawable);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.CompositeDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3) {
        batch0.setShader(App.desatShader);
        App.desatShader.setUniformf("u_desat", 1.0f);
        super.draw(batch0, f, f1, f2, f3);
        batch0.setShader(null);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.CompositeDrawable
    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
        batch0.setShader(App.desatShader);
        App.desatShader.setUniformf("u_desat", 1.0f);
        super.draw(batch0, f, f1, f2, f3, f4, f5, f6, f7, f8);
        batch0.setShader(null);
    }
}

