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
    public double executeCode(){
        evaluateSimplifiableCopy(myStartingIndex);
        double simplifiedExpression=Double.parseDouble(mySimplifiableCopy.get(myStartingIndex+1));
        myIndexOfList=myStartingIndex+2;
        if (!mySimplifiableCopy.get( myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        if(simplifiedExpression==1) {
            evaluateSimplifiableCopy(myIndexOfList);
        }
        myUserInput=mySimplifiableCopy;
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }
}