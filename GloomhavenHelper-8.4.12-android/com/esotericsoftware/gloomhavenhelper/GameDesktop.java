package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.files.FileHandle;
import com.esotericsoftware.gloomhavenhelper.network.Network;
import com.esotericsoftware.gloomhavenhelper.util.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.File;
import java.net.URI;
import java.util.Locale.Category;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameDesktop extends Game {
    public static FileHandle configFile;
    public static FileHandle configFileTemp;
    public static FileHandle stateFile;
    public static FileHandle stateFileTemp;
    public static final ExecutorService threadPool;

    static {
        GameDesktop.threadPool = GameDesktop.newThreadPool(1, 60, "Pool", true);
        GameDesktop.configFile = new FileHandle(new File("", ".ghh/config"));
        GameDesktop.configFileTemp = new FileHandle(new File("", ".ghh/config.temp"));
        GameDesktop.stateFile = new FileHandle(new File("", ".ghh/state"));
        GameDesktop.stateFileTemp = new FileHandle(new File("", ".ghh/state.temp"));
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public boolean canOpenURL() {
        if(System.getProperty("os.name").contains("Mac")) {
            return false;
        }
        try {
            Object object0 = Class.forName("java.awt.Desktop").getMethod("getDesktop").invoke(null);
            return ((Boolean)object0.getClass().getMethod("isDesktopSupported").invoke(object0)).booleanValue();
        }
        catch(Throwable unused_ex) {
            return false;
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void close() {
        Runtime.getRuntime().halt(0);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public Object currentThread() {
        return Thread.currentThread();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public String defaultLanguage() {
        String s = GameDesktop.languageCodeToName(System.getProperty("user.language.format"));
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault(Locale.Category.FORMAT).getISO3Language());
            }
            catch(Throwable unused_ex) {
            }
        }
        if(s == null) {
            s = GameDesktop.languageCodeToName("en");
        }
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault(Locale.Category.DISPLAY).getISO3Language());
            }
            catch(Throwable unused_ex) {
            }
        }
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault().getISO3Language());
            }
            catch(Throwable unused_ex) {
            }
        }
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault(Locale.Category.FORMAT).getLanguage());
            }
            catch(Throwable unused_ex) {
            }
        }
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault(Locale.Category.DISPLAY).getLanguage());
            }
            catch(Throwable unused_ex) {
            }
        }
        if(s == null) {
            try {
                s = GameDesktop.languageCodeToName(Locale.getDefault().getLanguage());
            }
            catch(Throwable unused_ex) {
            }
        }
        return s == null ? "en" : s;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void loadConfig() {
        Log.info("ghh", "Gloomhaven Helper 8.4.12");
        if(GameDesktop.configFile.exists()) {
            Log.info("ghh", "Loading game state: " + GameDesktop.configFile);
            try {
                App.config = (GameConfig)GameDesktop.json.fromJson(GameConfig.class, GameDesktop.configFile);
            }
            catch(Exception exception0) {
                if(Log.ERROR) {
                    Log.error("ghh", "Unable to read game config file.", exception0);
                }
            }
        }
        else {
            Log.info("ghh", "New game state: " + GameDesktop.configFile);
        }
        if(App.config == null) {
            App.config = new GameConfig();
        }
        if(App.config.language == null) {
            App.config.language = "en";
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void loadState() {
        synchronized(this) {
            if(GameDesktop.stateFile.exists()) {
                byte[] arr_b = GameDesktop.stateFile.readBytes();
                Serialization.input.setBuffer(arr_b);
                try {
                    int v1 = Serialization.input.readInt();
                    App.state = Serialization.read(Serialization.input);
                    App.state.changeNumber = v1;
                    App.state.apply(false);
                    Serialization.lastOutput.clear();
                    Serialization.lastOutput.writeBytes(arr_b, 0, ((int)Serialization.input.total()));
                    this.storeUndo();
                }
                catch(Exception exception0) {
                    if(Log.ERROR) {
                        Log.error("ghh", "Unable to load game state from file.", exception0);
                    }
                    App.toast("Unable to load game state!");
                    this.stateLoaded(false);
                }
                return;
            }
            this.stateLoaded(true);
        }
    }

    public static ThreadPoolExecutor newThreadPool(int v, int v1, String s, boolean z) {
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(v, v, ((long)v1), TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable0) {
                Thread thread0 = new Thread(runnable0, s);
                thread0.setDaemon(z);
                return thread0;
            }
        }, new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        return threadPoolExecutor0;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void openURL(String s) {
        try {
            Object object0 = Class.forName("java.awt.Desktop").getMethod("getDesktop").invoke(null);
            object0.getClass().getMethod("browse", URI.class).invoke(object0, new URI(s));
        }
        catch(Throwable throwable0) {
            if(Log.ERROR) {
                Log.error("ghh", "Unable to open URL: " + s, throwable0);
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void saveConfig() {
        Network.update();
        GameDesktop.configFile.parent().mkdirs();
        try {
            GameDesktop.json.toJson(App.config, GameDesktop.configFileTemp.writer(false));
            GameDesktop.configFileTemp.copyTo(GameDesktop.configFile);
            GameDesktop.configFileTemp.delete();
        }
        catch(Exception exception0) {
            if(Log.ERROR) {
                Log.error("ghh", "Unable to write game config file.", exception0);
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void saveState(byte[] arr_b, int v) {
        synchronized(this) {
            try {
                GameDesktop.stateFileTemp.writeBytes(arr_b, 0, v, false);
                GameDesktop.stateFileTemp.copyTo(GameDesktop.stateFile);
                GameDesktop.stateFileTemp.delete();
            }
            catch(Exception exception0) {
                if(Log.ERROR) {
                    Log.error("ghh", "Unable to write game state file.", exception0);
                }
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void thread(Runnable runnable0) {
        GameDesktop.threadPool.submit(runnable0);
    }
}

