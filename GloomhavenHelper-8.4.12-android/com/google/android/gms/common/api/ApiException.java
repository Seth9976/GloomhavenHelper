package com.google.android.gms.common.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ApiException extends Exception {
    protected final Status mStatus;

    public ApiException(@NonNull Status status0) {
        super(status0.getStatusCode() + ": " + (status0.getStatusMessage() == null ? "" : status0.getStatusMessage()));
        this.mStatus = status0;
    }

    public int getStatusCode() {
        return this.mStatus.getStatusCode();
    }

    @Nullable
    @Deprecated
    public String getStatusMessage() {
        return this.mStatus.getStatusMessage();
    }
}

