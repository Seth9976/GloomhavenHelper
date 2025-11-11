package com.esotericsoftware.spine;

public class EventData {
    String audioPath;
    float balance;
    float floatValue;
    int intValue;
    final String name;
    String stringValue;
    float volume;

    public EventData(String s) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.name = s;
    }

    public String getAudioPath() {
        return this.audioPath;
    }

    public float getBalance() {
        return this.balance;
    }

    public float getFloat() {
        return this.floatValue;
    }

    public int getInt() {
        return this.intValue;
    }

    public String getName() {
        return this.name;
    }

    public String getString() {
        return this.stringValue;
    }

    public float getVolume() {
        return this.volume;
    }

    public void setAudioPath(String s) {
        if(s == null) {
            throw new IllegalArgumentException("audioPath cannot be null.");
        }
        this.audioPath = s;
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
        return this.name;
    }
}

