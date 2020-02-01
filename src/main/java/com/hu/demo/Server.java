package com.hu.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        RPC.Server server = new RPC.Builder(new Configuration())
                .setProtocol(DemoProtocol.class)
                .setInstance(new DemoProtocolImpl())
                .setBindAddress("0.0.0.0")
                .setPort(9000)
                .build();
        server.start();
    }
}
