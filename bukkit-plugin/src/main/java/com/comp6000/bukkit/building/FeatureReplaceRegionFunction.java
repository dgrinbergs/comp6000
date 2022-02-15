package com.comp6000.bukkit.building;

import com.comp6000.grpc.BuildingDetails;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.function.RegionFunction;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.block.BlockTypes;

public class FeatureReplaceRegionFunction implements RegionFunction {

  private final Extent extent;
  private final BuildingDetails buildingDetails;

  public FeatureReplaceRegionFunction(Extent extent, BuildingDetails buildingDetails) {
    this.extent = extent;
    this.buildingDetails = buildingDetails;
  }

  @Override
  public boolean apply(BlockVector3 position) throws WorldEditException {
    var block = extent.getBlock(position);
    var blockType = block.getBlockType();
    if (blockType == BlockTypes.OAK_LOG) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getCornerBlockId()).getDefaultState());
      return true;
    } else if (blockType == BlockTypes.OAK_PLANKS) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getFloorBlockId()).getDefaultState());
      return true;
    } else if (blockType == BlockTypes.OAK_STAIRS) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getRoofBlockId()).getDefaultState().withProperties(block));
      return true;
    } else if (blockType == BlockTypes.COBBLESTONE) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getWallBlockId()).getDefaultState());
      return true;
    } else if (blockType == BlockTypes.YELLOW_BED) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getBedBlockId()).getDefaultState().withProperties(block));
      return true;
    } else if (blockType == BlockTypes.OAK_DOOR) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getDoorBlockId()).getDefaultState().withProperties(block));
      return true;
    } else if (blockType == BlockTypes.GLASS_PANE) {
      extent.setBlock(position.getX(), position.getY(), position.getZ(),
          BlockTypes.get(buildingDetails.getWindowBlockId()).getDefaultState().withProperties(block));
      return true;
    }
    return false;
  }
}
