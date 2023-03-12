package org.numamo.child.math.app.model.task;


import java.util.Objects;

import static java.lang.System.currentTimeMillis;
import static java.util.Objects.requireNonNull;
import static java.util.UUID.randomUUID;


/**
 * The user attempt data model object (DMO)
 *
 * @author Nazimov Aleksey I.
 */
public final class UserAttemptDmo implements Comparable<UserAttemptDmo> {

    private final String id;
    private final long timestamp;
    private final Integer attemptResult;

    public UserAttemptDmo(
            Integer attemptResult
    ) {
        this.id = randomUUID().toString();
        this.timestamp = currentTimeMillis();
        this.attemptResult = requireNonNull(attemptResult);
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Integer getAttemptResult() {
        return attemptResult;
    }

    @Override
    public String toString() {
        return "UserAttemptDmo{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", attemptResult=" + attemptResult +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAttemptDmo)) return false;
        UserAttemptDmo that = (UserAttemptDmo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(UserAttemptDmo o) {
        return Long.compare(this.timestamp, o.timestamp);
    }

}
