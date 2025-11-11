package com.google.android.gms.tasks;

import androidx.annotation.NonNull;

public interface Continuation {
    Object then(@NonNull Task arg1) throws Exception;
}

