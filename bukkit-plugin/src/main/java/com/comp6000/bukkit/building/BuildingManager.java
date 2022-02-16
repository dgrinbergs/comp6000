package com.comp6000.bukkit.building;

import com.comp6000.bukkit.COMP6000Plugin;
import com.comp6000.bukkit.util.VoidGenerator;
import com.comp6000.grpc.BuildingDetails;
import com.google.protobuf.Empty;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.BuiltInClipboardFormat;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.function.visitor.RegionVisitor;
import com.sk89q.worldedit.math.BlockVector3;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.IOException;
import java.util.UUID;

import static com.comp6000.bukkit.util.ChatUtil.colorize;

public class BuildingManager {

  private final COMP6000Plugin plugin;
  private Building lastBuilding;

  public BuildingManager(COMP6000Plugin plugin) {
    this.plugin = plugin;
  }

  public void init() {
    plugin.getServer().getPluginManager().registerEvents(new BuildingListener(this), plugin);

    plugin.getBackendServiceStub().streamCreateBuildingRequests(Empty.newBuilder().build(),
        new CreateBuildingRequestStreamObserver(this));
  }

  public void createBuilding(UUID id, BuildingDetails details) {
    plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
      plugin.getLogger().info("Creating building " + id);

      plugin.getLogger().info("Loading house schematic");
      Clipboard clipboard;
      try (var inputStream = getClass().getResourceAsStream("/house.schem")) {
        clipboard = BuiltInClipboardFormat.FAST.load(inputStream);
      } catch (IOException exception) {
        throw new RuntimeException(exception);
      }

      plugin.getLogger().info("Replacing blocks");
      var visitor = new RegionVisitor(clipboard.getRegion(), new FeatureReplaceRegionFunction(clipboard, details),
          clipboard);
      Operations.completeLegacy(visitor);

      plugin.getServer().getScheduler().runTask(plugin, () -> {
        var world = createWorld(id);

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
          plugin.getLogger().info("Pasting house schematic");
          var vector = BlockVector3.at(0, 64, 0);
          clipboard.paste(new BukkitWorld(world), vector, false, false, null);

          var spectateLocation = new Location(world, 0, 64, 0, -180f, 0f);
          lastBuilding = new Building(id, spectateLocation);

          plugin.getServer().getScheduler().runTask(plugin, () -> {
            plugin.getServer().getOnlinePlayers().forEach(player -> {
              player.teleport(spectateLocation);
              player.sendMessage(colorize("&7Teleported to building " + id));
            });
          });
        });
      });
    });
  }

  private World createWorld(UUID buildingId) {
    plugin.getLogger().info("Creating world for building " + buildingId);
    var worldCreator = new WorldCreator("building-" + buildingId.toString());
    worldCreator.generator(new VoidGenerator());
    return plugin.getServer().createWorld(worldCreator);
  }

  public Building getLastBuilding() {
    return lastBuilding;
  }
}
