package com.comp6000.backend.genetic.feature.doornum;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class DoorNumService implements FeatureService<DoorNum> {

    private List<DoorNum> doornums;

    public void setFloors(List<DoorNum> doornums) {
        this.doornums = doornums;
    }

    @Override
    public List<DoorNum> getFeatures() {
        return doornums;
    }
}
