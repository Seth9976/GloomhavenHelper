package com.badlogic.gdx.scenes.scene2d.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SerializationException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Method;
import com.badlogic.gdx.utils.reflect.ReflectionException;

public class Skin implements Disposable {
   ObjectMap resources = new ObjectMap();
   TextureAtlas atlas;
   float scale = 1.0F;
   private final ObjectMap jsonClassTags;
   private static final Class[] defaultTagClasses = new Class[]{
      BitmapFont.class,
      Color.class,
      Skin.TintedDrawable.class,
      NinePatchDrawable.class,
      SpriteDrawable.class,
      TextureRegionDrawable.class,
      TiledDrawable.class,
      Button.ButtonStyle.class,
      CheckBox.CheckBoxStyle.class,
      ImageButton.ImageButtonStyle.class,
      ImageTextButton.ImageTextButtonStyle.class,
      Label.LabelStyle.class,
      List.ListStyle.class,
      ProgressBar.ProgressBarStyle.class,
      ScrollPane.ScrollPaneStyle.class,
      SelectBox.SelectBoxStyle.class,
      Slider.SliderStyle.class,
      SplitPane.SplitPaneStyle.class,
      TextButton.TextButtonStyle.class,
      TextField.TextFieldStyle.class,
      TextTooltip.TextTooltipStyle.class,
      Touchpad.TouchpadStyle.class,
      Tree.TreeStyle.class,
      Window.WindowStyle.class
   };

   public Skin() {
      this.jsonClassTags = new ObjectMap(defaultTagClasses.length);

      for (Class c : defaultTagClasses) {
         this.jsonClassTags.put(c.getSimpleName(), c);
      }
   }

   public Skin(FileHandle skinFile) {
      this.jsonClassTags = new ObjectMap(defaultTagClasses.length);

      for (Class c : defaultTagClasses) {
         this.jsonClassTags.put(c.getSimpleName(), c);
      }

      FileHandle atlasFile = skinFile.sibling(skinFile.nameWithoutExtension() + ".atlas");
      if (atlasFile.exists()) {
         this.atlas = new TextureAtlas(atlasFile);
         this.addRegions(this.atlas);
      }

      this.load(skinFile);
   }

   public Skin(FileHandle skinFile, TextureAtlas atlas) {
      this.jsonClassTags = new ObjectMap(defaultTagClasses.length);

      for (Class c : defaultTagClasses) {
         this.jsonClassTags.put(c.getSimpleName(), c);
      }

      this.atlas = atlas;
      this.addRegions(atlas);
      this.load(skinFile);
   }

   public Skin(TextureAtlas atlas) {
      this.jsonClassTags = new ObjectMap(defaultTagClasses.length);

      for (Class c : defaultTagClasses) {
         this.jsonClassTags.put(c.getSimpleName(), c);
      }

      this.atlas = atlas;
      this.addRegions(atlas);
   }

   public void load(FileHandle skinFile) {
      try {
         this.getJsonLoader(skinFile).fromJson(Skin.class, skinFile);
      } catch (SerializationException var3) {
         throw new SerializationException("Error reading file: " + skinFile, var3);
      }
   }

   public void addRegions(TextureAtlas atlas) {
      Array regions = atlas.getRegions();
      int i = 0;

      for (int n = regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)regions.get(i);
         String name = region.name;
         if (region.index != -1) {
            name = name + "_" + region.index;
         }

         this.add(name, region, TextureRegion.class);
      }
   }

   public void add(String name, Object resource) {
      this.add(name, resource, resource.getClass());
   }

   public void add(String name, Object resource, Class type) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else if (resource == null) {
         throw new IllegalArgumentException("resource cannot be null.");
      } else {
         ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(type);
         if (typeResources == null) {
            typeResources = new ObjectMap(type != TextureRegion.class && type != Drawable.class && type != Sprite.class ? 64 : 256);
            this.resources.put(type, typeResources);
         }

         typeResources.put(name, resource);
      }
   }

   public void remove(String name, Class type) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else {
         ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(type);
         typeResources.remove(name);
      }
   }

   public Object get(Class type) {
      return this.get("default", type);
   }

   public Object get(String name, Class type) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else if (type == Drawable.class) {
         return this.getDrawable(name);
      } else if (type == TextureRegion.class) {
         return this.getRegion(name);
      } else if (type == NinePatch.class) {
         return this.getPatch(name);
      } else if (type == Sprite.class) {
         return this.getSprite(name);
      } else {
         ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(type);
         if (typeResources == null) {
            throw new GdxRuntimeException("No " + type.getName() + " registered with name: " + name);
         } else {
            Object resource = typeResources.get(name);
            if (resource == null) {
               throw new GdxRuntimeException("No " + type.getName() + " registered with name: " + name);
            } else {
               return resource;
            }
         }
      }
   }

   @Null
   public Object optional(String name, Class type) {
      if (name == null) {
         throw new IllegalArgumentException("name cannot be null.");
      } else if (type == null) {
         throw new IllegalArgumentException("type cannot be null.");
      } else {
         ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(type);
         return typeResources == null ? null : typeResources.get(name);
      }
   }

   public boolean has(String name, Class type) {
      ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(type);
      return typeResources == null ? false : typeResources.containsKey(name);
   }

   @Null
   public ObjectMap getAll(Class type) {
      return (ObjectMap)this.resources.get(type);
   }

   public Color getColor(String name) {
      return (Color)this.get(name, Color.class);
   }

   public BitmapFont getFont(String name) {
      return (BitmapFont)this.get(name, BitmapFont.class);
   }

   public TextureRegion getRegion(String name) {
      TextureRegion region = (TextureRegion)this.optional(name, TextureRegion.class);
      if (region != null) {
         return region;
      } else {
         Texture texture = (Texture)this.optional(name, Texture.class);
         if (texture == null) {
            throw new GdxRuntimeException("No TextureRegion or Texture registered with name: " + name);
         } else {
            region = new TextureRegion(texture);
            this.add(name, region, TextureRegion.class);
            return region;
         }
      }
   }

   @Null
   public Array getRegions(String regionName) {
      Array regions = null;
      int i = 0;
      TextureRegion region = (TextureRegion)this.optional(regionName + "_" + i++, TextureRegion.class);
      if (region != null) {
         for (regions = new Array(); region != null; region = (TextureRegion)this.optional(regionName + "_" + i++, TextureRegion.class)) {
            regions.add(region);
         }
      }

      return regions;
   }

   public TiledDrawable getTiledDrawable(String name) {
      TiledDrawable tiled = (TiledDrawable)this.optional(name, TiledDrawable.class);
      if (tiled != null) {
         return tiled;
      } else {
         tiled = new TiledDrawable(this.getRegion(name));
         tiled.setName(name);
         if (this.scale != 1.0F) {
            this.scale(tiled);
            tiled.setScale(this.scale);
         }

         this.add(name, tiled, TiledDrawable.class);
         return tiled;
      }
   }

   public NinePatch getPatch(String name) {
      NinePatch patch = (NinePatch)this.optional(name, NinePatch.class);
      if (patch != null) {
         return patch;
      } else {
         try {
            TextureRegion region = this.getRegion(name);
            if (region instanceof TextureAtlas.AtlasRegion) {
               int[] splits = ((TextureAtlas.AtlasRegion)region).findValue("split");
               if (splits != null) {
                  patch = new NinePatch(region, splits[0], splits[1], splits[2], splits[3]);
                  int[] pads = ((TextureAtlas.AtlasRegion)region).findValue("pad");
                  if (pads != null) {
                     patch.setPadding(pads[0], pads[1], pads[2], pads[3]);
                  }
               }
            }

            if (patch == null) {
               patch = new NinePatch(region);
            }

            if (this.scale != 1.0F) {
               patch.scale(this.scale, this.scale);
            }

            this.add(name, patch, NinePatch.class);
            return patch;
         } catch (GdxRuntimeException var6) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + name);
         }
      }
   }

   public Sprite getSprite(String name) {
      Sprite sprite = (Sprite)this.optional(name, Sprite.class);
      if (sprite != null) {
         return sprite;
      } else {
         try {
            TextureRegion textureRegion = this.getRegion(name);
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
               TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)textureRegion;
               if (region.rotate || region.packedWidth != region.originalWidth || region.packedHeight != region.originalHeight) {
                  sprite = new TextureAtlas.AtlasSprite(region);
               }
            }

            if (sprite == null) {
               sprite = new Sprite(textureRegion);
            }

            if (this.scale != 1.0F) {
               sprite.setSize(sprite.getWidth() * this.scale, sprite.getHeight() * this.scale);
            }

            this.add(name, sprite, Sprite.class);
            return sprite;
         } catch (GdxRuntimeException var5) {
            throw new GdxRuntimeException("No NinePatch, TextureRegion, or Texture registered with name: " + name);
         }
      }
   }

   public Drawable getDrawable(String name) {
      Drawable drawable = (Drawable)this.optional(name, Drawable.class);
      if (drawable != null) {
         return drawable;
      } else {
         try {
            TextureRegion textureRegion = this.getRegion(name);
            if (textureRegion instanceof TextureAtlas.AtlasRegion) {
               TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)textureRegion;
               if (region.findValue("split") != null) {
                  drawable = new NinePatchDrawable(this.getPatch(name));
               } else if (region.rotate || region.packedWidth != region.originalWidth || region.packedHeight != region.originalHeight) {
                  drawable = new SpriteDrawable(this.getSprite(name));
               }
            }

            if (drawable == null) {
               drawable = new TextureRegionDrawable(textureRegion);
               if (this.scale != 1.0F) {
                  this.scale(drawable);
               }
            }
         } catch (GdxRuntimeException var5) {
         }

         if (drawable == null) {
            NinePatch patch = (NinePatch)this.optional(name, NinePatch.class);
            if (patch != null) {
               drawable = new NinePatchDrawable(patch);
            } else {
               Sprite sprite = (Sprite)this.optional(name, Sprite.class);
               if (sprite == null) {
                  throw new GdxRuntimeException("No Drawable, NinePatch, TextureRegion, Texture, or Sprite registered with name: " + name);
               }

               drawable = new SpriteDrawable(sprite);
            }
         }

         if (drawable instanceof BaseDrawable) {
            ((BaseDrawable)drawable).setName(name);
         }

         this.add(name, drawable, Drawable.class);
         return drawable;
      }
   }

   @Null
   public String find(Object resource) {
      if (resource == null) {
         throw new IllegalArgumentException("style cannot be null.");
      } else {
         ObjectMap typeResources = (ObjectMap<String, Object>)this.resources.get(resource.getClass());
         return typeResources == null ? null : (String)typeResources.findKey(resource, true);
      }
   }

   public Drawable newDrawable(String name) {
      return this.newDrawable(this.getDrawable(name));
   }

   public Drawable newDrawable(String name, float r, float g, float b, float a) {
      return this.newDrawable(this.getDrawable(name), new Color(r, g, b, a));
   }

   public Drawable newDrawable(String name, Color tint) {
      return this.newDrawable(this.getDrawable(name), tint);
   }

   public Drawable newDrawable(Drawable drawable) {
      if (drawable instanceof TiledDrawable) {
         return new TiledDrawable((TiledDrawable)drawable);
      } else if (drawable instanceof TextureRegionDrawable) {
         return new TextureRegionDrawable((TextureRegionDrawable)drawable);
      } else if (drawable instanceof NinePatchDrawable) {
         return new NinePatchDrawable((NinePatchDrawable)drawable);
      } else if (drawable instanceof SpriteDrawable) {
         return new SpriteDrawable((SpriteDrawable)drawable);
      } else {
         throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable.getClass());
      }
   }

   public Drawable newDrawable(Drawable drawable, float r, float g, float b, float a) {
      return this.newDrawable(drawable, new Color(r, g, b, a));
   }

   public Drawable newDrawable(Drawable drawable, Color tint) {
      Drawable newDrawable;
      if (drawable instanceof TextureRegionDrawable) {
         newDrawable = ((TextureRegionDrawable)drawable).tint(tint);
      } else if (drawable instanceof NinePatchDrawable) {
         newDrawable = ((NinePatchDrawable)drawable).tint(tint);
      } else {
         if (!(drawable instanceof SpriteDrawable)) {
            throw new GdxRuntimeException("Unable to copy, unknown drawable type: " + drawable.getClass());
         }

         newDrawable = ((SpriteDrawable)drawable).tint(tint);
      }

      if (newDrawable instanceof BaseDrawable) {
         BaseDrawable named = (BaseDrawable)newDrawable;
         if (drawable instanceof BaseDrawable) {
            named.setName(((BaseDrawable)drawable).getName() + " (" + tint + ")");
         } else {
            named.setName(" (" + tint + ")");
         }
      }

      return newDrawable;
   }

   public void scale(Drawable drawble) {
      drawble.setLeftWidth(drawble.getLeftWidth() * this.scale);
      drawble.setRightWidth(drawble.getRightWidth() * this.scale);
      drawble.setBottomHeight(drawble.getBottomHeight() * this.scale);
      drawble.setTopHeight(drawble.getTopHeight() * this.scale);
      drawble.setMinWidth(drawble.getMinWidth() * this.scale);
      drawble.setMinHeight(drawble.getMinHeight() * this.scale);
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public void setEnabled(Actor actor, boolean enabled) {
      Method method = findMethod(actor.getClass(), "getStyle");
      if (method != null) {
         Object style;
         try {
            style = method.invoke(actor);
         } catch (Exception var8) {
            return;
         }

         String name = this.find(style);
         if (name != null) {
            name = name.replace("-disabled", "") + (enabled ? "" : "-disabled");
            style = this.get(name, style.getClass());
            method = findMethod(actor.getClass(), "setStyle");
            if (method != null) {
               try {
                  method.invoke(actor, style);
               } catch (Exception var7) {
               }
            }
         }
      }
   }

   @Null
   public TextureAtlas getAtlas() {
      return this.atlas;
   }

   @Override
   public void dispose() {
      if (this.atlas != null) {
         this.atlas.dispose();
      }

      for (ObjectMap entry : this.resources.values()) {
         for (Object resource : entry.values()) {
            if (resource instanceof Disposable) {
               ((Disposable)resource).dispose();
            }
         }
      }
   }

   protected Json getJsonLoader(final FileHandle skinFile) {
      final Skin skin = this;
      Json json = new Json() {
         private static final String parentFieldName = "parent";

         @Override
         public Object readValue(Class type, Class elementType, JsonValue jsonData) {
            return jsonData != null && jsonData.isString() && !ClassReflection.isAssignableFrom(CharSequence.class, type)
               ? Skin.this.get(jsonData.asString(), type)
               : super.readValue(type, elementType, jsonData);
         }

         @Override
         protected boolean ignoreUnknownField(Class type, String fieldName) {
            return fieldName.equals("parent");
         }

         @Override
         public void readFields(Object object, JsonValue jsonMap) {
            if (jsonMap.has("parent")) {
               String parentName = (String)this.readValue("parent", String.class, jsonMap);
               Class parentType = object.getClass();

               while (true) {
                  try {
                     this.copyFields(Skin.this.get(parentName, parentType), object);
                     break;
                  } catch (GdxRuntimeException var7) {
                     parentType = parentType.getSuperclass();
                     if (parentType == Object.class) {
                        SerializationException se = new SerializationException("Unable to find parent resource with name: " + parentName);
                        se.addTrace(jsonMap.child.trace());
                        throw se;
                     }
                  }
               }
            }

            super.readFields(object, jsonMap);
         }
      };
      json.setTypeName(null);
      json.setUsePrototypes(false);
      json.setSerializer(Skin.class, new Json.ReadOnlySerializer() {
         public Skin read(Json json, JsonValue typeToValueMap, Class ignored) {
            for (JsonValue valueMap = typeToValueMap.child; valueMap != null; valueMap = valueMap.next) {
               try {
                  Class type = json.getClass(valueMap.name());
                  if (type == null) {
                     type = ClassReflection.forName(valueMap.name());
                  }

                  this.readNamedObjects(json, type, valueMap);
               } catch (ReflectionException var6) {
                  throw new SerializationException(var6);
               }
            }

            return skin;
         }

         private void readNamedObjects(Json json, Class type, JsonValue valueMap) {
            Class addType = type == Skin.TintedDrawable.class ? Drawable.class : type;

            for (JsonValue valueEntry = valueMap.child; valueEntry != null; valueEntry = valueEntry.next) {
               Object object = json.readValue(type, valueEntry);
               if (object != null) {
                  try {
                     Skin.this.add(valueEntry.name, object, addType);
                     if (addType != Drawable.class && ClassReflection.isAssignableFrom(Drawable.class, addType)) {
                        Skin.this.add(valueEntry.name, object, Drawable.class);
                     }
                  } catch (Exception var8) {
                     throw new SerializationException("Error reading " + ClassReflection.getSimpleName(type) + ": " + valueEntry.name, var8);
                  }
               }
            }
         }
      });
      json.setSerializer(BitmapFont.class, new Json.ReadOnlySerializer() {
         public BitmapFont read(Json json, JsonValue jsonData, Class type) {
            String path = (String)json.readValue("file", String.class, jsonData);
            int scaledSize = (Integer)json.readValue("scaledSize", int.class, -1, jsonData);
            Boolean flip = (Boolean)json.readValue("flip", Boolean.class, false, jsonData);
            Boolean markupEnabled = (Boolean)json.readValue("markupEnabled", Boolean.class, false, jsonData);
            FileHandle fontFile = skinFile.parent().child(path);
            if (!fontFile.exists()) {
               fontFile = Gdx.files.internal(path);
            }

            if (!fontFile.exists()) {
               throw new SerializationException("Font file not found: " + fontFile);
            } else {
               String regionName = fontFile.nameWithoutExtension();

               try {
                  Array regions = skin.getRegions(regionName);
                  BitmapFont font;
                  if (regions != null) {
                     font = new BitmapFont(new BitmapFont.BitmapFontData(fontFile, flip), regions, true);
                  } else {
                     TextureRegion region = (TextureRegion)skin.optional(regionName, TextureRegion.class);
                     if (region != null) {
                        font = new BitmapFont(fontFile, region, flip);
                     } else {
                        FileHandle imageFile = fontFile.parent().child(regionName + ".png");
                        if (imageFile.exists()) {
                           font = new BitmapFont(fontFile, imageFile, flip);
                        } else {
                           font = new BitmapFont(fontFile, flip);
                        }
                     }
                  }

                  font.getData().markupEnabled = markupEnabled;
                  if (scaledSize != -1) {
                     font.getData().setScale(scaledSize / font.getCapHeight());
                  }

                  return font;
               } catch (RuntimeException var14) {
                  throw new SerializationException("Error loading bitmap font: " + fontFile, var14);
               }
            }
         }
      });
      json.setSerializer(Color.class, new Json.ReadOnlySerializer() {
         public Color read(Json json, JsonValue jsonData, Class type) {
            if (jsonData.isString()) {
               return (Color)Skin.this.get(jsonData.asString(), Color.class);
            } else {
               String hex = (String)json.readValue("hex", String.class, (String)null, jsonData);
               if (hex != null) {
                  return Color.valueOf(hex);
               } else {
                  float r = (Float)json.readValue("r", float.class, 0.0F, jsonData);
                  float g = (Float)json.readValue("g", float.class, 0.0F, jsonData);
                  float b = (Float)json.readValue("b", float.class, 0.0F, jsonData);
                  float a = (Float)json.readValue("a", float.class, 1.0F, jsonData);
                  return new Color(r, g, b, a);
               }
            }
         }
      });
      json.setSerializer(Skin.TintedDrawable.class, new Json.ReadOnlySerializer() {
         @Override
         public Object read(Json json, JsonValue jsonData, Class type) {
            String name = (String)json.readValue("name", String.class, jsonData);
            Color color = (Color)json.readValue("color", Color.class, jsonData);
            if (color == null) {
               throw new SerializationException("TintedDrawable missing color: " + jsonData);
            } else {
               Drawable drawable = Skin.this.newDrawable(name, color);
               if (drawable instanceof BaseDrawable) {
                  BaseDrawable named = (BaseDrawable)drawable;
                  named.setName(jsonData.name + " (" + name + ", " + color + ")");
               }

               return drawable;
            }
         }
      });

      for (ObjectMap.Entry entry : this.jsonClassTags) {
         json.addClassTag((String)entry.key, (Class)entry.value);
      }

      return json;
   }

   public ObjectMap getJsonClassTags() {
      return this.jsonClassTags;
   }

   @Null
   private static Method findMethod(Class type, String name) {
      Method[] methods = ClassReflection.getMethods(type);
      int i = 0;

      for (int n = methods.length; i < n; i++) {
         Method method = methods[i];
         if (method.getName().equals(name)) {
            return method;
         }
      }

      return null;
   }

   public static class TintedDrawable {
      public String name;
      public Color color;
   }
}
