package com.comp6000.backend.grpc;

import com.comp6000.backend.builds.BuildDetails;
import com.comp6000.backend.builds.events.BuildEvent;
import com.comp6000.backend.generation.GenerationService;
import com.comp6000.grpc.BackendServiceGrpc;
import com.comp6000.grpc.GenerationDetails;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

@GrpcService
public class BackendGrpcServiceImpl extends BackendServiceGrpc.BackendServiceImplBase {

  private final Flux<BuildEvent> buildEventFlux;
  private final GenerationService generationService;

  @Autowired
  public BackendGrpcServiceImpl(Flux<BuildEvent> buildEventFlux, GenerationService generationService) {
    this.buildEventFlux = buildEventFlux;
    this.generationService = generationService;
  }

  @Override
  public void getBuilds(Empty request, StreamObserver<GenerationDetails> responseObserver) {
    buildEventFlux.subscribe(event -> {
      var buildDetails = (BuildDetails) event.getSource();
      var schematicUrl = generationService.generateSchematicForBuild(buildDetails);
      responseObserver.onNext(GenerationDetails.newBuilder()
          .setBuildId(buildDetails.getBuildId().toString())
          .setSchematicUrl(schematicUrl)
          .build());
    });
  }
}
