import javax.sound.sampled.Control;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        String variable=myUserInput.get(myStartingIndex+2);
        myVariableName=myParser.removeColon(variable);
        myVariableValue=0;
        ArrayList<String> simplifiedLine=evaluateLineSection(myStartingIndex+1, myUserInput);
        myLimit=Double.parseDouble(simplifiedLine.get(myStartingIndex+3));
        myIndexOfList=myStartingIndex+4;
        if (!simplifiedLine.get(myIndexOfList).equals("[")); //throw error
        List<Command> previousCommandLog=myStorage.getMyCommandLog();
        while(myVariableValue<myLimit) {
            evaluateLineSection(myIndexOfList, simplifiedLine);
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

