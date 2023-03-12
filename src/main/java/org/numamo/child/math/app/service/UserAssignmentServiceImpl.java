package org.numamo.child.math.app.service;

import org.numamo.child.math.app.model.math.MathComparatorForEqual;
import org.numamo.child.math.app.model.math.OperationForSumImpl;
import org.numamo.child.math.app.model.task.*;
import org.numamo.child.math.app.service.api.MathAssignmentConfig;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static java.math.BigDecimal.valueOf;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public final class UserAssignmentServiceImpl implements UserAssignmentService {

    private static final Logger LOGGER = getLogger(UserAssignmentServiceImpl.class);

    private final MathAssignmentConfig mathAssignmentConfig;

    @Autowired
    public UserAssignmentServiceImpl(MathAssignmentConfig mathAssignmentConfig) {
        this.mathAssignmentConfig = mathAssignmentConfig;
    }

    @Override
    public UserAssignmentDmo makeAssignment() {
        final UserAssignmentDmo assignment = new UserAssignmentDmo(makeTaskSet());
        LOGGER.trace("Made assignment: {}", assignment);
        return assignment;
    }

    @Override
    public boolean checkAssignment(UserAssignmentDmo assignmentDmo) {

        final AutoCheckDmo autoCheck = assignmentDmo.getAutoCheck();

        autoCheck.setShowCheck(true);
        autoCheck.incrementAttempt();

        final boolean complete = checkTasks(assignmentDmo);
        autoCheck.setComplete(complete);

        if (!complete && autoCheck.getCheckAttempts() == mathAssignmentConfig.getCheckLimit()) {
            autoCheck.setFailedByChecks(true);
            autoCheck.setComplete(false);
        } else if (!complete && autoCheck.getCheckAttempts() > mathAssignmentConfig.getCheckLimit()) {
            autoCheck.setFailedByChecks(true);
            autoCheck.setComplete(true);
        }

        LOGGER.debug("Checked assignment: {}", assignmentDmo);
        return autoCheck.isComplete();
    }

    @Override
    public boolean checkTask(UserTaskResultDmo taskResult) {
        boolean result = check(taskResult);
        LOGGER.debug("The check result = {} of {}", result, taskResult);
        return result;
    }

    private Set<UserTaskDmo> makeTaskSet() {
        final Set<UserTaskDmo> set = new TreeSet<>();
        for (int i = 0; i < mathAssignmentConfig.getMaxNumberOfMathTasks(); ++i) {
            set.add(nextTask(i));
        }
        return set;
    }

    private UserTaskDmo nextTask(int number) {
        final Random random = new Random();
        // Default task for SUM and == operations;
        final UserTaskDmo userTask = new UserTaskDmo(number,
                random.nextInt(mathAssignmentConfig.getNumericalMathLimit()),
                new OperationForSumImpl(),
                random.nextInt(mathAssignmentConfig.getNumericalMathLimit()),
                new MathComparatorForEqual()
        );
        LOGGER.trace("Made task: {}", userTask);
        return userTask;
    }

    private boolean checkTasks(UserAssignmentDmo userAssignment) {

        if (userAssignment.isFullySet()) {
            for (final UserTaskResultDmo taskResultDmo : userAssignment.getUserTaskResults()) {
                if (!check(taskResultDmo)) {
                    LOGGER.trace("The following task was failed {}", taskResultDmo);
                    return false;
                }
            }
            LOGGER.debug("All tasks completed: {}", userAssignment);
            return true;
        } else {
            LOGGER.debug("Some tasks was skipped: {}", userAssignment);
            return false;
        }
    }

    private boolean check(UserTaskResultDmo taskResult) {

        final UserTaskDmo userTask = taskResult.getUserTask();
        final UserSolutionDmo userSolution = taskResult.getUserSolution();
        final UserAttemptDmo userAttempt = userSolution.getUserAnswer();

        boolean result = false;
        if (nonNull(userAttempt)) {
            result = userTask.getMathComparator()
                    .compare(
                            userTask.getValue1(),
                            userTask.getOperation(),
                            userTask.getValue2(),
                            valueOf(userAttempt.getAttemptResult())
                    );
        } else {
            LOGGER.debug("No user attempts!");
        }

        LOGGER.debug("The check result = {} of {}", result, taskResult);
        return result;
    }

}
