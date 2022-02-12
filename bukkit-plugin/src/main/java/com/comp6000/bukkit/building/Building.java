package com.comp6000.bukkit.building;

import org.bukkit.Location;

import java.util.UUID;

public class Building {

  private final UUID id;
  private final Location spectateLocation;

  public Building(UUID id, Location spectateLocation) {
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
