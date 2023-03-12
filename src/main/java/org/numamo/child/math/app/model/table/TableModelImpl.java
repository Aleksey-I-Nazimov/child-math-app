package org.numamo.child.math.app.model.table;

import org.numamo.child.math.app.model.table.api.TableTaskAnswerModel;
import org.numamo.child.math.app.model.task.UserAssignmentDmo;
import org.numamo.child.math.app.model.task.UserTaskResultDmo;
import org.numamo.child.math.app.service.api.MathAssignmentConfig;
import org.numamo.child.math.app.service.api.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.valueOf;


/**
 * The main SWING table model implementation.
 * It's used for SWING JTable
 *
 * @author Nazimov Aleksey I.
 */
@Component
public final class TableModelImpl implements TableModel {

    private static final int COLUMNS_NUMBER = 8;

    private final List<TableModelListener> listeners = new ArrayList<>();

    private final MathAssignmentConfig mathAssignmentConfig;
    private final TableTaskAnswerModel tableTaskAnswerModel;
    private final StorageService storageService;

    @Autowired
    public TableModelImpl(
            MathAssignmentConfig mathAssignmentConfig,
            TableTaskAnswerModel tableTaskAnswerModel,
            StorageService storageService
    ) {
        this.mathAssignmentConfig = mathAssignmentConfig;
        this.tableTaskAnswerModel = tableTaskAnswerModel;
        this.storageService = storageService;
    }

    @Override
    public int getRowCount() {
        return mathAssignmentConfig.getMaxNumberOfMathTasks();
    }

    @Override
    public int getColumnCount() {
        return COLUMNS_NUMBER;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Номер задания";
            case 1:
                return "X";
            case 2:
                return "Что сделать";
            case 3:
                return "Y";
            case 4:
                return "Как сравнить";
            // Answer column: For making answers:
            case 5:
                return "Ответ";
            case 6:
                return "Правильный ответ";
            case 7:
                return "Оценка";
            default:
                throw new IllegalArgumentException("Unknown column: " + columnIndex);
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return Integer.class;
            case 4:
                return String.class;
            // Answer column: For making answers:
            case 5:
                return Integer.class;
            case 6:
                return Integer.class;
            case 7:
                return UserTaskResultDmo.class;
            default:
                throw new IllegalArgumentException("Unknown column: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Answer column: For making answers:
        return columnIndex == 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex + 1;
        } else if (columnIndex == 1) {
            return tableTaskAnswerModel.getValue1(rowIndex);
        } else if (columnIndex == 2) {

            // The main operation description:
            return tableTaskAnswerModel.getOperation(rowIndex).getDescription();

        } else if (columnIndex == 3) {
            return tableTaskAnswerModel.getValue2(rowIndex);
        } else if (columnIndex == 4) {

            // The main comparator:
            return tableTaskAnswerModel.getComparator(rowIndex).getDescription();
        } else if (columnIndex == 5) {
            return tableTaskAnswerModel.getLastUserAnswer(rowIndex);
        } else if (columnIndex == 6) {
            final UserAssignmentDmo assignment = storageService.getCurrent().orElse(null)
                    .getIterator().getCurrent().orElse(null);

            if (assignment.getAutoCheck().isFailedByChecks()) {
                return tableTaskAnswerModel.getCorrectAnswer(rowIndex);
            } else {
                return null;
            }
        } else if (columnIndex == 7) {
            return tableTaskAnswerModel.checkUserAnswerAt(rowIndex);
        } else {
            throw new IllegalArgumentException("Unknown values " + rowIndex + ", " + columnIndex);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            final int value = Integer.parseInt(aValue.toString());
            tableTaskAnswerModel.appendUserAnswer(valueOf(value), rowIndex);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }


}
