package androidx.core.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 0x20;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 0x20;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;

    @NonNull
    public static String capabilityToString(int v) {
        switch(v) {
            case 1: {
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            }
            case 2: {
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            }
            case 4: {
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 8: {
                return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
            }
            default: {
                return "UNKNOWN";
            }
        }
    }

    @NonNull
    public static String feedbackTypeToString(int v) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("[");
        while(v > 0) {
            int v1 = 1 << Integer.numberOfTrailingZeros(v);
            v &= ~v1;
            if(stringBuilder0.length() > 1) {
                stringBuilder0.append(", ");
            }
            switch(v1) {
                case 1: {
                    stringBuilder0.append("FEEDBACK_SPOKEN");
                    break;
                }
                case 2: {
                    stringBuilder0.append("FEEDBACK_HAPTIC");
                    break;
                }
                case 4: {
                    stringBuilder0.append("FEEDBACK_AUDIBLE");
                    break;
                }
                case 8: {
                    stringBuilder0.append("FEEDBACK_VISUAL");
                    break;
                }
                case 16: {
                    stringBuilder0.append("FEEDBACK_GENERIC");
                }
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    @Nullable
    public static String flagToString(int v) {
        switch(v) {
            case 1: {
                return "DEFAULT";
            }
            case 2: {
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            }
            case 4: {
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            case 8: {
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 16: {
                return "FLAG_REPORT_VIEW_IDS";
            }
            case 0x20: {
                return "FLAG_REQUEST_FILTER_KEY_EVENTS";
            }
            default: {
                return null;
            }
        }
    }

    public static int getCapabilities(@NonNull AccessibilityServiceInfo accessibilityServiceInfo0) {
        if(Build.VERSION.SDK_INT >= 18) {
            return accessibilityServiceInfo0.getCapabilities();
        }
        return accessibilityServiceInfo0.getCanRetrieveWindowContent() ? 1 : 0;
    }

    @Nullable
    public static String loadDescription(@NonNull AccessibilityServiceInfo accessibilityServiceInfo0, @NonNull PackageManager packageManager0) {
        return Build.VERSION.SDK_INT < 16 ? accessibilityServiceInfo0.getDescription() : accessibilityServiceInfo0.loadDescription(packageManager0);
    }
}

