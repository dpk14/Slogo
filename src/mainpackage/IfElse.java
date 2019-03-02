package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class IfElse extends ControlStructure {
    private int myIndexOfSecondList;

    public IfElse(int numOfExpressionArgumens, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArgumens, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new IfElse(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+1);
        double simplifiedExpression=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+1));
        myIndexOfFirstList = myStartingIndex + 2;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ; //throw error
        myIndexOfSecondList = findIndexOfNextList(myIndexOfFirstList, mySimplifiableLine);
        if (!mySimplifiableLine.get(myIndexOfSecondList).equals("[")) ; //throw error

        if (simplifiedExpression == 1) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList);
        }
        else {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList);
        }
    }
}