package com.comp6000.backend.grpc;

import com.comp6000.backend.genetic.feature.Building;
import com.comp6000.grpc.BackendServiceGrpc;
import com.comp6000.grpc.BuildingDetails;
import com.comp6000.grpc.CreateBuildingRequest;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

@GrpcService
public class BackendGrpcServiceImpl extends BackendServiceGrpc.BackendServiceImplBase {

  private final Flux<Building> buildingFlux;

  @Autowired
  public BackendGrpcServiceImpl(Flux<Building> buildingFlux) {
    this.buildingFlux = buildingFlux;
  }

  @Override
  public void streamCreateBuildingRequests(Empty request, StreamObserver<CreateBuildingRequest> responseObserver) {
    buildingFlux.subscribe(building -> {

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
