package com.serenegiant.net;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public class UdpSocket {
    private final InetSocketAddress broadcast;
    private DatagramChannel channel;
    private String localAddress;
    private String remoteAddress;
    private int remotePort;
    private SocketAddress sender;

    public UdpSocket(int i) throws SocketException {
        InetAddress inetAddress = null;
        try {
            DatagramChannel datagramChannelOpen = DatagramChannel.open();
            this.channel = datagramChannelOpen;
            datagramChannelOpen.configureBlocking(false);
            DatagramSocket datagramSocketSocket = this.channel.socket();
            datagramSocketSocket.setBroadcast(true);
            datagramSocketSocket.setReuseAddress(true);
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (!networkInterface.isLoopback()) {
                    for (InetAddress inetAddress2 : Collections.list(networkInterface.getInetAddresses())) {
                        if (inetAddress2 instanceof Inet4Address) {
                            inetAddress = inetAddress2;
                        }
                    }
                }
            }
            this.localAddress = inetAddress.getHostAddress();
            datagramSocketSocket.bind(new InetSocketAddress(InetAddress.getByAddress(new byte[]{0, 0, 0, 0}), i));
            byte[] address = inetAddress.getAddress();
            address[3] = -1;
            this.broadcast = new InetSocketAddress(InetAddress.getByAddress(address), i);
        } catch (Exception e) {
            throw new SocketException("UdpSocket#constructor:" + e);
        }
    }

    public void release() {
        if (this.channel != null) {
            try {
                setSoTimeout(200);
            } catch (Exception unused) {
            }
            try {
                this.channel.close();
            } catch (Exception unused2) {
            }
        }
        this.channel = null;
    }

    public DatagramChannel channel() {
        return this.channel;
    }

    public DatagramSocket socket() {
        return this.channel.socket();
    }

    public void setReceiveBufferSize(int i) throws SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            datagramSocketSocket.setReceiveBufferSize(i);
        }
    }

    public void setReuseAddress(boolean z) throws SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            datagramSocketSocket.setReuseAddress(z);
        }
    }

    public boolean getReuseAddress() throws IllegalStateException, SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            return datagramSocketSocket.getReuseAddress();
        }
        throw new IllegalStateException("already released");
    }

    public void setBroadcast(boolean z) throws SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            datagramSocketSocket.setBroadcast(z);
        }
    }

    public boolean getBroadcast() throws IllegalStateException, SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            return datagramSocketSocket.getBroadcast();
        }
        throw new IllegalStateException("already released");
    }

    public void setSoTimeout(int i) throws SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            datagramSocketSocket.setSoTimeout(i);
        }
    }

    public int getSoTimeout() throws IllegalStateException, SocketException {
        DatagramChannel datagramChannel = this.channel;
        DatagramSocket datagramSocketSocket = datagramChannel != null ? datagramChannel.socket() : null;
        if (datagramSocketSocket != null) {
            return datagramSocketSocket.getSoTimeout();
        }
        throw new IllegalStateException("already released");
    }

    public String local() {
        return this.localAddress;
    }

    public String remote() {
        return this.remoteAddress;
    }

    public int remotePort() {
        return this.remotePort;
    }

    public void broadcast(ByteBuffer byteBuffer) throws IllegalStateException, IOException {
        DatagramChannel datagramChannel = this.channel;
        if (datagramChannel == null) {
            throw new IllegalStateException("already released");
        }
        datagramChannel.send(byteBuffer, this.broadcast);
    }

    public void broadcast(byte[] bArr) throws IllegalStateException, IOException {
        broadcast(ByteBuffer.wrap(bArr));
    }

    public int receive(ByteBuffer byteBuffer) throws IllegalStateException, IOException {
        if (this.channel == null) {
            throw new IllegalStateException("already released");
        }
        int iRemaining = byteBuffer.remaining();
        SocketAddress socketAddressReceive = this.channel.receive(byteBuffer);
        this.sender = socketAddressReceive;
        if (socketAddressReceive == null) {
            return -1;
        }
        InetSocketAddress inetSocketAddress = (InetSocketAddress) socketAddressReceive;
        this.remoteAddress = inetSocketAddress.getAddress().getHostAddress();
        this.remotePort = inetSocketAddress.getPort();
        return iRemaining - byteBuffer.remaining();
    }
}
