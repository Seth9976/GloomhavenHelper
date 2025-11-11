package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.utils.JsonValue;

public class Text {
    public static String addCharacters = "Add Characters";
    public static String addMonsters = "Add Monsters";
    public static String addMonstersCheckbox = "Add monsters";
    public static String addSection = "Add Section";
    public static String baseGame = "Base Game";
    public static String clear = "Clear";
    public static String custom = "Custom";
    public static String documentation = "Documentation";
    public static String draw = "Draw";
    public static String exit = "Exit";
    public static String forgottenCircles = "Forgotten Circles";
    public static String hideNames = "Hide Names";
    public static String jawsOfTheLion = "Jaws of the Lion";
    public static String nextRound = "Next Round";
    public static String ok = "OK";
    public static String redo = "Redo";
    public static String removeAds = "Remove Ads";
    public static String removeAll = "Remove All";
    public static String removeCharacters = "Remove Characters";
    public static String removeMonsters = "Remove Monsters";
    public static String setScenario = "Set Scenario";
    public static String settings = "Settings";
    public static String showNames = "Show Names";
    public static String solo = "Solo";
    public static String split = "Split";
    public static String undo = "Undo";

    static {
    }

    public static void loadText(JsonValue jsonValue0) {
        Text.removeAds = jsonValue0.getString("removeAds");
        Text.undo = jsonValue0.getString("undo");
        Text.redo = jsonValue0.getString("redo");
        Text.setScenario = jsonValue0.getString("setScenario");
        Text.addSection = jsonValue0.getString("addSection");
        Text.addMonsters = jsonValue0.getString("addMonsters");
        Text.removeMonsters = jsonValue0.getString("removeMonsters");
        Text.addCharacters = jsonValue0.getString("addCharacters");
        Text.removeCharacters = jsonValue0.getString("removeCharacters");
        Text.settings = jsonValue0.getString("settings");
        Text.documentation = jsonValue0.getString("documentation");
        Text.exit = jsonValue0.getString("exit");
        Text.ok = jsonValue0.getString("ok");
        Text.clear = jsonValue0.getString("clear");
        Text.custom = jsonValue0.getString("custom");
        Text.removeAll = jsonValue0.getString("removeAll");
        Text.solo = jsonValue0.getString("solo");
        Text.addMonstersCheckbox = jsonValue0.getString("addMonstersCheckbox");
        Text.baseGame = jsonValue0.getString("baseGame");
        Text.forgottenCircles = jsonValue0.getString("forgottenCircles");
        Text.jawsOfTheLion = jsonValue0.getString("jawsOfTheLion");
        Text.showNames = jsonValue0.getString("showNames");
        Text.hideNames = jsonValue0.getString("hideNames");
        Text.nextRound = jsonValue0.getString("nextRound");
        Text.draw = jsonValue0.getString("draw");
        Text.split = jsonValue0.getString("split");
    }
}

