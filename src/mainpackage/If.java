package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class If extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;

    public If(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    protected void convertCodeToCommands(){
        simplifyLineSection(myStartingIndex);
        double simplifiedExpression=Double.parseDouble(myUserInput.get(myStartingIndex+1));
        myIndexOfList=myStartingIndex+2;
        if (!myUserInput.get(myIndexOfList).equals("[")); //throw error
        List<Command> parsedList=new ArrayList<>();
        if(simplifiedExpression==1) {
            parsedList=simplifyLineSection(myIndexOfList);
        }
        myCommands.addAll(parsedList);
    }
}