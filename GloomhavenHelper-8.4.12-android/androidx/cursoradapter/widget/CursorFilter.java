package androidx.cursoradapter.widget;

import android.database.Cursor;
import android.widget.Filter.FilterResults;
import android.widget.Filter;

class CursorFilter extends Filter {
    interface CursorFilterClient {
        void changeCursor(Cursor arg1);

        CharSequence convertToString(Cursor arg1);

        Cursor getCursor();

        Cursor runQueryOnBackgroundThread(CharSequence arg1);
    }

    CursorFilterClient mClient;

    CursorFilter(CursorFilterClient cursorFilter$CursorFilterClient0) {
        this.mClient = cursorFilter$CursorFilterClient0;
    }

    @Override  // android.widget.Filter
    public CharSequence convertResultToString(Object object0) {
        return this.mClient.convertToString(((Cursor)object0));
    }

    @Override  // android.widget.Filter
    protected Filter.FilterResults performFiltering(CharSequence charSequence0) {
        Cursor cursor0 = this.mClient.runQueryOnBackgroundThread(charSequence0);
        Filter.FilterResults filter$FilterResults0 = new Filter.FilterResults();
        if(cursor0 != null) {
            filter$FilterResults0.count = cursor0.getCount();
            filter$FilterResults0.values = cursor0;
            return filter$FilterResults0;
        }
        filter$FilterResults0.count = 0;
        filter$FilterResults0.values = null;
        return filter$FilterResults0;
    }

    @Override  // android.widget.Filter
    protected void publishResults(CharSequence charSequence0, Filter.FilterResults filter$FilterResults0) {
        Cursor cursor0 = this.mClient.getCursor();
        if(filter$FilterResults0.values != null && filter$FilterResults0.values != cursor0) {
            this.mClient.changeCursor(((Cursor)filter$FilterResults0.values));
        }
    }
}

