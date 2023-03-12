package org.numamo.child.math.app.model.task;


import java.util.Objects;

import static java.util.Objects.requireNonNull;


/**
 * The main user task result with original task and proposed user solution
 *
 * @author Nazimov Aleksey I.
 */
public final class UserTaskResultDmo implements Comparable<UserTaskResultDmo> {

    private final int rowNumber;
    private final UserTaskDmo userTask;
    private final UserSolutionDmo userSolution;

    public UserTaskResultDmo(
            UserTaskDmo userTask,
            UserSolutionDmo userSolution
    ) {
        this.userTask = requireNonNull(userTask);
        this.userSolution = requireNonNull(userSolution);
        this.rowNumber = userTask.getRowNumber();

        if (userTask.getRowNumber() != userSolution.getRowNumber()) {
            throw new IllegalArgumentException("The task:" + userTask + " doesn't correspond to solution: " + userSolution);
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public UserTaskDmo getUserTask() {
        return userTask;
    }

    public UserSolutionDmo getUserSolution() {
        return userSolution;
    }

    @Override
    public String toString() {
        return "UserTaskResultDmo{" +
                "userTask=" + userTask +
                ", userSolution=" + userSolution +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTaskResultDmo)) return false;
        UserTaskResultDmo that = (UserTaskResultDmo) o;
        return getRowNumber() == that.getRowNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNumber());
    }

    @Override
    public int compareTo(UserTaskResultDmo o) {
        return Integer.compare(this.rowNumber, o.rowNumber);
    }
}
