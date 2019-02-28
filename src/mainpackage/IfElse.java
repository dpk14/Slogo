package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class IfElse extends ControlStructure {
    private int myIndexOfFirstList;
    private int myIndexOfSecondList;

    public IfElse(int numOfListArguments, ProgramParser parser, SystemStorage storage){
        super(numOfListArguments, parser, storage);
    }

    @Override
    public double executeCode() {
        ArrayList<String> simplifiedLine = evaluateLineSection(myStartingIndex + 1, myUserInput);
        double simplifiedExpression = Double.parseDouble(simplifiedLine.get(myStartingIndex + 1));
        myIndexOfFirstList = myStartingIndex + 2;
        if (!simplifiedLine.get(myIndexOfFirstList).equals("[")) ; //throw error
        myIndexOfSecondList = findIndexOfNextList(myIndexOfFirstList);
        if (!simplifiedLine.get(myIndexOfSecondList).equals("[")) ; //throw error
        List<Command> previousCommandLog = myStorage.getMyCommandLog();
        if (simplifiedExpression == 1) {
            evaluateLineSection(myIndexOfFirstList, simplifiedLine);
        } else {
            evaluateLineSection(myIndexOfSecondList, simplifiedLine);
        }

        List<Command> currentCommandLog = myStorage.getMyCommandLog();
        if (previousCommandLog.size() != currentCommandLog.size()) {
            return currentCommandLog.get(currentCommandLog.size() - 1).getReturnValue();
        }
        return 0;
    }
}