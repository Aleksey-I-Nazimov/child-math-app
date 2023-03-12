package org.numamo.child.math.app.model.task;

import java.util.Objects;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableSet;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;


public final class UserAssignmentDmo {

    private final String id = randomUUID().toString();
    private final Set<UserTaskDmo> userTasks;
    private final AutoCheckDmo autoCheck = new AutoCheckDmo();
    private Set<UserTaskResultDmo> userTaskResults;

    public UserAssignmentDmo(Set<UserTaskDmo> userTasks) {
        this.userTasks = requireNonNull(userTasks);
    }


    public void setUserTaskResults(Set<UserTaskResultDmo> userTaskResults) {
        this.userTaskResults = userTaskResults;
    }

    public Set<UserTaskDmo> getUserTasks() {
        return unmodifiableSet(userTasks);
    }

    public AutoCheckDmo getAutoCheck() {
        return autoCheck;
    }

    public Set<UserTaskResultDmo> getUserTaskResults() {
        return isNull(userTaskResults) ? emptySet() : userTaskResults;
    }

    public boolean isFullySet() {
        return getUserTaskResults().size() == userTasks.size();
    }

    @Override
    public String toString() {
        return "UserAssignmentDmo{" +
                "id='" + id + '\'' +
                ", userTasks=" + userTasks +
                ", autoCheck=" + autoCheck +
                ", userTaskResults=" + userTaskResults +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAssignmentDmo)) return false;
        UserAssignmentDmo that = (UserAssignmentDmo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
