package com.comp6000.backend.genetic.feedback;

import java.util.List;

record FeedbackRequest(
    String populationId, // The population which this feedback is for
    List<String> selected // The buildings that the user has selected as their favourites
) {
}
