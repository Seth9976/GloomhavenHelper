package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.graphics.g3d.attributes.DirectionalLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.PointLightsAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.SpotLightsAttribute;
import com.badlogic.gdx.graphics.g3d.environment.BaseLight;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.ShadowMap;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

public class Environment extends Attributes {
    public ShadowMap shadowMap;

    public Environment add(BaseLight baseLight0) {
        if(baseLight0 instanceof DirectionalLight) {
            this.add(((DirectionalLight)baseLight0));
            return this;
        }
        if(baseLight0 instanceof PointLight) {
            this.add(((PointLight)baseLight0));
            return this;
        }
        if(!(baseLight0 instanceof SpotLight)) {
            throw new GdxRuntimeException("Unknown light type");
        }
        this.add(((SpotLight)baseLight0));
        return this;
    }

    public Environment add(DirectionalLight directionalLight0) {
        DirectionalLightsAttribute directionalLightsAttribute0 = (DirectionalLightsAttribute)this.get(DirectionalLightsAttribute.Type);
        if(directionalLightsAttribute0 == null) {
            directionalLightsAttribute0 = new DirectionalLightsAttribute();
            this.set(directionalLightsAttribute0);
        }
        directionalLightsAttribute0.lights.add(directionalLight0);
        return this;
    }

    public Environment add(PointLight pointLight0) {
        PointLightsAttribute pointLightsAttribute0 = (PointLightsAttribute)this.get(PointLightsAttribute.Type);
        if(pointLightsAttribute0 == null) {
            pointLightsAttribute0 = new PointLightsAttribute();
            this.set(pointLightsAttribute0);
        }
        pointLightsAttribute0.lights.add(pointLight0);
        return this;
    }

    public Environment add(SpotLight spotLight0) {
        SpotLightsAttribute spotLightsAttribute0 = (SpotLightsAttribute)this.get(SpotLightsAttribute.Type);
        if(spotLightsAttribute0 == null) {
            spotLightsAttribute0 = new SpotLightsAttribute();
            this.set(spotLightsAttribute0);
        }
        spotLightsAttribute0.lights.add(spotLight0);
        return this;
    }

    public Environment add(Array array0) {
        for(Object object0: array0) {
            this.add(((BaseLight)object0));
        }
        return this;
    }

    public Environment add(BaseLight[] arr_baseLight) {
        for(int v = 0; v < arr_baseLight.length; ++v) {
            this.add(arr_baseLight[v]);
        }
        return this;
    }

    public Environment remove(BaseLight baseLight0) {
        if(baseLight0 instanceof DirectionalLight) {
            this.remove(((DirectionalLight)baseLight0));
            return this;
        }
        if(baseLight0 instanceof PointLight) {
            this.remove(((PointLight)baseLight0));
            return this;
        }
        if(!(baseLight0 instanceof SpotLight)) {
            throw new GdxRuntimeException("Unknown light type");
        }
        this.remove(((SpotLight)baseLight0));
        return this;
    }

    public Environment remove(DirectionalLight directionalLight0) {
        if(this.has(DirectionalLightsAttribute.Type)) {
            DirectionalLightsAttribute directionalLightsAttribute0 = (DirectionalLightsAttribute)this.get(DirectionalLightsAttribute.Type);
            directionalLightsAttribute0.lights.removeValue(directionalLight0, false);
            if(directionalLightsAttribute0.lights.size == 0) {
                this.remove(DirectionalLightsAttribute.Type);
            }
        }
        return this;
    }

    public Environment remove(PointLight pointLight0) {
        if(this.has(PointLightsAttribute.Type)) {
            PointLightsAttribute pointLightsAttribute0 = (PointLightsAttribute)this.get(PointLightsAttribute.Type);
            pointLightsAttribute0.lights.removeValue(pointLight0, false);
            if(pointLightsAttribute0.lights.size == 0) {
                this.remove(PointLightsAttribute.Type);
            }
        }
        return this;
    }

    public Environment remove(SpotLight spotLight0) {
        if(this.has(SpotLightsAttribute.Type)) {
            SpotLightsAttribute spotLightsAttribute0 = (SpotLightsAttribute)this.get(SpotLightsAttribute.Type);
            spotLightsAttribute0.lights.removeValue(spotLight0, false);
            if(spotLightsAttribute0.lights.size == 0) {
                this.remove(SpotLightsAttribute.Type);
            }
        }
        return this;
    }

    public Environment remove(Array array0) {
        for(Object object0: array0) {
            this.remove(((BaseLight)object0));
        }
        return this;
    }

    public Environment remove(BaseLight[] arr_baseLight) {
        for(int v = 0; v < arr_baseLight.length; ++v) {
            this.remove(arr_baseLight[v]);
        }
        return this;
    }
}

