package org.numamo.child.math.app.service;

import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.service.api.ScoreCalculator;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public final class ScoreCalculatorImpl implements ScoreCalculator {


    private static final Logger LOGGER = getLogger(ScoreCalculatorImpl.class);


    @Override
    public int calculateScore(UserLessonDmo lesson) {
        final double all = calculateAllTasks(lesson);
        final double score = calculateSuitable(lesson);
        final double result = score / all;
        final int assessment = BigDecimal.valueOf(5 * result).setScale(0, RoundingMode.HALF_DOWN).intValue();
        LOGGER.info("RESULT = {} -> {} assessment = {} {}", result, 5 * result, assessment);
        return assessment;
    }

    private long calculateAllTasks(UserLessonDmo lesson) {
        return lesson.getAssignments()
                .stream()
                .flatMap(a -> a.getUserTaskResults().stream())
                .count();
    }

    private float calculateSuitable(UserLessonDmo lesson) {
        final float[] xtr = {0.0f};
        lesson.getAssignments()
                .stream()
                .filter(a -> a.getAutoCheck().getCheckAttempts() < 3)
                .filter(this::testAssignments)
                .flatMap(a -> a.getUserTaskResults().stream())
                .map(t -> {
                    if (t.getUserSolution().getAllUserAnswers().size() > 1) {
                        xtr[0] += 0.25;
                    } else {
                        xtr[0] += 1.0;
                    }
                    return t;
                })
                .count();

        return xtr[0];
    }

    private boolean testAssignments(UserAssignmentDmo assignment) {
        return assignment.getUserTaskResults().stream().filter(t -> t.getUserSolution().getAllUserAnswers().size() > 3).count() == 0;
    }

}
