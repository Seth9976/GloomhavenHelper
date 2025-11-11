package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

final class zzw implements Continuation {
    private final Collection zzae;

    zzw(Collection collection0) {
        this.zzae = collection0;
        super();
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(@NonNull Task task0) throws Exception {
        if(this.zzae.size() == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.zzae) {
            arrayList0.add(((Task)object0).getResult());
        }
        return arrayList0;
    }
}

