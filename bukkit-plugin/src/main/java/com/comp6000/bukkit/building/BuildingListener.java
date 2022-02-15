package com.comp6000.bukkit.building;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.comp6000.bukkit.util.ChatUtil.colorize;

public class BuildingListener implements Listener {

  private final BuildingManager buildingManager;

  public BuildingListener(BuildingManager buildingManager) {
    this.buildingManager = buildingManager;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    var lastBuilding = buildingManager.getLastBuilding();
    if (lastBuilding != null) {
      var player = event.getPlayer();
      player.teleport(lastBuilding.getSpectateLocation());
      player.sendMessage(colorize("&7Teleported to building " + lastBuilding.getId()));
    }
  }
}
