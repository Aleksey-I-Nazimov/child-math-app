package org.numamo.child.math.app.view.sum.tables.renders;

import org.numamo.child.math.app.view.sum.tables.renders.api.ColorCellStrategy;
import org.numamo.child.math.app.view.sum.tables.renders.api.TableCellRenderExt;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

import static java.awt.Font.BOLD;
import static org.slf4j.LoggerFactory.getLogger;


@org.springframework.stereotype.Component
public final class TableCellRendererForInteger extends DefaultTableCellRenderer implements TableCellRenderExt.ForInt {

    private static final Logger LOGGER = getLogger(TableCellRendererForInteger.class);

    private final ColorCellStrategy colorCellStrategy;

    @Autowired
    public TableCellRendererForInteger(
            final ColorCellStrategy colorCellStrategy
    ) {
        this.colorCellStrategy = colorCellStrategy;
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
        final JLabel component = (JLabel) super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);

        component.setHorizontalAlignment(SwingConstants.CENTER);
        component.setVerticalAlignment(SwingConstants.CENTER);
        component.setFont(new Font("Default",BOLD,20));

        if (column ==0) {
            component.setForeground(Color.BLACK);
            component.setFont(new Font("Default",BOLD,8));
        } else if (column == 1) {
            component.setForeground(new Color(0,110,0));
            component.setFont(new Font("Default",BOLD,18));
        } else if (column==3) {
            component.setForeground(new Color(110,0,0));
            component.setFont(new Font("Default",BOLD,18));
        }

        colorCellStrategy.updateColor(component,row,column);

        return component;
    }

}
