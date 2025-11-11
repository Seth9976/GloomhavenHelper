package com.hm.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.TooltipManager;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.CharacterClass;
import com.hm.gloomhavenhelper.model.Condition;
import com.hm.gloomhavenhelper.model.Line;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.model.MonsterData;
import com.hm.gloomhavenhelper.model.MonsterStats;
import com.hm.gloomhavenhelper.model.MonsterType;
import com.hm.gloomhavenhelper.model.Scenario;
import com.hm.gloomhavenhelper.model.SummonColor;
import com.hm.gloomhavenhelper.util.CompositeDrawable;
import com.hm.gloomhavenhelper.util.FontWrapping;
import com.hm.gloomhavenhelper.util.Menu;
import com.hm.gloomhavenhelper.util.Row;
import com.hm.gloomhavenhelper.util.Shaders;
import com.hm.gloomhavenhelper.util.builders.CheckBoxBuilder;
import com.hm.gloomhavenhelper.util.builders.ImageButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.ImageTextButtonBuilder;
import com.hm.gloomhavenhelper.util.builders.TextButtonBuilder;
import com.hm.spine.AnimationStateData;
import com.hm.spine.BoneData;
import com.hm.spine.SkeletonBinary;
import com.hm.spine.SkeletonData;
import com.hm.spine.SkeletonRenderer;
import com.hm.spine.utils.SkeletonActorPool;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
   public static final String version = "9.1.0";
   public static final String majorMinor;
   public static final boolean eclipse = false;
   public static final String tag = "ghh";
   public static GloomhavenHelper gloom;
   public static Game game;
   public static String[] args;
   public static GameConfig config;
   public static GameState state;
   public static Object gameThread;
   public static final IntMap monsterAbilityDecks;
   public static final OrderedMap nameToMonsterData;
   public static final OrderedMap englishToMonsterData;
   public static final Array monsterDatas;
   public static MonsterData summonData;
   public static MonsterStats summonStats;
   public static final Array monsterAbilities;
   public static final Map allScenarios;
   public static final Map allSections;
   public static final List allClasses;
   public static BitmapFont fancyExtraLargeOutlineNumbers;
   public static BitmapFont fancyLargeOutline;
   public static BitmapFont fancyLargeOutlineFixedNumbers;
   public static BitmapFont plainExtraLargeNumbers;
   public static BitmapFont plainExtraLargeOutlineNumbers;
   public static BitmapFont plainLargeOutline;
   public static BitmapFont plainLargeFixedNumbers;
   public static BitmapFont plainLargeOutlineFixedNumbers;
   public static BitmapFont plainMedium;
   public static BitmapFont plainMediumOutline;
   public static BitmapFont plainMediumOutlineFixedNumbers;
   public static BitmapFont plainSmall;
   public static BitmapFont plainSmallOutline;
   public static Skin skin;
   public static TextureAtlas atlas;
   public static Stage stage;
   public static Table root;
   public static ExtendViewport viewport;
   public static SkeletonRenderer skeletonRenderer;
   static SkeletonActorPool rippleSkeletonPool;
   public static SkeletonData elementsSkeletonData;
   public static SkeletonData esotericSkeletonData;
   public static final Vector2 v2;
   public static final Vector3 v3;
   public static final Color c;
   public static final Color buttonGray;
   public static final Color disabledGray;
   public static final Color lightGray;
   public static final Color gray;
   public static final Color disabledDim;
   public static final Color healthGreen;
   public static final Color healthRed;
   public static final Color bossRed;
   public static final Color eliteGold;
   public static final Color xpBlue;
   public static final Color lootGold;
   public static final Color darken;
   public static final Color textSelect;
   public static final Color tooltips;
   public static final float monsterStatsDim = 0.4F;
   public static boolean dragging;
   public static Row dragRow;
   public static Row lastSwapRow;
   public static final Vector2 dragStart;
   public static ShaderProgram desatShader;
   public static final Array textures;
   static int animateNumber;
   public static List editions;
   public static List scenarioStartingNumbers;

   static void loadImagesFromDirectory(FileHandle directory) {
      for (FileHandle file : directory.list()) {
         if (file.isDirectory()) {
            loadImagesFromDirectory(file);
         } else if (file.extension().equals("png")) {
            String id = file.path().substring("images/".length(), file.path().length() - ".png".length());
            skin.add(id, texture(file.path()));
         }
      }
   }

   public static void load() {
      monsterAbilityDecks.clear(51);
      nameToMonsterData.clear(51);
      englishToMonsterData.clear(51);
      monsterDatas.clear();
      summonData = null;
      summonStats = null;
      monsterAbilities.clear();
      allScenarios.clear();
      allSections.clear();
      allClasses.clear();
      textures.clear();
      MonsterAbility.reset();
      MonsterAbilityDeck.reset();
      MonsterData.reset();
      Menu.menusShown = 0;
      Row.animatedHeight = true;
      PlayerRow.localInit.clear(51);
      Gdx.graphics.setContinuousRendering(false);
      TooltipManager.getInstance().initialTime = 0.8F;
      TooltipManager.getInstance().hideAll();
      atlas = loadAtlas();
      fancyExtraLargeOutlineNumbers = loadFont("fancyExtraLargeOutlineNumbers", -52, null);
      fancyLargeOutline = loadFont("fancyLargeOutline", -46, null);
      fancyLargeOutlineFixedNumbers = loadFont("fancyLargeOutline", -46, "0123456789_");
      plainExtraLargeNumbers = loadFont("plainExtraLargeNumbers", -46, null);
      plainExtraLargeOutlineNumbers = loadFont("plainExtraLargeOutlineNumbers", -46, "0123456789+-");
      plainLargeOutline = loadFont("plainLargeOutline", -35, null);
      plainLargeOutlineFixedNumbers = loadFont("plainLargeOutline", -32, "0123456789-+/");
      plainLargeFixedNumbers = loadFont("plainLargeNumbers", -35, "0123456789-+/");
      plainMedium = loadFont("plainMedium", -27, null);
      plainMediumOutline = loadFont("plainMediumOutline", -27, null);
      plainMediumOutlineFixedNumbers = loadFont("plainMediumOutline", -27, "0123456789-+/");
      plainSmall = loadFont("plainSmall", -20, null);
      plainSmallOutline = loadFont("plainSmallOutline", -20, null);
      (skin = new Skin()).add("default", plainLargeOutline);
      skin.add("fancyExtraLargeOutlineNumbers", fancyExtraLargeOutlineNumbers);
      skin.add("fancyLargeOutline", fancyLargeOutline);
      skin.add("fancyLargeOutlineFixedNumbers", fancyLargeOutlineFixedNumbers);
      skin.add("plainExtraLargeNumbers", plainExtraLargeNumbers);
      skin.add("plainExtraLargeOutlineNumbers", plainExtraLargeOutlineNumbers);
      skin.add("plainLargeOutline", plainLargeOutline);
      skin.add("plainLargeOutlineFixedNumbers", plainLargeOutlineFixedNumbers);
      skin.add("plainMediumOutline", plainMediumOutline);
      skin.add("plainMediumOutlineFixedNumbers", plainMediumOutlineFixedNumbers);
      skin.add("plainSmallOutline", plainSmallOutline);
      skin.addRegions(atlas);
      FileHandle[] files = Gdx.files.internal("images/").list();

      for (FileHandle file : files) {
         if (file.isDirectory()) {
            loadImagesFromDirectory(file);
         } else if (file.extension().equals("png")) {
            String id = file.path().substring("images/".length(), file.path().length() - ".png".length());
            skin.add(id, texture(file.path()));
         }
      }

      FileHandle textFile = Gdx.files.internal("images/assets.txt");

      try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(textFile.read()));

         try {
            while (reader.ready()) {
               String line = reader.readLine();
               String id = line.substring(0, line.length() - ".png".length());
               skin.add(id, texture("images/" + line));
            }
         } catch (Throwable var8) {
            try {
               reader.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }

            throw var8;
         }

         reader.close();
      } catch (FileNotFoundException var9) {
         var9.printStackTrace();
      } catch (IOException var10) {
         var10.printStackTrace();
      }

      for (Condition condition : Condition.values) {
         condition.drawable = skin.getDrawable("conditions/" + condition.name());
         if (condition != Condition.summonNew
            && condition != Condition.summon
            && condition != Condition.star
            && condition != Condition.invisible
            && condition != Condition.doom
            && condition != Condition.hatchet) {
            condition.drawableMedium = skin.getDrawable("abilities/" + condition.name() + "-medium");
         }
      }

      for (SummonColor color : SummonColor.values) {
         color.drawable = skin.getDrawable("summon/" + color.name());
      }

      stage = new Stage(new ScreenViewport(), new PolygonSpriteBatch()) {
         @Override
         public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            this.setKeyboardFocus(null);
            Gdx.input.setOnscreenKeyboardVisible(false);
            return super.touchDown(screenX, screenY, pointer, button);
         }
      };
      viewport = new ExtendViewport(1113.0F, 800.0F, 2.1474836E9F, 2.1474836E9F, stage.getCamera());
      stage.setViewport(viewport);
      skeletonRenderer = new SkeletonRenderer();
      SkeletonBinary json = new SkeletonBinary(atlas);
      elementsSkeletonData = json.readSkeletonData(file("skeletons/elements.skel"));
      esotericSkeletonData = json.readSkeletonData(file("skeletons/esoteric.skel"));
      SkeletonData rippleData = json.readSkeletonData(file("skeletons/ripple.skel"));
      ((BoneData)rippleData.getBones().first()).setScale(1.3F, 1.3F);
      rippleSkeletonPool = new SkeletonActorPool(skeletonRenderer, rippleData, new AnimationStateData(rippleData));
      desatShader = Shaders.desat();
      loadData();
   }

   public static FileHandle file(String path) {
      return Gdx.files.internal(path);
   }

   public static TextureAtlas loadAtlas() {
      FileHandle atlasPath = file("skin.atlas");

      try {
         TextureAtlas atlas = new TextureAtlas(atlasPath);
         atlas.getTextures().iterator().toArray(textures);
         return atlas;
      } catch (Throwable var3) {
         String message = var3.getMessage();
         if (message != null && (message.contains("Unable to allocate memory") || message.contains("Out of memory"))) {
            game.oom();
         }

         throw new RuntimeException("Unable to load texture atlas.", var3);
      }
   }

   public static BitmapFont loadFont(String name, int down, String fixed) {
      String subdir = config.language;
      if (subdir.equals("pl")
         || subdir.equals("cz")
         || subdir.equals("de")
         || subdir.equals("fr")
         || subdir.equals("it")
         || subdir.equals("en")
         || subdir.equals("es")
         || subdir.equals("pt")
         || subdir.equals("hu")) {
         subdir = "en";
      }

      String dir = "fonts/" + subdir + "/";
      FileHandle file = file(dir + name + ".fnt");
      BitmapFont.BitmapFontData data;
      if (config.language.equals("ko")) {
         data = new BitmapFont.BitmapFontData(file, false) {
            @Override
            public int getWrapIndex(Array glyphs, int start) {
               return FontWrapping.Korean.getWrapIndex(glyphs, start);
            }
         };
      } else if (config.language.equals("ja")) {
         data = new BitmapFont.BitmapFontData(file, false) {
            @Override
            public int getWrapIndex(Array glyphs, int start) {
               return FontWrapping.Japanese.getWrapIndex(glyphs, start);
            }
         };
      } else {
         data = new BitmapFont.BitmapFontData(file, false);
      }

      BitmapFont font = new BitmapFont(data, atlas.findRegion(dir + name), true);
      font.setUseIntegerPositions(true);
      font.getData().breakChars = new char[]{'-'};
      font.getData().down = down;
      if (fixed != null) {
         font.setFixedWidthGlyphs(fixed);
      }

      return font;
   }

   public static void main(String[] args) throws Exception {
      JsonValue root = new JsonReader().parse(new FileHandle("C:/Users/Nate/Desktop/json_data_new.json"));
      JsonValue.PrettyPrintSettings s = new JsonValue.PrettyPrintSettings();
      s.outputType = JsonWriter.OutputType.javascript;
      s.singleLineColumns = 130;
      s.wrapNumericArrays = false;
      new FileHandle("C:/Dev/projects/ghh/ghh-core/assets/data.json").writeString(root.prettyPrint(s), false);
   }

   private static void loadData() {
      String localizationFile = "strings-" + config.language + ".json";
      String baseFile = "data.json";
      JsonValue baseRoot = new JsonReader().parse(file("data.json"));
      JsonValue localizationRoot = new JsonReader().parse(file(localizationFile));
      Text.loadText(localizationRoot.get("text"));
      editions = Arrays.asList(baseRoot.get("editions").asStringArray());
      List editionRoots = new ArrayList();

      for (String edition : editions) {
         String file = "editions/" + edition + ".json";
         editionRoots.add(new JsonReader().parse(file(file)));
      }

      for (JsonValue classEntry = baseRoot.getChild("classes"); classEntry != null; classEntry = classEntry.next) {
         CharacterClass characterClass = new CharacterClass();
         characterClass.loadData(classEntry);
         allClasses.add(characterClass);
      }

      Line.loadTokens(localizationRoot.get("tokens"));

      for (JsonValue edition : editionRoots) {
         for (JsonValue classEntry = edition.getChild("classes"); classEntry != null; classEntry = classEntry.next) {
            CharacterClass characterClass = new CharacterClass();
            characterClass.loadData(classEntry);
            allClasses.add(characterClass);
         }

         boolean cardsHasTitles = edition.getBoolean("titlesOnAbilityCards", false);

         for (JsonValue deckEntry = edition.getChild("monsterAbilities"); deckEntry != null; deckEntry = deckEntry.next) {
            MonsterAbilityDeck deck = new MonsterAbilityDeck();
            deck.name = deckEntry.getString("name");
            monsterAbilityDecks.put(deck.id, deck);
            deck.edition = deckEntry.getString("edition", "Gloomhaven");
            List cardLayouts = new ArrayList();
            if (deckEntry.has("cardLayouts")) {
               JsonValue jsonLayoutArray = deckEntry.get("cardLayouts");

               for (int i = 0; i < jsonLayoutArray.size; i++) {
                  MonsterAbility.CardLayout cardLayout = new MonsterAbility.CardLayout();
                  cardLayout.nr = jsonLayoutArray.get(i).getString("nr");

                  for (int j = 0; j < jsonLayoutArray.get(i).get("lines").size; j++) {
                     MonsterAbility.LinesLayout linesLayout = new MonsterAbility.LinesLayout();
                     JsonValue jsonLine = jsonLayoutArray.get(i).get("lines").get(j);
                     linesLayout.xOffset = jsonLine.getInt("xOffset", 0);
                     linesLayout.yOffset = jsonLine.getInt("yOffset", 0);
                     linesLayout.heightOffset = jsonLine.getInt("heightOffset", 0);
                     linesLayout.spaceTopOffset = jsonLine.getInt("spaceTopOffset", 0);
                     linesLayout.type = jsonLine.getString("type", "line");
                     linesLayout.index = jsonLine.getInt("index", -1);
                     cardLayout.lines.add(linesLayout);
                  }

                  cardLayouts.add(cardLayout);
               }
            }

            for (JsonValue cardEntry = deckEntry.get("cards").child; cardEntry != null; cardEntry = cardEntry.next) {
               MonsterAbility card = new MonsterAbility();
               card.deck = deck;
               int numberIndex = 0;
               if (cardsHasTitles) {
                  numberIndex = 1;
                  card.title = cardEntry.getString(0);
               }

               card.number = cardEntry.getString(numberIndex);

               for (MonsterAbility.CardLayout cardLayout : cardLayouts) {
                  if (cardLayout.nr.equals(card.number)) {
                     card.cardLayout = cardLayout;
                  }
               }

               card.shuffle = cardEntry.getBoolean(numberIndex + 1);
               card.initiative = cardEntry.getInt(numberIndex + 2);
               card.initiativeString = Integer.toString(card.initiative);
               deck.abilities.add(card);

               for (JsonValue lineEntry = cardEntry.get(numberIndex + 3); lineEntry != null; lineEntry = lineEntry.next) {
                  card.text.add(lineEntry.asString());
               }

               monsterAbilities.add(card);
            }
         }

         for (JsonValue monsterEntry = edition.getChild("monsters"); monsterEntry != null; monsterEntry = monsterEntry.next) {
            MonsterData data = new MonsterData();
            data.name = monsterEntry.name;
            data.english = monsterEntry.getString("english", data.name);
            data.display = monsterEntry.getString("display", data.name);
            data.count = monsterEntry.getInt("count");
            data.edition = monsterEntry.getString("edition", "Gloomhaven");
            data.hidden = monsterEntry.getBoolean("hidden", false);
            data.gfx = monsterEntry.getString("gfx", data.name);
            String deckName = monsterEntry.getString("deck");

            for (IntMap.Entry entry : monsterAbilityDecks.entries()) {
               if (((MonsterAbilityDeck)entry.value).name.equals(deckName)) {
                  data.deckID = entry.key;
                  break;
               }
            }

            if (data.deckID == -1) {
               throw new RuntimeException("Deck not found: " + deckName);
            }

            data.flying = monsterEntry.getBoolean("flying", false);
            nameToMonsterData.put(data.name, data);
            if (!config.language.equals("en")) {
               englishToMonsterData.put(data.english, data);
            }

            monsterDatas.add(data);
            int i = 0;

            for (JsonValue levelEntry = monsterEntry.getChild("levels"); levelEntry != null; i++) {
               if (levelEntry.has("normal") && levelEntry.has("elite")) {
                  data.stats[MonsterType.normal.ordinal()][i] = readStats(levelEntry.get("normal"), data, false);
                  data.stats[MonsterType.elite.ordinal()][i] = readStats(levelEntry.get("elite"), data, true);
               } else {
                  data.stats[MonsterType.boss.ordinal()][i] = readStats(levelEntry, data, false);
               }

               levelEntry = levelEntry.next;
            }
         }

         String editionn = edition.getString("edition");
         int startNumber = edition.getInt("startingNumber", 1);
         JsonValue scenarios = edition.getChild("scenarios");
         if (edition.hasChild("sections")) {
            JsonValue sections = edition.getChild("sections");
            allSections.put(editionn, loadScenarios(sections));
         }

         allScenarios.put(editionn, loadScenarios(scenarios));
         scenarioStartingNumbers.add(startNumber);
      }

      MonsterAbilityDeck summonDeck = new MonsterAbilityDeck();
      summonDeck.name = "Summon";
      monsterAbilityDecks.put(summonDeck.id, summonDeck);
      summonData = new MonsterData();
      summonData.edition = "Gloomhaven";
      summonData.name = "Summon";
      summonData.english = "Summon";
      summonData.count = 6;
      summonData.deckID = summonDeck.id;
      nameToMonsterData.put(summonData.name, summonData);
      monsterDatas.add(summonData);
      summonStats = new MonsterStats();
      summonStats.hpMax = "2";
      summonStats.move = "2";
      summonStats.attack = "2";
      summonStats.range = "2";
   }

   private static ArrayList loadScenarios(JsonValue scenarioEntry) {
      ArrayList scenarios = new ArrayList();

      while (scenarioEntry != null) {
         String edition = scenarioEntry.getString("edition", "Gloomhaven");
         Scenario scenario = new Scenario();
         scenario.name = scenarioEntry.name;
         scenario.edition = edition;

         for (JsonValue monsterEntry = scenarioEntry.getChild("monsters"); monsterEntry != null; monsterEntry = monsterEntry.next) {
            scenario.monsters.add(findMonsterData(monsterEntry.asString()));
         }

         for (JsonValue specialEntry = scenarioEntry.getChild("special"); specialEntry != null; specialEntry = specialEntry.next) {
            scenario.special.add(specialEntry.asString());
         }

         scenarios.add(scenario);
         scenarioEntry = scenarioEntry.next;
      }

      return scenarios;
   }

   private static MonsterStats readStats(JsonValue statsEntry, MonsterData data, boolean elite) {
      MonsterStats stats = new MonsterStats();
      stats.hpMax = statsEntry.getString("health");
      stats.move = statsEntry.getString("move");
      stats.attack = statsEntry.getString("attack");
      stats.range = statsEntry.getString("range", "-");
      stats.notesText = statsEntry.getString("notes", "");

      for (JsonValue entry = statsEntry.getChild("immunities"); entry != null; entry = entry.next) {
         String name = entry.asString().replaceAll("%", "");
         if (name.equals("curse")) {
            stats.immuneCurse = true;
         } else if (name.equals("push")) {
            stats.immunePush = true;
         } else if (name.equals("pull")) {
            stats.immunePull = true;
         } else {
            stats.immunities.add(Condition.valueOf(name));
         }
      }

      for (JsonValue entryx = statsEntry.getChild("attributes"); entryx != null; entryx = entryx.next) {
         stats.attributeText.add(entryx.asString());
      }

      for (JsonValue entryx = statsEntry.getChild("special1"); entryx != null; entryx = entryx.next) {
         stats.specialText1.add(entryx.asString());
      }

      for (JsonValue entryx = statsEntry.getChild("special2"); entryx != null; entryx = entryx.next) {
         stats.specialText2.add(entryx.asString());
      }

      return stats;
   }

   public static MonsterAbilityDeck findMonsterAbilityDeck(int deckID) {
      MonsterAbilityDeck deck = (MonsterAbilityDeck)monsterAbilityDecks.get(deckID);
      if (deck == null) {
         throw new RuntimeException();
      } else {
         return deck;
      }
   }

   public static MonsterData findMonsterData(String name) {
      MonsterData data = (MonsterData)nameToMonsterData.get(name);
      if (data == null) {
         data = (MonsterData)englishToMonsterData.get(name);
         if (data == null) {
            throw new RuntimeException("Monster not found: " + name);
         }
      }

      return data;
   }

   public static Texture texture(String path) {
      Texture texture = new Texture(file(path));
      textures.add(texture);
      return texture;
   }

   public static void setAttackCards(AttackModifier attackModifier1, AttackModifier attackModifier2) {
      if (attackModifier1 == null) {
         gloom.attackImage1.setDrawable(null);
      } else {
         gloom.attackImage1.setDrawable(drawable("attack/" + attackModifier1, "attack/border"));
      }

      if (attackModifier2 == null) {
         gloom.attackImage2.setDrawable(null);
      } else {
         gloom.attackImage2.setDrawable(drawable("attack/" + attackModifier2, "attack/border"));
      }

      animateNumber++;
   }

   public static void animateAttackCard(AttackModifier attackModifier1, AttackModifier attackModifier2, AttackModifier attackModifier3) {
      if (attackModifier2 == null) {
         gloom.attackImage1.setDrawable(null);
      } else {
         gloom.attackImage1.setDrawable(drawable("attack/" + attackModifier2, "attack/border"));
      }

      if (attackModifier3 == null) {
         gloom.attackImage2.setDrawable(null);
      } else {
         gloom.attackImage2.setDrawable(drawable("attack/" + attackModifier3, "attack/border"));
      }

      animateAttackCard(attackModifier1, gloom.attackButton, gloom.attackImage1, gloom.attackImage2);
   }

   public static void animateAttackCard(AttackModifier attackModifier, Button attackButton, final Image attackImage1, final Image attackImage2) {
      float showTime = 0.6F;
      float flipTime = 0.3F;
      float pauseTime = 0.2F;
      float halfFlipTime = 0.15F;
      float finishTime = 0.4F;
      final Image darken = new Image(skin.newDrawable("white", new Color(0.0F, 0.0F, 0.0F, 0.4F)));
      darken.setFillParent(true);
      stage.addActor(darken);
      darken.getColor().a = 0.0F;
      darken.addAction(Actions.fadeIn(0.3F, Interpolation.fastSlow));
      final Image card = new Image(drawable("attack/back", "attack/border"));
      Vector2 start = attackButton.localToStageCoordinates(new Vector2(-139.5F, -94.0F));
      card.setScale(0.36155605F);
      card.setOrigin(218.5F, 148.0F);
      card.setPosition(Math.round(start.x), Math.round(start.y));
      stage.addActor(card);
      Vector2 end = attackImage1.localToStageCoordinates(new Vector2(-139.5F, -94.0F));
      final Drawable drawable = drawable("attack/" + attackModifier, "attack/border");
      final int startNumber = ++animateNumber;
      card.addAction(
         Actions.parallel(
            Actions.moveTo(
               Math.round(viewport.getWorldWidth() / 2.0F - 218.5F), Math.round(viewport.getWorldHeight() / 2.0F - 148.0F), 0.55F, Interpolation.fastSlow
            ),
            Actions.rotateTo(180.0F, 0.6F, Interpolation.fastSlow),
            Actions.scaleTo(1.75F, 1.75F, 0.3F, Interpolation.fastSlow),
            Actions.sequence(
               Actions.delay(0.3F),
               Actions.scaleTo(1.25F, 0.0F, 0.15F, Interpolation.slowFast),
               Actions.parallel(
                  Actions.scaleTo(1.0F, 1.0F, 0.15F, Interpolation.fastSlow),
                  new Action() {
                     @Override
                     public boolean act(float delta) {
                        card.setDrawable(
                           new CompositeDrawable(drawable) {
                              @Override
                              public void draw(
                                 Batch batch,
                                 float x,
                                 float y,
                                 float originX,
                                 float originY,
                                 float width,
                                 float height,
                                 float scaleX,
                                 float scaleY,
                                 float rotation
                              ) {
                                 super.draw(batch, x, y, originX, originY, width, height, scaleX, scaleY, rotation + 180.0F);
                              }
                           }
                        );
                        return true;
                     }
                  }
               ),
               Actions.delay(0.2F),
               new Action() {
                  @Override
                  public boolean act(float delta) {
                     if (App.animateNumber != startNumber) {
                        darken.remove();
                        card.remove();
                        return true;
                     } else {
                        darken.setTouchable(Touchable.disabled);
                        darken.addAction(Actions.sequence(Actions.fadeOut(0.4F, Interpolation.slowFast), Actions.removeActor()));
                        Vector2 endx = attackImage2.localToAscendantCoordinates(attackImage1.getParent(), new Vector2(0.0F, 0.0F));
                        attackImage1.clearActions();
                        attackImage1.addAction(
                           Actions.sequence(
                              Actions.parallel(
                                 Actions.moveTo(endx.x, endx.y, 0.4F, Interpolation.fastSlow), Actions.rotateTo(-31.5F, 0.4F, Interpolation.fastSlow)
                              ),
                              new Action() {
                                 @Override
                                 public boolean act(float delta) {
                                    attackImage2.setDrawable(attackImage1.getDrawable());
                                    attackImage1.setDrawable(drawable);
                                    attackImage1.setRotation(0.0F);
                                    ((Table)attackImage1.getParent()).invalidate();
                                    return true;
                                 }
                              }
                           )
                        );
                        return true;
                     }
                  }
               },
               Actions.parallel(
                  Actions.moveTo(Math.round(end.x), Math.round(end.y), 0.4F, Interpolation.slowFast),
                  Actions.scaleTo(0.36155605F, 0.36155605F, 0.4F, Interpolation.slowFast)
               ),
               Actions.removeActor()
            )
         )
      );
   }

   public static Drawable drawable(String... names) {
      if (names.length == 1) {
         return skin.getDrawable(names[0]);
      } else {
         Drawable[] drawables = new Drawable[names.length];
         int i = 0;

         for (int n = names.length; i < n; i++) {
            drawables[i] = skin.getDrawable(names[i]);
         }

         return new CompositeDrawable(drawables);
      }
   }

   public static Drawable drawable(String name, Color color) {
      return skin.newDrawable(name, color);
   }

   public static Color c(Color color, float a) {
      c.set(color.r, color.g, color.b, a);
      return c;
   }

   public static Color c(float r, float g, float b, float a) {
      return c.set(r, g, b, a);
   }

   public static Color c(int rgba, float a) {
      c.set(rgba);
      c.a = a;
      return c;
   }

   public static void animate(Vector2 current, Vector2 target, float minSpeed, float maxSpeed, float distanceForMaxSpeed, float delta) {
      if (Float.isNaN(target.x)) {
         target.x = 0.0F;
      }

      if (Float.isNaN(target.y)) {
         target.y = 0.0F;
      }

      if (current.x != target.x || current.y != target.y) {
         float diffX = target.x - current.x;
         float diffY = target.y - current.y;
         float slope = diffX != 0.0F && diffY != 0.0F ? diffY / diffX : 1.0F;
         float px = MathUtils.clamp(Math.abs(diffX / slope) / distanceForMaxSpeed, 0.0F, 1.0F);
         float py = MathUtils.clamp(Math.abs(diffY * slope) / distanceForMaxSpeed, 0.0F, 1.0F);
         float speedX = Interpolation.slowFast.apply(minSpeed, maxSpeed, px) * delta;
         float speedY = Interpolation.slowFast.apply(minSpeed, maxSpeed, py) * delta;
         if (diffX > 0.0F) {
            current.x = Math.min(target.x, current.x + speedX);
         } else {
            current.x = Math.max(target.x, current.x - speedX);
         }

         if (diffY > 0.0F) {
            current.y = Math.min(target.y, current.y + speedY);
         } else {
            current.y = Math.max(target.y, current.y - speedY);
         }

         Gdx.graphics.requestRendering();
      }
   }

   public static float animate(float current, float target, float minSpeed, float maxSpeed, float distanceForMaxSpeed, float delta) {
      if (Float.isNaN(target)) {
         target = 0.0F;
      }

      if (current == target) {
         return current;
      } else {
         Gdx.graphics.requestRendering();
         float speed = Interpolation.slowFast.apply(minSpeed, maxSpeed, MathUtils.clamp(Math.abs(target - current) / distanceForMaxSpeed, 0.0F, 1.0F)) * delta;
         return current < target ? Math.min(target, current + speed) : Math.max(target, current - speed);
      }
   }

   public static ImageButtonBuilder imageButton() {
      return (ImageButtonBuilder)new ImageButtonBuilder().programmaticChangeEvents(false);
   }

   public static ImageTextButtonBuilder imageTextButton(String text) {
      return ((ImageTextButtonBuilder)new ImageTextButtonBuilder().programmaticChangeEvents(false))
         .font("fancyLargeOutline")
         .fontColor(buttonGray)
         .overFontColor(Color.WHITE)
         .disabledFontColor(disabledGray)
         .text(text);
   }

   public static TextButtonBuilder textButton(String text) {
      return ((TextButtonBuilder)new TextButtonBuilder().programmaticChangeEvents(false))
         .font("fancyLargeOutline")
         .fontColor(buttonGray)
         .overFontColor(Color.WHITE)
         .disabledFontColor(disabledGray)
         .text(text);
   }

   public static CheckBox checkbox(String text) {
      CheckBox checkbox = ((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)new CheckBoxBuilder()
                              .checkboxOff("checkbox", buttonGray)
                              .checkboxOver("checkbox")
                              .checkboxOn("checkbox-checked")
                              .checkboxOnOver("checkbox-checked-over")
                              .checkboxOffDisabled("checkbox", disabledGray)
                              .checkboxOnDisabled("checkbox-checked-over", disabledGray)
                              .font("fancyLargeOutline"))
                           .fontColor(buttonGray))
                        .disabledFontColor(disabledGray))
                     .overFontColor(Color.WHITE))
                  .text(text))
               .programmaticChangeEvents(false))
            .tapSquareSize(28))
         .create();
      checkbox.getLabelCell().padLeft(12.0F).left().expandX();
      return checkbox;
   }

   public static CheckBox radio(String text) {
      CheckBox radio = ((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)((CheckBoxBuilder)new CheckBoxBuilder()
                           .checkboxOff("radio", buttonGray)
                           .checkboxOver("radio")
                           .checkboxOn("radio-checked")
                           .checkboxOnOver("radio-checked-over")
                           .font("fancyLargeOutline"))
                        .fontColor(buttonGray))
                     .overFontColor(Color.WHITE))
                  .text(text))
               .programmaticChangeEvents(false))
            .tapSquareSize(28))
         .create();
      radio.getLabelCell().padLeft(12.0F).left().expandX();
      return radio;
   }

   public static TextField textField(String text) {
      TextField.TextFieldStyle style = new TextField.TextFieldStyle();
      style.font = plainLargeOutline;
      style.fontColor = buttonGray;
      style.background = drawable("textfield");
      style.cursor = drawable("textfield-cursor", new Color(textSelect.r, textSelect.g, textSelect.b, 1.0F));
      style.selection = drawable("textfield-cursor", textSelect);
      TextField textField = new TextField(text, style);
      textField.setProgrammaticChangeEvents(false);
      return textField;
   }

   public static Image image(Drawable drawable) {
      Image image = new Image(drawable);
      image.setScaling(Scaling.none);
      return image;
   }

   public static Image image(String name) {
      return image(skin.getDrawable(name));
   }

   public static Image image(String name, Color color) {
      return image(skin.newDrawable(name, color));
   }

   public static TextTooltip tooltip(String text) {
      TextTooltip.TextTooltipStyle style = new TextTooltip.TextTooltipStyle();
      style.background = drawable("rounded", tooltips);
      style.wrapWidth = 700.0F;
      style.label = new Label.LabelStyle();
      style.label.font = plainLargeOutline;
      style.label.fontColor = Color.WHITE;
      return new TextTooltip(text, style);
   }

   public static void finishActions(Actor actor) {
      while (true) {
         Array actions = actor.getActions();
         if (actions.size == 0) {
            return;
         }

         actor.act(99.0F);
      }
   }

   public static AttackModifier readAttackModifier(byte b) {
      return b == 0 ? null : AttackModifier.values[b - 1];
   }

   public static void gameThread() {
   }

   public static void toast(String text) {
      gameThread();
      Label label = new Label(text, skin, "fancyLargeOutline", Color.WHITE);
      Container container = new Container(label);
      container.setTouchable(Touchable.disabled);
      container.setBackground(drawable("rounded", new Color(0.0F, 0.0F, 0.0F, 0.6F)));
      container.pad(5.0F, 14.0F, 5.0F, 14.0F);
      container.pack();
      container.setX(Math.round(stage.getWidth() / 2.0F - container.getWidth() / 2.0F));
      container.setY(Math.round(stage.getHeight() / 4.0F));
      container.setTransform(true);
      container.getColor().a = 0.0F;
      container.setScale(3.0F);
      container.setOrigin(container.getWidth() / 2.0F, container.getHeight() / 2.0F);
      container.addAction(
         Actions.sequence(
            Actions.parallel(Actions.fadeIn(0.3F, Interpolation.fastSlow), Actions.scaleTo(1.0F, 1.0F, 0.3F, Interpolation.fastSlow)),
            Actions.delay(5.0F),
            Actions.parallel(
               Actions.moveBy(0.0F, 150.0F, 0.8F, Interpolation.slowFast),
               Actions.fadeOut(0.8F, Interpolation.slowFast),
               Actions.scaleTo(0.5F, 0.5F, 0.8F, Interpolation.slowFast)
            ),
            Actions.removeActor()
         )
      );

      for (Actor other : gloom.toasts.getChildren()) {
         other.addAction(Actions.moveBy(0.0F, 60.0F, 0.25F));
      }

      gloom.toasts.addActor(container);
   }

   public static void sleep(int millis) {
      try {
         Thread.sleep(millis);
      } catch (InterruptedException var2) {
      }
   }

   public static int parseInt(String value) {
      if (value == null) {
         throw new IllegalArgumentException("value cannot be null.");
      } else if (value.length() == 0) {
         throw new NumberFormatException("Empty string.");
      } else {
         if (value.charAt(0) == '+') {
            value = value.substring(1);
         }

         int retVal = -1;

         try {
            retVal = Integer.parseInt(value);
         } catch (NumberFormatException var3) {
         }

         return retVal;
      }
   }

   static {
      int first = "9.1.0".indexOf(46);
      int last = "9.1.0".lastIndexOf(46);
      if (first != -1 && first != last) {
         majorMinor = "9.1.0".substring(0, last);
      } else {
         majorMinor = "9.1.0";
      }

      monsterAbilityDecks = new IntMap(64);
      nameToMonsterData = new OrderedMap(64);
      englishToMonsterData = new OrderedMap(64);
      monsterDatas = new Array(64);
      monsterAbilities = new Array(512);
      allSections = new HashMap();
      allScenarios = new HashMap();
      allClasses = new ArrayList();
      v2 = new Vector2();
      v3 = new Vector3();
      c = new Color();
      buttonGray = new Color(-1936945921);
      disabledGray = new Color(1280068863);
      lightGray = new Color(-976894465);
      gray = new Color(1819045119);
      disabledDim = new Color(1.0F, 1.0F, 1.0F, 0.4F);
      healthGreen = new Color(2147418367);
      healthRed = new Color(-164416001);
      bossRed = new Color(-164416001);
      eliteGold = new Color(-4777985);
      xpBlue = new Color(746955007);
      lootGold = new Color(-963698689);
      darken = new Color(-370873665);
      textSelect = new Color(613154695);
      tooltips = new Color(0.0F, 0.0F, 0.0F, 0.75F);
      dragStart = new Vector2();
      textures = new Array();
      editions = new ArrayList();
      scenarioStartingNumbers = new ArrayList();
   }
}
