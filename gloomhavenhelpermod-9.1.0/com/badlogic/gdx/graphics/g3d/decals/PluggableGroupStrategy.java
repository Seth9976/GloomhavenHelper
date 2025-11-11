package com.badlogic.gdx.graphics.g3d.decals;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

public abstract class PluggableGroupStrategy implements GroupStrategy {
   private IntMap plugs = new IntMap();

   @Override
   public void beforeGroup(int group, Array contents) {
      ((GroupPlug)this.plugs.get(group)).beforeGroup(contents);
   }

   @Override
   public void afterGroup(int group) {
      ((GroupPlug)this.plugs.get(group)).afterGroup();
   }

   public void plugIn(GroupPlug plug, int group) {
      this.plugs.put(group, plug);
   }

   public GroupPlug unPlug(int group) {
      return (GroupPlug)this.plugs.remove(group);
   }
}
