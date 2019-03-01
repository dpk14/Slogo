package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class For extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myStart;
    private double myEnd;
    private double myIncrement;

    public For(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public void simplifyAndExecuteStructure(){
        simplifyAndEvaluate(mySimplifiableLine, myStartingIndex+1);
        String variable=mySimplifiableLine.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        myStart=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+3));
        myVariableValue=myStart;
        myEnd=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+4));
        myIncrement=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+5));
        myIndexOfList=myStartingIndex+6;
        if (!mySimplifiableLine.get( myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myEnd) {
            mySimplifiableLine=new ArrayList<>(mySavedLine);
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfList);
            myVariableValue+=myIncrement;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
    }
}

