package org.numamo.child.math.app.service.api;

import static java.util.UUID.randomUUID;

public final class UserAssignmentDmo {

    public static final int MAX_ATTEMPTS = 3;
    public static final int ASSIGNMENT_SIZE = 10;

    private final String id = randomUUID().toString();
    private boolean showCheck = false;
    private boolean complete = false;
    private int attempts=0;

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isShowCheck() {
        return showCheck;
    }

    public void incrementAttempt () {
        this.attempts++;
    }

    public void setShowCheck (boolean showCheck) {
        this.showCheck = showCheck;
    }

    public boolean getShowCheck () {
        return showCheck;
    }

    public boolean wasNotFailed() {
        return attempts<=MAX_ATTEMPTS;
    }

    @Override
    public String toString() {
        return "UserAssignmentDmo{" +
                "id='" + id + '\'' +
                ", showCheck=" + showCheck +
                ", complete=" + complete +
                ", attempts=" + attempts +
                '}';
    }
}
