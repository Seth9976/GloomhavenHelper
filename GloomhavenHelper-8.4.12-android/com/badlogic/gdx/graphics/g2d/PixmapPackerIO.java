package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;

public class PixmapPackerIO {
    public static enum ImageFormat {
        CIM(".cim"),
        PNG(".png");

        private final String extension;

        private ImageFormat(String s1) {
            this.extension = s1;
        }

        public String getExtension() {
            return this.extension;
        }
    }

    public static class SaveParameters {
        public ImageFormat format;
        public TextureFilter magFilter;
        public TextureFilter minFilter;
        public boolean useIndexes;

        public SaveParameters() {
            this.format = ImageFormat.PNG;
            this.minFilter = TextureFilter.Nearest;
            this.magFilter = TextureFilter.Nearest;
        }
    }

    public void save(FileHandle fileHandle0, PixmapPacker pixmapPacker0) throws IOException {
        this.save(fileHandle0, pixmapPacker0, new SaveParameters());
    }

    public void save(FileHandle fileHandle0, PixmapPacker pixmapPacker0, SaveParameters pixmapPackerIO$SaveParameters0) throws IOException {
        int v1;
        String s1;
        Writer writer0 = fileHandle0.writer(false);
        int v = 0;
        for(Object object0: pixmapPacker0.pages) {
            Page pixmapPacker$Page0 = (Page)object0;
            if(pixmapPacker$Page0.rects.size > 0) {
                ++v;
                FileHandle fileHandle1 = fileHandle0.sibling(fileHandle0.nameWithoutExtension() + "_" + v + pixmapPackerIO$SaveParameters0.format.getExtension());
                switch(pixmapPackerIO$SaveParameters0.format) {
                    case CIM: {
                        PixmapIO.writeCIM(fileHandle1, pixmapPacker$Page0.image);
                        break;
                    }
                    case PNG: {
                        PixmapIO.writePNG(fileHandle1, pixmapPacker$Page0.image);
                    }
                }
                writer0.write("\n");
                writer0.write(fileHandle1.name() + "\n");
                writer0.write("size: " + pixmapPacker$Page0.image.getWidth() + "," + pixmapPacker$Page0.image.getHeight() + "\n");
                writer0.write("format: " + pixmapPacker0.pageFormat.name() + "\n");
                writer0.write("filter: " + pixmapPackerIO$SaveParameters0.minFilter.name() + "," + pixmapPackerIO$SaveParameters0.magFilter.name() + "\n");
                writer0.write("repeat: none\n");
                for(Object object1: pixmapPacker$Page0.rects.keys()) {
                    String s = (String)object1;
                    if(pixmapPackerIO$SaveParameters0.useIndexes) {
                        Matcher matcher0 = PixmapPacker.indexPattern.matcher(s);
                        if(matcher0.matches()) {
                            s1 = matcher0.group(1);
                            v1 = Integer.parseInt(matcher0.group(2));
                        }
                    }
                    else {
                        s1 = s;
                        v1 = -1;
                    }
                    writer0.write(s1 + "\n");
                    PixmapPackerRectangle pixmapPacker$PixmapPackerRectangle0 = (PixmapPackerRectangle)pixmapPacker$Page0.rects.get(s);
                    writer0.write("  rotate: false\n");
                    writer0.write("  xy: " + ((int)pixmapPacker$PixmapPackerRectangle0.x) + "," + ((int)pixmapPacker$PixmapPackerRectangle0.y) + "\n");
                    writer0.write("  size: " + ((int)pixmapPacker$PixmapPackerRectangle0.width) + "," + ((int)pixmapPacker$PixmapPackerRectangle0.height) + "\n");
                    if(pixmapPacker$PixmapPackerRectangle0.splits != null) {
                        writer0.write("  split: " + pixmapPacker$PixmapPackerRectangle0.splits[0] + ", " + pixmapPacker$PixmapPackerRectangle0.splits[1] + ", " + pixmapPacker$PixmapPackerRectangle0.splits[2] + ", " + pixmapPacker$PixmapPackerRectangle0.splits[3] + "\n");
                        if(pixmapPacker$PixmapPackerRectangle0.pads != null) {
                            writer0.write("  pad: " + pixmapPacker$PixmapPackerRectangle0.pads[0] + ", " + pixmapPacker$PixmapPackerRectangle0.pads[1] + ", " + pixmapPacker$PixmapPackerRectangle0.pads[2] + ", " + pixmapPacker$PixmapPackerRectangle0.pads[3] + "\n");
                        }
                    }
                    writer0.write("  orig: " + pixmapPacker$PixmapPackerRectangle0.originalWidth + ", " + pixmapPacker$PixmapPackerRectangle0.originalHeight + "\n");
                    writer0.write("  offset: " + pixmapPacker$PixmapPackerRectangle0.offsetX + ", " + ((int)(((float)pixmapPacker$PixmapPackerRectangle0.originalHeight) - pixmapPacker$PixmapPackerRectangle0.height - ((float)pixmapPacker$PixmapPackerRectangle0.offsetY))) + "\n");
                    writer0.write("  index: " + v1 + "\n");
                }
            }
        }
        writer0.close();
    }
}

