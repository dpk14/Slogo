package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class For extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myEnd;
    private double myIncrement;

    public For(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public void convertCodeToCommands(){
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        simplifyLineSection(myStartingIndex+1);
        double startValue=Double.parseDouble(myUserInput.get(myStartingIndex+3));
        myVariableValue=startValue;
        myEnd=Double.parseDouble(myUserInput.get(myStartingIndex+4));
        myIncrement=Double.parseDouble(myUserInput.get(myStartingIndex+5));
        myIndexOfList=myStartingIndex+6;
        if (!myUserInput.get( myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myEnd) {
            myCommands.addAll(simplifyLineSection(myIndexOfList));
            myVariableValue+=myIncrement;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
    }
}

