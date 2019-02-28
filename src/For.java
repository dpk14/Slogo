import java.util.ArrayList;

public class DoTimes extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myEnd;
    private double myIncrement;

    @Override
    public double executeCode(){
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=removeColon(variable);
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        double startValue=Double.parseDouble(simplifiedLine.get(myStartingIndex+3));
        myVariableValue=startValue;
        myEnd=Double.parseDouble(simplifiedLine.get(myStartingIndex+4));
        myIncrement=Double.parseDouble(simplifiedLine.get(myStartingIndex+5));
        myIndexOfList=myStartingIndex+6;
        if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myEnd) {
            evaluateLineSection(myIndexOfList, simplifiedLine);
            myVariableValue+=myIncrement;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
        //return last command result
    }
}

