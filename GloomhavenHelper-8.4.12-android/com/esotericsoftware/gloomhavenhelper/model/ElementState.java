package com.esotericsoftware.gloomhavenhelper.model;

public enum ElementState {
    inert,
    strong,
    waning;

    public static final ElementState[] values;

    static {
        ElementState.values = (ElementState[])ElementState.$VALUES.clone();
    }
}

