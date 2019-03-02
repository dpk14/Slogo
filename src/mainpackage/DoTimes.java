package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class DoTimes extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;

    public DoTimes(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new DoTimes(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure() {
        do {
            String variable = mySimplifiableLine.get(myStartingIndex + 2);
            myVariableName = myParser.removeColon(variable);
            myVariableValue = 1;
            myStorage.setVariableValue(myVariableName, myVariableValue);
            simplifyAndEvaluate(mySimplifiableLine, myStartingIndex + 1);
            myLimit = Double.parseDouble(mySimplifiableLine.get(myStartingIndex + 3));
            myIndexOfFirstList = myStartingIndex + 4;

            if (myLimit == 0) return;
            else if (myLimit < 0) ; // TODO: error
            if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ; //TODO: throw error

            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList);
            if (myVariableValue != myLimit) resetSimplification(mySavedLine);
            myVariableValue++;

        } while (myVariableValue <= myLimit);
    }
}

