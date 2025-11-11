package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.annotation.RestrictTo.Scope;
import androidx.annotation.RestrictTo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {
    static class FieldBuffer {
        final DataOutputStream mDataStream;
        private final int mFieldId;
        final ByteArrayOutputStream mOutput;
        private final DataOutputStream mTarget;

        FieldBuffer(int v, DataOutputStream dataOutputStream0) {
            this.mOutput = new ByteArrayOutputStream();
            this.mDataStream = new DataOutputStream(this.mOutput);
            this.mFieldId = v;
            this.mTarget = dataOutputStream0;
        }

        void flushField() throws IOException {
            this.mDataStream.flush();
            int v = this.mOutput.size();
            this.mTarget.writeInt(this.mFieldId << 16 | (v < 0xFFFF ? v : 0xFFFF));
            if(v >= 0xFFFF) {
                this.mTarget.writeInt(v);
            }
            this.mOutput.writeTo(this.mTarget);
        }
    }

    static class InputBuffer {
        final int mFieldId;
        final DataInputStream mInputStream;
        private final int mSize;

        InputBuffer(int v, int v1, DataInputStream dataInputStream0) throws IOException {
            this.mSize = v1;
            this.mFieldId = v;
            byte[] arr_b = new byte[this.mSize];
            dataInputStream0.readFully(arr_b);
            this.mInputStream = new DataInputStream(new ByteArrayInputStream(arr_b));
        }
    }

    private static final int TYPE_BOOLEAN = 5;
    private static final int TYPE_BOOLEAN_ARRAY = 6;
    private static final int TYPE_DOUBLE = 7;
    private static final int TYPE_DOUBLE_ARRAY = 8;
    private static final int TYPE_FLOAT = 13;
    private static final int TYPE_FLOAT_ARRAY = 14;
    private static final int TYPE_INT = 9;
    private static final int TYPE_INT_ARRAY = 10;
    private static final int TYPE_LONG = 11;
    private static final int TYPE_LONG_ARRAY = 12;
    private static final int TYPE_NULL = 0;
    private static final int TYPE_STRING = 3;
    private static final int TYPE_STRING_ARRAY = 4;
    private static final int TYPE_SUB_BUNDLE = 1;
    private static final int TYPE_SUB_PERSISTABLE_BUNDLE = 2;
    private static final Charset UTF_16;
    private final SparseArray mCachedFields;
    private DataInputStream mCurrentInput;
    private DataOutputStream mCurrentOutput;
    private FieldBuffer mFieldBuffer;
    private boolean mIgnoreParcelables;
    private final DataInputStream mMasterInput;
    private final DataOutputStream mMasterOutput;

    static {
        VersionedParcelStream.UTF_16 = Charset.forName("UTF-16");
    }

    public VersionedParcelStream(InputStream inputStream0, OutputStream outputStream0) {
        this.mCachedFields = new SparseArray();
        DataOutputStream dataOutputStream0 = null;
        this.mMasterInput = inputStream0 == null ? null : new DataInputStream(inputStream0);
        if(outputStream0 != null) {
            dataOutputStream0 = new DataOutputStream(outputStream0);
        }
        this.mMasterOutput = dataOutputStream0;
        this.mCurrentInput = this.mMasterInput;
        this.mCurrentOutput = this.mMasterOutput;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void closeField() {
        FieldBuffer versionedParcelStream$FieldBuffer0 = this.mFieldBuffer;
        if(versionedParcelStream$FieldBuffer0 != null) {
            try {
                if(versionedParcelStream$FieldBuffer0.mOutput.size() != 0) {
                    this.mFieldBuffer.flushField();
                }
                this.mFieldBuffer = null;
            }
            catch(IOException iOException0) {
                throw new ParcelException(iOException0);
            }
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    protected VersionedParcel createSubParcel() {
        return new VersionedParcelStream(this.mCurrentInput, this.mCurrentOutput);
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public boolean isStream() {
        return true;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public boolean readBoolean() {
        try {
            return this.mCurrentInput.readBoolean();
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public Bundle readBundle() {
        int v = this.readInt();
        if(v < 0) {
            return null;
        }
        Bundle bundle0 = new Bundle();
        for(int v1 = 0; v1 < v; ++v1) {
            String s = this.readString();
            this.readObject(this.readInt(), s, bundle0);
        }
        return bundle0;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public byte[] readByteArray() {
        try {
            int v = this.mCurrentInput.readInt();
            if(v > 0) {
                byte[] arr_b = new byte[v];
                this.mCurrentInput.readFully(arr_b);
                return arr_b;
            }
            return null;
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public double readDouble() {
        try {
            return this.mCurrentInput.readDouble();
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public boolean readField(int v) {
        InputBuffer versionedParcelStream$InputBuffer0 = (InputBuffer)this.mCachedFields.get(v);
        if(versionedParcelStream$InputBuffer0 != null) {
            this.mCachedFields.remove(v);
            this.mCurrentInput = versionedParcelStream$InputBuffer0.mInputStream;
            return true;
        }
        try {
            while(true) {
                int v1 = this.mMasterInput.readInt();
                InputBuffer versionedParcelStream$InputBuffer1 = new InputBuffer(v1 >> 16 & 0xFFFF, ((v1 & 0xFFFF) == 0xFFFF ? this.mMasterInput.readInt() : v1 & 0xFFFF), this.mMasterInput);
                if(versionedParcelStream$InputBuffer1.mFieldId == v) {
                    this.mCurrentInput = versionedParcelStream$InputBuffer1.mInputStream;
                    return true;
                }
                this.mCachedFields.put(versionedParcelStream$InputBuffer1.mFieldId, versionedParcelStream$InputBuffer1);
            }
        }
        catch(IOException unused_ex) {
            return false;
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public float readFloat() {
        try {
            return this.mCurrentInput.readFloat();
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public int readInt() {
        try {
            return this.mCurrentInput.readInt();
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public long readLong() {
        try {
            return this.mCurrentInput.readLong();
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    private void readObject(int v, String s, Bundle bundle0) {
        switch(v) {
            case 0: {
                bundle0.putParcelable(s, null);
                return;
            }
            case 1: {
                bundle0.putBundle(s, this.readBundle());
                return;
            }
            case 2: {
                bundle0.putBundle(s, this.readBundle());
                return;
            }
            case 3: {
                bundle0.putString(s, this.readString());
                return;
            }
            case 4: {
                bundle0.putStringArray(s, ((String[])this.readArray(new String[0])));
                return;
            }
            case 5: {
                bundle0.putBoolean(s, this.readBoolean());
                return;
            }
            case 6: {
                bundle0.putBooleanArray(s, this.readBooleanArray());
                return;
            }
            case 7: {
                bundle0.putDouble(s, this.readDouble());
                return;
            }
            case 8: {
                bundle0.putDoubleArray(s, this.readDoubleArray());
                return;
            }
            case 9: {
                bundle0.putInt(s, this.readInt());
                return;
            }
            case 10: {
                bundle0.putIntArray(s, this.readIntArray());
                return;
            }
            case 11: {
                bundle0.putLong(s, this.readLong());
                return;
            }
            case 12: {
                bundle0.putLongArray(s, this.readLongArray());
                return;
            }
            case 13: {
                bundle0.putFloat(s, this.readFloat());
                return;
            }
            case 14: {
                bundle0.putFloatArray(s, this.readFloatArray());
                return;
            }
            default: {
                throw new RuntimeException("Unknown type " + v);
            }
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public Parcelable readParcelable() {
        return null;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public String readString() {
        try {
            int v = this.mCurrentInput.readInt();
            if(v > 0) {
                byte[] arr_b = new byte[v];
                this.mCurrentInput.readFully(arr_b);
                return new String(arr_b, VersionedParcelStream.UTF_16);
            }
            return null;
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public IBinder readStrongBinder() {
        return null;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void setOutputField(int v) {
        this.closeField();
        this.mFieldBuffer = new FieldBuffer(v, this.mMasterOutput);
        this.mCurrentOutput = this.mFieldBuffer.mDataStream;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void setSerializationFlags(boolean z, boolean z1) {
        if(!z) {
            throw new RuntimeException("Serialization of this object is not allowed");
        }
        this.mIgnoreParcelables = z1;
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeBoolean(boolean z) {
        try {
            this.mCurrentOutput.writeBoolean(z);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeBundle(Bundle bundle0) {
        try {
            if(bundle0 == null) {
                this.mCurrentOutput.writeInt(-1);
                return;
            }
            Set set0 = bundle0.keySet();
            this.mCurrentOutput.writeInt(set0.size());
            for(Object object0: set0) {
                this.writeString(((String)object0));
                this.writeObject(bundle0.get(((String)object0)));
            }
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] arr_b) {
        try {
            if(arr_b != null) {
                this.mCurrentOutput.writeInt(arr_b.length);
                this.mCurrentOutput.write(arr_b);
                return;
            }
            this.mCurrentOutput.writeInt(-1);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeByteArray(byte[] arr_b, int v, int v1) {
        try {
            if(arr_b != null) {
                this.mCurrentOutput.writeInt(v1);
                this.mCurrentOutput.write(arr_b, v, v1);
                return;
            }
            this.mCurrentOutput.writeInt(-1);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeDouble(double f) {
        try {
            this.mCurrentOutput.writeDouble(f);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeFloat(float f) {
        try {
            this.mCurrentOutput.writeFloat(f);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeInt(int v) {
        try {
            this.mCurrentOutput.writeInt(v);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeLong(long v) {
        try {
            this.mCurrentOutput.writeLong(v);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    private void writeObject(Object object0) {
        if(object0 == null) {
            this.writeInt(0);
            return;
        }
        if(object0 instanceof Bundle) {
            this.writeInt(1);
            this.writeBundle(((Bundle)object0));
            return;
        }
        if(object0 instanceof String) {
            this.writeInt(3);
            this.writeString(((String)object0));
            return;
        }
        if(object0 instanceof String[]) {
            this.writeInt(4);
            this.writeArray(((String[])object0));
            return;
        }
        if(object0 instanceof Boolean) {
            this.writeInt(5);
            this.writeBoolean(((Boolean)object0).booleanValue());
            return;
        }
        if(object0 instanceof boolean[]) {
            this.writeInt(6);
            this.writeBooleanArray(((boolean[])object0));
            return;
        }
        if(object0 instanceof Double) {
            this.writeInt(7);
            this.writeDouble(((double)(((Double)object0))));
            return;
        }
        if(object0 instanceof double[]) {
            this.writeInt(8);
            this.writeDoubleArray(((double[])object0));
            return;
        }
        if(object0 instanceof Integer) {
            this.writeInt(9);
            this.writeInt(((int)(((Integer)object0))));
            return;
        }
        if(object0 instanceof int[]) {
            this.writeInt(10);
            this.writeIntArray(((int[])object0));
            return;
        }
        if(object0 instanceof Long) {
            this.writeInt(11);
            this.writeLong(((long)(((Long)object0))));
            return;
        }
        if(object0 instanceof long[]) {
            this.writeInt(12);
            this.writeLongArray(((long[])object0));
            return;
        }
        if(object0 instanceof Float) {
            this.writeInt(13);
            this.writeFloat(((float)(((Float)object0))));
            return;
        }
        if(!(object0 instanceof float[])) {
            throw new IllegalArgumentException("Unsupported type " + object0.getClass());
        }
        this.writeInt(14);
        this.writeFloatArray(((float[])object0));
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeParcelable(Parcelable parcelable0) {
        if(!this.mIgnoreParcelables) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeString(String s) {
        try {
            if(s != null) {
                byte[] arr_b = s.getBytes(VersionedParcelStream.UTF_16);
                this.mCurrentOutput.writeInt(arr_b.length);
                this.mCurrentOutput.write(arr_b);
                return;
            }
            this.mCurrentOutput.writeInt(-1);
        }
        catch(IOException iOException0) {
            throw new ParcelException(iOException0);
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeStrongBinder(IBinder iBinder0) {
        if(!this.mIgnoreParcelables) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    @Override  // androidx.versionedparcelable.VersionedParcel
    public void writeStrongInterface(IInterface iInterface0) {
        if(!this.mIgnoreParcelables) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }
}

