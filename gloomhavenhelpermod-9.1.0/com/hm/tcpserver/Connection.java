package com.hm.tcpserver;

import com.hm.minlog.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class Connection {
   private static final byte[] empty = new byte[0];
   final String category;
   private final String name;
   private final Socket socket;
   final DataInputStream input;
   final DataOutputStream output;
   final Object outputLock = new Object();
   final ArrayBlockingQueue sends = new ArrayBlockingQueue(1024, true);
   Thread writeThread;
   volatile boolean closed;
   byte[] data = empty;
   Object userObject;

   public Connection(String category, String name, Socket socket) throws IOException {
      this.category = category;
      this.name = name;
      this.socket = socket;

      try {
         this.input = new DataInputStream(socket.getInputStream());
         this.output = new DataOutputStream(socket.getOutputStream());
      } catch (IOException var5) {
         throw new IOException("Error opening socket streams.", var5);
      }
   }

   void start() {
      (new Thread(this.name + "Read") {
         @Override
         public void run() {
            try {
               while (!Connection.this.closed) {
                  String message = Connection.this.input.readUTF();
                  if (message != null && !Connection.this.closed) {
                     int index = message.indexOf(" ");
                     String event;
                     String payload;
                     if (index != -1) {
                        event = message.substring(0, index).trim();
                        payload = message.substring(index + 1).trim();
                     } else {
                        event = message.trim();
                        payload = "";
                     }

                     int dataLength = Util.readVarint(Connection.this.input);
                     if (dataLength > 0) {
                        if (Connection.this.closed) {
                           return;
                        }

                        if (Connection.this.data.length < dataLength) {
                           Connection.this.data = new byte[dataLength];
                        }

                        int offset = 0;
                        int remaining = dataLength;

                        while (true) {
                           int count = Connection.this.input.read(Connection.this.data, offset, remaining);
                           if (count == -1 || Connection.this.closed) {
                              break;
                           }

                           remaining -= count;
                           if (remaining == 0) {
                              break;
                           }

                           offset += count;
                        }
                     }

                     if (Log.TRACE) {
                        Log.trace(Connection.this.category, "Received: " + event + ", " + payload + (dataLength > 0 ? ", " + dataLength : ""));
                     }

                     try {
                        Connection.this.receive(event, payload, Connection.this.data, dataLength);
                        continue;
                     } catch (Throwable var14) {
                        if (Log.ERROR) {
                           Log.error(Connection.this.category, "Error processing message: " + message, var14);
                        }
                     }
                  }
                  break;
               }
            } catch (EOFException var15) {
               if (Log.TRACE) {
                  Log.trace(Connection.this.category, "Connection has closed.", var15);
               }
            } catch (IOException var16) {
               if (!Connection.this.closed) {
                  if (var16.getMessage() != null && var16.getMessage().contains("Connection reset")) {
                     if (Log.TRACE) {
                        Log.trace(Connection.this.category, "Client connection reset.", var16);
                     }
                  } else if (Log.ERROR) {
                     Log.error(Connection.this.category, "Error reading from connection.", var16);
                  }
               }
            } finally {
               Connection.this.close();
               if (Log.TRACE) {
                  Log.trace(Connection.this.category, "Read thread stopped.");
               }
            }
         }
      }).start();
      this.writeThread = new Thread(this.name + "Write") {
         @Override
         public void run() {
            try {
               while (!Connection.this.closed) {
                  try {
                     Object object = Connection.this.sends.take();
                     if (object instanceof String) {
                        Connection.this.sendBlocking((String)object, null, 0, 0);
                     } else {
                        Connection.Send send = (Connection.Send)object;
                        Connection.this.sendBlocking(send.message, send.bytes, send.offset, send.count);
                     }
                  } catch (InterruptedException var6) {
                  }
               }
            } finally {
               Connection.this.close();
               if (Log.TRACE) {
                  Log.trace(Connection.this.category, "Write thread stopped.");
               }
            }
         }
      };
      this.writeThread.start();
   }

   public void send(String message) {
      if (Log.TRACE) {
         Log.trace(this.category, "Queued: " + message);
      }

      this.sends.add(message);
   }

   public void send(String message, byte[] bytes) {
      this.send(message, bytes, 0, bytes.length);
   }

   public void send(String message, byte[] bytes, int offset, int count) {
      if (count != 0 && bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null when count != 0: " + count);
      } else {
         if (Log.TRACE) {
            Log.trace(this.category, "Queued: " + message + ", " + count);
         }

         Connection.Send send = new Connection.Send();
         send.message = message;
         send.bytes = bytes;
         send.offset = offset;
         send.count = count;
         this.sends.add(send);
      }
   }

   public boolean sendBlocking(String message) {
      return this.sendBlocking(message, null, 0, 0);
   }

   public boolean sendBlocking(String message, byte[] bytes) {
      return this.sendBlocking(message, bytes, 0, bytes.length);
   }

   public boolean sendBlocking(String message, byte[] bytes, int offset, int count) {
      if (count != 0 && bytes == null) {
         throw new IllegalArgumentException("bytes cannot be null when count != 0: " + count);
      } else if (this.closed) {
         return false;
      } else {
         try {
            synchronized (this.outputLock) {
               if (Log.TRACE) {
                  Log.trace(this.category, "Sent: " + message + (count > 0 ? ", " + count : ""));
               }

               this.output.writeUTF(message);
               Util.writeVarint(count, this.output);
               if (count != 0) {
                  this.output.write(bytes, offset, count);
               }

               this.output.flush();
            }

            return true;
         } catch (IOException var8) {
            if (Log.ERROR && !this.closed) {
               Log.error(this.category, "Error writing to connection: " + message, var8);
            }

            this.close();
            return false;
         }
      }
   }

   public abstract void receive(String var1, String var2, byte[] var3, int var4);

   public void close() {
      if (Log.INFO && !this.closed) {
         Log.info(this.category, "Client disconnected.");
      }

      this.closed = true;
      if (this.writeThread != null) {
         this.writeThread.interrupt();
      }

      Util.closeQuietly(this.output);
      Util.closeQuietly(this.input);
      Util.closeQuietly(this.socket);
   }

   public boolean isClosed() {
      return this.closed;
   }

   public Object getUserObject() {
      return this.userObject;
   }

   public void setUserObject(Object userObject) {
      this.userObject = userObject;
   }

   static class Send {
      String message;
      byte[] bytes;
      int offset;
      int count;
   }
}
