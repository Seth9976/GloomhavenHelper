package com.esotericsoftware.spine;

public class Event {
    float balance;
    private final EventData data;
    float floatValue;
    int intValue;
    String stringValue;
    final float time;
    float volume;

    public Event(float f, EventData eventData0) {
        if(eventData0 == null) {
            throw new IllegalArgumentException("data cannot be null.");
        }
        this.time = f;
        this.data = eventData0;
    }

    public float getBalance() {
        return this.balance;
    }

    public EventData getData() {
        return this.data;
    }

    public float getFloat() {
        return this.floatValue;
    }

    public int getInt() {
        return this.intValue;
    }

    public String getString() {
        return this.stringValue;
    }

    public float getTime() {
        return this.time;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setBalance(float f) {
        this.balance = f;
    }

    public void setFloat(float f) {
        this.floatValue = f;
    }

    public void setInt(int v) {
        this.intValue = v;
    }

    public void setString(String s) {
        if(s == null) {
            throw new IllegalArgumentException("stringValue cannot be null.");
        }
        this.stringValue = s;
    }

    public void setVolume(float f) {
        this.volume = f;
    }

    @Override
    public String toString() {
        return this.data.name;
    }
}

