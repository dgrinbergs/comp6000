package com.comp6000.bukkit.building;

import com.comp6000.grpc.CreateBuildingRequest;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class CreateBuildingRequestStreamObserver implements StreamObserver<CreateBuildingRequest> {

  private final BuildingManager buildingManager;

  public CreateBuildingRequestStreamObserver(BuildingManager buildingManager) {
    this.buildingManager = buildingManager;
  }

  @Override
  public void onNext(CreateBuildingRequest request) {
    buildingManager.createBuilding(UUID.fromString(request.getBuildingId()), request.getBuildingDetails());
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @Override
  public void onCompleted() {
  }
}
