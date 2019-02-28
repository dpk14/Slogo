import java.util.ArrayList;

public class If extends ControlStructure {
    private int myIndexOfList;
    private double myVariableValue;
    private String myVariableName;
    private double myLimit;

    @Override
    public double executeCode(){
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        double simplifiedExpression=Double.parseDouble(simplifiedLine.get(myStartingIndex+1));
        myIndexOfList=myStartingIndex+2;
        if (!simplifiedLine.get( myIndexOfList).equals("[")); //throw error
        if(simplifiedExpression==1) {
            evaluateLineSection(myIndexOfList, simplifiedLine);
        }
        //return last command result
    }
}