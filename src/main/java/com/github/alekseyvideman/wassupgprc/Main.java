package com.github.alekseyvideman.wassupgprc;

import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {
    private final static String HOST = "localhost";
    private final static Integer PORT = 8080;

    public static void main(String[] args) throws IOException, InterruptedException {
        final var server = ServerBuilder
                .forPort(PORT)
                .addService(new WassupService()).build();

        new GprcClient(
                NettyChannelBuilder.forAddress(new InetSocketAddress(HOST, PORT))
                        .usePlaintext() // to run it without SSL
                        .build()
        ); // I know it`s ugly

        server.start();
        server.awaitTermination();
    }
}
