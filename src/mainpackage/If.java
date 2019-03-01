package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class If extends ControlStructure {
    private int myIndexOfList;

    public If(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new If(myNumOfListArguments, myParser, myStorage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+1);
        double simplifiedExpression=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+1));
        myIndexOfList=myStartingIndex+2;
        if (!mySimplifiableLine.get(myIndexOfList).equals("[")); //throw error
        if(simplifiedExpression==1) {
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfList);
        }
    }
}