package com.google.android.gms.tasks;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.concurrent.Executor;

public abstract class Task {
    @NonNull
    public Task addOnCanceledListener(@NonNull Activity activity0, @NonNull OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    @NonNull
    public Task addOnCanceledListener(@NonNull OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented.");
    }

    @NonNull
    public Task addOnCanceledListener(@NonNull Executor executor0, @NonNull OnCanceledListener onCanceledListener0) {
        throw new UnsupportedOperationException("addOnCanceledListener is not implemented");
    }

    @NonNull
    public Task addOnCompleteListener(@NonNull Activity activity0, @NonNull OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task addOnCompleteListener(@NonNull OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public Task addOnCompleteListener(@NonNull Executor executor0, @NonNull OnCompleteListener onCompleteListener0) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    @NonNull
    public abstract Task addOnFailureListener(@NonNull Activity arg1, @NonNull OnFailureListener arg2);

    @NonNull
    public abstract Task addOnFailureListener(@NonNull OnFailureListener arg1);

    @NonNull
    public abstract Task addOnFailureListener(@NonNull Executor arg1, @NonNull OnFailureListener arg2);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull Activity arg1, @NonNull OnSuccessListener arg2);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull OnSuccessListener arg1);

    @NonNull
    public abstract Task addOnSuccessListener(@NonNull Executor arg1, @NonNull OnSuccessListener arg2);

    @NonNull
    public Task continueWith(@NonNull Continuation continuation0) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public Task continueWith(@NonNull Executor executor0, @NonNull Continuation continuation0) {
        throw new UnsupportedOperationException("continueWith is not implemented");
    }

    @NonNull
    public Task continueWithTask(@NonNull Continuation continuation0) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    @NonNull
    public Task continueWithTask(@NonNull Executor executor0, @NonNull Continuation continuation0) {
        throw new UnsupportedOperationException("continueWithTask is not implemented");
    }

    @Nullable
    public abstract Exception getException();

    @Nullable
    public abstract Object getResult();

    @Nullable
    public abstract Object getResult(@NonNull Class arg1) throws Throwable;

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    @NonNull
    public Task onSuccessTask(@NonNull SuccessContinuation successContinuation0) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }

    @NonNull
    public Task onSuccessTask(@NonNull Executor executor0, @NonNull SuccessContinuation successContinuation0) {
        throw new UnsupportedOperationException("onSuccessTask is not implemented");
    }
}

