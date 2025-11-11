package com.badlogic.gdx;

public interface ApplicationLogger {
    void debug(String arg1, String arg2);

    void debug(String arg1, String arg2, Throwable arg3);

    void error(String arg1, String arg2);

    void error(String arg1, String arg2, Throwable arg3);

    void log(String arg1, String arg2);

    void log(String arg1, String arg2, Throwable arg3);
}

