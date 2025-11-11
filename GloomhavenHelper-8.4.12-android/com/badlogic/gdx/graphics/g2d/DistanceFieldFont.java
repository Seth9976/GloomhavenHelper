package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;

public class DistanceFieldFont extends BitmapFont {
    static class DistanceFieldFontCache extends BitmapFontCache {
        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont0) {
            super(distanceFieldFont0, distanceFieldFont0.usesIntegerPositions());
        }

        public DistanceFieldFontCache(DistanceFieldFont distanceFieldFont0, boolean z) {
            super(distanceFieldFont0, z);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch0) {
            this.setSmoothingUniform(batch0, this.getSmoothingFactor());
            super.draw(batch0);
            this.setSmoothingUniform(batch0, 0.0f);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.BitmapFontCache
        public void draw(Batch batch0, int v, int v1) {
            this.setSmoothingUniform(batch0, this.getSmoothingFactor());
            super.draw(batch0, v, v1);
            this.setSmoothingUniform(batch0, 0.0f);
        }

        private float getSmoothingFactor() {
            DistanceFieldFont distanceFieldFont0 = (DistanceFieldFont)super.getFont();
            return distanceFieldFont0.getDistanceFieldSmoothing() * distanceFieldFont0.getScaleX();
        }

        private void setSmoothingUniform(Batch batch0, float f) {
            batch0.flush();
            batch0.getShader().setUniformf("u_smoothing", f);
        }
    }

    private float distanceFieldSmoothing;

    public DistanceFieldFont(FileHandle fileHandle0) {
        super(fileHandle0);
    }

    public DistanceFieldFont(FileHandle fileHandle0, FileHandle fileHandle1, boolean z) {
        super(fileHandle0, fileHandle1, z);
    }

    public DistanceFieldFont(FileHandle fileHandle0, FileHandle fileHandle1, boolean z, boolean z1) {
        super(fileHandle0, fileHandle1, z, z1);
    }

    public DistanceFieldFont(FileHandle fileHandle0, TextureRegion textureRegion0) {
        super(fileHandle0, textureRegion0);
    }

    public DistanceFieldFont(FileHandle fileHandle0, TextureRegion textureRegion0, boolean z) {
        super(fileHandle0, textureRegion0, z);
    }

    public DistanceFieldFont(FileHandle fileHandle0, boolean z) {
        super(fileHandle0, z);
    }

    public DistanceFieldFont(BitmapFontData bitmapFont$BitmapFontData0, TextureRegion textureRegion0, boolean z) {
        super(bitmapFont$BitmapFontData0, textureRegion0, z);
    }

    public DistanceFieldFont(BitmapFontData bitmapFont$BitmapFontData0, Array array0, boolean z) {
        super(bitmapFont$BitmapFontData0, array0, z);
    }

    public static ShaderProgram createDistanceFieldShader() {
        ShaderProgram shaderProgram0 = new ShaderProgram("attribute vec4 a_position;\nattribute vec4 a_color;\nattribute vec2 a_texCoord0;\nuniform mat4 u_projTrans;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tv_color = a_color;\n\tv_color.a = v_color.a * (255.0/254.0);\n\tv_texCoords = a_texCoord0;\n\tgl_Position =  u_projTrans * a_position;\n}\n", "#ifdef GL_ES\n\tprecision mediump float;\n\tprecision mediump int;\n#endif\n\nuniform sampler2D u_texture;\nuniform float u_smoothing;\nvarying vec4 v_color;\nvarying vec2 v_texCoords;\n\nvoid main() {\n\tif (u_smoothing > 0.0) {\n\t\tfloat smoothing = 0.25 / u_smoothing;\n\t\tfloat distance = texture2D(u_texture, v_texCoords).a;\n\t\tfloat alpha = smoothstep(0.5 - smoothing, 0.5 + smoothing, distance);\n\t\tgl_FragColor = vec4(v_color.rgb, alpha * v_color.a);\n\t} else {\n\t\tgl_FragColor = v_color * texture2D(u_texture, v_texCoords);\n\t}\n}\n");
        if(!shaderProgram0.isCompiled()) {
            throw new IllegalArgumentException("Error compiling distance field shader: " + shaderProgram0.getLog());
        }
        return shaderProgram0;
    }

    public float getDistanceFieldSmoothing() {
        return this.distanceFieldSmoothing;
    }

    @Override  // com.badlogic.gdx.graphics.g2d.BitmapFont
    protected void load(BitmapFontData bitmapFont$BitmapFontData0) {
        super.load(bitmapFont$BitmapFontData0);
        for(Object object0: this.getRegions()) {
            ((TextureRegion)object0).getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
    }

    @Override  // com.badlogic.gdx.graphics.g2d.BitmapFont
    public BitmapFontCache newFontCache() {
        return new DistanceFieldFontCache(this, this.integer);
    }

    public void setDistanceFieldSmoothing(float f) {
        this.distanceFieldSmoothing = f;
    }
}

