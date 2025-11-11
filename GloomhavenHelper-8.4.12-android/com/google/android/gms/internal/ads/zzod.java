package com.google.android.gms.internal.ads;

public final class zzod {
    private boolean isOpen;

    public final void block() throws InterruptedException {
        synchronized(this) {
            while(!this.isOpen) {
                this.wait();
            }
        }
    }

    public final boolean open() {
        synchronized(this) {
            if(this.isOpen) {
                return false;
            }
            this.isOpen = true;
            this.notifyAll();
            return true;
        }
    }

    public final boolean zzir() {
        boolean z;
        synchronized(this) {
            z = this.isOpen;
            this.isOpen = false;
        }
        return z;
    }
}

