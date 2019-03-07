package controlStructures;

import mainpackage.ProgramParser;
import mainpackage.SystemStorage;

import java.util.ArrayList;

public class DoTimes extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;
    private int myIndexOfSecondList;

    public DoTimes(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new DoTimes(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure() {
        int counter=0;
        do {
            myIndexOfFirstList=myStartingIndex+1;
            if(mySimplifiableLine.get(myIndexOfFirstList)!="["); //TODO: error, list expected
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList);
            String variable = mySimplifiableLine.get(myIndexOfFirstList + 1);
            myVariableName = myParser.removeColon(variable);
            if (counter==0) myVariableValue = 1;
            myStorage.setVariableValue(myVariableName, myVariableValue);
            myLimit = Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList + 2));
            myIndexOfSecondList = myIndexOfFirstList + 4;

            if (myLimit == 0) return;
            else if (myLimit < 0) ; // TODO: error
            if (!mySimplifiableLine.get(myIndexOfSecondList).equals("[")) ; //TODO: throw error

            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList);
            if (myVariableValue != myLimit) {
                resetSimplification(mySavedLine);
                mySavedLine=new ArrayList<>(mySavedLine);
            }
            myVariableValue++;
            counter++;
        } while (myVariableValue <= myLimit);
    }
}
