package org.numamo.child.math.app.model.sum;

import org.numamo.child.math.app.model.sum.api.ArithmeticResult;
import org.numamo.child.math.app.model.sum.api.MathModel;
import org.numamo.child.math.app.service.api.UserAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static org.numamo.child.math.app.service.api.UserAssignmentDmo.ASSIGNMENT_SIZE;


@Component
public final class TableModelImpl implements TableModel {

    private static final int COLUMNS_NUMBER = 8;

    private final List<TableModelListener> listeners = new ArrayList<>();
    private final MathModel mathModel;
    private final UserAssignmentService userAssignmentService;

    @Autowired
    public TableModelImpl(
            MathModel mathModel,
            UserAssignmentService userAssignmentService
    ) {
        this.mathModel = mathModel;
        this.userAssignmentService = userAssignmentService;
    }

    @Override
    public int getRowCount() {
        return ASSIGNMENT_SIZE;
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
            case 5:
                return Integer.class;
            case 6:
                return Integer.class;
            case 7:
                return ArithmeticResult.class;
            default:
                throw new IllegalArgumentException("Unknown column: " + columnIndex);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return rowIndex + 1;
        } else if (columnIndex == 1) {
            return mathModel.getValue1(rowIndex);
        } else if (columnIndex == 2) {
            return mathModel.getOperation(rowIndex);
        } else if (columnIndex == 3) {
            return mathModel.getValue2(rowIndex);
        } else if (columnIndex == 4) {
            return mathModel.getComparator(rowIndex);
        } else if (columnIndex == 5) {
            return mathModel.getUserAnswer(rowIndex);
        } else if (columnIndex == 6) {
            if (!userAssignmentService.getCurrentAssignment().wasNotFailed()) {
                return mathModel.getCorrectAnswer(rowIndex);
            } else {
                return null;
            }
        } else if (columnIndex == 7) {
            return mathModel.checkUserAnswerAt(rowIndex);
        } else {
            throw new IllegalArgumentException("Unknown values " + rowIndex + ", " + columnIndex);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            final int value = Integer.parseInt(aValue.toString());
            mathModel.putUserAnswer(value, rowIndex);
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
