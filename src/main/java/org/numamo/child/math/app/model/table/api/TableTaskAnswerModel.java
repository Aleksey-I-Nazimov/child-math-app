package org.numamo.child.math.app.model.table.api;

import org.numamo.child.math.app.model.math.api.MathComparator;
import org.numamo.child.math.app.model.math.api.Operation;
import org.numamo.child.math.app.model.task.UserTaskDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;

import java.math.BigDecimal;
import java.util.Set;


public interface TableTaskAnswerModel {

    Integer getValue1 (int rowNumber);

    Integer getValue2 (int rowNumber);

    Operation getOperation(int rowNumber);

    MathComparator getComparator(int rowNumber);

    void appendUserAnswer(BigDecimal userAnswer, int rowNumber);

    Integer getLastUserAnswer(int rowNumber);

    Integer getCorrectAnswer(int rowNumber);


    void update(Set<UserTaskDmo> newUserTasks);

    UserTaskResultDmo checkUserAnswerAt(int rowNumber);

    Set<UserTaskResultDmo> getAllAnswers();

}
