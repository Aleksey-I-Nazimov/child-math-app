package org.numamo.child.math.app.view.sum.tables.renders.sum;

import org.numamo.child.math.app.model.sum.api.ArithmeticResult;
import org.numamo.child.math.app.model.sum.api.MathModel;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.numamo.child.math.app.view.sum.tables.renders.sum.api.SumTableCellRenderExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static java.awt.Font.ITALIC;
import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class SumTableCellRendererForResult extends DefaultTableCellRenderer implements SumTableCellRenderExt.ForResult {

    private static final Logger LOGGER = getLogger(SumTableCellRendererForResult.class);

    private final ColorCellStrategy colorCellStrategy;
    private final MathModel mathModel;
    private final UserAssignmentService assignmentService;

    @Autowired
    public SumTableCellRendererForResult(
            final ColorCellStrategy colorCellStrategy,
            MathModel mathModel,
            UserAssignmentService assignmentService
    ) {
        this.colorCellStrategy = colorCellStrategy;
        this.mathModel = mathModel;
        this.assignmentService = assignmentService;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table,
            final Object value,
            final boolean isSelected,
            final boolean hasFocus,
            final int row,
            final int column
    ) {
        LOGGER.trace("Rendering: c[{}][{}]={}, selected={}/focused={}", row, column, value, isSelected, hasFocus);

        final JLabel component = (JLabel) super
                .getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        if (assignmentService.getCurrentAssignment().getShowCheck()) {
            final ArithmeticResult result  = (ArithmeticResult) value;
            component.setHorizontalAlignment(SwingConstants.LEFT);
            component.setVerticalAlignment(SwingConstants.CENTER);
            component.setFont(new Font("Default",ITALIC,18));

            final String stringValue;
            if (result.wasSet()) {
                if (result.wasCorrect()) {
                    stringValue = "Отлично";
                } else {
                    stringValue = "Неправильно";
                }
            } else {
                stringValue = "Пропущен";
            }
            super.setValue(stringValue);

        } else {
            super.setValue("");
        }

        colorCellStrategy.updateColor(component,row,column);
        return component;
    }

}
