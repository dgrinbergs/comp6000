package com.comp6000.backend.grpc;

import com.comp6000.backend.genetic.Building;
import com.comp6000.grpc.BackendServiceGrpc;
import com.comp6000.grpc.BuildingDetails;
import com.comp6000.grpc.CreateBuildingRequest;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Sinks;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

@GrpcService
public class BackendGrpcServiceImpl extends BackendServiceGrpc.BackendServiceImplBase {

  private final Sinks.Many<Building> sink;
  private final Queue<Building> queue = new ArrayBlockingQueue<>(1);

  private static final Logger LOGGER = LoggerFactory.getLogger(BackendGrpcServiceImpl.class);

  public BackendGrpcServiceImpl() {
    this.sink = Sinks.many().unicast().onBackpressureBuffer(queue);
  }

  public void consumeBuilding(Building building) {
    sink.emitNext(building, (signalType, emitResult) -> {
      LOGGER.error(signalType.toString());
      return true; //TODO: find out what this does
    });
    LOGGER.info("queue has {} items.", queue.size());
  }

  @Override
  public void streamCreateBuildingRequests(Empty request, StreamObserver<CreateBuildingRequest> responseObserver) {
    sink.asFlux()
        .log()
        .subscribe(building -> {

      var buildingDetails = BuildingDetails.newBuilder()
          .setCornerBlockId(building.cornerBlock().getMinecraftId())
          .setFloorBlockId(building.floorBlock().getMinecraftId())
          .setRoofBlockId(building.roofBlock().getMinecraftId())
          .setWallBlockId(building.wallBlock().getMinecraftId())
          .setBedBlockId(building.bedBlock().getMinecraftId())
          .setDoorBlockId(building.doorBlock().getMinecraftId())
          .setWindowBlockId(building.windowBlock().getMinecraftId())
          .build();

      responseObserver.onNext(CreateBuildingRequest.newBuilder()
          .setBuildingId(building.id().toString())
          .setBuildingDetails(buildingDetails)
          .build());
    });
  }
}
