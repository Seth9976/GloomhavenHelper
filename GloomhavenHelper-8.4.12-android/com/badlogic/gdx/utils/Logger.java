package com.badlogic.gdx.utils;

import com.badlogic.gdx.Gdx;

public class Logger {
    public static final int DEBUG = 3;
    public static final int ERROR = 1;
    public static final int INFO = 2;
    public static final int NONE;
    private int level;
    private final String tag;

    public Logger(String s) {
        this(s, 1);
    }

    public Logger(String s, int v) {
        this.tag = s;
        this.level = v;
    }

    public void debug(String s) {
        if(this.level >= 3) {
            Gdx.app.debug(this.tag, s);
        }
    }

    public void debug(String s, Exception exception0) {
        if(this.level >= 3) {
            Gdx.app.debug(this.tag, s, exception0);
        }
    }

    public void error(String s) {
        if(this.level >= 1) {
            Gdx.app.error(this.tag, s);
        }
    }

    public void error(String s, Throwable throwable0) {
        if(this.level >= 1) {
            Gdx.app.error(this.tag, s, throwable0);
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void info(String s) {
        if(this.level >= 2) {
            Gdx.app.log(this.tag, s);
        }
    }

    public void info(String s, Exception exception0) {
        if(this.level >= 2) {
            Gdx.app.log(this.tag, s, exception0);
        }
    }

    public void setLevel(int v) {
        this.level = v;
    }
}

