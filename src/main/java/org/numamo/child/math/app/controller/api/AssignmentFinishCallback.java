package org.numamo.child.math.app.controller.api;

import java.awt.event.ActionEvent;

public interface AssignmentFinishCallback {

    void onFinish(ActionEvent e);


    interface ForSumTable extends AssignmentFinishCallback {}


    interface ForSumCheckButton extends AssignmentFinishCallback {}


}
