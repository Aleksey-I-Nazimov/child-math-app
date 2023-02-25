package org.numamo.child.math.app.model.sum;

import org.numamo.child.math.app.model.sum.api.ArithmeticResult;
import org.numamo.child.math.app.model.sum.api.MathModel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.numamo.child.math.app.service.api.UserAssignmentDmo.ASSIGNMENT_SIZE;


@Component
public final class MathModelForSumImpl implements MathModel {

    private static final int MAX_VALUE = 10;

    private final List<Integer> firstValues = new ArrayList<>();
    private final List<Integer> secondValues = new ArrayList<>();
    private final Map<Integer,Integer> mapResult = new HashMap<>();
    private final Map<Integer,Integer> correctMapResult = new HashMap<>();

    @PostConstruct
    public void init () {
        cleanAndRestart(ASSIGNMENT_SIZE);
    }

    @Override
    public Integer getValue1(int sequentialNumber) {
        return firstValues.get(sequentialNumber);
    }

    @Override
    public Integer getValue2(int sequentialNumber) {
        return secondValues.get(sequentialNumber);
    }

    @Override
    public String getOperation(int sequentialNumber) {
        return "+";
    }

    @Override
    public String getComparator(int sequentialNumber) {
        return "=";
    }

    @Override
    public void putUserAnswer(Integer userAnswer, int sequentialNumber) {
        mapResult.put(sequentialNumber,userAnswer);
    }

    @Override
    public Integer getUserAnswer(int sequentialNumber) {
        return mapResult.get(sequentialNumber);
    }

    @Override
    public Integer getCorrectAnswer(int sequentialNumber) {
        return correctMapResult.get(sequentialNumber);
    }

    @Override
    public void cleanAndRestart(int maxNumberOfMathAssignments) {
        clean();
        initValues(maxNumberOfMathAssignments);
    }

    @Override
    public ArithmeticResult checkUserAnswerAt(int rowNumber) {
        return new ArithmeticResult(rowNumber,mapResult.get(rowNumber),correctMapResult.get(rowNumber));
    }


    @Override
    public boolean isCompleted() {

        for (int i=0;  i<this.firstValues.size();  ++i) {
            if (!checkUserAnswerAt(i).wasCorrect()) {
                return false;
            }
        }

        return true;
    }


    private void initValues ( int maxSequentialNumber ) {

        final Random random = new Random();

        for (int i=0;  i<maxSequentialNumber;  ++i) {

            final int value1 = random.nextInt(MAX_VALUE);
            final int value2 = random.nextInt(MAX_VALUE);

            firstValues.add(value1);
            secondValues.add(value2);
            correctMapResult.put(i,value1+value2);
        }
    }

    private void clean () {
        firstValues.clear();
        secondValues.clear();
        mapResult.clear();
        correctMapResult.clear();
    }
}
