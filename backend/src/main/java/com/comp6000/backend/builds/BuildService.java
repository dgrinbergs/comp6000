package com.comp6000.backend.builds;

import com.comp6000.backend.builds.exceptions.BuildNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class BuildService {

  private final Map<UUID, BuildRequest> buildRequests;

  public BuildService() {
    this.buildRequests = new ConcurrentHashMap<>();
  }

  public UUID saveBuild(BuildRequest buildRequest) {
    var uuid = UUID.randomUUID();
    buildRequests.put(uuid, buildRequest);
    return uuid;
  }

  public BuildRequest getBuild(UUID uuid) {
    return Optional.ofNullable(buildRequests.get(uuid)).orElseThrow(() ->
        new BuildNotFoundException("Could not find build with uuid: " + uuid)
    );
  }

  public Map<UUID, BuildRequest> getBuilds() {
    return buildRequests;
  }
}
