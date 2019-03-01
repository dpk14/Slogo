package mainpackage;

import java.util.ArrayList;
import java.util.List;

public class Repeat extends ControlStructure {
    double myTimesToRepeat;
    int myIndexOfList;

    public Repeat(int numOfListArguments, ProgramParser parser, SystemStorage storage) {
        super(numOfListArguments, parser, storage);
    }

    @Override
    public ControlStructure copy() {
        return new Repeat(myNumOfListArguments, myParser, myStorage);
    }

    // performs updates on Simplifiable copy, then adjusts myUserInput to equal this, until it's simplified
    @Override
    protected void simplifyAndExecuteStructure() {
        int counter = 0;
        do {
            simplifyAndEvaluate(mySimplifiableLine, myStartingIndex + 1);
            myTimesToRepeat = Double.parseDouble(mySimplifiableLine.get(myStartingIndex + 1)); //TODO: change to say MyParser.parseValue in case its a vari
            if (myTimesToRepeat == 0) return;
            myIndexOfList = myStartingIndex + 2;
            if (!mySimplifiableLine.get(myIndexOfList).equals("[")) ;
            simplifyAndEvaluate(mySimplifiableLine, myIndexOfList);
            if (counter != myTimesToRepeat - 1) resetSimplification(mySavedLine);
            counter++;
        } while (counter < myTimesToRepeat);
    }
}


