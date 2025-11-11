package com.badlogic.gdx;

import com.badlogic.gdx.utils.Clipboard;

public interface Application {
    public static enum ApplicationType {
        Android,
        Desktop,
        HeadlessDesktop,
        Applet,
        WebGL,
        iOS;

    }

    public static final int LOG_DEBUG = 3;
    public static final int LOG_ERROR = 1;
    public static final int LOG_INFO = 2;
    public static final int LOG_NONE;

    void addLifecycleListener(LifecycleListener arg1);

    void debug(String arg1, String arg2);

    void debug(String arg1, String arg2, Throwable arg3);

    void error(String arg1, String arg2);

    void error(String arg1, String arg2, Throwable arg3);

    void exit();

    ApplicationListener getApplicationListener();

    ApplicationLogger getApplicationLogger();

    Audio getAudio();

    Clipboard getClipboard();

    Files getFiles();

    Graphics getGraphics();

    Input getInput();

    long getJavaHeap();

    int getLogLevel();

    long getNativeHeap();

    Net getNet();

    Preferences getPreferences(String arg1);

    ApplicationType getType();

    int getVersion();

    void log(String arg1, String arg2);

    void log(String arg1, String arg2, Throwable arg3);

    void postRunnable(Runnable arg1);

    void removeLifecycleListener(LifecycleListener arg1);

    void setApplicationLogger(ApplicationLogger arg1);

    void setLogLevel(int arg1);
}

