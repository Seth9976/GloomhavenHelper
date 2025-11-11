package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

public final class zzctm implements zzcyb {
    private final zzuk zzblv;
    private final List zzghs;
    private final Context zzur;

    public zzctm(Context context0, zzuk zzuk0, List list0) {
        this.zzur = context0;
        this.zzblv = zzuk0;
        this.zzghs = list0;
    }

    @Override  // com.google.android.gms.internal.ads.zzcyb
    public final void zzs(Object object0) {
        if(((Boolean)zzabl.zzcvc.get()).booleanValue()) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("activity", zzawo.zzav(this.zzur));
            Bundle bundle1 = new Bundle();
            bundle1.putInt("width", this.zzblv.width);
            bundle1.putInt("height", this.zzblv.height);
            bundle0.putBundle("size", bundle1);
            if(this.zzghs.size() > 0) {
                bundle0.putParcelableArray("parents", ((Parcelable[])this.zzghs.toArray(new Parcelable[this.zzghs.size()])));
            }
            ((Bundle)object0).putBundle("view_hierarchy", bundle0);
        }
    }
}

