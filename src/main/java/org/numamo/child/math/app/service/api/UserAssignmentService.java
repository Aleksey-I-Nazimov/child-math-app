package org.numamo.child.math.app.service.api;

import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;

public interface UserAssignmentService {

    UserAssignmentDmo makeAssignment();

    boolean checkAssignment(UserAssignmentDmo assignment);

    boolean checkTask(UserTaskResultDmo taskResult);

}
