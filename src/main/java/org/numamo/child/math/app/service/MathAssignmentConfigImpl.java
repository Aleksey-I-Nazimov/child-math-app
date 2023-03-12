package org.numamo.child.math.app.service;

import org.numamo.child.math.app.service.api.MathAssignmentConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MathAssignmentConfigImpl implements MathAssignmentConfig {

    @Override
    public int getNumericalMathLimit() {
        return 13;
    }

    @Override
    public int getCheckLimit() {
        return 3;
    }

    @Override
    public int getMaxNumberOfMathTasks() {
        return 10;
    }

    @Override
    public int getMaxNumberOfAssignments() {
        return 3;
    }

}
