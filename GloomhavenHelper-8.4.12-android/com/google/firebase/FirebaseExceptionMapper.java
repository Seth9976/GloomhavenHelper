package com.google.firebase;

import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

@KeepForSdk
public class FirebaseExceptionMapper implements StatusExceptionMapper {
    @Override  // com.google.android.gms.common.api.internal.StatusExceptionMapper
    public Exception getException(Status status0) {
        return status0.getStatusCode() == 8 ? new FirebaseException(status0.zzg()) : new FirebaseApiNotAvailableException(status0.zzg());
    }
}

