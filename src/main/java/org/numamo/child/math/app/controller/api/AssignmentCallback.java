package org.numamo.child.math.app.controller.api;

import java.awt.event.ActionEvent;

public interface AssignmentCallback {

    void onCheck (ActionEvent e);

    void onNewAssignment (ActionEvent e);



    interface ForSumTable extends AssignmentCallback {}


    interface ForSumCheckButton extends AssignmentCallback {}


}
