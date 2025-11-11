package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonValue;
import com.esotericsoftware.gloomhavenhelper.App;

public enum CharacterClass {
    Escort(0xD54829FF),
    Objective(0x349D00FF),
    Brute(0x37AED7FF),
    Cragheart(-2104804353),
    Mindthief(0x6279A5FF),
    Scoundrel(0xA3D464FF),
    Spellweaver(0xD0A1E2FF),
    Tinkerer(0xD2C4A3FF),
    Diviner(0xA0FFFFFF),
    BeastTyrant(0xB4765AFF),
    Berserker(0xF07C4EFF),
    Doomstalker(0x5BC5DCFF),
    Elementalist(0xDCDCDCFF),
    Nightshroud(0x7440D4FF),
    Plagueherald(0x72E0BEFF),
    Quartermaster(0xC8AC8BFF),
    Sawbones(0xD5CA9CFF),
    Soothsinger(0xDE7190FF),
    Summoner(0xEA75B8FF),
    Sunkeeper(0xFCE434FF),
    Bladeswarm(0xD6AC99FF),
    Demolitionist(0xE5A26FFF),
    RedGuard(0xD8585DFF),
    Voidwarden(0xE4E5E5FF),
    Hatchet(0x7BBBD6FF);

    public transient Color color;
    public transient String name;
    public static CharacterClass[] values;

    static {
        CharacterClass.values = (CharacterClass[])CharacterClass.$VALUES.clone();
    }

    private CharacterClass(int v1) {
        this.color = new Color(v1);
    }

    public int hpMax(int v) {
        switch(this) {
            case BeastTyrant: 
            case Soothsinger: 
            case Plagueherald: 
            case Spellweaver: 
            case Mindthief: 
            case Voidwarden: 
            case Elementalist: 
            case Diviner: {
                return v + 5;
            }
            case Doomstalker: 
            case Sawbones: 
            case Nightshroud: 
            case Summoner: 
            case Scoundrel: 
            case Demolitionist: 
            case Hatchet: 
            case Tinkerer: 
            case Bladeswarm: {
                return v + 7 + (v - 1) / 2;
            }
            case Berserker: 
            case Quartermaster: 
            case Sunkeeper: 
            case Brute: 
            case Cragheart: 
            case RedGuard: {
                return (v - 1) * 2 + 10;
            }
            case Escort: {
                return v * 2 + 4;
            }
            case Objective: {
                return App.gloom.playerCount() * v + 6;
            }
            default: {
                return 0;
            }
        }
    }

    public static void loadText(JsonValue jsonValue0) {
        CharacterClass.BeastTyrant.name = jsonValue0.getString("BeastTyrant");
        CharacterClass.Soothsinger.name = jsonValue0.getString("Soothsinger");
        CharacterClass.Plagueherald.name = jsonValue0.getString("Plagueherald");
        CharacterClass.Spellweaver.name = jsonValue0.getString("Spellweaver");
        CharacterClass.Mindthief.name = jsonValue0.getString("Mindthief");
        CharacterClass.Elementalist.name = jsonValue0.getString("Elementalist");
        CharacterClass.Diviner.name = jsonValue0.getString("Diviner");
        CharacterClass.Doomstalker.name = jsonValue0.getString("Doomstalker");
        CharacterClass.Sawbones.name = jsonValue0.getString("Sawbones");
        CharacterClass.Nightshroud.name = jsonValue0.getString("Nightshroud");
        CharacterClass.Summoner.name = jsonValue0.getString("Summoner");
        CharacterClass.Scoundrel.name = jsonValue0.getString("Scoundrel");
        CharacterClass.Tinkerer.name = jsonValue0.getString("Tinkerer");
        CharacterClass.Bladeswarm.name = jsonValue0.getString("Bladeswarm");
        CharacterClass.Berserker.name = jsonValue0.getString("Berserker");
        CharacterClass.Quartermaster.name = jsonValue0.getString("Quartermaster");
        CharacterClass.Sunkeeper.name = jsonValue0.getString("Sunkeeper");
        CharacterClass.Brute.name = jsonValue0.getString("Brute");
        CharacterClass.Cragheart.name = jsonValue0.getString("Cragheart");
        CharacterClass.Demolitionist.name = jsonValue0.getString("Demolitionist");
        CharacterClass.RedGuard.name = jsonValue0.getString("RedGuard");
        CharacterClass.Voidwarden.name = jsonValue0.getString("Voidwarden");
        CharacterClass.Hatchet.name = jsonValue0.getString("Hatchet");
        CharacterClass.Escort.name = jsonValue0.getString("Escort");
        CharacterClass.Objective.name = jsonValue0.getString("Objective");
    }

    @Override
    public String toString() {
        if(this.name == null) {
            this.name = this.name();
            if(this == CharacterClass.BeastTyrant) {
                this.name = "Beast Tyrant";
                return "Beast Tyrant";
            }
            if(this == CharacterClass.RedGuard) {
                this.name = "Red Guard";
            }
        }
        return this.name;
    }
}

