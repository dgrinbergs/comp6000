package com.comp6000.bukkit.build;

import com.comp6000.bukkit.COMP6000Plugin;
import com.comp6000.bukkit.util.VoidGenerator;
import com.comp6000.grpc.GenerationDetails;
import com.google.protobuf.Empty;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.math.BlockVector3;
import io.grpc.stub.StreamObserver;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import static com.comp6000.bukkit.util.ChatUtil.colorize;

public class BuildManager {

  private final COMP6000Plugin plugin;
  private Build lastBuild;

  public BuildManager(COMP6000Plugin plugin) {
    this.plugin = plugin;
  }

  public void subscribeToBuilds() {
    plugin.getBackendServiceStub().getBuilds(Empty.newBuilder().build(), new StreamObserver<>() {
      @Override
      public void onNext(GenerationDetails generationDetails) {
        plugin.getLogger().info(String.format("Received build id: %s, schematic url: %s", generationDetails.getBuildId(),
            generationDetails.getSchematicUrl()));
        try {
          var url = new URL("http://backend:8080" + generationDetails.getSchematicUrl());
          plugin.getServer().getScheduler().runTask(plugin,
              () -> pasteBuild(UUID.fromString(generationDetails.getBuildId()), url));
        } catch (MalformedURLException exception) {
          exception.printStackTrace();
        }
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

  private void pasteBuild(UUID buildId, URL schematicUrl) {
    try {
      plugin.getLogger().info("Loading schematic " + schematicUrl);
      var schematic = BuiltInClipboardFormat.MCEDIT_SCHEMATIC;
      Clipboard clipboard;
      try (var inputStream = schematicUrl.openStream()) {
        clipboard = schematic.load(inputStream);
      }
      plugin.getLogger().info("Schematic " + schematicUrl + " loaded");
      var world = createWorld(buildId);
      plugin.getLogger().info("Pasting schematic " + schematicUrl);
      var vector = BlockVector3.at(0, 0, 0);
      clipboard.paste(new BukkitWorld(world), vector, true, true, null);
      plugin.getLogger().info("Schematic " + schematicUrl + " pasted");
      var spectateLocation = new Location(world, 0, clipboard.getMaxY() + 10, 0);
      plugin.getServer().getOnlinePlayers().forEach(player -> {
        player.teleport(spectateLocation);
        player.sendMessage(colorize("&7Teleported to build " + buildId));
      });
      lastBuild = new Build(buildId, spectateLocation);
    } catch (IOException exception) {
      throw new RuntimeException(exception);
    }
  }

  private World createWorld(UUID buildId) {
    plugin.getLogger().info("Creating world for build " + buildId);
    var worldCreator = new WorldCreator("build-" + buildId.toString());
    worldCreator.generator(new VoidGenerator());
    var world = plugin.getServer().createWorld(worldCreator);
    plugin.getLogger().info("World " + world.getName() + " created");
    return world;
  }

  public Build getLastBuild() {
    return lastBuild;
  }
}
