package com.comp6000.backend.generation;

import com.comp6000.backend.builds.BuildDetails;

/**
 * This interface handles the generation of schematic files which are used by the Minecraft plugin.
 */
public interface GenerationService {

  String generateSchematicForBuild(BuildDetails buildDetails);

}
