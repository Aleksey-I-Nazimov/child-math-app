package org.numamo.child.math.app.model.sum.api;

import java.util.Objects;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;


/**
 * The main DTO model object for checking user answers
 *
 * @author Nazimov Aleksey I.
 */
public final class ArithmeticResult {

    private final int rowNumber;
    private final Integer userValue;
    private final Integer correctValue;

    public ArithmeticResult(
            int rowNumber,
            Integer userValue,
            Integer correctValue
    ) {
        this.rowNumber = rowNumber;
        this.userValue = userValue;
        this.correctValue = requireNonNull(correctValue,"Correct value cannot be set as NULL");
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public Integer getUserValue() {
        return userValue;
    }

    public Integer getCorrectValue() {
        return correctValue;
    }

    public boolean wasSet () {
        return nonNull(userValue);
    }

    public boolean wasCorrect () {
        return wasSet() && userValue.compareTo(correctValue)==0;
    }

    @Override
    public String toString() {
        return "ArithmeticResult{" +
                "rowNumber=" + rowNumber +
                ", userValue=" + userValue +
                ", correctValue=" + correctValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArithmeticResult)) return false;
        ArithmeticResult that = (ArithmeticResult) o;
        return getRowNumber() == that.getRowNumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRowNumber());
    }
}
