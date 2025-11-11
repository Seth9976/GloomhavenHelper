package com.badlogic.gdx.graphics.g3d;

import com.badlogic.gdx.utils.Array;

public class Material extends Attributes {
    private static int counter;
    public String id;

    static {
    }

    public Material() {
        int v = Material.counter + 1;
        Material.counter = v;
        this("mtl" + v);
    }

    public Material(Material material0) {
        this(material0.id, material0);
    }

    public Material(Array array0) {
        this.set(array0);
    }

    public Material(String s) {
        this.id = s;
    }

    public Material(String s, Material material0) {
        this(s);
        for(Object object0: material0) {
            this.set(((Attribute)object0).copy());
        }
    }

    public Material(String s, Array array0) {
        this(s);
        this.set(array0);
    }

    public Material(String s, Attribute[] arr_attribute) {
        this(s);
        this.set(arr_attribute);
    }

    public Material(Attribute[] arr_attribute) {
        this.set(arr_attribute);
    }

    public Material copy() {
        return new Material(this);
    }

    // 去混淆评级： 低(40)
    @Override  // com.badlogic.gdx.graphics.g3d.Attributes
    public boolean equals(Object object0) {
        return object0 instanceof Material && (object0 == this || ((Material)object0).id.equals(this.id) && super.equals(object0));
    }

    @Override  // com.badlogic.gdx.graphics.g3d.Attributes
    public int hashCode() {
        int v = this.id.hashCode();
        return super.hashCode() + v * 3;
    }
}

