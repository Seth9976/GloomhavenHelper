package com.esotericsoftware.gloomhavenhelper.network;

public enum Message {
    gameState("s"),
    undo("u"),
    redo("r"),
    version("v"),
    rejected("r"),
    init("i");

    public static final byte animateAttackCard = 97;
    public static final byte setAttackCards = 98;
    final String value;

    private Message(String s1) {
        this.value = s1;
    }
}

