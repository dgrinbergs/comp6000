package com.comp6000.backend.genetic.feature;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Describes a feature of a building. For example a wall or roof
 */
public enum Feature {

  BED(Material.bedBlocks()),
  DOOR(Material.doorBlocks()),
  WINDOW(Material.glassBlocks()),
  CORNER(List.of(Material.values())),
  FLOOR(List.of(Material.values())),
  ROOF(List.of(Material.values())),
  WALL(List.of(Material.values())),
  ;

  private final List<Material> materials;

  Feature(List<Material> materials) {
    this.materials = materials;
  }

  public Material getRandomMaterial() {
    return materials.get(ThreadLocalRandom.current().nextInt(0, materials.size()));
  }

  public List<Material> getMaterials() {
    return materials;
  }
}
