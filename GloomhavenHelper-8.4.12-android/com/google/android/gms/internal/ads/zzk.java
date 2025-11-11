package com.google.android.gms.internal.ads;

import android.text.TextUtils;

public final class zzk {
    private final String mName;
    private final String mValue;

    public zzk(String s, String s1) {
        this.mName = s;
        this.mValue = s1;
    }

    // 去混淆评级： 低(30)
    @Override
    public final boolean equals(Object object0) {
        return this == object0 ? true : object0 != null && this.getClass() == object0.getClass() && TextUtils.equals(this.mName, ((zzk)object0).mName) && TextUtils.equals(this.mValue, ((zzk)object0).mValue);
    }

    public final String getName() {
        return this.mName;
    }

    public final String getValue() {
        return this.mValue;
    }

    @Override
    public final int hashCode() {
        return this.mName.hashCode() * 0x1F + this.mValue.hashCode();
    }

    @Override
    public final String toString() {
        return "Header[name=" + this.mName + ",value=" + this.mValue + "]";
    }
}

