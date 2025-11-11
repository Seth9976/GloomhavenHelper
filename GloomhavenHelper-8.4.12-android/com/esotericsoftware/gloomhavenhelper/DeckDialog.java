package com.esotericsoftware.gloomhavenhelper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.IntArray;
import com.esotericsoftware.gloomhavenhelper.model.AttackModifier;
import com.esotericsoftware.gloomhavenhelper.model.CharacterClass;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbility;
import com.esotericsoftware.gloomhavenhelper.model.MonsterAbilityDeck;
import com.esotericsoftware.gloomhavenhelper.util.Card;
import com.esotericsoftware.gloomhavenhelper.util.GloomScrollPane;
import com.esotericsoftware.gloomhavenhelper.util.Menu;

public abstract class DeckDialog extends Table {
    public static class AttackModifierDeckDialog extends DeckDialog {
        Drawable back;
        Drawable empty;
        Drawable selected;

        public AttackModifierDeckDialog() {
            this.back = App.drawable(new String[]{"attack/back", "attack/border"});
            this.empty = App.drawable(new String[]{"attack/back-empty"});
            this.selected = App.drawable(new String[]{"attack/selected"});
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getBackDrawable() {
            return this.back;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getEmptyDrawable() {
            return this.empty;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getSelectedDrawable() {
            return this.selected;
        }

        protected Actor newFrontActor(AttackModifier attackModifier0) {
            return new FixedSizeImage(App.drawable(new String[]{"attack/" + attackModifier0, "attack/border"}), this.cardWidth, this.cardHeight);
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Actor newFrontActor(Card card0) {
            return this.newFrontActor(((AttackModifier)card0));
        }

        protected void show() {
            this.show(158.0f, 107.0f, 9.0f, 9.0f, 18.0f, 18.0f, App.state.attackModifiers, App.state.attackModifiersDiscard, IntArray.with(new int[]{2, 3, 4, 5, 6, 7, 8, 9}), false);
            TextButton textButton0 = App.textButton(("Bad Omen" + (App.state.badOmen <= 0 ? "" : ": " + App.state.badOmen))).align(8).create();
            textButton0.pad(0.0f, 20.0f, 0.0f, 20.0f);
            textButton0.getLabelCell().height(80.0f);
            textButton0.invalidate();
            int v = App.state.count(AttackModifier.minus1, true);
            TextButton textButton1 = App.textButton(("Enfeebling Hex" + (v - 5 <= 0 ? "" : ": " + (v - 5)))).align(8).create();
            textButton1.pad(0.0f, 20.0f, 0.0f, 20.0f);
            textButton1.getLabelCell().height(80.0f);
            textButton1.invalidate();
            Table table0 = new Table();
            table0.add(textButton0).space(20.0f);
            table0.add(textButton1);
            this.actions.add(table0).colspan(2).padRight(20.0f);
            textButton0.addListener(new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.state.badOmen = 6;
                    App.state.changed();
                    textButton0.setText("Bad Omen: " + App.state.badOmen);
                    App.toast("Bad Omen will be applied the");
                    App.toast("next 6 times a null is shuffled.");
                }
            });
            textButton1.addListener(new ChangeListener() {
                @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
                public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                    App.state.add(AttackModifier.minus1);
                    App.state.attackModifiers.shuffle();
                    App.state.changed();
                    int v = App.state.count(AttackModifier.minus1, true);
                    textButton1.setText("Enfeebling Hex: " + (v - 5));
                    AttackModifierDeckDialog.this.resetRemaining();
                    App.toast("Enfeebling Hex has shuffled the");
                    App.toast("monster attack modifiers.");
                }
            });
        }
    }

    static class FixedSizeImage extends Image {
        private final float height;
        private final float width;

        public FixedSizeImage(Drawable drawable0, float f, float f1) {
            super(drawable0);
            this.width = f;
            this.height = f1;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Image
        public float getPrefHeight() {
            return this.height;
        }

        @Override  // com.badlogic.gdx.scenes.scene2d.ui.Image
        public float getPrefWidth() {
            return this.width;
        }
    }

    public static class MonsterAbilityDeckDialog extends DeckDialog {
        final Drawable back;
        final Drawable empty;
        final MonsterRow row;
        final Drawable selected;

        public MonsterAbilityDeckDialog(MonsterRow monsterRow0) {
            this.back = App.drawable(new String[]{"psd/monsterAbility-back"});
            this.empty = App.drawable(new String[]{"monsterAbility-back-empty"});
            this.selected = App.drawable(new String[]{"monsterAbility-selected"});
            this.row = monsterRow0;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getBackDrawable() {
            return this.back;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getEmptyDrawable() {
            return this.empty;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Drawable getSelectedDrawable() {
            return this.selected;
        }

        protected Actor newFrontActor(MonsterAbility monsterAbility0) {
            Actor actor0 = new MonsterAbilityCard(this.row, false);
            actor0.forceAbility = monsterAbility0;
            return actor0;
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.DeckDialog
        protected Actor newFrontActor(Card card0) {
            return this.newFrontActor(((MonsterAbility)card0));
        }

        public void show() {
            MonsterAbilityDeck monsterAbilityDeck0 = this.row.getAbilityDeck();
            Array array0 = new Array(monsterAbilityDeck0.abilitiesDiscard);
            IntArray intArray0 = new IntArray();
            if(this.row.data.isBoss()) {
                intArray0.add(2, 3);
            }
            else {
                intArray0.add(1, 2, 3, 4);
                intArray0.add(5);
            }
            intArray0.add(-1);
            this.show(441.0f, 209.0f, 18.0f, 15.0f, 36.0f, 34.0f, monsterAbilityDeck0.abilities, array0, intArray0, true);
        }
    }

    Menu actions;
    float cardHeight;
    float cardWidth;
    Array discard;
    DragScrollListener dragListener;
    float dragOffsetY;
    final VerticalGroup left;
    Array remaining;
    final VerticalGroup right;
    Actor selected;
    float selectedH;
    float selectedW;
    float selectedX;
    float selectedY;
    final BooleanArray shown;

    public DeckDialog() {
        this.right = new VerticalGroup();
        this.shown = new BooleanArray();
        this.left = new VerticalGroup() {
            @Override  // com.badlogic.gdx.scenes.scene2d.Group
            protected void drawChildren(Batch batch0, float f) {
                super.drawChildren(batch0, f);
                if(DeckDialog.this.selected != null) {
                    DeckDialog.this.getSelectedDrawable().draw(batch0, DeckDialog.this.selected.getX() - DeckDialog.this.selectedX, DeckDialog.this.selected.getY() - DeckDialog.this.selectedY, DeckDialog.this.cardWidth + DeckDialog.this.selectedW, DeckDialog.this.cardHeight + DeckDialog.this.selectedH);
                }
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup
            public void layout() {
                float f = DeckDialog.this.selected == null ? 0.0f : DeckDialog.this.selected.getY();
                super.layout();
                if(DeckDialog.this.selected != null && DeckDialog.this.dragListener.isDragging()) {
                    DeckDialog.this.selected.setY(f);
                }
            }
        };
    }

    protected abstract Drawable getBackDrawable();

    protected abstract Drawable getEmptyDrawable();

    protected abstract Drawable getSelectedDrawable();

    void hide() {
        this.addAction(Actions.sequence(Actions.fadeOut(0.3f), Actions.removeActor()));
    }

    protected abstract Actor newFrontActor(Card arg1);

    void resetRemaining() {
        this.left.clearChildren();
        this.shown.clear();
        if(this.remaining.isEmpty()) {
            FixedSizeImage deckDialog$FixedSizeImage0 = new FixedSizeImage(this.getEmptyDrawable(), this.cardWidth, this.cardHeight);
            this.left.addActor(deckDialog$FixedSizeImage0);
        }
        for(Object object0: this.remaining) {
            Card card0 = (Card)object0;
            FixedSizeImage deckDialog$FixedSizeImage1 = new FixedSizeImage(this.getBackDrawable(), this.cardWidth, this.cardHeight);
            this.left.addActor(deckDialog$FixedSizeImage1);
            this.shown.add(false);
        }
    }

    protected void show(float f, float f1, float f2, float f3, float f4, float f5, Array array0, Array array1, IntArray intArray0, boolean z) {
        this.cardWidth = f;
        this.cardHeight = f1;
        this.selectedX = f2;
        this.selectedY = f3;
        this.selectedW = f4;
        this.selectedH = f5;
        this.remaining = array0;
        this.discard = array1;
        this.resetRemaining();
        if(array1.isEmpty()) {
            FixedSizeImage deckDialog$FixedSizeImage0 = new FixedSizeImage(this.getEmptyDrawable(), f, f1);
            this.right.addActor(deckDialog$FixedSizeImage0);
        }
        for(Object object0: array1) {
            Actor actor0 = this.newFrontActor(((Card)object0));
            this.right.addActor(actor0);
        }
        this.left.reverse().top().space(10.0f);
        this.right.reverse().top().space(10.0f);
        com.esotericsoftware.gloomhavenhelper.DeckDialog.2 deckDialog$20 = new GloomScrollPane(new Container(this.left).pad(17.0f, 18.0f, 15.0f, 18.0f).top()) {
            @Override  // com.badlogic.gdx.scenes.scene2d.ui.ScrollPane
            public void act(float f) {
                float f1 = this.getVisualScrollY();
                super.act(f);
                float f2 = f1 - this.getVisualScrollY();
                if(f2 != 0.0f && DeckDialog.this.dragListener.isDragging()) {
                    DeckDialog.this.updateSelectedPosition(DeckDialog.this.selected.getY() - DeckDialog.this.dragOffsetY + f2);
                }
            }
        };
        deckDialog$20.setScrollingDisabled(true, false);
        deckDialog$20.setForceScroll(false, true);
        deckDialog$20.setFlickScrollTapSquareSize(35.0f);
        GloomScrollPane gloomScrollPane0 = new GloomScrollPane(new Container(this.right).pad(17.0f, 0.0f, 15.0f, 18.0f).top());
        gloomScrollPane0.setScrollingDisabled(true, false);
        gloomScrollPane0.setForceScroll(false, true);
        gloomScrollPane0.setFlickScrollTapSquareSize(35.0f);
        Table table0 = new Table();
        table0.defaults().space(10.0f);
        int v = intArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            TextButton textButton0 = App.textButton((intArray0.get(v1) == -1 ? "All" : Integer.toString(intArray0.get(v1)))).align(8).create();
            textButton0.pad(0.0f, 20.0f, 0.0f, 20.0f);
            textButton0.getLabelCell().height(80.0f);
            textButton0.invalidate();
            table0.add(textButton0);
        }
        TextButton textButton1 = App.textButton("Send to Bottom").align(8).create();
        textButton1.pad(0.0f, 20.0f, 0.0f, 20.0f);
        textButton1.getLabelCell().height(80.0f);
        textButton1.invalidate();
        textButton1.setDisabled(true);
        TextButton textButton2 = App.textButton("Remove").align(8).create();
        textButton2.pad(0.0f, 20.0f, 0.0f, 20.0f);
        textButton2.getLabelCell().height(80.0f);
        textButton2.invalidate();
        textButton2.setDisabled(true);
        Table table1 = new Table();
        table1.defaults().space(10.0f);
        table1.add(textButton1);
        if(z) {
            table1.add(textButton2);
        }
        this.actions = new Menu();
        this.actions.showArrow = false;
        this.actions.padLeft(30.0f).defaults().spaceRight(15.0f);
        if(table0.hasChildren()) {
            this.actions.add(new Label("Reveal:", App.skin, "fancyLargeOutline", Color.WHITE)).right();
            this.actions.add(table0).left().padRight(10.0f).row();
        }
        this.actions.add(new Label("Selected:", App.skin, "fancyLargeOutline", Color.WHITE)).right();
        this.actions.add(table1).left().row();
        if(!App.gloom.hasCharacterClass(CharacterClass.Diviner) && App.gloom.hasCharacters() || App.state.canDraw) {
            this.padTop(80.0f);
        }
        else {
            this.add(this.actions).colspan(2).padTop(12.0f).padBottom(12.0f).row();
        }
        Table table2 = new Table().background(App.skin.newDrawable("rounded-top", new Color(0.0f, 0.0f, 0.0f, 0.55f)));
        table2.pad(0.0f);
        table2.add(deckDialog$20).fill().minWidth(f);
        table2.add(gloomScrollPane0).fill().minWidth(f1);
        this.add(table2);
        this.setBackground(App.skin.newDrawable("white", new Color(0.0f, 0.0f, 0.0f, 0.5f)));
        this.setTouchable(Touchable.enabled);
        this.setFillParent(true);
        this.getColor().a = 0.0f;
        this.addAction(Actions.fadeIn(0.3f));
        App.stage.addActor(this);
        com.esotericsoftware.gloomhavenhelper.DeckDialog.3 deckDialog$30 = new InputListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.InputListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                inputEvent0.stop();
                return true;
            }
        };
        deckDialog$20.addListener(deckDialog$30);
        gloomScrollPane0.addListener(deckDialog$30);
        this.actions.setTouchable(Touchable.enabled);
        this.actions.addListener(deckDialog$30);
        this.addListener(new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                DeckDialog.this.hide();
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean isOver(Actor actor0, float f, float f1) {
                return this.inTapSquare(f, f1);
            }
        });
        table0.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(DeckDialog.this.shown.isEmpty()) {
                    return;
                }
                String s = ((TextButton)actor0).getText().toString();
                int v = s.equals("All") ? array0.size : ((int)Integer.valueOf(s));
                for(int v1 = 0; v1 < v; ++v1) {
                    int v2 = array0.size - 1 - v1;
                    if(v2 < 0) {
                        break;
                    }
                    if(!DeckDialog.this.shown.get(v2)) {
                        DeckDialog.this.shown.set(v2, true);
                        Actor actor1 = DeckDialog.this.left.getChild(v2);
                        int v3 = actor1.getZIndex();
                        Card card0 = (Card)array0.get(v2);
                        Actor actor2 = DeckDialog.this.newFrontActor(card0);
                        DeckDialog.this.left.addActorAt(v3, actor2);
                        actor1.remove();
                    }
                }
            }
        });
        textButton1.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(DeckDialog.this.selected == null) {
                    return;
                }
                int v = DeckDialog.this.selected.getZIndex();
                if(v == -1) {
                    return;
                }
                DeckDialog.this.selected.remove();
                DeckDialog.this.left.addActorAt(0, DeckDialog.this.selected);
                Object object0 = array0.removeIndex(v);
                array0.insert(0, object0);
                boolean z = DeckDialog.this.shown.removeIndex(v);
                DeckDialog.this.shown.insert(0, z);
                App.state.changed();
            }
        });
        textButton2.addListener(new ChangeListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
            public void changed(ChangeEvent changeListener$ChangeEvent0, Actor actor0) {
                if(DeckDialog.this.selected == null) {
                    return;
                }
                int v = DeckDialog.this.selected.getZIndex();
                DeckDialog.this.selected.remove();
                DeckDialog.this.selected = null;
                textButton2.setDisabled(true);
                DeckDialog.this.shown.removeIndex(v);
                MonsterAbility monsterAbility0 = (MonsterAbility)array0.removeIndex(v);
                array0.shuffle();
                App.state.removedAbilities.add(monsterAbility0.id);
                App.state.changed();
                DeckDialog.this.resetRemaining();
                App.toast("Hand of Destiny has shuffled");
                App.toast("the monster abilities.");
            }
        });
        com.esotericsoftware.gloomhavenhelper.DeckDialog.8 deckDialog$80 = new ClickListener() {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public void clicked(InputEvent inputEvent0, float f, float f1) {
                int v = inputEvent0.getTarget().getZIndex();
                if(DeckDialog.this.shown.isEmpty()) {
                    return;
                }
                if(!DeckDialog.this.shown.get(v)) {
                    return;
                }
                DeckDialog.this.selected = DeckDialog.this.selected == inputEvent0.getTarget() ? null : inputEvent0.getTarget();
                boolean z = true;
                textButton1.setDisabled(DeckDialog.this.selected == null);
                TextButton textButton0 = textButton2;
                if(DeckDialog.this.selected != null) {
                    z = false;
                }
                textButton0.setDisabled(z);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean isOver(Actor actor0, float f, float f1) {
                return this.inTapSquare(f, f1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.ClickListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(inputEvent0.getTarget() == DeckDialog.this.selected) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            com.esotericsoftware.gloomhavenhelper.DeckDialog.8.this.val$leftScroll.cancel();
                        }
                    });
                }
                return super.touchDown(inputEvent0, f, f1, v, v1);
            }
        };
        this.left.addListener(deckDialog$80);
        com.esotericsoftware.gloomhavenhelper.DeckDialog.9 deckDialog$90 = new DragScrollListener(deckDialog$20) {
            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener
            public void drag(InputEvent inputEvent0, float f, float f1, int v) {
                super.drag(inputEvent0, f, f1, v);
                DeckDialog.this.updateSelectedPosition(f1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener
            public void dragStop(InputEvent inputEvent0, float f, float f1, int v) {
                super.dragStop(inputEvent0, f, f1, v);
                DeckDialog.this.left.invalidate();
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener
            protected boolean isAbove(float f) {
                return super.isAbove(f + DeckDialog.this.dragOffsetY + f1);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener
            protected boolean isBelow(float f) {
                return super.isBelow(f + DeckDialog.this.dragOffsetY);
            }

            @Override  // com.badlogic.gdx.scenes.scene2d.utils.DragListener
            public boolean touchDown(InputEvent inputEvent0, float f, float f1, int v, int v1) {
                if(inputEvent0.getTarget() != DeckDialog.this.selected) {
                    return false;
                }
                DeckDialog.this.dragOffsetY = DeckDialog.this.selected.getY() - f1;
                return super.touchDown(inputEvent0, f, f1, v, v1);
            }
        };
        this.dragListener = deckDialog$90;
        this.left.addListener(deckDialog$90);
        this.dragListener.setPadding(20.0f, 20.0f);
    }

    void updateSelectedPosition(float f) {
        this.selected.setY(f + this.dragOffsetY);
        int v = this.selected.getZIndex();
        if(v > 0) {
            Actor actor0 = this.left.getChild(v - 1);
            if(this.selected.getY() <= actor0.getY()) {
                this.selected.remove();
                this.left.addActorAt(v - 1, this.selected);
                boolean z = this.shown.removeIndex(v);
                this.shown.insert(v - 1, z);
                this.remaining.insert(v - 1, this.remaining.removeIndex(v));
                App.state.changed();
            }
        }
        if(v < this.remaining.size - 1) {
            Actor actor1 = this.left.getChild(v + 1);
            if(this.selected.getTop() >= actor1.getY()) {
                this.selected.remove();
                this.left.addActorAt(v + 1, this.selected);
                boolean z1 = this.shown.removeIndex(v);
                this.shown.insert(v + 1, z1);
                this.remaining.insert(v + 1, this.remaining.removeIndex(v));
                App.state.changed();
            }
        }
    }
}

