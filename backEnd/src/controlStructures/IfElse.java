package controlStructures;

import general.ProgramParser;
import general.SystemStorage;

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
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+1, myAnimal);
        double simplifiedExpression=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+1));
        myIndexOfFirstList = myStartingIndex + 2;
        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")) ; //throw error
        myIndexOfSecondList = findIndexOfEndBracket(myIndexOfFirstList, mySimplifiableLine)+1;
        if (!mySimplifiableLine.get(myIndexOfSecondList).equals("[")) ; //throw error

        if (simplifiedExpression == 1) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
        }
        else {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfSecondList, myAnimal);
        }
    }
}