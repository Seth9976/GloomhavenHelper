package com.esotericsoftware.spine.attachments;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.utils.SpineUtils;

public class Sequence {
    public static enum SequenceMode {
        hold,
        once,
        loop,
        pingpong,
        onceReverse,
        loopReverse,
        pingpongReverse;

        public static final SequenceMode[] values;

        static {
            SequenceMode.values = (SequenceMode[])SequenceMode.$VALUES.clone();
        }
    }

    private int digits;
    private final int id;
    private static int nextID;
    private final TextureRegion[] regions;
    private int setupIndex;
    private int start;

    public Sequence(int v) {
        this.id = Sequence.nextID();
        this.regions = new TextureRegion[v];
    }

    protected Sequence(Sequence sequence0) {
        this.id = Sequence.nextID();
        this.regions = new TextureRegion[sequence0.regions.length];
        SpineUtils.arraycopy(sequence0.regions, 0, this.regions, 0, this.regions.length);
        this.start = sequence0.start;
        this.digits = sequence0.digits;
        this.setupIndex = sequence0.setupIndex;
    }

    public void apply(Slot slot0, HasTextureRegion hasTextureRegion0) {
        int v = slot0.getSequenceIndex();
        if(v == -1) {
            v = this.setupIndex;
        }
        TextureRegion[] arr_textureRegion = this.regions;
        if(v >= arr_textureRegion.length) {
            v = arr_textureRegion.length - 1;
        }
        TextureRegion textureRegion0 = this.regions[v];
        if(hasTextureRegion0.getRegion() != textureRegion0) {
            hasTextureRegion0.setRegion(textureRegion0);
            hasTextureRegion0.updateRegion();
        }
    }

    public int getDigits() {
        return this.digits;
    }

    public int getId() {
        return this.id;
    }

    public String getPath(String s, int v) {
        StringBuilder stringBuilder0 = new StringBuilder(s.length() + this.digits);
        stringBuilder0.append(s);
        String s1 = Integer.toString(this.start + v);
        for(int v1 = this.digits - s1.length(); v1 > 0; --v1) {
            stringBuilder0.append('0');
        }
        stringBuilder0.append(s1);
        return stringBuilder0.toString();
    }

    public TextureRegion[] getRegions() {
        return this.regions;
    }

    public int getSetupIndex() {
        return this.setupIndex;
    }

    public int getStart() {
        return this.start;
    }

    private static int nextID() {
        int v;
        synchronized(Sequence.class) {
            v = Sequence.nextID;
            Sequence.nextID = v + 1;
        }
        return v;
    }

    public void setDigits(int v) {
        this.digits = v;
    }

    public void setSetupIndex(int v) {
        this.setupIndex = v;
    }

    public void setStart(int v) {
        this.start = v;
    }
}

