package org.numamo.child.math.app.model.math;

import org.numamo.child.math.app.model.math.api.Operation;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;


public final class OperationForDifImpl implements Operation.ForDif {

    private static final Logger LOGGER = getLogger(OperationForDifImpl.class);

    @Override
    public int calculate(int operand1, int operand2) {

        final int result = operand1 + operand2;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("{} {} {} = {}", operand1, this.getDescription(), operand2, result);
        }

        return result;
    }

}
