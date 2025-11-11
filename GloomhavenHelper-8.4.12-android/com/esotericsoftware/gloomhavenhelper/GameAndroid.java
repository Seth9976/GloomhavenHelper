package com.esotericsoftware.gloomhavenhelper;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.minlog.Log;

public class GameAndroid extends GameDesktop {
    private AndroidLauncher launcher;

    public GameAndroid(AndroidLauncher androidLauncher0) {
        this.launcher = androidLauncher0;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.GameDesktop
    public boolean canOpenURL() {
        return true;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public boolean canPurchase() {
        return !this.launcher.billing.noAdsPurchased && this.launcher.billing.noAds != null;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.GameDesktop
    public void close() {
        this.launcher.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GameAndroid.this.launcher.getApplicationContext(), "The language has been changed.\nPlease restart Gloomhaven Helper.", 1).show();
                GameAndroid.this.launcher.finish();
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public boolean isPurchased() {
        return this.launcher.billing.noAdsPurchased;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.GameDesktop
    public void loadConfig() {
        GameAndroid.configFile = Gdx.files.local("config");
        GameAndroid.configFileTemp = Gdx.files.local("config.temp");
        super.loadConfig();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.GameDesktop
    public void loadState() {
        GameAndroid.stateFile = Gdx.files.local("state");
        GameAndroid.stateFileTemp = Gdx.files.local("state.temp");
        super.loadState();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void oom() {
        this.launcher.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GameAndroid.this.launcher.getApplicationContext(), "Sorry, your device ran out of memory!\nPlease restart Gloomhaven Helper.", 1).show();
                GameAndroid.this.launcher.finish();
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.GameDesktop
    public void openURL(String s) {
        try {
            Intent intent0 = new Intent("android.intent.action.VIEW", Uri.parse(s));
            this.launcher.startActivity(intent0);
        }
        catch(Throwable throwable0) {
            if(Log.ERROR) {
                Log.error(("Unable to open URL: " + s), throwable0);
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void purchase() {
        if(this.canPurchase()) {
            this.launcher.billing.purchase(this.launcher.billing.noAds);
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void purchased() {
        super.purchased();
        this.launcher.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                GameAndroid.this.launcher.removeAds();
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.Game
    public void showAd() {
        this.launcher.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(GameAndroid.this.launcher.billing.noAdsPurchased) {
                    GameAndroid.this.launcher.removeAds();
                    return;
                }
                if(GameAndroid.this.launcher.fullScreen != null && GameAndroid.this.launcher.fullScreen.isLoaded()) {
                    try {
                        GameAndroid.this.launcher.fullScreen.show();
                    }
                    catch(Throwable unused_ex) {
                    }
                }
            }
        });
    }
}

