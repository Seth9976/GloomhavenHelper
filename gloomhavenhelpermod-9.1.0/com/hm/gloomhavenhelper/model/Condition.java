package com.hm.gloomhavenhelper.model;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

public enum Condition {
   star,
   summonNew,
   summon,
   stun,
   immobilize,
   disarm,
   wound,
   muddle,
   poison,
   bane,
   brittle,
   chill,
   infect,
   impair,
   rupture,
   wound2,
   poison2,
   poison3,
   poison4,
   strengthen,
   invisible,
   regenerate,
   ward,
   doom,
   hatchet;

   public static Condition[] values = values();
   public transient Drawable drawable;
   public transient Drawable drawableMedium;
}
