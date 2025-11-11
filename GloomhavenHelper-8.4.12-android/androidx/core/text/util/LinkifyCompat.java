package androidx.core.text.util;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify.MatchFilter;
import android.text.util.Linkify.TransformFilter;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat {
    static class LinkSpec {
        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LinkifyMask {
    }

    private static final Comparator COMPARATOR;
    private static final String[] EMPTY_STRING;

    static {
        LinkifyCompat.EMPTY_STRING = new String[0];
        LinkifyCompat.COMPARATOR = new Comparator() {
            public int compare(LinkSpec linkifyCompat$LinkSpec0, LinkSpec linkifyCompat$LinkSpec1) {
                if(linkifyCompat$LinkSpec0.start < linkifyCompat$LinkSpec1.start) {
                    return -1;
                }
                if(linkifyCompat$LinkSpec0.start > linkifyCompat$LinkSpec1.start) {
                    return 1;
                }
                if(linkifyCompat$LinkSpec0.end < linkifyCompat$LinkSpec1.end) {
                    return 1;
                }
                return linkifyCompat$LinkSpec0.end <= linkifyCompat$LinkSpec1.end ? 0 : -1;
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((LinkSpec)object0), ((LinkSpec)object1));
            }
        };
    }

    private static void addLinkMovementMethod(@NonNull TextView textView0) {
        MovementMethod movementMethod0 = textView0.getMovementMethod();
        if((movementMethod0 == null || !(movementMethod0 instanceof LinkMovementMethod)) && textView0.getLinksClickable()) {
            textView0.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    public static void addLinks(@NonNull TextView textView0, @NonNull Pattern pattern0, @Nullable String s) {
        Linkify.addLinks(textView0, pattern0, s);
    }

    public static void addLinks(@NonNull TextView textView0, @NonNull Pattern pattern0, @Nullable String s, @Nullable Linkify.MatchFilter linkify$MatchFilter0, @Nullable Linkify.TransformFilter linkify$TransformFilter0) {
        Linkify.addLinks(textView0, pattern0, s, linkify$MatchFilter0, linkify$TransformFilter0);
    }

    // 去混淆评级： 低(20)
    @SuppressLint({"NewApi"})
    public static void addLinks(@NonNull TextView textView0, @NonNull Pattern pattern0, @Nullable String s, @Nullable String[] arr_s, @Nullable Linkify.MatchFilter linkify$MatchFilter0, @Nullable Linkify.TransformFilter linkify$TransformFilter0) {
        Linkify.addLinks(textView0, pattern0, s, arr_s, linkify$MatchFilter0, linkify$TransformFilter0);
    }

    // 去混淆评级： 低(40)
    public static boolean addLinks(@NonNull Spannable spannable0, int v) {
        return Linkify.addLinks(spannable0, v);
    }

    // 去混淆评级： 低(30)
    public static boolean addLinks(@NonNull Spannable spannable0, @NonNull Pattern pattern0, @Nullable String s) {
        return Linkify.addLinks(spannable0, pattern0, s);
    }

    // 去混淆评级： 低(30)
    public static boolean addLinks(@NonNull Spannable spannable0, @NonNull Pattern pattern0, @Nullable String s, @Nullable Linkify.MatchFilter linkify$MatchFilter0, @Nullable Linkify.TransformFilter linkify$TransformFilter0) {
        return Linkify.addLinks(spannable0, pattern0, s, linkify$MatchFilter0, linkify$TransformFilter0);
    }

    // 去混淆评级： 中等(60)
    @SuppressLint({"NewApi"})
    public static boolean addLinks(@NonNull Spannable spannable0, @NonNull Pattern pattern0, @Nullable String s, @Nullable String[] arr_s, @Nullable Linkify.MatchFilter linkify$MatchFilter0, @Nullable Linkify.TransformFilter linkify$TransformFilter0) {
        return Linkify.addLinks(spannable0, pattern0, s, arr_s, linkify$MatchFilter0, linkify$TransformFilter0);
    }

    // 去混淆评级： 中等(60)
    public static boolean addLinks(@NonNull TextView textView0, int v) {
        return Linkify.addLinks(textView0, v);
    }

    private static void applyLink(String s, int v, int v1, Spannable spannable0) {
        spannable0.setSpan(new URLSpan(s), v, v1, 33);
    }

    private static String findAddress(String s) {
        return Build.VERSION.SDK_INT < 28 ? FindAddress.findAddress(s) : WebView.findAddress(s);
    }

    private static void gatherLinks(ArrayList arrayList0, Spannable spannable0, Pattern pattern0, String[] arr_s, Linkify.MatchFilter linkify$MatchFilter0, Linkify.TransformFilter linkify$TransformFilter0) {
        Matcher matcher0 = pattern0.matcher(spannable0);
        while(matcher0.find()) {
            int v = matcher0.start();
            int v1 = matcher0.end();
            if(linkify$MatchFilter0 == null || linkify$MatchFilter0.acceptMatch(spannable0, v, v1)) {
                LinkSpec linkifyCompat$LinkSpec0 = new LinkSpec();
                linkifyCompat$LinkSpec0.url = LinkifyCompat.makeUrl(matcher0.group(0), arr_s, matcher0, linkify$TransformFilter0);
                linkifyCompat$LinkSpec0.start = v;
                linkifyCompat$LinkSpec0.end = v1;
                arrayList0.add(linkifyCompat$LinkSpec0);
            }
        }
    }

    private static void gatherMapLinks(ArrayList arrayList0, Spannable spannable0) {
        String s2;
        int v1;
        String s = spannable0.toString();
        int v = 0;
        try {
            while(true) {
                String s1 = LinkifyCompat.findAddress(s);
                if(s1 == null) {
                    return;
                }
                else {
                    v1 = s.indexOf(s1);
                    if(v1 < 0) {
                        return;
                    }
                }
                LinkSpec linkifyCompat$LinkSpec0 = new LinkSpec();
                int v2 = s1.length() + v1;
                linkifyCompat$LinkSpec0.start = v1 + v;
                v += v2;
                linkifyCompat$LinkSpec0.end = v;
                s = s.substring(v2);
                try {
                    s2 = URLEncoder.encode(s1, "UTF-8");
                }
                catch(UnsupportedEncodingException unused_ex) {
                    continue;
                }
                linkifyCompat$LinkSpec0.url = "geo:0,0?q=" + s2;
                arrayList0.add(linkifyCompat$LinkSpec0);
            }
        }
        catch(UnsupportedOperationException unused_ex) {
        }
    }

    private static String makeUrl(@NonNull String s, @NonNull String[] arr_s, Matcher matcher0, @Nullable Linkify.TransformFilter linkify$TransformFilter0) {
        if(linkify$TransformFilter0 != null) {
            s = linkify$TransformFilter0.transformUrl(matcher0, s);
        }
        for(int v = 0; v < arr_s.length; ++v) {
            if(s.regionMatches(true, 0, arr_s[v], 0, arr_s[v].length())) {
                return s.regionMatches(false, 0, arr_s[v], 0, arr_s[v].length()) ? s : arr_s[v] + s.substring(arr_s[v].length());
            }
        }
        return s;
    }

    private static void pruneOverlaps(ArrayList arrayList0, Spannable spannable0) {
        int v3;
        int v = 0;
        URLSpan[] arr_uRLSpan = (URLSpan[])spannable0.getSpans(0, spannable0.length(), URLSpan.class);
        for(int v1 = 0; v1 < arr_uRLSpan.length; ++v1) {
            LinkSpec linkifyCompat$LinkSpec0 = new LinkSpec();
            linkifyCompat$LinkSpec0.frameworkAddedSpan = arr_uRLSpan[v1];
            linkifyCompat$LinkSpec0.start = spannable0.getSpanStart(arr_uRLSpan[v1]);
            linkifyCompat$LinkSpec0.end = spannable0.getSpanEnd(arr_uRLSpan[v1]);
            arrayList0.add(linkifyCompat$LinkSpec0);
        }
        Collections.sort(arrayList0, LinkifyCompat.COMPARATOR);
        int v2 = arrayList0.size();
        while(v < v2 - 1) {
            LinkSpec linkifyCompat$LinkSpec1 = (LinkSpec)arrayList0.get(v);
            LinkSpec linkifyCompat$LinkSpec2 = (LinkSpec)arrayList0.get(v + 1);
            if(linkifyCompat$LinkSpec1.start <= linkifyCompat$LinkSpec2.start && linkifyCompat$LinkSpec1.end > linkifyCompat$LinkSpec2.start) {
                if(linkifyCompat$LinkSpec2.end <= linkifyCompat$LinkSpec1.end) {
                    v3 = v + 1;
                }
                else if(linkifyCompat$LinkSpec1.end - linkifyCompat$LinkSpec1.start > linkifyCompat$LinkSpec2.end - linkifyCompat$LinkSpec2.start) {
                    v3 = v + 1;
                }
                else {
                    v3 = linkifyCompat$LinkSpec1.end - linkifyCompat$LinkSpec1.start >= linkifyCompat$LinkSpec2.end - linkifyCompat$LinkSpec2.start ? -1 : v;
                }
                if(v3 != -1) {
                    URLSpan uRLSpan0 = ((LinkSpec)arrayList0.get(v3)).frameworkAddedSpan;
                    if(uRLSpan0 != null) {
                        spannable0.removeSpan(uRLSpan0);
                    }
                    arrayList0.remove(v3);
                    --v2;
                    continue;
                }
            }
            ++v;
        }
    }

    private static boolean shouldAddLinksFallbackToFramework() [...] // 潜在的解密器
}

