package com.badlogic.gdx.backends.android;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.NetJavaImpl;
import com.badlogic.gdx.net.NetJavaServerSocketImpl;
import com.badlogic.gdx.net.NetJavaSocketImpl;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.net.SocketHints;

public class AndroidNet implements Net {
    final AndroidApplicationBase app;
    NetJavaImpl netJavaImpl;

    public AndroidNet(AndroidApplicationBase androidApplicationBase0, AndroidApplicationConfiguration androidApplicationConfiguration0) {
        this.app = androidApplicationBase0;
        this.netJavaImpl = new NetJavaImpl(androidApplicationConfiguration0.maxNetThreads);
    }

    @Override  // com.badlogic.gdx.Net
    public void cancelHttpRequest(HttpRequest net$HttpRequest0) {
        this.netJavaImpl.cancelHttpRequest(net$HttpRequest0);
    }

    @Override  // com.badlogic.gdx.Net
    public Socket newClientSocket(Protocol net$Protocol0, String s, int v, SocketHints socketHints0) {
        return new NetJavaSocketImpl(net$Protocol0, s, v, socketHints0);
    }

    @Override  // com.badlogic.gdx.Net
    public ServerSocket newServerSocket(Protocol net$Protocol0, int v, ServerSocketHints serverSocketHints0) {
        return new NetJavaServerSocketImpl(net$Protocol0, v, serverSocketHints0);
    }

    @Override  // com.badlogic.gdx.Net
    public ServerSocket newServerSocket(Protocol net$Protocol0, String s, int v, ServerSocketHints serverSocketHints0) {
        return new NetJavaServerSocketImpl(net$Protocol0, s, v, serverSocketHints0);
    }

    @Override  // com.badlogic.gdx.Net
    public boolean openURI(String s) {
        Uri uri0 = Uri.parse(s);
        try {
            Intent intent0 = new Intent("android.intent.action.VIEW", uri0);
            if(!(this.app.getContext() instanceof Activity)) {
                intent0.addFlags(0x10000000);
            }
            this.app.startActivity(intent0);
            return true;
        }
        catch(ActivityNotFoundException unused_ex) {
            return false;
        }
    }

    @Override  // com.badlogic.gdx.Net
    public void sendHttpRequest(HttpRequest net$HttpRequest0, HttpResponseListener net$HttpResponseListener0) {
        this.netJavaImpl.sendHttpRequest(net$HttpRequest0, net$HttpResponseListener0);
    }
}

