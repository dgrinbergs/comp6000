package com.comp6000.backend.genetic.feature;

import java.util.List;

public interface FeatureService<T extends Feature> {

  List<T> getFeatures();

}
