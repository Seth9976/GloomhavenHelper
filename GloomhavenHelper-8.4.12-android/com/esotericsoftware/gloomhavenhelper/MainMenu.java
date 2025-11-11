package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.Player;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.network.Network;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.Output;
import com.esotericsoftware.gloomhavenhelper.util.Serialization;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;
import java.util.Comparator;

public class MainMenu extends TextMenu {
    public CheckBox clientCheckbox;
    int lastScreenHeight;
    int lastScreenWidth;
    public TextButton redoItem;
    private TextButton removeMonsterItem;
    private TextButton removePlayerItem;
    final Task saveTask;
    public TextButton undoItem;

    public MainMenu() {
        this.lastScreenWidth = Gdx.graphics.getWidth();
        this.lastScreenHeight = Gdx.graphics.getHeight();
        this.saveTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                App.game.saveConfig();
            }
        };
        Container container0 = new Container(new Label("v8.4.12", App.skin, "plainMediumOutline", App.buttonGray)).bottom().right().pad(0.0f, 0.0f, 16.0f, 22.0f);
        container0.setFillParent(true);
        this.addActor(container0);
        this.populate();
    }

    public void addCharacters(boolean z) {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        Table table0 = new Table(App.skin);
        table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table0.add(App.image("psd/level"));
        ButtonGroup buttonGroup0 = new ButtonGroup();
        for(int v = 1; v <= 9; ++v) {
            TextButton textButton0 = ((TextButtonBuilder)App.textButton((v + "")).checkedFontColor(Color.WHITE).checked("selected")).create();
            if(v == 1) {
                textButton0.setChecked(true);
            }
            buttonGroup0.add(textButton0);
            table0.add(textButton0);
            if(v == 4) {
                table0.row();
            }
        }
        textMenu0.table.add(table0).row();
        this.updateAddPlayerMenu(textMenu0, buttonGroup0, false);
        textMenu0.animate = z;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    void addMonsters(boolean z) {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        int v1 = 0;
        for(Object object0: App.gloom.playerRows) {
            v1 += ((PlayerRow)object0).player.level;
        }
        int v2 = (int)Math.ceil(((float)v1) / ((float)App.gloom.playerCount()) / 2.0f);
        Table table0 = new Table(App.skin);
        table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table0.add(App.image("psd/level"));
        ButtonGroup buttonGroup0 = new ButtonGroup();
        for(int v = 0; v <= 7; ++v) {
            TextButton textButton0 = ((TextButtonBuilder)App.textButton((v + "")).checkedFontColor(Color.WHITE).checked("selected")).create();
            if(App.state.scenarioNumber < 0) {
                if(v == v2) {
                    textButton0.setChecked(true);
                }
            }
            else if(v == App.state.scenarioLevel) {
                textButton0.setChecked(true);
                TextButtonStyle textButton$TextButtonStyle0 = textButton0.getStyle();
                textButton$TextButtonStyle0.up = App.drawable("selected", App.disabledGray);
            }
            buttonGroup0.add(textButton0);
            table0.add(textButton0);
            if(v == 3) {
                table0.row();
            }
        }
        textMenu0.table.add(table0).row();
        textMenu0.addSeperator().row();
        Table table1 = new Table();
        table1.add(this.monsterTable("Base Game", Edition.OG, buttonGroup0));
        Table table2 = new Table();
        table2.defaults().growX();
        table2.add(this.monsterTable("Forgotten Circles", Edition.FC, buttonGroup0)).row();
        table2.add(this.monsterTable("Jaws of the Lion", Edition.JotL, buttonGroup0)).padTop(40.0f);
        Table table3 = new Table().padTop(20.0f);
        table3.defaults().top().space(20.0f).growX();
        table3.add(table1);
        table3.add(table2);
        textMenu0.table.add(table3).row();
        textMenu0.animate = z;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    void addPlayer(int v, CharacterClass characterClass0) {
        if(v > 9) {
            v = 9;
        }
        Player player0 = new Player();
        player0.level = v;
        player0.name = characterClass0.toString();
        player0.characterClass = characterClass0;
        int v1 = characterClass0.hpMax(v);
        player0.hpMax = v1;
        player0.hp = v1;
        if(player0.characterClass == CharacterClass.Escort || player0.characterClass == CharacterClass.Objective) {
            player0.init(99);
        }
        PlayerRow playerRow0 = new PlayerRow(player0);
        App.gloom.playerRows.add(playerRow0);
        App.gloom.rows.addActorAt(0, playerRow0);
        if(characterClass0 == CharacterClass.BeastTyrant) {
            MonsterBox monsterBox0 = playerRow0.addMonsterBox(1, App.summonData, player0.level, false, false, SummonColor.beast, false);
            int v2 = player0.level * 2 + 8;
            monsterBox0.monster.hpMax = v2;
            monsterBox0.monster.hp = v2;
            monsterBox0.monster.summonMove = 3;
            monsterBox0.monster.summonAttack = 2;
            monsterBox0.monster.isNew = false;
        }
        App.state.changed();
    }

    public void addSection(boolean z) {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        Label label0 = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
        label0.setAlignment(1);
        Container container0 = new Container(label0);
        textMenu0.table.add(container0).row();
        com.esotericsoftware.gloomhavenhelper.MainMenu.18 mainMenu$180 = new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                String s = ((TextButton)actor0).getText().toString();
                String s1 = label0.getText().replace("_", "") + s;
                if(s1.length() > 3) {
                    s1 = s1.substring(1);
                }
                label0.setText(s1 + "_");
            }
        };
        Table table0 = new Table(App.skin);
        table0.pad(-6.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table0.columnDefaults(0).right();
        table0.columnDefaults(2).left();
        for(int v = 1; v <= 9; ++v) {
            table0.add(((TextButtonBuilder)App.textButton((v + "")).change(mainMenu$180)).create());
            if(v % 3 == 0) {
                table0.row();
            }
        }
        textMenu0.table.add(table0).row();
        TextButton textButton0 = (TextButton)textMenu0.addTextItem("OK", new ChangeListener() {
            // 去混淆评级： 中等(70)
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
            }
        }).getActor();
        textMenu0.table.row();
        textButton0.pad(0.0f, 0.0f, 0.0f, 14.0f);
        textButton0.getLabel().setAlignment(16);
        table0.add().expandX().left().uniformX().height(100.0f);
        table0.add(((TextButtonBuilder)App.textButton("0").change(mainMenu$180)).create());
        table0.add(textButton0).expandX().right().uniformX().size(100.0f);
        textMenu0.animate = z;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    void bladeswarmDialog(int v) {
        Label label0 = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
        label0.setAlignment(1);
        com.esotericsoftware.gloomhavenhelper.MainMenu.27 mainMenu$270 = new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                String s = ((TextButton)actor0).getText().toString();
                label0.setText("" + s + "_");
            }
        };
        Table table0 = new Table(App.skin);
        table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        new ButtonGroup();
        for(int v1 = 0; v1 < 12; ++v1) {
            TextButton textButton0 = App.textButton(("DRXLMSBGEATW".charAt(v1) + "")).create();
            textButton0.addListener(mainMenu$270);
            if(v1 % 4 == 0) {
                table0.row();
            }
            table0.add(textButton0);
        }
        TextMenu textMenu0 = new TextMenu();
        Label label1 = new Label("Enter the secret:", App.skin, "plainLargeOutline", Color.WHITE);
        textMenu0.table.add(label1).fill(false).row();
        textMenu0.table.add(table0).row();
        textMenu0.addSeperator().row();
        textMenu0.table.add(label0).row();
        TextButton textButton1 = (TextButton)textMenu0.addTextItem("OK", new ChangeListener() {
            // 去混淆评级： 中等(70)
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
            }
        }).getActor();
        textMenu0.table.row();
        textButton1.pad(0.0f, 0.0f, 0.0f, 14.0f);
        textButton1.getLabel().setAlignment(16);
        TextButton textButton2 = (TextButton)textMenu0.addTextItem("Clear", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                label0.setText("_");
            }
        }).getActor();
        textMenu0.table.row();
        textButton2.padLeft(10.0f).padRight(-18.0f);
        table0.row();
        table0.add(textButton2).expandX().left().uniformX().height(100.0f).colspan(2);
        table0.add(textButton1).expandX().right().uniformX().size(100.0f).colspan(2);
        textMenu0.animate = this.animate;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
    public void draw(Batch batch0, float f) {
        boolean z = false;
        this.undoItem.setDisabled(App.game.undoIndex >= App.game.undos.size - 1);
        TextButton textButton0 = this.redoItem;
        if(App.game.undoIndex <= 0) {
            z = true;
        }
        textButton0.setDisabled(z);
        super.draw(batch0, f);
    }

    void languageDialog() {
        TextMenu textMenu0 = new TextMenu();
        Label label0 = new Label("The app needs to be restarted after changing the language.", App.skin, "plainLargeOutline", Color.WHITE);
        textMenu0.table.add(label0).fill(false).pad(20.0f).colspan(4).row();
        CheckBox checkBox0 = textMenu0.radioItem("English", App.config.language.equals("en"), null);
        CheckBox checkBox1 = textMenu0.radioItem("German", App.config.language.equals("de"), null);
        CheckBox checkBox2 = textMenu0.radioItem("Russian", App.config.language.equals("ru"), null);
        CheckBox checkBox3 = textMenu0.radioItem("Polish", App.config.language.equals("pl"), null);
        CheckBox checkBox4 = textMenu0.radioItem("Czech", App.config.language.equals("cz"), null);
        CheckBox checkBox5 = textMenu0.radioItem("Korean", App.config.language.equals("ko"), null);
        CheckBox checkBox6 = textMenu0.radioItem("Spanish", App.config.language.equals("es"), null);
        CheckBox checkBox7 = textMenu0.radioItem("Hungarian", App.config.language.equals("hu"), null);
        CheckBox checkBox8 = textMenu0.radioItem("Portuguese", App.config.language.equals("pt"), null);
        CheckBox checkBox9 = textMenu0.radioItem("Japanese", App.config.language.equals("ja"), null);
        CheckBox checkBox10 = textMenu0.radioItem("French", App.config.language.equals("fr"), null);
        CheckBox checkBox11 = textMenu0.radioItem("Italian", App.config.language.equals("it"), null);
        new ButtonGroup(new Button[]{checkBox0, checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8, checkBox9, checkBox10, checkBox11});
        textMenu0.table.add(checkBox0);
        textMenu0.table.add(checkBox4);
        textMenu0.table.add(checkBox7);
        textMenu0.table.add(checkBox10).row();
        textMenu0.table.add(checkBox1);
        textMenu0.table.add(checkBox11);
        textMenu0.table.add(checkBox9);
        textMenu0.table.add(checkBox5).row();
        textMenu0.table.add(checkBox3);
        textMenu0.table.add(checkBox8);
        textMenu0.table.add(checkBox2);
        textMenu0.table.add(checkBox6).row();
        ((TextButton)textMenu0.addTextItem("OK", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                String s = checkBox1.isChecked() ? "de" : "en";
                if(checkBox2.isChecked()) {
                    s = "ru";
                }
                if(checkBox3.isChecked()) {
                    s = "pl";
                }
                if(checkBox4.isChecked()) {
                    s = "cz";
                }
                if(checkBox5.isChecked()) {
                    s = "ko";
                }
                if(checkBox6.isChecked()) {
                    s = "es";
                }
                if(checkBox8.isChecked()) {
                    s = "pt";
                }
                if(checkBox7.isChecked()) {
                    s = "hu";
                }
                if(checkBox9.isChecked()) {
                    s = "ja";
                }
                if(checkBox10.isChecked()) {
                    s = "fr";
                }
                if(checkBox11.isChecked()) {
                    s = "it";
                }
                if(!s.equals(App.config.language)) {
                    App.config.language = s;
                    App.game.saveConfig();
                    App.game.close();
                    if(Gdx.app.getType() == ApplicationType.WebGL) {
                        App.toast("Please refresh the page.");
                    }
                }
                textMenu0.hide();
            }
        }).colspan(4).getActor()).getLabel().setAlignment(1);
        textMenu0.animate = this.animate;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    Table monsterTable(String s, Edition edition0, ButtonGroup buttonGroup0) {
        Table table0 = new Table();
        table0.add(new Label(s, App.skin, "plainLargeOutline", Color.WHITE)).padLeft(20.0f).left().row();
        table0.defaults().growX();
        Array array0 = new Array(App.monsterDatas);
        array0.sort(new Comparator() {
            public int compare(MonsterData monsterData0, MonsterData monsterData1) {
                int v = monsterData0.edition.compareTo(monsterData1.edition);
                if(v != 0) {
                    return v;
                }
                int v1 = Boolean.compare(monsterData0.isBoss(), monsterData1.isBoss());
                if(v1 != 0) {
                    return v1;
                }
                return monsterData0.isBoss() ? 0 : monsterData0.name.compareTo(monsterData1.name);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((MonsterData)object0), ((MonsterData)object1));
            }
        });
        boolean z = false;
        for(Object object0: array0) {
            MonsterData monsterData0 = (MonsterData)object0;
            if(monsterData0 != App.summonData && !monsterData0.hidden && monsterData0.edition == edition0) {
                if(!z && monsterData0.isBoss()) {
                    table0.add(this.seperator()).row();
                    z = true;
                }
                TextButton textButton0 = (TextButton)table0.add(this.textItem(monsterData0.name, new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        int v = App.parseInt(((TextButton)buttonGroup0.getChecked()).getText().toString());
                        if(App.state.solo) {
                            ++v;
                        }
                        MonsterRow monsterRow0 = new MonsterRow(monsterData0, v);
                        App.gloom.addMonsterRow(monsterRow0);
                        if(!App.state.canDraw && !App.state.trackStandees) {
                            monsterRow0.showAbility();
                        }
                        if(!App.state.canDraw) {
                            App.gloom.sortByInitiative(0.0f);
                        }
                        App.state.changed();
                        ((TextButton)actor0).setDisabled(true);
                    }
                })).getActor();
                table0.row();
                for(Object object1: App.gloom.monsterRows) {
                    if(((MonsterRow)object1).data == monsterData0) {
                        textButton0.setDisabled(true);
                    }
                }
            }
        }
        return table0;
    }

    public void populate() {
        this.table.clearChildren();
        if(!App.game.isPurchased()) {
            this.addTextItem("Remove Ads", new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.game.purchase();
                }
            }).row();
            this.addSeperator().row();
        }
        this.undoItem = this.textItem(Text.undo, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.game.undoIndex >= App.game.undos.size - 1) {
                    return;
                }
                int v = App.game.undoIndex + 1;
                App.game.undoIndex = v;
                Output output0 = (Output)App.game.undos.get(v);
                Serialization.lastOutput.clear();
                App.game.loadState(output0.toBytes(), false);
                Serialization.lastOutput.clear();
                App.state.changed(false, null);
            }
        });
        this.undoItem.getLabel().setAlignment(1);
        this.redoItem = this.textItem("Redo", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.game.undoIndex <= 0) {
                    return;
                }
                int v = App.game.undoIndex - 1;
                App.game.undoIndex = v;
                Output output0 = (Output)App.game.undos.get(v);
                Serialization.lastOutput.clear();
                App.game.loadState(output0.toBytes(), false);
                Serialization.lastOutput.clear();
                App.state.changed(false, null);
            }
        });
        this.redoItem.getLabel().setAlignment(1);
        Table table0 = new Table();
        table0.defaults().grow();
        table0.add(this.undoItem);
        table0.add(this.redoItem);
        this.table.add(table0).grow().row();
        this.addSeperator().row();
        this.addTextItem("Set Scenario", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.setScenario(false);
            }
        }).row();
        this.addTextItem("Add Section", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.addSection(false);
            }
        }).row();
        this.addSeperator().row();
        this.addTextItem("Add Monsters", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.addMonsters(false);
            }
        }).row();
        this.removeMonsterItem = (TextButton)this.addTextItem("Remove Monsters", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.removeMonsterMenu();
            }
        }).getActor();
        this.table.row();
        this.addSeperator().row();
        this.addTextItem("Add Characters", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.addCharacters(false);
            }
        }).row();
        this.removePlayerItem = (TextButton)this.addTextItem("Remove Characters", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.removePlayerMenu();
            }
        }).getActor();
        this.table.row();
        this.addSeperator().row();
        this.addTextItem("Settings", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                MainMenu.this.settingsMenu(MainMenu.this);
            }
        }).row();
        if(App.game.canOpenURL()) {
            this.addTextItem("Documentation", new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.game.openURL("http://esotericsoftware.com/gloomhaven-helper#Usage");
                }
            }).row();
        }
        if(Gdx.app.getType() == ApplicationType.Desktop) {
            this.addTextItem("Exit", new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    Gdx.app.exit();
                }
            }).row();
        }
    }

    void removeMonsterMenu() {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        textMenu0.addTextItem("<Remove All>", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                for(Object object0: App.gloom.monsterRows) {
                    ((MonsterRow)object0).remove();
                }
                App.gloom.monsterRows.clear();
                App.state.changed();
                textMenu0.hide();
            }
        }).row();
        for(Object object0: App.gloom.monsterRows) {
            textMenu0.addTextItem(((MonsterRow)object0).data.name, new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    ((MonsterRow)object0).remove();
                    App.gloom.monsterRows.removeValue(((MonsterRow)object0), true);
                    App.state.changed();
                    actor0.remove();
                    if(App.gloom.monsterRows.size == 0) {
                        textMenu0.hide();
                        return;
                    }
                    textMenu0.pack();
                }
            }).row();
        }
        textMenu0.animate = false;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    void removePlayerMenu() {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        textMenu0.addTextItem("<Remove All>", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                for(Object object0: App.gloom.playerRows) {
                    ((PlayerRow)object0).remove();
                }
                App.gloom.playerRows.clear();
                App.state.changed();
                textMenu0.hide();
            }
        }).row();
        for(Object object0: App.gloom.playerRows) {
            textMenu0.addTextItem(((PlayerRow)object0).toString(), new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    ((PlayerRow)object0).remove();
                    App.gloom.playerRows.removeValue(((PlayerRow)object0), true);
                    App.state.changed();
                    actor0.remove();
                    if(App.gloom.playerRows.size == 0) {
                        textMenu0.hide();
                        return;
                    }
                    textMenu0.pack();
                }
            }).row();
        }
        textMenu0.animate = false;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    public void setScenario(boolean z) {
        this.animate = false;
        this.hide();
        this.animate = true;
        TextMenu textMenu0 = new TextMenu();
        int v = 0;
        for(Object object0: App.gloom.playerRows) {
            PlayerRow playerRow0 = (PlayerRow)object0;
            playerRow0.player.exhausted = false;
            if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                v += playerRow0.player.level;
            }
        }
        int v1 = (int)Math.ceil(((float)v) / ((float)App.gloom.playerCount()) / 2.0f);
        Table table0 = new Table(App.skin);
        table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table0.add(App.image("psd/level"));
        ButtonGroup buttonGroup0 = new ButtonGroup();
        for(int v2 = 0; v2 <= 7; ++v2) {
            TextButton textButton0 = ((TextButtonBuilder)App.textButton((v2 + "")).checkedFontColor(Color.WHITE).checked("selected")).create();
            if(v2 == v1) {
                TextButtonStyle textButton$TextButtonStyle0 = textButton0.getStyle();
                textButton$TextButtonStyle0.up = App.drawable("selected", App.disabledGray);
            }
            if(v2 == App.state.scenarioLevel || App.state.scenarioLevel == -1 && v2 == v1) {
                textButton0.setChecked(true);
            }
            buttonGroup0.add(textButton0);
            table0.add(textButton0).left();
            if(v2 == 3) {
                table0.row();
            }
        }
        CheckBox checkBox0 = textMenu0.checkBoxItem("Solo", App.state.solo, null);
        checkBox0.padRight(0.0f);
        table0.add(checkBox0).size(Value.prefWidth, Value.prefHeight).colspan(2);
        textMenu0.table.add(table0).row();
        textMenu0.addSeperator().row();
        Label label0 = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
        label0.setAlignment(1);
        Container container0 = new Container(label0);
        textMenu0.table.add(container0).row();
        com.esotericsoftware.gloomhavenhelper.MainMenu.14 mainMenu$140 = new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                String s = ((TextButton)actor0).getText().toString();
                String s1 = label0.getText().replace("_", "") + s;
                if(s1.length() > 3) {
                    s1 = s1.substring(1);
                }
                label0.setText(s1 + "_");
            }
        };
        Table table1 = new Table(App.skin);
        table1.pad(-6.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table1.columnDefaults(0).right();
        table1.columnDefaults(2).left();
        for(int v3 = 1; v3 <= 9; ++v3) {
            table1.add(((TextButtonBuilder)App.textButton((v3 + "")).change(mainMenu$140)).create());
            if(v3 % 3 == 0) {
                table1.row();
            }
        }
        textMenu0.table.add(table1).row();
        CheckBox checkBox1 = this.checkBoxItem("Jaws of the Lion", App.state.jotl, null);
        CheckBox checkBox2 = this.checkBoxItem("Add monsters", true, null);
        TextButton textButton1 = (TextButton)textMenu0.addTextItem("OK", new ChangeListener() {
            // 去混淆评级： 中等(60)
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
            }
        }).getActor();
        textMenu0.table.row();
        textButton1.pad(0.0f, 0.0f, 0.0f, 14.0f);
        textButton1.getLabel().setAlignment(16);
        TextButton textButton2 = (TextButton)textMenu0.addTextItem("Custom", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                int v = App.parseInt(((TextButton)buttonGroup0.getChecked()).getText().toString());
                App.gloom.setScenario(0, v, checkBox0.isChecked(), false, false);
                textMenu0.hide();
                App.state.changed();
            }
        }).getActor();
        textMenu0.table.row();
        textButton2.padLeft(35.0f).padRight(-18.0f);
        table1.add(textButton2).expandX().left().uniformX().height(100.0f);
        table1.add(((TextButtonBuilder)App.textButton("0").change(mainMenu$140)).create());
        table1.add(textButton1).expandX().right().uniformX().size(100.0f);
        Table table2 = new Table();
        table2.add(checkBox1).spaceRight(20.0f);
        table2.add(checkBox2).spaceRight(20.0f);
        textMenu0.table.add(table2).center().fill(false);
        textMenu0.animate = z;
        textMenu0.show(this);
        textMenu0.animate = true;
    }

    public TextMenu setScenarioLevel() {
        TextMenu textMenu0 = new TextMenu();
        int v1 = 0;
        for(Object object0: App.gloom.playerRows) {
            PlayerRow playerRow0 = (PlayerRow)object0;
            if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                v1 += playerRow0.player.level;
            }
        }
        int v2 = (int)Math.ceil(((float)v1) / ((float)App.gloom.playerCount()) / 2.0f);
        Table table0 = new Table(App.skin);
        table0.pad(5.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
        table0.add(App.image("psd/level"));
        ButtonGroup buttonGroup0 = new ButtonGroup();
        for(int v = 0; v <= 7; ++v) {
            TextButton textButton0 = App.textButton((v + "")).checkedFontColor(Color.WHITE).create();
            if(v == v2) {
                TextButtonStyle textButton$TextButtonStyle0 = textButton0.getStyle();
                textButton$TextButtonStyle0.up = App.drawable("selected", App.disabledGray);
            }
            if(v == App.state.scenarioLevel || App.state.scenarioLevel == -1 && v == v2) {
                textButton0.setChecked(true);
            }
            buttonGroup0.add(textButton0);
            table0.add(textButton0).left();
            if(v == 3) {
                table0.row();
            }
        }
        CheckBox checkBox0 = textMenu0.checkBoxItem("Solo", App.state.solo, null);
        checkBox0.setChecked(App.state.solo);
        checkBox0.padRight(0.0f);
        table0.add(checkBox0).size(Value.prefWidth, Value.prefHeight).colspan(2);
        textMenu0.table.add(table0).row();
        table0.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                int v = App.parseInt(((TextButton)buttonGroup0.getChecked()).getText().toString());
                App.gloom.setScenarioLevel(v, checkBox0.isChecked(), App.state.jotl);
                textMenu0.hide();
                App.state.changed();
            }
        });
        return textMenu0;
    }

    public void settingsMenu(Menu menu0) {
        menu0.animate = false;
        menu0.hide();
        menu0.animate = true;
        TextField textField0 = App.textField(App.config.clientHost);
        com.esotericsoftware.gloomhavenhelper.MainMenu.32 mainMenu$320 = new TextMenu() {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
            public void draw(Batch batch0, float f) {
                textField0.setText(App.config.clientHost);
                super.draw(batch0, f);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
            public boolean hide() {
                MainMenu.this.clientCheckbox = null;
                boolean z = super.hide();
                if(z) {
                    App.gloom.updateHelpText();
                    Gdx.input.setOnscreenKeyboardVisible(false);
                    App.game.saveConfig();
                }
                return z;
            }
        };
        ((CheckBox)mainMenu$320.addCheckBoxItem("Help", App.config.help, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.config.help = !App.config.help;
                App.game.saveConfig();
            }
        }).getActor()).addListener(App.tooltip("When unchecked, the helpful messages won\'t be shown."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Random standees", App.state.randomStandees, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.randomStandees = !App.state.randomStandees;
                App.state.changed();
            }
        }).getActor()).addListener(App.tooltip("When checked, adding monsters automatically and randomly chooses the standee number."));
        mainMenu$320.table.row();
        ((CheckBox)mainMenu$320.addCheckBoxItem("Hide stats", App.state.hideStats, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.hideStats = !App.state.hideStats;
                App.state.changed();
            }
        }).getActor()).addListener(App.tooltip("When checked, stats for monsters that have not been spawned are hidden. Press and hold stats to show."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Expire conditions", App.state.expireConditions, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.expireConditions = !App.state.expireConditions;
                App.state.changed();
            }
        }).getActor()).addListener(App.tooltip("When checked, conditions that expire at the end of the turn will be removed automatically. When using this, it is important to click each row\'s portrait after taking each turn so that end-of-turn conditions can be managed properly."));
        mainMenu$320.table.row();
        ((CheckBox)mainMenu$320.addCheckBoxItem("Calculate stats", App.state.calculateStats, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.calculateStats = !App.state.calculateStats;
                App.state.changed();
            }
        }).getActor()).addListener(App.tooltip("When checked, the monster ability cards show calculated values (eg, Attack 4 instead of Attack +1)."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Ability cards", App.state.abilityCards, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.abilityCards = !App.state.abilityCards;
                App.state.changed();
                App.gloom.rows.invalidateHierarchy();
            }
        }).getActor()).addListener(App.tooltip("When unchecked, ability cards for monsters are not shown. Use this when you want to track monster HP and conditions, but not ability cards."));
        mainMenu$320.table.row();
        ((CheckBox)mainMenu$320.addCheckBoxItem("Elites first", App.state.elitesFirst, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.elitesFirst = !App.state.elitesFirst;
                App.state.changed();
                for(Object object0: App.gloom.monsterRows) {
                    ((MonsterRow)object0).boxes.sort();
                    ((MonsterRow)object0).monstersGroup.getChildren().sort();
                    ((MonsterRow)object0).monstersGroup.invalidate();
                }
            }
        }).getActor()).addListener(App.tooltip("When checked, elite monsters are sorted first for each monster row. When unchecked, they are sorted only by standee number."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Ability numbers", App.config.abilityNumbers, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.config.abilityNumbers = !App.config.abilityNumbers;
                App.game.saveConfig();
            }
        }).getActor()).addListener(App.tooltip("When checked, monster ability cards will show the card number."));
        mainMenu$320.table.row();
        ((CheckBox)mainMenu$320.addCheckBoxItem("Drag HP", App.config.hpDrag, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.config.hpDrag = !App.config.hpDrag;
                App.game.saveConfig();
            }
        }).getActor()).addListener(App.tooltip("When checked, monster and character rows can be dragged left/right to adjust HP."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Auto scroll", App.config.autoScroll, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.config.autoScroll = !App.config.autoScroll;
                App.game.saveConfig();
            }
        }).getActor()).addListener(App.tooltip("When checked, the app will automatically scroll to the active row."));
        mainMenu$320.table.row();
        ((CheckBox)mainMenu$320.addCheckBoxItem("Track standees", App.state.trackStandees, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.trackStandees = !App.state.trackStandees;
                App.state.changed();
                App.gloom.rows.invalidateHierarchy();
            }
        }).getActor()).addListener(App.tooltip("When unchecked, the individual monsters for each row are not shown. Use this when you want to track monster HP and conditions some other way."));
        ((CheckBox)mainMenu$320.addCheckBoxItem("Hide monsters", App.config.hideMonsters, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.config.hideMonsters = !App.config.hideMonsters;
                App.game.saveConfig();
                for(Object object0: App.gloom.monsterRows) {
                    ((MonsterRow)object0).invalidate();
                }
                App.gloom.rows.invalidateHierarchy();
                App.root.validate();
            }
        }).getActor()).addListener(App.tooltip("When checked, monster rows are not shown."));
        mainMenu$320.table.row();
        mainMenu$320.addSeperator().colspan(2).row();
        CheckBox checkBox0 = mainMenu$320.checkBoxItem("Required", App.state.playerInit == PlayerInit.dragNumberRequired || App.state.playerInit == PlayerInit.numpad, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GameState gameState0 = App.state;
                gameState0.playerInit = ((CheckBox)actor0).isChecked() ? PlayerInit.dragNumberRequired : PlayerInit.dragNumber;
                App.state.changed();
            }
        });
        checkBox0.setDisabled(App.state.playerInit == PlayerInit.dragOrder || App.state.playerInit == PlayerInit.numpad);
        checkBox0.addListener(App.tooltip("When checked, character initiative numbers must be set before monster ability cards can be drawn."));
        CheckBox checkBox1 = mainMenu$320.radioItem("Drag order", App.state.playerInit == PlayerInit.dragOrder, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.playerInit = PlayerInit.dragOrder;
                checkBox0.setChecked(false);
                checkBox0.setDisabled(true);
                for(Object object0: App.gloom.playerRows) {
                    PlayerRow playerRow0 = (PlayerRow)object0;
                    if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                        playerRow0.player.init(0);
                    }
                    else {
                        playerRow0.player.init(99);
                    }
                }
                App.state.changed();
            }
        });
        checkBox1.addListener(App.tooltip("When checked, drag character portraits to set the intiative order."));
        CheckBox checkBox2 = mainMenu$320.radioItem("Drag number", App.state.playerInit == PlayerInit.dragNumber || App.state.playerInit == PlayerInit.dragNumberRequired, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GameState gameState0 = App.state;
                gameState0.playerInit = checkBox0.isChecked() ? PlayerInit.dragNumberRequired : PlayerInit.dragNumber;
                checkBox0.setDisabled(false);
                for(Object object0: App.gloom.playerRows) {
                    PlayerRow playerRow0 = (PlayerRow)object0;
                    if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                        playerRow0.player.init(0);
                    }
                    else {
                        playerRow0.player.init(99);
                    }
                }
                App.state.changed();
            }
        });
        checkBox2.addListener(App.tooltip("When checked, drag character portraits to the right to set the intiative number."));
        CheckBox checkBox3 = mainMenu$320.radioItem("Numpad", App.state.playerInit == PlayerInit.numpad, new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.state.playerInit = PlayerInit.numpad;
                checkBox0.setChecked(true);
                checkBox0.setDisabled(true);
                for(Object object0: App.gloom.playerRows) {
                    PlayerRow playerRow0 = (PlayerRow)object0;
                    if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                        playerRow0.player.init(0);
                    }
                    else {
                        playerRow0.player.init(99);
                    }
                }
                App.state.changed();
            }
        });
        checkBox3.addListener(App.tooltip("When checked, click character portraits to enter the intiative number."));
        new ButtonGroup(new Button[]{checkBox1, checkBox2, checkBox3});
        Table table0 = new Table();
        table0.defaults().growX();
        table0.add(checkBox3);
        table0.add(checkBox2);
        table0.add(checkBox1);
        Label label0 = new Label("Character Initiative", App.skin, "plainLargeOutline", App.buttonGray);
        mainMenu$320.table.add(label0).space(12.0f).left().top().pad(9.0f, 20.0f, 0.0f, 0.0f);
        mainMenu$320.table.add(checkBox0).right().row();
        mainMenu$320.table.add(table0).colspan(2);
        mainMenu$320.table.row();
        mainMenu$320.addSeperator().colspan(2).row();
        VerticalGroup verticalGroup0 = new VerticalGroup().left().grow();
        VerticalGroup verticalGroup1 = new VerticalGroup().left().grow();
        mainMenu$320.table.add(verticalGroup0).top().spaceRight(20.0f);
        mainMenu$320.table.add(verticalGroup1).top();
        if(Gdx.app.getType() != ApplicationType.WebGL) {
            CheckBox checkBox4 = mainMenu$320.checkBoxItem("Server", App.config.server, null);
            checkBox4.addListener(App.tooltip("When checked, a server runs on the port shown to accept connections from other devices. Any changes made on any of the devices will appear on all the others."));
            this.clientCheckbox = mainMenu$320.checkBoxItem("Client", App.config.client, new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    if(MainMenu.this.clientCheckbox == null) {
                        return;
                    }
                    checkBox4.setChecked(false);
                    GameConfig gameConfig0 = App.config;
                    gameConfig0.client = MainMenu.this.clientCheckbox.isChecked();
                    App.config.server = false;
                    App.game.saveConfig();
                    MainMenu.this.settingsMenu(mainMenu$320);
                }
            });
            this.clientCheckbox.addListener(App.tooltip("When checked, the app connects to a server using the IP or host name and port shown."));
            verticalGroup0.addActor(this.clientCheckbox);
            textField0.setTextFieldListener(new TextFieldListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldListener
                public void keyTyped(TextField textField0, char c) {
                    if(textField0.getText().equals(App.config.clientHost)) {
                        return;
                    }
                    App.config.clientHost = textField0.getText();
                    if(App.config.client) {
                        MainMenu.this.saveTask.cancel();
                        Timer.schedule(MainMenu.this.saveTask, 0.5f);
                        return;
                    }
                    App.game.saveConfig();
                }
            });
            TextField textField1 = App.textField((App.config.clientPort + ""));
            textField1.setTextFieldListener(new TextFieldListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldListener
                public void keyTyped(TextField textField0, char c) {
                    try {
                        int v = App.parseInt(textField0.getText());
                        if(App.config.clientPort == v) {
                            return;
                        }
                        if(v <= 0) {
                            return;
                        }
                        App.config.clientPort = v;
                        if(App.config.client) {
                            MainMenu.this.saveTask.cancel();
                            Timer.schedule(MainMenu.this.saveTask, 0.5f);
                            return;
                        }
                        App.game.saveConfig();
                    }
                    catch(NumberFormatException unused_ex) {
                    }
                }
            });
            Table table1 = new Table().padBottom(8.0f).left();
            table1.add(new Label("Host:", App.skin, "plainLargeOutline", App.buttonGray)).padLeft(65.0f).space(12.0f);
            table1.add(textField0).width(225.0f).padRight(9.0f).space(12.0f).row();
            table1.add(new Label("Port:", App.skin, "plainLargeOutline", App.buttonGray)).padLeft(63.0f);
            table1.add(textField1).width(104.0f).padRight(18.0f).left();
            verticalGroup0.addActor(table1);
            checkBox4.addListener(new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    if(MainMenu.this.clientCheckbox != null) {
                        MainMenu.this.clientCheckbox.setChecked(false);
                    }
                    App.config.server = !App.config.server;
                    App.config.client = false;
                    App.game.saveConfig();
                    MainMenu.this.settingsMenu(mainMenu$320);
                }
            });
            verticalGroup0.addActor(checkBox4);
            TextField textField2 = App.textField((App.config.serverPort + ""));
            textField2.setTextFieldListener(new TextFieldListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextField$TextFieldListener
                public void keyTyped(TextField textField0, char c) {
                    try {
                        int v = App.parseInt(textField0.getText());
                        if(App.config.serverPort == v) {
                            return;
                        }
                        if(v <= 0) {
                            return;
                        }
                        App.config.serverPort = v;
                        if(App.config.server) {
                            MainMenu.this.saveTask.cancel();
                            Timer.schedule(MainMenu.this.saveTask, 0.5f);
                            return;
                        }
                        App.game.saveConfig();
                    }
                    catch(NumberFormatException unused_ex) {
                    }
                }
            });
            Table table2 = new Table().padBottom(8.0f).left();
            table2.columnDefaults(0).padLeft(63.0f).right().space(12.0f);
            table2.columnDefaults(1).padRight(18.0f).expandX().left().space(12.0f);
            String s = Network.getIPs();
            if(s.length() > 0) {
                table2.row().padTop(-8.0f);
                table2.add(new Label("Local IPs:", App.skin, "plainLargeOutline", App.buttonGray)).top();
                table2.add(new Label(s, App.skin, "plainLargeOutline", App.buttonGray)).row();
            }
            table2.add(new Label("Port:", App.skin, "plainLargeOutline", App.buttonGray));
            table2.add(textField2).width(104.0f);
            verticalGroup0.addActor(table2);
        }
        TextButton textButton0 = mainMenu$320.textItem("Zoom Out", null);
        textButton0.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                App.stage.addAction(Actions.repeat(13, new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        App.gloom.zoomOut();
                        return true;
                    }
                }));
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                App.gloom.zoomOut = true;
                super.touchDown(inputEvent0, f, f1, v, v1);
                return true;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                super.touchUp(inputEvent0, f, f1, v, v1);
                App.gloom.zoomOut = false;
            }
        });
        verticalGroup1.addActor(textButton0);
        textButton0.addListener(App.tooltip("Scales the app smaller to provide more vertical space and reduce or remove the need for scrolling. Press until the desired zoom is reached."));
        TextButton textButton1 = mainMenu$320.textItem("Zoom In", null);
        textButton1.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                App.stage.addAction(Actions.repeat(13, new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        App.gloom.zoomIn();
                        return true;
                    }
                }));
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                App.gloom.zoomIn = true;
                super.touchDown(inputEvent0, f, f1, v, v1);
                return true;
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void touchUp(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                super.touchUp(inputEvent0, f, f1, v, v1);
                App.gloom.zoomIn = false;
            }
        });
        verticalGroup1.addActor(textButton1);
        textButton1.addListener(App.tooltip("Scales the app larger to use up more screen space. Press until the desired zoom is reached."));
        TextButton textButton2 = mainMenu$320.textItem("Reset Zoom", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                App.viewport.setMinWorldHeight(((float)Math.max(Gdx.graphics.getHeight(), 800)));
                App.gloom.updateViewport();
                App.gloom.updateTextures();
                App.config.zoom = 0.0f;
                App.config.zoomReset = true;
                App.game.saveConfig();
            }
        });
        verticalGroup1.addActor(textButton2);
        textButton2.addListener(App.tooltip("Resets the zoom to the default."));
        if(Gdx.app.getType() == ApplicationType.Desktop) {
            verticalGroup1.addActor(mainMenu$320.seperator());
            verticalGroup1.addActor(mainMenu$320.textItem((Gdx.graphics.isFullscreen() ? "Windowed" : "Fullscreen"), new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    if(Gdx.graphics.isFullscreen()) {
                        Gdx.graphics.setWindowedMode(MainMenu.this.lastScreenWidth, MainMenu.this.lastScreenHeight);
                        App.config.fullscreen = false;
                    }
                    else {
                        MainMenu.this.lastScreenWidth = Gdx.graphics.getWidth();
                        MainMenu.this.lastScreenHeight = Gdx.graphics.getHeight();
                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                        Gdx.graphics.setVSync(true);
                        App.config.fullscreen = true;
                    }
                    App.game.saveConfig();
                    MainMenu.this.settingsMenu(mainMenu$320);
                }
            }));
        }
        TextButton textButton3 = mainMenu$320.textItem("Language", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                mainMenu$320.animate = false;
                mainMenu$320.hide();
                MainMenu.this.animate = false;
                MainMenu.this.languageDialog();
                MainMenu.this.animate = true;
            }
        });
        verticalGroup1.addActor(this.seperator());
        verticalGroup1.addActor(textButton3);
        TextButton textButton4 = mainMenu$320.textItem("Envelope X", new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                mainMenu$320.animate = false;
                mainMenu$320.hide();
                MainMenu.this.animate = false;
                MainMenu.this.bladeswarmDialog(App.state.scenarioLevel);
                MainMenu.this.animate = true;
            }
        });
        verticalGroup1.addActor(this.seperator());
        verticalGroup1.addActor(textButton4);
        textButton4.setDisabled(App.gloom.hasCharacterClass(CharacterClass.Bladeswarm));
        if(Gdx.app.getType() == ApplicationType.Android) {
            TextButton textButton5 = mainMenu$320.textItem("Privacy policy", new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.game.openURL("http://esotericsoftware.com/files/ghh/privacy.html");
                }
            });
            TextButtonStyle textButton$TextButtonStyle0 = new TextButtonStyle(textButton5.getStyle());
            textButton$TextButtonStyle0.font = App.plainSmall;
            textButton5.setStyle(textButton$TextButtonStyle0);
            verticalGroup1.addActor(new Container(textButton5).bottom().right().padBottom(-15.0f));
        }
        else if(Gdx.app.getType() == ApplicationType.iOS) {
            TextButton textButton6 = mainMenu$320.textItem("Privacy policy", new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.game.openURL("https://www.badlogicgames.com/gloomhavenhelper/privacypolicy.html");
                }
            });
            TextButtonStyle textButton$TextButtonStyle1 = new TextButtonStyle(textButton6.getStyle());
            textButton$TextButtonStyle1.font = App.plainSmall;
            textButton6.setStyle(textButton$TextButtonStyle1);
            verticalGroup1.addActor(new Container(textButton6).bottom().right().padBottom(-15.0f));
        }
        mainMenu$320.animate = false;
        mainMenu$320.show(menu0);
        mainMenu$320.animate = true;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.TextMenu
    public boolean show(Actor actor0, float f, float f1, float f2, float f3, boolean z) {
        boolean z1 = true;
        this.removePlayerItem.setDisabled(App.gloom.playerRows.size == 0);
        TextButton textButton0 = this.removeMonsterItem;
        if(App.gloom.monsterRows.size != 0) {
            z1 = false;
        }
        textButton0.setDisabled(z1);
        return super.show(actor0, f, f1, f2, f3, z);
    }

    void updateAddPlayerMenu(TextMenu textMenu0, ButtonGroup buttonGroup0, boolean z) {
        Actor actor0 = (Actor)textMenu0.table.getChildren().first();
        textMenu0.table.clearChildren();
        textMenu0.table.add(actor0).row();
        textMenu0.addSeperator().row();
        Array array0 = new Array();
        array0.addAll(CharacterClass.values);
        array0.removeValue(CharacterClass.Demolitionist, true);
        array0.removeValue(CharacterClass.RedGuard, true);
        array0.removeValue(CharacterClass.Voidwarden, true);
        array0.removeValue(CharacterClass.Hatchet, true);
        int v = array0.indexOf(CharacterClass.Diviner, true);
        array0.insert(v + 1, CharacterClass.Demolitionist);
        array0.insert(v + 2, CharacterClass.Hatchet);
        array0.insert(v + 3, CharacterClass.RedGuard);
        array0.insert(v + 4, CharacterClass.Voidwarden);
        boolean z1 = true;
        for(Object object0: array0) {
            CharacterClass characterClass0 = (CharacterClass)object0;
            if(characterClass0 != CharacterClass.Bladeswarm) {
                if(characterClass0 == CharacterClass.Diviner || characterClass0 == CharacterClass.Demolitionist) {
                    textMenu0.addSeperator().row();
                }
                ImageTextButton imageTextButton0 = (ImageTextButton)textMenu0.addImageTextItem((z1 || z && characterClass0 != CharacterClass.Bladeswarm || characterClass0 == CharacterClass.Diviner || characterClass0 == CharacterClass.Demolitionist || characterClass0 == CharacterClass.RedGuard || characterClass0 == CharacterClass.Voidwarden || characterClass0 == CharacterClass.Hatchet ? characterClass0.toString() : "???"), "class-icons/" + characterClass0.name(), new ChangeListener() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                    public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                        if(actor0 instanceof TextButton) {
                            return;
                        }
                        int v = App.parseInt(((TextButton)buttonGroup0.getChecked()).getText().toString());
                        MainMenu.this.addPlayer(v, characterClass0);
                        if(characterClass0 != CharacterClass.Escort && characterClass0 != CharacterClass.Objective) {
                            ((ImageTextButton)actor0).setDisabled(true);
                        }
                    }
                }).getActor();
                textMenu0.table.row();
                imageTextButton0.getImageCell().size(50.0f, 60.0f).padRight(15.0f);
                imageTextButton0.getLabelCell().expandX().left();
                imageTextButton0.getImage().setScaling(Scaling.none);
                imageTextButton0.getStyle().overFontColor = characterClass0.color;
                for(Object object1: App.gloom.playerRows) {
                    if(((PlayerRow)object1).player.characterClass == characterClass0 && characterClass0 != CharacterClass.Escort && characterClass0 != CharacterClass.Objective) {
                        imageTextButton0.setDisabled(true);
                    }
                }
                if(characterClass0 == CharacterClass.Objective) {
                    textMenu0.addSeperator().row();
                }
                else if(characterClass0 == CharacterClass.Voidwarden) {
                    textMenu0.addSeperator().row();
                    textMenu0.table.add(((TextButtonBuilder)App.textButton((z ? "Hide Names" : "Show Names")).change(new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            MainMenu.this.updateAddPlayerMenu(textMenu0, buttonGroup0, !z);
                        }
                    })).create()).row();
                    textMenu0.addSeperator().row();
                    z1 = false;
                }
            }
        }
    }
}

