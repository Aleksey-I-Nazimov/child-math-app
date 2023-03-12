package org.numamo.child.math.app.model.math.api;

public interface Operation {

    int calculate(int operand1, int operand2);

    String getDescription();

    interface ForSum extends Operation {

        @Override
        default String getDescription() {
            return "+";
        }
    }

    interface ForDif extends Operation {

        @Override
        default String getDescription() {
            return "-";
        }
    }

}
