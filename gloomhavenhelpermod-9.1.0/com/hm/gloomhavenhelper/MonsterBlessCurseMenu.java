package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;

public class MonsterBlessCurseMenu extends Menu {
   final MonsterRow row;
   private ImageButton blessPlusButton;
   private ImageButton blessMinusButton;
   private ImageButton cursePlusButton;
   private ImageButton curseMinusButton;
   Label blessLabel;
   Label curseLabel;

   public MonsterBlessCurseMenu(MonsterRow row) {
      this.row = row;
      this.create();
      this.layoutUI();
      this.events();
      this.setBackgroundOffset(-4.0F, -2.0F, 898.0F, 5.0F);
   }

   private void create() {
      ImageButtonBuilder sub = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
      ImageButtonBuilder add = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray);
      this.blessMinusButton = sub.create();
      this.blessPlusButton = add.create();
      this.curseMinusButton = sub.create();
      this.cursePlusButton = add.create();
      this.blessLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.blessLabel.setTouchable(Touchable.disabled);
      this.curseLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
      this.curseLabel.setTouchable(Touchable.disabled);
   }

   private void layoutUI() {
      this.columnDefaults(0).size(100.0F);
      this.columnDefaults(1).fill();
      this.columnDefaults(2).size(100.0F);
      this.add(this.blessMinusButton);
      this.add(new Stack(App.image("bless-large"), new Container(this.blessLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      this.add(this.blessPlusButton).row();
      this.add(this.curseMinusButton);
      this.add(new Stack(App.image("curse-large"), new Container(this.curseLabel).pad(0.0F, 0.0F, -48.0F, -44.0F)));
      this.add(this.cursePlusButton).row();
   }

   private void events() {
      this.blessPlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.bless, false) < 10) {
               int bless = App.parseInt(MonsterBlessCurseMenu.this.blessLabel.getText().toString()) + 1;
               MonsterBlessCurseMenu.this.blessLabel.setText(bless);
               App.state.add(AttackModifier.bless);
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.blessMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.bless, false) != 0) {
               int bless = Math.max(0, App.parseInt(MonsterBlessCurseMenu.this.blessLabel.getText().toString()) - 1);
               MonsterBlessCurseMenu.this.blessLabel.setText(bless);
               App.state.remove(AttackModifier.bless);
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.cursePlusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.curse, false) < 10) {
               int curse = App.parseInt(MonsterBlessCurseMenu.this.curseLabel.getText().toString()) + 1;
               MonsterBlessCurseMenu.this.curseLabel.setText(curse);
               App.state.addCurse();
               App.state.attackModifiers.shuffle();
            }
         }
      });
      this.curseMinusButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (App.state.count(AttackModifier.curse, false) != 0) {
               int curse = Math.max(0, App.parseInt(MonsterBlessCurseMenu.this.curseLabel.getText().toString()) - 1);
               MonsterBlessCurseMenu.this.curseLabel.setText(curse);
               App.state.remove(AttackModifier.curse);
               App.state.attackModifiers.shuffle();
            }
         }
      });
   }

   @Override
   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.blessLabel.setText(String.valueOf(App.state.count(AttackModifier.bless, false)));
      this.curseLabel.setText(String.valueOf(App.state.count(AttackModifier.curse, false)));
      return super.show(positionActor, positionX, positionY, positionWidth, positionHeight, preferRight);
   }

   @Override
   protected boolean dir(boolean right) {
      return true;
   }

   @Override
   public void setPosition(float positionX, float positionY, float positionWidth, float positionHeight, float bgX, float bgY, float bgWidth, float bgHeight) {
      super.setPosition(positionX, positionY, positionWidth, positionHeight, bgX, bgY, bgWidth, bgHeight);
   }

   @Override
   public boolean hide() {
      super.hide();
      App.state.changed();
      return true;
   }

   @Override
   public void act(float delta) {
      super.act(delta);
      int blesses = App.state.count(AttackModifier.bless, false);
      this.blessMinusButton.setDisabled(blesses <= 0);
      this.blessPlusButton.setDisabled(blesses >= 10);
      int curses = App.state.count(AttackModifier.curse, false);
      this.curseMinusButton.setDisabled(curses <= 0);
      this.cursePlusButton.setDisabled(curses >= 10);
   }
}
