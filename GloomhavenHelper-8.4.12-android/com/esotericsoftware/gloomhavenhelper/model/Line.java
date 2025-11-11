package com.esotericsoftware.gloomhavenhelper.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.esotericsoftware.gloomhavenhelper.App;
import com.esotericsoftware.gloomhavenhelper.MonsterAbilityCard;

public class Line {
    public static abstract class LinePart {
        public float space;
        public float width;
        public float x;
        public float y;

        public abstract void draw(Batch arg1, Line arg2, float arg3, float arg4, MonsterData arg5, float arg6);
    }

    public static class RegionPart extends LinePart {
        TextureRegion flying;
        TextureRegion region;

        @Override  // com.esotericsoftware.gloomhavenhelper.model.Line$LinePart
        public void draw(Batch batch0, Line line0, float f, float f1, MonsterData monsterData0, float f2) {
            if(monsterData0 != null && monsterData0.flying) {
                TextureRegion textureRegion0 = this.flying;
                if(textureRegion0 != null) {
                    batch0.draw(textureRegion0, f + line0.x + this.x - 3.0f, f1 + line0.y + this.y);
                    return;
                }
            }
            batch0.draw(this.region, f + line0.x + this.x, f1 + line0.y + this.y);
        }
    }

    public static class TextPart extends LinePart {
        final BitmapFontCache cache;
        private final Color color;
        public final GlyphLayout layout;
        public final String text;

        public TextPart(String s, BitmapFont bitmapFont0, Color color0) {
            this.color = new Color();
            this.layout = new GlyphLayout();
            this.text = s;
            this.color.set(color0);
            this.cache = new BitmapFontCache(bitmapFont0);
        }

        @Override  // com.esotericsoftware.gloomhavenhelper.model.Line$LinePart
        public void draw(Batch batch0, Line line0, float f, float f1, MonsterData monsterData0, float f2) {
            this.cache.setPosition(f + line0.x + this.x, f1 + line0.y + this.y + this.layout.height);
            this.color.a = f2;
            this.cache.setColors(this.color.toFloatBits());
            this.cache.draw(batch0);
        }
    }

    private static String and;
    public BitmapFont font;
    public float height;
    public final String line;
    public final Array parts;
    public float spaceTop;
    private static final ObjectMap tokenText;
    public float width;
    public float x;
    public float y;

    static {
        Line.tokenText = new ObjectMap();
    }

    public Line(String s, String s1, int v, Color color0, float f, int v1) {
        this.parts = new Array();
        if(s != null) {
            this.line = s;
            if(s.charAt(0) == 33) {
                s = s.substring(1);
            }
            float f1 = 15.0f;
            float f2 = 10.0f;
            if(s.charAt(0) == 42) {
                this.font = color0 == Color.BLACK ? App.plainSmall : App.plainSmallOutline;
                s = s.substring(1);
                this.height = 10.0f;
                if(v >= 0) {
                    f2 = 27.0f;
                }
                this.spaceTop = f2;
                if(s1.equals("NORMAL:") || s1.equals("ELITE:") || s1.startsWith("*")) {
                    this.spaceTop -= 16.0f;
                }
            }
            else if(s.charAt(0) == 94) {
                this.font = color0 == Color.BLACK ? App.plainMedium : App.plainMediumOutline;
                s = s.substring(1);
                this.height = 15.0f;
                this.spaceTop = 12.0f;
                if(v == 62) {
                    --this.spaceTop;
                }
                if(v == 0xAB) {
                    this.spaceTop -= 2.0f;
                }
                if(v == 0xB3) {
                    --this.spaceTop;
                }
            }
            else {
                this.font = App.plainLargeOutline;
                this.height = 18.0f;
                if(!s1.endsWith(Line.and)) {
                    f1 = 24.0f;
                }
                this.spaceTop = f1;
                switch(v) {
                    case 62: {
                        this.spaceTop -= 7.0f;
                        break;
                    }
                    case 0xAB: {
                        this.spaceTop -= 6.0f;
                        break;
                    }
                    case 0xAC: {
                        this.spaceTop -= 7.0f;
                        break;
                    }
                    case 0xAE: {
                        this.spaceTop -= 7.0f;
                        break;
                    }
                    case 0xB3: {
                        this.spaceTop -= 3.0f;
                    }
                }
                if(v >= 375 && v <= 377) {
                    this.spaceTop -= 3.0f;
                }
                if(s.equals("ELITE:")) {
                    color0 = App.eliteGold;
                }
                if(s1.equals("NORMAL:") || s1.equals("ELITE:")) {
                    this.spaceTop -= 10.0f;
                }
            }
            if(s.indexOf(37) == -1) {
                this.addTextPart(s, color0, f, v1);
                return;
            }
            try {
                if(MonsterAbilityCard.statsPattern.matcher(s).matches()) {
                    int v2 = (s.indexOf(" + ") == -1 ? s.indexOf(" - ") : s.indexOf(" + ")) + 3;
                    int v3 = s.length();
                    while(v2 < v3) {
                        int v4 = s.charAt(v2);
                        if(v4 < 0x30 || v4 > 57) {
                            break;
                        }
                        ++v2;
                    }
                    if(v2 == v3) {
                        this.addParts(s, color0, v);
                        return;
                    }
                    this.addParts(s.substring(0, v2), color0, v);
                    this.addParts(s.substring(v2), color0, v);
                    return;
                }
                this.addParts(s, color0, v);
                return;
            }
            catch(Throwable throwable0) {
                throw new RuntimeException("Error parsing line: " + s, throwable0);
            }
        }
        throw new IllegalArgumentException("line cannot be null.");
    }

    private void addIconPart(String s, int v, Color color0) {
        boolean z = s.equals("move");
        RegionPart line$RegionPart0 = new RegionPart();
        line$RegionPart0.region = this.findRegion(s, color0);
        if(z) {
            line$RegionPart0.flying = this.findRegion("flying", color0);
        }
        line$RegionPart0.width = (float)line$RegionPart0.region.getRegionWidth();
        String s1 = (String)Line.tokenText.get(s, "");
        this.addTextPart(s1, color0, 0.0f, 0);
        float f = 6.0f;
        if(s1.length() <= 0 || !s1.equals(s1.toUpperCase())) {
            if(this.isSmall()) {
                line$RegionPart0.x = z ? 2.0f : 4.0f;
                line$RegionPart0.space = z ? 2.0f : 5.0f;
            }
            else if(this.isMedium()) {
                if(z) {
                    f = 5.0f;
                }
                line$RegionPart0.x = f;
                line$RegionPart0.space = z ? 5.0f : 8.0f;
            }
            else {
                line$RegionPart0.x = z ? 9.0f : 12.0f;
                line$RegionPart0.space = z ? 17.0f : 18.0f;
            }
        }
        else if(this.isSmall()) {
            line$RegionPart0.x = 4.0f;
            line$RegionPart0.space = 4.0f;
        }
        else {
            if(!this.isMedium()) {
            }
            line$RegionPart0.x = 6.0f;
            line$RegionPart0.space = 7.0f;
        }
        line$RegionPart0.width += line$RegionPart0.space;
        line$RegionPart0.x += this.width;
        line$RegionPart0.y = (float)Math.round((this.font.getData().capHeight - ((float)line$RegionPart0.region.getRegionHeight())) / 2.0f);
        switch(v) {
            case -46: {
                if(!App.config.isRussian()) {
                    if(s.equals("boss-aoe-sightless-eye-sp1")) {
                        line$RegionPart0.y -= 13.0f;
                    }
                    if(s.equals("boss-aoe-sightless-eye-sp2")) {
                        line$RegionPart0.x -= 80.0f;
                        line$RegionPart0.y -= 38.0f;
                    }
                }
                break;
            }
            case -40: {
                if(s.equals("boss-aoe-elder-drake-sp1")) {
                    line$RegionPart0.y -= (App.config.isRussian() ? 23.0f : 20.0f);
                }
                break;
            }
            case 202: {
                if(s.equals("aoe-triangle-2-side")) {
                    line$RegionPart0.y -= 17.0f;
                }
                break;
            }
            case 0xCF: {
                if(s.equals("aoe-circle")) {
                    line$RegionPart0.y -= 33.0f;
                }
            }
        }
        float f1 = 15.0f;
        if(v == 58 && s.equals("fire")) {
            this.height += 15.0f;
        }
        if(v == 0xE4 && s.equals("air")) {
            this.height += 25.0f;
        }
        if(v == 0x108 && s.equals("aoe-arc-3")) {
            line$RegionPart0.x -= 10.0f;
            line$RegionPart0.y += 15.0f;
        }
        if(s.equals("use")) {
            line$RegionPart0.x -= line$RegionPart0.width;
            this.width -= line$RegionPart0.width;
            if(this.isSmall()) {
                LinePart line$LinePart0 = (LinePart)this.parts.peek();
                line$LinePart0.x -= 5.0f;
                line$RegionPart0.x -= 5.0f;
                this.width -= 5.0f;
            }
        }
        if(this.font == App.plainLargeOutline && (z || s.equals("attack") || s.equals("heal") || s.equals("loot") || s.equals("shield") || s.equals("retaliate"))) {
            line$RegionPart0.y += 2.0f;
        }
        if(this.isMedium() && s.equals("retaliate")) {
            ++line$RegionPart0.y;
        }
        this.parts.add(line$RegionPart0);
        if(s.contains("aoe")) {
            line$RegionPart0.y -= 2.0f;
            switch(v) {
                case 3: {
                    line$RegionPart0.y -= 17.0f;
                    break;
                }
                case 4: {
                    line$RegionPart0.y -= 32.0f;
                    break;
                }
                case 7: {
                    line$RegionPart0.y -= 17.0f;
                    break;
                }
                case 0x20: 
                case 33: {
                    line$RegionPart0.x += 8.0f;
                    line$RegionPart0.y += 13.0f;
                    break;
                }
                case 41: 
                case 42: {
                    line$RegionPart0.x -= 8.0f;
                    line$RegionPart0.y -= 10.0f;
                    break;
                }
                case 55: {
                    this.height += 15.0f;
                    line$RegionPart0.y += 15.0f;
                    break;
                }
                case 58: {
                    --line$RegionPart0.y;
                    break;
                }
                case 68: 
                case 69: {
                    line$RegionPart0.y -= 12.0f;
                    break;
                }
                case 93: {
                    line$RegionPart0.y += 24.0f;
                    break;
                }
                case 180: {
                    if(!App.config.isRussian()) {
                        line$RegionPart0.y += 37.0f;
                    }
                    break;
                }
                case 0xE4: {
                    float f2 = line$RegionPart0.y;
                    if(s.equals("aoe-circle-with-side-black")) {
                        f1 = 3.0f;
                    }
                    line$RegionPart0.y = f2 + f1;
                }
            }
        }
        else {
            this.width += line$RegionPart0.width;
        }
        if(s.equals("use")) {
            this.addTextPart(":", color0, 0.0f, 0);
        }
    }

    private void addParts(String s, Color color0, int v) {
        int v1 = s.length();
        int v2 = 0;
        int v3 = 0;
        boolean z = false;
        while(v2 < v1) {
            if(s.charAt(v2) == 37) {
                if(z) {
                    this.addIconPart(s.substring(v3, v2), v, color0);
                    v3 = v2 + 1;
                    z = false;
                }
                else {
                    if(v2 - v3 > 0) {
                        this.addTextPart(s.substring(v3, v2), color0, 0.0f, 0);
                    }
                    v3 = v2 + 1;
                    z = true;
                }
            }
            ++v2;
        }
        if(v2 - v3 > 0) {
            this.addTextPart(s.substring(v3, v2), color0, 0.0f, 0);
        }
    }

    private void addTextPart(String s, Color color0, float f, int v) {
        if(s == null) {
            return;
        }
        TextPart line$TextPart0 = new TextPart(s, this.font, color0);
        if(f > 0.0f) {
            line$TextPart0.layout.setText(this.font, s, color0, f, v, true);
            this.height = Math.max(this.height, ((float)(line$TextPart0.layout.runs.size - 1)) * -this.font.getData().down + this.height);
            line$TextPart0.width = line$TextPart0.layout.width;
            line$TextPart0.layout.setText(this.font, s, color0, line$TextPart0.width, v, true);
        }
        else {
            line$TextPart0.layout.setText(this.font, s, color0, 0.0f, 8, false);
            line$TextPart0.x = this.width;
            line$TextPart0.width = line$TextPart0.layout.width;
        }
        if(line$TextPart0.width == 0.0f) {
            return;
        }
        this.width += line$TextPart0.width;
        if(s.equals(" ")) {
            return;
        }
        line$TextPart0.cache.setText(line$TextPart0.layout, 0.0f, 0.0f);
        this.parts.add(line$TextPart0);
        if(s.equals(":")) {
            if(this.isSmall()) {
                this.width -= 8.0f;
                return;
            }
            line$TextPart0.x -= 4.0f;
            this.width -= 6.0f;
        }
    }

    private static String calculateSpecial(String s, MonsterStats monsterStats0) {
        StringBuffer stringBuffer0 = new StringBuffer(s.length());
        int v = s.length();
        int v1 = 0;
        int v2 = 0;
        boolean z = false;
        while(v1 < v) {
            if(s.charAt(v1) == 37) {
                if(z) {
                    String s1 = s.substring(v2, v1);
                    stringBuffer0.append('%');
                    stringBuffer0.append(s1);
                    stringBuffer0.append('%');
                    if(v - v1 >= 4 && (s1.equals("move") || s1.equals("attack"))) {
                        int v3 = s.charAt(v1 + 2);
                        if(s.charAt(v1 + 1) == 0x20 && (v3 == 43 || v3 == 45) && s.charAt(v1 + 3) == 0x20) {
                            int v4;
                            for(v4 = v1 + 4; v4 < v; ++v4) {
                                int v5 = s.charAt(v4);
                                if(v5 < 0x30 || v5 > 57) {
                                    break;
                                }
                            }
                            if(v4 != v1 + 4) {
                                String s2 = Line.calculateSpecialValue(s1, s.substring(v1 + 4, v4), ((char)v3), monsterStats0);
                                if(s2 != null) {
                                    stringBuffer0.append(' ');
                                    stringBuffer0.append(s2);
                                    v1 = v4 - 1;
                                    v2 = v4;
                                    z = false;
                                    goto label_37;
                                }
                            }
                        }
                    }
                    v2 = v1 + 1;
                    z = false;
                }
                else {
                    if(v1 - v2 > 0) {
                        stringBuffer0.append(s.substring(v2, v1));
                    }
                    v2 = v1 + 1;
                    z = true;
                }
            }
        label_37:
            ++v1;
        }
        if(v1 - v2 > 0) {
            stringBuffer0.append(s.substring(v2, v1));
        }
        return stringBuffer0.toString();
    }

    private static String calculateSpecialValue(String s, String s1, char c, MonsterStats monsterStats0) {
        String s2;
        int v;
        try {
            v = App.parseInt(s1);
        }
        catch(NumberFormatException unused_ex) {
            return null;
        }
        if(c == 45) {
            v = -v;
        }
        if(s.equals("attack")) {
            s2 = monsterStats0.attack;
            return Integer.toString(App.parseInt(s2) + v);
        }
        if(s.equals("move")) {
            s2 = monsterStats0.move;
            try {
                return Integer.toString(App.parseInt(s2) + v);
            }
            catch(NumberFormatException unused_ex) {
                try {
                    monsterStats0.attack();
                    if(v > 9 || v < -9) {
                        throw new RuntimeException();
                    }
                    return "!!" + c + Math.abs(v);
                }
                catch(NumberFormatException unused_ex) {
                    return null;
                }
            }
        }
        throw new RuntimeException();
    }

    public void draw(Batch batch0, float f, float f1, MonsterData monsterData0, float f2) {
        for(Object object0: this.parts) {
            ((LinePart)object0).draw(batch0, this, f, f1, monsterData0, f2);
        }
    }

    private TextureRegion findRegion(String s, Color color0) {
        String s1 = color0 == Color.BLACK ? "-black" : "";
        if(this.isSmall()) {
            TextureRegion textureRegion0 = (TextureRegion)App.skin.optional("abilities/" + s + "-small" + s1, TextureRegion.class);
            if(textureRegion0 != null) {
                return textureRegion0;
            }
        }
        else if(this.isMedium()) {
            TextureRegion textureRegion1 = (TextureRegion)App.skin.optional("abilities/" + s + "-medium" + s1, TextureRegion.class);
            if(textureRegion1 != null) {
                return textureRegion1;
            }
            TextureRegion textureRegion2 = (TextureRegion)App.skin.optional("abilities/" + s + "-small" + s1, TextureRegion.class);
            if(textureRegion2 != null) {
                return textureRegion2;
            }
        }
        TextureRegion textureRegion3 = (TextureRegion)App.skin.optional("abilities/" + s + s1, TextureRegion.class);
        if(textureRegion3 != null) {
            return textureRegion3;
        }
        if(color0 != Color.BLACK) {
            throw new RuntimeException("Region not found: " + s);
        }
        return this.findRegion(s, Color.WHITE);
    }

    public boolean floatRight() {
        return this.line.charAt(0) == 33;
    }

    private boolean isMedium() {
        return this.font == App.plainMedium || this.font == App.plainMediumOutline;
    }

    private boolean isSmall() {
        return this.font == App.plainSmall || this.font == App.plainSmallOutline;
    }

    public static void layout(Array array0, float f, float f1, boolean z, int v) {
        int v1 = array0.size;
        for(int v2 = 0; v2 < v1; ++v2) {
            Line line0 = (Line)array0.get(v2);
            if(v2 != 0) {
                f1 -= line0.spaceTop;
            }
            f1 -= line0.height;
            line0.x = z ? f - ((float)Math.round(line0.width / 2.0f)) : f;
            line0.y += f1;
            if(v2 < v1 - 1 && ((Line)array0.get(v2 + 1)).floatRight()) {
                Line line1 = (Line)array0.get(v2 + 1);
                if(v2 + 1 >= v1 - 1 || !((Line)array0.get(v2 + 2)).floatRight()) {
                    float f4 = line0.width;
                    line0.width = line1.width + f4;
                    line0.x = z ? f - ((float)Math.round(line0.width / 2.0f)) - 8.0f : f;
                    line1.x += line0.x + f4;
                    if(line1.parts.first() instanceof TextPart) {
                        line1.x += 12.0f;
                    }
                    line1.y = f1;
                    ++v2;
                }
                else {
                    Line line2 = (Line)array0.get(v2 + 2);
                    float f2 = line0.width;
                    line0.width = Math.max(line1.width, line2.width) + f2;
                    line0.height = f2;
                    line1.height = 0.0f;
                    line2.height = 0.0f;
                    line0.x = z ? f - ((float)Math.round(line0.width / 2.0f)) - 8.0f : f;
                    line0.y = f1 - 12.0f;
                    line1.x += line0.x + f2;
                    if(line1.parts.first() instanceof TextPart) {
                        line1.x += 12.0f;
                    }
                    float f3 = line1.isSmall() ? ((float)(v >= 0 ? 12 : 8)) : 15.0f;
                    line1.y = f1 - 12.0f + f3;
                    line2.x += line0.x + f2;
                    if(line2.parts.first() instanceof TextPart) {
                        line2.x += 12.0f;
                    }
                    if(line2.line.equals("!^Self")) {
                        line2.x += (float)Math.round((line1.width - line2.width) / 2.0f);
                    }
                    line2.y = f1 - 12.0f - f3;
                    f1 = f1 - 12.0f - 12.0f;
                    v2 += 2;
                }
            }
        }
    }

    public static void loadAbilityLines(MonsterAbility monsterAbility0) {
        if(monsterAbility0.text.size == 0) {
            return;
        }
        try {
            String s = "";
            float f = 0.0f;
            for(Object object0: monsterAbility0.text) {
                Line line0 = new Line(((String)object0), s, monsterAbility0.id, Color.WHITE, 409.0f, 1);
                if(!line0.floatRight()) {
                    if(f > 0.0f) {
                        f += line0.spaceTop;
                    }
                    f += line0.height;
                }
                else if(monsterAbility0.lines.size >= 2 && ((Line)monsterAbility0.lines.peek()).floatRight()) {
                    f += 24.0f;
                }
                monsterAbility0.lines.add(line0);
                s = (String)object0;
            }
            if(monsterAbility0.id == 0x30) {
                f += 36.0f;
            }
            if(monsterAbility0.id == 62) {
                f += 5.0f;
            }
            if(monsterAbility0.id == 0x97) {
                f += 5.0f;
            }
            if(monsterAbility0.id == 0x9C || monsterAbility0.id == 0x9D) {
                f -= 24.0f;
            }
            if(monsterAbility0.id == 0xE0) {
                f -= 7.0f;
            }
            if(monsterAbility0.id == 0xE4) {
                f += 10.0f;
            }
            int v = monsterAbility0.id != 180 || !App.config.isRussian() ? 0xDA : 0xBC;
            if(monsterAbility0.id >= 375 && monsterAbility0.id <= 377) {
                v += 20;
            }
            Line.layout(monsterAbility0.lines, ((float)v), ((float)(Math.round(f / 2.0f) + 108)), true, monsterAbility0.id);
            monsterAbility0.text.clear();
        }
        catch(Throwable throwable0) {
            throw new RuntimeException("Error loading ability lines for card number: " + monsterAbility0.number, throwable0);
        }
    }

    public static void loadAttributeLines(MonsterData monsterData0, MonsterStats monsterStats0, boolean z) {
        if(monsterStats0.attributeText.size == 0) {
            return;
        }
        try {
            int v = 0;
            float f = 0.0f;
            for(Object object0: monsterStats0.attributeText) {
                String s = (String)object0;
                if(s.charAt(0) != 42) {
                    s = "^" + s;
                }
                Line line0 = new Line(s, "", -1, (z ? Color.WHITE : Color.BLACK), 148.0f, (z ? 8 : 16));
                if(v > 0 && line0.isSmall()) {
                    line0.spaceTop -= 11.0f;
                    line0.height += 10.0f;
                }
                if(v != 0) {
                    f -= line0.spaceTop;
                }
                if(!z) {
                    line0.x = Math.max(-18.0f, 130.0f - line0.width);
                }
                line0.y = f;
                monsterStats0.attributeLines.add(line0);
                f -= line0.height + 5.0f;
                ++v;
            }
            monsterStats0.attributeText.clear();
        }
        catch(Throwable throwable0) {
            throw new RuntimeException("Error loading attribute lines for monster: " + monsterData0.english, throwable0);
        }
    }

    public static void loadSpecialLines(MonsterData monsterData0, MonsterStats monsterStats0) {
        float f3;
        float f2;
        StringBuilder stringBuilder1;
        StringBuilder stringBuilder0;
        if(monsterStats0.specialText1.size == 0) {
            return;
        }
        try {
            float f = 212.0f;
            boolean z = App.config.isRussian();
            if(z) {
                f = 225.0f;
            }
            if(monsterData0.id == -55) {
                f = 999.0f;
            }
            if(monsterData0.id == -68) {
                f = 234.0f;
            }
            float f1 = f;
            for(Object object0: monsterStats0.specialText1) {
                String s = (String)object0;
                if(s.charAt(0) == 33) {
                    stringBuilder0 = new StringBuilder();
                    stringBuilder0.append("!*");
                    stringBuilder0.append(s.substring(1));
                }
                else {
                    stringBuilder0 = new StringBuilder();
                    stringBuilder0.append("*");
                    stringBuilder0.append(s);
                }
                String s1 = stringBuilder0.toString();
                Line line0 = new Line(s1, "", monsterData0.id, Color.WHITE, f, 8);
                monsterStats0.special1.add(line0);
                if(!z) {
                    f -= line0.height;
                }
                if(f < 150.0f) {
                    f = 150.0f;
                }
                Line line1 = new Line(Line.calculateSpecial(s1, monsterStats0), "", monsterData0.id, Color.WHITE, f1, 8);
                monsterStats0.specialCalculated1.add(line1);
                if(!z) {
                    f1 -= line1.height;
                }
                if(f1 < 150.0f) {
                    f1 = 150.0f;
                }
            }
            for(Object object1: monsterStats0.specialText2) {
                String s2 = (String)object1;
                if(s2.charAt(0) == 33) {
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("!*");
                    stringBuilder1.append(s2.substring(1));
                }
                else {
                    stringBuilder1 = new StringBuilder();
                    stringBuilder1.append("*");
                    stringBuilder1.append(s2);
                }
                String s3 = stringBuilder1.toString();
                Line line2 = new Line(s3, "", monsterData0.id, Color.WHITE, f, 8);
                monsterStats0.special2.add(line2);
                if(!z) {
                    f -= line2.height;
                }
                if(f < 150.0f) {
                    f = 150.0f;
                }
                Line line3 = new Line(Line.calculateSpecial(s3, monsterStats0), "", monsterData0.id, Color.WHITE, f1, 8);
                monsterStats0.specialCalculated2.add(line3);
                if(!z) {
                    f1 -= line3.height;
                }
                if(f1 < 150.0f) {
                    f1 = 150.0f;
                }
            }
            if(monsterStats0.notesText != null) {
                if(monsterData0.id == -39) {
                    f2 = f - 45.0f;
                    f3 = f1 - 45.0f;
                }
                else {
                    f2 = f;
                    f3 = f1;
                }
                monsterStats0.notes = new Line("*" + monsterStats0.notesText, "", monsterData0.id, Color.WHITE, f2, 8);
                monsterStats0.notesCalculated = new Line("*" + monsterStats0.notesText, "", monsterData0.id, Color.WHITE, f3, 8);
            }
            Line.layout(monsterStats0.special1, 0.0f, 0.0f, false, monsterData0.id);
            Line.layout(monsterStats0.special2, 0.0f, 0.0f, false, monsterData0.id);
            Line.layout(Array.with(new Line[]{monsterStats0.notes}), 0.0f, 0.0f, false, monsterData0.id);
            Line.layout(monsterStats0.specialCalculated1, 0.0f, 0.0f, false, monsterData0.id);
            Line.layout(monsterStats0.specialCalculated2, 0.0f, 0.0f, false, monsterData0.id);
            Line.layout(Array.with(new Line[]{monsterStats0.notesCalculated}), 0.0f, 0.0f, false, monsterData0.id);
            monsterStats0.specialText1.clear();
            monsterStats0.specialText2.clear();
        }
        catch(Throwable throwable0) {
            throw new RuntimeException("Error loading special lines for monster: " + monsterData0.english, throwable0);
        }
    }

    public static void loadTokens(JsonValue jsonValue0) {
        Line.tokenText.clear(51);
        String s = jsonValue0.getString("attack");
        Line.tokenText.put("attack", s);
        String s1 = jsonValue0.getString("move");
        Line.tokenText.put("move", s1);
        String s2 = jsonValue0.getString("range");
        Line.tokenText.put("range", s2);
        String s3 = jsonValue0.getString("heal");
        Line.tokenText.put("heal", s3);
        String s4 = jsonValue0.getString("target");
        Line.tokenText.put("target", s4);
        String s5 = jsonValue0.getString("shield");
        Line.tokenText.put("shield", s5);
        String s6 = jsonValue0.getString("loot");
        Line.tokenText.put("loot", s6);
        String s7 = jsonValue0.getString("retaliate");
        Line.tokenText.put("retaliate", s7);
        String s8 = jsonValue0.getString("jump");
        Line.tokenText.put("jump", s8);
        String s9 = jsonValue0.getString("stun");
        Line.tokenText.put("stun", s9);
        String s10 = jsonValue0.getString("wound");
        Line.tokenText.put("wound", s10);
        String s11 = jsonValue0.getString("disarm");
        Line.tokenText.put("disarm", s11);
        String s12 = jsonValue0.getString("immobilize");
        Line.tokenText.put("immobilize", s12);
        String s13 = jsonValue0.getString("poison");
        Line.tokenText.put("poison", s13);
        String s14 = jsonValue0.getString("invisible");
        Line.tokenText.put("invisible", s14);
        String s15 = jsonValue0.getString("strengthen");
        Line.tokenText.put("strengthen", s15);
        String s16 = jsonValue0.getString("muddle");
        Line.tokenText.put("muddle", s16);
        String s17 = jsonValue0.getString("regenerate");
        Line.tokenText.put("regenerate", s17);
        String s18 = jsonValue0.getString("push");
        Line.tokenText.put("push", s18);
        String s19 = jsonValue0.getString("pull");
        Line.tokenText.put("pull", s19);
        String s20 = jsonValue0.getString("pierce");
        Line.tokenText.put("pierce", s20);
        String s21 = jsonValue0.getString("curse");
        Line.tokenText.put("curse", s21);
        String s22 = jsonValue0.getString("bless");
        Line.tokenText.put("bless", s22);
        Line.and = jsonValue0.getString("and");
    }

    @Override
    public String toString() {
        return this.line;
    }
}

