package com.badlogic.gdx.graphics.g2d;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import java.io.IOException;
import java.io.Writer;
import java.util.regex.Matcher;

public class PixmapPackerIO {
   public void save(FileHandle file, PixmapPacker packer) throws IOException {
      this.save(file, packer, new PixmapPackerIO.SaveParameters());
   }

   public void save(FileHandle file, PixmapPacker packer, PixmapPackerIO.SaveParameters parameters) throws IOException {
      Writer writer = file.writer(false);
      int index = 0;

      for (PixmapPacker.Page page : packer.pages) {
         if (page.rects.size > 0) {
            FileHandle pageFile = file.sibling(file.nameWithoutExtension() + "_" + ++index + parameters.format.getExtension());
            switch (parameters.format) {
               case CIM:
                  PixmapIO.writeCIM(pageFile, page.image);
                  break;
               case PNG:
                  PixmapIO.writePNG(pageFile, page.image);
            }

            writer.write("\n");
            writer.write(pageFile.name() + "\n");
            writer.write("size: " + page.image.getWidth() + "," + page.image.getHeight() + "\n");
            writer.write("format: " + packer.pageFormat.name() + "\n");
            writer.write("filter: " + parameters.minFilter.name() + "," + parameters.magFilter.name() + "\n");
            writer.write("repeat: none\n");

            for (String name : page.rects.keys()) {
               int imageIndex = -1;
               String imageName = name;
               if (parameters.useIndexes) {
                  Matcher matcher = PixmapPacker.indexPattern.matcher(name);
                  if (matcher.matches()) {
                     imageName = matcher.group(1);
                     imageIndex = Integer.parseInt(matcher.group(2));
                  }
               }

               writer.write(imageName + "\n");
               PixmapPacker.PixmapPackerRectangle rect = (PixmapPacker.PixmapPackerRectangle)page.rects.get(name);
               writer.write("  rotate: false\n");
               writer.write("  xy: " + (int)rect.x + "," + (int)rect.y + "\n");
               writer.write("  size: " + (int)rect.width + "," + (int)rect.height + "\n");
               if (rect.splits != null) {
                  writer.write("  split: " + rect.splits[0] + ", " + rect.splits[1] + ", " + rect.splits[2] + ", " + rect.splits[3] + "\n");
                  if (rect.pads != null) {
                     writer.write("  pad: " + rect.pads[0] + ", " + rect.pads[1] + ", " + rect.pads[2] + ", " + rect.pads[3] + "\n");
                  }
               }

               writer.write("  orig: " + rect.originalWidth + ", " + rect.originalHeight + "\n");
               writer.write("  offset: " + rect.offsetX + ", " + (int)(rect.originalHeight - rect.height - rect.offsetY) + "\n");
               writer.write("  index: " + imageIndex + "\n");
            }
         }
      }

      writer.close();
   }

   public static enum ImageFormat {
      CIM(".cim"),
      PNG(".png");

      private final String extension;

      public String getExtension() {
         return this.extension;
      }

      private ImageFormat(String extension) {
         this.extension = extension;
      }
   }

   public static class SaveParameters {
      public PixmapPackerIO.ImageFormat format = PixmapPackerIO.ImageFormat.PNG;
      public Texture.TextureFilter minFilter = Texture.TextureFilter.Nearest;
      public Texture.TextureFilter magFilter = Texture.TextureFilter.Nearest;
      public boolean useIndexes;
   }
}
