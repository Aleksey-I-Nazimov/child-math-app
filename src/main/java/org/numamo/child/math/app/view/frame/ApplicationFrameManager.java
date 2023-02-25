package org.numamo.child.math.app.view.frame;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;

import static org.numamo.child.math.app.ChildMathAppApplication.APPLICATION_FRAME_MANAGER_COMPONENT;
import static org.slf4j.LoggerFactory.getLogger;

@Component(APPLICATION_FRAME_MANAGER_COMPONENT)
public final class ApplicationFrameManager {

    private static final Logger LOGGER  = getLogger(ApplicationFrameManager.class);

    private final JFrame frame = new JFrame ();

    @Autowired
    public ApplicationFrameManager(SumAssignmentPanel sumAssignmentPanel) {

        frame.setSize(new Dimension(400,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.add(sumAssignmentPanel);

    }

    public JFrame getFrame () {
        return this.frame;
    }

    @PostConstruct
    public void start () {
        LOGGER.info("The main application was launched");
        frame.setVisible(true);
        frame.repaint();
        frame.pack();
    }
}
