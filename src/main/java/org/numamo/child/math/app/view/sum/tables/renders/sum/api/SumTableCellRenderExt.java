package org.numamo.child.math.app.view.sum.tables.renders.sum.api;

import org.numamo.child.math.app.model.sum.api.ArithmeticResult;

import javax.swing.table.TableCellRenderer;


public interface SumTableCellRenderExt<T> extends TableCellRenderer {

    Class<T> getCellClass ();

    interface ForInt extends SumTableCellRenderExt<Integer> {
        default Class<Integer> getCellClass () {
            return Integer.class;
        }
    }

    interface ForString extends SumTableCellRenderExt<String> {
        default Class<String> getCellClass () {
            return String.class;
        }
    }

    interface ForResult extends SumTableCellRenderExt<ArithmeticResult> {
        default Class<ArithmeticResult> getCellClass () {return ArithmeticResult.class;}
    }

}
