package com.google.ads.mediation;

import com.google.android.gms.internal.ads.zzazh;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

@Deprecated
public class MediationServerParameters {
    public static final class MappingException extends Exception {
        public MappingException(String s) {
            super(s);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    public @interface Parameter {
        String name();

        boolean required() default true;
    }

    public void load(Map map0) throws MappingException {
        HashMap hashMap0 = new HashMap();
        Field[] arr_field = this.getClass().getFields();
        for(int v = 0; v < arr_field.length; ++v) {
            Field field0 = arr_field[v];
            Parameter mediationServerParameters$Parameter0 = (Parameter)field0.getAnnotation(Parameter.class);
            if(mediationServerParameters$Parameter0 != null) {
                hashMap0.put(mediationServerParameters$Parameter0.name(), field0);
            }
        }
        if(hashMap0.isEmpty()) {
            zzazh.zzfa("No server options fields detected. To suppress this message either add a field with the @Parameter annotation, or override the load() method.");
        }
        for(Object object0: map0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            Field field1 = (Field)hashMap0.remove(map$Entry0.getKey());
            if(field1 == null) {
                zzazh.zzeb(("Unexpected server option: " + ((String)map$Entry0.getKey()) + " = \"" + ((String)map$Entry0.getValue()) + "\""));
            }
            else {
                try {
                    field1.set(this, map$Entry0.getValue());
                }
                catch(IllegalAccessException unused_ex) {
                    zzazh.zzfa(("Server option \"" + ((String)map$Entry0.getKey()) + "\" could not be set: Illegal Access"));
                }
                catch(IllegalArgumentException unused_ex) {
                    zzazh.zzfa(("Server option \"" + ((String)map$Entry0.getKey()) + "\" could not be set: Bad Type"));
                }
            }
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object1: hashMap0.values()) {
            Field field2 = (Field)object1;
            if(((Parameter)field2.getAnnotation(Parameter.class)).required()) {
                String s = ((Parameter)field2.getAnnotation(Parameter.class)).name();
                zzazh.zzfa((s.length() == 0 ? new String("Required server option missing: ") : "Required server option missing: " + s));
                if(stringBuilder0.length() > 0) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(((Parameter)field2.getAnnotation(Parameter.class)).name());
            }
        }
        if(stringBuilder0.length() > 0) {
            String s1 = stringBuilder0.toString();
            throw new MappingException((s1.length() == 0 ? new String("Required server option(s) missing: ") : "Required server option(s) missing: " + s1));
        }
    }
}

