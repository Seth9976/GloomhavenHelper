package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

public class ParticleEmitter {
    public static class GradientColorValue extends ParticleValue {
        private float[] colors;
        private static float[] temp;
        float[] timeline;

        static {
            GradientColorValue.temp = new float[4];
        }

        public GradientColorValue() {
            this.colors = new float[]{1.0f, 1.0f, 1.0f};
            this.timeline = new float[]{0.0f};
            this.alwaysActive = true;
        }

        public float[] getColor(float f) {
            float[] arr_f = this.timeline;
            int v = 1;
            int v1 = 0;
            while(true) {
                if(v >= arr_f.length) {
                    v = -1;
                    break;
                }
                if(arr_f[v] > f) {
                    break;
                }
                v1 = v;
                ++v;
            }
            float f1 = arr_f[v1];
            float[] arr_f1 = this.colors;
            float f2 = arr_f1[v1 * 3];
            float f3 = arr_f1[v1 * 3 + 1];
            float f4 = arr_f1[v1 * 3 + 2];
            if(v == -1) {
                float[] arr_f2 = GradientColorValue.temp;
                arr_f2[0] = f2;
                arr_f2[1] = f3;
                arr_f2[2] = f4;
                return arr_f2;
            }
            float f5 = (f - f1) / (arr_f[v] - f1);
            float[] arr_f3 = GradientColorValue.temp;
            arr_f3[0] = f2 + (arr_f1[v * 3] - f2) * f5;
            arr_f3[1] = f3 + (arr_f1[v * 3 + 1] - f3) * f5;
            arr_f3[2] = f4 + (arr_f1[v * 3 + 2] - f4) * f5;
            return arr_f3;
        }

        public float[] getColors() {
            return this.colors;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        public void load(GradientColorValue particleEmitter$GradientColorValue0) {
            super.load(particleEmitter$GradientColorValue0);
            this.colors = new float[particleEmitter$GradientColorValue0.colors.length];
            System.arraycopy(particleEmitter$GradientColorValue0.colors, 0, this.colors, 0, this.colors.length);
            this.timeline = new float[particleEmitter$GradientColorValue0.timeline.length];
            System.arraycopy(particleEmitter$GradientColorValue0.timeline, 0, this.timeline, 0, this.timeline.length);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(!this.active) {
                return;
            }
            this.colors = new float[ParticleEmitter.readInt(bufferedReader0, "colorsCount")];
            for(int v1 = 0; true; ++v1) {
                float[] arr_f = this.colors;
                if(v1 >= arr_f.length) {
                    break;
                }
                arr_f[v1] = ParticleEmitter.readFloat(bufferedReader0, "colors" + v1);
            }
            this.timeline = new float[ParticleEmitter.readInt(bufferedReader0, "timelineCount")];
            for(int v = 0; true; ++v) {
                float[] arr_f1 = this.timeline;
                if(v >= arr_f1.length) {
                    break;
                }
                arr_f1[v] = ParticleEmitter.readFloat(bufferedReader0, "timeline" + v);
            }
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            if(!this.active) {
                return;
            }
            writer0.write("colorsCount: " + this.colors.length + "\n");
            for(int v1 = 0; v1 < this.colors.length; ++v1) {
                writer0.write("colors" + v1 + ": " + this.colors[v1] + "\n");
            }
            writer0.write("timelineCount: " + this.timeline.length + "\n");
            for(int v = 0; v < this.timeline.length; ++v) {
                writer0.write("timeline" + v + ": " + this.timeline[v] + "\n");
            }
        }

        public void setColors(float[] arr_f) {
            this.colors = arr_f;
        }

        public void setTimeline(float[] arr_f) {
            this.timeline = arr_f;
        }
    }

    public static class IndependentScaledNumericValue extends ScaledNumericValue {
        boolean independent;

        public boolean isIndependent() {
            return this.independent;
        }

        public void load(IndependentScaledNumericValue particleEmitter$IndependentScaledNumericValue0) {
            super.load(particleEmitter$IndependentScaledNumericValue0);
            this.independent = particleEmitter$IndependentScaledNumericValue0.independent;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ScaledNumericValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(bufferedReader0.markSupported()) {
                bufferedReader0.mark(100);
            }
            String s = bufferedReader0.readLine();
            if(s == null) {
                throw new IOException("Missing value: independent");
            }
            if(s.contains("independent")) {
                this.independent = Boolean.parseBoolean(ParticleEmitter.readString(s));
                return;
            }
            if(bufferedReader0.markSupported()) {
                bufferedReader0.reset();
                return;
            }
            Gdx.app.error("ParticleEmitter", "The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
            throw new IOException("The loaded particle effect descriptor file uses an old invalid format. Please download the latest version of the Particle Editor tool and recreate the file by loading and saving it again.");
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ScaledNumericValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            writer0.write("independent: " + this.independent + "\n");
        }

        public void set(IndependentScaledNumericValue particleEmitter$IndependentScaledNumericValue0) {
            super.set(particleEmitter$IndependentScaledNumericValue0);
            this.independent = particleEmitter$IndependentScaledNumericValue0.independent;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ScaledNumericValue
        public void set(RangedNumericValue particleEmitter$RangedNumericValue0) {
            if(particleEmitter$RangedNumericValue0 instanceof IndependentScaledNumericValue) {
                this.set(((IndependentScaledNumericValue)particleEmitter$RangedNumericValue0));
                return;
            }
            super.set(particleEmitter$RangedNumericValue0);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ScaledNumericValue
        public void set(ScaledNumericValue particleEmitter$ScaledNumericValue0) {
            if(particleEmitter$ScaledNumericValue0 instanceof IndependentScaledNumericValue) {
                this.set(((IndependentScaledNumericValue)particleEmitter$ScaledNumericValue0));
                return;
            }
            super.set(particleEmitter$ScaledNumericValue0);
        }

        public void setIndependent(boolean z) {
            this.independent = z;
        }
    }

    public static class NumericValue extends ParticleValue {
        private float value;

        public float getValue() {
            return this.value;
        }

        public void load(NumericValue particleEmitter$NumericValue0) {
            super.load(particleEmitter$NumericValue0);
            this.value = particleEmitter$NumericValue0.value;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(!this.active) {
                return;
            }
            this.value = ParticleEmitter.readFloat(bufferedReader0, "value");
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            if(!this.active) {
                return;
            }
            writer0.write("value: " + this.value + "\n");
        }

        public void setValue(float f) {
            this.value = f;
        }
    }

    public static class Particle extends Sprite {
        protected float angle;
        protected float angleCos;
        protected float angleDiff;
        protected float angleSin;
        protected int currentLife;
        protected int frame;
        protected float gravity;
        protected float gravityDiff;
        protected int life;
        protected float rotation;
        protected float rotationDiff;
        protected float[] tint;
        protected float transparency;
        protected float transparencyDiff;
        protected float velocity;
        protected float velocityDiff;
        protected float wind;
        protected float windDiff;
        protected float xScale;
        protected float xScaleDiff;
        protected float yScale;
        protected float yScaleDiff;

        public Particle(Sprite sprite0) {
            super(sprite0);
        }
    }

    public static class ParticleValue {
        boolean active;
        boolean alwaysActive;

        // 去混淆评级： 低(20)
        public boolean isActive() {
            return this.alwaysActive || this.active;
        }

        public boolean isAlwaysActive() {
            return this.alwaysActive;
        }

        public void load(ParticleValue particleEmitter$ParticleValue0) {
            this.active = particleEmitter$ParticleValue0.active;
            this.alwaysActive = particleEmitter$ParticleValue0.alwaysActive;
        }

        public void load(BufferedReader bufferedReader0) throws IOException {
            if(!this.alwaysActive) {
                this.active = ParticleEmitter.readBoolean(bufferedReader0, "active");
                return;
            }
            this.active = true;
        }

        public void save(Writer writer0) throws IOException {
            if(!this.alwaysActive) {
                writer0.write("active: " + this.active + "\n");
                return;
            }
            this.active = true;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void setAlwaysActive(boolean z) {
            this.alwaysActive = z;
        }
    }

    public static class RangedNumericValue extends ParticleValue {
        private float lowMax;
        private float lowMin;

        public float getLowMax() {
            return this.lowMax;
        }

        public float getLowMin() {
            return this.lowMin;
        }

        public void load(RangedNumericValue particleEmitter$RangedNumericValue0) {
            super.load(particleEmitter$RangedNumericValue0);
            this.lowMax = particleEmitter$RangedNumericValue0.lowMax;
            this.lowMin = particleEmitter$RangedNumericValue0.lowMin;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(!this.active) {
                return;
            }
            this.lowMin = ParticleEmitter.readFloat(bufferedReader0, "lowMin");
            this.lowMax = ParticleEmitter.readFloat(bufferedReader0, "lowMax");
        }

        public float newLowValue() {
            return this.lowMin + (this.lowMax - this.lowMin) * MathUtils.random();
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            if(!this.active) {
                return;
            }
            writer0.write("lowMin: " + this.lowMin + "\n");
            writer0.write("lowMax: " + this.lowMax + "\n");
        }

        public void scale(float f) {
            this.lowMin *= f;
            this.lowMax *= f;
        }

        public void set(RangedNumericValue particleEmitter$RangedNumericValue0) {
            this.lowMin = particleEmitter$RangedNumericValue0.lowMin;
            this.lowMax = particleEmitter$RangedNumericValue0.lowMax;
        }

        public void setLow(float f) {
            this.lowMin = f;
            this.lowMax = f;
        }

        public void setLow(float f, float f1) {
            this.lowMin = f;
            this.lowMax = f1;
        }

        public void setLowMax(float f) {
            this.lowMax = f;
        }

        public void setLowMin(float f) {
            this.lowMin = f;
        }
    }

    public static class ScaledNumericValue extends RangedNumericValue {
        private float highMax;
        private float highMin;
        private boolean relative;
        private float[] scaling;
        float[] timeline;

        public ScaledNumericValue() {
            this.scaling = new float[]{1.0f};
            this.timeline = new float[]{0.0f};
        }

        public float getHighMax() {
            return this.highMax;
        }

        public float getHighMin() {
            return this.highMin;
        }

        public float getScale(float f) {
            float[] arr_f = this.timeline;
            int v;
            for(v = 1; true; ++v) {
                if(v >= arr_f.length) {
                    v = -1;
                    break;
                }
                if(arr_f[v] > f) {
                    break;
                }
            }
            if(v == -1) {
                return this.scaling[arr_f.length - 1];
            }
            float[] arr_f1 = this.scaling;
            float f1 = arr_f1[v - 1];
            float f2 = arr_f[v - 1];
            return f1 + (arr_f1[v] - f1) * ((f - f2) / (arr_f[v] - f2));
        }

        public float[] getScaling() {
            return this.scaling;
        }

        public float[] getTimeline() {
            return this.timeline;
        }

        public boolean isRelative() {
            return this.relative;
        }

        public void load(ScaledNumericValue particleEmitter$ScaledNumericValue0) {
            super.load(particleEmitter$ScaledNumericValue0);
            this.highMax = particleEmitter$ScaledNumericValue0.highMax;
            this.highMin = particleEmitter$ScaledNumericValue0.highMin;
            this.scaling = new float[particleEmitter$ScaledNumericValue0.scaling.length];
            System.arraycopy(particleEmitter$ScaledNumericValue0.scaling, 0, this.scaling, 0, this.scaling.length);
            this.timeline = new float[particleEmitter$ScaledNumericValue0.timeline.length];
            System.arraycopy(particleEmitter$ScaledNumericValue0.timeline, 0, this.timeline, 0, this.timeline.length);
            this.relative = particleEmitter$ScaledNumericValue0.relative;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$RangedNumericValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(!this.active) {
                return;
            }
            this.highMin = ParticleEmitter.readFloat(bufferedReader0, "highMin");
            this.highMax = ParticleEmitter.readFloat(bufferedReader0, "highMax");
            this.relative = ParticleEmitter.readBoolean(bufferedReader0, "relative");
            this.scaling = new float[ParticleEmitter.readInt(bufferedReader0, "scalingCount")];
            for(int v1 = 0; true; ++v1) {
                float[] arr_f = this.scaling;
                if(v1 >= arr_f.length) {
                    break;
                }
                arr_f[v1] = ParticleEmitter.readFloat(bufferedReader0, "scaling" + v1);
            }
            this.timeline = new float[ParticleEmitter.readInt(bufferedReader0, "timelineCount")];
            for(int v = 0; true; ++v) {
                float[] arr_f1 = this.timeline;
                if(v >= arr_f1.length) {
                    break;
                }
                arr_f1[v] = ParticleEmitter.readFloat(bufferedReader0, "timeline" + v);
            }
        }

        public float newHighValue() {
            return this.highMin + (this.highMax - this.highMin) * MathUtils.random();
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$RangedNumericValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            if(!this.active) {
                return;
            }
            writer0.write("highMin: " + this.highMin + "\n");
            writer0.write("highMax: " + this.highMax + "\n");
            writer0.write("relative: " + this.relative + "\n");
            writer0.write("scalingCount: " + this.scaling.length + "\n");
            for(int v1 = 0; v1 < this.scaling.length; ++v1) {
                writer0.write("scaling" + v1 + ": " + this.scaling[v1] + "\n");
            }
            writer0.write("timelineCount: " + this.timeline.length + "\n");
            for(int v = 0; v < this.timeline.length; ++v) {
                writer0.write("timeline" + v + ": " + this.timeline[v] + "\n");
            }
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$RangedNumericValue
        public void scale(float f) {
            super.scale(f);
            this.highMin *= f;
            this.highMax *= f;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$RangedNumericValue
        public void set(RangedNumericValue particleEmitter$RangedNumericValue0) {
            if(particleEmitter$RangedNumericValue0 instanceof ScaledNumericValue) {
                this.set(((ScaledNumericValue)particleEmitter$RangedNumericValue0));
                return;
            }
            super.set(particleEmitter$RangedNumericValue0);
        }

        public void set(ScaledNumericValue particleEmitter$ScaledNumericValue0) {
            super.set(particleEmitter$ScaledNumericValue0);
            this.highMin = particleEmitter$ScaledNumericValue0.highMin;
            this.highMax = particleEmitter$ScaledNumericValue0.highMax;
            float[] arr_f = this.scaling;
            float[] arr_f1 = particleEmitter$ScaledNumericValue0.scaling;
            if(arr_f.length == arr_f1.length) {
                System.arraycopy(arr_f1, 0, arr_f, 0, arr_f.length);
            }
            else {
                this.scaling = Arrays.copyOf(arr_f1, arr_f1.length);
            }
            float[] arr_f2 = this.timeline;
            float[] arr_f3 = particleEmitter$ScaledNumericValue0.timeline;
            if(arr_f2.length == arr_f3.length) {
                System.arraycopy(arr_f3, 0, arr_f2, 0, arr_f2.length);
            }
            else {
                this.timeline = Arrays.copyOf(arr_f3, arr_f3.length);
            }
            this.relative = particleEmitter$ScaledNumericValue0.relative;
        }

        public void setHigh(float f) {
            this.highMin = f;
            this.highMax = f;
        }

        public void setHigh(float f, float f1) {
            this.highMin = f;
            this.highMax = f1;
        }

        public void setHighMax(float f) {
            this.highMax = f;
        }

        public void setHighMin(float f) {
            this.highMin = f;
        }

        public void setRelative(boolean z) {
            this.relative = z;
        }

        public void setScaling(float[] arr_f) {
            this.scaling = arr_f;
        }

        public void setTimeline(float[] arr_f) {
            this.timeline = arr_f;
        }
    }

    public static enum SpawnEllipseSide {
        both,
        top,
        bottom;

    }

    public static enum SpawnShape {
        point,
        line,
        square,
        ellipse;

    }

    public static class SpawnShapeValue extends ParticleValue {
        boolean edges;
        SpawnShape shape;
        SpawnEllipseSide side;

        public SpawnShapeValue() {
            this.shape = SpawnShape.point;
            this.side = SpawnEllipseSide.both;
        }

        public SpawnShape getShape() {
            return this.shape;
        }

        public SpawnEllipseSide getSide() {
            return this.side;
        }

        public boolean isEdges() {
            return this.edges;
        }

        public void load(SpawnShapeValue particleEmitter$SpawnShapeValue0) {
            super.load(particleEmitter$SpawnShapeValue0);
            this.shape = particleEmitter$SpawnShapeValue0.shape;
            this.edges = particleEmitter$SpawnShapeValue0.edges;
            this.side = particleEmitter$SpawnShapeValue0.side;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void load(BufferedReader bufferedReader0) throws IOException {
            super.load(bufferedReader0);
            if(!this.active) {
                return;
            }
            this.shape = SpawnShape.valueOf(ParticleEmitter.readString(bufferedReader0, "shape"));
            if(this.shape == SpawnShape.ellipse) {
                this.edges = ParticleEmitter.readBoolean(bufferedReader0, "edges");
                this.side = SpawnEllipseSide.valueOf(ParticleEmitter.readString(bufferedReader0, "side"));
            }
        }

        @Override  // com.badlogic.gdx.graphics.g2d.ParticleEmitter$ParticleValue
        public void save(Writer writer0) throws IOException {
            super.save(writer0);
            if(!this.active) {
                return;
            }
            writer0.write("shape: " + this.shape + "\n");
            if(this.shape == SpawnShape.ellipse) {
                writer0.write("edges: " + this.edges + "\n");
                writer0.write("side: " + this.side + "\n");
            }
        }

        public void setEdges(boolean z) {
            this.edges = z;
        }

        public void setShape(SpawnShape particleEmitter$SpawnShape0) {
            this.shape = particleEmitter$SpawnShape0;
        }

        public void setSide(SpawnEllipseSide particleEmitter$SpawnEllipseSide0) {
            this.side = particleEmitter$SpawnEllipseSide0;
        }
    }

    public static enum SpriteMode {
        single,
        random,
        animated;

    }

    private static final int UPDATE_ANGLE = 2;
    private static final int UPDATE_GRAVITY = 0x20;
    private static final int UPDATE_ROTATION = 4;
    private static final int UPDATE_SCALE = 1;
    private static final int UPDATE_SPRITE = 0x80;
    private static final int UPDATE_TINT = 0x40;
    private static final int UPDATE_VELOCITY = 8;
    private static final int UPDATE_WIND = 16;
    private float accumulator;
    private boolean[] active;
    private int activeCount;
    private boolean additive;
    private boolean aligned;
    private boolean allowCompletion;
    private ScaledNumericValue angleValue;
    private boolean attached;
    private boolean behind;
    private BoundingBox bounds;
    boolean cleansUpBlendFunction;
    private boolean continuous;
    private float delay;
    private float delayTimer;
    private RangedNumericValue delayValue;
    public float duration;
    public float durationTimer;
    private RangedNumericValue durationValue;
    private int emission;
    private int emissionDelta;
    private int emissionDiff;
    private ScaledNumericValue emissionValue;
    private boolean firstUpdate;
    private boolean flipX;
    private boolean flipY;
    private ScaledNumericValue gravityValue;
    private Array imagePaths;
    private int life;
    private int lifeDiff;
    private int lifeOffset;
    private int lifeOffsetDiff;
    private IndependentScaledNumericValue lifeOffsetValue;
    private IndependentScaledNumericValue lifeValue;
    private int maxParticleCount;
    private int minParticleCount;
    private RangedNumericValue[] motionValues;
    private String name;
    private Particle[] particles;
    private boolean premultipliedAlpha;
    private ScaledNumericValue rotationValue;
    private float spawnHeight;
    private float spawnHeightDiff;
    private ScaledNumericValue spawnHeightValue;
    private SpawnShapeValue spawnShapeValue;
    private float spawnWidth;
    private float spawnWidthDiff;
    private ScaledNumericValue spawnWidthValue;
    private SpriteMode spriteMode;
    private Array sprites;
    private GradientColorValue tintValue;
    private ScaledNumericValue transparencyValue;
    private int updateFlags;
    private ScaledNumericValue velocityValue;
    private ScaledNumericValue windValue;
    private float x;
    private RangedNumericValue xOffsetValue;
    private ScaledNumericValue xScaleValue;
    private RangedNumericValue[] xSizeValues;
    private float y;
    private RangedNumericValue yOffsetValue;
    private ScaledNumericValue yScaleValue;
    private RangedNumericValue[] ySizeValues;

    public ParticleEmitter() {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        this.initialize();
    }

    public ParticleEmitter(ParticleEmitter particleEmitter0) {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        this.sprites = new Array(particleEmitter0.sprites);
        this.name = particleEmitter0.name;
        this.imagePaths = new Array(particleEmitter0.imagePaths);
        this.setMaxParticleCount(particleEmitter0.maxParticleCount);
        this.minParticleCount = particleEmitter0.minParticleCount;
        this.delayValue.load(particleEmitter0.delayValue);
        this.durationValue.load(particleEmitter0.durationValue);
        this.emissionValue.load(particleEmitter0.emissionValue);
        this.lifeValue.load(particleEmitter0.lifeValue);
        this.lifeOffsetValue.load(particleEmitter0.lifeOffsetValue);
        this.xScaleValue.load(particleEmitter0.xScaleValue);
        this.yScaleValue.load(particleEmitter0.yScaleValue);
        this.rotationValue.load(particleEmitter0.rotationValue);
        this.velocityValue.load(particleEmitter0.velocityValue);
        this.angleValue.load(particleEmitter0.angleValue);
        this.windValue.load(particleEmitter0.windValue);
        this.gravityValue.load(particleEmitter0.gravityValue);
        this.transparencyValue.load(particleEmitter0.transparencyValue);
        this.tintValue.load(particleEmitter0.tintValue);
        this.xOffsetValue.load(particleEmitter0.xOffsetValue);
        this.yOffsetValue.load(particleEmitter0.yOffsetValue);
        this.spawnWidthValue.load(particleEmitter0.spawnWidthValue);
        this.spawnHeightValue.load(particleEmitter0.spawnHeightValue);
        this.spawnShapeValue.load(particleEmitter0.spawnShapeValue);
        this.attached = particleEmitter0.attached;
        this.continuous = particleEmitter0.continuous;
        this.aligned = particleEmitter0.aligned;
        this.behind = particleEmitter0.behind;
        this.additive = particleEmitter0.additive;
        this.premultipliedAlpha = particleEmitter0.premultipliedAlpha;
        this.cleansUpBlendFunction = particleEmitter0.cleansUpBlendFunction;
        this.spriteMode = particleEmitter0.spriteMode;
        this.setPosition(particleEmitter0.getX(), particleEmitter0.getY());
    }

    public ParticleEmitter(BufferedReader bufferedReader0) throws IOException {
        this.delayValue = new RangedNumericValue();
        this.lifeOffsetValue = new IndependentScaledNumericValue();
        this.durationValue = new RangedNumericValue();
        this.lifeValue = new IndependentScaledNumericValue();
        this.emissionValue = new ScaledNumericValue();
        this.xScaleValue = new ScaledNumericValue();
        this.yScaleValue = new ScaledNumericValue();
        this.rotationValue = new ScaledNumericValue();
        this.velocityValue = new ScaledNumericValue();
        this.angleValue = new ScaledNumericValue();
        this.windValue = new ScaledNumericValue();
        this.gravityValue = new ScaledNumericValue();
        this.transparencyValue = new ScaledNumericValue();
        this.tintValue = new GradientColorValue();
        this.xOffsetValue = new ScaledNumericValue();
        this.yOffsetValue = new ScaledNumericValue();
        this.spawnWidthValue = new ScaledNumericValue();
        this.spawnHeightValue = new ScaledNumericValue();
        this.spawnShapeValue = new SpawnShapeValue();
        this.spriteMode = SpriteMode.single;
        this.maxParticleCount = 4;
        this.duration = 1.0f;
        this.additive = true;
        this.premultipliedAlpha = false;
        this.cleansUpBlendFunction = true;
        this.initialize();
        this.load(bufferedReader0);
    }

    private void activateParticle(int v) {
        float f16;
        float f15;
        float f12;
        float f1;
        Sprite sprite0;
        switch(this.spriteMode) {
            case square: 
            case ellipse: {
                sprite0 = (Sprite)this.sprites.first();
                break;
            }
            case line: {
                sprite0 = (Sprite)this.sprites.random();
                break;
            }
            default: {
                sprite0 = null;
            }
        }
        Particle[] arr_particleEmitter$Particle = this.particles;
        Particle particleEmitter$Particle0 = arr_particleEmitter$Particle[v];
        if(particleEmitter$Particle0 == null) {
            particleEmitter$Particle0 = this.newParticle(sprite0);
            arr_particleEmitter$Particle[v] = particleEmitter$Particle0;
            particleEmitter$Particle0.flip(this.flipX, this.flipY);
        }
        else {
            particleEmitter$Particle0.set(sprite0);
        }
        float f = this.durationTimer / this.duration;
        int v1 = this.updateFlags;
        if(this.lifeValue.independent) {
            this.generateLifeValues();
        }
        if(this.lifeOffsetValue.independent) {
            this.generateLifeOffsetValues();
        }
        int v2 = this.life + ((int)(((float)this.lifeDiff) * this.lifeValue.getScale(f)));
        particleEmitter$Particle0.life = v2;
        particleEmitter$Particle0.currentLife = v2;
        if(this.velocityValue.active) {
            particleEmitter$Particle0.velocity = this.velocityValue.newLowValue();
            particleEmitter$Particle0.velocityDiff = this.velocityValue.newHighValue();
            if(!this.velocityValue.isRelative()) {
                particleEmitter$Particle0.velocityDiff -= particleEmitter$Particle0.velocity;
            }
        }
        particleEmitter$Particle0.angle = this.angleValue.newLowValue();
        particleEmitter$Particle0.angleDiff = this.angleValue.newHighValue();
        if(!this.angleValue.isRelative()) {
            particleEmitter$Particle0.angleDiff -= particleEmitter$Particle0.angle;
        }
        if((v1 & 2) == 0) {
            f1 = particleEmitter$Particle0.angle + particleEmitter$Particle0.angleDiff * this.angleValue.getScale(0.0f);
            particleEmitter$Particle0.angle = f1;
            particleEmitter$Particle0.angleCos = MathUtils.cosDeg(f1);
            particleEmitter$Particle0.angleSin = MathUtils.sinDeg(f1);
        }
        else {
            f1 = 0.0f;
        }
        float f2 = sprite0.getWidth();
        float f3 = sprite0.getHeight();
        particleEmitter$Particle0.xScale = this.xScaleValue.newLowValue() / f2;
        particleEmitter$Particle0.xScaleDiff = this.xScaleValue.newHighValue() / f2;
        if(!this.xScaleValue.isRelative()) {
            particleEmitter$Particle0.xScaleDiff -= particleEmitter$Particle0.xScale;
        }
        if(this.yScaleValue.active) {
            particleEmitter$Particle0.yScale = this.yScaleValue.newLowValue() / f3;
            particleEmitter$Particle0.yScaleDiff = this.yScaleValue.newHighValue() / f3;
            if(!this.yScaleValue.isRelative()) {
                particleEmitter$Particle0.yScaleDiff -= particleEmitter$Particle0.yScale;
            }
            particleEmitter$Particle0.setScale(particleEmitter$Particle0.xScale + particleEmitter$Particle0.xScaleDiff * this.xScaleValue.getScale(0.0f), particleEmitter$Particle0.yScale + particleEmitter$Particle0.yScaleDiff * this.yScaleValue.getScale(0.0f));
        }
        else {
            particleEmitter$Particle0.setScale(particleEmitter$Particle0.xScale + particleEmitter$Particle0.xScaleDiff * this.xScaleValue.getScale(0.0f));
        }
        if(this.rotationValue.active) {
            particleEmitter$Particle0.rotation = this.rotationValue.newLowValue();
            particleEmitter$Particle0.rotationDiff = this.rotationValue.newHighValue();
            if(!this.rotationValue.isRelative()) {
                particleEmitter$Particle0.rotationDiff -= particleEmitter$Particle0.rotation;
            }
            float f4 = particleEmitter$Particle0.rotation + particleEmitter$Particle0.rotationDiff * this.rotationValue.getScale(0.0f);
            if(this.aligned) {
                f4 += f1;
            }
            particleEmitter$Particle0.setRotation(f4);
        }
        if(this.windValue.active) {
            particleEmitter$Particle0.wind = this.windValue.newLowValue();
            particleEmitter$Particle0.windDiff = this.windValue.newHighValue();
            if(!this.windValue.isRelative()) {
                particleEmitter$Particle0.windDiff -= particleEmitter$Particle0.wind;
            }
        }
        if(this.gravityValue.active) {
            particleEmitter$Particle0.gravity = this.gravityValue.newLowValue();
            particleEmitter$Particle0.gravityDiff = this.gravityValue.newHighValue();
            if(!this.gravityValue.isRelative()) {
                particleEmitter$Particle0.gravityDiff -= particleEmitter$Particle0.gravity;
            }
        }
        float[] arr_f = particleEmitter$Particle0.tint;
        if(arr_f == null) {
            arr_f = new float[3];
            particleEmitter$Particle0.tint = arr_f;
        }
        float[] arr_f1 = this.tintValue.getColor(0.0f);
        arr_f[0] = arr_f1[0];
        arr_f[1] = arr_f1[1];
        arr_f[2] = arr_f1[2];
        particleEmitter$Particle0.transparency = this.transparencyValue.newLowValue();
        particleEmitter$Particle0.transparencyDiff = this.transparencyValue.newHighValue() - particleEmitter$Particle0.transparency;
        float f5 = this.xOffsetValue.active ? this.x + this.xOffsetValue.newLowValue() : this.x;
        float f6 = this.yOffsetValue.active ? this.y + this.yOffsetValue.newLowValue() : this.y;
        switch(this.spawnShapeValue.shape) {
            case square: {
                float f7 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
                float f8 = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
                f5 += MathUtils.random(f7) - f7 / 2.0f;
                f6 += MathUtils.random(f8) - f8 / 2.0f;
                break;
            }
            case ellipse: {
                float f9 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
                float f10 = (this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f)) / 2.0f;
                if(f9 / 2.0f != 0.0f && f10 != 0.0f) {
                    float f11 = f9 / 2.0f / f10;
                    if(this.spawnShapeValue.edges) {
                        switch(this.spawnShapeValue.side) {
                            case square: {
                                f12 = -MathUtils.random(179.0f);
                                break;
                            }
                            case ellipse: {
                                f12 = MathUtils.random(179.0f);
                                break;
                            }
                            default: {
                                f12 = MathUtils.random(360.0f);
                            }
                        }
                        float f13 = MathUtils.cosDeg(f12);
                        float f14 = MathUtils.sinDeg(f12);
                        f5 += f13 * (f9 / 2.0f);
                        f6 += f9 / 2.0f * f14 / f11;
                        if((v1 & 2) == 0) {
                            particleEmitter$Particle0.angle = f12;
                            particleEmitter$Particle0.angleCos = f13;
                            particleEmitter$Particle0.angleSin = f14;
                        }
                    }
                    else {
                        do {
                            f15 = MathUtils.random(f9) - f9 / 2.0f;
                            f16 = MathUtils.random(f9) - f9 / 2.0f;
                        }
                        while(f15 * f15 + f16 * f16 > f9 / 2.0f * (f9 / 2.0f));
                        f5 += f15;
                        f6 += f16 / f11;
                    }
                }
                break;
            }
            case line: {
                float f17 = this.spawnWidth + this.spawnWidthDiff * this.spawnWidthValue.getScale(f);
                float f18 = this.spawnHeight + this.spawnHeightDiff * this.spawnHeightValue.getScale(f);
                if(f17 == 0.0f) {
                    f6 += f18 * MathUtils.random();
                }
                else {
                    float f19 = MathUtils.random() * f17;
                    f5 += f19;
                    f6 += f19 * (f18 / f17);
                }
            }
        }
        particleEmitter$Particle0.setBounds(f5 - f2 / 2.0f, f6 - f3 / 2.0f, f2, f3);
        int v3 = (int)(((float)this.lifeOffset) + ((float)this.lifeOffsetDiff) * this.lifeOffsetValue.getScale(f));
        if(v3 > 0) {
            if(v3 >= particleEmitter$Particle0.currentLife) {
                v3 = particleEmitter$Particle0.currentLife - 1;
            }
            this.updateParticle(particleEmitter$Particle0, ((float)v3) / 1000.0f, v3);
        }
    }

    public void addParticle() {
        int v = this.activeCount;
        if(v == this.maxParticleCount) {
            return;
        }
        boolean[] arr_z = this.active;
        for(int v1 = 0; v1 < arr_z.length; ++v1) {
            if(!arr_z[v1]) {
                this.activateParticle(v1);
                arr_z[v1] = true;
                this.activeCount = v + 1;
                return;
            }
        }
    }

    public void addParticles(int v) {
        int v1 = Math.min(v, this.maxParticleCount - this.activeCount);
        if(v1 == 0) {
            return;
        }
        boolean[] arr_z = this.active;
        int v2 = 0;
        int v3 = 0;
    label_6:
        while(v2 < v1) {
            while(true) {
                if(v3 >= arr_z.length) {
                    break label_6;
                }
                if(arr_z[v3]) {
                    ++v3;
                    continue;
                }
                this.activateParticle(v3);
                arr_z[v3] = true;
                ++v2;
                ++v3;
                continue label_6;
            }
        }
        this.activeCount += v1;
    }

    public void allowCompletion() {
        this.allowCompletion = true;
        this.durationTimer = this.duration;
    }

    public boolean cleansUpBlendFunction() {
        return this.cleansUpBlendFunction;
    }

    public void draw(Batch batch0) {
        if(this.premultipliedAlpha) {
            batch0.setBlendFunction(1, 0x303);
        }
        else if(this.additive) {
            batch0.setBlendFunction(770, 1);
        }
        else {
            batch0.setBlendFunction(770, 0x303);
        }
        Particle[] arr_particleEmitter$Particle = this.particles;
        boolean[] arr_z = this.active;
        for(int v = 0; v < arr_z.length; ++v) {
            if(arr_z[v]) {
                arr_particleEmitter$Particle[v].draw(batch0);
            }
        }
        if(this.cleansUpBlendFunction && (this.additive || this.premultipliedAlpha)) {
            batch0.setBlendFunction(770, 0x303);
        }
    }

    public void draw(Batch batch0, float f) {
        this.accumulator += f * 1000.0f;
        float f1 = this.accumulator;
        if(f1 < 1.0f) {
            this.draw(batch0);
            return;
        }
        this.accumulator = f1 - ((float)(((int)f1)));
        if(this.premultipliedAlpha) {
            batch0.setBlendFunction(1, 0x303);
        }
        else if(this.additive) {
            batch0.setBlendFunction(770, 1);
        }
        else {
            batch0.setBlendFunction(770, 0x303);
        }
        Particle[] arr_particleEmitter$Particle = this.particles;
        boolean[] arr_z = this.active;
        int v = this.activeCount;
        for(int v1 = 0; v1 < arr_z.length; ++v1) {
            if(arr_z[v1]) {
                Particle particleEmitter$Particle0 = arr_particleEmitter$Particle[v1];
                if(this.updateParticle(particleEmitter$Particle0, f, ((int)f1))) {
                    particleEmitter$Particle0.draw(batch0);
                }
                else {
                    arr_z[v1] = false;
                    --v;
                }
            }
        }
        this.activeCount = v;
        if(this.cleansUpBlendFunction && (this.additive || this.premultipliedAlpha)) {
            batch0.setBlendFunction(770, 0x303);
        }
        float f2 = this.delayTimer;
        if(f2 < this.delay) {
            this.delayTimer = f2 + ((float)(((int)f1)));
            return;
        }
        if(this.firstUpdate) {
            this.firstUpdate = false;
            this.addParticle();
        }
        float f3 = this.durationTimer;
        if(f3 < this.duration) {
            this.durationTimer = f3 + ((float)(((int)f1)));
        }
        else if(this.continuous && !this.allowCompletion) {
            this.restart();
        }
        else {
            return;
        }
        this.emissionDelta += (int)f1;
        float f4 = ((float)this.emission) + ((float)this.emissionDiff) * this.emissionValue.getScale(this.durationTimer / this.duration);
        if(f4 > 0.0f) {
            int v2 = this.emissionDelta;
            if(((float)v2) >= 1000.0f / f4) {
                int v3 = Math.min(((int)(((float)v2) / (1000.0f / f4))), this.maxParticleCount - v);
                this.emissionDelta = (int)(((float)this.emissionDelta) - ((float)v3) * (1000.0f / f4));
                this.emissionDelta = (int)(((float)this.emissionDelta) % (1000.0f / f4));
                this.addParticles(v3);
            }
        }
        int v4 = this.minParticleCount;
        if(v < v4) {
            this.addParticles(v4 - v);
        }
    }

    public void flipY() {
        this.angleValue.setHigh(-this.angleValue.getHighMin(), -this.angleValue.getHighMax());
        this.angleValue.setLow(-this.angleValue.getLowMin(), -this.angleValue.getLowMax());
        this.gravityValue.setHigh(-this.gravityValue.getHighMin(), -this.gravityValue.getHighMax());
        this.gravityValue.setLow(-this.gravityValue.getLowMin(), -this.gravityValue.getLowMax());
        this.windValue.setHigh(-this.windValue.getHighMin(), -this.windValue.getHighMax());
        this.windValue.setLow(-this.windValue.getLowMin(), -this.windValue.getLowMax());
        this.rotationValue.setHigh(-this.rotationValue.getHighMin(), -this.rotationValue.getHighMax());
        this.rotationValue.setLow(-this.rotationValue.getLowMin(), -this.rotationValue.getLowMax());
        this.yOffsetValue.setLow(-this.yOffsetValue.getLowMin(), -this.yOffsetValue.getLowMax());
    }

    private void generateLifeOffsetValues() {
        this.lifeOffset = this.lifeOffsetValue.active ? ((int)this.lifeOffsetValue.newLowValue()) : 0;
        this.lifeOffsetDiff = (int)this.lifeOffsetValue.newHighValue();
        if(!this.lifeOffsetValue.isRelative()) {
            this.lifeOffsetDiff -= this.lifeOffset;
        }
    }

    private void generateLifeValues() {
        this.life = (int)this.lifeValue.newLowValue();
        this.lifeDiff = (int)this.lifeValue.newHighValue();
        if(!this.lifeValue.isRelative()) {
            this.lifeDiff -= this.life;
        }
    }

    public int getActiveCount() {
        return this.activeCount;
    }

    public ScaledNumericValue getAngle() {
        return this.angleValue;
    }

    public BoundingBox getBoundingBox() {
        if(this.bounds == null) {
            this.bounds = new BoundingBox();
        }
        Particle[] arr_particleEmitter$Particle = this.particles;
        boolean[] arr_z = this.active;
        BoundingBox boundingBox0 = this.bounds;
        boundingBox0.inf();
        for(int v = 0; v < arr_z.length; ++v) {
            if(arr_z[v]) {
                Rectangle rectangle0 = arr_particleEmitter$Particle[v].getBoundingRectangle();
                boundingBox0.ext(rectangle0.x, rectangle0.y, 0.0f);
                boundingBox0.ext(rectangle0.x + rectangle0.width, rectangle0.y + rectangle0.height, 0.0f);
            }
        }
        return boundingBox0;
    }

    public RangedNumericValue getDelay() {
        return this.delayValue;
    }

    public RangedNumericValue getDuration() {
        return this.durationValue;
    }

    public ScaledNumericValue getEmission() {
        return this.emissionValue;
    }

    public ScaledNumericValue getGravity() {
        return this.gravityValue;
    }

    public Array getImagePaths() {
        return this.imagePaths;
    }

    public ScaledNumericValue getLife() {
        return this.lifeValue;
    }

    public ScaledNumericValue getLifeOffset() {
        return this.lifeOffsetValue;
    }

    public int getMaxParticleCount() {
        return this.maxParticleCount;
    }

    public int getMinParticleCount() {
        return this.minParticleCount;
    }

    protected RangedNumericValue[] getMotionValues() {
        if(this.motionValues == null) {
            this.motionValues = new RangedNumericValue[3];
            RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.motionValues;
            arr_particleEmitter$RangedNumericValue[0] = this.velocityValue;
            arr_particleEmitter$RangedNumericValue[1] = this.windValue;
            arr_particleEmitter$RangedNumericValue[2] = this.gravityValue;
        }
        return this.motionValues;
    }

    public String getName() {
        return this.name;
    }

    protected Particle[] getParticles() {
        return this.particles;
    }

    public float getPercentComplete() {
        return this.delayTimer < this.delay ? 0.0f : Math.min(1.0f, this.durationTimer / this.duration);
    }

    public ScaledNumericValue getRotation() {
        return this.rotationValue;
    }

    public ScaledNumericValue getSpawnHeight() {
        return this.spawnHeightValue;
    }

    public SpawnShapeValue getSpawnShape() {
        return this.spawnShapeValue;
    }

    public ScaledNumericValue getSpawnWidth() {
        return this.spawnWidthValue;
    }

    public SpriteMode getSpriteMode() {
        return this.spriteMode;
    }

    public Array getSprites() {
        return this.sprites;
    }

    public GradientColorValue getTint() {
        return this.tintValue;
    }

    public ScaledNumericValue getTransparency() {
        return this.transparencyValue;
    }

    public ScaledNumericValue getVelocity() {
        return this.velocityValue;
    }

    public ScaledNumericValue getWind() {
        return this.windValue;
    }

    public float getX() {
        return this.x;
    }

    public RangedNumericValue getXOffsetValue() {
        return this.xOffsetValue;
    }

    public ScaledNumericValue getXScale() {
        return this.xScaleValue;
    }

    protected RangedNumericValue[] getXSizeValues() {
        if(this.xSizeValues == null) {
            this.xSizeValues = new RangedNumericValue[3];
            RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.xSizeValues;
            arr_particleEmitter$RangedNumericValue[0] = this.xScaleValue;
            arr_particleEmitter$RangedNumericValue[1] = this.spawnWidthValue;
            arr_particleEmitter$RangedNumericValue[2] = this.xOffsetValue;
        }
        return this.xSizeValues;
    }

    public float getY() {
        return this.y;
    }

    public RangedNumericValue getYOffsetValue() {
        return this.yOffsetValue;
    }

    public ScaledNumericValue getYScale() {
        return this.yScaleValue;
    }

    protected RangedNumericValue[] getYSizeValues() {
        if(this.ySizeValues == null) {
            this.ySizeValues = new RangedNumericValue[3];
            RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.ySizeValues;
            arr_particleEmitter$RangedNumericValue[0] = this.yScaleValue;
            arr_particleEmitter$RangedNumericValue[1] = this.spawnHeightValue;
            arr_particleEmitter$RangedNumericValue[2] = this.yOffsetValue;
        }
        return this.ySizeValues;
    }

    private void initialize() {
        this.sprites = new Array();
        this.imagePaths = new Array();
        this.durationValue.setAlwaysActive(true);
        this.emissionValue.setAlwaysActive(true);
        this.lifeValue.setAlwaysActive(true);
        this.xScaleValue.setAlwaysActive(true);
        this.transparencyValue.setAlwaysActive(true);
        this.spawnShapeValue.setAlwaysActive(true);
        this.spawnWidthValue.setAlwaysActive(true);
        this.spawnHeightValue.setAlwaysActive(true);
    }

    public boolean isAdditive() {
        return this.additive;
    }

    public boolean isAligned() {
        return this.aligned;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public boolean isBehind() {
        return this.behind;
    }

    public boolean isComplete() {
        if(this.continuous && !this.allowCompletion) {
            return false;
        }
        return this.delayTimer < this.delay ? false : this.durationTimer >= this.duration && this.activeCount == 0;
    }

    public boolean isContinuous() {
        return this.continuous;
    }

    public boolean isPremultipliedAlpha() {
        return this.premultipliedAlpha;
    }

    public void load(BufferedReader bufferedReader0) throws IOException {
        try {
            this.name = ParticleEmitter.readString(bufferedReader0, "name");
            bufferedReader0.readLine();
            this.delayValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.durationValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.setMinParticleCount(ParticleEmitter.readInt(bufferedReader0, "minParticleCount"));
            this.setMaxParticleCount(ParticleEmitter.readInt(bufferedReader0, "maxParticleCount"));
            bufferedReader0.readLine();
            this.emissionValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.lifeValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.lifeOffsetValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.xOffsetValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.yOffsetValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.spawnShapeValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.spawnWidthValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.spawnHeightValue.load(bufferedReader0);
            if(bufferedReader0.readLine().trim().equals("- Scale -")) {
                this.xScaleValue.load(bufferedReader0);
                this.yScaleValue.setActive(false);
            }
            else {
                this.xScaleValue.load(bufferedReader0);
                bufferedReader0.readLine();
                this.yScaleValue.load(bufferedReader0);
            }
            bufferedReader0.readLine();
            this.velocityValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.angleValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.rotationValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.windValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.gravityValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.tintValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.transparencyValue.load(bufferedReader0);
            bufferedReader0.readLine();
            this.attached = ParticleEmitter.readBoolean(bufferedReader0, "attached");
            this.continuous = ParticleEmitter.readBoolean(bufferedReader0, "continuous");
            this.aligned = ParticleEmitter.readBoolean(bufferedReader0, "aligned");
            this.additive = ParticleEmitter.readBoolean(bufferedReader0, "additive");
            this.behind = ParticleEmitter.readBoolean(bufferedReader0, "behind");
            String s = bufferedReader0.readLine();
            if(s.startsWith("premultipliedAlpha")) {
                this.premultipliedAlpha = ParticleEmitter.readBoolean(s);
                s = bufferedReader0.readLine();
            }
            if(s.startsWith("spriteMode")) {
                this.spriteMode = SpriteMode.valueOf(ParticleEmitter.readString(s));
                bufferedReader0.readLine();
            }
            Array array0 = new Array();
            while(true) {
                String s1 = bufferedReader0.readLine();
                if(s1 == null || s1.isEmpty()) {
                    break;
                }
                array0.add(s1);
            }
            this.setImagePaths(array0);
        }
        catch(RuntimeException runtimeException0) {
            throw this.name == null ? runtimeException0 : new RuntimeException("Error parsing emitter: " + this.name, runtimeException0);
        }
    }

    public void matchMotion(ParticleEmitter particleEmitter0) {
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.getMotionValues();
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue1 = particleEmitter0.getMotionValues();
        for(int v = 0; v < arr_particleEmitter$RangedNumericValue.length; ++v) {
            arr_particleEmitter$RangedNumericValue[v].set(arr_particleEmitter$RangedNumericValue1[v]);
        }
    }

    public void matchSize(ParticleEmitter particleEmitter0) {
        this.matchXSize(particleEmitter0);
        this.matchYSize(particleEmitter0);
    }

    public void matchXSize(ParticleEmitter particleEmitter0) {
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.getXSizeValues();
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue1 = particleEmitter0.getXSizeValues();
        for(int v = 0; v < arr_particleEmitter$RangedNumericValue.length; ++v) {
            arr_particleEmitter$RangedNumericValue[v].set(arr_particleEmitter$RangedNumericValue1[v]);
        }
    }

    public void matchYSize(ParticleEmitter particleEmitter0) {
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.getYSizeValues();
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue1 = particleEmitter0.getYSizeValues();
        for(int v = 0; v < arr_particleEmitter$RangedNumericValue.length; ++v) {
            arr_particleEmitter$RangedNumericValue[v].set(arr_particleEmitter$RangedNumericValue1[v]);
        }
    }

    protected Particle newParticle(Sprite sprite0) {
        return new Particle(sprite0);
    }

    public void preAllocateParticles() {
        if(this.sprites.isEmpty()) {
            throw new IllegalStateException("ParticleEmitter.setSprites() must have been called before preAllocateParticles()");
        }
        for(int v = 0; true; ++v) {
            Particle[] arr_particleEmitter$Particle = this.particles;
            if(v >= arr_particleEmitter$Particle.length) {
                break;
            }
            if(arr_particleEmitter$Particle[v] == null) {
                Particle particleEmitter$Particle0 = this.newParticle(((Sprite)this.sprites.first()));
                arr_particleEmitter$Particle[v] = particleEmitter$Particle0;
                particleEmitter$Particle0.flip(this.flipX, this.flipY);
            }
        }
    }

    static boolean readBoolean(BufferedReader bufferedReader0, String s) throws IOException {
        return Boolean.parseBoolean(ParticleEmitter.readString(bufferedReader0, s));
    }

    static boolean readBoolean(String s) throws IOException {
        return Boolean.parseBoolean(ParticleEmitter.readString(s));
    }

    static float readFloat(BufferedReader bufferedReader0, String s) throws IOException {
        return Float.parseFloat(ParticleEmitter.readString(bufferedReader0, s));
    }

    static int readInt(BufferedReader bufferedReader0, String s) throws IOException {
        return Integer.parseInt(ParticleEmitter.readString(bufferedReader0, s));
    }

    static String readString(BufferedReader bufferedReader0, String s) throws IOException {
        String s1 = bufferedReader0.readLine();
        if(s1 == null) {
            throw new IOException("Missing value: " + s);
        }
        return ParticleEmitter.readString(s1);
    }

    static String readString(String s) throws IOException {
        return s.substring(s.indexOf(":") + 1).trim();
    }

    public void reset() {
        this.emissionDelta = 0;
        this.durationTimer = this.duration;
        boolean[] arr_z = this.active;
        for(int v = 0; v < arr_z.length; ++v) {
            arr_z[v] = false;
        }
        this.activeCount = 0;
        this.start();
    }

    private void restart() {
        this.delay = this.delayValue.active ? this.delayValue.newLowValue() : 0.0f;
        this.delayTimer = 0.0f;
        this.durationTimer -= this.duration;
        this.duration = this.durationValue.newLowValue();
        this.emission = (int)this.emissionValue.newLowValue();
        this.emissionDiff = (int)this.emissionValue.newHighValue();
        if(!this.emissionValue.isRelative()) {
            this.emissionDiff -= this.emission;
        }
        if(!this.lifeValue.independent) {
            this.generateLifeValues();
        }
        if(!this.lifeOffsetValue.independent) {
            this.generateLifeOffsetValues();
        }
        this.spawnWidth = this.spawnWidthValue.newLowValue();
        this.spawnWidthDiff = this.spawnWidthValue.newHighValue();
        if(!this.spawnWidthValue.isRelative()) {
            this.spawnWidthDiff -= this.spawnWidth;
        }
        this.spawnHeight = this.spawnHeightValue.newLowValue();
        this.spawnHeightDiff = this.spawnHeightValue.newHighValue();
        if(!this.spawnHeightValue.isRelative()) {
            this.spawnHeightDiff -= this.spawnHeight;
        }
        this.updateFlags = 0;
        if(this.angleValue.active && this.angleValue.timeline.length > 1) {
            this.updateFlags |= 2;
        }
        if(this.velocityValue.active) {
            this.updateFlags |= 8;
        }
        if(this.xScaleValue.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        if(this.yScaleValue.active && this.yScaleValue.timeline.length > 1) {
            this.updateFlags |= 1;
        }
        if(this.rotationValue.active && this.rotationValue.timeline.length > 1) {
            this.updateFlags |= 4;
        }
        if(this.windValue.active) {
            this.updateFlags |= 16;
        }
        if(this.gravityValue.active) {
            this.updateFlags |= 0x20;
        }
        if(this.tintValue.timeline.length > 1) {
            this.updateFlags |= 0x40;
        }
        if(this.spriteMode == SpriteMode.animated) {
            this.updateFlags |= 0x80;
        }
    }

    public void save(Writer writer0) throws IOException {
        writer0.write(this.name + "\n");
        writer0.write("- Delay -\n");
        this.delayValue.save(writer0);
        writer0.write("- Duration - \n");
        this.durationValue.save(writer0);
        writer0.write("- Count - \n");
        writer0.write("min: " + this.minParticleCount + "\n");
        writer0.write("max: " + this.maxParticleCount + "\n");
        writer0.write("- Emission - \n");
        this.emissionValue.save(writer0);
        writer0.write("- Life - \n");
        this.lifeValue.save(writer0);
        writer0.write("- Life Offset - \n");
        this.lifeOffsetValue.save(writer0);
        writer0.write("- X Offset - \n");
        this.xOffsetValue.save(writer0);
        writer0.write("- Y Offset - \n");
        this.yOffsetValue.save(writer0);
        writer0.write("- Spawn Shape - \n");
        this.spawnShapeValue.save(writer0);
        writer0.write("- Spawn Width - \n");
        this.spawnWidthValue.save(writer0);
        writer0.write("- Spawn Height - \n");
        this.spawnHeightValue.save(writer0);
        writer0.write("- X Scale - \n");
        this.xScaleValue.save(writer0);
        writer0.write("- Y Scale - \n");
        this.yScaleValue.save(writer0);
        writer0.write("- Velocity - \n");
        this.velocityValue.save(writer0);
        writer0.write("- Angle - \n");
        this.angleValue.save(writer0);
        writer0.write("- Rotation - \n");
        this.rotationValue.save(writer0);
        writer0.write("- Wind - \n");
        this.windValue.save(writer0);
        writer0.write("- Gravity - \n");
        this.gravityValue.save(writer0);
        writer0.write("- Tint - \n");
        this.tintValue.save(writer0);
        writer0.write("- Transparency - \n");
        this.transparencyValue.save(writer0);
        writer0.write("- Options - \n");
        writer0.write("attached: " + this.attached + "\n");
        writer0.write("continuous: " + this.continuous + "\n");
        writer0.write("aligned: " + this.aligned + "\n");
        writer0.write("additive: " + this.additive + "\n");
        writer0.write("behind: " + this.behind + "\n");
        writer0.write("premultipliedAlpha: " + this.premultipliedAlpha + "\n");
        writer0.write("spriteMode: " + this.spriteMode.toString() + "\n");
        writer0.write("- Image Paths -\n");
        for(Object object0: this.imagePaths) {
            writer0.write(((String)object0) + "\n");
        }
        writer0.write("\n");
    }

    public void scaleMotion(float f) {
        if(f == 1.0f) {
            return;
        }
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.getMotionValues();
        for(int v = 0; v < arr_particleEmitter$RangedNumericValue.length; ++v) {
            arr_particleEmitter$RangedNumericValue[v].scale(f);
        }
    }

    public void scaleSize(float f) {
        if(f == 1.0f) {
            return;
        }
        this.scaleSize(f, f);
    }

    public void scaleSize(float f, float f1) {
        if(f == 1.0f && f1 == 1.0f) {
            return;
        }
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue = this.getXSizeValues();
        for(int v1 = 0; v1 < arr_particleEmitter$RangedNumericValue.length; ++v1) {
            arr_particleEmitter$RangedNumericValue[v1].scale(f);
        }
        RangedNumericValue[] arr_particleEmitter$RangedNumericValue1 = this.getYSizeValues();
        for(int v = 0; v < arr_particleEmitter$RangedNumericValue1.length; ++v) {
            arr_particleEmitter$RangedNumericValue1[v].scale(f1);
        }
    }

    public void setAdditive(boolean z) {
        this.additive = z;
    }

    public void setAligned(boolean z) {
        this.aligned = z;
    }

    public void setAttached(boolean z) {
        this.attached = z;
    }

    public void setBehind(boolean z) {
        this.behind = z;
    }

    public void setCleansUpBlendFunction(boolean z) {
        this.cleansUpBlendFunction = z;
    }

    public void setContinuous(boolean z) {
        this.continuous = z;
    }

    public void setFlip(boolean z, boolean z1) {
        this.flipX = z;
        this.flipY = z1;
        Particle[] arr_particleEmitter$Particle = this.particles;
        if(arr_particleEmitter$Particle == null) {
            return;
        }
        for(int v = 0; v < arr_particleEmitter$Particle.length; ++v) {
            Particle particleEmitter$Particle0 = this.particles[v];
            if(particleEmitter$Particle0 != null) {
                particleEmitter$Particle0.flip(z, z1);
            }
        }
    }

    public void setImagePaths(Array array0) {
        this.imagePaths = array0;
    }

    public void setMaxParticleCount(int v) {
        this.maxParticleCount = v;
        this.active = new boolean[v];
        this.activeCount = 0;
        this.particles = new Particle[v];
    }

    public void setMinParticleCount(int v) {
        this.minParticleCount = v;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setPosition(float f, float f1) {
        if(this.attached) {
            float f2 = f - this.x;
            float f3 = f1 - this.y;
            boolean[] arr_z = this.active;
            for(int v = 0; v < arr_z.length; ++v) {
                if(arr_z[v]) {
                    this.particles[v].translate(f2, f3);
                }
            }
        }
        this.x = f;
        this.y = f1;
    }

    public void setPremultipliedAlpha(boolean z) {
        this.premultipliedAlpha = z;
    }

    public void setSpriteMode(SpriteMode particleEmitter$SpriteMode0) {
        this.spriteMode = particleEmitter$SpriteMode0;
    }

    public void setSprites(Array array0) {
        this.sprites = array0;
        if(array0.size == 0) {
            return;
        }
        for(int v = 0; v < this.particles.length; ++v) {
            Particle particleEmitter$Particle0 = this.particles[v];
            if(particleEmitter$Particle0 == null) {
                break;
            }
            Sprite sprite0 = null;
            switch(this.spriteMode) {
                case square: {
                    sprite0 = (Sprite)array0.first();
                    break;
                }
                case ellipse: {
                    particleEmitter$Particle0.frame = Math.min(((int)((1.0f - ((float)particleEmitter$Particle0.currentLife) / ((float)particleEmitter$Particle0.life)) * ((float)array0.size))), array0.size - 1);
                    sprite0 = (Sprite)array0.get(particleEmitter$Particle0.frame);
                    break;
                }
                case line: {
                    sprite0 = (Sprite)array0.random();
                }
            }
            particleEmitter$Particle0.setRegion(sprite0);
            particleEmitter$Particle0.setOrigin(sprite0.getOriginX(), sprite0.getOriginY());
        }
    }

    public void start() {
        this.firstUpdate = true;
        this.allowCompletion = false;
        this.restart();
    }

    public void update(float f) {
        boolean z;
        this.accumulator += f * 1000.0f;
        float f1 = this.accumulator;
        if(f1 < 1.0f) {
            return;
        }
        this.accumulator = f1 - ((float)(((int)f1)));
        float f2 = this.delayTimer;
        if(f2 < this.delay) {
            this.delayTimer = f2 + ((float)(((int)f1)));
        }
        else {
            if(this.firstUpdate) {
                this.firstUpdate = false;
                this.addParticle();
            }
            float f3 = this.durationTimer;
            if(f3 < this.duration) {
                this.durationTimer = f3 + ((float)(((int)f1)));
                z = false;
            }
            else if(!this.continuous || this.allowCompletion) {
                z = true;
            }
            else {
                this.restart();
                z = false;
            }
            if(!z) {
                this.emissionDelta += (int)f1;
                float f4 = ((float)this.emission) + ((float)this.emissionDiff) * this.emissionValue.getScale(this.durationTimer / this.duration);
                if(f4 > 0.0f) {
                    int v = this.emissionDelta;
                    if(((float)v) >= 1000.0f / f4) {
                        int v1 = Math.min(((int)(((float)v) / (1000.0f / f4))), this.maxParticleCount - this.activeCount);
                        this.emissionDelta = (int)(((float)this.emissionDelta) - ((float)v1) * (1000.0f / f4));
                        this.emissionDelta = (int)(((float)this.emissionDelta) % (1000.0f / f4));
                        this.addParticles(v1);
                    }
                }
                int v2 = this.activeCount;
                int v3 = this.minParticleCount;
                if(v2 < v3) {
                    this.addParticles(v3 - v2);
                }
            }
        }
        boolean[] arr_z = this.active;
        Particle[] arr_particleEmitter$Particle = this.particles;
        int v4 = this.activeCount;
        for(int v5 = 0; v5 < arr_z.length; ++v5) {
            if(arr_z[v5] && !this.updateParticle(arr_particleEmitter$Particle[v5], f, ((int)f1))) {
                arr_z[v5] = false;
                --v4;
            }
        }
        this.activeCount = v4;
    }

    private boolean updateParticle(Particle particleEmitter$Particle0, float f, int v) {
        float f6;
        float f5;
        int v1 = particleEmitter$Particle0.currentLife - v;
        if(v1 <= 0) {
            return false;
        }
        particleEmitter$Particle0.currentLife = v1;
        float f1 = 1.0f;
        float f2 = 1.0f - ((float)particleEmitter$Particle0.currentLife) / ((float)particleEmitter$Particle0.life);
        int v2 = this.updateFlags;
        if((v2 & 1) != 0) {
            if(this.yScaleValue.active) {
                particleEmitter$Particle0.setScale(particleEmitter$Particle0.xScale + particleEmitter$Particle0.xScaleDiff * this.xScaleValue.getScale(f2), particleEmitter$Particle0.yScale + particleEmitter$Particle0.yScaleDiff * this.yScaleValue.getScale(f2));
            }
            else {
                particleEmitter$Particle0.setScale(particleEmitter$Particle0.xScale + particleEmitter$Particle0.xScaleDiff * this.xScaleValue.getScale(f2));
            }
        }
        if((v2 & 8) != 0) {
            float f3 = (particleEmitter$Particle0.velocity + particleEmitter$Particle0.velocityDiff * this.velocityValue.getScale(f2)) * f;
            if((v2 & 2) == 0) {
                f5 = f3 * particleEmitter$Particle0.angleCos;
                f6 = f3 * particleEmitter$Particle0.angleSin;
                if(this.aligned || (v2 & 4) != 0) {
                    float f8 = particleEmitter$Particle0.rotation + particleEmitter$Particle0.rotationDiff * this.rotationValue.getScale(f2);
                    if(this.aligned) {
                        f8 += particleEmitter$Particle0.angle;
                    }
                    particleEmitter$Particle0.setRotation(f8);
                }
            }
            else {
                float f4 = particleEmitter$Particle0.angle + particleEmitter$Particle0.angleDiff * this.angleValue.getScale(f2);
                f5 = MathUtils.cosDeg(f4) * f3;
                f6 = f3 * MathUtils.sinDeg(f4);
                if((v2 & 4) != 0) {
                    float f7 = particleEmitter$Particle0.rotation + particleEmitter$Particle0.rotationDiff * this.rotationValue.getScale(f2);
                    if(this.aligned) {
                        f7 += f4;
                    }
                    particleEmitter$Particle0.setRotation(f7);
                }
            }
            if((v2 & 16) != 0) {
                f5 += (particleEmitter$Particle0.wind + particleEmitter$Particle0.windDiff * this.windValue.getScale(f2)) * f;
            }
            if((v2 & 0x20) != 0) {
                f6 += (particleEmitter$Particle0.gravity + particleEmitter$Particle0.gravityDiff * this.gravityValue.getScale(f2)) * f;
            }
            particleEmitter$Particle0.translate(f5, f6);
        }
        else if((v2 & 4) != 0) {
            particleEmitter$Particle0.setRotation(particleEmitter$Particle0.rotation + particleEmitter$Particle0.rotationDiff * this.rotationValue.getScale(f2));
        }
        float[] arr_f = (v2 & 0x40) == 0 ? particleEmitter$Particle0.tint : this.tintValue.getColor(f2);
        if(this.premultipliedAlpha) {
            if(this.additive) {
                f1 = 0.0f;
            }
            float f9 = particleEmitter$Particle0.transparency + particleEmitter$Particle0.transparencyDiff * this.transparencyValue.getScale(f2);
            particleEmitter$Particle0.setColor(arr_f[0] * f9, arr_f[1] * f9, arr_f[2] * f9, f9 * f1);
        }
        else {
            particleEmitter$Particle0.setColor(arr_f[0], arr_f[1], arr_f[2], particleEmitter$Particle0.transparency + particleEmitter$Particle0.transparencyDiff * this.transparencyValue.getScale(f2));
        }
        if((v2 & 0x80) != 0) {
            int v3 = Math.min(((int)(f2 * ((float)this.sprites.size))), this.sprites.size - 1);
            if(particleEmitter$Particle0.frame != v3) {
                Sprite sprite0 = (Sprite)this.sprites.get(v3);
                float f10 = particleEmitter$Particle0.getWidth();
                float f11 = particleEmitter$Particle0.getHeight();
                particleEmitter$Particle0.setRegion(sprite0);
                particleEmitter$Particle0.setSize(sprite0.getWidth(), sprite0.getHeight());
                particleEmitter$Particle0.setOrigin(sprite0.getOriginX(), sprite0.getOriginY());
                particleEmitter$Particle0.translate((f10 - sprite0.getWidth()) / 2.0f, (f11 - sprite0.getHeight()) / 2.0f);
                particleEmitter$Particle0.frame = v3;
            }
        }
        return true;
    }
}

