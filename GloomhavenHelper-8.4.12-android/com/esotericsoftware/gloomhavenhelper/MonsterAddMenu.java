package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;

public class MonsterAddMenu extends Menu {
    private MonsterRow row;

    public MonsterAddMenu(MonsterRow monsterRow0, IntArray intArray0, boolean z) {
        Color color0;
        int v1;
        int v;
        ImageButton imageButton0;
        this.row = monsterRow0;
        this.defaults().size(100.0f);
        if(monsterRow0.data.isBoss()) {
            v = 0;
            imageButton0 = null;
        }
        else {
            imageButton0 = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/summon-large").checked("selected")).over("selected", App.buttonGray)).create();
            v = 1;
        }
        if(monsterRow0.data.count + v < 6) {
            v1 = 5;
        }
        else if(monsterRow0.data.count + v == 7) {
            v1 = 4;
        }
        else {
            v1 = monsterRow0.data.count + v >= 10 ? 4 : 3;
        }
        if(monsterRow0.data.isBoss()) {
            color0 = App.bossRed;
        }
        else {
            color0 = z ? App.eliteGold : Color.WHITE;
        }
        for(int v2 = 1; v2 <= monsterRow0.data.count; ++v2) {
            boolean z1 = intArray0.contains(v2);
            TextButton textButton0 = ((TextButtonBuilder)((TextButtonBuilder)((TextButtonBuilder)App.textButton((v2 + "")).fontColor(color0).over("selected", App.buttonGray)).disabled("white", Color.CLEAR)).disabled(!z1)).create();
            if(!z1 == 0) {
                textButton0.addListener(new ClickListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
                    public void clicked(InputEvent inputEvent0, float f, float f1) {
                        MonsterData monsterData0 = monsterRow0.data;
                        int v = monsterRow0.level;
                        boolean z = imageButton0 != null && imageButton0.isChecked();
                        monsterRow0.addMonsterBox(v2, monsterData0, v, z, z, SummonColor.blue, true);
                        App.state.changed();
                        if(monsterRow0.boxes.size == monsterRow0.data.count) {
                            MonsterAddMenu.this.hide();
                        }
                        textButton0.setDisabled(true);
                        textButton0.removeListener(this);
                    }
                });
            }
            this.add(textButton0);
            if(v2 % v1 == 0) {
                this.row();
            }
        }
        if(imageButton0 != null) {
            this.add(imageButton0);
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    protected void updatePosition() {
        super.updatePosition();
        App.gloom.rowsScroll.scrollTo(0.0f, this.row.getY() - 10.0f, 0.0f, this.row.getHeight() + 20.0f);
    }
}

