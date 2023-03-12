package org.numamo.child.math.app.view.sum.tables.renders;

import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.numamo.child.math.app.view.sum.tables.renders.api.TableCellRenderExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

import static java.awt.Font.BOLD;
import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class TableCellRendererForString extends DefaultTableCellRenderer implements TableCellRenderExt.ForString {

    private static final Logger LOGGER = getLogger(TableCellRendererForString.class);

    private final ColorCellStrategy colorCellStrategy;
    private final TableModel tableModel;

    @Autowired
    public TableCellRendererForString(
            final ColorCellStrategy colorCellStrategy,
            final TableModel tableModel
    ) {
        this.colorCellStrategy = colorCellStrategy;
        this.tableModel = tableModel;
    }

    @Override
    public Component getTableCellRendererComponent(
            final JTable table,
            final Object value,
            final boolean isSelected,
            final boolean hasFocus,
            final int row,
            final int column
    ) {
        LOGGER.trace("Rendering: c[{}][{}]={}, selected={}/focused={}", row, column, value, isSelected, hasFocus);
        final JLabel component = (JLabel) super.getTableCellRendererComponent(table,
                value, isSelected, hasFocus, row, column);

        final Class<?> columnClass = tableModel.getColumnClass(column);
        if (columnClass==String.class) {
            component.setHorizontalAlignment(SwingConstants.CENTER);
            component.setVerticalAlignment(SwingConstants.CENTER);
            component.setFont(new Font("Default",BOLD,25));
        }

        colorCellStrategy.updateColor(component,row,column);
        return component;
    }
}
