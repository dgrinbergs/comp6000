package com.comp6000.bukkit;

import com.comp6000.bukkit.build.BuildListener;
import com.comp6000.bukkit.build.BuildManager;
import com.comp6000.grpc.BackendServiceGrpc;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.bukkit.plugin.java.JavaPlugin;

public class COMP6000Plugin extends JavaPlugin {

  private BuildManager buildManager;
  private BackendServiceGrpc.BackendServiceStub backendServiceStub;

  @Override
  public void onEnable() {
    buildManager = new BuildManager(this);

    getServer().getPluginManager().registerEvents(new BuildListener(buildManager), this);

    var backendHost = System.getenv("BACKEND_HOST");
    var backendPort = Integer.parseInt(System.getenv("BACKEND_PORT"));
    backendServiceStub = BackendServiceGrpc.newStub(
        NettyChannelBuilder.forAddress(backendHost, backendPort).usePlaintext()
            .build());

    buildManager.subscribeToBuilds();
  }

  public BackendServiceGrpc.BackendServiceStub getBackendServiceStub() {
    return backendServiceStub;
  }
}
