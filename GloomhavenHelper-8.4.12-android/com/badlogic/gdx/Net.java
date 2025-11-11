package com.badlogic.gdx;

import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;
import com.badlogic.gdx.utils.Null;
import com.badlogic.gdx.utils.Pool.Poolable;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public interface Net {
    public interface HttpMethods {
        public static final String DELETE = "DELETE";
        public static final String GET = "GET";
        public static final String HEAD = "HEAD";
        public static final String PATCH = "PATCH";
        public static final String POST = "POST";
        public static final String PUT = "PUT";

    }

    public static class HttpRequest implements Poolable {
        private String content;
        private long contentLength;
        private InputStream contentStream;
        private boolean followRedirects;
        private Map headers;
        private String httpMethod;
        private boolean includeCredentials;
        private int timeOut;
        private String url;

        public HttpRequest() {
            this.timeOut = 0;
            this.followRedirects = true;
            this.includeCredentials = false;
            this.headers = new HashMap();
        }

        public HttpRequest(String s) {
            this.httpMethod = s;
        }

        public String getContent() {
            return this.content;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public InputStream getContentStream() {
            return this.contentStream;
        }

        public boolean getFollowRedirects() {
            return this.followRedirects;
        }

        public Map getHeaders() {
            return this.headers;
        }

        public boolean getIncludeCredentials() {
            return this.includeCredentials;
        }

        public String getMethod() {
            return this.httpMethod;
        }

        public int getTimeOut() {
            return this.timeOut;
        }

        public String getUrl() {
            return this.url;
        }

        @Override  // com.badlogic.gdx.utils.Pool$Poolable
        public void reset() {
            this.httpMethod = null;
            this.url = null;
            this.headers.clear();
            this.timeOut = 0;
            this.content = null;
            this.contentStream = null;
            this.contentLength = 0L;
            this.followRedirects = true;
        }

        public void setContent(InputStream inputStream0, long v) {
            this.contentStream = inputStream0;
            this.contentLength = v;
        }

        public void setContent(String s) {
            this.content = s;
        }

        public void setFollowRedirects(boolean z) throws IllegalArgumentException {
            if(!z && Gdx.app.getType() == ApplicationType.WebGL) {
                throw new IllegalArgumentException("Following redirects can\'t be disabled using the GWT/WebGL backend!");
            }
            this.followRedirects = z;
        }

        public void setHeader(String s, String s1) {
            this.headers.put(s, s1);
        }

        public void setIncludeCredentials(boolean z) {
            this.includeCredentials = z;
        }

        public void setMethod(String s) {
            this.httpMethod = s;
        }

        public void setTimeOut(int v) {
            this.timeOut = v;
        }

        public void setUrl(String s) {
            this.url = s;
        }
    }

    public interface HttpResponse {
        String getHeader(String arg1);

        Map getHeaders();

        byte[] getResult();

        InputStream getResultAsStream();

        String getResultAsString();

        HttpStatus getStatus();
    }

    public interface HttpResponseListener {
        void cancelled();

        void failed(Throwable arg1);

        void handleHttpResponse(HttpResponse arg1);
    }

    public static enum Protocol {
        TCP;

    }

    void cancelHttpRequest(HttpRequest arg1);

    Socket newClientSocket(Protocol arg1, String arg2, int arg3, SocketHints arg4);

    ServerSocket newServerSocket(Protocol arg1, int arg2, ServerSocketHints arg3);

    ServerSocket newServerSocket(Protocol arg1, String arg2, int arg3, ServerSocketHints arg4);

    boolean openURI(String arg1);

    void sendHttpRequest(HttpRequest arg1, @Null HttpResponseListener arg2);
}

