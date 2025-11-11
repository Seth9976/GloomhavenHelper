package com.badlogic.gdx.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.InputProcessor;
import java.io.DataOutputStream;
import java.net.Socket;

public class RemoteSender implements InputProcessor {
    public static final int ACCEL = 6;
    public static final int COMPASS = 7;
    public static final int GYRO = 9;
    public static final int KEY_DOWN = 0;
    public static final int KEY_TYPED = 2;
    public static final int KEY_UP = 1;
    public static final int SIZE = 8;
    public static final int TOUCH_DOWN = 3;
    public static final int TOUCH_DRAGGED = 5;
    public static final int TOUCH_UP = 4;
    private boolean connected;
    private DataOutputStream out;

    public RemoteSender(String s, int v) {
        this.connected = false;
        try {
            Socket socket0 = new Socket(s, v);
            socket0.setTcpNoDelay(true);
            socket0.setSoTimeout(3000);
            this.out = new DataOutputStream(socket0.getOutputStream());
            this.out.writeBoolean(Gdx.input.isPeripheralAvailable(Peripheral.MultitouchScreen));
            this.connected = true;
            Gdx.input.setInputProcessor(this);
        }
        catch(Exception unused_ex) {
            Gdx.app.log("RemoteSender", "couldn\'t connect to " + s + ":" + v);
        }
    }

    public boolean isConnected() {
        synchronized(this) {
        }
        return this.connected;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyDown(int v) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(0);
            this.out.writeInt(v);
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyTyped(char c) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(2);
            this.out.writeChar(((int)c));
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean keyUp(int v) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(1);
            this.out.writeInt(v);
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean mouseMoved(int v, int v1) {
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean scrolled(float f, float f1) {
        return false;
    }

    public void sendUpdate() {
        synchronized(this) {
            if(!this.connected) {
                return;
            }
        }
        try {
            this.out.writeInt(6);
            this.out.writeFloat(Gdx.input.getAccelerometerX());
            this.out.writeFloat(Gdx.input.getAccelerometerY());
            this.out.writeFloat(Gdx.input.getAccelerometerZ());
            this.out.writeInt(7);
            this.out.writeFloat(Gdx.input.getAzimuth());
            this.out.writeFloat(Gdx.input.getPitch());
            this.out.writeFloat(Gdx.input.getRoll());
            this.out.writeInt(8);
            this.out.writeFloat(((float)Gdx.graphics.getWidth()));
            this.out.writeFloat(((float)Gdx.graphics.getHeight()));
            this.out.writeInt(9);
            this.out.writeFloat(Gdx.input.getGyroscopeX());
            this.out.writeFloat(Gdx.input.getGyroscopeY());
            this.out.writeFloat(Gdx.input.getGyroscopeZ());
        }
        catch(Throwable unused_ex) {
            this.out = null;
            this.connected = false;
        }
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDown(int v, int v1, int v2, int v3) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(3);
            this.out.writeInt(v);
            this.out.writeInt(v1);
            this.out.writeInt(v2);
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchDragged(int v, int v1, int v2) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(5);
            this.out.writeInt(v);
            this.out.writeInt(v1);
            this.out.writeInt(v2);
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }

    @Override  // com.badlogic.gdx.InputProcessor
    public boolean touchUp(int v, int v1, int v2, int v3) {
        synchronized(this) {
            if(!this.connected) {
                return false;
            }
        }
        try {
            this.out.writeInt(4);
            this.out.writeInt(v);
            this.out.writeInt(v1);
            this.out.writeInt(v2);
        }
        catch(Throwable unused_ex) {
            synchronized(this) {
                this.connected = false;
            }
        }
        return false;
    }
}

