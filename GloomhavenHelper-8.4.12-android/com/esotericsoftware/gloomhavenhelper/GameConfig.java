package com.esotericsoftware.gloomhavenhelper;

public class GameConfig {
    public boolean abilityNumbers;
    public boolean autoScroll;
    public boolean client;
    public String clientHost;
    public int clientPort;
    public boolean fullscreen;
    public boolean help;
    public boolean hideMonsters;
    public boolean hpDrag;
    public String language;
    public boolean server;
    public int serverPort;
    public int toasts;
    public boolean welcome;
    public float zoom;
    public boolean zoomReset;

    public GameConfig() {
        this.serverPort = 0xE608;
        this.clientHost = "192.168.0.14";
        this.clientPort = 0xE608;
        this.help = true;
        this.hpDrag = true;
        this.zoomReset = true;
    }

    public boolean isJapanese() {
        return this.language.equals("ja");
    }

    public boolean isRussian() {
        return this.language.equals("ru");
    }
}

