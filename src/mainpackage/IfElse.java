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