package com.badlogic.gdx.backends.android;

import android.view.View.OnGenericMotionListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import com.badlogic.gdx.Input;

public interface AndroidInput extends View.OnGenericMotionListener, View.OnKeyListener, View.OnTouchListener, Input {
    void addGenericMotionListener(View.OnGenericMotionListener arg1);

    void addKeyListener(View.OnKeyListener arg1);

    void onDreamingStarted();

    void onDreamingStopped();

    void onPause();

    void onResume();

    void processEvents();

    void setKeyboardAvailable(boolean arg1);
}

