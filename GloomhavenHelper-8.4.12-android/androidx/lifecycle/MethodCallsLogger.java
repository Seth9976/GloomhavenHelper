package androidx.lifecycle;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class MethodCallsLogger {
    private Map mCalledMethods;

    public MethodCallsLogger() {
        this.mCalledMethods = new HashMap();
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    public boolean approveCall(String s, int v) {
        Integer integer0 = (Integer)this.mCalledMethods.get(s);
        int v1 = 0;
        int v2 = integer0 == null ? 0 : ((int)integer0);
        if((v2 & v) != 0) {
            v1 = 1;
        }
        this.mCalledMethods.put(s, ((int)(v | v2)));
        return v1 ^ 1;
    }
}

