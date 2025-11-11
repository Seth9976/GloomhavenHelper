package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.reflect.ArrayReflection;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.Field;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;

public class Json {
    static class FieldMetadata {
        boolean deprecated;
        Class elementType;
        final Field field;

        public FieldMetadata(Field field0) {
            int v;
            this.field = field0;
            Class class0 = field0.getType();
            if(ClassReflection.isAssignableFrom(ObjectMap.class, class0)) {
                v = 1;
            }
            else {
                Class class1 = field0.getType();
                v = ClassReflection.isAssignableFrom(Map.class, class1) ? 1 : 0;
            }
            this.elementType = field0.getElementType(v);
            this.deprecated = field0.isAnnotationPresent(Deprecated.class);
        }
    }

    public static abstract class ReadOnlySerializer implements Serializer {
        @Override  // com.badlogic.gdx.utils.Json$Serializer
        public abstract Object read(Json arg1, JsonValue arg2, Class arg3);

        @Override  // com.badlogic.gdx.utils.Json$Serializer
        public void write(Json json0, Object object0, Class class0) {
        }
    }

    public interface Serializable {
        void read(Json arg1, JsonValue arg2);

        void write(Json arg1);
    }

    public interface Serializer {
        Object read(Json arg1, JsonValue arg2, Class arg3);

        void write(Json arg1, Object arg2, Class arg3);
    }

    private final ObjectMap classToDefaultValues;
    private final ObjectMap classToSerializer;
    private final ObjectMap classToTag;
    private static final boolean debug = false;
    private Serializer defaultSerializer;
    private boolean enumNames;
    private final Object[] equals1;
    private final Object[] equals2;
    private boolean ignoreDeprecated;
    private boolean ignoreUnknownFields;
    private OutputType outputType;
    private boolean quoteLongValues;
    private boolean readDeprecated;
    private boolean sortFields;
    private final ObjectMap tagToClass;
    private String typeName;
    private final ObjectMap typeToFields;
    private boolean usePrototypes;
    private JsonWriter writer;

    public Json() {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap();
        this.tagToClass = new ObjectMap();
        this.classToTag = new ObjectMap();
        this.classToSerializer = new ObjectMap();
        this.classToDefaultValues = new ObjectMap();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = OutputType.minimal;
    }

    public Json(OutputType jsonWriter$OutputType0) {
        this.typeName = "class";
        this.usePrototypes = true;
        this.enumNames = true;
        this.typeToFields = new ObjectMap();
        this.tagToClass = new ObjectMap();
        this.classToTag = new ObjectMap();
        this.classToSerializer = new ObjectMap();
        this.classToDefaultValues = new ObjectMap();
        this.equals1 = new Object[]{null};
        this.equals2 = new Object[]{null};
        this.outputType = jsonWriter$OutputType0;
    }

    public void addClassTag(String s, Class class0) {
        this.tagToClass.put(s, class0);
        this.classToTag.put(class0, s);
    }

    // 去混淆评级： 低(20)
    private String convertToString(Enum enum0) {
        return this.enumNames ? enum0.name() : enum0.toString();
    }

    private String convertToString(Object object0) {
        if(object0 instanceof Enum) {
            return this.convertToString(((Enum)object0));
        }
        return object0 instanceof Class ? ((Class)object0).getName() : String.valueOf(object0);
    }

    public void copyFields(Object object0, Object object1) {
        OrderedMap orderedMap0 = this.getFields(object1.getClass());
        for(Object object2: this.getFields(object0.getClass())) {
            Entry objectMap$Entry0 = (Entry)object2;
            FieldMetadata json$FieldMetadata0 = (FieldMetadata)orderedMap0.get(objectMap$Entry0.key);
            Field field0 = ((FieldMetadata)objectMap$Entry0.value).field;
            if(json$FieldMetadata0 == null) {
                throw new SerializationException("To object is missing field: " + ((String)objectMap$Entry0.key));
            }
            try {
                Object object3 = field0.get(object0);
                json$FieldMetadata0.field.set(object1, object3);
            }
            catch(ReflectionException reflectionException0) {
                throw new SerializationException("Error copying field: " + field0.getName(), reflectionException0);
            }
        }
    }

    @Null
    public Object fromJson(Class class0, FileHandle fileHandle0) {
        try {
            return this.readValue(class0, null, new JsonReader().parse(fileHandle0));
        }
        catch(Exception exception0) {
            throw new SerializationException("Error reading file: " + fileHandle0, exception0);
        }
    }

    @Null
    public Object fromJson(Class class0, InputStream inputStream0) {
        return this.readValue(class0, null, new JsonReader().parse(inputStream0));
    }

    @Null
    public Object fromJson(Class class0, Reader reader0) {
        return this.readValue(class0, null, new JsonReader().parse(reader0));
    }

    @Null
    public Object fromJson(Class class0, Class class1, FileHandle fileHandle0) {
        try {
            return this.readValue(class0, class1, new JsonReader().parse(fileHandle0));
        }
        catch(Exception exception0) {
            throw new SerializationException("Error reading file: " + fileHandle0, exception0);
        }
    }

    @Null
    public Object fromJson(Class class0, Class class1, InputStream inputStream0) {
        return this.readValue(class0, class1, new JsonReader().parse(inputStream0));
    }

    @Null
    public Object fromJson(Class class0, Class class1, Reader reader0) {
        return this.readValue(class0, class1, new JsonReader().parse(reader0));
    }

    @Null
    public Object fromJson(Class class0, Class class1, String s) {
        return this.readValue(class0, class1, new JsonReader().parse(s));
    }

    @Null
    public Object fromJson(Class class0, Class class1, char[] arr_c, int v, int v1) {
        return this.readValue(class0, class1, new JsonReader().parse(arr_c, v, v1));
    }

    @Null
    public Object fromJson(Class class0, String s) {
        return this.readValue(class0, null, new JsonReader().parse(s));
    }

    @Null
    public Object fromJson(Class class0, char[] arr_c, int v, int v1) {
        return this.readValue(class0, null, new JsonReader().parse(arr_c, v, v1));
    }

    @Null
    public Class getClass(String s) {
        return (Class)this.tagToClass.get(s);
    }

    @Null
    private Object[] getDefaultValues(Class class0) {
        Field field0;
        Object object0;
        if(!this.usePrototypes) {
            return null;
        }
        if(this.classToDefaultValues.containsKey(class0)) {
            return (Object[])this.classToDefaultValues.get(class0);
        }
        try {
            object0 = this.newInstance(class0);
        }
        catch(Exception unused_ex) {
            this.classToDefaultValues.put(class0, null);
            return null;
        }
        OrderedMap orderedMap0 = this.getFields(class0);
        Object[] arr_object = new Object[orderedMap0.size];
        this.classToDefaultValues.put(class0, arr_object);
        Array array0 = orderedMap0.orderedKeys();
        int v = array0.size;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            FieldMetadata json$FieldMetadata0 = (FieldMetadata)orderedMap0.get(array0.get(v1));
            if(!this.ignoreDeprecated || !json$FieldMetadata0.deprecated) {
                try {
                    field0 = json$FieldMetadata0.field;
                    arr_object[v2] = field0.get(object0);
                    ++v2;
                }
                catch(ReflectionException reflectionException0) {
                    throw new SerializationException("Error accessing field: " + field0.getName() + " (" + class0.getName() + ")", reflectionException0);
                }
                catch(SerializationException serializationException0) {
                    serializationException0.addTrace(field0 + " (" + class0.getName() + ")");
                    throw serializationException0;
                }
                catch(RuntimeException runtimeException0) {
                    SerializationException serializationException1 = new SerializationException(runtimeException0);
                    serializationException1.addTrace(field0 + " (" + class0.getName() + ")");
                    throw serializationException1;
                }
            }
        }
        return arr_object;
    }

    private OrderedMap getFields(Class class0) {
        OrderedMap orderedMap0 = (OrderedMap)this.typeToFields.get(class0);
        if(orderedMap0 != null) {
            return orderedMap0;
        }
        Array array0 = new Array();
        for(Class class1 = class0; class1 != Object.class; class1 = class1.getSuperclass()) {
            array0.add(class1);
        }
        ArrayList arrayList0 = new ArrayList();
        for(int v = array0.size - 1; v >= 0; --v) {
            Collections.addAll(arrayList0, ClassReflection.getDeclaredFields(((Class)array0.get(v))));
        }
        OrderedMap orderedMap1 = new OrderedMap(arrayList0.size());
        int v2 = arrayList0.size();
        for(int v1 = 0; v1 < v2; ++v1) {
            Field field0 = (Field)arrayList0.get(v1);
            if(!field0.isTransient() && !field0.isStatic() && !field0.isSynthetic()) {
                if(!field0.isAccessible()) {
                    try {
                        field0.setAccessible(true);
                    }
                    catch(AccessControlException unused_ex) {
                        continue;
                    }
                }
                orderedMap1.put(field0.getName(), new FieldMetadata(field0));
            }
        }
        if(this.sortFields) {
            orderedMap1.keys.sort();
        }
        this.typeToFields.put(class0, orderedMap1);
        return orderedMap1;
    }

    public boolean getIgnoreUnknownFields() {
        return this.ignoreUnknownFields;
    }

    public Serializer getSerializer(Class class0) {
        return (Serializer)this.classToSerializer.get(class0);
    }

    @Null
    public String getTag(Class class0) {
        return (String)this.classToTag.get(class0);
    }

    public JsonWriter getWriter() {
        return this.writer;
    }

    protected boolean ignoreUnknownField(Class class0, String s) {
        return false;
    }

    protected Object newInstance(Class class0) {
        try {
            return ClassReflection.newInstance(class0);
        }
        catch(Exception exception0) {
            try {
                Constructor constructor0 = ClassReflection.getDeclaredConstructor(class0, new Class[0]);
                constructor0.setAccessible(true);
                return constructor0.newInstance(new Object[0]);
            }
            catch(SecurityException unused_ex) {
                if(ClassReflection.isAssignableFrom(Enum.class, class0)) {
                    if(class0.getEnumConstants() == null) {
                        class0 = class0.getSuperclass();
                    }
                    return class0.getEnumConstants()[0];
                }
                if(!class0.isArray()) {
                    throw !ClassReflection.isMemberClass(class0) || ClassReflection.isStaticClass(class0) ? new SerializationException("Class cannot be created (missing no-arg constructor): " + class0.getName(), exception0) : new SerializationException("Class cannot be created (non-static member class): " + class0.getName(), exception0);
                }
                throw new SerializationException("Encountered JSON object when expected array of type: " + class0.getName(), exception0);
            }
            catch(ReflectionException exception0) {
            }
            catch(Exception unused_ex) {
            }
            throw new SerializationException("Error constructing instance of class: " + class0.getName(), exception0);
        }
    }

    public String prettyPrint(@Null Object object0) {
        return this.prettyPrint(object0, 0);
    }

    public String prettyPrint(@Null Object object0, int v) {
        return this.prettyPrint(this.toJson(object0), v);
    }

    public String prettyPrint(@Null Object object0, PrettyPrintSettings jsonValue$PrettyPrintSettings0) {
        return this.prettyPrint(this.toJson(object0), jsonValue$PrettyPrintSettings0);
    }

    public String prettyPrint(String s) {
        return this.prettyPrint(s, 0);
    }

    public String prettyPrint(String s, int v) {
        return new JsonReader().parse(s).prettyPrint(this.outputType, v);
    }

    public String prettyPrint(String s, PrettyPrintSettings jsonValue$PrettyPrintSettings0) {
        return new JsonReader().parse(s).prettyPrint(jsonValue$PrettyPrintSettings0);
    }

    public void readField(@Null Object object0, Field field0, String s, @Null Class class0, JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.get(s);
        if(jsonValue1 == null) {
            return;
        }
        try {
            field0.set(object0, this.readValue(field0.getType(), class0, jsonValue1));
        }
        catch(ReflectionException reflectionException0) {
            throw new SerializationException("Error accessing field: " + field0.getName() + " (" + field0.getDeclaringClass().getName() + ")", reflectionException0);
        }
        catch(SerializationException serializationException0) {
            serializationException0.addTrace(field0.getName() + " (" + field0.getDeclaringClass().getName() + ")");
            throw serializationException0;
        }
        catch(RuntimeException runtimeException0) {
            SerializationException serializationException1 = new SerializationException(runtimeException0);
            serializationException1.addTrace(jsonValue1.trace());
            serializationException1.addTrace(field0.getName() + " (" + field0.getDeclaringClass().getName() + ")");
            throw serializationException1;
        }
    }

    public void readField(Object object0, String s, JsonValue jsonValue0) {
        this.readField(object0, s, s, null, jsonValue0);
    }

    public void readField(Object object0, String s, @Null Class class0, JsonValue jsonValue0) {
        this.readField(object0, s, s, class0, jsonValue0);
    }

    public void readField(Object object0, String s, String s1, JsonValue jsonValue0) {
        this.readField(object0, s, s1, null, jsonValue0);
    }

    public void readField(Object object0, String s, String s1, @Null Class class0, JsonValue jsonValue0) {
        Class class1 = object0.getClass();
        FieldMetadata json$FieldMetadata0 = (FieldMetadata)this.getFields(class1).get(s);
        if(json$FieldMetadata0 == null) {
            throw new SerializationException("Field not found: " + s + " (" + class1.getName() + ")");
        }
        Field field0 = json$FieldMetadata0.field;
        if(class0 == null) {
            class0 = json$FieldMetadata0.elementType;
        }
        this.readField(object0, field0, s1, class0, jsonValue0);
    }

    public void readFields(Object object0, JsonValue jsonValue0) {
        Field field0;
        Class class0 = object0.getClass();
        OrderedMap orderedMap0 = this.getFields(class0);
        for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
            FieldMetadata json$FieldMetadata0 = (FieldMetadata)orderedMap0.get(jsonValue1.name().replace(" ", "_"));
            if(json$FieldMetadata0 == null) {
                if(!jsonValue1.name.equals(this.typeName) && !this.ignoreUnknownFields && !this.ignoreUnknownField(class0, jsonValue1.name)) {
                    SerializationException serializationException0 = new SerializationException("Field not found: " + jsonValue1.name + " (" + class0.getName() + ")");
                    serializationException0.addTrace(jsonValue1.trace());
                    throw serializationException0;
                }
            }
            else if(!this.ignoreDeprecated || this.readDeprecated || !json$FieldMetadata0.deprecated) {
                try {
                    field0 = json$FieldMetadata0.field;
                    field0.set(object0, this.readValue(field0.getType(), json$FieldMetadata0.elementType, jsonValue1));
                }
                catch(ReflectionException reflectionException0) {
                    throw new SerializationException("Error accessing field: " + field0.getName() + " (" + class0.getName() + ")", reflectionException0);
                }
                catch(SerializationException serializationException1) {
                    serializationException1.addTrace(field0.getName() + " (" + class0.getName() + ")");
                    throw serializationException1;
                }
                catch(RuntimeException runtimeException0) {
                    SerializationException serializationException2 = new SerializationException(runtimeException0);
                    serializationException2.addTrace(jsonValue1.trace());
                    serializationException2.addTrace(field0.getName() + " (" + class0.getName() + ")");
                    throw serializationException2;
                }
            }
        }
    }

    @Null
    public Object readValue(@Null Class class0, JsonValue jsonValue0) {
        return this.readValue(class0, null, jsonValue0);
    }

    @Null
    public Object readValue(@Null Class class0, @Null Class class1, JsonValue jsonValue0) {
        JsonValue jsonValue14;
        if(jsonValue0 == null) {
            return null;
        }
        if(jsonValue0.isObject()) {
            String s = this.typeName == null ? null : jsonValue0.getString(this.typeName, null);
            if(s != null) {
                class0 = this.getClass(s);
                if(class0 == null) {
                    try {
                        class0 = ClassReflection.forName(s);
                    }
                    catch(ReflectionException reflectionException0) {
                        throw new SerializationException(reflectionException0);
                    }
                }
            }
            if(class0 == null) {
                Serializer json$Serializer0 = this.defaultSerializer;
                return json$Serializer0 != null ? json$Serializer0.read(this, jsonValue0, null) : jsonValue0;
            }
            if(this.typeName == null || !ClassReflection.isAssignableFrom(Collection.class, class0)) {
                goto label_20;
            }
            jsonValue0 = jsonValue0.get("items");
            if(jsonValue0 == null) {
                throw new SerializationException("Unable to convert object to collection: " + null + " (" + class0.getName() + ")");
            label_20:
                Serializer json$Serializer1 = (Serializer)this.classToSerializer.get(class0);
                if(json$Serializer1 != null) {
                    return json$Serializer1.read(this, jsonValue0, class0);
                }
                if(class0 != String.class && class0 != Integer.class && class0 != Boolean.class && class0 != Float.class && class0 != Long.class && class0 != Double.class && class0 != Short.class && class0 != Byte.class && class0 != Character.class && !ClassReflection.isAssignableFrom(Enum.class, class0)) {
                    Object object0 = this.newInstance(class0);
                    if(object0 instanceof Serializable) {
                        ((Serializable)object0).read(this, jsonValue0);
                        return object0;
                    }
                    if(object0 instanceof ObjectMap) {
                        for(JsonValue jsonValue1 = jsonValue0.child; jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                            ((ObjectMap)object0).put(jsonValue1.name, this.readValue(class1, null, jsonValue1));
                        }
                        return (ObjectMap)object0;
                    }
                    if(object0 instanceof ObjectIntMap) {
                        for(JsonValue jsonValue2 = jsonValue0.child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                            ((ObjectIntMap)object0).put(jsonValue2.name, ((int)(((Integer)this.readValue(Integer.class, null, jsonValue2)))));
                        }
                        return (ObjectIntMap)object0;
                    }
                    if(object0 instanceof ObjectFloatMap) {
                        for(JsonValue jsonValue3 = jsonValue0.child; jsonValue3 != null; jsonValue3 = jsonValue3.next) {
                            ((ObjectFloatMap)object0).put(jsonValue3.name, ((float)(((Float)this.readValue(Float.class, null, jsonValue3)))));
                        }
                        return (ObjectFloatMap)object0;
                    }
                    if(object0 instanceof ObjectSet) {
                        for(JsonValue jsonValue4 = jsonValue0.getChild("values"); jsonValue4 != null; jsonValue4 = jsonValue4.next) {
                            ((ObjectSet)object0).add(this.readValue(class1, null, jsonValue4));
                        }
                        return (ObjectSet)object0;
                    }
                    if(object0 instanceof IntMap) {
                        for(JsonValue jsonValue5 = jsonValue0.child; jsonValue5 != null; jsonValue5 = jsonValue5.next) {
                            ((IntMap)object0).put(Integer.parseInt(jsonValue5.name), this.readValue(class1, null, jsonValue5));
                        }
                        return (IntMap)object0;
                    }
                    if(object0 instanceof LongMap) {
                        for(JsonValue jsonValue6 = jsonValue0.child; jsonValue6 != null; jsonValue6 = jsonValue6.next) {
                            ((LongMap)object0).put(Long.parseLong(jsonValue6.name), this.readValue(class1, null, jsonValue6));
                        }
                        return (LongMap)object0;
                    }
                    if(object0 instanceof IntSet) {
                        for(JsonValue jsonValue7 = jsonValue0.getChild("values"); jsonValue7 != null; jsonValue7 = jsonValue7.next) {
                            ((IntSet)object0).add(jsonValue7.asInt());
                        }
                        return (IntSet)object0;
                    }
                    if(object0 instanceof ArrayMap) {
                        for(JsonValue jsonValue8 = jsonValue0.child; jsonValue8 != null; jsonValue8 = jsonValue8.next) {
                            ((ArrayMap)object0).put(jsonValue8.name, this.readValue(class1, null, jsonValue8));
                        }
                        return (ArrayMap)object0;
                    }
                    if(object0 instanceof Map) {
                        for(JsonValue jsonValue9 = jsonValue0.child; jsonValue9 != null; jsonValue9 = jsonValue9.next) {
                            if(!jsonValue9.name.equals(this.typeName)) {
                                ((Map)object0).put(jsonValue9.name, this.readValue(class1, null, jsonValue9));
                            }
                        }
                        return (Map)object0;
                    }
                    this.readFields(object0, jsonValue0);
                    return object0;
                }
                return this.readValue("value", class0, jsonValue0);
            }
        }
        if(class0 != null) {
            Serializer json$Serializer2 = (Serializer)this.classToSerializer.get(class0);
            if(json$Serializer2 != null) {
                return json$Serializer2.read(this, jsonValue0, class0);
            }
            if(ClassReflection.isAssignableFrom(Serializable.class, class0)) {
                Object object1 = this.newInstance(class0);
                ((Serializable)object1).read(this, jsonValue0);
                return object1;
            }
        }
        int v = 0;
        if(jsonValue0.isArray()) {
            if(class0 == null || class0 == Object.class) {
                class0 = Array.class;
            }
            if(ClassReflection.isAssignableFrom(Array.class, class0)) {
                Array array0 = class0 == Array.class ? new Array() : ((Array)this.newInstance(class0));
                for(JsonValue jsonValue10 = jsonValue0.child; jsonValue10 != null; jsonValue10 = jsonValue10.next) {
                    array0.add(this.readValue(class1, null, jsonValue10));
                }
                return array0;
            }
            if(ClassReflection.isAssignableFrom(Queue.class, class0)) {
                Queue queue0 = class0 == Queue.class ? new Queue() : ((Queue)this.newInstance(class0));
                for(JsonValue jsonValue11 = jsonValue0.child; jsonValue11 != null; jsonValue11 = jsonValue11.next) {
                    queue0.addLast(this.readValue(class1, null, jsonValue11));
                }
                return queue0;
            }
            if(ClassReflection.isAssignableFrom(Collection.class, class0)) {
                Collection collection0 = class0.isInterface() ? new ArrayList() : ((Collection)this.newInstance(class0));
                for(JsonValue jsonValue12 = jsonValue0.child; jsonValue12 != null; jsonValue12 = jsonValue12.next) {
                    collection0.add(this.readValue(class1, null, jsonValue12));
                }
                return collection0;
            }
            if(!class0.isArray()) {
                throw new SerializationException("Unable to convert value to required type: " + jsonValue0 + " (" + class0.getName() + ")");
            }
            Class class2 = class0.getComponentType();
            if(class1 == null) {
                class1 = class2;
            }
            Object object2 = ArrayReflection.newInstance(class2, jsonValue0.size);
            JsonValue jsonValue13 = jsonValue0.child;
            while(jsonValue13 != null) {
                ArrayReflection.set(object2, v, this.readValue(class1, null, jsonValue13));
                jsonValue13 = jsonValue13.next;
                ++v;
            }
            return object2;
        }
        if(jsonValue0.isNumber()) {
            try {
                if(class0 == null || (class0 == Float.TYPE || class0 == Float.class)) {
                    return jsonValue0.asFloat();
                }
                if(class0 == Integer.TYPE || class0 == Integer.class) {
                    return jsonValue0.asInt();
                }
                if(class0 == Long.TYPE || class0 == Long.class) {
                    return jsonValue0.asLong();
                }
                if(class0 == Double.TYPE || class0 == Double.class) {
                    return jsonValue0.asDouble();
                }
                if(class0 == String.class) {
                    return jsonValue0.asString();
                }
                if(class0 == Short.TYPE || class0 == Short.class) {
                    return jsonValue0.asShort();
                }
                if(class0 == Byte.TYPE || class0 == Byte.class) {
                    return jsonValue0.asByte();
                }
            }
            catch(NumberFormatException unused_ex) {
            }
            jsonValue14 = new JsonValue(jsonValue0.asString());
        }
        else {
            jsonValue14 = jsonValue0;
        }
        if(jsonValue14.isBoolean()) {
            try {
                boolean z = false;
                if(class0 == null) {
                    z = true;
                }
                else if(class0 == Boolean.TYPE || class0 == Boolean.class) {
                    z = true;
                }
                if(z) {
                    return Boolean.valueOf(jsonValue14.asBoolean());
                }
            }
            catch(NumberFormatException unused_ex) {
            }
            jsonValue14 = new JsonValue(jsonValue14.asString());
        }
        if(jsonValue14.isString()) {
            String s1 = jsonValue14.asString();
            if(class0 != null && class0 != String.class) {
                try {
                    if(class0 == Integer.TYPE || class0 == Integer.class) {
                        return Integer.valueOf(s1);
                    }
                    if(class0 == Float.TYPE || class0 == Float.class) {
                        return Float.valueOf(s1);
                    }
                    if(class0 == Long.TYPE || class0 == Long.class) {
                        return Long.valueOf(s1);
                    }
                    if(class0 == Double.TYPE || class0 == Double.class) {
                        return Double.valueOf(s1);
                    }
                    if(class0 == Short.TYPE || class0 == Short.class) {
                        return Short.valueOf(s1);
                    }
                    if(class0 == Byte.TYPE || class0 == Byte.class) {
                        return Byte.valueOf(s1);
                    }
                }
                catch(NumberFormatException unused_ex) {
                }
                if(class0 != Boolean.TYPE && class0 != Boolean.class) {
                    if(class0 != Character.TYPE && class0 != Character.class) {
                        if(ClassReflection.isAssignableFrom(Enum.class, class0)) {
                            Enum[] arr_enum = (Enum[])class0.getEnumConstants();
                            while(v < arr_enum.length) {
                                Enum enum0 = arr_enum[v];
                                if(s1.equals(this.convertToString(enum0))) {
                                    return enum0;
                                }
                                ++v;
                            }
                        }
                        if(class0 != CharSequence.class) {
                            throw new SerializationException("Unable to convert value to required type: " + jsonValue14 + " (" + class0.getName() + ")");
                        }
                        return s1;
                    }
                    return Character.valueOf(s1.charAt(0));
                }
                return Boolean.valueOf(s1);
            }
            return s1;
        }
        return null;
    }

    @Null
    public Object readValue(@Null Class class0, @Null Class class1, Object object0, JsonValue jsonValue0) {
        return jsonValue0 == null ? object0 : this.readValue(class0, class1, jsonValue0);
    }

    @Null
    public Object readValue(String s, @Null Class class0, JsonValue jsonValue0) {
        return this.readValue(class0, null, jsonValue0.get(s));
    }

    @Null
    public Object readValue(String s, @Null Class class0, @Null Class class1, JsonValue jsonValue0) {
        return this.readValue(class0, class1, jsonValue0.get(s));
    }

    @Null
    public Object readValue(String s, @Null Class class0, @Null Class class1, Object object0, JsonValue jsonValue0) {
        return this.readValue(class0, class1, object0, jsonValue0.get(s));
    }

    @Null
    public Object readValue(String s, @Null Class class0, Object object0, JsonValue jsonValue0) {
        JsonValue jsonValue1 = jsonValue0.get(s);
        return jsonValue1 == null ? object0 : this.readValue(class0, null, jsonValue1);
    }

    public void setDefaultSerializer(@Null Serializer json$Serializer0) {
        this.defaultSerializer = json$Serializer0;
    }

    public void setDeprecated(Class class0, String s, boolean z) {
        FieldMetadata json$FieldMetadata0 = (FieldMetadata)this.getFields(class0).get(s);
        if(json$FieldMetadata0 == null) {
            throw new SerializationException("Field not found: " + s + " (" + class0.getName() + ")");
        }
        json$FieldMetadata0.deprecated = z;
    }

    public void setElementType(Class class0, String s, Class class1) {
        FieldMetadata json$FieldMetadata0 = (FieldMetadata)this.getFields(class0).get(s);
        if(json$FieldMetadata0 == null) {
            throw new SerializationException("Field not found: " + s + " (" + class0.getName() + ")");
        }
        json$FieldMetadata0.elementType = class1;
    }

    public void setEnumNames(boolean z) {
        this.enumNames = z;
    }

    public void setIgnoreDeprecated(boolean z) {
        this.ignoreDeprecated = z;
    }

    public void setIgnoreUnknownFields(boolean z) {
        this.ignoreUnknownFields = z;
    }

    public void setOutputType(OutputType jsonWriter$OutputType0) {
        this.outputType = jsonWriter$OutputType0;
    }

    public void setQuoteLongValues(boolean z) {
        this.quoteLongValues = z;
    }

    public void setReadDeprecated(boolean z) {
        this.readDeprecated = z;
    }

    public void setSerializer(Class class0, Serializer json$Serializer0) {
        this.classToSerializer.put(class0, json$Serializer0);
    }

    public void setSortFields(boolean z) {
        this.sortFields = z;
    }

    public void setTypeName(@Null String s) {
        this.typeName = s;
    }

    public void setUsePrototypes(boolean z) {
        this.usePrototypes = z;
    }

    public void setWriter(Writer writer0) {
        if(!(writer0 instanceof JsonWriter)) {
            writer0 = new JsonWriter(writer0);
        }
        this.writer = (JsonWriter)writer0;
        this.writer.setOutputType(this.outputType);
        this.writer.setQuoteLongValues(this.quoteLongValues);
    }

    // 去混淆评级： 低(30)
    public String toJson(@Null Object object0) [...] // 潜在的解密器

    public String toJson(@Null Object object0, @Null Class class0) {
        return this.toJson(object0, class0, null);
    }

    public String toJson(@Null Object object0, @Null Class class0, @Null Class class1) [...] // 潜在的解密器

    public void toJson(@Null Object object0, FileHandle fileHandle0) {
        this.toJson(object0, (object0 == null ? null : object0.getClass()), null, fileHandle0);
    }

    public void toJson(@Null Object object0, Writer writer0) {
        this.toJson(object0, (object0 == null ? null : object0.getClass()), null, writer0);
    }

    public void toJson(@Null Object object0, @Null Class class0, FileHandle fileHandle0) {
        this.toJson(object0, class0, null, fileHandle0);
    }

    public void toJson(@Null Object object0, @Null Class class0, Writer writer0) {
        this.toJson(object0, class0, null, writer0);
    }

    public void toJson(@Null Object object0, @Null Class class0, @Null Class class1, FileHandle fileHandle0) {
        Writer writer0;
        try {
            writer0 = fileHandle0.writer(false, "UTF-8");
            this.toJson(object0, class0, class1, writer0);
        }
        catch(Exception exception0) {
            throw new SerializationException("Error writing file: " + fileHandle0, exception0);
        }
        finally {
            StreamUtils.closeQuietly(writer0);
        }
    }

    public void toJson(@Null Object object0, @Null Class class0, @Null Class class1, Writer writer0) {
        this.setWriter(writer0);
        try {
            this.writeValue(object0, class0, class1);
        }
        finally {
            StreamUtils.closeQuietly(this.writer);
            this.writer = null;
        }
    }

    public void writeArrayEnd() {
        try {
            this.writer.pop();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeArrayStart() {
        try {
            this.writer.array();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeArrayStart(String s) {
        try {
            this.writer.name(s);
            this.writer.array();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeField(Object object0, String s) {
        this.writeField(object0, s, s, null);
    }

    public void writeField(Object object0, String s, @Null Class class0) {
        this.writeField(object0, s, s, class0);
    }

    public void writeField(Object object0, String s, String s1) {
        this.writeField(object0, s, s1, null);
    }

    public void writeField(Object object0, String s, String s1, @Null Class class0) {
        Class class1 = object0.getClass();
        FieldMetadata json$FieldMetadata0 = (FieldMetadata)this.getFields(class1).get(s);
        if(json$FieldMetadata0 != null) {
            Field field0 = json$FieldMetadata0.field;
            if(class0 == null) {
                class0 = json$FieldMetadata0.elementType;
            }
            try {
                this.writer.name(s1);
                this.writeValue(field0.get(object0), field0.getType(), class0);
                return;
            }
            catch(ReflectionException reflectionException0) {
                throw new SerializationException("Error accessing field: " + field0.getName() + " (" + class1.getName() + ")", reflectionException0);
            }
            catch(SerializationException serializationException0) {
                serializationException0.addTrace(field0 + " (" + class1.getName() + ")");
                throw serializationException0;
            }
            catch(Exception exception0) {
                SerializationException serializationException1 = new SerializationException(exception0);
                serializationException1.addTrace(field0 + " (" + class1.getName() + ")");
                throw serializationException1;
            }
        }
        throw new SerializationException("Field not found: " + s + " (" + class1.getName() + ")");
    }

    public void writeFields(Object object0) {
        Field field0;
        Class class0 = object0.getClass();
        Object[] arr_object = this.getDefaultValues(class0);
        OrderedMap orderedMap0 = this.getFields(class0);
        Array array0 = orderedMap0.orderedKeys();
        int v = array0.size;
        int v1 = 0;
        int v2 = 0;
        while(v1 < v) {
            FieldMetadata json$FieldMetadata0 = (FieldMetadata)orderedMap0.get(array0.get(v1));
            if(!this.ignoreDeprecated || !json$FieldMetadata0.deprecated) {
                try {
                    field0 = json$FieldMetadata0.field;
                    Object object1 = field0.get(object0);
                    if(arr_object != null) {
                        Object object2 = arr_object[v2];
                        if(object1 != null || object2 != null) {
                            if(object1 == null || object2 == null) {
                                ++v2;
                                goto label_29;
                            }
                            else if(!object1.equals(object2)) {
                                if(!object1.getClass().isArray() || !object2.getClass().isArray()) {
                                    ++v2;
                                    goto label_29;
                                }
                                else {
                                    this.equals1[0] = object1;
                                    this.equals2[0] = object2;
                                    if(!Arrays.deepEquals(this.equals1, this.equals2)) {
                                        ++v2;
                                        goto label_29;
                                    }
                                }
                            }
                        }
                        ++v2;
                        goto label_41;
                    }
                label_29:
                    this.writer.name(field0.getName());
                    this.writeValue(object1, field0.getType(), json$FieldMetadata0.elementType);
                }
                catch(ReflectionException reflectionException0) {
                    throw new SerializationException("Error accessing field: " + field0.getName() + " (" + class0.getName() + ")", reflectionException0);
                }
                catch(SerializationException serializationException0) {
                    serializationException0.addTrace(field0 + " (" + class0.getName() + ")");
                    throw serializationException0;
                }
                catch(Exception exception0) {
                    SerializationException serializationException1 = new SerializationException(exception0);
                    serializationException1.addTrace(field0 + " (" + class0.getName() + ")");
                    throw serializationException1;
                }
            }
        label_41:
            ++v1;
        }
    }

    public void writeObjectEnd() {
        try {
            this.writer.pop();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeObjectStart() {
        try {
            this.writer.object();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeObjectStart(Class class0, @Null Class class1) {
        try {
            this.writer.object();
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        if(class1 == null || class1 != class0) {
            this.writeType(class0);
        }
    }

    public void writeObjectStart(String s) {
        try {
            this.writer.name(s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        this.writeObjectStart();
    }

    public void writeObjectStart(String s, Class class0, @Null Class class1) {
        try {
            this.writer.name(s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        this.writeObjectStart(class0, class1);
    }

    public void writeType(Class class0) {
        if(this.typeName == null) {
            return;
        }
        String s = this.getTag(class0);
        if(s == null) {
            s = class0.getName();
        }
        try {
            this.writer.set(this.typeName, s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
    }

    public void writeValue(@Null Object object0) {
        if(object0 == null) {
            this.writeValue(null, null, null);
            return;
        }
        this.writeValue(object0, object0.getClass(), null);
    }

    public void writeValue(@Null Object object0, @Null Class class0) {
        this.writeValue(object0, class0, null);
    }

    public void writeValue(@Null Object object0, @Null Class class0, @Null Class class1) {
        try {
            if(object0 == null) {
                this.writer.value(null);
                return;
            }
            if((class0 == null || !class0.isPrimitive()) && class0 != String.class && class0 != Integer.class && class0 != Boolean.class && class0 != Float.class && class0 != Long.class && class0 != Double.class && class0 != Short.class && class0 != Byte.class && class0 != Character.class) {
                Class class2 = object0.getClass();
                if(!class2.isPrimitive() && class2 != String.class && class2 != Integer.class && class2 != Boolean.class && class2 != Float.class && class2 != Long.class && class2 != Double.class && class2 != Short.class && class2 != Byte.class && class2 != Character.class) {
                    if(object0 instanceof Serializable) {
                        this.writeObjectStart(class2, class0);
                        ((Serializable)object0).write(this);
                        this.writeObjectEnd();
                        return;
                    }
                    int v = 0;
                    Serializer json$Serializer0 = (Serializer)this.classToSerializer.get(class2);
                    if(json$Serializer0 != null) {
                        json$Serializer0.write(this, object0, class0);
                        return;
                    }
                    if(object0 instanceof Array) {
                        if(class0 != null && class2 != class0 && class2 != Array.class) {
                            throw new SerializationException("Serialization of an Array other than the known type is not supported.\nKnown type: " + class0 + "\nActual type: " + class2);
                        }
                        this.writeArrayStart();
                        int v1 = ((Array)object0).size;
                        while(v < v1) {
                            this.writeValue(((Array)object0).get(v), class1, null);
                            ++v;
                        }
                        this.writeArrayEnd();
                        return;
                    }
                    if(object0 instanceof Queue) {
                        if(class0 != null && class2 != class0 && class2 != Queue.class) {
                            throw new SerializationException("Serialization of a Queue other than the known type is not supported.\nKnown type: " + class0 + "\nActual type: " + class2);
                        }
                        this.writeArrayStart();
                        int v2 = ((Queue)object0).size;
                        while(v < v2) {
                            this.writeValue(((Queue)object0).get(v), class1, null);
                            ++v;
                        }
                        this.writeArrayEnd();
                        return;
                    }
                    if(object0 instanceof Collection) {
                        if(this.typeName != null && class2 != ArrayList.class && (class0 == null || class0 != class2)) {
                            this.writeObjectStart(class2, class0);
                            this.writeArrayStart("items");
                            for(Object object1: ((Collection)object0)) {
                                this.writeValue(object1, class1, null);
                            }
                            this.writeArrayEnd();
                            this.writeObjectEnd();
                            return;
                        }
                        this.writeArrayStart();
                        for(Object object2: ((Collection)object0)) {
                            this.writeValue(object2, class1, null);
                        }
                        this.writeArrayEnd();
                        return;
                    }
                    if(class2.isArray()) {
                        if(class1 == null) {
                            class1 = class2.getComponentType();
                        }
                        int v3 = ArrayReflection.getLength(object0);
                        this.writeArrayStart();
                        while(v < v3) {
                            this.writeValue(ArrayReflection.get(object0, v), class1, null);
                            ++v;
                        }
                        this.writeArrayEnd();
                        return;
                    }
                    if(object0 instanceof ObjectMap) {
                        if(class0 == null) {
                            class0 = ObjectMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object3: ((ObjectMap)object0).entries()) {
                            this.writer.name(this.convertToString(((Entry)object3).key));
                            this.writeValue(((Entry)object3).value, class1, null);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof ObjectIntMap) {
                        if(class0 == null) {
                            class0 = ObjectIntMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object4: ((ObjectIntMap)object0).entries()) {
                            this.writer.name(this.convertToString(((com.badlogic.gdx.utils.ObjectIntMap.Entry)object4).key));
                            this.writeValue(((com.badlogic.gdx.utils.ObjectIntMap.Entry)object4).value, Integer.class);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof ObjectFloatMap) {
                        if(class0 == null) {
                            class0 = ObjectFloatMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object5: ((ObjectFloatMap)object0).entries()) {
                            this.writer.name(this.convertToString(((com.badlogic.gdx.utils.ObjectFloatMap.Entry)object5).key));
                            this.writeValue(((com.badlogic.gdx.utils.ObjectFloatMap.Entry)object5).value, Float.class);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof ObjectSet) {
                        if(class0 == null) {
                            class0 = ObjectSet.class;
                        }
                        this.writeObjectStart(class2, class0);
                        this.writer.name("values");
                        this.writeArrayStart();
                        for(Object object6: ((ObjectSet)object0)) {
                            this.writeValue(object6, class1, null);
                        }
                        this.writeArrayEnd();
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof IntMap) {
                        if(class0 == null) {
                            class0 = IntMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object7: ((IntMap)object0).entries()) {
                            this.writer.name(String.valueOf(((com.badlogic.gdx.utils.IntMap.Entry)object7).key));
                            this.writeValue(((com.badlogic.gdx.utils.IntMap.Entry)object7).value, class1, null);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof LongMap) {
                        if(class0 == null) {
                            class0 = LongMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object8: ((LongMap)object0).entries()) {
                            this.writer.name(String.valueOf(((com.badlogic.gdx.utils.LongMap.Entry)object8).key));
                            this.writeValue(((com.badlogic.gdx.utils.LongMap.Entry)object8).value, class1, null);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof IntSet) {
                        if(class0 == null) {
                            class0 = IntSet.class;
                        }
                        this.writeObjectStart(class2, class0);
                        this.writer.name("values");
                        this.writeArrayStart();
                        IntSetIterator intSet$IntSetIterator0 = ((IntSet)object0).iterator();
                        while(intSet$IntSetIterator0.hasNext) {
                            this.writeValue(intSet$IntSetIterator0.next(), Integer.class, null);
                        }
                        this.writeArrayEnd();
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof ArrayMap) {
                        if(class0 == null) {
                            class0 = ArrayMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        int v4 = ((ArrayMap)object0).size;
                        while(v < v4) {
                            this.writer.name(this.convertToString(((ArrayMap)object0).keys[v]));
                            this.writeValue(((ArrayMap)object0).values[v], class1, null);
                            ++v;
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(object0 instanceof Map) {
                        if(class0 == null) {
                            class0 = HashMap.class;
                        }
                        this.writeObjectStart(class2, class0);
                        for(Object object9: ((Map)object0).entrySet()) {
                            this.writer.name(this.convertToString(((Map.Entry)object9).getKey()));
                            this.writeValue(((Map.Entry)object9).getValue(), class1, null);
                        }
                        this.writeObjectEnd();
                        return;
                    }
                    if(ClassReflection.isAssignableFrom(Enum.class, class2)) {
                        if(this.typeName != null && (class0 == null || class0 != class2)) {
                            if(class2.getEnumConstants() == null) {
                                class2 = class2.getSuperclass();
                            }
                            this.writeObjectStart(class2, null);
                            this.writer.name("value");
                            this.writer.value(this.convertToString(((Enum)object0)));
                            this.writeObjectEnd();
                            return;
                        }
                        this.writer.value(this.convertToString(((Enum)object0)));
                        return;
                    }
                    this.writeObjectStart(class2, class0);
                    this.writeFields(object0);
                    this.writeObjectEnd();
                    return;
                }
                this.writeObjectStart(class2, null);
                this.writeValue("value", object0);
                this.writeObjectEnd();
                return;
            }
            this.writer.value(object0);
            return;
        }
        catch(IOException iOException0) {
        }
        throw new SerializationException(iOException0);
    }

    public void writeValue(String s, @Null Object object0) {
        try {
            this.writer.name(s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        if(object0 == null) {
            this.writeValue(null, null, null);
            return;
        }
        this.writeValue(object0, object0.getClass(), null);
    }

    public void writeValue(String s, @Null Object object0, @Null Class class0) {
        try {
            this.writer.name(s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        this.writeValue(object0, class0, null);
    }

    public void writeValue(String s, @Null Object object0, @Null Class class0, @Null Class class1) {
        try {
            this.writer.name(s);
        }
        catch(IOException iOException0) {
            throw new SerializationException(iOException0);
        }
        this.writeValue(object0, class0, class1);
    }
}

