package com.badlogic.gdx.backends.android;

import android.view.View.OnSystemUiVisibilityChangeListener;

public class AndroidVisibilityListener {
    public void createListener(AndroidApplicationBase androidApplicationBase0) {
        try {
            androidApplicationBase0.getApplicationWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override  // android.view.View$OnSystemUiVisibilityChangeListener
                public void onSystemUiVisibilityChange(int v) {
                    androidApplicationBase0.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            com.badlogic.gdx.backends.android.AndroidVisibilityListener.1.this.val$application.useImmersiveMode(true);
                        }
                    });
                }
            });
        }
        catch(Throwable throwable0) {
            androidApplicationBase0.log("AndroidApplication", "Can\'t create OnSystemUiVisibilityChangeListener, unable to use immersive mode.", throwable0);
        }
    }
}

