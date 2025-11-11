package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.XmlReader;

public class TmxMapLoader extends BaseTmxMapLoader {
   public TmxMapLoader() {
      super(new InternalFileHandleResolver());
   }

   public TmxMapLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public TiledMap load(String fileName) {
      return this.load(fileName, new TmxMapLoader.Parameters());
   }

   public TiledMap load(String fileName, TmxMapLoader.Parameters parameter) {
      FileHandle tmxFile = this.resolve(fileName);
      this.root = this.xml.parse(tmxFile);
      ObjectMap textures = new ObjectMap();

      for (FileHandle textureFile : this.getDependencyFileHandles(tmxFile)) {
         Texture texture = new Texture(textureFile, parameter.generateMipMaps);
         texture.setFilter(parameter.textureMinFilter, parameter.textureMagFilter);
         textures.put(textureFile.path(), texture);
      }

      TiledMap map = this.loadTiledMap(tmxFile, parameter, new ImageResolver.DirectImageResolver(textures));
      map.setOwnedResources(textures.values().toArray());
      return map;
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle tmxFile, TmxMapLoader.Parameters parameter) {
      this.map = this.loadTiledMap(tmxFile, parameter, new ImageResolver.AssetManagerImageResolver(manager));
   }

   public TiledMap loadSync(AssetManager manager, String fileName, FileHandle file, TmxMapLoader.Parameters parameter) {
      return this.map;
   }

   @Override
   protected Array getDependencyAssetDescriptors(FileHandle tmxFile, TextureLoader.TextureParameter textureParameter) {
      Array descriptors = new Array();

      for (FileHandle handle : this.getDependencyFileHandles(tmxFile)) {
         descriptors.add(new AssetDescriptor(handle, Texture.class, textureParameter));
      }

      return descriptors;
   }

   protected Array getDependencyFileHandles(FileHandle tmxFile) {
      Array fileHandles = new Array();

      for (XmlReader.Element tileset : this.root.getChildrenByName("tileset")) {
         String source = tileset.getAttribute("source", null);
         if (source != null) {
            FileHandle tsxFile = getRelativeFileHandle(tmxFile, source);
            tileset = this.xml.parse(tsxFile);
            XmlReader.Element imageElement = tileset.getChildByName("image");
            if (imageElement != null) {
               String imageSource = tileset.getChildByName("image").getAttribute("source");
               FileHandle image = getRelativeFileHandle(tsxFile, imageSource);
               fileHandles.add(image);
            } else {
               for (XmlReader.Element tile : tileset.getChildrenByName("tile")) {
                  String imageSource = tile.getChildByName("image").getAttribute("source");
                  FileHandle image = getRelativeFileHandle(tsxFile, imageSource);
                  fileHandles.add(image);
               }
            }
         } else {
            XmlReader.Element imageElement = tileset.getChildByName("image");
            if (imageElement != null) {
               String imageSource = tileset.getChildByName("image").getAttribute("source");
               FileHandle image = getRelativeFileHandle(tmxFile, imageSource);
               fileHandles.add(image);
            } else {
               for (XmlReader.Element tile : tileset.getChildrenByName("tile")) {
                  String imageSource = tile.getChildByName("image").getAttribute("source");
                  FileHandle image = getRelativeFileHandle(tmxFile, imageSource);
                  fileHandles.add(image);
               }
            }
         }
      }

      for (XmlReader.Element imageLayer : this.root.getChildrenByName("imagelayer")) {
         XmlReader.Element image = imageLayer.getChildByName("image");
         String source = image.getAttribute("source", null);
         if (source != null) {
            FileHandle handle = getRelativeFileHandle(tmxFile, source);
            fileHandles.add(handle);
         }
      }

      return fileHandles;
   }

   @Override
   protected void addStaticTiles(
      FileHandle tmxFile,
      ImageResolver imageResolver,
      TiledMapTileSet tileSet,
      XmlReader.Element element,
      Array tileElements,
      String name,
      int firstgid,
      int tilewidth,
      int tileheight,
      int spacing,
      int margin,
      String source,
      int offsetX,
      int offsetY,
      String imageSource,
      int imageWidth,
      int imageHeight,
      FileHandle image
   ) {
      MapProperties props = tileSet.getProperties();
      if (image != null) {
         TextureRegion texture = imageResolver.getImage(image.path());
         props.put("imagesource", imageSource);
         props.put("imagewidth", imageWidth);
         props.put("imageheight", imageHeight);
         props.put("tilewidth", tilewidth);
         props.put("tileheight", tileheight);
         props.put("margin", margin);
         props.put("spacing", spacing);
         int stopWidth = texture.getRegionWidth() - tilewidth;
         int stopHeight = texture.getRegionHeight() - tileheight;
         int id = firstgid;

         for (int y = margin; y <= stopHeight; y += tileheight + spacing) {
            for (int x = margin; x <= stopWidth; x += tilewidth + spacing) {
               TextureRegion tileRegion = new TextureRegion(texture, x, y, tilewidth, tileheight);
               int tileId = id++;
               this.addStaticTiledMapTile(tileSet, tileRegion, tileId, offsetX, offsetY);
            }
         }
      } else {
         for (XmlReader.Element tileElement : tileElements) {
            XmlReader.Element imageElement = tileElement.getChildByName("image");
            if (imageElement != null) {
               imageSource = imageElement.getAttribute("source");
               if (source != null) {
                  image = getRelativeFileHandle(getRelativeFileHandle(tmxFile, source), imageSource);
               } else {
                  image = getRelativeFileHandle(tmxFile, imageSource);
               }
            }

            TextureRegion texture = imageResolver.getImage(image.path());
            int tileId = firstgid + tileElement.getIntAttribute("id");
            this.addStaticTiledMapTile(tileSet, texture, tileId, offsetX, offsetY);
         }
      }
   }

   public static class Parameters extends BaseTmxMapLoader.Parameters {
   }
}
