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
    public void convertCodeToCommands() {
        simplifyLineSection(myStartingIndex + 1);
        double simplifiedExpression = Double.parseDouble(myUserInput.get(myStartingIndex + 1));
        myIndexOfFirstList = myStartingIndex + 2;
        if (!myUserInput.get(myIndexOfFirstList).equals("[")) ; //throw error
        myIndexOfSecondList = findIndexOfNextList(myIndexOfFirstList, myUserInput);
        if (!myUserInput.get(myIndexOfSecondList).equals("[")) ; //throw error
        List<Command> parsedList;
        if (simplifiedExpression == 1) {
            parsedList=simplifyLineSection(myIndexOfFirstList);
        } else {
            parsedList=simplifyLineSection(myIndexOfSecondList);
        }
        myCommands.addAll(parsedList);
    }
}