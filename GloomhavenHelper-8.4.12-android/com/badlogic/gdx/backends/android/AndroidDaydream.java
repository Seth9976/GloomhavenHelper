package com.badlogic.gdx.backends.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Debug;
import android.os.Handler;
import android.os.Looper;
import android.service.dreams.DreamService;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout.LayoutParams;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Clipboard;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.SnapshotArray;

@TargetApi(17)
public class AndroidDaydream extends DreamService implements AndroidApplicationBase {
    protected ApplicationLogger applicationLogger;
    protected AndroidAudio audio;
    protected AndroidClipboard clipboard;
    protected final Array executedRunnables;
    protected AndroidFiles files;
    protected boolean firstResume;
    protected AndroidGraphics graphics;
    protected Handler handler;
    protected AndroidInput input;
    protected final SnapshotArray lifecycleListeners;
    protected ApplicationListener listener;
    protected int logLevel;
    protected AndroidNet net;
    protected final Array runnables;

    static {
        GdxNativesLoader.load();
    }

    public AndroidDaydream() {
        this.firstResume = true;
        this.runnables = new Array();
        this.executedRunnables = new Array();
        this.lifecycleListeners = new SnapshotArray(LifecycleListener.class);
        this.logLevel = 2;
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
        this.getFilesDir();
        return new DefaultAndroidFiles(this.getAssets(), this, true);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public AndroidInput createInput(Application application0, Context context0, Object object0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        return new DefaultAndroidInput(this, this, this.graphics.view, androidApplicationConfiguration0);
    }

    protected FrameLayout.LayoutParams createLayoutParams() {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-1, -1);
        frameLayout$LayoutParams0.gravity = 17;
        return frameLayout$LayoutParams0;
    }

    protected void createWakeLock(boolean z) {
        if(z) {
            this.getWindow().addFlags(0x80);
        }
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
        this.handler.post(new Runnable() {
            @Override
            public void run() {
                AndroidDaydream.this.finish();
            }
        });
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
        return this.getWindow();
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
        return this;
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
        return this.handler;
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
        return new AndroidPreferences(this.getSharedPreferences(s, 0));
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidApplicationBase
    public Array getRunnables() {
        return this.runnables;
    }

    @Override  // com.badlogic.gdx.Application
    public ApplicationType getType() {
        return ApplicationType.Android;
    }

    @Override  // com.badlogic.gdx.Application
    public int getVersion() {
        return Build.VERSION.SDK_INT;
    }

    protected void hideStatusBar(AndroidApplicationConfiguration androidApplicationConfiguration0) {
        if(!androidApplicationConfiguration0.hideStatusBar) {
            return;
        }
        View view0 = this.getWindow().getDecorView();
        view0.setSystemUiVisibility(0);
        view0.setSystemUiVisibility(1);
    }

    private void init(ApplicationListener applicationListener0, AndroidApplicationConfiguration androidApplicationConfiguration0, boolean z) {
        this.setApplicationLogger(new AndroidApplicationLogger());
        ResolutionStrategy resolutionStrategy0 = androidApplicationConfiguration0.resolutionStrategy == null ? new FillResolutionStrategy() : androidApplicationConfiguration0.resolutionStrategy;
        this.graphics = new AndroidGraphics(this, androidApplicationConfiguration0, resolutionStrategy0);
        this.input = this.createInput(this, this, this.graphics.view, androidApplicationConfiguration0);
        this.audio = this.createAudio(this, androidApplicationConfiguration0);
        this.files = this.createFiles();
        this.net = new AndroidNet(this, androidApplicationConfiguration0);
        this.listener = applicationListener0;
        this.handler = new Handler();
        this.clipboard = new AndroidClipboard(this);
        this.addLifecycleListener(new LifecycleListener() {
            @Override  // com.badlogic.gdx.LifecycleListener
            public void dispose() {
                AndroidDaydream.this.audio.dispose();
                AndroidDaydream.this.audio = null;
            }

            @Override  // com.badlogic.gdx.LifecycleListener
            public void pause() {
                AndroidDaydream.this.audio.pause();
            }

            @Override  // com.badlogic.gdx.LifecycleListener
            public void resume() {
                AndroidDaydream.this.audio.resume();
            }
        });
        Gdx.app = this;
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        if(!z) {
            this.setFullscreen(true);
            this.setContentView(this.graphics.getView(), this.createLayoutParams());
        }
        this.createWakeLock(androidApplicationConfiguration0.useWakelock);
        this.hideStatusBar(androidApplicationConfiguration0);
        if(this.getResources().getConfiguration().keyboard != 1) {
            this.input.setKeyboardAvailable(true);
        }
    }

    public void initialize(ApplicationListener applicationListener0) {
        this.initialize(applicationListener0, new AndroidApplicationConfiguration());
    }

    public void initialize(ApplicationListener applicationListener0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.init(applicationListener0, androidApplicationConfiguration0, false);
    }

    public View initializeForView(ApplicationListener applicationListener0) {
        return this.initializeForView(applicationListener0, new AndroidApplicationConfiguration());
    }

    public View initializeForView(ApplicationListener applicationListener0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.init(applicationListener0, androidApplicationConfiguration0, true);
        return this.graphics.getView();
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

    @Override  // android.app.Service
    public void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.input.setKeyboardAvailable(configuration0.hardKeyboardHidden == 1);
    }

    @Override  // android.service.dreams.DreamService
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override  // android.service.dreams.DreamService
    public void onDreamingStarted() {
        Gdx.app = this;
        Gdx.input = this.getInput();
        Gdx.audio = this.getAudio();
        Gdx.files = this.getFiles();
        Gdx.graphics = this.getGraphics();
        Gdx.net = this.getNet();
        this.input.onDreamingStarted();
        AndroidGraphics androidGraphics0 = this.graphics;
        if(androidGraphics0 != null) {
            androidGraphics0.onResumeGLSurfaceView();
        }
        if(this.firstResume) {
            this.firstResume = false;
        }
        else {
            this.graphics.resume();
        }
        super.onDreamingStarted();
    }

    @Override  // android.service.dreams.DreamService
    public void onDreamingStopped() {
        boolean z = this.graphics.isContinuousRendering();
        this.graphics.setContinuousRendering(true);
        this.graphics.pause();
        this.input.onDreamingStopped();
        this.graphics.clearManagedCaches();
        this.graphics.destroy();
        this.graphics.setContinuousRendering(z);
        this.graphics.onPauseGLSurfaceView();
        super.onDreamingStopped();
    }

    @Override  // com.badlogic.gdx.Application
    public void postRunnable(Runnable runnable0) {
        synchronized(this.runnables) {
            this.runnables.add(runnable0);
            Gdx.graphics.requestRendering();
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
    public void useImmersiveMode(boolean z) {
        throw new UnsupportedOperationException();
    }
}

