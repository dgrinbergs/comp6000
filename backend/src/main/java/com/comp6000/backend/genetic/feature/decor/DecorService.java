package com.comp6000.backend.genetic.feature.decors;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class DecorService implements FeatureService<Decor> {

    private List<Decor> decors;

    public void setFloors(List<Decor> decors) {
        this.decors = decors;
    }

    @Override
    public List<Decor> getFeatures() {
        return decors;
    }
}
