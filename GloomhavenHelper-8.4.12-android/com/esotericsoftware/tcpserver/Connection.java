package com.esotericsoftware.tcpserver;

import com.esotericsoftware.minlog.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class Connection {
    static class Send {
        byte[] bytes;
        int count;
        String message;
        int offset;

    }

    final String category;
    volatile boolean closed;
    byte[] data;
    private static final byte[] empty;
    final DataInputStream input;
    private final String name;
    final DataOutputStream output;
    final Object outputLock;
    final ArrayBlockingQueue sends;
    private final Socket socket;
    Object userObject;
    Thread writeThread;

    static {
        Connection.empty = new byte[0];
    }

    public Connection(String s, String s1, Socket socket0) throws IOException {
        this.outputLock = new Object();
        this.sends = new ArrayBlockingQueue(0x400, true);
        this.data = Connection.empty;
        this.category = s;
        this.name = s1;
        this.socket = socket0;
        try {
            this.input = new DataInputStream(socket0.getInputStream());
            this.output = new DataOutputStream(socket0.getOutputStream());
        }
        catch(IOException iOException0) {
            throw new IOException("Error opening socket streams.", iOException0);
        }
    }

    public void close() {
        if(Log.INFO && !this.closed) {
            Log.info(this.category, "Client disconnected.");
        }
        this.closed = true;
        Thread thread0 = this.writeThread;
        if(thread0 != null) {
            thread0.interrupt();
        }
        Util.closeQuietly(this.output);
        Util.closeQuietly(this.input);
        Util.closeQuietly(this.socket);
    }

    public Object getUserObject() {
        return this.userObject;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public abstract void receive(String arg1, String arg2, byte[] arg3, int arg4);

    public void send(String s) {
        if(Log.TRACE) {
            Log.trace(this.category, "Queued: " + s);
        }
        this.sends.add(s);
    }

    public void send(String s, byte[] arr_b) {
        this.send(s, arr_b, 0, arr_b.length);
    }

    public void send(String s, byte[] arr_b, int v, int v1) {
        if(v1 != 0 && arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null when count != 0: " + v1);
        }
        if(Log.TRACE) {
            Log.trace(this.category, "Queued: " + s + ", " + v1);
        }
        Send connection$Send0 = new Send();
        connection$Send0.message = s;
        connection$Send0.bytes = arr_b;
        connection$Send0.offset = v;
        connection$Send0.count = v1;
        this.sends.add(connection$Send0);
    }

    public boolean sendBlocking(String s) {
        return this.sendBlocking(s, null, 0, 0);
    }

    public boolean sendBlocking(String s, byte[] arr_b) {
        return this.sendBlocking(s, arr_b, 0, arr_b.length);
    }

    public boolean sendBlocking(String s, byte[] arr_b, int v, int v1) {
        if(v1 != 0 && arr_b == null) {
            throw new IllegalArgumentException("bytes cannot be null when count != 0: " + v1);
        }
        if(this.closed) {
            return false;
        }
        try {
            synchronized(this.outputLock) {
                if(Log.TRACE) {
                    Log.trace(this.category, "Sent: " + s + (v1 <= 0 ? "" : ", " + v1));
                }
                this.output.writeUTF(s);
                Util.writeVarint(v1, this.output);
                if(v1 != 0) {
                    this.output.write(arr_b, v, v1);
                }
                this.output.flush();
                return true;
            }
        }
        catch(IOException iOException0) {
            if(Log.ERROR && !this.closed) {
                Log.error(this.category, "Error writing to connection: " + s, iOException0);
            }
            this.close();
            return false;
        }
    }

    public void setUserObject(Object object0) {
        this.userObject = object0;
    }

    void start() {
        new Thread(this.name + "Read") {
            @Override
            public void run() {
                int v2;
                String s2;
                String s1;
                String s;
                while(!Connection.this.closed) {
                    try {
                        s = Connection.this.input.readUTF();
                        if(s == null || Connection.this.closed) {
                            break;
                        }
                        int v = s.indexOf(" ");
                        if(v == -1) {
                            s1 = s.trim();
                            s2 = "";
                        }
                        else {
                            s1 = s.substring(0, v).trim();
                            s2 = s.substring(v + 1).trim();
                        }
                        v2 = Util.readVarint(Connection.this.input);
                        if(v2 > 0) {
                            if(Connection.this.closed) {
                                break;
                            }
                            if(Connection.this.data.length < v2) {
                                Connection.this.data = new byte[v2];
                            }
                            int v3 = v2;
                            for(int v1 = 0; true; v1 += v4) {
                                int v4 = Connection.this.input.read(Connection.this.data, v1, v3);
                                if(v4 == -1 || Connection.this.closed) {
                                    break;
                                }
                                v3 -= v4;
                                if(v3 == 0) {
                                    break;
                                }
                            }
                        }
                        if(Log.TRACE) {
                            Log.trace(Connection.this.category, "Received: " + s1 + ", " + s2 + (v2 <= 0 ? "" : ", " + v2));
                        }
                    }
                    catch(EOFException eOFException0) {
                        goto label_32;
                    }
                    catch(IOException iOException0) {
                        goto label_38;
                    }
                    catch(Throwable throwable0) {
                        goto label_49;
                    }
                    try {
                        Connection.this.receive(s1, s2, Connection.this.data, v2);
                        continue;
                    }
                    catch(Throwable throwable1) {
                        if(Log.ERROR) {
                            try {
                                Log.error(Connection.this.category, "Error processing message: " + s, throwable1);
                                break;
                            }
                            catch(EOFException eOFException0) {
                                goto label_32;
                            }
                            catch(IOException iOException0) {
                                goto label_38;
                            }
                            catch(Throwable throwable0) {
                                goto label_49;
                            }
                        }
                        else {
                            break;
                        }
                        return;
                    }
                label_32:
                    if(Log.TRACE) {
                        try {
                            Log.trace(Connection.this.category, "Connection has closed.", eOFException0);
                        }
                        catch(Throwable throwable0) {
                            goto label_49;
                        }
                    }
                    Connection.this.close();
                    if(Log.TRACE) {
                        Log.trace(Connection.this.category, "Read thread stopped.");
                        return;
                    }
                    return;
                label_38:
                    if(!Connection.this.closed) {
                        try {
                            if(iOException0.getMessage() == null || !iOException0.getMessage().contains("Connection reset")) {
                                if(Log.ERROR) {
                                    Log.error(Connection.this.category, "Error reading from connection.", iOException0);
                                }
                            }
                            else if(Log.TRACE) {
                                Log.trace(Connection.this.category, "Client connection reset.", iOException0);
                            }
                        }
                        catch(Throwable throwable0) {
                            goto label_49;
                        }
                    }
                    Connection.this.close();
                    if(Log.TRACE) {
                        Log.trace(Connection.this.category, "Read thread stopped.");
                        return;
                    label_49:
                        Connection.this.close();
                        if(Log.TRACE) {
                            Log.trace(Connection.this.category, "Read thread stopped.");
                        }
                        throw throwable0;
                    }
                    return;
                }
                Connection.this.close();
                if(Log.TRACE) {
                    Log.trace(Connection.this.category, "Read thread stopped.");
                }
            }
        }.start();
        this.writeThread = new Thread(this.name + "Write") {
            @Override
            public void run() {
                try {
                    while(!Connection.this.closed) {
                        try {
                            Object object0 = Connection.this.sends.take();
                            if(object0 instanceof String) {
                                Connection.this.sendBlocking(((String)object0), null, 0, 0);
                                continue;
                            }
                            Connection.this.sendBlocking(((Send)object0).message, ((Send)object0).bytes, ((Send)object0).offset, ((Send)object0).count);
                        }
                        catch(InterruptedException unused_ex) {
                        }
                    }
                }
                finally {
                    Connection.this.close();
                    if(Log.TRACE) {
                        Log.trace(Connection.this.category, "Write thread stopped.");
                    }
                }
            }
        };
        this.writeThread.start();
    }
}

