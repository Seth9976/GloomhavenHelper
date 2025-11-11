package com.esotericsoftware.minlog;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Log {
    public static class Logger {
        private final long firstLogTime;

        public Logger() {
            this.firstLogTime = System.currentTimeMillis();
        }

        public void log(int v, String s, String s1, Throwable throwable0) {
            StringBuilder stringBuilder0 = new StringBuilder(0x100);
            long v1 = System.currentTimeMillis() - this.firstLogTime;
            long v2 = v1 / 1000L % 60L;
            if(v1 / 60000L <= 9L) {
                stringBuilder0.append('0');
            }
            stringBuilder0.append(v1 / 60000L);
            stringBuilder0.append(':');
            if(v2 <= 9L) {
                stringBuilder0.append('0');
            }
            stringBuilder0.append(v2);
            switch(v) {
                case 1: {
                    stringBuilder0.append(" TRACE: ");
                    break;
                }
                case 2: {
                    stringBuilder0.append(" DEBUG: ");
                    break;
                }
                case 3: {
                    stringBuilder0.append("  INFO: ");
                    break;
                }
                case 4: {
                    stringBuilder0.append("  WARN: ");
                    break;
                }
                case 5: {
                    stringBuilder0.append(" ERROR: ");
                }
            }
            if(s != null) {
                stringBuilder0.append('[');
                stringBuilder0.append(s);
                stringBuilder0.append("] ");
            }
            stringBuilder0.append(s1);
            if(throwable0 != null) {
                StringWriter stringWriter0 = new StringWriter(0x100);
                throwable0.printStackTrace(new PrintWriter(stringWriter0));
                stringBuilder0.append('\n');
                stringBuilder0.append(stringWriter0.toString().trim());
            }
            this.print(stringBuilder0.toString());
        }

        protected void print(String s) {
            System.out.println(s);
        }
    }

    public static boolean DEBUG = false;
    public static boolean ERROR = false;
    public static boolean INFO = false;
    public static final int LEVEL_DEBUG = 2;
    public static final int LEVEL_ERROR = 5;
    public static final int LEVEL_INFO = 3;
    public static final int LEVEL_NONE = 6;
    public static final int LEVEL_TRACE = 1;
    public static final int LEVEL_WARN = 4;
    public static boolean TRACE = false;
    public static boolean WARN = false;
    private static int level = 3;
    private static Logger logger;

    static {
        boolean z = false;
        Log.ERROR = Log.level <= 5;
        Log.WARN = Log.level <= 4;
        Log.INFO = Log.level <= 3;
        Log.DEBUG = Log.level <= 2;
        if(Log.level <= 1) {
            z = true;
        }
        Log.TRACE = z;
        Log.logger = new Logger();
    }

    public static void DEBUG() {
        Log.set(2);
    }

    public static void ERROR() {
        Log.set(5);
    }

    public static void INFO() {
        Log.set(3);
    }

    public static void NONE() {
        Log.set(6);
    }

    public static void TRACE() {
        Log.set(1);
    }

    public static void WARN() {
        Log.set(4);
    }

    public static void debug(String s) {
        if(Log.DEBUG) {
            Log.logger.log(2, null, s, null);
        }
    }

    public static void debug(String s, String s1) {
        if(Log.DEBUG) {
            Log.logger.log(2, s, s1, null);
        }
    }

    public static void debug(String s, String s1, Throwable throwable0) {
        if(Log.DEBUG) {
            Log.logger.log(2, s, s1, throwable0);
        }
    }

    public static void debug(String s, Throwable throwable0) {
        if(Log.DEBUG) {
            Log.logger.log(2, null, s, throwable0);
        }
    }

    public static void error(String s) {
        if(Log.ERROR) {
            Log.logger.log(5, null, s, null);
        }
    }

    public static void error(String s, String s1) {
        if(Log.ERROR) {
            Log.logger.log(5, s, s1, null);
        }
    }

    public static void error(String s, String s1, Throwable throwable0) {
        if(Log.ERROR) {
            Log.logger.log(5, s, s1, throwable0);
        }
    }

    public static void error(String s, Throwable throwable0) {
        if(Log.ERROR) {
            Log.logger.log(5, null, s, throwable0);
        }
    }

    public static int getLevel() {
        return Log.level;
    }

    public static void info(String s) {
        if(Log.INFO) {
            Log.logger.log(3, null, s, null);
        }
    }

    public static void info(String s, String s1) {
        if(Log.INFO) {
            Log.logger.log(3, s, s1, null);
        }
    }

    public static void info(String s, String s1, Throwable throwable0) {
        if(Log.INFO) {
            Log.logger.log(3, s, s1, throwable0);
        }
    }

    public static void info(String s, Throwable throwable0) {
        if(Log.INFO) {
            Log.logger.log(3, null, s, throwable0);
        }
    }

    public static void set(int v) {
        Log.level = v;
        boolean z = false;
        Log.ERROR = v <= 5;
        Log.WARN = v <= 4;
        Log.INFO = v <= 3;
        Log.DEBUG = v <= 2;
        if(v <= 1) {
            z = true;
        }
        Log.TRACE = z;
    }

    public static void setLogger(Logger log$Logger0) {
        Log.logger = log$Logger0;
    }

    public static void trace(String s) {
        if(Log.TRACE) {
            Log.logger.log(1, null, s, null);
        }
    }

    public static void trace(String s, String s1) {
        if(Log.TRACE) {
            Log.logger.log(1, s, s1, null);
        }
    }

    public static void trace(String s, String s1, Throwable throwable0) {
        if(Log.TRACE) {
            Log.logger.log(1, s, s1, throwable0);
        }
    }

    public static void trace(String s, Throwable throwable0) {
        if(Log.TRACE) {
            Log.logger.log(1, null, s, throwable0);
        }
    }

    public static void warn(String s) {
        if(Log.WARN) {
            Log.logger.log(4, null, s, null);
        }
    }

    public static void warn(String s, String s1) {
        if(Log.WARN) {
            Log.logger.log(4, s, s1, null);
        }
    }

    public static void warn(String s, String s1, Throwable throwable0) {
        if(Log.WARN) {
            Log.logger.log(4, s, s1, throwable0);
        }
    }

    public static void warn(String s, Throwable throwable0) {
        if(Log.WARN) {
            Log.logger.log(4, null, s, throwable0);
        }
    }
}

