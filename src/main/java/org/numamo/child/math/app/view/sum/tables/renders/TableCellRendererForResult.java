package org.numamo.child.math.app.view.sum.tables.renders;

import org.numamo.child.math.app.model.task.AutoCheckDmo;
import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;
import org.numamo.child.math.app.service.api.StorageService;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.numamo.child.math.app.view.sum.tables.renders.api.TableCellRenderExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Optional;

import static java.awt.Font.ITALIC;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class TableCellRendererForResult extends DefaultTableCellRenderer implements TableCellRenderExt.ForResult {

    private static final Logger LOGGER = getLogger(TableCellRendererForResult.class);

    private final StorageService storageService;
    private final ColorCellStrategy colorCellStrategy;
    private final UserAssignmentService assignmentService;

    @Autowired
    public TableCellRendererForResult(
            StorageService storageService,
            ColorCellStrategy colorCellStrategy,
            UserAssignmentService assignmentService
    ) {
        this.storageService = storageService;
        this.colorCellStrategy = colorCellStrategy;
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

        final Optional<UserLessonDmo> lessonOpt = storageService.getCurrent();

        if (lessonOpt.isPresent()) {

            final UserLessonDmo lesson = lessonOpt.get();
            final UserLessonDmo.AssignmentIteratorDmo iterator = lesson.getIterator();
            final UserAssignmentDmo assignment = iterator.getCurrent()
                    .orElseThrow(IllegalStateException::new);
            final AutoCheckDmo check = assignment.getAutoCheck();

            if (check.isShowCheck()) {
                final UserTaskResultDmo result = (UserTaskResultDmo) value;
                component.setHorizontalAlignment(SwingConstants.LEFT);
                component.setVerticalAlignment(SwingConstants.CENTER);
                component.setFont(new Font("Default", ITALIC, 18));

                final boolean success = assignmentService.checkTask(result);
                final boolean set = nonNull(result.getUserSolution().getUserAnswer());

                final String stringValue;
                if (set) {
                    if (success) {
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
        }

        colorCellStrategy.updateColor(component,row,column);
        return component;
    }

}
