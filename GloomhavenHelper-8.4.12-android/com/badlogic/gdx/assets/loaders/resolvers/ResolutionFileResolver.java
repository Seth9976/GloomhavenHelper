package com.badlogic.gdx.assets.loaders.resolvers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;

public class ResolutionFileResolver implements FileHandleResolver {
    public static class Resolution {
        public final String folder;
        public final int portraitHeight;
        public final int portraitWidth;

        public Resolution(int v, int v1, String s) {
            this.portraitWidth = v;
            this.portraitHeight = v1;
            this.folder = s;
        }
    }

    protected final FileHandleResolver baseResolver;
    protected final Resolution[] descriptors;

    public ResolutionFileResolver(FileHandleResolver fileHandleResolver0, Resolution[] arr_resolutionFileResolver$Resolution) {
        if(arr_resolutionFileResolver$Resolution.length == 0) {
            throw new IllegalArgumentException("At least one Resolution needs to be supplied.");
        }
        this.baseResolver = fileHandleResolver0;
        this.descriptors = arr_resolutionFileResolver$Resolution;
    }

    public static Resolution choose(Resolution[] arr_resolutionFileResolver$Resolution) {
        int v = Gdx.graphics.getBackBufferWidth();
        int v1 = Gdx.graphics.getBackBufferHeight();
        int v2 = 0;
        Resolution resolutionFileResolver$Resolution0 = arr_resolutionFileResolver$Resolution[0];
        if(v < v1) {
            while(v2 < arr_resolutionFileResolver$Resolution.length) {
                Resolution resolutionFileResolver$Resolution1 = arr_resolutionFileResolver$Resolution[v2];
                if(v >= resolutionFileResolver$Resolution1.portraitWidth && resolutionFileResolver$Resolution1.portraitWidth >= resolutionFileResolver$Resolution0.portraitWidth && v1 >= resolutionFileResolver$Resolution1.portraitHeight && resolutionFileResolver$Resolution1.portraitHeight >= resolutionFileResolver$Resolution0.portraitHeight) {
                    resolutionFileResolver$Resolution0 = arr_resolutionFileResolver$Resolution[v2];
                }
                ++v2;
            }
            return resolutionFileResolver$Resolution0;
        }
        while(v2 < arr_resolutionFileResolver$Resolution.length) {
            Resolution resolutionFileResolver$Resolution2 = arr_resolutionFileResolver$Resolution[v2];
            if(v >= resolutionFileResolver$Resolution2.portraitHeight && resolutionFileResolver$Resolution2.portraitHeight >= resolutionFileResolver$Resolution0.portraitHeight && v1 >= resolutionFileResolver$Resolution2.portraitWidth && resolutionFileResolver$Resolution2.portraitWidth >= resolutionFileResolver$Resolution0.portraitWidth) {
                resolutionFileResolver$Resolution0 = arr_resolutionFileResolver$Resolution[v2];
            }
            ++v2;
        }
        return resolutionFileResolver$Resolution0;
    }

    @Override  // com.badlogic.gdx.assets.loaders.FileHandleResolver
    public FileHandle resolve(String s) {
        Resolution resolutionFileResolver$Resolution0 = ResolutionFileResolver.choose(this.descriptors);
        String s1 = this.resolve(new FileHandle(s), resolutionFileResolver$Resolution0.folder);
        FileHandle fileHandle0 = this.baseResolver.resolve(s1);
        return fileHandle0.exists() ? fileHandle0 : this.baseResolver.resolve(s);
    }

    protected String resolve(FileHandle fileHandle0, String s) {
        String s1 = "";
        FileHandle fileHandle1 = fileHandle0.parent();
        if(fileHandle1 != null && !fileHandle1.name().equals("")) {
            s1 = fileHandle1 + "/";
        }
        return s1 + s + "/" + fileHandle0.name();
    }
}

