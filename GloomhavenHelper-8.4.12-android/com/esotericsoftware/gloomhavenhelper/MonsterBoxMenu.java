package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.util.DesatDrawable;
import com.esotericsoftware.gloomhavenhelper.util.DragAdjust;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;

public class MonsterBoxMenu extends Menu {
    Label attackLabel;
    Label blessLabel;
    private ImageButton blessMinusButton;
    private ImageButton blessPlusButton;
    final MonsterBox box;
    Table buttonsTable;
    Table conditionsTable;
    private Image curseImage;
    Label curseLabel;
    private ImageButton curseMinusButton;
    private ImageButton cursePlusButton;
    private Image hpImage;
    Label hpLabel;
    private ImageButton hpMinusButton;
    private ImageButton hpPlusButton;
    ImageButton killButton;
    boolean lastArrowFlip;
    Table left;
    ImageTextButton levelButton;
    final Monster monster;
    Label moveLabel;
    Label rangeLabel;
    ImageButton summonButton;

    public MonsterBoxMenu(MonsterBox monsterBox0) {
        this.left = new Table();
        this.box = monsterBox0;
        this.monster = monsterBox0.monster;
        this.create();
        this.layoutUI();
        this.events();
        if(this.monster.type == MonsterType.summon) {
            this.setBackgroundOffset(-1.0f, 7.0f, 7.0f, 6.0f);
            return;
        }
        this.setBackgroundOffset(-1.0f, -4.0f, 7.0f, -6.0f);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        super.act(f);
        boolean z = true;
        this.hpMinusButton.setDisabled(this.monster.hp <= 0);
        int v = App.state.count(AttackModifier.bless, false);
        this.blessMinusButton.setDisabled(v <= 0);
        this.blessPlusButton.setDisabled(v >= 10);
        int v1 = App.state.count(AttackModifier.curse, false);
        this.curseMinusButton.setDisabled(v1 <= 0 || this.monster.stats.immuneCurse);
        ImageButton imageButton0 = this.cursePlusButton;
        if(v1 < 10 && !this.monster.stats.immuneCurse) {
            z = false;
        }
        imageButton0.setDisabled(z);
    }

    private void create() {
        ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
        ImageButtonBuilder imageButtonBuilder1 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray);
        this.hpMinusButton = imageButtonBuilder0.create();
        this.hpImage = App.image("blood-large");
        this.hpPlusButton = imageButtonBuilder1.create();
        this.blessMinusButton = imageButtonBuilder0.create();
        this.blessPlusButton = imageButtonBuilder1.create();
        this.curseMinusButton = imageButtonBuilder0.create();
        this.cursePlusButton = imageButtonBuilder1.create();
        this.hpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.hpLabel.setTouchable(Touchable.disabled);
        this.blessLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.blessLabel.setTouchable(Touchable.disabled);
        this.curseLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.curseLabel.setTouchable(Touchable.disabled);
        if(this.monster.stats.immuneCurse) {
            this.curseMinusButton.setDisabled(true);
            this.cursePlusButton.setDisabled(true);
            this.curseImage = App.image(new DesatDrawable(new Drawable[]{App.drawable(new String[]{"curse-large"})}));
            this.curseImage.setColor(App.disabledDim);
            this.curseLabel.setColor(App.lightGray);
        }
        else {
            this.curseImage = App.image("curse-large");
        }
        this.buttonsTable = new Table();
        this.conditionsTable = new Table();
        this.summonButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/summon-large").checked("selected")).over("selected", App.buttonGray)).create();
        this.summonButton.setUserObject(Condition.summon);
        this.killButton = App.imageButton().imageUp("psd/skull", App.buttonGray).imageOver("psd/skull", Color.WHITE).create();
        this.levelButton = App.imageTextButton("").imageUp("psd/level", App.buttonGray).imageOver("psd/level").font("plainLargeOutlineFixedNumbers").create();
        this.levelButton.clearChildren();
        this.levelButton.add(this.levelButton.getLabel()).space(8.0f);
        this.levelButton.add(this.levelButton.getImage()).padBottom(4.0f);
    }

    private void events() {
        this.hpPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(MonsterBoxMenu.this.monster.hp >= MonsterBoxMenu.this.monster.hpMax) {
                    return;
                }
                int v = 1;
                ++MonsterBoxMenu.this.monster.hp;
                MonsterBoxMenu.this.box.hpChanged(1);
                if(MonsterBoxMenu.this.hpLabel.getText().length != 0) {
                    v = App.parseInt("") + 1;
                }
                MonsterBoxMenu.this.hpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
                MonsterBoxMenu.this.hpLabel.setColor((v >= 0 ? App.healthGreen : App.healthRed));
            }
        });
        this.hpMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(MonsterBoxMenu.this.monster.hp <= 0) {
                    return;
                }
                --MonsterBoxMenu.this.monster.hp;
                int v = -1;
                MonsterBoxMenu.this.box.hpChanged(-1);
                if(MonsterBoxMenu.this.hpLabel.getText().length != 0) {
                    v = App.parseInt("") - 1;
                }
                MonsterBoxMenu.this.hpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
                MonsterBoxMenu.this.hpLabel.setColor((v >= 0 ? App.healthGreen : App.healthRed));
            }
        });
        new DragAdjust(this.hpLabel, new Actor[]{this.hpPlusButton, this.hpImage, this.hpMinusButton}) {
            long lastAnimateIcon;

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getMax() {
                return MonsterBoxMenu.this.monster.hpMax;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return MonsterBoxMenu.this.monster.hp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected void setValue(int v) {
                if(MonsterBoxMenu.this.monster.hp != v) {
                    MonsterBoxMenu.this.box.hpChanged(this.extra + v - this.start);
                }
                MonsterBoxMenu.this.monster.hp = v;
                MonsterBoxMenu.this.hpLabel.setColor((v + this.extra >= this.start ? App.healthGreen : App.healthRed));
            }
        };
        this.blessPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.bless, false) >= 10) {
                    return;
                }
                int v = App.parseInt("");
                MonsterBoxMenu.this.blessLabel.setText("" + (v + 1));
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
                MonsterBoxMenu.this.blessLabel.setText("" + v);
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
                MonsterBoxMenu.this.curseLabel.setText("" + (v + 1));
                App.state.addCurse();
            }
        });
        this.curseMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.count(AttackModifier.curse, false) == 0) {
                    return;
                }
                int v = Math.max(0, App.parseInt("") - 1);
                MonsterBoxMenu.this.curseLabel.setText("" + v);
                App.state.remove(AttackModifier.curse);
                App.state.attackModifiers.shuffle();
            }
        });
        this.summonButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(MonsterBoxMenu.this.summonButton.isChecked()) {
                    MonsterBoxMenu.this.monster.conditions.add(Condition.summonNew);
                    MonsterBoxMenu.this.monster.conditions.sort();
                    return;
                }
                MonsterBoxMenu.this.monster.conditions.removeValue(Condition.summon, true);
                if(MonsterBoxMenu.this.monster.conditions.removeValue(Condition.summonNew, true)) {
                    MonsterBoxMenu.this.monster.conditions.add(Condition.summon);
                    MonsterBoxMenu.this.monster.conditions.sort();
                    MonsterBoxMenu.this.summonButton.setChecked(true);
                }
            }
        });
        this.conditionsTable.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(((ImageButton)actor0) == MonsterBoxMenu.this.summonButton) {
                    return;
                }
                Condition condition0 = (Condition)((ImageButton)actor0).getUserObject();
                if(((ImageButton)actor0).isChecked()) {
                    if(!App.state.canDraw && MonsterBoxMenu.this.box.row.isCurrentTurn()) {
                        MonsterBoxMenu.this.monster.currentTurnConditions.add(condition0);
                    }
                    MonsterBoxMenu.this.monster.conditions.add(condition0);
                    MonsterBoxMenu.this.monster.conditions.sort();
                    MonsterBoxMenu.this.box.flashIcon(condition0.name());
                    return;
                }
                MonsterBoxMenu.this.monster.conditions.removeValue(condition0, true);
                MonsterBoxMenu.this.monster.currentTurnConditions.removeValue(condition0, true);
            }
        });
        this.killButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MonsterBoxMenu.this.animate = false;
                MonsterBoxMenu.this.hide();
                MonsterBoxMenu.this.animate = true;
                MonsterBoxMenu.this.box.removeMonster(true);
                App.state.changed();
            }
        });
        this.levelButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MonsterBoxMenu.this.animate = false;
                MonsterBoxMenu.this.hide();
                MonsterBoxMenu.this.animate = true;
                com.esotericsoftware.gloomhavenhelper.MonsterBoxMenu.11.1 monsterBoxMenu$11$10 = new TextMenu() {
                    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
                    public boolean hide() {
                        boolean z = super.hide();
                        if(z) {
                            App.state.changed();
                        }
                        return z;
                    }
                };
                ImageButton imageButton0 = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/star-large").checked("selected")).over("selected", App.buttonGray)).create();
                imageButton0.setChecked(MonsterBoxMenu.this.monster.conditions.contains(Condition.star, true));
                imageButton0.addListener(new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        boolean z = imageButton0.isChecked();
                        int v = 1;
                        if(z) {
                            MonsterBoxMenu.this.monster.conditions.add(Condition.star);
                            MonsterBoxMenu.this.monster.conditions.sort();
                            MonsterBoxMenu.this.box.flashIcon("star");
                            MonsterBoxMenu.this.box.row.animateIcon(MonsterBoxMenu.this.box, "conditions/star-large", 1.6f, 54.0f, 40.0f);
                        }
                        else {
                            MonsterBoxMenu.this.monster.conditions.removeValue(Condition.star, true);
                        }
                        int v1 = App.state.scenarioNumber;
                        String s = MonsterBoxMenu.this.monster.data.english;
                        boolean z1 = MonsterBoxMenu.this.monster.type == MonsterType.elite;
                        int v2 = v1 != 62 || !s.equals("Living Bones") || !z1 ? 0 : 1;
                        if(v1 != 87 || !s.equals("Ooze") || !z1) {
                            v = 0;
                        }
                        int v3 = App.gloom.playerCount();
                        if((v | v2) != 0 && v3 > 0) {
                            MonsterBoxMenu.this.monster.hpMax = MonsterBoxMenu.this.monster.stats.hpMax();
                            if(z) {
                                MonsterBoxMenu.this.monster.hpMax *= v3;
                            }
                            MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax;
                        }
                    }
                });
                imageButton0.addListener(App.tooltip("Special monsters can be marked with a star to denote that they are not affected by character abilities that target only normal or elite monsters."));
                if(MonsterBoxMenu.this.monster.type != MonsterType.summon) {
                    Table table0 = new Table(App.skin);
                    table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
                    table0.add(App.image("psd/level"));
                    ButtonGroup buttonGroup0 = new ButtonGroup();
                    for(int v = 0; v <= 7; ++v) {
                        TextButton textButton0 = App.textButton((v + "")).checkedFontColor(Color.WHITE).create();
                        buttonGroup0.add(textButton0);
                        textButton0.addListener(new ChangeListener() {
                            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                                MonsterBoxMenu.this.box.row.setLevel(v);
                            }
                        });
                        if(v == MonsterBoxMenu.this.box.row.getLevel()) {
                            textButton0.setChecked(true);
                        }
                        if(App.state.scenarioNumber >= 0 && v == App.state.scenarioLevel) {
                            TextButtonStyle textButton$TextButtonStyle0 = textButton0.getStyle();
                            textButton$TextButtonStyle0.up = App.drawable("selected", App.disabledGray);
                        }
                        table0.add(textButton0);
                        if(v == 3) {
                            table0.row();
                        }
                    }
                    table0.add(imageButton0);
                    monsterBoxMenu$11$10.table.add(table0).row();
                }
                ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
                ImageButtonBuilder imageButtonBuilder1 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
                ImageButton imageButton1 = imageButtonBuilder0.create();
                Image image0 = App.image("blood-large");
                ImageButton imageButton2 = imageButtonBuilder1.create();
                com.esotericsoftware.gloomhavenhelper.MonsterBoxMenu.11.4 monsterBoxMenu$11$40 = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Label
                    public void draw(Batch batch0, float f) {
                        this.setText(MonsterBoxMenu.this.monster.hp + "/" + MonsterBoxMenu.this.monster.hpMax);
                        super.draw(batch0, f);
                    }
                };
                monsterBoxMenu$11$40.setAlignment(1);
                monsterBoxMenu$11$40.setTouchable(Touchable.disabled);
                imageButton2.addListener(new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        if(MonsterBoxMenu.this.monster.hp >= MonsterBoxMenu.this.monster.hpMax) {
                            MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax + 1;
                        }
                        ++MonsterBoxMenu.this.monster.hpMax;
                    }
                });
                imageButton1.addListener(new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        if(MonsterBoxMenu.this.monster.hpMax <= 1) {
                            return;
                        }
                        if(MonsterBoxMenu.this.monster.hp <= MonsterBoxMenu.this.monster.hpMax) {
                            MonsterBoxMenu.this.monster.hp = MonsterBoxMenu.this.monster.hpMax - 1;
                        }
                        --MonsterBoxMenu.this.monster.hpMax;
                    }
                });
                new DragAdjust(null, new Actor[]{imageButton2, image0, imageButton1}) {
                    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
                    protected int getValue() {
                        return MonsterBoxMenu.this.monster.hpMax;
                    }

                    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
                    protected void setValue(int v) {
                        if(MonsterBoxMenu.this.monster.hp >= MonsterBoxMenu.this.monster.hpMax) {
                            MonsterBoxMenu.this.monster.hp = v;
                        }
                        MonsterBoxMenu.this.monster.hpMax = v;
                    }
                }.min = 1;
                Table table1 = new Table();
                table1.add(imageButton1).size(100.0f);
                table1.add(new Stack(new Actor[]{image0, new Container(monsterBoxMenu$11$40).bottom().width(0.0f)})).fill();
                table1.add(imageButton2).size(100.0f).row();
                monsterBoxMenu$11$10.table.add(table1);
                if(MonsterBoxMenu.this.monster.type == MonsterType.summon) {
                    ImageButton imageButton3 = imageButtonBuilder0.create();
                    Image image1 = App.image("abilities/move");
                    ImageButton imageButton4 = imageButtonBuilder1.create();
                    com.esotericsoftware.gloomhavenhelper.MonsterBoxMenu.11.8 monsterBoxMenu$11$80 = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Label
                        public void draw(Batch batch0, float f) {
                            if(MonsterBoxMenu.this.monster.summonMove == 0) {
                                this.setText("-");
                            }
                            else {
                                this.setText(MonsterBoxMenu.this.monster.summonMove);
                            }
                            super.draw(batch0, f);
                        }
                    };
                    monsterBoxMenu$11$80.setAlignment(1);
                    monsterBoxMenu$11$80.setTouchable(Touchable.disabled);
                    imageButton4.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            ++MonsterBoxMenu.this.monster.summonMove;
                        }
                    });
                    imageButton3.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            if(MonsterBoxMenu.this.monster.summonMove > 0) {
                                --MonsterBoxMenu.this.monster.summonMove;
                            }
                        }
                    });
                    ImageButton imageButton5 = imageButtonBuilder0.create();
                    Image image2 = App.image("abilities/attack");
                    ImageButton imageButton6 = imageButtonBuilder1.create();
                    com.esotericsoftware.gloomhavenhelper.MonsterBoxMenu.11.11 monsterBoxMenu$11$110 = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Label
                        public void draw(Batch batch0, float f) {
                            if(MonsterBoxMenu.this.monster.summonAttack == 0) {
                                this.setText("-");
                            }
                            else {
                                this.setText(MonsterBoxMenu.this.monster.summonAttack);
                            }
                            super.draw(batch0, f);
                        }
                    };
                    monsterBoxMenu$11$110.setAlignment(1);
                    monsterBoxMenu$11$110.setTouchable(Touchable.disabled);
                    imageButton6.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            ++MonsterBoxMenu.this.monster.summonAttack;
                        }
                    });
                    imageButton5.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            if(MonsterBoxMenu.this.monster.summonAttack > 0) {
                                --MonsterBoxMenu.this.monster.summonAttack;
                            }
                        }
                    });
                    ImageButton imageButton7 = imageButtonBuilder0.create();
                    Image image3 = App.image("abilities/range");
                    ImageButton imageButton8 = imageButtonBuilder1.create();
                    com.esotericsoftware.gloomhavenhelper.MonsterBoxMenu.11.14 monsterBoxMenu$11$140 = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Label
                        public void draw(Batch batch0, float f) {
                            if(MonsterBoxMenu.this.monster.summonRange == 0) {
                                this.setText("-");
                            }
                            else {
                                this.setText(MonsterBoxMenu.this.monster.summonRange);
                            }
                            super.draw(batch0, f);
                        }
                    };
                    monsterBoxMenu$11$140.setAlignment(1);
                    monsterBoxMenu$11$140.setTouchable(Touchable.disabled);
                    imageButton8.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            ++MonsterBoxMenu.this.monster.summonRange;
                        }
                    });
                    imageButton7.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            if(MonsterBoxMenu.this.monster.summonRange > 0) {
                                --MonsterBoxMenu.this.monster.summonRange;
                            }
                        }
                    });
                    table1.add(imageButton3).size(100.0f);
                    table1.add(new Stack(new Actor[]{image1, new Container(monsterBoxMenu$11$80).bottom().width(0.0f)})).fill();
                    table1.add(imageButton4).size(100.0f).row();
                    table1.add(imageButton5).size(100.0f);
                    table1.add(new Stack(new Actor[]{image2, new Container(monsterBoxMenu$11$110).bottom().width(0.0f)})).fill();
                    table1.add(imageButton6).size(100.0f).row();
                    table1.add(imageButton7).size(100.0f);
                    table1.add(new Stack(new Actor[]{image3, new Container(monsterBoxMenu$11$140).bottom().width(0.0f)})).fill();
                    table1.add(imageButton8).size(100.0f).row();
                }
                monsterBoxMenu$11$10.setBackgroundOffset(-1.0f, -2.0f, 7.0f, 3.0f);
                monsterBoxMenu$11$10.animate = false;
                monsterBoxMenu$11$10.show(MonsterBoxMenu.this);
                monsterBoxMenu$11$10.animate = true;
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean hide() {
        super.hide();
        this.box.checkDead(true);
        App.state.changed();
        return true;
    }

    private void layoutUI() {
        this.buttonsTable.columnDefaults(0).size(100.0f);
        this.buttonsTable.columnDefaults(1).fill();
        this.buttonsTable.columnDefaults(2).size(100.0f);
        this.buttonsTable.add(this.hpMinusButton);
        this.buttonsTable.add(new Stack(new Actor[]{this.hpImage, new Container(this.hpLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        this.buttonsTable.add(this.hpPlusButton).row();
        if(this.monster.type == MonsterType.summon) {
            Table table0 = new Table();
            table0.defaults().spaceTop(18.0f).spaceRight(9.0f);
            table0.add(App.image("abilities/move"));
            Label label0 = new Label("", App.skin, "plainLargeOutline", Color.WHITE);
            this.moveLabel = label0;
            table0.add(label0).row();
            table0.add(App.image("abilities/attack"));
            Label label1 = new Label("", App.skin, "plainLargeOutline", Color.WHITE);
            this.attackLabel = label1;
            table0.add(label1).row();
            table0.add(App.image("abilities/range"));
            Label label2 = new Label("", App.skin, "plainLargeOutline", Color.WHITE);
            this.rangeLabel = label2;
            table0.add(label2).row();
            this.buttonsTable.add(table0).colspan(3).growY().row();
        }
        else {
            this.buttonsTable.add(this.blessMinusButton);
            this.buttonsTable.add(new Stack(new Actor[]{App.image("bless-large"), new Container(this.blessLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
            this.buttonsTable.add(this.blessPlusButton).row();
            this.buttonsTable.add(this.curseMinusButton);
            this.buttonsTable.add(new Stack(new Actor[]{this.curseImage, new Container(this.curseLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
            this.buttonsTable.add(this.cursePlusButton).row();
        }
        this.left.add(this.buttonsTable).colspan(3).growY().top().row();
        this.left.add(this.killButton).size(100.0f);
        this.left.add(this.levelButton).size(100.0f);
        this.conditionsTable.defaults().size(100.0f);
        this.add(this.left).growY();
        this.add(this.conditionsTable);
    }

    private ImageButton newConditionButton(Condition condition0, ObjectMap objectMap0) {
        Drawable drawable0 = App.drawable(new String[]{"conditions/" + condition0.name() + "-large"});
        ImageButton imageButton0 = ((ImageButtonBuilder)((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp(drawable0).checked("selected")).over("selected", App.buttonGray)).disabled("white", Color.CLEAR)).imageDisabled(new DesatDrawable(new Drawable[]{drawable0})).create();
        imageButton0.setUserObject(condition0);
        objectMap0.put(condition0, imageButton0);
        return imageButton0;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        this.hpLabel.setText("");
        this.blessLabel.setText(App.state.count(AttackModifier.bless, false) + "");
        this.curseLabel.setText(App.state.count(AttackModifier.curse, false) + "");
        this.levelButton.setText(this.box.row.getLevel() + "");
        this.conditionsTable.clearChildren();
        ObjectMap objectMap0 = new ObjectMap();
        for(int v = 3; v < Condition.values.length; ++v) {
            Condition condition0 = Condition.values[v];
            if(condition0 != Condition.doom && condition0 != Condition.hatchet) {
                ImageButton imageButton0 = this.newConditionButton(condition0, objectMap0);
                this.conditionsTable.add(imageButton0);
                if((v - 2) % 3 == 0) {
                    this.conditionsTable.row();
                }
                if(condition0 == Condition.poison) {
                    Table table0 = new Table();
                    table0.defaults().size(100.0f);
                    if(this.monster.type != MonsterType.summon) {
                        table0.add(this.summonButton);
                    }
                    if(App.gloom.hasCharacterClass(CharacterClass.Doomstalker)) {
                        table0.add(this.newConditionButton(Condition.doom, objectMap0));
                    }
                    if(App.gloom.hasCharacterClass(CharacterClass.Hatchet)) {
                        table0.add(this.newConditionButton(Condition.hatchet, objectMap0));
                    }
                    if(table0.hasChildren()) {
                        this.conditionsTable.add(table0).colspan(3).row();
                    }
                    else {
                        this.conditionsTable.add().height(100.0f).row();
                    }
                }
                if(this.monster.stats.immunities.contains(condition0)) {
                    imageButton0.setDisabled(true);
                    imageButton0.setColor(App.disabledDim);
                }
            }
        }
        if(this.monster.type == MonsterType.summon) {
            this.moveLabel.setText((this.monster.summonMove == 0 ? "-" : Integer.toString(this.monster.summonMove)));
            this.attackLabel.setText((this.monster.summonAttack == 0 ? "-" : Integer.toString(this.monster.summonAttack)));
            this.rangeLabel.setText((this.monster.summonRange == 0 ? "-" : Integer.toString(this.monster.summonRange)));
        }
        for(Object object0: objectMap0.values()) {
            ((ImageButton)object0).setChecked(false);
        }
        this.summonButton.setChecked(false);
        for(Object object1: this.monster.conditions) {
            Condition condition1 = (Condition)object1;
            if(condition1 != Condition.summon && condition1 != Condition.summonNew && condition1 != Condition.star) {
                ImageButton imageButton1 = (ImageButton)objectMap0.get(condition1);
                if(imageButton1 == null) {
                    continue;
                }
                imageButton1.setChecked(true);
            }
            else {
                this.summonButton.setChecked(true);
            }
        }
        return super.show(actor0, f, f1, f2, f3, z);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    protected void updatePosition() {
        super.updatePosition();
        if(this.lastArrowFlip != this.arrowFlip) {
            this.lastArrowFlip = this.arrowFlip;
            this.clearChildren();
            this.left.clearChildren();
            this.left.add(this.buttonsTable).colspan(3).growY().top().row();
            if(this.arrowFlip) {
                if(this.monster.type != MonsterType.summon) {
                    this.left.add(this.summonButton).size(100.0f);
                }
                this.left.add(this.levelButton).size(100.0f);
                this.left.add(this.killButton).size(100.0f);
                this.add(this.conditionsTable);
                this.add(this.left).fill().growY();
            }
            else {
                this.left.add(this.killButton).size(100.0f);
                this.left.add(this.levelButton).size(100.0f);
                if(this.monster.type != MonsterType.summon) {
                    this.left.add(this.summonButton).size(100.0f);
                }
                this.add(this.left).fill().growY();
                this.add(this.conditionsTable);
            }
            this.buttonsTable.invalidate();
        }
    }
}

