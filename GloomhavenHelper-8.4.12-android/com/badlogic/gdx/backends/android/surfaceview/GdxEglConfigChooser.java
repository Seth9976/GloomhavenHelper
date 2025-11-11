package com.badlogic.gdx.backends.android.surfaceview;

import android.opengl.GLSurfaceView.EGLConfigChooser;
import android.util.Log;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLDisplay;

public class GdxEglConfigChooser implements GLSurfaceView.EGLConfigChooser {
    public static final int EGL_COVERAGE_BUFFERS_NV = 0x30E0;
    public static final int EGL_COVERAGE_SAMPLES_NV = 0x30E1;
    private static final int EGL_OPENGL_ES2_BIT = 4;
    private static final String TAG = "GdxEglConfigChooser";
    protected int mAlphaSize;
    protected int mBlueSize;
    protected final int[] mConfigAttribs;
    protected int mDepthSize;
    protected int mGreenSize;
    protected int mNumSamples;
    protected int mRedSize;
    protected int mStencilSize;
    private int[] mValue;

    public GdxEglConfigChooser(int v, int v1, int v2, int v3, int v4, int v5, int v6) {
        this.mValue = new int[1];
        this.mRedSize = v;
        this.mGreenSize = v1;
        this.mBlueSize = v2;
        this.mAlphaSize = v3;
        this.mDepthSize = v4;
        this.mStencilSize = v5;
        this.mNumSamples = v6;
        this.mConfigAttribs = new int[]{0x3024, 4, 0x3023, 4, 0x3022, 4, 0x3040, 4, 0x3038};
    }

    @Override  // android.opengl.GLSurfaceView$EGLConfigChooser
    public EGLConfig chooseConfig(EGL10 eGL100, EGLDisplay eGLDisplay0) {
        int[] arr_v = new int[1];
        eGL100.eglChooseConfig(eGLDisplay0, this.mConfigAttribs, null, 0, arr_v);
        int v = arr_v[0];
        if(v <= 0) {
            throw new IllegalArgumentException("No configs match configSpec");
        }
        EGLConfig[] arr_eGLConfig = new EGLConfig[v];
        eGL100.eglChooseConfig(eGLDisplay0, this.mConfigAttribs, arr_eGLConfig, v, arr_v);
        return this.chooseConfig(eGL100, eGLDisplay0, arr_eGLConfig);
    }

    public EGLConfig chooseConfig(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig[] arr_eGLConfig) {
        EGLConfig eGLConfig3;
        EGLConfig eGLConfig0 = null;
        EGLConfig eGLConfig1 = null;
        EGLConfig eGLConfig2 = null;
        for(int v = 0; true; ++v) {
            if(v >= arr_eGLConfig.length) {
                eGLConfig3 = eGLConfig1;
                break;
            }
            eGLConfig3 = arr_eGLConfig[v];
            int v1 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3025, 0);
            int v2 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3026, 0);
            if(v1 >= this.mDepthSize && v2 >= this.mStencilSize) {
                int v3 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3024, 0);
                int v4 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3023, 0);
                int v5 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3022, 0);
                int v6 = this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3021, 0);
                if(eGLConfig0 == null && v3 == 5 && v4 == 6 && v5 == 5 && v6 == 0) {
                    eGLConfig0 = eGLConfig3;
                }
                if(eGLConfig1 == null && v3 == this.mRedSize && v4 == this.mGreenSize && v5 == this.mBlueSize && v6 == this.mAlphaSize) {
                    if(this.mNumSamples == 0) {
                        break;
                    }
                    eGLConfig1 = eGLConfig3;
                }
                if(eGLConfig2 == null && this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3032, 0) == 1 && this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x3031, 0) >= this.mNumSamples && v3 == this.mRedSize && v4 == this.mGreenSize && v5 == this.mBlueSize && v6 == this.mAlphaSize || eGLConfig2 == null && this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x30E0, 0) == 1 && this.findConfigAttrib(eGL100, eGLDisplay0, eGLConfig3, 0x30E1, 0) >= this.mNumSamples && v3 == this.mRedSize && v4 == this.mGreenSize && v5 == this.mBlueSize && v6 == this.mAlphaSize) {
                    eGLConfig2 = eGLConfig3;
                }
            }
        }
        if(eGLConfig2 != null) {
            return eGLConfig2;
        }
        return eGLConfig3 == null ? eGLConfig0 : eGLConfig3;
    }

    // 去混淆评级： 低(20)
    private int findConfigAttrib(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0, int v, int v1) {
        return eGL100.eglGetConfigAttrib(eGLDisplay0, eGLConfig0, v, this.mValue) ? this.mValue[0] : v1;
    }

    private void printConfig(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig eGLConfig0) {
        int[] arr_v = new int[1];
        for(int v = 0; v < 35; ++v) {
            int v1 = new int[]{0x3020, 0x3021, 0x3022, 0x3023, 0x3024, 0x3025, 0x3026, 0x3027, 0x3028, 0x3029, 0x302A, 0x302B, 0x302C, 0x302D, 0x302E, 0x302F, 0x3030, 0x3031, 0x3032, 0x3033, 0x3034, 0x3037, 0x3036, 0x3035, 0x3039, 0x303A, 0x303B, 0x303C, 0x303D, 0x303E, 0x303F, 0x3040, 0x3042, 0x30E0, 0x30E1}[v];
            String s = new String[]{"EGL_BUFFER_SIZE", "EGL_ALPHA_SIZE", "EGL_BLUE_SIZE", "EGL_GREEN_SIZE", "EGL_RED_SIZE", "EGL_DEPTH_SIZE", "EGL_STENCIL_SIZE", "EGL_CONFIG_CAVEAT", "EGL_CONFIG_ID", "EGL_LEVEL", "EGL_MAX_PBUFFER_HEIGHT", "EGL_MAX_PBUFFER_PIXELS", "EGL_MAX_PBUFFER_WIDTH", "EGL_NATIVE_RENDERABLE", "EGL_NATIVE_VISUAL_ID", "EGL_NATIVE_VISUAL_TYPE", "EGL_PRESERVED_RESOURCES", "EGL_SAMPLES", "EGL_SAMPLE_BUFFERS", "EGL_SURFACE_TYPE", "EGL_TRANSPARENT_TYPE", "EGL_TRANSPARENT_RED_VALUE", "EGL_TRANSPARENT_GREEN_VALUE", "EGL_TRANSPARENT_BLUE_VALUE", "EGL_BIND_TO_TEXTURE_RGB", "EGL_BIND_TO_TEXTURE_RGBA", "EGL_MIN_SWAP_INTERVAL", "EGL_MAX_SWAP_INTERVAL", "EGL_LUMINANCE_SIZE", "EGL_ALPHA_MASK_SIZE", "EGL_COLOR_BUFFER_TYPE", "EGL_RENDERABLE_TYPE", "EGL_CONFORMANT", "EGL_COVERAGE_BUFFERS_NV", "EGL_COVERAGE_SAMPLES_NV"}[v];
            if(eGL100.eglGetConfigAttrib(eGLDisplay0, eGLConfig0, v1, arr_v)) {
                Log.w("GdxEglConfigChooser", String.format("  %s: %d\n", s, ((int)arr_v[0])));
            }
            else {
                eGL100.eglGetError();
            }
        }
    }

    private void printConfigs(EGL10 eGL100, EGLDisplay eGLDisplay0, EGLConfig[] arr_eGLConfig) {
        Log.w("GdxEglConfigChooser", String.format("%d configurations", ((int)arr_eGLConfig.length)));
        for(int v = 0; v < arr_eGLConfig.length; ++v) {
            Log.w("GdxEglConfigChooser", String.format("Configuration %d:\n", v));
            this.printConfig(eGL100, eGLDisplay0, arr_eGLConfig[v]);
        }
    }
}

