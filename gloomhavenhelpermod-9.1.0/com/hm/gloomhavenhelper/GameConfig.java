package com.hm.gloomhavenhelper;

public class GameConfig {
   public boolean server;
   public int serverPort = 58888;
   public boolean client;
   public String clientHost = "192.168.0.14";
   public int clientPort = 58888;
   public boolean help = true;
   public boolean abilityNumbers;
   public boolean showTitle = false;
   public boolean fullscreen;
   public boolean hideMonsters;
   public boolean hpDrag = true;
   public int toasts;
   public boolean welcome;
   public boolean zoomReset = true;
   public float zoom;
   public boolean autoScroll;
   public String language;

   public boolean isRussian() {
      return this.language.equals("ru");
   }

   public boolean isJapanese() {
      return this.language.equals("ja");
   }
}
