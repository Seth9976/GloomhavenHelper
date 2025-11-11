# gloomhavenhelpermod

##Change Log version 9.1.0 (Second modded version)
- Crimson Scales extras campaigns
    - 13 scenarios dealing with 3 extra classes. Added as 3 separate campaigns.
    - The solo scenarios didn't have numbers but they are put in the order they came. For example the AA Solo is nr 3.
    - You will need Ancient Artillery and Stone Golem ability decks for QA Solo scenario.
    - Yuo will need the Boss ability deck for RM2.
- Line separating actions on the monster ability cards. Applied for Crimson Scales and Jaws of the Lion.
  - A lot of work went into making the layouts work. Please enjoy.
- Monster ability card titles. 
  - Has an option in setting. 
  - Will make the cards taller.
  - Known issue: all monsters are removed on switch.
  - Only Jaws of the Lion monsters (and a couple of Crimson Scales monsters) have titles.
- Crimson Scales Conditions added after all.
    - wound 2 and poison 2,3,4 for monsters. Only visible when a certain class is present.
    - rupture for monsters and players
    - rupture and wound 2 greyed out if monster immune to wound
    - poison 2-4 greyed out if monster immune to poison
    - chill and infect for players
    - Didn't add counter for chill. You will need to keep track yourselves. (or use the coin counter)
- Frosthaven placeholders removed. Since this is the last update.    

A heavily modified version of the one and only(ish) [Gloomhaven Helper](http://esotericsoftware.com/gloomhaven-helper)
by esoteric Software.

Key Features:
  - All of Crimson scales classes, monsters, scenarios and special rules
      - Even the extra 13 scenarios.
  - Starting Classes of Frosthaven
  - Data driven character classes and campaigns
  - Frosthaven features:
      - new hazardous terrain calculations
      - solo level calculation.
      - new conditions: ward, brittle, bane, impair

To facilitate these modifications the java code has been reverse engineered and put
into a new project. As such there are bound to be subtle bugs here and there.
Please report any bugs to royalhasse@gmail.com. Mark the subject as ghhmod, and preferably supply detailed reproduction steps.


##Note:
- I am no paint shop wizard. If you are disturbed by pixel imperfection or off-colors
or just like to have more polish, you are very welcome to submit fixes.
- Frosthaven monsters and hidden classes are placeholders.
- This is a work in progress. Check the 'Yet to be done' section for upcoming features
- A small change in design: From now on, named monsters are treated as bosses in the layout.
This means they will have resistances visible, and any stat and attribute changes visible.
So far this is added for Crimson Scales, but I may backport this feature to Base Gloomhaven and Forgotten Circles.


## Credits:
- Huge thanks to **Esoteric software** for making a great app to start with and for allowing mods.
- Also great thanks to **u/FrostyPrinting** for the Crimson Scales graphics and base monster and scenario data.
- And **Isaac Childres** for the best board game.
- And my crazy cat **Tevildo** that passed away many years ago. You would have fit well in that big box.

## Known issues:
- only English language supported currently.
- incompatible with earlier saves.
     - If it crashed on startup you may need to remove file from your user/.ghh folder.
- named monsters as bosses use same standees as normal monster. If you play with randomized standee numbers
there might be collisions.
  
## Added features:
- Crimson scales and Frosthaven classes added.
    - Frosthaven locked classes are placeholders
- Frosthaven new conditions added
    - Ward, Brittle, Bane, Impair
- Frosthaven solo difficulty calculation option
    - changes the recommended level circle when checked.
- Frosthaven hazardous terrain calculation
    - shown next to trap value after the /
- More conditions reminders
    - Ward, Brittle, Regenerate flash on damage.
    - Bane and Brittle flash on heal.
    - Bane flash end of turn.
    - Bane and Impair expire, if that setting is on.
- Crimson Scales campaign added.
    - Including Sections and special rules.
      - Including 26 special hidden monsters and bosses
    - Many special monsters use base game standees, 
      but as they are added in sections and are supposed to be a surprise, are not in the 
      scenario to start when you 'set scenario'
      Please use the scenario book to prepare monster standees.
      If there is a wish for it, adding a 'CS no sections' campaign can be considered.
    - You will need the monster ability decks when attempting scenario 53.
    - You will need the Stone Golem ability deck for scenario 55  
    - Decided against adding the conditions for following reasons:
      - Would clog up already busy menus.
      - Rupture only used by one class.
      - Wound 2 and Poison 2-4 only used by one class. And one item.
      - Toxicity only used in 3 scenarios.
      - Chill only used in 3 scenarios.
      - Chill furthermore stacks which would mean more special code to support.
- For Modders: Editions in data file.
    - General note: the file to modify is data-en.json. It can be copied to outside .jar file and will
      override from there.
    - You can now more easily add campaigns without overwriting the base campaign
    - All campaigns listed to choose from in set scenario menu.
    - Editions are defined in campaign data.    
- For modders: Character Class data in data file.
    - Adding any amount of classes is now possible. Classes grouped by edition.
    - If you want to add a class not from a campaign, set the edition to 'na'
    - Character graphics:
      - add in folders outside the ghh.jar file
      - icon:
        - images/class-icons/className.png (approximately w:60-90,h:60-90 pixels)
      - background: images/separate/class-bgs/className.png (1103x153 pixels)
      - Alternatively, modify skin files inside jar instead.
- For modders: Support any formula for monster and objective/escort health  
    - Any mathematical formula including C: number of characters or L: scenario level
    - any number of parentheses, +-*/. (even sqrt, sin, cos, tan. In case that would ever be needed)


## Never to be done :(:
- Named summons with graphics
    - Add list with all available class summons on the add summons menu.
    - Prefill the summon base stats on select.
    - If the class has no summon standees, user have to choose graphics and standee number as usual
    - Summon added when choosing standee number.
    - Standee number not shown if summon has graphics and only one standee
    - Graphics shown on left side of the summon monster box (evaluate if this is visual enough)
    - Show name somewhere (summon stats menu at least)
- More generalized scenario special rules in data
- A reminder when a specified round number is reached.
    - If special rule on certain round: flash round number on the turn button and/or rule text as floating text (toast)
- refactor language support to be in separate files from general data.
    - Will lessen the amount of duplicated data and make it easier to continue supporting languages.
    - Negative: might make it harder to migrate data from other mods.
- Add community campaigns, Seeker of Xorn, etc.
- Support overriding and importing images and other data from external directory
    - For ease of use in modding. 
    - This already works, however the image directory structure is not well documented.
    - Unpacking all images from atlas and have them be available outside would make this better.
- Visualize special scenario rules somehow and maybe trap types as well.
    - Will need some consideration how it should be done.
    - Inspired by https://gloomhaven.smigiel.us/
- Frosthaven Data
    - When available add monsters, hidden classes and scenario data.
