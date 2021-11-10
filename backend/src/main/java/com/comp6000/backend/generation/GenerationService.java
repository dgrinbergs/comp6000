package com.comp6000.backend.generation;

import com.comp6000.backend.builds.events.BuildEvent;
import org.springframework.context.event.EventListener;

public interface GenerationService {

  @EventListener
  void handleBuildEvent(BuildEvent buildEvent);

  String getSchematicForBuild();

}
