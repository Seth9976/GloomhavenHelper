package com.badlogic.gdx.utils;

public class SerializationException extends RuntimeException {
    private StringBuilder trace;

    public SerializationException() {
    }

    public SerializationException(String s) {
        super(s);
    }

    public SerializationException(String s, Throwable throwable0) {
        super(s, throwable0);
    }

    public SerializationException(Throwable throwable0) {
        super("", throwable0);
    }

    public void addTrace(String s) {
        if(s == null) {
            throw new IllegalArgumentException("info cannot be null.");
        }
        if(this.trace == null) {
            this.trace = new StringBuilder(0x200);
        }
        this.trace.append('\n');
        this.trace.append(s);
    }

    private boolean causedBy(Throwable throwable0, Class class0) {
        Throwable throwable1 = throwable0.getCause();
        if(throwable1 != null && throwable1 != throwable0) {
            return class0.isAssignableFrom(throwable1.getClass()) ? true : this.causedBy(throwable1, class0);
        }
        return false;
    }

    public boolean causedBy(Class class0) {
        return this.causedBy(this, class0);
    }

    @Override
    public String getMessage() {
        if(this.trace == null) {
            return super.getMessage();
        }
        StringBuilder stringBuilder0 = new StringBuilder(0x200);
        stringBuilder0.append(super.getMessage());
        if(stringBuilder0.length() > 0) {
            stringBuilder0.append('\n');
        }
        stringBuilder0.append("Serialization trace:");
        stringBuilder0.append(this.trace);
        return "";
    }
}

