package androidx.core.text;

import android.text.SpannableStringBuilder;
import java.util.Locale;

public final class BidiFormatter {
    public static final class Builder {
        private int mFlags;
        private boolean mIsRtlContext;
        private TextDirectionHeuristicCompat mTextDirectionHeuristicCompat;

        public Builder() {
            this.initialize(BidiFormatter.isRtlLocale(Locale.getDefault()));
        }

        public Builder(Locale locale0) {
            this.initialize(BidiFormatter.isRtlLocale(locale0));
        }

        public Builder(boolean z) {
            this.initialize(z);
        }

        public BidiFormatter build() {
            return this.mFlags != 2 || this.mTextDirectionHeuristicCompat != BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC ? new BidiFormatter(this.mIsRtlContext, this.mFlags, this.mTextDirectionHeuristicCompat) : Builder.getDefaultInstanceFromContext(this.mIsRtlContext);
        }

        // 去混淆评级： 低(20)
        private static BidiFormatter getDefaultInstanceFromContext(boolean z) {
            return z ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
        }

        private void initialize(boolean z) {
            this.mIsRtlContext = z;
            this.mTextDirectionHeuristicCompat = BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC;
            this.mFlags = 2;
        }

        public Builder setTextDirectionHeuristic(TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
            this.mTextDirectionHeuristicCompat = textDirectionHeuristicCompat0;
            return this;
        }

        public Builder stereoReset(boolean z) {
            if(z) {
                this.mFlags |= 2;
                return this;
            }
            this.mFlags &= -3;
            return this;
        }
    }

    static class DirectionalityEstimator {
        private static final byte[] DIR_TYPE_CACHE = null;
        private static final int DIR_TYPE_CACHE_SIZE = 0x700;
        private int charIndex;
        private final boolean isHtml;
        private char lastChar;
        private final int length;
        private final CharSequence text;

        static {
            DirectionalityEstimator.DIR_TYPE_CACHE = new byte[0x700];
            for(int v = 0; v < 0x700; ++v) {
                DirectionalityEstimator.DIR_TYPE_CACHE[v] = Character.getDirectionality(v);
            }
        }

        DirectionalityEstimator(CharSequence charSequence0, boolean z) {
            this.text = charSequence0;
            this.isHtml = z;
            this.length = charSequence0.length();
        }

        byte dirTypeBackward() {
            this.lastChar = this.text.charAt(this.charIndex - 1);
            if(Character.isLowSurrogate(this.lastChar)) {
                int v = Character.codePointBefore(this.text, this.charIndex);
                this.charIndex -= Character.charCount(v);
                return Character.getDirectionality(v);
            }
            --this.charIndex;
            byte b = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if(this.isHtml) {
                int v1 = this.lastChar;
                if(v1 == 62) {
                    return this.skipTagBackward();
                }
                return v1 == 59 ? this.skipEntityBackward() : b;
            }
            return b;
        }

        byte dirTypeForward() {
            this.lastChar = this.text.charAt(this.charIndex);
            if(Character.isHighSurrogate(this.lastChar)) {
                int v = Character.codePointAt(this.text, this.charIndex);
                this.charIndex += Character.charCount(v);
                return Character.getDirectionality(v);
            }
            ++this.charIndex;
            byte b = DirectionalityEstimator.getCachedDirectionality(this.lastChar);
            if(this.isHtml) {
                int v1 = this.lastChar;
                if(v1 == 60) {
                    return this.skipTagForward();
                }
                return v1 == 38 ? this.skipEntityForward() : b;
            }
            return b;
        }

        private static byte getCachedDirectionality(char c) {
            return c >= 0x700 ? Character.getDirectionality(c) : DirectionalityEstimator.DIR_TYPE_CACHE[c];
        }

        int getEntryDir() {
            this.charIndex = 0;
            int v = 0;
            int v1 = 0;
            int v2 = 0;
            while(0 < this.length && v == 0) {
                int v3 = this.dirTypeForward();
                if(v3 != 9) {
                    switch(v3) {
                        case 0: {
                            if(v2 == 0) {
                                return -1;
                            }
                            v = v2;
                            break;
                        }
                        case 1: 
                        case 2: {
                            if(v2 == 0) {
                                return 1;
                            }
                            v = v2;
                            break;
                        }
                        case 14: 
                        case 15: {
                            ++v2;
                            v1 = -1;
                            break;
                        }
                        case 16: 
                        case 17: {
                            ++v2;
                            v1 = 1;
                            break;
                        }
                        case 18: {
                            --v2;
                            v1 = 0;
                            break;
                        }
                        default: {
                            v = v2;
                        }
                    }
                }
            }
            if(v == 0) {
                return 0;
            }
            return v1 == 0 ? 0 : v1;
        }

        int getExitDir() {
            this.charIndex = this.length;
            int v = 0;
            int v1 = 0;
            while(this.charIndex > 0) {
                int v2 = this.dirTypeBackward();
                if(v2 != 9) {
                    switch(v2) {
                        case 0: {
                            if(v1 == 0) {
                                return -1;
                            }
                            if(v != 0) {
                                continue;
                            }
                            v = v1;
                            break;
                        }
                        case 1: 
                        case 2: {
                            if(v1 == 0) {
                                return 1;
                            }
                            if(v != 0) {
                                continue;
                            }
                            v = v1;
                            break;
                        }
                        case 14: 
                        case 15: {
                            if(v == v1) {
                                return -1;
                            }
                            --v1;
                            break;
                        }
                        case 16: 
                        case 17: {
                            if(v == v1) {
                                return 1;
                            }
                            --v1;
                            break;
                        }
                        case 18: {
                            ++v1;
                            break;
                        }
                        default: {
                            if(v != 0) {
                                continue;
                            }
                            v = v1;
                        }
                    }
                }
            }
            return 0;
        }

        private byte skipEntityBackward() {
            int v = this.charIndex;
            do {
                int v1 = this.charIndex;
                if(v1 <= 0) {
                    break;
                }
                this.charIndex = v1 - 1;
                this.lastChar = this.text.charAt(v1 - 1);
                int v2 = this.lastChar;
                if(v2 == 38) {
                    return 12;
                }
            }
            while(v2 != 59);
            this.charIndex = v;
            this.lastChar = ';';
            return 13;
        }

        private byte skipEntityForward() {
            do {
                int v = this.charIndex;
                if(v >= this.length) {
                    break;
                }
                this.charIndex = v + 1;
                char c = this.text.charAt(v);
                this.lastChar = c;
            }
            while(c != 59);
            return 12;
        }

        private byte skipTagBackward() {
            int v = this.charIndex;
        alab1:
            while(true) {
            alab2:
                while(true) {
                    int v1 = this.charIndex;
                    if(v1 <= 0) {
                        break alab1;
                    }
                    this.charIndex = v1 - 1;
                    this.lastChar = this.text.charAt(v1 - 1);
                    int v2 = this.lastChar;
                    if(v2 == 60) {
                        return 12;
                    }
                    switch(v2) {
                        case 34: 
                        case 39: {
                            int v3 = this.lastChar;
                        label_11:
                            int v4 = this.charIndex;
                            if(v4 <= 0) {
                                continue alab1;
                            }
                            this.charIndex = v4 - 1;
                            char c = this.text.charAt(v4 - 1);
                            this.lastChar = c;
                            if(c == v3) {
                                break;
                            }
                            break alab2;
                        }
                        case 62: {
                            break alab1;
                        }
                    }
                }
                goto label_11;
            }
            this.charIndex = v;
            this.lastChar = '>';
            return 13;
        }

        private byte skipTagForward() {
            int v4;
            int v = this.charIndex;
        alab1:
            while(true) {
                do {
                    do {
                        int v1 = this.charIndex;
                        if(v1 >= this.length) {
                            break alab1;
                        }
                        this.charIndex = v1 + 1;
                        this.lastChar = this.text.charAt(v1);
                        int v2 = this.lastChar;
                        if(v2 == 62) {
                            return 12;
                        }
                        if(v2 != 34 && v2 != 39) {
                            continue alab1;
                        }
                        int v3 = this.lastChar;
                    label_10:
                        v4 = this.charIndex;
                    }
                    while(v4 >= this.length);
                    this.charIndex = v4 + 1;
                    char c = this.text.charAt(v4);
                    this.lastChar = c;
                }
                while(c == v3);
                goto label_10;
            }
            this.charIndex = v;
            this.lastChar = '<';
            return 13;
        }
    }

    private static final int DEFAULT_FLAGS = 2;
    static final BidiFormatter DEFAULT_LTR_INSTANCE = null;
    static final BidiFormatter DEFAULT_RTL_INSTANCE = null;
    static final TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC = null;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '‪';
    private static final char LRM = '‎';
    private static final String LRM_STRING = null;
    private static final char PDF = '‬';
    private static final char RLE = '‫';
    private static final char RLM = '‏';
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;

    static {
        BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        BidiFormatter.LRM_STRING = "‎";
        BidiFormatter.RLM_STRING = "\u200F";
        BidiFormatter.DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
        BidiFormatter.DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }

    BidiFormatter(boolean z, int v, TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
        this.mIsRtlContext = z;
        this.mFlags = v;
        this.mDefaultTextDirectionHeuristicCompat = textDirectionHeuristicCompat0;
    }

    private static int getEntryDir(CharSequence charSequence0) {
        return new DirectionalityEstimator(charSequence0, false).getEntryDir();
    }

    private static int getExitDir(CharSequence charSequence0) {
        return new DirectionalityEstimator(charSequence0, false).getExitDir();
    }

    public static BidiFormatter getInstance() {
        return new Builder().build();
    }

    public static BidiFormatter getInstance(Locale locale0) {
        return new Builder(locale0).build();
    }

    public static BidiFormatter getInstance(boolean z) {
        return new Builder(z).build();
    }

    public boolean getStereoReset() {
        return (this.mFlags & 2) != 0;
    }

    public boolean isRtl(CharSequence charSequence0) {
        int v = charSequence0.length();
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(charSequence0, 0, v);
    }

    public boolean isRtl(String s) {
        return this.isRtl(s);
    }

    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }

    static boolean isRtlLocale(Locale locale0) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale0) == 1;
    }

    // 去混淆评级： 低(20)
    private String markAfter(CharSequence charSequence0, TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
        boolean z = textDirectionHeuristicCompat0.isRtl(charSequence0, 0, charSequence0.length());
        if(!this.mIsRtlContext && (z || BidiFormatter.getExitDir(charSequence0) == 1)) {
            return "‎";
        }
        return !this.mIsRtlContext || z && BidiFormatter.getExitDir(charSequence0) != -1 ? "" : "\u200F";
    }

    // 去混淆评级： 低(20)
    private String markBefore(CharSequence charSequence0, TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
        boolean z = textDirectionHeuristicCompat0.isRtl(charSequence0, 0, charSequence0.length());
        if(!this.mIsRtlContext && (z || BidiFormatter.getEntryDir(charSequence0) == 1)) {
            return "‎";
        }
        return !this.mIsRtlContext || z && BidiFormatter.getEntryDir(charSequence0) != -1 ? "" : "\u200F";
    }

    public CharSequence unicodeWrap(CharSequence charSequence0) {
        return this.unicodeWrap(charSequence0, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence0, TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
        return this.unicodeWrap(charSequence0, textDirectionHeuristicCompat0, true);
    }

    public CharSequence unicodeWrap(CharSequence charSequence0, TextDirectionHeuristicCompat textDirectionHeuristicCompat0, boolean z) {
        if(charSequence0 == null) {
            return null;
        }
        boolean z1 = textDirectionHeuristicCompat0.isRtl(charSequence0, 0, charSequence0.length());
        CharSequence charSequence1 = new SpannableStringBuilder();
        if(this.getStereoReset() && z) {
            ((SpannableStringBuilder)charSequence1).append(this.markBefore(charSequence0, (z1 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR)));
        }
        if(z1 == this.mIsRtlContext) {
            ((SpannableStringBuilder)charSequence1).append(charSequence0);
        }
        else {
            ((SpannableStringBuilder)charSequence1).append(((char)(z1 ? 0x202B : 0x202A)));
            ((SpannableStringBuilder)charSequence1).append(charSequence0);
            ((SpannableStringBuilder)charSequence1).append('‬');
        }
        if(z) {
            ((SpannableStringBuilder)charSequence1).append(this.markAfter(charSequence0, (z1 ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR)));
        }
        return charSequence1;
    }

    public CharSequence unicodeWrap(CharSequence charSequence0, boolean z) {
        return this.unicodeWrap(charSequence0, this.mDefaultTextDirectionHeuristicCompat, z);
    }

    public String unicodeWrap(String s) {
        return this.unicodeWrap(s, this.mDefaultTextDirectionHeuristicCompat, true);
    }

    public String unicodeWrap(String s, TextDirectionHeuristicCompat textDirectionHeuristicCompat0) {
        return this.unicodeWrap(s, textDirectionHeuristicCompat0, true);
    }

    public String unicodeWrap(String s, TextDirectionHeuristicCompat textDirectionHeuristicCompat0, boolean z) {
        return s == null ? null : this.unicodeWrap(s, textDirectionHeuristicCompat0, z).toString();
    }

    public String unicodeWrap(String s, boolean z) {
        return this.unicodeWrap(s, this.mDefaultTextDirectionHeuristicCompat, z);
    }
}

