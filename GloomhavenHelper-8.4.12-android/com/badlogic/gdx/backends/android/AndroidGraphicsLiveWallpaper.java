package com.badlogic.gdx.backends.android;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.util.Log;
import android.view.SurfaceHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.utils.GdxRuntimeException;
import javax.microedition.khronos.opengles.GL10;

public final class AndroidGraphicsLiveWallpaper extends AndroidGraphics {
    public AndroidGraphicsLiveWallpaper(AndroidLiveWallpaper androidLiveWallpaper0, AndroidApplicationConfiguration androidApplicationConfiguration0, ResolutionStrategy resolutionStrategy0) {
        super(androidLiveWallpaper0, androidApplicationConfiguration0, resolutionStrategy0, false);
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidGraphics
    protected GLSurfaceView20 createGLSurfaceView(AndroidApplicationBase androidApplicationBase0, ResolutionStrategy resolutionStrategy0) {
        if(!this.checkGL20()) {
            throw new GdxRuntimeException("Libgdx requires OpenGL ES 2.0");
        }
        GLSurfaceView.EGLConfigChooser gLSurfaceView$EGLConfigChooser0 = this.getEglConfigChooser();
        GLSurfaceView20 gLSurfaceView200 = new GLSurfaceView20(androidApplicationBase0.getContext(), resolutionStrategy0) {
            @Override  // android.view.SurfaceView
            public SurfaceHolder getHolder() {
                return AndroidGraphicsLiveWallpaper.this.getSurfaceHolder();
            }
        };
        if(gLSurfaceView$EGLConfigChooser0 == null) {
            gLSurfaceView200.setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
        }
        else {
            gLSurfaceView200.setEGLConfigChooser(gLSurfaceView$EGLConfigChooser0);
        }
        gLSurfaceView200.setRenderer(this);
        return gLSurfaceView200;
    }

    SurfaceHolder getSurfaceHolder() {
        synchronized(((AndroidLiveWallpaper)this.app).service.sync) {
        }
        return ((AndroidLiveWallpaper)this.app).service.getSurfaceHolder();
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidGraphics
    protected void logManagedCachesStatus() {
        if(AndroidLiveWallpaperService.DEBUG) {
            super.logManagedCachesStatus();
        }
    }

    public void onDestroyGLSurfaceView() {
        if(this.view != null) {
            try {
                this.view.onDetachedFromWindow();
                if(AndroidLiveWallpaperService.DEBUG) {
                    Log.d("WallpaperService", " > AndroidLiveWallpaper - onDestroy() stopped GLThread managed by GLSurfaceView");
                }
            }
            catch(Throwable throwable0) {
                Log.e("WallpaperService", "failed to destroy GLSurfaceView\'s thread! GLSurfaceView.onDetachedFromWindow impl changed since API lvl 16!");
                throwable0.printStackTrace();
            }
        }
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidGraphics
    public void onDrawFrame(GL10 gL100) {
        long v = System.nanoTime();
        this.deltaTime = this.resume ? 0.0f : ((float)(v - this.lastFrameTime)) / 1000000000.0f;
        this.lastFrameTime = v;
        synchronized(this.synch) {
            boolean z = this.running;
            boolean z1 = this.pause;
            boolean z2 = this.destroy;
            boolean z3 = this.resume;
            if(this.resume) {
                this.resume = false;
                this.synch.notifyAll();
            }
            if(this.pause) {
                this.pause = false;
                this.synch.notifyAll();
            }
            if(this.destroy) {
                this.destroy = false;
                this.synch.notifyAll();
            }
        }
        if(z3) {
            this.app.getApplicationListener().resume();
            Gdx.app.log("AndroidGraphics", "resumed");
        }
        if(z) {
            synchronized(this.app.getRunnables()) {
                this.app.getExecutedRunnables().clear();
                this.app.getExecutedRunnables().addAll(this.app.getRunnables());
                this.app.getRunnables().clear();
                for(int v3 = 0; v3 < this.app.getExecutedRunnables().size; ++v3) {
                    try {
                        ((Runnable)this.app.getExecutedRunnables().get(v3)).run();
                    }
                    catch(Throwable throwable0) {
                        throwable0.printStackTrace();
                    }
                }
            }
            this.app.getInput().processEvents();
            ++this.frameId;
            this.app.getApplicationListener().render();
        }
        if(z1) {
            this.app.getApplicationListener().pause();
            Gdx.app.log("AndroidGraphics", "paused");
        }
        if(z2) {
            this.app.getApplicationListener().dispose();
            Gdx.app.log("AndroidGraphics", "destroyed");
        }
        if(v - this.frameStart > 1000000000L) {
            this.fps = this.frames;
            this.frames = 0;
            this.frameStart = v;
        }
        ++this.frames;
    }

    @Override  // com.badlogic.gdx.backends.android.AndroidGraphics
    void resume() {
        synchronized(this.synch) {
            this.running = true;
            this.resume = true;
            while(this.resume) {
                try {
                    this.requestRendering();
                    this.synch.wait();
                }
                catch(InterruptedException unused_ex) {
                    Gdx.app.log("AndroidGraphics", "waiting for resume synchronization failed!");
                }
            }
        }
    }
}

