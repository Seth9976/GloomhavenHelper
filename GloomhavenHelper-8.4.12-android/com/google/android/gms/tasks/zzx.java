package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;

final class zzx implements Continuation {
    private final Collection zzae;

    zzx(Collection collection0) {
        this.zzae = collection0;
        super();
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(@NonNull Task task0) throws Exception {
        ArrayList arrayList0 = new ArrayList();
        arrayList0.addAll(this.zzae);
        return Tasks.forResult(arrayList0);
    }
}

