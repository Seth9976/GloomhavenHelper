package com.badlogic.gdx;

public class Version {
    public static final int MAJOR = 0;
    public static final int MINOR = 0;
    public static final int REVISION = 0;
    public static final String VERSION = "1.10.0";

    static {
        Version.MAJOR = 1;
        Version.MINOR = 10;
        Version.REVISION = 0;
    }

    public static boolean isHigher(int v, int v1, int v2) {
        return Version.isHigherEqual(v, v1, v2 + 1);
    }

    public static boolean isHigherEqual(int v, int v1, int v2) {
        int v3 = Version.MAJOR;
        if(v3 != v) {
            return v3 > v;
        }
        return Version.MINOR == v1 ? Version.REVISION >= v2 : Version.MINOR > v1;
    }

    public static boolean isLower(int v, int v1, int v2) {
        return Version.isLowerEqual(v, v1, v2 - 1);
    }

    public static boolean isLowerEqual(int v, int v1, int v2) {
        int v3 = Version.MAJOR;
        if(v3 != v) {
            return v3 < v;
        }
        return Version.MINOR == v1 ? Version.REVISION <= v2 : Version.MINOR < v1;
    }
}

