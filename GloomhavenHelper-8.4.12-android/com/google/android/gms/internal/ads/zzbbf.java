package com.google.android.gms.internal.ads;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.WindowManager;
import javax.annotation.concurrent.GuardedBy;

final class zzbbf implements SensorEventListener {
    private final SensorManager zzeab;
    private final Object zzeac;
    private final Display zzead;
    private final float[] zzeae;
    private final float[] zzeaf;
    @GuardedBy("sensorThreadLock")
    private float[] zzeag;
    private Handler zzeah;
    private zzbbh zzeai;

    zzbbf(Context context0) {
        this.zzeab = (SensorManager)context0.getSystemService("sensor");
        this.zzead = ((WindowManager)context0.getSystemService("window")).getDefaultDisplay();
        this.zzeae = new float[9];
        this.zzeaf = new float[9];
        this.zzeac = new Object();
    }

    @Override  // android.hardware.SensorEventListener
    public final void onAccuracyChanged(Sensor sensor0, int v) {
    }

    @Override  // android.hardware.SensorEventListener
    public final void onSensorChanged(SensorEvent sensorEvent0) {
        float[] arr_f = sensorEvent0.values;
        if(arr_f[0] != 0.0f || arr_f[1] != 0.0f || arr_f[2] != 0.0f) {
            synchronized(this.zzeac) {
                if(this.zzeag == null) {
                    this.zzeag = new float[9];
                }
            }
            SensorManager.getRotationMatrixFromVector(this.zzeae, arr_f);
            switch(this.zzead.getRotation()) {
                case 1: {
                    SensorManager.remapCoordinateSystem(this.zzeae, 2, 0x81, this.zzeaf);
                    break;
                }
                case 2: {
                    SensorManager.remapCoordinateSystem(this.zzeae, 0x81, 130, this.zzeaf);
                    break;
                }
                case 3: {
                    SensorManager.remapCoordinateSystem(this.zzeae, 130, 1, this.zzeaf);
                    break;
                }
                default: {
                    System.arraycopy(this.zzeae, 0, this.zzeaf, 0, 9);
                }
            }
            this.zzl(1, 3);
            this.zzl(2, 6);
            this.zzl(5, 7);
            synchronized(this.zzeac) {
                System.arraycopy(this.zzeaf, 0, this.zzeag, 0, 9);
            }
            zzbbh zzbbh0 = this.zzeai;
            if(zzbbh0 != null) {
                zzbbh0.zztv();
            }
        }
    }

    final void start() {
        if(this.zzeah != null) {
            return;
        }
        Sensor sensor0 = this.zzeab.getDefaultSensor(11);
        if(sensor0 == null) {
            zzawf.zzey("No Sensor of TYPE_ROTATION_VECTOR");
            return;
        }
        HandlerThread handlerThread0 = new HandlerThread("OrientationMonitor");
        handlerThread0.start();
        this.zzeah = new zzdkp(handlerThread0.getLooper());
        if(!this.zzeab.registerListener(this, sensor0, 0, this.zzeah)) {
            zzawf.zzey("SensorManager.registerListener failed.");
            this.stop();
        }
    }

    final void stop() {
        if(this.zzeah == null) {
            return;
        }
        this.zzeab.unregisterListener(this);
        this.zzeah.post(new zzbbi(this));
        this.zzeah = null;
    }

    final void zza(zzbbh zzbbh0) {
        this.zzeai = zzbbh0;
    }

    final boolean zza(float[] arr_f) {
        synchronized(this.zzeac) {
            if(this.zzeag == null) {
                return false;
            }
            System.arraycopy(this.zzeag, 0, arr_f, 0, this.zzeag.length);
            return true;
        }
    }

    private final void zzl(int v, int v1) {
        float f = this.zzeaf[v];
        this.zzeaf[v] = this.zzeaf[v1];
        this.zzeaf[v1] = f;
    }
}

