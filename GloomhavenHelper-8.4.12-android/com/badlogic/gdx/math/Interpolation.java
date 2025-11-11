package com.badlogic.gdx.math;

public abstract class Interpolation {
    public static class Bounce extends BounceOut {
        public Bounce(int v) {
            super(v);
        }

        public Bounce(float[] arr_f, float[] arr_f1) {
            super(arr_f, arr_f1);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$BounceOut
        public float apply(float f) {
            return f <= 0.5f ? (1.0f - this.out(1.0f - f * 2.0f)) / 2.0f : this.out(f * 2.0f - 1.0f) / 2.0f + 0.5f;
        }

        private float out(float f) {
            float f1 = this.widths[0] / 2.0f + f;
            return f1 < this.widths[0] ? f1 / (this.widths[0] / 2.0f) - 1.0f : super.apply(f);
        }
    }

    public static class BounceIn extends BounceOut {
        public BounceIn(int v) {
            super(v);
        }

        public BounceIn(float[] arr_f, float[] arr_f1) {
            super(arr_f, arr_f1);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$BounceOut
        public float apply(float f) {
            return 1.0f - super.apply(1.0f - f);
        }
    }

    public static class BounceOut extends Interpolation {
        final float[] heights;
        final float[] widths;

        public BounceOut(int v) {
            if(v < 2 || v > 5) {
                throw new IllegalArgumentException("bounces cannot be < 2 or > 5: " + v);
            }
            this.widths = new float[v];
            this.heights = new float[v];
            float[] arr_f = this.heights;
            arr_f[0] = 1.0f;
            switch(v) {
                case 2: {
                    this.widths[0] = 0.6f;
                    this.widths[1] = 0.4f;
                    arr_f[1] = 0.33f;
                    break;
                }
                case 3: {
                    this.widths[0] = 0.4f;
                    this.widths[1] = 0.4f;
                    this.widths[2] = 0.2f;
                    arr_f[1] = 0.33f;
                    arr_f[2] = 0.1f;
                    break;
                }
                case 4: {
                    this.widths[0] = 0.34f;
                    this.widths[1] = 0.34f;
                    this.widths[2] = 0.2f;
                    this.widths[3] = 0.15f;
                    arr_f[1] = 0.26f;
                    arr_f[2] = 0.11f;
                    arr_f[3] = 0.03f;
                    break;
                }
                case 5: {
                    this.widths[0] = 0.3f;
                    this.widths[1] = 0.3f;
                    this.widths[2] = 0.2f;
                    this.widths[3] = 0.1f;
                    this.widths[4] = 0.1f;
                    arr_f[1] = 0.45f;
                    arr_f[2] = 0.3f;
                    arr_f[3] = 0.15f;
                    arr_f[4] = 0.06f;
                }
            }
            this.widths[0] *= 2.0f;
        }

        public BounceOut(float[] arr_f, float[] arr_f1) {
            if(arr_f.length != arr_f1.length) {
                throw new IllegalArgumentException("Must be the same number of widths and heights.");
            }
            this.widths = arr_f;
            this.heights = arr_f1;
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if(f == 1.0f) {
                return 1.0f;
            }
            float f1 = f + this.widths[0] / 2.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            for(int v = 0; v < this.widths.length; ++v) {
                f3 = this.widths[v];
                if(f1 <= f3) {
                    f2 = this.heights[v];
                    break;
                }
                f1 -= f3;
            }
            float f4 = f1 / f3;
            float f5 = 4.0f / f3 * f2 * f4;
            return 1.0f - (f5 - f4 * f5) * f3;
        }
    }

    public static class Elastic extends Interpolation {
        final float bounces;
        final float power;
        final float scale;
        final float value;

        public Elastic(float f, float f1, int v, float f2) {
            this.value = f;
            this.power = f1;
            this.scale = f2;
            this.bounces = ((float)v) * 3.141593f * ((float)(v % 2 == 0 ? 1 : -1));
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if(f <= 0.5f) {
                float f1 = MathUtils.sin(f * 2.0f * this.bounces);
                return ((float)Math.pow(this.value, this.power * (f * 2.0f - 1.0f))) * f1 * this.scale / 2.0f;
            }
            float f2 = (1.0f - f) * 2.0f;
            float f3 = MathUtils.sin(f2 * this.bounces);
            return 1.0f - ((float)Math.pow(this.value, this.power * (f2 - 1.0f))) * f3 * this.scale / 2.0f;
        }
    }

    public static class ElasticIn extends Elastic {
        public ElasticIn(float f, float f1, int v, float f2) {
            super(f, f1, v, f2);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Elastic
        public float apply(float f) {
            return ((double)f) >= 0.99 ? 1.0f : ((float)Math.pow(this.value, this.power * (f - 1.0f))) * MathUtils.sin(f * this.bounces) * this.scale;
        }
    }

    public static class ElasticOut extends Elastic {
        public ElasticOut(float f, float f1, int v, float f2) {
            super(f, f1, v, f2);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Elastic
        public float apply(float f) {
            return f == 0.0f ? 0.0f : 1.0f - ((float)Math.pow(this.value, this.power * (1.0f - f - 1.0f))) * MathUtils.sin((1.0f - f) * this.bounces) * this.scale;
        }
    }

    public static class Exp extends Interpolation {
        final float min;
        final float power;
        final float scale;
        final float value;

        public Exp(float f, float f1) {
            this.value = f;
            this.power = f1;
            this.min = (float)Math.pow(f, -f1);
            this.scale = 1.0f / (1.0f - this.min);
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f <= 0.5f ? (((float)Math.pow(this.value, this.power * (f * 2.0f - 1.0f))) - this.min) * this.scale / 2.0f : (2.0f - (((float)Math.pow(this.value, -this.power * (f * 2.0f - 1.0f))) - this.min) * this.scale) / 2.0f;
        }
    }

    public static class ExpIn extends Exp {
        public ExpIn(float f, float f1) {
            super(f, f1);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Exp
        public float apply(float f) {
            return (((float)Math.pow(this.value, this.power * (f - 1.0f))) - this.min) * this.scale;
        }
    }

    public static class ExpOut extends Exp {
        public ExpOut(float f, float f1) {
            super(f, f1);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Exp
        public float apply(float f) {
            return 1.0f - (((float)Math.pow(this.value, -this.power * f)) - this.min) * this.scale;
        }
    }

    public static class Pow extends Interpolation {
        final int power;

        public Pow(int v) {
            this.power = v;
        }

        // 去混淆评级： 低(20)
        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f <= 0.5f ? ((float)Math.pow(f * 2.0f, this.power)) / 2.0f : ((float)Math.pow((f - 1.0f) * 2.0f, this.power)) / ((float)(this.power % 2 == 0 ? -2 : 2)) + 1.0f;
        }
    }

    public static class PowIn extends Pow {
        public PowIn(int v) {
            super(v);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Pow
        public float apply(float f) {
            return (float)Math.pow(f, this.power);
        }
    }

    public static class PowOut extends Pow {
        public PowOut(int v) {
            super(v);
        }

        @Override  // com.badlogic.gdx.math.Interpolation$Pow
        public float apply(float f) {
            float f1 = (float)Math.pow(f - 1.0f, this.power);
            return this.power % 2 == 0 ? f1 * -1.0f + 1.0f : f1 * 1.0f + 1.0f;
        }
    }

    public static class Swing extends Interpolation {
        private final float scale;

        public Swing(float f) {
            this.scale = f * 2.0f;
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            if(f <= 0.5f) {
                return f * 2.0f * (f * 2.0f) * ((this.scale + 1.0f) * (f * 2.0f) - this.scale) / 2.0f;
            }
            float f1 = (f - 1.0f) * 2.0f;
            return f1 * f1 * ((this.scale + 1.0f) * f1 + this.scale) / 2.0f + 1.0f;
        }
    }

    public static class SwingIn extends Interpolation {
        private final float scale;

        public SwingIn(float f) {
            this.scale = f;
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return f * f * ((this.scale + 1.0f) * f - this.scale);
        }
    }

    public static class SwingOut extends Interpolation {
        private final float scale;

        public SwingOut(float f) {
            this.scale = f;
        }

        @Override  // com.badlogic.gdx.math.Interpolation
        public float apply(float f) {
            return (f - 1.0f) * (f - 1.0f) * ((this.scale + 1.0f) * (f - 1.0f) + this.scale) + 1.0f;
        }
    }

    public static final Bounce bounce;
    public static final BounceIn bounceIn;
    public static final BounceOut bounceOut;
    public static final Interpolation circle;
    public static final Interpolation circleIn;
    public static final Interpolation circleOut;
    public static final Elastic elastic;
    public static final ElasticIn elasticIn;
    public static final ElasticOut elasticOut;
    public static final Exp exp10;
    public static final ExpIn exp10In;
    public static final ExpOut exp10Out;
    public static final Exp exp5;
    public static final ExpIn exp5In;
    public static final ExpOut exp5Out;
    public static final Interpolation fade;
    public static final PowOut fastSlow;
    public static final Interpolation linear;
    public static final Pow pow2;
    public static final PowIn pow2In;
    public static final Interpolation pow2InInverse;
    public static final PowOut pow2Out;
    public static final Interpolation pow2OutInverse;
    public static final Pow pow3;
    public static final PowIn pow3In;
    public static final Interpolation pow3InInverse;
    public static final PowOut pow3Out;
    public static final Interpolation pow3OutInverse;
    public static final Pow pow4;
    public static final PowIn pow4In;
    public static final PowOut pow4Out;
    public static final Pow pow5;
    public static final PowIn pow5In;
    public static final PowOut pow5Out;
    public static final Interpolation sine;
    public static final Interpolation sineIn;
    public static final Interpolation sineOut;
    public static final PowIn slowFast;
    public static final Interpolation smooth;
    public static final Interpolation smooth2;
    public static final Interpolation smoother;
    public static final Swing swing;
    public static final SwingIn swingIn;
    public static final SwingOut swingOut;

    static {
        Interpolation.linear = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return f;
            }
        };
        Interpolation.smooth = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return f * f * (3.0f - f * 2.0f);
            }
        };
        Interpolation.smooth2 = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                float f1 = f * f * (3.0f - f * 2.0f);
                return f1 * f1 * (3.0f - f1 * 2.0f);
            }
        };
        Interpolation.smoother = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return f * f * f * (f * (6.0f * f - 15.0f) + 10.0f);
            }
        };
        Interpolation.fade = Interpolation.smoother;
        Interpolation.pow2 = new Pow(2);
        Interpolation.pow2In = new PowIn(2);
        Interpolation.slowFast = Interpolation.pow2In;
        Interpolation.pow2Out = new PowOut(2);
        Interpolation.fastSlow = Interpolation.pow2Out;
        Interpolation.pow2InInverse = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return f < 0.000001f ? 0.0f : ((float)Math.sqrt(f));
            }
        };
        Interpolation.pow2OutInverse = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                if(f < 0.000001f) {
                    return 0.0f;
                }
                return f > 1.0f ? 1.0f : 1.0f - ((float)Math.sqrt(-(f - 1.0f)));
            }
        };
        Interpolation.pow3 = new Pow(3);
        Interpolation.pow3In = new PowIn(3);
        Interpolation.pow3Out = new PowOut(3);
        Interpolation.pow3InInverse = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return (float)Math.cbrt(f);
            }
        };
        Interpolation.pow3OutInverse = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - ((float)Math.cbrt(-(f - 1.0f)));
            }
        };
        Interpolation.pow4 = new Pow(4);
        Interpolation.pow4In = new PowIn(4);
        Interpolation.pow4Out = new PowOut(4);
        Interpolation.pow5 = new Pow(5);
        Interpolation.pow5In = new PowIn(5);
        Interpolation.pow5Out = new PowOut(5);
        Interpolation.sine = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return (1.0f - MathUtils.cos(f * 3.141593f)) / 2.0f;
            }
        };
        Interpolation.sineIn = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - MathUtils.cos(f * 1.570796f);
            }
        };
        Interpolation.sineOut = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return MathUtils.sin(f * 1.570796f);
            }
        };
        Interpolation.exp10 = new Exp(2.0f, 10.0f);
        Interpolation.exp10In = new ExpIn(2.0f, 10.0f);
        Interpolation.exp10Out = new ExpOut(2.0f, 10.0f);
        Interpolation.exp5 = new Exp(2.0f, 5.0f);
        Interpolation.exp5In = new ExpIn(2.0f, 5.0f);
        Interpolation.exp5Out = new ExpOut(2.0f, 5.0f);
        Interpolation.circle = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                if(f <= 0.5f) {
                    return (1.0f - ((float)Math.sqrt(1.0f - f * 2.0f * (f * 2.0f)))) / 2.0f;
                }
                float f1 = (f - 1.0f) * 2.0f;
                return (((float)Math.sqrt(1.0f - f1 * f1)) + 1.0f) / 2.0f;
            }
        };
        Interpolation.circleIn = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return 1.0f - ((float)Math.sqrt(1.0f - f * f));
            }
        };
        Interpolation.circleOut = new Interpolation() {
            @Override  // com.badlogic.gdx.math.Interpolation
            public float apply(float f) {
                return (float)Math.sqrt(1.0f - (f - 1.0f) * (f - 1.0f));
            }
        };
        Interpolation.elastic = new Elastic(2.0f, 10.0f, 7, 1.0f);
        Interpolation.elasticIn = new ElasticIn(2.0f, 10.0f, 6, 1.0f);
        Interpolation.elasticOut = new ElasticOut(2.0f, 10.0f, 7, 1.0f);
        Interpolation.swing = new Swing(1.5f);
        Interpolation.swingIn = new SwingIn(2.0f);
        Interpolation.swingOut = new SwingOut(2.0f);
        Interpolation.bounce = new Bounce(4);
        Interpolation.bounceIn = new BounceIn(4);
        Interpolation.bounceOut = new BounceOut(4);
    }

    public abstract float apply(float arg1);

    public float apply(float f, float f1, float f2) {
        return f + (f1 - f) * this.apply(f2);
    }
}

