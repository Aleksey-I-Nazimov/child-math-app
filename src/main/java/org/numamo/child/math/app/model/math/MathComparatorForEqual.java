package org.numamo.child.math.app.model.math;

import org.numamo.child.math.app.model.math.api.MathComparator;
import org.numamo.child.math.app.model.math.api.Operation;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.slf4j.LoggerFactory.getLogger;


public final class MathComparatorForEqual implements MathComparator.ForEqual {

    private static final Logger LOGGER = getLogger(MathComparatorForEqual.class);

    @Override
    public boolean compare(
            final Integer value1,
            final Operation operation,
            final Integer value2,
            final BigDecimal targetValue
    ) {
        final Integer result = operation.calculate(value1, value2);
        final boolean compareResult = result == targetValue.intValue();
        LOGGER.debug("Comparing {} with the target {} result = {}", result, targetValue, compareResult);
        return compareResult;
    }

}
