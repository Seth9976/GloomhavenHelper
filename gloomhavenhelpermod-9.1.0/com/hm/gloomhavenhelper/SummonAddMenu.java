package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;

public class SummonAddMenu extends Menu {
   public SummonAddMenu(final PlayerRow row) {
      this.defaults().size(100.0F);
      final ButtonGroup colorButtons = new ButtonGroup();
      int i = 0;

      for (SummonColor color : SummonColor.values) {
         i++;
         if (color != SummonColor.beast) {
            ImageButton button = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp(color.drawable).over("selected", App.buttonGray))
                  .checked("selected"))
               .create();
            this.add(button);
            colorButtons.add(button);
            if (colorButtons.getButtons().size == 4) {
               this.row();
            }
         }
      }

      this.row();

      for (int var9 = 1; var9 <= 4; var9++) {
         TextButton button = ((TextButtonBuilder)((TextButtonBuilder)App.textButton(String.valueOf(var9)).over("selected", App.buttonGray))
               .disabled("white", Color.CLEAR))
            .create();
         final int number = var9;
         button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
               SummonAddMenu.this.hide();
               SummonColor colorx = SummonColor.values[colorButtons.getChecked().getZIndex() + 1];
               MonsterBox box = row.addMonsterBox(number, App.summonData, row.player.level, false, false, colorx, false);
               box.monster.hp = 2;
               box.monster.hpMax = 2;
               box.showMenu();
               box.menu.levelButton.setProgrammaticChangeEvents(true);
               box.menu.levelButton.toggle();
               box.menu.levelButton.setProgrammaticChangeEvents(false);
               App.state.changed();
            }
         });
         this.add(button);
      }
   }
}
