package org.numamo.child.math.app.view.sum.buttons;

import org.numamo.child.math.app.controller.api.ActionListener;
import org.numamo.child.math.app.controller.api.AssignmentCallback;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public final class CheckButtonPanel extends JPanel implements AssignmentCallback.ForSumCheckButton {

    private static final Logger LOGGER = getLogger(CheckButtonPanel.class);

    private static final String CHECK_TITLE = "Проверить";
    private static final String GET_NEW_TITLE = "Получить новое задание";

    private final JButton checkButton =new JButton(CHECK_TITLE);;

    @PostConstruct
    public void init () {
        super.add(checkButton);
    }

    @Autowired
    @Lazy
    public void setController (ActionListener.ForCheckSum checkSumController) {
        checkButton.addActionListener(checkSumController);
    }


    @Override
    public void onCheck(ActionEvent e) {
        LOGGER.debug("Check event: {}",e);
        checkButton.setText(GET_NEW_TITLE);
    }

    @Override
    public void onNewAssignment(ActionEvent e) {
        LOGGER.debug("New assignment event: {}",e);
        checkButton.setText(CHECK_TITLE);
    }
}
