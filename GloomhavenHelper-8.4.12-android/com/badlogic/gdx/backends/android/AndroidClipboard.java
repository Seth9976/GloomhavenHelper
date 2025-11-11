package com.badlogic.gdx.backends.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import com.badlogic.gdx.utils.Clipboard;

public class AndroidClipboard implements Clipboard {
    private final ClipboardManager clipboard;

    public AndroidClipboard(Context context0) {
        this.clipboard = (ClipboardManager)context0.getSystemService("clipboard");
    }

    @Override  // com.badlogic.gdx.utils.Clipboard
    public String getContents() {
        ClipData clipData0 = this.clipboard.getPrimaryClip();
        if(clipData0 == null) {
            return null;
        }
        CharSequence charSequence0 = clipData0.getItemAt(0).getText();
        return charSequence0 == null ? null : charSequence0.toString();
    }

    @Override  // com.badlogic.gdx.utils.Clipboard
    public boolean hasContents() {
        return this.clipboard.hasPrimaryClip();
    }

    @Override  // com.badlogic.gdx.utils.Clipboard
    public void setContents(String s) {
        ClipData clipData0 = ClipData.newPlainText(s, s);
        this.clipboard.setPrimaryClip(clipData0);
    }
}

