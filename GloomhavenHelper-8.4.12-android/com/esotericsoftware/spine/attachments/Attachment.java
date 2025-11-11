package com.esotericsoftware.spine.attachments;

public abstract class Attachment {
    final String name;

    protected Attachment(Attachment attachment0) {
        this.name = attachment0.name;
    }

    public Attachment(String s) {
        if(s == null) {
            throw new IllegalArgumentException("name cannot be null.");
        }
        this.name = s;
    }

    public abstract Attachment copy();

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

