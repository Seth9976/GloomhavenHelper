package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap.Blending;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.PixmapTextureData;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.OrderedMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PixmapPacker implements Disposable {
    public static class GuillotineStrategy implements PackStrategy {
        static class GuillotinePage extends Page {
            Node root;

            public GuillotinePage(PixmapPacker pixmapPacker0) {
                super(pixmapPacker0);
                this.root = new Node();
                this.root.rect.x = (float)pixmapPacker0.padding;
                this.root.rect.y = (float)pixmapPacker0.padding;
                this.root.rect.width = (float)(pixmapPacker0.pageWidth - pixmapPacker0.padding * 2);
                this.root.rect.height = (float)(pixmapPacker0.pageHeight - pixmapPacker0.padding * 2);
            }
        }

        static final class Node {
            public boolean full;
            public Node leftChild;
            public final Rectangle rect;
            public Node rightChild;

            Node() {
                this.rect = new Rectangle();
            }
        }

        Comparator comparator;

        private Node insert(Node pixmapPacker$GuillotineStrategy$Node0, Rectangle rectangle0) {
            if(!pixmapPacker$GuillotineStrategy$Node0.full && pixmapPacker$GuillotineStrategy$Node0.leftChild != null && pixmapPacker$GuillotineStrategy$Node0.rightChild != null) {
                Node pixmapPacker$GuillotineStrategy$Node1 = this.insert(pixmapPacker$GuillotineStrategy$Node0.leftChild, rectangle0);
                return pixmapPacker$GuillotineStrategy$Node1 == null ? this.insert(pixmapPacker$GuillotineStrategy$Node0.rightChild, rectangle0) : pixmapPacker$GuillotineStrategy$Node1;
            }
            if(pixmapPacker$GuillotineStrategy$Node0.full) {
                return null;
            }
            if(pixmapPacker$GuillotineStrategy$Node0.rect.width == rectangle0.width && pixmapPacker$GuillotineStrategy$Node0.rect.height == rectangle0.height) {
                return pixmapPacker$GuillotineStrategy$Node0;
            }
            if(pixmapPacker$GuillotineStrategy$Node0.rect.width >= rectangle0.width && pixmapPacker$GuillotineStrategy$Node0.rect.height >= rectangle0.height) {
                pixmapPacker$GuillotineStrategy$Node0.leftChild = new Node();
                pixmapPacker$GuillotineStrategy$Node0.rightChild = new Node();
                if(((int)pixmapPacker$GuillotineStrategy$Node0.rect.width) - ((int)rectangle0.width) > ((int)pixmapPacker$GuillotineStrategy$Node0.rect.height) - ((int)rectangle0.height)) {
                    pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.x = pixmapPacker$GuillotineStrategy$Node0.rect.x;
                    pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.y = pixmapPacker$GuillotineStrategy$Node0.rect.y;
                    pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.width = rectangle0.width;
                    pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.height = pixmapPacker$GuillotineStrategy$Node0.rect.height;
                    pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.x = pixmapPacker$GuillotineStrategy$Node0.rect.x + rectangle0.width;
                    pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.y = pixmapPacker$GuillotineStrategy$Node0.rect.y;
                    pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.width = pixmapPacker$GuillotineStrategy$Node0.rect.width - rectangle0.width;
                    pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.height = pixmapPacker$GuillotineStrategy$Node0.rect.height;
                    return this.insert(pixmapPacker$GuillotineStrategy$Node0.leftChild, rectangle0);
                }
                pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.x = pixmapPacker$GuillotineStrategy$Node0.rect.x;
                pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.y = pixmapPacker$GuillotineStrategy$Node0.rect.y;
                pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.width = pixmapPacker$GuillotineStrategy$Node0.rect.width;
                pixmapPacker$GuillotineStrategy$Node0.leftChild.rect.height = rectangle0.height;
                pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.x = pixmapPacker$GuillotineStrategy$Node0.rect.x;
                pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.y = pixmapPacker$GuillotineStrategy$Node0.rect.y + rectangle0.height;
                pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.width = pixmapPacker$GuillotineStrategy$Node0.rect.width;
                pixmapPacker$GuillotineStrategy$Node0.rightChild.rect.height = pixmapPacker$GuillotineStrategy$Node0.rect.height - rectangle0.height;
                return this.insert(pixmapPacker$GuillotineStrategy$Node0.leftChild, rectangle0);
            }
            return null;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.PixmapPacker$PackStrategy
        public Page pack(PixmapPacker pixmapPacker0, String s, Rectangle rectangle0) {
            Page pixmapPacker$Page0;
            if(pixmapPacker0.pages.size == 0) {
                pixmapPacker$Page0 = new GuillotinePage(pixmapPacker0);
                pixmapPacker0.pages.add(pixmapPacker$Page0);
            }
            else {
                pixmapPacker$Page0 = (GuillotinePage)pixmapPacker0.pages.peek();
            }
            float f = (float)pixmapPacker0.padding;
            rectangle0.width += f;
            rectangle0.height += f;
            Node pixmapPacker$GuillotineStrategy$Node0 = this.insert(pixmapPacker$Page0.root, rectangle0);
            if(pixmapPacker$GuillotineStrategy$Node0 == null) {
                pixmapPacker$Page0 = new GuillotinePage(pixmapPacker0);
                pixmapPacker0.pages.add(pixmapPacker$Page0);
                pixmapPacker$GuillotineStrategy$Node0 = this.insert(pixmapPacker$Page0.root, rectangle0);
            }
            pixmapPacker$GuillotineStrategy$Node0.full = true;
            rectangle0.set(pixmapPacker$GuillotineStrategy$Node0.rect.x, pixmapPacker$GuillotineStrategy$Node0.rect.y, pixmapPacker$GuillotineStrategy$Node0.rect.width - f, pixmapPacker$GuillotineStrategy$Node0.rect.height - f);
            return pixmapPacker$Page0;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.PixmapPacker$PackStrategy
        public void sort(Array array0) {
            if(this.comparator == null) {
                this.comparator = new Comparator() {
                    public int compare(Pixmap pixmap0, Pixmap pixmap1) {
                        return Math.max(pixmap0.getWidth(), pixmap0.getHeight()) - Math.max(pixmap1.getWidth(), pixmap1.getHeight());
                    }

                    @Override
                    public int compare(Object object0, Object object1) {
                        return this.compare(((Pixmap)object0), ((Pixmap)object1));
                    }
                };
            }
            array0.sort(this.comparator);
        }
    }

    public interface PackStrategy {
        Page pack(PixmapPacker arg1, String arg2, Rectangle arg3);

        void sort(Array arg1);
    }

    public static class Page {
        final Array addedRects;
        boolean dirty;
        Pixmap image;
        OrderedMap rects;
        Texture texture;

        public Page(PixmapPacker pixmapPacker0) {
            this.rects = new OrderedMap();
            this.addedRects = new Array();
            this.image = new Pixmap(pixmapPacker0.pageWidth, pixmapPacker0.pageHeight, pixmapPacker0.pageFormat);
            this.image.setBlending(Blending.None);
            this.image.setColor(pixmapPacker0.getTransparentColor());
            this.image.fill();
        }

        public Pixmap getPixmap() {
            return this.image;
        }

        public OrderedMap getRects() {
            return this.rects;
        }

        public Texture getTexture() {
            return this.texture;
        }

        public boolean updateTexture(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
            Texture texture0 = this.texture;
            if(texture0 == null) {
                this.texture = new Texture(new PixmapTextureData(this.image, this.image.getFormat(), z, false, true)) {
                    @Override  // com.badlogic.gdx.graphics.Texture
                    public void dispose() {
                        super.dispose();
                        Page.this.image.dispose();
                    }
                };
                this.texture.setFilter(texture$TextureFilter0, texture$TextureFilter1);
            }
            else {
                if(!this.dirty) {
                    return false;
                }
                texture0.load(texture0.getTextureData());
            }
            this.dirty = false;
            return true;
        }
    }

    public static class PixmapPackerRectangle extends Rectangle {
        int offsetX;
        int offsetY;
        int originalHeight;
        int originalWidth;
        int[] pads;
        int[] splits;

        PixmapPackerRectangle(int v, int v1, int v2, int v3) {
            super(((float)v), ((float)v1), ((float)v2), ((float)v3));
            this.offsetX = 0;
            this.offsetY = 0;
            this.originalWidth = v2;
            this.originalHeight = v3;
        }

        PixmapPackerRectangle(int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
            super(((float)v), ((float)v1), ((float)v2), ((float)v3));
            this.offsetX = v4;
            this.offsetY = v5;
            this.originalWidth = v6;
            this.originalHeight = v7;
        }
    }

    public static class SkylineStrategy implements PackStrategy {
        static class SkylinePage extends Page {
            static class Row {
                int height;
                int x;
                int y;

            }

            Array rows;

            public SkylinePage(PixmapPacker pixmapPacker0) {
                super(pixmapPacker0);
                this.rows = new Array();
            }
        }

        Comparator comparator;

        @Override  // com.badlogic.gdx.graphics.g2d.PixmapPacker$PackStrategy
        public Page pack(PixmapPacker pixmapPacker0, String s, Rectangle rectangle0) {
            int v = pixmapPacker0.padding;
            int v1 = pixmapPacker0.pageWidth - v * 2;
            int v2 = pixmapPacker0.pageHeight - v * 2;
            int v3 = ((int)rectangle0.width) + v;
            int v4 = ((int)rectangle0.height) + v;
            int v5 = pixmapPacker0.pages.size;
            int v6 = 0;
            while(v6 < v5) {
                Page pixmapPacker$Page0 = (SkylinePage)pixmapPacker0.pages.get(v6);
                int v7 = pixmapPacker$Page0.rows.size - 1;
                Row pixmapPacker$SkylineStrategy$SkylinePage$Row0 = null;
                for(int v8 = 0; v8 < v7; ++v8) {
                    Row pixmapPacker$SkylineStrategy$SkylinePage$Row1 = (Row)pixmapPacker$Page0.rows.get(v8);
                    if(pixmapPacker$SkylineStrategy$SkylinePage$Row1.x + v3 < v1 && pixmapPacker$SkylineStrategy$SkylinePage$Row1.y + v4 < v2 && v4 <= pixmapPacker$SkylineStrategy$SkylinePage$Row1.height && (pixmapPacker$SkylineStrategy$SkylinePage$Row0 == null || pixmapPacker$SkylineStrategy$SkylinePage$Row1.height < pixmapPacker$SkylineStrategy$SkylinePage$Row0.height)) {
                        pixmapPacker$SkylineStrategy$SkylinePage$Row0 = pixmapPacker$SkylineStrategy$SkylinePage$Row1;
                    }
                }
                if(pixmapPacker$SkylineStrategy$SkylinePage$Row0 == null) {
                    Row pixmapPacker$SkylineStrategy$SkylinePage$Row2 = (Row)pixmapPacker$Page0.rows.peek();
                    if(pixmapPacker$SkylineStrategy$SkylinePage$Row2.y + v4 < v2) {
                        if(pixmapPacker$SkylineStrategy$SkylinePage$Row2.x + v3 < v1) {
                            pixmapPacker$SkylineStrategy$SkylinePage$Row2.height = Math.max(pixmapPacker$SkylineStrategy$SkylinePage$Row2.height, v4);
                            pixmapPacker$SkylineStrategy$SkylinePage$Row0 = pixmapPacker$SkylineStrategy$SkylinePage$Row2;
                        }
                        else if(pixmapPacker$SkylineStrategy$SkylinePage$Row2.y + pixmapPacker$SkylineStrategy$SkylinePage$Row2.height + v4 < v2) {
                            pixmapPacker$SkylineStrategy$SkylinePage$Row0 = new Row();
                            pixmapPacker$SkylineStrategy$SkylinePage$Row0.y = pixmapPacker$SkylineStrategy$SkylinePage$Row2.y + pixmapPacker$SkylineStrategy$SkylinePage$Row2.height;
                            pixmapPacker$SkylineStrategy$SkylinePage$Row0.height = v4;
                            pixmapPacker$Page0.rows.add(pixmapPacker$SkylineStrategy$SkylinePage$Row0);
                        }
                        goto label_30;
                    }
                }
                else {
                label_30:
                    if(pixmapPacker$SkylineStrategy$SkylinePage$Row0 != null) {
                        rectangle0.x = (float)pixmapPacker$SkylineStrategy$SkylinePage$Row0.x;
                        rectangle0.y = (float)pixmapPacker$SkylineStrategy$SkylinePage$Row0.y;
                        pixmapPacker$SkylineStrategy$SkylinePage$Row0.x += v3;
                        return pixmapPacker$Page0;
                    }
                }
                ++v6;
            }
            Page pixmapPacker$Page1 = new SkylinePage(pixmapPacker0);
            pixmapPacker0.pages.add(pixmapPacker$Page1);
            Row pixmapPacker$SkylineStrategy$SkylinePage$Row3 = new Row();
            pixmapPacker$SkylineStrategy$SkylinePage$Row3.x = v3 + v;
            pixmapPacker$SkylineStrategy$SkylinePage$Row3.y = v;
            pixmapPacker$SkylineStrategy$SkylinePage$Row3.height = v4;
            pixmapPacker$Page1.rows.add(pixmapPacker$SkylineStrategy$SkylinePage$Row3);
            rectangle0.x = (float)v;
            rectangle0.y = (float)v;
            return pixmapPacker$Page1;
        }

        @Override  // com.badlogic.gdx.graphics.g2d.PixmapPacker$PackStrategy
        public void sort(Array array0) {
            if(this.comparator == null) {
                this.comparator = new Comparator() {
                    public int compare(Pixmap pixmap0, Pixmap pixmap1) {
                        return pixmap0.getHeight() - pixmap1.getHeight();
                    }

                    @Override
                    public int compare(Object object0, Object object1) {
                        return this.compare(((Pixmap)object0), ((Pixmap)object1));
                    }
                };
            }
            array0.sort(this.comparator);
        }
    }

    int alphaThreshold;
    private Color c;
    boolean disposed;
    boolean duplicateBorder;
    static Pattern indexPattern;
    PackStrategy packStrategy;
    boolean packToTexture;
    int padding;
    Format pageFormat;
    int pageHeight;
    int pageWidth;
    final Array pages;
    boolean stripWhitespaceX;
    boolean stripWhitespaceY;
    Color transparentColor;

    static {
        PixmapPacker.indexPattern = Pattern.compile("(.+)_(\\d+)$");
    }

    public PixmapPacker(int v, int v1, Format pixmap$Format0, int v2, boolean z) {
        this(v, v1, pixmap$Format0, v2, z, false, false, new GuillotineStrategy());
    }

    public PixmapPacker(int v, int v1, Format pixmap$Format0, int v2, boolean z, PackStrategy pixmapPacker$PackStrategy0) {
        this(v, v1, pixmap$Format0, v2, z, false, false, pixmapPacker$PackStrategy0);
    }

    public PixmapPacker(int v, int v1, Format pixmap$Format0, int v2, boolean z, boolean z1, boolean z2, PackStrategy pixmapPacker$PackStrategy0) {
        this.transparentColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        this.pages = new Array();
        this.c = new Color();
        this.pageWidth = v;
        this.pageHeight = v1;
        this.pageFormat = pixmap$Format0;
        this.padding = v2;
        this.duplicateBorder = z;
        this.stripWhitespaceX = z1;
        this.stripWhitespaceY = z2;
        this.packStrategy = pixmapPacker$PackStrategy0;
    }

    @Override  // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        synchronized(this) {
            for(Object object0: this.pages) {
                Page pixmapPacker$Page0 = (Page)object0;
                if(pixmapPacker$Page0.texture == null) {
                    pixmapPacker$Page0.image.dispose();
                }
            }
            this.disposed = true;
        }
    }

    public TextureAtlas generateTextureAtlas(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
        synchronized(this) {
            TextureAtlas textureAtlas0 = new TextureAtlas();
            this.updateTextureAtlas(textureAtlas0, texture$TextureFilter0, texture$TextureFilter1, z);
            return textureAtlas0;
        }
    }

    public boolean getDuplicateBorder() {
        return this.duplicateBorder;
    }

    public boolean getPackToTexture() {
        return this.packToTexture;
    }

    public int getPadding() {
        return this.padding;
    }

    private int[] getPads(Pixmap pixmap0, int[] arr_v) {
        int v7;
        int v = pixmap0.getHeight();
        int v1 = pixmap0.getWidth();
        int v2 = this.getSplitPoint(pixmap0, 1, v - 1, true, true);
        int v3 = this.getSplitPoint(pixmap0, v1 - 1, 1, true, false);
        int v4 = v2 == 0 ? 0 : this.getSplitPoint(pixmap0, v2 + 1, v - 1, false, true);
        int v5 = v3 == 0 ? 0 : this.getSplitPoint(pixmap0, v1 - 1, v3 + 1, false, false);
        int v6 = -1;
        this.getSplitPoint(pixmap0, v4 + 1, v - 1, true, true);
        this.getSplitPoint(pixmap0, v1 - 1, v5 + 1, true, false);
        if(v2 == 0 && v4 == 0 && v3 == 0 && v5 == 0) {
            return null;
        }
        if(v2 == 0 && v4 == 0) {
            v7 = -1;
            v2 = -1;
        }
        else if(v2 > 0) {
            --v2;
            v7 = pixmap0.getWidth() - 2 - (v4 - 1);
        }
        else {
            v7 = pixmap0.getWidth() - 2;
        }
        if(v3 == 0 && v5 == 0) {
            v3 = -1;
        }
        else if(v3 > 0) {
            --v3;
            v6 = pixmap0.getHeight() - 2 - (v5 - 1);
        }
        else {
            v6 = pixmap0.getHeight() - 2;
        }
        int[] arr_v1 = {v2, v7, v3, v6};
        return arr_v == null || !Arrays.equals(arr_v1, arr_v) ? arr_v1 : null;
    }

    public Page getPage(String s) {
        synchronized(this) {
            for(Object object0: this.pages) {
                Page pixmapPacker$Page0 = (Page)object0;
                if(((Rectangle)pixmapPacker$Page0.rects.get(s)) != null) {
                    return pixmapPacker$Page0;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }
    }

    public Format getPageFormat() {
        return this.pageFormat;
    }

    public int getPageHeight() {
        return this.pageHeight;
    }

    public int getPageIndex(String s) {
        synchronized(this) {
            for(int v1 = 0; v1 < this.pages.size; ++v1) {
                if(((Rectangle)((Page)this.pages.get(v1)).rects.get(s)) != null) {
                    return v1;
                }
            }
            return -1;
        }
    }

    public int getPageWidth() {
        return this.pageWidth;
    }

    public Array getPages() {
        return this.pages;
    }

    public Rectangle getRect(String s) {
        synchronized(this) {
            for(Object object0: this.pages) {
                Rectangle rectangle0 = (Rectangle)((Page)object0).rects.get(s);
                if(rectangle0 != null) {
                    return rectangle0;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }
    }

    private int getSplitPoint(Pixmap pixmap0, int v, int v1, boolean z, boolean z1) {
        int[] arr_v = new int[4];
        int v2 = z1 ? v : v1;
        int v3 = z1 ? pixmap0.getWidth() : pixmap0.getHeight();
        while(v2 != v3) {
            if(z1) {
                v = v2;
            }
            else {
                v1 = v2;
            }
            int v4 = pixmap0.getPixel(v, v1);
            this.c.set(v4);
            arr_v[0] = (int)(this.c.r * 255.0f);
            arr_v[1] = (int)(this.c.g * 255.0f);
            arr_v[2] = (int)(this.c.b * 255.0f);
            arr_v[3] = (int)(this.c.a * 255.0f);
            if(arr_v[3] == (z ? 0xFF : 0)) {
                return v2;
            }
            if(!z && (arr_v[0] != 0 || arr_v[1] != 0 || arr_v[2] != 0 || arr_v[3] != 0xFF)) {
                System.out.println(v + "  " + v1 + " " + arr_v + " ");
            }
            ++v2;
        }
        return 0;
    }

    private int[] getSplits(Pixmap pixmap0) {
        int v4;
        int v = this.getSplitPoint(pixmap0, 1, 0, true, true);
        int v1 = this.getSplitPoint(pixmap0, v, 0, false, true);
        int v2 = this.getSplitPoint(pixmap0, 0, 1, true, false);
        int v3 = this.getSplitPoint(pixmap0, 0, v2, false, false);
        this.getSplitPoint(pixmap0, v1 + 1, 0, true, true);
        this.getSplitPoint(pixmap0, 0, v3 + 1, true, false);
        if(v == 0 && v1 == 0 && v2 == 0 && v3 == 0) {
            return null;
        }
        if(v != 0) {
            --v;
            v4 = pixmap0.getWidth() - 2 - (v1 - 1);
            return v2 == 0 ? new int[]{v, v4, 0, pixmap0.getHeight() - 2} : new int[]{v, v4, v2 - 1, pixmap0.getHeight() - 2 - (v3 - 1)};
        }
        v4 = pixmap0.getWidth() - 2;
        return v2 == 0 ? new int[]{0, v4, 0, pixmap0.getHeight() - 2} : new int[]{0, v4, v2 - 1, pixmap0.getHeight() - 2 - (v3 - 1)};
    }

    public Color getTransparentColor() {
        return this.transparentColor;
    }

    public Rectangle pack(Pixmap pixmap0) {
        synchronized(this) {
            return this.pack(null, pixmap0);
        }
    }

    public Rectangle pack(String s, Pixmap pixmap0) {
        int v16;
        int v10;
        Rectangle rectangle0;
        Pixmap pixmap1 = null;
        String s1 = s;
        Pixmap pixmap2 = pixmap0;
        synchronized(this) {
            if(this.disposed) {
                return null;
            }
            if(s1 != null && this.getRect(s) != null) {
                throw new GdxRuntimeException("Pixmap has already been packed with name: " + s1);
            }
            if(s1 != null && s1.endsWith(".9")) {
                rectangle0 = new PixmapPackerRectangle(0, 0, pixmap0.getWidth() - 2, pixmap0.getHeight() - 2);
                Pixmap pixmap3 = new Pixmap(pixmap0.getWidth() - 2, pixmap0.getHeight() - 2, pixmap0.getFormat());
                pixmap3.setBlending(Blending.None);
                rectangle0.splits = this.getSplits(pixmap2);
                rectangle0.pads = this.getPads(pixmap2, rectangle0.splits);
                pixmap3.drawPixmap(pixmap0, 0, 0, 1, 1, pixmap0.getWidth() - 1, pixmap0.getHeight() - 1);
                s1 = s1.split("\\.")[0];
                pixmap2 = pixmap3;
                pixmap1 = pixmap2;
            }
            else if(this.stripWhitespaceX || this.stripWhitespaceY) {
                int v2 = pixmap0.getWidth();
                int v3 = pixmap0.getHeight();
                int v4 = pixmap0.getHeight();
                if(this.stripWhitespaceY) {
                    int v6 = 0;
                alab1:
                    for(int v5 = 0; v5 < pixmap0.getHeight(); ++v5) {
                        for(int v7 = 0; v7 < pixmap0.getWidth(); ++v7) {
                            if((pixmap2.getPixel(v7, v5) & 0xFF) > this.alphaThreshold) {
                                break alab1;
                            }
                        }
                        ++v6;
                    }
                    int v8 = pixmap0.getHeight();
                alab2:
                    while(true) {
                        --v8;
                        if(v8 < v6) {
                            break;
                        }
                        for(int v9 = 0; v9 < pixmap0.getWidth(); ++v9) {
                            if((pixmap2.getPixel(v9, v8) & 0xFF) > this.alphaThreshold) {
                                break alab2;
                            }
                        }
                        --v4;
                    }
                    v10 = v6;
                }
                else {
                    v10 = 0;
                }
                int v11 = pixmap0.getWidth();
                if(this.stripWhitespaceX) {
                    int v12 = 0;
                alab3:
                    for(int v = 0; v < pixmap0.getWidth(); ++v) {
                        for(int v13 = v10; v13 < v4; ++v13) {
                            if((pixmap2.getPixel(v, v13) & 0xFF) > this.alphaThreshold) {
                                break alab3;
                            }
                        }
                        ++v12;
                    }
                    int v14 = pixmap0.getWidth();
                alab4:
                    while(true) {
                        --v14;
                        if(v14 < v12) {
                            break;
                        }
                        for(int v15 = v10; v15 < v4; ++v15) {
                            if((pixmap2.getPixel(v14, v15) & 0xFF) > this.alphaThreshold) {
                                break alab4;
                            }
                        }
                        --v11;
                    }
                    v16 = v12;
                }
                else {
                    v16 = 0;
                }
                int v17 = v11 - v16;
                int v18 = v4 - v10;
                Pixmap pixmap4 = new Pixmap(v17, v18, pixmap0.getFormat());
                pixmap4.setBlending(Blending.None);
                pixmap4.drawPixmap(pixmap0, 0, 0, v16, v10, v17, v18);
                pixmap1 = pixmap4;
                rectangle0 = new PixmapPackerRectangle(0, 0, v17, v18, v16, v10, v2, v3);
                pixmap2 = pixmap1;
            }
            else {
                rectangle0 = new PixmapPackerRectangle(0, 0, pixmap0.getWidth(), pixmap0.getHeight());
            }
            if(((PixmapPackerRectangle)rectangle0).getWidth() <= ((float)this.pageWidth) && ((PixmapPackerRectangle)rectangle0).getHeight() <= ((float)this.pageHeight)) {
                Page pixmapPacker$Page0 = this.packStrategy.pack(this, s1, rectangle0);
                if(s1 != null) {
                    pixmapPacker$Page0.rects.put(s1, rectangle0);
                    pixmapPacker$Page0.addedRects.add(s1);
                }
                int v19 = (int)rectangle0.x;
                int v20 = (int)rectangle0.y;
                int v21 = (int)rectangle0.width;
                int v22 = (int)rectangle0.height;
                if(!this.packToTexture || this.duplicateBorder || pixmapPacker$Page0.texture == null || pixmapPacker$Page0.dirty) {
                    pixmapPacker$Page0.dirty = true;
                }
                else {
                    pixmapPacker$Page0.texture.bind();
                    Gdx.gl.glTexSubImage2D(pixmapPacker$Page0.texture.glTarget, 0, v19, v20, v21, v22, pixmap2.getGLFormat(), pixmap2.getGLType(), pixmap2.getPixels());
                }
                pixmapPacker$Page0.image.drawPixmap(pixmap2, v19, v20);
                if(this.duplicateBorder) {
                    int v23 = pixmap2.getWidth();
                    int v24 = pixmap2.getHeight();
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, 0, 0, 1, 1, v19 - 1, v20 - 1, 1, 1);
                    int v25 = v19 + v21;
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, v23 - 1, 0, 1, 1, v25, v20 - 1, 1, 1);
                    int v26 = v20 + v22;
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, 0, v24 - 1, 1, 1, v19 - 1, v26, 1, 1);
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, v23 - 1, v24 - 1, 1, 1, v25, v26, 1, 1);
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, 0, 0, v23, 1, v19, v20 - 1, v21, 1);
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, 0, v24 - 1, v23, 1, v19, v26, v21, 1);
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, 0, 0, 1, v24, v19 - 1, v20, 1, v22);
                    pixmapPacker$Page0.image.drawPixmap(pixmap2, v23 - 1, 0, 1, v24, v25, v20, 1, v22);
                }
                if(pixmap1 != null) {
                    pixmap1.dispose();
                }
                return rectangle0;
            }
            if(s1 == null) {
                throw new GdxRuntimeException("Page size too small for pixmap.");
            }
        }
        throw new GdxRuntimeException("Page size too small for pixmap: " + s1);
    }

    public void setDuplicateBorder(boolean z) {
        this.duplicateBorder = z;
    }

    public void setPackToTexture(boolean z) {
        this.packToTexture = z;
    }

    public void setPadding(int v) {
        this.padding = v;
    }

    public void setPageFormat(Format pixmap$Format0) {
        this.pageFormat = pixmap$Format0;
    }

    public void setPageHeight(int v) {
        this.pageHeight = v;
    }

    public void setPageWidth(int v) {
        this.pageWidth = v;
    }

    public void setTransparentColor(Color color0) {
        this.transparentColor.set(color0);
    }

    public void sort(Array array0) {
        this.packStrategy.sort(array0);
    }

    public void updatePageTextures(TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
        synchronized(this) {
            for(Object object0: this.pages) {
                ((Page)object0).updateTexture(texture$TextureFilter0, texture$TextureFilter1, z);
            }
        }
    }

    public void updateTextureAtlas(TextureAtlas textureAtlas0, TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
        synchronized(this) {
            this.updateTextureAtlas(textureAtlas0, texture$TextureFilter0, texture$TextureFilter1, z, true);
        }
    }

    public void updateTextureAtlas(TextureAtlas textureAtlas0, TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z, boolean z1) {
        synchronized(this) {
            this.updatePageTextures(texture$TextureFilter0, texture$TextureFilter1, z);
            for(Object object0: this.pages) {
                Page pixmapPacker$Page0 = (Page)object0;
                if(pixmapPacker$Page0.addedRects.size > 0) {
                    for(Object object1: pixmapPacker$Page0.addedRects) {
                        String s = (String)object1;
                        PixmapPackerRectangle pixmapPacker$PixmapPackerRectangle0 = (PixmapPackerRectangle)pixmapPacker$Page0.rects.get(s);
                        AtlasRegion textureAtlas$AtlasRegion0 = new AtlasRegion(pixmapPacker$Page0.texture, ((int)pixmapPacker$PixmapPackerRectangle0.x), ((int)pixmapPacker$PixmapPackerRectangle0.y), ((int)pixmapPacker$PixmapPackerRectangle0.width), ((int)pixmapPacker$PixmapPackerRectangle0.height));
                        if(pixmapPacker$PixmapPackerRectangle0.splits != null) {
                            textureAtlas$AtlasRegion0.names = new String[]{"split", "pad"};
                            textureAtlas$AtlasRegion0.values = new int[][]{pixmapPacker$PixmapPackerRectangle0.splits, pixmapPacker$PixmapPackerRectangle0.pads};
                        }
                        int v1 = -1;
                        if(z1) {
                            Matcher matcher0 = PixmapPacker.indexPattern.matcher(s);
                            if(matcher0.matches()) {
                                s = matcher0.group(1);
                                v1 = Integer.parseInt(matcher0.group(2));
                            }
                        }
                        textureAtlas$AtlasRegion0.name = s;
                        textureAtlas$AtlasRegion0.index = v1;
                        textureAtlas$AtlasRegion0.offsetX = (float)pixmapPacker$PixmapPackerRectangle0.offsetX;
                        textureAtlas$AtlasRegion0.offsetY = (float)(((int)(((float)pixmapPacker$PixmapPackerRectangle0.originalHeight) - pixmapPacker$PixmapPackerRectangle0.height - ((float)pixmapPacker$PixmapPackerRectangle0.offsetY))));
                        textureAtlas$AtlasRegion0.originalWidth = pixmapPacker$PixmapPackerRectangle0.originalWidth;
                        textureAtlas$AtlasRegion0.originalHeight = pixmapPacker$PixmapPackerRectangle0.originalHeight;
                        textureAtlas0.getRegions().add(textureAtlas$AtlasRegion0);
                    }
                    pixmapPacker$Page0.addedRects.clear();
                    textureAtlas0.getTextures().add(pixmapPacker$Page0.texture);
                }
            }
        }
    }

    public void updateTextureRegions(Array array0, TextureFilter texture$TextureFilter0, TextureFilter texture$TextureFilter1, boolean z) {
        synchronized(this) {
            this.updatePageTextures(texture$TextureFilter0, texture$TextureFilter1, z);
            while(array0.size < this.pages.size) {
                array0.add(new TextureRegion(((Page)this.pages.get(array0.size)).texture));
            }
        }
    }
}

