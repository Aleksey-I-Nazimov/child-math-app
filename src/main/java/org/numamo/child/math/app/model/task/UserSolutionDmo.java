package org.numamo.child.math.app.model.task;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;


/**
 * The main DMO model object for checking user answers
 *
 * @author Nazimov Aleksey I.
 */
public final class UserSolutionDmo {

    private final int rowNumber;
    private final UserAttemptDmo userAnswer;
    private final List<UserAttemptDmo> allUserAnswers;

    public UserSolutionDmo(
            int rowNumber,
            UserAttemptDmo userAnswer,
            List<UserAttemptDmo> allUserAnswers
    ) {
        this.rowNumber = rowNumber;
        this.userAnswer = userAnswer;
        this.allUserAnswers = requireNonNull(allUserAnswers);
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public UserAttemptDmo getUserAnswer() {
        return userAnswer;
    }

    public List<UserAttemptDmo> getAllUserAnswers() {
        return allUserAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSolutionDmo)) return false;
        UserSolutionDmo that = (UserSolutionDmo) o;
        return getRowNumber() == that.getRowNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNumber());
    }

    @Override
    public String toString() {
        return "UserSolutionDmo{" +
                "rowNumber=" + rowNumber +
                ", userAnswer=" + userAnswer +
                ", allUserAnswers=" + allUserAnswers +
                '}';
    }
}
