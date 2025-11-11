package com.badlogic.gdx.backends.android;

import android.app.WallpaperColors;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.service.wallpaper.WallpaperService.Engine;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxNativesLoader;

public abstract class AndroidLiveWallpaperService extends WallpaperService {
    public class AndroidWallpaperEngine extends WallpaperService.Engine {
        protected int engineFormat;
        protected int engineHeight;
        protected boolean engineIsVisible;
        protected int engineWidth;
        boolean iconDropConsumed;
        boolean offsetsConsumed;
        int xIconDrop;
        float xOffset;
        float xOffsetStep;
        int xPixelOffset;
        int yIconDrop;
        float yOffset;
        float yOffsetStep;
        int yPixelOffset;

        public AndroidWallpaperEngine() {
            this.engineIsVisible = false;
            this.iconDropConsumed = true;
            this.offsetsConsumed = true;
            this.xOffset = 0.0f;
            this.yOffset = 0.0f;
            this.xOffsetStep = 0.0f;
            this.yOffsetStep = 0.0f;
            this.xPixelOffset = 0;
            this.yPixelOffset = 0;
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine() " + this.hashCode());
            }
        }

        protected void notifyIconDropped() {
            if(AndroidLiveWallpaperService.this.linkedEngine == this && AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener && !this.iconDropConsumed) {
                this.iconDropConsumed = true;
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(AndroidLiveWallpaperService.this.sync) {
                        }
                        if(AndroidLiveWallpaperService.this.linkedEngine == AndroidWallpaperEngine.this) {
                            ((AndroidWallpaperListener)AndroidLiveWallpaperService.this.app.listener).iconDropped(AndroidWallpaperEngine.this.xIconDrop, AndroidWallpaperEngine.this.yIconDrop);
                        }
                    }
                });
            }
        }

        protected void notifyOffsetsChanged() {
            if(AndroidLiveWallpaperService.this.linkedEngine == this && AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener && !this.offsetsConsumed) {
                this.offsetsConsumed = true;
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        synchronized(AndroidLiveWallpaperService.this.sync) {
                        }
                        if(AndroidLiveWallpaperService.this.linkedEngine == AndroidWallpaperEngine.this) {
                            ((AndroidWallpaperListener)AndroidLiveWallpaperService.this.app.listener).offsetChange(AndroidWallpaperEngine.this.xOffset, AndroidWallpaperEngine.this.yOffset, AndroidWallpaperEngine.this.xOffsetStep, AndroidWallpaperEngine.this.yOffsetStep, AndroidWallpaperEngine.this.xPixelOffset, AndroidWallpaperEngine.this.yPixelOffset);
                        }
                    }
                });
            }
        }

        protected void notifyPreviewState() {
            if(AndroidLiveWallpaperService.this.linkedEngine == this && AndroidLiveWallpaperService.this.app.listener instanceof AndroidWallpaperListener) {
                boolean z = AndroidLiveWallpaperService.this.linkedEngine.isPreview();
                AndroidLiveWallpaperService.this.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        boolean z = true;
                        synchronized(AndroidLiveWallpaperService.this.sync) {
                            if(!AndroidLiveWallpaperService.this.isPreviewNotified || AndroidLiveWallpaperService.this.notifiedPreviewState != z) {
                                AndroidLiveWallpaperService.this.notifiedPreviewState = z;
                                AndroidLiveWallpaperService.this.isPreviewNotified = true;
                            }
                            else {
                                z = false;
                            }
                        }
                        if(z) {
                            AndroidLiveWallpaper androidLiveWallpaper0 = AndroidLiveWallpaperService.this.app;
                            if(androidLiveWallpaper0 != null) {
                                ((AndroidWallpaperListener)androidLiveWallpaper0.listener).previewStateChange(z);
                            }
                        }
                    }
                });
            }
        }

        private void notifySurfaceChanged(int v, int v1, int v2, boolean z) {
            if(z || v != AndroidLiveWallpaperService.this.viewFormat || v1 != AndroidLiveWallpaperService.this.viewWidth || v2 != AndroidLiveWallpaperService.this.viewHeight) {
                this.engineFormat = v;
                this.engineWidth = v1;
                this.engineHeight = v2;
                if(AndroidLiveWallpaperService.this.linkedEngine == this) {
                    AndroidLiveWallpaperService.this.viewFormat = this.engineFormat;
                    AndroidLiveWallpaperService.this.viewWidth = this.engineWidth;
                    AndroidLiveWallpaperService.this.viewHeight = this.engineHeight;
                    AndroidLiveWallpaperService.this.view.surfaceChanged(this.getSurfaceHolder(), AndroidLiveWallpaperService.this.viewFormat, AndroidLiveWallpaperService.this.viewWidth, AndroidLiveWallpaperService.this.viewHeight);
                    return;
                }
                if(AndroidLiveWallpaperService.DEBUG) {
                    Log.d("WallpaperService", " > engine is not active, skipping surfaceChanged event");
                }
            }
            else if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > surface is current, skipping surfaceChanged event");
            }
        }

        private void notifyVisibilityChanged(boolean z) {
            if(this.engineIsVisible != z) {
                this.engineIsVisible = z;
                if(this.engineIsVisible) {
                    this.onResume();
                    return;
                }
                this.onPause();
                return;
            }
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > visible state is current, skipping visibilityChanged event!");
            }
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public Bundle onCommand(String s, int v, int v1, int v2, Bundle bundle0, boolean z) {
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onCommand(" + s + " " + v + " " + v1 + " " + v2 + " " + bundle0 + " " + z + "), linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this));
            }
            if(s.equals("android.home.drop")) {
                this.iconDropConsumed = false;
                this.xIconDrop = v;
                this.yIconDrop = v1;
                this.notifyIconDropped();
            }
            return super.onCommand(s, v, v1, v2, bundle0, z);
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public WallpaperColors onComputeColors() {
            Application application0 = Gdx.app;
            if(Build.VERSION.SDK_INT >= 27 && application0 instanceof AndroidLiveWallpaper) {
                Color[] arr_color = ((AndroidLiveWallpaper)application0).wallpaperColors;
                return arr_color == null ? super.onComputeColors() : new WallpaperColors(android.graphics.Color.valueOf(arr_color[0].r, arr_color[0].g, arr_color[0].b, arr_color[0].a), android.graphics.Color.valueOf(arr_color[1].r, arr_color[1].g, arr_color[1].b, arr_color[1].a), android.graphics.Color.valueOf(arr_color[2].r, arr_color[2].g, arr_color[2].b, arr_color[2].a));
            }
            return super.onComputeColors();
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onCreate(SurfaceHolder surfaceHolder0) {
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onCreate() " + this.hashCode() + " running: " + AndroidLiveWallpaperService.this.engines + ", linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this) + ", thread: " + "Thread[jeb-dexdec-sb-st-61,5,main]");
            }
            super.onCreate(surfaceHolder0);
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onDestroy() {
            super.onDestroy();
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onOffsetsChanged(float f, float f1, float f2, float f3, int v, int v1) {
            this.offsetsConsumed = false;
            this.xOffset = f;
            this.yOffset = f1;
            this.xOffsetStep = f2;
            this.yOffsetStep = f3;
            this.xPixelOffset = v;
            this.yPixelOffset = v1;
            this.notifyOffsetsChanged();
            if(!Gdx.graphics.isContinuousRendering()) {
                Gdx.graphics.requestRendering();
            }
            super.onOffsetsChanged(f, f1, f2, f3, v, v1);
        }

        public void onPause() {
            --AndroidLiveWallpaperService.this.visibleEngines;
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onPause() " + this.hashCode() + ", running: " + AndroidLiveWallpaperService.this.engines + ", linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this) + ", visible: " + AndroidLiveWallpaperService.this.visibleEngines);
            }
            Log.i("WallpaperService", "engine paused");
            if(AndroidLiveWallpaperService.this.visibleEngines >= AndroidLiveWallpaperService.this.engines) {
                Log.e("WallpaperService", "wallpaper lifecycle error, counted too many visible engines! repairing..");
                AndroidLiveWallpaperService.this.visibleEngines = Math.max(AndroidLiveWallpaperService.this.engines - 1, 0);
            }
            if(AndroidLiveWallpaperService.this.linkedEngine != null && AndroidLiveWallpaperService.this.visibleEngines == 0) {
                AndroidLiveWallpaperService.this.app.onPause();
            }
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onPause() done!");
            }
        }

        public void onResume() {
            ++AndroidLiveWallpaperService.this.visibleEngines;
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onResume() " + this.hashCode() + ", running: " + AndroidLiveWallpaperService.this.engines + ", linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this) + ", visible: " + AndroidLiveWallpaperService.this.visibleEngines);
            }
            Log.i("WallpaperService", "engine resumed");
            if(AndroidLiveWallpaperService.this.linkedEngine != null) {
                if(AndroidLiveWallpaperService.this.linkedEngine == this) {
                    this.notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
                }
                else {
                    AndroidLiveWallpaperService.this.setLinkedEngine(this);
                    AndroidLiveWallpaperService.this.view.surfaceDestroyed(this.getSurfaceHolder());
                    this.notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
                    AndroidLiveWallpaperService.this.view.surfaceCreated(this.getSurfaceHolder());
                }
                if(AndroidLiveWallpaperService.this.visibleEngines == 1) {
                    AndroidLiveWallpaperService.this.app.onResume();
                }
                this.notifyPreviewState();
                this.notifyOffsetsChanged();
                if(!Gdx.graphics.isContinuousRendering()) {
                    Gdx.graphics.requestRendering();
                }
            }
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onSurfaceChanged(SurfaceHolder surfaceHolder0, int v, int v1, int v2) {
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onSurfaceChanged() isPreview: " + this.isPreview() + ", " + this.hashCode() + ", running: " + AndroidLiveWallpaperService.this.engines + ", linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this) + ", sufcace valid: " + this.getSurfaceHolder().getSurface().isValid());
            }
            Log.i("WallpaperService", "engine surface changed");
            super.onSurfaceChanged(surfaceHolder0, v, v1, v2);
            this.notifySurfaceChanged(v, v1, v2, true);
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onSurfaceCreated(SurfaceHolder surfaceHolder0) {
            ++AndroidLiveWallpaperService.this.engines;
            AndroidLiveWallpaperService.this.setLinkedEngine(this);
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onSurfaceCreated() " + this.hashCode() + ", running: " + AndroidLiveWallpaperService.this.engines + ", linked: " + (AndroidLiveWallpaperService.this.linkedEngine == this));
            }
            Log.i("WallpaperService", "engine surface created");
            super.onSurfaceCreated(surfaceHolder0);
            if(AndroidLiveWallpaperService.this.engines == 1) {
                AndroidLiveWallpaperService.this.visibleEngines = 0;
            }
            if(AndroidLiveWallpaperService.this.engines == 1 && AndroidLiveWallpaperService.this.app == null) {
                AndroidLiveWallpaperService.this.viewFormat = 0;
                AndroidLiveWallpaperService.this.viewWidth = 0;
                AndroidLiveWallpaperService.this.viewHeight = 0;
                AndroidLiveWallpaperService.this.app = new AndroidLiveWallpaper(AndroidLiveWallpaperService.this);
                AndroidLiveWallpaperService.this.onCreateApplication();
                if(AndroidLiveWallpaperService.this.app.graphics == null) {
                    throw new Error("You must override \'AndroidLiveWallpaperService.onCreateApplication\' method and call \'initialize\' from its body.");
                }
            }
            AndroidLiveWallpaperService.this.view = AndroidLiveWallpaperService.this.app.graphics.view;
            this.getSurfaceHolder().removeCallback(AndroidLiveWallpaperService.this.view);
            this.engineFormat = AndroidLiveWallpaperService.this.viewFormat;
            this.engineWidth = AndroidLiveWallpaperService.this.viewWidth;
            this.engineHeight = AndroidLiveWallpaperService.this.viewHeight;
            if(AndroidLiveWallpaperService.this.engines != 1) {
                AndroidLiveWallpaperService.this.view.surfaceDestroyed(surfaceHolder0);
                this.notifySurfaceChanged(this.engineFormat, this.engineWidth, this.engineHeight, false);
            }
            AndroidLiveWallpaperService.this.view.surfaceCreated(surfaceHolder0);
            this.notifyPreviewState();
            this.notifyOffsetsChanged();
            if(!Gdx.graphics.isContinuousRendering()) {
                Gdx.graphics.requestRendering();
            }
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onSurfaceDestroyed(SurfaceHolder surfaceHolder0) {
            boolean z = true;
            --AndroidLiveWallpaperService.this.engines;
            if(AndroidLiveWallpaperService.DEBUG) {
                StringBuilder stringBuilder0 = new StringBuilder();
                stringBuilder0.append(" > AndroidWallpaperEngine - onSurfaceDestroyed() ");
                stringBuilder0.append(this.hashCode());
                stringBuilder0.append(", running: ");
                stringBuilder0.append(AndroidLiveWallpaperService.this.engines);
                stringBuilder0.append(" ,linked: ");
                if(AndroidLiveWallpaperService.this.linkedEngine != this) {
                    z = false;
                }
                stringBuilder0.append(z);
                stringBuilder0.append(", isVisible: ");
                stringBuilder0.append(this.engineIsVisible);
                Log.d("WallpaperService", stringBuilder0.toString());
            }
            Log.i("WallpaperService", "engine surface destroyed");
            if(AndroidLiveWallpaperService.this.engines == 0) {
                AndroidLiveWallpaperService.this.onDeepPauseApplication();
            }
            if(AndroidLiveWallpaperService.this.linkedEngine == this && AndroidLiveWallpaperService.this.view != null) {
                AndroidLiveWallpaperService.this.view.surfaceDestroyed(surfaceHolder0);
            }
            this.engineFormat = 0;
            this.engineWidth = 0;
            this.engineHeight = 0;
            if(AndroidLiveWallpaperService.this.engines == 0) {
                AndroidLiveWallpaperService.this.linkedEngine = null;
            }
            super.onSurfaceDestroyed(surfaceHolder0);
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onTouchEvent(MotionEvent motionEvent0) {
            if(AndroidLiveWallpaperService.this.linkedEngine == this) {
                AndroidLiveWallpaperService.this.app.input.onTouch(null, motionEvent0);
            }
        }

        @Override  // android.service.wallpaper.WallpaperService$Engine
        public void onVisibilityChanged(boolean z) {
            boolean z1 = this.isVisible();
            if(AndroidLiveWallpaperService.DEBUG) {
                Log.d("WallpaperService", " > AndroidWallpaperEngine - onVisibilityChanged(paramVisible: " + z + " reportedVisible: " + z1 + ") " + this.hashCode() + ", sufcace valid: " + this.getSurfaceHolder().getSurface().isValid());
            }
            super.onVisibilityChanged(z);
            if(!z1 && z) {
                if(AndroidLiveWallpaperService.DEBUG) {
                    Log.d("WallpaperService", " > fake visibilityChanged event! Android WallpaperService likes do that!");
                }
                return;
            }
            this.notifyVisibilityChanged(z);
        }
    }

    static boolean DEBUG = false;
    static final String TAG = "WallpaperService";
    protected volatile AndroidLiveWallpaper app;
    protected int engines;
    protected volatile boolean isPreviewNotified;
    protected volatile AndroidWallpaperEngine linkedEngine;
    protected volatile boolean notifiedPreviewState;
    volatile int[] sync;
    protected SurfaceHolder.Callback view;
    protected int viewFormat;
    protected int viewHeight;
    protected int viewWidth;
    protected int visibleEngines;

    static {
        GdxNativesLoader.load();
        AndroidLiveWallpaperService.DEBUG = false;
    }

    public AndroidLiveWallpaperService() {
        this.app = null;
        this.view = null;
        this.engines = 0;
        this.visibleEngines = 0;
        this.linkedEngine = null;
        this.isPreviewNotified = false;
        this.notifiedPreviewState = false;
        this.sync = new int[0];
    }

    @Override
    protected void finalize() throws Throwable {
        Log.i("WallpaperService", "service finalized");
        super.finalize();
    }

    public AndroidLiveWallpaper getLiveWallpaper() {
        return this.app;
    }

    public SurfaceHolder getSurfaceHolder() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - getSurfaceHolder()");
        }
        synchronized(this.sync) {
            if(this.linkedEngine == null) {
                return null;
            }
        }
        return this.linkedEngine.getSurfaceHolder();
    }

    public WindowManager getWindowManager() {
        return (WindowManager)this.getSystemService("window");
    }

    public void initialize(ApplicationListener applicationListener0) {
        this.initialize(applicationListener0, new AndroidApplicationConfiguration());
    }

    public void initialize(ApplicationListener applicationListener0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - initialize()");
        }
        this.app.initialize(applicationListener0, androidApplicationConfiguration0);
        if(androidApplicationConfiguration0.getTouchEventsForLiveWallpaper && Integer.parseInt(Build.VERSION.SDK) >= 7) {
            this.linkedEngine.setTouchEventsEnabled(true);
        }
    }

    @Override  // android.service.wallpaper.WallpaperService
    public void onCreate() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - onCreate() " + this.hashCode());
        }
        Log.i("WallpaperService", "service created");
        super.onCreate();
    }

    public void onCreateApplication() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - onCreateApplication()");
        }
    }

    @Override  // android.service.wallpaper.WallpaperService
    public WallpaperService.Engine onCreateEngine() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - onCreateEngine()");
        }
        Log.i("WallpaperService", "engine created");
        return new AndroidWallpaperEngine(this);
    }

    public void onDeepPauseApplication() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - onDeepPauseApplication()");
        }
        if(this.app != null) {
            this.app.graphics.clearManagedCaches();
        }
    }

    @Override  // android.service.wallpaper.WallpaperService
    public void onDestroy() {
        if(AndroidLiveWallpaperService.DEBUG) {
            Log.d("WallpaperService", " > AndroidLiveWallpaperService - onDestroy() " + this.hashCode());
        }
        Log.i("WallpaperService", "service destroyed");
        super.onDestroy();
        if(this.app != null) {
            this.app.onDestroy();
            this.app = null;
            this.view = null;
        }
    }

    protected void setLinkedEngine(AndroidWallpaperEngine androidLiveWallpaperService$AndroidWallpaperEngine0) {
        synchronized(this.sync) {
            this.linkedEngine = androidLiveWallpaperService$AndroidWallpaperEngine0;
        }
    }
}

