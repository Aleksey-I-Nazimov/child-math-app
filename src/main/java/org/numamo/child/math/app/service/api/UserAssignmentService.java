package org.numamo.child.math.app.service.api;

public interface UserAssignmentService {
    UserAssignmentDmo getCurrentAssignment();

    boolean tryToGoThrough();

    void saveResults ();
}
