package androidx.core.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;

public final class ListViewCompat {
    public static boolean canScrollList(@NonNull ListView listView0, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            return listView0.canScrollList(v);
        }
        int v1 = listView0.getChildCount();
        if(v1 == 0) {
            return false;
        }
        int v2 = listView0.getFirstVisiblePosition();
        if(v > 0) {
            int v3 = listView0.getChildAt(v1 - 1).getBottom();
            return v2 + v1 < listView0.getCount() || v3 > listView0.getHeight() - listView0.getListPaddingBottom();
        }
        return v2 > 0 || listView0.getChildAt(0).getTop() < listView0.getListPaddingTop();
    }

    public static void scrollListBy(@NonNull ListView listView0, int v) {
        if(Build.VERSION.SDK_INT >= 19) {
            listView0.scrollListBy(v);
            return;
        }
        int v1 = listView0.getFirstVisiblePosition();
        if(v1 == -1) {
            return;
        }
        View view0 = listView0.getChildAt(0);
        if(view0 == null) {
            return;
        }
        listView0.setSelectionFromTop(v1, view0.getTop() - v);
    }
}

