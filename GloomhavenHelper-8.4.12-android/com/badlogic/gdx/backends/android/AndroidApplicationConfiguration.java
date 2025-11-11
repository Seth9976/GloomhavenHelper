package com.badlogic.gdx.backends.android;

import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;

public class AndroidApplicationConfiguration {
    public int a;
    public int b;
    public int depth;
    public boolean disableAudio;
    public int g;
    public boolean getTouchEventsForLiveWallpaper;
    public boolean hideStatusBar;
    public int maxNetThreads;
    public int maxSimultaneousSounds;
    public int numSamples;
    public int r;
    public ResolutionStrategy resolutionStrategy;
    public int sensorDelay;
    public int stencil;
    public int touchSleepTime;
    public boolean useAccelerometer;
    public boolean useCompass;
    @Deprecated
    public boolean useGL30;
    public boolean useGyroscope;
    public boolean useImmersiveMode;
    public boolean useRotationVectorSensor;
    public boolean useWakelock;

    public AndroidApplicationConfiguration() {
        this.r = 8;
        this.g = 8;
        this.b = 8;
        this.a = 0;
        this.depth = 16;
        this.stencil = 0;
        this.numSamples = 0;
        this.useAccelerometer = true;
        this.useGyroscope = false;
        this.useCompass = true;
        this.useRotationVectorSensor = false;
        this.sensorDelay = 1;
        this.touchSleepTime = 0;
        this.useWakelock = false;
        this.hideStatusBar = false;
        this.disableAudio = false;
        this.maxSimultaneousSounds = 16;
        this.resolutionStrategy = new FillResolutionStrategy();
        this.getTouchEventsForLiveWallpaper = false;
        this.useImmersiveMode = false;
        this.useGL30 = false;
        this.maxNetThreads = 0x7FFFFFFF;
    }
}

