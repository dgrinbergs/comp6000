package com.comp6000.backend.genetic.feature.roof;

import com.comp6000.backend.genetic.feature.Feature;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
public class Roof implements Feature {

    private final String name;
    private final String path;

    public Roof(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
