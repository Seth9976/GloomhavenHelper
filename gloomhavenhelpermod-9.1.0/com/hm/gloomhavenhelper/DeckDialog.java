package com.hm.gloomhavenhelper;

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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragScrollListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.BooleanArray;
import com.badlogic.gdx.utils.IntArray;
import com.hm.gloomhavenhelper.model.AttackModifier;
import com.hm.gloomhavenhelper.model.MonsterAbility;
import com.hm.gloomhavenhelper.model.MonsterAbilityDeck;
import com.hm.gloomhavenhelper.util.Card;
import com.hm.gloomhavenhelper.util.GloomScrollPane;
import com.hm.gloomhavenhelper.util.Menu;

public abstract class DeckDialog extends Table {
   Array remaining;
   Array discard;
   float cardWidth;
   float cardHeight;
   final VerticalGroup right = new VerticalGroup();
   Menu actions;
   final BooleanArray shown = new BooleanArray();
   float selectedX;
   float selectedY;
   float selectedW;
   float selectedH;
   Actor selected;
   DragScrollListener dragListener;
   float dragOffsetY;
   final VerticalGroup left = new VerticalGroup() {
      @Override
      public void layout() {
         float y = DeckDialog.this.selected != null ? DeckDialog.this.selected.getY() : 0.0F;
         super.layout();
         if (DeckDialog.this.selected != null && DeckDialog.this.dragListener.isDragging()) {
            DeckDialog.this.selected.setY(y);
         }
      }

      @Override
      protected void drawChildren(Batch batch, float parentAlpha) {
         super.drawChildren(batch, parentAlpha);
         if (DeckDialog.this.selected != null) {
            DeckDialog.this.getSelectedDrawable()
               .draw(
                  batch,
                  DeckDialog.this.selected.getX() - DeckDialog.this.selectedX,
                  DeckDialog.this.selected.getY() - DeckDialog.this.selectedY,
                  DeckDialog.this.cardWidth + DeckDialog.this.selectedW,
                  DeckDialog.this.cardHeight + DeckDialog.this.selectedH
               );
         }
      }
   };

   protected void show(
      float cardWidth,
      final float cardHeight,
      float selectedX,
      float selectedY,
      float selectedW,
      float selectedH,
      final Array remaining,
      Array discard,
      IntArray reveal,
      boolean canRemove
   ) {
      this.cardWidth = cardWidth;
      this.cardHeight = cardHeight;
      this.selectedX = selectedX;
      this.selectedY = selectedY;
      this.selectedW = selectedW;
      this.selectedH = selectedH;
      this.remaining = remaining;
      this.discard = discard;
      this.resetRemaining();
      if (discard.isEmpty()) {
         this.right.addActor(new DeckDialog.FixedSizeImage(this.getEmptyDrawable(), cardWidth, cardHeight));
      }

      for (Card card : discard) {
         this.right.addActor(this.newFrontActor(card));
      }

      this.left.reverse().top().space(10.0F);
      this.right.reverse().top().space(10.0F);
      final ScrollPane leftScroll = new GloomScrollPane(new Container(this.left).pad(17.0F, 18.0F, 15.0F, 18.0F).top()) {
         @Override
         public void act(float delta) {
            float y = this.getVisualScrollY();
            super.act(delta);
            y -= this.getVisualScrollY();
            if (y != 0.0F && DeckDialog.this.dragListener.isDragging()) {
               DeckDialog.this.updateSelectedPosition(DeckDialog.this.selected.getY() - DeckDialog.this.dragOffsetY + y);
            }
         }
      };
      leftScroll.setScrollingDisabled(true, false);
      leftScroll.setForceScroll(false, true);
      leftScroll.setFlickScrollTapSquareSize(35.0F);
      ScrollPane rightScroll = new GloomScrollPane(new Container(this.right).pad(17.0F, 0.0F, 15.0F, 18.0F).top());
      rightScroll.setScrollingDisabled(true, false);
      rightScroll.setForceScroll(false, true);
      rightScroll.setFlickScrollTapSquareSize(35.0F);
      Table revealButtons = new Table();
      revealButtons.defaults().space(10.0F);
      int i = 0;

      for (int n = reveal.size; i < n; i++) {
         String text;
         if (reveal.get(i) == -1) {
            text = "All";
         } else {
            text = Integer.toString(reveal.get(i));
         }

         TextButton button = App.textButton(text).align(8).create();
         button.pad(0.0F, 20.0F, 0.0F, 20.0F);
         button.getLabelCell().height(80.0F);
         button.invalidate();
         revealButtons.add(button);
      }

      final TextButton sendToBottomButton = App.textButton("Send to Bottom").align(8).create();
      sendToBottomButton.pad(0.0F, 20.0F, 0.0F, 20.0F);
      sendToBottomButton.getLabelCell().height(80.0F);
      sendToBottomButton.invalidate();
      sendToBottomButton.setDisabled(true);
      final TextButton removeButton = App.textButton("Remove").align(8).create();
      removeButton.pad(0.0F, 20.0F, 0.0F, 20.0F);
      removeButton.getLabelCell().height(80.0F);
      removeButton.invalidate();
      removeButton.setDisabled(true);
      Table selectedButtons = new Table();
      selectedButtons.defaults().space(10.0F);
      selectedButtons.add(sendToBottomButton);
      if (canRemove) {
         selectedButtons.add(removeButton);
      }

      this.actions = new Menu();
      this.actions.showArrow = false;
      this.actions.padLeft(30.0F).defaults().spaceRight(15.0F);
      if (revealButtons.hasChildren()) {
         this.actions.add(new Label("Reveal:", App.skin, "fancyLargeOutline", Color.WHITE)).right();
         this.actions.add(revealButtons).left().padRight(10.0F).row();
      }

      this.actions.add(new Label("Selected:", App.skin, "fancyLargeOutline", Color.WHITE)).right();
      this.actions.add(selectedButtons).left().row();
      if ((App.gloom.hasCharacterClass("Diviner") || !App.gloom.hasCharacters()) && !App.state.canDraw) {
         this.add(this.actions).colspan(2).padTop(12.0F).padBottom(12.0F).row();
      } else {
         this.padTop(80.0F);
      }

      Table table = new Table().background(App.skin.newDrawable("rounded-top", new Color(0.0F, 0.0F, 0.0F, 0.55F)));
      table.pad(0.0F);
      table.add(leftScroll).fill().minWidth(cardWidth);
      table.add(rightScroll).fill().minWidth(cardHeight);
      this.add(table);
      this.setBackground(App.skin.newDrawable("white", new Color(0.0F, 0.0F, 0.0F, 0.5F)));
      this.setTouchable(Touchable.enabled);
      this.setFillParent(true);
      this.getColor().a = 0.0F;
      this.addAction(Actions.fadeIn(0.3F));
      App.stage.addActor(this);
      InputListener eatTouch = new InputListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            event.stop();
            return true;
         }
      };
      leftScroll.addListener(eatTouch);
      rightScroll.addListener(eatTouch);
      this.actions.setTouchable(Touchable.enabled);
      this.actions.addListener(eatTouch);
      this.addListener(new ClickListener() {
         @Override
         public void clicked(InputEvent event, float x, float y) {
            DeckDialog.this.hide();
         }

         @Override
         public boolean isOver(Actor actor, float x, float y) {
            return this.inTapSquare(x, y);
         }
      });
      revealButtons.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (!DeckDialog.this.shown.isEmpty()) {
               String text = ((TextButton)actor).getText().toString();
               int count = text.equals("All") ? remaining.size : Integer.valueOf(text);

               for (int ix = 0; ix < count; ix++) {
                  int index = remaining.size - 1 - ix;
                  if (index < 0) {
                     break;
                  }

                  if (!DeckDialog.this.shown.get(index)) {
                     DeckDialog.this.shown.set(index, true);
                     Actor back = DeckDialog.this.left.getChild(index);
                     DeckDialog.this.left.addActorAt(back.getZIndex(), DeckDialog.this.newFrontActor((Card)remaining.get(index)));
                     back.remove();
                  }
               }
            }
         }
      });
      sendToBottomButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (DeckDialog.this.selected != null) {
               int index = DeckDialog.this.selected.getZIndex();
               if (index != -1) {
                  DeckDialog.this.selected.remove();
                  DeckDialog.this.left.addActorAt(0, DeckDialog.this.selected);
                  remaining.insert(0, remaining.removeIndex(index));
                  DeckDialog.this.shown.insert(0, DeckDialog.this.shown.removeIndex(index));
                  App.state.changed();
               }
            }
         }
      });
      removeButton.addListener(new ChangeListener() {
         @Override
         public void changed(ChangeListener.ChangeEvent event, Actor actor) {
            if (DeckDialog.this.selected != null) {
               int index = DeckDialog.this.selected.getZIndex();
               DeckDialog.this.selected.remove();
               DeckDialog.this.selected = null;
               removeButton.setDisabled(true);
               DeckDialog.this.shown.removeIndex(index);
               MonsterAbility card = (MonsterAbility)remaining.removeIndex(index);
               remaining.shuffle();
               App.state.removedAbilities.add(card.id);
               App.state.changed();
               DeckDialog.this.resetRemaining();
               App.toast("Hand of Destiny has shuffled");
               App.toast("the monster abilities.");
            }
         }
      });
      this.left.addListener(new ClickListener() {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (event.getTarget() == DeckDialog.this.selected) {
               Gdx.app.postRunnable(new Runnable() {
                  @Override
                  public void run() {
                     leftScroll.cancel();
                  }
               });
            }

            return super.touchDown(event, x, y, pointer, button);
         }

         @Override
         public boolean isOver(Actor actor, float x, float y) {
            return this.inTapSquare(x, y);
         }

         @Override
         public void clicked(InputEvent event, float x, float y) {
            int index = event.getTarget().getZIndex();
            if (!DeckDialog.this.shown.isEmpty()) {
               if (DeckDialog.this.shown.get(index)) {
                  DeckDialog.this.selected = DeckDialog.this.selected == event.getTarget() ? null : event.getTarget();
                  sendToBottomButton.setDisabled(DeckDialog.this.selected == null);
                  removeButton.setDisabled(DeckDialog.this.selected == null);
               }
            }
         }
      });
      this.left.addListener(this.dragListener = new DragScrollListener(leftScroll) {
         @Override
         public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (event.getTarget() != DeckDialog.this.selected) {
               return false;
            } else {
               DeckDialog.this.dragOffsetY = DeckDialog.this.selected.getY() - y;
               return super.touchDown(event, x, y, pointer, button);
            }
         }

         @Override
         public void drag(InputEvent event, float x, float y, int pointer) {
            super.drag(event, x, y, pointer);
            DeckDialog.this.updateSelectedPosition(y);
         }

         @Override
         public void dragStop(InputEvent event, float x, float y, int pointer) {
            super.dragStop(event, x, y, pointer);
            DeckDialog.this.left.invalidate();
         }

         @Override
         protected boolean isAbove(float y) {
            return super.isAbove(y + DeckDialog.this.dragOffsetY + cardHeight);
         }

         @Override
         protected boolean isBelow(float y) {
            return super.isBelow(y + DeckDialog.this.dragOffsetY);
         }
      });
      this.dragListener.setPadding(20.0F, 20.0F);
   }

   void hide() {
      this.addAction(Actions.sequence(Actions.fadeOut(0.3F), Actions.removeActor()));
   }

   void resetRemaining() {
      this.left.clearChildren();
      this.shown.clear();
      if (this.remaining.isEmpty()) {
         this.left.addActor(new DeckDialog.FixedSizeImage(this.getEmptyDrawable(), this.cardWidth, this.cardHeight));
      }

      for (Card card : this.remaining) {
         this.left.addActor(new DeckDialog.FixedSizeImage(this.getBackDrawable(), this.cardWidth, this.cardHeight));
         this.shown.add(false);
      }
   }

   void updateSelectedPosition(float y) {
      this.selected.setY(y + this.dragOffsetY);
      int index = this.selected.getZIndex();
      if (index > 0) {
         Actor before = this.left.getChild(index - 1);
         if (this.selected.getY() <= before.getY()) {
            this.selected.remove();
            this.left.addActorAt(index - 1, this.selected);
            this.shown.insert(index - 1, this.shown.removeIndex(index));
            this.remaining.insert(index - 1, this.remaining.removeIndex(index));
            App.state.changed();
         }
      }

      if (index < this.remaining.size - 1) {
         Actor after = this.left.getChild(index + 1);
         if (this.selected.getTop() >= after.getY()) {
            this.selected.remove();
            this.left.addActorAt(index + 1, this.selected);
            this.shown.insert(index + 1, this.shown.removeIndex(index));
            this.remaining.insert(index + 1, this.remaining.removeIndex(index));
            App.state.changed();
         }
      }
   }

   protected abstract Drawable getEmptyDrawable();

   protected abstract Drawable getBackDrawable();

   protected abstract Actor newFrontActor(Card var1);

   protected abstract Drawable getSelectedDrawable();

   public static class AttackModifierDeckDialog extends DeckDialog {
      Drawable back = App.drawable("attack/back", "attack/border");
      Drawable empty = App.drawable("attack/back-empty");
      Drawable selected = App.drawable("attack/selected");

      @Override
      protected Drawable getEmptyDrawable() {
         return this.empty;
      }

      @Override
      protected Drawable getBackDrawable() {
         return this.back;
      }

      protected Actor newFrontActor(AttackModifier card) {
         return new DeckDialog.FixedSizeImage(App.drawable("attack/" + card, "attack/border"), this.cardWidth, this.cardHeight);
      }

      @Override
      protected Drawable getSelectedDrawable() {
         return this.selected;
      }

      protected void show() {
         this.show(
            158.0F, 107.0F, 9.0F, 9.0F, 18.0F, 18.0F, App.state.attackModifiers, App.state.attackModifiersDiscard, IntArray.with(2, 3, 4, 5, 6, 7, 8, 9), false
         );
         final TextButton badOmenButton = App.textButton("Bad Omen" + (App.state.badOmen > 0 ? ": " + App.state.badOmen : "")).align(8).create();
         badOmenButton.pad(0.0F, 20.0F, 0.0F, 20.0F);
         badOmenButton.getLabelCell().height(80.0F);
         badOmenButton.invalidate();
         int minusOnes = App.state.count(AttackModifier.minus1, true) - 5;
         final TextButton enfeeblingHexButton = App.textButton("Enfeebling Hex" + (minusOnes > 0 ? ": " + minusOnes : "")).align(8).create();
         enfeeblingHexButton.pad(0.0F, 20.0F, 0.0F, 20.0F);
         enfeeblingHexButton.getLabelCell().height(80.0F);
         enfeeblingHexButton.invalidate();
         Table table = new Table();
         table.add(badOmenButton).space(20.0F);
         table.add(enfeeblingHexButton);
         this.actions.add(table).colspan(2).padRight(20.0F);
         badOmenButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.state.badOmen = 6;
               App.state.changed();
               badOmenButton.setText("Bad Omen: " + App.state.badOmen);
               App.toast("Bad Omen will be applied the");
               App.toast("next 6 times a null is shuffled.");
            }
         });
         enfeeblingHexButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
               App.state.add(AttackModifier.minus1);
               App.state.attackModifiers.shuffle();
               App.state.changed();
               int minusOnes = App.state.count(AttackModifier.minus1, true) - 5;
               enfeeblingHexButton.setText("Enfeebling Hex: " + minusOnes);
               AttackModifierDeckDialog.this.resetRemaining();
               App.toast("Enfeebling Hex has shuffled the");
               App.toast("monster attack modifiers.");
            }
         });
      }
   }

   static class FixedSizeImage extends Image {
      private final float width;
      private final float height;

      public FixedSizeImage(Drawable drawable, float width, float height) {
         super(drawable);
         this.width = width;
         this.height = height;
      }

      @Override
      public float getPrefWidth() {
         return this.width;
      }

      @Override
      public float getPrefHeight() {
         return this.height;
      }
   }

   public static class MonsterAbilityDeckDialog extends DeckDialog {
      final MonsterRow row;
      final Drawable back = App.drawable("psd/monsterAbility-back");
      final Drawable empty = App.drawable("monsterAbility-back-empty");
      final Drawable selected = App.drawable("monsterAbility-selected");

      public MonsterAbilityDeckDialog(MonsterRow row) {
         this.row = row;
      }

      @Override
      protected Drawable getEmptyDrawable() {
         return this.empty;
      }

      @Override
      protected Drawable getBackDrawable() {
         return this.back;
      }

      protected Actor newFrontActor(MonsterAbility ability) {
         MonsterAbilityCard card = new MonsterAbilityCard(this.row, false);
         card.forceAbility = ability;
         return card;
      }

      @Override
      protected Drawable getSelectedDrawable() {
         return this.selected;
      }

      public void show() {
         MonsterAbilityDeck deck = this.row.getAbilityDeck();
         Array discard = new Array(deck.abilitiesDiscard);
         IntArray reveal = new IntArray();
         if (this.row.data.treatLikeABoss()) {
            reveal.add(2, 3);
         } else {
            reveal.add(1, 2, 3, 4);
            reveal.add(5);
         }

         reveal.add(-1);
         this.show(441.0F, 209.0F, 18.0F, 15.0F, 36.0F, 34.0F, deck.abilities, discard, reveal, true);
      }
   }
}
