package com.google.android.gms.ads.initialization;

public interface AdapterStatus {
    public static enum State {
        NOT_READY,
        READY;

    }

    String getDescription();

    State getInitializationState();

    int getLatency();
}

