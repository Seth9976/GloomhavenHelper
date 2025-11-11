package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json.ReadOnlySerializer;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap.Entry;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class Skin implements Disposable {
    public static class TintedDrawable {
        public Color color;
        public String name;

    }

    TextureAtlas atlas;
    private static final Class[] defaultTagClasses;
    private final ObjectMap jsonClassTags;
    ObjectMap resources;
    float scale;

    static {
        Skin.defaultTagClasses = new Class[]{BitmapFont.class, Color.class, TintedDrawable.class, NinePatchDrawable.class, SpriteDrawable.class, TextureRegionDrawable.class, TiledDrawable.class, ButtonStyle.class, CheckBoxStyle.class, ImageButtonStyle.class, ImageTextButtonStyle.class, LabelStyle.class, ListStyle.class, ProgressBarStyle.class, ScrollPaneStyle.class, SelectBoxStyle.class, SliderStyle.class, SplitPaneStyle.class, TextButtonStyle.class, TextFieldStyle.class, TextTooltipStyle.class, TouchpadStyle.class, TreeStyle.class, WindowStyle.class};
    }

    public Skin() {
        this.resources = new ObjectMap();
        this.scale = 1.0f;
        this.jsonClassTags = new ObjectMap(Skin.defaultTagClasses.length);
        Class[] arr_class = Skin.defaultTagClasses;
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            this.jsonClassTags.put(class0.getSimpleName(), class0);
        }
    }

    public Skin(FileHandle fileHandle0) {
        this.resources = new ObjectMap();
        this.scale = 1.0f;
        this.jsonClassTags = new ObjectMap(Skin.defaultTagClasses.length);
        Class[] arr_class = Skin.defaultTagClasses;
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            this.jsonClassTags.put(class0.getSimpleName(), class0);
        }
        FileHandle fileHandle1 = fileHandle0.sibling(fileHandle0.nameWithoutExtension() + ".atlas");
        if(fileHandle1.exists()) {
            this.atlas = new TextureAtlas(fileHandle1);
            this.addRegions(this.atlas);
        }
        this.load(fileHandle0);
    }

    public Skin(FileHandle fileHandle0, TextureAtlas textureAtlas0) {
        this.resources = new ObjectMap();
        this.scale = 1.0f;
        this.jsonClassTags = new ObjectMap(Skin.defaultTagClasses.length);
        Class[] arr_class = Skin.defaultTagClasses;
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            this.jsonClassTags.put(class0.getSimpleName(), class0);
        }
        this.atlas = textureAtlas0;
        this.addRegions(textureAtlas0);
        this.load(fileHandle0);
    }

    public Skin(TextureAtlas textureAtlas0) {
        this.resources = new ObjectMap();
        this.scale = 1.0f;
        this.jsonClassTags = new ObjectMap(Skin.defaultTagClasses.length);
        Class[] arr_class = Skin.defaultTagClasses;
        for(int v = 0; v < arr_class.length; ++v) {
            Class class0 = arr_class[v];
            this.jsonClassTags.put(class0.getSimpleName(), class0);
        }
        this.atlas = textureAtlas0;
        this.addRegions(textureAtlas0);
    }

    public void add(String s, Object object0) {
        this.add(s, object0, object0.getClass());
    }

    public void add(String s, Object object0, Class class0) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if(object0 == null) {
            throw new IllegalArgumentException("resource cannot be null.");
        }
        ObjectMap objectMap0 = (ObjectMap)this.resources.get(class0);
        if(objectMap0 == null) {
            objectMap0 = new ObjectMap((class0 == TextureRegion.class || class0 == Drawable.class || class0 == Sprite.class ? 0x100 : 0x40));
            this.resources.put(class0, objectMap0);
        }
        objectMap0.put(s, object0);
    }

    public void addRegions(TextureAtlas textureAtlas0) {
        Array array0 = textureAtlas0.getRegions();
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)array0.get(v1);
            this.add((textureAtlas$AtlasRegion0.index == -1 ? textureAtlas$AtlasRegion0.name : textureAtlas$AtlasRegion0.name + "_" + textureAtlas$AtlasRegion0.index), textureAtlas$AtlasRegion0, TextureRegion.class);
        }
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        TextureAtlas textureAtlas0 = this.atlas;
        if(textureAtlas0 != null) {
            textureAtlas0.dispose();
        }
        for(Object object0: this.resources.values()) {
            for(Object object1: ((ObjectMap)object0).values()) {
                if(object1 instanceof Disposable) {
                    ((Disposable)object1).dispose();
                }
            }
        }
    }

    @Null
    public String find(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("style cannot be null.");
        }
        ObjectMap objectMap0 = (ObjectMap)this.resources.get(object0.getClass());
        return objectMap0 == null ? null : ((String)objectMap0.findKey(object0, true));
    }

    @Null
    private static Method findMethod(Class class0, String s) {
        Method[] arr_method = ClassReflection.getMethods(class0);
        for(int v = 0; v < arr_method.length; ++v) {
            Method method0 = arr_method[v];
            if(method0.getName().equals(s)) {
                return method0;
            }
        }
        return null;
    }

    public Object get(Class class0) {
        return this.get("default", class0);
    }

    public Object get(String s, Class class0) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if(class0 == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        if(class0 == Drawable.class) {
            return this.getDrawable(s);
        }
        if(class0 == TextureRegion.class) {
            return this.getRegion(s);
        }
        if(class0 == NinePatch.class) {
            return this.getPatch(s);
        }
        if(class0 == Sprite.class) {
            return this.getSprite(s);
        }
        ObjectMap objectMap0 = (ObjectMap)this.resources.get(class0);
        if(objectMap0 == null) {
            throw new GdxRuntimeException("No " + class0.getName() + " registered with name: " + s);
        }
        Object object0 = objectMap0.get(s);
        if(object0 == null) {
            throw new GdxRuntimeException("No " + class0.getName() + " registered with name: " + s);
        }
        return object0;
    }

    @Null
    public ObjectMap getAll(Class class0) {
        return (ObjectMap)this.resources.get(class0);
    }

    @Null
    public TextureAtlas getAtlas() {
        return this.atlas;
    }

    public Color getColor(String s) {
        return (Color)this.get(s, Color.class);
    }

    public Drawable getDrawable(String s) {
        Drawable drawable1;
        Drawable drawable0 = (Drawable)this.optional(s, Drawable.class);
        if(drawable0 != null) {
            return drawable0;
        }
        try {
            TextureRegion textureRegion0 = this.getRegion(s);
            if(textureRegion0 instanceof AtlasRegion) {
                if(((AtlasRegion)textureRegion0).findValue("split") != null) {
                    drawable0 = new NinePatchDrawable(this.getPatch(s));
                }
                else if(((AtlasRegion)textureRegion0).rotate || ((AtlasRegion)textureRegion0).packedWidth != ((AtlasRegion)textureRegion0).originalWidth || ((AtlasRegion)textureRegion0).packedHeight != ((AtlasRegion)textureRegion0).originalHeight) {
                    drawable0 = new SpriteDrawable(this.getSprite(s));
                }
            }
            if(drawable0 == null) {
                TextureRegionDrawable textureRegionDrawable0 = new TextureRegionDrawable(textureRegion0);
                try {
                    if(this.scale != 1.0f) {
                        this.scale(textureRegionDrawable0);
                    }
                }
                catch(GdxRuntimeException unused_ex) {
                }
                drawable0 = textureRegionDrawable0;
            }
        }
        catch(GdxRuntimeException unused_ex) {
        }
        if(drawable0 == null) {
            NinePatch ninePatch0 = (NinePatch)this.optional(s, NinePatch.class);
            if(ninePatch0 == null) {
                Sprite sprite0 = (Sprite)this.optional(s, Sprite.class);
                if(sprite0 == null) {
                    throw new GdxRuntimeException("No Drawable, NinePatch, TextureRegion, Texture, or Sprite registered with name: " + s);
                }
                drawable1 = new SpriteDrawable(sprite0);
            }
            else {
                drawable1 = new NinePatchDrawable(ninePatch0);
            }
        }
        else {
            drawable1 = drawable0;
        }
        if(drawable1 instanceof BaseDrawable) {
            ((BaseDrawable)drawable1).setName(s);
        }
        this.add(s, drawable1, Drawable.class);
        return drawable1;
    }

    public BitmapFont getFont(String s) {
        return (BitmapFont)this.get(s, BitmapFont.class);
    }

    public ObjectMap getJsonClassTags() {
        return this.jsonClassTags;
    }

    protected Json getJsonLoader(FileHandle fileHandle0) {
        Json json0 = new Json() {
            private static final String parentFieldName = "parent";

            @Override  // com.badlogic.gdx.utils.Json
            protected boolean ignoreUnknownField(Class class0, String s) {
                return s.equals("parent");
            }

            @Override  // com.badlogic.gdx.utils.Json
            public void readFields(Object object0, JsonValue jsonValue0) {
                if(jsonValue0.has("parent")) {
                    String s = (String)this.readValue("parent", String.class, jsonValue0);
                    Class class0 = object0.getClass();
                    while(true) {
                        try {
                            this.copyFields(Skin.this.get(s, class0), object0);
                            break;
                        }
                        catch(GdxRuntimeException unused_ex) {
                            class0 = class0.getSuperclass();
                            if(class0 == Object.class) {
                                SerializationException serializationException0 = new SerializationException("Unable to find parent resource with name: " + s);
                                serializationException0.addTrace(jsonValue0.child.trace());
                                throw serializationException0;
                            }
                        }
                    }
                }
                super.readFields(object0, jsonValue0);
            }

            @Override  // com.badlogic.gdx.utils.Json
            public Object readValue(Class class0, Class class1, JsonValue jsonValue0) {
                if(jsonValue0 != null && jsonValue0.isString() && !ClassReflection.isAssignableFrom(CharSequence.class, class0)) {
                    String s = jsonValue0.asString();
                    return Skin.this.get(s, class0);
                }
                return super.readValue(class0, class1, jsonValue0);
            }
        };
        json0.setTypeName(null);
        json0.setUsePrototypes(false);
        com.badlogic.gdx.scenes.scene2d.ui.Skin.2 skin$20 = new ReadOnlySerializer() {
            public Skin read(Json json0, JsonValue jsonValue0, Class class0) {
                for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                    try {
                        Class class1 = json0.getClass(jsonValue1.name());
                        if(class1 == null) {
                            class1 = ClassReflection.forName(jsonValue1.name());
                        }
                        this.readNamedObjects(json0, class1, jsonValue1);
                    }
                    catch(ReflectionException reflectionException0) {
                        throw new SerializationException(reflectionException0);
                    }
                }
                return this;
            }

            @Override  // com.badlogic.gdx.utils.Json$ReadOnlySerializer
            public Object read(Json json0, JsonValue jsonValue0, Class class0) {
                return this.read(json0, jsonValue0, class0);
            }

            private void readNamedObjects(Json json0, Class class0, JsonValue jsonValue0) {
                Class class1 = class0 == TintedDrawable.class ? Drawable.class : class0;
                JsonValue jsonValue1 = jsonValue0.child;
                while(jsonValue1 != null) {
                    Object object0 = json0.readValue(class0, jsonValue1);
                    if(object0 == null) {
                        jsonValue1 = jsonValue1.next;
                        continue;
                    }
                    else {
                        try {
                            Skin.this.add(jsonValue1.name, object0, class1);
                            if(class1 != Drawable.class && ClassReflection.isAssignableFrom(Drawable.class, class1)) {
                                Skin.this.add(jsonValue1.name, object0, Drawable.class);
                            }
                            jsonValue1 = jsonValue1.next;
                            continue;
                        }
                        catch(Exception exception0) {
                        }
                    }
                    throw new SerializationException("Error reading " + ClassReflection.getSimpleName(class0) + ": " + jsonValue1.name, exception0);
                }
            }
        };
        json0.setSerializer(Skin.class, skin$20);
        com.badlogic.gdx.scenes.scene2d.ui.Skin.3 skin$30 = new ReadOnlySerializer() {
            public BitmapFont read(Json json0, JsonValue jsonValue0, Class class0) {
                BitmapFont bitmapFont0;
                String s = (String)json0.readValue("file", String.class, jsonValue0);
                int v = (int)(((Integer)json0.readValue("scaledSize", Integer.TYPE, -1, jsonValue0)));
                Boolean boolean0 = (Boolean)json0.readValue("flip", Boolean.class, Boolean.FALSE, jsonValue0);
                Boolean boolean1 = (Boolean)json0.readValue("markupEnabled", Boolean.class, Boolean.FALSE, jsonValue0);
                FileHandle fileHandle0 = fileHandle0.parent().child(s);
                if(!fileHandle0.exists()) {
                    fileHandle0 = Gdx.files.internal(s);
                }
                if(fileHandle0.exists()) {
                    String s1 = fileHandle0.nameWithoutExtension();
                    try {
                        Array array0 = this.getRegions(s1);
                        if(array0 == null) {
                            TextureRegion textureRegion0 = (TextureRegion)this.optional(s1, TextureRegion.class);
                            if(textureRegion0 == null) {
                                FileHandle fileHandle1 = fileHandle0.parent().child(s1 + ".png");
                                bitmapFont0 = fileHandle1.exists() ? new BitmapFont(fileHandle0, fileHandle1, boolean0.booleanValue()) : new BitmapFont(fileHandle0, boolean0.booleanValue());
                            }
                            else {
                                bitmapFont0 = new BitmapFont(fileHandle0, textureRegion0, boolean0.booleanValue());
                            }
                        }
                        else {
                            bitmapFont0 = new BitmapFont(new BitmapFontData(fileHandle0, boolean0.booleanValue()), array0, true);
                        }
                        bitmapFont0.getData().markupEnabled = boolean1.booleanValue();
                        if(v != -1) {
                            bitmapFont0.getData().setScale(((float)v) / bitmapFont0.getCapHeight());
                        }
                        return bitmapFont0;
                    }
                    catch(RuntimeException runtimeException0) {
                        throw new SerializationException("Error loading bitmap font: " + fileHandle0, runtimeException0);
                    }
                }
                throw new SerializationException("Font file not found: " + fileHandle0);
            }

            @Override  // com.badlogic.gdx.utils.Json$ReadOnlySerializer
            public Object read(Json json0, JsonValue jsonValue0, Class class0) {
                return this.read(json0, jsonValue0, class0);
            }
        };
        json0.setSerializer(BitmapFont.class, skin$30);
        com.badlogic.gdx.scenes.scene2d.ui.Skin.4 skin$40 = new ReadOnlySerializer() {
            public Color read(Json json0, JsonValue jsonValue0, Class class0) {
                if(jsonValue0.isString()) {
                    String s = jsonValue0.asString();
                    return (Color)Skin.this.get(s, Color.class);
                }
                String s1 = (String)json0.readValue("hex", String.class, null, jsonValue0);
                return s1 == null ? new Color(((float)(((Float)json0.readValue("r", Float.TYPE, 0.0f, jsonValue0)))), ((float)(((Float)json0.readValue("g", Float.TYPE, 0.0f, jsonValue0)))), ((float)(((Float)json0.readValue("b", Float.TYPE, 0.0f, jsonValue0)))), ((float)(((Float)json0.readValue("a", Float.TYPE, 1.0f, jsonValue0))))) : Color.valueOf(s1);
            }

            @Override  // com.badlogic.gdx.utils.Json$ReadOnlySerializer
            public Object read(Json json0, JsonValue jsonValue0, Class class0) {
                return this.read(json0, jsonValue0, class0);
            }
        };
        json0.setSerializer(Color.class, skin$40);
        com.badlogic.gdx.scenes.scene2d.ui.Skin.5 skin$50 = new ReadOnlySerializer() {
            @Override  // com.badlogic.gdx.utils.Json$ReadOnlySerializer
            public Object read(Json json0, JsonValue jsonValue0, Class class0) {
                String s = (String)json0.readValue("name", String.class, jsonValue0);
                Color color0 = (Color)json0.readValue("color", Color.class, jsonValue0);
                if(color0 == null) {
                    throw new SerializationException("TintedDrawable missing color: " + jsonValue0);
                }
                Drawable drawable0 = Skin.this.newDrawable(s, color0);
                if(drawable0 instanceof BaseDrawable) {
                    ((BaseDrawable)drawable0).setName(jsonValue0.name + " (" + s + ", " + color0 + ")");
                }
                return drawable0;
            }
        };
        json0.setSerializer(TintedDrawable.class, skin$50);
        for(Object object0: this.jsonClassTags) {
            json0.addClassTag(((String)((Entry)object0).key), ((Class)((Entry)object0).value));
        }
        return json0;
    }

    public NinePatch getPatch(String s) {
        NinePatch ninePatch0 = (NinePatch)this.optional(s, NinePatch.class);
        if(ninePatch0 != null) {
            return ninePatch0;
        }
        try {
            TextureRegion textureRegion0 = this.getRegion(s);
            if(textureRegion0 instanceof AtlasRegion) {
                int[] arr_v = ((AtlasRegion)textureRegion0).findValue("split");
                if(arr_v != null) {
                    ninePatch0 = new NinePatch(textureRegion0, arr_v[0], arr_v[1], arr_v[2], arr_v[3]);
                    int[] arr_v1 = ((AtlasRegion)textureRegion0).findValue("pad");
                    if(arr_v1 != null) {
                        ninePatch0.setPadding(((float)arr_v1[0]), ((float)arr_v1[1]), ((float)arr_v1[2]), ((float)arr_v1[3]));
                    }
                }
            }
            if(ninePatch0 == null) {
                ninePatch0 = new NinePatch(textureRegion0);
            }
            if(this.scale != 1.0f) {
                ninePatch0.scale(this.scale, this.scale);
            }
            this.add(s, ninePatch0, NinePatch.class);
            return ninePatch0;
        }
        catch(GdxRuntimeException unused_ex) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + s);
        }
    }

    public TextureRegion getRegion(String s) {
        TextureRegion textureRegion0 = (TextureRegion)this.optional(s, TextureRegion.class);
        if(textureRegion0 != null) {
            return textureRegion0;
        }
        Texture texture0 = (Texture)this.optional(s, Texture.class);
        if(texture0 == null) {
            throw new GdxRuntimeException("No TextureRegion or Texture registered with name: " + s);
        }
        TextureRegion textureRegion1 = new TextureRegion(texture0);
        this.add(s, textureRegion1, TextureRegion.class);
        return textureRegion1;
    }

    @Null
    public Array getRegions(String s) {
        TextureRegion textureRegion0 = (TextureRegion)this.optional(s + "_" + 0, TextureRegion.class);
        if(textureRegion0 != null) {
            Array array0 = new Array();
            for(int v = 1; textureRegion0 != null; ++v) {
                array0.add(textureRegion0);
                textureRegion0 = (TextureRegion)this.optional(s + "_" + v, TextureRegion.class);
            }
            return array0;
        }
        return null;
    }

    public Sprite getSprite(String s) {
        Sprite sprite0 = (Sprite)this.optional(s, Sprite.class);
        if(sprite0 != null) {
            return sprite0;
        }
        try {
            TextureRegion textureRegion0 = this.getRegion(s);
            if(textureRegion0 instanceof AtlasRegion && (((AtlasRegion)textureRegion0).rotate || ((AtlasRegion)textureRegion0).packedWidth != ((AtlasRegion)textureRegion0).originalWidth || ((AtlasRegion)textureRegion0).packedHeight != ((AtlasRegion)textureRegion0).originalHeight)) {
                sprite0 = new AtlasSprite(((AtlasRegion)textureRegion0));
            }
            if(sprite0 == null) {
                sprite0 = new Sprite(textureRegion0);
            }
            if(this.scale != 1.0f) {
                sprite0.setSize(sprite0.getWidth() * this.scale, sprite0.getHeight() * this.scale);
            }
            this.add(s, sprite0, Sprite.class);
            return sprite0;
        }
        catch(GdxRuntimeException unused_ex) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + s);
        }
    }

    public TiledDrawable getTiledDrawable(String s) {
        TiledDrawable tiledDrawable0 = (TiledDrawable)this.optional(s, TiledDrawable.class);
        if(tiledDrawable0 != null) {
            return tiledDrawable0;
        }
        TiledDrawable tiledDrawable1 = new TiledDrawable(this.getRegion(s));
        tiledDrawable1.setName(s);
        if(this.scale != 1.0f) {
            this.scale(tiledDrawable1);
            tiledDrawable1.setScale(this.scale);
        }
        this.add(s, tiledDrawable1, TiledDrawable.class);
        return tiledDrawable1;
    }

    public boolean has(String s, Class class0) {
        ObjectMap objectMap0 = (ObjectMap)this.resources.get(class0);
        return objectMap0 == null ? false : objectMap0.containsKey(s);
    }

    public void load(FileHandle fileHandle0) {
        try {
            this.getJsonLoader(fileHandle0).fromJson(Skin.class, fileHandle0);
        }
        catch(SerializationException serializationException0) {
            throw new SerializationException("Error reading file: " + fileHandle0, serializationException0);
        }
    }

    public Drawable newDrawable(Drawable drawable0) {
        if(drawable0 instanceof TiledDrawable) {
            return new TiledDrawable(((TiledDrawable)drawable0));
        }
        if(drawable0 instanceof TextureRegionDrawable) {
            return new TextureRegionDrawable(((TextureRegionDrawable)drawable0));
        }
        if(drawable0 instanceof NinePatchDrawable) {
            return new NinePatchDrawable(((NinePatchDrawable)drawable0));
        }
        if(!(drawable0 instanceof SpriteDrawable)) {
            throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable0.getClass());
        }
        return new SpriteDrawable(((SpriteDrawable)drawable0));
    }

    public Drawable newDrawable(Drawable drawable0, float f, float f1, float f2, float f3) {
        return this.newDrawable(drawable0, new Color(f, f1, f2, f3));
    }

    public Drawable newDrawable(Drawable drawable0, Color color0) {
        Drawable drawable1;
        if(drawable0 instanceof TextureRegionDrawable) {
            drawable1 = ((TextureRegionDrawable)drawable0).tint(color0);
        }
        else if(drawable0 instanceof NinePatchDrawable) {
            drawable1 = ((NinePatchDrawable)drawable0).tint(color0);
        }
        else if(drawable0 instanceof SpriteDrawable) {
            drawable1 = ((SpriteDrawable)drawable0).tint(color0);
        }
        else {
            throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable0.getClass());
        }
        if(drawable1 instanceof BaseDrawable) {
            if(drawable0 instanceof BaseDrawable) {
                ((BaseDrawable)drawable1).setName(((BaseDrawable)drawable0).getName() + " (" + color0 + ")");
                return drawable1;
            }
            ((BaseDrawable)drawable1).setName(" (" + color0 + ")");
        }
        return drawable1;
    }

    public Drawable newDrawable(String s) {
        return this.newDrawable(this.getDrawable(s));
    }

    public Drawable newDrawable(String s, float f, float f1, float f2, float f3) {
        return this.newDrawable(this.getDrawable(s), new Color(f, f1, f2, f3));
    }

    public Drawable newDrawable(String s, Color color0) {
        return this.newDrawable(this.getDrawable(s), color0);
    }

    @Null
    public Object optional(String s, Class class0) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        if(class0 == null) {
            throw new IllegalArgumentException("type cannot be null.");
        }
        ObjectMap objectMap0 = (ObjectMap)this.resources.get(class0);
        return objectMap0 == null ? null : objectMap0.get(s);
    }

    public void remove(String s, Class class0) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        ((ObjectMap)this.resources.get(class0)).remove(s);
    }

    public void scale(Drawable drawable0) {
        drawable0.setLeftWidth(drawable0.getLeftWidth() * this.scale);
        drawable0.setRightWidth(drawable0.getRightWidth() * this.scale);
        drawable0.setBottomHeight(drawable0.getBottomHeight() * this.scale);
        drawable0.setTopHeight(drawable0.getTopHeight() * this.scale);
        drawable0.setMinWidth(drawable0.getMinWidth() * this.scale);
        drawable0.setMinHeight(drawable0.getMinHeight() * this.scale);
    }

    public void setEnabled(Actor actor0, boolean z) {
        Object object0;
        Method method0 = Skin.findMethod(actor0.getClass(), "getStyle");
        if(method0 == null) {
            return;
        }
        try {
            object0 = method0.invoke(actor0, new Object[0]);
        }
        catch(Exception unused_ex) {
            return;
        }
        String s = this.find(object0);
        if(s == null) {
            return;
        }
        Object object1 = this.get(s.replace("-disabled", "") + (z ? "" : "-disabled"), object0.getClass());
        Method method1 = Skin.findMethod(actor0.getClass(), "setStyle");
        if(method1 == null) {
            return;
        }
        try {
            method1.invoke(actor0, new Object[]{object1});
        }
        catch(Exception unused_ex) {
        }
    }

    public void setScale(float f) {
        this.scale = f;
    }
}

