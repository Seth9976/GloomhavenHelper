package com.badlogic.gdx;

import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.NumberUtils;

public class InputEventQueue {
    private static final int KEY_DOWN = 0;
    private static final int KEY_TYPED = 2;
    private static final int KEY_UP = 1;
    private static final int MOUSE_MOVED = 6;
    private static final int SCROLLED = 7;
    private static final int SKIP = -1;
    private static final int TOUCH_DOWN = 3;
    private static final int TOUCH_DRAGGED = 5;
    private static final int TOUCH_UP = 4;
    private long currentEventTime;
    private final IntArray processingQueue;
    private final IntArray queue;

    public InputEventQueue() {
        this.queue = new IntArray();
        this.processingQueue = new IntArray();
    }

    public void drain(@Null InputProcessor inputProcessor0) {
        synchronized(this) {
            if(inputProcessor0 == null) {
                this.queue.clear();
                return;
            }
            this.processingQueue.addAll(this.queue);
            this.queue.clear();
        }
        int[] arr_v = this.processingQueue.items;
        int v1 = 0;
        int v2 = this.processingQueue.size;
        while(v1 < v2) {
            int v3 = arr_v[v1];
            int v4 = v1 + 3;
            this.currentEventTime = ((long)arr_v[v1 + 1]) << 0x20 | ((long)arr_v[v1 + 2]) & 0xFFFFFFFFL;
            switch(v3) {
                case -1: {
                    v1 = v4 + arr_v[v4];
                    break;
                }
                case 0: {
                    v1 = v4 + 1;
                    inputProcessor0.keyDown(arr_v[v4]);
                    break;
                }
                case 1: {
                    v1 = v4 + 1;
                    inputProcessor0.keyUp(arr_v[v4]);
                    break;
                }
                case 2: {
                    v1 = v4 + 1;
                    inputProcessor0.keyTyped(((char)arr_v[v4]));
                    break;
                }
                case 3: {
                    inputProcessor0.touchDown(arr_v[v4], arr_v[v4 + 1], arr_v[v4 + 2], arr_v[v4 + 3]);
                    v1 = v4 + 4;
                    break;
                }
                case 4: {
                    inputProcessor0.touchUp(arr_v[v4], arr_v[v4 + 1], arr_v[v4 + 2], arr_v[v4 + 3]);
                    v1 = v4 + 4;
                    break;
                }
                case 5: {
                    inputProcessor0.touchDragged(arr_v[v4], arr_v[v4 + 1], arr_v[v4 + 2]);
                    v1 = v4 + 3;
                    break;
                }
                case 6: {
                    inputProcessor0.mouseMoved(arr_v[v4], arr_v[v4 + 1]);
                    v1 = v4 + 2;
                    break;
                }
                case 7: {
                    inputProcessor0.scrolled(NumberUtils.intBitsToFloat(arr_v[v4]), NumberUtils.intBitsToFloat(arr_v[v4 + 1]));
                    v1 = v4 + 2;
                    break;
                }
                default: {
                    throw new RuntimeException();
                }
            }
        }
        this.processingQueue.clear();
    }

    public long getCurrentEventTime() {
        return this.currentEventTime;
    }

    public boolean keyDown(int v, long v1) {
        synchronized(this) {
            this.queue.add(0);
            this.queueTime(v1);
            this.queue.add(v);
            return false;
        }
    }

    public boolean keyTyped(char c, long v) {
        synchronized(this) {
            this.queue.add(2);
            this.queueTime(v);
            this.queue.add(((int)c));
            return false;
        }
    }

    public boolean keyUp(int v, long v1) {
        synchronized(this) {
            this.queue.add(1);
            this.queueTime(v1);
            this.queue.add(v);
            return false;
        }
    }

    public boolean mouseMoved(int v, int v1, long v2) {
        synchronized(this) {
            for(int v4 = this.next(6, 0); v4 >= 0; v4 = this.next(6, v4 + 5)) {
                this.queue.set(v4, -1);
                this.queue.set(v4 + 3, 2);
            }
            this.queue.add(6);
            this.queueTime(v2);
            this.queue.add(v);
            this.queue.add(v1);
            return false;
        }
    }

    private int next(int v, int v1) {
        synchronized(this) {
            int[] arr_v = this.queue.items;
            int v3 = this.queue.size;
            while(v1 < v3) {
                int v4 = arr_v[v1];
                if(v4 == v) {
                    return v1;
                }
                switch(v4) {
                    case -1: {
                        v1 = v1 + 3 + arr_v[v1 + 3];
                        break;
                    }
                    case 0: {
                        v1 += 4;
                        break;
                    }
                    case 1: {
                        v1 += 4;
                        break;
                    }
                    case 2: {
                        v1 += 4;
                        break;
                    }
                    case 3: {
                        v1 += 7;
                        break;
                    }
                    case 4: {
                        v1 += 7;
                        break;
                    }
                    case 5: {
                        v1 += 6;
                        break;
                    }
                    case 6: {
                        v1 += 5;
                        break;
                    }
                    case 7: {
                        v1 += 5;
                        break;
                    }
                    default: {
                        throw new RuntimeException();
                    }
                }
            }
            return -1;
        }
    }

    private void queueTime(long v) {
        this.queue.add(((int)(v >> 0x20)));
        this.queue.add(((int)v));
    }

    public boolean scrolled(float f, float f1, long v) {
        synchronized(this) {
            this.queue.add(7);
            this.queueTime(v);
            this.queue.add(NumberUtils.floatToIntBits(f));
            this.queue.add(NumberUtils.floatToIntBits(f1));
            return false;
        }
    }

    public boolean touchDown(int v, int v1, int v2, int v3, long v4) {
        synchronized(this) {
            this.queue.add(3);
            this.queueTime(v4);
            this.queue.add(v);
            this.queue.add(v1);
            this.queue.add(v2);
            this.queue.add(v3);
            return false;
        }
    }

    public boolean touchDragged(int v, int v1, int v2, long v3) {
        synchronized(this) {
            for(int v5 = this.next(5, 0); v5 >= 0; v5 = this.next(5, v5 + 6)) {
                if(this.queue.get(v5 + 5) == v2) {
                    this.queue.set(v5, -1);
                    this.queue.set(v5 + 3, 3);
                }
            }
            this.queue.add(5);
            this.queueTime(v3);
            this.queue.add(v);
            this.queue.add(v1);
            this.queue.add(v2);
            return false;
        }
    }

    public boolean touchUp(int v, int v1, int v2, int v3, long v4) {
        synchronized(this) {
            this.queue.add(4);
            this.queueTime(v4);
            this.queue.add(v);
            this.queue.add(v1);
            this.queue.add(v2);
            this.queue.add(v3);
            return false;
        }
    }
}

