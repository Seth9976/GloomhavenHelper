package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface SuccessContinuation {
    @NonNull
    Task then(@Nullable Object arg1) throws Exception;
}

