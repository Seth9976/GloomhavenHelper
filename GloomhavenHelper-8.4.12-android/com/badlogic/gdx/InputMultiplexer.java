package com.badlogic.gdx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public class InputMultiplexer implements InputProcessor {
    private SnapshotArray processors;

    public InputMultiplexer() {
        this.processors = new SnapshotArray(4);
    }

    public InputMultiplexer(InputProcessor[] arr_inputProcessor) {
        this.processors = new SnapshotArray(4);
        this.processors.addAll(arr_inputProcessor);
    }

    public void addProcessor(int v, InputProcessor inputProcessor0) {
        if(inputProcessor0 == null) {
            throw new NullPointerException("processor cannot be null");
        }
        this.processors.insert(v, inputProcessor0);
    }

    public void addProcessor(InputProcessor inputProcessor0) {
        if(inputProcessor0 == null) {
            throw new NullPointerException("processor cannot be null");
        }
        this.processors.add(inputProcessor0);
    }

    public void clear() {
        this.processors.clear();
    }

    public SnapshotArray getProcessors() {
        return this.processors;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int v) {
        Object[] arr_object = this.processors.begin();
        try {
            int v2 = this.processors.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(((InputProcessor)arr_object[v3]).keyDown(v)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        Object[] arr_object = this.processors.begin();
        try {
            int v1 = this.processors.size;
            for(int v2 = 0; v2 < v1; ++v2) {
                if(((InputProcessor)arr_object[v2]).keyTyped(c)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int v) {
        Object[] arr_object = this.processors.begin();
        try {
            int v2 = this.processors.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                if(((InputProcessor)arr_object[v3]).keyUp(v)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int v, int v1) {
        Object[] arr_object = this.processors.begin();
        try {
            int v3 = this.processors.size;
            for(int v4 = 0; v4 < v3; ++v4) {
                if(((InputProcessor)arr_object[v4]).mouseMoved(v, v1)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    public void removeProcessor(int v) {
        this.processors.removeIndex(v);
    }

    public void removeProcessor(InputProcessor inputProcessor0) {
        this.processors.removeValue(inputProcessor0, true);
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f1) {
        Object[] arr_object = this.processors.begin();
        try {
            int v1 = this.processors.size;
            for(int v2 = 0; v2 < v1; ++v2) {
                if(((InputProcessor)arr_object[v2]).scrolled(f, f1)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    public void setProcessors(Array array0) {
        this.processors.clear();
        this.processors.addAll(array0);
    }

    public void setProcessors(InputProcessor[] arr_inputProcessor) {
        this.processors.clear();
        this.processors.addAll(arr_inputProcessor);
    }

    public int size() {
        return this.processors.size;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int v, int v1, int v2, int v3) {
        Object[] arr_object = this.processors.begin();
        try {
            int v5 = this.processors.size;
            for(int v6 = 0; v6 < v5; ++v6) {
                if(((InputProcessor)arr_object[v6]).touchDown(v, v1, v2, v3)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int v, int v1, int v2) {
        Object[] arr_object = this.processors.begin();
        try {
            int v4 = this.processors.size;
            for(int v5 = 0; v5 < v4; ++v5) {
                if(((InputProcessor)arr_object[v5]).touchDragged(v, v1, v2)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int v, int v1, int v2, int v3) {
        Object[] arr_object = this.processors.begin();
        try {
            int v5 = this.processors.size;
            for(int v6 = 0; v6 < v5; ++v6) {
                if(((InputProcessor)arr_object[v6]).touchUp(v, v1, v2, v3)) {
                    return true;
                }
            }
            return false;
        }
        finally {
            this.processors.end();
        }
    }
}

