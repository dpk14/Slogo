package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class IfElse extends ControlStructure {
    private int myIndexOfFirstList;
    private int myIndexOfSecondList;

    public IfElse(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode() {
        evaluateSimplifiableCopy(myStartingIndex + 1);
        double simplifiedExpression = Double.parseDouble(mySimplifiableCopy.get(myStartingIndex + 1));
        myIndexOfFirstList = myStartingIndex + 2;
        if (!mySimplifiableCopy.get(myIndexOfFirstList).equals("[")) ; //throw error
        myIndexOfSecondList = findIndexOfNextList(myIndexOfFirstList, mySimplifiableCopy);
        if (!mySimplifiableCopy.get(myIndexOfSecondList).equals("[")) ; //throw error
        List<Command> previousCommandLog = myStorage.getMyCommandLog();
        if (simplifiedExpression == 1) {
            evaluateSimplifiableCopy(myIndexOfFirstList);
        } else {
            evaluateSimplifiableCopy(myIndexOfSecondList);
        }
        myUserInput=mySimplifiableCopy;

        List<Command> currentCommandLog = myStorage.getMyCommandLog();
        if (previousCommandLog.size() != currentCommandLog.size()) {
            return currentCommandLog.get(currentCommandLog.size() - 1).getReturnValue();
        }
        return 0;
    }
}