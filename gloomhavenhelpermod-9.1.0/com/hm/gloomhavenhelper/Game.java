package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Queue;
import com.hm.gloomhavenhelper.network.Message;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Output;
import com.hm.gloomhavenhelper.util.Serialization;
import com.hm.minlog.Log;
import java.util.Locale;

public class Game {
   protected static Json json = new Json();
   Preferences preferences;
   public Queue undos = new Queue(16);
   public int undoIndex;

   public Game() {
      App.gloom = new GloomhavenHelper();
   }

   public void initialize() {
      this.preferences = Gdx.app.getPreferences("ghh");
   }

   public void loadConfig() {
      String configText = this.preferences.getString("config", null);
      if (configText != null) {
         try {
            App.config = (GameConfig)json.fromJson(GameConfig.class, configText);
         } catch (Exception var3) {
            if (Log.ERROR) {
               Log.error("ghh", "Unable to read game config preferences.", var3);
            }
         }
      }

      if (App.config == null) {
         App.config = new GameConfig();
      }

      if (App.config.language == null) {
         App.config.language = "en";
      }
   }

   public void close() {
   }

   public void saveConfig() {
      App.gameThread();
      Network.update();

      try {
         this.preferences.putString("config", json.toJson(App.config));
         this.preferences.flush();
      } catch (Exception var2) {
         if (Log.ERROR) {
            Log.error("ghh", "Unable to write game config preferences.", var2);
         }
      }
   }

   public void loadState() {
      App.gameThread();
      String stateText = this.preferences.getString("state", null);
      if (stateText != null) {
         byte[] oldBuffer = Serialization.input.getBuffer();
         Serialization.input.setBuffer(Base64Coder.decode(stateText));

         try {
            int changeNumber = Serialization.input.readInt();
            App.state = Serialization.read(Serialization.input);
            App.state.changeNumber = changeNumber;
            App.state.apply(false);
            Serialization.lastOutput.clear();
            Serialization.lastOutput.writeBytes(Serialization.input.getBuffer(), 0, (int)Serialization.input.total());
            this.storeUndo();
            return;
         } catch (Exception var7) {
            if (Log.ERROR) {
               Log.error("ghh", "Unable to load game state from file.", var7);
            }

            App.toast("Unable to load game state!");
            this.stateLoaded(false);
         } finally {
            Serialization.input.setBuffer(oldBuffer);
         }
      } else {
         this.stateLoaded(true);
      }
   }

   public int loadState(byte[] bytes, boolean storeUndo) {
      App.gameThread();
      App.state.ignoreChanges = true;
      App.stage.cancelTouchFocus();
      App.state.ignoreChanges = false;
      byte[] oldBuffer = Serialization.input.getBuffer();
      Serialization.input.setBuffer(bytes);

      int var5;
      try {
         int changeNumber = Serialization.input.readInt();
         App.state = Serialization.read(Serialization.input);
         App.state.changeNumber = changeNumber;
         App.state.apply(true);
         Serialization.lastOutput.clear();
         Serialization.lastOutput.writeBytes(bytes, 0, (int)Serialization.input.total());
         App.game.saveState(Serialization.lastOutput.getBuffer(), Serialization.lastOutput.position());
         if (storeUndo) {
            this.storeUndo();
         }

         var5 = Serialization.input.position();
      } catch (Exception var9) {
         throw new RuntimeException("Unable to read game state.", var9);
      } finally {
         Serialization.input.setBuffer(oldBuffer);
      }

      return var5;
   }

   void storeUndo() {
      while (this.undoIndex > 0 && !this.undos.isEmpty()) {
         this.undos.removeFirst();
         this.undoIndex--;
      }

      this.undoIndex = 0;
      if (this.undos.size > 25) {
         this.undos.removeLast();
      }

      Output undo = new Output(Serialization.lastOutput.position(), -1);
      undo.writeBytes(Serialization.lastOutput.getBuffer(), 0, Serialization.lastOutput.position());
      this.undos.addFirst(undo);
   }

   protected void stateLoaded(boolean save) {
      App.gloom.playerRows.clear();
      App.gloom.monsterRows.clear();
      App.gloom.rows.clearChildren();
      App.state = new GameState();
      App.state.shuffleAttackModifiers(false);
      App.state.apply(false);
      if (!save) {
         App.state.ignoreChanges = true;
      }

      App.state.changed();
      if (!save) {
         App.state.ignoreChanges = false;
      }
   }

   public void saveState(boolean storeUndo, byte[] animate) {
      Serialization.output.clear();
      Serialization.output.writeInt(App.state.changeNumber + 1);
      Serialization.write(Serialization.output, App.state);
      int count = Serialization.output.position();
      if (count == Serialization.lastOutput.position() && this.isEqual(Serialization.lastOutput.getBuffer(), Serialization.output.getBuffer(), count)) {
         if (Log.TRACE) {
            Log.trace("ghh", "State has not changed.");
         }
      } else {
         if (App.config.server || App.config.client) {
            byte[] bytes = Network.combine(Serialization.output, animate, 0);
            Network.send(Message.gameState, bytes, 0, bytes.length);
         }

         App.state.changeNumber++;
         this.preferences.putString("config", json.toJson(App.config));
         this.preferences.flush();
         this.saveState(Serialization.output.getBuffer(), Serialization.output.position());
         Output temp = Serialization.lastOutput;
         Serialization.lastOutput = Serialization.output;
         Serialization.output = temp;
         if (storeUndo) {
            this.storeUndo();
         }
      }
   }

   public void saveState(byte[] bytes, int count) {
      this.preferences.putString("state", new String(Base64Coder.encode(bytes, 0, count, Base64Coder.regularMap.getEncodingMap())));
      this.preferences.flush();
   }

   protected boolean isEqual(byte[] buffer1, byte[] buffer2, int size) {
      for (int i = 4; i < size; i++) {
         if (buffer1[i] != buffer2[i]) {
            return false;
         }
      }

      return true;
   }

   public Object currentThread() {
      return null;
   }

   public void thread(Runnable runnable) {
   }

   public boolean canOpenURL() {
      return false;
   }

   public void openURL(String string) {
   }

   public void oom() {
   }

   public boolean isPurchased() {
      return true;
   }

   public boolean canPurchase() {
      return false;
   }

   public void purchase() {
   }

   public void purchased() {
      Gdx.app.postRunnable(new Runnable() {
         @Override
         public void run() {
            App.gloom.mainMenu.populate();
         }
      });
   }

   public void showAd() {
   }

   public Game.Insets getInsets() {
      return new Game.Insets(0.0F, 0.0F, 0.0F, 0.0F);
   }

   public String defaultLanguage() {
      String language = languageCodeToName(System.getProperty("user.language.format"));
      if (language == null) {
         language = languageCodeToName(System.getProperty("user.language"));
      }

      if (language == null) {
         try {
            language = languageCodeToName(Locale.getDefault().getLanguage());
         } catch (Throwable var3) {
         }
      }

      return language == null ? "en" : language;
   }

   public static String languageCodeToName(String code) {
      if (code != null) {
         if (code.contains("-")) {
            code = code.split("-")[0];
         }

         if (code.equalsIgnoreCase("cze") || code.equalsIgnoreCase("ces") || code.equalsIgnoreCase("cz")) {
            return "cz";
         }

         if (code.equalsIgnoreCase("pol") || code.equalsIgnoreCase("pl")) {
            return "pl";
         }

         if (code.equalsIgnoreCase("kor") || code.equalsIgnoreCase("ko")) {
            return "ko";
         }

         if (code.equalsIgnoreCase("jpn") || code.equalsIgnoreCase("ja")) {
            return "ja";
         }

         if (code.equalsIgnoreCase("rus") || code.equalsIgnoreCase("ru")) {
            return "ru";
         }

         if (code.equalsIgnoreCase("spa") || code.equalsIgnoreCase("es")) {
            return "es";
         }

         if (code.equalsIgnoreCase("por") || code.equalsIgnoreCase("pt")) {
            return "pt";
         }

         if (code.equalsIgnoreCase("hun") || code.equalsIgnoreCase("hu")) {
            return "hu";
         }

         if (code.equalsIgnoreCase("ger") || code.equalsIgnoreCase("deu") || code.equalsIgnoreCase("de")) {
            return "de";
         }

         if (code.equalsIgnoreCase("fra") || code.equalsIgnoreCase("fre") || code.equalsIgnoreCase("fr")) {
            return "fr";
         }

         if (code.equalsIgnoreCase("ita") || code.equalsIgnoreCase("it")) {
            return "it";
         }
      }

      return null;
   }

   static {
      json.setUsePrototypes(false);
      json.setIgnoreUnknownFields(true);
   }

   public static class Insets {
      public float top;
      public float bottom;
      public float left;
      public float right;

      public Insets(float top, float bottom, float left, float right) {
         this.top = top;
         this.bottom = bottom;
         this.left = left;
         this.right = right;
      }

      @Override
      public String toString() {
         return "Insets [top=" + this.top + ", bottom=" + this.bottom + ", left=" + this.left + ", right=" + this.right + "]";
      }
   }
}
