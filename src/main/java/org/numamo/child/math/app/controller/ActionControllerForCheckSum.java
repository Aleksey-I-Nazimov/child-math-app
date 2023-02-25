package org.numamo.child.math.app.controller;

import org.numamo.child.math.app.controller.api.ActionListener;
import org.numamo.child.math.app.controller.api.AssignmentCallback;
import org.numamo.child.math.app.model.sum.api.MathModel;
import org.numamo.child.math.app.service.api.UserAssignmentDmo;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.numamo.child.math.app.view.frame.ApplicationFrameManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.numamo.child.math.app.service.api.UserAssignmentDmo.ASSIGNMENT_SIZE;
import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class ActionControllerForCheckSum implements ActionListener.ForCheckSum {

    private static final Logger LOGGER = getLogger(ActionControllerForCheckSum.class);

    private final MathModel mathModel;
    private final AssignmentCallback.ForSumTable tableCallback;
    private final AssignmentCallback.ForSumCheckButton buttonCallback;
    //private final AssignmentFinishCallback.ForSumTable finishTableCallback;
    //private final AssignmentFinishCallback.ForSumCheckButton finishButtonCallback;
    private final UserAssignmentService userAssignmentService;
    private final ApplicationFrameManager frameManager;

    @Autowired
    public ActionControllerForCheckSum(
            MathModel mathModel,
            AssignmentCallback.ForSumTable tableCallback,
            AssignmentCallback.ForSumCheckButton buttonCallback,
            //AssignmentFinishCallback.ForSumTable finishTableCallback,
            //AssignmentFinishCallback.ForSumCheckButton finishButtonCallback,
            UserAssignmentService userAssignmentService,
            ApplicationFrameManager frameManager
    ) {
        this.mathModel = mathModel;
        this.tableCallback = tableCallback;
        this.buttonCallback = buttonCallback;
        //this.finishTableCallback = finishTableCallback;
        //this.finishButtonCallback = finishButtonCallback;
        this.userAssignmentService = userAssignmentService;
        this.frameManager = frameManager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        LOGGER.info("Checking the sum table: {}",e);

        final UserAssignmentDmo assignment = userAssignmentService.getCurrentAssignment();

        if (!assignment.getShowCheck()) {
            LOGGER.info("The table is going to be checked...");
            assignment.setShowCheck(true);
            assignment.incrementAttempt();
            tableCallback.onCheck(e);
            buttonCallback.onCheck(e);

        } else {
            assignment.setComplete(mathModel.isCompleted());
            if(assignment.isComplete()) {
                if (userAssignmentService.tryToGoThrough()) {

                    JOptionPane.showMessageDialog(frameManager.getFrame(),
                            "Вы успешно выполнили задание!");

                    userAssignmentService.saveResults();
                }
                mathModel.cleanAndRestart(ASSIGNMENT_SIZE);
                tableCallback.onNewAssignment(e);
                buttonCallback.onNewAssignment(e);

            } else {
                LOGGER.info("The math assignment was not completed...");
                assignment.incrementAttempt();
                tableCallback.onCheck(e);
                buttonCallback.onCheck(e);
            }
        }
    }

}
