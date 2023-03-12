package org.numamo.child.math.app.model.task;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;


public final class UserLessonDmo {


    // Variables and constructors:-------------------------------------------------------
    private final String id = randomUUID().toString();
    private final List<UserAssignmentDmo> assignments;
    private final AssignmentIteratorDmo iterator;
    private boolean complete = false;

    public UserLessonDmo(List<UserAssignmentDmo> assignments) {
        this.assignments = assignments;
        this.iterator = new AssignmentIteratorDmo(assignments.iterator());
    }


    // Public API:-----------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public AssignmentIteratorDmo getIterator() {
        return iterator;
    }

    public List<UserAssignmentDmo> getAssignments() {
        return assignments;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "UserLessonDmo{" +
                "id='" + id + '\'' +
                ", assignments=" + assignments +
                ", complete=" + complete +
                '}';
    }

    public static class AssignmentIteratorDmo implements Iterator<UserAssignmentDmo> {

        private final Iterator<UserAssignmentDmo> lessonIterator;
        private UserAssignmentDmo current;

        AssignmentIteratorDmo(Iterator<UserAssignmentDmo> lessonIterator) {
            this.lessonIterator = lessonIterator;
        }

        @Override
        public boolean hasNext() {
            return lessonIterator.hasNext();
        }

        @Override
        public UserAssignmentDmo next() {
            this.current = lessonIterator.next();
            return current;
        }

        public Optional<UserAssignmentDmo> getCurrent() {
            return Optional.ofNullable(current);
        }
    }

}
