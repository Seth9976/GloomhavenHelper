package com.badlogic.gdx;

import java.util.Map;

public interface Preferences {
    void clear();

    boolean contains(String arg1);

    void flush();

    Map get();

    boolean getBoolean(String arg1);

    boolean getBoolean(String arg1, boolean arg2);

    float getFloat(String arg1);

    float getFloat(String arg1, float arg2);

    int getInteger(String arg1);

    int getInteger(String arg1, int arg2);

    long getLong(String arg1);

    long getLong(String arg1, long arg2);

    String getString(String arg1);

    String getString(String arg1, String arg2);

    Preferences put(Map arg1);

    Preferences putBoolean(String arg1, boolean arg2);

    Preferences putFloat(String arg1, float arg2);

    Preferences putInteger(String arg1, int arg2);

    Preferences putLong(String arg1, long arg2);

    Preferences putString(String arg1, String arg2);

    void remove(String arg1);
}

