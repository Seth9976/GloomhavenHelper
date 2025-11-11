package com.hm.gloomhavenhelper;

import com.badlogic.gdx.utils.JsonValue;

public class Text {
   public static String removeAds = "Remove Ads";
   public static String undo = "Undo";
   public static String redo = "Redo";
   public static String setScenario = "Set Scenario";
   public static String addSection = "Add Section";
   public static String addMonsters = "Add Monsters";
   public static String removeMonsters = "Remove Monsters";
   public static String addCharacters = "Add Characters";
   public static String removeCharacters = "Remove Characters";
   public static String settings = "Settings";
   public static String documentation = "Documentation";
   public static String exit = "Exit";
   public static String ok = "OK";
   public static String clear = "Clear";
   public static String custom = "Custom";
   public static String removeAll = "Remove All";
   public static String solo = "Solo";
   public static String soloFH = "FH Solo";
   public static String addMonstersCheckbox = "Add monsters";
   public static String baseGame = "Base Game";
   public static String forgottenCircles = "Forgotten Circles";
   public static String jawsOfTheLion = "Jaws of the Lion";
   public static String showNames = "Show Names";
   public static String hideNames = "Hide Names";
   public static String nextRound = "Next Round";
   public static String draw = "Draw";
   public static String split = "Split";

   public static void loadText(JsonValue text) {
      removeAds = text.getString("removeAds");
      undo = text.getString("undo");
      redo = text.getString("redo");
      setScenario = text.getString("setScenario");
      addSection = text.getString("addSection");
      addMonsters = text.getString("addMonsters");
      removeMonsters = text.getString("removeMonsters");
      addCharacters = text.getString("addCharacters");
      removeCharacters = text.getString("removeCharacters");
      settings = text.getString("settings");
      documentation = text.getString("documentation");
      exit = text.getString("exit");
      ok = text.getString("ok");
      clear = text.getString("clear");
      custom = text.getString("custom");
      removeAll = text.getString("removeAll");
      solo = text.getString("solo");
      soloFH = text.getString("soloFH");
      addMonstersCheckbox = text.getString("addMonstersCheckbox");
      baseGame = text.getString("baseGame");
      forgottenCircles = text.getString("forgottenCircles");
      jawsOfTheLion = text.getString("jawsOfTheLion");
      showNames = text.getString("showNames");
      hideNames = text.getString("hideNames");
      nextRound = text.getString("nextRound");
      draw = text.getString("draw");
      split = text.getString("split");
   }
}
