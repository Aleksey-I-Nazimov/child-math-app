package org.numamo.child.math.app.model.sum.api;


public interface MathModel {

    Integer getValue1 (int rowNumber);

    Integer getValue2 (int rowNumber);

    String getOperation (int rowNumber);

    String getComparator (int rowNumber);

    void putUserAnswer(Integer userAnswer, int rowNumber);

    Integer getUserAnswer (int rowNumber);

    Integer getCorrectAnswer(int rowNumber);

    void cleanAndRestart (int maxNumberOfMathAssignments);

    ArithmeticResult checkUserAnswerAt (int rowNumber);

    boolean isCompleted ();

}
