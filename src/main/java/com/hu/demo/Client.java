package com.hu.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Client {
    public static void main(String[] args) throws IOException {
        DemoProtocol proxy = RPC.getProxy(DemoProtocol.class,
                DemoProtocol.versionID,
                new InetSocketAddress("localhost", 9000),
                new Configuration());
        System.out.println(proxy.sum(1, 2, 3, 4, 5));
    }
}
