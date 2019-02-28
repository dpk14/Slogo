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
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        double startValue=Double.parseDouble(simplifiedLine.get(myStartingIndex+3));
        myVariableValue=startValue;
        myEnd=Double.parseDouble(simplifiedLine.get(myStartingIndex+4));
        myIncrement=Double.parseDouble(simplifiedLine.get(myStartingIndex+5));
        myIndexOfList=myStartingIndex+6;
        if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        while(myVariableValue<myEnd) {
            evaluateLineSection(myIndexOfList, simplifiedLine);
            myVariableValue+=myIncrement;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
        List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
            return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
        }
        return 0;
    }
}
