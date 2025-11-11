package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Disposable;

public interface Lwjgl3Input extends Input, Disposable {
   void windowHandleChanged(long var1);

   void update();

   void prepareNext();

   void resetPollingStates();
}
