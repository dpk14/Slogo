package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class DoTimes extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;

    public DoTimes(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    protected void simplifyAndExecuteStructure(){
        String variable=mySimplifiableLine.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        myVariableValue=0;
        simplifyAndEvaluate(mySimplifiableLine,myStartingIndex+1);
        myLimit=Double.parseDouble(mySimplifiableLine.get(myStartingIndex+3));
        myIndexOfList=myStartingIndex+4;
        if (!mySimplifiableLine.get(myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myLimit) {
            mySimplifiableLine=new ArrayList<>(mySavedLine);
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfList);
            myVariableValue++;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
    }
}

