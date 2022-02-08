package com.comp6000.backend.genetic.feature.roof;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class RoofService implements FeatureService<Roof> {

    private List<Roof> roofs;

    public void setFloors(List<Roof> roofs) {
        this.roofs = roofs;
    }

    @Override
    public List<Roof> getFeatures() {
        return roofs;
    }
}
