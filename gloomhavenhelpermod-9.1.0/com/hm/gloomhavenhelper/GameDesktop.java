package com.hm.gloomhavenhelper;

import com.badlogic.gdx.files.FileHandle;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Serialization;
import com.hm.minlog.Log;
import java.io.File;
import java.net.URI;
import java.util.Locale;
import java.util.Locale.Category;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

public class GameDesktop extends Game {
   public static final ExecutorService threadPool = newThreadPool(1, 60, "Pool", true);
   public static FileHandle configFile = new FileHandle(new File(System.getProperty("user.home"), ".ghh/config"));
   public static FileHandle configFileTemp = new FileHandle(new File(System.getProperty("user.home"), ".ghh/config.temp"));
   public static FileHandle stateFile = new FileHandle(new File(System.getProperty("user.home"), ".ghh/state"));
   public static FileHandle stateFileTemp = new FileHandle(new File(System.getProperty("user.home"), ".ghh/state.temp"));

   @Override
   public void loadConfig() {
      Log.info("ghh", "Gloomhaven Helper 8.4.12");
      if (configFile.exists()) {
         Log.info("ghh", "Loading game state: " + configFile);

         try {
            App.config = (GameConfig)json.fromJson(GameConfig.class, configFile);
         } catch (Exception var2) {
            if (Log.ERROR) {
               Log.error("ghh", "Unable to read game config file.", var2);
            }
         }
      } else {
         Log.info("ghh", "New game state: " + configFile);
      }

      if (App.config == null) {
         App.config = new GameConfig();
      }

      if (App.config.language == null) {
         App.config.language = "en";
      }
   }

   @Override
   public void saveConfig() {
      Network.update();
      configFile.parent().mkdirs();

      try {
         json.toJson(App.config, configFileTemp.writer(false));
         configFileTemp.copyTo(configFile);
         configFileTemp.delete();
      } catch (Exception var2) {
         if (Log.ERROR) {
            Log.error("ghh", "Unable to write game config file.", var2);
         }
      }
   }

   @Override
   public synchronized void loadState() {
      App.gameThread();
      if (stateFile.exists()) {
         byte[] bytes = stateFile.readBytes();
         Serialization.input.setBuffer(bytes);

         try {
            int changeNumber = Serialization.input.readInt();
            App.state = Serialization.read(Serialization.input);
            App.state.changeNumber = changeNumber;
            App.state.apply(false);
            Serialization.lastOutput.clear();
            Serialization.lastOutput.writeBytes(bytes, 0, (int)Serialization.input.total());
            this.storeUndo();
         } catch (Exception var3) {
            if (Log.ERROR) {
               Log.error("ghh", "Unable to load game state from file.", var3);
            }

            App.toast("Unable to load game state!");
            this.stateLoaded(false);
         }
      } else {
         this.stateLoaded(true);
      }
   }

   @Override
   public synchronized void saveState(byte[] bytes, int count) {
      try {
         stateFileTemp.writeBytes(bytes, 0, count, false);
         stateFileTemp.copyTo(stateFile);
         stateFileTemp.delete();
      } catch (Exception var4) {
         if (Log.ERROR) {
            Log.error("ghh", "Unable to write game state file.", var4);
         }
      }
   }

   @Override
   public Object currentThread() {
      return Thread.currentThread();
   }

   @Override
   public void thread(Runnable runnable) {
      threadPool.submit(runnable);
   }

   @Override
   public void close() {
      Runtime.getRuntime().halt(0);
   }

   @Override
   public boolean canOpenURL() {
      if (System.getProperty("os.name").contains("Mac")) {
         return false;
      } else {
         try {
            Object desktop = Class.forName("java.awt.Desktop").getMethod("getDesktop").invoke(null);
            return (Boolean)desktop.getClass().getMethod("isDesktopSupported").invoke(desktop);
         } catch (Throwable var2) {
            return false;
         }
      }
   }

   @Override
   public void openURL(String url) {
      try {
         Object desktop = Class.forName("java.awt.Desktop").getMethod("getDesktop").invoke(null);
         desktop.getClass().getMethod("browse", URI.class).invoke(desktop, new URI(url));
      } catch (Throwable var3) {
         if (Log.ERROR) {
            Log.error("ghh", "Unable to open URL: " + url, var3);
         }
      }
   }

   @Override
   public String defaultLanguage() {
      String language = Game.languageCodeToName(System.getProperty("user.language.format"));
      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault(Category.FORMAT).getISO3Language());
         } catch (Throwable var8) {
         }
      }

      if (language == null) {
         language = Game.languageCodeToName(System.getProperty("user.language"));
      }

      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault(Category.DISPLAY).getISO3Language());
         } catch (Throwable var7) {
         }
      }

      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault().getISO3Language());
         } catch (Throwable var6) {
         }
      }

      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault(Category.FORMAT).getLanguage());
         } catch (Throwable var5) {
         }
      }

      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault(Category.DISPLAY).getLanguage());
         } catch (Throwable var4) {
         }
      }

      if (language == null) {
         try {
            language = Game.languageCodeToName(Locale.getDefault().getLanguage());
         } catch (Throwable var3) {
         }
      }

      return language == null ? "en" : language;
   }

   public static ThreadPoolExecutor newThreadPool(int maxThreads, int liveSeconds, final String name, final boolean daemon) {
      ThreadPoolExecutor pool = new ThreadPoolExecutor(maxThreads, maxThreads, liveSeconds, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() {
         @Override
         public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, name);
            thread.setDaemon(daemon);
            return thread;
         }
      }, new AbortPolicy());
      pool.allowCoreThreadTimeOut(true);
      return pool;
   }
}
