package org.numamo.child.math.app.service.api;

/**
 * The configuration API of the main limits
 *
 * @author Nazimov Aleksey I.
 */
public interface MathAssignmentConfig {

    /**
     * The HIGHEST random value used in building math task
     *
     * @return the highest random int value
     */
    int getNumericalMathLimit();

    /**
     * The max number of checking of the selected assignment
     *
     * @return the max number of checking
     */
    int getCheckLimit();

    /**
     * The max number of math tasks of the selected assignment
     *
     * @return the max number of math tasks
     */
    int getMaxNumberOfMathTasks();

    /**
     * The max number of assignments in the selected lesson
     *
     * @return the max number of assignments
     */
    int getMaxNumberOfAssignments();

}
