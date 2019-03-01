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
    protected void convertCodeToCommands(){
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        myVariableValue=0;
        simplifyLineSection(myStartingIndex+1);
        myLimit=Double.parseDouble(myUserInput.get(myStartingIndex+3));
        myIndexOfList=myStartingIndex+4;
        if (!myUserInput.get(myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myLimit) {
            myCommands.addAll(simplifyLineSection(myIndexOfList));
            myVariableValue++;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
    }
}

