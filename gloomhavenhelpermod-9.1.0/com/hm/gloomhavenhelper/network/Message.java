package com.hm.gloomhavenhelper.network;

public enum Message {
   gameState("s"),
   undo("u"),
   redo("r"),
   version("v"),
   rejected("r"),
   init("i");

   final String value;
   public static final byte animateAttackCard = 97;
   public static final byte setAttackCards = 98;

   private Message(String value) {
      this.value = value;
   }
}
