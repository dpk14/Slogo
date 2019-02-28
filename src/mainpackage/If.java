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
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        double simplifiedExpression=Double.parseDouble(simplifiedLine.get(myStartingIndex+1));
        myIndexOfList=myStartingIndex+2;
        if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        if(simplifiedExpression==1) {
            simplifiedLine=evaluateLineSection(myIndexOfList, simplifiedLine);
        }
        myUserInput=simplifiedLine;
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }
}