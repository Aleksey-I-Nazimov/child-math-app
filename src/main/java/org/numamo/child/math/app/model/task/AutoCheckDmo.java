package org.numamo.child.math.app.model.task;


public final class AutoCheckDmo {

    private boolean showCheck = false;
    private boolean failedByChecks = false;
    private int checkAttempts = 0;
    private boolean complete;

    public boolean isShowCheck() {
        return showCheck;
    }

    public void setShowCheck(boolean showCheck) {
        this.showCheck = showCheck;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public int getCheckAttempts() {
        return checkAttempts;
    }

    public void incrementAttempt() {
        this.checkAttempts++;
    }

    public boolean isFailedByChecks() {
        return failedByChecks;
    }

    public void setFailedByChecks(boolean failedByChecks) {
        this.failedByChecks = failedByChecks;
    }

    @Override
    public String toString() {
        return "AutoCheckDmo{" +
                "showCheck=" + showCheck +
                ", complete=" + complete +
                ", failedByChecks=" + failedByChecks +
                ", checkAttempts=" + checkAttempts +
                '}';
    }
}
