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
    public double executeCode(){
        String variable=mySimplifiableCopy.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        myVariableValue=0;
        evaluateSimplifiableCopy(myStartingIndex+1);
        myLimit=Double.parseDouble(mySimplifiableCopy.get(myStartingIndex+3));
        myIndexOfList=myStartingIndex+4;
        if (!mySimplifiableCopy.get(myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        myUserInput=mySimplifiableCopy;
        while(myVariableValue<myLimit) {
            mySimplifiableCopy=new ArrayList<>(myUserInput);
            evaluateSimplifiableCopy(myIndexOfList);
            myVariableValue++;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }
}

