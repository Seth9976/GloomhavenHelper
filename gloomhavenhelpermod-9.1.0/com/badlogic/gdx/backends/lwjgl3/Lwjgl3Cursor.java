package com.badlogic.gdx.backends.lwjgl3;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWImage;

public class Lwjgl3Cursor implements Cursor {
   static final Array cursors = new Array();
   static final Map systemCursors = new HashMap();
   final Lwjgl3Window window;
   Pixmap pixmapCopy;
   GLFWImage glfwImage;
   final long glfwCursor;

   Lwjgl3Cursor(Lwjgl3Window window, Pixmap pixmap, int xHotspot, int yHotspot) {
      this.window = window;
      if (pixmap.getFormat() != Pixmap.Format.RGBA8888) {
         throw new GdxRuntimeException("Cursor image pixmap is not in RGBA8888 format.");
      } else if ((pixmap.getWidth() & pixmap.getWidth() - 1) != 0) {
         throw new GdxRuntimeException("Cursor image pixmap width of " + pixmap.getWidth() + " is not a power-of-two greater than zero.");
      } else if ((pixmap.getHeight() & pixmap.getHeight() - 1) != 0) {
         throw new GdxRuntimeException("Cursor image pixmap height of " + pixmap.getHeight() + " is not a power-of-two greater than zero.");
      } else if (xHotspot < 0 || xHotspot >= pixmap.getWidth()) {
         throw new GdxRuntimeException("xHotspot coordinate of " + xHotspot + " is not within image width bounds: [0, " + pixmap.getWidth() + ").");
      } else if (yHotspot >= 0 && yHotspot < pixmap.getHeight()) {
         this.pixmapCopy = new Pixmap(pixmap.getWidth(), pixmap.getHeight(), Pixmap.Format.RGBA8888);
         this.pixmapCopy.setBlending(Pixmap.Blending.None);
         this.pixmapCopy.drawPixmap(pixmap, 0, 0);
         this.glfwImage = GLFWImage.malloc();
         this.glfwImage.width(this.pixmapCopy.getWidth());
         this.glfwImage.height(this.pixmapCopy.getHeight());
         this.glfwImage.pixels(this.pixmapCopy.getPixels());
         this.glfwCursor = GLFW.glfwCreateCursor(this.glfwImage, xHotspot, yHotspot);
         cursors.add(this);
      } else {
         throw new GdxRuntimeException("yHotspot coordinate of " + yHotspot + " is not within image height bounds: [0, " + pixmap.getHeight() + ").");
      }
   }

   @Override
   public void dispose() {
      if (this.pixmapCopy == null) {
         throw new GdxRuntimeException("Cursor already disposed");
      } else {
         cursors.removeValue(this, true);
         this.pixmapCopy.dispose();
         this.pixmapCopy = null;
         this.glfwImage.free();
         GLFW.glfwDestroyCursor(this.glfwCursor);
      }
   }

   static void dispose(Lwjgl3Window window) {
      for (int i = cursors.size - 1; i >= 0; i--) {
         Lwjgl3Cursor cursor = (Lwjgl3Cursor)cursors.get(i);
         if (cursor.window.equals(window)) {
            ((Lwjgl3Cursor)cursors.removeIndex(i)).dispose();
         }
      }
   }

   static void disposeSystemCursors() {
      for (long systemCursor : systemCursors.values()) {
         GLFW.glfwDestroyCursor(systemCursor);
      }

      systemCursors.clear();
   }

   static void setSystemCursor(long windowHandle, Cursor.SystemCursor systemCursor) {
      Long glfwCursor = (Long)systemCursors.get(systemCursor);
      if (glfwCursor == null) {
         long handle = 0L;
         if (systemCursor == Cursor.SystemCursor.Arrow) {
            handle = GLFW.glfwCreateStandardCursor(221185);
         } else if (systemCursor == Cursor.SystemCursor.Crosshair) {
            handle = GLFW.glfwCreateStandardCursor(221187);
         } else if (systemCursor == Cursor.SystemCursor.Hand) {
            handle = GLFW.glfwCreateStandardCursor(221188);
         } else if (systemCursor == Cursor.SystemCursor.HorizontalResize) {
            handle = GLFW.glfwCreateStandardCursor(221189);
         } else if (systemCursor == Cursor.SystemCursor.VerticalResize) {
            handle = GLFW.glfwCreateStandardCursor(221190);
         } else {
            if (systemCursor != Cursor.SystemCursor.Ibeam) {
               throw new GdxRuntimeException("Unknown system cursor " + systemCursor);
            }

            handle = GLFW.glfwCreateStandardCursor(221186);
         }

         if (handle == 0L) {
            return;
         }

         glfwCursor = handle;
         systemCursors.put(systemCursor, glfwCursor);
      }

      GLFW.glfwSetCursor(windowHandle, glfwCursor);
   }
}
