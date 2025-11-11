package org.lwjgl.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;
import java.util.zip.CRC32;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import org.lwjgl.Version;

final class SharedLibraryLoader {
   private static final Lock EXTRACT_PATH_LOCK = new ReentrantLock();
   @Nullable
   @GuardedBy("EXTRACT_PATH_LOCK")
   private static Path extractPath;

   private SharedLibraryLoader() {
   }

   static FileChannel load(String name, String filename, URL resource) {
      try {
         EXTRACT_PATH_LOCK.lock();

         Path extractedFile;
         try {
            if (extractPath != null) {
               extractedFile = extractPath.resolve(filename);
            } else {
               extractedFile = getExtractPath(filename, resource);
               if (Platform.get() != Platform.WINDOWS || workaroundJDK8195129(extractedFile)) {
                  initExtractPath(extractPath = extractedFile.getParent());
               }
            }
         } finally {
            EXTRACT_PATH_LOCK.unlock();
         }

         return extract(extractedFile, resource);
      } catch (Exception var8) {
         throw new RuntimeException("\tFailed to extract " + name + " library", var8);
      }
   }

   private static void initExtractPath(Path extractPath) {
      String newLibPath = extractPath.toAbsolutePath().toString();
      String libPath = (String)Configuration.LIBRARY_PATH.get();
      if (libPath != null && !libPath.isEmpty()) {
         newLibPath = newLibPath + File.pathSeparator + libPath;
      }

      System.setProperty(Configuration.LIBRARY_PATH.getProperty(), newLibPath);
      Configuration.LIBRARY_PATH.set(newLibPath);
   }

   private static Path getExtractPath(String filename, URL resource) {
      String override = (String)Configuration.SHARED_LIBRARY_EXTRACT_PATH.get();
      if (override != null) {
         return Paths.get(override, filename);
      } else {
         String version = Version.getVersion().replace(' ', '-');
         Path tempDirectory = Paths.get(
            (String)Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl" + System.getProperty("user.name")), version, filename
         );
         Path root;
         Path file = (root = Paths.get(System.getProperty("java.io.tmpdir"))).resolve(tempDirectory);
         if (canWrite(root, file, resource)) {
            return file;
         } else {
            tempDirectory = Paths.get((String)Configuration.SHARED_LIBRARY_EXTRACT_DIRECTORY.get("lwjgl"), version, filename);
            file = (root = Paths.get(System.getProperty("user.home"))).resolve(tempDirectory);
            if (canWrite(root, file, resource)) {
               return file;
            } else {
               file = (root = Paths.get("").toAbsolutePath()).resolve(tempDirectory);
               if (canWrite(root, file, resource)) {
                  return file;
               } else {
                  if (Platform.get() == Platform.WINDOWS) {
                     String env = System.getenv("SystemRoot");
                     if (env != null) {
                        file = (root = Paths.get(env, "Temp")).resolve(tempDirectory);
                        if (canWrite(root, file, resource)) {
                           return file;
                        }
                     }

                     env = System.getenv("SystemDrive");
                     if (env != null) {
                        file = (root = Paths.get(env + "/")).resolve(Paths.get("Temp").resolve(tempDirectory));
                        if (canWrite(root, file, resource)) {
                           return file;
                        }
                     }
                  }

                  try {
                     file = Files.createTempDirectory("lwjgl");
                     root = file.getParent();
                     file = file.resolve(filename);
                     if (canWrite(root, file, resource)) {
                        return file;
                     }
                  } catch (IOException var8) {
                  }

                  throw new RuntimeException("Failed to find an appropriate directory to extract the native library");
               }
            }
         }
      }
   }

   private static FileChannel extract(Path file, URL resource) throws IOException {
      if (Files.exists(file)) {
         try (
            InputStream source = resource.openStream();
            InputStream target = Files.newInputStream(file);
         ) {
            if (crc(source) == crc(target)) {
               if ((Boolean)Configuration.DEBUG_LOADER.get(false)) {
                  APIUtil.apiLog(String.format("\tFound at: %s", file));
               }

               return lock(file);
            }
         }
      }

      APIUtil.apiLog(String.format("    Extracting: %s", resource.getPath()));
      if (extractPath == null) {
         APIUtil.apiLog(String.format("            to: %s", file));
      }

      Files.createDirectories(file.getParent());

      try (InputStream sourcex = resource.openStream()) {
         Files.copy(sourcex, file, StandardCopyOption.REPLACE_EXISTING);
      }

      return lock(file);
   }

   private static FileChannel lock(Path file) {
      try {
         FileChannel fc = FileChannel.open(file);
         if (fc.tryLock(0L, Long.MAX_VALUE, true) == null) {
            if ((Boolean)Configuration.DEBUG_LOADER.get(false)) {
               APIUtil.apiLog("\tFile is locked by another process, waiting...");
            }

            fc.lock(0L, Long.MAX_VALUE, true);
         }

         return fc;
      } catch (Exception var2) {
         throw new RuntimeException("Failed to lock file.", var2);
      }
   }

   private static long crc(InputStream input) throws IOException {
      CRC32 crc = new CRC32();
      byte[] buffer = new byte[8192];

      int n;
      while ((n = input.read(buffer)) != -1) {
         crc.update(buffer, 0, n);
      }

      return crc.getValue();
   }

   private static boolean canWrite(Path root, Path file, URL resource) {
      Path testFile;
      if (Files.exists(file)) {
         if (!Files.isWritable(file)) {
            return false;
         }

         testFile = file.getParent().resolve(".lwjgl.test");
      } else {
         try {
            Files.createDirectories(file.getParent());
         } catch (IOException var17) {
            return false;
         }

         testFile = file;
      }

      try {
         Files.write(testFile, new byte[0]);
         Files.delete(testFile);
         if (workaroundJDK8195129(file)) {
            try (FileChannel ignored = extract(file, resource)) {
               System.load(file.toAbsolutePath().toString());
            }
         }

         return true;
      } catch (Throwable var19) {
         if (file == testFile) {
            canWriteCleanup(root, file);
         }

         return false;
      }
   }

   private static void canWriteCleanup(Path root, Path file) {
      try {
         Files.deleteIfExists(file);

         for (Path parent = file.getParent(); !Files.isSameFile(parent, root); parent = parent.getParent()) {
            try (Stream dir = Files.list(parent)) {
               if (dir.findAny().isPresent()) {
                  break;
               }
            }

            Files.delete(parent);
         }
      } catch (IOException var17) {
      }
   }

   private static boolean workaroundJDK8195129(Path file) {
      return Platform.get() == Platform.WINDOWS && file.toString().endsWith(".dll");
   }
}
