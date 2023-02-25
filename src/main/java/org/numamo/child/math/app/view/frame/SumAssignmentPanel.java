package org.numamo.child.math.app.view.frame;

import org.numamo.child.math.app.view.sum.buttons.CheckButtonPanel;
import org.numamo.child.math.app.view.sum.tables.AssignmentTablePanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;

import java.awt.*;


@Component
public final class SumAssignmentPanel extends JPanel {

    private final AssignmentTablePanel assignmentTablePanel;
    private final CheckButtonPanel checkButtonPanel;

    @Autowired
    public SumAssignmentPanel(
            AssignmentTablePanel assignmentTablePanel,
            CheckButtonPanel checkButtonPanel
    ) {
        this.assignmentTablePanel = assignmentTablePanel;
        this.checkButtonPanel = checkButtonPanel;
    }

    @PostConstruct
    public void init () {
        final BorderLayout layout = new BorderLayout();
        super.setLayout(layout);

        super.add(assignmentTablePanel,BorderLayout.CENTER);
        super.add(checkButtonPanel,BorderLayout.SOUTH);
    }

}
