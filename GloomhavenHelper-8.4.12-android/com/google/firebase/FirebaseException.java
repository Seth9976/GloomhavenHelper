package com.google.firebase;

import androidx.annotation.NonNull;
import com.google.android.gms.common.internal.Preconditions;

public class FirebaseException extends Exception {
    @Deprecated
    protected FirebaseException() {
    }

    public FirebaseException(@NonNull String s) {
        super(Preconditions.checkNotEmpty(s, "Detail message must not be empty"));
    }

    public FirebaseException(@NonNull String s, Throwable throwable0) {
        super(Preconditions.checkNotEmpty(s, "Detail message must not be empty"), throwable0);
    }
}

