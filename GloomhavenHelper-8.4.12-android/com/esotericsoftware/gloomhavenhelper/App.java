package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip.TextTooltipStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap.Entry;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue.PrettyPrintSettings;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.Condition;
import com.esotericsoftware.gloomhavenhelper.model.Line;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.model.MonsterData;
import com.esotericsoftware.gloomhavenhelper.model.MonsterStats;
import com.esotericsoftware.gloomhavenhelper.model.MonsterType;
import com.esotericsoftware.gloomhavenhelper.model.Scenario;
import com.esotericsoftware.gloomhavenhelper.model.SummonColor;
import com.esotericsoftware.gloomhavenhelper.util.CompositeDrawable;
import com.esotericsoftware.gloomhavenhelper.util.FontWrapping.Japanese;
import com.esotericsoftware.gloomhavenhelper.util.FontWrapping.Korean;
import com.esotericsoftware.gloomhavenhelper.util.Menu;
import com.esotericsoftware.gloomhavenhelper.util.Row;
import com.esotericsoftware.gloomhavenhelper.util.Shaders;
import com.esotericsoftware.gloomhavenhelper.util.builders.CheckBoxBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.ImageTextButtonBuilder;
import com.esotericsoftware.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.BoneData;
import com.esotericsoftware.spine.SkeletonBinary;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.utils.SkeletonActorPool;

public class App {
    static int animateNumber = 0;
    public static String[] args = null;
    public static TextureAtlas atlas = null;
    public static final Color bossRed = null;
    public static final Color buttonGray = null;
    public static final Color c = null;
    public static GameConfig config = null;
    public static final Color darken = null;
    public static ShaderProgram desatShader = null;
    public static final Color disabledDim = null;
    public static final Color disabledGray = null;
    public static Row dragRow = null;
    public static final Vector2 dragStart = null;
    public static boolean dragging = false;
    public static final boolean eclipse = false;
    public static SkeletonData elementsSkeletonData = null;
    public static final Color eliteGold = null;
    public static final OrderedMap englishToMonsterData = null;
    public static SkeletonData esotericSkeletonData = null;
    public static BitmapFont fancyExtraLargeOutlineNumbers = null;
    public static BitmapFont fancyLargeOutline = null;
    public static BitmapFont fancyLargeOutlineFixedNumbers = null;
    public static Game game = null;
    public static Object gameThread = null;
    public static GloomhavenHelper gloom = null;
    public static final Color gray = null;
    public static final Color healthGreen = null;
    public static final Color healthRed = null;
    public static final Array jotlScenarios = null;
    public static Row lastSwapRow = null;
    public static final Color lightGray = null;
    public static final Color lootGold = null;
    public static final String majorMinor = null;
    public static final Array monsterAbilities = null;
    public static final IntMap monsterAbilityDecks = null;
    public static final Array monsterDatas = null;
    public static final float monsterStatsDim = 0.4f;
    public static final OrderedMap nameToMonsterData = null;
    public static BitmapFont plainExtraLargeNumbers = null;
    public static BitmapFont plainExtraLargeOutlineNumbers = null;
    public static BitmapFont plainLargeFixedNumbers = null;
    public static BitmapFont plainLargeOutline = null;
    public static BitmapFont plainLargeOutlineFixedNumbers = null;
    public static BitmapFont plainMedium = null;
    public static BitmapFont plainMediumOutline = null;
    public static BitmapFont plainMediumOutlineFixedNumbers = null;
    public static BitmapFont plainSmall = null;
    public static BitmapFont plainSmallOutline = null;
    static SkeletonActorPool rippleSkeletonPool = null;
    public static Table root = null;
    public static final Array scenarios = null;
    public static final Array sections = null;
    public static SkeletonRenderer skeletonRenderer = null;
    public static Skin skin = null;
    public static Stage stage = null;
    public static GameState state = null;
    public static MonsterData summonData = null;
    public static MonsterStats summonStats = null;
    public static final String tag = "ghh";
    public static final Color textSelect = null;
    public static final Array textures = null;
    public static final Color tooltips = null;
    public static final Vector2 v2 = null;
    public static final Vector3 v3 = null;
    public static final String version = "8.4.12";
    public static ExtendViewport viewport;
    public static final Color xpBlue;

    static {
        App.majorMinor = "8.4";
        App.monsterAbilityDecks = new IntMap(0x40);
        App.nameToMonsterData = new OrderedMap(0x40);
        App.englishToMonsterData = new OrderedMap(0x40);
        App.monsterDatas = new Array(0x40);
        App.monsterAbilities = new Array(0x200);
        App.scenarios = new Array(100);
        App.jotlScenarios = new Array(100);
        App.sections = new Array(100);
        App.v2 = new Vector2();
        App.v3 = new Vector3();
        App.c = new Color();
        App.buttonGray = new Color(0x8C8C8CFF);
        App.disabledGray = new Color(0x4C4C4CFF);
        App.lightGray = new Color(0xC5C5C5FF);
        App.gray = new Color(0x6C6C6CFF);
        App.disabledDim = new Color(1.0f, 1.0f, 1.0f, 0.4f);
        App.healthGreen = new Color(0x7FFF00FF);
        App.healthRed = new Color(-164416001);
        App.bossRed = new Color(-164416001);
        App.eliteGold = new Color(0xFFB717FF);
        App.xpBlue = new Color(0x2C85A0FF);
        App.lootGold = new Color(0xC68F1FFF);
        App.darken = new Color(-370873665);
        App.textSelect = new Color(0x248BFF87);
        App.tooltips = new Color(0.0f, 0.0f, 0.0f, 0.75f);
        App.dragStart = new Vector2();
        App.textures = new Array();
    }

    public static float animate(float f, float f1, float f2, float f3, float f4, float f5) {
        if(Float.isNaN(f1)) {
            f1 = 0.0f;
        }
        if(f == f1) {
            return f;
        }
        Gdx.graphics.requestRendering();
        float f6 = Interpolation.slowFast.apply(f2, f3, MathUtils.clamp(Math.abs(f1 - f) / f4, 0.0f, 1.0f)) * f5;
        return f < f1 ? Math.min(f1, f + f6) : Math.max(f1, f - f6);
    }

    public static void animate(Vector2 vector20, Vector2 vector21, float f, float f1, float f2, float f3) {
        if(Float.isNaN(vector21.x)) {
            vector21.x = 0.0f;
        }
        if(Float.isNaN(vector21.y)) {
            vector21.y = 0.0f;
        }
        if(vector20.x == vector21.x && vector20.y == vector21.y) {
            return;
        }
        float f4 = vector21.x - vector20.x;
        float f5 = vector21.y - vector20.y;
        float f6 = f4 == 0.0f || f5 == 0.0f ? 1.0f : f5 / f4;
        float f7 = Interpolation.slowFast.apply(f, f1, MathUtils.clamp(Math.abs(f4 / f6) / f2, 0.0f, 1.0f)) * f3;
        float f8 = Interpolation.slowFast.apply(f, f1, MathUtils.clamp(Math.abs(f6 * f5) / f2, 0.0f, 1.0f)) * f3;
        vector20.x = f4 > 0.0f ? Math.min(vector21.x, vector20.x + f7) : Math.max(vector21.x, vector20.x - f7);
        vector20.y = f5 > 0.0f ? Math.min(vector21.y, vector20.y + f8) : Math.max(vector21.y, vector20.y - f8);
        Gdx.graphics.requestRendering();
    }

    public static void animateAttackCard(AttackModifier attackModifier0, Button button0, Image image0, Image image1) {
        Image image2 = new Image(App.skin.newDrawable("white", new Color(0.0f, 0.0f, 0.0f, 0.4f)));
        image2.setFillParent(true);
        App.stage.addActor(image2);
        image2.getColor().a = 0.0f;
        image2.addAction(Actions.fadeIn(0.3f, Interpolation.fastSlow));
        Image image3 = new Image(App.drawable(new String[]{"attack/back", "attack/border"}));
        Vector2 vector20 = button0.localToStageCoordinates(new Vector2(-139.5f, -94.0f));
        image3.setScale(0.361556f);
        image3.setOrigin(218.5f, 148.0f);
        image3.setPosition(((float)Math.round(vector20.x)), ((float)Math.round(vector20.y)));
        App.stage.addActor(image3);
        Vector2 vector21 = image0.localToStageCoordinates(new Vector2(-139.5f, -94.0f));
        Drawable drawable0 = App.drawable(new String[]{"attack/" + attackModifier0, "attack/border"});
        int v = App.animateNumber + 1;
        App.animateNumber = v;
        image3.addAction(Actions.parallel(Actions.moveTo(Math.round(App.viewport.getWorldWidth() / 2.0f - 218.5f), Math.round(App.viewport.getWorldHeight() / 2.0f - 148.0f), 0.55f, Interpolation.fastSlow), Actions.rotateTo(180.0f, 0.6f, Interpolation.fastSlow), Actions.scaleTo(1.75f, 1.75f, 0.3f, Interpolation.fastSlow), Actions.sequence(new Action[]{Actions.delay(0.3f), Actions.scaleTo(1.25f, 0.0f, 0.15f, Interpolation.slowFast), Actions.parallel(Actions.scaleTo(1.0f, 1.0f, 0.15f, Interpolation.fastSlow), new Action() {
            @Override  // com.badlogic.gdx.scenes.scene2d.Action
            public boolean act(float f) {
                com.esotericsoftware.gloomhavenhelper.App.4.1 app$4$10 = new CompositeDrawable(new Drawable[]{drawable0}) {
                    @Override  // com.esotericsoftware.gloomhavenhelper.util.CompositeDrawable
                    public void draw(Batch batch0, float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8) {
                        super.draw(batch0, f, f1, f2, f3, f4, f5, f6, f7, f8 + 180.0f);
                    }
                };
                image3.setDrawable(app$4$10);
                return true;
            }
        }), Actions.delay(0.2f), new Action() {
            @Override  // com.badlogic.gdx.scenes.scene2d.Action
            public boolean act(float f) {
                if(App.animateNumber != v) {
                    image2.remove();
                    image3.remove();
                    return true;
                }
                image2.setTouchable(Touchable.disabled);
                SequenceAction sequenceAction0 = Actions.sequence(Actions.fadeOut(0.4f, Interpolation.slowFast), Actions.removeActor());
                image2.addAction(sequenceAction0);
                Group group0 = image0.getParent();
                Vector2 vector20 = new Vector2(0.0f, 0.0f);
                Vector2 vector21 = image1.localToAscendantCoordinates(group0, vector20);
                image0.clearActions();
                SequenceAction sequenceAction1 = Actions.sequence(Actions.parallel(Actions.moveTo(vector21.x, vector21.y, 0.4f, Interpolation.fastSlow), Actions.rotateTo(-31.5f, 0.4f, Interpolation.fastSlow)), new Action() {
                    @Override  // com.badlogic.gdx.scenes.scene2d.Action
                    public boolean act(float f) {
                        Drawable drawable0 = com.esotericsoftware.gloomhavenhelper.App.5.this.val$attackImage1.getDrawable();
                        com.esotericsoftware.gloomhavenhelper.App.5.this.val$attackImage2.setDrawable(drawable0);
                        com.esotericsoftware.gloomhavenhelper.App.5.this.val$attackImage1.setDrawable(com.esotericsoftware.gloomhavenhelper.App.5.this.val$drawable);
                        com.esotericsoftware.gloomhavenhelper.App.5.this.val$attackImage1.setRotation(0.0f);
                        ((Table)com.esotericsoftware.gloomhavenhelper.App.5.this.val$attackImage1.getParent()).invalidate();
                        return true;
                    }
                });
                image0.addAction(sequenceAction1);
                return true;
            }
        }, Actions.parallel(Actions.moveTo(Math.round(vector21.x), Math.round(vector21.y), 0.4f, Interpolation.slowFast), Actions.scaleTo(0.361556f, 0.361556f, 0.4f, Interpolation.slowFast)), Actions.removeActor()})));
    }

    public static void animateAttackCard(AttackModifier attackModifier0, AttackModifier attackModifier1, AttackModifier attackModifier2) {
        if(attackModifier1 == null) {
            App.gloom.attackImage1.setDrawable(null);
        }
        else {
            App.gloom.attackImage1.setDrawable(App.drawable(new String[]{"attack/" + attackModifier1, "attack/border"}));
        }
        if(attackModifier2 == null) {
            App.gloom.attackImage2.setDrawable(null);
        }
        else {
            App.gloom.attackImage2.setDrawable(App.drawable(new String[]{"attack/" + attackModifier2, "attack/border"}));
        }
        App.animateAttackCard(attackModifier0, App.gloom.attackButton, App.gloom.attackImage1, App.gloom.attackImage2);
    }

    public static Color c(float f, float f1, float f2, float f3) {
        return App.c.set(f, f1, f2, f3);
    }

    public static Color c(int v, float f) {
        App.c.set(v);
        App.c.a = f;
        return App.c;
    }

    public static Color c(Color color0, float f) {
        App.c.set(color0.r, color0.g, color0.b, f);
        return App.c;
    }

    public static CheckBox checkbox(String s) {
        CheckBox checkBox0 = ((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)new CheckBoxBuilder().checkboxOff("checkbox", App.buttonGray).checkboxOver("checkbox").checkboxOn("checkbox-checked").checkboxOnOver("checkbox-checked-over").checkboxOffDisabled("checkbox", App.disabledGray).checkboxOnDisabled("checkbox-checked-over", App.disabledGray).font("fancyLargeOutline")).fontColor(App.buttonGray)).disabledFontColor(App.disabledGray)).overFontColor(Color.WHITE)).text(s)).programmaticChangeEvents(false)).tapSquareSize(28)).create();
        checkBox0.getLabelCell().padLeft(12.0f).left().expandX();
        return checkBox0;
    }

    public static Drawable drawable(String s, Color color0) {
        return App.skin.newDrawable(s, color0);
    }

    public static Drawable drawable(String[] arr_s) {
        if(arr_s.length == 1) {
            return App.skin.getDrawable(arr_s[0]);
        }
        Drawable[] arr_drawable = new Drawable[arr_s.length];
        for(int v = 0; v < arr_s.length; ++v) {
            arr_drawable[v] = App.skin.getDrawable(arr_s[v]);
        }
        return new CompositeDrawable(arr_drawable);
    }

    public static FileHandle file(String s) {
        return Gdx.files.internal(s);
    }

    public static MonsterAbilityDeck findMonsterAbilityDeck(int v) {
        MonsterAbilityDeck monsterAbilityDeck0 = (MonsterAbilityDeck)App.monsterAbilityDecks.get(v);
        if(monsterAbilityDeck0 == null) {
            throw new RuntimeException();
        }
        return monsterAbilityDeck0;
    }

    public static MonsterData findMonsterData(String s) {
        MonsterData monsterData0 = (MonsterData)App.nameToMonsterData.get(s);
        if(monsterData0 == null) {
            monsterData0 = (MonsterData)App.englishToMonsterData.get(s);
            if(monsterData0 == null) {
                throw new RuntimeException("Monster not found: " + s);
            }
        }
        return monsterData0;
    }

    public static void finishActions(Actor actor0) {
        while(actor0.getActions().size != 0) {
            actor0.act(99.0f);
        }
    }

    public static void gameThread() {
    }

    public static Image image(Drawable drawable0) {
        Image image0 = new Image(drawable0);
        image0.setScaling(Scaling.none);
        return image0;
    }

    public static Image image(String s) {
        return App.image(App.skin.getDrawable(s));
    }

    public static Image image(String s, Color color0) {
        return App.image(App.skin.newDrawable(s, color0));
    }

    public static ImageButtonBuilder imageButton() {
        return (ImageButtonBuilder)new ImageButtonBuilder().programmaticChangeEvents(false);
    }

    public static ImageTextButtonBuilder imageTextButton(String s) {
        return ((ImageTextButtonBuilder)new ImageTextButtonBuilder().programmaticChangeEvents(false)).font("fancyLargeOutline").fontColor(App.buttonGray).overFontColor(Color.WHITE).disabledFontColor(App.disabledGray).text(s);
    }

    public static void load() {
        App.monsterAbilityDecks.clear(51);
        App.nameToMonsterData.clear(51);
        App.englishToMonsterData.clear(51);
        App.monsterDatas.clear();
        App.summonData = null;
        App.summonStats = null;
        App.monsterAbilities.clear();
        App.scenarios.clear();
        App.jotlScenarios.clear();
        App.sections.clear();
        App.textures.clear();
        MonsterAbility.reset();
        MonsterAbilityDeck.reset();
        MonsterData.reset();
        Menu.menusShown = 0;
        Row.animatedHeight = true;
        PlayerRow.localInit.clear(51);
        Gdx.graphics.setContinuousRendering(false);
        TooltipManager.getInstance().initialTime = 0.8f;
        TooltipManager.getInstance().hideAll();
        App.atlas = App.loadAtlas();
        App.fancyExtraLargeOutlineNumbers = App.loadFont("fancyExtraLargeOutlineNumbers", -52, null);
        App.fancyLargeOutline = App.loadFont("fancyLargeOutline", -46, null);
        App.fancyLargeOutlineFixedNumbers = App.loadFont("fancyLargeOutline", -46, "0123456789_");
        App.plainExtraLargeNumbers = App.loadFont("plainExtraLargeNumbers", -46, null);
        App.plainExtraLargeOutlineNumbers = App.loadFont("plainExtraLargeOutlineNumbers", -46, "0123456789+-");
        App.plainLargeOutline = App.loadFont("plainLargeOutline", -35, null);
        App.plainLargeOutlineFixedNumbers = App.loadFont("plainLargeOutline", 0xFFFFFFE0, "0123456789-+/");
        App.plainLargeFixedNumbers = App.loadFont("plainLargeNumbers", -35, "0123456789-+/");
        App.plainMedium = App.loadFont("plainMedium", -27, null);
        App.plainMediumOutline = App.loadFont("plainMediumOutline", -27, null);
        App.plainMediumOutlineFixedNumbers = App.loadFont("plainMediumOutline", -27, "0123456789-+/");
        App.plainSmall = App.loadFont("plainSmall", -20, null);
        App.plainSmallOutline = App.loadFont("plainSmallOutline", -20, null);
        App.skin = new Skin();
        App.skin.add("default", App.plainLargeOutline);
        App.skin.add("fancyExtraLargeOutlineNumbers", App.fancyExtraLargeOutlineNumbers);
        App.skin.add("fancyLargeOutline", App.fancyLargeOutline);
        App.skin.add("fancyLargeOutlineFixedNumbers", App.fancyLargeOutlineFixedNumbers);
        App.skin.add("plainExtraLargeNumbers", App.plainExtraLargeNumbers);
        App.skin.add("plainExtraLargeOutlineNumbers", App.plainExtraLargeOutlineNumbers);
        App.skin.add("plainLargeOutline", App.plainLargeOutline);
        App.skin.add("plainLargeOutlineFixedNumbers", App.plainLargeOutlineFixedNumbers);
        App.skin.add("plainMediumOutline", App.plainMediumOutline);
        App.skin.add("plainMediumOutlineFixedNumbers", App.plainMediumOutlineFixedNumbers);
        App.skin.add("plainSmallOutline", App.plainSmallOutline);
        App.skin.addRegions(App.atlas);
        Condition[] arr_condition = Condition.values;
        for(int v1 = 0; v1 < arr_condition.length; ++v1) {
            Condition condition0 = arr_condition[v1];
            condition0.drawable = App.skin.getDrawable("conditions/" + condition0.name());
            if(condition0 != Condition.summonNew && condition0 != Condition.summon && condition0 != Condition.star && condition0 != Condition.invisible && condition0 != Condition.doom && condition0 != Condition.hatchet) {
                condition0.drawableMedium = App.skin.getDrawable("abilities/" + condition0.name() + "-medium");
            }
        }
        SummonColor[] arr_summonColor = SummonColor.values;
        for(int v = 0; v < arr_summonColor.length; ++v) {
            SummonColor summonColor0 = arr_summonColor[v];
            summonColor0.drawable = App.skin.getDrawable("summon/" + summonColor0.name());
        }
        App.stage = new Stage(new ScreenViewport(), new PolygonSpriteBatch()) {
            @Override  // com.badlogic.gdx.scenes.scene2d.Stage
            public boolean touchDown(int v, int v1, int v2, int v3) {
                this.setKeyboardFocus(null);
                Gdx.input.setOnscreenKeyboardVisible(false);
                return super.touchDown(v, v1, v2, v3);
            }
        };
        App.viewport = new ExtendViewport(1113.0f, 800.0f, 2147483648.0f, 2147483648.0f, App.stage.getCamera());
        App.stage.setViewport(App.viewport);
        App.skeletonRenderer = new SkeletonRenderer();
        SkeletonBinary skeletonBinary0 = new SkeletonBinary(App.atlas);
        App.elementsSkeletonData = skeletonBinary0.readSkeletonData(App.file("skeletons/elements.skel"));
        App.esotericSkeletonData = skeletonBinary0.readSkeletonData(App.file("skeletons/esoteric.skel"));
        SkeletonData skeletonData0 = skeletonBinary0.readSkeletonData(App.file("skeletons/ripple.skel"));
        ((BoneData)skeletonData0.getBones().first()).setScale(1.3f, 1.3f);
        App.rippleSkeletonPool = new SkeletonActorPool(App.skeletonRenderer, skeletonData0, new AnimationStateData(skeletonData0));
        App.desatShader = Shaders.desat();
        App.loadData();
    }

    public static TextureAtlas loadAtlas() {
        FileHandle fileHandle0 = App.file("skin.atlas");
        try {
            TextureAtlas textureAtlas0 = new TextureAtlas(fileHandle0);
            textureAtlas0.getTextures().iterator().toArray(App.textures);
            return textureAtlas0;
        }
        catch(Throwable throwable0) {
            String s = throwable0.getMessage();
            if(s != null && (s.contains("Unable to allocate memory") || s.contains("Out of memory"))) {
                App.game.oom();
            }
            throw new RuntimeException("Unable to load texture atlas.", throwable0);
        }
    }

    private static void loadData() {
        JsonValue jsonValue0 = new JsonReader().parse(App.file(("data-" + App.config.language + ".json")));
        Text.loadText(jsonValue0.get("text"));
        CharacterClass.loadText(jsonValue0.get("classes"));
        Line.loadTokens(jsonValue0.get("tokens"));
        for(JsonValue jsonValue1 = jsonValue0.getChild("monsterAbilities"); jsonValue1 != null; jsonValue1 = jsonValue1.next) {
            MonsterAbilityDeck monsterAbilityDeck0 = new MonsterAbilityDeck();
            monsterAbilityDeck0.name = jsonValue1.getString("name");
            App.monsterAbilityDecks.put(monsterAbilityDeck0.id, monsterAbilityDeck0);
            monsterAbilityDeck0.edition = Edition.valueOf(jsonValue1.getString("edition", "OG"));
            for(JsonValue jsonValue2 = jsonValue1.get("cards").child; jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                MonsterAbility monsterAbility0 = new MonsterAbility();
                monsterAbility0.deck = monsterAbilityDeck0;
                monsterAbility0.number = jsonValue2.getString(0);
                monsterAbility0.shuffle = jsonValue2.getBoolean(1);
                monsterAbility0.initiative = jsonValue2.getInt(2);
                monsterAbility0.initiativeString = Integer.toString(monsterAbility0.initiative);
                monsterAbilityDeck0.abilities.add(monsterAbility0);
                for(JsonValue jsonValue3 = jsonValue2.get(3); jsonValue3 != null; jsonValue3 = jsonValue3.next) {
                    String s = jsonValue3.asString();
                    monsterAbility0.text.add(s);
                }
                App.monsterAbilities.add(monsterAbility0);
            }
        }
        for(JsonValue jsonValue4 = jsonValue0.getChild("monsters"); jsonValue4 != null; jsonValue4 = jsonValue4.next) {
            MonsterData monsterData0 = new MonsterData();
            monsterData0.name = jsonValue4.name;
            monsterData0.english = jsonValue4.getString("english", monsterData0.name);
            monsterData0.display = jsonValue4.getString("display", monsterData0.name);
            monsterData0.count = jsonValue4.getInt("count");
            monsterData0.edition = Edition.valueOf(jsonValue4.getString("edition", "OG"));
            monsterData0.hidden = jsonValue4.getBoolean("hidden", false);
            String s1 = jsonValue4.getString("deck");
            for(Object object0: App.monsterAbilityDecks.entries()) {
                Entry intMap$Entry0 = (Entry)object0;
                if(((MonsterAbilityDeck)intMap$Entry0.value).name.equals(s1)) {
                    monsterData0.deckID = intMap$Entry0.key;
                    break;
                }
                if(false) {
                    break;
                }
            }
            if(monsterData0.deckID == -1) {
                throw new RuntimeException("Deck not found: " + s1);
            }
            monsterData0.flying = jsonValue4.getBoolean("flying", false);
            App.nameToMonsterData.put(monsterData0.name, monsterData0);
            if(!App.config.language.equals("en")) {
                App.englishToMonsterData.put(monsterData0.english, monsterData0);
            }
            App.monsterDatas.add(monsterData0);
            JsonValue jsonValue5 = jsonValue4.getChild("levels");
            for(int v = 0; jsonValue5 != null; ++v) {
                if(!jsonValue5.has("normal") || !jsonValue5.has("elite")) {
                    MonsterStats[] arr_monsterStats2 = monsterData0.stats[MonsterType.boss.ordinal()];
                    arr_monsterStats2[v] = App.readStats(jsonValue5, monsterData0, false);
                }
                else {
                    MonsterStats[] arr_monsterStats = monsterData0.stats[MonsterType.normal.ordinal()];
                    arr_monsterStats[v] = App.readStats(jsonValue5.get("normal"), monsterData0, false);
                    MonsterStats[] arr_monsterStats1 = monsterData0.stats[MonsterType.elite.ordinal()];
                    arr_monsterStats1[v] = App.readStats(jsonValue5.get("elite"), monsterData0, true);
                }
                jsonValue5 = jsonValue5.next;
            }
        }
        MonsterAbilityDeck monsterAbilityDeck1 = new MonsterAbilityDeck();
        monsterAbilityDeck1.name = "Summon";
        App.monsterAbilityDecks.put(monsterAbilityDeck1.id, monsterAbilityDeck1);
        App.summonData = new MonsterData();
        App.summonData.edition = Edition.OG;
        App.summonData.name = "Summon";
        App.summonData.english = "Summon";
        App.summonData.count = 6;
        App.summonData.deckID = monsterAbilityDeck1.id;
        App.nameToMonsterData.put(App.summonData.name, App.summonData);
        App.monsterDatas.add(App.summonData);
        App.summonStats = new MonsterStats();
        App.summonStats.hpMax = "2";
        App.summonStats.move = "2";
        App.summonStats.attack = "2";
        App.summonStats.range = "2";
        App.loadScenarios(jsonValue0.getChild("scenarios"), App.scenarios, false);
        App.loadScenarios(jsonValue0.getChild("scenarios"), App.jotlScenarios, true);
        App.loadScenarios(jsonValue0.getChild("sections"), App.sections, false);
    }

    public static BitmapFont loadFont(String s, int v, String s1) {
        BitmapFontData bitmapFont$BitmapFontData0;
        String s2 = App.config.language;
        String s3 = "fonts/" + (!s2.equals("pl") && !s2.equals("cz") && !s2.equals("de") && !s2.equals("fr") && !s2.equals("it") && !s2.equals("en") && !s2.equals("es") && !s2.equals("pt") && !s2.equals("hu") ? App.config.language : "en") + "/";
        FileHandle fileHandle0 = App.file((s3 + s + ".fnt"));
        if(App.config.language.equals("ko")) {
            bitmapFont$BitmapFontData0 = new BitmapFontData(fileHandle0, false) {
                @Override  // com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData
                public int getWrapIndex(Array array0, int v) {
                    return Korean.getWrapIndex(array0, v);
                }
            };
        }
        else if(App.config.language.equals("ja")) {
            bitmapFont$BitmapFontData0 = new BitmapFontData(fileHandle0, false) {
                @Override  // com.badlogic.gdx.graphics.g2d.BitmapFont$BitmapFontData
                public int getWrapIndex(Array array0, int v) {
                    return Japanese.getWrapIndex(array0, v);
                }
            };
        }
        else {
            bitmapFont$BitmapFontData0 = new BitmapFontData(fileHandle0, false);
        }
        BitmapFont bitmapFont0 = new BitmapFont(bitmapFont$BitmapFontData0, App.atlas.findRegion(s3 + s), true);
        bitmapFont0.setUseIntegerPositions(true);
        bitmapFont0.getData().breakChars = new char[]{'-'};
        bitmapFont0.getData().down = (float)v;
        if(s1 != null) {
            bitmapFont0.setFixedWidthGlyphs(s1);
        }
        return bitmapFont0;
    }

    private static void loadScenarios(JsonValue jsonValue0, Array array0, boolean z) {
        while(jsonValue0 != null) {
            Edition edition0 = Edition.valueOf(jsonValue0.getString("edition", "OG"));
            if(!(z ? edition0 != Edition.JotL : edition0 == Edition.JotL)) {
                Scenario scenario0 = new Scenario();
                scenario0.name = jsonValue0.name;
                scenario0.edition = edition0;
                for(JsonValue jsonValue1 = jsonValue0.getChild("monsters"); jsonValue1 != null; jsonValue1 = jsonValue1.next) {
                    MonsterData monsterData0 = App.findMonsterData(jsonValue1.asString());
                    scenario0.monsters.add(monsterData0);
                }
                for(JsonValue jsonValue2 = jsonValue0.getChild("special"); jsonValue2 != null; jsonValue2 = jsonValue2.next) {
                    String s = jsonValue2.asString();
                    scenario0.special.add(s);
                }
                array0.add(scenario0);
            }
            jsonValue0 = jsonValue0.next;
        }
    }

    public static void main(String[] arr_s) throws Exception {
        JsonValue jsonValue0 = new JsonReader().parse(new FileHandle("C:/Users/Nate/Desktop/json_data_new.json"));
        PrettyPrintSettings jsonValue$PrettyPrintSettings0 = new PrettyPrintSettings();
        jsonValue$PrettyPrintSettings0.outputType = OutputType.javascript;
        jsonValue$PrettyPrintSettings0.singleLineColumns = 130;
        jsonValue$PrettyPrintSettings0.wrapNumericArrays = false;
        new FileHandle("C:/Dev/projects/ghh/ghh-core/assets/data.json").writeString(jsonValue0.prettyPrint(jsonValue$PrettyPrintSettings0), false);
    }

    public static int parseInt(String s) {
        if(s == null) {
            throw new IllegalArgumentException("value cannot be null.");
        }
        if(s.length() == 0) {
            throw new NumberFormatException("Empty string.");
        }
        if(s.charAt(0) == 43) {
            s = s.substring(1);
        }
        return Integer.parseInt(s);
    }

    public static CheckBox radio(String s) {
        CheckBox checkBox0 = ((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)new CheckBoxBuilder().checkboxOff("radio", App.buttonGray).checkboxOver("radio").checkboxOn("radio-checked").checkboxOnOver("radio-checked-over").font("fancyLargeOutline")).fontColor(App.buttonGray)).overFontColor(Color.WHITE)).text(s)).programmaticChangeEvents(false)).tapSquareSize(28)).create();
        checkBox0.getLabelCell().padLeft(12.0f).left().expandX();
        return checkBox0;
    }

    public static AttackModifier readAttackModifier(byte b) {
        return b == 0 ? null : AttackModifier.values[b - 1];
    }

    private static MonsterStats readStats(JsonValue jsonValue0, MonsterData monsterData0, boolean z) {
        MonsterStats monsterStats0 = new MonsterStats();
        monsterStats0.hpMax = jsonValue0.getString("health");
        monsterStats0.move = jsonValue0.getString("move");
        monsterStats0.attack = jsonValue0.getString("attack");
        monsterStats0.range = jsonValue0.getString("range", "-");
        monsterStats0.notesText = jsonValue0.getString("notes", "");
        for(JsonValue jsonValue1 = jsonValue0.getChild("immunities"); jsonValue1 != null; jsonValue1 = jsonValue1.next) {
            String s = jsonValue1.asString().replaceAll("%", "");
            if(s.equals("curse")) {
                monsterStats0.immuneCurse = true;
            }
            else if(s.equals("push")) {
                monsterStats0.immunePush = true;
            }
            else if(s.equals("pull")) {
                monsterStats0.immunePull = true;
            }
            else {
                monsterStats0.immunities.add(Condition.valueOf(s));
            }
        }
        for(JsonValue jsonValue2 = jsonValue0.getChild("attributes"); jsonValue2 != null; jsonValue2 = jsonValue2.next) {
            String s1 = jsonValue2.asString();
            monsterStats0.attributeText.add(s1);
        }
        for(JsonValue jsonValue3 = jsonValue0.getChild("special1"); jsonValue3 != null; jsonValue3 = jsonValue3.next) {
            String s2 = jsonValue3.asString();
            monsterStats0.specialText1.add(s2);
        }
        for(JsonValue jsonValue4 = jsonValue0.getChild("special2"); jsonValue4 != null; jsonValue4 = jsonValue4.next) {
            String s3 = jsonValue4.asString();
            monsterStats0.specialText2.add(s3);
        }
        return monsterStats0;
    }

    public static void setAttackCards(AttackModifier attackModifier0, AttackModifier attackModifier1) {
        if(attackModifier0 == null) {
            App.gloom.attackImage1.setDrawable(null);
        }
        else {
            App.gloom.attackImage1.setDrawable(App.drawable(new String[]{"attack/" + attackModifier0, "attack/border"}));
        }
        if(attackModifier1 == null) {
            App.gloom.attackImage2.setDrawable(null);
        }
        else {
            App.gloom.attackImage2.setDrawable(App.drawable(new String[]{"attack/" + attackModifier1, "attack/border"}));
        }
        ++App.animateNumber;
    }

    public static void sleep(int v) {
        try {
            Thread.sleep(v);
        }
        catch(InterruptedException unused_ex) {
        }
    }

    public static TextButtonBuilder textButton(String s) {
        return ((TextButtonBuilder)new TextButtonBuilder().programmaticChangeEvents(false)).font("fancyLargeOutline").fontColor(App.buttonGray).overFontColor(Color.WHITE).disabledFontColor(App.disabledGray).text(s);
    }

    public static TextField textField(String s) {
        TextFieldStyle textField$TextFieldStyle0 = new TextFieldStyle();
        textField$TextFieldStyle0.font = App.plainLargeOutline;
        textField$TextFieldStyle0.fontColor = App.buttonGray;
        textField$TextFieldStyle0.background = App.drawable(new String[]{"textfield"});
        textField$TextFieldStyle0.cursor = App.drawable("textfield-cursor", new Color(App.textSelect.r, App.textSelect.g, App.textSelect.b, 1.0f));
        textField$TextFieldStyle0.selection = App.drawable("textfield-cursor", App.textSelect);
        TextField textField0 = new TextField(s, textField$TextFieldStyle0);
        textField0.setProgrammaticChangeEvents(false);
        return textField0;
    }

    public static Texture texture(String s) {
        Texture texture0 = new Texture(App.file(s));
        App.textures.add(texture0);
        return texture0;
    }

    public static void toast(String s) {
        Container container0 = new Container(new Label(s, App.skin, "fancyLargeOutline", Color.WHITE));
        container0.setTouchable(Touchable.disabled);
        container0.setBackground(App.drawable("rounded", new Color(0.0f, 0.0f, 0.0f, 0.6f)));
        container0.pad(5.0f, 14.0f, 5.0f, 14.0f);
        container0.pack();
        container0.setX(((float)Math.round(App.stage.getWidth() / 2.0f - container0.getWidth() / 2.0f)));
        container0.setY(((float)Math.round(App.stage.getHeight() / 4.0f)));
        container0.setTransform(true);
        container0.getColor().a = 0.0f;
        container0.setScale(3.0f);
        container0.setOrigin(container0.getWidth() / 2.0f, container0.getHeight() / 2.0f);
        container0.addAction(Actions.sequence(Actions.parallel(Actions.fadeIn(0.3f, Interpolation.fastSlow), Actions.scaleTo(1.0f, 1.0f, 0.3f, Interpolation.fastSlow)), Actions.delay(5.0f), Actions.parallel(Actions.moveBy(0.0f, 150.0f, 0.8f, Interpolation.slowFast), Actions.fadeOut(0.8f, Interpolation.slowFast), Actions.scaleTo(0.5f, 0.5f, 0.8f, Interpolation.slowFast)), Actions.removeActor()));
        for(Object object0: App.gloom.toasts.getChildren()) {
            ((Actor)object0).addAction(Actions.moveBy(0.0f, 60.0f, 0.25f));
        }
        App.gloom.toasts.addActor(container0);
    }

    public static TextTooltip tooltip(String s) {
        TextTooltipStyle textTooltip$TextTooltipStyle0 = new TextTooltipStyle();
        textTooltip$TextTooltipStyle0.background = App.drawable("rounded", App.tooltips);
        textTooltip$TextTooltipStyle0.wrapWidth = 700.0f;
        textTooltip$TextTooltipStyle0.label = new LabelStyle();
        textTooltip$TextTooltipStyle0.label.font = App.plainLargeOutline;
        textTooltip$TextTooltipStyle0.label.fontColor = Color.WHITE;
        return new TextTooltip(s, textTooltip$TextTooltipStyle0);
    }
}

