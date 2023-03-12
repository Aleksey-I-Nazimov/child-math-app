package org.numamo.child.math.app.view.sum.tables.renders;

import org.numamo.child.math.app.model.table.api.TableTaskAnswerModel;
import org.numamo.child.math.app.model.task.AutoCheckDmo;
import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;
import org.numamo.child.math.app.service.api.StorageService;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Optional;

import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public final class ColorCellStrategyImpl implements ColorCellStrategy {

    private static final Logger LOGGER = getLogger(ColorCellStrategyImpl.class);

    private final StorageService storageService;
    private final UserAssignmentService assignmentService;
    private final TableTaskAnswerModel tableTaskAnswerModel;

    @Autowired
    public ColorCellStrategyImpl(
            StorageService storageService,
            UserAssignmentService assignmentService,
            TableTaskAnswerModel tableTaskAnswerModel
    ) {
        this.storageService = storageService;
        this.assignmentService = assignmentService;
        this.tableTaskAnswerModel = tableTaskAnswerModel;
    }

    @Override
    public boolean updateColor(
            final java.awt.Component component,
            final int row,
            final int column
    ) {
        final Optional<UserLessonDmo> lessonOpt = storageService.getCurrent();
        if (lessonOpt.isPresent()) {
            final UserLessonDmo lesson = lessonOpt.get();
            final UserLessonDmo.AssignmentIteratorDmo iterator = lesson.getIterator();
            final UserAssignmentDmo assignment = iterator.getCurrent()
                    .orElseThrow(IllegalStateException::new);
            final AutoCheckDmo check = assignment.getAutoCheck();
            if (check.isShowCheck()) {
                LOGGER.trace("Updating color for the row = {} column = {} component = {}", row, column, component);
                final UserTaskResultDmo result = tableTaskAnswerModel.checkUserAnswerAt(row);
                final boolean success = assignmentService.checkTask(result);
                final boolean notSet = isNull(result.getUserSolution().getUserAnswer());
                if (success) {
                    component.setBackground(new Color(150, 255, 150));
                } else if (notSet) {
                    component.setBackground(new Color(180, 180, 255));
                } else {
                    component.setBackground(new Color(255, 100, 100));
                }
                return true;
            }
        }

        LOGGER.trace("Updating color was not applied. " +
                "Let's set default for the row = {} column = {} component = {}", row, column, component);
        component.setBackground(Color.WHITE);
        return false;

    }
}
