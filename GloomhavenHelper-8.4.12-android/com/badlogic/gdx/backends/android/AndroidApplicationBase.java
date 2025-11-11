package com.badlogic.gdx.backends.android;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;

public interface AndroidApplicationBase extends Application {
    public static final int MINIMUM_SDK = 14;

    AndroidAudio createAudio(Context arg1, AndroidApplicationConfiguration arg2);

    AndroidInput createInput(Application arg1, Context arg2, Object arg3, AndroidApplicationConfiguration arg4);

    Window getApplicationWindow();

    Context getContext();

    Array getExecutedRunnables();

    Handler getHandler();

    AndroidInput getInput();

    SnapshotArray getLifecycleListeners();

    Array getRunnables();

    WindowManager getWindowManager();

    void runOnUiThread(Runnable arg1);

    void startActivity(Intent arg1);

    void useImmersiveMode(boolean arg1);
}

