package org.numamo.child.math.app.model.math.api;

import java.math.BigDecimal;


/**
 * The main interface for checking user tasks TARGET value
 *
 * @author Nazimov Aleksey I.
 */
public interface MathComparator {


    String getDescription();

    boolean allowBooleanAnswer();

    boolean compare(Integer value1, Operation operation, Integer value2, BigDecimal targetValue);


    interface ForHigher extends MathComparator {

        @Override
        default boolean allowBooleanAnswer() {
            return true;
        }

        @Override
        default String getDescription() {
            return ">";
        }

    }

    interface ForLower extends MathComparator {

        @Override
        default boolean allowBooleanAnswer() {
            return true;
        }

        @Override
        default String getDescription() {
            return "<";
        }

    }

    interface ForEqual extends MathComparator {

        @Override
        default boolean allowBooleanAnswer() {
            return false;
        }

        @Override
        default String getDescription() {
            return "=";
        }

    }

}
