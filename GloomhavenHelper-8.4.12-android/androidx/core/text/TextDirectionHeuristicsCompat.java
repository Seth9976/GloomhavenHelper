package androidx.core.text;

import java.nio.CharBuffer;
import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
    static class AnyStrong implements TextDirectionAlgorithm {
        static final AnyStrong INSTANCE_LTR;
        static final AnyStrong INSTANCE_RTL;
        private final boolean mLookForRtl;

        static {
            AnyStrong.INSTANCE_RTL = new AnyStrong(true);
            AnyStrong.INSTANCE_LTR = new AnyStrong(false);
        }

        private AnyStrong(boolean z) {
            this.mLookForRtl = z;
        }

        @Override  // androidx.core.text.TextDirectionHeuristicsCompat$TextDirectionAlgorithm
        public int checkRtl(CharSequence charSequence0, int v, int v1) {
            int v2 = v1 + v;
            boolean z = false;
            while(v < v2) {
                switch(TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charSequence0.charAt(v)))) {
                    case 0: {
                        if(this.mLookForRtl) {
                            return 0;
                        }
                        z = true;
                        break;
                    }
                    case 1: {
                        if(!this.mLookForRtl) {
                            return 1;
                        }
                        z = true;
                    }
                }
                ++v;
            }
            return z ? this.mLookForRtl : 2;
        }
    }

    static class FirstStrong implements TextDirectionAlgorithm {
        static final FirstStrong INSTANCE;

        static {
            FirstStrong.INSTANCE = new FirstStrong();
        }

        @Override  // androidx.core.text.TextDirectionHeuristicsCompat$TextDirectionAlgorithm
        public int checkRtl(CharSequence charSequence0, int v, int v1) {
            int v2 = v1 + v;
            int v3 = 2;
            while(v < v2 && v3 == 2) {
                v3 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(charSequence0.charAt(v)));
                ++v;
            }
            return v3;
        }
    }

    interface TextDirectionAlgorithm {
        int checkRtl(CharSequence arg1, int arg2, int arg3);
    }

    static abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionHeuristicsCompat$TextDirectionAlgorithm0) {
            this.mAlgorithm = textDirectionHeuristicsCompat$TextDirectionAlgorithm0;
        }

        protected abstract boolean defaultIsRtl();

        private boolean doCheck(CharSequence charSequence0, int v, int v1) {
            switch(this.mAlgorithm.checkRtl(charSequence0, v, v1)) {
                case 0: {
                    return true;
                }
                case 1: {
                    return false;
                }
                default: {
                    return this.defaultIsRtl();
                }
            }
        }

        @Override  // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(CharSequence charSequence0, int v, int v1) {
            if(charSequence0 == null || v < 0 || v1 < 0 || charSequence0.length() - v1 < v) {
                throw new IllegalArgumentException();
            }
            return this.mAlgorithm == null ? this.defaultIsRtl() : this.doCheck(charSequence0, v, v1);
        }

        @Override  // androidx.core.text.TextDirectionHeuristicCompat
        public boolean isRtl(char[] arr_c, int v, int v1) {
            return this.isRtl(CharBuffer.wrap(arr_c), v, v1);
        }
    }

    static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionHeuristicsCompat$TextDirectionAlgorithm0, boolean z) {
            super(textDirectionHeuristicsCompat$TextDirectionAlgorithm0);
            this.mDefaultIsRtl = z;
        }

        @Override  // androidx.core.text.TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl
        protected boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        static final TextDirectionHeuristicLocale INSTANCE;

        static {
            TextDirectionHeuristicLocale.INSTANCE = new TextDirectionHeuristicLocale();
        }

        TextDirectionHeuristicLocale() {
            super(null);
        }

        @Override  // androidx.core.text.TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl
        protected boolean defaultIsRtl() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }

    public static final TextDirectionHeuristicCompat ANYRTL_LTR = null;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR = null;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL = null;
    public static final TextDirectionHeuristicCompat LOCALE = null;
    public static final TextDirectionHeuristicCompat LTR = null;
    public static final TextDirectionHeuristicCompat RTL = null;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;

    static {
        TextDirectionHeuristicsCompat.LTR = new TextDirectionHeuristicInternal(null, false);
        TextDirectionHeuristicsCompat.RTL = new TextDirectionHeuristicInternal(null, true);
        TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);
        TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true);
        TextDirectionHeuristicsCompat.ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false);
        TextDirectionHeuristicsCompat.LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    }

    static int isRtlText(int v) {
        switch(v) {
            case 0: {
                return 1;
            }
            case 1: 
            case 2: {
                return 0;
            }
            default: {
                return 2;
            }
        }
    }

    static int isRtlTextOrFormat(int v) {
        switch(v) {
            case 0: 
            case 14: 
            case 15: {
                return 1;
            }
            case 1: 
            case 2: 
            case 16: 
            case 17: {
                return 0;
            }
            default: {
                return 2;
            }
        }
    }
}

