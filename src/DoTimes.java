import javax.sound.sampled.Control;
import java.util.ArrayList;
import java.util.Stack;

public class DoTimes extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;

    @Override
    public double executeCode(){
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=removeColon(variable);
        myVariableValue=0;
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        myLimit=Double.parseDouble(simplifiedLine.get(myStartingIndex+3));
        myIndexOfList=myStartingIndex+4;
        if (!simplifiedLine.get(myIndexOfList).equals("[")); //throw error
        while(myVariableValue<myLimit) {
            evaluateLineSection(myIndexOfList, simplifiedLine);
            myVariableValue++;
            myStorage.setVariableValue(myVariableName, myVariableValue);
        }
        //return last command result
    }
}

