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
import java.io.StringWriter;
import java.io.Writer;
import java.security.AccessControlException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Json {
   private static final boolean debug = false;
   private JsonWriter writer;
   private String typeName = "class";
   private boolean usePrototypes = true;
   private JsonWriter.OutputType outputType;
   private boolean quoteLongValues;
   private boolean ignoreUnknownFields;
   private boolean ignoreDeprecated;
   private boolean readDeprecated;
   private boolean enumNames = true;
   private boolean sortFields;
   private Json.Serializer defaultSerializer;
   private final ObjectMap typeToFields = new ObjectMap();
   private final ObjectMap tagToClass = new ObjectMap();
   private final ObjectMap classToTag = new ObjectMap();
   private final ObjectMap classToSerializer = new ObjectMap();
   private final ObjectMap classToDefaultValues = new ObjectMap();
   private final Object[] equals1 = new Object[]{null};
   private final Object[] equals2 = new Object[]{null};

   public Json() {
      this.outputType = JsonWriter.OutputType.minimal;
   }

   public Json(JsonWriter.OutputType outputType) {
      this.outputType = outputType;
   }

   public void setIgnoreUnknownFields(boolean ignoreUnknownFields) {
      this.ignoreUnknownFields = ignoreUnknownFields;
   }

   public boolean getIgnoreUnknownFields() {
      return this.ignoreUnknownFields;
   }

   public void setIgnoreDeprecated(boolean ignoreDeprecated) {
      this.ignoreDeprecated = ignoreDeprecated;
   }

   public void setReadDeprecated(boolean readDeprecated) {
      this.readDeprecated = readDeprecated;
   }

   public void setOutputType(JsonWriter.OutputType outputType) {
      this.outputType = outputType;
   }

   public void setQuoteLongValues(boolean quoteLongValues) {
      this.quoteLongValues = quoteLongValues;
   }

   public void setEnumNames(boolean enumNames) {
      this.enumNames = enumNames;
   }

   public void addClassTag(String tag, Class type) {
      this.tagToClass.put(tag, type);
      this.classToTag.put(type, tag);
   }

   @Null
   public Class getClass(String tag) {
      return (Class)this.tagToClass.get(tag);
   }

   @Null
   public String getTag(Class type) {
      return (String)this.classToTag.get(type);
   }

   public void setTypeName(@Null String typeName) {
      this.typeName = typeName;
   }

   public void setDefaultSerializer(@Null Json.Serializer defaultSerializer) {
      this.defaultSerializer = defaultSerializer;
   }

   public void setSerializer(Class type, Json.Serializer serializer) {
      this.classToSerializer.put(type, serializer);
   }

   public Json.Serializer getSerializer(Class type) {
      return (Json.Serializer)this.classToSerializer.get(type);
   }

   public void setUsePrototypes(boolean usePrototypes) {
      this.usePrototypes = usePrototypes;
   }

   public void setElementType(Class type, String fieldName, Class elementType) {
      Json.FieldMetadata metadata = (Json.FieldMetadata)this.getFields(type).get(fieldName);
      if (metadata == null) {
         throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
      } else {
         metadata.elementType = elementType;
      }
   }

   public void setDeprecated(Class type, String fieldName, boolean deprecated) {
      Json.FieldMetadata metadata = (Json.FieldMetadata)this.getFields(type).get(fieldName);
      if (metadata == null) {
         throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
      } else {
         metadata.deprecated = deprecated;
      }
   }

   public void setSortFields(boolean sortFields) {
      this.sortFields = sortFields;
   }

   private OrderedMap getFields(Class type) {
      OrderedMap fields = (OrderedMap<String, Json.FieldMetadata>)this.typeToFields.get(type);
      if (fields != null) {
         return fields;
      } else {
         Array classHierarchy = new Array();

         for (Class nextClass = type; nextClass != Object.class; nextClass = nextClass.getSuperclass()) {
            classHierarchy.add(nextClass);
         }

         ArrayList allFields = new ArrayList();

         for (int i = classHierarchy.size - 1; i >= 0; i--) {
            java.util.Collections.addAll(allFields, ClassReflection.getDeclaredFields((Class)classHierarchy.get(i)));
         }

         OrderedMap nameToField = new OrderedMap(allFields.size());
         int i = 0;

         for (int n = allFields.size(); i < n; i++) {
            Field field = (Field)allFields.get(i);
            if (!field.isTransient() && !field.isStatic() && !field.isSynthetic()) {
               if (!field.isAccessible()) {
                  try {
                     field.setAccessible(true);
                  } catch (AccessControlException var11) {
                     continue;
                  }
               }

               nameToField.put(field.getName(), new Json.FieldMetadata(field));
            }
         }

         if (this.sortFields) {
            nameToField.keys.sort();
         }

         this.typeToFields.put(type, nameToField);
         return nameToField;
      }
   }

   public String toJson(@Null Object object) {
      return this.toJson(object, object == null ? null : object.getClass(), (Class)null);
   }

   public String toJson(@Null Object object, @Null Class knownType) {
      return this.toJson(object, knownType, (Class)null);
   }

   public String toJson(@Null Object object, @Null Class knownType, @Null Class elementType) {
      StringWriter buffer = new StringWriter();
      this.toJson(object, knownType, elementType, buffer);
      return buffer.toString();
   }

   public void toJson(@Null Object object, FileHandle file) {
      this.toJson(object, object == null ? null : object.getClass(), null, file);
   }

   public void toJson(@Null Object object, @Null Class knownType, FileHandle file) {
      this.toJson(object, knownType, null, file);
   }

   public void toJson(@Null Object object, @Null Class knownType, @Null Class elementType, FileHandle file) {
      Writer writer = null;

      try {
         writer = file.writer(false, "UTF-8");
         this.toJson(object, knownType, elementType, writer);
      } catch (Exception var10) {
         throw new SerializationException("Error writing file: " + file, var10);
      } finally {
         StreamUtils.closeQuietly(writer);
      }
   }

   public void toJson(@Null Object object, Writer writer) {
      this.toJson(object, object == null ? null : object.getClass(), null, writer);
   }

   public void toJson(@Null Object object, @Null Class knownType, Writer writer) {
      this.toJson(object, knownType, null, writer);
   }

   public void toJson(@Null Object object, @Null Class knownType, @Null Class elementType, Writer writer) {
      this.setWriter(writer);

      try {
         this.writeValue(object, knownType, elementType);
      } finally {
         StreamUtils.closeQuietly(this.writer);
         this.writer = null;
      }
   }

   public void setWriter(Writer writer) {
      if (!(writer instanceof JsonWriter)) {
         writer = new JsonWriter(writer);
      }

      this.writer = (JsonWriter)writer;
      this.writer.setOutputType(this.outputType);
      this.writer.setQuoteLongValues(this.quoteLongValues);
   }

   public JsonWriter getWriter() {
      return this.writer;
   }

   public void writeFields(Object object) {
      Class type = object.getClass();
      Object[] defaultValues = this.getDefaultValues(type);
      OrderedMap fields = this.getFields(type);
      int defaultIndex = 0;
      Array fieldNames = fields.orderedKeys();
      int i = 0;

      for (int n = fieldNames.size; i < n; i++) {
         Json.FieldMetadata metadata = (Json.FieldMetadata)fields.get(fieldNames.get(i));
         if (!this.ignoreDeprecated || !metadata.deprecated) {
            Field field = metadata.field;

            try {
               Object value = field.get(object);
               if (defaultValues != null) {
                  Object defaultValue = defaultValues[defaultIndex++];
                  if (value == null && defaultValue == null) {
                     continue;
                  }

                  if (value != null && defaultValue != null) {
                     if (value.equals(defaultValue)) {
                        continue;
                     }

                     if (value.getClass().isArray() && defaultValue.getClass().isArray()) {
                        this.equals1[0] = value;
                        this.equals2[0] = defaultValue;
                        if (Arrays.deepEquals(this.equals1, this.equals2)) {
                           continue;
                        }
                     }
                  }
               }

               this.writer.name(field.getName());
               this.writeValue(value, field.getType(), metadata.elementType);
            } catch (ReflectionException var13) {
               throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", var13);
            } catch (SerializationException var14) {
               var14.addTrace(field + " (" + type.getName() + ")");
               throw var14;
            } catch (Exception var15) {
               SerializationException ex = new SerializationException(var15);
               ex.addTrace(field + " (" + type.getName() + ")");
               throw ex;
            }
         }
      }
   }

   @Null
   private Object[] getDefaultValues(Class type) {
      if (!this.usePrototypes) {
         return null;
      } else if (this.classToDefaultValues.containsKey(type)) {
         return (Object[])this.classToDefaultValues.get(type);
      } else {
         Object object;
         try {
            object = this.newInstance(type);
         } catch (Exception var16) {
            this.classToDefaultValues.put(type, null);
            return null;
         }

         OrderedMap fields = this.getFields(type);
         Object[] values = new Object[fields.size];
         this.classToDefaultValues.put(type, values);
         int defaultIndex = 0;
         Array fieldNames = fields.orderedKeys();
         int i = 0;

         for (int n = fieldNames.size; i < n; i++) {
            Json.FieldMetadata metadata = (Json.FieldMetadata)fields.get(fieldNames.get(i));
            if (!this.ignoreDeprecated || !metadata.deprecated) {
               Field field = metadata.field;

               try {
                  values[defaultIndex++] = field.get(object);
               } catch (ReflectionException var13) {
                  throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", var13);
               } catch (SerializationException var14) {
                  var14.addTrace(field + " (" + type.getName() + ")");
                  throw var14;
               } catch (RuntimeException var15) {
                  SerializationException ex = new SerializationException(var15);
                  ex.addTrace(field + " (" + type.getName() + ")");
                  throw ex;
               }
            }
         }

         return values;
      }
   }

   public void writeField(Object object, String name) {
      this.writeField(object, name, name, null);
   }

   public void writeField(Object object, String name, @Null Class elementType) {
      this.writeField(object, name, name, elementType);
   }

   public void writeField(Object object, String fieldName, String jsonName) {
      this.writeField(object, fieldName, jsonName, null);
   }

   public void writeField(Object object, String fieldName, String jsonName, @Null Class elementType) {
      Class type = object.getClass();
      Json.FieldMetadata metadata = (Json.FieldMetadata)this.getFields(type).get(fieldName);
      if (metadata == null) {
         throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
      } else {
         Field field = metadata.field;
         if (elementType == null) {
            elementType = metadata.elementType;
         }

         try {
            this.writer.name(jsonName);
            this.writeValue(field.get(object), field.getType(), elementType);
         } catch (ReflectionException var10) {
            throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", var10);
         } catch (SerializationException var11) {
            var11.addTrace(field + " (" + type.getName() + ")");
            throw var11;
         } catch (Exception var12) {
            SerializationException ex = new SerializationException(var12);
            ex.addTrace(field + " (" + type.getName() + ")");
            throw ex;
         }
      }
   }

   public void writeValue(String name, @Null Object value) {
      try {
         this.writer.name(name);
      } catch (IOException var4) {
         throw new SerializationException(var4);
      }

      if (value == null) {
         this.writeValue(value, null, null);
      } else {
         this.writeValue(value, value.getClass(), null);
      }
   }

   public void writeValue(String name, @Null Object value, @Null Class knownType) {
      try {
         this.writer.name(name);
      } catch (IOException var5) {
         throw new SerializationException(var5);
      }

      this.writeValue(value, knownType, null);
   }

   public void writeValue(String name, @Null Object value, @Null Class knownType, @Null Class elementType) {
      try {
         this.writer.name(name);
      } catch (IOException var6) {
         throw new SerializationException(var6);
      }

      this.writeValue(value, knownType, elementType);
   }

   public void writeValue(@Null Object value) {
      if (value == null) {
         this.writeValue(value, null, null);
      } else {
         this.writeValue(value, value.getClass(), null);
      }
   }

   public void writeValue(@Null Object value, @Null Class knownType) {
      this.writeValue(value, knownType, null);
   }

   public void writeValue(@Null Object value, @Null Class knownType, @Null Class elementType) {
      try {
         if (value == null) {
            this.writer.value(null);
         } else if ((knownType == null || !knownType.isPrimitive())
            && knownType != String.class
            && knownType != Integer.class
            && knownType != Boolean.class
            && knownType != Float.class
            && knownType != Long.class
            && knownType != Double.class
            && knownType != Short.class
            && knownType != Byte.class
            && knownType != Character.class) {
            Class actualType = value.getClass();
            if (actualType.isPrimitive()
               || actualType == String.class
               || actualType == Integer.class
               || actualType == Boolean.class
               || actualType == Float.class
               || actualType == Long.class
               || actualType == Double.class
               || actualType == Short.class
               || actualType == Byte.class
               || actualType == Character.class) {
               this.writeObjectStart(actualType, null);
               this.writeValue("value", value);
               this.writeObjectEnd();
            } else if (value instanceof Json.Serializable) {
               this.writeObjectStart(actualType, knownType);
               ((Json.Serializable)value).write(this);
               this.writeObjectEnd();
            } else {
               Json.Serializer serializer = (Json.Serializer)this.classToSerializer.get(actualType);
               if (serializer != null) {
                  serializer.write(this, value, knownType);
               } else if (value instanceof Array) {
                  if (knownType != null && actualType != knownType && actualType != Array.class) {
                     throw new SerializationException(
                        "Serialization of an Array other than the known type is not supported.\nKnown type: " + knownType + "\nActual type: " + actualType
                     );
                  } else {
                     this.writeArrayStart();
                     Array array = (Array)value;
                     int i = 0;

                     for (int n = array.size; i < n; i++) {
                        this.writeValue(array.get(i), elementType, null);
                     }

                     this.writeArrayEnd();
                  }
               } else if (value instanceof Queue) {
                  if (knownType != null && actualType != knownType && actualType != Queue.class) {
                     throw new SerializationException(
                        "Serialization of a Queue other than the known type is not supported.\nKnown type: " + knownType + "\nActual type: " + actualType
                     );
                  } else {
                     this.writeArrayStart();
                     Queue queue = (Queue)value;
                     int i = 0;

                     for (int n = queue.size; i < n; i++) {
                        this.writeValue(queue.get(i), elementType, null);
                     }

                     this.writeArrayEnd();
                  }
               } else if (value instanceof Collection) {
                  if (this.typeName != null && actualType != ArrayList.class && (knownType == null || knownType != actualType)) {
                     this.writeObjectStart(actualType, knownType);
                     this.writeArrayStart("items");

                     for (Object item : (Collection)value) {
                        this.writeValue(item, elementType, null);
                     }

                     this.writeArrayEnd();
                     this.writeObjectEnd();
                  } else {
                     this.writeArrayStart();

                     for (Object item : (Collection)value) {
                        this.writeValue(item, elementType, null);
                     }

                     this.writeArrayEnd();
                  }
               } else if (actualType.isArray()) {
                  if (elementType == null) {
                     elementType = actualType.getComponentType();
                  }

                  int length = ArrayReflection.getLength(value);
                  this.writeArrayStart();

                  for (int i = 0; i < length; i++) {
                     this.writeValue(ArrayReflection.get(value, i), elementType, null);
                  }

                  this.writeArrayEnd();
               } else if (value instanceof ObjectMap) {
                  if (knownType == null) {
                     knownType = ObjectMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (ObjectMap.Entry entry : ((ObjectMap)value).entries()) {
                     this.writer.name(this.convertToString(entry.key));
                     this.writeValue(entry.value, elementType, null);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof ObjectIntMap) {
                  if (knownType == null) {
                     knownType = ObjectIntMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (ObjectIntMap.Entry entry : ((ObjectIntMap)value).entries()) {
                     this.writer.name(this.convertToString(entry.key));
                     this.writeValue(entry.value, Integer.class);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof ObjectFloatMap) {
                  if (knownType == null) {
                     knownType = ObjectFloatMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (ObjectFloatMap.Entry entry : ((ObjectFloatMap)value).entries()) {
                     this.writer.name(this.convertToString(entry.key));
                     this.writeValue(entry.value, Float.class);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof ObjectSet) {
                  if (knownType == null) {
                     knownType = ObjectSet.class;
                  }

                  this.writeObjectStart(actualType, knownType);
                  this.writer.name("values");
                  this.writeArrayStart();

                  for (Object entry : (ObjectSet)value) {
                     this.writeValue(entry, elementType, null);
                  }

                  this.writeArrayEnd();
                  this.writeObjectEnd();
               } else if (value instanceof IntMap) {
                  if (knownType == null) {
                     knownType = IntMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (IntMap.Entry entry : ((IntMap)value).entries()) {
                     this.writer.name(String.valueOf(entry.key));
                     this.writeValue(entry.value, elementType, null);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof LongMap) {
                  if (knownType == null) {
                     knownType = LongMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (LongMap.Entry entry : ((LongMap)value).entries()) {
                     this.writer.name(String.valueOf(entry.key));
                     this.writeValue(entry.value, elementType, null);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof IntSet) {
                  if (knownType == null) {
                     knownType = IntSet.class;
                  }

                  this.writeObjectStart(actualType, knownType);
                  this.writer.name("values");
                  this.writeArrayStart();
                  IntSet.IntSetIterator iter = ((IntSet)value).iterator();

                  while (iter.hasNext) {
                     this.writeValue(iter.next(), Integer.class, null);
                  }

                  this.writeArrayEnd();
                  this.writeObjectEnd();
               } else if (value instanceof ArrayMap) {
                  if (knownType == null) {
                     knownType = ArrayMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);
                  ArrayMap map = (ArrayMap)value;
                  int i = 0;

                  for (int n = map.size; i < n; i++) {
                     this.writer.name(this.convertToString(map.keys[i]));
                     this.writeValue(map.values[i], elementType, null);
                  }

                  this.writeObjectEnd();
               } else if (value instanceof Map) {
                  if (knownType == null) {
                     knownType = HashMap.class;
                  }

                  this.writeObjectStart(actualType, knownType);

                  for (Entry entry : ((Map)value).entrySet()) {
                     this.writer.name(this.convertToString(entry.getKey()));
                     this.writeValue(entry.getValue(), elementType, null);
                  }

                  this.writeObjectEnd();
               } else if (!ClassReflection.isAssignableFrom(Enum.class, actualType)) {
                  this.writeObjectStart(actualType, knownType);
                  this.writeFields(value);
                  this.writeObjectEnd();
               } else {
                  if (this.typeName != null && (knownType == null || knownType != actualType)) {
                     if (actualType.getEnumConstants() == null) {
                        actualType = actualType.getSuperclass();
                     }

                     this.writeObjectStart(actualType, null);
                     this.writer.name("value");
                     this.writer.value(this.convertToString((Enum)value));
                     this.writeObjectEnd();
                  } else {
                     this.writer.value(this.convertToString((Enum)value));
                  }
               }
            }
         } else {
            this.writer.value(value);
         }
      } catch (IOException var9) {
         throw new SerializationException(var9);
      }
   }

   public void writeObjectStart(String name) {
      try {
         this.writer.name(name);
      } catch (IOException var3) {
         throw new SerializationException(var3);
      }

      this.writeObjectStart();
   }

   public void writeObjectStart(String name, Class actualType, @Null Class knownType) {
      try {
         this.writer.name(name);
      } catch (IOException var5) {
         throw new SerializationException(var5);
      }

      this.writeObjectStart(actualType, knownType);
   }

   public void writeObjectStart() {
      try {
         this.writer.object();
      } catch (IOException var2) {
         throw new SerializationException(var2);
      }
   }

   public void writeObjectStart(Class actualType, @Null Class knownType) {
      try {
         this.writer.object();
      } catch (IOException var4) {
         throw new SerializationException(var4);
      }

      if (knownType == null || knownType != actualType) {
         this.writeType(actualType);
      }
   }

   public void writeObjectEnd() {
      try {
         this.writer.pop();
      } catch (IOException var2) {
         throw new SerializationException(var2);
      }
   }

   public void writeArrayStart(String name) {
      try {
         this.writer.name(name);
         this.writer.array();
      } catch (IOException var3) {
         throw new SerializationException(var3);
      }
   }

   public void writeArrayStart() {
      try {
         this.writer.array();
      } catch (IOException var2) {
         throw new SerializationException(var2);
      }
   }

   public void writeArrayEnd() {
      try {
         this.writer.pop();
      } catch (IOException var2) {
         throw new SerializationException(var2);
      }
   }

   public void writeType(Class type) {
      if (this.typeName != null) {
         String className = this.getTag(type);
         if (className == null) {
            className = type.getName();
         }

         try {
            this.writer.set(this.typeName, className);
         } catch (IOException var4) {
            throw new SerializationException(var4);
         }
      }
   }

   @Null
   public Object fromJson(Class type, Reader reader) {
      return this.readValue(type, null, new JsonReader().parse(reader));
   }

   @Null
   public Object fromJson(Class type, Class elementType, Reader reader) {
      return this.readValue(type, elementType, new JsonReader().parse(reader));
   }

   @Null
   public Object fromJson(Class type, InputStream input) {
      return this.readValue(type, null, new JsonReader().parse(input));
   }

   @Null
   public Object fromJson(Class type, Class elementType, InputStream input) {
      return this.readValue(type, elementType, new JsonReader().parse(input));
   }

   @Null
   public Object fromJson(Class type, FileHandle file) {
      try {
         return this.readValue(type, null, new JsonReader().parse(file));
      } catch (Exception var4) {
         throw new SerializationException("Error reading file: " + file, var4);
      }
   }

   @Null
   public Object fromJson(Class type, Class elementType, FileHandle file) {
      try {
         return this.readValue(type, elementType, new JsonReader().parse(file));
      } catch (Exception var5) {
         throw new SerializationException("Error reading file: " + file, var5);
      }
   }

   @Null
   public Object fromJson(Class type, char[] data, int offset, int length) {
      return this.readValue(type, null, new JsonReader().parse(data, offset, length));
   }

   @Null
   public Object fromJson(Class type, Class elementType, char[] data, int offset, int length) {
      return this.readValue(type, elementType, new JsonReader().parse(data, offset, length));
   }

   @Null
   public Object fromJson(Class type, String json) {
      return this.readValue(type, null, new JsonReader().parse(json));
   }

   @Null
   public Object fromJson(Class type, Class elementType, String json) {
      return this.readValue(type, elementType, new JsonReader().parse(json));
   }

   public void readField(Object object, String name, JsonValue jsonData) {
      this.readField(object, name, name, null, jsonData);
   }

   public void readField(Object object, String name, @Null Class elementType, JsonValue jsonData) {
      this.readField(object, name, name, elementType, jsonData);
   }

   public void readField(Object object, String fieldName, String jsonName, JsonValue jsonData) {
      this.readField(object, fieldName, jsonName, null, jsonData);
   }

   public void readField(Object object, String fieldName, String jsonName, @Null Class elementType, JsonValue jsonMap) {
      Class type = object.getClass();
      Json.FieldMetadata metadata = (Json.FieldMetadata)this.getFields(type).get(fieldName);
      if (metadata == null) {
         throw new SerializationException("Field not found: " + fieldName + " (" + type.getName() + ")");
      } else {
         Field field = metadata.field;
         if (elementType == null) {
            elementType = metadata.elementType;
         }

         this.readField(object, field, jsonName, elementType, jsonMap);
      }
   }

   public void readField(@Null Object object, Field field, String jsonName, @Null Class elementType, JsonValue jsonMap) {
      JsonValue jsonValue = jsonMap.get(jsonName);
      if (jsonValue != null) {
         try {
            field.set(object, this.readValue(field.getType(), elementType, jsonValue));
         } catch (ReflectionException var9) {
            throw new SerializationException("Error accessing field: " + field.getName() + " (" + field.getDeclaringClass().getName() + ")", var9);
         } catch (SerializationException var10) {
            var10.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
            throw var10;
         } catch (RuntimeException var11) {
            SerializationException ex = new SerializationException(var11);
            ex.addTrace(jsonValue.trace());
            ex.addTrace(field.getName() + " (" + field.getDeclaringClass().getName() + ")");
            throw ex;
         }
      }
   }

   public void readFields(Object object, JsonValue jsonMap) {
      Class type = object.getClass();
      OrderedMap fields = this.getFields(type);

      for (JsonValue child = jsonMap.child; child != null; child = child.next) {
         Json.FieldMetadata metadata = (Json.FieldMetadata)fields.get(child.name().replace(" ", "_"));
         if (metadata == null) {
            if (!child.name.equals(this.typeName) && !this.ignoreUnknownFields && !this.ignoreUnknownField(type, child.name)) {
               SerializationException ex = new SerializationException("Field not found: " + child.name + " (" + type.getName() + ")");
               ex.addTrace(child.trace());
               throw ex;
            }
         } else if (!this.ignoreDeprecated || this.readDeprecated || !metadata.deprecated) {
            Field field = metadata.field;

            try {
               field.set(object, this.readValue(field.getType(), metadata.elementType, child));
            } catch (ReflectionException var10) {
               throw new SerializationException("Error accessing field: " + field.getName() + " (" + type.getName() + ")", var10);
            } catch (SerializationException var11) {
               var11.addTrace(field.getName() + " (" + type.getName() + ")");
               throw var11;
            } catch (RuntimeException var12) {
               SerializationException ex = new SerializationException(var12);
               ex.addTrace(child.trace());
               ex.addTrace(field.getName() + " (" + type.getName() + ")");
               throw ex;
            }
         }
      }
   }

   protected boolean ignoreUnknownField(Class type, String fieldName) {
      return false;
   }

   @Null
   public Object readValue(String name, @Null Class type, JsonValue jsonMap) {
      return this.readValue(type, null, jsonMap.get(name));
   }

   @Null
   public Object readValue(String name, @Null Class type, Object defaultValue, JsonValue jsonMap) {
      JsonValue jsonValue = jsonMap.get(name);
      return jsonValue == null ? defaultValue : this.readValue(type, null, jsonValue);
   }

   @Null
   public Object readValue(String name, @Null Class type, @Null Class elementType, JsonValue jsonMap) {
      return this.readValue(type, elementType, jsonMap.get(name));
   }

   @Null
   public Object readValue(String name, @Null Class type, @Null Class elementType, Object defaultValue, JsonValue jsonMap) {
      JsonValue jsonValue = jsonMap.get(name);
      return this.readValue(type, elementType, defaultValue, jsonValue);
   }

   @Null
   public Object readValue(@Null Class type, @Null Class elementType, Object defaultValue, JsonValue jsonData) {
      return jsonData == null ? defaultValue : this.readValue(type, elementType, jsonData);
   }

   @Null
   public Object readValue(@Null Class type, JsonValue jsonData) {
      return this.readValue(type, null, jsonData);
   }

   @Null
   public Object readValue(@Null Class type, @Null Class elementType, JsonValue jsonData) {
      if (jsonData == null) {
         return null;
      } else {
         if (jsonData.isObject()) {
            String className = this.typeName == null ? null : jsonData.getString(this.typeName, null);
            if (className != null) {
               type = this.getClass(className);
               if (type == null) {
                  try {
                     type = ClassReflection.forName(className);
                  } catch (ReflectionException var9) {
                     throw new SerializationException(var9);
                  }
               }
            }

            if (type == null) {
               if (this.defaultSerializer != null) {
                  return this.defaultSerializer.read(this, jsonData, type);
               }

               return jsonData;
            }

            if (this.typeName == null || !ClassReflection.isAssignableFrom(Collection.class, type)) {
               Json.Serializer serializer = (Json.Serializer)this.classToSerializer.get(type);
               if (serializer != null) {
                  return serializer.read(this, jsonData, type);
               } else if (type != String.class
                  && type != Integer.class
                  && type != Boolean.class
                  && type != Float.class
                  && type != Long.class
                  && type != Double.class
                  && type != Short.class
                  && type != Byte.class
                  && type != Character.class
                  && !ClassReflection.isAssignableFrom(Enum.class, type)) {
                  Object object = this.newInstance(type);
                  if (object instanceof Json.Serializable) {
                     ((Json.Serializable)object).read(this, jsonData);
                     return object;
                  } else if (object instanceof ObjectMap) {
                     ObjectMap result = (ObjectMap)object;

                     for (JsonValue child = jsonData.child; child != null; child = child.next) {
                        result.put(child.name, this.readValue(elementType, null, child));
                     }

                     return result;
                  } else if (object instanceof ObjectIntMap) {
                     ObjectIntMap result = (ObjectIntMap)object;

                     for (JsonValue child = jsonData.child; child != null; child = child.next) {
                        result.put(child.name, (Integer)this.readValue(Integer.class, null, child));
                     }

                     return result;
                  } else if (object instanceof ObjectFloatMap) {
                     ObjectFloatMap result = (ObjectFloatMap)object;

                     for (JsonValue child = jsonData.child; child != null; child = child.next) {
                        result.put(child.name, (Float)this.readValue(Float.class, null, child));
                     }

                     return result;
                  } else if (object instanceof ObjectSet) {
                     ObjectSet result = (ObjectSet)object;

                     for (JsonValue child = jsonData.getChild("values"); child != null; child = child.next) {
                        result.add(this.readValue(elementType, null, child));
                     }

                     return result;
                  } else if (object instanceof IntMap) {
                     IntMap result = (IntMap)object;

                     for (JsonValue child = jsonData.child; child != null; child = child.next) {
                        result.put(Integer.parseInt(child.name), this.readValue(elementType, null, child));
                     }

                     return result;
                  } else if (object instanceof LongMap) {
                     LongMap result = (LongMap)object;

                     for (JsonValue child = jsonData.child; child != null; child = child.next) {
                        result.put(Long.parseLong(child.name), this.readValue(elementType, null, child));
                     }

                     return result;
                  } else if (object instanceof IntSet) {
                     IntSet result = (IntSet)object;

                     for (JsonValue child = jsonData.getChild("values"); child != null; child = child.next) {
                        result.add(child.asInt());
                     }

                     return result;
                  } else if (!(object instanceof ArrayMap)) {
                     if (object instanceof Map) {
                        Map result = (Map)object;

                        for (JsonValue child = jsonData.child; child != null; child = child.next) {
                           if (!child.name.equals(this.typeName)) {
                              result.put(child.name, this.readValue(elementType, null, child));
                           }
                        }

                        return result;
                     } else {
                        this.readFields(object, jsonData);
                        return object;
                     }
                  } else {
                     ArrayMap result = (ArrayMap)object;

                     for (JsonValue childx = jsonData.child; childx != null; childx = childx.next) {
                        result.put(childx.name, this.readValue(elementType, null, childx));
                     }

                     return result;
                  }
               } else {
                  return this.readValue("value", type, jsonData);
               }
            }

            jsonData = jsonData.get("items");
            if (jsonData == null) {
               throw new SerializationException("Unable to convert object to collection: " + jsonData + " (" + type.getName() + ")");
            }
         }

         if (type != null) {
            Json.Serializer serializer = (Json.Serializer)this.classToSerializer.get(type);
            if (serializer != null) {
               return serializer.read(this, jsonData, type);
            }

            if (ClassReflection.isAssignableFrom(Json.Serializable.class, type)) {
               Object object = this.newInstance(type);
               ((Json.Serializable)object).read(this, jsonData);
               return object;
            }
         }

         if (jsonData.isArray()) {
            if (type == null || type == Object.class) {
               type = (Class<T>)Array.class;
            }

            if (ClassReflection.isAssignableFrom(Array.class, type)) {
               Array result = type == Array.class ? new Array() : (Array)this.newInstance(type);

               for (JsonValue childx = jsonData.child; childx != null; childx = childx.next) {
                  result.add(this.readValue(elementType, null, childx));
               }

               return result;
            } else if (ClassReflection.isAssignableFrom(Queue.class, type)) {
               Queue result = type == Queue.class ? new Queue() : (Queue)this.newInstance(type);

               for (JsonValue childx = jsonData.child; childx != null; childx = childx.next) {
                  result.addLast(this.readValue(elementType, null, childx));
               }

               return result;
            } else if (ClassReflection.isAssignableFrom(Collection.class, type)) {
               Collection result = (Collection)(type.isInterface() ? new ArrayList() : (Collection)this.newInstance(type));

               for (JsonValue childx = jsonData.child; childx != null; childx = childx.next) {
                  result.add(this.readValue(elementType, null, childx));
               }

               return result;
            } else if (!type.isArray()) {
               throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + type.getName() + ")");
            } else {
               Class componentType = type.getComponentType();
               if (elementType == null) {
                  elementType = componentType;
               }

               Object result = ArrayReflection.newInstance(componentType, jsonData.size);
               int i = 0;

               for (JsonValue childx = jsonData.child; childx != null; childx = childx.next) {
                  ArrayReflection.set(result, i++, this.readValue(elementType, null, childx));
               }

               return result;
            }
         } else {
            if (jsonData.isNumber()) {
               try {
                  if (type == null || type == float.class || type == Float.class) {
                     return jsonData.asFloat();
                  }

                  if (type == int.class || type == Integer.class) {
                     return jsonData.asInt();
                  }

                  if (type == long.class || type == Long.class) {
                     return jsonData.asLong();
                  }

                  if (type == double.class || type == Double.class) {
                     return jsonData.asDouble();
                  }

                  if (type == String.class) {
                     return jsonData.asString();
                  }

                  if (type == short.class || type == Short.class) {
                     return jsonData.asShort();
                  }

                  if (type == byte.class || type == Byte.class) {
                     return jsonData.asByte();
                  }
               } catch (NumberFormatException var12) {
               }

               jsonData = new JsonValue(jsonData.asString());
            }

            if (jsonData.isBoolean()) {
               try {
                  if (type == null || type == boolean.class || type == Boolean.class) {
                     return jsonData.asBoolean();
                  }
               } catch (NumberFormatException var11) {
               }

               jsonData = new JsonValue(jsonData.asString());
            }

            if (!jsonData.isString()) {
               return null;
            } else {
               String string = jsonData.asString();
               if (type != null && type != String.class) {
                  try {
                     if (type == int.class || type == Integer.class) {
                        return Integer.valueOf(string);
                     }

                     if (type == float.class || type == Float.class) {
                        return Float.valueOf(string);
                     }

                     if (type == long.class || type == Long.class) {
                        return Long.valueOf(string);
                     }

                     if (type == double.class || type == Double.class) {
                        return Double.valueOf(string);
                     }

                     if (type == short.class || type == Short.class) {
                        return Short.valueOf(string);
                     }

                     if (type == byte.class || type == Byte.class) {
                        return Byte.valueOf(string);
                     }
                  } catch (NumberFormatException var10) {
                  }

                  if (type == boolean.class || type == Boolean.class) {
                     return Boolean.valueOf(string);
                  } else if (type != char.class && type != Character.class) {
                     if (ClassReflection.isAssignableFrom(Enum.class, type)) {
                        Enum[] constants = (Enum[])type.getEnumConstants();
                        int i = 0;

                        for (int n = constants.length; i < n; i++) {
                           Enum e = constants[i];
                           if (string.equals(this.convertToString(e))) {
                              return e;
                           }
                        }
                     }

                     if (type == CharSequence.class) {
                        return string;
                     } else {
                        throw new SerializationException("Unable to convert value to required type: " + jsonData + " (" + type.getName() + ")");
                     }
                  } else {
                     return string.charAt(0);
                  }
               } else {
                  return string;
               }
            }
         }
      }
   }

   public void copyFields(Object from, Object to) {
      OrderedMap toFields = this.getFields(to.getClass());

      for (ObjectMap.Entry entry : this.getFields(from.getClass())) {
         Json.FieldMetadata toField = (Json.FieldMetadata)toFields.get(entry.key);
         Field fromField = ((Json.FieldMetadata)entry.value).field;
         if (toField == null) {
            throw new SerializationException("To object is missing field: " + (String)entry.key);
         }

         try {
            toField.field.set(to, fromField.get(from));
         } catch (ReflectionException var9) {
            throw new SerializationException("Error copying field: " + fromField.getName(), var9);
         }
      }
   }

   private String convertToString(Enum e) {
      return this.enumNames ? e.name() : e.toString();
   }

   private String convertToString(Object object) {
      if (object instanceof Enum) {
         return this.convertToString((Enum)object);
      } else {
         return object instanceof Class ? ((Class)object).getName() : String.valueOf(object);
      }
   }

   protected Object newInstance(Class type) {
      try {
         return ClassReflection.newInstance(type);
      } catch (Exception var7) {
         Exception ex = var7;

         try {
            Constructor constructor = ClassReflection.getDeclaredConstructor(type);
            constructor.setAccessible(true);
            return constructor.newInstance();
         } catch (SecurityException var4) {
         } catch (ReflectionException var5) {
            if (ClassReflection.isAssignableFrom(Enum.class, type)) {
               if (type.getEnumConstants() == null) {
                  type = type.getSuperclass();
               }

               return type.getEnumConstants()[0];
            }

            if (type.isArray()) {
               throw new SerializationException("Encountered JSON object when expected array of type: " + type.getName(), var7);
            }

            if (ClassReflection.isMemberClass(type) && !ClassReflection.isStaticClass(type)) {
               throw new SerializationException("Class cannot be created (non-static member class): " + type.getName(), var7);
            }

            throw new SerializationException("Class cannot be created (missing no-arg constructor): " + type.getName(), var7);
         } catch (Exception var6) {
            ex = var6;
         }

         throw new SerializationException("Error constructing instance of class: " + type.getName(), ex);
      }
   }

   public String prettyPrint(@Null Object object) {
      return this.prettyPrint(object, 0);
   }

   public String prettyPrint(String json) {
      return this.prettyPrint(json, 0);
   }

   public String prettyPrint(@Null Object object, int singleLineColumns) {
      return this.prettyPrint(this.toJson(object), singleLineColumns);
   }

   public String prettyPrint(String json, int singleLineColumns) {
      return new JsonReader().parse(json).prettyPrint(this.outputType, singleLineColumns);
   }

   public String prettyPrint(@Null Object object, JsonValue.PrettyPrintSettings settings) {
      return this.prettyPrint(this.toJson(object), settings);
   }

   public String prettyPrint(String json, JsonValue.PrettyPrintSettings settings) {
      return new JsonReader().parse(json).prettyPrint(settings);
   }

   private static class FieldMetadata {
      final Field field;
      Class elementType;
      boolean deprecated;

      public FieldMetadata(Field field) {
         this.field = field;
         int index = !ClassReflection.isAssignableFrom(ObjectMap.class, field.getType()) && !ClassReflection.isAssignableFrom(Map.class, field.getType())
            ? 0
            : 1;
         this.elementType = field.getElementType(index);
         this.deprecated = field.isAnnotationPresent(Deprecated.class);
      }
   }

   public abstract static class ReadOnlySerializer implements Json.Serializer {
      @Override
      public void write(Json json, Object object, Class knownType) {
      }

      @Override
      public abstract Object read(Json var1, JsonValue var2, Class var3);
   }

   public interface Serializable {
      void write(Json var1);

      void read(Json var1, JsonValue var2);
   }

   public interface Serializer {
      void write(Json var1, Object var2, Class var3);

      Object read(Json var1, JsonValue var2, Class var3);
   }
}
