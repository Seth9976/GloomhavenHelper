package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class TextureAtlas implements Disposable {
   private final ObjectSet textures = new ObjectSet(4);
   private final Array regions = new Array();

   public TextureAtlas() {
   }

   public TextureAtlas(String internalPackFile) {
      this(Gdx.files.internal(internalPackFile));
   }

   public TextureAtlas(FileHandle packFile) {
      this(packFile, packFile.parent());
   }

   public TextureAtlas(FileHandle packFile, boolean flip) {
      this(packFile, packFile.parent(), flip);
   }

   public TextureAtlas(FileHandle packFile, FileHandle imagesDir) {
      this(packFile, imagesDir, false);
   }

   public TextureAtlas(FileHandle packFile, FileHandle imagesDir, boolean flip) {
      this(new TextureAtlas.TextureAtlasData(packFile, imagesDir, flip));
   }

   public TextureAtlas(TextureAtlas.TextureAtlasData data) {
      this.load(data);
   }

   public void load(TextureAtlas.TextureAtlasData data) {
      this.textures.ensureCapacity(data.pages.size);

      for (TextureAtlas.TextureAtlasData.Page page : data.pages) {
         if (page.texture == null) {
            page.texture = new Texture(page.textureFile, page.format, page.useMipMaps);
         }

         page.texture.setFilter(page.minFilter, page.magFilter);
         page.texture.setWrap(page.uWrap, page.vWrap);
         this.textures.add(page.texture);
      }

      this.regions.ensureCapacity(data.regions.size);

      for (TextureAtlas.TextureAtlasData.Region region : data.regions) {
         TextureAtlas.AtlasRegion atlasRegion = new TextureAtlas.AtlasRegion(
            region.page.texture, region.left, region.top, region.rotate ? region.height : region.width, region.rotate ? region.width : region.height
         );
         atlasRegion.index = region.index;
         atlasRegion.name = region.name;
         atlasRegion.offsetX = region.offsetX;
         atlasRegion.offsetY = region.offsetY;
         atlasRegion.originalHeight = region.originalHeight;
         atlasRegion.originalWidth = region.originalWidth;
         atlasRegion.rotate = region.rotate;
         atlasRegion.degrees = region.degrees;
         atlasRegion.names = region.names;
         atlasRegion.values = region.values;
         if (region.flip) {
            atlasRegion.flip(false, true);
         }

         this.regions.add(atlasRegion);
      }
   }

   public TextureAtlas.AtlasRegion addRegion(String name, Texture texture, int x, int y, int width, int height) {
      this.textures.add(texture);
      TextureAtlas.AtlasRegion region = new TextureAtlas.AtlasRegion(texture, x, y, width, height);
      region.name = name;
      this.regions.add(region);
      return region;
   }

   public TextureAtlas.AtlasRegion addRegion(String name, TextureRegion textureRegion) {
      this.textures.add(textureRegion.texture);
      TextureAtlas.AtlasRegion region = new TextureAtlas.AtlasRegion(textureRegion);
      region.name = name;
      this.regions.add(region);
      return region;
   }

   public Array getRegions() {
      return this.regions;
   }

   @Null
   public TextureAtlas.AtlasRegion findRegion(String name) {
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         if (((TextureAtlas.AtlasRegion)this.regions.get(i)).name.equals(name)) {
            return (TextureAtlas.AtlasRegion)this.regions.get(i);
         }
      }

      return null;
   }

   @Null
   public TextureAtlas.AtlasRegion findRegion(String name, int index) {
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.regions.get(i);
         if (region.name.equals(name) && region.index == index) {
            return region;
         }
      }

      return null;
   }

   public Array findRegions(String name) {
      Array matched = new Array(TextureAtlas.AtlasRegion.class);
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.regions.get(i);
         if (region.name.equals(name)) {
            matched.add(new TextureAtlas.AtlasRegion(region));
         }
      }

      return matched;
   }

   public Array createSprites() {
      Array sprites = new Array(true, this.regions.size, Sprite.class);
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         sprites.add(this.newSprite((TextureAtlas.AtlasRegion)this.regions.get(i)));
      }

      return sprites;
   }

   @Null
   public Sprite createSprite(String name) {
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         if (((TextureAtlas.AtlasRegion)this.regions.get(i)).name.equals(name)) {
            return this.newSprite((TextureAtlas.AtlasRegion)this.regions.get(i));
         }
      }

      return null;
   }

   @Null
   public Sprite createSprite(String name, int index) {
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.regions.get(i);
         if (region.index == index && region.name.equals(name)) {
            return this.newSprite((TextureAtlas.AtlasRegion)this.regions.get(i));
         }
      }

      return null;
   }

   public Array createSprites(String name) {
      Array matched = new Array(Sprite.class);
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.regions.get(i);
         if (region.name.equals(name)) {
            matched.add(this.newSprite(region));
         }
      }

      return matched;
   }

   private Sprite newSprite(TextureAtlas.AtlasRegion region) {
      if (region.packedWidth != region.originalWidth || region.packedHeight != region.originalHeight) {
         return new TextureAtlas.AtlasSprite(region);
      } else if (region.rotate) {
         Sprite sprite = new Sprite(region);
         sprite.setBounds(0.0F, 0.0F, region.getRegionHeight(), region.getRegionWidth());
         sprite.rotate90(true);
         return sprite;
      } else {
         return new Sprite(region);
      }
   }

   @Null
   public NinePatch createPatch(String name) {
      int i = 0;

      for (int n = this.regions.size; i < n; i++) {
         TextureAtlas.AtlasRegion region = (TextureAtlas.AtlasRegion)this.regions.get(i);
         if (region.name.equals(name)) {
            int[] splits = region.findValue("split");
            if (splits == null) {
               throw new IllegalArgumentException("Region does not have ninepatch splits: " + name);
            }

            NinePatch patch = new NinePatch(region, splits[0], splits[1], splits[2], splits[3]);
            int[] pads = region.findValue("pad");
            if (pads != null) {
               patch.setPadding(pads[0], pads[1], pads[2], pads[3]);
            }

            return patch;
         }
      }

      return null;
   }

   public ObjectSet getTextures() {
      return this.textures;
   }

   @Override
   public void dispose() {
      for (Texture texture : this.textures) {
         texture.dispose();
      }

      this.textures.clear(0);
   }

   public static class AtlasRegion extends TextureRegion {
      public int index = -1;
      public String name;
      public float offsetX;
      public float offsetY;
      public int packedWidth;
      public int packedHeight;
      public int originalWidth;
      public int originalHeight;
      public boolean rotate;
      public int degrees;
      @Null
      public String[] names;
      @Null
      public int[][] values;

      public AtlasRegion(Texture texture, int x, int y, int width, int height) {
         super(texture, x, y, width, height);
         this.originalWidth = width;
         this.originalHeight = height;
         this.packedWidth = width;
         this.packedHeight = height;
      }

      public AtlasRegion(TextureAtlas.AtlasRegion region) {
         this.setRegion(region);
         this.index = region.index;
         this.name = region.name;
         this.offsetX = region.offsetX;
         this.offsetY = region.offsetY;
         this.packedWidth = region.packedWidth;
         this.packedHeight = region.packedHeight;
         this.originalWidth = region.originalWidth;
         this.originalHeight = region.originalHeight;
         this.rotate = region.rotate;
         this.degrees = region.degrees;
         this.names = region.names;
         this.values = region.values;
      }

      public AtlasRegion(TextureRegion region) {
         this.setRegion(region);
         this.packedWidth = region.getRegionWidth();
         this.packedHeight = region.getRegionHeight();
         this.originalWidth = this.packedWidth;
         this.originalHeight = this.packedHeight;
      }

      @Override
      public void flip(boolean x, boolean y) {
         super.flip(x, y);
         if (x) {
            this.offsetX = this.originalWidth - this.offsetX - this.getRotatedPackedWidth();
         }

         if (y) {
            this.offsetY = this.originalHeight - this.offsetY - this.getRotatedPackedHeight();
         }
      }

      public float getRotatedPackedWidth() {
         return this.rotate ? this.packedHeight : this.packedWidth;
      }

      public float getRotatedPackedHeight() {
         return this.rotate ? this.packedWidth : this.packedHeight;
      }

      @Null
      public int[] findValue(String name) {
         if (this.names != null) {
            int i = 0;

            for (int n = this.names.length; i < n; i++) {
               if (name.equals(this.names[i])) {
                  return this.values[i];
               }
            }
         }

         return null;
      }

      @Override
      public String toString() {
         return this.name;
      }
   }

   public static class AtlasSprite extends Sprite {
      final TextureAtlas.AtlasRegion region;
      float originalOffsetX;
      float originalOffsetY;

      public AtlasSprite(TextureAtlas.AtlasRegion region) {
         this.region = new TextureAtlas.AtlasRegion(region);
         this.originalOffsetX = region.offsetX;
         this.originalOffsetY = region.offsetY;
         this.setRegion(region);
         this.setOrigin(region.originalWidth / 2.0F, region.originalHeight / 2.0F);
         int width = region.getRegionWidth();
         int height = region.getRegionHeight();
         if (region.rotate) {
            super.rotate90(true);
            super.setBounds(region.offsetX, region.offsetY, height, width);
         } else {
            super.setBounds(region.offsetX, region.offsetY, width, height);
         }

         this.setColor(1.0F, 1.0F, 1.0F, 1.0F);
      }

      public AtlasSprite(TextureAtlas.AtlasSprite sprite) {
         this.region = sprite.region;
         this.originalOffsetX = sprite.originalOffsetX;
         this.originalOffsetY = sprite.originalOffsetY;
         this.set(sprite);
      }

      @Override
      public void setPosition(float x, float y) {
         super.setPosition(x + this.region.offsetX, y + this.region.offsetY);
      }

      @Override
      public void setX(float x) {
         super.setX(x + this.region.offsetX);
      }

      @Override
      public void setY(float y) {
         super.setY(y + this.region.offsetY);
      }

      @Override
      public void setBounds(float x, float y, float width, float height) {
         float widthRatio = width / this.region.originalWidth;
         float heightRatio = height / this.region.originalHeight;
         this.region.offsetX = this.originalOffsetX * widthRatio;
         this.region.offsetY = this.originalOffsetY * heightRatio;
         int packedWidth = this.region.rotate ? this.region.packedHeight : this.region.packedWidth;
         int packedHeight = this.region.rotate ? this.region.packedWidth : this.region.packedHeight;
         super.setBounds(x + this.region.offsetX, y + this.region.offsetY, packedWidth * widthRatio, packedHeight * heightRatio);
      }

      @Override
      public void setSize(float width, float height) {
         this.setBounds(this.getX(), this.getY(), width, height);
      }

      @Override
      public void setOrigin(float originX, float originY) {
         super.setOrigin(originX - this.region.offsetX, originY - this.region.offsetY);
      }

      @Override
      public void setOriginCenter() {
         super.setOrigin(this.width / 2.0F - this.region.offsetX, this.height / 2.0F - this.region.offsetY);
      }

      @Override
      public void flip(boolean x, boolean y) {
         if (this.region.rotate) {
            super.flip(y, x);
         } else {
            super.flip(x, y);
         }

         float oldOriginX = this.getOriginX();
         float oldOriginY = this.getOriginY();
         float oldOffsetX = this.region.offsetX;
         float oldOffsetY = this.region.offsetY;
         float widthRatio = this.getWidthRatio();
         float heightRatio = this.getHeightRatio();
         this.region.offsetX = this.originalOffsetX;
         this.region.offsetY = this.originalOffsetY;
         this.region.flip(x, y);
         this.originalOffsetX = this.region.offsetX;
         this.originalOffsetY = this.region.offsetY;
         this.region.offsetX *= widthRatio;
         this.region.offsetY *= heightRatio;
         this.translate(this.region.offsetX - oldOffsetX, this.region.offsetY - oldOffsetY);
         this.setOrigin(oldOriginX, oldOriginY);
      }

      @Override
      public void rotate90(boolean clockwise) {
         super.rotate90(clockwise);
         float oldOriginX = this.getOriginX();
         float oldOriginY = this.getOriginY();
         float oldOffsetX = this.region.offsetX;
         float oldOffsetY = this.region.offsetY;
         float widthRatio = this.getWidthRatio();
         float heightRatio = this.getHeightRatio();
         if (clockwise) {
            this.region.offsetX = oldOffsetY;
            this.region.offsetY = this.region.originalHeight * heightRatio - oldOffsetX - this.region.packedWidth * widthRatio;
         } else {
            this.region.offsetX = this.region.originalWidth * widthRatio - oldOffsetY - this.region.packedHeight * heightRatio;
            this.region.offsetY = oldOffsetX;
         }

         this.translate(this.region.offsetX - oldOffsetX, this.region.offsetY - oldOffsetY);
         this.setOrigin(oldOriginX, oldOriginY);
      }

      @Override
      public float getX() {
         return super.getX() - this.region.offsetX;
      }

      @Override
      public float getY() {
         return super.getY() - this.region.offsetY;
      }

      @Override
      public float getOriginX() {
         return super.getOriginX() + this.region.offsetX;
      }

      @Override
      public float getOriginY() {
         return super.getOriginY() + this.region.offsetY;
      }

      @Override
      public float getWidth() {
         return super.getWidth() / this.region.getRotatedPackedWidth() * this.region.originalWidth;
      }

      @Override
      public float getHeight() {
         return super.getHeight() / this.region.getRotatedPackedHeight() * this.region.originalHeight;
      }

      public float getWidthRatio() {
         return super.getWidth() / this.region.getRotatedPackedWidth();
      }

      public float getHeightRatio() {
         return super.getHeight() / this.region.getRotatedPackedHeight();
      }

      public TextureAtlas.AtlasRegion getAtlasRegion() {
         return this.region;
      }

      @Override
      public String toString() {
         return this.region.toString();
      }
   }

   public static class TextureAtlasData {
      final Array pages = new Array();
      final Array regions = new Array();

      public TextureAtlasData() {
      }

      public TextureAtlasData(FileHandle packFile, FileHandle imagesDir, boolean flip) {
         this.load(packFile, imagesDir, flip);
      }

      public void load(FileHandle packFile, FileHandle imagesDir, boolean flip) {
         final String[] entry = new String[5];
         ObjectMap pageFields = new ObjectMap(15, 0.99F);
         pageFields.put("size", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Page page) {
               page.width = Integer.parseInt(entry[1]);
               page.height = Integer.parseInt(entry[2]);
            }
         });
         pageFields.put("format", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Page page) {
               page.format = Pixmap.Format.valueOf(entry[1]);
            }
         });
         pageFields.put("filter", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Page page) {
               page.minFilter = Texture.TextureFilter.valueOf(entry[1]);
               page.magFilter = Texture.TextureFilter.valueOf(entry[2]);
               page.useMipMaps = page.minFilter.isMipMap();
            }
         });
         pageFields.put("repeat", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Page page) {
               if (entry[1].indexOf(120) != -1) {
                  page.uWrap = Texture.TextureWrap.Repeat;
               }

               if (entry[1].indexOf(121) != -1) {
                  page.vWrap = Texture.TextureWrap.Repeat;
               }
            }
         });
         pageFields.put("pma", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Page page) {
               page.pma = entry[1].equals("true");
            }
         });
         final boolean[] hasIndexes = new boolean[]{false};
         ObjectMap regionFields = new ObjectMap(127, 0.99F);
         regionFields.put("xy", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.left = Integer.parseInt(entry[1]);
               region.top = Integer.parseInt(entry[2]);
            }
         });
         regionFields.put("size", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.width = Integer.parseInt(entry[1]);
               region.height = Integer.parseInt(entry[2]);
            }
         });
         regionFields.put("bounds", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.left = Integer.parseInt(entry[1]);
               region.top = Integer.parseInt(entry[2]);
               region.width = Integer.parseInt(entry[3]);
               region.height = Integer.parseInt(entry[4]);
            }
         });
         regionFields.put("offset", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.offsetX = Integer.parseInt(entry[1]);
               region.offsetY = Integer.parseInt(entry[2]);
            }
         });
         regionFields.put("orig", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.originalWidth = Integer.parseInt(entry[1]);
               region.originalHeight = Integer.parseInt(entry[2]);
            }
         });
         regionFields.put("offsets", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.offsetX = Integer.parseInt(entry[1]);
               region.offsetY = Integer.parseInt(entry[2]);
               region.originalWidth = Integer.parseInt(entry[3]);
               region.originalHeight = Integer.parseInt(entry[4]);
            }
         });
         regionFields.put("rotate", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               String value = entry[1];
               if (value.equals("true")) {
                  region.degrees = 90;
               } else if (!value.equals("false")) {
                  region.degrees = Integer.parseInt(value);
               }

               region.rotate = region.degrees == 90;
            }
         });
         regionFields.put("index", new TextureAtlas.TextureAtlasData.Field() {
            public void parse(TextureAtlas.TextureAtlasData.Region region) {
               region.index = Integer.parseInt(entry[1]);
               if (region.index != -1) {
                  hasIndexes[0] = true;
               }
            }
         });
         BufferedReader reader = new BufferedReader(new InputStreamReader(packFile.read()), 1024);

         try {
            String line = reader.readLine();

            while (line != null && line.trim().length() == 0) {
               line = reader.readLine();
            }

            while (line != null && line.trim().length() != 0 && readEntry(entry, line) != 0) {
               line = reader.readLine();
            }

            TextureAtlas.TextureAtlasData.Page page = null;
            Array names = null;
            Array values = null;

            while (line != null) {
               if (line.trim().length() == 0) {
                  page = null;
                  line = reader.readLine();
               } else if (page == null) {
                  page = new TextureAtlas.TextureAtlasData.Page();
                  page.textureFile = imagesDir.child(line);

                  while (readEntry(entry, line = reader.readLine()) != 0) {
                     TextureAtlas.TextureAtlasData.Field field = (TextureAtlas.TextureAtlasData.Field)pageFields.get(entry[0]);
                     if (field != null) {
                        field.parse(page);
                     }
                  }

                  this.pages.add(page);
               } else {
                  TextureAtlas.TextureAtlasData.Region region = new TextureAtlas.TextureAtlasData.Region();
                  region.page = page;
                  region.name = line.trim();
                  if (flip) {
                     region.flip = true;
                  }

                  while (true) {
                     int count = readEntry(entry, line = reader.readLine());
                     if (count == 0) {
                        if (region.originalWidth == 0 && region.originalHeight == 0) {
                           region.originalWidth = region.width;
                           region.originalHeight = region.height;
                        }

                        if (names != null && names.size > 0) {
                           region.names = (String[])names.toArray(String.class);
                           region.values = (int[][])values.toArray(int[].class);
                           names.clear();
                           values.clear();
                        }

                        this.regions.add(region);
                        break;
                     }

                     TextureAtlas.TextureAtlasData.Field field = (TextureAtlas.TextureAtlasData.Field)regionFields.get(entry[0]);
                     if (field != null) {
                        field.parse(region);
                     } else {
                        if (names == null) {
                           names = new Array(8);
                           values = new Array(8);
                        }

                        names.add(entry[0]);
                        int[] entryValues = new int[count];

                        for (int i = 0; i < count; i++) {
                           try {
                              entryValues[i] = Integer.parseInt(entry[i + 1]);
                           } catch (NumberFormatException var23) {
                           }
                        }

                        values.add(entryValues);
                     }
                  }
               }
            }
         } catch (Exception var24) {
            throw new GdxRuntimeException("Error reading texture atlas file: " + packFile, var24);
         } finally {
            StreamUtils.closeQuietly(reader);
         }

         if (hasIndexes[0]) {
            this.regions.sort(new Comparator() {
               public int compare(TextureAtlas.TextureAtlasData.Region region1, TextureAtlas.TextureAtlasData.Region region2) {
                  int i1 = region1.index;
                  if (i1 == -1) {
                     i1 = Integer.MAX_VALUE;
                  }

                  int i2 = region2.index;
                  if (i2 == -1) {
                     i2 = Integer.MAX_VALUE;
                  }

                  return i1 - i2;
               }
            });
         }
      }

      public Array getPages() {
         return this.pages;
      }

      public Array getRegions() {
         return this.regions;
      }

      private static int readEntry(String[] entry, @Null String line) throws IOException {
         if (line == null) {
            return 0;
         } else {
            line = line.trim();
            if (line.length() == 0) {
               return 0;
            } else {
               int colon = line.indexOf(58);
               if (colon == -1) {
                  return 0;
               } else {
                  entry[0] = line.substring(0, colon).trim();
                  int i = 1;
                  int lastMatch = colon + 1;

                  while (true) {
                     int comma = line.indexOf(44, lastMatch);
                     if (comma == -1) {
                        entry[i] = line.substring(lastMatch).trim();
                        return i;
                     }

                     entry[i] = line.substring(lastMatch, comma).trim();
                     lastMatch = comma + 1;
                     if (i == 4) {
                        return 4;
                     }

                     i++;
                  }
               }
            }
         }
      }

      private interface Field {
         void parse(Object var1);
      }

      public static class Page {
         @Null
         public FileHandle textureFile;
         @Null
         public Texture texture;
         public float width;
         public float height;
         public boolean useMipMaps;
         public Pixmap.Format format = Pixmap.Format.RGBA8888;
         public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
         public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
         public Texture.TextureWrap uWrap = Texture.TextureWrap.ClampToEdge;
         public Texture.TextureWrap vWrap = Texture.TextureWrap.ClampToEdge;
         public boolean pma;
      }

      public static class Region {
         public TextureAtlas.TextureAtlasData.Page page;
         public String name;
         public int left;
         public int top;
         public int width;
         public int height;
         public float offsetX;
         public float offsetY;
         public int originalWidth;
         public int originalHeight;
         public int degrees;
         public boolean rotate;
         public int index = -1;
         @Null
         public String[] names;
         @Null
         public int[][] values;
         public boolean flip;

         @Null
         public int[] findValue(String name) {
            if (this.names != null) {
               int i = 0;

               for (int n = this.names.length; i < n; i++) {
                  if (name.equals(this.names[i])) {
                     return this.values[i];
                  }
               }
            }

            return null;
         }
      }
   }
}
