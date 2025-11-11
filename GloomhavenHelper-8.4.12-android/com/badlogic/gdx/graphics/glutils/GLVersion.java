package com.badlogic.gdx.graphics.glutils;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GLVersion {
    public static enum Type {
        OpenGL,
        GLES,
        WebGL,
        NONE;

    }

    private final String TAG;
    private int majorVersion;
    private int minorVersion;
    private int releaseVersion;
    private final String rendererString;
    private final Type type;
    private final String vendorString;

    public GLVersion(ApplicationType application$ApplicationType0, String s, String s1, String s2) {
        this.TAG = "GLVersion";
        if(application$ApplicationType0 == ApplicationType.Android) {
            this.type = Type.GLES;
        }
        else if(application$ApplicationType0 == ApplicationType.iOS) {
            this.type = Type.GLES;
        }
        else if(application$ApplicationType0 == ApplicationType.Desktop) {
            this.type = Type.OpenGL;
        }
        else if(application$ApplicationType0 == ApplicationType.Applet) {
            this.type = Type.OpenGL;
        }
        else if(application$ApplicationType0 == ApplicationType.WebGL) {
            this.type = Type.WebGL;
        }
        else {
            this.type = Type.NONE;
        }
        if(this.type == Type.GLES) {
            this.extractVersion("OpenGL ES (\\d(\\.\\d){0,2})", s);
        }
        else if(this.type == Type.WebGL) {
            this.extractVersion("WebGL (\\d(\\.\\d){0,2})", s);
        }
        else if(this.type == Type.OpenGL) {
            this.extractVersion("(\\d(\\.\\d){0,2})", s);
        }
        else {
            this.majorVersion = -1;
            this.minorVersion = -1;
            this.releaseVersion = -1;
            s1 = "";
            s2 = "";
        }
        this.vendorString = s1;
        this.rendererString = s2;
    }

    private void extractVersion(String s, String s1) {
        Matcher matcher0 = Pattern.compile(s).matcher(s1);
        int v = 0;
        if(matcher0.find()) {
            String[] arr_s = matcher0.group(1).split("\\.");
            this.majorVersion = this.parseInt(arr_s[0], 2);
            this.minorVersion = arr_s.length >= 2 ? this.parseInt(arr_s[1], 0) : 0;
            if(arr_s.length >= 3) {
                v = this.parseInt(arr_s[2], 0);
            }
            this.releaseVersion = v;
            return;
        }
        Gdx.app.log("GLVersion", "Invalid version string: " + s1);
        this.majorVersion = 2;
        this.minorVersion = 0;
        this.releaseVersion = 0;
    }

    public String getDebugVersionString() {
        return "Type: " + this.type + "\nVersion: " + this.majorVersion + ":" + this.minorVersion + ":" + this.releaseVersion + "\nVendor: " + this.vendorString + "\nRenderer: " + this.rendererString;
    }

    public int getMajorVersion() {
        return this.majorVersion;
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public int getReleaseVersion() {
        return this.releaseVersion;
    }

    public String getRendererString() {
        return this.rendererString;
    }

    public Type getType() {
        return this.type;
    }

    public String getVendorString() {
        return this.vendorString;
    }

    public boolean isVersionEqualToOrHigher(int v, int v1) {
        return this.majorVersion > v || this.majorVersion == v && this.minorVersion >= v1;
    }

    private int parseInt(String s, int v) {
        try {
            return Integer.parseInt(s);
        }
        catch(NumberFormatException unused_ex) {
            Gdx.app.error("LibGDX GL", "Error parsing number: " + s + ", assuming: " + v);
            return v;
        }
    }
}

