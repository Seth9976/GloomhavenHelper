package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.utils.Array;

public class DirectionalLightsAttribute extends Attribute {
    public static final String Alias = "directionalLights";
    public static final long Type;
    public final Array lights;

    static {
        DirectionalLightsAttribute.Type = DirectionalLightsAttribute.register("directionalLights");
    }

    public DirectionalLightsAttribute() {
        super(DirectionalLightsAttribute.Type);
        this.lights = new Array(1);
    }

    public DirectionalLightsAttribute(DirectionalLightsAttribute directionalLightsAttribute0) {
        this.lights.addAll(directionalLightsAttribute0.lights);
    }

    public int compareTo(Attribute attribute0) {
        if(this.type != attribute0.type) {
            return this.type >= attribute0.type ? 1 : -1;
        }
        return 0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Attribute)object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public Attribute copy() {
        return this.copy();
    }

    public DirectionalLightsAttribute copy() {
        return new DirectionalLightsAttribute(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = super.hashCode();
        for(Object object0: this.lights) {
            DirectionalLight directionalLight0 = (DirectionalLight)object0;
            v = v * 0x4CD + (directionalLight0 == null ? 0 : directionalLight0.hashCode());
        }
        return v;
    }

    public static final boolean is(long v) {
        return (DirectionalLightsAttribute.Type & v) == v;
    }
}

