package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
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
    public static class AtlasRegion extends TextureRegion {
        public int degrees;
        public int index;
        public String name;
        @Null
        public String[] names;
        public float offsetX;
        public float offsetY;
        public int originalHeight;
        public int originalWidth;
        public int packedHeight;
        public int packedWidth;
        public boolean rotate;
        @Null
        public int[][] values;

        public AtlasRegion(Texture texture0, int v, int v1, int v2, int v3) {
            super(texture0, v, v1, v2, v3);
            this.index = -1;
            this.originalWidth = v2;
            this.originalHeight = v3;
            this.packedWidth = v2;
            this.packedHeight = v3;
        }

        public AtlasRegion(AtlasRegion textureAtlas$AtlasRegion0) {
            this.index = -1;
            this.setRegion(textureAtlas$AtlasRegion0);
            this.index = textureAtlas$AtlasRegion0.index;
            this.name = textureAtlas$AtlasRegion0.name;
            this.offsetX = textureAtlas$AtlasRegion0.offsetX;
            this.offsetY = textureAtlas$AtlasRegion0.offsetY;
            this.packedWidth = textureAtlas$AtlasRegion0.packedWidth;
            this.packedHeight = textureAtlas$AtlasRegion0.packedHeight;
            this.originalWidth = textureAtlas$AtlasRegion0.originalWidth;
            this.originalHeight = textureAtlas$AtlasRegion0.originalHeight;
            this.rotate = textureAtlas$AtlasRegion0.rotate;
            this.degrees = textureAtlas$AtlasRegion0.degrees;
            this.names = textureAtlas$AtlasRegion0.names;
            this.values = textureAtlas$AtlasRegion0.values;
        }

        public AtlasRegion(TextureRegion textureRegion0) {
            this.index = -1;
            this.setRegion(textureRegion0);
            this.packedWidth = textureRegion0.getRegionWidth();
            this.packedHeight = textureRegion0.getRegionHeight();
            this.originalWidth = this.packedWidth;
            this.originalHeight = this.packedHeight;
        }

        @Null
        public int[] findValue(String s) {
            String[] arr_s = this.names;
            if(arr_s != null) {
                for(int v = 0; v < arr_s.length; ++v) {
                    if(s.equals(this.names[v])) {
                        return this.values[v];
                    }
                }
            }
            return null;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.TextureRegion
        public void flip(boolean z, boolean z1) {
            super.flip(z, z1);
            if(z) {
                this.offsetX = ((float)this.originalWidth) - this.offsetX - this.getRotatedPackedWidth();
            }
            if(z1) {
                this.offsetY = ((float)this.originalHeight) - this.offsetY - this.getRotatedPackedHeight();
            }
        }

        // 去混淆评级： 低(20)
        public float getRotatedPackedHeight() {
            return this.rotate ? ((float)this.packedWidth) : ((float)this.packedHeight);
        }

        // 去混淆评级： 低(20)
        public float getRotatedPackedWidth() {
            return this.rotate ? ((float)this.packedHeight) : ((float)this.packedWidth);
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    public static class AtlasSprite extends Sprite {
        float originalOffsetX;
        float originalOffsetY;
        final AtlasRegion region;

        public AtlasSprite(AtlasRegion textureAtlas$AtlasRegion0) {
            this.region = new AtlasRegion(textureAtlas$AtlasRegion0);
            this.originalOffsetX = textureAtlas$AtlasRegion0.offsetX;
            this.originalOffsetY = textureAtlas$AtlasRegion0.offsetY;
            this.setRegion(textureAtlas$AtlasRegion0);
            this.setOrigin(((float)textureAtlas$AtlasRegion0.originalWidth) / 2.0f, ((float)textureAtlas$AtlasRegion0.originalHeight) / 2.0f);
            int v = textureAtlas$AtlasRegion0.getRegionWidth();
            int v1 = textureAtlas$AtlasRegion0.getRegionHeight();
            if(textureAtlas$AtlasRegion0.rotate) {
                super.rotate90(true);
                super.setBounds(textureAtlas$AtlasRegion0.offsetX, textureAtlas$AtlasRegion0.offsetY, ((float)v1), ((float)v));
            }
            else {
                super.setBounds(textureAtlas$AtlasRegion0.offsetX, textureAtlas$AtlasRegion0.offsetY, ((float)v), ((float)v1));
            }
            this.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public AtlasSprite(AtlasSprite textureAtlas$AtlasSprite0) {
            this.region = textureAtlas$AtlasSprite0.region;
            this.originalOffsetX = textureAtlas$AtlasSprite0.originalOffsetX;
            this.originalOffsetY = textureAtlas$AtlasSprite0.originalOffsetY;
            this.set(textureAtlas$AtlasSprite0);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void flip(boolean z, boolean z1) {
            if(this.region.rotate) {
                super.flip(z1, z);
            }
            else {
                super.flip(z, z1);
            }
            float f = this.getOriginX();
            float f1 = this.getOriginY();
            float f2 = this.region.offsetX;
            float f3 = this.region.offsetY;
            float f4 = this.getWidthRatio();
            float f5 = this.getHeightRatio();
            this.region.offsetX = this.originalOffsetX;
            this.region.offsetY = this.originalOffsetY;
            this.region.flip(z, z1);
            this.originalOffsetX = this.region.offsetX;
            this.originalOffsetY = this.region.offsetY;
            this.region.offsetX *= f4;
            this.region.offsetY *= f5;
            this.translate(this.region.offsetX - f2, this.region.offsetY - f3);
            this.setOrigin(f, f1);
        }

        public AtlasRegion getAtlasRegion() {
            return this.region;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getHeight() {
            return super.getHeight() / this.region.getRotatedPackedHeight() * ((float)this.region.originalHeight);
        }

        public float getHeightRatio() {
            return super.getHeight() / this.region.getRotatedPackedHeight();
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getOriginX() {
            return super.getOriginX() + this.region.offsetX;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getOriginY() {
            return super.getOriginY() + this.region.offsetY;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getWidth() {
            return super.getWidth() / this.region.getRotatedPackedWidth() * ((float)this.region.originalWidth);
        }

        public float getWidthRatio() {
            return super.getWidth() / this.region.getRotatedPackedWidth();
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getX() {
            return super.getX() - this.region.offsetX;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public float getY() {
            return super.getY() - this.region.offsetY;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void rotate90(boolean z) {
            super.rotate90(z);
            float f = this.getOriginX();
            float f1 = this.getOriginY();
            float f2 = this.region.offsetX;
            float f3 = this.region.offsetY;
            float f4 = this.getWidthRatio();
            float f5 = this.getHeightRatio();
            if(z) {
                this.region.offsetX = f3;
                this.region.offsetY = ((float)this.region.originalHeight) * f5 - f2 - ((float)this.region.packedWidth) * f4;
            }
            else {
                this.region.offsetX = ((float)this.region.originalWidth) * f4 - f3 - ((float)this.region.packedHeight) * f5;
                this.region.offsetY = f2;
            }
            this.translate(this.region.offsetX - f2, this.region.offsetY - f3);
            this.setOrigin(f, f1);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setBounds(float f, float f1, float f2, float f3) {
            float f4 = f2 / ((float)this.region.originalWidth);
            float f5 = f3 / ((float)this.region.originalHeight);
            this.region.offsetX = this.originalOffsetX * f4;
            this.region.offsetY = this.originalOffsetY * f5;
            super.setBounds(f + this.region.offsetX, f1 + this.region.offsetY, ((float)(this.region.rotate ? this.region.packedHeight : this.region.packedWidth)) * f4, ((float)(this.region.rotate ? this.region.packedWidth : this.region.packedHeight)) * f5);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setOrigin(float f, float f1) {
            super.setOrigin(f - this.region.offsetX, f1 - this.region.offsetY);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setOriginCenter() {
            super.setOrigin(this.width / 2.0f - this.region.offsetX, this.height / 2.0f - this.region.offsetY);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setPosition(float f, float f1) {
            super.setPosition(f + this.region.offsetX, f1 + this.region.offsetY);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setSize(float f, float f1) {
            this.setBounds(this.getX(), this.getY(), f, f1);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setX(float f) {
            super.setX(f + this.region.offsetX);
        }

        @Override  // com.badlogic.gdx.graphics.g2d.Sprite
        public void setY(float f) {
            super.setY(f + this.region.offsetY);
        }

        @Override
        public String toString() {
            return this.region.toString();
        }
    }

    public static class TextureAtlasData {
        interface Field {
            void parse(Object arg1);
        }

        public static class Page {
            public Format format;
            public float height;
            public TextureFilter magFilter;
            public TextureFilter minFilter;
            public boolean pma;
            @Null
            public Texture texture;
            @Null
            public FileHandle textureFile;
            public TextureWrap uWrap;
            public boolean useMipMaps;
            public TextureWrap vWrap;
            public float width;

            public Page() {
                this.format = Format.RGBA8888;
                this.minFilter = TextureFilter.Nearest;
                this.magFilter = TextureFilter.Nearest;
                this.uWrap = TextureWrap.ClampToEdge;
                this.vWrap = TextureWrap.ClampToEdge;
            }
        }

        public static class Region {
            public int degrees;
            public boolean flip;
            public int height;
            public int index;
            public int left;
            public String name;
            @Null
            public String[] names;
            public float offsetX;
            public float offsetY;
            public int originalHeight;
            public int originalWidth;
            public Page page;
            public boolean rotate;
            public int top;
            @Null
            public int[][] values;
            public int width;

            public Region() {
                this.index = -1;
            }

            @Null
            public int[] findValue(String s) {
                String[] arr_s = this.names;
                if(arr_s != null) {
                    for(int v = 0; v < arr_s.length; ++v) {
                        if(s.equals(this.names[v])) {
                            return this.values[v];
                        }
                    }
                }
                return null;
            }
        }

        final Array pages;
        final Array regions;

        public TextureAtlasData() {
            this.pages = new Array();
            this.regions = new Array();
        }

        public TextureAtlasData(FileHandle fileHandle0, FileHandle fileHandle1, boolean z) {
            this.pages = new Array();
            this.regions = new Array();
            this.load(fileHandle0, fileHandle1, z);
        }

        public Array getPages() {
            return this.pages;
        }

        public Array getRegions() {
            return this.regions;
        }

        public void load(FileHandle fileHandle0, FileHandle fileHandle1, boolean z) {
            int[] arr_v;
            Array array1;
            Array array0;
            Page textureAtlas$TextureAtlasData$Page0;
            String s;
            String[] arr_s = new String[5];
            ObjectMap objectMap0 = new ObjectMap(15, 0.99f);
            objectMap0.put("size", new Field() {
                public void parse(Page textureAtlas$TextureAtlasData$Page0) {
                    textureAtlas$TextureAtlasData$Page0.width = (float)Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Page0.height = (float)Integer.parseInt(arr_s[2]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Page)object0));
                }
            });
            objectMap0.put("format", new Field() {
                public void parse(Page textureAtlas$TextureAtlasData$Page0) {
                    textureAtlas$TextureAtlasData$Page0.format = Format.valueOf(arr_s[1]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Page)object0));
                }
            });
            objectMap0.put("filter", new Field() {
                public void parse(Page textureAtlas$TextureAtlasData$Page0) {
                    textureAtlas$TextureAtlasData$Page0.minFilter = TextureFilter.valueOf(arr_s[1]);
                    textureAtlas$TextureAtlasData$Page0.magFilter = TextureFilter.valueOf(arr_s[2]);
                    textureAtlas$TextureAtlasData$Page0.useMipMaps = textureAtlas$TextureAtlasData$Page0.minFilter.isMipMap();
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Page)object0));
                }
            });
            objectMap0.put("repeat", new Field() {
                public void parse(Page textureAtlas$TextureAtlasData$Page0) {
                    if(arr_s[1].indexOf(120) != -1) {
                        textureAtlas$TextureAtlasData$Page0.uWrap = TextureWrap.Repeat;
                    }
                    if(arr_s[1].indexOf(0x79) != -1) {
                        textureAtlas$TextureAtlasData$Page0.vWrap = TextureWrap.Repeat;
                    }
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Page)object0));
                }
            });
            objectMap0.put("pma", new Field() {
                public void parse(Page textureAtlas$TextureAtlasData$Page0) {
                    textureAtlas$TextureAtlasData$Page0.pma = arr_s[1].equals("true");
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Page)object0));
                }
            });
            boolean[] arr_z = {false};
            ObjectMap objectMap1 = new ObjectMap(0x7F, 0.99f);
            objectMap1.put("xy", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.left = Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.top = Integer.parseInt(arr_s[2]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("size", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.width = Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.height = Integer.parseInt(arr_s[2]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("bounds", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.left = Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.top = Integer.parseInt(arr_s[2]);
                    textureAtlas$TextureAtlasData$Region0.width = Integer.parseInt(arr_s[3]);
                    textureAtlas$TextureAtlasData$Region0.height = Integer.parseInt(arr_s[4]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("offset", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.offsetX = (float)Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.offsetY = (float)Integer.parseInt(arr_s[2]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("orig", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.originalWidth = Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.originalHeight = Integer.parseInt(arr_s[2]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("offsets", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.offsetX = (float)Integer.parseInt(arr_s[1]);
                    textureAtlas$TextureAtlasData$Region0.offsetY = (float)Integer.parseInt(arr_s[2]);
                    textureAtlas$TextureAtlasData$Region0.originalWidth = Integer.parseInt(arr_s[3]);
                    textureAtlas$TextureAtlasData$Region0.originalHeight = Integer.parseInt(arr_s[4]);
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("rotate", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    boolean z = true;
                    String s = arr_s[1];
                    if(s.equals("true")) {
                        textureAtlas$TextureAtlasData$Region0.degrees = 90;
                    }
                    else if(!s.equals("false")) {
                        textureAtlas$TextureAtlasData$Region0.degrees = Integer.parseInt(s);
                    }
                    if(textureAtlas$TextureAtlasData$Region0.degrees != 90) {
                        z = false;
                    }
                    textureAtlas$TextureAtlasData$Region0.rotate = z;
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            objectMap1.put("index", new Field() {
                public void parse(Region textureAtlas$TextureAtlasData$Region0) {
                    textureAtlas$TextureAtlasData$Region0.index = Integer.parseInt(arr_s[1]);
                    if(textureAtlas$TextureAtlasData$Region0.index != -1) {
                        arr_z[0] = true;
                    }
                }

                @Override  // com.badlogic.gdx.graphics.g2d.TextureAtlas$TextureAtlasData$Field
                public void parse(Object object0) {
                    this.parse(((Region)object0));
                }
            });
            BufferedReader bufferedReader0 = new BufferedReader(new InputStreamReader(fileHandle0.read()), 0x400);
            try {
                do {
                    s = bufferedReader0.readLine();
                }
                while(s != null && s.trim().length() == 0);
                while(s != null && s.trim().length() != 0 && TextureAtlasData.readEntry(arr_s, s) != 0) {
                    s = bufferedReader0.readLine();
                }
                textureAtlas$TextureAtlasData$Page0 = null;
                array0 = null;
                array1 = null;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error reading texture atlas file: " + fileHandle0, exception0);
            }
            catch(Throwable throwable0) {
                StreamUtils.closeQuietly(bufferedReader0);
                throw throwable0;
            }
        alab1:
            while(true) {
                if(s == null) {
                    StreamUtils.closeQuietly(bufferedReader0);
                    if(arr_z[0]) {
                        com.badlogic.gdx.graphics.g2d.TextureAtlas.TextureAtlasData.14 textureAtlas$TextureAtlasData$140 = new Comparator() {
                            // 去混淆评级： 低(20)
                            public int compare(Region textureAtlas$TextureAtlasData$Region0, Region textureAtlas$TextureAtlasData$Region1) {
                                return (textureAtlas$TextureAtlasData$Region0.index == -1 ? 0x7FFFFFFF : textureAtlas$TextureAtlasData$Region0.index) - (textureAtlas$TextureAtlasData$Region1.index == -1 ? 0x7FFFFFFF : textureAtlas$TextureAtlasData$Region1.index);
                            }

                            @Override
                            public int compare(Object object0, Object object1) {
                                return this.compare(((Region)object0), ((Region)object1));
                            }
                        };
                        this.regions.sort(textureAtlas$TextureAtlasData$140);
                    }
                    return;
                }
                try {
                    if(s.trim().length() != 0) {
                        if(textureAtlas$TextureAtlasData$Page0 == null) {
                            goto label_68;
                        }
                        else {
                            Region textureAtlas$TextureAtlasData$Region0 = new Region();
                            textureAtlas$TextureAtlasData$Region0.page = textureAtlas$TextureAtlasData$Page0;
                            textureAtlas$TextureAtlasData$Region0.name = s.trim();
                            if(z) {
                                textureAtlas$TextureAtlasData$Region0.flip = true;
                            }
                            while(true) {
                            label_39:
                                s = bufferedReader0.readLine();
                                int v = TextureAtlasData.readEntry(arr_s, s);
                                if(v == 0) {
                                    if(textureAtlas$TextureAtlasData$Region0.originalWidth == 0 && textureAtlas$TextureAtlasData$Region0.originalHeight == 0) {
                                        textureAtlas$TextureAtlasData$Region0.originalWidth = textureAtlas$TextureAtlasData$Region0.width;
                                        textureAtlas$TextureAtlasData$Region0.originalHeight = textureAtlas$TextureAtlasData$Region0.height;
                                    }
                                    if(array0 != null && array0.size > 0) {
                                        textureAtlas$TextureAtlasData$Region0.names = (String[])array0.toArray(String.class);
                                        textureAtlas$TextureAtlasData$Region0.values = (int[][])array1.toArray(int[].class);
                                        array0.clear();
                                        array1.clear();
                                    }
                                    this.regions.add(textureAtlas$TextureAtlasData$Region0);
                                    continue alab1;
                                }
                                Field textureAtlas$TextureAtlasData$Field0 = (Field)objectMap1.get(arr_s[0]);
                                if(textureAtlas$TextureAtlasData$Field0 != null) {
                                    textureAtlas$TextureAtlasData$Field0.parse(textureAtlas$TextureAtlasData$Region0);
                                    continue;
                                }
                                if(array0 == null) {
                                    array0 = new Array(8);
                                    array1 = new Array(8);
                                }
                                array0.add(arr_s[0]);
                                arr_v = new int[v];
                                goto label_62;
                            }
                        }
                    }
                    goto label_78;
                }
                catch(Exception exception0) {
                    throw new GdxRuntimeException("Error reading texture atlas file: " + fileHandle0, exception0);
                }
                catch(Throwable throwable0) {
                    break;
                }
            label_62:
                for(int v1 = 0; v1 < v; ++v1) {
                    try {
                        arr_v[v1] = Integer.parseInt(arr_s[v1 + 1]);
                    }
                    catch(NumberFormatException unused_ex) {
                    }
                    catch(Exception exception0) {
                        throw new GdxRuntimeException("Error reading texture atlas file: " + fileHandle0, exception0);
                    }
                    catch(Throwable throwable0) {
                        break alab1;
                    }
                }
                try {
                    try {
                        array1.add(arr_v);
                        goto label_39;
                    label_68:
                        textureAtlas$TextureAtlasData$Page0 = new Page();
                        textureAtlas$TextureAtlasData$Page0.textureFile = fileHandle1.child(s);
                        while(true) {
                            s = bufferedReader0.readLine();
                            if(TextureAtlasData.readEntry(arr_s, s) == 0) {
                                break;
                            }
                            Field textureAtlas$TextureAtlasData$Field1 = (Field)objectMap0.get(arr_s[0]);
                            if(textureAtlas$TextureAtlasData$Field1 != null) {
                                textureAtlas$TextureAtlasData$Field1.parse(textureAtlas$TextureAtlasData$Page0);
                            }
                        }
                        this.pages.add(textureAtlas$TextureAtlasData$Page0);
                        continue;
                    label_78:
                        s = bufferedReader0.readLine();
                        textureAtlas$TextureAtlasData$Page0 = null;
                    }
                    catch(Exception exception0) {
                        throw new GdxRuntimeException("Error reading texture atlas file: " + fileHandle0, exception0);
                    }
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            StreamUtils.closeQuietly(bufferedReader0);
            throw throwable0;
        }

        private static int readEntry(String[] arr_s, @Null String s) throws IOException {
            if(s == null) {
                return 0;
            }
            String s1 = s.trim();
            if(s1.length() == 0) {
                return 0;
            }
            int v = s1.indexOf(58);
            if(v == -1) {
                return 0;
            }
            arr_s[0] = s1.substring(0, v).trim();
            int v2 = v + 1;
            for(int v1 = 1; true; ++v1) {
                int v3 = s1.indexOf(44, v2);
                if(v3 == -1) {
                    arr_s[v1] = s1.substring(v2).trim();
                    return v1;
                }
                arr_s[v1] = s1.substring(v2, v3).trim();
                v2 = v3 + 1;
                if(v1 == 4) {
                    return 4;
                }
            }
        }
    }

    private final Array regions;
    private final ObjectSet textures;

    public TextureAtlas() {
        this.textures = new ObjectSet(4);
        this.regions = new Array();
    }

    public TextureAtlas(FileHandle fileHandle0) {
        this(fileHandle0, fileHandle0.parent());
    }

    public TextureAtlas(FileHandle fileHandle0, FileHandle fileHandle1) {
        this(fileHandle0, fileHandle1, false);
    }

    public TextureAtlas(FileHandle fileHandle0, FileHandle fileHandle1, boolean z) {
        this(new TextureAtlasData(fileHandle0, fileHandle1, z));
    }

    public TextureAtlas(FileHandle fileHandle0, boolean z) {
        this(fileHandle0, fileHandle0.parent(), z);
    }

    public TextureAtlas(TextureAtlasData textureAtlas$TextureAtlasData0) {
        this.textures = new ObjectSet(4);
        this.regions = new Array();
        this.load(textureAtlas$TextureAtlasData0);
    }

    public TextureAtlas(String s) {
        this(Gdx.files.internal(s));
    }

    public AtlasRegion addRegion(String s, Texture texture0, int v, int v1, int v2, int v3) {
        this.textures.add(texture0);
        AtlasRegion textureAtlas$AtlasRegion0 = new AtlasRegion(texture0, v, v1, v2, v3);
        textureAtlas$AtlasRegion0.name = s;
        this.regions.add(textureAtlas$AtlasRegion0);
        return textureAtlas$AtlasRegion0;
    }

    public AtlasRegion addRegion(String s, TextureRegion textureRegion0) {
        this.textures.add(textureRegion0.texture);
        AtlasRegion textureAtlas$AtlasRegion0 = new AtlasRegion(textureRegion0);
        textureAtlas$AtlasRegion0.name = s;
        this.regions.add(textureAtlas$AtlasRegion0);
        return textureAtlas$AtlasRegion0;
    }

    @Null
    public NinePatch createPatch(String s) {
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.regions.get(v1);
            if(textureAtlas$AtlasRegion0.name.equals(s)) {
                int[] arr_v = textureAtlas$AtlasRegion0.findValue("split");
                if(arr_v == null) {
                    throw new IllegalArgumentException("Region does not have ninepatch splits: " + s);
                }
                NinePatch ninePatch0 = new NinePatch(textureAtlas$AtlasRegion0, arr_v[0], arr_v[1], arr_v[2], arr_v[3]);
                int[] arr_v1 = textureAtlas$AtlasRegion0.findValue("pad");
                if(arr_v1 != null) {
                    ninePatch0.setPadding(((float)arr_v1[0]), ((float)arr_v1[1]), ((float)arr_v1[2]), ((float)arr_v1[3]));
                }
                return ninePatch0;
            }
        }
        return null;
    }

    @Null
    public Sprite createSprite(String s) {
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((AtlasRegion)this.regions.get(v1)).name.equals(s)) {
                return this.newSprite(((AtlasRegion)this.regions.get(v1)));
            }
        }
        return null;
    }

    @Null
    public Sprite createSprite(String s, int v) {
        int v1 = this.regions.size;
        int v2 = 0;
        while(v2 < v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.regions.get(v2);
            if(textureAtlas$AtlasRegion0.index != v || !textureAtlas$AtlasRegion0.name.equals(s)) {
                ++v2;
                continue;
            }
            return this.newSprite(((AtlasRegion)this.regions.get(v2)));
        }
        return null;
    }

    public Array createSprites() {
        Array array0 = new Array(true, this.regions.size, Sprite.class);
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            array0.add(this.newSprite(((AtlasRegion)this.regions.get(v1))));
        }
        return array0;
    }

    public Array createSprites(String s) {
        Array array0 = new Array(Sprite.class);
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.regions.get(v1);
            if(textureAtlas$AtlasRegion0.name.equals(s)) {
                array0.add(this.newSprite(textureAtlas$AtlasRegion0));
            }
        }
        return array0;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        for(Object object0: this.textures) {
            ((Texture)object0).dispose();
        }
        this.textures.clear(0);
    }

    @Null
    public AtlasRegion findRegion(String s) {
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((AtlasRegion)this.regions.get(v1)).name.equals(s)) {
                return (AtlasRegion)this.regions.get(v1);
            }
        }
        return null;
    }

    @Null
    public AtlasRegion findRegion(String s, int v) {
        int v1 = this.regions.size;
        int v2 = 0;
        while(v2 < v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.regions.get(v2);
            if(!textureAtlas$AtlasRegion0.name.equals(s) || textureAtlas$AtlasRegion0.index != v) {
                ++v2;
                continue;
            }
            return textureAtlas$AtlasRegion0;
        }
        return null;
    }

    public Array findRegions(String s) {
        Array array0 = new Array(AtlasRegion.class);
        int v = this.regions.size;
        for(int v1 = 0; v1 < v; ++v1) {
            AtlasRegion textureAtlas$AtlasRegion0 = (AtlasRegion)this.regions.get(v1);
            if(textureAtlas$AtlasRegion0.name.equals(s)) {
                array0.add(new AtlasRegion(textureAtlas$AtlasRegion0));
            }
        }
        return array0;
    }

    public Array getRegions() {
        return this.regions;
    }

    public ObjectSet getTextures() {
        return this.textures;
    }

    public void load(TextureAtlasData textureAtlas$TextureAtlasData0) {
        this.textures.ensureCapacity(textureAtlas$TextureAtlasData0.pages.size);
        for(Object object0: textureAtlas$TextureAtlasData0.pages) {
            Page textureAtlas$TextureAtlasData$Page0 = (Page)object0;
            if(textureAtlas$TextureAtlasData$Page0.texture == null) {
                textureAtlas$TextureAtlasData$Page0.texture = new Texture(textureAtlas$TextureAtlasData$Page0.textureFile, textureAtlas$TextureAtlasData$Page0.format, textureAtlas$TextureAtlasData$Page0.useMipMaps);
            }
            textureAtlas$TextureAtlasData$Page0.texture.setFilter(textureAtlas$TextureAtlasData$Page0.minFilter, textureAtlas$TextureAtlasData$Page0.magFilter);
            textureAtlas$TextureAtlasData$Page0.texture.setWrap(textureAtlas$TextureAtlasData$Page0.uWrap, textureAtlas$TextureAtlasData$Page0.vWrap);
            this.textures.add(textureAtlas$TextureAtlasData$Page0.texture);
        }
        this.regions.ensureCapacity(textureAtlas$TextureAtlasData0.regions.size);
        for(Object object1: textureAtlas$TextureAtlasData0.regions) {
            Region textureAtlas$TextureAtlasData$Region0 = (Region)object1;
            AtlasRegion textureAtlas$AtlasRegion0 = new AtlasRegion(textureAtlas$TextureAtlasData$Region0.page.texture, textureAtlas$TextureAtlasData$Region0.left, textureAtlas$TextureAtlasData$Region0.top, (textureAtlas$TextureAtlasData$Region0.rotate ? textureAtlas$TextureAtlasData$Region0.height : textureAtlas$TextureAtlasData$Region0.width), (textureAtlas$TextureAtlasData$Region0.rotate ? textureAtlas$TextureAtlasData$Region0.width : textureAtlas$TextureAtlasData$Region0.height));
            textureAtlas$AtlasRegion0.index = textureAtlas$TextureAtlasData$Region0.index;
            textureAtlas$AtlasRegion0.name = textureAtlas$TextureAtlasData$Region0.name;
            textureAtlas$AtlasRegion0.offsetX = textureAtlas$TextureAtlasData$Region0.offsetX;
            textureAtlas$AtlasRegion0.offsetY = textureAtlas$TextureAtlasData$Region0.offsetY;
            textureAtlas$AtlasRegion0.originalHeight = textureAtlas$TextureAtlasData$Region0.originalHeight;
            textureAtlas$AtlasRegion0.originalWidth = textureAtlas$TextureAtlasData$Region0.originalWidth;
            textureAtlas$AtlasRegion0.rotate = textureAtlas$TextureAtlasData$Region0.rotate;
            textureAtlas$AtlasRegion0.degrees = textureAtlas$TextureAtlasData$Region0.degrees;
            textureAtlas$AtlasRegion0.names = textureAtlas$TextureAtlasData$Region0.names;
            textureAtlas$AtlasRegion0.values = textureAtlas$TextureAtlasData$Region0.values;
            if(textureAtlas$TextureAtlasData$Region0.flip) {
                textureAtlas$AtlasRegion0.flip(false, true);
            }
            this.regions.add(textureAtlas$AtlasRegion0);
        }
    }

    private Sprite newSprite(AtlasRegion textureAtlas$AtlasRegion0) {
        if(textureAtlas$AtlasRegion0.packedWidth == textureAtlas$AtlasRegion0.originalWidth && textureAtlas$AtlasRegion0.packedHeight == textureAtlas$AtlasRegion0.originalHeight) {
            if(textureAtlas$AtlasRegion0.rotate) {
                Sprite sprite0 = new Sprite(textureAtlas$AtlasRegion0);
                sprite0.setBounds(0.0f, 0.0f, ((float)textureAtlas$AtlasRegion0.getRegionHeight()), ((float)textureAtlas$AtlasRegion0.getRegionWidth()));
                sprite0.rotate90(true);
                return sprite0;
            }
            return new Sprite(textureAtlas$AtlasRegion0);
        }
        return new AtlasSprite(textureAtlas$AtlasRegion0);
    }
}

