package com.comp6000.bukkit.build;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static com.comp6000.bukkit.util.ChatUtil.colorize;

public class BuildListener implements Listener {

  private final BuildManager buildManager;

  public BuildListener(BuildManager buildManager) {
    this.buildManager = buildManager;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    var lastBuild = buildManager.getLastBuild();
    if (lastBuild != null) {
      var player = event.getPlayer();
      player.teleport(lastBuild.getSpectateLocation());
      player.sendMessage(colorize("&7Teleported to build " + lastBuild.getId()));
    }
  }
}
