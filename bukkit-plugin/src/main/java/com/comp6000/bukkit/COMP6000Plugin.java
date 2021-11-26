package com.comp6000.bukkit;

import com.comp6000.grpc.BackendServiceGrpc;
import com.comp6000.grpc.GenerationDetails;
import com.google.protobuf.Empty;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.bukkit.plugin.java.JavaPlugin;

public class COMP6000Plugin extends JavaPlugin {

  @Override
  public void onEnable() {
    var backendHost = System.getenv("BACKEND_HOST");
    var backendPort = Integer.parseInt(System.getenv("BACKEND_PORT"));
    BackendServiceGrpc.BackendServiceStub backendStub = BackendServiceGrpc.newStub(
        NettyChannelBuilder.forAddress(backendHost, backendPort).usePlaintext()
            .build());

    backendStub.getSchematics(Empty.newBuilder().build(), new StreamObserver<>() {
      @Override
      public void onNext(GenerationDetails generationDetails) {
        getLogger().info(String.format("Received schematic build id: %s, url: %s", generationDetails.getBuildId(),
            generationDetails.getSchematicUrl()));
        getServer().getOnlinePlayers().forEach(player -> {
          player.sendMessage(String.format("Received schematic build id: %s, url: %s", generationDetails.getBuildId(),
              generationDetails.getSchematicUrl()));
        });
      }

      @Override
      public void onError(Throwable throwable) {
        throwable.printStackTrace();
      }

      @Override
      public void onCompleted() {
      }
    });
  }
}
