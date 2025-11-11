package androidx.core.text.util;

import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestrictTo({Scope.LIBRARY_GROUP})
class FindAddress {
    static class ZipRange {
        int mException1;
        int mException2;
        int mHigh;
        int mLow;

        ZipRange(int v, int v1, int v2, int v3) {
            this.mLow = v;
            this.mHigh = v1;
            this.mException1 = v2;
            this.mException2 = v2;
        }

        boolean matches(String s) {
            int v = Integer.parseInt(s.substring(0, 2));
            return this.mLow <= v && v <= this.mHigh || v == this.mException1 || v == this.mException2;
        }
    }

    private static final String HOUSE_COMPONENT = "(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)";
    private static final String HOUSE_END = "(?=[,\"\'\t                　\n\u000B\f\r\u0085  ]|$)";
    private static final String HOUSE_POST_DELIM = ",\"\'\t                　\n\u000B\f\r\u0085  ";
    private static final String HOUSE_PRE_DELIM = ":,\"\'\t                　\n\u000B\f\r\u0085  ";
    private static final int MAX_ADDRESS_LINES = 5;
    private static final int MAX_ADDRESS_WORDS = 14;
    private static final int MAX_LOCATION_NAME_DISTANCE = 5;
    private static final int MIN_ADDRESS_WORDS = 4;
    private static final String NL = "\n\u000B\f\r\u0085  ";
    private static final String SP = "\t                　";
    private static final String WORD_DELIM = ",*•\t                　\n\u000B\f\r\u0085  ";
    private static final String WORD_END = "(?=[,*•\t                　\n\u000B\f\r\u0085  ]|$)";
    private static final String WS = "\t                　\n\u000B\f\r\u0085  ";
    private static final int kMaxAddressNameWordLength = 25;
    private static final Pattern sHouseNumberRe;
    private static final Pattern sLocationNameRe;
    private static final Pattern sStateRe;
    private static final ZipRange[] sStateZipCodeRanges;
    private static final Pattern sSuffixedNumberRe;
    private static final Pattern sWordRe;
    private static final Pattern sZipCodeRe;

    static {
        FindAddress.sStateZipCodeRanges = new ZipRange[]{new ZipRange(99, 99, -1, -1), new ZipRange(35, 36, -1, -1), new ZipRange(71, 72, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(85, 86, -1, -1), new ZipRange(90, 0x60, -1, -1), new ZipRange(80, 81, -1, -1), new ZipRange(6, 6, -1, -1), new ZipRange(20, 20, -1, -1), new ZipRange(19, 19, -1, -1), new ZipRange(0x20, 34, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(30, 0x1F, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(50, 52, -1, -1), new ZipRange(83, 83, -1, -1), new ZipRange(60, 62, -1, -1), new ZipRange(46, 0x2F, -1, -1), new ZipRange(66, 67, 73, -1), new ZipRange(40, 42, -1, -1), new ZipRange(70, 71, -1, -1), new ZipRange(1, 2, -1, -1), new ZipRange(20, 21, -1, -1), new ZipRange(3, 4, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(0x30, 49, -1, -1), new ZipRange(55, 56, -1, -1), new ZipRange(0x3F, 65, -1, -1), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(38, 39, -1, -1), new ZipRange(55, 56, -1, -1), new ZipRange(27, 28, -1, -1), new ZipRange(58, 58, -1, -1), new ZipRange(68, 69, -1, -1), new ZipRange(3, 4, -1, -1), new ZipRange(7, 8, -1, -1), new ZipRange(87, 88, 86, -1), new ZipRange(88, 89, 0x60, -1), new ZipRange(10, 14, 0, 6), new ZipRange(43, 45, -1, -1), new ZipRange(73, 74, -1, -1), new ZipRange(97, 97, -1, -1), new ZipRange(15, 19, -1, -1), new ZipRange(6, 6, 0, 9), new ZipRange(0x60, 0x60, -1, -1), new ZipRange(2, 2, -1, -1), new ZipRange(29, 29, -1, -1), new ZipRange(57, 57, -1, -1), new ZipRange(37, 38, -1, -1), new ZipRange(75, 0x4F, 87, 88), new ZipRange(84, 84, -1, -1), new ZipRange(22, 24, 20, -1), new ZipRange(6, 9, -1, -1), new ZipRange(5, 5, -1, -1), new ZipRange(98, 99, -1, -1), new ZipRange(53, 54, -1, -1), new ZipRange(24, 26, -1, -1), new ZipRange(82, 83, -1, -1)};
        FindAddress.sWordRe = Pattern.compile("[^,*•\t                　\n\u000B\f\r\u0085  ]+(?=[,*•\t                　\n\u000B\f\r\u0085  ]|$)", 2);
        FindAddress.sHouseNumberRe = Pattern.compile("(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?)(?:-(?:one|\\d+([a-z](?=[^a-z]|$)|st|nd|rd|th)?))*(?=[,\"\'\t                　\n\u000B\f\r\u0085  ]|$)", 2);
        FindAddress.sStateRe = Pattern.compile("(?:(ak|alaska)|(al|alabama)|(ar|arkansas)|(as|american[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+samoa)|(az|arizona)|(ca|california)|(co|colorado)|(ct|connecticut)|(dc|district[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+of[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+columbia)|(de|delaware)|(fl|florida)|(fm|federated[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+states[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+of[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+micronesia)|(ga|georgia)|(gu|guam)|(hi|hawaii)|(ia|iowa)|(id|idaho)|(il|illinois)|(in|indiana)|(ks|kansas)|(ky|kentucky)|(la|louisiana)|(ma|massachusetts)|(md|maryland)|(me|maine)|(mh|marshall[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+islands)|(mi|michigan)|(mn|minnesota)|(mo|missouri)|(mp|northern[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+mariana[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+islands)|(ms|mississippi)|(mt|montana)|(nc|north[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+carolina)|(nd|north[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+dakota)|(ne|nebraska)|(nh|new[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+hampshire)|(nj|new[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+jersey)|(nm|new[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+mexico)|(nv|nevada)|(ny|new[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+york)|(oh|ohio)|(ok|oklahoma)|(or|oregon)|(pa|pennsylvania)|(pr|puerto[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+rico)|(pw|palau)|(ri|rhode[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+island)|(sc|south[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+carolina)|(sd|south[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+dakota)|(tn|tennessee)|(tx|texas)|(ut|utah)|(va|virginia)|(vi|virgin[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+islands)|(vt|vermont)|(wa|washington)|(wi|wisconsin)|(wv|west[\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000]+virginia)|(wy|wyoming))(?=[,*\u2022\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000\n\u000B\f\r\u0085\u2028\u2029]|$)", 2);
        FindAddress.sLocationNameRe = Pattern.compile("(?:alley|annex|arcade|ave[.]?|avenue|alameda|bayou|beach|bend|bluffs?|bottom|boulevard|branch|bridge|brooks?|burgs?|bypass|broadway|camino|camp|canyon|cape|causeway|centers?|circles?|cliffs?|club|common|corners?|course|courts?|coves?|creek|crescent|crest|crossing|crossroad|curve|circulo|dale|dam|divide|drives?|estates?|expressway|extensions?|falls?|ferry|fields?|flats?|fords?|forest|forges?|forks?|fort|freeway|gardens?|gateway|glens?|greens?|groves?|harbors?|haven|heights|highway|hills?|hollow|inlet|islands?|isle|junctions?|keys?|knolls?|lakes?|land|landing|lane|lights?|loaf|locks?|lodge|loop|mall|manors?|meadows?|mews|mills?|mission|motorway|mount|mountains?|neck|orchard|oval|overpass|parks?|parkways?|pass|passage|path|pike|pines?|plains?|plaza|points?|ports?|prairie|privada|radial|ramp|ranch|rapids?|rd[.]?|rest|ridges?|river|roads?|route|row|rue|run|shoals?|shores?|skyway|springs?|spurs?|squares?|station|stravenue|stream|st[.]?|streets?|summit|speedway|terrace|throughway|trace|track|trafficway|trail|tunnel|turnpike|underpass|unions?|valleys?|viaduct|views?|villages?|ville|vista|walks?|wall|ways?|wells?|xing|xrd)(?=[,*\u2022\t \u00A0\u1680\u2000\u2001\u2002\u2003\u2004\u2005\u2006\u2007\u2008\u2009\u200A\u202F\u205F\u3000\n\u000B\f\r\u0085\u2028\u2029]|$)", 2);
        FindAddress.sSuffixedNumberRe = Pattern.compile("(\\d+)(st|nd|rd|th)", 2);
        FindAddress.sZipCodeRe = Pattern.compile("(?:\\d{5}(?:-\\d{4})?)(?=[,*•\t                　\n\u000B\f\r\u0085  ]|$)", 2);
    }

    private static int attemptMatch(String s, MatchResult matchResult0) {
        int v = matchResult0.end();
        Matcher matcher0 = FindAddress.sWordRe.matcher(s);
        String s1 = "";
        int v1 = 1;
        int v2 = 1;
        boolean z = true;
        boolean z1 = false;
        int v3 = -1;
        int v4 = -1;
        while(v < s.length()) {
            if(!matcher0.find(v)) {
                return -s.length();
            }
            if(matcher0.end() - matcher0.start() > 25) {
                return -matcher0.end();
            }
            while(v < matcher0.start()) {
                if("\n\u000B\f\r\u0085  ".indexOf(s.charAt(v)) != -1) {
                    ++v1;
                }
                ++v;
            }
            if(v1 <= 5) {
                ++v2;
                if(v2 > 14) {
                    break;
                }
                else if(FindAddress.matchHouseNumber(s, v) != null) {
                    if(z && v1 > 1) {
                        return -v;
                    }
                    if(v3 == -1) {
                        v3 = v;
                    }
                    s1 = matcher0.group(0);
                    v = matcher0.end();
                    continue;
                }
                else if(FindAddress.isValidLocationName(matcher0.group(0))) {
                    z = false;
                    z1 = true;
                    s1 = matcher0.group(0);
                    v = matcher0.end();
                    continue;
                }
                else if(v2 == 5 && !z1) {
                    v = matcher0.end();
                    break;
                }
                else if(!z1 || v2 <= 4) {
                    z = false;
                    s1 = matcher0.group(0);
                    v = matcher0.end();
                    continue;
                }
                else {
                    MatchResult matchResult1 = FindAddress.matchState(s, v);
                    if(matchResult1 == null) {
                        z = false;
                        s1 = matcher0.group(0);
                        v = matcher0.end();
                        continue;
                    }
                    else if(s1.equals("et") && matchResult1.group(0).equals("al")) {
                        v = matchResult1.end();
                        break;
                    }
                    else {
                        Matcher matcher1 = FindAddress.sWordRe.matcher(s);
                        if(!matcher1.find(matchResult1.end())) {
                            v4 = matchResult1.end();
                        }
                        else if(FindAddress.isValidZipCode(matcher1.group(0), matchResult1)) {
                            return matcher1.end();
                        }
                        z = false;
                        s1 = matcher0.group(0);
                        v = matcher0.end();
                        continue;
                    }
                }
            }
            else {
                break;
            }
            goto label_64;
        }
        if(v4 > 0) {
            return v4;
        }
    label_64:
        if(v3 > 0) {
            v = v3;
        }
        return -v;
    }

    private static boolean checkHouseNumber(String s) {
        int v1 = 0;
        for(int v = 0; v < s.length(); ++v) {
            if(Character.isDigit(s.charAt(v))) {
                ++v1;
            }
        }
        if(v1 > 5) {
            return false;
        }
        Matcher matcher0 = FindAddress.sSuffixedNumberRe.matcher(s);
        if(matcher0.find()) {
            int v2 = Integer.parseInt(matcher0.group(1));
            if(v2 == 0) {
                return false;
            }
            String s1 = matcher0.group(2).toLowerCase(Locale.getDefault());
            switch(v2 % 10) {
                case 1: {
                    return v2 % 100 == 11 ? s1.equals("th") : s1.equals("st");
                }
                case 2: {
                    return v2 % 100 == 12 ? s1.equals("th") : s1.equals("nd");
                }
                case 3: {
                    return v2 % 100 == 13 ? s1.equals("th") : s1.equals("rd");
                }
                default: {
                    return s1.equals("th");
                }
            }
        }
        return true;
    }

    static String findAddress(String s) {
        Matcher matcher0 = FindAddress.sHouseNumberRe.matcher(s);
        int v = 0;
        while(matcher0.find(v)) {
            if(FindAddress.checkHouseNumber(matcher0.group(0))) {
                int v1 = matcher0.start();
                int v2 = FindAddress.attemptMatch(s, matcher0);
                if(v2 > 0) {
                    return s.substring(v1, v2);
                }
                v = -v2;
            }
            else {
                v = matcher0.end();
            }
        }
        return null;
    }

    @VisibleForTesting
    public static boolean isValidLocationName(String s) {
        return FindAddress.sLocationNameRe.matcher(s).matches();
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String s) {
        return FindAddress.sZipCodeRe.matcher(s).matches();
    }

    @VisibleForTesting
    public static boolean isValidZipCode(String s, String s1) {
        return FindAddress.isValidZipCode(s, FindAddress.matchState(s1, 0));
    }

    private static boolean isValidZipCode(String s, MatchResult matchResult0) {
        if(matchResult0 == null) {
            return false;
        }
        int v;
        for(v = matchResult0.groupCount(); v > 0; --v) {
            if(matchResult0.group(v) != null) {
                return FindAddress.sZipCodeRe.matcher(s).matches() && FindAddress.sStateZipCodeRanges[v - 1].matches(s);
            }
        }
        return FindAddress.sZipCodeRe.matcher(s).matches() && FindAddress.sStateZipCodeRanges[v].matches(s);
    }

    @VisibleForTesting
    public static MatchResult matchHouseNumber(String s, int v) {
        if(v > 0 && ":,\"\'\t                　\n\u000B\f\r\u0085  ".indexOf(s.charAt(v - 1)) == -1) {
            return null;
        }
        Matcher matcher0 = FindAddress.sHouseNumberRe.matcher(s).region(v, s.length());
        if(matcher0.lookingAt()) {
            MatchResult matchResult0 = matcher0.toMatchResult();
            return FindAddress.checkHouseNumber(matchResult0.group(0)) ? matchResult0 : null;
        }
        return null;
    }

    @VisibleForTesting
    public static MatchResult matchState(String s, int v) {
        if(v > 0 && ",*•\t                　\n\u000B\f\r\u0085  ".indexOf(s.charAt(v - 1)) == -1) {
            return null;
        }
        Matcher matcher0 = FindAddress.sStateRe.matcher(s).region(v, s.length());
        return matcher0.lookingAt() ? matcher0.toMatchResult() : null;
    }
}

