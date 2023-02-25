package org.numamo.child.math.app.service;

import org.numamo.child.math.app.service.api.UserAssignmentDmo;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public final class UserAssignmentServiceImpl implements UserAssignmentService {

    private static final Logger LOGGER = getLogger(UserAssignmentServiceImpl.class);

    private static final int MAX_ASSIGNMENT_NUMBER = 5;

    private final List<UserAssignmentDmo> userAssignments = new ArrayList<>();
    private UserAssignmentDmo currentAssignment;

    @PostConstruct
    public void init () {
        currentAssignment = new UserAssignmentDmo();
    }

    @Override
    public UserAssignmentDmo getCurrentAssignment() {
        return this.currentAssignment;
    }

    @Override
    public boolean tryToGoThrough() {
        userAssignments.add(currentAssignment);
        currentAssignment = new UserAssignmentDmo();
        return userAssignments.stream().filter(a->a.wasNotFailed() && a.isComplete()).count()
                ==
                MAX_ASSIGNMENT_NUMBER;
    }

    @Override
    public void saveResults() {
        LOGGER.info("Saving results: {}",userAssignments);
        userAssignments.clear();

    }

}
