package com.hu.proxy;


import com.google.protobuf.BlockingService;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB;
import org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolServerSideTranslatorPB;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;


public class NameNodeProxyServer {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();

        RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class,
                ProtobufRpcEngine.class);

        ClientNamenodeProtocolPB proxy = new ClientNamenodeProtocolServerSideTranslatorPB(new ClientProtocolImpl());

        BlockingService clientNNPbService = ClientNamenodeProtocolProtos.ClientNamenodeProtocol.
                newReflectiveBlockingService(proxy);

        RPC.Server server = new RPC.Builder(conf)
                .setProtocol(ClientNamenodeProtocolPB.class)
                .setInstance(clientNNPbService)
                .setBindAddress("0.0.0.0")
                .setPort(9000)
                .setVerbose(false)
                .build();

        server.start();
    }
}
