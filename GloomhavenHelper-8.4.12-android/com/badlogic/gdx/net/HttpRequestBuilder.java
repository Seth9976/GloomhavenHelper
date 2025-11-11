package com.badlogic.gdx.net;

import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Pools;
import java.io.InputStream;
import java.util.Map;

public class HttpRequestBuilder {
    public static String baseUrl = "";
    public static int defaultTimeout = 1000;
    private HttpRequest httpRequest;
    public static Json json;

    static {
        HttpRequestBuilder.json = new Json();
    }

    public HttpRequestBuilder basicAuthentication(String s, String s1) {
        this.validate();
        this.httpRequest.setHeader("Authorization", "Basic " + Base64Coder.encodeString((s + ":" + s1)));
        return this;
    }

    public HttpRequest build() {
        this.validate();
        HttpRequest net$HttpRequest0 = this.httpRequest;
        this.httpRequest = null;
        return net$HttpRequest0;
    }

    public HttpRequestBuilder content(InputStream inputStream0, long v) {
        this.validate();
        this.httpRequest.setContent(inputStream0, v);
        return this;
    }

    public HttpRequestBuilder content(String s) {
        this.validate();
        this.httpRequest.setContent(s);
        return this;
    }

    public HttpRequestBuilder followRedirects(boolean z) {
        this.validate();
        this.httpRequest.setFollowRedirects(z);
        return this;
    }

    public HttpRequestBuilder formEncodedContent(Map map0) {
        this.validate();
        this.httpRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");
        String s = HttpParametersUtils.convertHttpParameters(map0);
        this.httpRequest.setContent(s);
        return this;
    }

    public HttpRequestBuilder header(String s, String s1) {
        this.validate();
        this.httpRequest.setHeader(s, s1);
        return this;
    }

    public HttpRequestBuilder includeCredentials(boolean z) {
        this.validate();
        this.httpRequest.setIncludeCredentials(z);
        return this;
    }

    public HttpRequestBuilder jsonContent(Object object0) {
        this.validate();
        this.httpRequest.setHeader("Content-Type", "application/json");
        String s = HttpRequestBuilder.json.toJson(object0);
        this.httpRequest.setContent(s);
        return this;
    }

    public HttpRequestBuilder method(String s) {
        this.validate();
        this.httpRequest.setMethod(s);
        return this;
    }

    public HttpRequestBuilder newRequest() {
        if(this.httpRequest != null) {
            throw new IllegalStateException("A new request has already been started. Call HttpRequestBuilder.build() first.");
        }
        this.httpRequest = (HttpRequest)Pools.obtain(HttpRequest.class);
        this.httpRequest.setTimeOut(HttpRequestBuilder.defaultTimeout);
        return this;
    }

    public HttpRequestBuilder timeout(int v) {
        this.validate();
        this.httpRequest.setTimeOut(v);
        return this;
    }

    public HttpRequestBuilder url(String s) {
        this.validate();
        this.httpRequest.setUrl(HttpRequestBuilder.baseUrl + s);
        return this;
    }

    private void validate() {
        if(this.httpRequest == null) {
            throw new IllegalStateException("A new request has not been started yet. Call HttpRequestBuilder.newRequest() first.");
        }
    }
}

