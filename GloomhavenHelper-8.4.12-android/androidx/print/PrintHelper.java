package androidx.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.pdf.PdfDocument.Page;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal.OnCancelListener;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public final class PrintHelper {
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    @RequiresApi(19)
    class PrintBitmapAdapter extends PrintDocumentAdapter {
        private PrintAttributes mAttributes;
        private final Bitmap mBitmap;
        private final OnPrintFinishCallback mCallback;
        private final int mFittingMode;
        private final String mJobName;

        PrintBitmapAdapter(String s, int v, Bitmap bitmap0, OnPrintFinishCallback printHelper$OnPrintFinishCallback0) {
            this.mJobName = s;
            this.mFittingMode = v;
            this.mBitmap = bitmap0;
            this.mCallback = printHelper$OnPrintFinishCallback0;
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onFinish() {
            OnPrintFinishCallback printHelper$OnPrintFinishCallback0 = this.mCallback;
            if(printHelper$OnPrintFinishCallback0 != null) {
                printHelper$OnPrintFinishCallback0.onFinish();
            }
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes0, PrintAttributes printAttributes1, CancellationSignal cancellationSignal0, PrintDocumentAdapter.LayoutResultCallback printDocumentAdapter$LayoutResultCallback0, Bundle bundle0) {
            this.mAttributes = printAttributes1;
            printDocumentAdapter$LayoutResultCallback0.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), !printAttributes1.equals(printAttributes0));
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] arr_pageRange, ParcelFileDescriptor parcelFileDescriptor0, CancellationSignal cancellationSignal0, PrintDocumentAdapter.WriteResultCallback printDocumentAdapter$WriteResultCallback0) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor0, cancellationSignal0, printDocumentAdapter$WriteResultCallback0);
        }
    }

    @RequiresApi(19)
    class PrintUriAdapter extends PrintDocumentAdapter {
        PrintAttributes mAttributes;
        Bitmap mBitmap;
        final OnPrintFinishCallback mCallback;
        final int mFittingMode;
        final Uri mImageFile;
        final String mJobName;
        AsyncTask mLoadBitmap;

        PrintUriAdapter(String s, Uri uri0, OnPrintFinishCallback printHelper$OnPrintFinishCallback0, int v) {
            this.mJobName = s;
            this.mImageFile = uri0;
            this.mCallback = printHelper$OnPrintFinishCallback0;
            this.mFittingMode = v;
            this.mBitmap = null;
        }

        void cancelLoad() {
            synchronized(PrintHelper.this.mLock) {
                if(PrintHelper.this.mDecodeOptions != null) {
                    if(Build.VERSION.SDK_INT < 24) {
                        PrintHelper.this.mDecodeOptions.requestCancelDecode();
                    }
                    PrintHelper.this.mDecodeOptions = null;
                }
            }
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onFinish() {
            super.onFinish();
            this.cancelLoad();
            AsyncTask asyncTask0 = this.mLoadBitmap;
            if(asyncTask0 != null) {
                asyncTask0.cancel(true);
            }
            OnPrintFinishCallback printHelper$OnPrintFinishCallback0 = this.mCallback;
            if(printHelper$OnPrintFinishCallback0 != null) {
                printHelper$OnPrintFinishCallback0.onFinish();
            }
            Bitmap bitmap0 = this.mBitmap;
            if(bitmap0 != null) {
                bitmap0.recycle();
                this.mBitmap = null;
            }
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onLayout(PrintAttributes printAttributes0, PrintAttributes printAttributes1, CancellationSignal cancellationSignal0, PrintDocumentAdapter.LayoutResultCallback printDocumentAdapter$LayoutResultCallback0, Bundle bundle0) {
            synchronized(this) {
                this.mAttributes = printAttributes1;
            }
            if(cancellationSignal0.isCanceled()) {
                printDocumentAdapter$LayoutResultCallback0.onLayoutCancelled();
                return;
            }
            if(this.mBitmap != null) {
                printDocumentAdapter$LayoutResultCallback0.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), !printAttributes1.equals(printAttributes0));
                return;
            }
            this.mLoadBitmap = new AsyncTask() {
                protected Bitmap doInBackground(Uri[] arr_uri) {
                    try {
                        return PrintHelper.this.loadConstrainedBitmap(PrintUriAdapter.this.mImageFile);
                    }
                    catch(FileNotFoundException unused_ex) {
                        return null;
                    }
                }

                @Override  // android.os.AsyncTask
                protected Object doInBackground(Object[] arr_object) {
                    return this.doInBackground(((Uri[])arr_object));
                }

                protected void onCancelled(Bitmap bitmap0) {
                    printDocumentAdapter$LayoutResultCallback0.onLayoutCancelled();
                    PrintUriAdapter.this.mLoadBitmap = null;
                }

                @Override  // android.os.AsyncTask
                protected void onCancelled(Object object0) {
                    this.onCancelled(((Bitmap)object0));
                }

                protected void onPostExecute(Bitmap bitmap0) {
                    super.onPostExecute(bitmap0);
                    if(bitmap0 != null && (!PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION || PrintHelper.this.mOrientation == 0)) {
                        synchronized(this) {
                            PrintAttributes.MediaSize printAttributes$MediaSize0 = PrintUriAdapter.this.mAttributes.getMediaSize();
                        }
                        if(printAttributes$MediaSize0 != null && printAttributes$MediaSize0.isPortrait() != PrintHelper.isPortrait(bitmap0)) {
                            Matrix matrix0 = new Matrix();
                            matrix0.postRotate(90.0f);
                            bitmap0 = Bitmap.createBitmap(bitmap0, 0, 0, bitmap0.getWidth(), bitmap0.getHeight(), matrix0, true);
                        }
                    }
                    PrintUriAdapter printHelper$PrintUriAdapter0 = PrintUriAdapter.this;
                    printHelper$PrintUriAdapter0.mBitmap = bitmap0;
                    if(bitmap0 == null) {
                        printDocumentAdapter$LayoutResultCallback0.onLayoutFailed(null);
                    }
                    else {
                        PrintDocumentInfo printDocumentInfo0 = new PrintDocumentInfo.Builder(printHelper$PrintUriAdapter0.mJobName).setContentType(1).setPageCount(1).build();
                        boolean z = printAttributes1.equals(printAttributes0);
                        printDocumentAdapter$LayoutResultCallback0.onLayoutFinished(printDocumentInfo0, ((boolean)(true ^ z)));
                    }
                    PrintUriAdapter.this.mLoadBitmap = null;
                }

                @Override  // android.os.AsyncTask
                protected void onPostExecute(Object object0) {
                    this.onPostExecute(((Bitmap)object0));
                }

                @Override  // android.os.AsyncTask
                protected void onPreExecute() {
                    androidx.print.PrintHelper.PrintUriAdapter.1.1 printHelper$PrintUriAdapter$1$10 = new CancellationSignal.OnCancelListener() {
                        @Override  // android.os.CancellationSignal$OnCancelListener
                        public void onCancel() {
                            PrintUriAdapter.this.cancelLoad();
                            androidx.print.PrintHelper.PrintUriAdapter.1.this.cancel(false);
                        }
                    };
                    cancellationSignal0.setOnCancelListener(printHelper$PrintUriAdapter$1$10);
                }
            }.execute(new Uri[0]);
        }

        @Override  // android.print.PrintDocumentAdapter
        public void onWrite(PageRange[] arr_pageRange, ParcelFileDescriptor parcelFileDescriptor0, CancellationSignal cancellationSignal0, PrintDocumentAdapter.WriteResultCallback printDocumentAdapter$WriteResultCallback0) {
            PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, parcelFileDescriptor0, cancellationSignal0, printDocumentAdapter$WriteResultCallback0);
        }
    }

    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_COLOR = 2;
    @SuppressLint({"InlinedApi"})
    public static final int COLOR_MODE_MONOCHROME = 1;
    static final boolean IS_MIN_MARGINS_HANDLING_CORRECT = false;
    private static final String LOG_TAG = "PrintHelper";
    private static final int MAX_PRINT_SIZE = 3500;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION = false;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    int mColorMode;
    final Context mContext;
    BitmapFactory.Options mDecodeOptions;
    final Object mLock;
    int mOrientation;
    int mScaleMode;

    static {
        boolean z = false;
        PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION = Build.VERSION.SDK_INT < 20 || Build.VERSION.SDK_INT > 23;
        if(Build.VERSION.SDK_INT != 23) {
            z = true;
        }
        PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT = z;
    }

    public PrintHelper(@NonNull Context context0) {
        this.mDecodeOptions = null;
        this.mLock = new Object();
        this.mScaleMode = 2;
        this.mColorMode = 2;
        this.mOrientation = 1;
        this.mContext = context0;
    }

    static Bitmap convertBitmapForColorMode(Bitmap bitmap0, int v) {
        if(v != 1) {
            return bitmap0;
        }
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap0.getWidth(), bitmap0.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas0 = new Canvas(bitmap1);
        Paint paint0 = new Paint();
        ColorMatrix colorMatrix0 = new ColorMatrix();
        colorMatrix0.setSaturation(0.0f);
        paint0.setColorFilter(new ColorMatrixColorFilter(colorMatrix0));
        canvas0.drawBitmap(bitmap0, 0.0f, 0.0f, paint0);
        canvas0.setBitmap(null);
        return bitmap1;
    }

    @RequiresApi(19)
    private static PrintAttributes.Builder copyAttributes(PrintAttributes printAttributes0) {
        PrintAttributes.Builder printAttributes$Builder0 = new PrintAttributes.Builder().setMediaSize(printAttributes0.getMediaSize()).setResolution(printAttributes0.getResolution()).setMinMargins(printAttributes0.getMinMargins());
        if(printAttributes0.getColorMode() != 0) {
            printAttributes$Builder0.setColorMode(printAttributes0.getColorMode());
        }
        if(Build.VERSION.SDK_INT >= 23 && printAttributes0.getDuplexMode() != 0) {
            printAttributes$Builder0.setDuplexMode(printAttributes0.getDuplexMode());
        }
        return printAttributes$Builder0;
    }

    public int getColorMode() {
        return this.mColorMode;
    }

    static Matrix getMatrix(int v, int v1, RectF rectF0, int v2) {
        Matrix matrix0 = new Matrix();
        float f = rectF0.width() / ((float)v);
        float f1 = v2 == 2 ? Math.max(f, rectF0.height() / ((float)v1)) : Math.min(f, rectF0.height() / ((float)v1));
        matrix0.postScale(f1, f1);
        matrix0.postTranslate((rectF0.width() - ((float)v) * f1) / 2.0f, (rectF0.height() - ((float)v1) * f1) / 2.0f);
        return matrix0;
    }

    public int getOrientation() {
        return Build.VERSION.SDK_INT < 19 || this.mOrientation != 0 ? this.mOrientation : 1;
    }

    public int getScaleMode() {
        return this.mScaleMode;
    }

    static boolean isPortrait(Bitmap bitmap0) {
        return bitmap0.getWidth() <= bitmap0.getHeight();
    }

    private Bitmap loadBitmap(Uri uri0, BitmapFactory.Options bitmapFactory$Options0) throws FileNotFoundException {
        Bitmap bitmap0;
        InputStream inputStream0;
        if(uri0 != null) {
            Context context0 = this.mContext;
            if(context0 != null) {
                try {
                    inputStream0 = null;
                    inputStream0 = context0.getContentResolver().openInputStream(uri0);
                    bitmap0 = BitmapFactory.decodeStream(inputStream0, null, bitmapFactory$Options0);
                }
                catch(Throwable throwable0) {
                    if(inputStream0 != null) {
                        try {
                            inputStream0.close();
                        }
                        catch(IOException iOException0) {
                            Log.w("PrintHelper", "close fail ", iOException0);
                        }
                    }
                    throw throwable0;
                }
                if(inputStream0 != null) {
                    try {
                        inputStream0.close();
                        return bitmap0;
                    }
                    catch(IOException iOException1) {
                        Log.w("PrintHelper", "close fail ", iOException1);
                    }
                }
                return bitmap0;
            }
        }
        throw new IllegalArgumentException("bad argument to loadBitmap");
    }

    Bitmap loadConstrainedBitmap(Uri uri0) throws FileNotFoundException {
        if(uri0 == null || this.mContext == null) {
            throw new IllegalArgumentException("bad argument to getScaledBitmap");
        }
        BitmapFactory.Options bitmapFactory$Options0 = new BitmapFactory.Options();
        bitmapFactory$Options0.inJustDecodeBounds = true;
        this.loadBitmap(uri0, bitmapFactory$Options0);
        int v = bitmapFactory$Options0.outWidth;
        int v1 = bitmapFactory$Options0.outHeight;
        if(v > 0 && v1 > 0) {
            int v2 = Math.max(v, v1);
            int v3;
            for(v3 = 1; v2 > 3500; v3 <<= 1) {
                v2 >>>= 1;
            }
            if(v3 > 0 && Math.min(v, v1) / v3 > 0) {
                synchronized(this.mLock) {
                    this.mDecodeOptions = new BitmapFactory.Options();
                    this.mDecodeOptions.inMutable = true;
                    this.mDecodeOptions.inSampleSize = v3;
                }
                try {
                    return this.loadBitmap(uri0, this.mDecodeOptions);
                }
                finally {
                    synchronized(this.mLock) {
                        this.mDecodeOptions = null;
                    }
                }
            }
            return null;
        }
        return null;
    }

    public void printBitmap(@NonNull String s, @NonNull Bitmap bitmap0) {
        this.printBitmap(s, bitmap0, null);
    }

    public void printBitmap(@NonNull String s, @NonNull Bitmap bitmap0, @Nullable OnPrintFinishCallback printHelper$OnPrintFinishCallback0) {
        if(Build.VERSION.SDK_INT >= 19 && bitmap0 != null) {
            PrintManager printManager0 = (PrintManager)this.mContext.getSystemService("print");
            PrintAttributes.MediaSize printAttributes$MediaSize0 = PrintHelper.isPortrait(bitmap0) ? PrintAttributes.MediaSize.UNKNOWN_PORTRAIT : PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
            PrintAttributes printAttributes0 = new PrintAttributes.Builder().setMediaSize(printAttributes$MediaSize0).setColorMode(this.mColorMode).build();
            printManager0.print(s, new PrintBitmapAdapter(this, s, this.mScaleMode, bitmap0, printHelper$OnPrintFinishCallback0), printAttributes0);
        }
    }

    public void printBitmap(@NonNull String s, @NonNull Uri uri0) throws FileNotFoundException {
        this.printBitmap(s, uri0, null);
    }

    public void printBitmap(@NonNull String s, @NonNull Uri uri0, @Nullable OnPrintFinishCallback printHelper$OnPrintFinishCallback0) throws FileNotFoundException {
        if(Build.VERSION.SDK_INT < 19) {
            return;
        }
        PrintUriAdapter printHelper$PrintUriAdapter0 = new PrintUriAdapter(this, s, uri0, printHelper$OnPrintFinishCallback0, this.mScaleMode);
        PrintManager printManager0 = (PrintManager)this.mContext.getSystemService("print");
        PrintAttributes.Builder printAttributes$Builder0 = new PrintAttributes.Builder();
        printAttributes$Builder0.setColorMode(this.mColorMode);
        int v = this.mOrientation;
        if(v == 0 || v == 1) {
            printAttributes$Builder0.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
        }
        else if(v == 2) {
            printAttributes$Builder0.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
        }
        printManager0.print(s, printHelper$PrintUriAdapter0, printAttributes$Builder0.build());
    }

    public void setColorMode(int v) {
        this.mColorMode = v;
    }

    public void setOrientation(int v) {
        this.mOrientation = v;
    }

    public void setScaleMode(int v) {
        this.mScaleMode = v;
    }

    public static boolean systemSupportsPrint() {
        return Build.VERSION.SDK_INT >= 19;
    }

    @RequiresApi(19)
    void writeBitmap(PrintAttributes printAttributes0, int v, Bitmap bitmap0, ParcelFileDescriptor parcelFileDescriptor0, CancellationSignal cancellationSignal0, PrintDocumentAdapter.WriteResultCallback printDocumentAdapter$WriteResultCallback0) {
        new AsyncTask() {
            @Override  // android.os.AsyncTask
            protected Object doInBackground(Object[] arr_object) {
                return this.doInBackground(((Void[])arr_object));
            }

            protected Throwable doInBackground(Void[] arr_void) {
                RectF rectF0;
                try {
                    if(cancellationSignal0.isCanceled()) {
                        return null;
                    }
                    PrintedPdfDocument printedPdfDocument0 = new PrintedPdfDocument(PrintHelper.this.mContext, (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT ? printAttributes0 : PrintHelper.copyAttributes(printAttributes0).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build()));
                    int v = (PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT ? printAttributes0 : PrintHelper.copyAttributes(printAttributes0).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build()).getColorMode();
                    Bitmap bitmap0 = PrintHelper.convertBitmapForColorMode(bitmap0, v);
                    if(cancellationSignal0.isCanceled()) {
                        return null;
                    }
                    try {
                        PdfDocument.Page pdfDocument$Page0 = printedPdfDocument0.startPage(1);
                        if(PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                            rectF0 = new RectF(pdfDocument$Page0.getInfo().getContentRect());
                        }
                        else {
                            PrintedPdfDocument printedPdfDocument1 = new PrintedPdfDocument(PrintHelper.this.mContext, printAttributes0);
                            PdfDocument.Page pdfDocument$Page1 = printedPdfDocument1.startPage(1);
                            RectF rectF1 = new RectF(pdfDocument$Page1.getInfo().getContentRect());
                            printedPdfDocument1.finishPage(pdfDocument$Page1);
                            printedPdfDocument1.close();
                            rectF0 = rectF1;
                        }
                        Matrix matrix0 = PrintHelper.getMatrix(bitmap0.getWidth(), bitmap0.getHeight(), rectF0, v);
                        if(!PrintHelper.IS_MIN_MARGINS_HANDLING_CORRECT) {
                            matrix0.postTranslate(rectF0.left, rectF0.top);
                            pdfDocument$Page0.getCanvas().clipRect(rectF0);
                        }
                        pdfDocument$Page0.getCanvas().drawBitmap(bitmap0, matrix0, null);
                        printedPdfDocument0.finishPage(pdfDocument$Page0);
                        if(!cancellationSignal0.isCanceled()) {
                            printedPdfDocument0.writeTo(new FileOutputStream(parcelFileDescriptor0.getFileDescriptor()));
                            goto label_33;
                        }
                        goto label_39;
                    }
                    catch(Throwable throwable1) {
                        printedPdfDocument0.close();
                        if(parcelFileDescriptor0 != null) {
                            try {
                                parcelFileDescriptor0.close();
                            }
                            catch(IOException unused_ex) {
                            }
                        }
                        if(bitmap0 != bitmap0) {
                            bitmap0.recycle();
                        }
                        throw throwable1;
                    }
                label_33:
                    printedPdfDocument0.close();
                    if(parcelFileDescriptor0 != null) {
                        try {
                            parcelFileDescriptor0.close();
                        }
                        catch(IOException unused_ex) {
                        }
                    }
                    if(bitmap0 != bitmap0) {
                        bitmap0.recycle();
                    }
                    return null;
                label_39:
                    printedPdfDocument0.close();
                    if(parcelFileDescriptor0 != null) {
                        try {
                            parcelFileDescriptor0.close();
                        }
                        catch(IOException unused_ex) {
                        }
                    }
                    if(bitmap0 != bitmap0) {
                        bitmap0.recycle();
                    }
                    return null;
                }
                catch(Throwable throwable0) {
                    return throwable0;
                }
            }

            @Override  // android.os.AsyncTask
            protected void onPostExecute(Object object0) {
                this.onPostExecute(((Throwable)object0));
            }

            protected void onPostExecute(Throwable throwable0) {
                if(cancellationSignal0.isCanceled()) {
                    printDocumentAdapter$WriteResultCallback0.onWriteCancelled();
                    return;
                }
                if(throwable0 == null) {
                    printDocumentAdapter$WriteResultCallback0.onWriteFinished(new PageRange[]{PageRange.ALL_PAGES});
                    return;
                }
                Log.e("PrintHelper", "Error writing printed content", throwable0);
                printDocumentAdapter$WriteResultCallback0.onWriteFailed(null);
            }
        }.execute(new Void[0]);
    }
}

