package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Player;
import com.esotericsoftware.gloomhavenhelper.util.DragAdjust;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerRowMenu extends Menu {
    final ObjectMap conditionButtons;
    Table conditionsTable;
    private Image hpImage;
    Label hpLabel;
    private ImageButton hpMinusButton;
    private ImageButton hpPlusButton;
    ImageButton killButton;
    ImageTextButton levelButton;
    private Image lootImage;
    Label lootLabel;
    private ImageButton lootMinusButton;
    private ImageButton lootPlusButton;
    final Player player;
    final PlayerRow row;
    private static final int startCondition = 3;
    private Image xpImage;
    Label xpLabel;
    private ImageButton xpMinusButton;
    private ImageButton xpPlusButton;

    public PlayerRowMenu(PlayerRow playerRow0) {
        this.conditionButtons = new ObjectMap();
        this.row = playerRow0;
        this.player = playerRow0.player;
        this.create();
        this.layoutUI();
        this.events();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public void act(float f) {
        super.act(f);
        boolean z = true;
        this.xpMinusButton.setDisabled(this.player.xp <= 0);
        ImageButton imageButton0 = this.lootMinusButton;
        if(this.player.loot > 0) {
            z = false;
        }
        imageButton0.setDisabled(z);
    }

    private void create() {
        ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
        ImageButtonBuilder imageButtonBuilder1 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
        this.hpMinusButton = imageButtonBuilder0.create();
        this.hpImage = App.image("blood-large");
        this.hpPlusButton = imageButtonBuilder1.create();
        this.xpMinusButton = imageButtonBuilder0.create();
        this.xpImage = App.image("psd/xp-large", App.xpBlue);
        this.xpPlusButton = imageButtonBuilder1.create();
        this.lootMinusButton = imageButtonBuilder0.create();
        this.lootImage = App.image("psd/loot-large", App.lootGold);
        this.lootPlusButton = imageButtonBuilder1.create();
        this.levelButton = App.imageTextButton("").imageUp("psd/level", App.buttonGray).imageOver("psd/level").font("plainLargeOutlineFixedNumbers").create();
        this.levelButton.clearChildren();
        this.levelButton.add(this.levelButton.getLabel()).space(8.0f);
        this.levelButton.add(this.levelButton.getImage()).padBottom(4.0f);
        this.killButton = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("psd/skull", App.buttonGray).imageOver("psd/skull").imageChecked("psd/skull").checked("selected")).over("selected", App.buttonGray)).create();
        this.hpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.hpLabel.setTouchable(Touchable.disabled);
        this.xpLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.xpLabel.setTouchable(Touchable.disabled);
        this.lootLabel = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE);
        this.lootLabel.setTouchable(Touchable.disabled);
    }

    private void events() {
        this.hpPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(PlayerRowMenu.this.player.hp >= PlayerRowMenu.this.player.hpMax) {
                    return;
                }
                int v = 1;
                ++PlayerRowMenu.this.player.hp;
                PlayerRowMenu.this.row.hpChanged(1);
                if(PlayerRowMenu.this.hpLabel.getText().length != 0) {
                    v = App.parseInt("") + 1;
                }
                PlayerRowMenu.this.hpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
                PlayerRowMenu.this.hpLabel.setColor((v >= 0 ? App.healthGreen : App.healthRed));
            }
        });
        this.hpMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(PlayerRowMenu.this.player.hp <= 0) {
                    return;
                }
                --PlayerRowMenu.this.player.hp;
                int v = -1;
                PlayerRowMenu.this.row.hpChanged(-1);
                if(PlayerRowMenu.this.hpLabel.getText().length != 0) {
                    v = App.parseInt("") - 1;
                }
                PlayerRowMenu.this.hpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
                PlayerRowMenu.this.hpLabel.setColor((v >= 0 ? App.healthGreen : App.healthRed));
            }
        });
        new DragAdjust(this.hpLabel, new Actor[]{this.hpPlusButton, this.hpImage, this.hpMinusButton}) {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getMax() {
                return PlayerRowMenu.this.player.hpMax;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRowMenu.this.player.hp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected void setValue(int v) {
                if(PlayerRowMenu.this.player.hp != v) {
                    PlayerRowMenu.this.row.hpChanged(this.extra + v - this.start);
                }
                PlayerRowMenu.this.player.hp = v;
                PlayerRowMenu.this.hpLabel.setColor((v + this.extra >= this.start ? App.healthGreen : App.healthRed));
            }
        };
        this.xpPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                int v = 1;
                ++PlayerRowMenu.this.player.xp;
                if(PlayerRowMenu.this.xpLabel.getText().length != 0) {
                    v = App.parseInt("") + 1;
                }
                PlayerRowMenu.this.xpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
            }
        });
        this.xpMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(PlayerRowMenu.this.player.xp == 0) {
                    return;
                }
                --PlayerRowMenu.this.player.xp;
                int v = PlayerRowMenu.this.xpLabel.getText().length == 0 ? -1 : App.parseInt("") - 1;
                PlayerRowMenu.this.xpLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
            }
        });
        new DragAdjust(this.xpLabel, new Actor[]{this.xpPlusButton, this.xpImage, this.xpMinusButton}) {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRowMenu.this.player.xp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected void setValue(int v) {
                PlayerRowMenu.this.player.xp = v;
            }
        };
        this.lootPlusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                int v = 1;
                ++PlayerRowMenu.this.player.loot;
                if(PlayerRowMenu.this.lootLabel.getText().length != 0) {
                    v = App.parseInt("") + 1;
                }
                PlayerRowMenu.this.lootLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
            }
        });
        this.lootMinusButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(PlayerRowMenu.this.player.loot == 0) {
                    return;
                }
                --PlayerRowMenu.this.player.loot;
                int v = PlayerRowMenu.this.lootLabel.getText().length == 0 ? -1 : App.parseInt("") - 1;
                PlayerRowMenu.this.lootLabel.setText((v == 0 ? "" : (v <= 0 ? "" : "+") + v));
            }
        });
        new DragAdjust(this.lootLabel, new Actor[]{this.lootPlusButton, this.lootImage, this.lootMinusButton}) {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRowMenu.this.player.loot;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected void setValue(int v) {
                PlayerRowMenu.this.player.loot = v;
            }
        };
        this.conditionsTable.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                Condition condition0 = (Condition)((ImageButton)actor0).getUserObject();
                if(((ImageButton)actor0).isChecked()) {
                    if(!App.state.canDraw && PlayerRowMenu.this.row.isCurrentTurn()) {
                        PlayerRowMenu.this.player.currentTurnConditions.add(condition0);
                    }
                    PlayerRowMenu.this.player.conditions.add(condition0);
                    PlayerRowMenu.this.player.conditions.sort();
                    PlayerRowMenu.this.row.flashIcon(condition0.name());
                    return;
                }
                PlayerRowMenu.this.player.conditions.removeValue(condition0, true);
                PlayerRowMenu.this.player.currentTurnConditions.removeValue(condition0, true);
            }
        });
        this.killButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                PlayerRowMenu.this.player.exhausted = PlayerRowMenu.this.killButton.isChecked();
                App.state.changed();
            }
        });
        this.levelButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                PlayerRowMenu.this.animate = false;
                PlayerRowMenu.this.hide();
                PlayerRowMenu.this.animate = true;
                AtomicBoolean atomicBoolean0 = new AtomicBoolean();
                com.esotericsoftware.gloomhavenhelper.PlayerRowMenu.12.1 playerRowMenu$12$10 = new TextMenu() {
                    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
                    public boolean hide() {
                        super.hide();
                        if(atomicBoolean0.get()) {
                            App.state.changed();
                        }
                        return true;
                    }
                };
                Table table0 = new Table(App.skin);
                table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
                table0.add(App.image("psd/level"));
                ButtonGroup buttonGroup0 = new ButtonGroup();
                for(int v = 1; v <= 9; ++v) {
                    TextButton textButton0 = App.textButton((v + "")).checkedFontColor(Color.WHITE).create();
                    buttonGroup0.add(textButton0);
                    textButton0.addListener(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            PlayerRowMenu.this.row.setLevel(v);
                        }
                    });
                    if(v == PlayerRowMenu.this.player.level) {
                        textButton0.setChecked(true);
                    }
                    table0.add(textButton0);
                    if(v == 4) {
                        table0.row();
                    }
                }
                playerRowMenu$12$10.table.add(table0).colspan(2).row();
                TextField textField0 = App.textField(PlayerRowMenu.this.player.name);
                textField0.setTextFieldListener(new TextFieldListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldListener
                    public void keyTyped(TextField textField0, char c) {
                        String s = textField0.getText().trim();
                        PlayerRowMenu.this.player.name = s.length() == 0 ? PlayerRowMenu.this.player.characterClass.toString() : s;
                        App.state.changed();
                    }
                });
                playerRowMenu$12$10.table.add(textField0).growX().padLeft(18.0f).padRight(9.0f);
                ImageButtonBuilder imageButtonBuilder0 = App.imageButton().imageUp("psd/sub", App.buttonGray).imageOver("psd/sub").imageDisabled("psd/sub", App.disabledGray);
                ImageButtonBuilder imageButtonBuilder1 = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add");
                ImageButton imageButton0 = imageButtonBuilder0.create();
                Image image0 = App.image("blood-large");
                ImageButton imageButton1 = imageButtonBuilder1.create();
                com.esotericsoftware.gloomhavenhelper.PlayerRowMenu.12.4 playerRowMenu$12$40 = new Label("", App.skin, "plainMediumOutlineFixedNumbers", Color.WHITE) {
                    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Label
                    public void draw(Batch batch0, float f) {
                        this.setText(PlayerRowMenu.this.player.hp + "/" + PlayerRowMenu.this.player.hpMax);
                        super.draw(batch0, f);
                    }
                };
                playerRowMenu$12$40.setAlignment(1);
                playerRowMenu$12$40.setTouchable(Touchable.disabled);
                imageButton1.addListener(new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        if(PlayerRowMenu.this.player.hp >= PlayerRowMenu.this.player.hpMax) {
                            PlayerRowMenu.this.player.hp = PlayerRowMenu.this.player.hpMax + 1;
                        }
                        ++PlayerRowMenu.this.player.hpMax;
                    }
                });
                imageButton0.addListener(new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        if(PlayerRowMenu.this.player.hpMax <= 1) {
                            return;
                        }
                        if(PlayerRowMenu.this.player.hp <= PlayerRowMenu.this.player.hpMax) {
                            PlayerRowMenu.this.player.hp = PlayerRowMenu.this.player.hpMax - 1;
                        }
                        --PlayerRowMenu.this.player.hpMax;
                        atomicBoolean0.set(true);
                    }
                });
                new DragAdjust(null, new Actor[]{imageButton1, image0, imageButton0}) {
                    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
                    protected int getValue() {
                        return PlayerRowMenu.this.player.hpMax;
                    }

                    @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
                    protected void setValue(int v) {
                        if(PlayerRowMenu.this.player.hp >= PlayerRowMenu.this.player.hpMax) {
                            PlayerRowMenu.this.player.hp = v;
                        }
                        PlayerRowMenu.this.player.hpMax = v;
                        atomicBoolean0.set(true);
                    }
                }.min = 1;
                Table table1 = new Table();
                table1.add(imageButton0).size(100.0f);
                table1.add(new Stack(new Actor[]{image0, new Container(playerRowMenu$12$40).bottom().width(0.0f)})).fill();
                table1.add(imageButton1).size(100.0f);
                playerRowMenu$12$10.table.add(table1);
                playerRowMenu$12$10.animate = false;
                playerRowMenu$12$10.show(PlayerRowMenu.this);
                playerRowMenu$12$10.animate = true;
            }
        });
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean hide() {
        App.state.changed();
        return super.hide();
    }

    private void layoutUI() {
        Table table0 = new Table();
        table0.columnDefaults(0).size(100.0f);
        table0.columnDefaults(1).fill();
        table0.columnDefaults(2).size(100.0f);
        table0.add(this.hpMinusButton);
        table0.add(new Stack(new Actor[]{this.hpImage, new Container(this.hpLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        table0.add(this.hpPlusButton).row();
        table0.add(this.xpMinusButton);
        table0.add(new Stack(new Actor[]{this.xpImage, new Container(this.xpLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        table0.add(this.xpPlusButton).row();
        table0.add(this.lootMinusButton);
        table0.add(new Stack(new Actor[]{this.lootImage, new Container(this.lootLabel).pad(0.0f, 0.0f, -48.0f, -44.0f)}));
        table0.add(this.lootPlusButton).row();
        table0.add(this.killButton).size(100.0f);
        table0.add(this.levelButton).colspan(2).size(100.0f).left();
        this.conditionsTable = new Table();
        this.conditionsTable.defaults().size(100.0f);
        for(int v = 3; v < Condition.values.length; ++v) {
            Condition condition0 = Condition.values[v];
            if(condition0 != Condition.doom && condition0 != Condition.hatchet) {
                ImageButton imageButton0 = ((ImageButtonBuilder)((ImageButtonBuilder)App.imageButton().imageUp("conditions/" + condition0.name() + "-large").checked("selected")).over("selected", App.buttonGray)).create();
                imageButton0.setUserObject(condition0);
                this.conditionButtons.put(condition0, imageButton0);
                if(v % 3 == 0) {
                    this.conditionsTable.row();
                }
                this.conditionsTable.add(imageButton0);
                if(condition0 == Condition.poison) {
                    this.conditionsTable.row();
                    this.conditionsTable.add().height(100.0f).row();
                }
            }
        }
        this.defaults().space(9.0f);
        this.add(table0);
        this.add(this.conditionsTable);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        this.killButton.setChecked(this.player.exhausted);
        this.levelButton.setText(this.player.level + "");
        this.hpLabel.setText("");
        this.xpLabel.setText("");
        this.lootLabel.setText("");
        for(Object object0: this.conditionButtons.values()) {
            ((ImageButton)object0).setChecked(false);
        }
        for(Object object1: this.player.conditions) {
            ((ImageButton)this.conditionButtons.get(((Condition)object1))).setChecked(true);
        }
        return super.show(actor0, f, f1, f2, f3, z);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    protected void updatePosition() {
        Label label0 = this.row.nameLabel;
        float f = this.row.nameLabel.getPrefWidth();
        float f1 = this.row.nameLabel.getHeight();
        label0.localToStageCoordinates(App.v2.set(f, ((float)Math.round(f1 / 2.0f))));
        float f2 = App.v2.x + 6.0f;
        float f3 = App.v2.y;
        Vector2 vector20 = App.v2.set(0.0f, 0.0f);
        this.row.localToStageCoordinates(vector20);
        this.setPosition(f2, f3, 0.0f, 0.0f, App.v2.x + 2.0f, App.v2.y, this.row.getWidth() - 3.0f, this.row.getHeight() + ((float)(this.row.boxes.size == 0 ? 0 : 6)));
        super.updatePosition();
    }
}

