package com.hm.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntArray;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;

public class MonsterAddMenu extends Menu {
   private MonsterRow row;

   public MonsterAddMenu(final MonsterRow row, IntArray free, final boolean elite) {
      this.row = row;
      this.defaults().size(100.0F);
      int extra = 0;
      final ImageButton summonButton;
      if (!row.data.isBoss()) {
         extra = 1;
         summonButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/summon-large").checked("selected"))
               .over("selected", App.buttonGray))
            .create();
      } else {
         summonButton = null;
      }

      int columns = 4;
      if (row.data.count + extra < 6) {
         columns = 5;
      } else if (row.data.count + extra == 7) {
         columns = 4;
      } else if (row.data.count + extra < 10) {
         columns = 3;
      }

      Color color = row.data.isBoss() ? App.bossRed : (elite ? App.eliteGold : Color.WHITE);

      for (final int i = 1; i <= row.data.count; i++) {
         boolean disabled = !free.contains(i);
         final TextButton button = ((TextButtonBuilder)((TextButtonBuilder)((TextButtonBuilder)App.textButton(String.valueOf(i))
                     .fontColor(color)
                     .over("selected", App.buttonGray))
                  .disabled("white", Color.CLEAR))
               .disabled(disabled))
            .create();
         if (!disabled) {
            button.addListener(new ClickListener() {
               @Override
               public void clicked(InputEvent event, float x, float y) {
                  row.addMonsterBox(i, row.data, row.level, elite, summonButton != null && summonButton.isChecked(), SummonColor.blue, true);
                  App.state.changed();
                  if (row.boxes.size == row.data.count) {
                     MonsterAddMenu.this.hide();
                  }

                  button.setDisabled(true);
                  button.removeListener(this);
               }
            });
         }

         this.add(button);
         if (i % columns == 0) {
            this.row();
         }
      }

      if (summonButton != null) {
         this.add(summonButton);
      }
   }

   @Override
   protected void updatePosition() {
      super.updatePosition();
      App.gloom.rowsScroll.scrollTo(0.0F, this.row.getY() - 10.0F, 0.0F, this.row.getHeight() + 20.0F);
   }
}
