package controlStructures;

import general.ProgramParser;
import general.SystemStorage;

public class If extends ControlStructure {

    public If(int numOfExpressionArguments, int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfExpressionArguments, numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new If(myNumOfExpressionArguments, myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+1, myAnimal);
        double simplifiedExpression=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+1));
        myIndexOfFirstList=myStartingIndex+2;

        if (!mySimplifiableLine.get(myIndexOfFirstList).equals("[")); //throw error

        if(simplifiedExpression==1) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfFirstList, myAnimal);
        }
    }
}