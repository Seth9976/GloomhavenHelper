package androidx.core.util;

import android.util.Log;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.io.Writer;

@RestrictTo({Scope.LIBRARY_GROUP})
public class LogWriter extends Writer {
    private StringBuilder mBuilder;
    private final String mTag;

    public LogWriter(String s) {
        this.mBuilder = new StringBuilder(0x80);
        this.mTag = s;
    }

    @Override
    public void close() {
        this.flushBuilder();
    }

    @Override
    public void flush() {
        this.flushBuilder();
    }

    private void flushBuilder() {
        if(this.mBuilder.length() > 0) {
            Log.d(this.mTag, this.mBuilder.toString());
            this.mBuilder.delete(0, this.mBuilder.length());
        }
    }

    @Override
    public void write(char[] arr_c, int v, int v1) {
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = arr_c[v + v2];
            if(v3 == 10) {
                this.flushBuilder();
            }
            else {
                this.mBuilder.append(((char)v3));
            }
        }
    }
}

