package com.badlogic.gdx.backends.android;

import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import com.badlogic.gdx.Preferences;
import java.util.Map.Entry;
import java.util.Map;

public class AndroidPreferences implements Preferences {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPrefs;

    public AndroidPreferences(SharedPreferences sharedPreferences0) {
        this.sharedPrefs = sharedPreferences0;
    }

    @Override  // com.badlogic.gdx.Preferences
    public void clear() {
        this.edit();
        this.editor.clear();
    }

    @Override  // com.badlogic.gdx.Preferences
    public boolean contains(String s) {
        return this.sharedPrefs.contains(s);
    }

    private void edit() {
        if(this.editor == null) {
            this.editor = this.sharedPrefs.edit();
        }
    }

    @Override  // com.badlogic.gdx.Preferences
    public void flush() {
        SharedPreferences.Editor sharedPreferences$Editor0 = this.editor;
        if(sharedPreferences$Editor0 != null) {
            sharedPreferences$Editor0.apply();
            this.editor = null;
        }
    }

    @Override  // com.badlogic.gdx.Preferences
    public Map get() {
        return this.sharedPrefs.getAll();
    }

    @Override  // com.badlogic.gdx.Preferences
    public boolean getBoolean(String s) {
        return this.sharedPrefs.getBoolean(s, false);
    }

    @Override  // com.badlogic.gdx.Preferences
    public boolean getBoolean(String s, boolean z) {
        return this.sharedPrefs.getBoolean(s, z);
    }

    @Override  // com.badlogic.gdx.Preferences
    public float getFloat(String s) {
        return this.sharedPrefs.getFloat(s, 0.0f);
    }

    @Override  // com.badlogic.gdx.Preferences
    public float getFloat(String s, float f) {
        return this.sharedPrefs.getFloat(s, f);
    }

    @Override  // com.badlogic.gdx.Preferences
    public int getInteger(String s) {
        return this.sharedPrefs.getInt(s, 0);
    }

    @Override  // com.badlogic.gdx.Preferences
    public int getInteger(String s, int v) {
        return this.sharedPrefs.getInt(s, v);
    }

    @Override  // com.badlogic.gdx.Preferences
    public long getLong(String s) {
        return this.sharedPrefs.getLong(s, 0L);
    }

    @Override  // com.badlogic.gdx.Preferences
    public long getLong(String s, long v) {
        return this.sharedPrefs.getLong(s, v);
    }

    @Override  // com.badlogic.gdx.Preferences
    public String getString(String s) {
        return this.sharedPrefs.getString(s, "");
    }

    @Override  // com.badlogic.gdx.Preferences
    public String getString(String s, String s1) {
        return this.sharedPrefs.getString(s, s1);
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences put(Map map0) {
        this.edit();
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(map$Entry0.getValue() instanceof Boolean) {
                this.putBoolean(((String)map$Entry0.getKey()), ((Boolean)map$Entry0.getValue()).booleanValue());
            }
            if(map$Entry0.getValue() instanceof Integer) {
                this.putInteger(((String)map$Entry0.getKey()), ((int)(((Integer)map$Entry0.getValue()))));
            }
            if(map$Entry0.getValue() instanceof Long) {
                this.putLong(((String)map$Entry0.getKey()), ((long)(((Long)map$Entry0.getValue()))));
            }
            if(map$Entry0.getValue() instanceof String) {
                this.putString(((String)map$Entry0.getKey()), ((String)map$Entry0.getValue()));
            }
            if(map$Entry0.getValue() instanceof Float) {
                this.putFloat(((String)map$Entry0.getKey()), ((float)(((Float)map$Entry0.getValue()))));
            }
        }
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences putBoolean(String s, boolean z) {
        this.edit();
        this.editor.putBoolean(s, z);
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences putFloat(String s, float f) {
        this.edit();
        this.editor.putFloat(s, f);
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences putInteger(String s, int v) {
        this.edit();
        this.editor.putInt(s, v);
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences putLong(String s, long v) {
        this.edit();
        this.editor.putLong(s, v);
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public Preferences putString(String s, String s1) {
        this.edit();
        this.editor.putString(s, s1);
        return this;
    }

    @Override  // com.badlogic.gdx.Preferences
    public void remove(String s) {
        this.edit();
        this.editor.remove(s);
    }
}

