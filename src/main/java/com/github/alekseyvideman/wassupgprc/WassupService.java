package com.github.alekseyvideman.wassupgprc;

import com.github.alekseyvideman.services.grpc.BroMessage;
import com.github.alekseyvideman.services.grpc.BroResponse;
import com.github.alekseyvideman.services.grpc.WassupServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class WassupService extends WassupServiceGrpc.WassupServiceImplBase {
    private final Logger log = Logger.getLogger(WassupService.class.getName());

    @Override
    public void sayWassup(BroMessage request, StreamObserver<BroResponse> responseObserver) {
        log.info("Message received: " + request.toString());
        final var response = BroResponse.newBuilder()
                .setFromWho("CJ from GTA")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
