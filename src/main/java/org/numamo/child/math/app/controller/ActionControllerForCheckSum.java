package org.numamo.child.math.app.controller;

import org.numamo.child.math.app.controller.api.ActionListener;
import org.numamo.child.math.app.controller.api.AssignmentCallback;
import org.numamo.child.math.app.model.table.api.TableTaskAnswerModel;
import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserLessonDmo;
import org.numamo.child.math.app.service.api.ScoreCalculator;
import org.numamo.child.math.app.service.api.StorageService;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.service.api.UserLessonService;
import org.numamo.child.math.app.view.frame.ApplicationFrameManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class ActionControllerForCheckSum implements ActionListener.ForCheckSum {

    private static final Logger LOGGER = getLogger(ActionControllerForCheckSum.class);

    private final StorageService storageService;
    private final UserAssignmentService userAssignmentService;
    private final UserLessonService userLessonService;
    private final AssignmentCallback.ForSumTable tableCallback;
    private final AssignmentCallback.ForSumCheckButton buttonCallback;
    private final ApplicationFrameManager frameManager;
    private final TableTaskAnswerModel tableTaskAnswerModel;
    private final ScoreCalculator scoreCalculator;

    @Autowired
    public ActionControllerForCheckSum(
            StorageService storageService,
            UserAssignmentService userAssignmentService,
            UserLessonService userLessonService,
            AssignmentCallback.ForSumTable tableCallback,
            AssignmentCallback.ForSumCheckButton buttonCallback,
            ApplicationFrameManager frameManager,
            TableTaskAnswerModel tableTaskAnswerModel,
            ScoreCalculator scoreCalculator
    ) {
        this.storageService = storageService;
        this.userAssignmentService = userAssignmentService;
        this.userLessonService = userLessonService;
        this.tableCallback = tableCallback;
        this.buttonCallback = buttonCallback;
        this.frameManager = frameManager;
        this.tableTaskAnswerModel = tableTaskAnswerModel;
        this.scoreCalculator = scoreCalculator;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.info("Checking the sum table: {}",e);

        final UserLessonDmo.AssignmentIteratorDmo iterator = storageService.getCurrent()
                .orElseThrow(NullPointerException::new).getIterator();
        final UserAssignmentDmo assignment = iterator.getCurrent().orElseThrow(NullPointerException::new);
        assignment.setUserTaskResults(tableTaskAnswerModel.getAllAnswers());

        final boolean success = userAssignmentService.checkAssignment(assignment);
        tableCallback.onCheck(e);
        buttonCallback.onCheck(e);

        if (success) {
            if (iterator.hasNext()) {
                final UserAssignmentDmo next = iterator.next();
                tableTaskAnswerModel.update(next.getUserTasks());
                tableCallback.onNewAssignment(e);
                buttonCallback.onNewAssignment(e);
                LOGGER.info("The assignment was set {}", next);

            } else {
                final int score = scoreCalculator.calculateScore(storageService.getCurrent()
                        .orElseThrow(NullPointerException::new));
                JOptionPane.showMessageDialog(frameManager.getFrame(),
                        "Вы выполнили задание. Ваша оценка = " + score);
                storageService.getCurrent().map(c -> {
                    storageService.saveLesson(c);
                    return c;
                });
                storageService.setCurrent(userLessonService.makeLesson());
                final UserLessonDmo.AssignmentIteratorDmo newIterator = storageService.getCurrent()
                        .orElse(null).getIterator();
                final UserAssignmentDmo next = newIterator.next();
                tableTaskAnswerModel.update(next.getUserTasks());
                tableCallback.onNewAssignment(e);
                buttonCallback.onNewAssignment(e);
                LOGGER.info("The new lesson assignment was set {}", next);
            }
        }
    }

}
