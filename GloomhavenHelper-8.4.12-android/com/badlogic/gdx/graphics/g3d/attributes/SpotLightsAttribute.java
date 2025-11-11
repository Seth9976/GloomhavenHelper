package com.badlogic.gdx.graphics.g3d.attributes;

import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.Array;

public class SpotLightsAttribute extends Attribute {
    public static final String Alias = "spotLights";
    public static final long Type;
    public final Array lights;

    static {
        SpotLightsAttribute.Type = SpotLightsAttribute.register("spotLights");
    }

    public SpotLightsAttribute() {
        super(SpotLightsAttribute.Type);
        this.lights = new Array(1);
    }

    public SpotLightsAttribute(SpotLightsAttribute spotLightsAttribute0) {
        this.lights.addAll(spotLightsAttribute0.lights);
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

    public SpotLightsAttribute copy() {
        return new SpotLightsAttribute(this);
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attribute
    public int hashCode() {
        int v = super.hashCode();
        for(Object object0: this.lights) {
            SpotLight spotLight0 = (SpotLight)object0;
            v = v * 0x4D5 + (spotLight0 == null ? 0 : spotLight0.hashCode());
        }
        return v;
    }

    public static final boolean is(long v) {
        return (SpotLightsAttribute.Type & v) == v;
    }
}

