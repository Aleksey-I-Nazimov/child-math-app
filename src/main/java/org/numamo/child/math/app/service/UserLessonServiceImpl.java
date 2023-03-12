package org.numamo.child.math.app.service;

import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.service.api.MathAssignmentConfig;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.service.api.UserLessonService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Service
public final class UserLessonServiceImpl implements UserLessonService {

    private static final Logger LOGGER = getLogger(UserLessonServiceImpl.class);

    private final UserAssignmentService userAssignmentService;
    private final MathAssignmentConfig mathAssignmentConfig;

    @Autowired
    public UserLessonServiceImpl(
            UserAssignmentService userAssignmentService,
            MathAssignmentConfig mathAssignmentConfig
    ) {
        this.userAssignmentService = userAssignmentService;
        this.mathAssignmentConfig = mathAssignmentConfig;
    }

    @Override
    public UserLessonDmo makeLesson() {
        final UserLessonDmo lessonDmo = new UserLessonDmo(makeUserAssignments());
        lessonDmo.setComplete(false);

        LOGGER.debug("Made new lesson: {}", lessonDmo);
        return lessonDmo;
    }

    private List<UserAssignmentDmo> makeUserAssignments() {
        final List<UserAssignmentDmo> assignments = new ArrayList<>();
        for (int i = 0; i < mathAssignmentConfig.getMaxNumberOfAssignments(); ++i) {
            assignments.add(userAssignmentService.makeAssignment());
        }
        return assignments;
    }

}
