package com.badlogic.gdx.scenes.scene2d.actions;

import com.badlogic.gdx.scenes.scene2d.Event;

public class CountdownEventAction extends EventAction {
   int count;
   int current;

   public CountdownEventAction(Class eventClass, int count) {
      super(eventClass);
      this.count = count;
   }

   @Override
   public boolean handle(Event event) {
      this.current++;
      return this.current >= this.count;
   }
}
