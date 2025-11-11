package com.badlogic.gdx.net;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NetJavaImpl {
   private final ThreadPoolExecutor executorService;
   final ObjectMap connections;
   final ObjectMap listeners;

   public NetJavaImpl() {
      this(Integer.MAX_VALUE);
   }

   public NetJavaImpl(int maxThreads) {
      boolean isCachedPool = maxThreads == Integer.MAX_VALUE;
      this.executorService = new ThreadPoolExecutor(
         isCachedPool ? 0 : maxThreads,
         maxThreads,
         60L,
         TimeUnit.SECONDS,
         (BlockingQueue)(isCachedPool ? new SynchronousQueue() : new LinkedBlockingQueue()),
         new ThreadFactory() {
            AtomicInteger threadID = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
               Thread thread = new Thread(r, "NetThread" + this.threadID.getAndIncrement());
               thread.setDaemon(true);
               return thread;
            }
         }
      );
      this.executorService.allowCoreThreadTimeOut(!isCachedPool);
      this.connections = new ObjectMap();
      this.listeners = new ObjectMap();
   }

   public void sendHttpRequest(final Net.HttpRequest httpRequest, final Net.HttpResponseListener httpResponseListener) {
      if (httpRequest.getUrl() == null) {
         httpResponseListener.failed(new GdxRuntimeException("can't process a HTTP request without URL set"));
      } else {
         try {
            String method = httpRequest.getMethod();
            boolean doInput = !method.equalsIgnoreCase("HEAD");
            final boolean doingOutPut = method.equalsIgnoreCase("POST") || method.equalsIgnoreCase("PUT") || method.equalsIgnoreCase("PATCH");
            URL url;
            if (!method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("HEAD")) {
               url = new URL(httpRequest.getUrl());
            } else {
               String queryString = "";
               String value = httpRequest.getContent();
               if (value != null && !"".equals(value)) {
                  queryString = "?" + value;
               }

               url = new URL(httpRequest.getUrl() + queryString);
            }

            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(doingOutPut);
            connection.setDoInput(doInput);
            connection.setRequestMethod(method);
            HttpURLConnection.setFollowRedirects(httpRequest.getFollowRedirects());
            this.putIntoConnectionsAndListeners(httpRequest, httpResponseListener, connection);

            for (Entry header : httpRequest.getHeaders().entrySet()) {
               connection.addRequestProperty((String)header.getKey(), (String)header.getValue());
            }

            connection.setConnectTimeout(httpRequest.getTimeOut());
            connection.setReadTimeout(httpRequest.getTimeOut());
            this.executorService.submit(new Runnable() {
               @Override
               public void run() {
                  try {
                     if (doingOutPut) {
                        String contentAsString = httpRequest.getContent();
                        if (contentAsString != null) {
                           OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF8");

                           try {
                              writer.write(contentAsString);
                           } finally {
                              StreamUtils.closeQuietly(writer);
                           }
                        } else {
                           InputStream contentAsStream = httpRequest.getContentStream();
                           if (contentAsStream != null) {
                              OutputStream os = connection.getOutputStream();

                              try {
                                 StreamUtils.copyStream(contentAsStream, os);
                              } finally {
                                 StreamUtils.closeQuietly(os);
                              }
                           }
                        }
                     }

                     connection.connect();
                     NetJavaImpl.HttpClientResponse clientResponse = new NetJavaImpl.HttpClientResponse(connection);

                     try {
                        Net.HttpResponseListener listener = NetJavaImpl.this.getFromListeners(httpRequest);
                        if (listener != null) {
                           listener.handleHttpResponse(clientResponse);
                        }

                        NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest);
                     } finally {
                        connection.disconnect();
                     }
                  } catch (Exception var31) {
                     Exception e = var31;
                     connection.disconnect();

                     try {
                        httpResponseListener.failed(e);
                     } finally {
                        NetJavaImpl.this.removeFromConnectionsAndListeners(httpRequest);
                     }
                  }
               }
            });
         } catch (Exception var14) {
            Exception e = var14;

            try {
               httpResponseListener.failed(e);
            } finally {
               this.removeFromConnectionsAndListeners(httpRequest);
            }
         }
      }
   }

   public void cancelHttpRequest(Net.HttpRequest httpRequest) {
      Net.HttpResponseListener httpResponseListener = this.getFromListeners(httpRequest);
      if (httpResponseListener != null) {
         httpResponseListener.cancelled();
         this.removeFromConnectionsAndListeners(httpRequest);
      }
   }

   synchronized void removeFromConnectionsAndListeners(Net.HttpRequest httpRequest) {
      this.connections.remove(httpRequest);
      this.listeners.remove(httpRequest);
   }

   synchronized void putIntoConnectionsAndListeners(Net.HttpRequest httpRequest, Net.HttpResponseListener httpResponseListener, HttpURLConnection connection) {
      this.connections.put(httpRequest, connection);
      this.listeners.put(httpRequest, httpResponseListener);
   }

   synchronized Net.HttpResponseListener getFromListeners(Net.HttpRequest httpRequest) {
      return (Net.HttpResponseListener)this.listeners.get(httpRequest);
   }

   static class HttpClientResponse implements Net.HttpResponse {
      private final HttpURLConnection connection;
      private HttpStatus status;

      public HttpClientResponse(HttpURLConnection connection) throws IOException {
         this.connection = connection;

         try {
            this.status = new HttpStatus(connection.getResponseCode());
         } catch (IOException var3) {
            this.status = new HttpStatus(-1);
         }
      }

      @Override
      public byte[] getResult() {
         InputStream input = this.getInputStream();
         if (input == null) {
            return StreamUtils.EMPTY_BYTES;
         } else {
            byte[] var3;
            try {
               return StreamUtils.copyStreamToByteArray(input, this.connection.getContentLength());
            } catch (IOException var7) {
               var3 = StreamUtils.EMPTY_BYTES;
            } finally {
               StreamUtils.closeQuietly(input);
            }

            return var3;
         }
      }

      @Override
      public String getResultAsString() {
         InputStream input = this.getInputStream();
         if (input == null) {
            return "";
         } else {
            String var3;
            try {
               return StreamUtils.copyStreamToString(input, this.connection.getContentLength(), "UTF8");
            } catch (IOException var7) {
               var3 = "";
            } finally {
               StreamUtils.closeQuietly(input);
            }

            return var3;
         }
      }

      @Override
      public InputStream getResultAsStream() {
         return this.getInputStream();
      }

      @Override
      public HttpStatus getStatus() {
         return this.status;
      }

      @Override
      public String getHeader(String name) {
         return this.connection.getHeaderField(name);
      }

      @Override
      public Map getHeaders() {
         return this.connection.getHeaderFields();
      }

      private InputStream getInputStream() {
         try {
            return this.connection.getInputStream();
         } catch (IOException var2) {
            return this.connection.getErrorStream();
         }
      }
   }
}
