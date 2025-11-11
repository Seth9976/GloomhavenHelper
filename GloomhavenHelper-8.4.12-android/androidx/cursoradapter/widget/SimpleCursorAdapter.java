package androidx.cursoradapter.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;

public class SimpleCursorAdapter extends ResourceCursorAdapter {
    public interface CursorToStringConverter {
        CharSequence convertToString(Cursor arg1);
    }

    public interface ViewBinder {
        boolean setViewValue(View arg1, Cursor arg2, int arg3);
    }

    private CursorToStringConverter mCursorToStringConverter;
    @RestrictTo({Scope.LIBRARY_GROUP})
    protected int[] mFrom;
    String[] mOriginalFrom;
    private int mStringConversionColumn;
    @RestrictTo({Scope.LIBRARY_GROUP})
    protected int[] mTo;
    private ViewBinder mViewBinder;

    @Deprecated
    public SimpleCursorAdapter(Context context0, int v, Cursor cursor0, String[] arr_s, int[] arr_v) {
        super(context0, v, cursor0);
        this.mStringConversionColumn = -1;
        this.mTo = arr_v;
        this.mOriginalFrom = arr_s;
        this.findColumns(cursor0, arr_s);
    }

    public SimpleCursorAdapter(Context context0, int v, Cursor cursor0, String[] arr_s, int[] arr_v, int v1) {
        super(context0, v, cursor0, v1);
        this.mStringConversionColumn = -1;
        this.mTo = arr_v;
        this.mOriginalFrom = arr_s;
        this.findColumns(cursor0, arr_s);
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public void bindView(View view0, Context context0, Cursor cursor0) {
        ViewBinder simpleCursorAdapter$ViewBinder0 = this.mViewBinder;
        int[] arr_v = this.mTo;
        int[] arr_v1 = this.mFrom;
        for(int v = 0; v < arr_v.length; ++v) {
            View view1 = view0.findViewById(arr_v[v]);
            if(view1 != null && !(simpleCursorAdapter$ViewBinder0 == null ? false : simpleCursorAdapter$ViewBinder0.setViewValue(view1, cursor0, arr_v1[v]))) {
                String s = cursor0.getString(arr_v1[v]);
                if(s == null) {
                    s = "";
                }
                if(view1 instanceof TextView) {
                    this.setViewText(((TextView)view1), s);
                }
                else {
                    if(!(view1 instanceof ImageView)) {
                        throw new IllegalStateException(view1.getClass().getName() + " is not a " + " view that can be bounds by this SimpleCursorAdapter");
                    }
                    this.setViewImage(((ImageView)view1), s);
                    continue;
                }
            }
        }
    }

    public void changeCursorAndColumns(Cursor cursor0, String[] arr_s, int[] arr_v) {
        this.mOriginalFrom = arr_s;
        this.mTo = arr_v;
        this.findColumns(cursor0, this.mOriginalFrom);
        super.changeCursor(cursor0);
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public CharSequence convertToString(Cursor cursor0) {
        CursorToStringConverter simpleCursorAdapter$CursorToStringConverter0 = this.mCursorToStringConverter;
        if(simpleCursorAdapter$CursorToStringConverter0 != null) {
            return simpleCursorAdapter$CursorToStringConverter0.convertToString(cursor0);
        }
        int v = this.mStringConversionColumn;
        return v > -1 ? cursor0.getString(v) : super.convertToString(cursor0);
    }

    private void findColumns(Cursor cursor0, String[] arr_s) {
        if(cursor0 != null) {
            if(this.mFrom == null || this.mFrom.length != arr_s.length) {
                this.mFrom = new int[arr_s.length];
            }
            for(int v = 0; v < arr_s.length; ++v) {
                int[] arr_v = this.mFrom;
                arr_v[v] = cursor0.getColumnIndexOrThrow(arr_s[v]);
            }
            return;
        }
        this.mFrom = null;
    }

    public CursorToStringConverter getCursorToStringConverter() {
        return this.mCursorToStringConverter;
    }

    public int getStringConversionColumn() {
        return this.mStringConversionColumn;
    }

    public ViewBinder getViewBinder() {
        return this.mViewBinder;
    }

    public void setCursorToStringConverter(CursorToStringConverter simpleCursorAdapter$CursorToStringConverter0) {
        this.mCursorToStringConverter = simpleCursorAdapter$CursorToStringConverter0;
    }

    public void setStringConversionColumn(int v) {
        this.mStringConversionColumn = v;
    }

    public void setViewBinder(ViewBinder simpleCursorAdapter$ViewBinder0) {
        this.mViewBinder = simpleCursorAdapter$ViewBinder0;
    }

    public void setViewImage(ImageView imageView0, String s) {
        try {
            imageView0.setImageResource(Integer.parseInt(s));
        }
        catch(NumberFormatException unused_ex) {
            imageView0.setImageURI(Uri.parse(s));
        }
    }

    public void setViewText(TextView textView0, String s) {
        textView0.setText(s);
    }

    @Override  // androidx.cursoradapter.widget.CursorAdapter
    public Cursor swapCursor(Cursor cursor0) {
        this.findColumns(cursor0, this.mOriginalFrom);
        return super.swapCursor(cursor0);
    }
}

