package com.badlogic.gdx.backends.lwjgl3;

public interface Lwjgl3WindowListener {
   void created(Lwjgl3Window var1);

   void iconified(boolean var1);

   void maximized(boolean var1);

   void focusLost();

   void focusGained();

   boolean closeRequested();

   void filesDropped(String[] var1);

   void refreshRequested();
}
