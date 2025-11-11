package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;

public class MonsterBlessCurseMenu extends Menu {
    Label blessLabel;
    private ImageButton blessMinusButton;
    private ImageButton blessPlusButton;
    Label curseLabel;
    private ImageButton curseMinusButton;
    private ImageButton cursePlusButton;
    final MonsterRow row;

    public MonsterBlessCurseMenu(MonsterRow monsterRow0) {
        this.row = monsterRow0;
        this.create();
        this.layoutUI();
        this.events();
        this.setBackgroundOffset(-4.0f, -2.0f, 898.0f, 5.0f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        super.act(f);
        boolean z = false;
        int v = App.state.count(AttackModifier.bless, false);
        this.blessMinusButton.setDisabled(v <= 0);
        this.blessPlusButton.setDisabled(v >= 10);
        int v1 = App.state.count(AttackModifier.curse, false);
        this.curseMinusButton.setDisabled(v1 <= 0);
        ImageButton imageButton0 = this.cursePlusButton;
        if(v1 >= 10) {
            z = true;
        }
        imageButton0.setDisabled(z);
    }

    private void create() {
        ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
        ImageButtonBuilder imageButtonBuilder1 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray);
        this.blessMinusButton = imageButtonBuilder0.create();
        this.blessPlusButton = imageButtonBuilder1.create();
        this.curseMinusButton = imageButtonBuilder0.create();
        this.cursePlusButton = imageButtonBuilder1.create();
        this.blessLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.blessLabel.setTouchable(Touchable.disabled);
        this.curseLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.curseLabel.setTouchable(Touchable.disabled);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    protected boolean dir(boolean z) {
        return true;
    }

    private void events() {
        this.blessPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.bless, false) >= 10) {
                    return;
                }
                int v = App.parseInt("");
                MonsterBlessCurseMenu.this.blessLabel.setText("" + (v + 1));
                App.state.add(AttackModifier.bless);
                App.state.attackModifiers.shuffle();
            }
        });
        this.blessMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.bless, false) == 0) {
                    return;
                }
                int v = Math.max(0, App.parseInt("") - 1);
                MonsterBlessCurseMenu.this.blessLabel.setText("" + v);
                App.state.remove(AttackModifier.bless);
                App.state.attackModifiers.shuffle();
            }
        });
        this.cursePlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.curse, false) >= 10) {
                    return;
                }
                int v = App.parseInt("");
                MonsterBlessCurseMenu.this.curseLabel.setText("" + (v + 1));
                App.state.addCurse();
                App.state.attackModifiers.shuffle();
            }
        });
        this.curseMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.curse, false) == 0) {
                    return;
                }
                int v = Math.max(0, App.parseInt("") - 1);
                MonsterBlessCurseMenu.this.curseLabel.setText("" + v);
                App.state.remove(AttackModifier.curse);
                App.state.attackModifiers.shuffle();
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean hide() {
        super.hide();
        App.state.changed();
        return true;
    }

    private void layoutUI() {
        this.columnDefaults(0).size(100.0f);
        this.columnDefaults(1).fill();
        this.columnDefaults(2).size(100.0f);
        this.add(this.blessMinusButton);
        this.add(new Stack(new Actor[]{App.image("bless-large"), new Container(this.blessLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        this.add(this.blessPlusButton).row();
        this.add(this.curseMinusButton);
        this.add(new Stack(new Actor[]{App.image("curse-large"), new Container(this.curseLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        this.add(this.cursePlusButton).row();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public void setPosition(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7) {
        super.setPosition(f, f1, f2, f3, f4, f5, f6, f7);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        this.blessLabel.setText(App.state.count(AttackModifier.bless, false) + "");
        this.curseLabel.setText(App.state.count(AttackModifier.curse, false) + "");
        return super.show(actor0, f, f1, f2, f3, z);
    }
}

