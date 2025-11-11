package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Null;

public abstract class Value {
    public static class Fixed extends Value {
        static final Fixed[] cache;
        private final float value;

        static {
            Fixed.cache = new Fixed[0x6F];
        }

        public Fixed(float f) {
            this.value = f;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
        public float get(@Null Actor actor0) {
            return this.value;
        }

        @Override
        public String toString() {
            return Float.toString(this.value);
        }

        public static Fixed valueOf(float f) {
            if(f == 0.0f) {
                return Fixed.zero;
            }
            if(f >= -10.0f && f <= 100.0f && f == ((float)(((int)f)))) {
                Fixed[] arr_value$Fixed = Fixed.cache;
                Fixed value$Fixed0 = arr_value$Fixed[((int)f) + 10];
                if(value$Fixed0 == null) {
                    value$Fixed0 = new Fixed(f);
                    arr_value$Fixed[((int)f) + 10] = value$Fixed0;
                }
                return value$Fixed0;
            }
            return new Fixed(f);
        }
    }

    public static Value maxHeight;
    public static Value maxWidth;
    public static Value minHeight;
    public static Value minWidth;
    public static Value prefHeight;
    public static Value prefWidth;
    public static final Fixed zero;

    static {
        Value.zero = new Fixed(0.0f);
        Value.minWidth = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getMinWidth();
                }
                return actor0 == null ? 0.0f : actor0.getWidth();
            }
        };
        Value.minHeight = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getMinHeight();
                }
                return actor0 == null ? 0.0f : actor0.getHeight();
            }
        };
        Value.prefWidth = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getPrefWidth();
                }
                return actor0 == null ? 0.0f : actor0.getWidth();
            }
        };
        Value.prefHeight = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getPrefHeight();
                }
                return actor0 == null ? 0.0f : actor0.getHeight();
            }
        };
        Value.maxWidth = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getMaxWidth();
                }
                return actor0 == null ? 0.0f : actor0.getWidth();
            }
        };
        Value.maxHeight = new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                if(actor0 instanceof Layout) {
                    return ((Layout)actor0).getMaxHeight();
                }
                return actor0 == null ? 0.0f : actor0.getHeight();
            }
        };
    }

    public float get() {
        return this.get(null);
    }

    public abstract float get(@Null Actor arg1);

    public static Value percentHeight(float f) {
        return new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                return actor0.getHeight() * f;
            }
        };
    }

    public static Value percentHeight(float f, Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        return new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                return actor0.getHeight() * f;
            }
        };
    }

    public static Value percentWidth(float f) {
        return new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                return actor0.getWidth() * f;
            }
        };
    }

    public static Value percentWidth(float f, Actor actor0) {
        if(actor0 == null) {
            throw new IllegalArgumentException("actor cannot be null.");
        }
        return new Value() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Value
            public float get(@Null Actor actor0) {
                return actor0.getWidth() * f;
            }
        };
    }
}

