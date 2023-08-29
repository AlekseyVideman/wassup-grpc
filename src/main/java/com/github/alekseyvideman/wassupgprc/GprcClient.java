package com.github.alekseyvideman.wassupgprc;

import com.github.alekseyvideman.services.grpc.BroMessage;
import com.github.alekseyvideman.services.grpc.WassupServiceGrpc;
import io.grpc.Channel;

import java.util.logging.Logger;

public class GprcClient {
    private final Logger log = Logger.getLogger(GprcClient.class.getName());
    private final WassupServiceGrpc.WassupServiceBlockingStub stub;

    public GprcClient(Channel channel) {
        stub = WassupServiceGrpc.newBlockingStub(channel);

        new Thread(() -> {
            log.info("Background thread started");
            try {
                Thread.sleep(1000L);


                final var req = BroMessage.newBuilder()
                        .setToWhom("From: Kanye West, bro")
                        .build();
                final var res = stub.sayWassup(req);
                log.info("Received message from my bro: ".concat(res.toString()));


            } catch (InterruptedException e) {
                log.warning("Thread was interrrupted");
            }
        }).start();
    }
}
