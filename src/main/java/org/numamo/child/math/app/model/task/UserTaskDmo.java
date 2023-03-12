package org.numamo.child.math.app.model.task;


import org.numamo.child.math.app.model.math.api.MathComparator;
import org.numamo.child.math.app.model.math.api.Operation;

import java.math.BigDecimal;
import java.util.Objects;

import static java.util.Objects.requireNonNull;


public final class UserTaskDmo implements Comparable<UserTaskDmo> {

    private final int rowNumber;
    private final Integer value1;
    private final Integer value2;
    private final Operation operation;
    private final BigDecimal targetValue;

    private final MathComparator mathComparator;

    public UserTaskDmo(
            int rowNumber,
            Integer value1,
            Operation operation,
            Integer value2,
            MathComparator mathComparator
    ) {
        this(rowNumber, value1, operation, value2,
                BigDecimal.valueOf(operation.calculate(value1, value2)), mathComparator);
    }

    public UserTaskDmo(
            int rowNumber,
            Integer value1,
            Operation operation,
            Integer value2,
            BigDecimal targetValue,
            MathComparator mathComparator
    ) {
        this.rowNumber = rowNumber;
        this.value1 = requireNonNull(value1);
        this.operation = requireNonNull(operation);
        this.value2 = requireNonNull(value2);
        this.targetValue = requireNonNull(targetValue);
        this.mathComparator = requireNonNull(mathComparator);
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public Integer getValue1() {
        return value1;
    }

    public Integer getValue2() {
        return value2;
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getTargetValue() {
        return targetValue;
    }

    public MathComparator getMathComparator() {
        return mathComparator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTaskDmo)) return false;
        UserTaskDmo that = (UserTaskDmo) o;
        return getRowNumber() == that.getRowNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNumber());
    }

    @Override
    public String toString() {
        return "UserTaskDmo{" +
                "rowNumber=" + rowNumber +
                ", value1=" + value1 +
                ", value2=" + value2 +
                ", operation=" + operation +
                ", targetValue=" + targetValue +
                ", mathComparator=" + mathComparator +
                '}';
    }

    @Override
    public int compareTo(UserTaskDmo o) {
        return Long.compare(this.rowNumber, o.rowNumber);
    }
}
