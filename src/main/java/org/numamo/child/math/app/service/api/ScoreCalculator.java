package org.numamo.child.math.app.service.api;

import org.numamo.child.math.app.model.task.UserLessonDmo;

public interface ScoreCalculator {

    int calculateScore(UserLessonDmo lesson);

}
