package com.comp6000.backend.genetic.feature.corner;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class CornerService implements FeatureService<Corner> {

    private List<Corner> corners;

    public void setFloors(List<Corner> corners) {
        this.corners = corners;
    }

    @Override
    public List<Corner> getFeatures() {
        return corners;
    }
}
