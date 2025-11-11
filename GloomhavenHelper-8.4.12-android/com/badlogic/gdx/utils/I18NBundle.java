package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

public class I18NBundle {
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final Locale ROOT_LOCALE;
    private static boolean exceptionOnMissingKey;
    private TextFormatter formatter;
    private Locale locale;
    private I18NBundle parent;
    private ObjectMap properties;
    private static boolean simpleFormatter;

    static {
        I18NBundle.ROOT_LOCALE = new Locale("", "", "");
        I18NBundle.simpleFormatter = false;
        I18NBundle.exceptionOnMissingKey = true;
    }

    private static boolean checkFileExistence(FileHandle fileHandle0) {
        try {
            fileHandle0.read().close();
            return true;
        }
        catch(Exception unused_ex) {
            return false;
        }
    }

    public static I18NBundle createBundle(FileHandle fileHandle0) {
        return I18NBundle.createBundleImpl(fileHandle0, Locale.getDefault(), "UTF-8");
    }

    public static I18NBundle createBundle(FileHandle fileHandle0, String s) {
        return I18NBundle.createBundleImpl(fileHandle0, Locale.getDefault(), s);
    }

    public static I18NBundle createBundle(FileHandle fileHandle0, Locale locale0) {
        return I18NBundle.createBundleImpl(fileHandle0, locale0, "UTF-8");
    }

    public static I18NBundle createBundle(FileHandle fileHandle0, Locale locale0, String s) {
        return I18NBundle.createBundleImpl(fileHandle0, locale0, s);
    }

    private static I18NBundle createBundleImpl(FileHandle fileHandle0, Locale locale0, String s) {
        I18NBundle i18NBundle1;
        if(fileHandle0 == null || locale0 == null || s == null) {
            throw new NullPointerException();
        }
        I18NBundle i18NBundle0 = null;
        Locale locale1 = locale0;
        do {
            List list0 = I18NBundle.getCandidateLocales(locale1);
            i18NBundle1 = I18NBundle.loadBundleChain(fileHandle0, s, list0, 0, i18NBundle0);
            if(i18NBundle1 != null) {
                Locale locale2 = i18NBundle1.getLocale();
                if(!locale2.equals(I18NBundle.ROOT_LOCALE) || locale2.equals(locale0) || list0.size() == 1 && locale2.equals(list0.get(0))) {
                    break;
                }
                if(i18NBundle0 == null) {
                    i18NBundle0 = i18NBundle1;
                }
            }
            locale1 = I18NBundle.getFallbackLocale(locale1);
        }
        while(locale1 != null);
        if(i18NBundle1 == null) {
            if(i18NBundle0 == null) {
                throw new MissingResourceException("Can\'t find bundle for base file handle " + fileHandle0.path() + ", locale " + locale0, fileHandle0 + "_" + locale0, "");
            }
            return i18NBundle0;
        }
        return i18NBundle1;
    }

    public void debug(String s) {
        Keys objectMap$Keys0 = this.properties.keys();
        if(objectMap$Keys0 == null) {
            return;
        }
        while(objectMap$Keys0.hasNext()) {
            Object object0 = objectMap$Keys0.next();
            this.properties.put(((String)object0), s);
        }
    }

    public String format(String s, Object[] arr_object) {
        return this.formatter.format(this.get(s), arr_object);
    }

    public String get(String s) {
        String s1 = (String)this.properties.get(s);
        if(s1 == null) {
            I18NBundle i18NBundle0 = this.parent;
            if(i18NBundle0 != null) {
                s1 = i18NBundle0.get(s);
            }
            if(s1 == null) {
                if(I18NBundle.exceptionOnMissingKey) {
                    throw new MissingResourceException("Can\'t find bundle key " + s, this.getClass().getName(), s);
                }
                return "???" + s + "???";
            }
        }
        return s1;
    }

    private static List getCandidateLocales(Locale locale0) {
        String s = locale0.getLanguage();
        String s1 = locale0.getCountry();
        String s2 = locale0.getVariant();
        List list0 = new ArrayList(4);
        if(s2.length() > 0) {
            list0.add(locale0);
        }
        if(s1.length() > 0) {
            list0.add((list0.isEmpty() ? locale0 : new Locale(s, s1)));
        }
        if(s.length() > 0) {
            if(!list0.isEmpty()) {
                locale0 = new Locale(s);
            }
            list0.add(locale0);
        }
        list0.add(I18NBundle.ROOT_LOCALE);
        return list0;
    }

    public static boolean getExceptionOnMissingKey() {
        return I18NBundle.exceptionOnMissingKey;
    }

    private static Locale getFallbackLocale(Locale locale0) {
        Locale locale1 = Locale.getDefault();
        return locale0.equals(locale1) ? null : locale1;
    }

    public Locale getLocale() {
        return this.locale;
    }

    public static boolean getSimpleFormatter() {
        return I18NBundle.simpleFormatter;
    }

    protected void load(Reader reader0) throws IOException {
        this.properties = new ObjectMap();
        PropertiesUtils.load(this.properties, reader0);
    }

    private static I18NBundle loadBundle(FileHandle fileHandle0, String s, Locale locale0) {
        I18NBundle i18NBundle0;
        Reader reader0 = null;
        try {
            FileHandle fileHandle1 = I18NBundle.toFileHandle(fileHandle0, locale0);
            if(I18NBundle.checkFileExistence(fileHandle1)) {
                i18NBundle0 = new I18NBundle();
                reader0 = fileHandle1.reader(s);
                i18NBundle0.load(reader0);
            }
            else {
                i18NBundle0 = null;
            }
        }
        catch(IOException iOException0) {
            throw new GdxRuntimeException(iOException0);
        }
        finally {
            StreamUtils.closeQuietly(reader0);
        }
        if(i18NBundle0 != null) {
            i18NBundle0.setLocale(locale0);
        }
        return i18NBundle0;
    }

    private static I18NBundle loadBundleChain(FileHandle fileHandle0, String s, List list0, int v, I18NBundle i18NBundle0) {
        I18NBundle i18NBundle1;
        Locale locale0 = (Locale)list0.get(v);
        if(v == list0.size() - 1) {
            if(i18NBundle0 != null && locale0.equals(I18NBundle.ROOT_LOCALE)) {
                return i18NBundle0;
            }
            i18NBundle1 = null;
        }
        else {
            i18NBundle1 = I18NBundle.loadBundleChain(fileHandle0, s, list0, v + 1, i18NBundle0);
        }
        I18NBundle i18NBundle2 = I18NBundle.loadBundle(fileHandle0, s, locale0);
        if(i18NBundle2 != null) {
            i18NBundle2.parent = i18NBundle1;
            return i18NBundle2;
        }
        return i18NBundle1;
    }

    public static void setExceptionOnMissingKey(boolean z) {
        I18NBundle.exceptionOnMissingKey = z;
    }

    private void setLocale(Locale locale0) {
        this.locale = locale0;
        this.formatter = new TextFormatter(locale0, !I18NBundle.simpleFormatter);
    }

    public static void setSimpleFormatter(boolean z) {
        I18NBundle.simpleFormatter = z;
    }

    private static FileHandle toFileHandle(FileHandle fileHandle0, Locale locale0) {
        StringBuilder stringBuilder0 = new StringBuilder(fileHandle0.name());
        if(!locale0.equals(I18NBundle.ROOT_LOCALE)) {
            String s = locale0.getLanguage();
            String s1 = locale0.getCountry();
            String s2 = locale0.getVariant();
            boolean z = "".equals(s1);
            boolean z1 = "".equals(s2);
            if(!"".equals(s) || !z || !z1) {
                stringBuilder0.append('_');
                if(!z1) {
                    stringBuilder0.append(s).append('_').append(s1).append('_').append(s2);
                    return fileHandle0.sibling("");
                }
                if(!z) {
                    stringBuilder0.append(s).append('_').append(s1);
                    return fileHandle0.sibling("");
                }
                stringBuilder0.append(s);
            }
        }
        return fileHandle0.sibling("");
    }
}

