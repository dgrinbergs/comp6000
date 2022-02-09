package com.comp6000.backend.genetic.feature;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Describes a feature of a building. For example a wall or roof
 */
public enum Feature {

  // Regular blocks
  CORNER(Material.regularBlocks()),
  FLOOR(Material.regularBlocks()),
  ROOF(Material.regularBlocks()),
  WALL(Material.regularBlocks()),

  // Special blocks
  BED(Material.bedBlocks()),
  DOOR(Material.doorBlocks()),
  WINDOW(Material.glassBlocks()),
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
