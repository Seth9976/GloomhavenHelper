package com.badlogic.gdx.files;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel.MapMode;

public class FileHandle {
    protected File file;
    protected FileType type;

    protected FileHandle() {
    }

    public FileHandle(File file0) {
        this.file = file0;
        this.type = FileType.Absolute;
    }

    protected FileHandle(File file0, FileType files$FileType0) {
        this.file = file0;
        this.type = files$FileType0;
    }

    public FileHandle(String s) {
        this.file = new File(s);
        this.type = FileType.Absolute;
    }

    protected FileHandle(String s, FileType files$FileType0) {
        this.type = files$FileType0;
        this.file = new File(s);
    }

    public FileHandle child(String s) {
        return this.file.getPath().length() == 0 ? new FileHandle(new File(s), this.type) : new FileHandle(new File(this.file, s), this.type);
    }

    private static void copyDirectory(FileHandle fileHandle0, FileHandle fileHandle1) {
        fileHandle1.mkdirs();
        FileHandle[] arr_fileHandle = fileHandle0.list();
        for(int v = 0; v < arr_fileHandle.length; ++v) {
            FileHandle fileHandle2 = arr_fileHandle[v];
            FileHandle fileHandle3 = fileHandle1.child(fileHandle2.name());
            if(fileHandle2.isDirectory()) {
                FileHandle.copyDirectory(fileHandle2, fileHandle3);
            }
            else {
                FileHandle.copyFile(fileHandle2, fileHandle3);
            }
        }
    }

    private static void copyFile(FileHandle fileHandle0, FileHandle fileHandle1) {
        try {
            fileHandle1.write(fileHandle0.read(), false);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error copying source file: " + fileHandle0.file + " (" + fileHandle0.type + ")\nTo destination: " + fileHandle1.file + " (" + fileHandle1.type + ")", exception0);
        }
    }

    public void copyTo(FileHandle fileHandle0) {
        if(!this.isDirectory()) {
            if(fileHandle0.isDirectory()) {
                fileHandle0 = fileHandle0.child(this.name());
            }
            FileHandle.copyFile(this, fileHandle0);
            return;
        }
        if(!fileHandle0.exists()) {
            fileHandle0.mkdirs();
            if(!fileHandle0.isDirectory()) {
                throw new GdxRuntimeException("Destination directory cannot be created: " + fileHandle0);
            }
        }
        else if(!fileHandle0.isDirectory()) {
            throw new GdxRuntimeException("Destination exists but is not a directory: " + fileHandle0);
        }
        FileHandle.copyDirectory(this, fileHandle0.child(this.name()));
    }

    public boolean delete() {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if(this.type == FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        return this.file().delete();
    }

    private static boolean deleteDirectory(File file0) {
        FileHandle.emptyDirectory(file0, false);
        return file0.delete();
    }

    public boolean deleteDirectory() {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if(this.type == FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        return FileHandle.deleteDirectory(this.file());
    }

    private static void emptyDirectory(File file0, boolean z) {
        if(file0.exists()) {
            File[] arr_file = file0.listFiles();
            if(arr_file != null) {
                for(int v = 0; v < arr_file.length; ++v) {
                    if(!arr_file[v].isDirectory()) {
                        arr_file[v].delete();
                    }
                    else if(z) {
                        FileHandle.emptyDirectory(arr_file[v], true);
                    }
                    else {
                        FileHandle.deleteDirectory(arr_file[v]);
                    }
                }
            }
        }
    }

    public void emptyDirectory() {
        this.emptyDirectory(false);
    }

    public void emptyDirectory(boolean z) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot delete a classpath file: " + this.file);
        }
        if(this.type == FileType.Internal) {
            throw new GdxRuntimeException("Cannot delete an internal file: " + this.file);
        }
        FileHandle.emptyDirectory(this.file(), z);
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof FileHandle ? this.type == ((FileHandle)object0).type && this.path().equals(((FileHandle)object0).path()) : false;
    }

    private int estimateLength() {
        int v = (int)this.length();
        return v == 0 ? 0x200 : v;
    }

    public boolean exists() {
        switch(this.type) {
            case Internal: {
                return this.file().exists() ? true : FileHandle.class.getResource("/" + this.file.getPath().replace('\\', '/')) != null;
            }
            case Classpath: {
                return FileHandle.class.getResource("/" + this.file.getPath().replace('\\', '/')) != null;
            }
            default: {
                return this.file().exists();
            }
        }
    }

    public String extension() {
        String s = this.file.getName();
        int v = s.lastIndexOf(46);
        return v == -1 ? "" : s.substring(v + 1);
    }

    public File file() {
        return this.type == FileType.External ? new File(Gdx.files.getExternalStoragePath(), this.file.getPath()) : this.file;
    }

    @Override
    public int hashCode() {
        return (this.type.hashCode() + 37) * 67 + this.path().hashCode();
    }

    public boolean isDirectory() {
        return this.type == FileType.Classpath ? false : this.file().isDirectory();
    }

    public long lastModified() {
        return this.file().lastModified();
    }

    public long length() {
        int v1;
        if(this.type != FileType.Classpath && (this.type != FileType.Internal || this.file.exists())) {
            return this.file().length();
        }
        InputStream inputStream0 = this.read();
        try {
            v1 = inputStream0.available();
        }
        catch(Exception unused_ex) {
            return 0L;
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
        }
        return (long)v1;
    }

    public FileHandle[] list() {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] arr_s = this.file().list();
        if(arr_s == null) {
            return new FileHandle[0];
        }
        FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            arr_fileHandle[v] = this.child(arr_s[v]);
        }
        return arr_fileHandle;
    }

    public FileHandle[] list(FileFilter fileFilter0) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] arr_s = this.file().list();
        if(arr_s == null) {
            return new FileHandle[0];
        }
        FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            FileHandle fileHandle0 = this.child(arr_s[v]);
            if(fileFilter0.accept(fileHandle0.file())) {
                arr_fileHandle[v1] = fileHandle0;
                ++v1;
            }
        }
        if(v1 < arr_s.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    public FileHandle[] list(FilenameFilter filenameFilter0) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        File file0 = this.file();
        String[] arr_s = file0.list();
        if(arr_s == null) {
            return new FileHandle[0];
        }
        FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(filenameFilter0.accept(file0, s)) {
                arr_fileHandle[v1] = this.child(s);
                ++v1;
            }
        }
        if(v1 < arr_s.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    public FileHandle[] list(String s) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot list a classpath directory: " + this.file);
        }
        String[] arr_s = this.file().list();
        if(arr_s == null) {
            return new FileHandle[0];
        }
        FileHandle[] arr_fileHandle = new FileHandle[arr_s.length];
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            String s1 = arr_s[v];
            if(s1.endsWith(s)) {
                arr_fileHandle[v1] = this.child(s1);
                ++v1;
            }
        }
        if(v1 < arr_s.length) {
            FileHandle[] arr_fileHandle1 = new FileHandle[v1];
            System.arraycopy(arr_fileHandle, 0, arr_fileHandle1, 0, v1);
            return arr_fileHandle1;
        }
        return arr_fileHandle;
    }

    public ByteBuffer map() {
        return this.map(FileChannel.MapMode.READ_ONLY);
    }

    public ByteBuffer map(FileChannel.MapMode fileChannel$MapMode0) {
        ByteBuffer byteBuffer0;
        RandomAccessFile randomAccessFile0;
        if(this.type != FileType.Classpath) {
            try {
                randomAccessFile0 = new RandomAccessFile(this.file, (fileChannel$MapMode0 == FileChannel.MapMode.READ_ONLY ? "r" : "rw"));
                byteBuffer0 = randomAccessFile0.getChannel().map(fileChannel$MapMode0, 0L, this.file.length());
                byteBuffer0.order(ByteOrder.nativeOrder());
                return byteBuffer0;
            }
            catch(Exception exception0) {
                throw new GdxRuntimeException("Error memory mapping file: " + this + " (" + this.type + ")", exception0);
            }
            finally {
                StreamUtils.closeQuietly(randomAccessFile0);
            }
        }
        throw new GdxRuntimeException("Cannot map a classpath file: " + this);
    }

    public void mkdirs() {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot mkdirs with a classpath file: " + this.file);
        }
        if(this.type == FileType.Internal) {
            throw new GdxRuntimeException("Cannot mkdirs with an internal file: " + this.file);
        }
        this.file().mkdirs();
    }

    public void moveTo(FileHandle fileHandle0) {
        switch(this.type) {
            case Internal: {
                throw new GdxRuntimeException("Cannot move an internal file: " + this.file);
            }
            case Classpath: {
                throw new GdxRuntimeException("Cannot move a classpath file: " + this.file);
            }
            case Absolute: 
            case External: {
                if(this.file().renameTo(fileHandle0.file())) {
                    return;
                }
            }
        }
        this.copyTo(fileHandle0);
        this.delete();
        if(this.exists() && this.isDirectory()) {
            this.deleteDirectory();
        }
    }

    public String name() {
        return this.file.getName();
    }

    public String nameWithoutExtension() {
        String s = this.file.getName();
        int v = s.lastIndexOf(46);
        return v == -1 ? s : s.substring(0, v);
    }

    public FileHandle parent() {
        File file0 = this.file.getParentFile();
        if(file0 == null) {
            if(this.type == FileType.Absolute) {
                return new FileHandle(new File("/"), this.type);
            }
            file0 = new File("");
        }
        return new FileHandle(file0, this.type);
    }

    public String path() {
        return this.file.getPath().replace('\\', '/');
    }

    public String pathWithoutExtension() {
        String s = this.file.getPath().replace('\\', '/');
        int v = s.lastIndexOf(46);
        return v == -1 ? s : s.substring(0, v);
    }

    public BufferedInputStream read(int v) {
        return new BufferedInputStream(this.read(), v);
    }

    public InputStream read() {
        if(this.type != FileType.Classpath && (this.type != FileType.Internal || this.file().exists()) && (this.type != FileType.Local || this.file().exists())) {
            try {
                return new FileInputStream(this.file());
            }
            catch(Exception exception0) {
                throw this.file().isDirectory() ? new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", exception0) : new GdxRuntimeException("Error reading file: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        InputStream inputStream0 = FileHandle.class.getResourceAsStream("/" + this.file.getPath().replace('\\', '/'));
        if(inputStream0 == null) {
            throw new GdxRuntimeException("File not found: " + this.file + " (" + this.type + ")");
        }
        return inputStream0;
    }

    public int readBytes(byte[] arr_b, int v, int v1) {
        InputStream inputStream0 = this.read();
        int v2 = 0;
        try {
            int v4;
            while((v4 = inputStream0.read(arr_b, v + v2, v1 - v2)) > 0) {
                v2 += v4;
            }
            return v2 - v;
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading file: " + this, iOException0);
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
        }
    }

    public byte[] readBytes() {
        InputStream inputStream0 = this.read();
        try {
            return StreamUtils.copyStreamToByteArray(inputStream0, this.estimateLength());
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error reading file: " + this, iOException0);
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
        }
    }

    public String readString() {
        return this.readString(null);
    }

    public String readString(String s) {
        InputStreamReader inputStreamReader0;
        StringBuilder stringBuilder0 = new StringBuilder(this.estimateLength());
        try {
            try {
                inputStreamReader0 = s == null ? new InputStreamReader(this.read()) : new InputStreamReader(this.read(), s);
                char[] arr_c = new char[0x100];
                int v1;
                while((v1 = inputStreamReader0.read(arr_c)) != -1) {
                    stringBuilder0.append(arr_c, 0, v1);
                }
            }
            catch(IOException iOException0) {
                throw new GdxRuntimeException("Error reading layout file: " + this, iOException0);
            }
        }
        finally {
            StreamUtils.closeQuietly(inputStreamReader0);
        }
        return stringBuilder0.toString();
    }

    public BufferedReader reader(int v) {
        return new BufferedReader(new InputStreamReader(this.read()), v);
    }

    public BufferedReader reader(int v, String s) {
        try {
            return new BufferedReader(new InputStreamReader(this.read(), s), v);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new GdxRuntimeException("Error reading file: " + this, unsupportedEncodingException0);
        }
    }

    public Reader reader() {
        return new InputStreamReader(this.read());
    }

    public Reader reader(String s) {
        InputStream inputStream0 = this.read();
        try {
            return new InputStreamReader(inputStream0, s);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            StreamUtils.closeQuietly(inputStream0);
            throw new GdxRuntimeException("Error reading file: " + this, unsupportedEncodingException0);
        }
    }

    public FileHandle sibling(String s) {
        if(this.file.getPath().length() == 0) {
            throw new GdxRuntimeException("Cannot get the sibling of the root.");
        }
        return new FileHandle(new File(this.file.getParent(), s), this.type);
    }

    public static FileHandle tempDirectory(String s) {
        try {
            File file0 = File.createTempFile(s, null);
            if(!file0.delete()) {
                throw new IOException("Unable to delete temp file: " + file0);
            }
            if(!file0.mkdir()) {
                throw new IOException("Unable to create temp directory: " + file0);
            }
            return new FileHandle(file0);
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Unable to create temp file.", iOException0);
        }
    }

    public static FileHandle tempFile(String s) {
        try {
            return new FileHandle(File.createTempFile(s, null));
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Unable to create temp file.", iOException0);
        }
    }

    @Override
    public String toString() {
        return this.file.getPath().replace('\\', '/');
    }

    public FileType type() {
        return this.type;
    }

    public OutputStream write(boolean z) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot write to a classpath file: " + this.file);
        }
        if(this.type != FileType.Internal) {
            this.parent().mkdirs();
            try {
                return new FileOutputStream(this.file(), z);
            }
            catch(Exception exception0) {
                throw this.file().isDirectory() ? new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", exception0) : new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", exception0);
            }
        }
        throw new GdxRuntimeException("Cannot write to an internal file: " + this.file);
    }

    public OutputStream write(boolean z, int v) {
        return new BufferedOutputStream(this.write(z), v);
    }

    public void write(InputStream inputStream0, boolean z) {
        OutputStream outputStream0;
        try {
            outputStream0 = this.write(z);
            StreamUtils.copyStream(inputStream0, outputStream0);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error stream writing to file: " + this.file + " (" + this.type + ")", exception0);
        }
        finally {
            StreamUtils.closeQuietly(inputStream0);
            StreamUtils.closeQuietly(outputStream0);
        }
    }

    public void writeBytes(byte[] arr_b, int v, int v1, boolean z) {
        OutputStream outputStream0 = this.write(z);
        try {
            outputStream0.write(arr_b, v, v1);
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", iOException0);
        }
        finally {
            StreamUtils.closeQuietly(outputStream0);
        }
    }

    public void writeBytes(byte[] arr_b, boolean z) {
        OutputStream outputStream0 = this.write(z);
        try {
            outputStream0.write(arr_b);
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", iOException0);
        }
        finally {
            StreamUtils.closeQuietly(outputStream0);
        }
    }

    public void writeString(String s, boolean z) {
        this.writeString(s, z, null);
    }

    public void writeString(String s, boolean z, String s1) {
        Writer writer0;
        try {
            writer0 = this.writer(z, s1);
            writer0.write(s);
        }
        catch(Exception exception0) {
            throw new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", exception0);
        }
        finally {
            StreamUtils.closeQuietly(writer0);
        }
    }

    public Writer writer(boolean z) {
        return this.writer(z, null);
    }

    public Writer writer(boolean z, String s) {
        if(this.type == FileType.Classpath) {
            throw new GdxRuntimeException("Cannot write to a classpath file: " + this.file);
        }
        if(this.type != FileType.Internal) {
            this.parent().mkdirs();
            try {
                FileOutputStream fileOutputStream0 = new FileOutputStream(this.file(), z);
                return s == null ? new OutputStreamWriter(fileOutputStream0) : new OutputStreamWriter(fileOutputStream0, s);
            }
            catch(IOException iOException0) {
                throw this.file().isDirectory() ? new GdxRuntimeException("Cannot open a stream to a directory: " + this.file + " (" + this.type + ")", iOException0) : new GdxRuntimeException("Error writing file: " + this.file + " (" + this.type + ")", iOException0);
            }
        }
        throw new GdxRuntimeException("Cannot write to an internal file: " + this.file);
    }
}

