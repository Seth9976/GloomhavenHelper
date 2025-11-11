package com.hm.gloomhavenhelper;

import java.io.IOException;
import java.io.OutputStream;

public class MultiplexOutputStream extends OutputStream {
   private final OutputStream[] streams;

   public MultiplexOutputStream(OutputStream... streams) {
      if (streams == null) {
         throw new IllegalArgumentException("streams cannot be null.");
      } else {
         this.streams = streams;
      }
   }

   @Override
   public void write(int b) throws IOException {
      for (int i = 0; i < this.streams.length; i++) {
         synchronized (this.streams[i]) {
            this.streams[i].write(b);
         }
      }
   }

   @Override
   public void write(byte[] b, int off, int len) throws IOException {
      for (int i = 0; i < this.streams.length; i++) {
         synchronized (this.streams[i]) {
            this.streams[i].write(b, off, len);
         }
      }
   }
}
