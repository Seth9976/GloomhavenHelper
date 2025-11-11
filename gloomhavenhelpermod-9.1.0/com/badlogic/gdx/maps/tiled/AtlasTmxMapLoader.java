package com.badlogic.gdx.maps.tiled;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.ImageResolver;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;

public class AtlasTmxMapLoader extends BaseTmxMapLoader {
   protected Array trackedTextures = new Array();
   protected AtlasTmxMapLoader.AtlasResolver atlasResolver;

   public AtlasTmxMapLoader() {
      super(new InternalFileHandleResolver());
   }

   public AtlasTmxMapLoader(FileHandleResolver resolver) {
      super(resolver);
   }

   public TiledMap load(String fileName) {
      return this.load(fileName, new AtlasTmxMapLoader.AtlasTiledMapLoaderParameters());
   }

   public TiledMap load(String fileName, AtlasTmxMapLoader.AtlasTiledMapLoaderParameters parameter) {
      FileHandle tmxFile = this.resolve(fileName);
      this.root = this.xml.parse(tmxFile);
      FileHandle atlasFileHandle = this.getAtlasFileHandle(tmxFile);
      TextureAtlas atlas = new TextureAtlas(atlasFileHandle);
      this.atlasResolver = new AtlasTmxMapLoader.AtlasResolver.DirectAtlasResolver(atlas);
      TiledMap map = this.loadTiledMap(tmxFile, parameter, this.atlasResolver);
      map.setOwnedResources(new Array(new TextureAtlas[]{atlas}));
      this.setTextureFilters(parameter.textureMinFilter, parameter.textureMagFilter);
      return map;
   }

   public void loadAsync(AssetManager manager, String fileName, FileHandle tmxFile, AtlasTmxMapLoader.AtlasTiledMapLoaderParameters parameter) {
      FileHandle atlasHandle = this.getAtlasFileHandle(tmxFile);
      this.atlasResolver = new AtlasTmxMapLoader.AtlasResolver.AssetManagerAtlasResolver(manager, atlasHandle.path());
      this.map = this.loadTiledMap(tmxFile, parameter, this.atlasResolver);
   }

   public TiledMap loadSync(AssetManager manager, String fileName, FileHandle file, AtlasTmxMapLoader.AtlasTiledMapLoaderParameters parameter) {
      if (parameter != null) {
         this.setTextureFilters(parameter.textureMinFilter, parameter.textureMagFilter);
      }

      return this.map;
   }

   @Override
   protected Array getDependencyAssetDescriptors(FileHandle tmxFile, TextureLoader.TextureParameter textureParameter) {
      Array descriptors = new Array();
      FileHandle atlasFileHandle = this.getAtlasFileHandle(tmxFile);
      if (atlasFileHandle != null) {
         descriptors.add(new AssetDescriptor(atlasFileHandle, TextureAtlas.class));
      }

      return descriptors;
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
      TextureAtlas atlas = this.atlasResolver.getAtlas();

      for (Texture texture : atlas.getTextures()) {
         this.trackedTextures.add(texture);
      }

      MapProperties props = tileSet.getProperties();
      props.put("imagesource", imageSource);
      props.put("imagewidth", imageWidth);
      props.put("imageheight", imageHeight);
      props.put("tilewidth", tilewidth);
      props.put("tileheight", tileheight);
      props.put("margin", margin);
      props.put("spacing", spacing);
      if (imageSource != null && imageSource.length() > 0) {
         int lastgid = firstgid + imageWidth / tilewidth * (imageHeight / tileheight) - 1;

         for (TextureAtlas.AtlasRegion region : atlas.findRegions(name)) {
            if (region != null) {
               int tileId = firstgid + region.index;
               if (tileId >= firstgid && tileId <= lastgid) {
                  this.addStaticTiledMapTile(tileSet, region, tileId, offsetX, offsetY);
               }
            }
         }
      }

      for (XmlReader.Element tileElement : tileElements) {
         int tileId = firstgid + tileElement.getIntAttribute("id", 0);
         TiledMapTile tile = tileSet.getTile(tileId);
         if (tile == null) {
            XmlReader.Element imageElement = tileElement.getChildByName("image");
            if (imageElement != null) {
               String regionName = imageElement.getAttribute("source");
               regionName = regionName.substring(0, regionName.lastIndexOf(46));
               TextureAtlas.AtlasRegion regionx = atlas.findRegion(regionName);
               if (regionx == null) {
                  throw new GdxRuntimeException("Tileset atlasRegion not found: " + regionName);
               }

               this.addStaticTiledMapTile(tileSet, regionx, tileId, offsetX, offsetY);
            }
         }
      }
   }

   protected FileHandle getAtlasFileHandle(FileHandle tmxFile) {
      XmlReader.Element properties = this.root.getChildByName("properties");
      String atlasFilePath = null;
      if (properties != null) {
         for (XmlReader.Element property : properties.getChildrenByName("property")) {
            String name = property.getAttribute("name");
            if (name.startsWith("atlas")) {
               atlasFilePath = property.getAttribute("value");
               break;
            }
         }
      }

      if (atlasFilePath == null) {
         throw new GdxRuntimeException("The map is missing the 'atlas' property");
      } else {
         FileHandle fileHandle = getRelativeFileHandle(tmxFile, atlasFilePath);
         if (!fileHandle.exists()) {
            throw new GdxRuntimeException("The 'atlas' file could not be found: '" + atlasFilePath + "'");
         } else {
            return fileHandle;
         }
      }
   }

   protected void setTextureFilters(Texture.TextureFilter min, Texture.TextureFilter mag) {
      for (Texture texture : this.trackedTextures) {
         texture.setFilter(min, mag);
      }

      this.trackedTextures.clear();
   }

   protected interface AtlasResolver extends ImageResolver {
      TextureAtlas getAtlas();

      public static class AssetManagerAtlasResolver implements AtlasTmxMapLoader.AtlasResolver {
         private final AssetManager assetManager;
         private final String atlasName;

         public AssetManagerAtlasResolver(AssetManager assetManager, String atlasName) {
            this.assetManager = assetManager;
            this.atlasName = atlasName;
         }

         @Override
         public TextureAtlas getAtlas() {
            return (TextureAtlas)this.assetManager.get(this.atlasName, TextureAtlas.class);
         }

         @Override
         public TextureRegion getImage(String name) {
            return this.getAtlas().findRegion(name);
         }
      }

      public static class DirectAtlasResolver implements AtlasTmxMapLoader.AtlasResolver {
         private final TextureAtlas atlas;

         public DirectAtlasResolver(TextureAtlas atlas) {
            this.atlas = atlas;
         }

         @Override
         public TextureAtlas getAtlas() {
            return this.atlas;
         }

         @Override
         public TextureRegion getImage(String name) {
            return this.atlas.findRegion(name);
         }
      }
   }

   public static class AtlasTiledMapLoaderParameters extends BaseTmxMapLoader.Parameters {
      public boolean forceTextureFilters = false;
   }
}
