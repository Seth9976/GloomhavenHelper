package com.badlogic.gdx;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;

public class InputEventQueue {
   private static final int SKIP = -1;
   private static final int KEY_DOWN = 0;
   private static final int KEY_UP = 1;
   private static final int KEY_TYPED = 2;
   private static final int TOUCH_DOWN = 3;
   private static final int TOUCH_UP = 4;
   private static final int TOUCH_DRAGGED = 5;
   private static final int MOUSE_MOVED = 6;
   private static final int SCROLLED = 7;
   private final IntArray queue = new IntArray();
   private final IntArray processingQueue = new IntArray();
   private long currentEventTime;

   public void drain(@Null InputProcessor processor) {
      synchronized (this) {
         if (processor == null) {
            this.queue.clear();
            return;
         }

         this.processingQueue.addAll(this.queue);
         this.queue.clear();
      }

      int[] q = this.processingQueue.items;
      int i = 0;
      int n = this.processingQueue.size;

      while (i < n) {
         int type = q[i++];
         this.currentEventTime = (long)q[i++] << 32 | q[i++] & 4294967295L;
         switch (type) {
            case -1:
               i += q[i];
               break;
            case 0:
               processor.keyDown(q[i++]);
               break;
            case 1:
               processor.keyUp(q[i++]);
               break;
            case 2:
               processor.keyTyped((char)q[i++]);
               break;
            case 3:
               processor.touchDown(q[i++], q[i++], q[i++], q[i++]);
               break;
            case 4:
               processor.touchUp(q[i++], q[i++], q[i++], q[i++]);
               break;
            case 5:
               processor.touchDragged(q[i++], q[i++], q[i++]);
               break;
            case 6:
               processor.mouseMoved(q[i++], q[i++]);
               break;
            case 7:
               processor.scrolled(NumberUtils.intBitsToFloat(q[i++]), NumberUtils.intBitsToFloat(q[i++]));
               break;
            default:
               throw new RuntimeException();
         }
      }

      this.processingQueue.clear();
   }

   private synchronized int next(int nextType, int i) {
      int[] q = this.queue.items;
      int n = this.queue.size;

      while (i < n) {
         int type = q[i];
         if (type == nextType) {
            return i;
         }

         i += 3;
         switch (type) {
            case -1:
               i += q[i];
               break;
            case 0:
               i++;
               break;
            case 1:
               i++;
               break;
            case 2:
               i++;
               break;
            case 3:
               i += 4;
               break;
            case 4:
               i += 4;
               break;
            case 5:
               i += 3;
               break;
            case 6:
               i += 2;
               break;
            case 7:
               i += 2;
               break;
            default:
               throw new RuntimeException();
         }
      }

      return -1;
   }

   private void queueTime(long time) {
      this.queue.add((int)(time >> 32));
      this.queue.add((int)time);
   }

   public synchronized boolean keyDown(int keycode, long time) {
      this.queue.add(0);
      this.queueTime(time);
      this.queue.add(keycode);
      return false;
   }

   public synchronized boolean keyUp(int keycode, long time) {
      this.queue.add(1);
      this.queueTime(time);
      this.queue.add(keycode);
      return false;
   }

   public synchronized boolean keyTyped(char character, long time) {
      this.queue.add(2);
      this.queueTime(time);
      this.queue.add(character);
      return false;
   }

   public synchronized boolean touchDown(int screenX, int screenY, int pointer, int button, long time) {
      this.queue.add(3);
      this.queueTime(time);
      this.queue.add(screenX);
      this.queue.add(screenY);
      this.queue.add(pointer);
      this.queue.add(button);
      return false;
   }

   public synchronized boolean touchUp(int screenX, int screenY, int pointer, int button, long time) {
      this.queue.add(4);
      this.queueTime(time);
      this.queue.add(screenX);
      this.queue.add(screenY);
      this.queue.add(pointer);
      this.queue.add(button);
      return false;
   }

   public synchronized boolean touchDragged(int screenX, int screenY, int pointer, long time) {
      for (int i = this.next(5, 0); i >= 0; i = this.next(5, i + 6)) {
         if (this.queue.get(i + 5) == pointer) {
            this.queue.set(i, -1);
            this.queue.set(i + 3, 3);
         }
      }

      this.queue.add(5);
      this.queueTime(time);
      this.queue.add(screenX);
      this.queue.add(screenY);
      this.queue.add(pointer);
      return false;
   }

   public synchronized boolean mouseMoved(int screenX, int screenY, long time) {
      for (int i = this.next(6, 0); i >= 0; i = this.next(6, i + 5)) {
         this.queue.set(i, -1);
         this.queue.set(i + 3, 2);
      }

      this.queue.add(6);
      this.queueTime(time);
      this.queue.add(screenX);
      this.queue.add(screenY);
      return false;
   }

   public synchronized boolean scrolled(float amountX, float amountY, long time) {
      this.queue.add(7);
      this.queueTime(time);
      this.queue.add(NumberUtils.floatToIntBits(amountX));
      this.queue.add(NumberUtils.floatToIntBits(amountY));
      return false;
   }

   public long getCurrentEventTime() {
      return this.currentEventTime;
   }
}
