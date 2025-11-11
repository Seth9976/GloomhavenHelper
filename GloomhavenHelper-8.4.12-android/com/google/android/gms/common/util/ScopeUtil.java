package com.google.android.gms.common.util;

import android.text.TextUtils;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@KeepForSdk
public final class ScopeUtil {
    @KeepForSdk
    public static Set fromScopeString(Collection collection0) {
        Preconditions.checkNotNull(collection0, "scopeStrings can\'t be null.");
        return ScopeUtil.fromScopeString(((String[])collection0.toArray(new String[collection0.size()])));
    }

    @KeepForSdk
    public static Set fromScopeString(String[] arr_s) {
        Preconditions.checkNotNull(arr_s, "scopeStrings can\'t be null.");
        Set set0 = new HashSet(arr_s.length);
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(!TextUtils.isEmpty(s)) {
                set0.add(new Scope(s));
            }
        }
        return set0;
    }

    @KeepForSdk
    public static String[] toScopeString(Set set0) {
        Preconditions.checkNotNull(set0, "scopes can\'t be null.");
        return ScopeUtil.toScopeString(((Scope[])set0.toArray(new Scope[set0.size()])));
    }

    @KeepForSdk
    public static String[] toScopeString(Scope[] arr_scope) {
        Preconditions.checkNotNull(arr_scope, "scopes can\'t be null.");
        String[] arr_s = new String[arr_scope.length];
        for(int v = 0; v < arr_scope.length; ++v) {
            arr_s[v] = arr_scope[v].getScopeUri();
        }
        return arr_s;
    }
}

