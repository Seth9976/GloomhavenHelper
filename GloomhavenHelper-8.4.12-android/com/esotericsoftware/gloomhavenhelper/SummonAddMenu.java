package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;

public class SummonAddMenu extends Menu {
    public SummonAddMenu(PlayerRow playerRow0) {
        this.defaults().size(100.0f);
        ButtonGroup buttonGroup0 = new ButtonGroup();
        SummonColor[] arr_summonColor = SummonColor.values;
        for(int v = 0; v < arr_summonColor.length; ++v) {
            SummonColor summonColor0 = arr_summonColor[v];
            if(summonColor0 != SummonColor.beast) {
                ImageButton imageButton0 = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp(summonColor0.drawable).over("selected", App.buttonGray)).checked("selected")).create();
                this.add(imageButton0);
                buttonGroup0.add(imageButton0);
                if(buttonGroup0.getButtons().size == 4) {
                    this.row();
                }
            }
        }
        this.row();
        for(int v1 = 1; v1 <= 4; ++v1) {
            TextButton textButton0 = ((TextButtonBuilder)((TextButtonBuilder)App.textButton((v1 + "")).over("selected", App.buttonGray)).disabled("white", Color.CLEAR)).create();
            textButton0.addListener(new ClickListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                public void clicked(InputEvent inputEvent0, float f, float f1) {
                    SummonAddMenu.this.hide();
                    int v = buttonGroup0.getChecked().getZIndex();
                    MonsterBox monsterBox0 = playerRow0.addMonsterBox(v1, App.summonData, playerRow0.player.level, false, false, SummonColor.values[v + 1], false);
                    monsterBox0.monster.hp = 2;
                    monsterBox0.monster.hpMax = 2;
                    monsterBox0.showMenu();
                    monsterBox0.menu.levelButton.setProgrammaticChangeEvents(true);
                    monsterBox0.menu.levelButton.toggle();
                    monsterBox0.menu.levelButton.setProgrammaticChangeEvents(false);
                    App.state.changed();
                }
            });
            this.add(textButton0);
        }
    }
}

