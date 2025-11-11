package com.google.android.gms.dynamite;

import dalvik.system.PathClassLoader;

final class zzh extends PathClassLoader {
    zzh(String s, ClassLoader classLoader0) {
        super(s, classLoader0);
    }

    @Override
    protected final Class loadClass(String s, boolean z) throws ClassNotFoundException {
        if(!s.startsWith("java.") && !s.startsWith("android.")) {
            try {
                return this.findClass(s);
            }
            catch(ClassNotFoundException unused_ex) {
            }
        }
        return super.loadClass(s, z);
    }
}

