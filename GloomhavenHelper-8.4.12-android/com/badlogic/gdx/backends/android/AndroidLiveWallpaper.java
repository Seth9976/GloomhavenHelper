package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.ApplicationLogger;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.android.surfaceview.FillResolutionStrategy;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.SnapshotArray;

public class AndroidLiveWallpaper implements AndroidApplicationBase {
    protected ApplicationLogger applicationLogger;
    protected AndroidAudio audio;
    protected AndroidClipboard clipboard;
    protected final Array executedRunnables;
    protected AndroidFiles files;
    protected boolean firstResume;
    protected AndroidGraphicsLiveWallpaper graphics;
    protected AndroidInput input;
    protected final SnapshotArray lifecycleListeners;
    protected ApplicationListener listener;
    protected int logLevel;
    protected AndroidNet net;
    protected final Array runnables;
    protected AndroidLiveWallpaperService service;
    protected volatile Color[] wallpaperColors;

    static {
        GdxNativesLoader.load();
    }

    public AndroidLiveWallpaper(AndroidLiveWallpaperService androidLiveWallpaperService0) {
        this.firstResume = true;
        this.runnables = new Array();
        this.executedRunnables = new Array();
        this.lifecycleListeners = new SnapshotArray(LifecycleListener.class);
        this.logLevel = 2;
        this.wallpaperColors = null;
        this.service = androidLiveWallpaperService0;
    }

    @Override  // com.badlogic.gdx.Application
    public void addLifecycleListener(LifecycleListener lifecycleListener0) {
        synchronized(this.lifecycleListeners) {
            this.lifecycleListeners.add(lifecycleListener0);
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidAudio createAudio(Context context0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        return new DefaultAndroidAudio(context0, androidApplicationConfiguration0);
    }

    protected AndroidFiles createFiles() {
        this.getService().getFilesDir();
        return new DefaultAndroidFiles(this.getService().getAssets(), this.getService(), true);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidInput createInput(Application application0, Context context0, Object object0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        return new DefaultAndroidInput(this, this.getService(), this.graphics.view, androidApplicationConfiguration0);
    }

    @Override  // com.badlogic.gdx.Application
    public void debug(String s, String s1) {
        if(this.logLevel >= 3) {
            this.getApplicationLogger().debug(s, s1);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void debug(String s, String s1, Throwable throwable0) {
        if(this.logLevel >= 3) {
            this.getApplicationLogger().debug(s, s1, throwable0);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void error(String s, String s1) {
        if(this.logLevel >= 1) {
            this.getApplicationLogger().error(s, s1);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void error(String s, String s1, Throwable throwable0) {
        if(this.logLevel >= 1) {
            this.getApplicationLogger().error(s, s1, throwable0);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void exit() {
    }

    @Override  // com.badlogic.gdx.Application
    public ApplicationListener getApplicationListener() {
        return this.listener;
    }

    @Override  // com.badlogic.gdx.Application
    public ApplicationLogger getApplicationLogger() {
        return this.applicationLogger;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Window getApplicationWindow() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.badlogic.gdx.Application
    public Audio getAudio() {
        return this.audio;
    }

    @Override  // com.badlogic.gdx.Application
    public Clipboard getClipboard() {
        return this.clipboard;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Context getContext() {
        return this.service;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Array getExecutedRunnables() {
        return this.executedRunnables;
    }

    @Override  // com.badlogic.gdx.Application
    public Files getFiles() {
        return this.files;
    }

    @Override  // com.badlogic.gdx.Application
    public Graphics getGraphics() {
        return this.graphics;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Handler getHandler() {
        throw new UnsupportedOperationException();
    }

    @Override  // com.badlogic.gdx.Application
    public Input getInput() {
        return this.getInput();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidInput getInput() {
        return this.input;
    }

    @Override  // com.badlogic.gdx.Application
    public long getJavaHeap() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public SnapshotArray getLifecycleListeners() {
        return this.lifecycleListeners;
    }

    @Override  // com.badlogic.gdx.Application
    public int getLogLevel() {
        return this.logLevel;
    }

    @Override  // com.badlogic.gdx.Application
    public long getNativeHeap() {
        return Debug.getNativeHeapAllocatedSize();
    }

    @Override  // com.badlogic.gdx.Application
    public Net getNet() {
        return this.net;
    }

    @Override  // com.badlogic.gdx.Application
    public Preferences getPreferences(String s) {
        return new AndroidPreferences(this.service.getSharedPreferences(s, 0));
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Array getRunnables() {
        return this.runnables;
    }

    public AndroidLiveWallpaperService getService() {
        return this.service;
    }

    @Override  // com.badlogic.gdx.Application
    public ApplicationType getType() {
        return ApplicationType.Android;
    }

    @Override  // com.badlogic.gdx.Application
    public int getVersion() [...] // 潜在的解密器

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public WindowManager getWindowManager() {
        return this.service.getWindowManager();
    }

    public void initialize(ApplicationListener applicationListener0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.setApplicationLogger(new AndroidApplicationLogger());
        ResolutionStrategy resolutionStrategy0 = androidApplicationConfiguration0.resolutionStrategy == null ? new FillResolutionStrategy() : androidApplicationConfiguration0.resolutionStrategy;
        this.graphics = new AndroidGraphicsLiveWallpaper(this, androidApplicationConfiguration0, resolutionStrategy0);
        this.input = this.createInput(this, this.getService(), this.graphics.view, androidApplicationConfiguration0);
        this.audio = this.createAudio(this.getService(), androidApplicationConfiguration0);
        this.files = this.createFiles();
        this.net = new AndroidNet(this, androidApplicationConfiguration0);
        this.listener = applicationListener0;
        this.clipboard = new AndroidClipboard(this.getService());
        Gdx.app = this;
        Gdx.input = this.input;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
        Gdx.net = this.net;
    }

    @Override  // com.badlogic.gdx.Application
    public void log(String s, String s1) {
        if(this.logLevel >= 2) {
            this.getApplicationLogger().log(s, s1);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void log(String s, String s1, Throwable throwable0) {
        if(this.logLevel >= 2) {
            this.getApplicationLogger().log(s, s1, throwable0);
        }
    }

    public void notifyColorsChanged(Color color0, Color color1, Color color2) {
        if(Build.VERSION.SDK_INT < 27) {
            return;
        }
        this.wallpaperColors = new Color[]{new Color(color0), new Color(color1), new Color(color2)};
        AndroidWallpaperEngine androidLiveWallpaperService$AndroidWallpaperEngine0 = this.service.linkedEngine;
        if(androidLiveWallpaperService$AndroidWallpaperEngine0 != null) {
            androidLiveWallpaperService$AndroidWallpaperEngine0.notifyColorsChanged();
        }
    }

    public void onDestroy() {
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper0 = this.graphics;
        if(androidGraphicsLiveWallpaper0 != null) {
            androidGraphicsLiveWallpaper0.onDestroyGLSurfaceView();
        }
        AndroidAudio androidAudio0 = this.audio;
        if(androidAudio0 != null) {
            androidAudio0.dispose();
        }
    }

    public void onPause() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause()");
        }
        this.audio.pause();
        this.input.onPause();
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper0 = this.graphics;
        if(androidGraphicsLiveWallpaper0 != null) {
            androidGraphicsLiveWallpaper0.onPauseGLSurfaceView();
        }
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaper - onPause() done!");
        }
    }

    public void onResume() {
        Gdx.app = this;
        Gdx.input = this.input;
        Gdx.audio = this.audio;
        Gdx.files = this.files;
        Gdx.graphics = this.graphics;
        Gdx.net = this.net;
        this.input.onResume();
        AndroidGraphicsLiveWallpaper androidGraphicsLiveWallpaper0 = this.graphics;
        if(androidGraphicsLiveWallpaper0 != null) {
            androidGraphicsLiveWallpaper0.onResumeGLSurfaceView();
        }
        if(!this.firstResume) {
            this.audio.resume();
            this.graphics.resume();
            return;
        }
        this.firstResume = false;
    }

    @Override  // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable0) {
        synchronized(this.runnables) {
            this.runnables.add(runnable0);
        }
    }

    @Override  // com.badlogic.gdx.Application
    public void removeLifecycleListener(LifecycleListener lifecycleListener0) {
        synchronized(this.lifecycleListeners) {
            this.lifecycleListeners.removeValue(lifecycleListener0, true);
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void runOnUiThread(Runnable runnable0) {
        if(Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(runnable0);
            return;
        }
        runnable0.run();
    }

    @Override  // com.badlogic.gdx.Application
    public void setApplicationLogger(ApplicationLogger applicationLogger0) {
        this.applicationLogger = applicationLogger0;
    }

    @Override  // com.badlogic.gdx.Application
    public void setLogLevel(int v) {
        this.logLevel = v;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void startActivity(Intent intent0) {
        this.service.startActivity(intent0);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public void useImmersiveMode(boolean z) {
        throw new UnsupportedOperationException();
    }
}

