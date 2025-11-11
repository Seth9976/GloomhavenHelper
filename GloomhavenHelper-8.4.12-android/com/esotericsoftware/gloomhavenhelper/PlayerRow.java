package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasSprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectSet;
import com.badlogic.gdx.utils.SnapshotArray;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Monster;
import com.esotericsoftware.gloomhavenhelper.model.Player;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.Animator;
import com.esotericsoftware.gloomhavenhelper.util.DragAdjust;
import com.esotericsoftware.gloomhavenhelper.util.HPAdjust;
import com.esotericsoftware.gloomhavenhelper.util.Row;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.esotericsoftware.minlog.Log;

public class PlayerRow extends Row {
    ImageButton addSummonButton;
    Drawable bloodDrawable;
    Actor dragArea;
    float glowAlpha;
    Drawable glowDrawable;
    HPAdjust hpAdjust;
    Label hpLabel;
    private float hpPercent;
    private TextureRegion hpRegion;
    Drawable iconDrawable;
    DragAdjust initListener;
    private TextureRegion initRegion;
    long lastAnimateIcon;
    Table leftInfo;
    public static final ObjectSet localInit;
    Label lootLabel;
    PlayerRowMenu menu;
    Label nameLabel;
    public final Player player;
    Table rightInfo;
    float summonAlpha;
    HPAdjust xpAdjust;
    Label xpLabel;

    static {
        PlayerRow.localInit = new ObjectSet();
    }

    public PlayerRow(Player player0) {
        this.player = player0;
        this.hpPercent = Math.min(1.0f, ((float)player0.hp) / ((float)player0.hpMax));
        this.create();
        this.layoutUI();
        this.events();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void act(float f) {
        float f1;
        super.act(f);
        this.readyTime -= f;
        this.hpPercent = App.animate(this.hpPercent, Math.min(1.0f, ((float)this.player.hp) / ((float)this.player.hpMax)), 0.2f, 2.0f, 2.0f, f);
        this.summonAlpha = App.animate(this.summonAlpha, (this.boxes.size == 0 ? 0.0f : 1.0f), 0.2f, 2.0f, 2.0f, f);
        if(!App.state.ignoreChanges) {
            if(!App.state.canDraw || !this.isAlive()) {
                f1 = this.isCurrentTurn() ? 1.0f : 0.0f;
            }
            else if((App.state.playerInit == PlayerInit.numpad || App.state.playerInit == PlayerInit.dragNumberRequired) && this.player.init == 0) {
                f1 = 1.0f;
            }
            else {
                f1 = 0.0f;
            }
            this.glowAlpha = App.animate(this.glowAlpha, f1, 0.2f, 2.0f, 2.0f, f);
        }
        this.nameLabel.setText(this.player.name);
    }

    private void create() {
        this.dragArea = new Actor();
        this.hpRegion = App.skin.getRegion("player-hp");
        this.initRegion = App.skin.getRegion("init");
        this.glowDrawable = App.skin.getDrawable("player-glow");
        this.bloodDrawable = App.skin.getDrawable("blood");
        this.iconDrawable = App.skin.getDrawable("class-icons/" + this.player.characterClass.name());
        this.nameLabel = new Label(this.player.characterClass.toString(), App.skin, "fancyLargeOutline", Color.WHITE);
        this.nameLabel.setEllipsis(true);
        this.leftInfo = new Table();
        this.rightInfo = new Table();
        this.hpLabel = new Label("", App.skin, "plainExtraLargeOutlineNumbers", Color.WHITE);
        this.lootLabel = new Label("", App.skin, "plainLargeOutlineFixedNumbers", App.lootGold);
        this.lootLabel.setAlignment(16);
        this.xpLabel = new Label("", App.skin, "plainLargeOutlineFixedNumbers", App.xpBlue);
        this.xpLabel.setAlignment(16);
        this.addSummonButton = App.imageButton().imageUp("psd/add", App.buttonGray).imageOver("psd/add").imageDisabled("psd/add", App.disabledGray).create();
        this.menu = new PlayerRowMenu(this);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public float desat() {
        return this.isAlive() ? super.desat() : 1.0f;
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public void draw(Batch batch0, float f) {
        float f5;
        int v = this.getInitiative();
        boolean z = App.state.playerInit != PlayerInit.dragOrder && v > 0 && v < 100;
        if(!z) {
            if(this.leftInfo.getPadLeft() != 0.0f) {
                this.leftInfo.padLeft(0.0f).invalidate();
                this.hpAdjust.minX = 199.0f;
            }
        }
        else if(this.leftInfo.getPadLeft() == 0.0f) {
            this.leftInfo.padLeft(53.0f).invalidate();
            this.hpAdjust.minX = 252.0f;
        }
        int v1 = this.player.hpMax;
        this.hpLabel.setColor(App.c(Color.WHITE, f));
        this.hpLabel.setText(this.player.hp + "/" + v1);
        this.lootLabel.setText(this.player.loot);
        this.xpLabel.setText(this.player.xp);
        super.draw(batch0, f);
        float f1 = this.getX();
        float f2 = this.getY();
        if(z) {
            batch0.setColor(1.0f, 1.0f, 1.0f, f);
            batch0.draw(this.initRegion, f1 + 188.0f, f2 + 97.0f);
            App.fancyExtraLargeOutlineNumbers.setColor(1.0f, 1.0f, 1.0f, f);
            App.fancyExtraLargeOutlineNumbers.draw(batch0, this.player.initString, f1 + 202.0f, f2 + 89.0f, 0.0f, 1, false);
        }
        Label label0 = this.hpLabel;
        float f3 = this.hpLabel.getWidth();
        float f4 = f1 + (label0.localToAscendantCoordinates(this, App.v2.set(f3, 0.0f)).x + 18.0f);
        if(this.player.conditions.size > 0) {
            switch(this.player.conditions.size) {
                case 6: {
                    f5 = 41.0f;
                    break;
                }
                case 7: {
                    f4 -= 5.0f;
                    f5 = 36.0f;
                    break;
                }
                default: {
                    if(this.player.conditions.size > 7) {
                        f4 -= 8.0f;
                        f5 = 31.0f;
                    }
                    else {
                        f5 = 42.0f;
                    }
                }
            }
            batch0.setColor(App.c.set(1.0f, 1.0f, 1.0f, f));
            int v2 = this.player.conditions.size - 1;
            for(int v3 = 0; v3 <= v2; ++v3) {
                ((Condition)this.player.conditions.get(v2 - v3)).drawable.draw(batch0, f4 + ((float)v3) * f5, f2 + 29.0f, 34.0f, 34.0f);
            }
        }
        float f6 = this.getX();
        float f7 = this.summonAlpha;
        if(f7 != 0.0f) {
            batch0.setColor(1.0f, 1.0f, 1.0f, f7 * f);
            this.iconDrawable.draw(batch0, f6 + 115.0f, f2 + this.getHeight() - 93.0f, 97.0f, 97.0f);
        }
        float f8 = this.glowAlpha;
        if(f8 != 0.0f) {
            batch0.setColor(1.0f, 1.0f, 1.0f, f8 * f);
            this.glowDrawable.draw(batch0, f6 + 24.0f, f2 + 2.0f, 156.0f, 142.0f);
        }
        this.endDesat(batch0);
        float f9 = MathUtils.clamp(this.hpPercent / (1.0f / ((float)v1)), 0.0f, 1.0f);
        batch0.setColor(App.c(0x37373788, 0.533f * f9 * f));
        this.hpRegion.setRegionWidth(0x75);
        batch0.draw(this.hpRegion, f6 + 43.0f, f2 + 21.0f);
        if(this.hpPercent > 0.0f) {
            int v4 = this.player.hp;
            batch0.setColor(App.c(((v4 > 3 || this.hpPercent > 0.4f || v4 == v1) && this.hpPercent >= 0.3f ? App.healthGreen : App.healthRed), f));
            this.hpRegion.setRegionWidth(((int)Math.max(f9 * 14.0f, Math.round(this.hpPercent * 117.0f))));
            batch0.draw(this.hpRegion, f6 + 43.0f, f2 + 21.0f);
        }
        batch0.setColor(1.0f, 1.0f, 1.0f, f);
        this.bloodDrawable.draw(batch0, f6 + this.leftInfo.getPadLeft() + 197.0f, f2 + 32.0f, 18.0f, 25.0f);
    }

    private void events() {
        this.dragStartActor = this.dragArea;
        this.dragArea.addListener(this.dragListener);
        this.dragArea.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(!App.state.canDraw) {
                    if(PlayerRow.this.turnComplete) {
                        PlayerRow.this.turnEndRevert();
                    }
                    else {
                        PlayerRow.this.turnEnd(false);
                    }
                    App.state.changed();
                }
                else if(App.state.playerInit == PlayerInit.numpad) {
                    com.esotericsoftware.gloomhavenhelper.PlayerRow.2.1 playerRow$2$10 = new TextMenu() {
                        @Override  // com.esotericsoftware.gloomhavenhelper.util.Menu
                        protected void updatePosition() {
                            PlayerRow.this.dragArea.localToStageCoordinates(App.v2.set(PlayerRow.this.dragArea.getWidth(), ((float)Math.round(PlayerRow.this.dragArea.getHeight() / 2.0f))));
                            float f = App.v2.x - 12.0f;
                            float f1 = App.v2.y;
                            Vector2 vector20 = App.v2.set(0.0f, 0.0f);
                            PlayerRow.this.localToStageCoordinates(vector20);
                            this.setPosition(f, f1, 0.0f, 0.0f, App.v2.x + 2.0f, App.v2.y, PlayerRow.this.getWidth() - 3.0f, PlayerRow.this.getHeight() + ((float)(PlayerRow.this.boxes.size == 0 ? 0 : 6)));
                            super.updatePosition();
                        }
                    };
                    Label label0 = new Label("__", App.skin, "fancyLargeOutlineFixedNumbers", Color.WHITE);
                    label0.setAlignment(1);
                    Container container0 = new Container(label0);
                    playerRow$2$10.table.add(container0).row();
                    playerRow$2$10.addListener(new InputListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
                        public boolean keyDown(InputEvent inputEvent0, int v) {
                            if(v >= 7 && v <= 16) {
                                label0.setText(label0.getText().charAt(1) + Integer.toString(v - 7));
                                if(label0.getText().charAt(0) != 0x5F) {
                                    int v1 = Integer.parseInt("");
                                    PlayerRow.this.player.init(v1);
                                    PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
                                    playerRow$2$10.hide();
                                    App.state.changed();
                                }
                            }
                            return true;
                        }
                    });
                    com.esotericsoftware.gloomhavenhelper.PlayerRow.2.3 playerRow$2$30 = new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            String s = ((TextButton)actor0).getText().toString();
                            label0.setText(label0.getText().charAt(1) + s);
                            if(label0.getText().charAt(0) != 0x5F) {
                                int v = Integer.parseInt("");
                                PlayerRow.this.player.init(v);
                                PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
                                playerRow$2$10.hide();
                                App.state.changed();
                            }
                        }
                    };
                    Table table0 = new Table(App.skin);
                    table0.pad(-6.0f, 12.0f, 0.0f, 12.0f).defaults().size(100.0f);
                    table0.columnDefaults(0).right();
                    table0.columnDefaults(2).left();
                    for(int v = 1; v <= 9; ++v) {
                        table0.add(((TextButtonBuilder)App.textButton((v + "")).change(playerRow$2$30)).create());
                        if(v % 3 == 0) {
                            table0.row();
                        }
                    }
                    table0.add();
                    table0.add(((TextButtonBuilder)App.textButton("0").change(playerRow$2$30)).create());
                    playerRow$2$10.table.add(table0).row();
                    playerRow$2$10.show(null, 0.0f, 0.0f, 0.0f, 0.0f, true);
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                super.touchDragged(inputEvent0, f, f1, v);
                if(PlayerRow.this.dragListener.isDragging()) {
                    this.cancel();
                }
                if(PlayerRow.this.initListener.isDragging()) {
                    this.cancel();
                }
            }
        });
        this.setTouchable(Touchable.enabled);
        this.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(this.inTapSquare(f, f1) && f >= 200.0f && f1 <= 152.0f) {
                    App.gloom.rowsScroll.scrollTo(0.0f, PlayerRow.this.getY() - 7.0f, 0.0f, PlayerRow.this.getHeight() + 14.0f);
                    PlayerRow.this.menu.show(null, 0.0f, 0.0f, 0.0f, 0.0f, true);
                    PlayerRow.this.hpAdjust.changeContainer.clearActions();
                    PlayerRow.this.hpAdjust.changeContainer.setVisible(false);
                    PlayerRow.this.xpAdjust.changeContainer.clearActions();
                    PlayerRow.this.xpAdjust.changeContainer.setVisible(false);
                }
            }
        });
        this.hpAdjust = new HPAdjust(this, "plainExtraLargeOutlineNumbers") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void apply() {
                App.state.changed();
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getMax() {
                return PlayerRow.this.player.hpMax;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void getPosition(Vector2 vector20) {
                PlayerRow.this.hpLabel.localToStageCoordinates(vector20.set(PlayerRow.this.hpLabel.getWidth() + 3.0f, -5.0f));
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRow.this.player.hp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void setValue(int v) {
                if(PlayerRow.this.player.hp != v) {
                    PlayerRow.this.hpChanged(this.extra + v - this.start);
                }
                PlayerRow.this.player.hp = v;
                if(v > 0) {
                    App.state.changed();
                }
                super.setValue(v);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(f < 200.0f) {
                    return false;
                }
                return f >= 780.0f ? false : super.touchDown(inputEvent0, f, f1, v, v1);
            }
        };
        this.xpAdjust = new HPAdjust(this, "plainExtraLargeOutlineNumbers") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void getPosition(Vector2 vector20) {
                PlayerRow.this.xpLabel.localToStageCoordinates(vector20.set(PlayerRow.this.xpLabel.getWidth() + 45.0f, -25.0f));
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRow.this.player.xp;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            protected void setValue(int v) {
                PlayerRow.this.player.xp = v;
                App.state.changed();
                super.setValue(v);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.HPAdjust
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                return f < 780.0f ? false : super.touchDown(inputEvent0, f, f1, v, v1);
            }
        };
        this.initListener = new DragAdjust(null, new Actor[]{this.dragArea}) {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int amount(float f, float f1) {
                return Math.round(f / 9.0f);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            public void dragStart(InputEvent inputEvent0, float f, float f1, int v) {
                App.stage.cancelTouchFocusExcept(PlayerRow.this.initListener, PlayerRow.this.dragArea);
                PlayerRow.this.player.init(1);
                PlayerRow.localInit.add(PlayerRow.this.player.characterClass);
                super.dragStart(inputEvent0, f, f1, v);
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
                super.dragStop(inputEvent0, f, f1, v);
                if(!App.state.canDraw) {
                    PlayerRow.this.setZIndex(0x1869F);
                    SnapshotArray snapshotArray0 = App.gloom.rows.getChildren();
                    int v2 = snapshotArray0.size;
                    for(int v1 = 0; v1 < v2; ++v1) {
                        Row row0 = (Row)snapshotArray0.get(v1);
                        if(row0 != PlayerRow.this) {
                            int v3 = row0.getInitiative();
                            if(v3 >= 1 && v3 <= 99 && v3 >= PlayerRow.this.player.init) {
                                PlayerRow.this.setZIndex(v1);
                                break;
                            }
                        }
                    }
                }
                App.state.changed();
                if(!App.state.canDraw) {
                    Animator.storeChildren(App.gloom.rows, 0.1f);
                }
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getMax() {
                return 99;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected int getValue() {
                return PlayerRow.this.player.init;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            protected void setValue(int v) {
                PlayerRow.this.player.init(v);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                return App.state.playerInit == PlayerInit.dragNumber || App.state.playerInit == PlayerInit.dragNumberRequired ? super.touchDown(inputEvent0, f, f1, v, v1) : false;
            }

            @Override  // com.esotericsoftware.gloomhavenhelper.util.DragAdjust
            public void touchDragged(InputEvent inputEvent0, float f, float f1, int v) {
                super.touchDragged(inputEvent0, f, this.getTouchDownY(), v);
            }
        };
        this.initListener.min = 1;
        this.addSummonButton.addListener(new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.stop();
                return super.touchDown(inputEvent0, f, f1, v, v1);
            }
        });
        this.addSummonButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                new SummonAddMenu(PlayerRow.this).show(PlayerRow.this.addSummonButton, 19.0f, -22.0f, -41.0f, 22.0f, true);
            }
        });
    }

    public void flashIcon(String s) {
        this.animateIcon(this.dragArea, "conditions/" + s + "-large", 2.0f, 105.0f, this.dragArea.getHeight() / 2.0f);
    }

    public void flashIcons(Array array0) {
        int v = array0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            String s = (String)array0.get(v1);
            if(v1 > 0) {
                this.addAction(Actions.sequence(Actions.delay(((float)v1) * 0.4f), new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        PlayerRow.this.flashIcon(s);
                        return true;
                    }
                }));
            }
            else {
                this.flashIcon(s);
            }
        }
    }

    // 去混淆评级： 低(20)
    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public int getInitiative() {
        return this.isAlive() ? this.player.init : 102;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public int getLevel() {
        return this.player.level;
    }

    // 去混淆评级： 低(20)
    @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
    public float getPrefHeight() {
        return !PlayerRow.animatedHeight || !Animator.enabled || this.animateHeight == 0.0f ? super.getPrefHeight() : this.animateHeight;
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public boolean hasExpiringConditions() {
        for(int v = this.player.conditions.size - 1; v >= 0; --v) {
            if(this.isAlive()) {
                switch(com.esotericsoftware.gloomhavenhelper.PlayerRow.11.$SwitchMap$com$esotericsoftware$gloomhavenhelper$model$Condition[((Condition)this.player.conditions.get(v)).ordinal()]) {
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    void hpChanged(int v) {
        long v1 = System.currentTimeMillis();
        if(v1 > this.lastAnimateIcon + 1000L) {
            this.lastAnimateIcon = v1;
            boolean z = this.player.conditions.contains(Condition.poison, true);
            if(v < 0) {
                if(z) {
                    this.flashIcon("poison");
                }
            }
            else if(v > 0) {
                boolean z1 = this.player.conditions.contains(Condition.wound, true);
                if(z && z1) {
                    this.flashIcon("poison");
                    this.addAction(Actions.sequence(Actions.delay(0.4f), new Action() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.Action
                        public boolean act(float f) {
                            PlayerRow.this.flashIcon("wound");
                            return true;
                        }
                    }));
                    return;
                }
                if(z) {
                    this.flashIcon("poison");
                    return;
                }
                if(z1) {
                    this.flashIcon("wound");
                }
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public boolean isAlive() {
        return this.player.hp > 0 && !this.player.exhausted;
    }

    private void layoutUI() {
        TextureRegion textureRegion1;
        TextureRegion textureRegion0;
        Image image0 = new Image(this.iconDrawable);
        image0.setTouchable(Touchable.disabled);
        this.leftInfo.add(image0).pad(-36.0f, -16.0f, -40.0f, -14.0f).minSize(Value.prefWidth);
        this.leftInfo.add(this.nameLabel).growX().pad(-2.0f, 0.0f, -1.0f, 0.0f).minWidth(0.0f).prefWidth(0.0f).row();
        this.leftInfo.add(this.hpLabel).colspan(2).expand().pad(-2.0f, 53.0f, 0.0f, 0.0f).left();
        this.rightInfo.add(this.xpLabel).right().padRight(9.0f).height(0.0f).padTop(2.0f).minWidth(30.0f);
        this.rightInfo.add(new Image(App.skin.newDrawable("psd/xp", App.xpBlue))).row();
        this.rightInfo.add(this.lootLabel).right().padRight(7.0f).height(0.0f).padTop(17.0f).minWidth(30.0f);
        this.rightInfo.add(new Image(App.skin.newDrawable("abilities/loot", App.lootGold))).padTop(13.0f);
        try {
            if(Gdx.app.getType() == ApplicationType.Desktop) {
                FileHandle fileHandle0 = new FileHandle("class-bgs/" + this.player.characterClass.name() + ".png");
                if(fileHandle0.exists()) {
                    try {
                        textureRegion0 = new TextureRegion(new Texture(fileHandle0));
                    }
                    catch(RuntimeException runtimeException0) {
                        Log.error("ghh", "Unable to read image: " + fileHandle0, runtimeException0);
                        textureRegion1 = (TextureRegion)App.skin.optional("class-bgs/" + this.player.characterClass.name(), TextureRegion.class);
                        goto label_22;
                    }
                }
                else {
                    textureRegion1 = (TextureRegion)App.skin.optional("class-bgs/" + this.player.characterClass.name(), TextureRegion.class);
                    goto label_22;
                }
            }
            else {
                textureRegion1 = (TextureRegion)App.skin.optional("class-bgs/" + this.player.characterClass.name(), TextureRegion.class);
                goto label_22;
            }
        }
        catch(RuntimeException unused_ex) {
            textureRegion0 = null;
        }
        goto label_28;
    label_22:
        if(textureRegion1 == null) {
            try {
                textureRegion0 = App.skin.getRegion("separate/class-bgs/" + this.player.characterClass.name());
            }
            catch(RuntimeException unused_ex) {
                textureRegion0 = null;
            }
        }
        else {
            textureRegion0 = textureRegion1;
        }
    label_28:
        com.esotericsoftware.gloomhavenhelper.PlayerRow.1 playerRow$10 = new Table() {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
            public void draw(Batch batch0, float f) {
                PlayerRow.this.beginDesat(batch0);
                super.draw(batch0, f);
            }
        };
        playerRow$10.setClip(true);
        playerRow$10.pad(10.0f, 0.0f, 15.0f, 12.0f);
        if(textureRegion0 != null) {
            playerRow$10.background(new SpriteDrawable(new AtlasSprite(((AtlasRegion)textureRegion0))));
        }
        playerRow$10.add(this.dragArea).width(174.0f).fillY().pad(-20.0f, 0.0f, -20.0f, 0.0f);
        playerRow$10.add(this.leftInfo).growX().top().left().fillY();
        playerRow$10.add(this.addSummonButton).size(102.0f).padRight(9.0f);
        playerRow$10.add(this.rightInfo).padRight(158.0f).fillY();
        this.bottom();
        this.monstersCell = this.add(this.monstersGroup).colspan(2).pad(0.0f, 212.0f, 0.0f, 5.0f).fillX().expandY().top();
        this.row();
        this.add(playerRow$10).pad(-1.0f, -1.0f, 1.0f, -1.0f).fillX().height(Value.prefHeight).padBottom(-5.0f);
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void roundEnd() {
        this.player.expiredConditions.clear();
        this.player.currentTurnConditions.clear();
        if(this.player.characterClass != CharacterClass.Escort && this.player.characterClass != CharacterClass.Objective) {
            this.player.init(0);
        }
        super.roundEnd();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void setLevel(int v) {
        if(v > 9) {
            v = 9;
        }
        this.player.level = v;
        int v1 = this.player.hpMax;
        this.player.hpMax = this.player.characterClass.hpMax(v);
        if(this.player.hp >= v1) {
            this.player.hp = this.player.hpMax;
        }
        for(Object object0: this.boxes) {
            MonsterBox monsterBox0 = (MonsterBox)object0;
            Monster monster0 = monsterBox0.monster;
            int v2 = monster0.hpMax;
            if(monster0.summonColor == SummonColor.beast) {
                int v3 = this.player.level * 2 + 8;
                monsterBox0.monster.hpMax = v3;
                monster0.hp = v3;
            }
            else {
                monster0.hpMax = monster0.stats.hpMax();
                if(monster0.hp >= v2 || v2 == 0) {
                    monster0.hp = monster0.hpMax;
                }
            }
        }
        App.state.changed();
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Actor
    protected void setParent(Group group0) {
        super.setParent(group0);
        if(group0 == null) {
            this.hpAdjust.changeContainer.remove();
            this.xpAdjust.changeContainer.remove();
            return;
        }
        App.stage.addActor(this.hpAdjust.changeContainer);
        App.stage.addActor(this.xpAdjust.changeContainer);
    }

    @Override  // com.badlogic.gdx.scenes.scene2d.Group
    public String toString() {
        String s = this.player.name;
        return s.equals(this.player.characterClass.toString()) ? s : s + " (" + this.player.characterClass.name() + ")";
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void turnEnd(boolean z) {
        if(this.turnComplete) {
            return;
        }
        super.turnEnd(z);
        if(this.isAlive() && App.state.expireConditions) {
            for(int v = this.player.conditions.size - 1; v >= 0; --v) {
                Condition condition0 = (Condition)this.player.conditions.get(v);
                switch(com.esotericsoftware.gloomhavenhelper.PlayerRow.11.$SwitchMap$com$esotericsoftware$gloomhavenhelper$model$Condition[condition0.ordinal()]) {
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: 
                    case 5: 
                    case 6: {
                        if(!this.player.currentTurnConditions.contains(condition0, true)) {
                            this.player.conditions.removeIndex(v);
                            this.player.expiredConditions.add(condition0);
                        }
                    }
                }
            }
        }
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void turnEndRevert() {
        if(!this.turnComplete) {
            return;
        }
        super.turnEndRevert();
        Array array0 = new Array(2);
        if(this.player.conditions.contains(Condition.regenerate, true)) {
            array0.add("regenerate");
        }
        if(this.player.conditions.contains(Condition.wound, true)) {
            array0.add("wound");
        }
        this.flashIcons(array0);
        this.player.conditions.addAll(this.player.expiredConditions);
        this.player.conditions.sort();
        this.player.expiredConditions.clear();
    }

    @Override  // com.esotericsoftware.gloomhavenhelper.util.Row
    public void turnStart(boolean z) {
        super.turnStart(z);
        Array array0 = new Array(2);
        if(this.player.conditions.contains(Condition.regenerate, true)) {
            array0.add("regenerate");
        }
        if(this.player.conditions.contains(Condition.wound, true)) {
            array0.add("wound");
        }
        this.flashIcons(array0);
    }
}

