package com.badlogic.gdx.backends.android.surfaceview;

import android.annotation.TargetApi;
import android.content.Context;
import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.opengl.GLSurfaceView.EGLContextFactory;
import android.opengl.GLSurfaceView;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.badlogic.gdx.Input.OnscreenKeyboardType;
import com.badlogic.gdx.backends.android.DefaultAndroidInput;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;

public class GLSurfaceView20 extends GLSurfaceView {
    static class ConfigChooser implements GLSurfaceView.EGLConfigChooser {
        private static int EGL_OPENGL_ES2_BIT = 4;
        protected int mAlphaSize;
        protected int mBlueSize;
        protected int mDepthSize;
        protected int mGreenSize;
        protected int mRedSize;
        protected int mStencilSize;
        private int[] mValue;
        private static int[] s_configAttribs2;

        static {
            ConfigChooser.s_configAttribs2 = new int[]{0x3024, 4, 0x3023, 4, 0x3022, 4, 0x3040, ConfigChooser.EGL_OPENGL_ES2_BIT, 0x3038};
        }

        public ConfigChooser(int v, int v1, int v2, int v3, int v4, int v5) {
            this.mValue = new int[1];
            this.mRedSize = v;
            this.mGreenSize = v1;
            this.mBlueSize = v2;
            this.mAlphaSize = v3;
            this.mDepthSize = v4;
            this.mStencilSize = v5;
        }

        @Override  // android.opengl.GLSurfaceView$EGLConfigChooser
        public EGLConfig chooseConfig(EGL10 eGL100, EGLDisplay eGLDisplay0) {
            int[] arr_v = new int[1];
            eGL100.eglChooseConfig(eGLDisplay0, ConfigChooser.s_configAttribs2, null, 0, arr_v);
            int v = arr_v[0];
            if(v <= 0) {
                throw new IllegalArgumentException("No configs match configSpec");
            }
            EGLConfig[] arr_eGLConfig = new EGLConfig[v];
            eGL100.eglChooseConfig(eGLDisplay0, ConfigChooser.s_configAttribs2, arr_eGLConfig, v, arr_v);
            return this.chooseConfig(eGL100, eGLDisplay0, arr_eGLConfig);
        }

        public EGLConfig chooseConfig(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig[] arr_eGLConfig) {
            for(int v = 0; v < arr_eGLConfig.length; ++v) {
                EGLConfig eGLConfig0 = arr_eGLConfig[v];
                int v1 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3025, 0);
                int v2 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3026, 0);
                if(v1 >= this.mDepthSize && v2 >= this.mStencilSize) {
                    int v3 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3024, 0);
                    int v4 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3023, 0);
                    int v5 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3022, 0);
                    int v6 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig0, 0x3021, 0);
                    if(v3 == this.mRedSize && v4 == this.mGreenSize && v5 == this.mBlueSize && v6 == this.mAlphaSize) {
                        return eGLConfig0;
                    }
                }
            }
            return null;
        }

        // 去混淆评级： 低(20)
        private int findConfigAttrib(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0, int v, int v1) {
            return eGL100.eglGetConfigAttrib(eGLDisplay0, eGLConfig0, v, this.mValue) ? this.mValue[0] : v1;
        }

        private void printConfig(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0) {
            int[] arr_v = new int[1];
            for(int v = 0; v < 33; ++v) {
                int v1 = new int[]{0x3020, 0x3021, 0x3022, 0x3023, 0x3024, 0x3025, 0x3026, 0x3027, 0x3028, 0x3029, 0x302A, 0x302B, 0x302C, 0x302D, 0x302E, 0x302F, 0x3030, 0x3031, 0x3032, 0x3033, 0x3034, 0x3037, 0x3036, 0x3035, 0x3039, 0x303A, 0x303B, 0x303C, 0x303D, 0x303E, 0x303F, 0x3040, 0x3042}[v];
                String s = new String[]{"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT"}[v];
                if(eGL100.eglGetConfigAttrib(eGLDisplay0, eGLConfig0, v1, arr_v)) {
                    Log.w("GL2JNIView", String.format("  %s: %d\n", s, ((int)arr_v[0])));
                }
                else {
                    while(eGL100.eglGetError() != 0x3000) {
                    }
                }
            }
        }

        private void printConfigs(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig[] arr_eGLConfig) {
            Log.w("GL2JNIView", String.format("%d configurations", ((int)arr_eGLConfig.length)));
            for(int v = 0; v < arr_eGLConfig.length; ++v) {
                Log.w("GL2JNIView", String.format("Configuration %d:\n", v));
                this.printConfig(eGL100, eGLDisplay0, arr_eGLConfig[v]);
            }
        }
    }

    static class ContextFactory implements GLSurfaceView.EGLContextFactory {
        private static int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

        static {
        }

        @Override  // android.opengl.GLSurfaceView$EGLContextFactory
        public EGLContext createContext(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0) {
            Log.w("GL2JNIView", "creating OpenGL ES " + GLSurfaceView20.targetGLESVersion + ".0 context");
            GLSurfaceView20.checkEglError(("Before eglCreateContext " + GLSurfaceView20.targetGLESVersion), eGL100);
            EGLContext eGLContext0 = eGL100.eglCreateContext(eGLDisplay0, eGLConfig0, EGL10.EGL_NO_CONTEXT, new int[]{ContextFactory.EGL_CONTEXT_CLIENT_VERSION, GLSurfaceView20.targetGLESVersion, 0x3038});
            if((!GLSurfaceView20.checkEglError(("After eglCreateContext " + GLSurfaceView20.targetGLESVersion), eGL100) || eGLContext0 == null) && GLSurfaceView20.targetGLESVersion > 2) {
                Log.w("GL2JNIView", "Falling back to GLES 2");
                GLSurfaceView20.targetGLESVersion = 2;
                return this.createContext(eGL100, eGLDisplay0, eGLConfig0);
            }
            Log.w("GL2JNIView", "Returning a GLES " + GLSurfaceView20.targetGLESVersion + " context");
            return eGLContext0;
        }

        @Override  // android.opengl.GLSurfaceView$EGLContextFactory
        public void destroyContext(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLContext eGLContext0) {
            eGL100.eglDestroyContext(eGLDisplay0, eGLContext0);
        }
    }

    private static final boolean DEBUG = false;
    static String TAG = "GL2JNIView";
    public OnscreenKeyboardType onscreenKeyboardType;
    final ResolutionStrategy resolutionStrategy;
    static int targetGLESVersion;

    static {
    }

    public GLSurfaceView20(Context context0, ResolutionStrategy resolutionStrategy0) {
        this(context0, resolutionStrategy0, 2);
    }

    public GLSurfaceView20(Context context0, ResolutionStrategy resolutionStrategy0, int v) {
        super(context0);
        this.onscreenKeyboardType = OnscreenKeyboardType.Default;
        GLSurfaceView20.targetGLESVersion = v;
        this.resolutionStrategy = resolutionStrategy0;
        this.init(false, 16, 0);
    }

    public GLSurfaceView20(Context context0, boolean z, int v, int v1, ResolutionStrategy resolutionStrategy0) {
        super(context0);
        this.onscreenKeyboardType = OnscreenKeyboardType.Default;
        this.resolutionStrategy = resolutionStrategy0;
        this.init(z, v, v1);
    }

    static boolean checkEglError(String s, EGL10 eGL100) {
        boolean z = true;
        int v;
        while((v = eGL100.eglGetError()) != 0x3000) {
            Log.e("GL2JNIView", String.format("%s: EGL error: 0x%x", s, v));
            z = false;
        }
        return z;
    }

    private void init(boolean z, int v, int v1) {
        if(z) {
            this.getHolder().setFormat(-3);
        }
        this.setEGLContextFactory(new ContextFactory());
        this.setEGLConfigChooser((z ? new ConfigChooser(8, 8, 8, 8, v, v1) : new ConfigChooser(8, 8, 8, 0, v, v1)));
    }

    @Override  // android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo0) {
        if(editorInfo0 != null) {
            editorInfo0.imeOptions |= 0x10000000;
            editorInfo0.inputType = DefaultAndroidInput.getAndroidInputType(this.onscreenKeyboardType);
        }
        return new BaseInputConnection(this, false) {
            @Override  // android.view.inputmethod.BaseInputConnection
            public boolean deleteSurroundingText(int v, int v1) {
                if(Build.VERSION.SDK_INT >= 16 && v == 1 && v1 == 0) {
                    this.sendDownUpKeyEventForBackwardCompatibility(67);
                    return true;
                }
                return super.deleteSurroundingText(v, v1);
            }

            @TargetApi(16)
            private void sendDownUpKeyEventForBackwardCompatibility(int v) {
                long v1 = SystemClock.uptimeMillis();
                super.sendKeyEvent(new KeyEvent(v1, v1, 0, v, 0, 0, -1, 0, 6));
                super.sendKeyEvent(new KeyEvent(SystemClock.uptimeMillis(), v1, 1, v, 0, 0, -1, 0, 6));
            }
        };
    }

    @Override  // android.opengl.GLSurfaceView
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override  // android.view.SurfaceView
    protected void onMeasure(int v, int v1) {
        MeasuredDimension resolutionStrategy$MeasuredDimension0 = this.resolutionStrategy.calcMeasures(v, v1);
        this.setMeasuredDimension(resolutionStrategy$MeasuredDimension0.width, resolutionStrategy$MeasuredDimension0.height);
    }
}

