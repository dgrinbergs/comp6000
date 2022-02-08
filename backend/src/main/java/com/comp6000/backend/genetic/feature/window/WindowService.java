package com.comp6000.backend.genetic.feature.window;

import com.comp6000.backend.genetic.feature.FeatureService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ConfigurationProperties(prefix = "features")
public class WindowService implements FeatureService<Window> {

    private List<Window> windows;

    public void setFloors(List<Window> windows) {
        this.windows = windows;
    }

    @Override
    public List<Window> getFeatures() {
        return windows;
    }
}
