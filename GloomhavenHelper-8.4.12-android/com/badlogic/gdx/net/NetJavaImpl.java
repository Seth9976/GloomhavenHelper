package com.badlogic.gdx.net;

import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.StreamUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class NetJavaImpl {
    static class HttpClientResponse implements HttpResponse {
        private final HttpURLConnection connection;
        private HttpStatus status;

        public HttpClientResponse(HttpURLConnection httpURLConnection0) throws IOException {
            this.connection = httpURLConnection0;
            try {
                this.status = new HttpStatus(httpURLConnection0.getResponseCode());
            }
            catch(IOException unused_ex) {
                this.status = new HttpStatus(-1);
            }
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public String getHeader(String s) {
            return this.connection.getHeaderField(s);
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public Map getHeaders() {
            return this.connection.getHeaderFields();
        }

        private InputStream getInputStream() {
            try {
                return this.connection.getInputStream();
            }
            catch(IOException unused_ex) {
                return this.connection.getErrorStream();
            }
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public byte[] getResult() {
            InputStream inputStream0 = this.getInputStream();
            if(inputStream0 == null) {
                return StreamUtils.EMPTY_BYTES;
            }
            try {
                return StreamUtils.copyStreamToByteArray(inputStream0, this.connection.getContentLength());
            }
            catch(IOException unused_ex) {
                return StreamUtils.EMPTY_BYTES;
            }
            finally {
                StreamUtils.closeQuietly(inputStream0);
            }
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public InputStream getResultAsStream() {
            return this.getInputStream();
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public String getResultAsString() {
            InputStream inputStream0 = this.getInputStream();
            if(inputStream0 == null) {
                return "";
            }
            try {
                return StreamUtils.copyStreamToString(inputStream0, this.connection.getContentLength(), "UTF8");
            }
            catch(IOException unused_ex) {
                return "";
            }
            finally {
                StreamUtils.closeQuietly(inputStream0);
            }
        }

        @Override  // com.badlogic.gdx.Net$HttpResponse
        public HttpStatus getStatus() {
            return this.status;
        }
    }

    final ObjectMap connections;
    private final ThreadPoolExecutor executorService;
    final ObjectMap listeners;

    public NetJavaImpl() {
        this(0x7FFFFFFF);
    }

    public NetJavaImpl(int v) {
        int v1 = v == 0x7FFFFFFF ? 1 : 0;
        TimeUnit timeUnit0 = TimeUnit.SECONDS;
        SynchronousQueue synchronousQueue0 = v1 == 0 ? new LinkedBlockingQueue() : new SynchronousQueue();
        this.executorService = new ThreadPoolExecutor((v1 == 0 ? v : 0), v, 60L, timeUnit0, synchronousQueue0, new ThreadFactory() {
            AtomicInteger threadID;

            {
                this.threadID = new AtomicInteger();
            }

            @Override
            public Thread newThread(Runnable runnable0) {
                Thread thread0 = new Thread(runnable0, "NetThread" + this.threadID.getAndIncrement());
                thread0.setDaemon(true);
                return thread0;
            }
        });
        this.executorService.allowCoreThreadTimeOut(((boolean)(1 ^ v1)));
        this.connections = new ObjectMap();
        this.listeners = new ObjectMap();
    }

    public void cancelHttpRequest(HttpRequest net$HttpRequest0) {
        HttpResponseListener net$HttpResponseListener0 = this.getFromListeners(net$HttpRequest0);
        if(net$HttpResponseListener0 != null) {
            net$HttpResponseListener0.cancelled();
            this.removeFromConnectionsAndListeners(net$HttpRequest0);
        }
    }

    HttpResponseListener getFromListeners(HttpRequest net$HttpRequest0) {
        synchronized(this) {
            return (HttpResponseListener)this.listeners.get(net$HttpRequest0);
        }
    }

    void putIntoConnectionsAndListeners(HttpRequest net$HttpRequest0, HttpResponseListener net$HttpResponseListener0, HttpURLConnection httpURLConnection0) {
        synchronized(this) {
            this.connections.put(net$HttpRequest0, httpURLConnection0);
            this.listeners.put(net$HttpRequest0, net$HttpResponseListener0);
        }
    }

    void removeFromConnectionsAndListeners(HttpRequest net$HttpRequest0) {
        synchronized(this) {
            this.connections.remove(net$HttpRequest0);
            this.listeners.remove(net$HttpRequest0);
        }
    }

    public void sendHttpRequest(HttpRequest net$HttpRequest0, HttpResponseListener net$HttpResponseListener0) {
        URL uRL0;
        if(net$HttpRequest0.getUrl() == null) {
            net$HttpResponseListener0.failed(new GdxRuntimeException("can\'t process a HTTP request without URL set"));
            return;
        }
        try {
            String s = net$HttpRequest0.getMethod();
            boolean z = false;
            boolean z1 = !s.equalsIgnoreCase("HEAD");
            if(s.equalsIgnoreCase("POST") || s.equalsIgnoreCase("PUT") || s.equalsIgnoreCase("PATCH")) {
                z = true;
            }
            if(s.equalsIgnoreCase("GET") || s.equalsIgnoreCase("HEAD")) {
                String s1 = "";
                String s2 = net$HttpRequest0.getContent();
                if(s2 != null && !"".equals(s2)) {
                    s1 = "?" + s2;
                }
                uRL0 = new URL(net$HttpRequest0.getUrl() + s1);
            }
            else {
                uRL0 = new URL(net$HttpRequest0.getUrl());
            }
            URLConnection uRLConnection0 = uRL0.openConnection();
            ((HttpURLConnection)uRLConnection0).setDoOutput(z);
            ((HttpURLConnection)uRLConnection0).setDoInput(z1);
            ((HttpURLConnection)uRLConnection0).setRequestMethod(s);
            HttpURLConnection.setFollowRedirects(net$HttpRequest0.getFollowRedirects());
            this.putIntoConnectionsAndListeners(net$HttpRequest0, net$HttpResponseListener0, ((HttpURLConnection)uRLConnection0));
            for(Object object0: net$HttpRequest0.getHeaders().entrySet()) {
                ((HttpURLConnection)uRLConnection0).addRequestProperty(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue()));
            }
            ((HttpURLConnection)uRLConnection0).setConnectTimeout(net$HttpRequest0.getTimeOut());
            ((HttpURLConnection)uRLConnection0).setReadTimeout(net$HttpRequest0.getTimeOut());
            com.badlogic.gdx.net.NetJavaImpl.2 netJavaImpl$20 = new Runnable() {
                @Override
                public void run() {
                    try {
                        if(z) {
                            String s = net$HttpRequest0.getContent();
                            if(s == null) {
                                InputStream inputStream0 = net$HttpRequest0.getContentStream();
                                if(inputStream0 != null) {
                                    OutputStream outputStream0 = ((HttpURLConnection)uRLConnection0).getOutputStream();
                                    try {
                                        StreamUtils.copyStream(inputStream0, outputStream0);
                                    }
                                    finally {
                                        StreamUtils.closeQuietly(outputStream0);
                                    }
                                }
                            }
                            else {
                                OutputStreamWriter outputStreamWriter0 = new OutputStreamWriter(((HttpURLConnection)uRLConnection0).getOutputStream(), "UTF8");
                                try {
                                    outputStreamWriter0.write(s);
                                }
                                finally {
                                    StreamUtils.closeQuietly(outputStreamWriter0);
                                }
                            }
                        }
                        ((HttpURLConnection)uRLConnection0).connect();
                        HttpClientResponse netJavaImpl$HttpClientResponse0 = new HttpClientResponse(((HttpURLConnection)uRLConnection0));
                        try {
                            HttpResponseListener net$HttpResponseListener0 = NetJavaImpl.this.getFromListeners(net$HttpRequest0);
                            if(net$HttpResponseListener0 != null) {
                                net$HttpResponseListener0.handleHttpResponse(netJavaImpl$HttpClientResponse0);
                            }
                            NetJavaImpl.this.removeFromConnectionsAndListeners(net$HttpRequest0);
                        }
                        finally {
                            ((HttpURLConnection)uRLConnection0).disconnect();
                        }
                    }
                    catch(Exception exception0) {
                        ((HttpURLConnection)uRLConnection0).disconnect();
                        try {
                            net$HttpResponseListener0.failed(exception0);
                        }
                        finally {
                            NetJavaImpl.this.removeFromConnectionsAndListeners(net$HttpRequest0);
                        }
                    }
                }
            };
            this.executorService.submit(netJavaImpl$20);
        }
        catch(Exception unused_ex) {
            try {
                net$HttpResponseListener0.failed(exception0);
            }
            finally {
                this.removeFromConnectionsAndListeners(net$HttpRequest0);
            }
        }
    }
}

