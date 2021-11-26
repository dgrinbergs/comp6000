package com.comp6000.bukkit.build;

import org.bukkit.Location;

import java.util.UUID;

public class Build {

  private final UUID id;
  private final Location spectateLocation;

  public Build(UUID id, Location spectateLocation) {
    this.id = id;
    this.spectateLocation = spectateLocation;
  }

  public UUID getId() {
    return id;
  }

  public Location getSpectateLocation() {
    return spectateLocation;
  }
}
