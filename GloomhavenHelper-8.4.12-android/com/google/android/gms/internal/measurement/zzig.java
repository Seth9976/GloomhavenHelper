package com.google.android.gms.internal.measurement;

final class zzig extends IllegalArgumentException {
    zzig(int v, int v1) {
        super("Unpaired surrogate at index " + v + " of " + v1);
    }
}

