package mainpackage;

import java.util.ArrayList;

public class List extends ControlStructure {
    String myIndexOfList=myUserInput.get(myStartingIndex);

    ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
    double startValue=Double.parseDouble(simplifiedLine.get(myStartingIndex+3));
    myVariableValue=startValue;
    myEnd=Double.parseDouble(simplifiedLine.get(myStartingIndex+4));
    myIncrement=Double.parseDouble(simplifiedLine.get(myStartingIndex+5));
    myIndexOfList=myStartingIndex+6;
        if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
    java.util.List<Command> previousCommandLog=myStorage.getMyCommandLog();
        while(myVariableValue<myEnd) {
        evaluateLineSection(myIndexOfList, simplifiedLine);
        myVariableValue+=myIncrement;
        myStorage.setVariableValue(myVariableName, myVariableValue);
    }
    java.util.List<Command> currentCommandLog=myStorage.getMyCommandLog();
        if(previousCommandLog.size()!=currentCommandLog.size()){
        return currentCommandLog.get(currentCommandLog.size()-1).getReturnValue();
}
