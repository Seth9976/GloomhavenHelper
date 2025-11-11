package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.Timer;
import com.hm.gloomhavenhelper.model.CharacterClass;
import com.hm.gloomhavenhelper.model.Monster;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.Player;
import com.hm.gloomhavenhelper.model.PlayerInit;
import com.hm.gloomhavenhelper.model.Scenario;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.network.Message;
import com.hm.gloomhavenhelper.network.Network;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.Output;
import com.hm.gloomhavenhelper.util.Serialization;
import com.hm.gloomhavenhelper.util.TextMenu;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainMenu extends TextMenu {
   private TextButton removeMonsterItem;
   private TextButton removePlayerItem;
   public TextButton undoItem;
   public TextButton redoItem;
   int lastScreenWidth = Gdx.graphics.getWidth();
   int lastScreenHeight = Gdx.graphics.getHeight();
   public CheckBox clientCheckbox;
   final Timer.Task saveTask = new Timer.Task() {
      @Override
      public void run() {
         App.game.saveConfig();
      }
   };

   public MainMenu() {
      Container version = new Container(new Label("9.1.0", App.skin, "plainMediumOutline", App.buttonGray)).bottom().right().pad(0.0F, 0.0F, 16.0F, 22.0F);
      version.setFillParent(true);
      this.addActor(version);
      this.populate();
   }

   public void populate() {
      this.table.clearChildren();
      if (!App.game.isPurchased()) {
         this.addTextItem(Text.removeAds, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.game.purchase();
            }
         }).row();
         this.addSeperator().row();
      }

      this.undoItem = this.textItem(Text.undo, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (Network.clientIsConnected()) {
               Network.send(Message.undo, null, 0, 0);
            } else if (App.game.undoIndex < App.game.undos.size - 1) {
               Output output = (Output)App.game.undos.get(++App.game.undoIndex);
               Serialization.lastOutput.clear();
               App.game.loadState(output.toBytes(), false);
               Serialization.lastOutput.clear();
               App.state.changed(false, null);
            }
         }
      });
      this.undoItem.getLabel().setAlignment(1);
      this.redoItem = this.textItem(Text.redo, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (Network.clientIsConnected()) {
               Network.send(Message.redo, null, 0, 0);
            } else if (App.game.undoIndex > 0) {
               Queue undos = App.game.undos;
               Game game = App.game;
               int n = game.undoIndex - 1;
               game.undoIndex = n;
               Output output = (Output)undos.get(n);
               Serialization.lastOutput.clear();
               App.game.loadState(output.toBytes(), false);
               Serialization.lastOutput.clear();
               App.state.changed(false, null);
            }
         }
      });
      this.redoItem.getLabel().setAlignment(1);
      Table undoRedo = new Table();
      undoRedo.defaults().grow();
      undoRedo.add(this.undoItem);
      undoRedo.add(this.redoItem);
      this.table.add(undoRedo).grow().row();
      this.addSeperator().row();
      this.addTextItem(Text.setScenario, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.setScenario(false);
         }
      }).row();
      this.addTextItem(Text.addSection, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.addSection(false);
         }
      }).row();
      this.addSeperator().row();
      this.addTextItem(Text.addMonsters, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.addMonsters(false);
         }
      }).row();
      this.removeMonsterItem = (TextButton)this.addTextItem(Text.removeMonsters, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.removeMonsterMenu();
         }
      }).getActor();
      this.table.row();
      this.addSeperator().row();
      this.addTextItem(Text.addCharacters, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.addCharacters(false);
         }
      }).row();
      this.removePlayerItem = (TextButton)this.addTextItem(Text.removeCharacters, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.removePlayerMenu();
         }
      }).getActor();
      this.table.row();
      this.addSeperator().row();
      this.addTextItem(Text.settings, new ChangeListener() {
         final MainMenu mainMenu = MainMenu.this;

         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            MainMenu.this.settingsMenu(this.mainMenu);
         }
      }).row();
      if (App.game.canOpenURL()) {
         this.addTextItem(Text.documentation, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.game.openURL("http://esotericsoftware.com/gloomhaven-helper#Usage");
            }
         }).row();
      }

      if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
         this.addTextItem(Text.exit, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               Gdx.app.exit();
            }
         }).row();
      }
   }

   public void setScenario(boolean animate) {
      this.animate = false;
      this.hide();
      this.animate = true;
      final TextMenu menu = new TextMenu();
      int normal = 0;

      for (PlayerRow row : App.gloom.playerRows) {
         row.player.exhausted = false;
         if (!row.player.characterClass.nonPlayer) {
            normal += row.player.level;
         }
      }

      if (App.state.soloFH) {
         normal = (int)Math.ceil(((float)normal / App.gloom.playerCount() + 1.0F) / 2.0F);
      } else {
         normal = (int)Math.ceil((float)normal / App.gloom.playerCount() / 2.0F);
      }

      Table levelTable = new Table(App.skin);
      levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      levelTable.add(App.image("psd/level"));
      final ButtonGroup buttonGroup = new ButtonGroup();

      for (int i = 0; i <= 7; i++) {
         TextButton button = ((TextButtonBuilder)App.textButton(new StringBuilder(String.valueOf(i)).toString())
               .checkedFontColor(Color.WHITE)
               .checked("selected"))
            .create();
         if (i == normal) {
            button.getStyle().up = App.drawable("selected", App.disabledGray);
         }

         if (i == App.state.scenarioLevel || App.state.scenarioLevel == -1 && i == normal) {
            button.setChecked(true);
         }

         buttonGroup.add(button);
         levelTable.add(button).left();
         if (i == 3) {
            levelTable.row();
         }
      }

      final CheckBox soloCheckbox = menu.checkBoxItem(Text.solo, App.state.solo, null);
      soloCheckbox.padRight(0.0F);
      levelTable.add(soloCheckbox).size(Value.prefWidth, Value.prefHeight).colspan(2);
      menu.table.add(levelTable).row();
      menu.addSeperator().row();
      final Label numberLabel = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
      numberLabel.setAlignment(1);
      menu.table.add(new Container(numberLabel)).row();
      ChangeListener listener = new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String digit = ((TextButton)actor).getText().toString();
            String number = numberLabel.getText().replace("_", "") + digit;
            if (number.length() > 3) {
               number = number.substring(1);
            }

            numberLabel.setText(number + "_");
         }
      };
      Table numberTable = new Table(App.skin);
      numberTable.pad(-6.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      numberTable.columnDefaults(0).right();
      numberTable.columnDefaults(2).left();

      for (int j = 1; j <= 9; j++) {
         numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder(String.valueOf(j)).toString()).change(listener)).create());
         if (j % 3 == 0) {
            numberTable.row();
         }
      }

      menu.table.add(numberTable).row();
      Table editionTable = new Table(App.skin);
      editionTable.defaults().growX();
      ButtonGroup editionGroup = new ButtonGroup();

      for (final String edition : App.editions) {
         CheckBox editionCheckBox = menu.radioItem(edition, App.state.edition.equals(edition), new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.state.edition = edition;
            }
         });
         editionGroup.add(editionCheckBox);
         editionTable.add(editionCheckBox);
         editionTable.row();
         editionTable.defaults().growX();
      }

      final CheckBox addMonstersCheckbox = this.checkBoxItem(Text.addMonstersCheckbox, true, null);
      TextButton ok = (TextButton)menu.addTextItem(Text.ok, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String text = numberLabel.getText().toString().replace("_", "");
            if (text.length() != 0) {
               int scenarioNumber = App.parseInt(text);
               List scenarios = (List<Scenario>)App.allScenarios.get(App.state.edition);
               int startingNumber = 1;

               for (int i = 0; i < App.editions.size(); i++) {
                  if (((String)App.editions.get(i)).equals(App.state.edition)) {
                     startingNumber = (Integer)App.scenarioStartingNumbers.get(i);
                     break;
                  }
               }

               if (scenarioNumber >= 0 && scenarioNumber < scenarios.size() + startingNumber) {
                  TextButton button = (TextButton)buttonGroup.getChecked();
                  String taxt = button.getText().toString();
                  int level = App.parseInt(taxt);
                  App.gloom.setScenario(scenarioNumber, level, soloCheckbox.isChecked(), addMonstersCheckbox.isChecked(), App.state.edition);
                  menu.hide();
                  App.state.changed();
               }
            }
         }
      }).getActor();
      menu.table.row();
      ok.pad(0.0F, 0.0F, 0.0F, 14.0F);
      ok.getLabel().setAlignment(16);
      TextButton custom = (TextButton)menu.addTextItem(Text.custom, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            TextButton button = (TextButton)buttonGroup.getChecked();
            String text = button.getText().toString();
            int level = App.parseInt(text);
            App.gloom.setScenario(0, level, soloCheckbox.isChecked(), false, "Gloomhaven");
            menu.hide();
            App.state.changed();
         }
      }).getActor();
      menu.table.row();
      custom.padLeft(35.0F).padRight(-18.0F);
      numberTable.add(custom).expandX().left().uniformX().height(100.0F);
      numberTable.add(((TextButtonBuilder)App.textButton("0").change(listener)).create());
      numberTable.add(ok).expandX().right().uniformX().size(100.0F);
      menu.table.add(addMonstersCheckbox);
      menu.table.row();
      Table lowerThanBottom = new Table();
      lowerThanBottom.add(editionTable);
      menu.table.add(lowerThanBottom).center().fill(false);
      menu.animate = animate;
      menu.show(this);
      menu.animate = true;
   }

   public int calculateNormalLevel() {
      int normal = 0;

      for (PlayerRow row : App.gloom.playerRows) {
         if (!row.player.characterClass.nonPlayer) {
            normal += row.player.level;
         }
      }

      if (App.state.soloFH) {
         normal = (int)Math.ceil(((float)normal / App.gloom.playerCount() + 1.0F) / 2.0F);
      } else {
         normal = (int)Math.ceil((float)normal / App.gloom.playerCount() / 2.0F);
      }

      return normal;
   }

   public TextMenu setScenarioLevel() {
      final TextMenu menu = new TextMenu();
      int normal = this.calculateNormalLevel();
      Table levelTable = new Table(App.skin);
      levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      levelTable.add(App.image("psd/level"));
      final ButtonGroup buttonGroup = new ButtonGroup();

      for (int i = 0; i <= 7; i++) {
         TextButton button = App.textButton(new StringBuilder(String.valueOf(i)).toString()).checkedFontColor(Color.WHITE).create();
         if (i == normal) {
            button.getStyle().up = App.drawable("selected", App.disabledGray);
         }

         if (i == App.state.scenarioLevel || App.state.scenarioLevel == -1 && i == normal) {
            button.setChecked(true);
         }

         buttonGroup.add(button);
         levelTable.add(button).left();
         if (i == 3) {
            levelTable.row();
         }
      }

      final CheckBox soloCheckbox = menu.checkBoxItem(Text.solo, App.state.solo, null);
      soloCheckbox.setChecked(App.state.solo);
      soloCheckbox.padRight(0.0F);
      soloCheckbox.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            TextButton buttonx = (TextButton)buttonGroup.getChecked();
            String text = buttonx.getText().toString();
            int level = App.parseInt(text);
            App.gloom.setScenarioLevel(level, soloCheckbox.isChecked(), App.state.edition);
            App.state.changed();
         }
      });
      final CheckBox soloFHCheckbox = menu.checkBoxItem(Text.soloFH, App.state.soloFH, null);
      soloFHCheckbox.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            soloFHCheckbox.setChecked(!App.state.soloFH);
            App.state.soloFH = !App.state.soloFH;
            App.state.changed();
            int newNormal = MainMenu.this.calculateNormalLevel();

            for (TextButton button : buttonGroup.getButtons()) {
               button.getStyle().up = null;
            }

            TextButton button = (TextButton)buttonGroup.getButtons().get(newNormal);
            button.getStyle().up = App.drawable("selected", App.disabledGray);
         }
      });
      soloFHCheckbox.setChecked(App.state.soloFH);
      soloFHCheckbox.padRight(30.0F);
      soloFHCheckbox.padLeft(130.0F);
      menu.table.add(levelTable).row();
      Table checkButtonsTable = new Table(App.skin);
      checkButtonsTable.add(soloCheckbox).padLeft(10.0F);
      checkButtonsTable.add(soloFHCheckbox);
      menu.table.add(checkButtonsTable);
      levelTable.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            TextButton button = (TextButton)buttonGroup.getChecked();
            String text = button.getText().toString();
            int level = App.parseInt(text);
            App.gloom.setScenarioLevel(level, soloCheckbox.isChecked(), App.state.edition);
            menu.hide();
            App.state.changed();
         }
      });
      return menu;
   }

   public void addSection(boolean animate) {
      this.animate = false;
      this.hide();
      this.animate = true;
      final TextMenu menu = new TextMenu();
      final Label numberLabel = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
      numberLabel.setAlignment(1);
      menu.table.add(new Container(numberLabel)).row();
      ChangeListener listener = new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String digit = ((TextButton)actor).getText().toString();
            String number = numberLabel.getText().replace("_", "") + digit;
            if (number.length() > 3) {
               number = number.substring(1);
            }

            numberLabel.setText(number + "_");
         }
      };
      Table numberTable = new Table(App.skin);
      numberTable.pad(-6.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      numberTable.columnDefaults(0).right();
      numberTable.columnDefaults(2).left();

      for (int i = 1; i <= 9; i++) {
         numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder(String.valueOf(i)).toString()).change(listener)).create());
         if (i % 3 == 0) {
            numberTable.row();
         }
      }

      numberTable.add().expandX().left().uniformX().height(100.0F);
      numberTable.add(((TextButtonBuilder)App.textButton("0").change(listener)).create()).row();
      numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder("A").toString()).change(listener)).create());
      numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder("B").toString()).change(listener)).create()).row();
      numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder("C").toString()).change(listener)).create());
      numberTable.add(((TextButtonBuilder)App.textButton(new StringBuilder("D").toString()).change(listener)).create());
      numberTable.row();
      menu.table.add(numberTable).row();
      TextButton ok = (TextButton)menu.addTextItem(
            Text.ok,
            new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  String text = numberLabel.getText().toString().replace("_", "");
                  if (text.length() != 0) {
                     String appendix = "";
                     if (text.charAt(text.length() - 1) == 'A'
                        || text.charAt(text.length() - 1) == 'B'
                        || text.charAt(text.length() - 1) == 'C'
                        || text.charAt(text.length() - 1) == 'D') {
                        appendix = appendix + text.charAt(text.length() - 1);
                        text = text.substring(0, text.length() - 1);
                     }

                     String name = "#" + App.parseInt(text) + appendix + " ";
                     String nameWithScenario = "#" + App.parseInt(text) + appendix + "-" + App.state.scenarioNumber + " ";

                     for (Scenario section : (List)App.allSections.get(App.state.edition)) {
                        if (section.name.startsWith(name) || section.name.startsWith(nameWithScenario)) {
                           App.gloom.addSection(section);
                           menu.hide();
                           App.state.changed();
                           App.toast("Section added:");
                           App.toast(section.name);
                        }
                     }
                  }
               }
            }
         )
         .getActor();
      menu.table.row();
      ok.pad(0.0F, 0.0F, 0.0F, 14.0F);
      ok.getLabel().setAlignment(16);
      numberTable.add().expandX().left().uniformX().height(100.0F);
      numberTable.add(ok).expandX().right().uniformX().size(100.0F);
      menu.animate = animate;
      menu.show(this);
      menu.animate = true;
   }

   void addMonsters(boolean animate) {
      this.animate = false;
      this.hide();
      this.animate = true;
      TextMenu menu = new TextMenu();
      int normal = 0;

      for (PlayerRow row : App.gloom.playerRows) {
         normal += row.player.level;
      }

      if (App.state.soloFH) {
         normal = (int)Math.ceil(((float)normal / App.gloom.playerCount() + 1.0F) / 2.0F);
      } else {
         normal = (int)Math.ceil((float)normal / App.gloom.playerCount() / 2.0F);
      }

      Table levelTable = new Table(App.skin);
      levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      levelTable.add(App.image("psd/level"));
      ButtonGroup buttonGroup = new ButtonGroup();

      for (int i = 0; i <= 7; i++) {
         TextButton button = ((TextButtonBuilder)App.textButton(new StringBuilder(String.valueOf(i)).toString())
               .checkedFontColor(Color.WHITE)
               .checked("selected"))
            .create();
         if (App.state.scenarioNumber >= 0) {
            if (i == App.state.scenarioLevel) {
               button.setChecked(true);
               button.getStyle().up = App.drawable("selected", App.disabledGray);
            }
         } else if (i == normal) {
            button.setChecked(true);
         }

         buttonGroup.add(button);
         levelTable.add(button);
         if (i == 3) {
            levelTable.row();
         }
      }

      menu.table.add(levelTable).row();
      menu.addSeperator().row();
      Table left = new Table();
      Table right = new Table();
      boolean addToleft = true;

      for (String edition : App.editions) {
         if (addToleft) {
            left.add(this.monsterTable(edition, edition, buttonGroup));
            left.row().padBottom(40.0F);
         } else {
            right.defaults().growX();
            right.add(this.monsterTable(edition, edition, buttonGroup));
            right.row().padBottom(40.0F);
         }

         addToleft = left.getMinHeight() < right.getMinHeight();
      }

      Table monsters = new Table().padTop(20.0F);
      monsters.defaults().top().space(20.0F).growX();
      monsters.add(left);
      monsters.add(right);
      menu.table.add(monsters).row();
      menu.animate = animate;
      menu.show(this);
      menu.animate = true;
   }

   Table monsterTable(String title, String edition, final ButtonGroup buttonGroup) {
      Table table = new Table();
      table.add(new Label(title, App.skin, "plainLargeOutline", Color.WHITE)).padLeft(20.0F).left().row();
      table.defaults().growX();
      boolean bosses = false;
      Array datas = new Array(App.monsterDatas);
      datas.sort(new Comparator() {
         public int compare(MonsterData o1, MonsterData o2) {
            int diff = o1.edition.compareTo(o2.edition);
            if (diff != 0) {
               return diff;
            } else {
               diff = Boolean.compare(o1.isBoss(), o2.isBoss());
               if (diff != 0) {
                  return diff;
               } else {
                  return o1.isBoss() ? 0 : o1.name.compareTo(o2.name);
               }
            }
         }
      });
      boolean editionHasMonsters = false;

      for (final MonsterData data : datas) {
         if (data != App.summonData && !data.hidden && data.edition.equals(edition)) {
            editionHasMonsters = true;
            if (!bosses && data.isBoss()) {
               table.add(seperator()).row();
               bosses = true;
            }

            TextButton item = (TextButton)table.add(this.textItem(data.name, new ChangeListener() {
               @Override
               public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                  TextButton button = (TextButton)buttonGroup.getChecked();
                  String text = button.getText().toString();
                  int level = App.parseInt(text);
                  if (App.state.solo) {
                     level++;
                  }

                  MonsterRow row = new MonsterRow(data, level);
                  App.gloom.addMonsterRow(row);
                  if (!App.state.canDraw && !App.state.trackStandees) {
                     row.showAbility();
                  }

                  if (!App.state.canDraw) {
                     App.gloom.sortByInitiative(0.0F);
                  }

                  App.state.changed();
                  ((TextButton)actor).setDisabled(true);
               }
            })).getActor();
            table.row();

            for (MonsterRow row : App.gloom.monsterRows) {
               if (row.data == data) {
                  item.setDisabled(true);
               }
            }
         }
      }

      return !editionHasMonsters ? new Table() : table;
   }

   void removeMonsterMenu() {
      this.animate = false;
      this.hide();
      this.animate = true;
      final TextMenu menu = new TextMenu();
      menu.addTextItem("<" + Text.removeAll + ">", new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            for (MonsterRow row : App.gloom.monsterRows) {
               row.remove();
            }

            App.gloom.monsterRows.clear();
            App.state.changed();
            menu.hide();
         }
      }).row();

      for (final MonsterRow row : App.gloom.monsterRows) {
         menu.addTextItem(row.data.name, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               row.remove();
               App.gloom.monsterRows.removeValue(row, true);
               App.state.changed();
               actor.remove();
               if (App.gloom.monsterRows.size == 0) {
                  menu.hide();
               } else {
                  menu.pack();
               }
            }
         }).row();
      }

      menu.animate = false;
      menu.show(this);
      menu.animate = true;
   }

   public void addCharacters(boolean animate) {
      this.animate = false;
      this.hide();
      this.animate = true;
      TextMenu menu = new TextMenu();
      Table levelTable = new Table(App.skin);
      levelTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      levelTable.add(App.image("psd/level"));
      ButtonGroup buttonGroup = new ButtonGroup();

      for (int i = 1; i <= 9; i++) {
         TextButton button = ((TextButtonBuilder)App.textButton(new StringBuilder(String.valueOf(i)).toString())
               .checkedFontColor(Color.WHITE)
               .checked("selected"))
            .create();
         if (i == 1) {
            button.setChecked(true);
         }

         buttonGroup.add(button);
         levelTable.add(button);
         if (i == 4) {
            levelTable.row();
         }
      }

      menu.table.add(levelTable).row();
      this.updateAddPlayerMenu(menu, buttonGroup, new ArrayList());
      menu.animate = animate;
      menu.show(this);
      menu.animate = true;
   }

   void updateAddPlayerMenu(final TextMenu menu, final ButtonGroup buttonGroup, final List showNamesForTheseEditions) {
      Actor levelTable = (Actor)menu.table.getChildren().first();
      menu.table.clearChildren();
      menu.table.add(levelTable).row();
      menu.addSeperator().row();
      Table left = new Table();
      left.padRight(60.0F);
      left.align(8);
      Table right = new Table();
      right.align(8);
      Table currentTable = left;
      String lastEdition = "na";
      boolean lastHidden = false;
      int classIndex = 0;

      for (final CharacterClass characterClass : App.allClasses) {
         if (!characterClass.asString().equals("Bladeswarm")) {
            currentTable.defaults().growX();
            if (!lastEdition.equals(characterClass.edition)) {
               currentTable.add(seperator()).row();
               if (classIndex >= App.allClasses.size() * 0.4) {
                  currentTable = right;
                  right.defaults().growX();
               }

               currentTable.add(new Label(characterClass.edition, App.skin, "plainLargeOutline", Color.WHITE)).padLeft(20.0F).left().row();
               lastEdition = characterClass.edition;
               lastHidden = false;
            }

            if (!lastHidden == characterClass.hidden) {
               lastHidden = characterClass.hidden;
               if (characterClass.hidden) {
                  currentTable.add(seperator()).row();
                  int index = -1;

                  for (int i = 0; i < showNamesForTheseEditions.size(); i++) {
                     if (((String)showNamesForTheseEditions.get(i)).equals(lastEdition)) {
                        index = i;
                     }
                  }

                  final boolean showNames = index != -1;
                  final int finalIndex = index;
                  final String finalLastEdition = lastEdition;
                  currentTable.add(((TextButtonBuilder)App.textButton(showNames ? Text.hideNames : Text.showNames).change(new ChangeListener() {
                     @Override
                     public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        if (showNames) {
                           showNamesForTheseEditions.remove(finalIndex);
                        } else {
                           showNamesForTheseEditions.add(finalLastEdition);
                        }

                        MainMenu.this.updateAddPlayerMenu(menu, buttonGroup, showNamesForTheseEditions);
                     }
                  })).create()).row();
                  currentTable.add(seperator()).row();
               }
            }

            boolean unlocked = false;

            for (String edition : showNamesForTheseEditions) {
               if (edition.equals(lastEdition)) {
                  unlocked = true;
               }
            }

            boolean showName = !characterClass.hidden || unlocked;
            ImageTextButton item = (ImageTextButton)TextMenu.addImageTextItem(
                  currentTable, showName ? characterClass.name : "???", "class-icons/" + characterClass.asString(), new ChangeListener() {
                     @Override
                     public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                        if (!(actor instanceof TextButton)) {
                           TextButton button = (TextButton)buttonGroup.getChecked();
                           String text = button.getText().toString();
                           int level = App.parseInt(text);
                           MainMenu.this.addPlayer(level, characterClass);
                           if (!characterClass.nonPlayer) {
                              ((ImageTextButton)actor).setDisabled(true);
                           }
                        }
                     }
                  }
               )
               .getActor();
            currentTable.row();
            item.getImageCell().size(50.0F, 60.0F);
            item.align(8).padLeft(15.0F);
            item.getImage().setScaling(Scaling.none);
            item.getStyle().overFontColor = characterClass.color;

            for (PlayerRow row : App.gloom.playerRows) {
               if (row.player.characterClass == characterClass && !characterClass.nonPlayer) {
                  item.setDisabled(true);
               }
            }

            classIndex++;
         }
      }

      Table characters = new Table();
      characters.defaults().top().growX();
      left.defaults().grow();
      right.defaults().grow();
      characters.add(left);
      characters.add(right);
      menu.table.add(characters).row();
   }

   void addPlayer(int level, CharacterClass characterClass) {
      if (level > 9) {
         level = 9;
      }

      Player player = new Player();
      player.level = level;
      player.name = characterClass.name;
      player.characterClass = characterClass;
      int hpMax = characterClass.hpMax(level);
      player.hpMax = hpMax;
      player.hp = hpMax;
      if (player.characterClass.nonPlayer) {
         player.init(99);
      }

      PlayerRow row = new PlayerRow(player);
      App.gloom.playerRows.add(row);
      App.gloom.rows.addActorAt(0, row);
      if (characterClass.asString().equals("BeastTyrant")) {
         MonsterBox box = row.addMonsterBox(1, App.summonData, player.level, false, false, SummonColor.beast, false);
         Monster monster = box.monster;
         Monster monster2 = box.monster;
         int n = 8 + player.level * 2;
         monster2.hpMax = n;
         monster.hp = n;
         box.monster.summonMove = 3;
         box.monster.summonAttack = 2;
         box.monster.isNew = false;
      }

      App.state.changed();
   }

   void languageDialog() {
      final TextMenu menu = new TextMenu();
      menu.table
         .add(new Label("The app needs to be restarted after changing the language.", App.skin, "plainLargeOutline", Color.WHITE))
         .fill(false)
         .pad(20.0F)
         .colspan(4)
         .row();
      CheckBox englishItem = menu.radioItem("English", App.config.language.equals("en"), null);
      final CheckBox germanItem = menu.radioItem("German", App.config.language.equals("de"), null);
      final CheckBox russianItem = menu.radioItem("Russian", App.config.language.equals("ru"), null);
      final CheckBox polishItem = menu.radioItem("Polish", App.config.language.equals("pl"), null);
      final CheckBox czechItem = menu.radioItem("Czech", App.config.language.equals("cz"), null);
      final CheckBox koreanItem = menu.radioItem("Korean", App.config.language.equals("ko"), null);
      final CheckBox spanishItem = menu.radioItem("Spanish", App.config.language.equals("es"), null);
      final CheckBox hungarianItem = menu.radioItem("Hungarian", App.config.language.equals("hu"), null);
      final CheckBox portugueseItem = menu.radioItem("Portuguese", App.config.language.equals("pt"), null);
      final CheckBox japaneseItem = menu.radioItem("Japanese", App.config.language.equals("ja"), null);
      final CheckBox frenchItem = menu.radioItem("French", App.config.language.equals("fr"), null);
      final CheckBox italianItem = menu.radioItem("Italian", App.config.language.equals("it"), null);
      new ButtonGroup(
         englishItem,
         germanItem,
         russianItem,
         polishItem,
         czechItem,
         koreanItem,
         spanishItem,
         hungarianItem,
         portugueseItem,
         japaneseItem,
         frenchItem,
         italianItem
      );
      menu.table.add(englishItem);
      menu.table.add(czechItem);
      menu.table.add(hungarianItem);
      menu.table.add(frenchItem).row();
      menu.table.add(germanItem);
      menu.table.add(italianItem);
      menu.table.add(japaneseItem);
      menu.table.add(koreanItem).row();
      menu.table.add(polishItem);
      menu.table.add(portugueseItem);
      menu.table.add(russianItem);
      menu.table.add(spanishItem).row();
      TextButton ok = (TextButton)menu.addTextItem(Text.ok, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String language = "en";
            if (germanItem.isChecked()) {
               language = "de";
            }

            if (russianItem.isChecked()) {
               language = "ru";
            }

            if (polishItem.isChecked()) {
               language = "pl";
            }

            if (czechItem.isChecked()) {
               language = "cz";
            }

            if (koreanItem.isChecked()) {
               language = "ko";
            }

            if (spanishItem.isChecked()) {
               language = "es";
            }

            if (portugueseItem.isChecked()) {
               language = "pt";
            }

            if (hungarianItem.isChecked()) {
               language = "hu";
            }

            if (japaneseItem.isChecked()) {
               language = "ja";
            }

            if (frenchItem.isChecked()) {
               language = "fr";
            }

            if (italianItem.isChecked()) {
               language = "it";
            }

            if (!language.equals(App.config.language)) {
               App.config.language = language;
               App.game.saveConfig();
               App.game.close();
               if (Gdx.app.getType() == Application.ApplicationType.WebGL) {
                  App.toast("Please refresh the page.");
               }
            }

            menu.hide();
         }
      }).colspan(4).getActor();
      ok.getLabel().setAlignment(1);
      menu.animate = this.animate;
      menu.show(this);
      menu.animate = true;
   }

   void bladeswarmDialog(int level) {
      final Label textLabel = new Label("_", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
      textLabel.setAlignment(1);
      ChangeListener listener = new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String letter = ((TextButton)actor).getText().toString();
            textLabel.setText(textLabel.getText().toString().replace("_", "") + letter + "_");
         }
      };
      String letters = "DRXLMSBGEATW";
      Table letterTable = new Table(App.skin);
      letterTable.pad(5.0F, 12.0F, 0.0F, 12.0F).defaults().size(100.0F);
      new ButtonGroup();
      int i = 0;

      for (int n = "DRXLMSBGEATW".length(); i < n; i++) {
         TextButton button = App.textButton(new StringBuilder(String.valueOf("DRXLMSBGEATW".charAt(i))).toString()).create();
         button.addListener(listener);
         if (i % 4 == 0) {
            letterTable.row();
         }

         letterTable.add(button);
      }

      final TextMenu menu = new TextMenu();
      menu.table.add(new Label("Enter the secret:", App.skin, "plainLargeOutline", Color.WHITE)).fill(false).row();
      menu.table.add(letterTable).row();
      menu.addSeperator().row();
      menu.table.add(textLabel).row();
      TextButton ok = (TextButton)menu.addTextItem(Text.ok, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            String text = textLabel.getText().toString().replace("_", "");
            if (text.length() != 0) {
               if (text.equals("BLADESWARM")) {
                  for (CharacterClass charClass : App.allClasses) {
                     if (charClass.toString().equals("Bladeswarm")) {
                        MainMenu.this.addPlayer(1, charClass);
                        break;
                     }
                  }

                  menu.hide();
                  App.state.changed();
                  App.toast("Character added:");
                  App.toast("Bladeswarm");
               } else {
                  App.toast("Wrong!");
               }
            }
         }
      }).getActor();
      menu.table.row();
      ok.pad(0.0F, 0.0F, 0.0F, 14.0F);
      ok.getLabel().setAlignment(16);
      TextButton clear = (TextButton)menu.addTextItem(Text.clear, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            textLabel.setText("_");
         }
      }).getActor();
      menu.table.row();
      clear.padLeft(10.0F).padRight(-18.0F);
      letterTable.row();
      letterTable.add(clear).expandX().left().uniformX().height(100.0F).colspan(2);
      letterTable.add(ok).expandX().right().uniformX().size(100.0F).colspan(2);
      menu.animate = this.animate;
      menu.show(this);
      menu.animate = true;
   }

   void removePlayerMenu() {
      this.animate = false;
      this.hide();
      this.animate = true;
      final TextMenu menu = new TextMenu();
      menu.addTextItem("<" + Text.removeAll + ">", new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            for (PlayerRow row : App.gloom.playerRows) {
               row.remove();
            }

            App.gloom.playerRows.clear();
            App.state.changed();
            menu.hide();
         }
      }).row();

      for (final PlayerRow row : App.gloom.playerRows) {
         menu.addTextItem(row.toString(), new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               row.remove();
               App.gloom.playerRows.removeValue(row, true);
               App.state.changed();
               actor.remove();
               if (App.gloom.playerRows.size == 0) {
                  menu.hide();
               } else {
                  menu.pack();
               }
            }
         }).row();
      }

      menu.animate = false;
      menu.show(this);
      menu.animate = true;
   }

   public void settingsMenu(Menu oldMenu) {
      oldMenu.animate = false;
      oldMenu.hide();
      oldMenu.animate = true;
      final TextField hostText = App.textField(App.config.clientHost);
      final TextMenu menu = new TextMenu() {
         @Override
         public void draw(Batch batch, float parentAlpha) {
            hostText.setText(App.config.clientHost);
            super.draw(batch, parentAlpha);
         }

         @Override
         public boolean hide() {
            MainMenu.this.clientCheckbox = null;
            boolean hidden = super.hide();
            if (hidden) {
               App.gloom.updateHelpText();
               Gdx.input.setOnscreenKeyboardVisible(false);
               App.game.saveConfig();
            }

            return hidden;
         }
      };
      CheckBox helpItem = (CheckBox)menu.addCheckBoxItem("Help", App.config.help, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.help = !App.config.help;
            App.game.saveConfig();
         }
      }).getActor();
      helpItem.addListener(App.tooltip("When unchecked, the helpful messages won't be shown."));
      CheckBox randomStandees = (CheckBox)menu.addCheckBoxItem("Random standees", App.state.randomStandees, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.randomStandees = !App.state.randomStandees;
            App.state.changed();
         }
      }).getActor();
      randomStandees.addListener(App.tooltip("When checked, adding monsters automatically and randomly chooses the standee number."));
      menu.table.row();
      CheckBox hideStatsItem = (CheckBox)menu.addCheckBoxItem("Hide stats", App.state.hideStats, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.hideStats = !App.state.hideStats;
            App.state.changed();
         }
      }).getActor();
      hideStatsItem.addListener(App.tooltip("When checked, stats for monsters that have not been spawned are hidden. Press and hold stats to show."));
      CheckBox expireConditionsItem = (CheckBox)menu.addCheckBoxItem("Expire conditions", App.state.expireConditions, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.expireConditions = !App.state.expireConditions;
            App.state.changed();
         }
      }).getActor();
      expireConditionsItem.addListener(
         App.tooltip(
            "When checked, conditions that expire at the end of the turn will be removed automatically. When using this, it is important to click each row's portrait after taking each turn so that end-of-turn conditions can be managed properly."
         )
      );
      menu.table.row();
      CheckBox calculateStatsItem = (CheckBox)menu.addCheckBoxItem("Calculate stats", App.state.calculateStats, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.calculateStats = !App.state.calculateStats;
            App.state.changed();
         }
      }).getActor();
      calculateStatsItem.addListener(App.tooltip("When checked, the monster ability cards show calculated values (eg, Attack 4 instead of Attack +1)."));
      CheckBox abilityCardsItem = (CheckBox)menu.addCheckBoxItem("Ability cards", App.state.abilityCards, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.abilityCards = !App.state.abilityCards;
            App.state.changed();
            App.gloom.rows.invalidateHierarchy();
         }
      }).getActor();
      abilityCardsItem.addListener(
         App.tooltip(
            "When unchecked, ability cards for monsters are not shown. Use this when you want to track monster HP and conditions, but not ability cards."
         )
      );
      menu.table.row();
      CheckBox elitesFirstItem = (CheckBox)menu.addCheckBoxItem("Elites first", App.state.elitesFirst, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.elitesFirst = !App.state.elitesFirst;
            App.state.changed();

            for (MonsterRow row : App.gloom.monsterRows) {
               row.boxes.sort();
               row.monstersGroup.getChildren().sort();
               row.monstersGroup.invalidate();
            }
         }
      }).getActor();
      elitesFirstItem.addListener(
         App.tooltip("When checked, elite monsters are sorted first for each monster row. When unchecked, they are sorted only by standee number.")
      );
      CheckBox abilityNumbersItem = (CheckBox)menu.addCheckBoxItem("Ability numbers", App.config.abilityNumbers, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.abilityNumbers = !App.config.abilityNumbers;
            App.game.saveConfig();
         }
      }).getActor();
      abilityNumbersItem.addListener(App.tooltip("When checked, monster ability cards will show the card number."));
      menu.table.row();
      CheckBox hpDragItem = (CheckBox)menu.addCheckBoxItem("Drag HP", App.config.hpDrag, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.hpDrag = !App.config.hpDrag;
            App.game.saveConfig();
         }
      }).getActor();
      hpDragItem.addListener(App.tooltip("When checked, monster and character rows can be dragged left/right to adjust HP."));
      CheckBox abilityTitleItem = (CheckBox)menu.addCheckBoxItem("Ability titles", App.config.showTitle, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.showTitle = !App.config.showTitle;
            float height = 209.0F;
            if (App.config.showTitle) {
               height = 255.0F;
            }

            App.gloom.rows.layout();

            for (Actor row : App.gloom.monsterRows) {
               row.setHeight(height);
               row.remove();
            }

            App.gloom.rows.invalidateHierarchy();
            App.gloom.rows.sizeChanged();
            App.game.saveConfig();
         }
      }).getActor();
      abilityTitleItem.addListener(App.tooltip("When checked, monster ability cards will show the card title."));
      menu.table.row();
      CheckBox scrollItem = (CheckBox)menu.addCheckBoxItem("Auto scroll", App.config.autoScroll, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.autoScroll = !App.config.autoScroll;
            App.game.saveConfig();
         }
      }).getActor();
      scrollItem.addListener(App.tooltip("When checked, the app will automatically scroll to the active row."));
      CheckBox trackStandeesItem = (CheckBox)menu.addCheckBoxItem("Track standees", App.state.trackStandees, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.trackStandees = !App.state.trackStandees;
            App.state.changed();
            App.gloom.rows.invalidateHierarchy();
         }
      }).getActor();
      trackStandeesItem.addListener(
         App.tooltip(
            "When unchecked, the individual monsters for each row are not shown. Use this when you want to track monster HP and conditions some other way."
         )
      );
      menu.table.row();
      CheckBox hideMonstersItem = (CheckBox)menu.addCheckBoxItem("Hide monsters", App.config.hideMonsters, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.config.hideMonsters = !App.config.hideMonsters;
            App.game.saveConfig();

            for (MonsterRow row : App.gloom.monsterRows) {
               row.invalidate();
            }

            App.gloom.rows.invalidateHierarchy();
            App.root.validate();
         }
      }).getActor();
      hideMonstersItem.addListener(App.tooltip("When checked, monster rows are not shown."));
      menu.table.row();
      menu.addSeperator().colspan(2).row();
      final CheckBox initRequiredItem = menu.checkBoxItem(
         "Required", App.state.playerInit == PlayerInit.dragNumberRequired || App.state.playerInit == PlayerInit.numpad, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.state.playerInit = ((CheckBox)actor).isChecked() ? PlayerInit.dragNumberRequired : PlayerInit.dragNumber;
               App.state.changed();
            }
         }
      );
      initRequiredItem.setDisabled(App.state.playerInit == PlayerInit.dragOrder || App.state.playerInit == PlayerInit.numpad);
      initRequiredItem.addListener(App.tooltip("When checked, character initiative numbers must be set before monster ability cards can be drawn."));
      CheckBox initDragItem = menu.radioItem("Drag order", App.state.playerInit == PlayerInit.dragOrder, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.playerInit = PlayerInit.dragOrder;
            initRequiredItem.setChecked(false);
            initRequiredItem.setDisabled(true);

            for (PlayerRow row : App.gloom.playerRows) {
               if (row.player.characterClass.nonPlayer) {
                  row.player.init(99);
               } else {
                  row.player.init(0);
               }
            }

            App.state.changed();
         }
      });
      initDragItem.addListener(App.tooltip("When checked, drag character portraits to set the intiative order."));
      CheckBox initDragNumbersItem = menu.radioItem(
         "Drag number", App.state.playerInit == PlayerInit.dragNumber || App.state.playerInit == PlayerInit.dragNumberRequired, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.state.playerInit = initRequiredItem.isChecked() ? PlayerInit.dragNumberRequired : PlayerInit.dragNumber;
               initRequiredItem.setDisabled(false);

               for (PlayerRow row : App.gloom.playerRows) {
                  if (row.player.characterClass.nonPlayer) {
                     row.player.init(99);
                  } else {
                     row.player.init(0);
                  }
               }

               App.state.changed();
            }
         }
      );
      initDragNumbersItem.addListener(App.tooltip("When checked, drag character portraits to the right to set the intiative number."));
      CheckBox initEnterNumbersItem = menu.radioItem("Numpad", App.state.playerInit == PlayerInit.numpad, new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            App.state.playerInit = PlayerInit.numpad;
            initRequiredItem.setChecked(true);
            initRequiredItem.setDisabled(true);

            for (PlayerRow row : App.gloom.playerRows) {
               if (row.player.characterClass.nonPlayer) {
                  row.player.init(99);
               } else {
                  row.player.init(0);
               }
            }

            App.state.changed();
         }
      });
      initEnterNumbersItem.addListener(App.tooltip("When checked, click character portraits to enter the intiative number."));
      new ButtonGroup(initDragItem, initDragNumbersItem, initEnterNumbersItem);
      Table init = new Table();
      init.defaults().growX();
      init.add(initEnterNumbersItem);
      init.add(initDragNumbersItem);
      init.add(initDragItem);
      menu.table.add(new Label("Character Initiative", App.skin, "plainLargeOutline", App.buttonGray)).space(12.0F).left().top().pad(9.0F, 20.0F, 0.0F, 0.0F);
      menu.table.add(initRequiredItem).right().row();
      menu.table.add(init).colspan(2);
      menu.table.row();
      menu.addSeperator().colspan(2).row();
      VerticalGroup left = new VerticalGroup().left().grow();
      VerticalGroup right = new VerticalGroup().left().grow();
      menu.table.add(left).top().spaceRight(20.0F);
      menu.table.add(right).top();
      if (Gdx.app.getType() != Application.ApplicationType.WebGL) {
         final CheckBox serverCheckbox = menu.checkBoxItem("Server", App.config.server, null);
         serverCheckbox.addListener(
            App.tooltip(
               "When checked, a server runs on the port shown to accept connections from other devices. Any changes made on any of the devices will appear on all the others."
            )
         );
         (this.clientCheckbox = menu.checkBoxItem("Client", App.config.client, new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               if (MainMenu.this.clientCheckbox != null) {
                  serverCheckbox.setChecked(false);
                  App.config.client = MainMenu.this.clientCheckbox.isChecked();
                  App.config.server = false;
                  App.game.saveConfig();
                  MainMenu.this.settingsMenu(menu);
               }
            }
         })).addListener(App.tooltip("When checked, the app connects to a server using the IP or host name and port shown."));
         left.addActor(this.clientCheckbox);
         hostText.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
               if (!textField.getText().equals(App.config.clientHost)) {
                  String host = textField.getText();
                  App.config.clientHost = host;
                  if (App.config.client) {
                     MainMenu.this.saveTask.cancel();
                     Timer.schedule(MainMenu.this.saveTask, 0.5F);
                  } else {
                     App.game.saveConfig();
                  }
               }
            }
         });
         TextField port = App.textField(new StringBuilder(String.valueOf(App.config.clientPort)).toString());
         port.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
               try {
                  int port = App.parseInt(textField.getText());
                  if (App.config.clientPort == port) {
                     return;
                  }

                  if (port <= 0) {
                     return;
                  }

                  App.config.clientPort = port;
                  if (App.config.client) {
                     MainMenu.this.saveTask.cancel();
                     Timer.schedule(MainMenu.this.saveTask, 0.5F);
                  } else {
                     App.game.saveConfig();
                  }
               } catch (NumberFormatException var4) {
               }
            }
         });
         Table table = new Table().padBottom(8.0F).left();
         table.add(new Label("Host:", App.skin, "plainLargeOutline", App.buttonGray)).padLeft(65.0F).space(12.0F);
         table.add(hostText).width(225.0F).padRight(9.0F).space(12.0F).row();
         table.add(new Label("Port:", App.skin, "plainLargeOutline", App.buttonGray)).padLeft(63.0F);
         table.add(port).width(104.0F).padRight(18.0F).left();
         left.addActor(table);
         serverCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               if (MainMenu.this.clientCheckbox != null) {
                  MainMenu.this.clientCheckbox.setChecked(false);
               }

               App.config.server = !App.config.server;
               App.config.client = false;
               App.game.saveConfig();
               MainMenu.this.settingsMenu(menu);
            }
         });
         left.addActor(serverCheckbox);
         port = App.textField(new StringBuilder(String.valueOf(App.config.serverPort)).toString());
         port.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
               try {
                  int port = App.parseInt(textField.getText());
                  if (App.config.serverPort == port) {
                     return;
                  }

                  if (port <= 0) {
                     return;
                  }

                  App.config.serverPort = port;
                  if (App.config.server) {
                     MainMenu.this.saveTask.cancel();
                     Timer.schedule(MainMenu.this.saveTask, 0.5F);
                  } else {
                     App.game.saveConfig();
                  }
               } catch (NumberFormatException var4) {
               }
            }
         });
         table = new Table().padBottom(8.0F).left();
         table.columnDefaults(0).padLeft(63.0F).right().space(12.0F);
         table.columnDefaults(1).padRight(18.0F).expandX().left().space(12.0F);
         String ip = Network.getIPs();
         if (ip.length() > 0) {
            table.row().padTop(-8.0F);
            table.add(new Label("Local IPs:", App.skin, "plainLargeOutline", App.buttonGray)).top();
            table.add(new Label(ip, App.skin, "plainLargeOutline", App.buttonGray)).row();
         }

         table.add(new Label("Port:", App.skin, "plainLargeOutline", App.buttonGray));
         table.add(port).width(104.0F);
         left.addActor(table);
      }

      TextButton zoomOutItem = menu.textItem("Zoom Out", null);
      zoomOutItem.addListener(new ClickListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            App.gloom.zoomOut = true;
            super.touchDown(event, x, y, pointer, button);
            return true;
         }

         @Override
         public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            App.gloom.zoomOut = false;
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            App.stage.addAction(Actions.repeat(13, new Action() {
               @Override
               public boolean act(float delta) {
                  App.gloom.zoomOut();
                  return true;
               }
            }));
         }
      });
      right.addActor(zoomOutItem);
      zoomOutItem.addListener(
         App.tooltip(
            "Scales the app smaller to provide more vertical space and reduce or remove the need for scrolling. Press until the desired zoom is reached."
         )
      );
      TextButton zoomInItem = menu.textItem("Zoom In", null);
      zoomInItem.addListener(new ClickListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            App.gloom.zoomIn = true;
            super.touchDown(event, x, y, pointer, button);
            return true;
         }

         @Override
         public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            super.touchUp(event, x, y, pointer, button);
            App.gloom.zoomIn = false;
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            App.stage.addAction(Actions.repeat(13, new Action() {
               @Override
               public boolean act(float delta) {
                  App.gloom.zoomIn();
                  return true;
               }
            }));
         }
      });
      right.addActor(zoomInItem);
      zoomInItem.addListener(App.tooltip("Scales the app larger to use up more screen space. Press until the desired zoom is reached."));
      TextButton resetZoomItem = menu.textItem("Reset Zoom", new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor button) {
            App.viewport.setMinWorldHeight(Math.max(Gdx.graphics.getHeight(), 800));
            App.gloom.updateViewport();
            App.gloom.updateTextures();
            App.config.zoom = 0.0F;
            App.config.zoomReset = true;
            App.game.saveConfig();
         }
      });
      right.addActor(resetZoomItem);
      resetZoomItem.addListener(App.tooltip("Resets the zoom to the default."));
      if (Gdx.app.getType() == Application.ApplicationType.Desktop) {
         right.addActor(TextMenu.seperator());
         right.addActor(menu.textItem(Gdx.graphics.isFullscreen() ? "Windowed" : "Fullscreen", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               if (Gdx.graphics.isFullscreen()) {
                  Gdx.graphics.setWindowedMode(MainMenu.this.lastScreenWidth, MainMenu.this.lastScreenHeight);
                  App.config.fullscreen = false;
               } else {
                  MainMenu.this.lastScreenWidth = Gdx.graphics.getWidth();
                  MainMenu.this.lastScreenHeight = Gdx.graphics.getHeight();
                  Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                  Gdx.graphics.setVSync(true);
                  App.config.fullscreen = true;
               }

               App.game.saveConfig();
               MainMenu.this.settingsMenu(menu);
            }
         }));
      }

      TextButton languageItem = menu.textItem("Language", new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor button) {
            menu.animate = false;
            menu.hide();
            MainMenu.this.animate = false;
            MainMenu.this.languageDialog();
            MainMenu.this.animate = true;
         }
      });
      right.addActor(seperator());
      right.addActor(languageItem);
      TextButton envelopexItem = menu.textItem("Envelope X", new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor button) {
            menu.animate = false;
            menu.hide();
            MainMenu.this.animate = false;
            MainMenu.this.bladeswarmDialog(App.state.scenarioLevel);
            MainMenu.this.animate = true;
         }
      });
      right.addActor(seperator());
      right.addActor(envelopexItem);
      envelopexItem.setDisabled(App.gloom.hasCharacterClass("Bladeswarm"));
      if (Gdx.app.getType() == Application.ApplicationType.Android) {
         TextButton item = menu.textItem("Privacy policy", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.game.openURL("http://esotericsoftware.com/files/ghh/privacy.html");
            }
         });
         TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(item.getStyle());
         style.font = App.plainSmall;
         item.setStyle(style);
         right.addActor(new Container(item).bottom().right().padBottom(-15.0F));
      } else if (Gdx.app.getType() == Application.ApplicationType.iOS) {
         TextButton item = menu.textItem("Privacy policy", new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.game.openURL("https://www.badlogicgames.com/gloomhavenhelper/privacypolicy.html");
            }
         });
         TextButton.TextButtonStyle style = new TextButton.TextButtonStyle(item.getStyle());
         style.font = App.plainSmall;
         item.setStyle(style);
         right.addActor(new Container(item).bottom().right().padBottom(-15.0F));
      }

      menu.animate = false;
      menu.show(oldMenu);
      menu.animate = true;
   }

   @Override
   public void draw(Batch batch, float parentAlpha) {
      if (Network.clientIsConnected()) {
         this.undoItem.setDisabled(false);
         this.redoItem.setDisabled(false);
      } else {
         this.undoItem.setDisabled(App.game.undoIndex >= App.game.undos.size - 1);
         this.redoItem.setDisabled(App.game.undoIndex <= 0);
      }

      super.draw(batch, parentAlpha);
   }

   @Override
   public boolean show(Actor positionActor, float positionX, float positionY, float positionWidth, float positionHeight, boolean preferRight) {
      this.removePlayerItem.setDisabled(App.gloom.playerRows.size == 0);
      this.removeMonsterItem.setDisabled(App.gloom.monsterRows.size == 0);
      return super.show(positionActor, positionX, positionY, positionWidth, positionHeight, preferRight);
   }
}
