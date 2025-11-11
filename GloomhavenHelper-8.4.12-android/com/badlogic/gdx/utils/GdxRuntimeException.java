package com.badlogic.gdx.utils;

public class GdxRuntimeException extends RuntimeException {
    private static final long serialVersionUID = 0x5D7A8FE415D6341DL;

    public GdxRuntimeException(String s) {
        super(s);
    }

    public GdxRuntimeException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public GdxRuntimeException(Throwable throwable0) {
        super(throwable0);
    }
}

