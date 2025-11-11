package androidx.core.net;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketImpl;

class DatagramSocketWrapper extends Socket {
    static class DatagramSocketImplWrapper extends SocketImpl {
        DatagramSocketImplWrapper(DatagramSocket datagramSocket0, FileDescriptor fileDescriptor0) {
            this.localport = datagramSocket0.getLocalPort();
            this.fd = fileDescriptor0;
        }

        @Override
        protected void accept(SocketImpl socketImpl0) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected int available() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void bind(InetAddress inetAddress0, int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void close() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void connect(String s, int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void connect(InetAddress inetAddress0, int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void connect(SocketAddress socketAddress0, int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void create(boolean z) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected InputStream getInputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object getOption(int v) throws SocketException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected OutputStream getOutputStream() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void listen(int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        protected void sendUrgentData(int v) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setOption(int v, Object object0) throws SocketException {
            throw new UnsupportedOperationException();
        }
    }

    DatagramSocketWrapper(DatagramSocket datagramSocket0, FileDescriptor fileDescriptor0) throws SocketException {
        super(new DatagramSocketImplWrapper(datagramSocket0, fileDescriptor0));
    }
}

