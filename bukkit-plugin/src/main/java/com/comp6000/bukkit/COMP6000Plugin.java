package com.comp6000.bukkit;

import com.comp6000.bukkit.building.BuildingManager;
import com.comp6000.grpc.BackendServiceGrpc;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class COMP6000Plugin extends JavaPlugin {

  private BuildingManager buildingManager;
  private BackendServiceGrpc.BackendServiceStub backendServiceStub;

  @Override
  public void onEnable() {
    buildingManager = new BuildingManager(this);

    var backendHost = System.getenv("BACKEND_HOST");
    var backendPort = Integer.parseInt(System.getenv("BACKEND_PORT"));
    backendServiceStub = BackendServiceGrpc.newStub(
        NettyChannelBuilder.forAddress(backendHost, backendPort).usePlaintext()
            .build());

    buildingManager.initialize();
  }

  public BackendServiceGrpc.BackendServiceStub getBackendServiceStub() {
    return backendServiceStub;
  }
}
