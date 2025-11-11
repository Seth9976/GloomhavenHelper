package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzez;

    @KeepForSdk
    public CursorWrapper(Cursor cursor0) {
        super(cursor0);
        for(int v = 0; v < 10 && cursor0 instanceof android.database.CursorWrapper; ++v) {
            cursor0 = ((android.database.CursorWrapper)cursor0).getWrappedCursor();
        }
        if(!(cursor0 instanceof AbstractWindowedCursor)) {
            String s = cursor0.getClass().getName();
            throw new IllegalArgumentException((s.length() == 0 ? new String("Unknown type: ") : "Unknown type: " + s));
        }
        this.zzez = (AbstractWindowedCursor)cursor0;
    }

    @Override  // android.database.CrossProcessCursor
    @KeepForSdk
    public void fillWindow(int v, CursorWindow cursorWindow0) {
        this.zzez.fillWindow(v, cursorWindow0);
    }

    @Override  // android.database.CrossProcessCursor
    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }

    @Override  // android.database.CursorWrapper
    public Cursor getWrappedCursor() {
        return this.zzez;
    }

    @Override  // android.database.CrossProcessCursor
    public boolean onMove(int v, int v1) {
        return this.zzez.onMove(v, v1);
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow0) {
        this.zzez.setWindow(cursorWindow0);
    }
}

