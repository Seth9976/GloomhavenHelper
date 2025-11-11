package com.esotericsoftware.gloomhavenhelper;

public enum Edition {
    OG,
    FC,
    JotL,
    Other;

    public static final Edition[] values;

    static {
        Edition.values = (Edition[])Edition.$VALUES.clone();
    }
}

