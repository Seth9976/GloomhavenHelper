package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class APKExpansionSupport {
    private static final String EXP_PATH = "/Android/obb/";

    static String[] getAPKExpansionFiles(Context context0, int v, int v1) {
        Vector vector0 = new Vector();
        if(Environment.getExternalStorageState().equals("mounted")) {
            File file0 = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + "com.esotericsoftware.gloomhavenhelper");
            if(file0.exists()) {
                if(v > 0) {
                    String s = file0 + "/" + "main." + v + "." + "com.esotericsoftware.gloomhavenhelper" + ".obb";
                    if(new File(s).isFile()) {
                        vector0.add(s);
                    }
                }
                if(v1 > 0) {
                    String s1 = file0 + "/" + "patch." + v1 + "." + "com.esotericsoftware.gloomhavenhelper" + ".obb";
                    if(new File(s1).isFile()) {
                        vector0.add(s1);
                    }
                }
            }
        }
        String[] arr_s = new String[vector0.size()];
        vector0.toArray(arr_s);
        return arr_s;
    }

    public static ZipResourceFile getAPKExpansionZipFile(Context context0, int v, int v1) throws IOException {
        return APKExpansionSupport.getResourceZipFile(APKExpansionSupport.getAPKExpansionFiles(context0, v, v1));
    }

    public static ZipResourceFile getResourceZipFile(String[] arr_s) throws IOException {
        ZipResourceFile zipResourceFile0 = null;
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(zipResourceFile0 == null) {
                zipResourceFile0 = new ZipResourceFile(s);
            }
            else {
                zipResourceFile0.addPatchFile(s);
            }
        }
        return zipResourceFile0;
    }
}

