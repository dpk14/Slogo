package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class For extends ControlStructure {
    private double myVariableValue;
    private String myVariableName;
    private double myStart;
    private double myEnd;
    private double myIncrement;
    private int myIndexOfSecondList;

    public For(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new For(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    public void simplifyAndExecuteStructure(){
        int counter=0;
        do {
            myIndexOfFirstList=myStartingIndex+1;
            if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")); //TODO error
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myActiveAnimals);
            String variable = mySimplifiableLine.get(myIndexOfFirstList + 1);
            myVariableName = myParser.removeColon(variable);
            myStart = Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList + 2));
            if (counter == 0) myVariableValue = myStart;
            myStorage.setVariableValue(myVariableName, myVariableValue);
            //System.out.printf("%f", myStorage.getVariableValue(myVariableName));
            myEnd = Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList + 3));
            myIncrement = Double.parseDouble(mySimplifiableLine.get(myIndexOfFirstList + 4));
            myIndexOfSecondList = findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine)+1;

            if (!mySimplifiableLine.get(myIndexOfSecondList).equals("[")) ; //throw error
            //myIndexOfSecondList = myStartingIndex + 6;
            if (myStart > myEnd) ; //TODO: error

            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList, myActiveAnimals);
            if (myVariableValue != myEnd) {
                resetSimplification(mySavedLine);
                mySavedLine=new ArrayList<>(mySavedLine);
            }
            myVariableValue += myIncrement;
            counter++;

        } while(myVariableValue<=myEnd);
    }
}

