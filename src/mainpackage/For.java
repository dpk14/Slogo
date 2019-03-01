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
    public double executeCode(){
        String variable=mySimplifiableCopy.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        evaluateSimplifiableCopy(myStartingIndex+1);
        double startValue=Double.parseDouble(mySimplifiableCopy.get(myStartingIndex+3));
        myVariableValue=startValue;
        myEnd=Double.parseDouble(mySimplifiableCopy.get(myStartingIndex+4));
        myIncrement=Double.parseDouble(mySimplifiableCopy.get(myStartingIndex+5));
        myIndexOfList=myStartingIndex+6;
        if (!mySimplifiableCopy.get( myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        myUserInput=mySimplifiableCopy;
        while(myVariableValue<myEnd) {
            mySimplifiableCopy=new ArrayList<>(myUserInput);
            evaluateSimplifiableCopy(myIndexOfList);
            myVariableValue+=myIncrement;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
        myUserInput=mySimplifiableCopy;
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }
}

