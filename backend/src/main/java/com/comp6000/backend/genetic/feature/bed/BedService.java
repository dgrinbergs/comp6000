package com.comp6000.backend.genetic.feature.bed;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class BedService implements FeatureService<Bed> {

    private List<Bed> beds;

    public void setFloors(List<Bed> beds) {
        this.beds = beds;
    }

    @Override
    public List<Bed> getFeatures() {
        return beds;
    }
}
