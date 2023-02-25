package org.numamo.child.math.app.view.sum.tables.renders;

import org.numamo.child.math.app.model.sum.api.ArithmeticResult;
import org.numamo.child.math.app.model.sum.api.MathModel;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public final class ColorCellStrategyImpl implements ColorCellStrategy {

    private static final Logger LOGGER = getLogger(ColorCellStrategyImpl.class);

    private final UserAssignmentService assignmentService;
    private final MathModel mathModel;

    @Autowired
    public ColorCellStrategyImpl(
            UserAssignmentService assignmentService,
            MathModel mathModel
    ) {
        this.assignmentService = assignmentService;
        this.mathModel = mathModel;
    }

    @Override
    public boolean updateColor(java.awt.Component component, int row, int column) {

        if (assignmentService.getCurrentAssignment().getShowCheck()) {
            LOGGER.trace("Updating color for the row = {} column = {} component = {}",row,column,component);
            final ArithmeticResult result = mathModel.checkUserAnswerAt(row);
            if (result.wasCorrect()) {
                component.setBackground(new Color(150,255,150));
            } else if (!result.wasSet()) {
                component.setBackground(new Color(180,180,255));
            } else {
                component.setBackground(new Color(255,100,100));
            }
            return true;
        } else {
            LOGGER.trace("Updating color was not applied. " +
                    "Let's set default for the row = {} column = {} component = {}",row,column,component);
            component.setBackground(Color.WHITE);
            return false;
        }
    }
}
