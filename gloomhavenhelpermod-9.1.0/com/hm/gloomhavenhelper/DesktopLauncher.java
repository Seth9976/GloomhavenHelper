package com.hm.gloomhavenhelper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.glutils.HdpiMode;
import com.hm.minlog.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class DesktopLauncher extends ApplicationAdapter {
   public static void main(String[] args) throws Exception {
      File logFile = new File(System.getProperty("user.home"), ".ghh/log.txt");
      logFile.getParentFile().mkdirs();

      try {
         FileOutputStream output = new FileOutputStream(logFile);
         System.setOut(new PrintStream(new MultiplexOutputStream(System.out, output), true));
         System.setErr(new PrintStream(new MultiplexOutputStream(System.err, output), true));
      } catch (Throwable var4) {
         Log.error("ghh", "Unable to write log file: " + logFile.getAbsolutePath(), var4);
      }

      System.setProperty("org.lwjgl.system.allocator", "system");
      Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
      config.setTitle("Gloomhaven Helper");
      Graphics.DisplayMode screen = Lwjgl3ApplicationConfiguration.getDisplayMode();
      config.setWindowedMode(Math.min(1113, screen.width - 25), Math.min(1024, screen.height - 80));
      config.disableAudio(true);
      config.setHdpiMode(HdpiMode.Pixels);
      config.setWindowIcon("icon.png");
      App.args = args;
      App.game = new GameDesktop();
      packAtlas();
      new Lwjgl3Application(App.gloom, config);
   }

   private static void packAtlas() {
   }
}
