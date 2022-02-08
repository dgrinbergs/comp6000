package com.comp6000.backend.genetic.feature.door;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class DoorService implements FeatureService<Door> {

    private List<Door> doors;

    public void setFloors(List<Door> doors) {
        this.doors = doors;
    }

    @Override
    public List<Door> getFeatures() {
        return doors;
    }
}
