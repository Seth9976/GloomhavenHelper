package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Queue;
import com.esotericsoftware.gloomhavenhelper.network.Message;
import com.esotericsoftware.gloomhavenhelper.network.Network;
import com.esotericsoftware.gloomhavenhelper.util.Output;
import com.esotericsoftware.gloomhavenhelper.util.Serialization;
import com.esotericsoftware.minlog.Log;
import java.util.Locale;

public class Game {
    public static class Insets {
        public float bottom;
        public float left;
        public float right;
        public float top;

        public Insets(float f, float f1, float f2, float f3) {
            this.top = f;
            this.bottom = f1;
            this.left = f2;
            this.right = f3;
        }

        @Override
        public String toString() {
            return "Insets [top=" + this.top + ", bottom=" + this.bottom + ", left=" + this.left + ", right=" + this.right + "]";
        }
    }

    protected static Json json;
    Preferences preferences;
    public int undoIndex;
    public Queue undos;

    static {
        Game.json = new Json();
        Game.json.setUsePrototypes(false);
        Game.json.setIgnoreUnknownFields(true);
    }

    public Game() {
        this.undos = new Queue(16);
        App.gloom = new GloomhavenHelper();
    }

    public boolean canOpenURL() {
        return false;
    }

    public boolean canPurchase() {
        return false;
    }

    public void close() {
    }

    public Object currentThread() {
        return null;
    }

    public String defaultLanguage() {
        String s = Game.languageCodeToName(System.getProperty("user.language.format"));
        if(s == null) {
            s = Game.languageCodeToName("en");
        }
        if(s == null) {
            try {
                s = Game.languageCodeToName(Locale.getDefault().getLanguage());
            }
            catch(Throwable unused_ex) {
            }
        }
        return s == null ? "en" : s;
    }

    public Insets getInsets() {
        return new Insets(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void initialize() {
        this.preferences = Gdx.app.getPreferences("ghh");
    }

    protected boolean isEqual(byte[] arr_b, byte[] arr_b1, int v) {
        for(int v1 = 4; v1 < v; ++v1) {
            if(arr_b[v1] != arr_b1[v1]) {
                return false;
            }
        }
        return true;
    }

    public boolean isPurchased() {
        return true;
    }

    public static String languageCodeToName(String s) {
        if(s != null) {
            if(s.contains("-")) {
                s = s.split("-")[0];
            }
            if(!s.equalsIgnoreCase("cze") && !s.equalsIgnoreCase("ces") && !s.equalsIgnoreCase("cz")) {
                if(!s.equalsIgnoreCase("pol") && !s.equalsIgnoreCase("pl")) {
                    if(!s.equalsIgnoreCase("kor") && !s.equalsIgnoreCase("ko")) {
                        if(!s.equalsIgnoreCase("jpn") && !s.equalsIgnoreCase("ja")) {
                            if(!s.equalsIgnoreCase("rus") && !s.equalsIgnoreCase("ru")) {
                                if(!s.equalsIgnoreCase("spa") && !s.equalsIgnoreCase("es")) {
                                    if(!s.equalsIgnoreCase("por") && !s.equalsIgnoreCase("pt")) {
                                        if(!s.equalsIgnoreCase("hun") && !s.equalsIgnoreCase("hu")) {
                                            if(!s.equalsIgnoreCase("ger") && !s.equalsIgnoreCase("deu") && !s.equalsIgnoreCase("de")) {
                                                if(!s.equalsIgnoreCase("fra") && !s.equalsIgnoreCase("fre") && !s.equalsIgnoreCase("fr")) {
                                                    return !s.equalsIgnoreCase("ita") && !s.equalsIgnoreCase("it") ? null : "it";
                                                }
                                                return "fr";
                                            }
                                            return "de";
                                        }
                                        return "hu";
                                    }
                                    return "pt";
                                }
                                return "es";
                            }
                            return "ru";
                        }
                        return "ja";
                    }
                    return "ko";
                }
                return "pl";
            }
            return "cz";
        }
        return null;
    }

    public void loadConfig() {
        String s = this.preferences.getString("config", null);
        if(s != null) {
            try {
                App.config = (GameConfig)Game.json.fromJson(GameConfig.class, s);
            }
            catch(Exception exception0) {
                if(Log.ERROR) {
                    Log.error("ghh", "Unable to read game config preferences.", exception0);
                }
            }
        }
        if(App.config == null) {
            App.config = new GameConfig();
        }
        if(App.config.language == null) {
            App.config.language = "en";
        }
    }

    public int loadState(byte[] arr_b, boolean z) {
        App.state.ignoreChanges = true;
        App.stage.cancelTouchFocus();
        App.state.ignoreChanges = false;
        byte[] arr_b1 = Serialization.input.getBuffer();
        Serialization.input.setBuffer(arr_b);
        try {
            int v1 = Serialization.input.readInt();
            App.state = Serialization.read(Serialization.input);
            App.state.changeNumber = v1;
            App.state.apply(true);
            Serialization.lastOutput.clear();
            Serialization.lastOutput.writeBytes(arr_b, 0, ((int)Serialization.input.total()));
            App.game.saveState(Serialization.lastOutput.getBuffer(), Serialization.lastOutput.position());
            if(z) {
                this.storeUndo();
            }
            return Serialization.input.position();
        }
        catch(Exception exception0) {
            throw new RuntimeException("Unable to read game state.", exception0);
        }
        finally {
            Serialization.input.setBuffer(arr_b1);
        }
    }

    public void loadState() {
        String s = this.preferences.getString("state", null);
        if(s != null) {
            byte[] arr_b = Serialization.input.getBuffer();
            byte[] arr_b1 = Base64Coder.decode(s);
            Serialization.input.setBuffer(arr_b1);
            try {
                int v1 = Serialization.input.readInt();
                App.state = Serialization.read(Serialization.input);
                App.state.changeNumber = v1;
                App.state.apply(false);
                Serialization.lastOutput.clear();
                Serialization.lastOutput.writeBytes(Serialization.input.getBuffer(), 0, ((int)Serialization.input.total()));
                this.storeUndo();
            }
            catch(Exception exception0) {
                if(Log.ERROR) {
                    Log.error("ghh", "Unable to load game state from file.", exception0);
                }
                App.toast("Unable to load game state!");
                this.stateLoaded(false);
                return;
            }
            finally {
                Serialization.input.setBuffer(arr_b);
            }
            return;
        }
        this.stateLoaded(true);
    }

    public void oom() {
    }

    public void openURL(String s) {
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

    public void saveConfig() {
        Network.update();
        try {
            this.preferences.putString("config", "null");
            this.preferences.flush();
        }
        catch(Exception exception0) {
            if(Log.ERROR) {
                Log.error("ghh", "Unable to write game config preferences.", exception0);
            }
        }
    }

    public void saveState(boolean z, byte[] arr_b) {
        Serialization.output.clear();
        Serialization.output.writeInt(App.state.changeNumber + 1);
        Serialization.write(Serialization.output, App.state);
        int v = Serialization.output.position();
        if(v == Serialization.lastOutput.position() && this.isEqual(Serialization.lastOutput.getBuffer(), Serialization.output.getBuffer(), v)) {
            if(Log.TRACE) {
                Log.trace("ghh", "State has not changed.");
            }
            return;
        }
        if(App.config.server || App.config.client) {
            byte[] arr_b1 = Network.combine(Serialization.output, arr_b, 0);
            Network.send(Message.gameState, arr_b1, 0, arr_b1.length);
        }
        ++App.state.changeNumber;
        this.preferences.putString("config", "null");
        this.preferences.flush();
        this.saveState(Serialization.output.getBuffer(), Serialization.output.position());
        Output output0 = Serialization.lastOutput;
        Serialization.lastOutput = Serialization.output;
        Serialization.output = output0;
        if(z) {
            this.storeUndo();
        }
    }

    public void saveState(byte[] arr_b, int v) {
        this.preferences.putString("state", new String(Base64Coder.encode(arr_b, 0, v, Base64Coder.regularMap.getEncodingMap())));
        this.preferences.flush();
    }

    public void showAd() {
    }

    protected void stateLoaded(boolean z) {
        App.gloom.playerRows.clear();
        App.gloom.monsterRows.clear();
        App.gloom.rows.clearChildren();
        App.state = new GameState();
        App.state.shuffleAttackModifiers(false);
        App.state.apply(false);
        if(!z) {
            App.state.ignoreChanges = true;
        }
        App.state.changed();
        if(!z) {
            App.state.ignoreChanges = false;
        }
    }

    void storeUndo() {
        while(this.undoIndex > 0 && !this.undos.isEmpty()) {
            this.undos.removeFirst();
            --this.undoIndex;
        }
        this.undoIndex = 0;
        if(this.undos.size > 25) {
            this.undos.removeLast();
        }
        Output output0 = new Output(Serialization.lastOutput.position(), -1);
        output0.writeBytes(Serialization.lastOutput.getBuffer(), 0, Serialization.lastOutput.position());
        this.undos.addFirst(output0);
    }

    public void thread(Runnable runnable0) {
    }
}

