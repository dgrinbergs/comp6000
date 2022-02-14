package com.comp6000.backend.genetic.feature;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Material {

  // Regular Blocks
  COBBLESTONE("minecraft:cobblestone", "Cobblestone", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/67/Cobblestone.png"),
  STONE("minecraft:stone", "Stone", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/d4/Stone.png"),
  BRICKS("minecraft:bricks", "Bricks", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/62/Bricks_JE5_BE3.png"),
  SMOOTH_QUARTZ("minecraft:smooth_quartz", "Smooth Quartz", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/d/de/Smooth_Quartz_Block_JE3_BE2.png"),
  OAK_PLANKS("minecraft:oak_planks", "Oak Planks", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/c1/Oak_Planks.png"),
  OAK_LOG("minecraft:oak_log", "Oak Log", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/e/e9/Oak_Log_%28UD%29_JE5_BE3.png"),
  STONE_BRICKS("minecraft:stone_bricks", "Stone Bricks", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/5/5a/Stone_Bricks_JE3_BE2.png"),
  POLISHED_DIORITE("minecraft:polished_diorite", "Polished Diorite", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/a/a8/Polished_Diorite_JE2_BE2.png"),
  CONCRETE_LIGHT_BLUE("minecraft:light_blue_concrete", "Light-blue Concrete", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/60/Light_Blue_Concrete.png"),

  // Glass
  GLASS_STANDARD("minecraft:glass", "Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/3/3e/Glass_JE4_BE2.png"),
  GLASS_LIGHT_BLUE("minecraft:light_blue_stained_glass", "Light-blue Stained Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/2/26/Light_Blue_Stained_Glass_JE3_BE3.png"),
  GLASS_GRAY("minecraft:gray_stained_glass", "Gray Stained Glass", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/cd/Gray_Stained_Glass_JE3_BE3.png"),

  // Doors
  DOOR_OAK("minecraft:oak_door", "Oak Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/0e/Oak_Door_JE8.png"),
  DOOR_BIRCH("minecraft:birch_door", "Birch Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/4/49/Birch_Door_JE4.png"),
  DOOR_SPRUCE("minecraft:spruce_door", "Spruce Door", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/2/2a/Spruce_Door_JE4.png"),

  // Bed
  BED_WHITE("minecraft:white_bed", "White Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/09/White_Bed_%28N%29.png"),
  BED_LIGHT_BLUE("minecraft:light_blue_bed", "Light-blue Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/e/e0/Light_Blue_Bed.png"),
  BED_LIME("minecraft:lime_bed", "Lime bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/f/f7/Lime_Bed.png"),
  BED_RED("minecraft:red_bed", "Red Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/6a/Red_Bed_%28N%29.png"),
  BED_MAGENTA("minecraft:magenta_bed", "Magenta Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/0/04/Magenta_Bed.png"),
  BED_BLUE("minecraft:blue_bed", "Blue Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/65/Blue_Bed.png"),
  BED_YELLOW("minecraft:yellow_bed" ,"Yellow Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/c/c4/Yellow_Bed.png"),
  BED_PINK("minecraft:pink_bed", "Pink Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/63/Pink_Bed.png"),
  BED_CYAN("minecraft:cyan_bed", "Cyan Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/6/62/Cyan_Bed.png"),
  BED_GRAY("minecraft:gray_bed", "Gray Bed", "https://static.wikia.nocookie.net/minecraft_gamepedia/images/4/4e/Gray_Bed.png"),
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
        COBBLESTONE, STONE, BRICKS, SMOOTH_QUARTZ, OAK_PLANKS, OAK_LOG, STONE_BRICKS, POLISHED_DIORITE, CONCRETE_LIGHT_BLUE
    );
  }

  public static List<Material> glassBlocks() {
    return List.of(
        GLASS_STANDARD, GLASS_LIGHT_BLUE, GLASS_GRAY
    );
  }

  public static List<Material> doorBlocks() {
    return List.of(
        DOOR_OAK, DOOR_SPRUCE, DOOR_BIRCH
    );
  }

  public static List<Material> bedBlocks() {
    return List.of(
        BED_WHITE, BED_LIGHT_BLUE, BED_LIME, BED_BLUE, BED_YELLOW, BED_PINK, BED_CYAN, BED_GRAY, BED_MAGENTA, BED_RED
    );
  }

}
