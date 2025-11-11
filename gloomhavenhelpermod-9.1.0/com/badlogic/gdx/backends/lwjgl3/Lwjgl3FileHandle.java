package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.File;

public final class Lwjgl3FileHandle extends FileHandle {
   public Lwjgl3FileHandle(String fileName, Files.FileType type) {
      super(fileName, type);
   }

   public Lwjgl3FileHandle(File file, Files.FileType type) {
      super(file, type);
   }

   @Override
   public FileHandle child(String name) {
      return this.file.getPath().length() == 0 ? new Lwjgl3FileHandle(new File(name), this.type) : new Lwjgl3FileHandle(new File(this.file, name), this.type);
   }

   @Override
   public FileHandle sibling(String name) {
      if (this.file.getPath().length() == 0) {
         throw new GdxRuntimeException("Cannot get the sibling of the root.");
      } else {
         return new Lwjgl3FileHandle(new File(this.file.getParent(), name), this.type);
      }
   }

   @Override
   public FileHandle parent() {
      File parent = this.file.getParentFile();
      if (parent == null) {
         if (this.type == Files.FileType.Absolute) {
            parent = new File("/");
         } else {
            parent = new File("");
         }
      }

      return new Lwjgl3FileHandle(parent, this.type);
   }

   @Override
   public File file() {
      if (this.type == Files.FileType.External) {
         return new File(Lwjgl3Files.externalPath, this.file.getPath());
      } else {
         return this.type == Files.FileType.Local ? new File(Lwjgl3Files.localPath, this.file.getPath()) : this.file;
      }
   }
}
