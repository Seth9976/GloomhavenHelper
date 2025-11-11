package com.badlogic.gdx.backends.android;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Build.VERSION;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.DisplayCutout;
import android.view.View;
import com.badlogic.gdx.AbstractGraphics;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.BufferFormat;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.GraphicsType;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.LifecycleListener;
import com.badlogic.gdx.backends.android.surfaceview.GLSurfaceView20;
import com.badlogic.gdx.backends.android.surfaceview.GdxEglConfigChooser;
import com.badlogic.gdx.backends.android.surfaceview.ResolutionStrategy;
import com.badlogic.gdx.graphics.Cubemap;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.GLVersion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.SnapshotArray;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.opengles.GL10;

public class AndroidGraphics extends AbstractGraphics implements GLSurfaceView.Renderer {
    class AndroidDisplayMode extends DisplayMode {
        protected AndroidDisplayMode(int v, int v1, int v2, int v3) {
            super(v, v1, v2, v3);
        }
    }

    class AndroidMonitor extends Monitor {
        public AndroidMonitor(int v, int v1, String s) {
            super(v, v1, s);
        }
    }

    private static final String LOG_TAG = "AndroidGraphics";
    AndroidApplicationBase app;
    private BufferFormat bufferFormat;
    protected final AndroidApplicationConfiguration config;
    volatile boolean created;
    protected float deltaTime;
    private float density;
    volatile boolean destroy;
    EGLContext eglContext;
    static volatile boolean enforceContinuousRendering = false;
    String extensions;
    protected int fps;
    protected long frameId;
    protected long frameStart;
    protected int frames;
    GL20 gl20;
    GL30 gl30;
    GLVersion glVersion;
    int height;
    private boolean isContinuous;
    protected long lastFrameTime;
    volatile boolean pause;
    private float ppcX;
    private float ppcY;
    private float ppiX;
    private float ppiY;
    volatile boolean resume;
    volatile boolean running;
    int safeInsetBottom;
    int safeInsetLeft;
    int safeInsetRight;
    int safeInsetTop;
    Object synch;
    int[] value;
    final GLSurfaceView20 view;
    int width;

    static {
    }

    public AndroidGraphics(AndroidApplicationBase androidApplicationBase0, AndroidApplicationConfiguration androidApplicationConfiguration0, ResolutionStrategy resolutionStrategy0) {
        this(androidApplicationBase0, androidApplicationConfiguration0, resolutionStrategy0, true);
    }

    public AndroidGraphics(AndroidApplicationBase androidApplicationBase0, AndroidApplicationConfiguration androidApplicationConfiguration0, ResolutionStrategy resolutionStrategy0, boolean z) {
        this.lastFrameTime = System.nanoTime();
        this.deltaTime = 0.0f;
        this.frameStart = System.nanoTime();
        this.frameId = -1L;
        this.frames = 0;
        this.created = false;
        this.running = false;
        this.pause = false;
        this.resume = false;
        this.destroy = false;
        this.ppiX = 0.0f;
        this.ppiY = 0.0f;
        this.ppcX = 0.0f;
        this.ppcY = 0.0f;
        this.density = 1.0f;
        this.bufferFormat = new BufferFormat(8, 8, 8, 0, 16, 0, 0, false);
        this.isContinuous = true;
        this.value = new int[1];
        this.synch = new Object();
        this.config = androidApplicationConfiguration0;
        this.app = androidApplicationBase0;
        this.view = this.createGLSurfaceView(androidApplicationBase0, resolutionStrategy0);
        this.preserveEGLContextOnPause();
        if(z) {
            this.view.setFocusable(true);
            this.view.setFocusableInTouchMode(true);
        }
    }

    protected boolean checkGL20() {
        EGL10 eGL100 = (EGL10)EGLContext.getEGL();
        EGLDisplay eGLDisplay0 = eGL100.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        eGL100.eglInitialize(eGLDisplay0, new int[2]);
        int[] arr_v = new int[1];
        eGL100.eglChooseConfig(eGLDisplay0, new int[]{0x3024, 4, 0x3023, 4, 0x3022, 4, 0x3040, 4, 0x3038}, new EGLConfig[10], 10, arr_v);
        eGL100.eglTerminate(eGLDisplay0);
        return arr_v[0] > 0;
    }

    public void clearManagedCaches() {
        Mesh.clearAllMeshes(this.app);
        Texture.clearAllTextures(this.app);
        Cubemap.clearAllCubemaps(this.app);
        TextureArray.clearAllTextureArrays(this.app);
        ShaderProgram.clearAllShaderPrograms(this.app);
        FrameBuffer.clearAllFrameBuffers(this.app);
        this.logManagedCachesStatus();
    }

    protected GLSurfaceView20 createGLSurfaceView(AndroidApplicationBase androidApplicationBase0, ResolutionStrategy resolutionStrategy0) {
        if(!this.checkGL20()) {
            throw new GdxRuntimeException("Libgdx requires OpenGL ES 2.0");
        }
        GLSurfaceView.EGLConfigChooser gLSurfaceView$EGLConfigChooser0 = this.getEglConfigChooser();
        GLSurfaceView20 gLSurfaceView200 = new GLSurfaceView20(androidApplicationBase0.getContext(), resolutionStrategy0, (this.config.useGL30 ? 3 : 2));
        if(gLSurfaceView$EGLConfigChooser0 == null) {
            gLSurfaceView200.setEGLConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil);
        }
        else {
            gLSurfaceView200.setEGLConfigChooser(gLSurfaceView$EGLConfigChooser0);
        }
        gLSurfaceView200.setRenderer(this);
        return gLSurfaceView200;
    }

    void destroy() {
        synchronized(this.synch) {
            this.running = false;
            this.destroy = true;
            while(this.destroy) {
                try {
                    this.synch.wait();
                }
                catch(InterruptedException unused_ex) {
                    Gdx.app.log("AndroidGraphics", "waiting for destroy synchronization failed!");
                }
            }
        }
    }

    // 去混淆评级： 低(20)
    private int getAttrib(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0, int v, int v1) {
        return eGL100.eglGetConfigAttrib(eGLDisplay0, eGLConfig0, v, this.value) ? this.value[0] : v1;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getBackBufferHeight() {
        return this.height;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getBackBufferWidth() {
        return this.width;
    }

    @Override  // com.badlogic.gdx.Graphics
    public BufferFormat getBufferFormat() {
        return this.bufferFormat;
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getDeltaTime() {
        return this.deltaTime;
    }

    @Override  // com.badlogic.gdx.AbstractGraphics
    public float getDensity() {
        return this.density;
    }

    @Override  // com.badlogic.gdx.Graphics
    public DisplayMode getDisplayMode() {
        DisplayMetrics displayMetrics0 = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics0);
        return new AndroidDisplayMode(this, displayMetrics0.widthPixels, displayMetrics0.heightPixels, 0, 0);
    }

    @Override  // com.badlogic.gdx.Graphics
    public DisplayMode getDisplayMode(Monitor graphics$Monitor0) {
        return this.getDisplayMode();
    }

    @Override  // com.badlogic.gdx.Graphics
    public DisplayMode[] getDisplayModes() {
        return new DisplayMode[]{this.getDisplayMode()};
    }

    @Override  // com.badlogic.gdx.Graphics
    public DisplayMode[] getDisplayModes(Monitor graphics$Monitor0) {
        return this.getDisplayModes();
    }

    protected GLSurfaceView.EGLConfigChooser getEglConfigChooser() {
        return new GdxEglConfigChooser(this.config.r, this.config.g, this.config.b, this.config.a, this.config.depth, this.config.stencil, this.config.numSamples);
    }

    @Override  // com.badlogic.gdx.Graphics
    public long getFrameId() {
        return this.frameId;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getFramesPerSecond() {
        return this.fps;
    }

    @Override  // com.badlogic.gdx.Graphics
    public GL20 getGL20() {
        return this.gl20;
    }

    @Override  // com.badlogic.gdx.Graphics
    public GL30 getGL30() {
        return this.gl30;
    }

    @Override  // com.badlogic.gdx.Graphics
    public GLVersion getGLVersion() {
        return this.glVersion;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getHeight() {
        return this.height;
    }

    @Override  // com.badlogic.gdx.Graphics
    public Monitor getMonitor() {
        return this.getPrimaryMonitor();
    }

    @Override  // com.badlogic.gdx.Graphics
    public Monitor[] getMonitors() {
        return new Monitor[]{this.getPrimaryMonitor()};
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getPpcX() {
        return this.ppcX;
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getPpcY() {
        return this.ppcY;
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getPpiX() {
        return this.ppiX;
    }

    @Override  // com.badlogic.gdx.Graphics
    public float getPpiY() {
        return this.ppiY;
    }

    @Override  // com.badlogic.gdx.Graphics
    public Monitor getPrimaryMonitor() {
        return new AndroidMonitor(this, 0, 0, "Primary Monitor");
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getSafeInsetBottom() {
        return this.safeInsetBottom;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getSafeInsetLeft() {
        return this.safeInsetLeft;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getSafeInsetRight() {
        return this.safeInsetRight;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getSafeInsetTop() {
        return this.safeInsetTop;
    }

    @Override  // com.badlogic.gdx.Graphics
    public GraphicsType getType() {
        return GraphicsType.AndroidGL;
    }

    public View getView() {
        return this.view;
    }

    @Override  // com.badlogic.gdx.Graphics
    public int getWidth() {
        return this.width;
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean isContinuousRendering() {
        return this.isContinuous;
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean isFullscreen() {
        return true;
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean isGL30Available() {
        return this.gl30 != null;
    }

    protected void logConfig(EGLConfig eGLConfig0) {
        EGL10 eGL100 = (EGL10)EGLContext.getEGL();
        EGLDisplay eGLDisplay0 = eGL100.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        int v = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3024, 0);
        int v1 = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3023, 0);
        int v2 = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3022, 0);
        int v3 = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3021, 0);
        int v4 = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3025, 0);
        int v5 = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3026, 0);
        int v6 = Math.max(this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3031, 0), this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x30E1, 0));
        boolean z = this.getAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x30E1, 0) != 0;
        Gdx.app.log("AndroidGraphics", "framebuffer: (" + v + ", " + v1 + ", " + v2 + ", " + v3 + ")");
        Gdx.app.log("AndroidGraphics", "depthbuffer: (" + v4 + ")");
        Gdx.app.log("AndroidGraphics", "stencilbuffer: (" + v5 + ")");
        Gdx.app.log("AndroidGraphics", "samples: (" + v6 + ")");
        Gdx.app.log("AndroidGraphics", "coverage sampling: (" + z + ")");
        this.bufferFormat = new BufferFormat(v, v1, v2, v3, v4, v5, v6, z);
    }

    protected void logManagedCachesStatus() {
        Gdx.app.log("AndroidGraphics", "Managed meshes/app: { }");
        Gdx.app.log("AndroidGraphics", "Managed textures/app: { }");
        Gdx.app.log("AndroidGraphics", "Managed cubemap/app: { }");
        Gdx.app.log("AndroidGraphics", ShaderProgram.getManagedStatus());
        Gdx.app.log("AndroidGraphics", "Managed buffers/app: { }");
    }

    @Override  // com.badlogic.gdx.Graphics
    public Cursor newCursor(Pixmap pixmap0, int v, int v1) {
        return null;
    }

    @Override  // android.opengl.GLSurfaceView$Renderer
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
            SnapshotArray snapshotArray0 = this.app.getLifecycleListeners();
            synchronized(snapshotArray0) {
                LifecycleListener[] arr_lifecycleListener = (LifecycleListener[])snapshotArray0.begin();
                int v3 = snapshotArray0.size;
                for(int v4 = 0; v4 < v3; ++v4) {
                    arr_lifecycleListener[v4].resume();
                }
                snapshotArray0.end();
            }
            this.app.getApplicationListener().resume();
            Gdx.app.log("AndroidGraphics", "resumed");
        }
        if(z) {
            synchronized(this.app.getRunnables()) {
                this.app.getExecutedRunnables().clear();
                this.app.getExecutedRunnables().addAll(this.app.getRunnables());
                this.app.getRunnables().clear();
            }
            for(int v6 = 0; v6 < this.app.getExecutedRunnables().size; ++v6) {
                try {
                    ((Runnable)this.app.getExecutedRunnables().get(v6)).run();
                }
                catch(Throwable throwable0) {
                    throwable0.printStackTrace();
                }
            }
            this.app.getInput().processEvents();
            ++this.frameId;
            this.app.getApplicationListener().render();
        }
        if(z1) {
            SnapshotArray snapshotArray1 = this.app.getLifecycleListeners();
            synchronized(snapshotArray1) {
                LifecycleListener[] arr_lifecycleListener1 = (LifecycleListener[])snapshotArray1.begin();
                int v8 = snapshotArray1.size;
                for(int v9 = 0; v9 < v8; ++v9) {
                    arr_lifecycleListener1[v9].pause();
                }
            }
            this.app.getApplicationListener().pause();
            Gdx.app.log("AndroidGraphics", "paused");
        }
        if(z2) {
            SnapshotArray snapshotArray2 = this.app.getLifecycleListeners();
            synchronized(snapshotArray2) {
                LifecycleListener[] arr_lifecycleListener2 = (LifecycleListener[])snapshotArray2.begin();
                int v11 = snapshotArray2.size;
                for(int v12 = 0; v12 < v11; ++v12) {
                    arr_lifecycleListener2[v12].dispose();
                }
            }
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

    public void onPauseGLSurfaceView() {
        GLSurfaceView20 gLSurfaceView200 = this.view;
        if(gLSurfaceView200 != null) {
            gLSurfaceView200.onPause();
        }
    }

    public void onResumeGLSurfaceView() {
        GLSurfaceView20 gLSurfaceView200 = this.view;
        if(gLSurfaceView200 != null) {
            gLSurfaceView200.onResume();
        }
    }

    @Override  // android.opengl.GLSurfaceView$Renderer
    public void onSurfaceChanged(GL10 gL100, int v, int v1) {
        this.width = v;
        this.height = v1;
        this.updatePpi();
        this.updateSafeAreaInsets();
        gL100.glViewport(0, 0, this.width, this.height);
        if(!this.created) {
            this.app.getApplicationListener().create();
            this.created = true;
            synchronized(this) {
                this.running = true;
            }
        }
        this.app.getApplicationListener().resize(v, v1);
    }

    @Override  // android.opengl.GLSurfaceView$Renderer
    public void onSurfaceCreated(GL10 gL100, EGLConfig eGLConfig0) {
        this.eglContext = ((EGL10)EGLContext.getEGL()).eglGetCurrentContext();
        this.setupGL(gL100);
        this.logConfig(eGLConfig0);
        this.updatePpi();
        this.updateSafeAreaInsets();
        Mesh.invalidateAllMeshes(this.app);
        Texture.invalidateAllTextures(this.app);
        Cubemap.invalidateAllCubemaps(this.app);
        TextureArray.invalidateAllTextureArrays(this.app);
        ShaderProgram.invalidateAllShaderPrograms(this.app);
        FrameBuffer.invalidateAllFrameBuffers(this.app);
        this.logManagedCachesStatus();
        Display display0 = this.app.getWindowManager().getDefaultDisplay();
        this.width = display0.getWidth();
        this.height = display0.getHeight();
        this.lastFrameTime = System.nanoTime();
        gL100.glViewport(0, 0, this.width, this.height);
    }

    void pause() {
        synchronized(this.synch) {
            if(!this.running) {
                return;
            }
            this.running = false;
            this.pause = true;
            com.badlogic.gdx.backends.android.AndroidGraphics.1 androidGraphics$10 = new Runnable() {
                @Override
                public void run() {
                    if(!AndroidGraphics.this.pause) {
                        return;
                    }
                    AndroidGraphics.this.onDrawFrame(null);
                }
            };
            this.view.queueEvent(androidGraphics$10);
            while(this.pause) {
                try {
                    this.synch.wait(4000L);
                    if(!this.pause) {
                        continue;
                    }
                    Gdx.app.error("AndroidGraphics", "waiting for pause synchronization took too long; assuming deadlock and killing");
                    Process.killProcess(Process.myPid());
                }
                catch(InterruptedException unused_ex) {
                    Gdx.app.log("AndroidGraphics", "waiting for pause synchronization failed!");
                }
            }
        }
    }

    protected void preserveEGLContextOnPause() {
        this.view.setPreserveEGLContextOnPause(true);
    }

    @Override  // com.badlogic.gdx.Graphics
    public void requestRendering() {
        GLSurfaceView20 gLSurfaceView200 = this.view;
        if(gLSurfaceView200 != null) {
            gLSurfaceView200.requestRender();
        }
    }

    void resume() {
        synchronized(this.synch) {
            this.running = true;
            this.resume = true;
        }
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setContinuousRendering(boolean z) {
        if(this.view != null) {
            this.isContinuous = AndroidGraphics.enforceContinuousRendering || z;
            this.view.setRenderMode(((int)this.isContinuous));
        }
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setCursor(Cursor cursor0) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setForegroundFPS(int v) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean setFullscreenMode(DisplayMode graphics$DisplayMode0) {
        return false;
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setGL20(GL20 gL200) {
        this.gl20 = gL200;
        if(this.gl30 == null) {
            Gdx.gl = gL200;
            Gdx.gl20 = gL200;
        }
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setGL30(GL30 gL300) {
        this.gl30 = gL300;
        if(gL300 != null) {
            this.gl20 = gL300;
            Gdx.gl = this.gl20;
            Gdx.gl20 = this.gl20;
            Gdx.gl30 = gL300;
        }
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setResizable(boolean z) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setSystemCursor(SystemCursor cursor$SystemCursor0) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setTitle(String s) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setUndecorated(boolean z) {
        this.app.getApplicationWindow().setFlags(0x400, ((int)z));
    }

    @Override  // com.badlogic.gdx.Graphics
    public void setVSync(boolean z) {
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean setWindowedMode(int v, int v1) {
        return false;
    }

    protected void setupGL(GL10 gL100) {
        String s = gL100.glGetString(0x1F02);
        String s1 = gL100.glGetString(0x1F00);
        String s2 = gL100.glGetString(0x1F01);
        this.glVersion = new GLVersion(ApplicationType.Android, s, s1, s2);
        if(!this.config.useGL30 || this.glVersion.getMajorVersion() <= 2) {
            if(this.gl20 != null) {
                return;
            }
            this.gl20 = new AndroidGL20();
            Gdx.gl = this.gl20;
            Gdx.gl20 = this.gl20;
        }
        else {
            if(this.gl30 != null) {
                return;
            }
            AndroidGL30 androidGL300 = new AndroidGL30();
            this.gl30 = androidGL300;
            this.gl20 = androidGL300;
            Gdx.gl = this.gl30;
            Gdx.gl20 = this.gl30;
            Gdx.gl30 = this.gl30;
        }
        Gdx.app.log("AndroidGraphics", "OGL renderer: " + gL100.glGetString(0x1F01));
        Gdx.app.log("AndroidGraphics", "OGL vendor: " + gL100.glGetString(0x1F00));
        Gdx.app.log("AndroidGraphics", "OGL version: " + gL100.glGetString(0x1F02));
        Gdx.app.log("AndroidGraphics", "OGL extensions: " + gL100.glGetString(0x1F03));
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean supportsDisplayModeChange() {
        return false;
    }

    @Override  // com.badlogic.gdx.Graphics
    public boolean supportsExtension(String s) {
        if(this.extensions == null) {
            this.extensions = Gdx.gl.glGetString(0x1F03);
        }
        return this.extensions.contains(s);
    }

    protected void updatePpi() {
        DisplayMetrics displayMetrics0 = new DisplayMetrics();
        this.app.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics0);
        this.ppiX = displayMetrics0.xdpi;
        this.ppiY = displayMetrics0.ydpi;
        this.ppcX = displayMetrics0.xdpi / 2.54f;
        this.ppcY = displayMetrics0.ydpi / 2.54f;
        this.density = displayMetrics0.density;
    }

    protected void updateSafeAreaInsets() {
        this.safeInsetLeft = 0;
        this.safeInsetTop = 0;
        this.safeInsetRight = 0;
        this.safeInsetBottom = 0;
        if(Build.VERSION.SDK_INT >= 28) {
            try {
                DisplayCutout displayCutout0 = this.app.getApplicationWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
                if(displayCutout0 != null) {
                    this.safeInsetRight = displayCutout0.getSafeInsetRight();
                    this.safeInsetBottom = displayCutout0.getSafeInsetBottom();
                    this.safeInsetTop = displayCutout0.getSafeInsetTop();
                    this.safeInsetLeft = displayCutout0.getSafeInsetLeft();
                }
            }
            catch(UnsupportedOperationException unused_ex) {
                Gdx.app.log("AndroidGraphics", "Unable to get safe area insets");
            }
        }
    }
}

