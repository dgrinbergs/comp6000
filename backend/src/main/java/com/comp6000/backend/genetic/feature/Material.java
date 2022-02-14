package com.comp6000.backend.genetic.feature;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Material {

  // Regular Blocks
  COBBLESTONE("", "Cobblestone", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/67/Cobblestone.png"),
  STONE("", "Stone", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d4/Stone.png"),
  BRICKS("", "Bricks", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/62/Bricks_JE5_BE3.png"),
  QUARTZ("", "Quartz", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/1/1f/Block_of_Quartz_JE4_BE2.png"),
  OAK_PLANKS("", "Oak Planks", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/c1/Oak_Planks.png"),
  OAK_LOG("", "Oak Log", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/e/e9/Oak_Log_%28UD%29_JE5_BE3.png"),
  CONCRETE_WHITE("", "White Concrete", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d3/White_Concrete.png"),
  CONCRETE_BLACK("", "Black Concrete", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d8/Black_Concrete.png"),
  CONCRETE_LIGHT_BLUE("", "Light-blue Concrete", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/60/Light_Blue_Concrete.png"),

  // Glass
  GLASS("", "Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/3/3e/Glass_JE4_BE2.png"),
  GLASS_LIGHT_BLUE("", "Light-blue Stained Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/2/26/Light_Blue_Stained_Glass_JE3_BE3.png"),
  GLASS_GRAY("", "Gray Stained Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/cd/Gray_Stained_Glass_JE3_BE3.png"),

  // Doors
  DOOR_OAK("", "Oak Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/0e/Oak_Door_JE8.png"),
  DOOR_BIRCH("", "Birch Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/4/49/Birch_Door_JE4.png"),
  DOOR_SPRUCE("", "Spruce Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/2/2a/Spruce_Door_JE4.png"),

  // Bed
  BED_WHITE("", "White Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/09/White_Bed_%28N%29.png"),
  BED_LIGHT_BLUE("", "Light-blue Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/e/e0/Light_Blue_Bed.png"),
  BED_LIME("", "Lime bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/f/f7/Lime_Bed.png"),
  ;

  private final String minecraftId;
  private final String name;
  private final String icon;

  Material(String minecraftId, String name, String icon) {
    this.minecraftId = minecraftId;
    this.name = name;
    this.icon = icon;
  }

  public String getMinecraftId() {
    return minecraftId;
  }

  public String getName() {
    return name;
  }

  public String getIcon() {
    return icon;
  }

  public static List<Material> regularBlocks() {
    return List.of(
        COBBLESTONE, STONE, BRICKS, QUARTZ, OAK_PLANKS, OAK_LOG, CONCRETE_WHITE, CONCRETE_BLACK, CONCRETE_LIGHT_BLUE
    );
  }

  public static List<Material> glassBlocks() {
    return List.of(
        GLASS, GLASS_LIGHT_BLUE, GLASS_GRAY
    );
  }

  public static List<Material> doorBlocks() {
    return List.of(
        DOOR_OAK, DOOR_SPRUCE, DOOR_BIRCH
    );
  }

  public static List<Material> bedBlocks() {
    return List.of(
        BED_WHITE, BED_LIGHT_BLUE, BED_LIME
    );
  }

}
