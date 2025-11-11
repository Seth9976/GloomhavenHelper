package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array.ArrayIterator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.SnapshotArray;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.Timer;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.ElementState;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.Player;
import com.esotericsoftware.gloomhavenhelper.model.PlayerInit;
import com.esotericsoftware.gloomhavenhelper.model.Scenario;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.Animator;
import com.esotericsoftware.gloomhavenhelper.util.ElementButton;
import com.esotericsoftware.gloomhavenhelper.util.GloomScrollPane;
import com.esotericsoftware.gloomhavenhelper.util.Row;
import com.esotericsoftware.gloomhavenhelper.util.TextMenu;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.utils.SkeletonActor;

public class GloomhavenHelper extends ApplicationAdapter {
    public ElementButton airButton;
    Button attackButton;
    Image attackImage1;
    Image attackImage2;
    public Label attackLabel;
    Sprite bg;
    Sprite bgLeft;
    Sprite bgMiddle;
    Sprite bgRight;
    private Label coinsLabel;
    public ElementButton darkButton;
    TextButton drawButton;
    int drawMinutes;
    Task drawTask;
    public ElementButton earthButton;
    Table elementsTable;
    public ElementButton fireButton;
    TextButton helpAddMonsters;
    TextButton helpAddPlayers;
    public Cell helpCell;
    Label helpChooseCards1;
    Label helpChooseCards2;
    Label helpChooseCards3;
    Label helpNextRound1;
    Label helpNextRound2;
    TextButton helpSetScenario;
    Table helpSetScenarioOrAddMonsters;
    Label helpSpawnMonsters;
    Label helpTakeTurns1;
    Label helpTakeTurns2;
    Label helpTakeTurns3;
    public ElementButton iceButton;
    public Intro intro;
    private long lastZoomTime;
    public ElementButton lightButton;
    public MainMenu mainMenu;
    Button mainMenuButton;
    public final Array monsterRows;
    private Cell networkCell;
    Container networkConnected;
    Container networkDisconnected;
    Container networkServerGray;
    Container networkServerGreen;
    Stack nextButtonStack;
    public final Array playerRows;
    TextButton roundButton;
    public Label roundLabel;
    public Rows rows;
    public ScrollPane rowsScroll;
    public Label scenarioLabel;
    private Label scenarioLevelLabel;
    private int scenarioNumber;
    Table scenarioTable;
    private boolean solo;
    public Group toasts;
    private Label trapsLabel;
    Sprite vignette;
    private Label xpLabel;
    public boolean zoomIn;
    public boolean zoomOut;

    public GloomhavenHelper() {
        this.monsterRows = new Array();
        this.playerRows = new Array();
    }

    public void addMonsterRow(MonsterRow monsterRow0) {
        this.monsterRows.add(monsterRow0);
        this.rows.addActor(monsterRow0);
        App.root.validate();
    }

    public void addSection(Scenario scenario0) {
        int v = App.state.scenarioLevel;
        for(Object object0: scenario0.monsters) {
            MonsterData monsterData0 = (MonsterData)object0;
            int v1 = Math.min(7, this.solo + v);
            if(this.getMonsterRow(monsterData0) == null) {
                this.addMonsterRow(new MonsterRow(monsterData0, v1));
            }
        }
        for(Object object1: scenario0.special) {
            if(((String)object1).equals("Valrath Tracker: %target% C, %pierce% 10, Vermling Shaman: CxH/2, City Guard: CxH/2, Savvas Lavaflow: CxH/2")) {
                this.getMonsterRow("Vermling Shaman");
            }
        }
        this.addSpecials(scenario0.special, v);
    }

    private void addSpecials(Array array0, int v) {
        if(v > 7) {
            v = 7;
        }
        Player player0 = null;
        for(Object object0: array0) {
            String s = (String)object0;
            boolean z = s.startsWith("Escort: ");
            if(z || s.startsWith("Objective: ")) {
                String s1 = s.substring((z ? 8 : 11));
                Player player1 = this.getPlayer(s1);
                if(player1 == null) {
                    Player player2 = new Player();
                    player2.level = v;
                    player2.name = s1;
                    player2.characterClass = z ? CharacterClass.Escort : CharacterClass.Objective;
                    int v9 = player2.characterClass.hpMax(v);
                    player2.hpMax = v9;
                    player2.hp = v9;
                    PlayerRow playerRow0 = new PlayerRow(player2);
                    this.playerRows.add(playerRow0);
                    this.rows.addActor(playerRow0);
                    player0 = player2;
                }
                else {
                    player0 = player1;
                }
            }
            else if(player0 != null) {
                if(s.equals("4+2xL")) {
                    int v1 = v * 2 + 4;
                    player0.hpMax = v1;
                    player0.hp = v1;
                }
                else if(s.equals("6+2xL")) {
                    int v2 = v * 2 + 6;
                    player0.hpMax = v2;
                    player0.hp = v2;
                }
                else if(s.equals("C+L+1")) {
                    int v3 = this.playerCount() + v + 1;
                    player0.hpMax = v3;
                    player0.hp = v3;
                }
                else if(s.equals("Cx2+L")) {
                    int v4 = this.playerCount() * 2 + v;
                    player0.hpMax = v4;
                    player0.hp = v4;
                }
                else if(s.equals("Cx2+L+4")) {
                    int v5 = this.playerCount() * 2 + v + 4;
                    player0.hpMax = v5;
                    player0.hp = v5;
                }
                else if(s.equals("2x(C+L)+3")) {
                    int v6 = (this.playerCount() + v) * 2 + 3;
                    player0.hpMax = v6;
                    player0.hp = v6;
                }
                else if(s.equals("5+L+2xC")) {
                    int v7 = v + 5 + this.playerCount() * 2;
                    player0.hpMax = v7;
                    player0.hp = v7;
                }
                else {
                    if(!s.equals("20+L+2xC")) {
                        continue;
                    }
                    int v8 = v + 20 + this.playerCount() * 2;
                    player0.hpMax = v8;
                    player0.hp = v8;
                }
            }
        }
    }

    @Override  // com.badlogic.gdx.ApplicationAdapter
    public void create() {
        App.game.initialize();
        if(App.args != null) {
            for(int v = 0; v < App.args.length; ++v) {
                if(App.args[v].equals("-verbose")) {
                    Log.TRACE();
                    Gdx.app.setLogLevel(3);
                }
            }
        }
        App.gameThread = App.game.currentThread();
        App.game.loadConfig();
        App.load();
        App.viewport.setMinWorldHeight((App.config.zoomReset ? ((float)Math.max(800, Gdx.graphics.getHeight())) : App.config.zoom));
        this.updateViewport();
        this.drawTask = new Task() {
            @Override  // com.badlogic.gdx.utils.Timer$Task
            public void run() {
                ++GloomhavenHelper.this.drawMinutes;
            }
        };
        this.createUI();
        this.layoutUI();
        this.events();
        App.stage.setScrollFocus(this.rowsScroll);
        if(App.config.fullscreen) {
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
            Gdx.graphics.setVSync(true);
        }
        App.game.loadState();
        this.updateHelpText();
    }

    public void createUI() {
        App.root = new Table();
        this.intro = new Intro();
        this.bg = App.skin.getSprite("bg/bg");
        this.vignette = App.skin.getSprite("vignette/vignette");
        this.bgLeft = App.skin.getSprite("vignette/bg-left");
        this.bgRight = App.skin.getSprite("vignette/bg-right");
        this.bgMiddle = App.skin.getSprite("vignette/bg-middle");
        this.networkDisconnected = new Container(new Image(App.drawable("network-disconnected", new Color(0xD01E1EFF)))).padLeft(35.0f);
        this.networkConnected = new Container(new Image(App.drawable("network-connected", new Color(0xC7A20FF)))).padLeft(35.0f);
        this.networkServerGray = new Container(new Image(App.drawable("network-server", App.buttonGray))).padLeft(35.0f);
        this.networkServerGreen = new Container(new Image(App.drawable("network-server", new Color(0xC7A20FF)))).padLeft(35.0f);
        this.mainMenu = new MainMenu();
        this.mainMenuButton = App.imageButton().imageUp("psd/menu", App.buttonGray).imageOver("psd/menu").imageChecked("psd/menu").create();
        this.scenarioLabel = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
        this.scenarioLabel.setWrap(true);
        this.scenarioLabel.setAlignment(1);
        this.fireButton = new ElementButton("fire") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.fire = elementState0;
                App.state.changed();
            }
        };
        this.iceButton = new ElementButton("ice") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.ice = elementState0;
                App.state.changed();
            }
        };
        this.airButton = new ElementButton("air") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.air = elementState0;
                App.state.changed();
            }
        };
        this.earthButton = new ElementButton("earth") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.earth = elementState0;
                App.state.changed();
            }
        };
        this.lightButton = new ElementButton("light") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.light = elementState0;
                App.state.changed();
            }
        };
        this.darkButton = new ElementButton("dark") {
            @Override  // com.esotericsoftware.gloomhavenhelper.util.ElementButton
            protected void clicked(ElementState elementState0) {
                App.state.dark = elementState0;
                App.state.changed();
            }
        };
        this.roundButton = App.textButton("Next\nRound").disabledFontColor(App.gray).create();
        this.roundButton.getLabelCell().pad(-12.0f, 9.0f, 0.0f, 0.0f);
        TextButtonBuilder textButtonBuilder0 = App.textButton("Draw").disabledFontColor(App.gray);
        this.drawButton = textButtonBuilder0.set(new TextButton("", textButtonBuilder0.set(new TextButtonStyle())) {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.TextButton
            public void draw(Batch batch0, float f) {
                if(GloomhavenHelper.this.drawMinutes > 0) {
                    App.plainMediumOutline.setColor(App.buttonGray);
                    App.plainMediumOutline.draw(batch0, (GloomhavenHelper.this.drawMinutes == 1 ? "1 minute" : GloomhavenHelper.this.drawMinutes + " minutes"), ((float)Math.round(this.getWidth() / 2.0f)), this.getHeight() - 11.0f, 0.0f, 1, false);
                }
                super.draw(batch0, f);
            }
        });
        this.roundLabel = new Label("", App.skin, "plainMediumOutline", App.buttonGray);
        this.roundLabel.setTouchable(Touchable.disabled);
        this.nextButtonStack = new Stack(new Actor[]{new Container(this.roundLabel).width(0.0f).pad(0.0f, 0.0f, 5.0f, (App.config.language.equals("fr") || App.config.language.equals("pl") || App.config.language.equals("jp") || App.config.language.equals("it") || App.config.language.equals("ru") ? -4.0f : 8.0f)).bottom().right()});
        this.scenarioLevelLabel = new Label("", App.skin, "default", App.buttonGray);
        this.xpLabel = new Label("", App.skin, "default", App.buttonGray);
        this.trapsLabel = new Label("", App.skin, "default", App.buttonGray);
        this.coinsLabel = new Label("", App.skin, "default", App.buttonGray);
        this.attackButton = App.imageButton().imageUp(App.drawable(new String[]{"attack/back", "attack/border"})).create();
        this.attackImage1 = new Image();
        this.attackImage2 = new Image();
        this.attackLabel = new Label("", App.skin, "plainMediumOutline", App.lightGray);
        this.attackLabel.setTouchable(Touchable.disabled);
        this.rows = new Rows();
        this.elementsTable = new Table();
        this.scenarioTable = new Table(App.skin);
        this.scenarioTable.setTouchable(Touchable.enabled);
        this.rowsScroll = new GloomScrollPane(this.rows) {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
            public void draw(Batch batch0, float f) {
                this.validate();
                batch0.setColor(f, f, f, 0.0f);
                batch0.disableBlending();
                float f1 = GloomhavenHelper.this.rows.startX;
                float f2 = (float)Math.round(this.getHeight() + this.getVisualScrollY());
                while(f1 > 0.0f) {
                    f1 -= 1024.0f;
                }
                float f3 = this.getWidth();
                while(f1 < f3 + 1024.0f) {
                    for(float f4 = f2; f4 >= -1024.0f; f4 -= 1024.0f) {
                        batch0.draw(GloomhavenHelper.this.bg, f1, f4, 1024.0f, 1024.0f);
                    }
                    f1 += 1024.0f;
                }
                batch0.enableBlending();
                batch0.setColor(1.0f, 1.0f, 1.0f, f);
                float f5 = this.getWidth();
                while(f2 >= -1024.0f) {
                    batch0.draw(GloomhavenHelper.this.bgLeft, 0.0f, f2, 124.0f, 1024.0f);
                    batch0.draw(GloomhavenHelper.this.bgRight, f5 - 145.0f, f2, 145.0f, 1024.0f);
                    for(int v = 1; v < GloomhavenHelper.this.rows.columns; ++v) {
                        batch0.draw(GloomhavenHelper.this.bgMiddle, ((float)Math.round(GloomhavenHelper.this.rows.startX + (GloomhavenHelper.this.rows.spaceX + 1101.0f) * ((float)v) - GloomhavenHelper.this.rows.spaceX / 2.0f - 40.0f)), f2, 81.0f, 1024.0f);
                    }
                    f2 -= 1024.0f;
                }
                batch0.draw(GloomhavenHelper.this.vignette, this.getX(), this.getY(), this.getWidth(), this.getHeight());
                super.draw(batch0, f);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup
            public void invalidate() {
                super.invalidate();
                GloomhavenHelper.this.rows.invalidate();
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
            protected void visualScrollY(float f) {
                super.visualScrollY(f);
                if(App.dragging) {
                    Stage stage0 = App.stage;
                    float f1 = (float)Gdx.input.getX();
                    float f2 = (float)Gdx.input.getY();
                    stage0.screenToStageCoordinates(App.v2.set(f1, f2));
                    GloomhavenHelper.this.updateDrag(App.v2.x, App.v2.y);
                }
            }
        };
        ScrollPaneStyle scrollPane$ScrollPaneStyle0 = this.rowsScroll.getStyle();
        scrollPane$ScrollPaneStyle0.vScrollKnob = App.skin.getDrawable("scroll-vert");
        this.rowsScroll.setFadeScrollBars(true);
        this.rowsScroll.setupFadeScrollBars(0.4f, 0.0f);
        this.rowsScroll.setScrollBarTouch(false);
        this.rowsScroll.setFlickScrollTapSquareSize(35.0f);
        this.helpAddPlayers = App.textButton("Add Characters").create();
        this.helpSetScenario = App.textButton("Set Scenario").create();
        this.helpAddMonsters = App.textButton("Add Monsters").create();
        this.helpSetScenarioOrAddMonsters = new Table(App.skin).left();
        this.helpSetScenarioOrAddMonsters.add(this.helpSetScenario);
        this.helpSetScenarioOrAddMonsters.add(new Label("    or    ", App.skin, "plainLargeOutline", App.buttonGray));
        this.helpSetScenarioOrAddMonsters.add(this.helpAddMonsters);
        this.helpSpawnMonsters = new Label("Use the \"+\" buttons\nto spawn monsters.", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpChooseCards1 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpChooseCards2 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpChooseCards3 = new Label("Choose cards.", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpTakeTurns1 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpTakeTurns2 = new Label("", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpTakeTurns3 = new Label("Take turns.", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpNextRound1 = new Label("Click next round.", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpNextRound2 = new Label("Next round.", App.skin, "plainLargeOutline", App.buttonGray);
        this.helpSpawnMonsters.setAlignment(1);
        this.helpChooseCards1.setAlignment(1);
        this.helpChooseCards2.setAlignment(1);
        this.helpChooseCards3.setAlignment(1);
        this.helpTakeTurns1.setAlignment(1);
        this.helpTakeTurns2.setAlignment(1);
        this.helpTakeTurns3.setAlignment(1);
        this.helpNextRound1.setAlignment(1);
        this.helpNextRound2.setAlignment(1);
        this.toasts = new Group();
        App.stage.addActor(this.toasts);
    }

    private void events() {
        Gdx.input.setInputProcessor(new InputMultiplexer(new InputProcessor[]{new InputAdapter() {
            @Override  // com.badlogic.gdx.InputAdapter
            public boolean touchDown(int v, int v1, int v2, int v3) {
                if(GloomhavenHelper.this.intro.introPhase > -1 && GloomhavenHelper.this.intro.introPhase < 3) {
                    return false;
                }
                SkeletonActor skeletonActor0 = App.rippleSkeletonPool.obtain();
                TrackEntry animationState$TrackEntry0 = skeletonActor0.getAnimationState().setAnimation(0, "ripple", false);
                animationState$TrackEntry0.setTrackEnd(animationState$TrackEntry0.getAnimation().getDuration());
                skeletonActor0.addAction(Actions.sequence(Actions.delay(animationState$TrackEntry0.getTrackEnd()), Actions.removeActor()));
                Vector2 vector20 = App.stage.screenToStageCoordinates(App.v2.set(((float)v), ((float)v1)));
                skeletonActor0.setPosition(vector20.x, vector20.y);
                App.stage.addActor(skeletonActor0);
                return false;
            }
        }, App.stage}));
        this.attackButton.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                if(App.state.canDraw) {
                    return;
                }
                AttackModifier attackModifier0 = App.state.attackModifier2();
                if(App.state.attackModifiers.size == 0) {
                    App.state.shuffleAttackModifiers(true);
                    GloomhavenHelper.this.attackImage1.setDrawable(null);
                    GloomhavenHelper.this.attackImage2.setDrawable(null);
                }
                App.state.attackModifiersDiscard.add(App.state.attackModifiers.pop());
                AttackModifier attackModifier1 = App.state.attackModifier1();
                AttackModifier attackModifier2 = App.state.attackModifier2();
                switch(com.esotericsoftware.gloomhavenhelper.GloomhavenHelper.22.$SwitchMap$com$esotericsoftware$gloomhavenhelper$model$AttackModifier[attackModifier1.ordinal()]) {
                    case 1: 
                    case 2: {
                        App.state.needsShuffle = true;
                    }
                }
                App.animateAttackCard(attackModifier1, GloomhavenHelper.this.attackButton, GloomhavenHelper.this.attackImage1, GloomhavenHelper.this.attackImage2);
                if(!App.config.server && !App.config.client) {
                    App.state.changed();
                    return;
                }
                byte[] arr_b = new byte[4];
                int v = 0;
                arr_b[0] = 97;
                arr_b[1] = (byte)(attackModifier1.ordinal() + 1);
                arr_b[2] = (byte)(attackModifier2 == null ? 0 : attackModifier2.ordinal() + 1);
                if(attackModifier0 != null) {
                    v = attackModifier0.ordinal() + 1;
                }
                arr_b[3] = (byte)v;
                App.state.changed(true, arr_b);
            }
        });
        com.esotericsoftware.gloomhavenhelper.GloomhavenHelper.13 gloomhavenHelper$130 = new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                new AttackModifierDeckDialog().show();
            }
        };
        this.attackImage1.addListener(gloomhavenHelper$130);
        this.attackImage2.addListener(gloomhavenHelper$130);
        this.mainMenuButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GloomhavenHelper.this.mainMenu.show(GloomhavenHelper.this.mainMenuButton, 0.0f, 0.0f, -31.0f, 0.0f, true);
            }
        });
        this.drawButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if((App.state.playerInit == PlayerInit.dragNumberRequired || App.state.playerInit == PlayerInit.numpad) && !GloomhavenHelper.this.playerInitiativeSet()) {
                    TextMenu textMenu0 = new TextMenu();
                    Container container0 = new Container(new Label("Player initiative numbers must be set.", App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0f, 16.0f, 0.0f, 16.0f);
                    textMenu0.table.add(container0).row();
                    if(App.config.help) {
                        Container container1 = new Container(new Label((App.state.playerInit == PlayerInit.numpad ? "Click each player\'s portrait to set the initiative number." : "Drag each player\'s portrait to the right to set the initiative number."), App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0f, 16.0f, 0.0f, 16.0f);
                        textMenu0.table.add(container1).row();
                    }
                    textMenu0.table.padBottom(9.0f);
                    textMenu0.show(GloomhavenHelper.this.drawButton, 0.0f, 0.0f, 0.0f, 0.0f, true);
                    textMenu0.setBackgroundOffset(9.0f, 9.0f, 0.0f, 0.0f);
                    return;
                }
                int v = GloomhavenHelper.this.monsterRows.size;
                for(int v1 = 0; v1 < v; ++v1) {
                    ((MonsterRow)GloomhavenHelper.this.monsterRows.get(v1)).showAbility();
                }
                App.state.canDraw = false;
                GloomhavenHelper.this.sortByInitiative(0.5f);
                App.state.changed();
                GloomhavenHelper.this.rowsScroll.setScrollY(0.0f);
            }
        });
        this.roundButton.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(App.state.expireConditions && !GloomhavenHelper.this.turnsFinished()) {
                    SnapshotArray snapshotArray0 = GloomhavenHelper.this.rows.getChildren();
                    int v = 0;
                    if(App.state.expireConditions) {
                        int v1 = snapshotArray0.size;
                        int v2 = 0;
                        while(v < v1) {
                            if(((Row)snapshotArray0.get(v)).hasExpiringConditions()) {
                                v2 = 1;
                            }
                            ++v;
                        }
                        v = v2;
                    }
                    if(v == 0) {
                        GloomhavenHelper.this.nextRound();
                        return;
                    }
                    TextMenu textMenu0 = new TextMenu();
                    Container container0 = new Container(new Label("Some turns have not been taken.", App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0f, 16.0f, 0.0f, 16.0f);
                    textMenu0.table.add(container0).row();
                    if(App.config.help) {
                        Container container1 = new Container(new Label("Click each portrait as turns are completed\nso conditions expire correctly.", App.skin, "plainLargeOutline", App.buttonGray)).pad(6.0f, 16.0f, 0.0f, 16.0f);
                        textMenu0.table.add(container1).row();
                    }
                    ((TextButton)textMenu0.addTextItem("Next Round", new ChangeListener() {
                        @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                        public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                            GloomhavenHelper.this.nextRound();
                            textMenu0.hide();
                        }
                    }).getActor()).getLabel().setAlignment(1);
                    textMenu0.show(GloomhavenHelper.this.roundButton, 0.0f, 0.0f, 0.0f, 0.0f, true);
                    textMenu0.setBackgroundOffset(9.0f, 9.0f, 0.0f, 0.0f);
                    return;
                }
                GloomhavenHelper.this.nextRound();
            }
        });
        this.scenarioTable.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                TextMenu textMenu0 = GloomhavenHelper.this.mainMenu.setScenarioLevel();
                textMenu0.show(GloomhavenHelper.this.scenarioTable, 0.0f, 38.0f, 0.0f, 0.0f, true);
                textMenu0.setBackgroundOffset(-9.0f, -9.0f, 18.0f, 0.0f);
            }
        });
        this.helpAddPlayers.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
                GloomhavenHelper.this.mainMenuButton.toggle();
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
                GloomhavenHelper.this.mainMenu.addCharacters(true);
            }
        });
        this.helpSetScenario.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
                GloomhavenHelper.this.mainMenuButton.toggle();
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
                GloomhavenHelper.this.mainMenu.setScenario(true);
            }
        });
        this.helpAddMonsters.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(true);
                GloomhavenHelper.this.mainMenuButton.toggle();
                GloomhavenHelper.this.mainMenuButton.setProgrammaticChangeEvents(false);
                GloomhavenHelper.this.mainMenu.addMonsters(true);
            }
        });
    }

    public MonsterRow getMonsterRow(MonsterData monsterData0) {
        for(Object object0: this.monsterRows) {
            MonsterRow monsterRow0 = (MonsterRow)object0;
            if(monsterRow0.data == monsterData0) {
                return monsterRow0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public MonsterRow getMonsterRow(String s) {
        for(Object object0: this.monsterRows) {
            MonsterRow monsterRow0 = (MonsterRow)object0;
            if(monsterRow0.data.name.equals(s)) {
                return monsterRow0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public Player getPlayer(String s) {
        for(Object object0: this.playerRows) {
            PlayerRow playerRow0 = (PlayerRow)object0;
            if(playerRow0.player.name.equals(s)) {
                return playerRow0.player;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public boolean hasCharacterClass(CharacterClass characterClass0) {
        for(Object object0: this.playerRows) {
            if(((PlayerRow)object0).player.characterClass == characterClass0) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public boolean hasCharacters() {
        for(Object object0: this.playerRows) {
            if(((PlayerRow)object0).player.characterClass != CharacterClass.Escort && ((PlayerRow)object0).player.characterClass != CharacterClass.Objective) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private boolean isFirst(SnapshotArray snapshotArray0, int v, Row row0) {
        if(v == 0) {
            return true;
        }
        Row row1 = (Row)snapshotArray0.get(v - 1);
        if(row1 == App.dragRow) {
            if(v == 1) {
                return true;
            }
            row1 = (Row)snapshotArray0.get(v - 2);
        }
        return row1.finalX != row0.finalX;
    }

    private boolean isLast(SnapshotArray snapshotArray0, int v, Row row0) {
        int v1 = snapshotArray0.size;
        if(v == v1 - 1) {
            return true;
        }
        Row row1 = (Row)snapshotArray0.get(v + 1);
        if(row1 == App.dragRow) {
            if(v == v1 - 2) {
                return true;
            }
            row1 = (Row)snapshotArray0.get(v + 2);
        }
        return row1.finalX != row0.finalX;
    }

    private void layoutUI() {
        this.elementsTable.defaults().space(5.0f);
        this.elementsTable.add(this.fireButton);
        this.elementsTable.add(this.iceButton);
        this.elementsTable.add(this.airButton);
        this.elementsTable.add(this.earthButton);
        this.elementsTable.add(this.lightButton);
        this.elementsTable.add(this.darkButton);
        this.scenarioTable.add(this.scenarioLabel).colspan(8).padBottom(10.0f).fill().row();
        this.scenarioTable.add(this.scenarioLevelLabel).padRight(10.0f);
        this.scenarioTable.add(App.image("psd/level", App.buttonGray)).padRight(35.0f);
        this.scenarioTable.add(this.trapsLabel).padRight(9.0f);
        this.scenarioTable.add(App.image("psd/traps", App.buttonGray)).padRight(34.0f);
        this.scenarioTable.add(this.xpLabel).padRight(10.0f);
        this.scenarioTable.add(App.image("psd/xp", App.buttonGray)).padRight(34.0f);
        this.scenarioTable.add(this.coinsLabel).padRight(10.0f);
        this.scenarioTable.add(App.image("psd/coins", App.buttonGray));
        Table table0 = new Table();
        table0.padBottom(3.0f);
        table0.add(this.scenarioTable);
        this.networkCell = table0.add().bottom();
        Table table1 = new Table();
        table1.setBackground(App.skin.getDrawable("psd/bar-top"));
        table1.pad(9.0f, 9.0f, 21.0f, 9.0f).defaults().space(15.0f);
        table1.add(this.mainMenuButton).size(102.0f);
        this.helpCell = table1.add().expandX().padLeft(-9.0f);
        table1.add(this.elementsTable);
        Group group0 = new Group();
        group0.addActor(this.attackImage2);
        com.esotericsoftware.gloomhavenhelper.GloomhavenHelper.10 gloomhavenHelper$100 = new Table() {
            float desat;

            @Override  // com.badlogic.gdx.scenes.scene2d.Group
            public void act(float f) {
                super.act(f);
                this.desat = App.animate(this.desat, (GloomhavenHelper.this.monsterRows.size == 0 || App.state.canDraw ? 1.0f : 0.0f), 0.5f, 1.0f, 2.0f, f);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.Table
            public void draw(Batch batch0, float f) {
                if(this.desat != 0.0f) {
                    batch0.setShader(App.desatShader);
                    App.desatShader.setUniformf("u_desat", this.desat * 0.9f);
                }
                super.draw(batch0, f);
                if(this.desat != 0.0f) {
                    batch0.setShader(null);
                }
            }
        };
        gloomhavenHelper$100.defaults().space(20.0f);
        gloomhavenHelper$100.add(new Stack(new Actor[]{this.attackButton, new Container(this.attackLabel).padRight(4.0f).padBottom(-3.0f).bottom().right()})).size(158.0f, 107.0f);
        gloomhavenHelper$100.add(this.attackImage1).size(158.0f, 107.0f);
        gloomhavenHelper$100.add(group0).width(38.0f);
        Table table2 = new Table();
        table2.setBackground(App.skin.getDrawable("psd/bar-bottom"));
        table2.pad(20.0f, 9.0f, 9.0f, 9.0f).defaults().space(20.0f);
        table2.add(this.nextButtonStack).size(127.0f, 118.0f).pad(0.0f, (App.config.language.equals("pl") || App.config.language.equals("cz") || App.config.language.equals("it") || App.config.language.equals("es") ? -2.0f : ((float)(App.config.language.equals("hu") ? 3 : -9))), -9.0f, 0.0f);
        table2.add(table0).expand().padLeft(-17.0f);
        table2.add(gloomhavenHelper$100);
        this.attackImage2.setRotation(-31.5f);
        this.attackImage2.setBounds(-82.0f, -29.0f, 158.0f, 107.0f);
        group0.toBack();
        App.root.setFillParent(true);
        App.root.defaults().growX();
        App.root.add(table1).padBottom(-12.0f).row();
        App.root.add(this.rowsScroll).grow().row();
        App.root.add(table2).height(131.0f).padTop(-11.0f);
        table1.toFront();
    }

    public boolean nearest() {
        return App.viewport.getScreenWidth() >= 0x459 && App.viewport.getMinWorldHeight() == ((float)App.viewport.getScreenHeight());
    }

    void nextRound() {
        if(App.state.round >= 3 || App.config.language.equals("es")) {
            App.game.showAd();
        }
        if(App.state.needsShuffle) {
            App.state.shuffleAttackModifiers(true);
            this.attackImage1.setDrawable(null);
            this.attackImage2.setDrawable(null);
        }
        this.fireButton.endOfRound();
        this.iceButton.endOfRound();
        this.airButton.endOfRound();
        this.earthButton.endOfRound();
        this.lightButton.endOfRound();
        this.darkButton.endOfRound();
        if(App.state.scenarioNumber == 0x1F) {
            this.lightButton.setState(ElementState.inert, true);
            this.darkButton.setState(ElementState.strong, true);
        }
        for(Object object0: this.monsterRows) {
            ((MonsterRow)object0).turnEnd(true);
            ((MonsterRow)object0).roundEnd();
        }
        for(Object object1: this.playerRows) {
            ((PlayerRow)object1).turnEnd(true);
            ((PlayerRow)object1).roundEnd();
        }
        ++App.state.round;
        App.state.canDraw = true;
        this.sortByInitiative(0.5f);
        App.state.changed();
        this.rowsScroll.setScrollY(0.0f);
    }

    public int playerCount() {
        int v = 0;
        for(Object object0: this.playerRows) {
            if(((PlayerRow)object0).player.characterClass != CharacterClass.Escort && ((PlayerRow)object0).player.characterClass != CharacterClass.Objective) {
                ++v;
            }
        }
        return v;
    }

    boolean playerInitiativeSet() {
        for(Object object0: this.playerRows) {
            if(((PlayerRow)object0).player.init == 0 && ((PlayerRow)object0).isAlive()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override  // com.badlogic.gdx.ApplicationAdapter
    public void render() {
        boolean z4;
        if(Gdx.input.isKeyPressed(19) || this.zoomIn) {
            Gdx.graphics.requestRendering();
            if(System.currentTimeMillis() - this.lastZoomTime > 16L) {
                this.lastZoomTime = System.currentTimeMillis();
                this.zoomIn();
            }
        }
        else if(Gdx.input.isKeyPressed(20) || this.zoomOut) {
            Gdx.graphics.requestRendering();
            if(System.currentTimeMillis() - this.lastZoomTime > 16L) {
                this.lastZoomTime = System.currentTimeMillis();
                this.zoomOut();
            }
        }
        TooltipManager tooltipManager0 = TooltipManager.getInstance();
        tooltipManager0.enabled = App.config.help;
        float f = Math.min(Gdx.graphics.getDeltaTime(), 0.033333f);
        this.intro.update(f);
        boolean z = false;
        boolean z1 = this.playerCount() > 0;
        boolean z2 = this.monsterRows.size > 0;
        boolean z3 = this.turnsFinished();
        if(App.state.trackStandees) {
            z4 = false;
            for(Object object0: this.monsterRows) {
                if(((MonsterRow)object0).boxes.size > 0) {
                    z4 = true;
                    break;
                }
            }
        }
        else {
            z4 = true;
        }
        boolean z5 = App.state.canDraw;
        if(this.scenarioLevelLabel.setText(App.state.scenarioLevel) || this.solo != App.state.solo || this.scenarioNumber != App.state.scenarioNumber) {
            this.scenarioNumber = App.state.scenarioNumber;
            this.solo = App.state.solo;
            int v = App.state.scenarioLevel;
            this.trapsLabel.setText(v + 2 + (!App.state.solo || App.state.jotl ? 0 : 1));
            this.coinsLabel.setText("x" + (v == 7 ? 6 : v / 2 + 2));
            if(!App.state.jotl || App.state.scenarioNumber >= 3) {
                this.xpLabel.setText("+" + (v * 2 + 4));
            }
            else {
                this.xpLabel.setText("+0");
            }
            if(App.state.scenarioNumber <= 0) {
                this.scenarioLabel.setText("");
            }
            else if(App.state.jotl) {
                this.scenarioLabel.setText(((Scenario)App.jotlScenarios.get(App.state.scenarioNumber - 1)).name);
            }
            else {
                this.scenarioLabel.setText(((Scenario)App.scenarios.get(App.state.scenarioNumber - 1)).name);
            }
        }
        this.roundLabel.setText(App.state.round);
        this.attackLabel.setText(App.state.attackModifiers.size);
        boolean z6 = this.drawButton.hasParent();
        if(!z2) {
            this.drawButton.remove();
            this.roundButton.remove();
        }
        else if(z5) {
            this.nextButtonStack.addActorAt(0, this.drawButton);
            this.roundButton.remove();
        }
        else {
            this.nextButtonStack.addActorAt(0, this.roundButton);
            this.drawButton.remove();
        }
        boolean z7 = this.drawButton.hasParent();
        if(!z6 && z7) {
            this.drawTask.cancel();
            Timer.schedule(this.drawTask, 60.0f, 60.0f);
            this.drawMinutes = 0;
        }
        else if(z6 && !z7) {
            this.drawTask.cancel();
        }
        this.attackButton.setVisible(z2);
        this.attackImage1.setVisible(z2);
        this.attackImage2.setVisible(z2);
        this.attackLabel.setVisible(z2);
        this.roundLabel.setVisible(z2);
        this.elementsTable.setVisible(z2);
        Table table0 = this.scenarioTable;
        if(App.state.scenarioNumber >= 0) {
            z = true;
        }
        table0.setVisible(z);
        this.helpCell.width(Value.prefWidth);
        if(!z2 && !z1) {
            this.helpCell.setActor(this.helpAddPlayers).left();
        }
        else if(!z2) {
            this.helpCell.setActor(this.helpSetScenarioOrAddMonsters).left().width(0.0f);
        }
        else if(!App.config.help) {
            this.helpCell.setActor(null);
        }
        else if(!z5) {
            if(z3 && App.state.round < 2) {
                this.helpCell.setActor(this.helpNextRound1).center();
            }
            else if(z3) {
                this.helpCell.setActor(this.helpNextRound2).center();
            }
            else {
                switch(App.state.round) {
                    case 1: {
                        this.helpCell.setActor(this.helpTakeTurns1).center().width(0.0f);
                        break;
                    }
                    case 2: {
                        this.helpCell.setActor(this.helpTakeTurns2).center();
                        break;
                    }
                    default: {
                        this.helpCell.setActor(this.helpTakeTurns3).center();
                    }
                }
            }
        }
        else if(App.state.round == 1 && !z4) {
            this.helpCell.setActor(this.helpSpawnMonsters).center();
        }
        else {
            switch(App.state.round) {
                case 1: {
                    this.helpCell.setActor(this.helpChooseCards1).center().width(0.0f);
                    break;
                }
                case 2: {
                    this.helpCell.setActor(this.helpChooseCards2).center();
                    break;
                }
                default: {
                    this.helpCell.setActor(this.helpChooseCards3).center();
                }
            }
        }
        if(App.config.client) {
        }
        else if(App.config.server) {
        }
        this.networkCell.setActor(null);
        App.rippleSkeletonPool.freeComplete();
        this.toasts.toFront();
        Gdx.gl.glClear(0x4000);
        App.stage.act(f);
        App.stage.draw();
    }

    @Override  // com.badlogic.gdx.ApplicationAdapter
    public void resize(int v, int v1) {
        Gdx.graphics.requestRendering();
        if(v != 0 && v1 != 0) {
            if(App.config.zoomReset) {
                App.viewport.setMinWorldHeight(((float)Math.max(Gdx.graphics.getHeight(), 800)));
            }
            this.updateViewport();
            this.updateTextures();
        }
    }

    public void setScenario(int v, int v1, boolean z, boolean z1, boolean z2) {
        if(v1 > 7) {
            v1 = 7;
        }
        for(Object object0: this.monsterRows) {
            ((MonsterRow)object0).remove();
        }
        this.monsterRows.clear();
        PlayerRow.localInit.clear(51);
        ArrayIterator array$ArrayIterator1 = this.playerRows.iterator();
        while(array$ArrayIterator1.hasNext()) {
            Object object1 = array$ArrayIterator1.next();
            PlayerRow playerRow0 = (PlayerRow)object1;
            if(playerRow0.player.characterClass != CharacterClass.Escort && playerRow0.player.characterClass != CharacterClass.Objective) {
                playerRow0.boxes.clear();
                playerRow0.monstersGroup.clearChildren();
                playerRow0.player.init(0);
                playerRow0.turnComplete = false;
                if(playerRow0.player.characterClass != CharacterClass.BeastTyrant) {
                    continue;
                }
                MonsterBox monsterBox0 = playerRow0.addMonsterBox(1, App.summonData, playerRow0.player.level, false, false, SummonColor.beast, false);
                int v2 = playerRow0.player.level * 2 + 8;
                monsterBox0.monster.hpMax = v2;
                monsterBox0.monster.hp = v2;
                monsterBox0.monster.summonMove = 3;
                monsterBox0.monster.summonAttack = 2;
                monsterBox0.monster.isNew = false;
            }
            else {
                playerRow0.remove();
                array$ArrayIterator1.remove();
            }
        }
        if(v > 0) {
            Scenario scenario0 = z2 ? ((Scenario)App.jotlScenarios.get(v - 1)) : ((Scenario)App.scenarios.get(v - 1));
            if(z1) {
                for(Object object2: scenario0.monsters) {
                    MonsterData monsterData0 = (MonsterData)object2;
                    int v3 = Math.min(7, v1 + z);
                    for(Object object3: scenario0.special) {
                        if(((String)object3).equals("Living Corpse: +2 levels") && monsterData0.english.equals("Living Corpse")) {
                            v3 = Math.min(7, v3 + 2);
                        }
                        if(((String)object3).equals("Elite Stone Golem: +1 level, HPxC") && monsterData0.english.equals("Stone Golem")) {
                            v3 = Math.min(7, v3 + 1);
                        }
                        if(((String)object3).equals("Harrower Infester: +1 level") && monsterData0.english.equals("Harrower Infester")) {
                            v3 = Math.min(7, v3 + 1);
                        }
                        if(((String)object3).equals("City Guard: +1 level") && monsterData0.english.equals("City Guard")) {
                            v3 = Math.min(7, v3 + 1);
                        }
                    }
                    this.addMonsterRow(new MonsterRow(monsterData0, v3));
                }
            }
            this.addSpecials(scenario0.special, v1);
        }
        this.scenarioLevelLabel.setText("");
        this.attackImage1.setDrawable(null);
        this.attackImage2.setDrawable(null);
        for(Object object4: this.playerRows) {
            ((PlayerRow)object4).player.hp = ((PlayerRow)object4).player.hpMax;
            ((PlayerRow)object4).player.xp = 0;
            ((PlayerRow)object4).player.loot = 0;
            ((PlayerRow)object4).player.conditions.clear();
            ((PlayerRow)object4).player.expiredConditions.clear();
            ((PlayerRow)object4).player.currentTurnConditions.clear();
        }
        App.state.abilityDecks.clear(51);
        App.state.removedAbilities.clear();
        App.state.badOmen = 0;
        App.state.round = 1;
        App.state.scenarioNumber = v;
        App.state.scenarioLevel = v1;
        App.state.solo = z;
        App.state.jotl = z2;
        App.state.canDraw = true;
        App.state.needsShuffle = false;
        App.state.attackModifiers.clear();
        App.state.shuffleAttackModifiers(false);
        App.state.fire = ElementState.inert;
        App.state.ice = ElementState.inert;
        App.state.air = ElementState.inert;
        App.state.earth = ElementState.inert;
        App.state.light = ElementState.inert;
        App.state.dark = ElementState.inert;
        App.state.apply(false);
        App.state.changed();
    }

    public void setScenarioLevel(int v, boolean z, boolean z1) {
        Scenario scenario0;
        if(App.state.scenarioNumber <= 0) {
            scenario0 = null;
        }
        else if(z1) {
            scenario0 = (Scenario)App.jotlScenarios.get(this.scenarioNumber - 1);
        }
        else {
            scenario0 = (Scenario)App.scenarios.get(this.scenarioNumber - 1);
        }
        App.state.scenarioLevel = v;
        App.state.solo = (int)z;
        for(Object object0: this.monsterRows) {
            MonsterRow monsterRow0 = (MonsterRow)object0;
            int v1 = Math.min(7, v + ((int)z));
            if(scenario0 != null) {
                for(Object object1: scenario0.monsters) {
                    MonsterData monsterData0 = (MonsterData)object1;
                    if(monsterRow0.data == monsterData0) {
                        for(Object object2: scenario0.special) {
                            if(((String)object2).equals("Living Corpse: +2 levels") && monsterData0.english.equals("Living Corpse")) {
                                v1 = Math.min(7, v1 + 2);
                            }
                            if(((String)object2).equals("Elite Stone Golem: +1 level, HPxC") && monsterData0.english.equals("Stone Golem")) {
                                v1 = Math.min(7, v1 + 1);
                            }
                            if(((String)object2).equals("Harrower Infester: +1 level") && monsterData0.english.equals("Harrower Infester")) {
                                v1 = Math.min(7, v1 + 1);
                            }
                            if(((String)object2).equals("City Guard: +1 level") && monsterData0.english.equals("City Guard")) {
                                v1 = Math.min(7, v1 + 1);
                            }
                        }
                    }
                }
            }
            monsterRow0.setLevel(v1);
        }
    }

    public void sortByInitiative(float f) {
        this.rows.getChildren().sort();
        Animator.storeChildren(this.rows, f);
        this.roundButton.setDisabled(true);
        this.drawButton.setDisabled(true);
        this.rows.addAction(Actions.sequence(Actions.delay(f + 0.5f), new Action() {
            @Override  // com.badlogic.gdx.scenes.scene2d.Action
            public boolean act(float f) {
                GloomhavenHelper.this.roundButton.setDisabled(false);
                GloomhavenHelper.this.drawButton.setDisabled(false);
                return true;
            }
        }));
    }

    boolean turnsFinished() {
        for(Object object0: this.monsterRows) {
            if((((MonsterRow)object0).isAlive() || !App.state.trackStandees) && !((MonsterRow)object0).turnComplete) {
                return false;
            }
            if(false) {
                break;
            }
        }
        for(Object object1: this.playerRows) {
            if(!((PlayerRow)object1).turnComplete && ((PlayerRow)object1).isAlive()) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public void updateDrag(float f, float f1) {
        if(!App.dragging) {
            return;
        }
        this.rows.stageToLocalCoordinates(App.v2.set(f, f1));
        float f2 = App.v2.x;
        float f3 = App.v2.y;
        if(App.lastSwapRow != null && App.lastSwapRow.dragStartActor != null && (f3 < App.lastSwapRow.finalY + App.lastSwapRow.dragStartActor.getY() || f3 > App.lastSwapRow.finalY + App.lastSwapRow.getHeight() - (App.lastSwapRow.getHeight() - App.lastSwapRow.dragStartActor.getTop()) || Math.abs(f2 - App.lastSwapRow.finalX) > 550.0f)) {
            App.lastSwapRow = null;
        }
        if(App.lastSwapRow == null) {
            SnapshotArray snapshotArray0 = this.rows.getChildren();
            int v = App.dragRow.getZIndex();
            int v1 = snapshotArray0.size;
            for(int v2 = 0; v2 < v1; ++v2) {
                Row row0 = (Row)snapshotArray0.get(v2);
                if(row0 != App.dragRow && (!App.config.hideMonsters || !(row0 instanceof MonsterRow))) {
                    float f4 = row0.finalX;
                    float f5 = row0.finalX + row0.getWidth() * 0.8f;
                    if(this.rows.columns == 1 || f2 >= f4 && f2 < f5) {
                        float f6 = row0.finalY;
                        float f7 = row0.finalY + row0.getHeight();
                        if(v2 == v - 1) {
                            f6 += row0.dragStartActor.getY();
                        }
                        if(v2 == v + 1) {
                            f7 -= row0.getHeight() - row0.dragStartActor.getTop();
                        }
                        boolean z = f3 > f6 && f3 < f7;
                        if(z) {
                            App.lastSwapRow = row0;
                        }
                        else if(this.isLast(snapshotArray0, v2, row0) && f3 < f6) {
                            ++v2;
                            z = true;
                        }
                        else if(this.isFirst(snapshotArray0, v2, row0) && f3 > f7) {
                            v2 = Math.max(0, v2 - 1);
                            z = true;
                        }
                        if(z) {
                            App.dragRow.setZIndex(v2);
                            Animator.storeChildren(this.rows, 0.0f);
                            App.dragRow.getAnimator().finish(false);
                            break;
                        }
                    }
                }
            }
        }
        if(this.rows.columns == 1) {
            App.dragRow.setPosition(App.dragRow.finalX, f3 - App.dragStart.y);
            return;
        }
        App.dragRow.setPosition(f2 - App.dragStart.x, f3 - App.dragStart.y);
    }

    public void updateHelpText() {
        if(App.state.playerInit == PlayerInit.dragOrder) {
            this.helpChooseCards1.setText("Choose character ability cards,\nthen click Draw.");
            this.helpChooseCards2.setText("Choose character ability cards.");
            this.helpTakeTurns1.setText("Drag portraits for init order.\nTake turns, click each portrait.");
            this.helpTakeTurns2.setText("Drag portraits, take turns.");
            return;
        }
        if(App.state.playerInit == PlayerInit.numpad) {
            this.helpChooseCards1.setText("Choose character ability cards,\nclick portraits, draw.");
            this.helpChooseCards2.setText("Choose character ability cards,\nset initiatives, draw.");
            this.helpTakeTurns1.setText("Take turns, click each portrait.");
            this.helpTakeTurns2.setText("Take turns, click each portrait.");
            return;
        }
        this.helpChooseCards1.setText("Choose character ability cards,\ndrag portraits right, draw.");
        this.helpChooseCards2.setText("Choose character ability cards,\nset initiatives, draw.");
        this.helpTakeTurns1.setText("Take turns, click each portrait.");
        this.helpTakeTurns2.setText("Take turns, click each portrait.");
    }

    public void updateTextures() {
        TextureFilter texture$TextureFilter0 = this.nearest() ? TextureFilter.Nearest : TextureFilter.Linear;
        for(Object object0: App.textures) {
            ((Texture)object0).setFilter(texture$TextureFilter0, texture$TextureFilter0);
        }
        if(this.intro.introPhase >= 0 && this.intro.introPhase <= 4) {
            App.atlas.findRegion("esoteric/logo").getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
        }
    }

    public void updateViewport() {
        Insets game$Insets0 = App.game.getInsets();
        int v = (int)game$Insets0.top;
        int v1 = (int)game$Insets0.left;
        int v2 = (int)game$Insets0.bottom;
        int v3 = (int)game$Insets0.right;
        App.viewport.update(Gdx.graphics.getWidth() - v1 - v3, Gdx.graphics.getHeight() - v - v2, true);
        App.viewport.setScreenX(App.viewport.getScreenX() + v1);
        App.viewport.setScreenY(App.viewport.getScreenY() + v2);
        HdpiUtils.glViewport(App.viewport.getScreenX(), App.viewport.getScreenY(), App.viewport.getScreenWidth(), App.viewport.getScreenHeight());
    }

    public void zoomIn() {
        float f = Math.max(Math.max(700.0f, App.viewport.getMinWorldHeight() - 5.0f), Gdx.graphics.getHeight() * 0x459 / Gdx.graphics.getWidth());
        App.viewport.setMinWorldHeight(f);
        this.updateViewport();
        this.updateTextures();
        Gdx.graphics.requestRendering();
        App.config.zoom = f;
        App.config.zoomReset = false;
        App.game.saveConfig();
    }

    public void zoomOut() {
        float f = Math.max(App.viewport.getMinWorldHeight() + 5.0f, Gdx.graphics.getHeight() * 0x459 / Gdx.graphics.getWidth());
        App.viewport.setMinWorldHeight(f);
        this.updateViewport();
        this.updateTextures();
        Gdx.graphics.requestRendering();
        App.config.zoom = f;
        App.config.zoomReset = false;
        App.game.saveConfig();
    }
}

