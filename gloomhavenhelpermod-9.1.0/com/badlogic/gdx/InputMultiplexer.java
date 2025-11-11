package com.badlogic.gdx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class InputMultiplexer implements InputProcessor {
   private SnapshotArray processors = new SnapshotArray(4);

   public InputMultiplexer() {
   }

   public InputMultiplexer(InputProcessor... processors) {
      this.processors.addAll(processors);
   }

   public void addProcessor(int index, InputProcessor processor) {
      if (processor == null) {
         throw new NullPointerException("processor cannot be null");
      } else {
         this.processors.insert(index, processor);
      }
   }

   public void removeProcessor(int index) {
      this.processors.removeIndex(index);
   }

   public void addProcessor(InputProcessor processor) {
      if (processor == null) {
         throw new NullPointerException("processor cannot be null");
      } else {
         this.processors.add(processor);
      }
   }

   public void removeProcessor(InputProcessor processor) {
      this.processors.removeValue(processor, true);
   }

   public int size() {
      return this.processors.size;
   }

   public void clear() {
      this.processors.clear();
   }

   public void setProcessors(InputProcessor... processors) {
      this.processors.clear();
      this.processors.addAll(processors);
   }

   public void setProcessors(Array processors) {
      this.processors.clear();
      this.processors.addAll(processors);
   }

   public SnapshotArray getProcessors() {
      return this.processors;
   }

   @Override
   public boolean keyDown(int keycode) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).keyDown(keycode)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean keyUp(int keycode) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).keyUp(keycode)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean keyTyped(char character) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).keyTyped(character)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).touchDown(screenX, screenY, pointer, button)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).touchUp(screenX, screenY, pointer, button)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).touchDragged(screenX, screenY, pointer)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).mouseMoved(screenX, screenY)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }

   @Override
   public boolean scrolled(float amountX, float amountY) {
      Object[] items = this.processors.begin();

      try {
         int i = 0;

         for (int n = this.processors.size; i < n; i++) {
            if (((InputProcessor)items[i]).scrolled(amountX, amountY)) {
               return true;
            }
         }

         return false;
      } finally {
         this.processors.end();
      }
   }
}
