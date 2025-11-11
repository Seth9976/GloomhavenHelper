package org.lwjgl.system;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.lwjgl.Version;

public final class Library {
   public static final String JNI_LIBRARY_NAME = (String)Configuration.LIBRARY_NAME.get(Platform.mapLibraryNameBundled("lwjgl"));
   static final String JAVA_LIBRARY_PATH = "java.library.path";
   private static final Pattern PATH_SEPARATOR = Pattern.compile(File.pathSeparator);
   private static final Pattern NATIVES_JAR = Pattern.compile("/[\\w-]+?-natives-\\w+.jar!/");

   private Library() {
   }

   public static void initialize() {
   }

   public static void loadSystem(String module, String name) throws UnsatisfiedLinkError {
      loadSystem(System::load, System::loadLibrary, Library.class, module, name);
   }

   public static void loadSystem(Consumer load, Consumer loadLibrary, Class context, String module, String name) throws UnsatisfiedLinkError {
      APIUtil.apiLog("Loading JNI library: " + name);
      APIUtil.apiLog("\tModule: " + module);
      if (Paths.get(name).isAbsolute()) {
         load.accept(name);
         APIUtil.apiLog("\tSuccess");
      } else {
         String libName = Platform.get().mapLibraryName(name);
         boolean bundledWithLWJGL = name.contains("lwjgl");
         URL libURL = findResource(context, module, libName, bundledWithLWJGL);
         if (libURL == null) {
            if (loadSystemFromLibraryPath(load, context, module, libName, bundledWithLWJGL)) {
               return;
            }
         } else {
            boolean debugLoader = (Boolean)Configuration.DEBUG_LOADER.get(false);

            try {
               label158: {
                  String regular = getRegularFilePath(libURL);
                  if (regular != null) {
                     load.accept(regular);
                     APIUtil.apiLog("\tLoaded from classpath: " + regular);
                     return;
                  }

                  if (debugLoader) {
                     APIUtil.apiLog("\tUsing SharedLibraryLoader...");
                  }

                  try (FileChannel ignored = SharedLibraryLoader.load(name, libName, libURL)) {
                     if (!loadSystemFromLibraryPath(load, context, module, libName, bundledWithLWJGL)) {
                        break label158;
                     }
                  }

                  return;
               }
            } catch (Exception var26) {
               if (debugLoader) {
                  var26.printStackTrace(APIUtil.DEBUG_STREAM);
               }
            }
         }

         String javaLibraryPath = System.getProperty("java.library.path");
         if (!bundledWithLWJGL
            || javaLibraryPath == null
            || !loadSystem(load, context, module, getBundledPath(module, libName), false, "java.library.path", javaLibraryPath)) {
            try {
               loadLibrary.accept(name);
               Path libFile = javaLibraryPath == null ? null : findFile(javaLibraryPath, module, libName, bundledWithLWJGL);
               if (libFile != null) {
                  APIUtil.apiLog(String.format("\tLoaded from %s: %s", "java.library.path", libFile));
                  checkHash(context, libFile);
               } else {
                  APIUtil.apiLog("\tLoaded from a ClassLoader provided path.");
               }
            } catch (Throwable var22) {
               APIUtil.apiLog(String.format("\t%s not found in %s", libName, "java.library.path"));
               printError(true);
               throw new UnsatisfiedLinkError("Failed to locate library: " + libName);
            }
         }
      }
   }

   private static boolean loadSystemFromLibraryPath(Consumer load, Class context, String module, String libName, boolean bundledWithLWJGL) {
      String paths = (String)Configuration.LIBRARY_PATH.get();
      return paths != null && loadSystem(load, context, module, libName, bundledWithLWJGL, Configuration.LIBRARY_PATH.getProperty(), paths);
   }

   private static boolean loadSystem(Consumer load, Class context, String module, String libName, boolean bundledWithLWJGL, String property, String paths) {
      Path libFile = findFile(paths, module, libName, bundledWithLWJGL);
      if (libFile == null) {
         APIUtil.apiLog(String.format("\t%s not found in %s=%s", libName, property, paths));
         return false;
      } else {
         load.accept(libFile.toAbsolutePath().toString());
         APIUtil.apiLog(String.format("\tLoaded from %s: %s", property, libFile));
         checkHash(context, libFile);
         return true;
      }
   }

   public static SharedLibrary loadNative(String module, String name) {
      return loadNative(Library.class, module, name);
   }

   public static SharedLibrary loadNative(Class context, String module, String name) {
      return loadNative(context, module, name, false);
   }

   public static SharedLibrary loadNative(Class context, String module, String name, boolean bundledWithLWJGL) {
      return loadNative(context, module, name, bundledWithLWJGL, true);
   }

   private static SharedLibrary loadNative(Class context, String module, String name, boolean bundledWithLWJGL, boolean printError) {
      APIUtil.apiLog("Loading library: " + name);
      APIUtil.apiLog("\tModule: " + module);
      if (Paths.get(name).isAbsolute()) {
         SharedLibrary lib = APIUtil.apiCreateLibrary(name);
         APIUtil.apiLog("\tSuccess");
         return lib;
      } else {
         String libName = Platform.get().mapLibraryName(name);
         URL libURL = findResource(context, module, libName, bundledWithLWJGL);
         if (libURL == null) {
            SharedLibrary lib = loadNativeFromLibraryPath(context, module, libName, bundledWithLWJGL);
            if (lib != null) {
               return lib;
            }
         } else {
            boolean debugLoader = (Boolean)Configuration.DEBUG_LOADER.get(false);

            try {
               label179: {
                  String regular = getRegularFilePath(libURL);
                  if (regular != null) {
                     SharedLibrary lib = APIUtil.apiCreateLibrary(regular);
                     APIUtil.apiLog("\tLoaded from classpath: " + regular);
                     return lib;
                  }

                  if (debugLoader) {
                     APIUtil.apiLog("\tUsing SharedLibraryLoader...");
                  }

                  SharedLibrary var12;
                  try (FileChannel ignored = SharedLibraryLoader.load(name, libName, libURL)) {
                     SharedLibrary lib = loadNativeFromLibraryPath(context, module, libName, bundledWithLWJGL);
                     if (lib == null) {
                        break label179;
                     }

                     var12 = lib;
                  }

                  return var12;
               }
            } catch (Exception var27) {
               if (debugLoader) {
                  var27.printStackTrace(APIUtil.DEBUG_STREAM);
               }
            }
         }

         if (!bundledWithLWJGL) {
            SharedLibrary lib = loadNativeFromSystem(libName);
            if (lib != null) {
               return lib;
            }
         }

         if ((Boolean)Configuration.EMULATE_SYSTEM_LOADLIBRARY.get(false)) {
            try {
               Method findLibrary = ClassLoader.class.getDeclaredMethod("findLibrary", String.class);
               findLibrary.setAccessible(true);
               String libPath = (String)findLibrary.invoke(context.getClassLoader(), name);
               if (libPath != null) {
                  SharedLibrary lib = APIUtil.apiCreateLibrary(libPath);
                  APIUtil.apiLog(String.format("\tLoaded from ClassLoader provided path: %s", libPath));
                  return lib;
               }
            } catch (Exception var23) {
            }
         }

         String paths = System.getProperty("java.library.path");
         if (paths != null) {
            SharedLibrary lib = loadNative(context, module, libName, bundledWithLWJGL, "java.library.path", paths);
            if (lib != null) {
               return lib;
            }
         }

         if (bundledWithLWJGL) {
            SharedLibrary lib = loadNativeFromSystem(libName);
            if (lib != null) {
               return lib;
            }
         }

         if (printError) {
            printError(bundledWithLWJGL);
         }

         throw new UnsatisfiedLinkError("Failed to locate library: " + libName);
      }
   }

   @Nullable
   private static SharedLibrary loadNativeFromSystem(String libName) {
      SharedLibrary lib;
      try {
         lib = APIUtil.apiCreateLibrary(libName);
         String path = lib.getPath();
         APIUtil.apiLog(path == null ? "\tLoaded from system paths" : "\tLoaded from system paths: " + path);
      } catch (UnsatisfiedLinkError var3) {
         lib = null;
         APIUtil.apiLog(String.format("\t%s not found in system paths", libName));
      }

      return lib;
   }

   @Nullable
   private static SharedLibrary loadNativeFromLibraryPath(Class context, String module, String libName, boolean bundledWithLWJGL) {
      String paths = (String)Configuration.LIBRARY_PATH.get();
      return paths == null ? null : loadNative(context, module, libName, bundledWithLWJGL, Configuration.LIBRARY_PATH.getProperty(), paths);
   }

   @Nullable
   private static SharedLibrary loadNative(Class context, String module, String libName, boolean bundledWithLWJGL, String property, String paths) {
      Path libFile = findFile(paths, module, libName, bundledWithLWJGL);
      if (libFile == null) {
         APIUtil.apiLog(String.format("\t%s not found in %s=%s", libName, property, paths));
         return null;
      } else {
         SharedLibrary lib = APIUtil.apiCreateLibrary(libFile.toAbsolutePath().toString());
         APIUtil.apiLog(String.format("\tLoaded from %s: %s", property, libFile));
         checkHash(context, libFile);
         return lib;
      }
   }

   public static SharedLibrary loadNative(Class context, String module, @Nullable Configuration name, String... defaultNames) {
      return loadNative(context, module, name, null, defaultNames);
   }

   public static SharedLibrary loadNative(Class context, String module, @Nullable Configuration name, @Nullable Supplier fallback, String... defaultNames) {
      if (defaultNames.length == 0) {
         throw new IllegalArgumentException("No default names specified.");
      } else {
         if (name != null) {
            String libraryName = (String)name.get();
            if (libraryName != null) {
               return loadNative(context, module, libraryName);
            }
         }

         if (fallback == null && defaultNames.length <= 1) {
            return loadNative(context, module, defaultNames[0]);
         } else {
            try {
               return loadNative(context, module, defaultNames[0], false, false);
            } catch (Throwable var9) {
               for (int i = 1; i < defaultNames.length; i++) {
                  try {
                     return loadNative(context, module, defaultNames[i], false, fallback == null && i == defaultNames.length - 1);
                  } catch (Throwable var8) {
                  }
               }

               if (fallback != null) {
                  return (SharedLibrary)fallback.get();
               } else {
                  throw var9;
               }
            }
         }
      }
   }

   private static String getBundledPath(String module, String resource) {
      return Platform.mapLibraryPathBundled(module.replace('.', '/') + "/" + resource);
   }

   @Nullable
   static URL findResource(Class context, String module, String resource, boolean bundledWithLWJGL) {
      URL url = null;
      if (bundledWithLWJGL) {
         String bundledResource = getBundledPath(module, resource);
         if (!bundledResource.equals(resource)) {
            url = context.getClassLoader().getResource(bundledResource);
         }
      }

      return url == null ? context.getClassLoader().getResource(resource) : url;
   }

   @Nullable
   static String getRegularFilePath(URL url) {
      if (url.getProtocol().equals("file")) {
         try {
            Path path = Paths.get(url.toURI());
            if (path.isAbsolute() && Files.isReadable(path)) {
               return path.toString();
            }
         } catch (URISyntaxException var2) {
         }
      }

      return null;
   }

   @Nullable
   static Path findFile(String path, String module, String file, boolean bundledWithLWJGL) {
      if (bundledWithLWJGL) {
         String bundledFile = getBundledPath(module, file);
         if (!bundledFile.equals(file)) {
            Path p = findFile(path, bundledFile);
            if (p != null) {
               return p;
            }
         }
      }

      return findFile(path, file);
   }

   @Nullable
   private static Path findFile(String path, String file) {
      for (String directory : PATH_SEPARATOR.split(path)) {
         Path p = Paths.get(directory, file);
         if (Files.isReadable(p)) {
            return p;
         }
      }

      return null;
   }

   private static void printError(boolean bundledWithLWJGL) {
      printError(
         "[LWJGL] Failed to load a library. Possible solutions:\n"
            + (
               bundledWithLWJGL
                  ? "\ta) Add the directory that contains the shared library to -Djava.library.path or -Dorg.lwjgl.librarypath.\n\tb) Add the JAR that contains the shared library to the classpath."
                  : "\ta) Install the library or the driver that provides the library.\n\tb) Ensure that the library is accessible from the system library paths."
            )
      );
   }

   static void printError(String message) {
      APIUtil.DEBUG_STREAM.println(message);
      if (!Checks.DEBUG) {
         APIUtil.DEBUG_STREAM.println("[LWJGL] Enable debug mode with -Dorg.lwjgl.util.Debug=true for better diagnostics.");
         if (!(Boolean)Configuration.DEBUG_LOADER.get(false)) {
            APIUtil.DEBUG_STREAM.println("[LWJGL] Enable the SharedLibraryLoader debug mode with -Dorg.lwjgl.util.DebugLoader=true for better diagnostics.");
         }
      }
   }

   private static void checkHash(Class context, Path libFile) {
      if (Checks.CHECKS) {
         try {
            URL classesURL = null;
            URL nativesURL = null;
            Enumeration resources = context.getClassLoader().getResources(libFile.getFileName() + ".sha1");

            while (resources.hasMoreElements()) {
               URL url = (URL)resources.nextElement();
               if (NATIVES_JAR.matcher(url.toExternalForm()).find()) {
                  nativesURL = url;
               } else {
                  classesURL = url;
               }
            }

            if (classesURL == null) {
               return;
            }

            byte[] expected = getSHA1(classesURL);
            byte[] actual = !Checks.DEBUG && nativesURL != null ? getSHA1(nativesURL) : getSHA1(libFile);
            if (!Arrays.equals(expected, actual)) {
               APIUtil.DEBUG_STREAM
                  .println(
                     "[LWJGL] [ERROR] Incompatible Java and native library versions detected.\nPossible reasons:\n\ta) -Djava.library.path is set to a folder containing shared libraries of an older LWJGL version.\n\tb) The classpath contains jar files of an older LWJGL version.\nPossible solutions:\n\ta) Make sure to not set -Djava.library.path (it is not needed for developing with LWJGL 3) or make\n\t   sure the folder it points to contains the shared libraries of the correct LWJGL version.\n\tb) Check the classpath and make sure to only have jar files of the same LWJGL version in it."
                  );
            }
         } catch (Throwable var7) {
            if (Checks.DEBUG) {
               APIUtil.apiLog("Failed to verify native library.");
               var7.printStackTrace();
            }
         }
      }
   }

   private static byte[] getSHA1(URL hashURL) throws IOException {
      byte[] hash = new byte[20];

      try (InputStream sha1 = hashURL.openStream()) {
         for (int i = 0; i < 20; i++) {
            hash[i] = (byte)(Character.digit(sha1.read(), 16) << 4 | Character.digit(sha1.read(), 16));
         }
      }

      return hash;
   }

   private static byte[] getSHA1(Path libFile) throws NoSuchAlgorithmException, IOException {
      MessageDigest digest = MessageDigest.getInstance("SHA-1");

      try (InputStream input = Files.newInputStream(libFile)) {
         byte[] buffer = new byte[8192];

         int n;
         while ((n = input.read(buffer)) != -1) {
            digest.update(buffer, 0, n);
         }
      }

      return digest.digest();
   }

   static {
      if (Checks.DEBUG) {
         APIUtil.apiLog("Version: " + Version.getVersion());
         APIUtil.apiLog("\t OS: " + System.getProperty("os.name") + " v" + System.getProperty("os.version"));
         APIUtil.apiLog("\tJRE: " + System.getProperty("java.version") + " " + System.getProperty("os.arch"));
         APIUtil.apiLog(
            "\tJVM: " + System.getProperty("java.vm.name") + " v" + System.getProperty("java.vm.version") + " by " + System.getProperty("java.vm.vendor")
         );
      }

      loadSystem("org.lwjgl", JNI_LIBRARY_NAME);
   }
}
