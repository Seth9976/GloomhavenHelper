package com.badlogic.gdx.utils;

import com.badlogic.gdx.math.MathUtils;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.UUID;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jeb.synthetic.FIN;

public class SharedLibraryLoader {
    public static boolean is64Bit;
    public static boolean isARM;
    public static boolean isAndroid;
    public static boolean isIos;
    public static boolean isLinux;
    public static boolean isMac;
    public static boolean isWindows;
    private static final HashSet loadedLibraries;
    private String nativesJar;

    // 去混淆评级： 低(36)
    static {
        SharedLibraryLoader.isIos = false;
        SharedLibraryLoader.isARM = true;
        SharedLibraryLoader.isAndroid = true;
        SharedLibraryLoader.isWindows = false;
        SharedLibraryLoader.isLinux = false;
        SharedLibraryLoader.isMac = false;
        SharedLibraryLoader.is64Bit = false;
        SharedLibraryLoader.loadedLibraries = new HashSet();
    }

    public SharedLibraryLoader() {
    }

    public SharedLibraryLoader(String s) {
        this.nativesJar = s;
    }

    private boolean canExecute(File file0) {
        try {
            if(file0.canExecute()) {
                return true;
            }
            file0.setExecutable(true, false);
            return file0.canExecute();
        }
        catch(Exception unused_ex) {
            return false;
        }
    }

    private boolean canWrite(File file0) {
        File file1 = file0.getParentFile();
        if(file0.exists()) {
            if(file0.canWrite() && this.canExecute(file0)) {
                file0 = new File(file1, SharedLibraryLoader.randomUUID());
                goto label_9;
            }
            return false;
        }
        else {
            file1.mkdirs();
            if(!file1.isDirectory()) {
                return false;
            }
        }
        try {
        label_9:
            new FileOutputStream(file0).close();
            if(!this.canExecute(file0)) {
                goto label_14;
            }
            goto label_16;
        }
        catch(Throwable unused_ex) {
            file0.delete();
            return false;
        }
    label_14:
        file0.delete();
        return false;
    label_16:
        file0.delete();
        return true;
    }

    public String crc(InputStream inputStream0) {
        if(inputStream0 == null) {
            throw new IllegalArgumentException("input cannot be null.");
        }
        CRC32 cRC320 = new CRC32();
        byte[] arr_b = new byte[0x1000];
        try {
            int v1;
            while((v1 = inputStream0.read(arr_b)) != -1) {
                cRC320.update(arr_b, 0, v1);
            }
        }
        catch(Exception unused_ex) {
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
        }
        return Long.toString(cRC320.getValue(), 16);
    }

    private File extractFile(String s, String s1, File file0) throws IOException {
        FileOutputStream fileOutputStream0;
        Closeable closeable0;
        IOException iOException1;
        InputStream inputStream1;
        InputStream inputStream0 = null;
        String s2 = null;
        if(file0.exists()) {
            try {
                s2 = this.crc(new FileInputStream(file0));
            }
            catch(FileNotFoundException unused_ex) {
            }
        }
        if(s2 == null || !s2.equals(s1)) {
            try {
                inputStream1 = this.readFile(s);
            }
            catch(IOException iOException0) {
                iOException1 = iOException0;
                closeable0 = null;
                throw new GdxRuntimeException("Error extracting file: " + s + "\nTo: " + file0.getAbsolutePath(), iOException1);
            }
            catch(Throwable throwable0) {
                closeable0 = null;
                StreamUtils.closeQuietly(inputStream0);
                StreamUtils.closeQuietly(closeable0);
                throw throwable0;
            }
            try {
                file0.getParentFile().mkdirs();
                fileOutputStream0 = new FileOutputStream(file0);
            }
            catch(IOException iOException1) {
                inputStream0 = inputStream1;
                closeable0 = null;
                throw new GdxRuntimeException("Error extracting file: " + s + "\nTo: " + file0.getAbsolutePath(), iOException1);
            }
            catch(Throwable throwable0) {
                inputStream0 = inputStream1;
                closeable0 = null;
                StreamUtils.closeQuietly(inputStream0);
                StreamUtils.closeQuietly(closeable0);
                throw throwable0;
            }
            try {
                byte[] arr_b = new byte[0x1000];
                while(true) {
                    int v = inputStream1.read(arr_b);
                    if(v == -1) {
                        goto label_43;
                    }
                    fileOutputStream0.write(arr_b, 0, v);
                }
            }
            catch(IOException iOException2) {
                inputStream0 = inputStream1;
                closeable0 = fileOutputStream0;
                iOException1 = iOException2;
                try {
                    throw new GdxRuntimeException("Error extracting file: " + s + "\nTo: " + file0.getAbsolutePath(), iOException1);
                }
                catch(Throwable throwable0) {
                }
            }
            catch(Throwable throwable0) {
                inputStream0 = inputStream1;
                closeable0 = fileOutputStream0;
            }
            StreamUtils.closeQuietly(inputStream0);
            StreamUtils.closeQuietly(closeable0);
            throw throwable0;
        label_43:
            StreamUtils.closeQuietly(inputStream1);
            StreamUtils.closeQuietly(fileOutputStream0);
        }
        return file0;
    }

    public File extractFile(String s, String s1) throws IOException {
        try {
            String s2 = this.crc(this.readFile(s));
            if(s1 == null) {
                s1 = s2;
            }
            File file0 = this.getExtractedFile(s1, new File(s).getName());
            if(file0 == null) {
                file0 = this.getExtractedFile(SharedLibraryLoader.randomUUID().toString(), new File(s).getName());
                if(file0 == null) {
                    throw new GdxRuntimeException("Unable to find writable path to extract file. Is the user home directory writable?");
                }
            }
            return this.extractFile(s, s2, file0);
        }
        catch(RuntimeException runtimeException0) {
            File file1 = new File("/system/lib64:/system_ext/lib64", s);
            if(!file1.exists()) {
                throw runtimeException0;
            }
            return file1;
        }
    }

    public void extractFileTo(String s, File file0) throws IOException {
        this.extractFile(s, this.crc(this.readFile(s)), new File(file0, new File(s).getName()));
    }

    @Null
    private File getExtractedFile(String s, String s1) {
        File file0 = new File("/data/user/0/com.esotericsoftware.gloomhavenhelper/cache/libgdxroot/" + s, s1);
        if(this.canWrite(file0)) {
            return file0;
        }
        try {
            File file1 = File.createTempFile(s, null);
            if(file1.delete()) {
                File file2 = new File(file1, s1);
                if(this.canWrite(file2)) {
                    return file2;
                }
            }
        }
        catch(IOException unused_ex) {
        }
        File file3 = new File("/.libgdx/" + s, s1);
        if(this.canWrite(file3)) {
            return file3;
        }
        File file4 = new File(".temp/" + s, s1);
        if(this.canWrite(file4)) {
            return file4;
        }
        return System.getenv("APP_SANDBOX_CONTAINER_ID") == null ? null : file0;
    }

    public static boolean isLoaded(String s) {
        synchronized(SharedLibraryLoader.class) {
            return SharedLibraryLoader.loadedLibraries.contains(s);
        }
    }

    public void load(String s) {
        Class class0 = SharedLibraryLoader.class;
        __monitor_enter(class0);
        int v = FIN.finallyOpen$NT();
        if(SharedLibraryLoader.isLoaded(s)) {
            FIN.finallyCodeBegin$NT(v);
            __monitor_exit(class0);
            FIN.finallyCodeEnd$NT(v);
            return;
        }
        String s1 = this.mapLibraryName(s);
        try {
            System.loadLibrary(s1);
            SharedLibraryLoader.setLoaded(s);
            FIN.finallyExec$NT(v);
        }
        catch(Throwable throwable0) {
            FIN.finallyExec$NT(v);
            throw new GdxRuntimeException("Couldn\'t load shared library \'" + s1 + "\' for target: " + "Linux" + ", 32-bit", throwable0);
        }
    }

    @Null
    private Throwable loadFile(String s, String s1, File file0) {
        try {
            System.load(this.extractFile(s, s1, file0).getAbsolutePath());
            return null;
        }
        catch(Throwable throwable0) {
            return throwable0;
        }
    }

    private void loadFile(String s) {
        String s1 = this.crc(this.readFile(s));
        String s2 = new File(s).getName();
        Throwable throwable0 = this.loadFile(s, s1, new File("/data/user/0/com.esotericsoftware.gloomhavenhelper/cache/libgdxroot/" + s1, s2));
        if(throwable0 == null) {
            return;
        }
        try {
            File file0 = File.createTempFile(s1, null);
            if(file0.delete() && this.loadFile(s, s1, file0) == null) {
                return;
            }
        }
        catch(Throwable unused_ex) {
        }
        if(this.loadFile(s, s1, new File("/.libgdx/" + s1, s2)) == null) {
            return;
        }
        if(this.loadFile(s, s1, new File(".temp/" + s1, s2)) == null) {
            return;
        }
        File file1 = new File("/system/lib64:/system_ext/lib64", s);
        if(!file1.exists()) {
            throw new GdxRuntimeException(throwable0);
        }
        System.load(file1.getAbsolutePath());
    }

    public String mapLibraryName(String s) {
        return s;
    }

    static String randomUUID() {
        return new UUID(MathUtils.random.nextLong(), MathUtils.random.nextLong()).toString();
    }

    private InputStream readFile(String s) {
        String s1 = this.nativesJar;
        if(s1 == null) {
            InputStream inputStream0 = SharedLibraryLoader.class.getResourceAsStream("/" + s);
            if(inputStream0 == null) {
                throw new GdxRuntimeException("Unable to read file for extraction: " + s);
            }
            return inputStream0;
        }
        try {
            ZipFile zipFile0 = new ZipFile(s1);
            ZipEntry zipEntry0 = zipFile0.getEntry(s);
            if(zipEntry0 == null) {
                throw new GdxRuntimeException("Couldn\'t find \'" + s + "\' in JAR: " + this.nativesJar);
            }
            return zipFile0.getInputStream(zipEntry0);
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading \'" + s + "\' in JAR: " + this.nativesJar, iOException0);
        }
    }

    public static void setLoaded(String s) {
        synchronized(SharedLibraryLoader.class) {
            SharedLibraryLoader.loadedLibraries.add(s);
        }
    }
}

