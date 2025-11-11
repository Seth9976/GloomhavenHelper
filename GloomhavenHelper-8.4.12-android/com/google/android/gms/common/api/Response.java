package com.google.android.gms.common.api;

import androidx.annotation.NonNull;

public class Response {
    private Result zzap;

    public Response() {
    }

    protected Response(@NonNull Result result0) {
        this.zzap = result0;
    }

    @NonNull
    protected Result getResult() {
        return this.zzap;
    }

    public void setResult(@NonNull Result result0) {
        this.zzap = result0;
    }
}

