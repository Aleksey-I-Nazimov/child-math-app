package org.numamo.child.math.app.model.table;

import org.numamo.child.math.app.model.math.api.MathComparator;
import org.numamo.child.math.app.model.math.api.Operation;
import org.numamo.child.math.app.model.table.api.TableTaskAnswerModel;
import org.numamo.child.math.app.model.task.UserAttemptDmo;
import org.numamo.child.math.app.model.task.UserSolutionDmo;
import org.numamo.child.math.app.model.task.UserTaskDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import static java.util.Objects.requireNonNull;
import static org.slf4j.LoggerFactory.getLogger;


/**
 * The main table model with user tasks and answers
 *
 * @author Nazimov Aleksey I.
 */
@Component
public final class TableTaskAnswerModelImpl implements TableTaskAnswerModel {

    private static final Logger LOGGER = getLogger(TableTaskAnswerModelImpl.class);

    private final Map<Integer, UserTaskDmo> taskMap = new HashMap<>();
    private final Map<Integer, List<UserAttemptDmo>> answerMap = new HashMap<>();

    @Override
    public Integer getValue1(int sequentialNumber) {
        final UserTaskDmo userTask = requireNonNull(taskMap.get(sequentialNumber));
        final Integer value1 = requireNonNull(userTask.getValue1());
        LOGGER.trace("Requesting value1: {} for {}", value1, sequentialNumber);
        return value1;
    }

    @Override
    public Integer getValue2(int sequentialNumber) {
        final UserTaskDmo userTask = requireNonNull(taskMap.get(sequentialNumber));
        final Integer value2 = requireNonNull(userTask.getValue2());
        LOGGER.trace("Requesting value2: {} for {}", value2, sequentialNumber);
        return value2;
    }

    @Override
    public Operation getOperation(
            final int sequentialNumber
    ) {
        final UserTaskDmo userTask = requireNonNull(taskMap.get(sequentialNumber));
        final Operation operation = requireNonNull(userTask.getOperation());
        LOGGER.trace("Requesting math operation: {} for {}", operation, sequentialNumber);
        return operation;
    }

    @Override
    public MathComparator getComparator(
            final int sequentialNumber
    ) {
        final UserTaskDmo userTask = requireNonNull(taskMap.get(sequentialNumber));
        final MathComparator comparator = requireNonNull(userTask.getMathComparator());
        LOGGER.trace("Requesting math comparator: {} for {}", comparator, sequentialNumber);
        return comparator;
    }

    @Override
    public void appendUserAnswer(
            final BigDecimal userAnswer,
            final int rowNumber
    ) {
        LOGGER.debug("Appending user answer: {} to row {}", userAnswer, rowNumber);
        getAttempts(rowNumber)
                .add(new UserAttemptDmo(userAnswer.intValue()));
    }

    @Override
    public Integer getLastUserAnswer(final int sequentialNumber) {
        return getLastAttempt(sequentialNumber)
                .map(UserAttemptDmo::getAttemptResult)
                .orElse(null);
    }

    @Override
    public Integer getCorrectAnswer(final int sequentialNumber) {
        return taskMap.get(sequentialNumber).getTargetValue().intValue();
    }

    @Override
    public void update(Set<UserTaskDmo> newUserTasks) {
        taskMap.clear();
        answerMap.clear();
        newUserTasks.forEach(t -> taskMap.put(t.getRowNumber(), t));
    }

    @Override
    public UserTaskResultDmo checkUserAnswerAt(
            final int rowNumber
    ) {
        return extract(rowNumber);
    }

    @Override
    public Set<UserTaskResultDmo> getAllAnswers() {
        final Set<UserTaskResultDmo> results = new TreeSet<>();
        taskMap.forEach((r, t) -> results.add(extract(r)));
        LOGGER.debug("Collected results: {}", results);
        return results;
    }


    private Optional<UserAttemptDmo> getLastAttempt(int rowNumber) {
        final List<UserAttemptDmo> list = getAttempts(rowNumber);
        if (list.isEmpty()) {
            LOGGER.trace("No attempts");
            return Optional.empty();
        } else {
            final UserAttemptDmo attempt = list.get(list.size() - 1);
            LOGGER.trace("Requested last attempt {}", attempt);
            return Optional.of(attempt);
        }
    }

    private List<UserAttemptDmo> getAttempts(int rowNumber) {
        final List<UserAttemptDmo> list = answerMap
                .computeIfAbsent(rowNumber, i -> new ArrayList<>());
        LOGGER.trace("Requesting all attempts {} for {}", list, rowNumber);
        return list;
    }

    private UserTaskResultDmo extract(
            final int rowNumber
    ) {
        final UserSolutionDmo solution = new UserSolutionDmo(rowNumber,
                getLastAttempt(rowNumber).orElse(null), getAttempts(rowNumber));

        final UserTaskDmo userTask = taskMap.get(rowNumber);

        final UserTaskResultDmo taskResult = new UserTaskResultDmo(userTask, solution);

        LOGGER.debug("Requested task result: {}", taskResult);
        return taskResult;
    }


}
