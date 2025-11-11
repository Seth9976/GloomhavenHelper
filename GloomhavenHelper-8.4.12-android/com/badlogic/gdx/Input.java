package com.badlogic.gdx;

import com.badlogic.gdx.utils.ObjectIntMap;

public interface Input {
    public static class Buttons {
        public static final int BACK = 3;
        public static final int FORWARD = 4;
        public static final int LEFT = 0;
        public static final int MIDDLE = 2;
        public static final int RIGHT = 1;

    }

    public static class Keys {
        public static final int A = 29;
        public static final int ALT_LEFT = 57;
        public static final int ALT_RIGHT = 58;
        public static final int ANY_KEY = -1;
        public static final int APOSTROPHE = 75;
        public static final int AT = 77;
        public static final int B = 30;
        public static final int BACK = 4;
        public static final int BACKSLASH = 73;
        public static final int BACKSPACE = 67;
        public static final int BUTTON_A = 0x60;
        public static final int BUTTON_B = 97;
        public static final int BUTTON_C = 98;
        public static final int BUTTON_CIRCLE = 0xFF;
        public static final int BUTTON_L1 = 102;
        public static final int BUTTON_L2 = 104;
        public static final int BUTTON_MODE = 110;
        public static final int BUTTON_R1 = 103;
        public static final int BUTTON_R2 = 105;
        public static final int BUTTON_SELECT = 109;
        public static final int BUTTON_START = 108;
        public static final int BUTTON_THUMBL = 106;
        public static final int BUTTON_THUMBR = 107;
        public static final int BUTTON_X = 99;
        public static final int BUTTON_Y = 100;
        public static final int BUTTON_Z = 101;
        public static final int C = 0x1F;
        public static final int CALL = 5;
        public static final int CAMERA = 27;
        public static final int CAPS_LOCK = 0x73;
        public static final int CENTER = 23;
        public static final int CLEAR = 28;
        public static final int COLON = 0xF3;
        public static final int COMMA = 55;
        public static final int CONTROL_LEFT = 0x81;
        public static final int CONTROL_RIGHT = 130;
        public static final int D = 0x20;
        public static final int DEL = 67;
        public static final int DOWN = 20;
        public static final int DPAD_CENTER = 23;
        public static final int DPAD_DOWN = 20;
        public static final int DPAD_LEFT = 21;
        public static final int DPAD_RIGHT = 22;
        public static final int DPAD_UP = 19;
        public static final int E = 33;
        public static final int END = 0x7B;
        public static final int ENDCALL = 6;
        public static final int ENTER = 66;
        public static final int ENVELOPE = 65;
        public static final int EQUALS = 70;
        public static final int ESCAPE = 0x6F;
        public static final int EXPLORER = 0x40;
        public static final int F = 34;
        public static final int F1 = 0x83;
        public static final int F10 = 140;
        public static final int F11 = 0x8D;
        public static final int F12 = 0x8E;
        public static final int F13 = 0xB7;
        public static final int F14 = 0xB8;
        public static final int F15 = 0xB9;
        public static final int F16 = 0xBA;
        public static final int F17 = 0xBB;
        public static final int F18 = 0xBC;
        public static final int F19 = 0xBD;
        public static final int F2 = 0x84;
        public static final int F20 = 190;
        public static final int F21 = 0xBF;
        public static final int F22 = 0xC0;
        public static final int F23 = 0xC1;
        public static final int F24 = 0xC2;
        public static final int F3 = 0x85;
        public static final int F4 = 0x86;
        public static final int F5 = 0x87;
        public static final int F6 = 0x88;
        public static final int F7 = 0x89;
        public static final int F8 = 0x8A;
        public static final int F9 = 0x8B;
        public static final int FOCUS = 80;
        public static final int FORWARD_DEL = 0x70;
        public static final int G = 35;
        public static final int GRAVE = 68;
        public static final int H = 36;
        public static final int HEADSETHOOK = 0x4F;
        public static final int HOME = 3;
        public static final int I = 37;
        public static final int INSERT = 0x7C;
        public static final int J = 38;
        public static final int K = 39;
        public static final int L = 40;
        public static final int LEFT = 21;
        public static final int LEFT_BRACKET = 71;
        public static final int M = 41;
        public static final int MAX_KEYCODE = 0xFF;
        public static final int MEDIA_FAST_FORWARD = 90;
        public static final int MEDIA_NEXT = 87;
        public static final int MEDIA_PLAY_PAUSE = 85;
        public static final int MEDIA_PREVIOUS = 88;
        public static final int MEDIA_REWIND = 89;
        public static final int MEDIA_STOP = 86;
        public static final int MENU = 82;
        public static final int META_ALT_LEFT_ON = 16;
        public static final int META_ALT_ON = 2;
        public static final int META_ALT_RIGHT_ON = 0x20;
        public static final int META_SHIFT_LEFT_ON = 0x40;
        public static final int META_SHIFT_ON = 1;
        public static final int META_SHIFT_RIGHT_ON = 0x80;
        public static final int META_SYM_ON = 4;
        public static final int MINUS = 69;
        public static final int MUTE = 91;
        public static final int N = 42;
        public static final int NOTIFICATION = 83;
        public static final int NUM = 78;
        public static final int NUMPAD_0 = 0x90;
        public static final int NUMPAD_1 = 0x91;
        public static final int NUMPAD_2 = 0x92;
        public static final int NUMPAD_3 = 0x93;
        public static final int NUMPAD_4 = 0x94;
        public static final int NUMPAD_5 = 0x95;
        public static final int NUMPAD_6 = 150;
        public static final int NUMPAD_7 = 0x97;
        public static final int NUMPAD_8 = 0x98;
        public static final int NUMPAD_9 = 0x99;
        public static final int NUMPAD_ADD = 0x9D;
        public static final int NUMPAD_COMMA = 0x9F;
        public static final int NUMPAD_DIVIDE = 0x9A;
        public static final int NUMPAD_DOT = 0x9E;
        public static final int NUMPAD_ENTER = 0xA0;
        public static final int NUMPAD_EQUALS = 0xA1;
        public static final int NUMPAD_LEFT_PAREN = 0xA2;
        public static final int NUMPAD_MULTIPLY = 0x9B;
        public static final int NUMPAD_RIGHT_PAREN = 0xA3;
        public static final int NUMPAD_SUBTRACT = 0x9C;
        public static final int NUM_0 = 7;
        public static final int NUM_1 = 8;
        public static final int NUM_2 = 9;
        public static final int NUM_3 = 10;
        public static final int NUM_4 = 11;
        public static final int NUM_5 = 12;
        public static final int NUM_6 = 13;
        public static final int NUM_7 = 14;
        public static final int NUM_8 = 15;
        public static final int NUM_9 = 16;
        public static final int NUM_LOCK = 0x8F;
        public static final int O = 43;
        public static final int P = 44;
        public static final int PAGE_DOWN = 93;
        public static final int PAGE_UP = 92;
        public static final int PAUSE = 0x79;
        public static final int PERIOD = 56;
        public static final int PICTSYMBOLS = 94;
        public static final int PLUS = 81;
        public static final int POUND = 18;
        public static final int POWER = 26;
        public static final int PRINT_SCREEN = 120;
        public static final int Q = 45;
        public static final int R = 46;
        public static final int RIGHT = 22;
        public static final int RIGHT_BRACKET = 72;
        public static final int S = 0x2F;
        public static final int SCROLL_LOCK = 0x74;
        public static final int SEARCH = 84;
        public static final int SEMICOLON = 74;
        public static final int SHIFT_LEFT = 59;
        public static final int SHIFT_RIGHT = 60;
        public static final int SLASH = 76;
        public static final int SOFT_LEFT = 1;
        public static final int SOFT_RIGHT = 2;
        public static final int SPACE = 62;
        public static final int STAR = 17;
        public static final int SWITCH_CHARSET = 0x5F;
        public static final int SYM = 0x3F;
        public static final int T = 0x30;
        public static final int TAB = 61;
        public static final int U = 49;
        public static final int UNKNOWN = 0;
        public static final int UP = 19;
        public static final int V = 50;
        public static final int VOLUME_DOWN = 25;
        public static final int VOLUME_UP = 24;
        public static final int W = 51;
        public static final int X = 52;
        public static final int Y = 53;
        public static final int Z = 54;
        private static ObjectIntMap keyNames;

        private static void initializeKeyNames() {
            Keys.keyNames = new ObjectIntMap();
            for(int v = 0; v < 0x100; ++v) {
                String s = Keys.toString(v);
                if(s != null) {
                    Keys.keyNames.put(s, v);
                }
            }
        }

        public static String toString(int v) {
            if(v < 0) {
                throw new IllegalArgumentException("keycode cannot be negative, keycode: " + v);
            }
            if(v > 0xFF) {
                throw new IllegalArgumentException("keycode cannot be greater than 255, keycode: " + v);
            }
            if(v != 0xF3) {
                switch(v) {
                    case 0: {
                        return "Unknown";
                    }
                    case 1: {
                        return "Soft Left";
                    }
                    case 2: {
                        return "Soft Right";
                    }
                    case 3: {
                        return "Home";
                    }
                    case 4: {
                        return "Back";
                    }
                    case 5: {
                        return "Call";
                    }
                    case 6: {
                        return "End Call";
                    }
                    case 7: {
                        return "0";
                    }
                    case 8: {
                        return "1";
                    }
                    case 9: {
                        return "2";
                    }
                    case 10: {
                        return "3";
                    }
                    case 11: {
                        return "4";
                    }
                    case 12: {
                        return "5";
                    }
                    case 13: {
                        return "6";
                    }
                    case 14: {
                        return "7";
                    }
                    case 15: {
                        return "8";
                    }
                    case 16: {
                        return "9";
                    }
                    case 17: {
                        return "*";
                    }
                    case 18: {
                        return "#";
                    }
                    case 19: {
                        return "Up";
                    }
                    case 20: {
                        return "Down";
                    }
                    case 21: {
                        return "Left";
                    }
                    case 22: {
                        return "Right";
                    }
                    case 23: {
                        return "Center";
                    }
                    case 24: {
                        return "Volume Up";
                    }
                    case 25: {
                        return "Volume Down";
                    }
                    case 26: {
                        return "Power";
                    }
                    case 27: {
                        return "Camera";
                    }
                    case 28: {
                        return "Clear";
                    }
                    case 29: {
                        return "A";
                    }
                    case 30: {
                        return "B";
                    }
                    case 0x1F: {
                        return "C";
                    }
                    case 0x20: {
                        return "D";
                    }
                    case 33: {
                        return "E";
                    }
                    case 34: {
                        return "F";
                    }
                    case 35: {
                        return "G";
                    }
                    case 36: {
                        return "H";
                    }
                    case 37: {
                        return "I";
                    }
                    case 38: {
                        return "J";
                    }
                    case 39: {
                        return "K";
                    }
                    case 40: {
                        return "L";
                    }
                    case 41: {
                        return "M";
                    }
                    case 42: {
                        return "N";
                    }
                    case 43: {
                        return "O";
                    }
                    case 44: {
                        return "P";
                    }
                    case 45: {
                        return "Q";
                    }
                    case 46: {
                        return "R";
                    }
                    case 0x2F: {
                        return "S";
                    }
                    case 0x30: {
                        return "T";
                    }
                    case 49: {
                        return "U";
                    }
                    case 50: {
                        return "V";
                    }
                    case 51: {
                        return "W";
                    }
                    case 52: {
                        return "X";
                    }
                    case 53: {
                        return "Y";
                    }
                    case 54: {
                        return "Z";
                    }
                    case 55: {
                        return ",";
                    }
                    case 56: {
                        return ".";
                    }
                    case 57: {
                        return "L-Alt";
                    }
                    case 58: {
                        return "R-Alt";
                    }
                    case 59: {
                        return "L-Shift";
                    }
                    case 60: {
                        return "R-Shift";
                    }
                    case 61: {
                        return "Tab";
                    }
                    case 62: {
                        return "Space";
                    }
                    case 0x3F: {
                        return "SYM";
                    }
                    case 0x40: {
                        return "Explorer";
                    }
                    case 65: {
                        return "Envelope";
                    }
                    case 66: {
                        return "Enter";
                    }
                    case 67: {
                        return "Delete";
                    }
                    case 68: {
                        return "`";
                    }
                    case 69: {
                        return "-";
                    }
                    case 70: {
                        return "=";
                    }
                    case 71: {
                        return "[";
                    }
                    case 72: {
                        return "]";
                    }
                    case 73: {
                        return "\\";
                    }
                    case 74: {
                        return ";";
                    }
                    case 75: {
                        return "\'";
                    }
                    case 76: {
                        return "/";
                    }
                    case 77: {
                        return "@";
                    }
                    case 78: {
                        return "Num";
                    }
                    case 0x4F: {
                        return "Headset Hook";
                    }
                    case 80: {
                        return "Focus";
                    }
                    case 81: {
                        return "Plus";
                    }
                    case 82: {
                        return "Menu";
                    }
                    case 83: {
                        return "Notification";
                    }
                    case 84: {
                        return "Search";
                    }
                    case 85: {
                        return "Play/Pause";
                    }
                    case 86: {
                        return "Stop Media";
                    }
                    case 87: {
                        return "Next Media";
                    }
                    case 88: {
                        return "Prev Media";
                    }
                    case 89: {
                        return "Rewind";
                    }
                    case 90: {
                        return "Fast Forward";
                    }
                    case 91: {
                        return "Mute";
                    }
                    case 92: {
                        return "Page Up";
                    }
                    case 93: {
                        return "Page Down";
                    }
                    case 94: {
                        return "PICTSYMBOLS";
                    }
                    case 0x5F: {
                        return "SWITCH_CHARSET";
                    }
                    case 0x60: {
                        return "A Button";
                    }
                    case 97: {
                        return "B Button";
                    }
                    case 98: {
                        return "C Button";
                    }
                    case 99: {
                        return "X Button";
                    }
                    case 100: {
                        return "Y Button";
                    }
                    case 101: {
                        return "Z Button";
                    }
                    case 102: {
                        return "L1 Button";
                    }
                    case 103: {
                        return "R1 Button";
                    }
                    case 104: {
                        return "L2 Button";
                    }
                    case 105: {
                        return "R2 Button";
                    }
                    case 106: {
                        return "Left Thumb";
                    }
                    case 107: {
                        return "Right Thumb";
                    }
                    case 108: {
                        return "Start";
                    }
                    case 109: {
                        return "Select";
                    }
                    case 110: {
                        return "Button Mode";
                    }
                    case 0x6F: {
                        return "Escape";
                    }
                    case 0x70: {
                        return "Forward Delete";
                    }
                    case 0x73: {
                        return "Caps Lock";
                    }
                    case 0x74: {
                        return "Scroll Lock";
                    }
                    case 120: {
                        return "Print";
                    }
                    case 0x79: {
                        return "Pause";
                    }
                    case 0x7B: {
                        return "End";
                    }
                    case 0x7C: {
                        return "Insert";
                    }
                    case 0x81: {
                        return "L-Ctrl";
                    }
                    case 130: {
                        return "R-Ctrl";
                    }
                    case 0x83: {
                        return "F1";
                    }
                    case 0x84: {
                        return "F2";
                    }
                    case 0x85: {
                        return "F3";
                    }
                    case 0x86: {
                        return "F4";
                    }
                    case 0x87: {
                        return "F5";
                    }
                    case 0x88: {
                        return "F6";
                    }
                    case 0x89: {
                        return "F7";
                    }
                    case 0x8A: {
                        return "F8";
                    }
                    case 0x8B: {
                        return "F9";
                    }
                    case 140: {
                        return "F10";
                    }
                    case 0x8D: {
                        return "F11";
                    }
                    case 0x8E: {
                        return "F12";
                    }
                    case 0x8F: {
                        return "Num Lock";
                    }
                    case 0x90: {
                        return "Numpad 0";
                    }
                    case 0x91: {
                        return "Numpad 1";
                    }
                    case 0x92: {
                        return "Numpad 2";
                    }
                    case 0x93: {
                        return "Numpad 3";
                    }
                    case 0x94: {
                        return "Numpad 4";
                    }
                    case 0x95: {
                        return "Numpad 5";
                    }
                    case 150: {
                        return "Numpad 6";
                    }
                    case 0x97: {
                        return "Numpad 7";
                    }
                    case 0x98: {
                        return "Numpad 8";
                    }
                    case 0x99: {
                        return "Numpad 9";
                    }
                    case 0x9A: {
                        return "Num /";
                    }
                    case 0x9B: {
                        return "Num *";
                    }
                    case 0x9C: {
                        return "Num -";
                    }
                    case 0x9D: {
                        return "Num +";
                    }
                    case 0x9E: {
                        return "Num .";
                    }
                    case 0x9F: {
                        return "Num ,";
                    }
                    case 0xA0: {
                        return "Num Enter";
                    }
                    case 0xA1: {
                        return "Num =";
                    }
                    case 0xA2: {
                        return "Num (";
                    }
                    case 0xA3: {
                        return "Num )";
                    }
                    case 0xB7: {
                        return "F13";
                    }
                    case 0xB8: {
                        return "F14";
                    }
                    case 0xB9: {
                        return "F15";
                    }
                    case 0xBA: {
                        return "F16";
                    }
                    case 0xBB: {
                        return "F17";
                    }
                    case 0xBC: {
                        return "F18";
                    }
                    case 0xBD: {
                        return "F19";
                    }
                    case 190: {
                        return "F20";
                    }
                    case 0xBF: {
                        return "F21";
                    }
                    case 0xC0: {
                        return "F22";
                    }
                    case 0xC1: {
                        return "F23";
                    }
                    case 0xC2: {
                        return "F24";
                    }
                    default: {
                        return null;
                    }
                }
            }
            return ":";
        }

        public static int valueOf(String s) {
            if(Keys.keyNames == null) {
                Keys.initializeKeyNames();
            }
            return Keys.keyNames.get(s, -1);
        }
    }

    public static enum OnscreenKeyboardType {
        Default,
        NumberPad,
        PhonePad,
        Email,
        Password,
        URI;

    }

    public static enum Orientation {
        Landscape,
        Portrait;

    }

    public static enum Peripheral {
        HardwareKeyboard,
        OnscreenKeyboard,
        MultitouchScreen,
        Accelerometer,
        Compass,
        Vibrator,
        Gyroscope,
        RotationVector,
        Pressure;

    }

    public interface TextInputListener {
        void canceled();

        void input(String arg1);
    }

    void cancelVibrate();

    float getAccelerometerX();

    float getAccelerometerY();

    float getAccelerometerZ();

    float getAzimuth();

    long getCurrentEventTime();

    int getDeltaX();

    int getDeltaX(int arg1);

    int getDeltaY();

    int getDeltaY(int arg1);

    float getGyroscopeX();

    float getGyroscopeY();

    float getGyroscopeZ();

    InputProcessor getInputProcessor();

    int getMaxPointers();

    Orientation getNativeOrientation();

    float getPitch();

    float getPressure();

    float getPressure(int arg1);

    float getRoll();

    int getRotation();

    void getRotationMatrix(float[] arg1);

    void getTextInput(TextInputListener arg1, String arg2, String arg3, String arg4);

    void getTextInput(TextInputListener arg1, String arg2, String arg3, String arg4, OnscreenKeyboardType arg5);

    int getX();

    int getX(int arg1);

    int getY();

    int getY(int arg1);

    boolean isButtonJustPressed(int arg1);

    boolean isButtonPressed(int arg1);

    @Deprecated
    boolean isCatchBackKey();

    boolean isCatchKey(int arg1);

    @Deprecated
    boolean isCatchMenuKey();

    boolean isCursorCatched();

    boolean isKeyJustPressed(int arg1);

    boolean isKeyPressed(int arg1);

    boolean isPeripheralAvailable(Peripheral arg1);

    boolean isTouched();

    boolean isTouched(int arg1);

    boolean justTouched();

    @Deprecated
    void setCatchBackKey(boolean arg1);

    void setCatchKey(int arg1, boolean arg2);

    @Deprecated
    void setCatchMenuKey(boolean arg1);

    void setCursorCatched(boolean arg1);

    void setCursorPosition(int arg1, int arg2);

    void setInputProcessor(InputProcessor arg1);

    void setOnscreenKeyboardVisible(boolean arg1);

    void setOnscreenKeyboardVisible(boolean arg1, OnscreenKeyboardType arg2);

    void vibrate(int arg1);

    void vibrate(long[] arg1, int arg2);
}

