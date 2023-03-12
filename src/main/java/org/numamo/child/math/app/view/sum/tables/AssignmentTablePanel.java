package org.numamo.child.math.app.view.sum.tables;

import org.numamo.child.math.app.controller.api.AssignmentCallback;
import org.numamo.child.math.app.view.sum.tables.renders.api.TableCellRenderExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public final class AssignmentTablePanel extends JPanel implements AssignmentCallback.ForSumTable {

    private static final Logger LOGGER = getLogger(AssignmentTablePanel.class);

    private final List<TableCellRenderExt> renderList;
    private final TableModel tableModel;
    private JTable assignmentTable;

    @Autowired
    public AssignmentTablePanel(
            final List<TableCellRenderExt> renderList,
            final TableModel tableModel
    ) {
        this.renderList = renderList;
        this.tableModel = tableModel;
    }

    @PostConstruct
    public void init () {

        assignmentTable = new JTable(tableModel);
        renderList
                .stream()
                .collect(toMap((Function<TableCellRenderExt, Class>) TableCellRenderExt::getCellClass, v -> v))
                .forEach(assignmentTable::setDefaultRenderer);

        assignmentTable.setShowGrid(true);
        assignmentTable.setShowHorizontalLines(true);
        assignmentTable.setShowVerticalLines(false);

        super.setLayout(new BorderLayout());
        super.add (new JScrollPane(assignmentTable));
        super.add (assignmentTable, BorderLayout.CENTER);
        super.add (assignmentTable.getTableHeader(),BorderLayout.NORTH);
    }

    @Override
    public void onCheck(ActionEvent e) {
        LOGGER.debug ("Check event: {}",e);
        assignmentTable.repaint();
    }

    @Override
    public void onNewAssignment(ActionEvent e) {
        LOGGER.debug ("New assignment event: {}",e);
        assignmentTable.repaint();
    }

}
