package org.numamo.child.math.app.view.sum.tables.renders.api;

import org.numamo.child.math.app.model.task.UserTaskResultDmo;

import javax.swing.table.TableCellRenderer;


public interface TableCellRenderExt<T> extends TableCellRenderer {

    Class<T> getCellClass ();

    interface ForInt extends TableCellRenderExt<Integer> {
        default Class<Integer> getCellClass () {
            return Integer.class;
        }
    }

    interface ForString extends TableCellRenderExt<String> {
        default Class<String> getCellClass () {
            return String.class;
        }
    }

    interface ForResult extends TableCellRenderExt<UserTaskResultDmo> {
        default Class<UserTaskResultDmo> getCellClass() {
            return UserTaskResultDmo.class;
        }
    }

}
